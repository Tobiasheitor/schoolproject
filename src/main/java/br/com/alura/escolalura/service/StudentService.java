package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelStudent;
import br.com.alura.escolalura.dto.SubjectStudentNotesDTO;
import br.com.alura.escolalura.entity.Student;

import java.util.List;

public interface StudentService {

  Student save(ModelStudent modelStudent);

  List<SubjectStudentNotesDTO> getStudentSubjects(String studentId);

}
