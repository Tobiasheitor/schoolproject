package br.com.alura.escolalura.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubjectStudentNotesDTO {

  private String subjectName;
  private List<Double> notes;

}
