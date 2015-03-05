package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.SujetCriteresRecherche;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;

public abstract class SujetAgeCriteresRecherche extends ConstruireRechercheSQL{

    protected void assignerAgeCriteresRecherche(PreparerSQL preparerSQL, SujetCriteresRecherche sujetCriteresRecherche, long age){
        if (age != 0 && (sujetCriteresRecherche.isAgeReel() || sujetCriteresRecherche.isAgeReelPlusMoins() || sujetCriteresRecherche.isAgeEstime())){
            //RA0015 - Sujet ayant un �ge �5 ans r�el ou estim� ***
            //Ex.: �ge 40 ans --> Retourne les sujets ayant de 35 � 45 ans, �ge r�el ou estim�,
            //si une de deux conditiones suivantes est vraie.
            //Cond. 1: �ge r�el, �ge r�el �5 et �ge estim� �5 coch�s OU
            //Cond. 2: �ge r�el pas coch� et �ge r�el �5 et �ge estim� �5 coch�s
            if ((sujetCriteresRecherche.isAgeReel()==false && sujetCriteresRecherche.isAgeReelPlusMoins()==false && sujetCriteresRecherche.isAgeEstime()==false)
                    || (sujetCriteresRecherche.isAgeReel() && sujetCriteresRecherche.isAgeReelPlusMoins() && sujetCriteresRecherche.isAgeEstime())
                            || (sujetCriteresRecherche.isAgeReel()==false && sujetCriteresRecherche.isAgeReelPlusMoins() && sujetCriteresRecherche.isAgeEstime())){
                
                if (sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" AND (S.D_SU_DATE_NAISSANCE between to_date(to_char(sysdate, 'YYYY') - (? + 5), 'YYYY') " );
                    preparerSQL.getSQL().append(" AND to_date(to_char(sysdate, 'YYYY') - (? - 5), 'YYYY') " );
                    preparerSQL.getSQL().append(" AND s.l_su_type_age in (" + GlobalConstants.TypeAge.REEL + "," + GlobalConstants.TypeAge.ESTIME + ")" );
                    preparerSQL.getSQL().append("  OR s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU + ")");
                }else{
                    preparerSQL.getSQL().append(" AND S.D_SU_DATE_NAISSANCE between to_date(to_char(sysdate,'YYYY') - (? + 5),'YYYY') " );
                    preparerSQL.getSQL().append(" AND to_date(to_char(sysdate,'YYYY') - (? - 5),'YYYY') " );
                    preparerSQL.getSQL().append(" AND s.l_su_type_age in (" + GlobalConstants.TypeAge.REEL + "," + GlobalConstants.TypeAge.ESTIME + ")" );
                }
                
                preparerSQL.addParametre(age);
                preparerSQL.addParametre(age);
                
            //RA0016 - Sujet ayant r�ellement X ans ou un �ge estim� d'�ge-5 � �ge+5 ***
            //Ex. �ge 40 ans --> Retourne les sujets ayant r�ellement 40 ans et
            //un �ge estim� de 35 � 45 ans, si la condition suivante est vraie.
            //Cond. 1: �ge r�el coch�, �ge r�el �5 pas coch� et �ge estim� �5 coch�
            }else if(sujetCriteresRecherche.isAgeReel() && sujetCriteresRecherche.isAgeReelPlusMoins()==false && sujetCriteresRecherche.isAgeEstime()){
                
                preparerSQL.getSQL().append(" AND ((S.D_SU_DATE_NAISSANCE between to_date(to_char(sysdate,'YYYY') - (? + 5),'YYYY')" );

                if(sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" AND to_date(to_char(sysdate,'YYYY') - (? - 5),'YYYY')" );
                    preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.ESTIME); 
                    preparerSQL.getSQL().append("  OR s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU + ")" );
                    preparerSQL.getSQL().append(" OR (TRUNC(EXTRACT(YEAR FROM NUMTOYMINTERVAL(MONTHS_BETWEEN(TRUNC(SYSDATE),S.D_SU_DATE_NAISSANCE+1),'YEAR'))/12,0) = ? ");
                    preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.REEL);
                    preparerSQL.getSQL().append("  OR s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU + ")) ");
                }else{
                    preparerSQL.getSQL().append(" AND to_date(to_char(sysdate,'YYYY') - (? - 5),'YYYY') " );
                    preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.ESTIME + ")" );
                    preparerSQL.getSQL().append(" OR (TRUNC(EXTRACT(YEAR FROM NUMTOYMINTERVAL(MONTHS_BETWEEN(TRUNC(SYSDATE),S.D_SU_DATE_NAISSANCE+1),'YEAR'))/12,0) = ? ");
                    preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.REEL + ")) ");
                }
                
                preparerSQL.addParametre(age);
                preparerSQL.addParametre(age);
                preparerSQL.addParametre(age);
                
            //RA0017 - Sujet ayant r�ellement un �ge �5 ans ***
            //Ex.: �ge 40 ans --> Retourne les sujets ayant r�llement de 35 � 45 ans,
            //si une de deux conditions suivantes est vraie.
            //Cond. 1: �ge r�el pas coch�, �ge r�el �5 coch�s et �ge estim� �5 pas coch�, OU
            //Cond. 2: �ge r�el et �ge r�el �5 ans coch�s et �ge estim� �5 pas coch� 
            }else if((sujetCriteresRecherche.isAgeReel()==false && sujetCriteresRecherche.isAgeReelPlusMoins() && sujetCriteresRecherche.isAgeEstime()==false)
                    || (sujetCriteresRecherche.isAgeReel() && sujetCriteresRecherche.isAgeReelPlusMoins() && sujetCriteresRecherche.isAgeEstime()==false)){
                
                preparerSQL.getSQL().append(" AND (S.D_SU_DATE_NAISSANCE between to_date(to_char(sysdate,'YYYY') - (? + 5),'YYYY') " );
                preparerSQL.getSQL().append(" AND to_date(to_char(sysdate,'YYYY') - (? - 5),'YYYY') " );
                preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.REEL);
                
                if (sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" OR s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU);
                }

                preparerSQL.getSQL().append(" ) " );                     
                preparerSQL.addParametre(age);
                preparerSQL.addParametre(age);
                
            //RA0018 - Sujet ayant un �ge estim� �5 ans ***
            //Ex. : �ge 40 ans --> Retourne les sujets ayant un �ge 
            //estim� de 35 � 45 ans, si la condition suivante est vraie.
            //Cond. 1: �ge r�el et �ge r�el �5 pas coch�s et �ge estim� �5 coch�
            }else if(sujetCriteresRecherche.isAgeReel()==false && sujetCriteresRecherche.isAgeReelPlusMoins()==false && sujetCriteresRecherche.isAgeEstime()) {
                
                preparerSQL.getSQL().append(" AND (S.D_SU_DATE_NAISSANCE between to_date(to_char(sysdate,'YYYY') - (? + 5),'YYYY')" );
                preparerSQL.getSQL().append(" AND to_date(to_char(sysdate,'YYYY') - (? - 5),'YYYY') AND s.l_su_type_age = 1352 ");
                
                if(sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" or s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU );
                }

                preparerSQL.getSQL().append(" ) " );
                preparerSQL.addParametre(age);
                preparerSQL.addParametre(age);
                
            //RA0019 - Sujet ayant r�ellemnt l'�ge rentr�***
            //Ex. : �ge 40 ans --> Retourne les sujets ayant r�ellement 
            //40 ans, si la condition suivante est vraie.
            //Cond. 1: �ge r�el coch� et �ge r�el �5 et �ge estim� �5 pas coch�
            }else if(sujetCriteresRecherche.isAgeReel() && sujetCriteresRecherche.isAgeReelPlusMoins()==false && sujetCriteresRecherche.isAgeEstime()==false) {
                
                preparerSQL.getSQL().append(" AND ((TRUNC(EXTRACT(YEAR FROM NUMTOYMINTERVAL(MONTHS_BETWEEN(TRUNC(SYSDATE),S.D_SU_DATE_NAISSANCE+1),'YEAR'))/12,0) = ? ) " );
                preparerSQL.getSQL().append(" AND s.l_su_type_age = 1351 " );
                
                if(sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" or s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU);
                }
                
                preparerSQL.getSQL().append(" ) " );                 
                preparerSQL.addParametre(age);
            }
        //Sujet ayant un �ge inconnu***
        //Cond. : �ge inconnu coch�
        }else if(sujetCriteresRecherche.isAgeInconnu()) {
            preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU);
        }
    }
}
