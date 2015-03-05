/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class BilletOngletTrieListe extends TrierListeColumns {
	
	public final static String CLE_NOM_BILLET = "nom.billet";
	public final static String NOM_BILLET = "nom";
	
	public final static String CLE_NUMERO_CONTROL = "numero.control";
	public final static String NUMERO_CONTROL = "numeroControl";

	public final static String CLE_MONTANT_LOT = "montant.lot";
	public final static String MONTANT_LOT = "montantLot";

	public final static String CLE_PROVENANCE_BILLET = "provenance.billet";
	public final static String PROVENANCE_BILLET = "provenanceBillet";

	public final static String CLE_VALIDATION_BILLET = "validation.billet";
	public final static String VALIDATION_BILLET = "validationBillet";
		
	public final static String CLE_VERIFICATION_BILLET = "verification.billet";
	public final static String VERIFICATION_BILLET = "verificationBillet";

	public final static String CLE_PAIEMENT_BILLET = "date.paiement";
	public final static String PAIEMENT_BILLET = "datePaiement";

	public BilletOngletTrieListe() {
		addNewComparator(CLE_NOM_BILLET, NOM_BILLET);
		
		addNewComparator(CLE_NUMERO_CONTROL, NUMERO_CONTROL);
		
		addComparatorNumerique(CLE_MONTANT_LOT, MONTANT_LOT, NOM_BILLET);
		
		addComparator(CLE_PROVENANCE_BILLET, PROVENANCE_BILLET, NOM_BILLET);
		
		addComparator(CLE_VALIDATION_BILLET, VALIDATION_BILLET, NOM_BILLET);

		addComparator(CLE_VERIFICATION_BILLET, VERIFICATION_BILLET, NOM_BILLET);

		addComparator(CLE_PAIEMENT_BILLET, PAIEMENT_BILLET, NOM_BILLET);

	}
	
}
