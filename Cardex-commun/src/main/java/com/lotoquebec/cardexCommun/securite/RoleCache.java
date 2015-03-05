package com.lotoquebec.cardexCommun.securite;

import java.io.Serializable;

public class RoleCache implements Serializable {

	private String role = "";
	private String classeForm = "";
	private String classeVO = "";
	private String url = "";
	private String codeListe = "";
	private boolean administrer = false;

	public RoleCache(String role) {
		super();
		this.role = role;
	}

	public RoleCache(String role, String classeForm, String classeVO, String codeListe, String url, boolean administrer) {
		super();
		this.role = role;
		this.classeForm = classeForm;
		this.classeVO = classeVO;
		this.codeListe = codeListe;
		this.url = url;
		this.administrer = administrer;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getClasseForm() {
		return classeForm;
	}
	
	public void setClasseForm(String classeForm) {
		this.classeForm = classeForm;
	}
	
	public String getClasseVO() {
		return classeVO;
	}
	
	public void setClasseVO(String classeVO) {
		this.classeVO = classeVO;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getCodeListe() {
		return codeListe;
	}

	public void setCodeListe(String codeListe) {
		this.codeListe = codeListe;
	}
	
	public boolean equals(Object o){
		
		if (o instanceof RoleCache == false)
			return false;
		RoleCache roleCache = (RoleCache) o;
		
		return role.equals(roleCache.getRole());
	}
	
	public int hashCode(){
		return 1;
	}

	public boolean isAdministrer() {
		return administrer;
	}

	public void setAdministrer(boolean administrer) {
		this.administrer = administrer;
	}
	
	
}
