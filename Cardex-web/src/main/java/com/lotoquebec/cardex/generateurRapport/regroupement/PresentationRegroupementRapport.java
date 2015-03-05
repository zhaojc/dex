package com.lotoquebec.cardex.generateurRapport.regroupement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;


/**
 * Conserve les différentes valeurs relatives au formulaire pour la production
 * des rapports de regroupement.
 *
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheJournalHtmlForm
 */
public class PresentationRegroupementRapport  {

    private List listeResultatPresentationRegroupement = new ArrayList(); //ResultatPresentationRegroupement
    private TotalRechercheRegroupement totalRechercheRegroupement = new TotalRechercheRegroupement();
    private Boolean heuresProductiveValide = true;
    
    /**
     * Constructeur de CriteresRechercheRegroupementForm par défaut.
     */
    public PresentationRegroupementRapport() {}


    // Getters
    
    public int getSizeListeResultatPresentationRegroupement(){
    	return listeResultatPresentationRegroupement.size();
    }

	public void init(){
        viderResultatPresentationRegroupement();
	}
	public List getListeResultatPresentationRegroupement() {
		return listeResultatPresentationRegroupement;
	}
	public void addListeResultatPresentationRegroupement(
			ResultatPresentationRegroupement resultatPresentationRegroupement) {
		totalRechercheRegroupement.addMinutes( resultatPresentationRegroupement.getNombreMinutes() );
		this.listeResultatPresentationRegroupement.add( resultatPresentationRegroupement );
	}
	
	public void setListeResultatPresentationRegroupement(
			List listeResultatPresentationRegroupement) {
		this.listeResultatPresentationRegroupement = listeResultatPresentationRegroupement;
	}
	
	public void trierNomRegroupement(){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		Collections.sort(listeResultatPresentationRegroupement, chainActif);			 
	}
	
	public void trierCategorie(){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("categorie")); 
		Collections.sort(listeResultatPresentationRegroupement, chainActif);			 
	}

	public void trierMoisNombreRegroupement(){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("moisNombre")); 
		chainActif.addComparator(new BeanComparator("nomRegroupement"));
		Collections.sort(listeResultatPresentationRegroupement, chainActif);			 
	}

	
	public void calculerPourcentageListeResultatPresentationRegroupement(){
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			resultatPresentationRegroupement.setNombreMinutes( resultatPresentationRegroupement.calculerNombreMinutesSub() );
			resultatPresentationRegroupement.calculerPoucentage( totalRechercheRegroupement.getNombreTotalMinutes() );
		}
		totalRechercheRegroupement.calculerQuota(listeResultatPresentationRegroupement);
	}
	public void viderResultatPresentationRegroupement(){
		listeResultatPresentationRegroupement = new ArrayList();
		totalRechercheRegroupement.init();
	}

	public void trierEndroitNomRegroupement() {
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("endroit"));
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		Collections.sort(listeResultatPresentationRegroupement, chainActif);	
	}

	public void trierNomRegroupementIntervenantTypeCategorie() {
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		chainActif.addComparator(new BeanComparator("intervenant"));
		chainActif.addComparator(new BeanComparator("type"));
		chainActif.addComparator(new BeanComparator("categorie"));
		Collections.sort(listeResultatPresentationRegroupement, chainActif);	
	}	

	public void trierIntervenantNomRegroupementEndroit() {
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("intervenant"));
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		chainActif.addComparator(new BeanComparator("endroit"));
		Collections.sort(listeResultatPresentationRegroupement, chainActif);	
	}	
	
	public void trierNomRegroupementTypeCategorie() {
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		chainActif.addComparator(new BeanComparator("type"));
		chainActif.addComparator(new BeanComparator("categorie"));
		Collections.sort(listeResultatPresentationRegroupement, chainActif);	
	}	

	public void trierNomRegroupementIntervenant() {
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		chainActif.addComparator(new BeanComparator("intervenant"));
		Collections.sort(listeResultatPresentationRegroupement, chainActif);	
	}	
	
	public void trierIntervenantNomRegroupement() {
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("intervenant"));
		chainActif.addComparator(new BeanComparator("nomRegroupement")); 
		Collections.sort(listeResultatPresentationRegroupement, chainActif);	
	}		
	
	public void traiterListeResultatPresentationRegroupementEndroit(){
		ResultatPresentationRegroupement temoinResultatPresentationRegroupement = null;
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			
			if (temoinResultatPresentationRegroupement == null){
				temoinResultatPresentationRegroupement = resultatPresentationRegroupement;
				continue;
			}
			if (temoinResultatPresentationRegroupement.getNomRegroupement().equals(
					resultatPresentationRegroupement.getNomRegroupement())){
				temoinResultatPresentationRegroupement.getListeSubResultatPresentationRegroupement().add( resultatPresentationRegroupement );
				iter.remove();
			}else{
				temoinResultatPresentationRegroupement = resultatPresentationRegroupement;
			}
		}
	}
	
	public TotalRechercheRegroupement getTotalRechercheRegroupement() {
		return totalRechercheRegroupement;
	}
	public void setTotalRechercheRegroupement(
			TotalRechercheRegroupement totalRechercheRegroupement) {
		this.totalRechercheRegroupement = totalRechercheRegroupement;
	}
	public Boolean isHeuresProductiveValide() {
		return heuresProductiveValide;
	}
	public void setHeuresProductiveValide(Boolean heuresProductiveValide) {
		this.heuresProductiveValide = heuresProductiveValide;
	}


	public Boolean getHeuresProductiveValide() {
		return heuresProductiveValide;
	}
	
}