package ru.itsjava.dao;

import ru.itsjava.domain.Student;

import java.util.Optional;

public interface StudentDao {
    int countStudents();
    long insertStudent(Student student);
    Optional<Student> findById(long id);
}
