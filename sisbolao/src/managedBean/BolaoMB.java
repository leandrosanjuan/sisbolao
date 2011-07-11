package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pojo.Bolao;
import pojo.Campeonato;
import pojo.Permissao;
import pojo.Usuario;
import util.EnviaEmail;
import util.MessagesReader;
import bo.IBolaoBO;
import bo.ICampeonatoBO;
import bo.implementation.BolaoBO;
import bo.implementation.CampeonatoBO;


@ManagedBean(name="bolaoMB")
@SessionScoped
public class BolaoMB {
	
	private static Usuario usuario;
	private IBolaoBO bolaoBO;
	private Bolao bolao;
	private ICampeonatoBO campeonatoBO;
	
	private FacesContext ctx;
	
	private List<Campeonato> campeonatosDisponiveis;
	private List<SelectItem> campeonatosSI;
	private Campeonato campeonato;
	private List<Bolao> meusBoloes;
	private String participantes;
	
	public BolaoMB() {
		setBolao(new Bolao());
		bolaoBO = new BolaoBO();
		campeonatoBO = new CampeonatoBO();
		campeonatosSI = new ArrayList<SelectItem>();
	}
	
	public static boolean permissao(Usuario usuarioLogado){
		usuario = usuarioLogado;
		if(usuario.getPermissoes().contains(Permissao.BOLAO)){
			return true;
		}
		return false;
	}
	public String preCriar(){
		
		campeonato = new Campeonato();
		bolao = new Bolao();
		return "criarbolao?faces-redirect=true";
	}
	public void criar(){
		ctx = FacesContext.getCurrentInstance();
		Usuario usuario = LoginMB.getUsuarioLogadoExt();
		bolao.setUsuarioDono(usuario);
		String[] convidados;
		if(participantes.contains(";")){
			convidados = participantes.split(";");
		} else {
			convidados = new String[] {participantes};
		}
		for(String convidado:convidados) {
			
			try {
			//	EnviaEmail.enviar("convite", "sisbolao", convidado, convidado);
				
				bolaoBO.create(bolao);							
			} catch (Exception e) {
				String m = MessagesReader.getMessages().getProperty("problemaEmail");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m);
				e.printStackTrace();
			}
		}
		
		
	}
	
	public List<Campeonato> getCampeonatosDisponiveis() {		
		return campeonatosDisponiveis;
	}


	public void setCampeonatosDisponiveis() {
		
		this.campeonatosDisponiveis = campeonatoBO.findAll();
	}


	public Campeonato getCampeonato() {
		return campeonato;
	}


	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setCampeonatosSI(List<SelectItem> campeonatosSI) {
		this.campeonatosSI = campeonatosSI;
	}

	public List<SelectItem> getCampeonatosSI() {
		setCampeonatosDisponiveis();
		for (Campeonato campeonato : campeonatosDisponiveis) {
			SelectItem item = new SelectItem(campeonato, campeonato.getNome());
			campeonatosSI.add(item);
		}
		return campeonatosSI;
	}

	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}

	public String getParticipantes() {
		return participantes;
	}


	public List<Bolao> getMeusBoloes() {
		return bolaoBO.findByParticipant(LoginMB.getUsuarioLogadoExt());
	}

	

	

	
	
}
