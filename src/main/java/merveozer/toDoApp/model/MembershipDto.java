package merveozer.toDoApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDto {

    private String userName;
    private List<ToDoEntity> todoList = new ArrayList<>();

}
