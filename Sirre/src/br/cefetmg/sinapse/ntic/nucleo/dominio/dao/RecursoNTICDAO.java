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

    private static final String LISTAR_RECURSO = "RecursoNTIC.findAll";
    private static final String CONSULTAR_ID = "RecursoNTIC.findById";
    private static final String CONSULTAR_NOME = "RecursoNTIC.findByNome";
    private static final String CONSULTAR_NOME_SIMILAR = "RecursoNTIC.findByLikeNome";
    private static final String CONSULTAR_COUNT_NOME_SIMILAR = "RecursoNTIC.findByCountLikeNome";
    private static final String CONSULTAR_CODIGO_TIPO = "RecursoNTIC.findByCodTipoRecurso";
    private static final String CONSULTAR_CODIGO_STATUS = "RecursoNTIC.findByCodStatusRecurso";

    
    @PersistenceContext
    private EntityManager entityManager;

    public void inserir(RecursoNTIC recurso) throws NegocioException {

        entityManager.persist(recurso);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(recurso, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public RecursoNTIC atualizar(RecursoNTIC recurso) throws NegocioException {
        entityManager.merge(recurso);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(recurso, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return recurso;
    }

    public void remover(RecursoNTIC recurso) {
        entityManager.remove(entityManager.getReference(RecursoNTIC.class, recurso
                .getIdRecursoNTIC()));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(recurso, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public RecursoNTIC consultar(long id) {

        return this.entityManager.find(RecursoNTIC.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<RecursoNTIC> buscar() {
        Query query = entityManager.createNamedQuery(RecursoNTICDAO.LISTAR_RECURSO);
        List<RecursoNTIC> titulacoes = query.getResultList();
        return titulacoes;
    }

    @SuppressWarnings("unchecked")
    public List<RecursoNTIC> consultar(RecursoNTIC recurso) {

        Query query = this.entityManager
                .createNamedQuery(RecursoNTICDAO.CONSULTAR_NOME);
        query.setParameter("nome", recurso.getNomeStatRecurso().toUpperCase());
                
        return (List<RecursoNTIC>) query.getResultList();

    }
    
    @SuppressWarnings("unchecked")
    public List<RecursoNTIC> buscar(RecursoNTIC recurso, int pagina,
            int rows) {        
        
        StringBuilder param = new StringBuilder();
        
        if(!recurso.getNomeRecursoNTIC().isEmpty()){
            param.append("upper(r.nome) like '%"
                    + recurso.getNomeRecursoNTIC().toUpperCase() + "%'");
        }       
        
        String consulta = "SELECT r FROM RecursoNTIC r ";
        // consulta para contagem do numero de registros total da tabela, usado
        // na paginacao
        String consultaNumRegistros = "SELECT COUNT(r) FROM RecursoNTIC r ";

        if (param.length() > 0) {
            consulta = consulta + "WHERE :parametrosParaBusca";
            consulta = consulta.replace(":parametrosParaBusca", param
                    .toString());
            consultaNumRegistros = consultaNumRegistros
                    + "WHERE :parametrosParaBusca";
            consultaNumRegistros = consultaNumRegistros.replace(
                    ":parametrosParaBusca", param.toString());
        }
        
        consulta += " ORDER BY r.nomeStatRecurso ";

        // corresposde ao numero total de registros na tabela
        Long numTotalRegistros = (Long) entityManager.createQuery(
                consultaNumRegistros).getSingleResult();

        Query query = entityManager.createQuery(consulta);

        // numRows corresponde ao numero de registros por pagina, vem do MB
        // pagina corresponde a pagina corrente que o usuário está
        List<RecursoNTIC> lista = query.setFirstResult(
                LazyList.getIndicePrimeiroRegistro(pagina, rows))
                .setMaxResults(rows).getResultList();


        return new LazyList(lista, rows, numTotalRegistros);
    }
}