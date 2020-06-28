package br.com.alura.escolalura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    private String course;
    private String students;
    private List<String> subjects;
    private List<String> notes;

}
