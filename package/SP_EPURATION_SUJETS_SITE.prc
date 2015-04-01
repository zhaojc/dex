CREATE OR REPLACE PROCEDURE "SP_EPURATION_SUJETS_SITE"(SITE IN SI_SITE.L_SI_CLE%TYPE) IS
BEGIN
  -- Épuration de tous les sujets inactifs (confidentialité 8) par site.
  -- On supprime toutes les références des sujets avant de les épurer
  -- Créé le 2000-10-03 par F. Guérin
  DELETE FROM AD_ADRESSE
   WHERE (L_AD_REFERENCE, L_AD_REF_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
            FROM SU_SUJET
           WHERE I_CC_CLE = 14920
             AND L_SI_CLE = SITE) --Confidentialité 8 (inactif)
     AND C_AD_REF_GENRE = 'SU';
  COMMIT;
  DELETE FROM CO_COMMENTAIRE2
   WHERE (L_CO_REFERENCE, L_CO_REF_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
            FROM SU_SUJET
           WHERE I_CC_CLE = 14920
             AND L_SI_CLE = SITE)
     AND C_CO_REF_GENRE = 'SU';
  COMMIT;
  DELETE FROM LSC_CARACTERISTIQUE
   WHERE (L_LSC_REFERENCE, L_LSC_REF_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
            FROM SU_SUJET
           WHERE I_CC_CLE = 14920
             AND L_SI_CLE = SITE)
     AND C_LSC_REF_GENRE = 'SU';
  COMMIT;
  DELETE FROM LDD_LIEN_DOSSIER
   WHERE ((L_DO_CLE, L_DO_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
             FROM SU_SUJET
            WHERE I_CC_CLE = 14920
              AND L_SI_CLE = SITE) AND C_DO_GENRE = 'SU')
      OR ((L_LDD_DOSSIER_ASSOCIE, L_LDD_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
             FROM SU_SUJET
            WHERE I_CC_CLE = 14920
              AND L_SI_CLE = SITE) AND C_LDD_GENRE = 'SU');
  COMMIT;
  DELETE FROM LMM_LIEN_MULTIMEDIA
   WHERE (L_LMM_REFERENCE, L_LMM_REF_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
            FROM SU_SUJET
           WHERE I_CC_CLE = 14920
             AND L_SI_CLE = SITE)
     AND C_LMM_REF_GENRE = 'SU';
  COMMIT;
  DELETE FROM AC_ACCES A
   WHERE (A.L_ORI_CLE, A.L_ORI_SITE) IN
         (SELECT L_SU_CLE, L_SI_CLE
            FROM SU_SUJET
           WHERE I_CC_CLE = 14920
             AND L_SI_CLE = SITE)
     AND A.C_GF_REFERENCE = 'SU';
  COMMIT;
  delete from AUD_SU_SUJET
   where L_SU_CLE IN
         (SELECT S.L_SU_CLE FROM SU_SUJET S WHERE S.I_CC_CLE = 14920)
     and L_SI_CLE = SITE;
  commit;

  DELETE FROM SU_SUJET
   WHERE I_CC_CLE = 14920
     AND L_SI_CLE = SITE;
  COMMIT;
END SP_EPURATION_SUJETS_SITE;
/
