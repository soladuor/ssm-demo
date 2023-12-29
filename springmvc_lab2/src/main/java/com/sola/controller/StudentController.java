package com.sola.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.sola.pojo.SchoolClass;
import com.sola.pojo.Student;
import com.sola.service.SchoolClassService;
import com.sola.service.StudentService;
import com.sola.utils.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/student")
@RestController()
public class StudentController extends BaseController {
    // TODO: 不让用spring的东西，看看他发的东西里面怎么用的，将这个实验关于spring的部分去掉吧
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

    @GetMapping("/getById/{id}")
    public JSONResult getById(@PathVariable("id") String id) {
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

    @GetMapping("/delete/{id}")
    public JSONResult delete(@PathVariable String id) {
        studentService.delete(id);
        return JSONResult.ok();
    }

}