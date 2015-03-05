/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;

/**
 * @author levassc
 */
public class SocieteOngletTrieListe extends TrierListeColumns {

	public final static String CLE_NO_FICHE = "v_no_fiche_t2";
	public final static String NO_FICHE = "numeroFiche";

	public final static String CLE_SEVERITE = "i_sev_inv_t";
	public final static String SEVERITE = "severiteDescription";

	public final static String CLE_SEVERITE_CASINO = "i_sev_inv_casino_t";
	public final static String SEVERITE_CASINO = "severiteCasinoDescription";

	public final static String CLE_RAISON_SOCIALE = "v_so_raison_sociale_t3";
	public final static String RAISON_SOCIALE = "raisonEtre";

	public final static String CLE_NOM = "v_so_nom";
	public final static String NOM = "nom";

	public final static String CLE_CLASSE = "tabpage_classe";
	public final static String CLASSE = "classeDescription";
	
	public final static String CLE_ROLE = "tabpage_role";
	public final static String ROLE = "roleDescription";
	
	public final static String CLE_LIEN_CREATEUR = "lie.par";
	public final static String LIEN_CREATEUR = "lienCreateur";	
	
	public final static String CLE_DATE_LIAISON = "lie.le";
	public final static String DATE_LIAISON = "lienDateCreation";	

	public final static String AUDIT = "audit";
	
	public SocieteOngletTrieListe() {
		super();
		addComparator( CLE_NOM, AUDIT, NOM );
		
		addComparator(CLE_NO_FICHE, AUDIT, NO_FICHE);

		addComparator(CLE_SEVERITE, AUDIT, SEVERITE);

		addComparator(CLE_SEVERITE_CASINO, AUDIT, SEVERITE_CASINO);

		addComparator(CLE_RAISON_SOCIALE, AUDIT, RAISON_SOCIALE);

		addComparator(CLE_CLASSE, AUDIT, CLASSE);
		
		addComparator(CLE_ROLE, AUDIT, ROLE);
		
		addComparator(CLE_LIEN_CREATEUR, AUDIT, LIEN_CREATEUR);

		addComparator(CLE_DATE_LIAISON, AUDIT, DATE_LIAISON);

	}
	
}
