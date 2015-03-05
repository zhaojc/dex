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
public class SujetTrieListe extends TrierListeColumns {

	public final static String CLE_NO_FICHE = "v_no_fiche_t2";
	public final static String NO_FICHE = "numeroFiche";

	public final static String CLE_SEVERITE = "i_sev_inv_t";
	public final static String SEVERITE = "severiteDescription";
	
	public final static String CLE_SEVERITE_AUTRES = "i_sev_t";
	public final static String SEVERITE_AUTRES = "severiteDescriptionAutres";

	public final static String CLE_SEVERITE_CASINO = "i_sev_inv_casino_t";
	public final static String SEVERITE_CASINO = "severiteCasinoDescription";

	public final static String CLE_SURNOM = "v_su_surnom_t2";
	public final static String SURNOM = "alias";
	
	public final static String CLE_SEXE = "tabpage_sexe";
	public final static String SEXE = "sexeDescription";
	
	public final static String CLE_ETHNIE = "tabpage_nationalite";
	public final static String ETHNIE = "ethnieDescription";

	public final static String CLE_RACE = "tabpage_race";
	public final static String RACE = "raceDescription";

	public final static String CLE_LANGUE = "i_la_cle_t";
	public final static String LANGUE = "langueDescription";

	public final static String CLE_DATE_NAISSANCE = "d_su_date_naissance_t";
	public final static String DATE_NAISSANCE = "dateNaissance";

	public final static String CLE_NOM = "v_su_nom_t2";
	public final static String NOM = "nom";
	
	public final static String CLE_PRENOM = "v_su_prenom_t2";
	public final static String PRENOM = "prenom";
	
    public final static String CLE_TYPE_AGE = "tri.type.age";
    public final static String TYPE_AGE = "typeAge";
	
	public final static String CLE_REFERENCE1 = "v_do_reference1";
	public final static String REFERENCE1 = "reference1";

	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";	
	
    public ComparatorChain nomComparatorAsc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(NOM, new StringComparator()));
			addComparator(new BeanComparator(PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};	
	public ComparatorChain nomComparatorDesc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(NOM, new StringComparator()), true);
			addComparator(new BeanComparator(PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};
	
	public ComparatorChain prenomComparatorAsc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};	
	public ComparatorChain prenomComparatorDesc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(PRENOM, new StringComparator()), true);
			addComparator(new BeanComparator(NOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};	

	
	public SujetTrieListe() {
		super();
		
		addNewComparator( CLE_NO_FICHE, NO_FICHE );
		
		addComparator(CLE_SEVERITE, SEVERITE, NO_FICHE);
		addComparator(CLE_SEVERITE_AUTRES, SEVERITE_AUTRES, NO_FICHE);
		addComparator(CLE_SEVERITE_CASINO, SEVERITE_CASINO, NO_FICHE);
		addComparator(CLE_NOM, false, nomComparatorAsc);
		addComparator(CLE_NOM, true, nomComparatorDesc);
		
		addComparator(CLE_PRENOM, false, prenomComparatorAsc);
		addComparator(CLE_PRENOM, true, prenomComparatorDesc);
		
		addComparator(CLE_SURNOM, SURNOM, NO_FICHE);

		addComparator(CLE_SEXE, SEXE, NO_FICHE);

		addComparator(CLE_ETHNIE, ETHNIE, NO_FICHE);

		addComparator(CLE_RACE, RACE, NO_FICHE);

		addComparator(CLE_LANGUE, LANGUE, NO_FICHE);

		addComparator(CLE_DATE_NAISSANCE, DATE_NAISSANCE, NO_FICHE);
		
		addComparator(CLE_TYPE_AGE, TYPE_AGE,NO_FICHE);

		addComparator(CLE_REFERENCE1, REFERENCE1, NO_FICHE);

		addComparator(CLE_STATUT, STATUT, NO_FICHE);
				
	}
	
}
