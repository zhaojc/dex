package com.lotoquebec.cardex.business.vo;

import java.util.HashSet;
import java.util.Set;

import com.lotoquebec.cardex.business.SousCategories;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;

public class SousCategoriesVO implements SousCategories, EntiteCardex {

	private long lien = 0;
    private long lienSite = 0;
    private long type = 0;
    private Set<SousCategorieVO> sousCategories = new HashSet<SousCategorieVO>();

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#getLien()
	 */
	public long getLien() {
		return lien;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#setLien(long)
	 */
	public void setLien(long lien) {
		this.lien = lien;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#getLienSite()
	 */
	public long getLienSite() {
		return lienSite;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#setLienSite(long)
	 */
	public void setLienSite(long lienSite) {
		this.lienSite = lienSite;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#getListeSousCategoriesChoisis()
	 */
	public Set<SousCategorieVO> getSousCategories() {
		return sousCategories;
	}
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#setListeSousCategoriesChoisis(java.util.List)
	 */
	public void setSousCategories(Set<SousCategorieVO> sousCategories) {
		this.sousCategories = sousCategories;
	}
    /* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.vo.SousCategories#addListeSousCategorie(com.lotoquebec.cardex.business.vo.SousCategorieVO)
	 */
    public void addSousCategorie(SousCategorieVO sousCategorieVO){
    	this.sousCategories.add( sousCategorieVO );
    }
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public long getCle() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Set<LiaisonEntiteVO> getLiaisonEntites() {
		// TODO Auto-generated method stub
		return null;
	}
	public long getSite() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setCle(long cle) {
		lien = cle;
	}
	public void setSite(long site) {
		this.lienSite = site;
	}
}
