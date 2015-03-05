package com.lotoquebec.cardexCommun.business.regle;

import java.util.ArrayList;
import java.util.List;

import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class ValidationRegleAffaire <T>{

	private List<RegleAffaire<T>> regleAffaires = new ArrayList<RegleAffaire<T>>();
	
	public void executer(T t) throws BusinessException, DAOException{

		for(RegleAffaire<T> regleAffaire:regleAffaires)
			regleAffaire.executer(t);
	}

	public void addRegleAffaire(RegleAffaire<T> regleAffaire) {
		regleAffaires.add(regleAffaire);
	}

	
}
