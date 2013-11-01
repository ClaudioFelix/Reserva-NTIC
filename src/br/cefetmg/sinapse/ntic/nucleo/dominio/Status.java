package br.cefetmg.sinapse.ntic.nucleo.dominio;

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
    @NamedQuery(name = "Status.findByCodigoStatus", query = "SELECT s FROM Status s WHERE s.CodigoStatus = :CodigoStatus")
})

public class Status{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigoStatus = 0;
	
	private String nomeStatus;
	
	// Abstração
	private List<PedidoReserva> lista_pedidos;
	
	public Status() {
		super();
	}
	
	public long getCodigoStatus() {
		return codigoStatus;
	}

	public void setCodigoStatus(long codigoStatus) {
		this.codigoStatus = codigoStatus;
	}

	@Override
	public String toString() {
		return "Status [codigoStatus=" + codigoStatus + ", nomeStatus="
				+ nomeStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigoStatus ^ (codigoStatus >>> 32));
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
		if (codigoStatus != other.codigoStatus)
			return false;
		if (nomeStatus == null) {
			if (other.nomeStatus != null)
				return false;
		} else if (!nomeStatus.equals(other.nomeStatus))
			return false;
		return true;
	}
}
