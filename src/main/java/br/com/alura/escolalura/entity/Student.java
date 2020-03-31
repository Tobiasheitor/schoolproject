package br.com.alura.escolalura.entity;

import br.com.alura.escolalura.dto.ContactDTO;
import br.com.alura.escolalura.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    private ObjectId id;
    private String name;
    private ZonedDateTime birthDate;
    private CourseDTO course;
    private ContactDTO contact;

}
