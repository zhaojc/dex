package com.lotoquebec.cardex.business.delegate;


public class FabriqueBusinessDelegate {

	public static AccesBusinessDelegate getAccesBusinessDelegate() {
		return new AccesBusinessDelegate();
	}
	
	public static AdresseBusinessDelegate getAdresseBusinessDelegate() {
		return new AdresseBusinessDelegate();
	}	
	
	public static BilletBusinessDelegate getBilletBusinessDelegate() {
		return new BilletBusinessDelegate();
	}		

	public static ConsignationBusinessDelegate getConsignationBusinessDelegate() {
		return new ConsignationBusinessDelegate();
	}	
	
	public static DossierBusinessDelegate getDossierBusinessDelegate() {
		return new DossierBusinessDelegate();
	}
	
	public static MessageBusinessDelegate getMessageBusinessDelegate() {
		return new MessageBusinessDelegate();
	}
	
	public static NarrationBusinessDelegate getNarrationBusinessDelegate() {
		return new NarrationBusinessDelegate();
	}	
	
	public static PhotoBusinessDelegate getPhotoBusinessDelegate() {
		return new PhotoBusinessDelegate();
	}		
	
	public static PSUMandatBusinessDelegate getPSUMandatBusinessDelegate() {
		return new PSUMandatBusinessDelegate();
	}
	
	public static RapportBusinessDelegate getRapportBusinessDelegate() {
		return new RapportBusinessDelegate();
	}
	
	public static RegroupementBusinessDelegate getRegroupementBusinessDelegate() {
		return new RegroupementBusinessDelegate();
	}

	public static SocieteBusinessDelegate getSocieteBusinessDelegate() {
		return new SocieteBusinessDelegate();
	}

	public static SuiviBusinessDelegate getSuiviBusinessDelegate() {
		return new SuiviBusinessDelegate();
	}
	
	public static SujetBusinessDelegate getSujetBusinessDelegate() {
		return new SujetBusinessDelegate();
	}

	public static VehiculeBusinessDelegate getVehiculeBusinessDelegate() {
		return new VehiculeBusinessDelegate();
	}
	
}
