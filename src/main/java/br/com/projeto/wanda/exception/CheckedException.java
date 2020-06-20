package br.com.projeto.wanda.exception;

/**
 * Excecao generica sem rollback automatico
 *
 */
public class CheckedException extends Exception {

	public CheckedException() {
		super();
	}

	public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckedException(String message) {
		super(message);
	}

	public CheckedException(Throwable cause) {
		super(cause);
	}

	
}
