package br.com.alura.escolalura.repository;

import br.com.alura.escolalura.entity.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, ObjectId> {

  Course findByName(String name);

}
