package repositories;

public class RepositoryException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public RepositoryException(String message) {
		super(message);
	}
	
	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
