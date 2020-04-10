package br.com.alura.escolalura.repository;

import br.com.alura.escolalura.entity.Teacher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, ObjectId> {

}
