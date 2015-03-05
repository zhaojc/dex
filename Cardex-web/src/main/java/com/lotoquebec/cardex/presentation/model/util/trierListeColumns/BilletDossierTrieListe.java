/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class BilletDossierTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_DOSSIER = "v_do_ancienne_reference";
	public final static String NUMERO_DOSSIER = "dossierForm.numeroDossier";

	public final static String CLE_NUMERO_CARDEX = "v_do_numero_dossier";
	public final static String NUMERO_CARDEX = "dossierForm.numeroCardex.affichage";

	public final static String CLE_NOM_BILLET = "nom.billet";
	public final static String NOM_BILLET = "nom";

	public final static String CLE_NUMERO_CONTROLE = "numero.control";
	public final static String NUMERO_CONTROLE = "numeroControl";

	public final static String CLE_DATE_CREATION = "d_cn_date_creation";
	public final static String DATE_CREATION = "dateCreation";

	public final static String CLE_MONTANT_LOT = "montant.lot";
	public final static String MONTANT_LOT = "montantLot";
	
	public final static String CLE_CONFIDENTIALITE = "i_cc_commentaire_t";
	public final static String CONFIDENTIALITE = "dossierForm.confidentialiteDescription";
	
	public final static String CLE_TYPE = "i_type_t";
	public final static String TYPE = "dossierForm.typeDescription";

	public final static String CLE_CATEGORIE = "tabpage_categorie";
	public final static String CATEGORIE = "dossierForm.categorieDescription";
	
	public final static String CLE_PERIODE = "tabpage_periode";
	public final static String PERIODE = "dossierForm.periodeDescription";
	
	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "dossierForm.statutDescription";

	public final static String CLE_DATE_DEBUT = "d_is_date_debut_t";
	public final static String DATE_DEBUT = "dossierForm.dateDebut";
	
	public final static String CLE_DATE_FIN = "d_is_date_fin_t";
	public final static String DATE_FIN = "dossierForm.dateFin";
	
	public final static String CLE_DATE_PAIEMENT = "date.paiement";
	public final static String DATE_PAIEMENT = "datePaiement";

	public final static String CLE_INTERVENANT = "v_do_assigne_a";
	public final static String INTERVENANT = "dossierForm.intervenant";

	public BilletDossierTrieListe() {
		addComparator(CLE_NUMERO_DOSSIER, NUMERO_DOSSIER, NUMERO_CARDEX);
		
		addNewComparator( CLE_NUMERO_CARDEX, NUMERO_CARDEX );
		
		addComparator(CLE_NOM_BILLET, NOM_BILLET, NUMERO_CARDEX);
		
		addComparator(CLE_NUMERO_CONTROLE, NUMERO_CONTROLE, NUMERO_CARDEX);

		addComparator(CLE_MONTANT_LOT, MONTANT_LOT, NUMERO_CARDEX);

		addComparator(CLE_DATE_CREATION, DATE_CREATION, NUMERO_CARDEX);
		
		addComparator(CLE_CONFIDENTIALITE, CONFIDENTIALITE, NUMERO_CARDEX);
		
		addComparator(CLE_TYPE, TYPE, NUMERO_CARDEX);
		
		addComparator(CLE_CATEGORIE, CATEGORIE, NUMERO_CARDEX);
		
		addComparator(CLE_PERIODE, PERIODE, NUMERO_CARDEX);
		
		addComparator(CLE_STATUT, STATUT, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_DEBUT, DATE_DEBUT, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_FIN, DATE_FIN, NUMERO_CARDEX);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, NUMERO_CARDEX);

		addComparator(CLE_DATE_PAIEMENT, DATE_PAIEMENT, NUMERO_CARDEX);
	}
	
}
