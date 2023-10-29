import com.google.gson.Gson;
import com.sola.mapper.SchoolClassMapper;
import com.sola.mapper.StudentMapper;
import com.sola.pojo.SchoolClass;
import com.sola.pojo.Student;
import com.sola.service.SchoolClassService;
import com.sola.service.StudentService;
import com.sola.service.impl.SchoolClassServiceImpl;
import com.sola.service.impl.StudentServiceImpl;
import com.sola.utils.MapperTools;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMapping {

    // mapper
    private final StudentMapper studentMapper = MapperTools.getMapper(StudentMapper.class);
    private final SchoolClassMapper schoolClassMapper = MapperTools.getMapper(SchoolClassMapper.class);
    // service
    private final StudentService studentService = new StudentServiceImpl();
    private final SchoolClassService schoolClassService = new SchoolClassServiceImpl();

    SqlSession sqlSession = MapperTools.getSqlSession();
    private final Gson gson = new Gson();

    private String toJson(Object data) {
        return gson.toJson(data);
    }

    @Test
    public void test01() {
        Student student = studentMapper.getById_annotation("20211677");
        System.out.println("student = " + student);
    }

    @Test
    public void test02() {
        SchoolClass schoolClass = schoolClassMapper.getById_annotation(1);
        System.out.println("schoolClass = " + schoolClass);
    }

    @Test
    public void test03() {
        List<Student> studentList = studentMapper.getStuListByClassId_annotation(2);
        System.out.println("studentList = " + studentList);
    }

}
