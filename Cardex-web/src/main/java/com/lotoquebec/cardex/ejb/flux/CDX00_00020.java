package com.lotoquebec.cardex.ejb.flux;

/**
 * Sert � produire les rapports CDX_00257 - Clients-myst�re - D�taillants en infraction
 * Lanc� le lundi � 6h via la flux DCDXQ06
 *
 */
public class CDX00_00020 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00020_ClientMystereCDX_0257()).execute();
	}
	
}
