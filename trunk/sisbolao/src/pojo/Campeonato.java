package pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "campeonato")
public class Campeonato   {

	@Id
	@SequenceGenerator(name = "campeonato_seq", sequenceName = "campeonato_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "campeonato_seq")
	private Long id;

	@Column(unique=true)
	private String nome;

	/**
	 * 
	 * @element-type Bolao
	 */
	@OneToMany(mappedBy="campeonato")
	private List<Bolao> boloes;
	/**
	 * 
	 * @element-type Rodada
	 */
	@OneToMany(mappedBy="campeonato")
	private List<Rodada> rodadas;
	
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
	public List<Bolao> getBoloes() {
		if(boloes == null) {
			boloes = new ArrayList<Bolao>();
		}
		return boloes;
	}
	public void setBoloes(List<Bolao> boloes) {
		this.boloes = boloes;
	}
	public List<Rodada> getRodadas() {
		return rodadas;
	}
	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

}