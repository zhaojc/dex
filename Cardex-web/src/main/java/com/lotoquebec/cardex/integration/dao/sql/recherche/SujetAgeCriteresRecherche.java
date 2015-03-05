package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.SujetCriteresRecherche;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;

public abstract class SujetAgeCriteresRecherche extends ConstruireRechercheSQL{

    protected void assignerAgeCriteresRecherche(PreparerSQL preparerSQL, SujetCriteresRecherche sujetCriteresRecherche, long age){
        if (age != 0 && (sujetCriteresRecherche.isAgeReel() || sujetCriteresRecherche.isAgeReelPlusMoins() || sujetCriteresRecherche.isAgeEstime())){
            //RA0015 - Sujet ayant un âge ±5 ans réel ou estimé ***
            //Ex.: Âge 40 ans --> Retourne les sujets ayant de 35 à 45 ans, âge réel ou estimé,
            //si une de deux conditiones suivantes est vraie.
            //Cond. 1: âge réel, âge réel ±5 et âge estimé ±5 cochés OU
            //Cond. 2: âge réel pas coché et âge réel ±5 et âge estimé ±5 cochés
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
                
            //RA0016 - Sujet ayant réellement X ans ou un âge estimé d'âge-5 à âge+5 ***
            //Ex. Âge 40 ans --> Retourne les sujets ayant réellement 40 ans et
            //un âge estimé de 35 à 45 ans, si la condition suivante est vraie.
            //Cond. 1: âge réel coché, âge réel ±5 pas coché et âge estimé ±5 coché
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
                
            //RA0017 - Sujet ayant réellement un âge ±5 ans ***
            //Ex.: Âge 40 ans --> Retourne les sujets ayant réllement de 35 à 45 ans,
            //si une de deux conditions suivantes est vraie.
            //Cond. 1: âge réel pas coché, âge réel ±5 cochés et âge estimé ±5 pas coché, OU
            //Cond. 2: âge réel et âge réel ±5 ans cochés et âge estimé ±5 pas coché 
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
                
            //RA0018 - Sujet ayant un âge estimé ±5 ans ***
            //Ex. : Âge 40 ans --> Retourne les sujets ayant un âge 
            //estimé de 35 à 45 ans, si la condition suivante est vraie.
            //Cond. 1: âge réel et âge réel ±5 pas cochés et âge estimé ±5 coché
            }else if(sujetCriteresRecherche.isAgeReel()==false && sujetCriteresRecherche.isAgeReelPlusMoins()==false && sujetCriteresRecherche.isAgeEstime()) {
                
                preparerSQL.getSQL().append(" AND (S.D_SU_DATE_NAISSANCE between to_date(to_char(sysdate,'YYYY') - (? + 5),'YYYY')" );
                preparerSQL.getSQL().append(" AND to_date(to_char(sysdate,'YYYY') - (? - 5),'YYYY') AND s.l_su_type_age = 1352 ");
                
                if(sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" or s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU );
                }

                preparerSQL.getSQL().append(" ) " );
                preparerSQL.addParametre(age);
                preparerSQL.addParametre(age);
                
            //RA0019 - Sujet ayant réellemnt l'âge rentré***
            //Ex. : Âge 40 ans --> Retourne les sujets ayant réellement 
            //40 ans, si la condition suivante est vraie.
            //Cond. 1: âge réel coché et âge réel ±5 et âge estimé ±5 pas coché
            }else if(sujetCriteresRecherche.isAgeReel() && sujetCriteresRecherche.isAgeReelPlusMoins()==false && sujetCriteresRecherche.isAgeEstime()==false) {
                
                preparerSQL.getSQL().append(" AND ((TRUNC(EXTRACT(YEAR FROM NUMTOYMINTERVAL(MONTHS_BETWEEN(TRUNC(SYSDATE),S.D_SU_DATE_NAISSANCE+1),'YEAR'))/12,0) = ? ) " );
                preparerSQL.getSQL().append(" AND s.l_su_type_age = 1351 " );
                
                if(sujetCriteresRecherche.isAgeInconnu()){
                    preparerSQL.getSQL().append(" or s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU);
                }
                
                preparerSQL.getSQL().append(" ) " );                 
                preparerSQL.addParametre(age);
            }
        //Sujet ayant un âge inconnu***
        //Cond. : âge inconnu coché
        }else if(sujetCriteresRecherche.isAgeInconnu()) {
            preparerSQL.getSQL().append(" AND s.l_su_type_age = " + GlobalConstants.TypeAge.INCONNU);
        }
    }
}
