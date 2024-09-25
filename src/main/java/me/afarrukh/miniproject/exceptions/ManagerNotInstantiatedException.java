package me.afarrukh.miniproject.exceptions;

/**
 * @author Abdullah Farrukh
 */
public class ManagerNotInstantiatedException extends RuntimeException {

    public ManagerNotInstantiatedException() {
        super("The manager object was not instantiated before being called via getInstance(). " +
                "Please use Manager.init() and provide a Game instance before calling this.");
    }
}
