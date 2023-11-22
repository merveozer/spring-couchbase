package merveozer.toDoApp.mapper;

import merveozer.toDoApp.model.ToDoDto;
import merveozer.toDoApp.model.ToDoEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToDoMapper {
    public ToDoDto convertToDto(ToDoEntity toDoEntity) {
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setDueDate(toDoEntity.getDueDate());
        toDoDto.setDone(toDoEntity.isDone());
        toDoDto.setDetail(toDoEntity.getDetail());
        toDoDto.setTitle(toDoEntity.getTitle());
        return toDoDto;
    }

    public ToDoEntity convertToEntity(ToDoDto toDoDto) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setDueDate(toDoDto.getDueDate());
        toDoEntity.setDone(toDoDto.isDone());
        toDoEntity.setDetail(toDoDto.getDetail());
        toDoEntity.setTitle(toDoDto.getTitle());
        return toDoEntity;
    }

    public List<ToDoEntity> convertToEntityList(List<ToDoDto> toDoDtoList) {
        List<ToDoEntity> toDoEntityList = new ArrayList<>();
        for (ToDoDto toDoDto : toDoDtoList) {
            ToDoEntity toDoEntity = new ToDoEntity();
            toDoEntity.setId(toDoDto.getId());
            toDoEntity.setDueDate(toDoDto.getDueDate());
            toDoEntity.setDone(toDoDto.isDone());
            toDoEntity.setDetail(toDoDto.getDetail());
            toDoEntity.setTitle(toDoDto.getTitle());
            toDoEntityList.add(toDoEntity);
        }
        return toDoEntityList;
    }

    public List<ToDoDto> convertToDtoList(List<ToDoEntity> toDoEntities) {
        List<ToDoDto> toDoDtoList = new ArrayList<>();
        for (ToDoEntity toDoEntity : toDoEntities) {
            ToDoDto toDoDto = new ToDoDto();
            toDoDto.setDueDate(toDoEntity.getDueDate());
            toDoDto.setDone(toDoEntity.isDone());
            toDoDto.setDetail(toDoEntity.getDetail());
            toDoDto.setTitle(toDoEntity.getTitle());
            toDoDto.setId(toDoEntity.getId());
            toDoDtoList.add(toDoDto);
        }
        return toDoDtoList;
    }

}
