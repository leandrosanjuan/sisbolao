package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="palpite")
public class Palpite {
	
	@Id
	@SequenceGenerator(name = "palpite_seq", sequenceName = "palpite_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "palpite_seq")
	private Long id;
	
	@Column
	private String golCasa;

	@Column
	private String golVisitante;

	@ManyToOne(targetEntity=Usuario.class,fetch=FetchType.LAZY)	
	private Usuario usuario;
	
	@ManyToOne(targetEntity=Partida.class,fetch=FetchType.LAZY)
	private Partida partida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public String getGolVisitante() {
		return golVisitante;
	}

	public void setGolVisitante(String golVisitante) {
		this.golVisitante = golVisitante;
	}

	public String getGolCasa() {
		return golCasa;
	}

	public void setGolCasa(String golCasa) {
		this.golCasa = golCasa;
	}

}