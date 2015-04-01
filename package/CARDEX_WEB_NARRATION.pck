CREATE OR REPLACE PACKAGE CARDEX_WEB_NARRATION is
  procedure SPW_E_CO_COMMENTAIRE2(action                        IN CHAR,
                                  NEW_L_CO_CLE                  IN OUT CO_COMMENTAIRE2.L_CO_CLE%TYPE,
                                  NEW_L_SI_CLE                  IN OUT CO_COMMENTAIRE2.L_SI_CLE%TYPE,
                                  NEW_L_CO_REFERENCE            IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                  NEW_V_CO_COMMENTAIRE          IN CO_COMMENTAIRE2.V_CO_COMMENTAIRE%TYPE,
                                  NEW_R_CO_MONTANT              IN CO_COMMENTAIRE2.R_CO_MONTANT%TYPE,
                                  NEW_L_CO_REF_SITE             IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                  NEW_C_CO_REF_GENRE            IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                  NEW_I_CC_COMMENTAIRE          IN CO_COMMENTAIRE2.I_CC_COMMENTAIRE%TYPE,
                                  NEW_I_NH_COMMENTAIRE          IN CO_COMMENTAIRE2.I_NH_COMMENTAIRE%TYPE,
                                  NEW_I_CC_CREATEUR             IN CO_COMMENTAIRE2.I_CC_CREATEUR%TYPE,
                                  NEW_I_NH_CREATEUR             IN CO_COMMENTAIRE2.I_NH_CREATEUR%TYPE,
                                  NEW_V_CO_APPROBATEUR          IN CO_COMMENTAIRE2.V_CO_APPROBATEUR%TYPE,
                                  NEW_I_CC_APPROBATEUR          IN CO_COMMENTAIRE2.I_CC_APPROBATEUR%TYPE,
                                  NEW_I_NH_APPROBATEUR          IN CO_COMMENTAIRE2.I_NH_APPROBATEUR%TYPE,
                                  NEW_D_CO_APPROBATION          IN CO_COMMENTAIRE2.D_CO_APPROBATION%TYPE,
                                  NEW_V_CO_REFERENCE            IN CO_COMMENTAIRE2.V_CO_REFERENCE%TYPE,
                                  NEW_V_CO_RAPPORTE_PAR         IN CO_COMMENTAIRE2.V_CO_RAPPORTE_PAR%TYPE,
                                  NEW_V_CO_TEMPS                IN CO_COMMENTAIRE2.V_CO_TEMPS%TYPE,
                                  NEW_L_CO_GABARIT              IN CO_COMMENTAIRE2.L_CO_GABARIT%TYPE);
  TYPE DTS_CO_COMMENTAIRE2 IS REF CURSOR RETURN CO_COMMENTAIRE2%ROWTYPE;
  PROCEDURE SPW_L_Co_Commentaire2(NEW_L_CO_REFERENCE IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                  NEW_L_CO_REF_SITE  IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                  NEW_C_CO_REF_GENRE IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                  rc1                IN OUT DTS_CO_COMMENTAIRE2);
  PROCEDURE SPW_RAP_UNI_Co_Commentaire2(NEW_L_CO_REFERENCE IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                        NEW_L_CO_REF_SITE  IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                        NEW_C_CO_REF_GENRE IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                        NEW_SECTION        IN CO_COMMENTAIRE2.V_CO_REFERENCE%TYPE,
                                        rc1                IN OUT DTS_CO_COMMENTAIRE2);
  PROCEDURE SPW_L_Co_Commentaire2(NEW_L_CO_CLE IN CO_COMMENTAIRE2.L_CO_CLE%TYPE,
                                  NEW_L_SI_CLE IN CO_COMMENTAIRE2.L_SI_CLE%TYPE,
                                  rc1          IN OUT DTS_CO_COMMENTAIRE2);
  TYPE REC_RAP_CO_COMMENTAIRE2 IS RECORD(
    L_CO_CLE             CO_COMMENTAIRE2.L_CO_CLE%TYPE,
    L_SI_CLE             CO_COMMENTAIRE2.L_SI_CLE%TYPE,
    L_CO_REFERENCE       CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
    V_CO_COMMENTAIRE     CO_COMMENTAIRE2.V_CO_COMMENTAIRE%TYPE,
    V_CO_CREE_PAR        CO_COMMENTAIRE2.V_CO_CREE_PAR%TYPE,
    D_CO_DATE_CREATION   CO_COMMENTAIRE2.D_CO_DATE_CREATION%TYPE,
    V_CO_MODIFIE_PAR     CO_COMMENTAIRE2.V_CO_MODIFIE_PAR%TYPE,
    D_CO_MODIFICATION    CO_COMMENTAIRE2.D_CO_MODIFICATION%TYPE,
    R_CO_MONTANT         CO_COMMENTAIRE2.R_CO_MONTANT%TYPE,
    L_CO_REF_SITE        CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
    C_CO_REF_GENRE       CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
    I_CC_COMMENTAIRE     CO_COMMENTAIRE2.I_CC_COMMENTAIRE%TYPE,
    I_NH_COMMENTAIRE     CO_COMMENTAIRE2.I_NH_COMMENTAIRE%TYPE,
    I_CC_CREATEUR        CO_COMMENTAIRE2.I_CC_CREATEUR%TYPE,
    I_NH_CREATEUR        CO_COMMENTAIRE2.I_NH_CREATEUR%TYPE,
    V_CO_APPROBATEUR     CO_COMMENTAIRE2.V_CO_APPROBATEUR%TYPE,
    I_CC_APPROBATEUR     CO_COMMENTAIRE2.I_CC_APPROBATEUR%TYPE,
    I_NH_APPROBATEUR     CO_COMMENTAIRE2.I_NH_APPROBATEUR%TYPE,
    D_CO_APPROBATION     CO_COMMENTAIRE2.D_CO_APPROBATION%TYPE,
    V_CO_REFERENCE       CO_COMMENTAIRE2.V_CO_REFERENCE%TYPE,
    V_CO_RAPPORTE_PAR    CO_COMMENTAIRE2.V_CO_RAPPORTE_PAR%TYPE,
    V_CO_TEMPS           CO_COMMENTAIRE2.V_CO_TEMPS%TYPE,
    CLOB_CO_TEXTE_FORMAT CO_COMMENTAIRE2.CLOB_CO_TEXTE_FORMAT%TYPE,
    CLOB_CO_TEXTE_NORMAL CO_COMMENTAIRE2.CLOB_CO_TEXTE_NORMAL%TYPE,
    DESCRIPTION          IN_INTERVENANT.NAME%TYPE);
  TYPE DTS_RAP_CO_COMMENTAIRE2 IS REF CURSOR RETURN REC_RAP_CO_COMMENTAIRE2;
  PROCEDURE SPW_RAP_Co_Commentaire2(NEW_L_CO_REFERENCE IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                    NEW_L_CO_REF_SITE  IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                    NEW_C_CO_REF_GENRE IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                    rc1                IN OUT DTS_RAP_CO_COMMENTAIRE2);

  procedure SPW_I_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE             IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE             IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE       IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE        IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE       IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           NEW_CLOB_NR_TEXTE_FORMAT IN NR_NARRATION_TRANSITOIRE.CLOB_NR_TEXTE_FORMAT%TYPE);

  procedure SPW_E_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE             IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE             IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE       IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE        IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE       IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           NEW_CLOB_NR_TEXTE_FORMAT IN NR_NARRATION_TRANSITOIRE.CLOB_NR_TEXTE_FORMAT%TYPE);

  procedure SPW_E_NR_ASSIGNER_CLE(NEW_L_NR_CLE       IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                  NEW_L_SI_CLE       IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                  NEW_L_NR_REFERENCE IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                  NEW_L_NR_REF_SITE  IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                  NEW_C_NR_REF_GENRE IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE);

  TYPE DTS_NR_NARRATION_TRANSITOIRE IS REF CURSOR RETURN NR_NARRATION_TRANSITOIRE%ROWTYPE;
  procedure SPW_L_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE       IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE       IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE  IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           rc1                IN OUT DTS_NR_NARRATION_TRANSITOIRE);

  procedure SPW_S_NR_NARRATION_TRANSITOIRE;

  procedure SPW_E_APPROBATION_AUTO(NEW_DATE_DEBUT IN CO_COMMENTAIRE2.D_CO_DATE_CREATION%TYPE,
                                   NEW_DATE_FIN   IN CO_COMMENTAIRE2.D_CO_DATE_CREATION%TYPE);

