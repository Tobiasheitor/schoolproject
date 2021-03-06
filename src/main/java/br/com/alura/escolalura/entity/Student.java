package br.com.alura.escolalura.entity;

import br.com.alura.escolalura.dto.ContactDTO;
import br.com.alura.escolalura.dto.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {

    @Id
    private ObjectId id;
    private String name;
    private String sponsor;
    private LocalDate birthDate;
    private List<SubjectDTO> subjects;
    private ContactDTO contact;

}

