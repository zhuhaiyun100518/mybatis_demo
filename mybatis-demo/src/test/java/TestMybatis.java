import cn.ekgc.mybatisdemo.dao.StudentMapper;
import cn.ekgc.mybatisdemo.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    private  SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws Exception{
        System.out.println("初始化资源");
        //定义字符串变量，指明配置文件的路径
        String resource = "mybatis.cfg.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @After
    public void destroy(){
         //做资源释放
        System.out.println("释放资源");
    }
    @Test
    public void testsave(){
        Student student1 = new Student();
        student1.setName("王五");
        student1.setAge(14);
        student1.setGender("男");
        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(34);
        student2.setGender("男");
        Student student3 = new Student();
        student3.setName("钱八");
        student3.setAge(14);
        student3.setGender("男");
        SqlSession sqlSession=sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        studentMapper.save(student1);
        studentMapper.save(student2);
        studentMapper.save(student3);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testlist(){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.list();
        students.forEach(student -> System.out.println(student));
    }
}
