package br.cefetmg.sinapse.nucleo.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.cefetmg.sinapse.aaa.nucleo.dao.IAuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dao.jpa.AuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dominio.AuditoriaTipoTransacao;
import br.cefetmg.sinapse.nucleo.dao.IStatusDAO;
import br.cefetmg.sinapse.nucleo.dominio.Status;
import br.cefetmg.sinapse.nucleo.exception.NegocioException;
import br.cefetmg.sinapse.nucleo.util.LazyList;


@Stateless
public class StatusDAO implements IStatusDAO {

    private static final String LISTAR_STATUS = "Status.findAll";
    private static final String CONSULTAR_LIKE_NOME = "Status.findByLikeNome";
    private static final String CONSULTAR_NOME = "Status.findByNome";
    private static final String CONSULTAR_CODIGO = "Status.findByCodigoStatus";
   
   
    @PersistenceContext
    private EntityManager entityManager;

    public void inserir(Status status) throws NegocioException {

        entityManager.persist(status);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public Status atualizar(Status status) throws NegocioException {
        entityManager.merge(status);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return status;
    }

    public void remover(Status status) {
        entityManager.remove(entityManager.getReference(Status.class, status
                .getId()));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public Status consultar(long id) {

        return this.entityManager.find(Status.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<Status> buscar() {
        Query query = entityManager.createNamedQuery(StatusDAO.LISTAR_STATUS);
        List<Status> statusList = query.getResultList();
        return statusList;
    }

    @SuppressWarnings("unchecked")
    public List<Status> consultar(Status status) {

        Query query = this.entityManager
                .createNamedQuery(StatusDAO.CONSULTAR_NOME);
        query.setParameter("nome", status.getNomeStatus().toUpperCase());
               
        return (List<Status>) query.getResultList();

    }
	
	@SuppressWarnings("unchecked")
    public List<Status> consultarCodigo(Status status,long codigo) {

        Query query = this.entityManager
                .createNamedQuery(StatusDAO.CONSULTAR_CODIGO);
        query.setParameter("nome", status.getId().toUpperCase());
               
        return (List<Status>) query.getResultList();

    }
   
   @SuppressWarnings("unchecked")
    public List<Status> bucarLikeNome(Status status,String nome) {

        Query query = this.entityManager
                .createNamedQuery(PaisDAO.CONSULTAR_LIKE_NOME);
        query.setParameter("nome", status.getNomeStatus().toUpperCase());
               
        return (List<Status>) query.getResultList();

    }
}