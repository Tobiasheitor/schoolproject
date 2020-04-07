package br.com.alura.escolalura.service;

import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        log.info("Service start - save student: {}", student);
        Student result;

        result = studentRepository.save(student);

        log.debug("Service end - saved student: {}", result);
        return result;
    }

    @Override
    public Student get(ObjectId id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public List<Student> filter(String name) {
        return null;
    }
}
