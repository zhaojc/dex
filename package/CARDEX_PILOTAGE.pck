CREATE OR REPLACE PACKAGE CARDEX_PILOTAGE is
  procedure SP_E_EN_ENTITE(action               IN CHAR,
                           new_I_EN_CLE         IN OUT EN_ENTITE.I_EN_CLE%TYPE,
                           new_V_EN_ABREVIATION IN EN_ENTITE.V_EN_ABREVIATION%TYPE,
                           new_V_EN_ENTITE      IN EN_ENTITE.V_EN_ENTITE%TYPE,
                           new_V_EN_COMMENTAIRE IN EN_ENTITE.V_EN_COMMENTAIRE%TYPE);
  procedure SP_E_LA_LANGUE(action       IN CHAR,
                           new_I_LA_CLE IN OUT LA_LANGUE.I_LA_CLE%TYPE);
  procedure SP_E_ST_STATUT(action                 IN CHAR,
                           new_I_ST_CLE           IN OUT ST_STATUT.I_ST_CLE%TYPE,
                           new_C_ST_GENRE_FICHIER IN ST_STATUT.C_ST_GENRE_FICHIER%TYPE,
                           new_C_ST_TYPE          IN ST_STATUT.C_ST_TYPE%TYPE);
  procedure SP_E_GE_GENRE(action          IN CHAR,
                          new_I_GE_CLE    IN OUT GE_GENRE.I_GE_CLE%TYPE,
                          new_I_CC_CLE_DEFAUT IN GE_GENRE.I_CC_CLE_DEFAUT%TYPE,
                          new_I_GE_ENTITE IN GE_GENRE.I_GE_ENTITE%TYPE);
  procedure SP_E_PA_PAYS(action       IN CHAR,
                         new_I_PA_CLE IN OUT PA_PAYS.I_PA_CLE%TYPE);
  procedure SP_E_SE_SEVERITE(action       IN CHAR,
                             new_I_SE_CLE IN OUT SE_SEVERITE.I_SE_CLE%TYPE);
  procedure SP_E_NT_NATIONALITE(action       IN CHAR,
                                new_I_NT_CLE IN OUT NT_NATIONALITE.I_NT_CLE%TYPE);
  procedure SP_E_RA_RACE(action       IN CHAR,
                         new_I_RA_CLE IN OUT RA_RACE.I_RA_CLE%TYPE);
  procedure SP_E_CR_CARACTERISTIQUE(action       IN CHAR,
                                    new_L_CR_CLE IN OUT CR_CARACTERISTIQUE.L_CR_CLE%TYPE);
  procedure SP_E_RO_ROLE(action       IN CHAR,
                         new_I_RO_CLE IN OUT RO_ROLE.I_RO_CLE%TYPE);
  procedure SP_E_CL_CLASSE(action       IN CHAR,
                           new_I_CL_CLE IN OUT CL_CLASSE.I_CL_CLE%TYPE);
  procedure SP_E_PT_PARTICULARITE(action       IN CHAR,
                                  new_I_PT_CLE IN OUT PT_PARTICULARITE.I_PT_CLE%TYPE);
  procedure SP_E_MA_MARQUE(action               IN CHAR,
                           new_I_MA_CLE         IN OUT MA_MARQUE.I_MA_CLE%TYPE,
                           new_C_MA_ABREVIATION IN MA_MARQUE.C_MA_ABREVIATION%TYPE,
                           new_V_MA_MARQUE      IN MA_MARQUE.V_MA_MARQUE%TYPE);
  procedure SP_E_LS_LANGUE(action       IN CHAR,
                           new_I_LS_CLE IN OUT LS_LANGUE.I_LS_CLE%TYPE);
  procedure SP_E_CC_CONFIDENTIALITE(action       IN CHAR,
                                    new_I_CC_CLE IN OUT CC_CONFIDENTIALITE.I_CC_CLE%TYPE);
  procedure SP_E_PE_PERIODE(action       IN CHAR,
                            new_I_PE_CLE IN OUT PE_PERIODE.I_PE_CLE%TYPE);
  procedure SP_E_TM_TYPE_MULTIMEDIA(action       IN CHAR,
                                    new_I_TM_CLE IN OUT TM_TYPE_MULTIMEDIA.I_TM_CLE%TYPE);
  procedure SP_E_SX_SEXE(action       IN CHAR,
                         new_I_SX_CLE IN OUT SX_SEXE.I_SX_CLE%TYPE);
  procedure SP_E_CR_CARDINALITE(action       IN CHAR,
                                new_I_CR_CLE IN OUT CR_CARDINALITE.I_CR_CLE%TYPE,
                                new_L_SI_CLE IN SI_SITE.L_SI_CLE%TYPE);
  procedure SP_E_SR_SERVICE(action       IN CHAR,
                            new_L_SR_CLE IN OUT SR_SERVICE.L_SR_CLE%TYPE,
                            new_L_SI_CLE IN SI_SITE.L_SI_CLE%TYPE);
  procedure SP_E_OR_ORIENTATION(action       IN CHAR,
                                new_I_OR_CLE IN OUT OR_ORIENTATION.I_OR_CLE%TYPE,
                                NEW_L_SI_CLE IN SI_SITE.L_SI_CLE%TYPE,
                                NEW_L_SR_CLE IN SR_SERVICE.L_SR_CLE%TYPE);
  procedure SP_E_NH_NIVEAU_HIERARCHIQUE(action       IN CHAR,
                                        new_I_NH_CLE IN OUT NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE);
  procedure SP_E_SI_SITE(action               IN CHAR,
                         NEW_L_SI_CLE         IN SI_SITE.L_SI_CLE%TYPE,
                         NEW_I_EN_CLE         IN SI_SITE.I_EN_CLE%TYPE,
                         NEW_V_SI_ABREVIATION IN SI_SITE.V_SI_ABREVIATION%TYPE,
                         NEW_V_SI_SITE        IN SI_SITE.V_SI_SITE%TYPE,
                         NEW_D_SI_SEQUENCE    IN SI_SITE.D_SI_SEQUENCE%TYPE,
                         NEW_N_SI_SEQUENCE    IN SI_SITE.N_SI_SEQUENCE%TYPE,
                         NEW_B_SI_ORIGINE     IN SI_SITE.B_SI_ORIGINE%TYPE,
                         NEW_B_SI_APPLICABLE  IN SI_SITE.B_SI_APPLICABLE%TYPE,
                         NEW_V_SI_HEURE       IN SI_SITE.V_SI_HEURE_DEBUT%TYPE);
  procedure SP_E_NA_NATURE(action               IN CHAR,
                           new_I_NA_CLE         IN OUT NA_NATURE.I_NA_CLE%TYPE,
                           new_I_GE_CLE         IN NA_NATURE.I_GE_CLE%TYPE,
                           new_B_NA_INSCRIPTION IN NA_NATURE.B_NA_INSCRIPTION%TYPE);
  procedure SP_E_TY_TYPE(action       IN CHAR,
                         new_I_TY_CLE IN OUT TY_TYPE.I_TY_CLE%TYPE,
                         new_I_NA_CLE IN TY_TYPE.I_NA_CLE%TYPE);
  procedure SP_E_CA_CATEGORIE(action       IN CHAR,
                              new_I_CA_CLE IN OUT CA_CATEGORIE.I_CA_CLE%TYPE,
                              new_I_TY_CLE IN CA_CATEGORIE.I_TY_CLE%TYPE);
  procedure SP_E_PR_PROVINCE(action       IN CHAR,
                             new_L_PR_CLE IN OUT PR_PROVINCE.L_PR_CLE%TYPE,
                             new_I_PA_CLE IN PR_PROVINCE.I_PA_CLE%TYPE);
  procedure SP_E_VI_VILLE(action         IN CHAR,
                          new_L_VI_CLE   IN OUT VI_VILLE.L_VI_CLE%TYPE,
                          new_L_PR_CLE   IN VI_VILLE.L_PR_CLE%TYPE,
                          new_V_VI_VILLE IN VI_VILLE.V_VI_VILLE%TYPE);
  procedure SP_E_MD_MODELE(action          IN CHAR,
                           new_I_MD_CLE    IN OUT MD_MODELE.I_MD_CLE%TYPE,
                           new_I_MA_CLE    IN MD_MODELE.I_MA_CLE%TYPE,
                           new_V_MD_MODELE IN MD_MODELE.V_MD_MODELE%TYPE);
  
  procedure SP_E_TR_TRADUCTION(new_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                               new_I_LA_CLE         IN TR_TRADUCTION.I_LA_CLE%TYPE,
                               new_V_TR_ABREVIATION IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                               new_V_TR_DESCRIPTION IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);
  procedure SP_E_GF_GENRE_FICHIER(action                 IN CHAR,
                                  new_C_GF_CLE           IN OUT GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                                  new_V_GF_GENRE_FICHIER IN GF_GENRE_FICHIER.V_GF_GENRE_FICHIER%TYPE);
  procedure SP_E_RF_REF_VIDEO(action       IN CHAR,
                              new_I_RF_CLE IN OUT RF_REFERENCE_VIDEO.I_RF_CLE%TYPE);
  procedure SP_E_TC_TYPE_ACTIVITE(action       IN CHAR,
                                  new_I_TC_CLE IN OUT TC_TYPE_ACTIVITE.I_TC_CLE%TYPE);
  procedure SP_E_FO_FONDE(action       IN CHAR,
                          new_I_FO_CLE IN OUT FO_FONDE.I_FO_CLE%TYPE);
  procedure SP_E_TN_TYPE_CONSIGNATION(action               IN CHAR,
                                      new_I_TN_CLE         IN OUT TN_TYPE_CONSIGNATION.I_TN_CLE%TYPE,
                                      new_B_TN_APPROBATION IN OUT TN_TYPE_CONSIGNATION.B_TN_APPROBATION%TYPE);
  procedure SP_E_DE_DEVISE(action       IN CHAR,
                           new_I_DE_CLE IN OUT DE_DEVISE.I_DE_CLE%TYPE);
  procedure SP_E_DN_DENOMINATION(action       IN CHAR,
                                 new_I_DN_CLE IN OUT DN_DENOMINATION.I_DN_CLE%TYPE);
  procedure SP_E_RG_REGROUPEMENT(action             IN CHAR,
                                 NEW_I_RG_CLE       IN OUT RG_REGROUPEMENT.I_RG_CLE%TYPE,
                                 NEW_NAME           RG_REGROUPEMENT.NAME%TYPE,
                                 NEW_I_RG_QUOTA     RG_REGROUPEMENT.I_RG_QUOTA%TYPE,
                                 NEW_I_RG_QUOTA_MIN RG_REGROUPEMENT.I_RG_QUOTA_MIN%TYPE,
                                 NEW_L_IN_SECTEUR   RG_REGROUPEMENT.L_IN_SECTEUR%TYPE);
  procedure SP_E_LRG_LIEN_REGROUPEMENT(action       IN CHAR,
                                       NEW_I_RG_CLE IN LRG_LIEN_REGROUPEMENT.I_RG_CLE%TYPE,
                                       NEW_I_CA_CLE IN LRG_LIEN_REGROUPEMENT.I_CA_CLE%TYPE);
  procedure SP_E_TR_TYPE_RUE(action       IN CHAR,
                             new_I_TR_CLE IN OUT TR_TYPE_RUE.I_TR_CLE%TYPE);
  procedure SP_E_CT_CARDINALITE(action       IN CHAR,
                                new_I_CT_CLE IN OUT CT_CARDINALITE.I_CT_CLE%TYPE);
  procedure SP_E_TU_TYPE_UNITE(action       IN CHAR,
                               new_I_TU_CLE IN OUT TU_TYPE_UNITE.I_TU_CLE%TYPE);
  procedure SP_E_TE_TELEPHONE_UTIL(action       IN CHAR,
                                   new_I_TE_CLE IN OUT TE_TELEPHONE_UTIL.I_TE_CLE%TYPE);

  procedure SP_E_SQ_SEQUENCE(NEW_V_SQ_NOM      IN SQ_SEQUENCE.V_SQ_NOM%TYPE,
                             NEW_I_SQ_SEQUENCE IN SQ_SEQUENCE.I_SQ_SEQUENCE%TYPE);

