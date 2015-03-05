package com.lotoquebec.cardex.integration.dao;

public class FabriqueCardexDAO {

	private static FabriqueCardexDAO fabriqueDAO = null;

	private FabriqueCardexDAO(){}
	
	public static FabriqueCardexDAO getInstance(){
		if (fabriqueDAO == null)
			fabriqueDAO = new FabriqueCardexDAO();
		return fabriqueDAO;
	}

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public AccesDAO getAccesDAO() {
      return new AccesDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public AdresseDAO getAdresseDAO() {
      return new AdresseDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public CaracteristiqueDAO getCaracteristiqueDAO() {
      return new CaracteristiqueDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public DossierDAO getDossierDAO() {
      return new DossierDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public InscriptionDAO getInscriptionDAO() {
      return new InscriptionDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public JeuDAO getJeuDAO() {
      return new JeuDAO();
    }

    public SousCategorieDAO getSousCategorieDAO() {
      return new SousCategorieDAO();
    }
    
    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public MessageDAO getMessageDAO() {
      return new MessageDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public PhotoDAO getPhotoDAO() {
      return new PhotoDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public NarrationDAO getNarrationDAO() {
      return new NarrationDAO();
    }

	/**
	 * @see com.lotoquebec.cardex.integration.dao.DAOFactory
	 */
	public PSUMandatDAO getPSUMandatDAO() {
	  return new PSUMandatDAO();
	}

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public ParticulariteDAO getParticulariteDAO() {
      return new ParticulariteDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public EvaluationDAO getEvaluationDAO() {
      return new EvaluationDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public SocieteDAO getSocieteDAO() {
      return new SocieteDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public SuiviDAO getSuiviDAO() {
      return new SuiviDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public ConsignationDAO getConsignationDAO() {
      return new ConsignationDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public SujetDAO getSujetDAO() {
      return new SujetDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public VehiculeDAO getVehiculeDAO() {
      return new VehiculeDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public IntervenantDAO getIntervenantDAO() {
        return new IntervenantDAO();
    }

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.DAOFactory#getRegroupementDAO()
	 */
	public RegroupementDAO getRegroupementDAO() {
		return new RegroupementDAO();
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.DAOFactory#getRapportDAO()
	 */
	public RapportDAO getRapportDAO() {
		return new RapportDAO();
	}

	/**
	 * @see com.lotoquebec.cardex.integration.dao.DAOFactory
	 */
	public PartageDAO getPartageDAO() {
	  return new PartageDAO();
	}

	public MouvementsDAO getMouvementsDAO() {
		return new MouvementsDAO();
	}

	public BilletDAO getBilletDAO() {
		return new BilletDAO();
	}
	
	public SiteDAO getSiteDAO() {
		return new SiteDAO();
	}	

	public UrgenceDAO getUrgenceDAO() {
		return new UrgenceDAO();
	}
	
	public LiaisonDAO getLiaisonDAO() {
		return new LiaisonDAO();
	}
	
}
