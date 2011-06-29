package pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rodada")
public class Rodada {

	@Id
	@SequenceGenerator(name = "rodada_seq", sequenceName = "rodada_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "rodada_seq")
	private Long id;
	
	@Column
	private String nome;

	/**
	 * 
	 * @element-type Partida
	 */
	@OneToMany(mappedBy="rodada")
	private List<Partida> partidas;
	
	
	@ManyToOne(targetEntity=Campeonato.class)
	private Campeonato campeonato;


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


	public List<Partida> getPartidas() {
		return partidas;
	}


	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}


	public Campeonato getCampeonato() {
		return campeonato;
	}


	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

}