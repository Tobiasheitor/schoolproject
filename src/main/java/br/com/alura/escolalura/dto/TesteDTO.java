package br.com.alura.escolalura.dto;

import br.com.alura.escolalura.entity.Student;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class TesteDTO {

  private String nomeDoCurso;
  private Student student;
  private List<String> notes;

  public TesteDTO() {
    List<String> tempNotes = new ArrayList<>();
    tempNotes.add("0");
    tempNotes.add("0");
    tempNotes.add("0");
    this.notes = tempNotes;
  }

}
