package cc.qianglovepei.mappers;

import cc.qianglovepei.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cumt_ on 2017/1/7.
 */
@Mapper
public interface UserMapper {

    Integer login(@Param("userName") String userName, @Param("passWord") String passWord);

    User findByName(@Param("userName") String userName);

    List<User> findAllUser();

    Integer save(User user);

    Integer remove(@Param("id") Long id);
}
