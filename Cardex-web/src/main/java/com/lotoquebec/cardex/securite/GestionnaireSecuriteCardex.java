/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.securite;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.SecuritePredicate;


public class GestionnaireSecuriteCardex extends GestionnaireSecurite{
    private static final Logger log = LoggerFactory.getLogger((GestionnaireSecuriteCardex.class.getClass()));
    
    /*
     * La modification est un cas particulier, car il faut :
     * 1.	Valider que l�utilisateur peut consulter l�enregistrement qu�il modifie
     * 2.	Valider la source des donn�es 
     * 3.	Valider les donn�es de la cible de la modification.  
     * 
     * Par exemple, un utilisateur tente de modifier un dossier, mais ne poss�de 
     * pas la confidentialit� n�cessaire.  Il connait l�URL envoie la modification 
     * directement.  L�application valide les donn�es fournies et constate qu�il ne 
     * poss�de pas le r�le de confidentialit� n�cessaire.  L�utilisateur ressaye 
     * de modifier le dossier en passant la bonne cl� et site, mais pas les bons 
     * renseignements dans les donn�es.  Lors de la validation des donn�es envoy�es 
     * par l�utilisateur, la validation constate que les donn�es sources sont en r�gle.  
     * Le syst�me doit retrouver les donn�es du dossier cible pour constater que 
     * l�utilisateur n�a pas le droit de consulter ce dossier.  Car il ne poss�de pas 
     * le r�le de confidentialit� n�cessaire.
     */
    public static void validerSecuriteModificationDossierIntervenantEstAssigne(CardexAuthenticationSubject subject, Object source, Object cible) {
    	validerSecuriteModification(subject, source, cible, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.CONSULTER_DOSSIER, GlobalConstants.ActionSecurite.SELECTION);
    }
    public static void validerSecuriteModificationIntervenantEstLeCreateur(CardexAuthenticationSubject subject, Object source, Object cible) {
    	validerSecuriteModification(subject, source, cible, Arrays.asList((SecuritePredicate)new IntervenantEstLeCreateur()));
    }

    public static void validerEtFiltrerSecuriteConsulterDossierIntervenantEstAssigne(CardexAuthenticationSubject subject, Object vo) {
    	validerEtFiltrerSecurite(subject, vo, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.CONSULTER_DOSSIER, GlobalConstants.ActionSecurite.SELECTION);
    }
    public static void validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(CardexAuthenticationSubject subject, Object vo) {
    	validerEtFiltrerSecurite(subject, vo, Arrays.asList((SecuritePredicate)new IntervenantEstLeCreateur()), GlobalConstants.ActionSecurite.CONSULTER_DOSSIER, GlobalConstants.ActionSecurite.SELECTION);
    }        

	/*
	 * L'action rechercher c'est pour filtrer la recherche.
	 * L'action consulter c'est pour filtrer l'affichage 
	 */
    /*public static List validerEtFiltrerSecuriteRecherche(CardexAuthenticationSubject subject, List list, List<SecuritePredicate> securitePredicates) {
    	return (List) validerEtFiltrerSecurite(subject, list, securitePredicates, GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION);
    }*/
    /*
    public static Collection validerEtFiltrerSecuriteRecherche(CardexAuthenticationSubject subject, Collection collection, List<SecuritePredicate> securitePredicates) {
    	return validerEtFiltrerSecurite(subject, collection, securitePredicates, GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION);
    }*/
    
    public static Collection validerEtFiltrerSecuriteConsulterNarrationIntervenantEstLeCreateur(CardexAuthenticationSubject subject, Collection collection) {
    	return validerEtFiltrerSecurite(subject, collection, Arrays.asList((SecuritePredicate)new IntervenantEstLeCreateur()), GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION, GlobalConstants.ActionSecurite.CONSULTER_NARRATION);
    }
    
    public static Collection validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(CardexAuthenticationSubject subject, Collection collection) {
    	return validerEtFiltrerSecurite(subject, collection, Arrays.asList((SecuritePredicate)new IntervenantEstLeCreateur()), GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION);
    }

