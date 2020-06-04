package br.com.alura.escolalura.entity;

import br.com.alura.escolalura.constants.Days;
import br.com.alura.escolalura.dto.SubjectDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Builder
@Data
public class Course {

    private static final String DEFAULT_NAME = "";

    @Id
    private ObjectId id;

  @Indexed(unique = true)
  @NotEmpty(message = "Nome obrigatório")
  private String name;

  private String description;

  private List<SubjectDTO> subject;

  private List<Student> students;

  public Course() {
    this.subject = Arrays.asList(
        new SubjectDTO(Days.MONDAY, DEFAULT_NAME),
        new SubjectDTO(Days.TUESDAY, DEFAULT_NAME),
        new SubjectDTO(Days.WEDNESDAY, DEFAULT_NAME),
        new SubjectDTO(Days.THURSDAY, DEFAULT_NAME),
        new SubjectDTO(Days.FRIDAY, DEFAULT_NAME));
    this.students = new ArrayList<>();
  }
}
