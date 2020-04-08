package br.com.alura.escolalura.entity;

import br.com.alura.escolalura.constants.ProjectConstants;
import br.com.alura.escolalura.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    private ObjectId id;

    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    private String course;

    @NotNull
    @DateTimeFormat(pattern = ProjectConstants.DATE_PATTERN)
    private LocalDate birthDate;

    private ContactDTO contact;

}

