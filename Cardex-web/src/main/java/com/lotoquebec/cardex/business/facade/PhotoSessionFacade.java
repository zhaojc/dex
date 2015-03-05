/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.comparators.ComparatorChain;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.fabrique.dossier.RechercheSujetOptionAgeValidationFabrique;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.securite.SecuriteAdHoc;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe.TriGalerieCleRessourceHardListe;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Le PhotoBusinessFacade offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/04/04 20:41:02 $
 */
public class PhotoSessionFacade {

    static class DateCreationComparator implements Comparator, Serializable {
        public int compare(Object o1,Object o2) {
          if (o1 instanceof PhotoVO && o2 instanceof PhotoVO ) {
            PhotoVO l1 = (PhotoVO)o1;
            PhotoVO l2 = (PhotoVO)o2;
            if (l1.getDateCreation() == null) {
                return 1;
            }else if (l2.getDateCreation() == null) {
                    return -1;
                  }else{
                        //return String.CASE_INSENSITIVE_ORDER.compare(l1.getDateCreation(),l2.getDateCreation());
                        if(l1.getDateCreation().before(l2.getDateCreation())){
                          return 1;
                        }else if(l2.getDateCreation().before(l1.getDateCreation())){
                                return -1;
                              }else{
                                return 0;
                              }
                        }
            }else {
               return -1;
            }
          }
      }
  static class DateDebutDossierComparator implements Comparator, Serializable {
  	public int compare(Object o1,Object o2) {
  		if (o1 instanceof PhotoVO && o2 instanceof PhotoVO ) {
  	        PhotoVO l1 = (PhotoVO)o1;
  	        PhotoVO l2 = (PhotoVO)o2;
  	        Dossier do1 = l1.getDossier();
  	        Dossier do2 = l2.getDossier();
  	        
  	        if (do1.getDateDebut() == null) {
  	            return 1;
  	        }else if (do2.getDateDebut() == null) {
  	        	return -1;
  	        }else{
  	        	if(do1.getDateDebut().before(do2.getDateDebut())){
  	        		return 1;
  	            }else if(do2.getDateDebut().before(do1.getDateDebut())){
  	            	return -1;
  	            }else{
  	            	return 0;
  	            }
  	        }
  	    }else {
  	       return -1;
  	    }
  	} 
  }
      
  static class DateFinDossierComparator implements Comparator, Serializable {
  	public int compare(Object o1,Object o2) {
  		if (o1 instanceof PhotoVO && o2 instanceof PhotoVO ) {
  	        PhotoVO l1 = (PhotoVO)o1;
  	        PhotoVO l2 = (PhotoVO)o2;
  	        Dossier do1 = l1.getDossier();
  	        Dossier do2 = l2.getDossier();
  	        
  	        if (do1.getDateFin() == null) {
  	            return 1;
  	        }else if (do2.getDateFin() == null) {
  	        	return -1;
  	        }else{
  	        	if(do1.getDateFin().before(do2.getDateFin())){
  	        		return 1;
  	            }else if(do2.getDateFin().before(do1.getDateFin())){
  	            	return -1;
  	            }else{
  	            	return 0;
  	            }
  	        }
  	    }else {
  	       return -1;
  	    }
  	} 
  }
	
