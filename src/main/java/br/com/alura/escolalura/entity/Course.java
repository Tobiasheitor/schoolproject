package br.com.alura.escolalura.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Builder
@Data
public class Course implements Serializable {

  private static final long serialVersionUID = -6145184211921150731L;
  private static final String DEFAULT_NAME = "";

  @Id
  private ObjectId id;

  @Indexed(unique = true)
  private String name;
  private String description;
  private List<ObjectId> subjects;
  private List<ObjectId> students;

  public Course() {
    this.subjects = new ArrayList<>();
    this.students = new ArrayList<>();
  }

}
