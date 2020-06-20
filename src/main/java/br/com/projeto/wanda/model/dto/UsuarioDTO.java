package br.com.projeto.wanda.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import br.com.projeto.wanda.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
	
	private Long codigo;
	@NotBlank
	private String login;
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	private String senha;
	@NotBlank
	private String email;
	private Boolean ativo;
	private Date dataRegistro;
	
	public static UsuarioDTO convert(Usuario usuario) {
		return UsuarioDTO.builder()
				.codigo(usuario.getCodigo())
				.login(usuario.getLogin())
				.nome(usuario.getNome())
				.cpf(usuario.getCpf())
//				.senha(usuario.getSenha())
				.email(usuario.getEmail())
				.ativo(usuario.getAtivo())
				.dataRegistro(usuario.getDataRegistro())
				.build();
	}
	
	public Usuario convertToEntity() {
		Usuario usuario = Usuario.builder()
				.login(login)
				.nome(nome)
				.cpf(cpf)
				.senha(senha)
				.email(email)
				.build();
		
		return usuario;
	}

}
