package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import br.cefetmg.sinapse.ntic.nucleo.dominio.StatusPedReserva;

public interface IStatusPedReservaDAO{
	public void inserir(StatusPedReserva status);
	public StatusPedReserva atualizar(StatusPedReserva status);
	public void remover(StatusPedReserva status);
	public StatusPedReserva consultar(long id);
	public List<StatusPedReserva> buscar();
	public List<StatusPedReserva> consultar(StatusPedReserva status);
	public List<StatusPedReserva> buscar(StatusPedReserva status, int pagina, int rows);
}
