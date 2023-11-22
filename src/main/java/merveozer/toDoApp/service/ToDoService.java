package merveozer.toDoApp.service;

import merveozer.toDoApp.model.ToDoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {
    ResponseEntity<List<ToDoDto>> getAll(String userName);
    ResponseEntity<ToDoDto> create(ToDoDto toDoDto, String userName);
    ResponseEntity<ToDoDto> update(ToDoDto toDoDto, String userName);
}
