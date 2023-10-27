package com.sola.mapper;

import com.github.pagehelper.Page;
import com.sola.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {

    /*
        // 子查询式（n+1）
        @Select("SELECT id, class_id, student_name, logo_url, grade, a_grade, b_grade, c_grade, z_grade" +
                " FROM students" +
                " WHERE id=#{id}")
        @Results(id = "stu_and_class", value = {
                @Result(property = "schoolClass", column = "{id=class_id}",
                        one = @One(select = "com.sola.mapper.SchoolClassMapper.getById"))
        })
    */
    // 多表联查式（关联查询）
    @Select("SELECT students.id, class_id, student_name, logo_url, grade, a_grade, b_grade, c_grade, z_grade, school_classes.class_name" +
            " FROM students, school_classes" +
            " WHERE students.id = #{id}" +
            " AND school_classes.id = class_id")
    @Results({
            @Result(property = "schoolClass.id", column = "class_id"),
            @Result(property = "schoolClass.className", column = "class_name")
    })
    Student getById_annotation(String id);

    /**
     * 注解实现查询所有
     */
    @Select("SELECT id, class_id, student_name, logo_url, grade, a_grade, b_grade, c_grade, z_grade" +
            " FROM students" +
            " WHERE is_deleted = 0")
    List<Student> findAll_annotation();

    /**
     * 注解实现增加
     */
    @Insert("INSERT INTO students(id, class_id, student_name, logo_url, grade, a_grade, b_grade, c_grade, z_grade) " +
            "VALUES (#{id}, #{schoolClass.id}, #{studentName}, #{logoUrl}, #{grade}, #{aGrade}, #{bGrade}, #{cGrade}, #{zGrade})")
    Integer insert_annotation(Student student);

    /**
     * 注解实现逻辑删除
     */
    @Update("UPDATE students SET is_deleted  = 1, update_time = NOW()" +
            " WHERE id = #{id}")
    void delete_annotation(String id);

    /**
     * 分页操作
     *
     * @param filters 过滤条件
     * @return 分页结果
     */
    Page<Student> findPage(Map<String, Object> filters);

}
