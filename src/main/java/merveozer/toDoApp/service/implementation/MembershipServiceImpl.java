package merveozer.toDoApp.service.implementation;

import lombok.RequiredArgsConstructor;
import merveozer.toDoApp.model.POSTMembershipREQ;
import merveozer.toDoApp.service.MembershipService;
import merveozer.toDoApp.mapper.MembershipMapper;
import merveozer.toDoApp.model.MembershipDto;
import merveozer.toDoApp.model.MembershipEntity;
import merveozer.toDoApp.repository.MembershipRepository;
import merveozer.toDoApp.validator.MembershipValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;
    private final MembershipValidator membershipValidator;

    @Override
    public ResponseEntity<POSTMembershipREQ> create(POSTMembershipREQ membershipReq) {
        List<HttpStatus> errors = membershipValidator.membershipRequestValidation(membershipReq);
        if (errors.isEmpty()) {
            MembershipEntity membershipEntity = membershipMapper.reqToEntity(membershipReq);
            membershipEntity.setId(UUID.randomUUID().toString());
            membershipRepository.save(membershipEntity);
            return new ResponseEntity<>(membershipReq, HttpStatus.CREATED);
        } else {
            HttpStatus httpStatus = errors.get(0);
            return new ResponseEntity<>(membershipReq, httpStatus);
        }
    }

    @Override
    public ResponseEntity<List<MembershipDto>> getAllMembership() {
        List<MembershipEntity> membershipEntityList = membershipRepository.findAll();
        List<MembershipDto> membershipDtoList = membershipMapper.convertToDtoList(membershipEntityList);
        return new ResponseEntity<>(membershipDtoList, HttpStatus.OK);
    }


}
