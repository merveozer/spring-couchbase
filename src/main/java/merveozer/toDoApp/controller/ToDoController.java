package merveozer.toDoApp.controller;

import merveozer.toDoApp.model.ToDoDto;
import merveozer.toDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ToDoController implements ToDoService {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("todo")
    @Override
    public ResponseEntity<List<ToDoDto>> getAll(String userName) {
        return toDoService.getAll(userName);
    }

    @PostMapping("todo")
    @Override
    public ResponseEntity<ToDoDto> create(@RequestBody ToDoDto toDoDto, String userName) {
        return toDoService.create(toDoDto, userName);
    }

    @PatchMapping("todo")
    @Override
    public ResponseEntity<ToDoDto> update(@RequestBody ToDoDto toDoDto, String userName) {
        return toDoService.update(toDoDto, userName);
    }
}
