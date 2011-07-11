package filter;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import managedBean.CadastroMB;
import managedBean.LoginMB;


public class LoginFilter implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		 FacesContext context = event.getFacesContext();
		   boolean isCadastro = context.getViewRoot().getViewId().contains("cadastro");
	       boolean isIndex = context.getViewRoot().getViewId().lastIndexOf("index") > -1? true:false;	 
	       boolean isRecuperacao = context.getViewRoot().getViewId().contains("recuperar");
	       String view = context.getViewRoot().getViewId();
	       // pegar o managed bean de sessão
	       Application app = context.getApplication();

	       ValueExpression expression = app.getExpressionFactory().createValueExpression(context.getELContext(),
	                            String.format("#{%s}", "loginMB"), Object.class);

	       LoginMB loginMB = (LoginMB) expression.getValue(context.getELContext());
	      
	       if(loginMB!=null){
	    	   if(!isCadastro && !isRecuperacao && !( isIndex) && loginMB.getUsuarioLogado()== null ) {
		           NavigationHandler nh = app.getNavigationHandler();
		           loginMB.setLastPage(view);
		           nh.handleNavigation(context, null, "index?faces-redirect=true");
		       }else if(loginMB.getUsuarioLogado()!= null && isIndex) {
		    	   NavigationHandler nh = app.getNavigationHandler();
		           nh.handleNavigation(context, null,"principal");
		       } else  if(loginMB.isTestLogged()){
	    		   NavigationHandler nh = app.getNavigationHandler();
	    		   loginMB.setTestLogged(false);
	    		   
		           nh.handleNavigation(context, null, loginMB.getLastPage()+"?faces-redirect=true");
		           loginMB.setLastPage("principal");
		           
		       }
	       } else {
	    	   if(!isCadastro && !isRecuperacao && !isIndex){
	    		   NavigationHandler nh = app.getNavigationHandler();
		           nh.handleNavigation(context, null, "index?faces-redirect=true");
	    	   }
	    	   
	       } 
	       
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {

		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
