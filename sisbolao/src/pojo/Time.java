package pojo;

import java.io.ByteArrayInputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name = "time")
public class Time {

	@Id
	@SequenceGenerator(name = "time_seq", sequenceName = "time_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "time_seq")
	private Long id;

	@Column
	private String nome;

	@Column
	private byte[] imagem;

	@Column
	private String imagemContentType;

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

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public void setImagemStream(StreamedContent imagemStream) {
		this.imagemStream = imagemStream;
	}

	public StreamedContent getImagemStream() {		

		ByteArrayInputStream is = new ByteArrayInputStream(imagem);

		imagemStream = new DefaultStreamedContent(is, imagemContentType);

		return imagemStream;

	}

	public void setImagemContentType(String imagemContentType) {
		this.imagemContentType = imagemContentType;
	}

	public String getImagemContentType() {
		return imagemContentType;
	}

}