package br.com.projeto.wanda.exception;

public class CampoInvalidoException extends RuntimeException {

	public CampoInvalidoException() {}

	public CampoInvalidoException(Exception e) {
		super(e.getMessage(), e);
	}

	public CampoInvalidoException(String mensagem) {
		super(mensagem);
	}

	public CampoInvalidoException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}

	public CampoInvalidoException(String mensagem, Throwable erro, String... params) {
		super(mensagem, erro);
	}
}
