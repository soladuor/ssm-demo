package com.sola.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.sola.pojo.SchoolClass;
import com.sola.pojo.Student;
import com.sola.service.SchoolClassService;
import com.sola.service.StudentService;
import com.sola.utils.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping("/getAll")
    public JSONResult getAll(HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Student> page = studentService.findPage(filters);
        return JSONResult.ok(page);
    }

    @GetMapping("/getById")
    public JSONResult getById(@RequestParam("id") String id) {
        if (StringUtil.isEmpty(id)) {
            return JSONResult.fail("id不能为空");
        }
        return JSONResult.ok(studentService.getById(id));
    }

    @PostMapping("/insert")
    public JSONResult insert(Student student, String classId) {
        if (StringUtil.isNotEmpty(classId)) {
            SchoolClass schoolClass = schoolClassService.getById(classId);
            if (schoolClass == null) {
                throw new RuntimeException("班级不存在");
            } else {
                student.setSchoolClass(schoolClass);
            }
        }
        Integer num = studentService.insert(student);
        return JSONResult.ok("影响行数: " + num + "行");
    }

    @PostMapping("/update")
    public JSONResult update(Student student, String classId) {
        if (StringUtil.isNotEmpty(classId)) {
            SchoolClass schoolClass = schoolClassService.getById(classId);
            if (schoolClass == null) {
                throw new RuntimeException("班级不存在");
            } else {
                student.setSchoolClass(schoolClass);
            }
        }
        Integer num = studentService.update(student);
        return JSONResult.ok("影响行数: " + num + "行");
    }

    @GetMapping("/delete")
    public JSONResult delete(String id) {
        studentService.delete(id);
        return JSONResult.ok();
    }

}