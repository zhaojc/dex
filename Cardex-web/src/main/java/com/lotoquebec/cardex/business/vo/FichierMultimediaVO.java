package com.lotoquebec.cardex.business.vo;

import java.sql.Blob;

public class FichierMultimediaVO {
 
	private Blob image;
	private byte[] imageByte;
	private String extention;
	private long typeMultimedia;
	private long cle;
	private long site;
	private String genre="";
	private long entite;
	private long confidentialite;
	

	public FichierMultimediaVO(String extention, long typeMultimedia, long cle, long site, String genre) {
		super();
		this.extention = extention;
		this.typeMultimedia = typeMultimedia;
		this.cle = cle;
		this.site = site;
		this.genre = genre;
	}

	public Blob getImage() {
		return image;
	}
	
	public void setImage(Blob image) {
		this.image = image;
	}

	public long getCle() {
		return cle;
	}
	
	public long getSite() {
		return site;
	}
	
	public String getGenre() {
		return genre;
	}

	public long getTypeMultimedia() {
		return typeMultimedia;
	}

	public String getStringTypeMultimedia() {
		return String.valueOf(typeMultimedia);
	}
	
	public long getEntite() {
		return entite;
	}

	public void setEntite(long entite) {
		this.entite = entite;
	}

	public long getConfidentialite() {
		return confidentialite;
	}

	public void setConfidentialite(long confidentialite) {
		this.confidentialite = confidentialite;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	/**
	 * @return imageByte
	 */
	public byte[] getImageByte() {
		return imageByte;
	}

	/**
	 * @param imageByte imageByte à définir
	 */
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	
	
}
