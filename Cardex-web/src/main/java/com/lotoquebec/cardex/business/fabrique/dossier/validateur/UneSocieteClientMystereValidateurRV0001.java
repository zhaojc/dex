package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import java.util.Collection;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * RV0001:Pour un dossier de vente aux mineurs, à l'exception de la catégorie Échantillon
 * Une seule société RDD peut liée au dossier.
 * 
 * @author levassc
 *
 */
public class UneSocieteClientMystereValidateurRV0001 implements Validateur<Dossier> {

	private CardexAuthenticationSubject subject;
	private Societe societe;
	private Dossier dossierOrigine;
	
	public UneSocieteClientMystereValidateurRV0001(CardexAuthenticationSubject subject, Dossier dossierOrigine, Societe societe) {
		super();
		this.subject = subject;
		this.societe = societe;
		this.dossierOrigine = dossierOrigine;
	}

	public boolean isValide(Dossier t) throws BusinessException, DAOException {
		
        if(dossierOrigine.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && dossierOrigine.getType() == GlobalConstants.Type.VENTE_MINEUR
        && dossierOrigine.getCategorie() != GlobalConstants.CategorieClientMystere.ECHANTILLON){
            Collection<Societe> societes = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSociete(subject, dossierOrigine);
    		
            boolean isContientSocieteRDD = false;
    		societes.add(societe);
    		
    		for (Societe societe:societes) {
    			
    			if (societe.isIndicateurRdd()){
    				
    				if (isContientSocieteRDD)
    					throw new BusinessException( getMessage() );
    				isContientSocieteRDD = true;
    			}
    		}
        }
        return true;
	}

	public BusinessMessage getMessage() {
		return new BusinessMessage("ME0001"); //Une seule société RDD est permise par dossier de type "Vente aux mineurs"
	}

}
