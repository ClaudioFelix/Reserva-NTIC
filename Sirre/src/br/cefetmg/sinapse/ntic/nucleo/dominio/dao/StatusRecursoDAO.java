package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.cefetmg.sinapse.aaa.nucleo.dao.IAuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dao.jpa.AuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dominio.AuditoriaTipoTransacao;
import br.cefetmg.sinapse.nucleo.exception.NegocioException;
import br.cefetmg.sinapse.nucleo.util.LazyList;
import br.cefetmg.sinapse.ntic.nucleo.dominio.dao.IStatusRecursoDAO;
import br.cefetmg.sinapse.ntic.nucleo.dominio.StatusRecurso;



@Stateless
public class StatusRecursoDAO implements IStatusRecursoDAO {

    private static final String LISTAR_STATUS_RECURSO = "StatusRecurso.findAll";
    private static final String CONSULTAR_ID = "StatusRecurso.findById";
    private static final String CONSULTAR_NOME = "StatusRecurso.findByLikeNome";
    
    
    @PersistenceContext
    private EntityManager entityManager;

    public void inserir(StatusRecurso status) throws NegocioException {

        entityManager.persist(status);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public StatusRecurso atualizar(StatusRecurso status) throws NegocioException {
        entityManager.merge(status);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return status;
    }

    public void remover(StatusRecurso status) {
        entityManager.remove(entityManager.getReference(StatusRecurso.class, status
                .getCodStatRecurso()));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public StatusRecurso consultar(long id) {

        return this.entityManager.find(StatusRecurso.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<StatusRecurso> buscar() {
        Query query = entityManager.createNamedQuery(StatusRecursoDAO.LISTAR_STATUS_RECURSO);
        List<StatusRecurso> titulacoes = query.getResultList();
        return titulacoes;
    }

    @SuppressWarnings("unchecked")
    public List<StatusRecurso> consultar(StatusRecurso status) {

        Query query = this.entityManager
                .createNamedQuery(StatusRecursoDAO.CONSULTAR_NOME);
        query.setParameter("nome", status.getNomeStatRecurso().toUpperCase());
                
        return (List<StatusRecurso>) query.getResultList();

    }
    
    @SuppressWarnings("unchecked")
    public List<StatusRecurso> buscar(StatusRecurso status, int pagina,
            int rows) {        
        
        StringBuilder param = new StringBuilder();
        
        if(!status.getNomeStatRecurso().isEmpty()){
            param.append("upper(sr.nome) like '%"
                    + status.getNomeStatRecurso().toUpperCase() + "%'");
        }       
        
        String consulta = "SELECT sr FROM StatusRecurso sr ";
        // consulta para contagem do numero de registros total da tabela, usado
        // na paginacao
        String consultaNumRegistros = "SELECT COUNT(sr) FROM StatusRecurso sr ";

        if (param.length() > 0) {
            consulta = consulta + "WHERE :parametrosParaBusca";
            consulta = consulta.replace(":parametrosParaBusca", param
                    .toString());
            consultaNumRegistros = consultaNumRegistros
                    + "WHERE :parametrosParaBusca";
            consultaNumRegistros = consultaNumRegistros.replace(
                    ":parametrosParaBusca", param.toString());
        }
        
        consulta += " ORDER BY sr.nomeStatRecurso ";

        // corresposde ao numero total de registros na tabela
        Long numTotalRegistros = (Long) entityManager.createQuery(
                consultaNumRegistros).getSingleResult();

        Query query = entityManager.createQuery(consulta);

        // numRows corresponde ao numero de registros por pagina, vem do MB
        // pagina corresponde a pagina corrente que o usuário está
        List<StatusRecurso> lista = query.setFirstResult(
                LazyList.getIndicePrimeiroRegistro(pagina, rows))
                .setMaxResults(rows).getResultList();


        return new LazyList(lista, rows, numTotalRegistros);
    }
}