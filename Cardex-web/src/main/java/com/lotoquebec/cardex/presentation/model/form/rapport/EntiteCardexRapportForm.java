package com.lotoquebec.cardex.presentation.model.form.rapport;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.EntiteCardexVO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class EntiteCardexRapportForm extends RapportForm{

	private static final long serialVersionUID = -2539535659271231511L;

	public EntiteCardexRapportForm() {
		super(null);
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
		String cle = (String) request.getParameter("cle");
		String site = (String) request.getParameter("site");
		EntiteCardexVO entiteCardexVO = new EntiteCardexVO(Long.valueOf(cle),Long.valueOf(site));
		//entiteCardexVO = new SujetBusinessDelegate().find(subject, sujet);
		return obtenirGenererRapport(request).executer(subject, entiteCardexVO, locale);
	}

	protected GenererRapport obtenirGenererRapport(HttpServletRequest request){
        String genererRapportclassStr = (String)request.getParameter("genererRapporClass");
        
        if (StringUtils.isNotEmpty(genererRapportclassStr)){
        	try {
				return (GenererRapport) Class.forName(genererRapportclassStr).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        return null;
	}
	
}
