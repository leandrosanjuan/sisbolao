package managedBean;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import pojo.Permissao;
import pojo.Time;
import pojo.Usuario;
import util.MessagesReader;
import bo.ITimeBO;
import bo.implementation.TimeBO;

@ManagedBean(name = "timeMB")
@SessionScoped
public class TimeMB {
	FacesContext ctx;
	FacesMessage msg;
	private ITimeBO timeBO;
	private Time time;
	private List<Time> times;
	private StreamedContent imagem;
	private UploadedFile arquivo;

	public TimeMB() {
		timeBO = new TimeBO();
		time = new Time();

	}

	public static boolean permissao(Usuario usuarioLogado) {
		if (usuarioLogado.getPermissoes().contains(Permissao.TIME)) {
			return true;
		}
		return false;
	}

	public void upload(FileUploadEvent event) {
		ctx = FacesContext.getCurrentInstance();
		try {
			arquivo = event.getFile();
			imagem = new DefaultStreamedContent(arquivo.getInputstream());
		} catch (IOException e) {
			String mensagem = MessagesReader.getMessages().getProperty(
					"problemaArquivo");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
					mensagem);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void criar() {
		
		time.setImagem(arquivo.getContents());
		timeBO.create(time);
		time = new Time();
		imagem = null;
		arquivo = null;

	}

	public void setTime(Time time) {
		time = time;
	}

	public Time getTime() {
		return time;
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	public StreamedContent getImagem() {
		return imagem;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}
	public List<Time> getTimes() {
		return timeBO.findAll();
	}
}
