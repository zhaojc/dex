package com.lotoquebec.cardex.presentation.model.form.rapport;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.DateUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;


public class CriteresRapportForm extends ValidatorForm implements Serializable, RapportForm {

	private String dateDebutDu = null;
	private String dateDebutAu = null;
    private String dateHeureDebutDu = null;
    private String dateHeureDebutAu = null;
	private GenererRapport genererRapport = null;
	private boolean lancerRapport = false;
	private String typeSortieServlet = "";
	
	public void init(CardexAuthenticationSubject subject){
		dateDebutDu = null;
		dateDebutAu = null;
        dateHeureDebutDu = null;
        dateHeureDebutAu = null;
		initDateDebut();
	}
	
	public void initDateDebut(){
		initDateDebutDu();
		initDateDebutAu();
        if (StringUtils.isEmpty(dateHeureDebutDu)){
            Date date = DateUtils.ajoutJour(new Date(), -7);
            dateHeureDebutDu = DateFormat.format(date, DateFormat.DATE_FORMAT_AVEC_HEURE);          
        }
	}
	
	private void initDateDebutDu(){
		
		if (StringUtils.isEmpty(dateDebutDu)){
			Date date = DateUtils.ajoutJour(new Date(), -7);
			dateDebutDu = DateFormat.format(date);			
		}
        if (StringUtils.isEmpty(dateHeureDebutAu)){
            dateHeureDebutAu = DateFormat.format(new Date(), DateFormat.DATE_FORMAT_AVEC_HEURE);
        }
	}
	
	private void initDateDebutAu(){
		
		if (StringUtils.isEmpty(dateDebutAu)){
			dateDebutAu = DateFormat.format(new Date());
		}
	}
	
	public String getDateDebutDu() {
		return dateDebutDu;
	}

	public void setDateDebutDu(String dateDebutDu) {
		this.dateDebutDu = dateDebutDu;
	}

	public String getDateDebutAu() {
		return dateDebutAu;
	}

	public void setDateDebutAu(String dateDebutAu) {
		this.dateDebutAu = dateDebutAu;
	}

	public boolean isLancerRapport() {
		return lancerRapport;
	}

	public void setLancerRapport(boolean lancerRapport) {
		this.lancerRapport = lancerRapport;
	}

	public GenererRapport getGenererRapport(){
		throw new RuntimeException("genererRapport doit être défini");
	}

	public String getTypeSortieServlet() {
		return typeSortieServlet;
	}

	public void setTypeSortieServlet(String typeSortieServlet) {
		this.typeSortieServlet = typeSortieServlet;
	}
	
    /**
     * @return dateHeureDebutDu
     */
    public String getDateHeureDebutDu() {
        return dateHeureDebutDu;
    }

    /**
     * @param dateHeureDebutDu dateHeureDebutDu à définir
     */
    public void setDateHeureDebutDu(String dateHeureDebutDu) {
        this.dateHeureDebutDu = dateHeureDebutDu;
    }

    /**
     * @return dateHeureDebutAu
     */
    public String getDateHeureDebutAu() {
        return dateHeureDebutAu;
    }

    /**
     * @param dateHeureDebutAu dateHeureDebutAu à définir
     */
    public void setDateHeureDebutAu(String dateHeureDebutAu) {
        this.dateHeureDebutAu = dateHeureDebutAu;
    }
	
}