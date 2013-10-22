package br.cefetmg.sinapse.ntic.nucleo.dominio;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recurso
 *
 */
@Entity
@NamedQueries( {
    @NamedQuery(name = "Recurso.findAll", query = "SELECT p FROM Recurso p"),
    @NamedQuery(name = "Recurso.findById", query = "SELECT p FROM Recurso p WHERE p.id = :id"),
    @NamedQuery(name = "Recurso.findByNome", query = "SELECT p FROM Recurso p WHERE p.nome = :nome"),
    @NamedQuery(name = "Recurso.findByLikeNome", query = "SELECT p FROM Recurso p WHERE upper(p.nome) like :nome"),
    @NamedQuery(name = "Recurso.findByCountLikeNome", query = "SELECT COUNT(p) FROM Recurso p WHERE upper(p.nome) like :nome"),
    @NamedQuery(name = "Recurso.findByLocal", query = "SELECT p FROM Recurso p WHERE p.local = :local"),
    @NamedQuery(name = "Recurso.findByTipo", query = "SELECT p FROM Recurso p WHERE p.tipo = :tipo")
})

public class Recurso{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 0;
	
	@Lob
	private String descricao = "";
	
	private int funcional;
	private String nome = "";
	
	@ManyToOne
	@JoinColumn(name = "id_tipo")
	private TipoRecurso tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_unidade_organizacional")
	private UnidadeOrganizacional local;
	
	
	public Recurso() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getFuncional() {
		return funcional;
	}

	public void setFuncional(int funcional) {
		this.funcional = funcional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoRecurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoRecurso tipo) {
		this.tipo = tipo;
	}

	public UnidadeOrganizacional getLocal() {
		return local;
	}

	public void setLocal(UnidadeOrganizacional local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Recurso [id=" + id + ", descricao=" + descricao
				+ ", funcional=" + funcional + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + funcional;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recurso other = (Recurso) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (funcional != other.funcional)
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}   
}
