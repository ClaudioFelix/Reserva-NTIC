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

public void inserir(Status status) throws NegocioException {

    }

    public Status atualizar(Status status) throws NegocioException {
	
    }

    public void remover(Status status) {

    }

    public Status consultar(long id) {

    }

    @SuppressWarnings("unchecked")
    public List<Status> buscar() {
        
    }

    @SuppressWarnings("unchecked")
    public List<Status> consultar(Status status) {

    }
	
	@SuppressWarnings("unchecked")
    public List<Status> consultarCodigo(Status status,long codigo) {

    }
   
   @SuppressWarnings("unchecked")
    public List<Status> bucarLikeNome(Status status,String nome) {

    }
}