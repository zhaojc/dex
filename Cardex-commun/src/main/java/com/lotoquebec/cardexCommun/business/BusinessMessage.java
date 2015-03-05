package com.lotoquebec.cardexCommun.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Convenient wrapper for collections of messages returned
* from the business layer.
*/
public class BusinessMessage {

	private String  key = null;
	private List<String> arguments = new ArrayList<String>();
  
  
	public BusinessMessage() {
		super();
	}

	public BusinessMessage(String key, String...arguments) {
		this.key = key;
		addArguments(arguments);
	}

	public String getKey(){
		return this.key;
	}

  public List<String> getArguments(){
    return this.arguments;
  }

  public void setKey(String key){
    this.key = key;;
  }

  public boolean addArguments(String...argument){
	  return this.arguments.addAll(Arrays.asList(argument));
  }
}