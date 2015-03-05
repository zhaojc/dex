package com.lotoquebec.cardex.business.facade.rapport;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.RapportClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.SectionSocieteVO_CDX_0255;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.DateUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class ClientMystereCDX_0255RapportCardex extends RapportCardex{

	private final static Logger log = (Logger)LoggerCardex.getLogger(ClientMystereCDX_0255RapportCardex.class);
	protected Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap = new HashMap<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255>();
	private Set<Dossier> echantillonDossierClientMystereActif;
	
	public ClientMystereCDX_0255RapportCardex(CardexAuthenticationSubject subject) {
		super(subject);
	}

	@Override
	protected Collection construireDonneeRapport(CardexAuthenticationSubject subject) throws DAOException {
        chargerSocieteMystere();
        return convertir( societeClientMystereMap.values() );
	}

	protected void chargerSocieteMystere() throws DAOException {
		chargerDossierEchantillonActif();
        chargerSocieteClientMystere();
        chargerSocieteResponsableSujet();
        chargerSocieteResponsableSociete();
	}
	
	protected Collection convertir(Collection<ClientMystereVO_CDX_0255> clientMystereVOs){
		log.info("convertir");
		List<RapportClientMystereVO_CDX_0255> rapportClientMysteres = new ArrayList<RapportClientMystereVO_CDX_0255>();
		
		for(ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255:clientMystereVOs){
			
			for(SectionSocieteVO_CDX_0255 sectionSocieteCDX0255VO:clientMystereVO_CDX_0255.getSectionSocieteCDX255VOs()){
				RapportClientMystereVO_CDX_0255 rapportClientMystereVO_CDX_0255 = new RapportClientMystereVO_CDX_0255();
				
				rapportClientMystereVO_CDX_0255.setDerniereMAJDate(DateUtils.defaultString(sectionSocieteCDX0255VO.getDerniereMAJDate()));
				rapportClientMystereVO_CDX_0255.setDistrict(StringUtils.defaultString(clientMystereVO_CDX_0255.getDistrict()));
				rapportClientMystereVO_CDX_0255.setRegionAdministrative(StringUtils.defaultString(clientMystereVO_CDX_0255.getRegionAdministrative()));
				rapportClientMystereVO_CDX_0255.setNumero(StringUtils.defaultString(clientMystereVO_CDX_0255.getNumero()));
				rapportClientMystereVO_CDX_0255.setNom(StringUtils.defaultString(clientMystereVO_CDX_0255.getNom()));
				rapportClientMystereVO_CDX_0255.setAdresse(StringUtils.defaultString(clientMystereVO_CDX_0255.getAdresse()));
				rapportClientMystereVO_CDX_0255.setVille(StringUtils.defaultString(clientMystereVO_CDX_0255.getVille()));
				rapportClientMystereVO_CDX_0255.setCodePostal(StringUtils.defaultString(clientMystereVO_CDX_0255.getCodePostal()));
				rapportClientMystereVO_CDX_0255.setCentreRegional(StringUtils.defaultString(clientMystereVO_CDX_0255.getCentreRegional()));
				rapportClientMystereVO_CDX_0255.setTelephone(StringUtils.defaultString(clientMystereVO_CDX_0255.getTelephone()));
				rapportClientMystereVO_CDX_0255.setLangue(StringUtils.defaultString(clientMystereVO_CDX_0255.getLangue()));
				rapportClientMystereVO_CDX_0255.setCodeCommerce(StringUtils.defaultString(clientMystereVO_CDX_0255.getCodeCommerce()));
				rapportClientMystereVO_CDX_0255.setNomCompte(StringUtils.defaultString(clientMystereVO_CDX_0255.getNomCompte()));
				
				if (clientMystereVO_CDX_0255.getResponsableSociete() != null)
					rapportClientMystereVO_CDX_0255.setResponsable(StringUtils.defaultString(clientMystereVO_CDX_0255.getResponsableSociete().getAffichageResponsable()));
				
				// Visite 1
				rapportClientMystereVO_CDX_0255.setVisiteDate1(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getVisiteDate()));
				rapportClientMystereVO_CDX_0255.setHeure1(sectionSocieteCDX0255VO.getVisites()[0].getHeureVisiteDate());
				rapportClientMystereVO_CDX_0255.setCarte1(sectionSocieteCDX0255VO.getVisites()[0].getCarte());
				rapportClientMystereVO_CDX_0255.setNumeroBillet1(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getNumeroBillet()));
				
				if (sectionSocieteCDX0255VO.getVisites()[0].isCarte() == false){
					rapportClientMystereVO_CDX_0255.setEnvoieAvisDate1(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getEnvoieAvisDate()));
					rapportClientMystereVO_CDX_0255.setReceptionAvisDate1(sectionSocieteCDX0255VO.getVisites()[0].getReceptionAvisDate());
				}
				rapportClientMystereVO_CDX_0255.setVisiteConfirme1(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getVisiteConfirme()));
				
				// Visite 1
				rapportClientMystereVO_CDX_0255.setVisiteDate1(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getVisiteDate()));
				rapportClientMystereVO_CDX_0255.setHeure1(sectionSocieteCDX0255VO.getVisites()[0].getHeureVisiteDate());
				rapportClientMystereVO_CDX_0255.setCarte1(sectionSocieteCDX0255VO.getVisites()[0].getCarte());
				rapportClientMystereVO_CDX_0255.setNumeroBillet1(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getNumeroBillet()));
				
				if (sectionSocieteCDX0255VO.getVisites()[0].isCarte() == false){
					rapportClientMystereVO_CDX_0255.setEnvoieAvisDate1(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getEnvoieAvisDate()));
					rapportClientMystereVO_CDX_0255.setReceptionAvisDate1(sectionSocieteCDX0255VO.getVisites()[0].getReceptionAvisDate());
				}
				rapportClientMystereVO_CDX_0255.setVisiteConfirme1(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[0].getVisiteConfirme()));
					
				// Visite 2
				rapportClientMystereVO_CDX_0255.setVisiteDate2(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[1].getVisiteDate()));
				rapportClientMystereVO_CDX_0255.setHeure2(sectionSocieteCDX0255VO.getVisites()[1].getHeureVisiteDate());
				rapportClientMystereVO_CDX_0255.setCarte2(sectionSocieteCDX0255VO.getVisites()[1].getCarte());
				rapportClientMystereVO_CDX_0255.setNumeroBillet2(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[1].getNumeroBillet()));
				
				if (sectionSocieteCDX0255VO.getVisites()[0].isCarte() == false){
					rapportClientMystereVO_CDX_0255.setEnvoieAvisDate2(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[1].getEnvoieAvisDate()));
					rapportClientMystereVO_CDX_0255.setReceptionAvisDate2(sectionSocieteCDX0255VO.getVisites()[1].getReceptionAvisDate());
				}
				rapportClientMystereVO_CDX_0255.setVisiteConfirme2(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[1].getVisiteConfirme()));				
				
				// Visite 3
				rapportClientMystereVO_CDX_0255.setVisiteDate3(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[2].getVisiteDate()));
				rapportClientMystereVO_CDX_0255.setHeure3(sectionSocieteCDX0255VO.getVisites()[2].getHeureVisiteDate());
				rapportClientMystereVO_CDX_0255.setCarte3(sectionSocieteCDX0255VO.getVisites()[2].getCarte());
				rapportClientMystereVO_CDX_0255.setNumeroBillet3(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[2].getNumeroBillet()));
				
				if (sectionSocieteCDX0255VO.getVisites()[2].isCarte() == false){
					rapportClientMystereVO_CDX_0255.setEnvoieAvisDate3(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[2].getEnvoieAvisDate()));
					rapportClientMystereVO_CDX_0255.setReceptionAvisDate3(sectionSocieteCDX0255VO.getVisites()[2].getReceptionAvisDate());
					rapportClientMystereVO_CDX_0255.setSuspensionDate3(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[2].getReceptionAvisDate())); // oui, c'est la même date...
				}
				rapportClientMystereVO_CDX_0255.setVisiteConfirme3(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[2].getVisiteConfirme()));				
				
				// Visite 4
				rapportClientMystereVO_CDX_0255.setVisiteDate4(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[3].getVisiteDate()));
				rapportClientMystereVO_CDX_0255.setHeure4(sectionSocieteCDX0255VO.getVisites()[3].getHeureVisiteDate());
				rapportClientMystereVO_CDX_0255.setCarte4(sectionSocieteCDX0255VO.getVisites()[3].getCarte());
				rapportClientMystereVO_CDX_0255.setNumeroBillet4(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[3].getNumeroBillet()));
				
				if (sectionSocieteCDX0255VO.getVisites()[3].isCarte() == false){
					rapportClientMystereVO_CDX_0255.setEnvoieAvisDate4(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[3].getEnvoieAvisDate()));
					rapportClientMystereVO_CDX_0255.setReceptionAvisDate4(sectionSocieteCDX0255VO.getVisites()[3].getReceptionAvisDate());
					rapportClientMystereVO_CDX_0255.setSuspensionDate4(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[3].getReceptionAvisDate())); // oui, c'est la même date...
				}
				rapportClientMystereVO_CDX_0255.setVisiteConfirme4(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[3].getVisiteConfirme()));				
				
				// Visite 5
				rapportClientMystereVO_CDX_0255.setVisiteDate5(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[4].getVisiteDate()));
				rapportClientMystereVO_CDX_0255.setHeure5(sectionSocieteCDX0255VO.getVisites()[4].getHeureVisiteDate());
				rapportClientMystereVO_CDX_0255.setCarte5(sectionSocieteCDX0255VO.getVisites()[4].getCarte());
				rapportClientMystereVO_CDX_0255.setNumeroBillet5(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[4].getNumeroBillet()));
				
				if (sectionSocieteCDX0255VO.getVisites()[4].isCarte() == false){
					rapportClientMystereVO_CDX_0255.setEnvoieAvisDate5(StringUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[4].getEnvoieAvisDate()));
					rapportClientMystereVO_CDX_0255.setReceptionAvisDate5(sectionSocieteCDX0255VO.getVisites()[4].getReceptionAvisDate());
					rapportClientMystereVO_CDX_0255.setSuspensionDate5(DateUtils.defaultString(sectionSocieteCDX0255VO.getVisites()[4].getReceptionAvisDate())); // oui, c'est la même date...
				}
				
				rapportClientMystereVO_CDX_0255.setAnnulation(sectionSocieteCDX0255VO.getAnnulation());
				rapportClientMystereVO_CDX_0255.setAnnulationCommentaire(StringUtils.defaultString(sectionSocieteCDX0255VO.getAnnulationCommentaire()));
				rapportClientMystereVO_CDX_0255.setAutreCommentaire(StringUtils.defaultString(sectionSocieteCDX0255VO.getAutreCommentaire()));
				rapportClientMystereVO_CDX_0255.setVague(StringUtils.defaultString(sectionSocieteCDX0255VO.getVague()));
				
				rapportClientMysteres.add(rapportClientMystereVO_CDX_0255);
			}
		}
		
		return rapportClientMysteres;
		
	}

    private void chargerSocieteResponsableSujet () throws DAOException {
        log.info("chargerSocieteResponsableSujet");
        FabriqueCardexDAO.getInstance().getRapportDAO().assignerSujetResponsableSociete(subject, societeClientMystereMap);
    }
    
    private void chargerSocieteResponsableSociete () throws DAOException {
        log.info("chargerSocieteResponsableSociete");
        FabriqueCardexDAO.getInstance().getRapportDAO().assignerSocieteResponsableSociete(subject, societeClientMystereMap);
    }   
    
    private void chargerDossierEchantillonActif() throws DAOException {
    	log.info("chargerDossierEchantillonActif");
    	echantillonDossierClientMystereActif = FabriqueCardexDAO.getInstance().getDossierDAO().echantillonDossierClientMystereActif(subject);
    }   
    
    private void chargerSocieteClientMystere() throws DAOException  {
    	log.info("chargerSocieteClientMystere");
    	societeClientMystereMap = FabriqueCardexDAO.getInstance().getRapportDAO().societeAvecEtSansDossierClientMystereCDX_0255(subject,echantillonDossierClientMystereActif);
    }
	
	@Override
	protected Map construireParametres() {
		return new HashMap();
	}

	@Override
	protected InputStream obtenirJasperReport() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_FICHIER_MAITRE);
	}

}