    /**
     * Recherche de photos
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La photo recherché
     * @throws BusinessRuleException
     * @throws BusinessException
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     */
    public List<Photo> select(CardexAuthenticationSubject subject,
                        CriteresRecherchePhoto criteria) throws BusinessRuleException, BusinessException {
        try {
        	
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheGalerie(subject, criteria);
            (new RechercheSujetOptionAgeValidationFabrique(criteria)).executer(criteria);
            List<Photo> photoList = lancerRechercheGalerie(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheGalerie(subject, photoList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de photos à l'aide de critères de recherche dans la Galerie.
     * Il y a trois possibilités de recherche : dossier, sujet et repérage.
     * À cela, s'ajoutent également les recherches directes par numéros de
     * dossier ou de sujet.
     * Les recherches de dossiers et de sujets peuvent être cumulées si l'utilisateur
     * le désire (cases à cocher dans l'interface).  Les autres recherches sont
     * exclusives.
     * Le test est effectué ici et les méthodes correspondantes de recherche
     * sont ensuite appelées. Les données retournées sont traitées avant l'affichage.
     * Date de création : (2002-02-25)
     * @author François Guérin
     * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
     * @param criteria CriteresRecherchePhoto : critères de recherche
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "stored procedure".
     * @return ValueListIterator : liste des photos retournées par la recherche.
     * @throws BusinessException 
     */
    private List<Photo> lancerRechercheGalerie(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria) throws DAOException, BusinessException{
        List<Photo> results = new ArrayList<Photo>();
   
        retraitDuSiteApplicablePourAccesInterditEtAvisDeGuet(criteria);
   	  
        //Recherche directe par numéro de dossier.
        if (StringUtils.isNotEmpty(criteria.getNumeroDossier())){
            results.addAll(FabriqueCardexDAO.getInstance().getPhotoDAO().selectDossierDirect(subject, criteria));
        }else{
        	//Recherche directe par numéro de sujet.
        	if (StringUtils.isNotEmpty(criteria.getNumeroSujet())){
        		results.addAll(FabriqueCardexDAO.getInstance().getPhotoDAO().selectSujetDirect(subject, criteria));
        	}else{
    			//Recherche des photos associées aux sujets
    			if (criteria.isSujetAttache()){

	        		//Recherche des repérages
	        		if (Long.valueOf(GlobalConstants.Categorie.REPERAGE_AUTOEXCLUSION).longValue() == criteria.getCategorie()
	        		 || Long.valueOf(GlobalConstants.Categorie.REPERAGE_ACCES_INTERDIT) == criteria.getCategorie()
	        		 || Long.valueOf(GlobalConstants.Categorie.REPERAGE_AVIS_GUET) == criteria.getCategorie()){
	        			checkNombreEnregistrementRechercheGalerieReperage(subject, criteria);
	        			results.addAll(FabriqueCardexDAO.getInstance().getPhotoDAO().selectReperage(subject, criteria));
	        		}else{
        				checkNombreEnregistrementRechercheGalerieSujet(subject, criteria);
        				results.addAll(FabriqueCardexDAO.getInstance().getPhotoDAO().selectSujet(subject, criteria));
        			}
        		}

    			//Recherche des photos associées aux dossiers
    			if (criteria.isDossierAttache()){
    				checkNombreEnregistrementRechercheGalerieDossier(subject, criteria);
    				results.addAll(FabriqueCardexDAO.getInstance().getPhotoDAO().selectDossier(subject, criteria));
    			}
    			
        	}//else numéro sujet
        }//else numéro dossier
        ComparatorChain chainActif = new ComparatorChain(); 
     	Comparator comparator = null;
     	boolean sensAscendant = false;
     
     	if (TriGalerieCleRessourceHardListe.TRI_DATE_CREATION_PHOTO.equals( criteria.getOrdreTri() )){
     		comparator = new DateCreationComparator();
     		
     	}else if (TriGalerieCleRessourceHardListe.TRI_DATE_DEBUT_DOSSIER.equals( criteria.getOrdreTri() )){
     		comparator = new DateDebutDossierComparator();
     		
     	}else if (TriGalerieCleRessourceHardListe.TRI_DATE_FIN_DOSSIER.equals( criteria.getOrdreTri() )){
     		comparator = new DateFinDossierComparator();
     	}
     	
     	if (GlobalConstants.SensTri.ASC.equals( criteria.getSensTri() )){
     		sensAscendant = true;
     	}else if (GlobalConstants.SensTri.ASC.equals( criteria.getSensTri() )){
     		sensAscendant = false;
     	}
     	
     	chainActif.addComparator(comparator, sensAscendant);
     	Collections.sort(results, chainActif);			 
     	
        return results;

   }//select
    
    private void checkNombreEnregistrementRechercheGalerieDossier(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getPhotoDAO().nombreDeGalerieDossierRecherche(subject, criteria);

    		if(nbEnregistrement == null) nbEnregistrement = 0;
    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheGalerieDossier");
    	}
    }
    
    private void checkNombreEnregistrementRechercheGalerieReperage(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getPhotoDAO().nombreDeGalerieReperageRecherche(subject, criteria);

    		if(nbEnregistrement == null) nbEnregistrement = 0;
    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheGalerieDossier");
    	}
    }
    
    private void checkNombreEnregistrementRechercheGalerieSujet(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getPhotoDAO().nombreDeGalerieSujetRecherche(subject, criteria);

    		if(nbEnregistrement == null) nbEnregistrement = 0;
    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheGalerieDossier");
    	}
    }    
    
    /**
     * Pour les accès interdit il ne faut pas tenir compte du site applicable.
     * Un accès interdit et un avis de guet c'est pour tous les sites applicables.
     * @param criteria
     */    
    private void retraitDuSiteApplicablePourAccesInterditEtAvisDeGuet(CriteresRecherchePhoto criteria) {
    	
		if (GlobalConstants.Nature.ACCES_INTERDIT == criteria.getNature()
		|| GlobalConstants.Nature.AVIS_DE_GUET == criteria.getNature()){
			criteria.setSiteApplicable(0);
		}
	}    
    
    /**
     * Obtenir le fichier demandé par la clé et le site
     * La sécurité valide si l'utilisateur peut 
	 * 1-rechercher dans cette entité
	 * 2-à la confidentilité requise
	 * 3-peut consulter cette extention (typeMultimedia)
     * @param cle
     * @param site
     * @return
     */
    public FichierMultimediaVO obtenirFichier(CardexAuthenticationSubject subject, long cle, long site, boolean grandeImage) throws BusinessRuleException, BusinessException {
        try {
        	FichierMultimediaVO fichierMultimediaVO = FabriqueCardexDAO.getInstance().getPhotoDAO().obtenirFichier(subject, cle, site, grandeImage);
            SecuriteAdHoc.validerSecuriteConsulterPhoto(subject, fichierMultimediaVO);
            return fichierMultimediaVO;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Mise à jour d'un lien multimédia d'un dossier
     *
     * @param subject Le sujet qui modifie le dossier
     * @param photo Le lien multimédia à mettre à jour
     * @throws BusinessException
     */
    public void updateLienMultimedia(CardexAuthenticationSubject subject,
                Photo photo) throws BusinessRuleException, BusinessException {
        try {
            FabriqueCardexDAO.getInstance().getDossierDAO().updateLienMultimedia(subject, photo);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
}

