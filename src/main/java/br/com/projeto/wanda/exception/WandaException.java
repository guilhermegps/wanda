package br.com.projeto.wanda.exception;

public class WandaException extends RuntimeException {

	public WandaException() {}

	public WandaException(Exception e) {
		super(e.getMessage(), e);
	}

	public WandaException(String mensagem) {
		super(mensagem);
	}

	public WandaException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}

	public WandaException(String mensagem, Throwable erro, String... params) {
		super(mensagem, erro);
	}
}
