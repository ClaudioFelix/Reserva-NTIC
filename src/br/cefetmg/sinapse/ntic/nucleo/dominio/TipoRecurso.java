package br.cefetmg.sinapse.ntic.nucleo.dominio;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoRecurso
 *
 */
@Entity
@NamedQueries( {
    @NamedQuery(name = "TipoRecurso.findAll", query = "SELECT s FROM TipoRecurso s"),
    @NamedQuery(name = "TipoRecurso.findByNome", query = "SELECT s FROM TipoRecurso s WHERE s.nomeTipoRecurso = :nomeTipoRecurso"),
    @NamedQuery(name = "TipoRecurso.findByLikeNome", query = "SELECT s FROM TipoRecurso s WHERE upper(s.nomeTipoRecurso) like :nomeTipoRecurso"),
    @NamedQuery(name = "TipoRecurso.findByCodigoTipoRecurso", query = "SELECT s FROM TipoRecurso s WHERE s.CodigoTipoRecurso = :CodigoTipoRecurso")
})

public class TipoRecurso{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigoTipoRecurso = 0;
	
	private String nomeTipoRecurso;
	
	// Abstração
	private List<Recurso> lista_recursos;
	
	public TipoRecurso() {
		super();
	}
	
	public long getCodigoTipoRecurso() {
		return codigoTipoRecurso;
	}

	public void setCodigoTipoRecurso(long codigoTipoRecurso) {
		this.codigoTipoRecurso = codigoTipoRecurso;
	}
	
	@Override
	public String toString() {
		return "TipoRecurso [codigoTipoRecurso=" + codigoTipoRecurso
				+ ", nomeTipoRecurso=" + nomeTipoRecurso + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (codigoTipoRecurso ^ (codigoTipoRecurso >>> 32));
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
		if (codigoTipoRecurso != other.codigoTipoRecurso)
			return false;
		if (nomeTipoRecurso == null) {
			if (other.nomeTipoRecurso != null)
				return false;
		} else if (!nomeTipoRecurso.equals(other.nomeTipoRecurso))
			return false;
		return true;
	}
}
