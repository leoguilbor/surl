package br.com.leoguilbor.surl.exception;

public class UrlNotShortedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UrlNotShortedException() {
		super();
	}

	public UrlNotShortedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UrlNotShortedException(String message) {
		super(message);
	}

	
}
