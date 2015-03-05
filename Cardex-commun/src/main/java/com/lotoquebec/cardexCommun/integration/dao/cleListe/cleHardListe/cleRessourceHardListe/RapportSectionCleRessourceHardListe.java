/*
 * Created on 28-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe;

import javax.servlet.http.HttpServletRequest;

/**
 * @author levassc
 */
public class RapportSectionCleRessourceHardListe extends CleRessourceHardListe {

	public final static String RAPPORT_SECTION_PAGE_TITRE = "rapport.section.page.titre";
	public final static String RAPPORT_SECTION_IDENTIFICATION_1 = "rapport.section.identification.1";
	public final static String RAPPORT_SECTION_TABLE_DES_MATIERES_1 = "rapport.section.table.des.matieres.1";
	public final static String RAPPORT_SECTION_INTRODUCTION_1 = "rapport.section.introduction.1";
	public final static String RAPPORT_SECTION_ENQUETE_1 = "rapport.section.enquete.1";
	public final static String RAPPORT_SECTION_ENQUETE_1_1 = "rapport.section.enquete.1.1";
	public final static String RAPPORT_SECTION_ENQUETE_1_2 = "rapport.section.enquete.1.2";
	public final static String RAPPORT_SECTION_ENQUETE_2 = "rapport.section.enquete.2";
	public final static String RAPPORT_SECTION_ENQUETE_2_1 = "rapport.section.enquete.2.1";
	public final static String RAPPORT_SECTION_ENQUETE_2_2 = "rapport.section.enquete.2.2";
	public final static String RAPPORT_SECTION_ENQUETE_3 = "rapport.section.enquete.3";
	public final static String RAPPORT_SECTION_ENQUETE_3_1 = "rapport.section.enquete.3.1";
	public final static String RAPPORT_SECTION_ENQUETE_3_2 = "rapport.section.enquete.3.2";
	public final static String RAPPORT_SECTION_ENQUETE_4 = "rapport.section.enquete.4";
	public final static String RAPPORT_SECTION_ENQUETE_4_1 = "rapport.section.enquete.4.1";
	public final static String RAPPORT_SECTION_ENQUETE_4_2 = "rapport.section.enquete.4.2";
	public final static String RAPPORT_SECTION_ENQUETE_5 = "rapport.section.enquete.5";
	public final static String RAPPORT_SECTION_ENQUETE_5_1 = "rapport.section.enquete.5.1";
	public final static String RAPPORT_SECTION_ENQUETE_5_2 = "rapport.section.enquete.5.2";
	public final static String RAPPORT_SECTION_ENQUETE_6 = "rapport.section.enquete.6";
	public final static String RAPPORT_SECTION_CONSTAT_1 = "rapport.section.constat.1";
	public final static String RAPPORT_SECTION_CONCLUSION_1 = "rapport.section.conclusion.1";
	public final static String RAPPORT_SECTION_RECOMMANDATIONS_1 = "rapport.section.recommandations.1";
	public final static String RAPPORT_SECTION_AUTRES_1 = "rapport.section.autres.1";
	public final static String RAPPORT_SECTION_AUTRES_2 = "rapport.section.autres.2";
	public final static String RAPPORT_SECTION_AUTRES_3 = "rapport.section.autres.3";
	
	
	public RapportSectionCleRessourceHardListe(HttpServletRequest request, String langue) {
		super(request, langue);
	}

	protected void genererListe(){
		add("","");
		addLabelKey(RAPPORT_SECTION_PAGE_TITRE);
		addLabelKey(RAPPORT_SECTION_IDENTIFICATION_1);
		addLabelKey(RAPPORT_SECTION_TABLE_DES_MATIERES_1);
		addLabelKey(RAPPORT_SECTION_INTRODUCTION_1);
		addLabelKey(RAPPORT_SECTION_ENQUETE_1);
		addLabelKey(RAPPORT_SECTION_ENQUETE_1_1);
		addLabelKey(RAPPORT_SECTION_ENQUETE_1_2);
		addLabelKey(RAPPORT_SECTION_ENQUETE_2);
		addLabelKey(RAPPORT_SECTION_ENQUETE_2_1);
		addLabelKey(RAPPORT_SECTION_ENQUETE_2_2);
		addLabelKey(RAPPORT_SECTION_ENQUETE_3);
		addLabelKey(RAPPORT_SECTION_ENQUETE_3_1);
		addLabelKey(RAPPORT_SECTION_ENQUETE_3_2);
		addLabelKey(RAPPORT_SECTION_ENQUETE_4);
		addLabelKey(RAPPORT_SECTION_ENQUETE_4_1);
		addLabelKey(RAPPORT_SECTION_ENQUETE_4_2);
		addLabelKey(RAPPORT_SECTION_ENQUETE_5);
		addLabelKey(RAPPORT_SECTION_CONSTAT_1);
		addLabelKey(RAPPORT_SECTION_CONCLUSION_1);
		addLabelKey(RAPPORT_SECTION_RECOMMANDATIONS_1);
		addLabelKey(RAPPORT_SECTION_AUTRES_1);
		addLabelKey(RAPPORT_SECTION_AUTRES_2);
		addLabelKey(RAPPORT_SECTION_AUTRES_3);
	}
}
