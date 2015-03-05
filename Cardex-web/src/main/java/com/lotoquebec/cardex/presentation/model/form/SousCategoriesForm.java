package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Predicate;
import org.apache.struts.action.ActionMapping;

import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGN;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SousCategoriesNatureCle;
import com.lotoquebec.cardexCommun.model.EntiteForm;
import com.lotoquebec.cardexCommun.model.DoubleListe;
import com.lotoquebec.cardexCommun.model.LienCascade;
import com.lotoquebec.cardexCommun.presentation.util.ExclureLabelValuesPredicate;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.presentation.util.RetraitVidePredicate;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.ListeCacheUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class SousCategoriesForm extends EntiteForm<SousCategoriesVO> implements Serializable {

	private static final long serialVersionUID = -1739225092827736685L;
    private LienCascade hierarchieEGN = new HierarchieEGN();
    private DoubleListe doubleListe = new DoubleListe();
    private List<SousCategorieForm> sousCategories = new ArrayList<SousCategorieForm>();
    private Long categorieDossier;
    private List<Predicate> listeGauchePredicates = new ArrayList<Predicate>();
    
    
    public SousCategoriesForm(SousCategoriesVO sousCategoriesVO) {
		super(sousCategoriesVO);
		
		for(SousCategorieVO sousCategorieVO:sousCategoriesVO.getSousCategories()){
			sousCategories.add( new SousCategorieForm(sousCategorieVO) );
		}
	}

	public void init(CardexAuthenticationSubject subject, DossierForm dossierForm) throws BusinessResourceException, ValueObjectMapperException {
    	CardexUser user = (CardexUser) subject.getUser();
    	
        //doubleListe = new DoubleListe();
        categorieDossier = Long.valueOf(dossierForm.getCategorie());
        hierarchieEGN = new HierarchieEGN();
        
        hierarchieEGN.set(HierarchieEGN.ENTITE, String.valueOf(user.getEntite()));
        setGenre( String.valueOf(dossierForm.getGenre()) );
        setNature( String.valueOf(dossierForm.getNature()) );
        setCategorieDossier( Long.valueOf(dossierForm.getCategorie()));
        
        initDoubleListe(subject);
    }

    /**
     * RP0004	La liste des sous-catégories ne contient pas la catégorie du 
     * dossier.  Cette liste ne contient pas de vide.  Ces listes sont 
     * disjointes, il ne contienne pas les mêmes valeurs
     * 
     * @param exclureListe
     */
    public void assignerListeGauche(List<Long> exclureListe){
    	listeGauchePredicates.clear();
    	listeGauchePredicates.add( new RetraitVidePredicate() );
    	listeGauchePredicates.add( new ExclureLabelValuesPredicate(exclureListe) );
    	listeGauchePredicates.add( new ExclureLabelValuesPredicate( Arrays.asList(categorieDossier) ));
    }
	
	/*
	 * Vider les listes et les recharger
	 * Assigner les categories déjà sélectionné
	 */
    public void initDoubleListe(CardexAuthenticationSubject subject) throws BusinessResourceException, ValueObjectMapperException{
    	doubleListe.vider();
    	initListeDroit(subject);
    	initListeGauche(subject);
    	doubleListe.trier();
    }
    
    private void initListeDroit(CardexAuthenticationSubject subject) throws BusinessResourceException, ValueObjectMapperException{
    	
    	for(SousCategorieForm sousCategorieForm:sousCategories){
    		sousCategorieForm.assignerValeurDeListe(subject);
			doubleListe.addDroiteCol(sousCategorieForm);
		}
    }    
    
    public void initListeGauche(CardexAuthenticationSubject subject) throws BusinessResourceException, ValueObjectMapperException{
    	ListeCache listeCache = ListeCache.getInstance();
    	doubleListe.viderGaucheCol();
    	
    	if (StringUtils.isEmpty(getNature()))
    		return;
    	assignerListeGauche( ListeCacheUtils.obtenirListeValue( doubleListe.getDroiteCols() ) );
    	
    	List<LabelValue> labelValues = listeCache.obtenirListe(subject, new SousCategoriesNatureCle(subject, getNature()), listeGauchePredicates);
    	List<LabelValue> sousCategoriesGauche = new ArrayList<LabelValue>();
    	
    	for(LabelValue labelValue:labelValues){
    		SousCategorieVO sousCategorieVO = new SousCategorieVO();
    		sousCategorieVO.setCle( Long.valueOf(labelValue.getValue()) );
    		SousCategorieForm sousCategorieForm = new SousCategorieForm(sousCategorieVO);
    		sousCategorieForm.assignerValeurDeListe(subject,getNature());
    		sousCategoriesGauche.add( sousCategorieForm );
    	}
    	
    	doubleListe.setGaucheCols( sousCategoriesGauche );
    }   
    
	public void assignerSousCategorieVO() {
		Set<SousCategorieVO> sousCategories = new HashSet<SousCategorieVO>();
		
		for (LabelValue labelValue:doubleListe.getDroiteCols()){
			SousCategorieForm sousCategorieForm = (SousCategorieForm) labelValue;
			sousCategories.add( sousCategorieForm.getEntite() );
		}
		
		this.entite.setSousCategories( sousCategories );
	}
	
    // get / set
    public String getGenre() {
    	return hierarchieEGN.get(HierarchieEGN.GENRE);
    }

    public String getNature() {
    	return hierarchieEGN.get(HierarchieEGN.NATURE);
    } 
    
    public Long getCategorieDossier(){
        return categorieDossier;
    }

    public void setGenre(String genre) {
    	hierarchieEGN.set(HierarchieEGN.GENRE, genre);
    }

    public void setNature(String nature) {
    	hierarchieEGN.set(HierarchieEGN.NATURE, nature);
    }
    
    public void setCategorieDossier(Long categorieDossier)
    {
        this.categorieDossier = categorieDossier;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        doubleListe.viderDroiteCol();
    }

	public List<SousCategorieForm> getSousCategories() {
		return sousCategories;
	}

	public void setSousCategories(List<SousCategorieForm> sousCategories) {
		this.sousCategories = sousCategories;
	}

	public void addSousCategorie(SousCategorieForm sousCategorie) {
		this.sousCategories.add( sousCategorie );
	}

	public DoubleListe getDoubleListe() {
		return doubleListe;
	}

	public void setDoubleListe(DoubleListe doubleListe) {
		this.doubleListe = doubleListe;
	}

	public SousCategoriesVO getSousCategoriesVO() {
		return entite;
	}
	
	public String getLien() {
		return String.valueOf( entite.getLien() );
	}

	public void setLien(String lien) {
		this.entite.setLien( Long.valueOf(lien) );
	}

	public String getLienSite() {
		return String.valueOf( entite.getLienSite() );
	}

	public void setLienSite(String lienSite) {
		this.entite.setLienSite( Long.valueOf(lienSite) );
	}


	
}