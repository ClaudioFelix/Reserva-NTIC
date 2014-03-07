package br.cefetmg.sinapse.ntic.nucleo.dominio;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: StatusPedReserva
 *
 */
@Entity
@Table(name="StatusPedReserva")
@NamedQueries({
    @NamedQuery(name = "StatusPedReserva.findAll", query = "SELECT sp FROM StatusPedReserva sp"),
    @NamedQuery(name = "StatusPedReserva.findById", query = "SELECT sp FROM StatusPedReserva sp WHERE sp.codStatPedReserva = :id"),
    @NamedQuery(name = "StatusPedReserva.findByLikeNome", query = "SELECT sp FROM StatusPedReserva sp WHERE upper(sp.nomeStatPedReserva) like :nome"),
})

public class StatusPedReserva {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codStatPedReserva = 0;
	
	@Lob
	private String nomeStatPedReserva = "";

	public StatusPedReserva() {
		super();
	}

	public int getCodStatPedReserva() {
		return codStatPedReserva;
	}

	public void setCodStatPedReserva(int codStatPedReserva) {
		this.codStatPedReserva = codStatPedReserva;
	}

	public String getNomeStatPedReserva() {
		return nomeStatPedReserva;
	}

	public void setNomeStatPedReserva(String nomeStatPedReserva) {
		this.nomeStatPedReserva = nomeStatPedReserva;
	}

	@Override
	public String toString() {
		return "StatusPedReserva [codStatPedReserva=" + codStatPedReserva
				+ ", nomeStatPedReserva=" + nomeStatPedReserva + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codStatPedReserva;
		result = prime
				* result
				+ ((nomeStatPedReserva == null) ? 0 : nomeStatPedReserva
						.hashCode());
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
		StatusPedReserva other = (StatusPedReserva) obj;
		if (codStatPedReserva != other.codStatPedReserva)
			return false;
		if (nomeStatPedReserva == null) {
			if (other.nomeStatPedReserva != null)
				return false;
		} else if (!nomeStatPedReserva.equals(other.nomeStatPedReserva))
			return false;
		return true;
	}
   
}
