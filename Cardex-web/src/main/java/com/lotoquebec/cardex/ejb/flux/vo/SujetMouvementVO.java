package com.lotoquebec.cardex.ejb.flux.vo;

import java.util.List;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.vo.MouvementVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lotoquebec.cardexCommun.util.MessageDuCourriel;

public class SujetMouvementVO implements MessageDuCourriel{
	
	private List<Sujet> listeSujetVO = null;
	private Sujet sujetConcerne = null;
	private MouvementVO mouvementVO = null;
	private boolean trouveMatricule = false;
	private boolean trouveNomPrenomDateNaissance = false;
	
	
	public List<Sujet> getListeSujetVO() {
		return listeSujetVO;
	}

	public void setListeSujetVO(List<Sujet> listeSujetVO) {
		this.listeSujetVO = listeSujetVO;
	}

	public MouvementVO getMouvementVO() {
		return mouvementVO;
	}

	public void setMouvementVO(MouvementVO mouvementVO) {
		this.mouvementVO = mouvementVO;
	}

	public boolean isTrouveMatricule() {
		return trouveMatricule;
	}

	public void setTrouveMatricule(boolean trouveMatricule) {
		this.trouveMatricule = trouveMatricule;
	}

	public boolean isTrouveNomPrenomDateNaissance() {
		return trouveNomPrenomDateNaissance;
	}

	public void setTrouveNomPrenomDateNaissance(boolean trouveNomPrenomDateNaissance) {
		this.trouveNomPrenomDateNaissance = trouveNomPrenomDateNaissance;
	}
	
	public boolean isRienTrouve(){
		return trouveMatricule == false && trouveNomPrenomDateNaissance == false;
	}

	public Sujet getSujetConcerne() {
		
		if (sujetConcerne == null && listeSujetVO.size() == 1)
			sujetConcerne = listeSujetVO.get(0); 
		
		return sujetConcerne;
	}
	
	public void setSujetConcerne(Sujet sujetConcerne) {
		this.sujetConcerne = sujetConcerne;
	}

	public String construireObjectMessage(CardexAuthenticationSubject subject) throws BusinessResourceException{
		return CourrielAction.constuireObjectMessage(subject, mouvementVO.getTypeCodeIntervention(), getMouvementVO().getNom(), getMouvementVO().getPrenom());
	}
	
	public String construireMessage(String type) {
		String texte = "";
		String typeEmploi = mouvementVO.getStatut();
		
		if (typeEmploi.equals(GlobalConstants.Emplois.TempsComplet)) {
			typeEmploi = GlobalConstants.Emplois.TC;
		}
		if (typeEmploi.equals(GlobalConstants.Emplois.TempsCompletTemporaire)) {
			typeEmploi = GlobalConstants.Emplois.TCT;
		}
		if (typeEmploi.equals(GlobalConstants.Emplois.TempsPartiel)) {
			typeEmploi = GlobalConstants.Emplois.TP;
		}
		if (typeEmploi.equals(GlobalConstants.Emplois.TempsPartielTemporaire)) {
			typeEmploi = GlobalConstants.Emplois.TPT;
		}
		texte = texte + "<br>  " + mouvementVO.getNom() + ", "
				+ mouvementVO.getPrenom() + " (" + typeEmploi + ")";
		texte = texte + " - Num�ro : " + mouvementVO.getNumeroClientEmploye();
		texte = texte + "<br>  " + mouvementVO.getSiteConcerne() + ", "
				+ mouvementVO.getDescriptionEmploi();
		texte = texte + "<br> Secteur : "
				+ mouvementVO.getDescriptionSecteur() + " - "
				+ mouvementVO.getAdministrateur();
		if (mouvementVO.getDateEmbauche() != null) {
			texte = texte
					+ "<br> Date de d�but d'emploi : "
					+ mouvementVO.getDateEmbauche().toString().substring(0,
							10);
		}
		if (type.equals(GlobalConstants.TypesIntervention.SujetDouble)
		|| type.equals(GlobalConstants.TypesIntervention.Embauche)
		|| type.equals(GlobalConstants.TypesIntervention.EmbaucheSansEnquete)
		|| type.equals(GlobalConstants.TypesIntervention.SujetDoubleDepart)
		|| type.equals(GlobalConstants.TypesIntervention.NASDoubleDepart)) {
			texte = texte
					+ " - Date de naissance : "
					+ mouvementVO.getDateNaissance().toString().substring(0,
							10);
		}
		if (type.equals(GlobalConstants.TypesIntervention.Depart) // ||
		// type.equals(ConstantesGlobales.TypesIntervention.
		// DossierActifDepart)||
		//type.equals(ConstantesGlobales.TypesIntervention.NoDoubleDepart
		// )
		) {
			texte = texte + "<br> Date de d�part : "
					+ mouvementVO.getDateDepart().toString().substring(0, 10);
			texte = texte + "<br> Raison du d�part (s'il y a lieu) : "
					+ mouvementVO.getDescriptionDepart();
		}
		if (type.equals(GlobalConstants.TypesIntervention.Embauche)) {
			// Pour �tre embauch�, unemploy� doit �tre de s�v�rit� 3
			// (r�sultat d'enqu�te n�gatif).
			Sujet sujet = getSujetConcerne(); 
			if (sujet != null && sujet.getSeverite() != GlobalConstants.Severite.SEVERITE_3) {
				texte = texte
						+ "<br> Attention, il semble y avoir un probl�me avec la s�v�rit�.  Pri�re de v�rifier. ";
			}
		}
		/*
		 * if(type.equals(ConstantesGlobales.TypesIntervention.NASDouble)
		 * ){ //texte = texte + "Num�ro d'assurance sociale : " +
		 * donnees.getNumeroAssuranceSociale(); }
		 */if (type.equals(GlobalConstants.TypesIntervention.Conge)) {
			texte = texte + "<br> Date de d�but de cong� : "
					+ mouvementVO.getDateConge().toString().substring(0, 10);
			if (mouvementVO.getDateFinConge() != null) {
				texte = texte
						+ "<br> Date pr�vue de retour : "
						+ mouvementVO.getDateFinConge().toString().substring(
								0, 10);
			}
			texte = texte + "<br> Raison du cong� : "
					+ mouvementVO.getDescriptionConge();
		}
		return texte;
	}
}
