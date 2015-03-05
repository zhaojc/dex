package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Permet de transiter les informations relatives à la recherche
 * d'une adresse à la page de présentation
 */
public class AdresseRechercheForm extends AdresseForm implements Serializable {

	private long numeroMunicipalMin = 0;
	private long numeroMunicipalMax = 0;

	private String prefixNumeroUnite = "";
	private String sufixNumeroUnite = "";
	
	private long numeroUniteMin = 0;
	private long numeroUniteMax = 0;
	
	public long getNumeroMunicipalMin() {
		return numeroMunicipalMin;
	}
	public void setNumeroMunicipalMin(long numeroMunicipalMin) {
		this.numeroMunicipalMin = numeroMunicipalMin;
	}
	public long getNumeroMunicipalMax() {
		return numeroMunicipalMax;
	}
	public void setNumeroMunicipalMax(long numeroMunicipalMax) {
		this.numeroMunicipalMax = numeroMunicipalMax;
	}
	public boolean isNumeroMunicipalVariable(){
		return (numeroMunicipalMin != 0 || numeroMunicipalMax != 0) && numeroMunicipalMin != numeroMunicipalMax;
	}
	public boolean isNumeroMunicipalDansRange(long numMunicipal){
		return numeroMunicipalMin <= numMunicipal && numMunicipal <= numeroMunicipalMax;
	}
	public String getPrefixNumeroUnite() {
		return prefixNumeroUnite;
	}
	public void setPrefixNumeroUnite(String prefixNumeroUnite) {
		this.prefixNumeroUnite = prefixNumeroUnite;
	}
	public String getSufixNumeroUnite() {
		return sufixNumeroUnite;
	}
	public void setSufixNumeroUnite(String sufixNumeroUnite) {
		this.sufixNumeroUnite = sufixNumeroUnite;
	}
	public long getNumeroUniteMin() {
		return numeroUniteMin;
	}
	public void setNumeroUniteMin(long numeroUniteMin) {
		this.numeroUniteMin = numeroUniteMin;
	}
	public long getNumeroUniteMax() {
		return numeroUniteMax;
	}
	public void setNumeroUniteMax(long numeroUniteMax) {
		this.numeroUniteMax = numeroUniteMax;
	}
	public boolean isNumeroUniteVariable(){
		return (numeroUniteMin != 0 || numeroUniteMax != 0) && numeroUniteMin != numeroUniteMax;
	}
	public boolean isNumeroUniteDansRange(long numUnite){
		return numeroUniteMin <= numUnite && numUnite <= numeroUniteMax;
	}
	public boolean isAdresseTrouve(AdresseForm adresseForm) {
		try{
			if (isNumeroMunicipalVariable()){
				long numMunicipal = Long.valueOf(adresseForm.getNumeroMunicipal()).longValue();
				
				if (isNumeroMunicipalDansRange(numMunicipal))
					return true;
			}
			if (isNumeroUniteVariable()){
				long numUnite = Long.valueOf(adresseForm.getUnite()).longValue();
				
				if (isNumeroUniteDansRange(numUnite))
					return true;
			}
			
			if (isNumeroMunicipalVariable() == false && isNumeroUniteVariable() == false)
				return true;
			
		}catch(Exception e){
			return false;
		}
		
		return false;
	}
	public void assignerInformationSaisie(AdresseForm adresseForm) {
		try{
			if (isNumeroMunicipalVariable()){
				long numMunicipal = Long.valueOf(adresseForm.getNumeroMunicipal()).longValue();
				
				if (isNumeroMunicipalDansRange(numMunicipal))
					setNumeroMunicipal(String.valueOf(numMunicipal));
			}
			if (isNumeroUniteVariable()){
				long numUnite = Long.valueOf(adresseForm.getUnite()).longValue();
				
				if (isNumeroUniteDansRange(numUnite))
					setUnite(String.valueOf(numUnite));
			}
		}catch(Exception e){}
	}
	
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        //ServletContext application = getServlet().getServletContext();
        ActionErrors errors = new ActionErrors();

        /*String validationKey = getValidationKey(mapping, request);

        Validator validator =
            Resources.initValidator(validationKey, this, application, request,
                errors, page);*/
        
        //try {
        	testRequis(errors);
        	
            //validatorResults = validator.validate();
        /*} catch (ValidatorException e) {
            e.printStackTrace();
        }*/

        return errors;
    }

	private void testRequis(ActionErrors errors) {
		
		if (isNumeroMunicipalVariable()
		&& (StringUtils.isEmpty(getNumeroMunicipal())
		|| StringUtils.isNumeric(getNumeroMunicipal()) == false
		|| isNumeroMunicipalDansRange(Long.valueOf(getNumeroMunicipal()).longValue()) == false)){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cardex_choisir_un_numero_municipal", String.valueOf(getNumeroMunicipalMin()), String.valueOf(getNumeroMunicipalMax())));
		}
		
		if (isNumeroUniteVariable()
		&& (StringUtils.isEmpty(getNumeroUnite())
		|| StringUtils.isNumeric(getNumeroUnite()) == false
		|| isNumeroMunicipalDansRange(Long.valueOf(getNumeroUnite()).longValue()) == false)){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cardex_choisir_un_numero_unite", String.valueOf(getNumeroUniteMin()), String.valueOf(getNumeroUniteMax())));
		}
		
	}

}