package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.cefetmg.sinapse.aaa.nucleo.dao.IAuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dao.jpa.AuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dominio.AuditoriaTipoTransacao;


import br.cefetmg.sinapse.ntic.nucleo.dominio.dao.IRecursoNTICDAO;
import br.cefetmg.sinapse.ntic.nucleo.dominio.RecursoNTIC;

import br.cefetmg.sinapse.nucleo.exception.NegocioException;
import br.cefetmg.sinapse.nucleo.util.LazyList;

@Stateless
public class RecursoNTICDAO implements IRecursoNTICDAO {

	private static final String LISTAR_RECURSONTIC = "Recurso.findAll";
	private static final String CONSULTAR_ID = "Recurso.findById";
	private static final String CONSULTAR_NOME = "Recurso.findByNome";
	private static final String CONSULTAR_LOCAL = "Recurso.findByLocal";

	@PersistenceContext
	private EntityManager entityManager;

    public void inserir(RecursoNTIC recursoNTIC) throws NegocioException {

        entityManager.persist(recursoNTIC);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(recursoNTIC, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public RecursoNTIC atualizar(RecursoNTIC recursoNTIC) throws NegocioException {
        entityManager.merge(recursoNTIC);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(recursoNTIC, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return recursoNTIC;
    }

    public void remover(RecursoNTIC recursoNTIC) {
        entityManager.remove(entityManager.getReference(RecursoNTIC.class, recursoNTIC
                .getId()));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(recursoNTIC, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public RecursoNTIC consultar(long id) {

        return this.entityManager.find(RecursoNTIC.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<TipoRecurso> buscar() {
        Query query = entityManager.createNamedQuery(RecursoNTICDAO.LISTAR_RECURSONTIC);
        List<TipoRecurso> titulacoes = query.getResultList();
        return titulacoes;
    }

    @SuppressWarnings("unchecked")
    public List<TipoRecurso> consultar(RecursoNTIC recursoNTIC) {

        Query query = this.entityManager
                .createNamedQuery(RecursoNTICDAO.CONSULTAR_NOME);
        query.setParameter("nome", RecursoNTIC.getNome().toUpperCase());
               
        return (List<TipoRecurso>) query.getResultList();
    }
   
    @SuppressWarnings("unchecked")
    public List<TipoRecurso> buscar(RecursoNTIC recursoNTIC, int pagina,
            int rows) {       
       
        StringBuilder param = new StringBuilder();
       
        if(!recursoNTIC.getNome().isEmpty()){
            param.append("upper(p.nome) like '%"
                    + recursoNTIC.getNome().toUpperCase() + "%'");
        }
     
        String consulta = "SELECT p FROM RecursoNTIC p ";
        // consulta para contagem do numero de registros total da tabela, usado
        // na paginacao
        String consultaNumRegistros = "SELECT COUNT(p) FROM RecursoNTIC p ";

        if (param.length() > 0) {
            consulta = consulta + "WHERE :parametrosParaBusca";
            consulta = consulta.replace(":parametrosParaBusca", param
                    .toString());
            consultaNumRegistros = consultaNumRegistros
                    + "WHERE :parametrosParaBusca";
            consultaNumRegistros = consultaNumRegistros.replace(
                    ":parametrosParaBusca", param.toString());
        }
       
        consulta += " ORDER BY p.nome ";

        // corresposde ao numero total de registros na tabela
        Long numTotalRegistros = (Long) entityManager.createQuery(
                consultaNumRegistros).getSingleResult();

        Query query = entityManager.createQuery(consulta);

        // numRows corresponde ao numero de registros por pagina, vem do MB
        // pagina corresponde a pagina corrente que o usuário está
        List lista = query.setFirstResult(
                LazyList.getIndicePrimeiroRegistro(pagina, rows))
                .setMaxResults(rows).getResultList();

        return new LazyList(lista, rows, numTotalRegistros);
    }	
}