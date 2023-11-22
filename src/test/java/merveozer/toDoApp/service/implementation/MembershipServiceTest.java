package merveozer.toDoApp.service.implementation;

import merveozer.toDoApp.mapper.MembershipMapper;
import merveozer.toDoApp.model.MembershipDto;
import merveozer.toDoApp.model.MembershipEntity;
import merveozer.toDoApp.model.POSTMembershipREQ;
import merveozer.toDoApp.repository.MembershipRepository;
import merveozer.toDoApp.validator.MembershipValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceTest {
    @Mock
    private MembershipValidator membershipValidator;
    @Mock
    private MembershipMapper membershipMapper;
    @Mock
    private MembershipRepository membershipRepository;
    @InjectMocks
    @Spy
    private MembershipServiceImpl membershipService;

    @Test
    public void createMembershipSuccess() {
        List<HttpStatus> errors = new ArrayList<>();
        MembershipEntity membershipEntity = new MembershipEntity();
        POSTMembershipREQ membershipReq = new POSTMembershipREQ();
        Mockito.when(membershipValidator.membershipRequestValidation(membershipReq)).thenReturn(errors);
        Mockito.when(membershipMapper.reqToEntity(membershipReq)).thenReturn(membershipEntity);
        ResponseEntity<POSTMembershipREQ> responseEntity = membershipService.create(membershipReq);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void createMembershipFailed() {
        HttpStatus error = HttpStatus.BAD_REQUEST;
        List<HttpStatus> errors = new ArrayList<>();
        errors.add(error);
        POSTMembershipREQ membershipReq = new POSTMembershipREQ();
        Mockito.when(membershipValidator.membershipRequestValidation(membershipReq)).thenReturn(errors);
        ResponseEntity<POSTMembershipREQ> responseEntity = membershipService.create(membershipReq);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void getMemberships() {
        List<MembershipEntity> membershipEntityList = new ArrayList<>();
        List<MembershipDto> membershipDtoList = new ArrayList<>();
        Mockito.when(membershipRepository.findAll()).thenReturn(membershipEntityList);
        Mockito.when(membershipMapper.convertToDtoList(membershipEntityList)).thenReturn(membershipDtoList);
        ResponseEntity<List<MembershipDto>> responseEntity = membershipService.getAllMembership();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}