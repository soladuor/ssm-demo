package com.sola.mapper;

import com.sola.pojo.SchoolClass;
import com.sola.pojo.Student;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SchoolClassMapper extends BaseMapper<SchoolClass> {

    // 一对多的多表联查（n+1式）
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "studentList", column = "id",
                    many = @Many(select = "com.sola.mapper.SchoolClassMapper.getStudentListByClassId")
            )
    })
    @Select("SELECT id, class_name" +
            " FROM school_classes" +
            " WHERE id = #{id}")
    SchoolClass getById_annotation(Integer id);    // 注解和配置文件在单个方法上不能混用

    // 一对多的多表联查（子查询）
    @Select("SELECT id, class_id, student_name, logo_url, grade, a_grade, b_grade, c_grade, z_grade" +
            " FROM students" +
            " WHERE class_id = #{classId}")
    List<Student> getStudentListByClassId(Integer classId);

}
