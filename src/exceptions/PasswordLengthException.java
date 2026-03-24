package exceptions;

public class PasswordLengthException extends RuntimeException {
	
	public PasswordLengthException() {
		super("The password must be at least 8 characters long.");
	}
}
