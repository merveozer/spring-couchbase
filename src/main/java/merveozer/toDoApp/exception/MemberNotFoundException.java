package merveozer.toDoApp.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String userName) {
        super("The member with username " + userName + " was not found");
    }
}
