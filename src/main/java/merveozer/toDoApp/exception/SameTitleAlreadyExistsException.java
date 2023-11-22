package merveozer.toDoApp.exception;

public class SameTitleAlreadyExistsException extends RuntimeException {

    public SameTitleAlreadyExistsException(String title) {
        super("There is already a to-do item with title is " + title);
    }
}
