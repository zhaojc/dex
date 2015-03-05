/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import com.lotoquebec.cardexCommun.util.StringComparator;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * cleComp: Cette cle est aussi la "key" dans la page JSP.  C'est grâce à elle que
 * le tag EnteteListeTriable peut lier la colonne a un comparateur de recherche.
 * @author levassc
 */
public abstract class TrierListeColumns implements Serializable {

	private String cleCompDernier = "";
	private Map comparatorMap = new HashMap();
	private String premierChamp = "";
	
	public String obtenirPremierChamp(){
		return premierChamp;
	}	
	
	public void trier(List liste, String cleComp, boolean descendant){
		Comparator comparator = (Comparator) comparatorMap.get( getCle( cleComp, descendant ) );
		
		if (comparator != null){
			assignerCleCompCourante( cleComp, descendant );
			Collections.sort(liste, comparator);
		}
	}
	
	public void trier(List liste){
		
		if (StringUtils.isNotEmpty( cleCompDernier )){
			Comparator comparator = (Comparator) comparatorMap.get( cleCompDernier );
			
			if (comparator != null){
				Collections.sort(liste, comparator);
			}
		}
	}
	
	private String getCle(String cleComp, boolean descendant){
		return cleComp+descendant;
	}
	
	public boolean isCleCompCourante(String cleComp, boolean descendant){
		return cleCompDernier.equals( getCle( cleComp, descendant ) );
	}
	
	public boolean isContientCle(String cleComp){
		boolean isContientAsc = comparatorMap.containsKey( getCle( cleComp, true ) );
		boolean isContientDesc = comparatorMap.containsKey( getCle( cleComp, false ) );
		return isContientAsc || isContientDesc;
	}
	
	protected void addListComparator(String cleComp, int index){
		addComparator( cleComp, false, construire( new ListeStringComparator(index), false ));
		addComparator( cleComp, true, construire( new ListeStringComparator(index), true ));
	}	
	
	protected void addListComparator(String cleComp, int index, Comparator preComparator){
		
		if (index == 0)
			premierChamp = cleComp;
		
		addComparator( cleComp, false, construire( new ListeStringComparator(index), preComparator, false ));
		addComparator( cleComp, true, construire( new ListeStringComparator(index), preComparator, true ));
	}
	
	protected void addComparator(String cleComp, boolean descendant, Comparator comparator){
		comparatorMap.put( getCle( cleComp, descendant ), comparator );
	}

	protected void addComparator(String cleComp, String cleDeTrie, String defaultcleDeTrie){
		ComparatorChain chainActifAsc = construireComparatorChain(cleDeTrie, defaultcleDeTrie, false); 
		ComparatorChain chainActifDesc = construireComparatorChain(cleDeTrie, defaultcleDeTrie, true);
		
		addComparator( cleComp, false, chainActifAsc );
		addComparator( cleComp, true, chainActifDesc );
	}

	protected void addComparatorNumerique(String cleComp, String cleDeTrie, String defaultcleDeTrie){
		ComparatorChain chainActifAsc = construireComparatorChain(cleDeTrie, defaultcleDeTrie, new NumeriqueComparator(), false); 
		ComparatorChain chainActifDesc = construireComparatorChain(cleDeTrie, defaultcleDeTrie, new NumeriqueComparator(), true);
		
		addComparator( cleComp, false, chainActifAsc );
		addComparator( cleComp, true, chainActifDesc );
	}	
	
	protected void addComparator(String cleComp, String cleDeTrie, Comparator comparator, String defaultcleDeTrie){
		ComparatorChain chainActifAsc = construireComparatorChain(cleDeTrie, defaultcleDeTrie, comparator, false); 
		ComparatorChain chainActifDesc = construireComparatorChain(cleDeTrie, defaultcleDeTrie, comparator, true);
		
		addComparator( cleComp, false, chainActifAsc );
		addComparator( cleComp, true, chainActifDesc );
	}		
	
	private ComparatorChain construireComparatorChain(String cleDeTrie, String defaultcleDeTrie, boolean descendant){
		ComparatorChain chainActif = new ComparatorChain();
		chainActif.addComparator( new BeanComparator(cleDeTrie, new StringComparator()), descendant ); 
		chainActif.addComparator( new BeanComparator(defaultcleDeTrie, new StringComparator()), false );		
		return chainActif;
	}
	
	private ComparatorChain construireComparatorChain(String cleDeTrie, String defaultcleDeTrie, Comparator comparator, boolean descendant){
		ComparatorChain chainActif = new ComparatorChain();
		chainActif.addComparator( new BeanComparator(cleDeTrie, comparator), descendant ); 
		chainActif.addComparator( new BeanComparator(defaultcleDeTrie, new StringComparator()), false );		
		return chainActif;
	}	
	
	public void assignerCleCompCourante(String cleComp, boolean descendant){
		cleCompDernier = getCle( cleComp, descendant );
	}

	protected void addNewComparator(String cleComp, String cleDeTrie){
		addComparator( cleComp, true, construire(cleDeTrie, true) );
		addComparator( cleComp, false, construire(cleDeTrie, false) );
	}
	
	protected void addNewComparator(String cle, boolean descendant){
		addComparator( cle, descendant, construire(cle, descendant) );
	}
	
	protected Comparator construire(String cleDeTrie, boolean descendant){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator(cleDeTrie, new StringComparator()), descendant); 
		return chainActif;
	}
	
	protected Comparator construire(Comparator comparator, boolean descendant){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(comparator, descendant);
		return chainActif;
	}	
	
	protected Comparator construire(Comparator comparator, Comparator preComparator, boolean descendant){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(comparator, descendant);
		chainActif.addComparator(preComparator, false);
		return chainActif;
	}		
	
}
