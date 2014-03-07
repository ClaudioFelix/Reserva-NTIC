package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.cefetmg.sinapse.aaa.nucleo.dao.IAuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dao.jpa.AuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dominio.AuditoriaTipoTransacao;

import br.cefetmg.sinapse.ntic.nucleo.dominio.dao.IStatusPedReservaDAO;
import br.cefetmg.sinapse.ntic.nucleo.dominio.StatusPedReserva;

import br.cefetmg.sinapse.nucleo.exception.NegocioException;
import br.cefetmg.sinapse.nucleo.util.LazyList;



@Stateless
public class StatusPedReservaDAO implements IStatusPedReservaDAO {

    private static final String LISTAR_STATUS_PEDRESERVA = "StatusPedReserva.findAll";
    private static final String CONSULTAR_ID = "StatusPedReserva.findById";
    private static final String CONSULTAR_NOME = "StatusPedReserva.findByLikeNome";
    
    
    @PersistenceContext
    private EntityManager entityManager;

    public void inserir(StatusPedReserva status) throws NegocioException {

        entityManager.persist(status);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public StatusPedReserva atualizar(StatusPedReserva status) throws NegocioException {
        entityManager.merge(status);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return status;
    }

    public void remover(StatusPedReserva status) {
        entityManager.remove(entityManager.getReference(StatusPedReserva.class, status
                .getCodStatPedReserva()));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(status, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public StatusPedReserva consultar(long id) {

        return this.entityManager.find(StatusPedReserva.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<StatusPedReserva> buscar() {
        Query query = entityManager.createNamedQuery(StatusPedReservaDAO.LISTAR_STATUS_PEDRESERVA);
        List<StatusPedReserva> titulacoes = query.getResultList();
        return titulacoes;
    }

    @SuppressWarnings("unchecked")
    public List<StatusPedReserva> consultar(StatusPedReserva status) {

        Query query = this.entityManager
                .createNamedQuery(StatusPedReservaDAO.CONSULTAR_NOME);
        query.setParameter("nome", status.getNomeStatPedReserva().toUpperCase());
                
        return (List<StatusPedReserva>) query.getResultList();

    }
    
    @SuppressWarnings("unchecked")
    public List<StatusPedReserva> buscar(StatusPedReserva status, int pagina,
            int rows) {        
        
        StringBuilder param = new StringBuilder();
        
        if(!status.getNomeStatPedReserva().isEmpty()){
            param.append("upper(spr.nome) like '%"
                    + status.getNomeStatPedReserva().toUpperCase() + "%'");
        }       
        
        String consulta = "SELECT spr FROM StatusPedReserva spr ";
        // consulta para contagem do numero de registros total da tabela, usado
        // na paginacao
        String consultaNumRegistros = "SELECT COUNT(spr) FROM StatusPedReserva spr ";

        if (param.length() > 0) {
            consulta = consulta + "WHERE :parametrosParaBusca";
            consulta = consulta.replace(":parametrosParaBusca", param
                    .toString());
            consultaNumRegistros = consultaNumRegistros
                    + "WHERE :parametrosParaBusca";
            consultaNumRegistros = consultaNumRegistros.replace(
                    ":parametrosParaBusca", param.toString());
        }
        
        consulta += " ORDER BY spr.nomeStatPedReserva ";

        // corresposde ao numero total de registros na tabela
        Long numTotalRegistros = (Long) entityManager.createQuery(
                consultaNumRegistros).getSingleResult();

        Query query = entityManager.createQuery(consulta);

        // numRows corresponde ao numero de registros por pagina, vem do MB
        // pagina corresponde a pagina corrente que o usuário está
        List<StatusPedReserva> lista = query.setFirstResult(
                LazyList.getIndicePrimeiroRegistro(pagina, rows))
                .setMaxResults(rows).getResultList();


        return new LazyList(lista, rows, numTotalRegistros);
    }
}