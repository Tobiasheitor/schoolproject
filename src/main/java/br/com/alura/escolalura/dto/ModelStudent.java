package br.com.alura.escolalura.dto;

import br.com.alura.escolalura.constants.ProjectConstants;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Data
public class ModelStudent {

  @NotEmpty(message = "Nome é obrigatório")
  private String name;
  private ObjectId courseId;
  private ContactDTO contact;

  @NotNull
  @DateTimeFormat(pattern = ProjectConstants.DATE_PATTERN)
  private LocalDate birthDate;

  public ModelStudent() {
    this.contact = new ContactDTO();
  }
}
