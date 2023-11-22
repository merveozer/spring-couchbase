package merveozer.toDoApp.service;

import merveozer.toDoApp.model.MembershipDto;
import merveozer.toDoApp.model.POSTMembershipREQ;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MembershipService {
    ResponseEntity<POSTMembershipREQ> create(POSTMembershipREQ membershipReq);
    ResponseEntity<List<MembershipDto>> getAllMembership();
}
