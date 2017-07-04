package cc.qianglovepei.web;

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
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home() {
        return "Hello World!";
    }

    @ResponseBody
    @RequestMapping(value="/getAllUser",method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userService.findAllUser();
    }


    @ResponseBody
    @RequestMapping(value="/user",method = RequestMethod.POST)
    public void saveUser(@RequestBody User user){
        userService.save(user);
    }

}
