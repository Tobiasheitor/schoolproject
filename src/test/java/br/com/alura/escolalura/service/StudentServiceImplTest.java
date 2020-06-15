package br.com.alura.escolalura.service;

import br.com.alura.escolalura.repository.CourseRepository;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest {

  private static final String COURSE = "CC";
  @Autowired
  private StudentService studentService;

  @MockBean
  private CourseRepository courseRepository;

  private LocalDate birthDate;

  @Before
  public void init() {
    birthDate = LocalDate.now();
  }

  @Test
  public void save() {

  }

}
