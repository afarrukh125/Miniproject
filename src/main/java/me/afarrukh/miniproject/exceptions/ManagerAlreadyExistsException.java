package me.afarrukh.miniproject.exceptions;

/**
 * @author Abdullah Farrukh
 */
public class ManagerAlreadyExistsException extends RuntimeException {

    public ManagerAlreadyExistsException() {
        super("The manager already exists. You cannot create another one. Please use Manager.getInstance() to grab the " +
                "manager.");
    }
}
