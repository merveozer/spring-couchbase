package merveozer.toDoApp.model;

import org.junit.Assert;
import org.junit.Test;

public class ToDoDtoTest {

    @Test
    public void testSetters() {

        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setTitle("title");
        toDoDto.setDone(true);
        toDoDto.setDetail("detail");
        toDoDto.setDueDate("10-10-2023");
        toDoDto.setId("random");

        Assert.assertEquals("random", toDoDto.getId());
        Assert.assertEquals("title", toDoDto.getTitle());
        Assert.assertEquals(true, toDoDto.isDone());
        Assert.assertEquals("10-10-2023", toDoDto.getDueDate());
        Assert.assertEquals("detail", toDoDto.getDetail());
    }
}
