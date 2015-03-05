package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class SujetDateDeNaissanceValidateurRV0009 implements Validateur<Sujet>
{

    public SujetDateDeNaissanceValidateurRV0009(){
        super();
    }
    
   public boolean isValide(Sujet t) throws BusinessException, DAOException
    {
       if((t.getTypeAge() == GlobalConstants.TypeAge.ESTIME || t.getTypeAge() == GlobalConstants.TypeAge.REEL) && t.getAge() == "")
           throw new BusinessException( getMessage() );
   
   return true;
    }

   public BusinessMessage getMessage() throws BusinessResourceException  {
           return new BusinessMessage("ME0008");  //Lorsque le type d’âge est Réel ou Estimé, 
                                                  //la date de naissance est requisse
   }

}
