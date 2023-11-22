package merveozer.toDoApp.service.implementation;

import lombok.RequiredArgsConstructor;
import merveozer.toDoApp.exception.SameTitleAlreadyExistsException;
import merveozer.toDoApp.exception.MemberNotFoundException;
import merveozer.toDoApp.mapper.ToDoMapper;
import merveozer.toDoApp.model.MembershipEntity;
import merveozer.toDoApp.model.ToDoDto;
import merveozer.toDoApp.model.ToDoEntity;
import merveozer.toDoApp.repository.MembershipRepository;
import merveozer.toDoApp.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final MembershipRepository membershipRepository;
    private final ToDoMapper toDoMapper;

    @Override
    public ResponseEntity<List<ToDoDto>> getAll(String userName) {
        MembershipEntity membership = getMembershipEntity(userName);
        if (!CollectionUtils.isEmpty(membership.getTodoList())) {
            List<ToDoDto> toDoDtoList = toDoMapper.convertToDtoList(membership.getTodoList());
            return new ResponseEntity<>(toDoDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ToDoDto> create(ToDoDto toDoDto, String userName) {
        MembershipEntity membership = getMembershipEntity(userName);
        checkExistToDoWithSameTitle(toDoDto, membership);
        toDoDto.setId(UUID.randomUUID().toString());
        List<ToDoDto> toDoDtoList = toDoMapper.convertToDtoList(membership.getTodoList());
        toDoDtoList.add(toDoDto);
        List<ToDoEntity> toDoEntityList = toDoMapper.convertToEntityList(toDoDtoList);
        membership.setTodoList(toDoEntityList);
        membershipRepository.save(membership);
        return new ResponseEntity<>(toDoDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ToDoDto> update(ToDoDto toDoDto, String userName) {
        MembershipEntity membership = getMembershipEntity(userName);
        List<ToDoEntity> toDoEntityList = membership.getTodoList();
        ToDoEntity toDoEntity = toDoEntityList
                .stream()
                .filter(t -> t.getId().equals(toDoDto.getId())).findFirst().get();
        toDoEntityList.remove(toDoEntity);
        ToDoEntity updatedEntity = toDoMapper.convertToEntity(toDoDto);
        updatedEntity.setId(toDoDto.getId());
        toDoEntityList.add(updatedEntity);
        membership.setTodoList(toDoEntityList);
        membershipRepository.save(membership);
        return new ResponseEntity<>(toDoDto, HttpStatus.OK);
    }

    private MembershipEntity getMembershipEntity(String userName) {
        MembershipEntity membershipEntity = membershipRepository.findByUserName(userName);
        if (membershipEntity == null) {
            throw new MemberNotFoundException(userName);
        }
        return membershipEntity;
    }

    private void checkExistToDoWithSameTitle(ToDoDto toDoDto, MembershipEntity savedMembership) {
        if (savedMembership.getTodoList().stream().anyMatch(toDo -> toDo.getTitle().equals(toDoDto.getTitle()))) {
            throw new SameTitleAlreadyExistsException(toDoDto.getTitle());
        }
    }


}
