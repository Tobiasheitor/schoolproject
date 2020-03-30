package br.com.alura.escolalura.service;

import br.com.alura.escolalura.entity.Student;
import org.bson.types.ObjectId;

import java.util.List;

public interface StudentService {

    Student save(Student student);
    Student get(ObjectId id);
    List<Student> getAll();
    List<Student> filter(String name);

}
