package br.com.alura.escolalura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditStudent {

    private String studentId;
    private String courseId;
    private String name;
    private LocalDate birthDate;
    private String address;

}
