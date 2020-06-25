package br.com.alura.escolalura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectWithNameDTO {

    private ObjectId subjectId;
    private String name;
    private List<Double> notes;

}
