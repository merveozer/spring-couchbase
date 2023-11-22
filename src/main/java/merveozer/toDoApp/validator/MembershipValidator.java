package merveozer.toDoApp.validator;

import merveozer.toDoApp.model.MembershipEntity;
import merveozer.toDoApp.model.POSTMembershipREQ;
import merveozer.toDoApp.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MembershipValidator {
    @Autowired
    MembershipRepository membershipRepository;

    public List<HttpStatus> membershipRequestValidation(POSTMembershipREQ membershipReq) {
        List<HttpStatus> errors = new ArrayList<>();
        if (membershipReq.getUserName() != null && membershipReq.getPassword() != null) {
            MembershipEntity savedMembership = membershipRepository.findByUserName(membershipReq.getUserName());
            if (savedMembership != null) {
                HttpStatus error = HttpStatus.CONFLICT;
                errors.add(error);
                return errors;
            }
        } else {
            HttpStatus error = HttpStatus.BAD_REQUEST;
            errors.add(error);
            return errors;
        }
        return errors;
    }
}
