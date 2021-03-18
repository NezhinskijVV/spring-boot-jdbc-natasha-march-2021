package ru.itsjava;


import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.dao.StudentDao;
import ru.itsjava.domain.Student;

import java.sql.SQLException;

@SpringBootApplication
public class MyApp {

    public static void main(String[] args) throws SQLException {
        var context = SpringApplication.run(MyApp.class);

        StudentDao dao = context.getBean(StudentDao.class);
        System.out.println("Before insert context.getBean(StudentDao.class).countStudents() = "
                + dao.countStudents());

        dao.insertStudent(new Student(3L, "Mark"));

        System.out.println("After insert context.getBean(StudentDao.class).countStudents() = "
                + dao.countStudents());
        Console.main(args);
    }
}

//DAo для скидок
// Insert, Update, Delete + кол-во скидок + SQL (лекции курсы)

