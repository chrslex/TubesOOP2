package oopokemon.exception;

public class NotInitializedException extends Throwable implements Exceptions{

    public NotInitializedException() {
        super();
    }

    @Override
    public String getErrorMessage() {
        return "object not initialized";
    }
}
