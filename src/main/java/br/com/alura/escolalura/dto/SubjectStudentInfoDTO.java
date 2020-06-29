package br.com.alura.escolalura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStudentInfoDTO {

    String studentName;
    List<String> notes;

}
