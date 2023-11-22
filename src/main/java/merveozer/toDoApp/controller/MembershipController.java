package merveozer.toDoApp.controller;

import lombok.RequiredArgsConstructor;
import merveozer.toDoApp.model.MembershipDto;
import merveozer.toDoApp.model.POSTMembershipREQ;
import merveozer.toDoApp.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class MembershipController implements MembershipService {

    @Autowired
    private MembershipService membershipService;

    @Override
    @PostMapping("membership")
    public ResponseEntity<POSTMembershipREQ> create(@RequestBody POSTMembershipREQ membershipReq) {
        return this.membershipService.create(membershipReq);
    }

    @Override
    @GetMapping("memberships")
    public ResponseEntity<List<MembershipDto>> getAllMembership() {
        return this.membershipService.getAllMembership();
    }
}
