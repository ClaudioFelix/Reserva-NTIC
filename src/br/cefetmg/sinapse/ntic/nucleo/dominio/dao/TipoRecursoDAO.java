package br.cefetmg.sinapse.ntic.nucleo.dominio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.cefetmg.sinapse.aaa.nucleo.dao.IAuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dao.jpa.AuditoriaDAO;
import br.cefetmg.sinapse.aaa.nucleo.dominio.AuditoriaTipoTransacao;


import br.cefetmg.sinapse.ntic.nucleo.dominio.dao.ITipoRecursoDAO;
import br.cefetmg.sinapse.ntic.nucleo.dominio.TipoRecurso;

import br.cefetmg.sinapse.nucleo.exception.NegocioException;
import br.cefetmg.sinapse.nucleo.util.LazyList;

@Stateless
public class TipoRecursoDAO implements ITipoRecursoDAO {

    private static final String LISTAR_TIPORECURSO = "TipoRecurso.findAll";
    private static final String CONSULTAR_NOME = "TipoRecurso.findByNome";
    private static final String CONSULTAR_LIKENOME = "TipoRecurso.findByLikeNome";
    private static final String CONSULTAR_CODIGO = "TipoRecurso.findByCodigoTipoRecurso";

    @PersistenceContext
    private EntityManager entityManager;

    public void inserir(TipoRecurso tipoRecurso) throws NegocioException {

        entityManager.persist(tipoRecurso);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(tipoRecurso, AuditoriaTipoTransacao.INCLUSAO,
                entityManager);
    }

    public TipoRecurso atualizar(TipoRecurso tipoRecurso) throws NegocioException {
        entityManager.merge(tipoRecurso);
        entityManager.flush();

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(tipoRecurso, AuditoriaTipoTransacao.EDICAO,
                entityManager);

        return tipoRecurso;
    }

    public void remover(TipoRecurso tipoRecurso) {
        entityManager.remove(entityManager.getReference(TipoRecurso.class, tipoRecurso
                .getId()));

        // auditoria
        IAuditoriaDAO auditoriaDao = new AuditoriaDAO();
        auditoriaDao.gravarAuditoria(tipoRecurso, AuditoriaTipoTransacao.REMOCAO,
                entityManager);

    }

    public TipoRecurso consultar(long id) {

        return this.entityManager.find(TipoRecurso.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<TipoRecurso> buscar() {
        Query query = entityManager.createNamedQuery(TipoRecursoDAO.LISTAR_TIPORECURSO);
        List<TipoRecurso> titulacoes = query.getResultList();
        return titulacoes;
    }

    @SuppressWarnings("unchecked")
    public List<TipoRecurso> consultar(TipoRecurso tipoRecurso) {

        Query query = this.entityManager
                .createNamedQuery(TipoRecursoDAO.CONSULTAR_NOME);
        query.setParameter("nomeTipoRecurso", TipoRecurso.getNomeTipoRecurso().toUpperCase());
               
        return (List<TipoRecurso>) query.getResultList();
    }
   
    @SuppressWarnings("unchecked")
    public List<TipoRecurso> buscar(TipoRecurso tipoRecurso, int pagina,
            int rows) {       
       
        StringBuilder param = new StringBuilder();
       
        if(!tipoRecurso.getNomeTipoRecurso().isEmpty()){
            param.append("upper(s.nomeTipoRecurso) like '%"
                    + tipoRecurso.getNomeTipoRecurso().toUpperCase() + "%'");
        }
     
        String consulta = "SELECT s FROM TipoRecurso s ";
        // consulta para contagem do numero de registros total da tabela, usado
        // na paginacao
        String consultaNumRegistros = "SELECT COUNT(s) FROM TipoRecurso s ";

        if (param.length() > 0) {
            consulta = consulta + "WHERE :parametrosParaBusca";
            consulta = consulta.replace(":parametrosParaBusca", param
                    .toString());
            consultaNumRegistros = consultaNumRegistros
                    + "WHERE :parametrosParaBusca";
            consultaNumRegistros = consultaNumRegistros.replace(
                    ":parametrosParaBusca", param.toString());
        }
       
        consulta += " ORDER BY s.nomeTipoRecurso ";

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