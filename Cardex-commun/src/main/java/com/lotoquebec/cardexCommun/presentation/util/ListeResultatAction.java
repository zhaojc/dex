/*
 * Created on 22-Apr-2008
 */
package com.lotoquebec.cardexCommun.presentation.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class ListeResultatAction extends AbstractAction{

	public ActionForward changerPage(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String nomTable = (String) request.getParameter("nomTable");
		
		if (StringUtils.isNotEmpty(nomTable)){
			ListeResultat listeResultat = obtenirListeResultat(form, nomTable);
			listeResultat.chargerAffichage();
		}
		
		return mapping.findForward("success"); 
	}	

	public ActionForward trier(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String nomTable = (String) request.getParameter("nomTable");
		String trierPart = (String) request.getParameter("trierPart");
		String trierAscendant = (String) request.getParameter("trierAscendant");
		
		if (StringUtils.isNotEmpty(nomTable)
		&& StringUtils.isNotEmpty(trierPart)
		&& StringUtils.isNotEmpty(trierAscendant)){
			ListeResultat listeResultat = obtenirListeResultat(form, nomTable);
			
			listeResultat.trier(trierPart, Boolean.valueOf( trierAscendant ).booleanValue());
		}
		return mapping.findForward("success");
	}

	/**
	 * Il est possible d'avoir plusieurs liste de résultat dans une page.
	 * Ce qui fait que le nomTable est requis.
	 * @param form
	 * @param nomTable
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static ListeResultat obtenirListeResultat(ActionForm form, String nomTable) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		if (StringUtils.isEmpty(nomTable))
			return null;
		Class[] classe = null;
		Object[] object = null;
		Method method = form.getClass().getMethod("get"+StringUtils.capitalise(nomTable), classe);
		ListeResultat listeResultat = (ListeResultat) method.invoke(form, object);
		return listeResultat;
	}	
}
