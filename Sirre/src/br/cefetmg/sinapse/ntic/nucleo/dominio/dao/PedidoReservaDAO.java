package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.cefetmg.sinapse.aaa.nucleo.dao.IAuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dao.jpa.AuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dominio.AuditoriaTipoTransacao;

import br.cefetmg.sinapse.ntic.nucleo.dominio.dao.IPedidoReservaDAO;
import br.cefetmg.sinapse.ntic.nucleo.dominio.PedidoReserva;
import br.cefetmg.sinapse.ntic.nucleo.dominio.RecursoNTIC;
import br.cefetmg.sinapse.ntic.nucleo.dominio.StatusPedReserva;

import br.cefetmg.sinapse.nucleo.exception.NegocioException;
import br.cefetmg.sinapse.nucleo.util.LazyList;



@Stateless
public class PedidoReservaDAO implements IPedidoReservaDAO {

    private static final String LISTAR_PEDIDORESERVA = "PedidoReserva.findAll";
    private static final String CONSULTAR_POR_ID_RECURSONTIC = "PedidoReserva.findByidRecursoNTIC";
    private static final String CONSULTAR_POR_ID_USUARIO_RESERVADOR = "PedidoReserva.findByidUsuarioReservador";
    private static final String CONSULTAR_POR_ID_USUARIO_UTILIZADOR = "PedidoReserva.findByidUsuarioUtilizador";
    private static final String CONSULTAR_POR_CODSTATPEDRESERVA = "PedidoReserva.findByCodStatPedReserva";
    
    @PersistenceContext
    private EntityManager entityManager;

    public void inserir(PedidoReserva pedido) throws NegocioException {

        entityManager.persist(pedido);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(pedido, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public PedidoReserva atualizar(PedidoReserva pedido) throws NegocioException {
        entityManager.merge(pedido);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(pedido, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return pedido;
    }

    /* Problema na remoção pois não temos um id fixo para a Entidadde PedidoReserva */
    public void remover(PedidoReserva pedido) {
        entityManager.remove(entityManager.getReference(PedidoReserva.class, pedido));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(pedido, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public PedidoReserva consultar(long id) {

        return this.entityManager.find(PedidoReserva.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<PedidoReserva> buscar() {
        Query query = entityManager.createNamedQuery(PedidoReservaDAO.LISTAR_PEDIDORESERVA);
        List<PedidoReserva> titulacoes = query.getResultList();
        return titulacoes;
    }

    @SuppressWarnings("unchecked")
    public List<PedidoReserva> consultarPorIdRecursoNTIC(RecursoNTIC recurso) {

        Query query = this.entityManager
                .createNamedQuery(PedidoReservaDAO.CONSULTAR_POR_ID_RECURSONTIC);
        query.setParameter("id", recurso.getIdRecursoNTIC());
                
        return (List<PedidoReserva>) query.getResultList();

    }
    
    
    @SuppressWarnings("unchecked")
    public List<PedidoReserva> consultarPorCodStatPedReserva(StatusPedReserva status) {

        Query query = this.entityManager
                .createNamedQuery(PedidoReservaDAO.CONSULTAR_POR_ID_RECURSONTIC);
        query.setParameter("codStatus", status.getCodStatPedReserva());
                
        return (List<PedidoReserva>) query.getResultList();

    }
    /* Falta Dados para a implementação */
    @SuppressWarnings("unchecked")
    public List<PedidoReserva> consultarPorIdUsuarioReservador(Usuario usuario) {

        Query query = this.entityManager
                .createNamedQuery(PedidoReservaDAO.CONSULTAR_POR_ID_USUARIO_RESERVADOR);
        query.setParameter("id", usuario.getIdUsuario());
                
        return (List<PedidoReserva>) query.getResultList();

    }
    /* Falta Dados para a implementação */
    @SuppressWarnings("unchecked")
    public List<PedidoReserva> consultarPorIdUsuarioUtilizador(Usuario usuario) {

        Query query = this.entityManager
                .createNamedQuery(PedidoReservaDAO.CONSULTAR_POR_ID_USUARIO_UTILIZADOR);
        query.setParameter("id", usuario.getIdUsuario());
                
        return (List<PedidoReserva>) query.getResultList();

    }

    
    
}
