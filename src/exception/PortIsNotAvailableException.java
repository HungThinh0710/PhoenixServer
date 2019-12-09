package exception;

public class PortIsNotAvailableException extends Exception{
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	public PortIsNotAvailableException(String errors) {
		super(errors);
	}
}
