package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.Result;
import com.Tzj.lost_found_system.pojo.User;
import com.Tzj.lost_found_system.service.LoginService;
import com.Tzj.lost_found_system.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/userLogin") //登录
    public Result login(String userName, String password) {
        User user = loginService.login(userName, password);
        System.out.println(user);
        if (user != null) {
            Map<String, Object> clamis = new HashMap<>();
            clamis.put("uid", user.getId());
            clamis.put("uname", user.getUserName());
            clamis.put("password", user.getPassword());
            String jwt = JwtUtils.generateJwt(clamis);
            log.info("令牌：{}", jwt);
            return Result.successAndObject(jwt,user);
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/isLogin")
    public Result isLogin(HttpServletRequest req){
        String jwt= req.getHeader("token");
        System.out.println(jwt);
        log.info("令牌：{}",jwt);
        try {
            JwtUtils.testParseJwt(jwt);
        }catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败");
            Result error = Result.error("NOT_ERROR");
            return error;
        }

        return Result.success();
    }
}
