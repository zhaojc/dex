package com.lotoquebec.cardex.ejb.flux;

/**
 * Sert � produire les rapports CDX_00255 - Clients-myst�re
 * Lanc� le jeudi � 6h via la flux DCDXQ06
 *
 */
public class CDX00_00019 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00019_ClientMystereCDX_0255()).execute();
	}
	
}
