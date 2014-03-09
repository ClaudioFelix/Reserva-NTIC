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
    @NamedQuery(name = "PedidoReserva.findAll", query = "SELECT pr FROM PedidoReserva pr"),
    @NamedQuery(name = "PedidoReserva.findById", query = "SELECT pr FROM PedidoReserva pr WHERE pr.id = :id"),
    
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
	private List<RecursoNTIC> listaRecurso;


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
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<RecursoNTIC> getLista_recurso() {
		return listaRecurso;
	}
	public void setLista_recurso(List<RecursoNTIC> lista_recurso) {
		this.listaRecurso = lista_recurso;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PedidoReserva [id=" + id + ", data=" + data
				+ ", lista_recurso=" + listaRecurso + "]";
	}   
}