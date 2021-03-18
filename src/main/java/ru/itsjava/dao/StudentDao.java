package ru.itsjava.dao;

import ru.itsjava.domain.Student;

public interface StudentDao {
    int countStudents();
    void insertStudent(Student student);
}
