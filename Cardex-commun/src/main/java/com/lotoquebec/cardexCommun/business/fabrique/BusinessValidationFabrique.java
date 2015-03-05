package com.lotoquebec.cardexCommun.business.fabrique;

import java.util.List;

import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.regle.ValidationRegleAffaire;
import com.lotoquebec.cardexCommun.business.validateur.ValidationValidateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public abstract class BusinessValidationFabrique <T>{

	protected ValidationValidateur<T> validationValidateur = new ValidationValidateur<T>();
	protected ValidationRegleAffaire<T> validationRegleAffaire = new ValidationRegleAffaire<T>();
	
	protected abstract void constuireValidation(T t) throws BusinessResourceException, BusinessRuleException, DAOException;

	public void executer(T t) throws BusinessRuleException, BusinessException, DAOException{
		constuireValidation(t);
		validationValidateur.valider(t);
		validationRegleAffaire.executer(t);
	}

	public List<BusinessMessage> getMessages() {
		return validationValidateur.getMessages();
	}
	
}
