package com.lotoquebec.cardexCommun.presentation.taglib.cardex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;

/**
 * 
 * @author levassc
 */
public class NavigationResultatListe extends BaseHandlerTag {

	private String name = "";
	private String property = "";
	private String URLChangerPage = "";
	

	
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String URL = request.getContextPath()+URLChangerPage;
		
		if (tagUtils.lookup(pageContext, name, null) == null)
            return (SKIP_BODY);  // Nothing to output

        ListeResultat listeResultat = (ListeResultat) tagUtils.lookup(pageContext, name, property, null);

        StringBuilder retour = new StringBuilder("");
        int pageCourante = listeResultat.getNumeroPageCourant();
        
        if (listeResultat.isPossedePrecedant())
        	retour.append( construrieAncre(URL, "|&lt;&lt;", 1) );
        else
        	retour.append( "<b style='color:#999999;'>|&lt;&lt;</b>" );
        retour.append( "&nbsp;&nbsp;" );
        
        if (listeResultat.isPossedePrecedant())
        	retour.append( construrieAncre(URL, "&lt;&lt;", pageCourante-1) );
        else
        	retour.append( "<b style='color:#999999;'>&lt;&lt;</b>" );
        retour.append( "&nbsp;&nbsp;" );
        
        retour.append( "&nbsp;&nbsp;<input type='text' id='"+property+".numeroPageCourante' name='"+property+".numeroPageCourante' size='1' maxlength='4' value='" );
        retour.append( listeResultat.getNumeroPageCourant() );
        retour.append( "' "); 
        
        retour.append("onkeydown=\"return retourPostControleNavigation('"+URL+"','"+property+"',this.value);\" /><b>&nbsp;/&nbsp;" );
        
        retour.append( listeResultat.getNombrePageTotal() );
        retour.append( "&nbsp;&nbsp;</b>" );
        
        if (listeResultat.isPossedeSuivant())
        	retour.append( construrieAncre(URL, "&gt;&gt;", pageCourante+1) );
        else
        	retour.append( "<b style='color:#999999;'>&gt;&gt;</b>" );
        retour.append( "&nbsp;&nbsp;" );
        
        if (listeResultat.isPossedeSuivant())
        	retour.append( construrieAncre(URL, "&gt;&gt;|", listeResultat.getNombrePageTotal()) );
        else
        	retour.append( "<b style='color:#999999;'>&gt;&gt;|</b>" );
        
        tagUtils.write(pageContext, retour.toString());		
		
		return SKIP_BODY;
	}
	
	private String construrieAncre(String URL, String affichage, int page){
		return "<a href=\"javascript:soumettreControleNavigation('"+URL+"','"+property+"','"+page+"');\"><b>"+affichage+"</b></a>";
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setURLChangerPage(String changerPage) {
		URLChangerPage = changerPage;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
}