package merveozer.toDoApp.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class MembershipEntityTest {
    @Test
    public void testSetters() {
        MembershipEntity membershipEntity = new MembershipEntity();
        membershipEntity.setUserName("merve");
        membershipEntity.setPassword("123");
        membershipEntity.setId("1");
        membershipEntity.setTodoList(new ArrayList<>());
        Assert.assertEquals("merve", membershipEntity.getUserName());
        Assert.assertEquals("123", membershipEntity.getPassword());
        Assert.assertEquals("1", membershipEntity.getId());
        Assert.assertTrue(membershipEntity.getTodoList().isEmpty());
    }
}
