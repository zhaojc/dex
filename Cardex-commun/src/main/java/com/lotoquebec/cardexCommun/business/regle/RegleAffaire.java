package com.lotoquebec.cardexCommun.business.regle;

import com.lotoquebec.cardexCommun.exception.DAOException;


public interface RegleAffaire <T> {

	public void executer(T t) throws DAOException;
	
}
