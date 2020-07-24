package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.dto.*;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.StudentRepository;
import br.com.alura.escolalura.service.CourseService;
import br.com.alura.escolalura.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class StudentController {
    private static final String HOME_PAGE = "redirect:/";
    private static final String PAGE_REGISTER = "aluno/cadastrar";
    private static final String PAGE_NOTE_REGISTER = "nota/cadastrar";
    private static final String PAGE_MANAGE = "aluno/gerenciar";
    private static final String EDIT_STUDENT_PAGE = "aluno/editar";
    private static final String INPUT_NOTES_FRAGMENT = "layout/fragments :: input_notes";

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping(PAGE_REGISTER)
    public String register(Model model) {
        ModelStudent modelStudent = new ModelStudent();

        List<Course> courses = courseService.getAll();

        model.addAttribute("student", modelStudent);
        model.addAttribute("courses", courses);

        return PAGE_REGISTER;
    }

    @PostMapping("/student/save")
    public String save(@ModelAttribute @Valid ModelStudent modelStudent, BindingResult bindingResult) {
        log.info("Controller start - save student: {}", modelStudent);

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }

        Student studentSaved = studentService.save(modelStudent);

        log.info("StudentController.save end - student {}", studentSaved);
        return HOME_PAGE;
    }

    @GetMapping(PAGE_NOTE_REGISTER)
    public String registerNotes(Model model) {
        log.info("Controller.registerNotes start");

        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("teste", new TesteDTO());

        return PAGE_NOTE_REGISTER;
    }

    @PostMapping("/note/save")
    public ResponseEntity<String> saveNote(@RequestBody CreateUserDTO createUserDTO) {
        log.info("StudentController.saveNote - Start - createUserDTO {}", createUserDTO);

        studentService.saveNotes(createUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @GetMapping("/student/{studentId}/subjects")
    public String getSubjects(Model model, @PathVariable("studentId") String studentId) {
        log.info("StudentController.getSubjects - studentId: {}", studentId);

        List<SubjectStudentNotesDTO> studentSubjects = studentService.getStudentSubjects(studentId);

        model.addAttribute("notes", studentSubjects);

        return INPUT_NOTES_FRAGMENT;
    }

    @GetMapping("/aluno/gerenciar")
    public String manageStudent(Model model) {
        log.info("StudentController.manageStudent - start");

        model.addAttribute("students", studentService.getAllStudents());

        return PAGE_MANAGE;
    }

    @GetMapping("/student/{studentId}/edit")
    public String editStudent(Model model, @PathVariable("studentId") String studentId) {
        log.info("StudentController.editStudent - start");

        Student student = studentService.getStudent(studentId);
        EditStudent editStudent = new EditStudent(student.getId().toString(), null, student.getName(), student.getBirthDate(), student.getContact().getAddress());

        model.addAttribute("student", editStudent);
        model.addAttribute("courses", courseService.getAll());

        return EDIT_STUDENT_PAGE;
    }

    @PostMapping("/aluno/salvarEditado")
    public String saveEditedStudent(@ModelAttribute EditStudent editStudent) {
        log.info("StudentController.saveEditedStudent - Start - editStudent {}", editStudent);

        studentService.editStudent(editStudent);

        return HOME_PAGE + PAGE_MANAGE;
    }

    @DeleteMapping("/student/{studentId}")
    public String delete(@PathVariable("studentId") String studentId) {
        log.info("StudentController.delete - Start - studentId {}", studentId);

        studentService.deleteStudent(studentId);

        log.info("StudentController.delete - End - studentId {}", studentId);
        return PAGE_MANAGE;
    }
}
