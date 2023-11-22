package merveozer.toDoApp.model;

import org.junit.Assert;
import org.junit.Test;


public class MembershipDtoTest {

    @Test
    public void testSetters() {
        MembershipDto membershipDto = new MembershipDto();
        membershipDto.setUserName("merve");
        Assert.assertEquals("merve", membershipDto.getUserName());
    }
}
