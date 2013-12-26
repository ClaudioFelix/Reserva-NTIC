package br.cefetmg.sinapse.ntic.nucleo.dominio;


import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoReserva
 *
 */
@Entity
@Table(name="PedidoReserva")
@IdClass(PedidoReserva.class)
@NamedQueries({
    @NamedQuery(name = "PedidoReserva.findAll", query = "SELECT pr FROM PedidoReserva pr"),
    @NamedQuery(name = "PedidoReserva.findByidRecursoNTIC", query = "SELECT pr FROM PedidoReserva pr WHERE pr.recursoNTIC.idRecursoNTIC = :id"),
    @NamedQuery(name = "PedidoReserva.findByidUsuarioReservador", query = "SELECT pr FROM PedidoReserva pr WHERE pr.usuarioReservador.idUsuario = :id"),
    @NamedQuery(name = "PedidoReserva.findByidUsuarioUtilizador", query = "SELECT pr FROM PedidoReserva pr WHERE pr.usuarioUtilizador.idUsuario = :id"),
    @NamedQuery(name = "PedidoReserva.findByCountByRecursoNTIC", query = "SELECT COUNT(pr) FROM PedidoReserva pr WHERE pr.recursoNTIC.idRecursoNTIC = :id"),
    @NamedQuery(name = "PedidoReserva.findByCodStatPedReserva", query = "SELECT pr FROM PedidoReserva pr WHERE pr.statusPedReserva.codStatPedReserva = :codStatus" ),    
})
public class PedidoReserva implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @ManyToOne @JoinColumn(name = "idRecurso")
	private RecursoNTIC recursoNTIC;

	/* Apenas uma classe representativa */
	@Id @ManyToOne @JoinColumn(name = "idUsuario")
	private Usuario usuarioReservador;
	
	@Id
	private Date datPedidoReserva;
	
	/* Apenas uma classe representativa */
	@ManyToOne @JoinColumn(name = "idUsuario")
	private Usuario usuarioUtilizador;
	
	@ManyToOne @JoinColumn(name = "codStatPedReserva")
	private StatusPedReserva statusPedReserva;
	
	private Date horarioUtilRecurso;
	
	private int tempoUtilRecurso;
	
	
	public PedidoReserva() {
		super();
	}


	public RecursoNTIC getRecursoNTIC() {
		return recursoNTIC;
	}


	public void setRecursoNTIC(RecursoNTIC recursoNTIC) {
		this.recursoNTIC = recursoNTIC;
	}


	public Usuario getUsuarioReservador() {
		return usuarioReservador;
	}


	public void setUsuarioReservador(Usuario usuarioReservador) {
		this.usuarioReservador = usuarioReservador;
	}


	public Date getDatPedidoReserva() {
		return datPedidoReserva;
	}


	public void setDatPedidoReserva(Date datPedidoReserva) {
		this.datPedidoReserva = datPedidoReserva;
	}


	public Usuario getUsuarioUtilizador() {
		return usuarioUtilizador;
	}


	public void setUsuarioUtilizador(Usuario usuarioUtilizador) {
		this.usuarioUtilizador = usuarioUtilizador;
	}


	public StatusPedReserva getStatusPedReserva() {
		return statusPedReserva;
	}


	public void setStatusPedReserva(StatusPedReserva statusPedReserva) {
		this.statusPedReserva = statusPedReserva;
	}


	public Date getHorarioUtilRecurso() {
		return horarioUtilRecurso;
	}


	public void setHorarioUtilRecurso(Date horarioUtilRecurso) {
		this.horarioUtilRecurso = horarioUtilRecurso;
	}


	public int getTempoUtilRecurso() {
		return tempoUtilRecurso;
	}


	public void setTempoUtilRecurso(int tempoUtilRecurso) {
		this.tempoUtilRecurso = tempoUtilRecurso;
	}


	@Override
	public String toString() {
		return "PedidoReserva [recursoNTIC=" + recursoNTIC
				+ ", usuarioReservador=" + usuarioReservador
				+ ", datPedidoReserva=" + datPedidoReserva
				+ ", usuarioUtilizador=" + usuarioUtilizador
				+ ", statusPedReserva=" + statusPedReserva
				+ ", horarioUtilRecurso=" + horarioUtilRecurso
				+ ", tempoUtilRecurso=" + tempoUtilRecurso + "]";
	}

	
	/* Pode existir algo de errado na implementação desses metodos gerados */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((datPedidoReserva == null) ? 0 : datPedidoReserva.hashCode());
		result = prime
				* result
				+ ((horarioUtilRecurso == null) ? 0 : horarioUtilRecurso
						.hashCode());
		result = prime * result
				+ ((recursoNTIC == null) ? 0 : recursoNTIC.hashCode());
		result = prime
				* result
				+ ((statusPedReserva == null) ? 0 : statusPedReserva.hashCode());
		result = prime * result + tempoUtilRecurso;
		result = prime
				* result
				+ ((usuarioReservador == null) ? 0 : usuarioReservador
						.hashCode());
		result = prime
				* result
				+ ((usuarioUtilizador == null) ? 0 : usuarioUtilizador
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
		PedidoReserva other = (PedidoReserva) obj;
		if (datPedidoReserva == null) {
			if (other.datPedidoReserva != null)
				return false;
		} else if (!datPedidoReserva.equals(other.datPedidoReserva))
			return false;
		if (horarioUtilRecurso == null) {
			if (other.horarioUtilRecurso != null)
				return false;
		} else if (!horarioUtilRecurso.equals(other.horarioUtilRecurso))
			return false;
		if (recursoNTIC == null) {
			if (other.recursoNTIC != null)
				return false;
		} else if (!recursoNTIC.equals(other.recursoNTIC))
			return false;
		if (statusPedReserva == null) {
			if (other.statusPedReserva != null)
				return false;
		} else if (!statusPedReserva.equals(other.statusPedReserva))
			return false;
		if (tempoUtilRecurso != other.tempoUtilRecurso)
			return false;
		if (usuarioReservador == null) {
			if (other.usuarioReservador != null)
				return false;
		} else if (!usuarioReservador.equals(other.usuarioReservador))
			return false;
		if (usuarioUtilizador == null) {
			if (other.usuarioUtilizador != null)
				return false;
		} else if (!usuarioUtilizador.equals(other.usuarioUtilizador))
			return false;
		return true;
	}
   
}
