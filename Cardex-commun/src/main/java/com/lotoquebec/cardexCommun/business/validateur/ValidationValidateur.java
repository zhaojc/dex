package com.lotoquebec.cardexCommun.business.validateur;

import java.util.ArrayList;
import java.util.List;

import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class ValidationValidateur <T> {

	private List<Validateur<T>> validateurs = new ArrayList<Validateur<T>>();
	private List<BusinessMessage> messages = new ArrayList<BusinessMessage>();
	private boolean valide = false;
	
	public void valider(T t) throws BusinessException, DAOException{
		
		for(Validateur<T> validateur:validateurs){
			
			if (validateur.isValide(t) == false){
				messages.add( validateur.getMessage() );
				valide = false;
			}
		}
	}
	
	public void addValidateur(Validateur<T> validateur) {
		validateurs.add(validateur);
	}
	
	public boolean isValide() {
		return valide;
	}

	public List<BusinessMessage> getMessages() {
		return messages;
	}
	
	
	
}
