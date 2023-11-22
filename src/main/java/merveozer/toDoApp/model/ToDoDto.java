package merveozer.toDoApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {

    private String id;

    private String title;

    private String detail;

    private String dueDate;

    private boolean isDone;

}
