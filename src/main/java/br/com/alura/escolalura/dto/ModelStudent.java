package br.com.alura.escolalura.dto;

import br.com.alura.escolalura.constants.ProjectConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ModelStudent {

  private String name;
  private ObjectId courseId;
    private String sponsor;
    private ContactDTO contact;

  @NotNull
  @DateTimeFormat(pattern = ProjectConstants.DATE_PATTERN)
  private LocalDate birthDate;

  public ModelStudent() {
    this.contact = new ContactDTO();
  }
}
