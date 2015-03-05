package com.lotoquebec.cardex.presentation.taglib.html;

import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.SelectTag;
import org.apache.struts.util.ResponseUtils;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.presentation.taglib.cardex.OptionTag;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;


/**
 * Tag pour créer de multiple options &lt;select&gt; à partir d'une collection.
 * Ce tag est spécialisé pour affiché les code de sévérité selon les code de
 * couleur suivant:
 * <ul>
 * <li>1 - rouge</li>
 * <li>2 - jaune</li>
 * <li>3 - vert </li>
 * <li>4 - bleu </li>
 * </ul>
 *
 * @see org.apache.struts.taglib.html.OptionsTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $ $Date: 2002/02/08 17:38:19 $
 */
public class SeveriteOptionsTag extends OptionTag {

	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		
		try{
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.getSession().getAttribute(AuthenticationSubject.class.getName());
			
	        SelectTag selectTag =
	            (SelectTag) pageContext.getAttribute(Constants.SELECT_KEY);
			
	        Iterator iter = obtenirListe(subject, GlobalConstants.CleListe.SEVERITE).iterator();
	        StringBuilder html = new StringBuilder();
	        while (iter.hasNext()) {
				LabelValueBean label = (LabelValueBean) iter.next();
				html.append("<option value=\"");
				html.append(label.getValue());
				html.append("\"");
				
				if (selectTag.isMatched(label.getValue())){
		        	html.append(" selected=\"selected\"");
		        }
		        if (ResponseUtils.filter(label.getLabel()) != null){
		        	html.append(" class=\"severity"+ ResponseUtils.filter(label.getLabel()).trim() +"\"");
		        }
		        html.append(">");
		        html.append(ResponseUtils.filter(label.getLabel()));
		        html.append("</option>\r\n");
			}
	        
	        tagUtils.write(pageContext, html.toString());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}    	
		
		return (SKIP_BODY);
    }
	

}