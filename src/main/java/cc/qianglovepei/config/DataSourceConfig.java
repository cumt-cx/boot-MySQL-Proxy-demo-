package cc.qianglovepei.config;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Configuration
public class DataSourceConfig {

    private static final String DATASOURCE_WRITE = "write";

    private static final String DATASOURCE_READ1 = "read1";

    private static final String DATASOURCE_READ2 = "read2";

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //
        return dynamicDataSource;
    }

    @Bean(DATASOURCE_WRITE)
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource WriteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DATASOURCE_READ1)
    @ConfigurationProperties(prefix = "datasource.read1")
    public DataSource Read1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DATASOURCE_READ2)
    @ConfigurationProperties(prefix = "datasource.read2")
    public DataSource Read2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public WRInterceptor mapperInterceptor() {
        return new WRInterceptor();
    }

    public static class DynamicDataSource extends AbstractRoutingDataSource implements BeanFactoryAware {

        private static ThreadLocal<String> currentLookupKeyLocal = new ThreadLocal<String>();
        private BeanFactory beanFactory;

        public static String getCurrentLookupKey() {
            return currentLookupKeyLocal.get();
        }

        public static void setCurrentLookupKey(String key) {
            currentLookupKeyLocal.set(key);
        }

        @Override
        protected Object determineCurrentLookupKey() {
            return currentLookupKeyLocal.get();
        }

        @Override
        public void afterPropertiesSet() {
            //
            Map<String, DataSource> targetDataSources = BeanFactoryUtils
                    .beansOfTypeIncludingAncestors((ListableBeanFactory) beanFactory, DataSource.class);
            super.setTargetDataSources(new HashMap<Object, Object>(targetDataSources));
            //
            super.afterPropertiesSet();
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }
    }

    @Aspect
    private static class WRInterceptor {

        @Around("execution(* cc.qianglovepei.service..*.*(..))")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            // 获取到当前执行的方法名
            String methodName = joinPoint.getSignature().getName();
            if (isSlave(methodName)) {
                // 标记为读库,可以自定义选择数据源
                Random random = new Random();
                int randomNum = random.nextInt(3);
                if (randomNum == 0) {
                    DynamicDataSource.setCurrentLookupKey(DATASOURCE_READ1);
                } else if (randomNum == 1) {
                    DynamicDataSource.setCurrentLookupKey(DATASOURCE_READ2);
                } else if (randomNum == 2) {
                    DynamicDataSource.setCurrentLookupKey(DATASOURCE_WRITE);
                }
            } else {
                // 标记为写库
                DynamicDataSource.setCurrentLookupKey(DATASOURCE_WRITE);
            }
            return joinPoint.proceed();
        }

        /**
         * 判断是否为读库
         *
         * @param methodName
         * @return
         */
        private Boolean isSlave(String methodName) {
            // 方法名以query、find、get开头的方法名走从库
            return StringUtils.startsWithAny(methodName, new String[] { "query", "find", "get" });
        }

    }


}
