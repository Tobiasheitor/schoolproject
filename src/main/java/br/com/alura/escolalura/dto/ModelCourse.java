package br.com.alura.escolalura.dto;

import br.com.alura.escolalura.constants.Days;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ModelCourse {

  private static final String DEFAULT_NAME = "";

  private String name;
  private String description;
  private List<ModelSubject> subject;

  public ModelCourse() {
    this.subject = Arrays.asList(
        new ModelSubject(Days.MONDAY, DEFAULT_NAME),
        new ModelSubject(Days.TUESDAY, DEFAULT_NAME),
        new ModelSubject(Days.WEDNESDAY, DEFAULT_NAME),
        new ModelSubject(Days.THURSDAY, DEFAULT_NAME),
        new ModelSubject(Days.FRIDAY, DEFAULT_NAME));
  }
}
