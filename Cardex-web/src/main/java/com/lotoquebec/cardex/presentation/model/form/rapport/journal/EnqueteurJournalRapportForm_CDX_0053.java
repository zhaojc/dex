package com.lotoquebec.cardex.presentation.model.form.rapport.journal;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.journal.EnqueteurJournalGenerateurRapport_CDX_0053;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

public class EnqueteurJournalRapportForm_CDX_0053 extends CriteresRapportForm{

	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(String.valueOf(GlobalConstants.Entite.LOTO_QUEBEC));
        setGenre(GlobalConstants.Genre.DOSSIERS_POL);
        setNature(String.valueOf(GlobalConstants.Nature.JOURNAL_ENQUETES));
	}
	
	public String getEntite() {
		return hierarchieEGNTC.getEntite();
	}

	public void setEntite(String entite) {
		this.hierarchieEGNTC.setEntite(entite);
	}

    public String getGenre() {
    	return hierarchieEGNTC.getGenre();
    }

    public void setGenre(String genre) {
    	hierarchieEGNTC.setGenre(genre);
    }
        
	public String getNature() {
		return hierarchieEGNTC.getNature();
	}

	public void setNature(String nature) {
		hierarchieEGNTC.setNature(nature);
	}

	@Override
	public GenererRapport getGenererRapport(){
		return new EnqueteurJournalGenerateurRapport_CDX_0053();
	}
}
