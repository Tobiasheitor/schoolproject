package br.com.alura.escolalura.repository;

import br.com.alura.escolalura.entity.Subject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, ObjectId> {
}
