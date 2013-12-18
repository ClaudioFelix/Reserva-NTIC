package br.cefetmg.sinapse.ntic.nucleo.dominio;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: StatusRecurso
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "StatusRecurso.findAll", query = "SELECT sr FROM StatusRecurso sr"),
    @NamedQuery(name = "StatusRecurso.findById", query = "SELECT sr FROM StatusRecurso sr WHERE sr.codStatRecurso = :id"),
    @NamedQuery(name = "StatusRecurso.findByLikeNome", query = "SELECT sr FROM StatusRecurso sr WHERE upper(sr.nomeStatRecurso) like :nome"),
})

public class StatusRecurso{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codStatRecurso = 0;
	
	private String nomeStatRecurso = "";

	public StatusRecurso() {
		super();
	}

	public long getCodStatRecurso() {
		return codStatRecurso;
	}

	public void setCodStatRecurso(long codStatRecurso) {
		this.codStatRecurso = codStatRecurso;
	}

	public String getNomeStatRecurso() {
		return nomeStatRecurso;
	}

	public void setNomeStatRecurso(String nomeStatRecurso) {
		this.nomeStatRecurso = nomeStatRecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (codStatRecurso ^ (codStatRecurso >>> 32));
		result = prime * result
				+ ((nomeStatRecurso == null) ? 0 : nomeStatRecurso.hashCode());
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
		StatusRecurso other = (StatusRecurso) obj;
		if (codStatRecurso != other.codStatRecurso)
			return false;
		if (nomeStatRecurso == null) {
			if (other.nomeStatRecurso != null)
				return false;
		} else if (!nomeStatRecurso.equals(other.nomeStatRecurso))
			return false;
		return true;
	}
   
}
