package com.lotoquebec.cardex.business.facade;

public class FabriqueFacade {

	public static AccesSessionFacade getAccesSessionFacade() {
		return new AccesSessionFacade();
	}
	
	public static AdresseSessionFacade getAdresseSessionFacade() {
		return new AdresseSessionFacade();
	}

	public static BilletSessionFacade getBilletSessionFacade() {
		return new BilletSessionFacade();
	}

	public static ConsignationSessionFacade getConsignationSessionFacade() {
		return new ConsignationSessionFacade();
	}

	public static EvaluationSessionFacade getEvaluationSessionFacade() {
		return new EvaluationSessionFacade();
	}

	public static DossierSessionFacade getDossierSessionFacade() {
		return new DossierSessionFacade();
	}

	public static MessageSessionFacade getMessageSessionFacade() {
		return new MessageSessionFacade();
	}
	
	public static NarrationSessionFacade getNarrationSessionFacade() {
		return new NarrationSessionFacade();
	}

	public static PhotoSessionFacade getPhotoSessionFacade() {
		return new PhotoSessionFacade();
	}
	public static PSUMandatSessionFacade getPSUMandatSessionFacade() {
		return new PSUMandatSessionFacade();
	}

	public static RapportSessionFacade getRapportSessionFacade() {
		return new RapportSessionFacade();
	}

	public static RegroupementSessionFacade getRegroupementSessionFacade() {
		return new RegroupementSessionFacade();
	}
	
	public static SocieteSessionFacade getSocieteSessionFacade() {
		return new SocieteSessionFacade();
	}
	
	public static SuiviSessionFacade getSuiviSessionFacade() {
		return new SuiviSessionFacade();
	}
	
	public static SujetSessionFacade getSujetSessionFacade() {
		return new SujetSessionFacade();
	}
	
	public static VehiculeSessionFacade getVehiculeSessionFacade() {
		return new VehiculeSessionFacade();
	}
	
	public static UrgenceSessionFacade getUrgenceSessionFacade() {
		return new UrgenceSessionFacade();
	}

}
