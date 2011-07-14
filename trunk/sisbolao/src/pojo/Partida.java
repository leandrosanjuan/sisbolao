package pojo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "partida")
public class Partida {

	@Id
	@SequenceGenerator(name = "partida_seq", sequenceName = "partida_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "partida_seq")
	private Long id;

	@Column
	private int golCasa;

	@Column
	private int golVisitante;

	@Column
	private Calendar dataHora;

	@Transient
	private Date dtHora;

	@ManyToOne(targetEntity = Rodada.class, fetch = FetchType.LAZY)
	private Rodada rodada;
	/**
	 * 
	 * @element-type Palpite
	 */
	@OneToMany(mappedBy = "partida")
	private List<Palpite> palpite;

	@OneToOne(targetEntity = Time.class)
	private Time timeCasa;

	@OneToOne(targetEntity = Time.class)
	private Time timeVisitante;

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

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public List<Palpite> getPalpite() {
		return palpite;
	}

	public void setPalpite(List<Palpite> palpite) {
		this.palpite = palpite;
	}

	public Time getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(Time timeCasa) {
		this.timeCasa = timeCasa;
	}

	public Time getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(Time timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public void setDtHora(Date dtHora) {
		this.dtHora = dtHora;

		// Setando a variável persistente dataHora
		Calendar dtHr = new GregorianCalendar();
		dtHr.setTime(dtHora);
		this.dataHora = dtHr;
	}

	public Date getDtHora() {
		return dtHora;
	}

}