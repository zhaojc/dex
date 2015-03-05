package com.lotoquebec.cardexCommun.integration.dao;

public class FabriqueDAO {

	private static FabriqueDAO fabriqueDAO = null;

	private FabriqueDAO(){}
	
	public static FabriqueDAO getInstance(){
		if (fabriqueDAO == null)
			fabriqueDAO = new FabriqueDAO();
		return fabriqueDAO;
	}

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public IntervenantDAO getIntervenantDAO() {
        return new IntervenantDAO();
    }

    /**
     * @see com.lotoquebec.cardex.integration.dao.DAOFactory
     */
    public ItemListDAO getItemListDAO() {
        return new ItemListDAO();
    }

    public SecuriteDAO getSecuriteDAO() {
        return new SecuriteDAO();
    }
    
}
