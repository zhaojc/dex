/*
 * Created on 14-March-2014
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;
import com.lotoquebec.cardexCommun.util.StringComparator;

/**
 * @author mazzucr
 */
public class UrgenceTrieListe extends TrierListeColumns {

	public final static String CLE_CLE = "co_no_fiche_t2";
	public final static String CLE = "cle";

	public final static String CLE_CLASSE = "tabpage_classe";
    public final static String CLASSE = "classeDescription";

    public final static String CLE_DOSSIER = "no_dossier_entete_t";
    public final static String DOSSIER  = "dossier.numeroCardexTexte";

    public final static String CLE_SOCIETE = "tabpage_societe";
    public final static String SOCIETE = "societe";
    
	public final static String CLE_UNITE = "tabpage_unite";
	public final static String UNITE = "unite";
	
	public final static String CLE_DISTRICT = "tabpage_district";
	public final static String DISTRICT = "district";
	
	public final static String CLE_CONTACT = "tabpage_contact";
	public final static String CONTACT = "contact";
	
	public final static String CLE_CONTACT_PRENOM = "tabpage_contact_prenom";
	public final static String CONTACT_PRENOM = "contactPrenom";

	public final static String CLE_VILLE = "tabpage_ville";
	public final static String VILLE = "ville";

    public final static String CLE_EVENEMENT = "tabpage_evenement";
    public final static String EVENEMENT = "evenement";

    public final static String CLE_FONCTION = "tabpage_fonction";
    public final static String FONCTION = "fonction";

    public final static String CLE_MATRICULE = "tabpage_matricule";
    public final static String MATRICULE = "matricule";

    public ComparatorChain nomComparatorAsc = new ComparatorChain(){
        {
            addComparator(new BeanComparator(CONTACT, new StringComparator()));
            addComparator(new BeanComparator(CONTACT_PRENOM, new StringComparator()));
            addComparator(new BeanComparator(CLE, new StringComparator()));
        }
    };  
    public ComparatorChain nomComparatorDesc = new ComparatorChain(){
        {
            addComparator(new BeanComparator(CONTACT, new StringComparator()), true);
            addComparator(new BeanComparator(CONTACT_PRENOM, new StringComparator()));
            addComparator(new BeanComparator(CLE, new StringComparator()));
        }
    };
    
    public ComparatorChain prenomComparatorAsc = new ComparatorChain(){
        {
            addComparator(new BeanComparator(CONTACT_PRENOM, new StringComparator()));
            addComparator(new BeanComparator(CONTACT, new StringComparator()));
            addComparator(new BeanComparator(CLE, new StringComparator()));
        }
    };  
    public ComparatorChain prenomComparatorDesc = new ComparatorChain(){
        {
            addComparator(new BeanComparator(CONTACT_PRENOM, new StringComparator()), true);
            addComparator(new BeanComparator(CONTACT, new StringComparator()));
            addComparator(new BeanComparator(CLE, new StringComparator()));
        }
    };  
    
	public UrgenceTrieListe() {
	    addNewComparator( CLE_CLE, CLE );
		
	    addNewComparator(CLE_CLASSE, CLASSE);
	    
        addComparator(CLE_CONTACT, false, nomComparatorAsc);
        addComparator(CLE_CONTACT, true, nomComparatorDesc);
        
        addComparator(CLE_CONTACT_PRENOM, false, prenomComparatorAsc);
        addComparator(CLE_CONTACT_PRENOM, true, prenomComparatorDesc);
		
        addNewComparator( CLE_DOSSIER, DOSSIER );

        addNewComparator( CLE_SOCIETE, SOCIETE );

        addComparator(CLE_UNITE, UNITE, CLE);
		
		addComparator(CLE_DISTRICT, DISTRICT, CLE);
		
		addComparator(CLE_CONTACT, CONTACT, CLE);
		
		addComparator(CLE_CONTACT_PRENOM, CONTACT_PRENOM, CLE);
		
		addComparator(CLE_VILLE, VILLE, CLE);

		addComparator(CLE_EVENEMENT, EVENEMENT, CLE);

        addComparator(CLE_FONCTION, FONCTION, CLE);

        addComparator(CLE_MATRICULE, MATRICULE, CLE);
	}
}
