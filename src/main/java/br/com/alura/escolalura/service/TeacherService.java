package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelTeacher;
import br.com.alura.escolalura.entity.Teacher;

public interface TeacherService {

    Teacher saveTeacher(ModelTeacher teacher);
}
