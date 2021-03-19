package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Faculty;
import ru.itsjava.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("all")
@Repository
@RequiredArgsConstructor
public class StudentJdbcImpl implements StudentDao {
    //    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int countStudents() {
        return namedParameterJdbcOperations.
                getJdbcOperations()
                .queryForObject("select count(*) from students", Integer.class);
    }

    @Override
    public long insertStudent(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        val parametrs = new MapSqlParameterSource();
        parametrs.addValue("fio", student.getFio());
        parametrs.addValue("facId", student.getFaculty().getId());

        namedParameterJdbcOperations.update(" insert into students(fio, faculty_id) values (:fio,:facId )",
                parametrs, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Student> findById(long id) {
        try {
            return Optional.of(namedParameterJdbcOperations.queryForObject(
                    "select s.id, s.fio, f.id, f.name from students s, faculties f " +
                            "where s.id = :id and s.faculty_id = f.id",
                    Map.of("id", id),
                    new StudentMapper()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    private static class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            long idStudent = resultSet.getLong("students.id");
            String fioStudent = resultSet.getString("students.fio");
            long idFac = resultSet.getLong("faculties.id");
            String nameFac = resultSet.getString("faculties.name");

            Faculty facultyStudent = new Faculty(idFac, nameFac);
            return new Student(idStudent, fioStudent, facultyStudent);
        }
    }


}
