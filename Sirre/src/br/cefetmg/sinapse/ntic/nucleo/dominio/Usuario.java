package br.cefetmg.sinapse.ntic.nucleo.dominio;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private long idUsuario;	

	public Usuario() {
		super();
	}
   
}
