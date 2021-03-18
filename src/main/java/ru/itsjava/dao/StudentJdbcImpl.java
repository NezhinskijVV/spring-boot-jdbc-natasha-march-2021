package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Student;

@SuppressWarnings("all")
@Repository
@RequiredArgsConstructor
public class StudentJdbcImpl implements StudentDao {
    private final JdbcOperations jdbcOperations;

    @Override
    public int countStudents() {
        return jdbcOperations.queryForObject("select count(*) from students", Integer.class);
    }

    @Override
    public void insertStudent(Student student) {
        jdbcOperations.update("insert into students(id, fio) values (?, ?) ", student.getId(), student.getFio());
    }
}
