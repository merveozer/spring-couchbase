package merveozer.toDoApp.mapper;

import merveozer.toDoApp.model.MembershipDto;
import merveozer.toDoApp.model.MembershipEntity;
import merveozer.toDoApp.model.POSTMembershipREQ;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MembershipMapper {
    public MembershipDto convertToDao(MembershipEntity membershipEntity) {
        return null;
    }

    public MembershipEntity convertToEntity(MembershipDto membershipDto) {
        MembershipEntity membershipEntity = new MembershipEntity();
        membershipEntity.setTodoList(membershipDto.getTodoList());
        membershipEntity.setUserName(membershipDto.getUserName());
        return membershipEntity;
    }

    public MembershipEntity reqToEntity(POSTMembershipREQ membershipReq) {
        MembershipEntity membershipEntity = new MembershipEntity();
        membershipEntity.setPassword(membershipReq.getPassword());
        membershipEntity.setUserName(membershipReq.getUserName());
        return membershipEntity;
    }

    public List<MembershipDto> convertToDtoList(List<MembershipEntity> membershipEntityList) {
        List<MembershipDto> membershipDtoList = new ArrayList<>();
        for (MembershipEntity membershipEntity : membershipEntityList) {
            MembershipDto membershipDto = new MembershipDto();
            membershipDto.setTodoList(membershipEntity.getTodoList());
            membershipDto.setUserName(membershipEntity.getUserName());
            membershipDtoList.add(membershipDto);
        }

        return membershipDtoList;
    }
}
