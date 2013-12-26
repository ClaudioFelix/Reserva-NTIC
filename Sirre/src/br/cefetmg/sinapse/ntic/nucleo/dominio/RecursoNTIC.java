package br.cefetmg.sinapse.ntic.nucleo.dominio;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RecursoNTIC
 *
 */
@Entity
@Table(name="RecursoNTIC")
@NamedQueries({
    @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM RecursoNTIC r"),
    @NamedQuery(name = "Recurso.findById", query = "SELECT r FROM RecursoNTIC r WHERE r.idRecursoNTIC = :id"),
    @NamedQuery(name = "Recurso.findByNome", query = "SELECT r FROM RecursoNTIC r WHERE r.nomeRecursoNTIC = :nome"),
    @NamedQuery(name = "Recurso.findByLikeNome", query = "SELECT r FROM RecursoNTIC r WHERE upper(r.nomeRecursoNTIC) like :nome"),
    @NamedQuery(name = "Recurso.findByCountLikeNome", query = "SELECT COUNT(r) FROM RecursoNTIC r WHERE upper(r.nomeRecursoNTIC) like :nome"),
    @NamedQuery(name = "Recurso.findByCodTipoRecurso", query = "SELECT r FROM RecursoNTIC r WHERE r.tipoRecursoNTIC.codTipoRecurso = :codTipo"),
    @NamedQuery(name = "Recurso.findByCodStatusRecurso", query = "SELECT r FROM RecursoNTIC r WHERE r.statusRecursoNTIC.codStatRecurso = :codStatus" ),
    
})

public class RecursoNTIC {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRecursoNTIC = 0;
	
	private String nomeRecursoNTIC = "";	
	
	@Lob
    private String descRecursoNTIC = "";
	
	@ManyToOne @JoinColumn(name = "codStatRecurso")
	private StatusRecurso statusRecursoNTIC;
	
	@ManyToOne @JoinColumn(name = "codTipoRecurso")
	private TipoRecurso tipoRecursoNTIC;	
	

	public RecursoNTIC() {
		super();
	}


	public long getIdRecursoNTIC() {
		return idRecursoNTIC;
	}


	public void setIdRecursoNTIC(long idRecursoNTIC) {
		this.idRecursoNTIC = idRecursoNTIC;
	}


	public String getNomeRecursoNTIC() {
		return nomeRecursoNTIC;
	}


	public void setNomeRecursoNTIC(String nomeRecursoNTIC) {
		this.nomeRecursoNTIC = nomeRecursoNTIC;
	}


	public String getDescRecursoNTIC() {
		return descRecursoNTIC;
	}


	public void setDescRecursoNTIC(String descRecursoNTIC) {
		this.descRecursoNTIC = descRecursoNTIC;
	}


	public StatusRecurso getStatusRecursoNTIC() {
		return statusRecursoNTIC;
	}


	public void setStatusRecursoNTIC(StatusRecurso statusRecursoNTIC) {
		this.statusRecursoNTIC = statusRecursoNTIC;
	}


	public TipoRecurso getTipoRecursoNTIC() {
		return tipoRecursoNTIC;
	}


	public void setTipoRecursoNTIC(TipoRecurso tipoRecursoNTIC) {
		this.tipoRecursoNTIC = tipoRecursoNTIC;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descRecursoNTIC == null) ? 0 : descRecursoNTIC.hashCode());
		result = prime * result
				+ (int) (idRecursoNTIC ^ (idRecursoNTIC >>> 32));
		result = prime * result
				+ ((nomeRecursoNTIC == null) ? 0 : nomeRecursoNTIC.hashCode());
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
		RecursoNTIC other = (RecursoNTIC) obj;
		if (descRecursoNTIC == null) {
			if (other.descRecursoNTIC != null)
				return false;
		} else if (!descRecursoNTIC.equals(other.descRecursoNTIC))
			return false;
		if (idRecursoNTIC != other.idRecursoNTIC)
			return false;
		if (nomeRecursoNTIC == null) {
			if (other.nomeRecursoNTIC != null)
				return false;
		} else if (!nomeRecursoNTIC.equals(other.nomeRecursoNTIC))
			return false;
		return true;
	}
	
	
   
}
