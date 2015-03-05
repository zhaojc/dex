<%@ taglib uri='/WEB-INF/struts-template.tld'  prefix='template' %>
<%@ taglib uri='/WEB-INF/struts-html.tld'  prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld'  prefix='bean' %>
<%@ taglib uri='/WEB-INF/cardex-html.tld'  prefix='cardex' %>
<%@ taglib uri='/WEB-INF/struts-tiles.tld'  prefix='tiles' %>
<%@ taglib uri='/WEB-INF/myriap-html.tld'  prefix='myriap' %>

<SCRIPT language="JavaScript" type="text/javascript">

function doClose() {
	window.close();
}
</SCRIPT>

<TABLE cellpadding="1" cellspacing="0" border="0">

  <TR>
    <TD CLASS="tabTitle">

    <TABLE  width="850" cellpadding="5" cellspacing="5" border="0" 
    style="filter:progid:DXImageTransform.Microsoft.Gradient(endColorstr='#cbd8e9', startColorstr='#FFFFFF', gradientType='0');">
      <TR>
      <TD ALIGN="center" COLSPAN="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR><!-- First row uses transparent pixel to force good alignment -->

   <tr >
       <TD VALIGN="top" nowrap="nowrap">
		<html:img page="/images/blank.gif" width="220" height="1" border="0" /><BR>
		<cardex:securityDefineTag nameDefine="sectionDossiers" urlSecurite="/rapport/dossiersContratsAutoexclusion.do,/rapport/dossiersAutoexclusionSommaireRencontresInitiales.do,/rapport/dossiersAutoexclusionDetailleRencontresInitiales.do,/rapport/dossiersAutoexclusionSommaireRencontresFinales.do,/rapport/dossiersAutoexclusionDetailleRencontresFinales.do,/rapport/detailVisitesClientsMysteres.do,/rapport/statistiqueVigilanceSommaire.do,/rapport/statistiqueVigilanceDetaille.do,/rapport/dossiersActifsParIntervenant.do,/rapport/dossiersHebdomadaireDefaut.do,/rapport/dossiersCumulatifDefaut.do,/rapport/dossiersAmbulanceDefaut.do,/rapport/rapportIncidentsDCSI.do,/rapport/rapportNombreRechercheDossiers.do,/rapport/nombreDossiersEnquetes.do,/rapport/facturationSQ.do,/rapport/facturationECHO.do,/rapport/facturationSOQUIJ.do,/rapport/dossiersCumulatifEspaceJeux.do,/rapport/dossiersHebdomadaireEspaceJeux.do,/rapport/dossiersEnqueteReclamation.do,/rapport/dossiersComparatifAnnuelEnquetes.do,/rapport/dossiersCumulatifHebdomadaireEnquetes.do,/rapport/dossiersRegistreRFCMaitre.do,/rapport/dossiersStatutDossier.do,/rapport/dossiersActifsParEnqueteurLQ.do,/rapport/dossiersPartagesParIntervenant.do,/rapport/dossiersPartagesParResponsable.do,/rapport/dossiersReperagesAutoexclusionNumero.do,/rapport/dossiersReperages5PlusAutoexclusionNumero.do,/rapport/dossiersReperagesAutoexclusionNombre.do,/rapport/dossiersReperages5PlusAutoexclusionNombre.do,/rapport/dossiersReperagesAccesInterditsNumero.do,/rapport/dossiersReperagesAccesInterditsNombre.do,/rapport/dossiersStatistiqueDossierCategories.do,/rapport/dossiersStatistiqueMoisGraphique.do,/rapport/dossiersStatistiqueTypeGraphique.do,/rapport/dossiersStatistiqueTempsConsacre.do">
            <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
                <TR>
                  <TD ALIGN="left" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/l_up_corner.gif" width="5" height="24" border="0" /></TD>
                  <TD CLASS="folderTitle"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
                    <bean:message key="tabpage_dossier"/>
                  </TD>
                  <TD ALIGN="right" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/r_up_corner.gif" width="5" height="24" border="0" /></TD>
                </TR>
                <TR>
                  <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
					<TD CLASS="mainCellDetail" valign="middle" nowrap="nowrap" width="500">
					<cardex:securityDefineTag nameDefine="sectionAcces" urlSecurite="/rapport/auditSommaireAcces.do,/rapport/auditDetailAcces.do,/rapport/auditIntervenantAcces.do,/rapport/auditNouveauxIntervenantsAcces.do,/rapport/auditSuperutilisateursAcces.do,/rapport/auditDetailAccesIntervenant.do,/rapport/auditAccesSujets.do,/rapport/auditAccesSujetsIntervenant.do,/rapport/auditAnalyseAccesDossiers.do,/rapport/auditAccesEmploye.do,/rapport/auditAccesFournisseur.do,/rapport/auditAccesNarration.do">
						<fieldset>
						<legend><bean:message key="rapports_acces"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="auditSommaireAcces" urlSecurite="/rapport/auditSommaireAcces.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditSommaireAcces.do?actionMethod=defaut" ><bean:message key="acces_sommaire"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditDetailAcces" urlSecurite="/rapport/auditDetailAcces.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditDetailAcces.do?actionMethod=defaut" ><bean:message key="acces_detail"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditIntervenantAcces" urlSecurite="/rapport/auditIntervenantAcces.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditIntervenantAcces.do?actionMethod=defaut" ><bean:message key="acces_intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditNouveauxIntervenantsAcces" urlSecurite="/rapport/auditNouveauxIntervenantsAcces.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditNouveauxIntervenantsAcces.do?actionMethod=defaut" ><bean:message key="acces_nouveaux_intervenants"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditSuperutilisateursAcces" urlSecurite="/rapport/auditSuperutilisateursAcces.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditSuperutilisateursAcces.do?actionMethod=defaut" ><bean:message key="acces_superutilisateurs"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditDetailIntervenantAcces" urlSecurite="/rapport/auditDetailAccesIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditDetailAccesIntervenant.do?actionMethod=defaut" ><bean:message key="acces_detail_intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditAccesSujets" urlSecurite="/rapport/auditAccesSujets.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditAccesSujets.do?actionMethod=defaut" ><bean:message key="acces_consultation_sujets"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditAccesSujetsIntervenant" urlSecurite="/rapport/auditAccesSujetsIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditAccesSujetsIntervenant.do?actionMethod=defaut" ><bean:message key="acces_consultation_sujets_intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditAnalyseAccesDossiers" urlSecurite="/rapport/auditAnalyseAccesDossiers.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditAnalyseAccesDossiers.do?actionMethod=defaut" ><bean:message key="access_dossiers"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditAccesEmploye" urlSecurite="/rapport/auditAccesEmploye.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditAccesEmploye.do?actionMethod=defaut" ><bean:message key="acces_sujet_employe"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditAccesFournisseur" urlSecurite="/rapport/auditAccesFournisseur.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditAccesFournisseur.do?actionMethod=defaut" ><bean:message key="acces_sujet_fournisseur"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="auditAccesNarration" urlSecurite="/rapport/auditAccesNarration.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/auditAccesNarration.do?actionMethod=defaut" ><bean:message key="acces_narration"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					<cardex:securityDefineTag nameDefine="sectionDossiersAutoexclusion" urlSecurite="/rapport/dossiersContratsAutoexclusion.do,/rapport/dossiersAutoexclusionSommaireRencontresInitiales.do,/rapport/dossiersAutoexclusionDetailleRencontresInitiales.do,/rapport/dossiersAutoexclusionSommaireRencontresFinales.do,/rapport/dossiersAutoexclusionDetailleRencontresFinales.do">
						<fieldset>
						<legend><bean:message key="autoexclusion"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersContratsAutoexclusion" urlSecurite="/rapport/dossiersContratsAutoexclusion.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersContratsAutoexclusion.do?actionMethod=defaut" ><bean:message key="rapport.des.contrats.autoexclusion"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersAutoexclusionSommaireRencontresInitiales" urlSecurite="/rapport/dossiersAutoexclusionSommaireRencontresInitiales.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAutoexclusionSommaireRencontresInitiales.do?actionMethod=defaut" ><bean:message key="rapport.autoexclusion.sommaire.rencontres.initiales"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>					
						<cardex:securityDefineTag nameDefine="dossiersAutoexclusionDetailleRencontresInitiales" urlSecurite="/rapport/dossiersAutoexclusionDetailleRencontresInitiales.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAutoexclusionDetailleRencontresInitiales.do?actionMethod=defaut" ><bean:message key="rapport.autoexclusion.detaille.rencontres.initiales"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersAutoexclusionSommaireRencontresFinales" urlSecurite="/rapport/dossiersAutoexclusionSommaireRencontresFinales.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAutoexclusionSommaireRencontresFinales.do?actionMethod=defaut" ><bean:message key="rapport.autoexclusion.sommaire.rencontres.finales"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>					
						<cardex:securityDefineTag nameDefine="dossiersAutoexclusionDetailleRencontresFinales" urlSecurite="/rapport/dossiersAutoexclusionDetailleRencontresFinales.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAutoexclusionDetailleRencontresFinales.do?actionMethod=defaut" ><bean:message key="rapport.autoexclusion.detaille.rencontres.finales"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>							
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionClientMystere" urlSecurite="/rapport/detailVisitesClientsMysteres.do,/rapport/detailVisitesAVenirClientsMysteres.do,/rapport/detaillantsFautifsClientsMysteres.do,/rapport/visitesRegionAdministrative.do,/rapport/visitesCentreRegional.do,/rapport/clientsMysteresFichierMaitre.do">
						<fieldset>
						<legend><bean:message key="client.mystere"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="detailVisites" urlSecurite="/rapport/detailVisitesClientsMysteres.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/detailVisitesClientsMysteres.do?actionMethod=defaut" ><bean:message key="rapport.detail.visites"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="detailVisitesAVenir" urlSecurite="/rapport/detailVisitesAVenirClientsMysteres.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/detailVisitesAVenirClientsMysteres.do?actionMethod=defaut" ><bean:message key="rapport.detail.visites.a.venir"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="detaillantsFautifs" urlSecurite="/rapport/detaillantsFautifsClientsMysteres.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/detaillantsFautifsClientsMysteres.do?actionMethod=defaut" ><bean:message key="rapport.detaillants.fautifs"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="visitesRegion" urlSecurite="/rapport/visitesRegionAdministrative.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/visitesRegionAdministrative.do?actionMethod=defaut" ><bean:message key="rapport.visites.region"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="visitesCentre" urlSecurite="/rapport/visitesCentreRegional.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/visitesCentreRegional.do?actionMethod=defaut" ><bean:message key="rapport.visites.centre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="fichierMaitre" urlSecurite="/rapport/clientsMysteresFichierMaitre.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/clientsMysteresFichierMaitre.do?actionMethod=defaut" ><bean:message key="fichier.maitre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="societesInactives" urlSecurite="/rapport/societesInactives.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/societesInactives.do?actionMethod=defaut" ><bean:message key="societes.inactives"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionComiteVigilance" urlSecurite="/rapport/statistiqueVigilanceSommaire.do,/rapport/statistiqueVigilanceDetaille.do">
						<fieldset>
						<legend><bean:message key="comite.vigilance"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="statistiqueVigilanceSommaire" urlSecurite="/rapport/statistiqueVigilanceSommaire.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/statistiqueVigilanceSommaire.do?actionMethod=defaut" ><bean:message key="rapport.statistique.vigilance.sommaire"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="statistiqueVigilanceDetaille" urlSecurite="/rapport/statistiqueVigilanceDetaille.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/statistiqueVigilanceDetaille.do?actionMethod=defaut" ><bean:message key="rapport.statistique.vigilance.detaille"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionDivers" urlSecurite="/rapport/dossiersActifsParIntervenant.do,/rapport/dossiersHebdomadaireDefaut.do,/rapport/dossiersCumulatifDefaut.do,/rapport/dossiersAmbulanceDefaut.do,/rapport/rapportIncidentsDCSI.do,/rapport/rapportNombreRechercheDossiers.do,/rapport/reconnaissancePlaque.do,/rapport/employeDossier.do">
						<fieldset>
						<legend><bean:message key="divers"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersActifsParIntervenant" urlSecurite="/rapport/dossiersActifsParIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersActifsParIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.dossiers.actifs.par.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersHebdomadaire" urlSecurite="/rapport/dossiersHebdomadaireDefaut.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersHebdomadaireDefaut.do" ><bean:message key="rapport.hebdomadaire.dossiers"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersCumulatif" urlSecurite="/rapport/dossiersCumulatifDefaut.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersCumulatifDefaut.do" ><bean:message key="rapport.cumulatif.dossiers"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersAmbulance" urlSecurite="/rapport/dossiersAmbulanceDefaut.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAmbulanceDefaut.do" ><bean:message key="rapport.ambulances"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="rapportIncidentsDCSI" urlSecurite="/rapport/rapportIncidentsDCSI.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/rapportIncidentsDCSI.do?actionMethod=defaut" ><bean:message key="rapport.incidents.dcsi"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="rapportSommaire.nombre.dossiers" urlSecurite="/rapport/rapportNombreRechercheDossiers.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/rapportNombreRechercheDossiers.do?actionMethod=defaut" ><bean:message key="rapport.sommaire.nombre.dossiers"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="rapport.reconnaissance.plaque" urlSecurite="/rapport/reconnaissancePlaque.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/reconnaissancePlaque.do?actionMethod=defaut" ><bean:message key="rapport.reconnaissance.plaque"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="rapport.employe.dossier" urlSecurite="/rapport/employeDossier.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/employeDossier.do?actionMethod=defaut" ><bean:message key="rapport.employe.dossier"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
						
					<cardex:securityDefineTag nameDefine="sectionEspaceJeux" urlSecurite="/rapport/dossiersCumulatifEspaceJeux.do,/rapport/dossiersHebdomadaireEspaceJeux.do,/rapport/dossiersEspaceJeuxAutoexclusionActif.do,/rapport/dossiersEspaceJeuxFraudeFonde.do,/rapport/dossiersEspaceJeuxTricherieFonde.do">
						<fieldset>
						<legend><bean:message key="espace.jeux"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersCumulatifEspaceJeux" urlSecurite="/rapport/dossiersCumulatifEspaceJeux.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersCumulatifEspaceJeux.do?actionMethod=defaut" ><bean:message key="rapport.cumulatif.dossiers.espacejeux"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersHebdomadaireEspaceJeux" urlSecurite="/rapport/dossiersHebdomadaireEspaceJeux.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersHebdomadaireEspaceJeux.do?actionMethod=defaut" ><bean:message key="rapport.hebdomadaire.dossiers.espacejeux"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersAutoexclusionActifEspaceJeux" urlSecurite="/rapport/dossiersEspaceJeuxAutoexclusionActif.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersEspaceJeuxAutoexclusionActif.do?actionMethod=defaut" ><bean:message key="rapport.espacejeux.autoexclusion.actif"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersFraudeFondeEspaceJeux" urlSecurite="/rapport/dossiersEspaceJeuxFraudeFonde.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersEspaceJeuxFraudeFonde.do?actionMethod=defaut" ><bean:message key="rapport.espacejeux.fraude.fonde"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersTricherieFondeEspaceJeux" urlSecurite="/rapport/dossiersEspaceJeuxTricherieFonde.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersEspaceJeuxTricherieFonde.do?actionMethod=defaut" ><bean:message key="rapport.espacejeux.tricherie.fonde"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionHabilitationSecuritaire" urlSecurite="/rapport/nombreDossiersEnquetes.do,/rapport/facturationSQ.do,/rapport/facturationECHO.do,/rapport/facturationSOQUIJ.do,/rapport/sujetsSeverite4.do,/rapport/societesSeverite4.do,/rapport/delaiTraitementEnquetes.do,/rapport/enqueteEnRetard.do,/rapport/enqueteTraitementEnRetard.do">
						<fieldset>
						<legend><bean:message key="habilitation.securitaire"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="nombreDossiersEnquete" urlSecurite="/rapport/nombreDossiersEnquetes.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/nombreDossiersEnquetes.do?actionMethod=defaut" ><bean:message key="rapport.nombre.enquetes.investigation"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="facturationSQ" urlSecurite="/rapport/facturationSQ.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/facturationSQ.do?actionMethod=defaut" ><bean:message key="rapport.facturation.sq"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="facturationECHO" urlSecurite="/rapport/facturationECHO.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/facturationECHO.do?actionMethod=defaut" ><bean:message key="rapport.facturation.echo"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="facturationSOQUIJ" urlSecurite="/rapport/facturationSOQUIJ.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/facturationSOQUIJ.do?actionMethod=defaut" ><bean:message key="rapport.facturation.soquij"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="sujetsSeverite4" urlSecurite="/rapport/sujetsSeverite4.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/sujetsSeverite4.do?actionMethod=defaut" ><bean:message key="rapport.severite.sujets.4"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="societesSeverite4" urlSecurite="/rapport/societesSeverite4.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/societesSeverite4.do?actionMethod=defaut" ><bean:message key="rapport.severite.societes.4"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="delaiTraitementEnquetes" urlSecurite="/rapport/delaiTraitementEnquetes.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/delaiTraitementEnquetes.do?actionMethod=defaut" ><bean:message key="rapport.delai.traitement.enquetes"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="enqueteEnRetard" urlSecurite="/rapport/enqueteEnRetard.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/enqueteEnRetard.do?actionMethod=defaut" ><bean:message key="rapport.enquete.en.retard"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="enqueteTraitementEnRetard" urlSecurite="/rapport/enqueteTraitementEnRetard.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/enqueteTraitementEnRetard.do?actionMethod=defaut" ><bean:message key="rapport.enquete.traitement.en.retard"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>

					<cardex:securityDefineTag nameDefine="sectionLoteries" urlSecurite="/rapport/dossiersEnqueteReclamation.do,/rapport/dossiersComparatifAnnuelEnquetes.do,/rapport/dossiersCumulatifHebdomadaireEnquetes.do,/rapport/dossiersRegistreRFCMaitre.do,/rapport/dossiersStatutDossier.do,/rapport/dossiersActifsParEnqueteurLQ.do">
						<fieldset>
						<legend><bean:message key="loteries"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersEnqueteReclamation" urlSecurite="/rapport/dossiersEnqueteReclamation.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersEnqueteReclamation.do?actionMethod=defaut" ><bean:message key="rapport.enquete.reclamations"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersComparatifAnnuelEnquetes" urlSecurite="/rapport/dossiersComparatifAnnuelEnquetes.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersComparatifAnnuelEnquetes.do?actionMethod=defaut" ><bean:message key="rapport.comparatif.annuel.des.enquetes"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersCumulatifHebdomadaireEnquetes" urlSecurite="/rapport/dossiersCumulatifHebdomadaireEnquetes.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersCumulatifHebdomadaireEnquetes.do?actionMethod=defaut" ><bean:message key="rapport.cumulatif.hebdomadaire.des.enquetes"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersRegistreRFCMaitre" urlSecurite="/rapport/dossiersRegistreRFCMaitre.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersRegistreRFCMaitre.do?actionMethod=defaut" ><bean:message key="rapport.registre.des.rfc.maitre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersStatutDossier" urlSecurite="/rapport/dossiersStatutDossier.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersStatutDossier.do?actionMethod=defaut" ><bean:message key="rapport.statut.des.dossiers"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersActifsEnqueteur" urlSecurite="/rapport/dossiersActifsParEnqueteurLQ.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersActifsParEnqueteurLQ.do?actionMethod=defaut" ><bean:message key="rapport.dossiers.actifs.par.enqueteur.lq"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionPartage" urlSecurite="/rapport/dossiersPartagesParIntervenant.do,/rapport/dossiersPartagesParResponsable.do">
						<fieldset>
						<legend><bean:message key="partage"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersPartagesParIntervenant" urlSecurite="/rapport/dossiersPartagesParIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersPartagesParIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.dossiers.partages.par.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersPartagesParResponsable" urlSecurite="/rapport/dossiersPartagesParResponsable.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersPartagesParResponsable.do?actionMethod=defaut" ><bean:message key="rapport.dossiers.partages.par.responsable"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>	
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionReperage" urlSecurite="/rapport/dossiersReperagesAutoexclusionNumero.do,/rapport/dossiersReperages5PlusAutoexclusionNumero.do,/rapport/dossiersReperagesAutoexclusionNombre.do,/rapport/dossiersReperages5PlusAutoexclusionNombre.do,/rapport/dossiersReperagesAccesInterditsNumero.do,/rapport/dossiersReperagesAccesInterditsNombre">
						<fieldset>
						<legend><bean:message key="Reperage"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersReperagesAutoexclusionNumero" urlSecurite="/rapport/dossiersReperagesAutoexclusionNumero.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersReperagesAutoexclusionNumero.do?actionMethod=defaut" ><bean:message key="rapport.reperages.des.autoexclusions.par.numero"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersReperages5PlusAutoexclusionNumero" urlSecurite="/rapport/dossiersReperages5PlusAutoexclusionNumero.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersReperages5PlusAutoexclusionNumero.do?actionMethod=defaut" ><bean:message key="rapport.reperages.5.plus.autoexclusions.par.numero"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersReperagesAutoexclusionNombre" urlSecurite="/rapport/dossiersReperagesAutoexclusionNombre.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersReperagesAutoexclusionNombre.do?actionMethod=defaut" ><bean:message key="rapport.reperages.des.autoexclusions.par.nombre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>					
						<cardex:securityDefineTag nameDefine="dossiersReperages5PlusAutoexclusionNombre" urlSecurite="/rapport/dossiersReperages5PlusAutoexclusionNombre.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersReperages5PlusAutoexclusionNombre.do?actionMethod=defaut" ><bean:message key="rapport.reperages.5.plus.autoexclusions.par.nombre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersReperagesAccesInterditNumero" urlSecurite="/rapport/dossiersReperagesAccesInterditsNumero.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersReperagesAccesInterditsNumero.do?actionMethod=defaut" ><bean:message key="rapport.reperages.des.acces.interdits.tries.par.numero"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>					
						<cardex:securityDefineTag nameDefine="dossiersReperagesAccesInterditNombre" urlSecurite="/rapport/dossiersReperagesAccesInterditsNombre.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersReperagesAccesInterditsNombre.do?actionMethod=defaut" ><bean:message key="rapport.reperages.des.acces.interdits.tries.par.nombre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>
					</cardex:securityDefineTag>
					
					<cardex:securityDefineTag nameDefine="sectionStatistiques" urlSecurite="/rapport/dossiersStatistiqueDossierCategories.do,/rapport/dossiersStatistiqueMoisGraphique.do,/rapport/dossiersStatistiqueTypeGraphique.do,/rapport/dossiersStatistiqueTempsConsacre.do,/rapport/statistiqueEndroitsRegroupes.do,/rapport/tableauEndroitsRegroupes.do">
						<fieldset>
						<legend><bean:message key="statistiques"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersStatistiqueDossierCategories" urlSecurite="/rapport/dossiersStatistiqueDossierCategories.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersStatistiqueDossierCategories.do?actionMethod=defaut" ><bean:message key="rapport.statistique.des.dossiers.par.categories"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersStatistiqueDossierMoisGraphique" urlSecurite="/rapport/dossiersStatistiqueMoisGraphique.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersStatistiqueMoisGraphique.do?actionMethod=defaut" ><bean:message key="rapport.statistique.des.dossiers.par.mois"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersStatistiqueDossierTypeGraphique" urlSecurite="/rapport/dossiersStatistiqueTypeGraphique.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersStatistiqueTypeGraphique.do?actionMethod=defaut" ><bean:message key="rapport.statistique.des.dossiers.par.types"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersStatistiqueDossierTempsConsacre" urlSecurite="/rapport/dossiersStatistiqueTempsConsacre.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersStatistiqueTempsConsacre.do?actionMethod=defaut" ><bean:message key="rapport.statistique.des.dossiers.temps.consacre"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="statistique.endroits.regroupes" urlSecurite="/rapport/statistiqueEndroitsRegroupes.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/statistiqueEndroitsRegroupes.do?actionMethod=defaut" ><bean:message key="statistique.endroits.regroupes"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="tableau.endroits.regroupes" urlSecurite="/rapport/tableauEndroitsRegroupes.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/tableauEndroitsRegroupes.do?actionMethod=defaut" ><bean:message key="tableau.endroits.regroupes"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>	
					</cardex:securityDefineTag>

					<cardex:securityDefineTag nameDefine="sectionServiceUrgence" urlSecurite="/rapport/dossiersAmbulanceSommaire,/rapport/dossiersAmbulanceDetail">
						<fieldset>
						<legend><bean:message key="urgence"/></legend>
						<UL compact>
						<cardex:securityDefineTag nameDefine="dossiersAmbulanceSommaire" urlSecurite="/rapport/dossiersAmbulanceSommaire.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAmbulanceSommaire.do?actionMethod=defaut" ><bean:message key="ambulance.sommaire"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="dossiersAmbulanceDetail" urlSecurite="/rapport/dossiersAmbulanceDetail.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/dossiersAmbulanceDetail.do?actionMethod=defaut" ><bean:message key="ambulance.detail"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</fieldset>	
					</cardex:securityDefineTag>

					</TD>
					<TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
					<TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                </TR>
                <TR>
                        <TD COLSPAN="5" CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                </TR>
	          </TABLE>
          </cardex:securityDefineTag>
        </TD>
       <TD VALIGN="top" nowrap="nowrap" >
		<html:img page="/images/blank.gif" width="220" height="1" border="0" /><BR>
			<cardex:securityDefineTag nameDefine="sectionRegroupement" urlSecurite="/rapport/regroupementGlobal.do,/rapport/regroupementEndroit.do,/rapport/regroupementIntervenantCategorie.do,/rapport/regroupementIntervenantEndroit.do,/rapport/regroupementIntervenant.do,/rapport/regroupementGlobalTotalHeures.do,/rapport/regroupementTendanceMois.do,/rapport/regroupementGlobalCasino.do,/rapport/regroupementMatrice.do">
	            <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
	                <TR>
	                  <TD ALIGN="left" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/l_up_corner.gif" width="5" height="24" border="0" /></TD>
	                  <TD CLASS="folderTitle"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
	                    <bean:message key="tabpage_regroupement"/>
	                  </TD>
	                  <TD ALIGN="right" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/r_up_corner.gif" width="5" height="24" border="0" /></TD>
	                </TR>
	                <TR>
	                  <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
						<TD CLASS="mainCellDetail" valign="middle" nowrap="nowrap" width="525">
						<BR>
						<UL compact>
						<cardex:securityDefineTag nameDefine="globalRegroupement" urlSecurite="/rapport/regroupementGlobal.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementGlobal.do?actionMethod=defaut" ><bean:message key="rapport.global.regroupement.heures.travaillees"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="endroitRegroupement" urlSecurite="/rapport/regroupementEndroit.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementEndroit.do?actionMethod=defaut" ><bean:message key="rapport.regroupement.endroit"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="intervenantCategorie" urlSecurite="/rapport/regroupementIntervenantCategorie.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementIntervenantCategorie.do?actionMethod=defaut" ><bean:message key="rapport.regroupement.intervenant.categorie"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="intervenantEndroitRegroupement" urlSecurite="/rapport/regroupementIntervenantEndroit.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementIntervenantEndroit.do?actionMethod=defaut" ><bean:message key="rapport.regroupement.intervenant.endroit"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="intervenantRegroupement" urlSecurite="/rapport/regroupementIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.regroupement.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="globalTotalHeuresRegroupement" urlSecurite="/rapport/regroupementGlobalTotalHeures.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementGlobalTotalHeures.do?actionMethod=defaut" ><bean:message key="rapport.global.regroupement.total.heures"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="tendanceMoisRegroupement" urlSecurite="/rapport/regroupementTendanceMois.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementTendanceMois.do?actionMethod=defaut" ><bean:message key="rapport.mois.tendance.regroupements"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="globalCasinoRegroupement" urlSecurite="/rapport/regroupementGlobalCasino.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementGlobalCasino.do?actionMethod=defaut" ><bean:message key="rapport.global.regroupement.Casinos"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="matriceRegroupement" urlSecurite="/rapport/regroupementMatrice.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/regroupementMatrice.do?actionMethod=defaut" ><bean:message key="rapport.matrice.regroupements"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</TD>
						<TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
						<TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                </TR>
	                <TR>
	                        <TD COLSPAN="5" CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                </TR>
	          </TABLE>
	          <br/>
			</cardex:securityDefineTag>	          
          
          	<cardex:securityDefineTag nameDefine="sectionSuivi" urlSecurite="/rapport/suivi30Jours.do,/rapport/suivi24HeuresIntervenant.do,/rapport/suivi21JoursIntervenant.do,/rapport/suivi30JoursIntervenant.do,/rapport/suiviSiteIntervenant.do,/rapport/suiviRetardIntervenant.do,/rapport/rapportSuivis.do">
	            <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
	                <TR>
	                  <TD ALIGN="left" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/l_up_corner.gif" width="5" height="24" border="0" /></TD>
	                  <TD CLASS="folderTitle"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
	                    <bean:message key="Suivis"/>
	                  </TD>
	                  <TD ALIGN="right" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/r_up_corner.gif" width="5" height="24" border="0" /></TD>
	                </TR>
	                <TR>
	                  <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
						<TD CLASS="mainCellDetail" valign="middle"  nowrap="nowrap" width="525">
						<BR>
						<UL compact>
						<cardex:securityDefineTag nameDefine="suivi30Jours" urlSecurite="/rapport/suivi30Jours.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/suivi30Jours.do?actionMethod=defaut" ><bean:message key="rapport.suivis.30.jours"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="suivi24HeuresIntervenant" urlSecurite="/rapport/suivi24HeuresIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/suivi24HeuresIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.suivis.24.heures.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="suivi21JoursIntervenant" urlSecurite="/rapport/suivi21JoursIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/suivi21JoursIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.suivis.21.jours.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="suivi30JoursIntervenant" urlSecurite="/rapport/suivi30JoursIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/suivi30JoursIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.suivis.30.jours.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="suiviSiteIntervenant" urlSecurite="/rapport/suiviSiteIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/suiviSiteIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.suivis.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="suiviRetardIntervenant" urlSecurite="/rapport/suiviRetardIntervenant.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/suiviRetardIntervenant.do?actionMethod=defaut" ><bean:message key="rapport.suivis.retard.intervenant"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="rapportSuivis" urlSecurite="/rapport/rapportSuivis.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/rapportSuivis.do?actionMethod=defaut" ><bean:message key="rapport.suivis"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						</UL>
						</TD>
						<TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
						<TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                </TR>
	                <TR>
	                        <TD COLSPAN="5" CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                </TR>
	          </TABLE>
	          <br/>
			</cardex:securityDefineTag>
          
          	<cardex:securityDefineTag nameDefine="sectionJournal" urlSecurite="/rapport/journalSommaire.do,/rapport/journalDetaille.do,/rapport/journalOrigine.do,/rapport/journalDescriptif.do,/rapport/journalEnqueteur.do">
	            <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
	                <TR>
	                  <TD ALIGN="left" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/l_up_corner.gif" width="5" height="24" border="0" /></TD>
	                  <TD CLASS="folderTitle"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
	                    <bean:message key="journal"/>
	                  </TD>
	                  <TD ALIGN="right" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/r_up_corner.gif" width="5" height="24" border="0" /></TD>
	                </TR>
	                <TR>
	                  <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
						<TD CLASS="mainCellDetail" valign="middle"  nowrap="nowrap" width="525">
						<BR>
						<UL compact>
							<cardex:securityDefineTag nameDefine="journalSommaire" urlSecurite="/rapport/journalSommaire.do">
								<LI><cardex:linkOpenWindowObject page="/rapport/journalSommaire.do?actionMethod=defaut" ><bean:message key="rapport.journal.sommaire"/></cardex:linkOpenWindowObject></LI>
							</cardex:securityDefineTag>
							<cardex:securityDefineTag nameDefine="journalDetaillle" urlSecurite="/rapport/journalDetaille.do">
								<LI><cardex:linkOpenWindowObject page="/rapport/journalDetaille.do?actionMethod=defaut" ><bean:message key="rapport.journal.detaille"/></cardex:linkOpenWindowObject></LI>
							</cardex:securityDefineTag>
							<cardex:securityDefineTag nameDefine="journalOrigines" urlSecurite="/rapport/journalOrigine.do">
								<LI><cardex:linkOpenWindowObject page="/rapport/journalOrigine.do?actionMethod=defaut" ><bean:message key="rapport.journal.origines"/></cardex:linkOpenWindowObject></LI>
							</cardex:securityDefineTag>
							<cardex:securityDefineTag nameDefine="journalDescriptif" urlSecurite="/rapport/journalDescriptif.do">
								<LI><cardex:linkOpenWindowObject page="/rapport/journalDescriptif.do?actionMethod=defaut" ><bean:message key="rapport.journal.descriptifs"/></cardex:linkOpenWindowObject></LI>
							</cardex:securityDefineTag>
							<cardex:securityDefineTag nameDefine="journalEnqueteur" urlSecurite="/rapport/journalEnqueteur.do">
								<LI><cardex:linkOpenWindowObject page="/rapport/journalEnqueteur.do?actionMethod=defaut" ><bean:message key="journal.enqueteurs"/></cardex:linkOpenWindowObject></LI>
							</cardex:securityDefineTag>
						</UL>
						</TD>
						<TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
						<TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                </TR>
	                <TR>
	                        <TD COLSPAN="5" CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
	                </TR>
	          </TABLE>
	          <br/>
	      </cardex:securityDefineTag>
          
          <cardex:securityDefineTag nameDefine="sectionConsignation" urlSecurite="/rapport/sommaireConsignationRapport.do,/rapport/detailleConsignationRapport.do,/rapport/denominationConsignationRapport.do,/rapport/numeroSerieDenominationConsignationRapport.do,/rapport/denominationNumeroSerieConsignationRapport.do">
            <TABLE width="220" CELLSPACING="0" CELLPADDING="0" border="0" >
                <TR>
                  <TD ALIGN="left" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/l_up_corner.gif" width="5" height="24" border="0" /></TD>
                  <TD CLASS="folderTitle"><html:img page="/images/blank.gif" width="208" height="1" border="0" /><BR>
                    <bean:message key="consignation_t"/>
                  </TD>
                  <TD ALIGN="right" COLSPAN="2" CLASS="folderTitle"><html:img page="/images/r_up_corner.gif" width="5" height="24" border="0" /></TD>
                </TR>
                <TR>
                  <TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                  <TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
					<TD CLASS="mainCellDetail" valign="middle"  nowrap="nowrap" width="525">
					<BR>
					<UL compact>
						<cardex:securityDefineTag nameDefine="sommaireConsignation" urlSecurite="/rapport/sommaireConsignationRapport.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/sommaireConsignationRapport.do?actionMethod=defaut" ><bean:message key="rapport.sommaire.consignations"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="detailleConsignation" urlSecurite="/rapport/detailleConsignationRapport.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/detailleConsignationRapport.do?actionMethod=defaut" ><bean:message key="rapport.detaille.consignations"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>						
						<cardex:securityDefineTag nameDefine="denominationConsignation" urlSecurite="/rapport/denominationConsignationRapport.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/denominationConsignationRapport.do?actionMethod=defaut" ><bean:message key="rapport.denomination"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>						
						<cardex:securityDefineTag nameDefine="numeroSerieDenominationConsignation" urlSecurite="/rapport/numeroSerieDenominationConsignationRapport.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/numeroSerieDenominationConsignationRapport.do?actionMethod=defaut" ><bean:message key="rapport.numero.serie.denomination"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>						
						<cardex:securityDefineTag nameDefine="denominationNumeroSerieConsignation" urlSecurite="/rapport/denominationNumeroSerieConsignationRapport.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/denominationNumeroSerieConsignationRapport.do?actionMethod=defaut" ><bean:message key="rapport.denomination.numero.serie"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
						<cardex:securityDefineTag nameDefine="echangesCasinoClientConsignation" urlSecurite="/rapport/echangesCasinoClientConsignationRapport.do">
							<LI><cardex:linkOpenWindowObject page="/rapport/echangesCasinoClientConsignationRapport.do?actionMethod=defaut" ><bean:message key="rapport.echanges.casino.client"/></cardex:linkOpenWindowObject></LI>
						</cardex:securityDefineTag>
					</UL>
					</TD>
					<TD><html:img page="/images/blank.gif" width="5" height="1" border="0" /></TD>
					<TD CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                </TR>
                <TR>
                        <TD COLSPAN="5" CLASS="folderFrameGallery"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
                </TR>
           </TABLE>
          </cardex:securityDefineTag>
          <!-- End gallery -->
    </TD>        
        
   </tr>   
     
 
    <TR>
      <TD WIDTH="1100" ALIGN="center" COLSPAN="6" HEIGHT="15"><html:img page="/images/0061CFpixel.gif" width="1100" height="1" border="0" /></TD>
    </TR>
    <TR>
      <TD WIDTH="1100" ALIGN="center" COLSPAN="6">

      <!-- eSort by options for Kind & nature listing -->
      <TABLE width="100%" align="left" cellPadding="2" cellSpacing="0" border="0">
		<tr>
	      <TD align="right">
	          <cardex:button labelKey='cb_fermer'  onclick='doClose();' />
	     </TD>	
		
	   </TR>

      </TABLE>
      <!-- End Sort by -->

      </TD>
    </TR>

    <TR>
      <TD ALIGN="center" COLSPAN="6"><html:img page="/images/blank.gif" width="1" height="1" border="0" /></TD>
    </TR>
    </TABLE>
    <!-- End Kind & nature search fields -->

  </TD>
  </TR>
</TABLE>
