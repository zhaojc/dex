/*
 * Created on 18-Oct-2007
 */
package com.lotoquebec.cardexCommun.exception;

import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;

/**
 * @author levassc
 */
public class BusinessRuleExceptionHandle {

	private BusinessException businessException = new BusinessException();
	
	public BusinessRuleExceptionHandle() {
		super();
		businessException.setBusinessMessageResult( new BusinessMessageResult() );
	}

	public BusinessRuleExceptionHandle(String messageKey) {
		super();
		businessException.setBusinessMessageResult( new BusinessMessageResult() );
		add(messageKey);
	}
	
    public void add(String messageKey) {
		BusinessMessage message = new BusinessMessage();
		message.setKey(messageKey);
		businessException.getBusinessMessageResult().addMessage(message);
	}

    public void add(String messageKey, String...arg) {
		BusinessMessage message = new BusinessMessage();
		message.setKey(messageKey);
		message.addArguments(arg);
		businessException.getBusinessMessageResult().addMessage(message);
	}
    
	public BusinessException getBusinessException() {
		return businessException;
	}
	
	public void setBusinessException(BusinessException businessException) {
		this.businessException = businessException;
	}
	
}
