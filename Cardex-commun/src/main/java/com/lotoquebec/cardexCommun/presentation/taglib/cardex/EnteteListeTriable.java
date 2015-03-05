package com.lotoquebec.cardexCommun.presentation.taglib.cardex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.Globals;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.model.ListeResultat;

/**
 * 
 * @author levassc
 */
public class EnteteListeTriable extends BaseHandlerTag {

	private String name = "";
	private String property = "";
	private String key = "";
	private String URLTrier = "";
	private boolean isCleCouranteAsc = false;
    private boolean isCleCouranteDesc = false;
	private final String IMAGE_ASC = "iconsortupblue.gif";
	private final String IMAGE_DESC = "iconsortdownblue.gif";
	
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		
		if (tagUtils.lookup(pageContext, name, null) == null)
            return (SKIP_BODY);  // Nothing to output

        ListeResultat listeResultat =
            (ListeResultat) tagUtils.lookup(pageContext, name, property, null);
		
		String message = tagUtils.message(pageContext, Globals.MESSAGES_KEY,
				pageContext.getRequest().getLocale().getLanguage(), this.key);

        String retour = "";
        
        if (listeResultat.getTrierListeColumns() != null){
            isCleCouranteAsc = listeResultat.getTrierListeColumns().isCleCompCourante( key, false );
	        isCleCouranteDesc = listeResultat.getTrierListeColumns().isCleCompCourante( key, true );
	        boolean isContientCleTrie = listeResultat.getTrierListeColumns().isContientCle( key );
	        
	        if (isContientCleTrie){
	            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	
	        	retour += "<a href='#' class='enteteColonneTriable' onclick="+construireMethodeTrierSoumettre()+"/>";
	        	retour += message;
	        	
	        	if (isCleCouranteAsc || isCleCouranteDesc){
		        	retour += "&nbsp;<img src='"+request.getContextPath()+"/images/";
		        	
		        	if (isCleCouranteAsc)
		        		retour += IMAGE_ASC;
		        	
		        	else if (isCleCouranteDesc)
		        		retour += IMAGE_DESC;
		        	
		        	retour += "' border='0'/>";
	        	}
	        	retour += "</a>";
	        }else{
	        	retour += message;	
	        }
        }else{
        	retour += message;	
        }
        tagUtils.write(pageContext, retour);		
		
		return SKIP_BODY;
	}
	
	public String construireMethodeTrierSoumettre(){
		String URL = ((HttpServletRequest) pageContext.getRequest()).getContextPath()+URLTrier;
		String str = "";
		str += '"'+"trierSoumettre(";
		str += "'"+URL+"', ";
		str += "'"+property+"', ";
		str += "'"+key+"', ";
		str += "'"+isCleCouranteAsc+"'";
		
		str += ");"+'"';
		
		return str;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public void setURLTrier(String trier) {
		URLTrier = trier;
	}
}