END CARDEX_WEB_NARRATION;
/
CREATE OR REPLACE PACKAGE BODY CARDEX_WEB_NARRATION is
  --Écriture des narrations
 procedure SPW_E_CO_COMMENTAIRE2(action                        IN CHAR,
                                  NEW_L_CO_CLE                  IN OUT CO_COMMENTAIRE2.L_CO_CLE%TYPE,
                                  NEW_L_SI_CLE                  IN OUT CO_COMMENTAIRE2.L_SI_CLE%TYPE,
                                  NEW_L_CO_REFERENCE            IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                  NEW_V_CO_COMMENTAIRE          IN CO_COMMENTAIRE2.V_CO_COMMENTAIRE%TYPE,
                                  NEW_R_CO_MONTANT              IN CO_COMMENTAIRE2.R_CO_MONTANT%TYPE,
                                  NEW_L_CO_REF_SITE             IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                  NEW_C_CO_REF_GENRE            IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                  NEW_I_CC_COMMENTAIRE          IN CO_COMMENTAIRE2.I_CC_COMMENTAIRE%TYPE,
                                  NEW_I_NH_COMMENTAIRE          IN CO_COMMENTAIRE2.I_NH_COMMENTAIRE%TYPE,
                                  NEW_I_CC_CREATEUR             IN CO_COMMENTAIRE2.I_CC_CREATEUR%TYPE,
                                  NEW_I_NH_CREATEUR             IN CO_COMMENTAIRE2.I_NH_CREATEUR%TYPE,
                                  NEW_V_CO_APPROBATEUR          IN CO_COMMENTAIRE2.V_CO_APPROBATEUR%TYPE,
                                  NEW_I_CC_APPROBATEUR          IN CO_COMMENTAIRE2.I_CC_APPROBATEUR%TYPE,
                                  NEW_I_NH_APPROBATEUR          IN CO_COMMENTAIRE2.I_NH_APPROBATEUR%TYPE,
                                  NEW_D_CO_APPROBATION          IN CO_COMMENTAIRE2.D_CO_APPROBATION%TYPE,
                                  NEW_V_CO_REFERENCE            IN CO_COMMENTAIRE2.V_CO_REFERENCE%TYPE,
                                  NEW_V_CO_RAPPORTE_PAR         IN CO_COMMENTAIRE2.V_CO_RAPPORTE_PAR%TYPE,
                                  NEW_V_CO_TEMPS                IN CO_COMMENTAIRE2.V_CO_TEMPS%TYPE,
                                  NEW_L_CO_GABARIT              IN CO_COMMENTAIRE2.L_CO_GABARIT%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_CO_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into CO_COMMENTAIRE2
        (L_CO_CLE,
         L_SI_CLE,
         L_CO_REFERENCE,
         V_CO_COMMENTAIRE,
         V_CO_CREE_PAR,
         D_CO_DATE_CREATION,
         R_CO_MONTANT,
         L_CO_REF_SITE,
         C_CO_REF_GENRE,
         I_CC_COMMENTAIRE,
         I_NH_COMMENTAIRE,
         I_CC_CREATEUR,
         I_NH_CREATEUR,
         V_CO_APPROBATEUR,
         I_CC_APPROBATEUR,
         I_NH_APPROBATEUR,
         D_CO_APPROBATION,
         V_CO_REFERENCE,
         V_CO_RAPPORTE_PAR,
         V_CO_TEMPS,
         CLOB_CO_TEXTE_FORMAT,
         CLOB_CO_TEXTE_NORMAL,
         L_CO_GABARIT)
      values
        (NEW_L_CO_CLE,
         NEW_L_SI_CLE,
         NEW_L_CO_REFERENCE,
         NEW_V_CO_COMMENTAIRE,
         CARDEX_SPECIAL.g_usager,
         SYSDATE,
         NEW_R_CO_MONTANT,
         NEW_L_CO_REF_SITE,
         NEW_C_CO_REF_GENRE,
         NEW_I_CC_COMMENTAIRE,
         NEW_I_NH_COMMENTAIRE,
         NEW_I_CC_CREATEUR,
         NEW_I_NH_CREATEUR,
         NEW_V_CO_APPROBATEUR,
         NEW_I_CC_APPROBATEUR,
         NEW_I_NH_APPROBATEUR,
         NEW_D_CO_APPROBATION,
         NEW_V_CO_REFERENCE,
         NEW_V_CO_RAPPORTE_PAR,
         NEW_V_CO_TEMPS,
         EMPTY_CLOB(),
         EMPTY_CLOB(),
         NEW_L_CO_GABARIT);
      commit;
      -- Inserer narration journal pré approuvé
    elsif action = 'IA' then
      select seq_dossier.nextval into NEW_L_CO_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into CO_COMMENTAIRE2
        (L_CO_CLE,
         L_SI_CLE,
         L_CO_REFERENCE,
         V_CO_COMMENTAIRE,
         V_CO_CREE_PAR,
         D_CO_DATE_CREATION,
         R_CO_MONTANT,
         L_CO_REF_SITE,
         C_CO_REF_GENRE,
         I_CC_COMMENTAIRE,
         I_NH_COMMENTAIRE,
         I_CC_CREATEUR,
         I_NH_CREATEUR,
         V_CO_APPROBATEUR,
         I_CC_APPROBATEUR,
         I_NH_APPROBATEUR,
         D_CO_APPROBATION,
         V_CO_REFERENCE,
         V_CO_RAPPORTE_PAR,
         V_CO_TEMPS,
         CLOB_CO_TEXTE_FORMAT,
         CLOB_CO_TEXTE_NORMAL)
      values
        (NEW_L_CO_CLE,
         NEW_L_SI_CLE,
         NEW_L_CO_REFERENCE,
         NEW_V_CO_COMMENTAIRE,
         CARDEX_SPECIAL.g_usager,
         SYSDATE,
         NEW_R_CO_MONTANT,
         NEW_L_CO_REF_SITE,
         NEW_C_CO_REF_GENRE,
         NEW_I_CC_COMMENTAIRE,
         NEW_I_NH_COMMENTAIRE,
         NEW_I_CC_CREATEUR,
         NEW_I_NH_CREATEUR,
         NEW_V_CO_APPROBATEUR,
         NEW_I_CC_APPROBATEUR,
         NEW_I_NH_APPROBATEUR,
         SYSDATE,
         NEW_V_CO_REFERENCE,
         NEW_V_CO_RAPPORTE_PAR,
         NEW_V_CO_TEMPS,
         EMPTY_CLOB(),
         EMPTY_CLOB());
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_NARRATION(NEW_L_CO_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update CO_COMMENTAIRE2
         set V_CO_COMMENTAIRE          = NEW_V_CO_COMMENTAIRE,
             V_CO_MODIFIE_PAR          = CARDEX_SPECIAL.g_usager,
             D_CO_MODIFICATION         = sysdate,
             R_CO_MONTANT              = NEW_R_CO_MONTANT,
             I_CC_COMMENTAIRE          = NEW_I_CC_COMMENTAIRE,
             I_NH_COMMENTAIRE          = NEW_I_NH_COMMENTAIRE,
             I_CC_CREATEUR             = NEW_I_CC_CREATEUR,
             I_NH_CREATEUR             = NEW_I_NH_CREATEUR,
             V_CO_APPROBATEUR          = NEW_V_CO_APPROBATEUR,
             I_CC_APPROBATEUR          = NEW_I_CC_APPROBATEUR,
             I_NH_APPROBATEUR          = NEW_I_NH_APPROBATEUR,
             D_CO_APPROBATION          = NEW_D_CO_APPROBATION,
             V_CO_REFERENCE            = NEW_V_CO_REFERENCE,
             V_CO_RAPPORTE_PAR         = NEW_V_CO_RAPPORTE_PAR,
             V_CO_TEMPS                = NEW_V_CO_TEMPS,
             L_CO_GABARIT              = NEW_L_CO_GABARIT
       where L_CO_CLE = NEW_L_CO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Cas spécial de mise à jour (approbation ou permettre modification du
      --commentaire).
    elsif action = 'M' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_NARRATION(NEW_L_CO_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update CO_COMMENTAIRE2
         set I_NH_COMMENTAIRE = NEW_I_NH_COMMENTAIRE,
             V_CO_APPROBATEUR = NEW_V_CO_APPROBATEUR,
             I_CC_APPROBATEUR = NEW_I_CC_APPROBATEUR,
             I_NH_APPROBATEUR = NEW_I_NH_APPROBATEUR,
             D_CO_APPROBATION = NEW_D_CO_APPROBATION
       where L_CO_CLE = NEW_L_CO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_NARRATION(NEW_L_CO_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      delete from CO_COMMENTAIRE2
       where L_CO_CLE = NEW_L_CO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'CO',
                                 NEW_L_CO_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_CO_REF_GENRE,
                                 NEW_L_CO_REFERENCE,
                                 NEW_L_CO_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SPW_E_CO_COMMENTAIRE2',
                              TRUE);
  END SPW_E_CO_COMMENTAIRE2;
  --Lecture de tous les commentaires d'un onglet (ordre décroissant)
  PROCEDURE SPW_L_Co_Commentaire2(NEW_L_CO_REFERENCE IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                  NEW_L_CO_REF_SITE  IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                  NEW_C_CO_REF_GENRE IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                  rc1                IN OUT DTS_CO_COMMENTAIRE2) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_CO_CLE,
             P.L_SI_CLE,
             P.L_CO_REFERENCE,
             P.V_CO_COMMENTAIRE,
             P.V_CO_CREE_PAR,
             P.D_CO_DATE_CREATION,
             P.V_CO_MODIFIE_PAR,
             P.D_CO_MODIFICATION,
             P.R_CO_MONTANT,
             P.L_CO_REF_SITE,
             P.C_CO_REF_GENRE,
             P.I_CC_COMMENTAIRE,
             P.I_NH_COMMENTAIRE,
             P.I_CC_CREATEUR,
             P.I_NH_CREATEUR,
             P.V_CO_APPROBATEUR,
             P.I_CC_APPROBATEUR,
             P.I_NH_APPROBATEUR,
             P.D_CO_APPROBATION,
             P.V_CO_REFERENCE,
             P.V_CO_RAPPORTE_PAR,
             P.V_CO_TEMPS,
             P.CLOB_CO_TEXTE_FORMAT,
             P.CLOB_CO_TEXTE_NORMAL,
             L_CO_GABARIT
        FROM CO_COMMENTAIRE2 P
       WHERE P.L_CO_REF_SITE = NEW_L_CO_REF_SITE
         AND P.C_CO_REF_GENRE = NEW_C_CO_REF_GENRE
         AND P.L_CO_REFERENCE = NEW_L_CO_REFERENCE
         AND (P.V_CO_CREE_PAR = Cardex_Special.g_usager OR
             NVL(P.I_CC_COMMENTAIRE, Cardex_Special.g_code_confidentialite) <=
             Cardex_Special.g_code_confidentialite)
       order by P.D_CO_DATE_CREATION DESC;
  END SPW_L_Co_Commentaire2;
  --Lecture de tous les commentaires pour un rapport (ordre croissant)
  --(2007-08-01) Sans les narrations du rapport uniformisé
  PROCEDURE SPW_RAP_Co_Commentaire2(NEW_L_CO_REFERENCE IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                    NEW_L_CO_REF_SITE  IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                    NEW_C_CO_REF_GENRE IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                    rc1                IN OUT DTS_RAP_CO_COMMENTAIRE2) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_CO_CLE,
             P.L_SI_CLE,
             P.L_CO_REFERENCE,
             P.V_CO_COMMENTAIRE,
             P.V_CO_CREE_PAR,
             P.D_CO_DATE_CREATION,
             P.V_CO_MODIFIE_PAR,
             P.D_CO_MODIFICATION,
             P.R_CO_MONTANT,
             P.L_CO_REF_SITE,
             P.C_CO_REF_GENRE,
             P.I_CC_COMMENTAIRE,
             P.I_NH_COMMENTAIRE,
             P.I_CC_CREATEUR,
             P.I_NH_CREATEUR,
             P.V_CO_APPROBATEUR,
             P.I_CC_APPROBATEUR,
             P.I_NH_APPROBATEUR,
             P.D_CO_APPROBATION,
             P.V_CO_REFERENCE,
             P.V_CO_RAPPORTE_PAR,
             P.V_CO_TEMPS,
             P.CLOB_CO_TEXTE_FORMAT,
             P.CLOB_CO_TEXTE_NORMAL,
             i.v_in_nom || ', ' || i.v_in_prenom || ' (' ||
             tr.v_tr_description || ') ' as description
        FROM CO_COMMENTAIRE2 P, IN_INTERVENANT I, TR_TRADUCTION TR
       WHERE P.L_CO_REF_SITE = NEW_L_CO_REF_SITE
         AND P.C_CO_REF_GENRE = NEW_C_CO_REF_GENRE
         AND P.L_CO_REFERENCE = NEW_L_CO_REFERENCE
         AND (P.V_CO_CREE_PAR = Cardex_Special.g_usager OR
             NVL(P.I_CC_COMMENTAIRE, Cardex_Special.g_code_confidentialite) <=
             Cardex_Special.g_code_confidentialite)
         AND P.V_CO_RAPPORTE_PAR = I.NAME
         and i.l_in_secteur = tr.l_tr_cle
         and tr.i_la_cle = 1
         AND (p.v_co_reference < '9' or p.v_co_reference is null)
       order by P.D_CO_DATE_CREATION ASC;
  END SPW_RAP_Co_Commentaire2;
  --Lecture des narrations pour le rapport uniformisé
  PROCEDURE SPW_RAP_UNI_Co_Commentaire2(NEW_L_CO_REFERENCE IN CO_COMMENTAIRE2.L_CO_REFERENCE%TYPE,
                                        NEW_L_CO_REF_SITE  IN CO_COMMENTAIRE2.L_CO_REF_SITE%TYPE,
                                        NEW_C_CO_REF_GENRE IN CO_COMMENTAIRE2.C_CO_REF_GENRE%TYPE,
                                        NEW_SECTION        IN CO_COMMENTAIRE2.V_CO_REFERENCE%TYPE,
                                        rc1                IN OUT DTS_CO_COMMENTAIRE2) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_CO_CLE,
             P.L_SI_CLE,
             P.L_CO_REFERENCE,
             P.V_CO_COMMENTAIRE,
             P.V_CO_CREE_PAR,
             P.D_CO_DATE_CREATION,
             P.V_CO_MODIFIE_PAR,
             P.D_CO_MODIFICATION,
             P.R_CO_MONTANT,
             P.L_CO_REF_SITE,
             P.C_CO_REF_GENRE,
             P.I_CC_COMMENTAIRE,
             P.I_NH_COMMENTAIRE,
             P.I_CC_CREATEUR,
             P.I_NH_CREATEUR,
             P.V_CO_APPROBATEUR,
             P.I_CC_APPROBATEUR,
             P.I_NH_APPROBATEUR,
             P.D_CO_APPROBATION,
             P.V_CO_REFERENCE,
             P.V_CO_RAPPORTE_PAR,
             P.V_CO_TEMPS,
             P.CLOB_CO_TEXTE_FORMAT,
             P.CLOB_CO_TEXTE_NORMAL,
             L_CO_GABARIT
        FROM CO_COMMENTAIRE2 P
       WHERE P.L_CO_REF_SITE = NEW_L_CO_REF_SITE
         AND P.C_CO_REF_GENRE = NEW_C_CO_REF_GENRE
         AND P.L_CO_REFERENCE = NEW_L_CO_REFERENCE
         AND (P.V_CO_CREE_PAR = Cardex_Special.g_usager OR
             NVL(P.I_CC_COMMENTAIRE, Cardex_Special.g_code_confidentialite) <=
             Cardex_Special.g_code_confidentialite)
         and p.v_co_reference like new_section
       order by P.V_CO_REFERENCE ASC;
  END SPW_RAP_UNI_Co_Commentaire2;
  --Lecture d'un commentaire particulier
  PROCEDURE SPW_L_Co_Commentaire2(NEW_L_CO_CLE IN CO_COMMENTAIRE2.L_CO_CLE%TYPE,
                                  NEW_L_SI_CLE IN CO_COMMENTAIRE2.L_SI_CLE%TYPE,
                                  rc1          IN OUT DTS_CO_COMMENTAIRE2) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_CO_CLE,
             P.L_SI_CLE,
             P.L_CO_REFERENCE,
             P.V_CO_COMMENTAIRE,
             P.V_CO_CREE_PAR,
             P.D_CO_DATE_CREATION,
             P.V_CO_MODIFIE_PAR,
             P.D_CO_MODIFICATION,
             P.R_CO_MONTANT,
             P.L_CO_REF_SITE,
             P.C_CO_REF_GENRE,
             P.I_CC_COMMENTAIRE,
             P.I_NH_COMMENTAIRE,
             P.I_CC_CREATEUR,
             P.I_NH_CREATEUR,
             P.V_CO_APPROBATEUR,
             P.I_CC_APPROBATEUR,
             P.I_NH_APPROBATEUR,
             P.D_CO_APPROBATION,
             P.V_CO_REFERENCE,
             P.V_CO_RAPPORTE_PAR,
             P.V_CO_TEMPS,
             P.CLOB_CO_TEXTE_FORMAT,
             P.CLOB_CO_TEXTE_NORMAL,
             L_CO_GABARIT
        FROM CO_COMMENTAIRE2 P
       WHERE P.L_CO_CLE = NEW_L_CO_CLE
         AND P.L_SI_CLE = NEW_L_SI_CLE;
  END SPW_L_Co_Commentaire2;

  procedure SPW_I_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE             IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE             IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE       IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE        IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE       IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           NEW_CLOB_NR_TEXTE_FORMAT IN NR_NARRATION_TRANSITOIRE.CLOB_NR_TEXTE_FORMAT%TYPE) IS
  begin
    insert into NR_NARRATION_TRANSITOIRE
      (L_NR_CLE,
       L_SI_CLE,
       L_NR_REFERENCE,
       L_NR_REF_SITE,
       C_NR_REF_GENRE,
       V_NR_CHANGE_PAR,
       CLOB_NR_TEXTE_FORMAT,
       D_NR_DATE_CHANGEMENT)
    values
      (NEW_L_NR_CLE,
       NEW_L_SI_CLE,
       NEW_L_NR_REFERENCE,
       NEW_L_NR_REF_SITE,
       NEW_C_NR_REF_GENRE,
       CARDEX_SPECIAL.g_usager,
       NEW_CLOB_NR_TEXTE_FORMAT,
       SYSDATE);
  END SPW_I_NR_NARRATION_TRANSITOIRE;

  procedure SPW_E_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE             IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE             IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE       IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE        IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE       IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           NEW_CLOB_NR_TEXTE_FORMAT IN NR_NARRATION_TRANSITOIRE.CLOB_NR_TEXTE_FORMAT%TYPE) IS
  begin
    UPDATE NR_NARRATION_TRANSITOIRE
       SET CLOB_NR_TEXTE_FORMAT = NEW_CLOB_NR_TEXTE_FORMAT,
           D_NR_DATE_CHANGEMENT = SYSDATE
     WHERE L_NR_CLE = NEW_L_NR_CLE
       AND L_SI_CLE = NEW_L_SI_CLE
       AND L_NR_REFERENCE = NEW_L_NR_REFERENCE
       AND L_NR_REF_SITE = NEW_L_NR_REF_SITE
       AND C_NR_REF_GENRE = NEW_C_NR_REF_GENRE
       AND V_NR_CHANGE_PAR = CARDEX_SPECIAL.g_usager;
  END SPW_E_NR_NARRATION_TRANSITOIRE;

  procedure SPW_E_NR_ASSIGNER_CLE(NEW_L_NR_CLE       IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                  NEW_L_SI_CLE       IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                  NEW_L_NR_REFERENCE IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                  NEW_L_NR_REF_SITE  IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                  NEW_C_NR_REF_GENRE IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE) IS
  begin
    UPDATE NR_NARRATION_TRANSITOIRE
       SET L_NR_CLE             = NEW_L_NR_CLE,
           L_SI_CLE             = NEW_L_SI_CLE,
           D_NR_DATE_CHANGEMENT = SYSDATE
     WHERE L_NR_CLE = 0
       AND L_SI_CLE = 0
       AND L_NR_REFERENCE = NEW_L_NR_REFERENCE
       AND L_NR_REF_SITE = NEW_L_NR_REF_SITE
       AND C_NR_REF_GENRE = NEW_C_NR_REF_GENRE
       AND V_NR_CHANGE_PAR = CARDEX_SPECIAL.g_usager;
  END SPW_E_NR_ASSIGNER_CLE;

  procedure SPW_L_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE       IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE       IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE  IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           rc1                IN OUT DTS_NR_NARRATION_TRANSITOIRE) IS
  begin
    OPEN rc1 FOR
      SELECT *
        FROM NR_NARRATION_TRANSITOIRE
       WHERE L_NR_CLE = NEW_L_NR_CLE
         AND L_SI_CLE = NEW_L_SI_CLE
         AND L_NR_REFERENCE = NEW_L_NR_REFERENCE
         AND L_NR_REF_SITE = NEW_L_NR_REF_SITE
         AND C_NR_REF_GENRE = NEW_C_NR_REF_GENRE
         AND V_NR_CHANGE_PAR = CARDEX_SPECIAL.g_usager;
  END SPW_L_NR_NARRATION_TRANSITOIRE;

  procedure SPW_S_NR_NARRATION_TRANSITOIRE IS
  begin
    DELETE NR_NARRATION_TRANSITOIRE
     WHERE D_NR_DATE_CHANGEMENT < SYSDATE - 7;
  END SPW_S_NR_NARRATION_TRANSITOIRE;

  procedure SPW_E_APPROBATION_AUTO(NEW_DATE_DEBUT IN CO_COMMENTAIRE2.D_CO_DATE_CREATION%TYPE,
                                   NEW_DATE_FIN   IN CO_COMMENTAIRE2.D_CO_DATE_CREATION%TYPE) IS
  begin                             
      update CO_COMMENTAIRE2
         set I_NH_COMMENTAIRE = I_NH_CREATEUR,
             V_CO_APPROBATEUR = V_CO_CREE_PAR,
             I_CC_APPROBATEUR = I_CC_CREATEUR,
             I_NH_APPROBATEUR = I_NH_CREATEUR,
             D_CO_APPROBATION = sysdate
     where L_CO_GABARIT = 1336
         AND D_CO_DATE_CREATION BETWEEN NEW_DATE_DEBUT and NEW_DATE_FIN;
     commit;
  END SPW_E_APPROBATION_AUTO;                     

END CARDEX_WEB_NARRATION;
/
