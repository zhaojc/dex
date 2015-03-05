package com.lotoquebec.cardexCommun.business.validateur;

import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public interface Validateur<T> {

	public boolean isValide(T t) throws BusinessException, DAOException;
	
	public BusinessMessage getMessage() throws BusinessResourceException;
	
}
