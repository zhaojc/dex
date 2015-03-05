/*
 * Created on 21-Apr-2008
 */
package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Cette classe � la responsabilit� de g�rer l'affichage de r�sultat � l'int�rieur
 * de Cardex.  Elle est responsable de la navigation � l'�cran.
 * @author levassc
 */
public class ListeResultat <T> implements Serializable {

	private TrierListeColumns trierListeColumns = null;
	private LinkedList<T> resultatComplet = new LinkedList(); // liste compl�te des enregistrements possbile � pr�snter
	private LinkedList resultatCompletAudit = new LinkedList(); // liste compl�te des audits de changement � pr�senter 
	private List<T> resultatAffichage = new ArrayList(); // sous ensemble de "resultatComplet" 
	private String plageResultats = ""; // Quantilit� d'enregistrement � pr�senter par �cran.
	private int numeroPageCourante = 1;
	private String cleCompDefault = "";
	private boolean descendantDefault = false;
	private boolean defaultActif = false;
	
	public ListeResultat() {
		super();
		plageResultats = GlobalConstants.RechercheList.MAX_RESULTATS_DEFAULT;
	}
	
	// Aller � l'enregistrement de d�part
	public void premier(){
		numeroPageCourante = 1;
		chargerAffichage();
	}

	// Aller � les enregistrements de fin
	public void dernier(){
		numeroPageCourante = getNombrePageTotal();
		chargerAffichage();
	}	

	public boolean isPossedeSuivant(){
		int nbPageTotal = getNombrePageTotal();
		
		if (numeroPageCourante < nbPageTotal)
			return true;
		
		return false;
	}
	
	// Consulter la plage suivant
	public void suivant(){
		numeroPageCourante++;
		chargerAffichage();
	}

	public boolean isPossedePrecedant(){
		
		if (numeroPageCourante > 1)
			return true;
		
		return false;
	}
	
	// Consulter la plage pr�c�dente
	public void precendant(){
		numeroPageCourante--;
		chargerAffichage();
	}	
	
	public void chargerAffichage() {
		
		if (numeroPageCourante < 1)
			numeroPageCourante = 1;
		else if (numeroPageCourante > getNombrePageTotal())
			numeroPageCourante = getNombrePageTotal();
		
		int debut = (numeroPageCourante - 1) * Integer.valueOf(plageResultats).intValue();
		int fin = numeroPageCourante * Integer.valueOf(plageResultats).intValue();
		
		if (debut < 0)
			debut = 0;
		
		if (fin > resultatComplet.size())
			fin = resultatComplet.size();
		
		if (resultatComplet.size() > 0)
			resultatAffichage = new ArrayList( resultatComplet.subList(debut, fin) );
	}

	private void chargerAffichageAudit() {
		int debut = (numeroPageCourante - 1) * Integer.valueOf(plageResultats).intValue();
		int fin = numeroPageCourante * Integer.valueOf(plageResultats).intValue();
		
		if (debut < 0)
			debut = 0;
		
		if (fin > resultatCompletAudit.size())
			fin = resultatCompletAudit.size();
		resultatAffichage = new ArrayList( resultatCompletAudit.subList(debut, fin) );
	}

	public void init(){
		vider();
		plageResultats = GlobalConstants.RechercheList.MAX_RESULTATS_DEFAULT;
		
		if (defaultActif)
			trier(cleCompDefault, descendantDefault);
	}
	
	public void vider(){
		resultatComplet = new LinkedList();
		resultatCompletAudit = new LinkedList();
		resultatAffichage = new ArrayList();
		numeroPageCourante = 1;
	}
	
	public int getNombrePageTotal(){
		return (int)Math.ceil(((double)resultatComplet.size())/(Integer.valueOf(plageResultats).intValue()));		
	}
	
	public String getPlageResultats() {
		return plageResultats;
	}
	
	public void setPlageResultats(String pageResultats) {
		this.plageResultats = pageResultats;
	}
	
	public List<T> getResultatComplet() {
		return resultatComplet;
	}
	
	public void add(T o) {
		resultatComplet.add( o );
	}
	
	public void setResultatComplet(List<T> resultatComplet) {
		vider();
		this.resultatComplet = new LinkedList( resultatComplet );
		//retirerDoublon();
		chargerAffichage();
	}
	
	//Sert � afficher tous les r�sultats qui proviennent de la table de l'audit des changements.
	//Dans ce cas, on ne retire pas les doublons
	public void setResultatCompletAudit(List resultatComplet) {
		vider();
		this.resultatCompletAudit = new LinkedList( resultatComplet );
		//retirerDoublon();
		chargerAffichageAudit();
	}
	
	// Il est parfois possible d'avoir des doublons d'entit� Cardex.
	// Cette m�thode les retires gr�ce � la cle unique Cardex. 
	// => Il ne devrait pas y avoir de doublon?
	/*private void retirerDoublon(){
		Map cleMap = new HashMap();
		Iterator iter = resultatComplet.iterator();
		
		while (iter.hasNext()) {
			EntiteCardex element = (EntiteCardex) iter.next();
			String cle = getCle( element );
			
			if (cleMap.containsKey(cle))
				iter.remove(); // doublon
			else
				cleMap.put(cle, element);
		}
	}*/
	
	public String getCle(EntiteCardex element){
		return element.getCle()+"-"+element.getSite();
	}
	
	public List<T> getResultatAffichage() {
		return resultatAffichage;
	}
	
	public int getSize(){
		return resultatComplet.size();
	}
	
	public int getNumeroPageCourant() {
		return numeroPageCourante;
	}
	/*
	public void trier(Comparator comparator){
		numeroPageCourant = 1;
		Collections.sort(resultatComplet, comparator);
		chargerAffichage();
	}*/

	public void assignerTrierDefault(String cleComp, boolean descendant, TrierListeColumns trierListeColumns){
		cleCompDefault = cleComp;
		descendantDefault = descendant;
		defaultActif = true;
		this.trierListeColumns = trierListeColumns;
		trier(cleComp, descendant);
	}
	
	public void trier(String cleComp, boolean descendant){
		numeroPageCourante = 1;
		trierListeColumns.trier( resultatComplet, cleComp, descendant );
		chargerAffichage();
	}
	
	public void trier(){
		numeroPageCourante = 1;
		trierListeColumns.trier( resultatComplet );
		chargerAffichage();
	}
	
	public void setTrierListeColumns(TrierListeColumns trierListeColumns) {
		this.trierListeColumns = trierListeColumns;
	}
	
	public TrierListeColumns getTrierListeColumns() {
		return trierListeColumns;
	}

	public void setNumeroPageCourante(String numeroPageCourante) {
		this.numeroPageCourante = Integer.valueOf( numeroPageCourante );
	}

	
}
