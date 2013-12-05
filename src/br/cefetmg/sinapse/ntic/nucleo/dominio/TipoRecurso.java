package br.cefetmg.sinapse.ntic.nucleo.dominio;

import java.util.List;

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
    @NamedQuery(name = "TipoRecurso.findByCodigoTipoRecurso", query = "SELECT s FROM TipoRecurso s WHERE s.id = :CodigoTipoRecurso")
})

public class TipoRecurso{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 0;
	
	private String nomeTipoRecurso;
	
	// Abstração
	private List<RecursoNTIC> lista_recursos;
	
	public TipoRecurso() {
		super();
	}
	
	public long getCodigoTipoRecurso() {
		return id;
	}

	public String getNomeTipoRecurso(){
		return nomeTipoRecurso;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "TipoRecurso [codigoTipoRecurso=" + id
				+ ", nomeTipoRecurso=" + nomeTipoRecurso + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		if (nomeTipoRecurso == null) {
			if (other.nomeTipoRecurso != null)
				return false;
		} else if (!nomeTipoRecurso.equals(other.nomeTipoRecurso))
			return false;
		return true;
	}
}