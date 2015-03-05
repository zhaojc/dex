/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;
import com.lotoquebec.cardexCommun.util.StringComparator;


/**
 * @author levassc
 */
public class SocieteTrieListe extends TrierListeColumns {

	public final static String CLE_NOM = "v_so_nom";
	public final static String NOM = "nom";

	public final static String CLE_NO_FICHE = "v_no_fiche_t2";
	public final static String NO_FICHE = "numeroFiche";

	public final static String CLE_SEVERITE = "i_sev_inv_t";
	public final static String SEVERITE = "severiteDescription";

	public final static String CLE_SEVERITE_CASINO = "i_sev_inv_casino_t";
	public final static String SEVERITE_CASINO = "severiteCasinoDescription";

	public final static String CLE_RAISON_SOCIALE = "v_so_raison_sociale_t3";
	public final static String RAISON_SOCIALE = "raisonEtre";

	public final static String CLE_CLASSE = "tabpage_classe";
	public final static String CLASSE = "classeDescription";
	
	public final static String CLE_REFERENCE_NOM = "v_so_reference_nom_t2";
	public final static String REFERENCE_NOM = "referenceNom";
	
	public final static String CLE_REFERENCE_PRENOM = "v_so_reference_prenom_t2";
	public final static String REFERENCE_PRENOM = "referencePrenom";

	public ComparatorChain referenceNomComparatorAsc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(REFERENCE_NOM, new StringComparator()));
			addComparator(new BeanComparator(REFERENCE_PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NOM, new StringComparator()));
		}
	};	
	public ComparatorChain referenceNomComparatorDesc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(REFERENCE_NOM, new StringComparator()), true);
			addComparator(new BeanComparator(REFERENCE_PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NOM, new StringComparator()));
		}
	};
	
	public ComparatorChain referencePrenomComparatorAsc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(REFERENCE_PRENOM, new StringComparator()));
			addComparator(new BeanComparator(REFERENCE_NOM, new StringComparator()));
			addComparator(new BeanComparator(NOM, new StringComparator()));
		}
	};	
	public ComparatorChain referencePrenomComparatorDesc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(REFERENCE_PRENOM, new StringComparator()), true);
			addComparator(new BeanComparator(REFERENCE_NOM, new StringComparator()));
			addComparator(new BeanComparator(NOM, new StringComparator()));
		}
	};	

	
	class PreNomComparator extends ComparatorChain{
		public PreNomComparator(String property, boolean descendant) {
			super();
			addComparator(new BeanComparator(property, new StringComparator()), descendant);
			addComparator(new BeanComparator(NOM, new StringComparator()));
		}
	}
	
	public SocieteTrieListe() {
		super();
		addNewComparator( CLE_NOM, NOM );
		
		addComparator(CLE_NO_FICHE, NO_FICHE, NOM);

		addComparator(CLE_SEVERITE, SEVERITE, NOM);
		addComparator(CLE_SEVERITE_CASINO, SEVERITE_CASINO, NOM);

		addComparator(CLE_RAISON_SOCIALE, RAISON_SOCIALE, NOM);

		addComparator(CLE_CLASSE, CLASSE, NOM);
		
		addComparator(CLE_REFERENCE_NOM, false, referenceNomComparatorAsc);
		addComparator(CLE_REFERENCE_NOM, true, referenceNomComparatorDesc);
		
		addComparator(CLE_REFERENCE_PRENOM, false, referencePrenomComparatorAsc);
		addComparator(CLE_REFERENCE_PRENOM, true, referencePrenomComparatorDesc);
	}
	
}
