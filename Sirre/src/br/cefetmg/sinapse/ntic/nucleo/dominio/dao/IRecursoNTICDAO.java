package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import br.cefetmg.sinapse.ntic.nucleo.dominio.RecursoNTIC;

public interface IRecursoNTICDAO{
	public void inserir(RecursoNTIC recurso);
	public RecursoNTIC atualizar(RecursoNTIC recurso);
	public void remover(RecursoNTIC recurso);
	public RecursoNTIC consultar(long id);
	public List<RecursoNTIC> buscar();
	public List<RecursoNTIC> consultar(RecursoNTIC recurso);
	public List<RecursoNTIC> buscar(RecursoNTIC recurso, int pagina, int rows);
}