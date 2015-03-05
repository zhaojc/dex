package com.lotoquebec.cardexCommun.util;

public class TableValeur {
	private String cle = "";
	private int action = 0;
	private String description = "";
	private boolean actif = false;
	private String role = "";
	private boolean obligatoire = false;
	private boolean administrer = false;
	
	/**
	 * @param actif
	 * @param cle
	 * @param description
	 * @param role
	 */
	public TableValeur(String cle, String description, boolean actif, String role, int action, boolean obligatoire, boolean administrer) {
		super();
		this.cle = cle;
		this.description = description;
		this.actif = actif;
		this.administrer = administrer;
		this.obligatoire = obligatoire;
		this.role = role;
		this.action = action;
	}
	
	public String getCle() {
		return cle;
	}
	
	public void setCle(String cle) {
		this.cle = cle;
	}
	
	public boolean isActif() {
		return actif;
	}
	
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isAdministrer() {
		return administrer;
	}
	
	public void setAdministrer(boolean administrer) {
		this.administrer = administrer;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public boolean isObligatoire() {
		return obligatoire;
	}

	public void setObligatoire(boolean obligatoire) {
		this.obligatoire = obligatoire;
	}
	
}
