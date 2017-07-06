package cc.qianglovepei.web;

import cc.qianglovepei.common.Result;
import cc.qianglovepei.exception.MyException;
import cc.qianglovepei.model.User;
import cc.qianglovepei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class testController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<String> home() {
        return Result.Success("hello World!");
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public void getError() throws Exception {
        throw new MyException("测试错误");
    }

    @ResponseBody
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public Result<List<User>> getAllUser() {
        return Result.Success(userService.findAllUser());
    }


    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

}
