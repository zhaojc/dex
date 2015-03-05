package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import java.util.Collection;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * RV0002	Pour un dossier de vente aux mineurs � l'exception de la cat�gorie 
 * �chantillon.  Le champs r�f�rence 1 (qui contient le num�ro de vague) doit 
 * correspondre au num�ro du dossier du dossier d'�chantillon.
 * 
 * @author levassc
 *
 */
public class NumeroVagueClientMystereValidateurRV0002 implements Validateur<Dossier> {

	private CardexAuthenticationSubject subject;
	private Dossier venteMineurDossier;
	
	public NumeroVagueClientMystereValidateurRV0002(CardexAuthenticationSubject subject, Dossier venteMineurDossier) {
		super();
		this.subject = subject;
		this.venteMineurDossier = venteMineurDossier;
	}

	public boolean isValide(Dossier t) throws BusinessException, DAOException {
		
        if(venteMineurDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && venteMineurDossier.getType() == GlobalConstants.Type.VENTE_MINEUR
        && venteMineurDossier.getCategorie() != GlobalConstants.CategorieClientMystere.ECHANTILLON){
     	   Collection<Societe> societes = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSociete(subject, venteMineurDossier);
     	   
     	   for(Societe societe:societes){
     		   
     		   /*
     		    * Il ne peut y avoir qu'une seule soci�t� RDD par dossier vente aux mineurs
     		    * 
     		    * RV0001	Pour un dossier de vente aux mineurs � l'exception de la 
     		    * cat�gorie �chantillon.  Une seule soci�t� RDD peut li�e au dossier.
     		    */
     		   if (societe.isIndicateurRdd()){
	 	    	   Dossier dossierEchantillion = dernierDossierEchantillionClientMystere(subject, societe);
	 				   
	 	    	   if (dossierEchantillion != null){
	 		    	   //On v�rifie que les num�ros de vague correspondent.
	 		    	   //Si le dernier dossier est le dossier ma�tre, on compare avec le num�ro de dossier, sinon avec le champ R�f�rence 1
	 		    	   if(StringUtils.equals(dossierEchantillion.getNumeroDossier(), venteMineurDossier.getReference1()) == false){
	 		    		  throw new BusinessException( getMessage() );
	 		    	   }
	 	    	   }
	 	    	  break;
     		   }
     	   }
        }
        return true;
	}

    private Dossier dernierDossierEchantillionClientMystere(CardexAuthenticationSubject subject, Societe societe)throws BusinessException  {
        Dossier dossierTrouve = new DossierVO();
        try {
           dossierTrouve = FabriqueCardexDAO.getInstance().getDossierDAO().dernierDossierEchantillionClientMystere(subject, societe);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
        return dossierTrouve;
    }
    
	public BusinessMessage getMessage() {
		return new BusinessMessage("ME0002"); //Le champ "r�f�rence 1" doit correspondre au num�ro de vague du dossier d'�chantillon.
	}

}
