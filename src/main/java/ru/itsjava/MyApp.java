package ru.itsjava;


import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.dao.StudentDao;
import ru.itsjava.domain.Faculty;
import ru.itsjava.domain.Student;

import java.sql.SQLException;

@SpringBootApplication
public class MyApp {

    public static void main(String[] args) throws SQLException {
        var context = SpringApplication.run(MyApp.class);

        StudentDao dao = context.getBean(StudentDao.class);
        System.out.println("Before insert context.getBean(StudentDao.class).countStudents() = "
                + dao.countStudents());
//
        System.out.println(dao.insertStudent(new Student(0L, "Mark", new Faculty(1L, "ola"))));
//
        System.out.println("After insert context.getBean(StudentDao.class).countStudents() = "
                + dao.countStudents());
//
//        System.out.println(dao.findById(1L));
//        Console.main(args);

        System.out.println(dao.findById(1L));
        System.out.println(dao.findById(2L));
        System.out.println(dao.findById(3L));
        System.out.println(dao.findById(4L));
        System.out.println(dao.findById(40L));
    }
}

//DAo для скидок
// Insert, Update, Delete + кол-во скидок + SQL (лекции курсы)
// Переписать с помощью NamedParameterJdbcOperations и KeyHolder
//решить проблему дубликатов на уровне сервисов
//тесты
//

