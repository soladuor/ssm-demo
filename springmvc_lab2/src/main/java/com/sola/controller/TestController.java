package com.sola.controller;

import com.sola.pojo.Student;
import com.sola.utils.result.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    /**
     * 前往testMessage页面
     */
    @GetMapping("/testMessage")
    public ModelAndView testMessage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "测试消息");
        mav.setViewName("test/testMessage");
        return mav;
    }

    /**
     * 测试返回对象转JSON
     */
    @GetMapping("/testJsonData")
    @ResponseBody
    public JSONResult testObj() {
        Student student = new Student();
        student.setId("1");
        student.setStudentName("张三");
        return JSONResult.ok(student);
    }

    /**
     * 测试集合传参并返回
     */
    @PostMapping("/testListData")
    @ResponseBody
    public JSONResult test(@RequestBody List<Student> stuList) {
        System.out.println(stuList);
        return JSONResult.ok(stuList);
    }

}
