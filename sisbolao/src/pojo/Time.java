package pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name = "time")
public class Time {

	@Id
	@SequenceGenerator(name = "time_seq", sequenceName = "time_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "time_seq")
	private Long id;
	
	@Column(unique=true)
	private String nome;

	@Column
	private String imagem;

	@ManyToOne(targetEntity=Categoria.class,fetch=FetchType.LAZY)	
	private Categoria categoria;
	
	@Transient
	private StreamedContent imagemStream;
	
	

	/**
	 * 
	 * @element-type Partida
	 */

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

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagem() {
		return imagem;
	}
	public StreamedContent getImagemStream() {
		return imagemStream;
	}
	public void setImagemStream(StreamedContent imagemStream) {
		this.imagemStream = imagemStream;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	
	
	

}