package cc.qianglovepei.service;

import cc.qianglovepei.model.User;

import java.util.List;

public interface UserService {

    Integer login(String userName, String passWord);

    User findByName(String userName);

    List<User> findAllUser();

    Integer save(User user);

    Integer remove(Long id);

}
