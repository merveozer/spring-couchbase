package merveozer.toDoApp.repository;

import merveozer.toDoApp.model.ToDoEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ToDoRepository extends CouchbaseRepository<ToDoEntity, Integer> {
}
