package managedBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import pojo.Permissao;
import pojo.Time;
import pojo.Usuario;
import util.MessagesReader;
import bo.ITimeBO;
import bo.implementation.TimeBO;
import config.Config;

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
	private String filePath;
	private String path;

	public TimeMB() {
		timeBO = new TimeBO();
		time = new Time();
		filePath = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath(Config.getProperty("imagesPath"));
		setPath(Config.getProperty("imagesPath"));
	}

	public static boolean permissao(Usuario usuarioLogado) {

		if (usuarioLogado.getPermissoes().contains(Permissao.TIME)) {
			return true;
		}
		return false;
	}

	public String preIncluir() {
		time = new Time();
		return "criartime?faces-redirect=true";
	}

	public void upload(FileUploadEvent event) {

		arquivo = event.getFile();
	}

	public void criar() {
		ctx = FacesContext.getCurrentInstance();
		FacesMessage facesMsg;
		try {
			
			time.setImagem("/"
					+ Config.getProperty("nomeArqImagem")
					+ Config.getProperty("numImagem")
					+ arquivo.getFileName().substring(
							arquivo.getFileName().lastIndexOf(".")));
			File path = new File(filePath);
			if (!path.exists()) {
				path.mkdir();
			}

			File arqImagem = new File(filePath + time.getImagem());
			if (!arqImagem.exists()) {
				arqImagem.createNewFile();
			}
			Integer numImagem = Integer.parseInt( Config.getProperty("numImagem")) +1;
			Config.setProperty("numImagem", numImagem.toString());
			FileOutputStream fos;
			
			fos = new FileOutputStream(arqImagem);

			fos.write(arquivo.getContents());

			timeBO.create(time);
			time = new Time();
			imagem = null;
			arquivo = null;
		} catch (FileNotFoundException e) {
			String msg = MessagesReader.getMessages().getProperty(
					"problemaArquivo");
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			ctx.addMessage(null, facesMsg);
			e.printStackTrace();
		} catch (IOException e) {
			String msg = MessagesReader.getMessages().getProperty(
					"problemaArquivo");
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);

			e.printStackTrace();
		}
	}

	public void alterar() {
		timeBO.update(time);
	}

	public void excluir() {
		timeBO.delete(time);
	}

	public void setTime(Time time) {
		this.time = time;
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

	public String getFilePath() {
		return filePath;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
