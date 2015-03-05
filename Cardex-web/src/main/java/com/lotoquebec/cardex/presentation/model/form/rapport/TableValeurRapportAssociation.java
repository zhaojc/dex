package com.lotoquebec.cardex.presentation.model.form.rapport;

import com.lotoquebec.cardex.presentation.model.form.rapport.dossier.AutoexclusionEntiteRapportForm_CDX_0220;
import com.lotoquebec.cardex.presentation.model.form.rapport.dossier.AutoexclusionEspaceJeuxEntiteRapportForm_CDX_0221;
import com.lotoquebec.cardex.presentation.model.form.rapport.dossier.NombreRechercheDossiersRapportForm_CDX_0229;
import com.lotoquebec.cardex.presentation.model.form.rapport.dossier.ReadmissionEntiteRapportForm_CDX_0234;
import com.lotoquebec.cardex.presentation.model.form.rapport.dossier.SuiviEntiteRapportForm_CDX_0223;

public class TableValeurRapportAssociation {

	public static RapportForm obtenirEntiteRapport(int choixRapport){
		switch(choixRapport){
			case 484: //Contrat d'autoexclusion
				return new AutoexclusionEntiteRapportForm_CDX_0220();	
			case 486: //Contrat d'autoexclusion Espacejeux
				return new AutoexclusionEspaceJeuxEntiteRapportForm_CDX_0221();	
			case 487: //Rapport avec suivis
				return new SuiviEntiteRapportForm_CDX_0223();
			case 493: //Rapport sur le nombre de dossiers retournés par une recherche
				return new NombreRechercheDossiersRapportForm_CDX_0229();
			case 550: //Formulaire de réadmission
				return new ReadmissionEntiteRapportForm_CDX_0234();
				
			default:
				throw new RuntimeException("Aucun rapport trouvé pour la valeur "+choixRapport);		
		}
	}
}