    public static Collection validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(CardexAuthenticationSubject subject, Collection collection) {
    	return validerEtFiltrerSecurite(subject, collection, Arrays.asList((SecuritePredicate)new IntervenantEstLeCreateur()), GlobalConstants.ActionSecurite.SELECTION, GlobalConstants.ActionSecurite.CONSULTER_NARRATION);
    }
    
    public static List validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(CardexAuthenticationSubject subject, List liste) {
    	return (List) validerEtFiltrerSecurite(subject, liste, Arrays.asList((SecuritePredicate)new IntervenantEstLeCreateur()), GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION);
    }
    
    
    public static Collection validerEtFiltrerSecuriteRechercheIntervenantEstAssigne(CardexAuthenticationSubject subject, Collection collection) {
    	return validerEtFiltrerSecurite(subject, collection, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION);
    }    

    
    public static Collection validerEtFiltrerSecuriteRechercheSansSecuritePredicate(CardexAuthenticationSubject subject, Collection collection) {
    	return validerEtFiltrerSecurite(subject, collection, null, GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.SELECTION);
    }    
    
    /**
     * L'action Rechercher est importante pour les listes qui ne sont pas le genre et nature ex: confidentialit� 
     */
    
    public static List<Photo> validerEtFiltrerSecuriteRechercheGalerie(CardexAuthenticationSubject subject, List<Photo> photoList) {
    	return (List<Photo>) validerEtFiltrerSecurite(subject, photoList, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_GALERIE, GlobalConstants.ActionSecurite.RECHERCHE);
    }
    
    public static ValueListIterator validerEtFiltrerSecuriteRechercheGalerie(CardexAuthenticationSubject subject, ValueListIterator valueListIterator) {
		return validerEtFiltrerListIteratorSecurite(subject, valueListIterator, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_GALERIE, GlobalConstants.ActionSecurite.RECHERCHE);
    }

    public static Collection validerEtFiltrerSecuriteRechercheDossier(CardexAuthenticationSubject subject, Collection list) {
		return validerEtFiltrerSecurite(subject, list, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER, GlobalConstants.ActionSecurite.RECHERCHE);
    }
    
    public static Collection validerEtFiltrerSecuriteRechercheGalerieDossier(CardexAuthenticationSubject subject, Collection list) {
		return validerEtFiltrerSecurite(subject, list, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_GALERIE, GlobalConstants.ActionSecurite.RECHERCHE);
    }

    public static List validerEtFiltrerSecuriteRechercheDossierConsulterNarration(CardexAuthenticationSubject subject, List list) {
		return (List) validerEtFiltrerSecurite(subject, list, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER, GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.CONSULTER_NARRATION);
    }
    
    public static List validerEtFiltrerSecuriteRechercheDossier(CardexAuthenticationSubject subject, List list) {
		return (List) validerEtFiltrerSecurite(subject, list, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER, GlobalConstants.ActionSecurite.RECHERCHE);
    }
    
    public static ValueListIterator validerEtFiltrerSecuriteRechercheDossier(CardexAuthenticationSubject subject, ValueListIterator valueListIterator) {
		return validerEtFiltrerListIteratorSecurite(subject, valueListIterator, Arrays.asList((SecuritePredicate)new IntervenantEstAssigne()), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER, GlobalConstants.ActionSecurite.RECHERCHE);
    }
    
	
    /*
     * On valide que chaque champ est correctement s�curis�. 
     * Valide si la s�curit� permet d'envoyer ce object � Cardex.
     */
    public static void validerSecuriteEntreeUtilisateurRechercheGalerie(CardexAuthenticationSubject subject, Object vo) {
    	validerSecuriteEntreeUtilisateur(subject, vo, GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.RECHERCHE_GALERIE);
    }

    public static void validerSecuriteEntreeUtilisateurRechercheDossier(CardexAuthenticationSubject subject, Object vo) {
    	validerSecuriteEntreeUtilisateur(subject, vo, GlobalConstants.ActionSecurite.RECHERCHE, GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER);
    }
    
}

