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
public class SujetOngletTrieListe extends TrierListeColumns {

	public final static String CLE_NO_FICHE = "v_no_fiche_t2";
	public final static String NO_FICHE = "numeroFiche";

	public final static String CLE_SEVERITE = "i_sev_inv_t";
	public final static String SEVERITE = "severiteDescription";
	
	public final static String CLE_SEVERITE_AUTRES = "i_sev_t";
	public final static String SEVERITE_AUTRES = "severiteDescriptionAutres";

	public final static String CLE_SEVERITE_CASINO = "i_sev_inv_casino_t";
	public final static String SEVERITE_CASINO = "severiteCasinoDescription";
	
	public final static String CLE_CONFIDENTIALITE = "i_cc_commentaire_t";
	public final static String CONFIDENTIALITE = "confidentialiteDescription";
	
	public final static String CLE_SEXE = "tabpage_sexe";
	public final static String SEXE = "sexeDescription";
	
	public final static String CLE_ETHNIE = "tabpage_nationalite";
	public final static String ETHNIE = "ethnieDescription";

	public final static String CLE_RACE = "tabpage_race";
	public final static String RACE = "raceDescription";

	public final static String CLE_LANGUE = "i_la_cle_t";
	public final static String LANGUE = "langueDescription";

	public final static String CLE_ROLE = "tabpage_role";
	public final static String ROLE = "roleDescription";

	public final static String CLE_NOM = "v_su_nom_t2";
	public final static String NOM = "nom";
	
	public final static String CLE_PRENOM = "v_su_prenom_t2";
	public final static String PRENOM = "prenom";
	
	public final static String CLE_DATE_CREATION = "date_t";
	public final static String DATE_CREATION = "lienDateCreation";
	
	public final static String CLE_ANNEE_NAISSANCE = "d_su_annee_naissance";
	public final static String ANNEE_NAISSANCE = "anneeNaissance";
	
	public final static String CLE_LIEN_CREATEUR = "lie.par";
	public final static String LIEN_CREATEUR = "lienCreateur";	

	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";	
	
	public final static String CLE_DATE_FIN_ENQUETE = "date.fin.enquete";
	public final static String DATE_FIN_ENQUETE = "dateFinEnquete";	

	public final static String CLE_DATE_FIN_EMPLOI = "date.fin.emploi";
	public final static String DATE_FIN_EMPLOI = "dateFinEmploi";	

	public final static String CLE_DATE_LIAISON = "lie.le";
	public final static String DATE_LIAISON = "lienDateCreation";	

	public final static String AUDIT = "audit";
	
	public ComparatorChain nomComparatorAsc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(AUDIT, new StringComparator()), true);
			addComparator(new BeanComparator(NOM, new StringComparator()));
			addComparator(new BeanComparator(PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};	
	public ComparatorChain nomComparatorDesc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(AUDIT, new StringComparator()), true);
			addComparator(new BeanComparator(NOM, new StringComparator()), true);
			addComparator(new BeanComparator(PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};
	
	public ComparatorChain prenomComparatorAsc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(AUDIT, new StringComparator()), true);
			addComparator(new BeanComparator(PRENOM, new StringComparator()));
			addComparator(new BeanComparator(NOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};	
	public ComparatorChain prenomComparatorDesc = new ComparatorChain(){
		{
			addComparator(new BeanComparator(AUDIT, new StringComparator()), true);
			addComparator(new BeanComparator(PRENOM, new StringComparator()), true);
			addComparator(new BeanComparator(NOM, new StringComparator()));
			addComparator(new BeanComparator(NO_FICHE, new StringComparator()));
		}
	};	

	
	public SujetOngletTrieListe() {
		super();
		
		addNewComparator( CLE_NO_FICHE, NO_FICHE );
		
		addComparator(CLE_SEVERITE, AUDIT, SEVERITE);
		addComparator(CLE_SEVERITE_AUTRES, AUDIT, SEVERITE_AUTRES);
		addComparator(CLE_SEVERITE_CASINO, AUDIT, SEVERITE_CASINO);
		
		addComparator(CLE_CONFIDENTIALITE, AUDIT, CONFIDENTIALITE);
		
		addComparator(CLE_NOM, false, nomComparatorAsc);
		addComparator(CLE_NOM, true, nomComparatorDesc);
		
		addComparator(CLE_PRENOM, false, prenomComparatorAsc);
		addComparator(CLE_PRENOM, true, prenomComparatorDesc);
		
		//Le critère AUDIT est utilisé pour garder les informations historiques dans le bas de l'onglet
		addComparator(CLE_SEXE, AUDIT, SEXE);

		addComparator(CLE_ETHNIE, AUDIT, ETHNIE);

		addComparator(CLE_RACE, AUDIT, RACE);

		addComparator(CLE_LANGUE, AUDIT, LANGUE);

		addComparator(CLE_ROLE, AUDIT, ROLE);
		
		addComparator(CLE_STATUT, AUDIT, STATUT);
		
		addComparator(CLE_LIEN_CREATEUR, AUDIT, LIEN_CREATEUR);

		addComparator(CLE_DATE_CREATION, AUDIT, DATE_CREATION);

		addComparator(CLE_ANNEE_NAISSANCE, AUDIT, ANNEE_NAISSANCE);

		addComparator(CLE_DATE_FIN_ENQUETE, AUDIT, DATE_FIN_ENQUETE);

		addComparator(CLE_DATE_FIN_EMPLOI, AUDIT, DATE_FIN_EMPLOI);

		addComparator(CLE_DATE_LIAISON, AUDIT, DATE_LIAISON);

	}
	
}
