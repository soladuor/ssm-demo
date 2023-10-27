package com.sola.servlet;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.Gson;
import com.sola.pojo.SchoolClass;
import com.sola.pojo.Student;
import com.sola.service.SchoolClassService;
import com.sola.service.StudentService;
import com.sola.service.impl.SchoolClassServiceImpl;
import com.sola.service.impl.StudentServiceImpl;
import com.sola.servlet.base.BaseServlet;
import com.sola.utils.result.JSONResult;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends BaseServlet {

    private final StudentService studentService = new StudentServiceImpl();
    private final SchoolClassService schoolClassService = new SchoolClassServiceImpl();

    public JSONResult getAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Student> page = studentService.findPage(filters);
        return JSONResult.ok(page);
    }

    public JSONResult getById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (StringUtil.isEmpty(id)) {
            return JSONResult.fail("id不能为空");
        }
        return JSONResult.ok(studentService.getById(id));
    }

    public JSONResult insert(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String studentName = request.getParameter("studentName");
        if (StringUtil.isEmpty(id) || StringUtil.isEmpty(studentName)) {
            return JSONResult.fail("id和姓名不能为空");
        }
        Student student = parseStudent(request, response);
        Integer num = studentService.insert(student);
        return JSONResult.ok("影响行数: " + num + "行");
    }

    public JSONResult update(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String studentName = request.getParameter("studentName");
        if (StringUtil.isEmpty(id) || StringUtil.isEmpty(studentName)) {
            return JSONResult.fail("id和姓名不能为空");
        }
        Student student = parseStudent(request, response);
        Integer num = studentService.update(student);
        return JSONResult.ok("影响行数: " + num + "行");
    }

    public JSONResult delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (StringUtil.isEmpty(id)) {
            return JSONResult.fail("id不能为空");
        }
        studentService.delete(id);
        return JSONResult.ok();
    }

    private Student parseStudent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("构建参数parameterMap = " + new Gson().toJson(parameterMap));
        Student student = new Student();

        try {
            BeanUtils.populate(student, parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 设置班级
        String classId = request.getParameter("classId");
        if (StringUtil.isNotEmpty(classId)) {
            SchoolClass schoolClass = schoolClassService.getById(classId);
            if (schoolClass == null) {
                throw new RuntimeException("班级不存在");
            } else {
                student.setSchoolClass(schoolClass);
            }
        }
        System.out.println("构建的student = " + student);
        return student;
    }

}