package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelStudent;
import br.com.alura.escolalura.entity.Student;

public interface StudentService {

  Student save(ModelStudent modelStudent);

}
