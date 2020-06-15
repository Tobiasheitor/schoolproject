package br.com.alura.escolalura.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectDTO {

  private ObjectId subjectId;
  private List<Double> notes;

}
