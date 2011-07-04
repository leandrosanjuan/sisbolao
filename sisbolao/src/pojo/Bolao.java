package pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bolao")
public class Bolao {

	@Id
	@SequenceGenerator(name = "bolao_seq", sequenceName = "bolao_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "bolao_seq")
	private Long id;

	@Column
	private String nome;

	@ManyToOne(targetEntity = Campeonato.class, fetch = FetchType.LAZY)
	private Campeonato campeonato;
	/**
	 * 
	 * @element-type Usuario
	 */
	@ManyToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY)
	private List<Usuario> usuarios;

	@OneToOne
	private Usuario usuarioDono;

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

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioDono() {
		return usuarioDono;
	}

	public void setUsuarioDono(Usuario usuarioDono) {
		this.usuarioDono = usuarioDono;
	}

}