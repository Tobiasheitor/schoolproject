package br.com.alura.escolalura.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Subject implements Serializable {

    private static final long serialVersionUID = -8782046828386962196L;

    @Id
    private ObjectId subjectId;
    private String name;
    private String dayOfWeek;

}
