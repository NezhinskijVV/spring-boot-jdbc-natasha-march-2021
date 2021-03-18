package ru.itsjava.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Student;

@JdbcTest
@Import(StudentJdbcImpl.class)
public class StudentJdbcImplTest {

    @Autowired
    private StudentDao studentJdbc;


    @Test
    public void shouldHaveCorrectInsert() {
        int countBeforeInsert = studentJdbc.countStudents();
        studentJdbc.insertStudent(new Student(2, "Vanya"));
        int countAfterInsert = studentJdbc.countStudents();

        Assertions.assertEquals(countBeforeInsert + 1, countAfterInsert);
    }
}
