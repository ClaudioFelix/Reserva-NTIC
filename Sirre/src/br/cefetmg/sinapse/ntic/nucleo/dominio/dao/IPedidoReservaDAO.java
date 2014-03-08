package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import javax.persistence.Query;

import br.cefetmg.sinapse.ntic.nucleo.dominio.PedidoReserva;
import br.cefetmg.sinapse.ntic.nucleo.dominio.RecursoNTIC;
import br.cefetmg.sinapse.ntic.nucleo.dominio.StatusPedReserva;

public interface IPedidoReservaDAO {
    public void inserir(PedidoReserva pedido);
    public PedidoReserva atualizar(PedidoReserva pedido);
    public void remover(PedidoReserva pedido);
    public PedidoReserva consultar(long id);
    public List<PedidoReserva> buscar();
    public List<PedidoReserva> consultarPorIdRecursoNTIC(RecursoNTIC recurso);
    public List<PedidoReserva> consultarPorCodStatPedReserva(StatusPedReserva status);
    public List<PedidoReserva> consultarPorIdUsuarioReservador(Usuario usuario);
    public List<PedidoReserva> consultarPorIdUsuarioUtilizador(Usuario usuario);

}
