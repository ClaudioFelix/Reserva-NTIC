package br.cefetmg.sinapse.ntic.nucleo.dominio;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoReserva
 *
 */
@Entity
@NamedQueries( {
    @NamedQuery(name = "PedidoReserva.findAll", query = "SELECT p FROM PedidoReserva p"),
    @NamedQuery(name = "PedidoReserva.findById", query = "SELECT p FROM PedidoReserva p WHERE p.id = :id"),
    @NamedQuery(name = "PedidoReserva.findByData", query = "SELECT p FROM PedidoReserva p WHERE p.Data = :Data"),
    @NamedQuery(name = "PedidoReserva.findByLikeData", query = "SELECT p FROM PedidoReserva p WHERE p.Data like :Data"),
    @NamedQuery(name = "PedidoReserva.findByCountLikeData", query = "SELECT COUNT(p) FROM PedidoReserva p WHERE p.Data like :Data")
})

public class PedidoReserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 0;
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;
	
	/* private Funcionario responsavel */ // Outra abstração
	private List<Recurso> lista_recurso;


	public PedidoReserva() {
		super();
	}   

	public long getId() {
		return this.id;
	}

	public void setId(long idPedidoReserva) {
		this.id = idPedidoReserva;
	}   
	public Date getData() {
		return this.Data;
	}

	public void setData(Date Data) {
		this.Data = Data;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Recurso> getLista_recurso() {
		return lista_recurso;
	}
	public void setLista_recurso(List<Recurso> lista_recurso) {
		this.lista_recurso = lista_recurso;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Data == null) ? 0 : Data.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		PedidoReserva other = (PedidoReserva) obj;
		if (Data == null) {
			if (other.Data != null)
				return false;
		} else if (!Data.equals(other.Data))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PedidoReserva [id=" + id + ", data=" + data
				+ ", lista_recurso=" + lista_recurso + "]";
	}   
}
