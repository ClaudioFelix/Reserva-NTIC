package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import br.cefetmg.sinapse.ntic.nucleo.dominio.TipoRecurso;

public interface ITipoRecursoDAO {
	public void inserir(TipoRecurso tipoRecurso);
	public TipoRecurso atualizar(TipoRecurso tipoRecurso);
	public void remover(TipoRecurso tipoRecurso);
	public TipoRecurso consultar(long id);
	public List<TipoRecurso> buscar();
	public List<TipoRecurso> consultar(TipoRecurso tipoRecurso);
	public List<TipoRecurso> buscar(TipoRecurso tipoRecurso, int pagina, int rows);
}
