package pojo;

import java.io.ByteArrayInputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.omg.CORBA.portable.InputStream;
import org.primefaces.model.DefaultStreamedContent;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

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

	@Transient
	private DefaultStreamedContent imagemStream;

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

	public DefaultStreamedContent getImagemStream() {

		ByteArrayInputStream is = new ByteArrayInputStream(imagem);

		return new DefaultStreamedContent(is);
	}

}