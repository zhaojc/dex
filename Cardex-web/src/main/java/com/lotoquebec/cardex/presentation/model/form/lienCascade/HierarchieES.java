/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardex.presentation.model.form.lienCascade;

import com.lotoquebec.cardexCommun.model.LienCascade;

/**
 * @author levassc
 */
public class HierarchieES extends LienCascade{

	public final static String ENTITE = "entite";
	public final static String SITE_ORIGINE = "siteOrigine";

	public HierarchieES() {
		ajout(ENTITE);
		ajout(SITE_ORIGINE);
	}
	
	public String getEntite() {
		return get(HierarchieES.ENTITE);
	}
	public void setEntite(String entite) {
		set(HierarchieES.ENTITE, entite);
	}
	public String getSiteOrigine() {
		return get(HierarchieES.SITE_ORIGINE);
	}
	public void setSiteOrigine(String siteOrigine) {
		set(HierarchieES.SITE_ORIGINE, siteOrigine);
	}
}
