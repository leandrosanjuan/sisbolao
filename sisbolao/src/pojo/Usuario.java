package pojo;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {


	@Id
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_seq")
	private Long id;
	
	@Column
	private String nome;

	@Column
	private Boolean pagamento;

	@Column
	private String email;

	@Column
	private String login;

	@Column
	private String senha;

	/**
	 * 
	 * @element-type Palpite
	 */
	@OneToMany(mappedBy="usuario")
	private List<Palpite> palpites;
	/**
	 * 
	 * @element-type Bolao
	 */
	@ManyToMany(mappedBy="usuarios")
	private List<Bolao> boloes;
	
	@Enumerated(EnumType.ORDINAL)
	private Perfil perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getPagamento() {
		return pagamento;
	}

	public void setPagamento(Boolean pagamento) {
		this.pagamento = pagamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Palpite> getPalpites() {
		return palpites;
	}

	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}

	public List<Bolao> getBoloes() {
		return boloes;
	}

	public void setBoloes(List<Bolao> boloes) {
		this.boloes = boloes;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}