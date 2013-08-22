package evanther.resteasy.server.other;

public class InvalidTokenException extends IllegalArgumentException {

    private static final long serialVersionUID = -8719942214617888165L;

    public InvalidTokenException() {
        super("Invalid token");
    }

}
