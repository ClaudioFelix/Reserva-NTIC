package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import br.cefetmg.sinapse.ntic.nucleo.dominio.StatusRecurso;

public interface IStatusRecursoDAO{
	public void inserir(StatusRecurso status);
	public StatusRecurso atualizar(StatusRecurso status);
	public void remover(StatusRecurso status);
	public StatusRecurso consultar(long id);
	public List<StatusRecurso> buscar();
	public List<StatusRecurso> consultar(StatusRecurso status);
	public List<StatusRecurso> buscar(StatusRecurso status, int pagina, int rows);
}
