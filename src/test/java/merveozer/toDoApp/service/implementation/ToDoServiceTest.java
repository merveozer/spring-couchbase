package merveozer.toDoApp.service.implementation;

import merveozer.toDoApp.exception.MemberNotFoundException;
import merveozer.toDoApp.exception.SameTitleAlreadyExistsException;
import merveozer.toDoApp.mapper.MembershipMapper;
import merveozer.toDoApp.mapper.ToDoMapper;
import merveozer.toDoApp.model.MembershipEntity;
import merveozer.toDoApp.model.ToDoDto;
import merveozer.toDoApp.model.ToDoEntity;
import merveozer.toDoApp.repository.MembershipRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceTest {
    @Mock
    private MembershipMapper membershipMapper;
    @Mock
    private ToDoMapper toDoMapper;
    @Mock
    private MembershipRepository membershipRepository;
    @InjectMocks
    @Spy
    private ToDoServiceImpl toDoService;

    @Test
    public void getAllToDos() {
        MembershipEntity membership = buildMembership("merve", "1", "1", "title");
        List<ToDoDto> toDoDtoList = getToDoDtos("1");
        Mockito.when(membershipRepository.findByUserName("merve")).thenReturn(membership);
        Mockito.when(toDoMapper.convertToDtoList(membership.getTodoList())).thenReturn(toDoDtoList);

        ResponseEntity<List<ToDoDto>> responseEntity = toDoService.getAll("merve");
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test(expected= MemberNotFoundException.class)
    public void getAllToDosMemberNotFound() {
        Mockito.when(membershipRepository.findByUserName("merve")).thenReturn(null);
        ResponseEntity<List<ToDoDto>> responseEntity = toDoService.getAll("merve");
    }

    @Test
    public void getAllToDosNoContent() {
        MembershipEntity membership = new MembershipEntity();
        membership.setUserName("merve");
        Mockito.when(membershipRepository.findByUserName("merve")).thenReturn(membership);
        ResponseEntity<List<ToDoDto>> responseEntity = toDoService.getAll("merve");
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void createToDo() {
        MembershipEntity membership = new MembershipEntity();
        membership.setUserName("merve");
        Mockito.when(membershipRepository.findByUserName("merve")).thenReturn(membership);
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setDetail("1");
        ResponseEntity<ToDoDto> responseEntity = toDoService.create(toDoDto, "merve");
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void updateToDoSuccess() {
        MembershipEntity membership = buildMembership("merve","1", "1", "title");
        Mockito.when(membershipRepository.findByUserName("merve")).thenReturn(membership);
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setId("1");
        Mockito.when(toDoMapper.convertToEntity(toDoDto)).thenReturn(membership.getTodoList().get(0));
        ResponseEntity<ToDoDto> responseEntity = toDoService.update(toDoDto, "merve");
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test(expected= SameTitleAlreadyExistsException.class)
    public void createToDoFailed_ExistSameTitle() {
        MembershipEntity membership = buildMembership("merve","1", "1", "title");
        Mockito.when(membershipRepository.findByUserName("merve")).thenReturn(membership);
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setId("1");
        toDoDto.setTitle("title");
        Mockito.when(toDoMapper.convertToEntity(toDoDto)).thenReturn(membership.getTodoList().get(0));
        ResponseEntity<ToDoDto> responseEntity = toDoService.create(toDoDto, "merve");
    }

    private List<ToDoEntity> getToDoEntities(String detail, String id, String title) {
        List<ToDoEntity> toDoEntityList = new ArrayList<>();
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setDetail(detail);
        toDoEntity.setId(id);
        toDoEntity.setTitle(title);
        toDoEntityList.add(toDoEntity);
        return toDoEntityList;
    }

    private List<ToDoDto> getToDoDtos(String detail) {
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setDetail(detail);
        List<ToDoDto> toDoDtoList = new ArrayList<>();
        toDoDtoList.add(toDoDto);
        return toDoDtoList;
    }

    private MembershipEntity buildMembership(String userName, String detail, String id, String title){
        MembershipEntity membership = new MembershipEntity();
        membership.setUserName(userName);

        List<ToDoEntity> toDoEntityList = getToDoEntities(detail, id, title);
        membership.setTodoList(toDoEntityList);
        return membership;
    }
}
