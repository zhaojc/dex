package com.lotoquebec.cardex.business;

import java.util.Set;

import com.lotoquebec.cardex.business.vo.SousCategorieVO;

public interface SousCategories {

	public abstract long getLien();

	public abstract void setLien(long lien);

	public abstract long getLienSite();

	public abstract void setLienSite(long lienSite);

	public abstract Set<SousCategorieVO> getSousCategories();

	public abstract void setSousCategories(
			Set<SousCategorieVO> sousCategories);

	public abstract void addSousCategorie(SousCategorieVO sousCategorieVO);

}