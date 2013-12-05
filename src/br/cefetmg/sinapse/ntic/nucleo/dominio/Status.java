package br.cefetmg.sinapse.ntic.nucleo.dominio;

import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Status
 *
 */
@Entity
@NamedQueries( {
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
    @NamedQuery(name = "Status.findByNome", query = "SELECT s FROM Status s WHERE s.nomeStatus = :nomeStatus"),
    @NamedQuery(name = "Status.findByLikeNome", query = "SELECT s FROM Status s WHERE upper(s.nomeStatus) like :nomeStatus"),
    @NamedQuery(name = "Status.findByCodigoStatus", query = "SELECT s FROM Status s WHERE s.id = :CodigoStatus")
})

public class Status{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 0;
	
	private String nomeStatus;
	
	// Abstração
	private List<PedidoReserva> lista_pedidos;
	
	public Status() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", nomeStatus="
				+ nomeStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((nomeStatus == null) ? 0 : nomeStatus.hashCode());
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
		Status other = (Status) obj;
		if (id != other.id)
			return false;
		if (nomeStatus == null) {
			if (other.nomeStatus != null)
				return false;
		} else if (!nomeStatus.equals(other.nomeStatus))
			return false;
		return true;
	}
}