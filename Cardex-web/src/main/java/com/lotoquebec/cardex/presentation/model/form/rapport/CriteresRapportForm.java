package com.lotoquebec.cardex.presentation.model.form.rapport;

import java.io.Serializable;
import java.util.Date;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.DateUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ValueObjectMapper;


public class CriteresRapportForm extends RapportForm implements Serializable {

	private static final long serialVersionUID = 1448949466583743702L;
	private String dateDebutDu = null;
	private String dateDebutAu = null;
    private String dateHeureDebutDu = null;
    private String dateHeureDebutAu = null;
	private String typeSortieServlet = "";

	public CriteresRapportForm() {
		super(null);
	}
	
	public CriteresRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}
	
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
     * @param dateHeureDebutDu dateHeureDebutDu � d�finir
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
     * @param dateHeureDebutAu dateHeureDebutAu � d�finir
     */
    public void setDateHeureDebutAu(String dateHeureDebutAu) {
        this.dateHeureDebutAu = dateHeureDebutAu;
    }
	
	@Override
	public RapportVO obtenirRapportVO() {
        CritereRapportVO rapportVO = genererRapport.construireNouveauRapportVO();
        try {
			ValueObjectMapper.convert(this, rapportVO);
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        return rapportVO;
	}
	
}