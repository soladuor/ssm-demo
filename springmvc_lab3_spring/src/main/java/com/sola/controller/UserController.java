package com.sola.controller;

import com.sola.pojo.FileSpace;
import com.sola.pojo.FileUser;
import com.sola.service.UserService;
import com.sola.utils.MySessionContext;
import com.sola.utils.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/signup")
    public JSONResult signup(@RequestBody FileUser user) {
        FileUser signupUser = userService.signup(user);
        // 注册失败
        if (signupUser == null) {
            return JSONResult.errorMsg("用户已注册");
        }
        // 注册成功
        return JSONResult.ok(signupUser);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public JSONResult login(@RequestBody FileUser user, HttpSession session, HttpServletResponse response) {
        FileUser loginUser = userService.login(user);
        // 登录失败
        if (loginUser == null) {
            return JSONResult.errorMsg("id或密码错误");
        }
        // 登录成功
        session.setAttribute("user", loginUser);
        // 设置凭据（处理cookie，前端得在请求头中加上myAuth）
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(60 * 60);// 设置过期时间(单位秒)，1小时
        response.addCookie(cookie);
        System.out.println(session.getId() + " === " + session.getAttribute("user"));
        return JSONResult.ok(session.getId());
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping
    public JSONResult getUserMessage(@RequestHeader("myAuth") String myAuth) {
        MySessionContext instance = MySessionContext.getInstance();
        HttpSession session = instance.getSession(myAuth);
        // 由于拦截器已经处理了为空的情况，这里不做处理
        FileUser user = (FileUser) session.getAttribute("user");
        FileSpace fileSpace = userService.getFileSpace(user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("username", user.getUsername());
        map.put("fileSpaceId", fileSpace.getId());
        map.put("fileSpaceSize", fileSpace.getSpaceSize());
        return JSONResult.ok(map);
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public JSONResult logout(@RequestHeader("myAuth") String myAuth) {
        MySessionContext instance = MySessionContext.getInstance();
        HttpSession session = instance.getSession(myAuth);
        session.invalidate();
        return JSONResult.ok();
    }

}
