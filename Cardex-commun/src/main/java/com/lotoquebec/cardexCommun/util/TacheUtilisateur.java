package com.lotoquebec.cardexCommun.util;

import java.sql.SQLException;
import java.sql.Statement;

public class TacheUtilisateur {

	private Integer numero = null;
	private Statement statement = null;
	private Thread thread = null;
	
	public TacheUtilisateur(Integer numero, Statement statement, Thread thread) {
		super();
		this.numero = numero;
		this.statement = statement;
		this.thread = thread;
	}

	public void cancel(){
		try {
			statement.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
	public int hashCode(){
		return numero;
	}

}
