/*
 * Created on 10-Apr-2008
 */
package com.lotoquebec.cardex.business.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author levassc
 */
public class ResultatRegroupementVO {

	private List listRegroupement = new ArrayList(); // ResultatRegroupementVO
	private Map mapMatriculeHeuresProductive = new HashMap(); // key: matricule; value: nombre heure
	private double nombreHeuresProductive = 0;
	private boolean heuresProductiveValide = false; 
	
	public List getListRegroupement() {
		return listRegroupement;
	}
	public void setListRegroupement(List listRegroupement) {
		this.listRegroupement = listRegroupement;
	}
	public double getNombreHeuresProductive() {
		return nombreHeuresProductive;
	}
	public void setNombreHeuresProductive(double nombreHeuresProductive) {
		this.nombreHeuresProductive = nombreHeuresProductive;
	}
	public Boolean isHeuresProductiveValide() {
		return heuresProductiveValide;
	}
	public void setHeuresProductiveValide(Boolean heuresProductiveValide) {
		this.heuresProductiveValide = heuresProductiveValide;
	}
	public Map getMapMatriculeHeuresProductive() {
		return mapMatriculeHeuresProductive;
	}
	public void setMapMatriculeHeuresProductive(Map mapMatriculeHeuresProductive) {
		this.mapMatriculeHeuresProductive = mapMatriculeHeuresProductive;
	}
	public void setHeuresProductiveValide(boolean heuresProductiveValide) {
		this.heuresProductiveValide = heuresProductiveValide;
	}
	
}
