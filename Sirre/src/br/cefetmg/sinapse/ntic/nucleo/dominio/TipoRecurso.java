package br.cefetmg.sinapse.ntic.nucleo.dominio;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoRecurso
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "TipoRecurso.findAll", query = "SELECT tr FROM TipoRecurso tr"),
    @NamedQuery(name = "TipoRecurso.findById", query = "SELECT tr FROM TipoRecurso tr WHERE tr.codTipoRecurso = :id"),
    @NamedQuery(name = "TipoRecurso.findByLikeNome", query = "SELECT tr FROM TipoRecurso tr WHERE upper(tr.nomeTipoRecurso) like :nome"),
})

public class TipoRecurso {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codTipoRecurso = 0;
	
	private String nomeTipoRecurso = "";

	public TipoRecurso() {
		super();
	}

	public long getCodTipoRecurso() {
		return codTipoRecurso;
	}

	public void setCodTipoRecurso(long codTipoRecurso) {
		this.codTipoRecurso = codTipoRecurso;
	}

	public String getNomeTipoRecurso() {
		return nomeTipoRecurso;
	}

	public void setNomeTipoRecurso(String nomeTipoRecurso) {
		this.nomeTipoRecurso = nomeTipoRecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (codTipoRecurso ^ (codTipoRecurso >>> 32));
		result = prime * result
				+ ((nomeTipoRecurso == null) ? 0 : nomeTipoRecurso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoRecurso other = (TipoRecurso) obj;
		if (codTipoRecurso != other.codTipoRecurso)
			return false;
		if (nomeTipoRecurso == null) {
			if (other.nomeTipoRecurso != null)
				return false;
		} else if (!nomeTipoRecurso.equals(other.nomeTipoRecurso))
			return false;
		return true;
	}
   
}
