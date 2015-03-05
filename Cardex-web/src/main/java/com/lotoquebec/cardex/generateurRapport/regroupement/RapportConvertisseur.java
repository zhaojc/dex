/*
 * Created on 5-May-2008
 */
package com.lotoquebec.cardex.generateurRapport.regroupement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import com.lotoquebec.cardex.business.ResultatRegroupement;
import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.util.ValueObjectMapper;

/**
 * @author levassc
 * @deprecated Il faut rediriger le traitement de cette classe.  Idéalement, il 
 * faut faire une section sommaire pour le temps non comptabilisé
 */
public class RapportConvertisseur {
	public final static String TEMPS_NON_COMPTABILISE = "temps_non_comptabilise";

	public static void convertirResultat(PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement) {
		regroupementRapportVO.viderResultatPresentationRegroupement();
		Iterator iter = regroupement.getListRegroupement().iterator();
		
		while (iter.hasNext()) {
			ResultatRegroupement resultat = (ResultatRegroupement) iter.next();
			ResultatPresentationRegroupement resultatPre = new ResultatPresentationRegroupement();
			try {
				ValueObjectMapper.convert(resultat, resultatPre);
			} catch (ValueObjectMapperException e) {
				e.printStackTrace();
			}
			regroupementRapportVO.addListeResultatPresentationRegroupement( resultatPre );
		}
		
		if (regroupement.isHeuresProductiveValide())
			regroupementRapportVO.getTotalRechercheRegroupement().setNombreHeuresProductive( regroupement.getNombreHeuresProductive() );
		regroupementRapportVO.setHeuresProductiveValide( regroupement.isHeuresProductiveValide() );
	}
	
	/*
	 * Faire un total des heures par intervenant.
	 * Chaque total est un regroupement de type "temps non comptabilisé".
	 * Faire la somme des heures Cardex et la somme des heures remphor (payé)
	 * pour obtenir le temps non comptabilisé.
	 */
	
	public static void convertirResultatAjoutTempsNonComptabiliseParIntervenant(CardexAuthenticationSubject subject, ResourceBundle bundle, PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement){
		Map mapTempComptabilise = new HashMap();
		regroupementRapportVO.viderResultatPresentationRegroupement();
		Iterator iter = regroupement.getListRegroupement().iterator();
		
		while (iter.hasNext()) {
			ResultatRegroupement resultat = (ResultatRegroupement) iter.next();
			ResultatPresentationRegroupement resultatPre = new ResultatPresentationRegroupement();
			try {
				ValueObjectMapper.convert(resultat, resultatPre);
			} catch (ValueObjectMapperException e) {
				e.printStackTrace();
				continue;
			}
			
			if (regroupement.isHeuresProductiveValide())
				ajoutMapTempComptabilise(subject, bundle, mapTempComptabilise, resultatPre);
			regroupementRapportVO.addListeResultatPresentationRegroupement( resultatPre );
		}
		
		if (regroupement.isHeuresProductiveValide()){
			Map mapMatriculeHeuresProductive = regroupement.getMapMatriculeHeuresProductive();
			Iterator iterTempComptabilise = mapTempComptabilise.values().iterator();
			
			while (iterTempComptabilise.hasNext()) {
				ResultatPresentationRegroupement regroupementTempsNonComptabilise = (ResultatPresentationRegroupement) iterTempComptabilise.next();
				
				if (mapMatriculeHeuresProductive.containsKey( regroupementTempsNonComptabilise.getMatricule() )){
					long nbMinutesTempsComptabilise = regroupementTempsNonComptabilise.getNombreMinutes();
					double nbHeureTempsProductif = ((Double)mapMatriculeHeuresProductive.get( regroupementTempsNonComptabilise.getMatricule() )).doubleValue();
					regroupementTempsNonComptabilise.setNombreMinutes(getMinutesNonComptablilise(nbHeureTempsProductif, nbMinutesTempsComptabilise));
					regroupementRapportVO.addListeResultatPresentationRegroupement( regroupementTempsNonComptabilise );
				}
			}
		}
	}
	
	private static long getMinutesNonComptablilise(double nombreHeuresProductive, double nombreTotalMinutes){
		return (long) ((nombreHeuresProductive*60)-nombreTotalMinutes);
	}

	/*
	 * Si on a déjà un regroupement on additionne le temps
	 * Si c'est nouveau, en créer un regroupeement de temps non comptabilisé
	 * et on l'ajoute au map
	 */
	private static void ajoutMapTempComptabilise(CardexAuthenticationSubject subject, ResourceBundle bundle, Map mapResultatPresentationRegroupement, ResultatPresentationRegroupement resultat) {
		
		if (mapResultatPresentationRegroupement.containsKey( resultat.getMatricule() )){
			ResultatPresentationRegroupement regroupementTempsNonComptabilise = (ResultatPresentationRegroupement) mapResultatPresentationRegroupement.get( resultat.getMatricule() );
			regroupementTempsNonComptabilise.addNombreMinutes( resultat.getNombreMinutes() );
			
		}else{
			ResultatPresentationRegroupement regroupementTempsNonComptabilise = construireRegroupementTempsNonComptabilise(subject, bundle, resultat.getNombreMinutes());
			// Ces informations sont importantes car elles ne peuvent être retrouvées plus tard
			regroupementTempsNonComptabilise.setCleIntervenant( resultat.getCleIntervenant() );
			regroupementTempsNonComptabilise.setPrenomIntervenant( resultat.getPrenomIntervenant() );
			regroupementTempsNonComptabilise.setNomIntervenant( resultat.getNomIntervenant() );
			regroupementTempsNonComptabilise.setMatricule( resultat.getMatricule() );
			mapResultatPresentationRegroupement.put(resultat.getMatricule(), regroupementTempsNonComptabilise);
		}
	}
	
	private static ResultatPresentationRegroupement construireRegroupementTempsNonComptabilise(CardexAuthenticationSubject subject, ResourceBundle bundle, long nbMinutesNonComptablilise) {
		ResultatPresentationRegroupement resultatRegroupement = new ResultatPresentationRegroupement();
		String strTempsNonComptabilise = bundle.getString(TEMPS_NON_COMPTABILISE);
		assignerPremiereColonne(resultatRegroupement, strTempsNonComptabilise);
		resultatRegroupement.setNombreMinutes(nbMinutesNonComptablilise);
		return resultatRegroupement;
	}

	private static void assignerPremiereColonne(ResultatPresentationRegroupement resultatRegroupement, String strTempsNonComptabilise) {
	    resultatRegroupement.setNomRegroupement(strTempsNonComptabilise);
	}
	
	public static void ajouterTempsNonComptabilise(CardexAuthenticationSubject subject, ResourceBundle bundle, PresentationRegroupementRapport regroupementRapportVO) {
		
		// Si le temps est égal à 0, on n'affiche pas de "regroupement" pour ça.
		if (regroupementRapportVO.isHeuresProductiveValide()){
			ResultatPresentationRegroupement resultatRegroupement = construireRegroupementTempsNonComptabilise(subject, bundle, regroupementRapportVO.getTotalRechercheRegroupement().getMinutesNonComptablilise());
			regroupementRapportVO.addListeResultatPresentationRegroupement( resultatRegroupement );
		}
	}
	
}
