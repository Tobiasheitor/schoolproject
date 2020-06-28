package br.com.alura.escolalura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubjectStudentNotesDTO {

  private String subjectName;
  private List<String> notes;

}
