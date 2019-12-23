package cn.ekgc.mybatisdemo;

import cn.ekgc.mybatisdemo.dao.StudentMapper;
import cn.ekgc.mybatisdemo.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

import static org.apache.ibatis.mapping.SqlCommandType.INSERT;

public class TestMybatis  {
    public static void main(String[] args) throws Exception{
        //定义字符串变量，指明配置文件路径
        String resource = "mybatis.cfg.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        //getMapper方法动态代理动态生成studentMapper接口的实现类
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("张三");
        student.setAge(17);
        student.setGender("女");
        studentMapper.save(student);
        System.out.println("================");
        List<Student> studentList = studentMapper.list();
        for(Student stu:studentList){
            System.out.println(stu);
        }
        sqlSession.commit();
        sqlSession.close();
        System.out.println("ok....");



    }
}
