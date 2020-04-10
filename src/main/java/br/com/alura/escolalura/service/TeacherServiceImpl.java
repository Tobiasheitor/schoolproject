package br.com.alura.escolalura.service;

import br.com.alura.escolalura.entity.Teacher;
import br.com.alura.escolalura.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher save(Teacher teacher) {
        log.info("Service start - save teacher: {}", teacher);
        Teacher result;

        result = teacherRepository.save(teacher);

        log.debug("Service end - saved teacher: {}", result);
        return result;
    }

}
