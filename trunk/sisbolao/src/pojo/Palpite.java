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
	private int golCasa;

	@Column
	private int golVisitante;

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

	public int getGolCasa() {
		return golCasa;
	}

	public void setGolCasa(int golCasa) {
		this.golCasa = golCasa;
	}

	public int getGolVisitante() {
		return golVisitante;
	}

	public void setGolVisitante(int golVisitante) {
		this.golVisitante = golVisitante;
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

}