END CARDEX_PILOTAGE;
/
CREATE OR REPLACE PACKAGE BODY CARDEX_PILOTAGE is
  procedure SP_REMPLIR_TRADUCTION(new_cle   IN TR_TRADUCTION.L_TR_CLE%TYPE,
                                  new_genre IN char) is
  begin
    FOR rec IN (select I_LA_CLE from LA_LANGUE) LOOP
      insert into TR_TRADUCTION
      values
        (new_cle,
         rec.I_LA_CLE,
         new_genre,
         NULL,
         NULL,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NULL,
         NULL);
    END LOOP;
  END SP_REMPLIR_TRADUCTION;
  procedure SP_E_EN_ENTITE(action               IN CHAR,
                           NEW_I_EN_CLE         IN OUT EN_ENTITE.I_EN_CLE%TYPE,
                           NEW_V_EN_ABREVIATION IN EN_ENTITE.V_EN_ABREVIATION%TYPE,
                           NEW_V_EN_ENTITE      IN EN_ENTITE.V_EN_ENTITE%TYPE,
                           NEW_V_EN_COMMENTAIRE IN EN_ENTITE.V_EN_COMMENTAIRE%TYPE) is
  begin
    if action = 'I' then
      insert into EN_ENTITE
        (I_EN_CLE, V_EN_ABREVIATION, V_EN_ENTITE, V_EN_COMMENTAIRE)
      values
        (NEW_I_EN_CLE,
         NEW_V_EN_ABREVIATION,
         NEW_V_EN_ENTITE,
         NEW_V_EN_COMMENTAIRE);
      commit;
    elsif action = 'U' then
      update EN_ENTITE
         set V_EN_ABREVIATION = NEW_V_EN_ABREVIATION,
             V_EN_ENTITE      = NEW_V_EN_ENTITE,
             V_EN_COMMENTAIRE = NEW_V_EN_COMMENTAIRE
       where I_EN_CLE = NEW_I_EN_CLE;
      commit;
    elsif action = 'D' then
      delete from EN_ENTITE where I_EN_CLE = NEW_I_EN_CLE;
      commit;
    end if;
  END SP_E_EN_ENTITE;
  procedure SP_E_LA_LANGUE(action       IN CHAR,
                           new_I_LA_CLE IN OUT LA_LANGUE.I_LA_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into LA_LANGUE (I_LA_CLE) values (new_I_LA_CLE);
      insert into TR_TRADUCTION
        select unique L_TR_CLE,
               new_I_LA_CLE,
               C_TR_GENRE_FICHIER,
               NULL,
               NULL,
               CARDEX_SPECIAL.g_usager,
               sysdate,
               NULL,
               NULL
          from TR_TRADUCTION;
      SP_REMPLIR_TRADUCTION(new_I_LA_CLE, 'LA');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_LA_CLE;
      delete from LA_LANGUE where I_LA_CLE = new_I_LA_CLE;
      commit;
    end if;
  END SP_E_LA_LANGUE;
  procedure SP_E_ST_STATUT(action                 IN CHAR,
                           new_I_ST_CLE           IN OUT ST_STATUT.I_ST_CLE%TYPE,
                           new_C_ST_GENRE_FICHIER IN ST_STATUT.C_ST_GENRE_FICHIER%TYPE,
                           new_C_ST_TYPE          IN ST_STATUT.C_ST_TYPE%TYPE) IS
  begin
    if action = 'I' then
      insert into ST_STATUT
        (I_ST_CLE, C_ST_GENRE_FICHIER, C_ST_TYPE)
      values
        (new_I_ST_CLE, new_C_ST_GENRE_FICHIER, new_C_ST_TYPE);
      SP_REMPLIR_TRADUCTION(new_I_ST_CLE, 'ST');
      commit;
    elsif action = 'U' then
      update ST_STATUT
         set C_ST_GENRE_FICHIER = new_C_ST_GENRE_FICHIER,
             C_ST_TYPE          = new_C_ST_TYPE
       where I_ST_CLE = new_I_ST_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_ST_CLE;
      delete from ST_STATUT where I_ST_CLE = new_I_ST_CLE;
      commit;
    end if;
  END SP_E_ST_STATUT;
  procedure SP_E_GE_GENRE(action          IN CHAR,
                          new_I_GE_CLE    IN OUT GE_GENRE.I_GE_CLE%TYPE,
                          new_I_CC_CLE_DEFAUT IN GE_GENRE.I_CC_CLE_DEFAUT%TYPE,                          
                          new_I_GE_ENTITE IN GE_GENRE.I_GE_ENTITE%TYPE) IS
  begin
    if action = 'I' then
      insert into GE_GENRE
        (I_GE_CLE, I_CC_CLE_DEFAUT, I_GE_ENTITE)
      values
        (new_I_GE_CLE, new_I_CC_CLE_DEFAUT, new_I_GE_ENTITE);
      SP_REMPLIR_TRADUCTION(new_I_GE_CLE, 'GE');
      commit;
    elsif action = 'U' then
      update GE_GENRE
         set I_GE_ENTITE = new_I_GE_ENTITE,
             I_CC_CLE_DEFAUT = new_I_CC_CLE_DEFAUT
       where I_GE_CLE = new_I_GE_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_GE_CLE;
      delete from GE_GENRE where I_GE_CLE = new_I_GE_CLE;
      commit;
    end if;
  END SP_E_GE_GENRE;
  procedure SP_E_PA_PAYS(action       IN CHAR,
                         new_I_PA_CLE IN OUT PA_PAYS.I_PA_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into PA_PAYS (I_PA_CLE) values (new_I_PA_CLE);
      SP_REMPLIR_TRADUCTION(new_I_PA_CLE, 'PA');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_PA_CLE;
      delete from PA_PAYS where I_PA_CLE = new_I_PA_CLE;
      commit;
    end if;
  END SP_E_PA_PAYS;
  procedure SP_E_SE_SEVERITE(action       IN CHAR,
                             new_I_SE_CLE IN OUT SE_SEVERITE.I_SE_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into SE_SEVERITE (I_SE_CLE) values (new_I_SE_CLE);
      SP_REMPLIR_TRADUCTION(new_I_SE_CLE, 'SE');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_SE_CLE;
      delete from SE_SEVERITE where I_SE_CLE = new_I_SE_CLE;
      commit;
    end if;
  END SP_E_SE_SEVERITE;
  procedure SP_E_NT_NATIONALITE(action       IN CHAR,
                                new_I_NT_CLE IN OUT NT_NATIONALITE.I_NT_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into NT_NATIONALITE (I_NT_CLE) values (new_I_NT_CLE);
      SP_REMPLIR_TRADUCTION(new_I_NT_CLE, 'NT');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_NT_CLE;
      delete from NT_NATIONALITE where I_NT_CLE = new_I_NT_CLE;
      commit;
    end if;
  END SP_E_NT_NATIONALITE;
  procedure SP_E_RA_RACE(action       IN CHAR,
                         new_I_RA_CLE IN OUT RA_RACE.I_RA_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into RA_RACE (I_RA_CLE) values (new_I_RA_CLE);
      SP_REMPLIR_TRADUCTION(new_I_RA_CLE, 'RA');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_RA_CLE;
      delete from RA_RACE where I_RA_CLE = new_I_RA_CLE;
      commit;
    end if;
  END SP_E_RA_RACE;
  procedure SP_E_CR_CARACTERISTIQUE(action       IN CHAR,
                                    new_L_CR_CLE IN OUT CR_CARACTERISTIQUE.L_CR_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into CR_CARACTERISTIQUE (L_CR_CLE) values (new_L_CR_CLE);
      SP_REMPLIR_TRADUCTION(new_L_CR_CLE, 'CR');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_L_CR_CLE;
      delete from CR_CARACTERISTIQUE where L_CR_CLE = new_L_CR_CLE;
      commit;
    end if;
  END SP_E_CR_CARACTERISTIQUE;
  procedure SP_E_RO_ROLE(action       IN CHAR,
                         new_I_RO_CLE IN OUT RO_ROLE.I_RO_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into RO_ROLE (I_RO_CLE) values (new_I_RO_CLE);
      SP_REMPLIR_TRADUCTION(new_I_RO_CLE, 'RO');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_RO_CLE;
      delete from RO_ROLE where I_RO_CLE = new_I_RO_CLE;
      commit;
    end if;
  END SP_E_RO_ROLE;
  procedure SP_E_CL_CLASSE(action       IN CHAR,
                           new_I_CL_CLE IN OUT CL_CLASSE.I_CL_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into CL_CLASSE (I_CL_CLE) values (new_I_CL_CLE);
      SP_REMPLIR_TRADUCTION(new_I_CL_CLE, 'CL');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_CL_CLE;
      delete from CL_CLASSE where I_CL_CLE = new_I_CL_CLE;
      commit;
    end if;
  END SP_E_CL_CLASSE;
  procedure SP_E_PT_PARTICULARITE(action       IN CHAR,
                                  new_I_PT_CLE IN OUT PT_PARTICULARITE.I_PT_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into PT_PARTICULARITE (I_PT_CLE) values (new_I_PT_CLE);
      SP_REMPLIR_TRADUCTION(new_I_PT_CLE, 'PT');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_PT_CLE;
      delete from PT_PARTICULARITE where I_PT_CLE = new_I_PT_CLE;
      commit;
    end if;
  END SP_E_PT_PARTICULARITE;
  procedure SP_E_MA_MARQUE(action               IN CHAR,
                           NEW_I_MA_CLE         IN OUT MA_MARQUE.I_MA_CLE%TYPE,
                           NEW_C_MA_ABREVIATION IN MA_MARQUE.C_MA_ABREVIATION%TYPE,
                           NEW_V_MA_MARQUE      IN MA_MARQUE.V_MA_MARQUE%TYPE) is
  begin
    if action = 'I' then
      insert into MA_MARQUE
        (I_MA_CLE,
         C_MA_ABREVIATION,
         V_MA_MARQUE,
         V_MA_CREE_PAR,
         D_MA_DATE_CREATION)
      values
        (NEW_I_MA_CLE,
         NEW_C_MA_ABREVIATION,
         NEW_V_MA_MARQUE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update MA_MARQUE
         set C_MA_ABREVIATION = NEW_C_MA_ABREVIATION,
             V_MA_MARQUE      = NEW_V_MA_MARQUE
       where I_MA_CLE = NEW_I_MA_CLE;
      commit;
    elsif action = 'D' then
      delete from MA_MARQUE where I_MA_CLE = NEW_I_MA_CLE;
      commit;
    end if;
  END SP_E_MA_MARQUE;
  procedure SP_E_LS_LANGUE(action       IN CHAR,
                           new_I_LS_CLE IN OUT LS_LANGUE.I_LS_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into LS_LANGUE (I_LS_CLE) values (new_I_LS_CLE);
      SP_REMPLIR_TRADUCTION(new_I_LS_CLE, 'LS');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_LS_CLE;
      delete from LS_LANGUE where I_LS_CLE = new_I_LS_CLE;
      commit;
    end if;
  END SP_E_LS_LANGUE;
  procedure SP_E_CC_CONFIDENTIALITE(action       IN CHAR,
                                    new_I_CC_CLE IN OUT CC_CONFIDENTIALITE.I_CC_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into CC_CONFIDENTIALITE (I_CC_CLE) values (new_I_CC_CLE);
      SP_REMPLIR_TRADUCTION(new_I_CC_CLE, 'CC');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_CC_CLE;
      delete from CC_CONFIDENTIALITE where I_CC_CLE = new_I_CC_CLE;
      commit;
    end if;
  END SP_E_CC_CONFIDENTIALITE;
  procedure SP_E_PE_PERIODE(action       IN CHAR,
                            new_I_PE_CLE IN OUT PE_PERIODE.I_PE_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into PE_PERIODE (I_PE_CLE) values (new_I_PE_CLE);
      SP_REMPLIR_TRADUCTION(new_I_PE_CLE, 'PE');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_PE_CLE;
      delete from PE_PERIODE where I_PE_CLE = new_I_PE_CLE;
      commit;
    end if;
  END SP_E_PE_PERIODE;
  procedure SP_E_TM_TYPE_MULTIMEDIA(action       IN CHAR,
                                    new_I_TM_CLE IN OUT TM_TYPE_MULTIMEDIA.I_TM_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into TM_TYPE_MULTIMEDIA (I_TM_CLE) values (new_I_TM_CLE);
      SP_REMPLIR_TRADUCTION(new_I_TM_CLE, 'TM');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TM_CLE;
      delete from TM_TYPE_MULTIMEDIA where I_TM_CLE = new_I_TM_CLE;
      commit;
    end if;
  END SP_E_TM_TYPE_MULTIMEDIA;
  procedure SP_E_SX_SEXE(action       IN CHAR,
                         new_I_SX_CLE IN OUT SX_SEXE.I_SX_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into SX_SEXE (I_SX_CLE) values (new_I_SX_CLE);
      SP_REMPLIR_TRADUCTION(new_I_SX_CLE, 'SX');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_SX_CLE;
      delete from SX_SEXE where I_SX_CLE = new_I_SX_CLE;
      commit;
    end if;
  END SP_E_SX_SEXE;
  procedure SP_E_CR_CARDINALITE(action       IN CHAR,
                                new_I_CR_CLE IN OUT CR_CARDINALITE.I_CR_CLE%TYPE,
                                new_L_SI_CLE IN SI_SITE.L_SI_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into CR_CARDINALITE
        (I_CR_CLE, L_SI_CLE)
      values
        (new_I_CR_CLE, new_L_SI_CLE);
      SP_REMPLIR_TRADUCTION(new_I_CR_CLE, 'CD');
      commit;
    elsif action = 'U' then
      update CR_CARDINALITE
         set L_SI_CLE = new_L_SI_CLE
       where I_CR_CLE = new_I_CR_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_CR_CLE;
      delete from CR_CARDINALITE where I_CR_CLE = new_I_CR_CLE;
      commit;
    end if;
  END SP_E_CR_CARDINALITE;
  procedure SP_E_SR_SERVICE(action       IN CHAR,
                            new_L_SR_CLE IN OUT SR_SERVICE.L_SR_CLE%TYPE,
                            new_L_SI_CLE IN SI_SITE.L_SI_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into SR_SERVICE
        (L_SR_CLE, L_SI_CLE)
      values
        (new_L_SR_CLE, new_L_SI_CLE);
      SP_REMPLIR_TRADUCTION(new_L_SR_CLE, 'CD');
      commit;
    elsif action = 'U' then
      update SR_SERVICE
         set L_SI_CLE = new_L_SI_CLE
       where L_SR_CLE = new_L_SR_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_L_SR_CLE;
      delete from SR_SERVICE where L_SR_CLE = new_L_SR_CLE;
      commit;
    end if;
  END SP_E_SR_SERVICE;
  procedure SP_E_OR_ORIENTATION(action       IN CHAR,
                                NEW_I_OR_CLE IN OUT OR_ORIENTATION.I_OR_CLE%TYPE,
                                NEW_L_SI_CLE IN SI_SITE.L_SI_CLE%TYPE,
                                NEW_L_SR_CLE IN SR_SERVICE.L_SR_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into OR_ORIENTATION
        (I_OR_CLE, L_SI_CLE, L_SR_CLE)
      values
        (NEW_I_OR_CLE, NEW_L_SI_CLE, NEW_L_SR_CLE);
      SP_REMPLIR_TRADUCTION(NEW_I_OR_CLE, 'OR');
      commit;
    elsif action = 'U' then
      UPDATE OR_ORIENTATION
         set L_SI_CLE = NEW_L_SI_CLE
       where I_OR_CLE = new_I_OR_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_OR_CLE;
      delete from OR_ORIENTATION where I_OR_CLE = new_I_OR_CLE;
      commit;
    end if;
  END SP_E_OR_ORIENTATION;
  procedure SP_E_NH_NIVEAU_HIERARCHIQUE(action       IN CHAR,
                                        new_I_NH_CLE IN OUT NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into NH_NIVEAU_HIERARCHIQUE (I_NH_CLE) values (new_I_NH_CLE);
      SP_REMPLIR_TRADUCTION(new_I_NH_CLE, 'NH');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_NH_CLE;
      delete from NH_NIVEAU_HIERARCHIQUE where I_NH_CLE = new_I_NH_CLE;
      commit;
    end if;
  END SP_E_NH_NIVEAU_HIERARCHIQUE;
  procedure SP_E_SI_SITE(action               IN CHAR,
                         NEW_L_SI_CLE         IN SI_SITE.L_SI_CLE%TYPE,
                         NEW_I_EN_CLE         IN SI_SITE.I_EN_CLE%TYPE,
                         NEW_V_SI_ABREVIATION IN SI_SITE.V_SI_ABREVIATION%TYPE,
                         NEW_V_SI_SITE        IN SI_SITE.V_SI_SITE%TYPE,
                         NEW_D_SI_SEQUENCE    IN SI_SITE.D_SI_SEQUENCE%TYPE,
                         NEW_N_SI_SEQUENCE    IN SI_SITE.N_SI_SEQUENCE%TYPE,
                         NEW_B_SI_ORIGINE     IN SI_SITE.B_SI_ORIGINE%TYPE,
                         NEW_B_SI_APPLICABLE  IN SI_SITE.B_SI_APPLICABLE%TYPE,
                         NEW_V_SI_HEURE       IN SI_SITE.V_SI_HEURE_DEBUT%TYPE) is
  begin
    if action = 'I' then
      insert into SI_SITE
        (L_SI_CLE,
         I_EN_CLE,
         V_SI_ABREVIATION,
         V_SI_SITE,
         B_SI_ORIGINE,
         B_SI_APPLICABLE,
         V_SI_CREE_PAR,
         D_SI_DATE_CREATION,
         V_SI_HEURE_DEBUT)
      values
        (NEW_L_SI_CLE,
         NEW_I_EN_CLE,
         NEW_V_SI_ABREVIATION,
         NEW_V_SI_SITE,
         NEW_B_SI_ORIGINE,
         NEW_B_SI_APPLICABLE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_V_SI_HEURE);
      commit;
    elsif action = 'U' then
      update SI_SITE
         set I_EN_CLE               = NEW_I_EN_CLE,
             V_SI_ABREVIATION       = NEW_V_SI_ABREVIATION,
             V_SI_SITE              = NEW_V_SI_SITE,
             D_SI_SEQUENCE          = NEW_D_SI_SEQUENCE,
             N_SI_SEQUENCE          = NEW_N_SI_SEQUENCE,
             B_SI_ORIGINE           = NEW_B_SI_ORIGINE,
             B_SI_APPLICABLE        = NEW_B_SI_APPLICABLE,
             V_SI_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_SI_DATE_MODIFICATION = sysdate,
             V_SI_HEURE_DEBUT       = NEW_V_SI_HEURE
       where L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from SI_SITE where L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
  END SP_E_SI_SITE;

  procedure SP_E_NA_NATURE(action               IN CHAR,
                           new_I_NA_CLE         IN OUT NA_NATURE.I_NA_CLE%TYPE,
                           new_I_GE_CLE         IN NA_NATURE.I_GE_CLE%TYPE,
                           new_B_NA_INSCRIPTION IN NA_NATURE.B_NA_INSCRIPTION%TYPE) IS
  begin
    if action = 'I' then
      insert into NA_NATURE
        (I_NA_CLE, I_GE_CLE, B_NA_INSCRIPTION)
      values
        (new_I_NA_CLE, new_I_GE_CLE, new_B_NA_INSCRIPTION);
      SP_REMPLIR_TRADUCTION(new_I_NA_CLE, 'NA');
      commit;
    elsif action = 'U' then
      update NA_NATURE
         set I_GE_CLE         = new_I_GE_CLE,
             B_NA_INSCRIPTION = new_B_NA_INSCRIPTION
       where I_NA_CLE = new_I_NA_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_NA_CLE;
      delete from NA_NATURE where I_NA_CLE = new_I_NA_CLE;
      commit;
    end if;
  END SP_E_NA_NATURE;
  procedure SP_E_TY_TYPE(action       IN CHAR,
                         new_I_TY_CLE IN OUT TY_TYPE.I_TY_CLE%TYPE,
                         new_I_NA_CLE IN TY_TYPE.I_NA_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into TY_TYPE
        (I_TY_CLE, I_NA_CLE)
      values
        (new_I_TY_CLE, new_I_NA_CLE);
      SP_REMPLIR_TRADUCTION(new_I_TY_CLE, 'TY');
      commit;
    elsif action = 'U' then
      update TY_TYPE
         set I_NA_CLE = new_I_NA_CLE
       where I_TY_CLE = new_I_TY_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TY_CLE;
      delete from TY_TYPE where I_TY_CLE = new_I_TY_CLE;
      commit;
    end if;
  END SP_E_TY_TYPE;
  procedure SP_E_CA_CATEGORIE(action       IN CHAR,
                              new_I_CA_CLE IN OUT CA_CATEGORIE.I_CA_CLE%TYPE,
                              new_I_TY_CLE IN CA_CATEGORIE.I_TY_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into CA_CATEGORIE
        (I_CA_CLE, I_TY_CLE)
      values
        (new_I_CA_CLE, new_I_TY_CLE);
      SP_REMPLIR_TRADUCTION(new_I_CA_CLE, 'CA');
      commit;
    elsif action = 'U' then
      update CA_CATEGORIE
         set I_TY_CLE = new_I_TY_CLE
       where I_CA_CLE = new_I_CA_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_CA_CLE;
      delete from CA_CATEGORIE where I_CA_CLE = new_I_CA_CLE;
      commit;
    end if;
  END SP_E_CA_CATEGORIE;
  procedure SP_E_PR_PROVINCE(action       IN CHAR,
                             new_L_PR_CLE IN OUT PR_PROVINCE.L_PR_CLE%TYPE,
                             new_I_PA_CLE IN PR_PROVINCE.I_PA_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into PR_PROVINCE
        (L_PR_CLE, I_PA_CLE)
      values
        (new_L_PR_CLE, new_I_PA_CLE);
      SP_REMPLIR_TRADUCTION(new_L_PR_CLE, 'PR');
      commit;
    elsif action = 'U' then
      update PR_PROVINCE
         set I_PA_CLE = new_I_PA_CLE
       where L_PR_CLE = new_L_PR_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_L_PR_CLE;
      delete from PR_PROVINCE where L_PR_CLE = new_L_PR_CLE;
      commit;
    end if;
  END SP_E_PR_PROVINCE;
  procedure SP_E_VI_VILLE(action         IN CHAR,
                          NEW_L_VI_CLE   IN OUT VI_VILLE.L_VI_CLE%TYPE,
                          NEW_L_PR_CLE   IN VI_VILLE.L_PR_CLE%TYPE,
                          NEW_V_VI_VILLE IN VI_VILLE.V_VI_VILLE%TYPE) is
  begin
    if action = 'I' then
      insert into VI_VILLE
        (L_VI_CLE, L_PR_CLE, V_VI_VILLE, V_VI_CREE_PAR, D_VI_DATE_CREATION)
      values
        (NEW_L_VI_CLE,
         NEW_L_PR_CLE,
         NEW_V_VI_VILLE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update VI_VILLE
         set V_VI_VILLE             = NEW_V_VI_VILLE,
             L_PR_CLE               = NEW_L_PR_CLE,
             V_VI_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_VI_DATE_MODIFICATION = sysdate
       where L_VI_CLE = NEW_L_VI_CLE;
      commit;
    elsif action = 'D' then
      delete from VI_VILLE where L_VI_CLE = NEW_L_VI_CLE;
      commit;
    end if;
  END SP_E_VI_VILLE;
  procedure SP_E_MD_MODELE(action          IN CHAR,
                           NEW_I_MD_CLE    IN OUT MD_MODELE.I_MD_CLE%TYPE,
                           NEW_I_MA_CLE    IN MD_MODELE.I_MA_CLE%TYPE,
                           NEW_V_MD_MODELE IN MD_MODELE.V_MD_MODELE%TYPE) is
  begin
    if action = 'I' then
      insert into MD_MODELE
        (I_MD_CLE,
         I_MA_CLE,
         V_MD_MODELE,
         V_MD_CREE_PAR,
         D_MD_DATE_CREATION)
      values
        (NEW_I_MD_CLE,
         NEW_I_MA_CLE,
         NEW_V_MD_MODELE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update MD_MODELE
         set V_MD_MODELE = NEW_V_MD_MODELE
       where I_MD_CLE = NEW_I_MD_CLE;
      commit;
    elsif action = 'D' then
      delete from MD_MODELE where I_MD_CLE = NEW_I_MD_CLE;
      commit;
    end if;
  END SP_E_MD_MODELE;
  

  procedure SP_E_TR_TRADUCTION(new_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                               new_I_LA_CLE         IN TR_TRADUCTION.I_LA_CLE%TYPE,
                               new_V_TR_ABREVIATION IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                               new_V_TR_DESCRIPTION IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE) is
  begin
    update TR_TRADUCTION
       set V_TR_ABREVIATION       = new_V_TR_ABREVIATION,
           V_TR_DESCRIPTION       = new_V_TR_DESCRIPTION,
           V_TR_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
           D_TR_DATE_MODIFICATION = sysdate
     where L_TR_CLE = new_L_TR_CLE
       and I_LA_CLE = new_I_LA_CLE;
  END SP_E_TR_TRADUCTION;
  procedure SP_E_GF_GENRE_FICHIER(action                 IN CHAR,
                                  NEW_C_GF_CLE           IN OUT GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                                  NEW_V_GF_GENRE_FICHIER IN GF_GENRE_FICHIER.V_GF_GENRE_FICHIER%TYPE) is
  begin
    if action = 'I' then
      insert into GF_GENRE_FICHIER
        (C_GF_CLE, V_GF_GENRE_FICHIER, V_GF_CREE_PAR, V_GF_DATE_CREATION)
      values
        (NEW_C_GF_CLE,
         NEW_V_GF_GENRE_FICHIER,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update GF_GENRE_FICHIER
         set V_GF_GENRE_FICHIER = NEW_V_GF_GENRE_FICHIER
       where C_GF_CLE = NEW_C_GF_CLE;
      commit;
    elsif action = 'D' then
      delete from GF_GENRE_FICHIER where C_GF_CLE = NEW_C_GF_CLE;
      commit;
    end if;
  END SP_E_GF_GENRE_FICHIER;
  procedure SP_E_RF_REF_VIDEO(action       IN CHAR,
                              new_I_RF_CLE IN OUT RF_REFERENCE_VIDEO.I_RF_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into RF_REFERENCE_VIDEO (I_RF_CLE) values (new_I_RF_CLE);
      SP_REMPLIR_TRADUCTION(new_I_RF_CLE, 'RF');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_RF_CLE;
      delete from RF_REFERENCE_VIDEO where I_RF_CLE = new_I_RF_CLE;
      commit;
    end if;
  END SP_E_RF_REF_VIDEO;
  procedure SP_E_TC_TYPE_ACTIVITE(action       IN CHAR,
                                  new_I_TC_CLE IN OUT TC_TYPE_ACTIVITE.I_TC_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into TC_TYPE_ACTIVITE (I_TC_CLE) values (new_I_TC_CLE);
      SP_REMPLIR_TRADUCTION(new_I_TC_CLE, 'TC');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TC_CLE;
      delete from TC_TYPE_ACTIVITE where I_TC_CLE = new_I_TC_CLE;
      commit;
    end if;
  END SP_E_TC_TYPE_ACTIVITE;
  procedure SP_E_FO_FONDE(action       IN CHAR,
                          new_I_FO_CLE IN OUT FO_FONDE.I_FO_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into FO_FONDE (I_FO_CLE) values (new_I_FO_CLE);
      SP_REMPLIR_TRADUCTION(new_I_FO_CLE, 'FO');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_FO_CLE;
      delete from FO_FONDE where I_FO_CLE = new_I_FO_CLE;
      commit;
    end if;
  END SP_E_FO_FONDE;
  procedure SP_E_TN_TYPE_CONSIGNATION(action               IN CHAR,
                                      new_I_TN_CLE         IN OUT TN_TYPE_CONSIGNATION.I_TN_CLE%TYPE,
                                      new_B_TN_APPROBATION IN OUT TN_TYPE_CONSIGNATION.B_TN_APPROBATION%TYPE) IS
  begin
    if action = 'I' then
      insert into TN_TYPE_CONSIGNATION
        (I_TN_CLE, B_TN_APPROBATION)
      values
        (new_I_TN_CLE, new_B_TN_APPROBATION);
      SP_REMPLIR_TRADUCTION(new_I_TN_CLE, 'TN');
      commit;
    elsif action = 'U' then
      update TN_TYPE_CONSIGNATION
         set B_TN_APPROBATION = new_B_TN_APPROBATION
       where I_TN_CLE = new_I_TN_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TN_CLE;
      delete from TN_TYPE_CONSIGNATION where I_TN_CLE = new_I_TN_CLE;
      commit;
    end if;
  END SP_E_TN_TYPE_CONSIGNATION;
  procedure SP_E_DE_DEVISE(action       IN CHAR,
                           new_I_DE_CLE IN OUT DE_DEVISE.I_DE_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into DE_DEVISE (I_DE_CLE) values (new_I_DE_CLE);
      SP_REMPLIR_TRADUCTION(new_I_DE_CLE, 'DE');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_DE_CLE;
      delete from DE_DEVISE where I_DE_CLE = new_I_DE_CLE;
      commit;
    end if;
  END SP_E_DE_DEVISE;
  procedure SP_E_DN_DENOMINATION(action       IN CHAR,
                                 new_I_DN_CLE IN OUT DN_DENOMINATION.I_DN_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into DN_DENOMINATION (I_DN_CLE) values (new_I_DN_CLE);
      SP_REMPLIR_TRADUCTION(new_I_DN_CLE, 'DN');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_DN_CLE;
      delete from DN_DENOMINATION where I_DN_CLE = new_I_DN_CLE;
      commit;
    end if;
  END SP_E_DN_DENOMINATION;
  procedure SP_E_RG_REGROUPEMENT(action             IN CHAR,
                                 NEW_I_RG_CLE       IN OUT RG_REGROUPEMENT.I_RG_CLE%TYPE,
                                 NEW_NAME           RG_REGROUPEMENT.NAME%TYPE,
                                 NEW_I_RG_QUOTA     RG_REGROUPEMENT.I_RG_QUOTA%TYPE,
                                 NEW_I_RG_QUOTA_MIN RG_REGROUPEMENT.I_RG_QUOTA_MIN%TYPE,
                                 NEW_L_IN_SECTEUR   RG_REGROUPEMENT.L_IN_SECTEUR%TYPE) is
  begin
    if action = 'I' then
      insert into RG_REGROUPEMENT
        (I_RG_CLE, NAME, I_RG_QUOTA, I_RG_QUOTA_MIN, L_IN_SECTEUR)
      values
        (NEW_I_RG_CLE,
         NEW_NAME,
         NEW_I_RG_QUOTA,
         NEW_I_RG_QUOTA_MIN,
         NEW_L_IN_SECTEUR);
      SP_REMPLIR_TRADUCTION(NEW_I_RG_CLE, 'RG');
      commit;
    elsif action = 'U' then
      update RG_REGROUPEMENT
         set NAME           = NEW_NAME,
             I_RG_QUOTA     = NEW_I_RG_QUOTA,
             I_RG_QUOTA_MIN = NEW_I_RG_QUOTA_MIN,
             L_IN_SECTEUR   = NEW_L_IN_SECTEUR
       where I_RG_CLE = NEW_I_RG_CLE;
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = NEW_I_RG_CLE;
      delete from RG_REGROUPEMENT where I_RG_CLE = NEW_I_RG_CLE;
      delete from LRG_LIEN_REGROUPEMENT where I_RG_CLE = NEW_I_RG_CLE;
      commit;
    end if;
  END SP_E_RG_REGROUPEMENT;
  procedure SP_E_LRG_LIEN_REGROUPEMENT(action       IN CHAR,
                                       NEW_I_RG_CLE IN LRG_LIEN_REGROUPEMENT.I_RG_CLE%TYPE,
                                       NEW_I_CA_CLE IN LRG_LIEN_REGROUPEMENT.I_CA_CLE%TYPE) IS
  BEGIN
    if action = 'I' then
      insert into LRG_LIEN_REGROUPEMENT
        (I_RG_CLE, I_CA_CLE)
      values
        (NEW_I_RG_CLE, NEW_I_CA_CLE);
      commit;
    elsif action = 'D' then
      delete from LRG_LIEN_REGROUPEMENT
       where I_RG_CLE = NEW_I_RG_CLE
         AND I_CA_CLE = NEW_I_CA_CLE;
      commit;
    end if;
  END SP_E_LRG_LIEN_REGROUPEMENT;
  procedure SP_E_TR_TYPE_RUE(action       IN CHAR,
                             new_I_TR_CLE IN OUT TR_TYPE_RUE.I_TR_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into TR_TYPE_RUE (I_TR_CLE) values (new_I_TR_CLE);
      SP_REMPLIR_TRADUCTION(new_I_TR_CLE, 'TR');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TR_CLE;
      delete from TR_TYPE_RUE where I_TR_CLE = new_I_TR_CLE;
      commit;
    end if;
  END SP_E_TR_TYPE_RUE;
  procedure SP_E_CT_CARDINALITE(action       IN CHAR,
                                new_I_CT_CLE IN OUT CT_CARDINALITE.I_CT_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into CT_CARDINALITE (I_CT_CLE) values (new_I_CT_CLE);
      SP_REMPLIR_TRADUCTION(new_I_CT_CLE, 'CT');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_CT_CLE;
      delete from CT_CARDINALITE where I_CT_CLE = new_I_CT_CLE;
      commit;
    end if;
  END SP_E_CT_CARDINALITE;
  procedure SP_E_TU_TYPE_UNITE(action       IN CHAR,
                               new_I_TU_CLE IN OUT TU_TYPE_UNITE.I_TU_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into TU_TYPE_UNITE (I_TU_CLE) values (new_I_TU_CLE);
      SP_REMPLIR_TRADUCTION(new_I_TU_CLE, 'TU');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TU_CLE;
      delete from TU_TYPE_UNITE where I_TU_CLE = new_I_TU_CLE;
      commit;
    end if;
  END SP_E_TU_TYPE_UNITE;
  procedure SP_E_TE_TELEPHONE_UTIL(action       IN CHAR,
                                   new_I_TE_CLE IN OUT TE_TELEPHONE_UTIL.I_TE_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into TE_TELEPHONE_UTIL (I_TE_CLE) values (new_I_TE_CLE);
      SP_REMPLIR_TRADUCTION(new_I_TE_CLE, 'TE');
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_I_TE_CLE;
      delete from TE_TELEPHONE_UTIL where I_TE_CLE = new_I_TE_CLE;
      commit;
    end if;
  END SP_E_TE_TELEPHONE_UTIL;

  procedure SP_E_SQ_SEQUENCE(NEW_V_SQ_NOM      IN SQ_SEQUENCE.V_SQ_NOM%TYPE,
                             NEW_I_SQ_SEQUENCE IN SQ_SEQUENCE.I_SQ_SEQUENCE%TYPE) IS
  begin
    update SQ_SEQUENCE
       set I_SQ_SEQUENCE = NEW_I_SQ_SEQUENCE
     where V_SQ_NOM = NEW_V_SQ_NOM;
  END SP_E_SQ_SEQUENCE;

END CARDEX_PILOTAGE;
/
