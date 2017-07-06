package cc.qianglovepei.service.impl;

import cc.qianglovepei.config.DataSourceConfig;
import cc.qianglovepei.mappers.UserMapper;
import cc.qianglovepei.model.User;
import cc.qianglovepei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override public Integer login(String userName, String passWord) {
        return userMapper.login(userName, passWord);
    }

    @Override public User findByName(String userName) {
        return null;
    }

    @Override public List<User> findAllUser() {
        String currentDataSource = DataSourceConfig.DynamicDataSource.getCurrentLookupKey();
        System.out.println("findAllUser 的数据源是[" + currentDataSource + "]");
        return userMapper.findAllUser();
    }

    @Override public Integer save(User user) {
        String currentDataSource = DataSourceConfig.DynamicDataSource.getCurrentLookupKey();
        System.out.println("save 的数据源是[" + currentDataSource + "]");
        return userMapper.save(user);
    }

    @Override public Integer remove(Long id) {
        return userMapper.remove(id);
    }
}
