CREATE OR REPLACE PACKAGE CARDEX_LIEN is
  procedure SP_E_SIS_SITE_INSCRIPTION(action        IN CHAR,
                                      NEW_L_IS_CLE  IN OUT SIS_SITE_INSCRIPTION.L_IS_CLE%TYPE,
                                      NEW_L_SI_CLE  IN OUT SIS_SITE_INSCRIPTION.L_SI_CLE%TYPE,
                                      NEW_L_IS_SITE IN OUT SIS_SITE_INSCRIPTION.L_IS_SITE%TYPE);
  procedure SP_E_IN_INTERVENANT(action                   IN CHAR,
                                NEW_L_IN_CLE             IN OUT IN_INTERVENANT.L_IN_CLE%TYPE,
                                NEW_L_SI_CLE             IN OUT IN_INTERVENANT.L_SI_CLE%TYPE,
                                NEW_NAME                 IN IN_INTERVENANT.NAME%TYPE,
                                NEW_I_LA_CLE             IN IN_INTERVENANT.I_LA_CLE%TYPE,
                                NEW_I_ST_CLE             IN IN_INTERVENANT.I_ST_CLE%TYPE,
                                NEW_I_CC_CLE             IN IN_INTERVENANT.I_CC_CLE%TYPE,
                                NEW_I_NH_CLE             IN IN_INTERVENANT.I_NH_CLE%TYPE,
                                NEW_V_IN_NO_EMPLOYE      IN IN_INTERVENANT.V_IN_NO_EMPLOYE%TYPE,
                                NEW_V_IN_NOM             IN IN_INTERVENANT.V_IN_NOM%TYPE,
                                NEW_V_IN_PRENOM          IN IN_INTERVENANT.V_IN_PRENOM%TYPE,
                                NEW_V_IN_COMMENTAIRE     IN IN_INTERVENANT.V_IN_COMMENTAIRE%TYPE,
                                NEW_C_IN_MOT_PASSE       IN IN_INTERVENANT.C_IN_MOT_PASSE%TYPE,
                                NEW_L_IN_TEMPS_ACTIF     IN IN_INTERVENANT.L_IN_TEMPS_ACTIF%TYPE,
                                NEW_L_IN_TEMPS_CONNEXION IN IN_INTERVENANT.L_IN_TEMPS_CONNEXION%TYPE);
  procedure SP_E_LME_LIEN_MISE_EVAL(action                    IN CHAR,
                                    NEW_L_LME_CLE             IN OUT LME_LIEN_MISE_EVAL.L_LME_CLE%TYPE,
                                    NEW_L_SI_CLE              IN OUT LME_LIEN_MISE_EVAL.L_SI_CLE%TYPE,
                                    NEW_L_EV_CLE              IN LME_LIEN_MISE_EVAL.L_EV_CLE%TYPE,
                                    NEW_L_EV_SI_CLE           IN LME_LIEN_MISE_EVAL.L_EV_SI_CLE%TYPE,
                                    NEW_L_LME_TYPE_JEU        IN LME_LIEN_MISE_EVAL.L_LME_TYPE_JEU%TYPE,
                                    NEW_V_LME_COMMENTAIRE_JEU IN LME_LIEN_MISE_EVAL.V_LME_COMMENTAIRE_JEU%TYPE,
                                    NEW_L_LME_MISE_MINIMUM    IN LME_LIEN_MISE_EVAL.L_LME_MISE_MINIMUM%TYPE,
                                    NEW_L_LME_MISE_MAXIMUM    IN LME_LIEN_MISE_EVAL.L_LME_MISE_MAXIMUM%TYPE,
                                    NEW_L_LME_MISE_MOYENNE    IN LME_LIEN_MISE_EVAL.L_LME_MISE_MOYENNE%TYPE,
                                    NEW_L_LME_GAIN_PERTE      IN LME_LIEN_MISE_EVAL.L_LME_GAIN_PERTE%TYPE,
                                    NEW_L_LME_MISE_TOTAL      IN LME_LIEN_MISE_EVAL.L_LME_MISE_TOTAL%TYPE,
                                    NEW_L_LME_TEMPS_JEU_MIN   IN LME_LIEN_MISE_EVAL.L_LME_TEMPS_JEU_MIN%TYPE,
                                    NEW_L_LME_TEMPS_JEU_MAX   IN LME_LIEN_MISE_EVAL.L_LME_TEMPS_JEU_MAX%TYPE,
                                    NEW_L_LME_TEMPS_JEU_TOTAL IN LME_LIEN_MISE_EVAL.L_LME_TEMPS_JEU_TOTAL%TYPE);
  procedure SP_E_LMM_LIEN_MULTIMEDIA(action              IN CHAR,
                                     NEW_L_LMM_CLE       IN OUT LMM_LIEN_MULTIMEDIA.L_LMM_CLE%TYPE,
                                     NEW_L_SI_CLE        IN OUT LMM_LIEN_MULTIMEDIA.L_SI_CLE%TYPE,
                                     NEW_L_LMM_REFERENCE IN LMM_LIEN_MULTIMEDIA.L_LMM_REFERENCE%TYPE,
                                     NEW_L_MM_CLE        IN LMM_LIEN_MULTIMEDIA.L_MM_CLE%TYPE,
                                     NEW_L_LMM_REF_SITE  IN LMM_LIEN_MULTIMEDIA.L_LMM_REF_SITE%TYPE,
                                     NEW_C_LMM_REF_GENRE IN LMM_LIEN_MULTIMEDIA.C_LMM_REF_GENRE%TYPE,
                                     NEW_L_MM_REF_SITE   IN LMM_LIEN_MULTIMEDIA.L_MM_REF_SITE%TYPE,
                                     NEW_I_CC_CLE        IN LMM_LIEN_MULTIMEDIA.I_CC_CLE%TYPE);

  procedure SP_E_LJD_LIEN_JEU_DOSSIER(action              IN CHAR,
                                      NEW_L_LJD_CLE       IN OUT LJD_LIEN_JEU_DOSSIER.L_LJD_CLE%TYPE,
                                      NEW_L_SI_CLE        IN OUT LJD_LIEN_JEU_DOSSIER.L_SI_CLE%TYPE,
                                      NEW_L_DO_CLE        IN LJD_LIEN_JEU_DOSSIER.L_DO_CLE%TYPE,
                                      NEW_I_JE_CLE        IN LJD_LIEN_JEU_DOSSIER.I_JE_CLE%TYPE,
                                      NEW_L_LJD_REF_SITE  IN LJD_LIEN_JEU_DOSSIER.L_LJD_REF_SITE%TYPE,
                                      NEW_C_LJD_REF_GENRE IN LJD_LIEN_JEU_DOSSIER.C_LJD_REF_GENRE%TYPE);

  procedure SP_E_LJV_JEU_EVAL(action             IN CHAR,
                              NEW_L_LJV_CLE      IN OUT LJV_JEU_EVAL.L_LJV_CLE%TYPE,
                              NEW_L_SI_CLE       IN OUT LJV_JEU_EVAL.L_SI_CLE%TYPE,
                              NEW_L_LME_CLE      IN LJV_JEU_EVAL.L_LME_CLE%TYPE,
                              NEW_L_LME_REF_SITE IN LJV_JEU_EVAL.L_LME_REF_SITE%TYPE,
                              NEW_L_JE_CLE       IN LJV_JEU_EVAL.L_JE_CLE%TYPE);

  procedure SP_E_LEE_ETAT_EVAL(action            IN CHAR,
                               NEW_L_LEE_CLE     IN OUT LEE_LIEN_ETAT_EVAL.L_LEE_CLE%TYPE,
                               NEW_L_SI_CLE      IN OUT LEE_LIEN_ETAT_EVAL.L_SI_CLE%TYPE,
                               NEW_L_EV_CLE      IN LEE_LIEN_ETAT_EVAL.L_EV_CLE%TYPE,
                               NEW_L_EV_REF_SITE IN LEE_LIEN_ETAT_EVAL.L_EV_REF_SITE%TYPE,
                               NEW_L_EE_CLE      IN LEE_LIEN_ETAT_EVAL.L_EE_CLE%TYPE);

  procedure SP_E_LPE_PROPOS_EVAL(action            IN CHAR,
                                 NEW_L_LPE_CLE     IN OUT LPE_LIEN_PROPOS_EVAL.L_LPE_CLE%TYPE,
                                 NEW_L_SI_CLE      IN OUT LPE_LIEN_PROPOS_EVAL.L_SI_CLE%TYPE,
                                 NEW_L_EV_CLE      IN LPE_LIEN_PROPOS_EVAL.L_EV_CLE%TYPE,
                                 NEW_L_EV_REF_SITE IN LPE_LIEN_PROPOS_EVAL.L_EV_REF_SITE%TYPE,
                                 NEW_L_PE_CLE      IN LPE_LIEN_PROPOS_EVAL.L_PE_CLE%TYPE);

  procedure SP_E_LDC_LIEN_DOSSIER_CAT(action       IN CHAR,
                                      NEW_L_DO_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.L_DO_CLE%TYPE,
                                      NEW_L_SI_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.L_SI_CLE%TYPE,
                                      NEW_I_CA_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.I_CA_CLE%TYPE);
  procedure SP_E_LSC_CARACTERISTIQUE(action              IN CHAR,
                                     NEW_L_LSC_CLE       IN OUT LSC_CARACTERISTIQUE.L_LSC_CLE%TYPE,
                                     NEW_L_SI_CLE        IN OUT LSC_CARACTERISTIQUE.L_SI_CLE%TYPE,
                                     NEW_L_CR_CLE        IN LSC_CARACTERISTIQUE.L_CR_CLE%TYPE,
                                     NEW_L_LSC_REFERENCE IN LSC_CARACTERISTIQUE.L_LSC_REFERENCE%TYPE,
                                     NEW_L_LSC_REF_SITE  IN LSC_CARACTERISTIQUE.L_LSC_REF_SITE%TYPE,
                                     NEW_C_LSC_REF_GENRE IN LSC_CARACTERISTIQUE.C_LSC_REF_GENRE%TYPE);
  procedure SP_E_AD_ADRESSE(action                  IN CHAR,
                            NEW_L_AD_CLE            IN OUT AD_ADRESSE.L_AD_CLE%TYPE,
                            NEW_L_SI_CLE            IN OUT AD_ADRESSE.L_SI_CLE%TYPE,
                            NEW_L_AD_REFERENCE      IN AD_ADRESSE.L_AD_REFERENCE%TYPE,
                            NEW_V_AD_ADRESSE        IN AD_ADRESSE.V_AD_ADRESSE%TYPE,
                            NEW_V_AD_ADRESSE2       IN AD_ADRESSE.V_AD_ADRESSE2%TYPE,
                            NEW_V_AD_NUM_MUNICIPAL  IN AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE,
                            NEW_L_AD_TYPE_RUE       IN AD_ADRESSE.L_AD_TYPE_RUE%TYPE,
                            NEW_V_AD_NOM_RUE        IN AD_ADRESSE.V_AD_NOM_RUE%TYPE,
                            NEW_L_AD_POINT_CARDINAL IN AD_ADRESSE.L_AD_POINT_CARDINAL%TYPE,
                            NEW_L_AD_TYPE_UNITE     IN AD_ADRESSE.L_AD_TYPE_UNITE%TYPE,
                            NEW_V_AD_NO_UNITE       IN AD_ADRESSE.V_AD_NO_UNITE%TYPE,
                            NEW_V_AD_ADRESSE_POST   IN AD_ADRESSE.V_AD_ADRESSE_POST%TYPE,
                            NEW_V_AD_ELECTRONIQUE_1 IN AD_ADRESSE.V_AD_ELECTRONIQUE_1%TYPE,
                            NEW_V_AD_ELECTRONIQUE_2 IN AD_ADRESSE.V_AD_ELECTRONIQUE_2%TYPE,
                            NEW_I_PA_CLE            IN AD_ADRESSE.I_PA_CLE%TYPE,
                            NEW_L_PR_CLE            IN AD_ADRESSE.L_PR_CLE%TYPE,
                            NEW_L_VI_CLE            IN AD_ADRESSE.L_VI_CLE%TYPE,
                            NEW_V_AD_CODE_POSTAL    IN AD_ADRESSE.V_AD_CODE_POSTAL%TYPE,
                            NEW_I_TE_CLE_1          IN AD_ADRESSE.I_TE_CLE_1%TYPE,
                            NEW_I_AD_TEL_PERIODE_1  IN AD_ADRESSE.I_AD_TEL_PERIODE_1%TYPE,
                            NEW_V_AD_TELEPHONE_1    IN AD_ADRESSE.V_AD_TELEPHONE_1%TYPE,
                            NEW_I_TE_CLE_2          IN AD_ADRESSE.I_TE_CLE_2%TYPE,
                            NEW_I_AD_TEL_PERIODE_2  IN AD_ADRESSE.I_AD_TEL_PERIODE_2%TYPE,
                            NEW_V_AD_TELEPHONE_2    IN AD_ADRESSE.V_AD_TELEPHONE_2%TYPE,
                            NEW_I_TE_CLE_3          IN AD_ADRESSE.I_TE_CLE_3%TYPE,
                            NEW_I_AD_TEL_PERIODE_3  IN AD_ADRESSE.I_AD_TEL_PERIODE_3%TYPE,
                            NEW_V_AD_TELEPHONE_3    IN AD_ADRESSE.V_AD_TELEPHONE_3%TYPE,
                            NEW_I_ST_CLE            IN AD_ADRESSE.I_ST_CLE%TYPE,
                            NEW_V_AD_COMMENTAIRE    IN AD_ADRESSE.V_AD_COMMENTAIRE%TYPE,
                            NEW_L_AD_REF_SITE       IN AD_ADRESSE.L_AD_REF_SITE%TYPE,
                            NEW_C_AD_REF_GENRE      IN AD_ADRESSE.C_AD_REF_GENRE%TYPE,
                            NEW_B_AD_IND_RDD        IN AD_ADRESSE.B_AD_IND_RDD%TYPE);
  procedure SP_E_AD_ADRESSE_RDD(action                  IN CHAR,
                                NEW_L_AD_CLE            IN OUT AD_ADRESSE.L_AD_CLE%TYPE,
                                NEW_L_SI_CLE            IN OUT AD_ADRESSE.L_SI_CLE%TYPE,
                                NEW_L_AD_REFERENCE      IN AD_ADRESSE.L_AD_REFERENCE%TYPE,
                                NEW_V_AD_ADRESSE        IN AD_ADRESSE.V_AD_ADRESSE%TYPE,
                                NEW_V_AD_ADRESSE2       IN AD_ADRESSE.V_AD_ADRESSE2%TYPE,
                                NEW_V_AD_NUM_MUNICIPAL  IN AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE,
                                NEW_L_AD_TYPE_RUE       IN AD_ADRESSE.L_AD_TYPE_RUE%TYPE,
                                NEW_V_AD_NOM_RUE        IN AD_ADRESSE.V_AD_NOM_RUE%TYPE,
                                NEW_L_AD_POINT_CARDINAL IN AD_ADRESSE.L_AD_POINT_CARDINAL%TYPE,
                                NEW_L_AD_TYPE_UNITE     IN AD_ADRESSE.L_AD_TYPE_UNITE%TYPE,
                                NEW_V_AD_NO_UNITE       IN AD_ADRESSE.V_AD_NO_UNITE%TYPE,
                                NEW_V_AD_ADRESSE_POST   IN AD_ADRESSE.V_AD_ADRESSE_POST%TYPE,
                                NEW_V_AD_ELECTRONIQUE_1 IN AD_ADRESSE.V_AD_ELECTRONIQUE_1%TYPE,
                                NEW_V_AD_ELECTRONIQUE_2 IN AD_ADRESSE.V_AD_ELECTRONIQUE_2%TYPE,
                                NEW_I_PA_CLE            IN AD_ADRESSE.I_PA_CLE%TYPE,
                                NEW_L_PR_CLE            IN AD_ADRESSE.L_PR_CLE%TYPE,
                                NEW_L_VI_CLE            IN AD_ADRESSE.L_VI_CLE%TYPE,
                                NEW_V_AD_CODE_POSTAL    IN AD_ADRESSE.V_AD_CODE_POSTAL%TYPE,
                                NEW_I_TE_CLE_1          IN AD_ADRESSE.I_TE_CLE_1%TYPE,
                                NEW_V_AD_TELEPHONE_1    IN AD_ADRESSE.V_AD_TELEPHONE_1%TYPE,
                                NEW_I_TE_CLE_2          IN AD_ADRESSE.I_TE_CLE_2%TYPE,
                                NEW_V_AD_TELEPHONE_2    IN AD_ADRESSE.V_AD_TELEPHONE_2%TYPE,
                                NEW_I_TE_CLE_3          IN AD_ADRESSE.I_TE_CLE_3%TYPE,
                                NEW_V_AD_TELEPHONE_3    IN AD_ADRESSE.V_AD_TELEPHONE_3%TYPE,
                                NEW_I_ST_CLE            IN AD_ADRESSE.I_ST_CLE%TYPE,
                                NEW_V_AD_COMMENTAIRE    IN AD_ADRESSE.V_AD_COMMENTAIRE%TYPE,
                                NEW_L_AD_REF_SITE       IN AD_ADRESSE.L_AD_REF_SITE%TYPE,
                                NEW_C_AD_REF_GENRE      IN AD_ADRESSE.C_AD_REF_GENRE%TYPE,
                                NEW_B_AD_IND_RDD        IN AD_ADRESSE.B_AD_IND_RDD%TYPE);

  procedure SP_E_LDD_LIEN_DOSSIER(action                    IN CHAR,
                                  NEW_L_LDD_CLE             IN OUT LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
                                  NEW_L_SI_CLE              IN OUT LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
                                  NEW_L_DO_CLE              IN LDD_LIEN_DOSSIER.L_DO_CLE%TYPE,
                                  NEW_L_LDD_DOSSIER_ASSOCIE IN LDD_LIEN_DOSSIER.L_LDD_DOSSIER_ASSOCIE%TYPE,
                                  NEW_I_RO_CLE              IN LDD_LIEN_DOSSIER.I_RO_CLE%TYPE,
                                  NEW_I_TL_CLE              IN LDD_LIEN_DOSSIER.I_TL_CLE%TYPE,
                                  NEW_L_DO_SITE             IN LDD_LIEN_DOSSIER.L_DO_SITE%TYPE,
                                  NEW_C_DO_GENRE            IN LDD_LIEN_DOSSIER.C_DO_GENRE%TYPE,
                                  NEW_L_LDD_SITE            IN LDD_LIEN_DOSSIER.L_LDD_SITE%TYPE,
                                  NEW_C_LDD_GENRE           IN LDD_LIEN_DOSSIER.C_LDD_GENRE%TYPE);
  procedure SP_E_LPV_LIEN_PARTICULARITE(action              IN CHAR,
                                        NEW_L_LPV_CLE       IN OUT LPV_LIEN_PARTICULARITE.L_LPV_CLE%TYPE,
                                        NEW_L_SI_CLE        IN OUT LPV_LIEN_PARTICULARITE.L_SI_CLE%TYPE,
                                        NEW_L_VE_CLE        IN LPV_LIEN_PARTICULARITE.L_VE_CLE%TYPE,
                                        NEW_I_PT_CLE        IN LPV_LIEN_PARTICULARITE.I_PT_CLE%TYPE,
                                        NEW_L_LPV_REF_SITE  IN LPV_LIEN_PARTICULARITE.L_LPV_REF_SITE%TYPE,
                                        NEW_C_LPV_REF_GENRE IN LPV_LIEN_PARTICULARITE.C_LPV_REF_GENRE%TYPE);
  procedure SP_E_IS_INSCRIPTION(action                        IN CHAR,
                                NEW_L_IS_CLE                  IN OUT IS_INSCRIPTION.L_IS_CLE%TYPE,
                                NEW_L_SI_CLE                  IN OUT IS_INSCRIPTION.L_SI_CLE%TYPE,
                                NEW_L_IS_REFERENCE            IN IS_INSCRIPTION.L_IS_REFERENCE%TYPE,
                                NEW_V_IS_DUREE                IN IS_INSCRIPTION.V_IS_DUREE%TYPE,
                                NEW_D_IS_DATE_DEBUT           IN IS_INSCRIPTION.D_IS_DATE_DEBUT%TYPE,
                                NEW_D_IS_DATE_FIN             IN IS_INSCRIPTION.D_IS_DATE_FIN%TYPE,
                                NEW_I_IS_PERIODE              IN IS_INSCRIPTION.I_IS_PERIODE%TYPE,
                                NEW_I_IS_SITE                 IN IS_INSCRIPTION.I_IS_SITE%TYPE,
                                NEW_I_ST_CLE                  IN IS_INSCRIPTION.I_ST_CLE%TYPE,
                                NEW_C_IS_REF_GENRE            IN IS_INSCRIPTION.C_IS_REF_GENRE%TYPE,
                                NEW_L_IS_REF_SITE             IN IS_INSCRIPTION.L_IS_REF_SITE%TYPE,
                                NEW_B_IS_AIDE_INITIALE        IN IS_INSCRIPTION.B_IS_AIDE_INITIALE%TYPE,
                                NEW_B_IS_AIDE_IMMEDIATE       IN IS_INSCRIPTION.B_IS_AIDE_IMMEDIATE%TYPE,
                                NEW_D_IS_COURRIEL_AIDE        IS_INSCRIPTION.D_IS_COURRIEL_AIDE%TYPE,
                                NEW_D_IS_COURRIEL_SUIVI       IS_INSCRIPTION.D_IS_COURRIEL_SUIVI%TYPE,
                                NEW_V_IS_INTERVENANT_FINAL    IS_INSCRIPTION.V_IS_INTERVENANT_FINAL%TYPE,
                                NEW_V_IS_INTERVENANT_INITIAL  IS_INSCRIPTION.V_IS_INTERVENANT_INITIAL%TYPE,
                                NEW_D_IS_RENCONTRE_FINALE     IS_INSCRIPTION.D_IS_RENCONTRE_FINALE%TYPE,
                                NEW_D_IS_RENCONTRE_INITIALE   IS_INSCRIPTION.D_IS_RENCONTRE_INITIALE%TYPE,
                                NEW_B_IS_TOUS_SITE_APPLICABLE IS_INSCRIPTION.B_IS_TOUS_SITE_APPLICABLE%TYPE);

  PROCEDURE SP_E_LMM_LIEN_MULTIMEDIA(action               IN CHAR,
                                     new_l_mm_si_cle      IN OUT NUMBER,
                                     new_l_mm_cle         IN OUT NUMBER,
                                     new_i_tm_cle         IN NUMBER,
                                     new_v_mm_description IN VARCHAR2,
                                     new_l_lmm_cle        IN OUT NUMBER,
                                     new_l_lmm_si_cle     IN OUT NUMBER,
                                     new_l_lmm_reference  IN NUMBER,
                                     new_l_lmm_site_ref   IN NUMBER,
                                     new_c_lmm_genre_ref  IN CHAR,
                                     new_l_em_cle         IN OUT NUMBER,
                                     new_l_em_si_cle      IN OUT NUMBER,
                                     new_v_mm_extension   IN VARCHAR2,
                                     new_i_cc_cle         IN NUMBER);
  PROCEDURE SP_E_LMM_LIEN_MULTIMEDIA(action          IN CHAR,
                                     new_c_genre_ref IN CHAR,
                                     new_l_cle       IN NUMBER,
                                     new_l_si_cle    IN NUMBER,
                                     new_i_cc_cle    IN NUMBER);
  PROCEDURE SP_U_LMM_LIEN_MULTIMEDIA(new_l_lmm_cle      IN NUMBER,
                                     new_l_si_cle       IN NUMBER,
                                     new_i_cc_cle       IN NUMBER);
  PROCEDURE SP_E_SV_SUIVI(action                  IN CHAR,
                          NEW_L_SV_CLE            IN OUT SV_SUIVI.L_SV_CLE%TYPE,
                          NEW_L_SI_CLE            IN OUT SI_SITE.L_SI_CLE%TYPE,
                          NEW_L_SV_REFERENCE      IN SV_SUIVI.L_SV_REFERENCE%TYPE,
                          NEW_L_SV_REF_SITE       IN SV_SUIVI.L_SV_REF_SITE%TYPE,
                          NEW_C_SV_REF_GENRE      IN SV_SUIVI.C_SV_REF_GENRE%TYPE,
                          NEW_V_SV_SUIVI          IN SV_SUIVI.V_SV_SUIVI%TYPE,
                          NEW_I_TC_CLE            IN TC_TYPE_ACTIVITE.I_TC_CLE%TYPE,
                          NEW_I_ST_CLE            IN ST_STATUT.I_ST_CLE%TYPE,
                          NEW_D_SV_DATE_PREVUE    IN SV_SUIVI.D_SV_DATE_PREVUE%TYPE,
                          NEW_D_SV_DATE_COMPLETEE IN SV_SUIVI.D_SV_DATE_COMPLETEE%TYPE,
                          NEW_L_SV_PO_ORIGINE     IN PO_POSTE.L_PO_CLE%TYPE,
                          NEW_V_SV_DEMANDEUR      IN IN_INTERVENANT.NAME%TYPE,
                          NEW_V_SV_INTERVENANT    IN IN_INTERVENANT.NAME%TYPE,
                          NEW_L_SV_PO_ASSIGNE     IN PO_POSTE.L_PO_CLE%TYPE,
                          NEW_V_SV_CREE_PAR       IN IN_INTERVENANT.NAME%TYPE,
                          NEW_I_CC_SUIVI          IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          NEW_I_NH_SUIVI          IN NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE,
                          NEW_I_CC_CREATEUR       IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          NEW_I_NH_CREATEUR       IN NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE,
                          NEW_D_SV_DATE_CREATION  IN SV_SUIVI.D_SV_DATE_CREATION%TYPE,
                          NEW_V_SV_MODIFIE_PAR    IN IN_INTERVENANT.NAME%TYPE,
                          NEW_D_SV_MODIFICATION   IN SV_SUIVI.D_SV_MODIFICATION%TYPE,
                          NEW_V_SV_APPROBATEUR    IN IN_INTERVENANT.NAME%TYPE,
                          NEW_I_CC_APPROBATEUR    IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          NEW_I_NH_APPROBATEUR    IN NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE,
                          NEW_D_SV_APPROBATION    IN SV_SUIVI.D_SV_APPROBATION%TYPE,
                          NEW_V_SV_REFERENCE_1    IN SV_SUIVI.V_SV_REFERENCE_1%TYPE,
                          NEW_V_SV_REFERENCE_2    IN SV_SUIVI.V_SV_REFERENCE_2%TYPE,
                          NEW_V_SV_REFERENCE_3    IN SV_SUIVI.V_SV_REFERENCE_3%TYPE);
  PROCEDURE SP_E_CN_CONSIGNATION(action                     IN CHAR,
                                 NEW_L_CN_CLE               IN OUT CN_CONSIGNATION.L_CN_CLE%TYPE,
                                 NEW_L_SI_CLE               IN OUT SI_SITE.L_SI_CLE%TYPE,
                                 NEW_L_CN_REF_CLE           IN CN_CONSIGNATION.L_CN_REF_CLE%TYPE,
                                 NEW_L_CN_REF_SITE          IN CN_CONSIGNATION.L_CN_REF_SITE%TYPE,
                                 NEW_C_CN_REF_GENRE         IN CN_CONSIGNATION.C_CN_REF_GENRE%TYPE,
                                 NEW_I_DE_CLE               IN DE_DEVISE.I_DE_CLE%TYPE,
                                 NEW_I_TN_CLE               IN TN_TYPE_CONSIGNATION.I_TN_CLE%TYPE,
                                 NEW_L_CN_QUANTITE          IN CN_CONSIGNATION.L_CN_QUANTITE%TYPE,
                                 NEW_R_CN_PRIX              IN CN_CONSIGNATION.R_CN_PRIX%TYPE,
                                 NEW_R_CN_MONTANT           IN CN_CONSIGNATION.R_CN_MONTANT%TYPE,
                                 NEW_V_CN_POIDS             IN CN_CONSIGNATION.V_CN_POIDS%TYPE,
                                 NEW_V_CN_DIMENSION         IN CN_CONSIGNATION.V_CN_DIMENSION%TYPE,
                                 NEW_V_CN_MARQUE            IN CN_CONSIGNATION.V_CN_MARQUE%TYPE,
                                 NEW_V_CN_MODELE            IN CN_CONSIGNATION.V_CN_MODELE%TYPE,
                                 NEW_V_CN_FOURNISSEUR       IN CN_CONSIGNATION.V_CN_FOURNISSEUR%TYPE,
                                 NEW_V_CN_DESCRIPTION       IN CN_CONSIGNATION.V_CN_DESCRIPTION%TYPE,
                                 NEW_V_CN_COMMENTAIRE       IN CN_CONSIGNATION.V_CN_COMMENTAIRE%TYPE,
                                 NEW_V_CN_NUMERO_SERIE      IN CN_CONSIGNATION.V_CN_NUMERO_SERIE%TYPE,
                                 NEW_V_CN_CREE_PAR          IN CN_CONSIGNATION.V_CN_CREE_PAR%TYPE,
                                 NEW_D_CN_DATE_CREATION     IN CN_CONSIGNATION.D_CN_DATE_CREATION%TYPE,
                                 NEW_V_CN_MODIFIE_PAR       IN CN_CONSIGNATION.V_CN_MODIFIE_PAR%TYPE,
                                 NEW_D_CN_DATE_MODIFICATION IN CN_CONSIGNATION.D_CN_DATE_MODIFICATION%TYPE,
                                 NEW_V_CN_APPROUVE_PAR      IN CN_CONSIGNATION.V_CN_APPROUVE_PAR%TYPE,
                                 NEW_D_CN_DATE_APPROBATION  IN CN_CONSIGNATION.D_CN_DATE_APPROBATION%TYPE,
                                 NEW_C_CN_APPROUVABLE       IN CN_CONSIGNATION.C_CN_APPROUVABLE%TYPE,
                                 NEW_C_CN_APPROUVE          IN CN_CONSIGNATION.C_CN_APPROUVE%TYPE,
                                 NEW_V_CN_REFERENCE1        IN CN_CONSIGNATION.V_CN_REFERENCE1%TYPE,
                                 NEW_V_CN_REFERENCE2        IN CN_CONSIGNATION.V_CN_REFERENCE2%TYPE,
                                 NEW_I_DN_CLE               IN DN_DENOMINATION.I_DN_CLE%TYPE);
  PROCEDURE SP_E_PSU_MANDAT(action                       IN CHAR,
                            NEW_L_PSU_CLE                IN OUT PSU_MANDAT.L_PSU_CLE%TYPE,
                            NEW_L_SI_CLE                 IN OUT SI_SITE.L_SI_CLE%TYPE,
                            NEW_V_PSU_NUMERO_MANDAT      IN OUT PSU_MANDAT.V_PSU_NUMERO_MANDAT%TYPE,
                            NEW_I_TA_CLE                 IN TA_TYPE_ACCES.I_TA_CLE%TYPE,
                            NEW_V_PSU_DESCRIPTION        IN PSU_MANDAT.V_PSU_DESCRIPTION%TYPE,
                            NEW_V_PSU_DESTINATAIRE_A     IN PSU_MANDAT.V_PSU_DESTINATAIRE_A%TYPE,
                            NEW_V_PSU_DESTINATAIRE_CC    IN PSU_MANDAT.V_PSU_DESTINATAIRE_CC%TYPE,
                            NEW_V_PSU_DESTINATAIRE_CCI   IN PSU_MANDAT.V_PSU_DESTINATAIRE_CCI%TYPE,
                            NEW_I_ST_CLE                 IN ST_STATUT.I_ST_CLE%TYPE,
                            NEW_V_PSU_MESSAGE            IN PSU_MANDAT.V_PSU_MESSAGE%TYPE,
                            NEW_D_PSU_DATE_DEBUT         IN PSU_MANDAT.D_PSU_DATE_DEBUT%TYPE,
                            NEW_D_PSU_DATE_FIN           IN PSU_MANDAT.D_PSU_DATE_FIN%TYPE,
                            NEW_C_PSU_GENRE_FICHIER      IN PSU_MANDAT.C_PSU_GENRE_FICHIER%TYPE,
                            NEW_L_PSU_REFERENCE_CLE      IN PSU_MANDAT.L_PSU_REFERENCE_CLE%TYPE,
                            NEW_L_PSU_REFERENCE_SITE     IN PSU_MANDAT.L_PSU_REFERENCE_SITE%TYPE,
                            NEW_I_PSU_EN_CLE             IN PSU_MANDAT.I_PSU_EN_CLE%TYPE,
                            NEW_L_PSU_SI_CLE             IN PSU_MANDAT.L_PSU_SI_CLE%TYPE,
                            NEW_I_PSU_GE_CLE             IN PSU_MANDAT.I_PSU_GE_CLE%TYPE,
                            NEW_I_PSU_NA_CLE             IN PSU_MANDAT.I_PSU_NA_CLE%TYPE,
                            NEW_I_PSU_TY_CLE             IN PSU_MANDAT.I_PSU_TY_CLE%TYPE,
                            NEW_I_PSU_CA_CLE             IN PSU_MANDAT.I_PSU_CA_CLE%TYPE,
                            NEW_I_PSU_FO_CLE             IN PSU_MANDAT.I_PSU_FO_CLE%TYPE,
                            NEW_V_PSU_NAME               IN PSU_MANDAT.V_PSU_NAME%TYPE,
                            NEW_V_PSU_DO_ANCIENNE_REF    IN PSU_MANDAT.V_PSU_DO_ANCIENNE_REFERENCE%TYPE,
                            NEW_V_PSU_DO_NUMERO_DOSSIER  IN PSU_MANDAT.V_PSU_DO_NUMERO_DOSSIER%TYPE,
                            NEW_V_PSU_DO_REFERENCE_1     IN PSU_MANDAT.V_PSU_DO_REFERENCE_1%TYPE,
                            NEW_V_PSU_SU_REFERENCE_3     IN PSU_MANDAT.V_PSU_SU_REFERENCE_3%TYPE,
                            NEW_V_PSU_SU_NOM             IN PSU_MANDAT.V_PSU_SU_NOM%TYPE,
                            NEW_V_PSU_SU_PRENOM          IN PSU_MANDAT.V_PSU_SU_PRENOM%TYPE,
                            NEW_V_PSU_SO_REFERENCE_3     IN PSU_MANDAT.V_PSU_SO_REFERENCE_3%TYPE,
                            NEW_V_PSU_SO_NOM             IN PSU_MANDAT.V_PSU_SO_NOM%TYPE,
                            NEW_V_PSU_VE_IMMATRICULATION IN PSU_MANDAT.V_PSU_VE_IMMATRICULATION%TYPE,
                            NEW_V_PSU_VE_PROVINCE        IN PSU_MANDAT.V_PSU_VE_PROVINCE%TYPE,
                            NEW_V_PSU_MOT_CLE1           IN PSU_MANDAT.V_PSU_MOT_CLE1%TYPE,
                            NEW_V_PSU_MOT_CLE2           IN PSU_MANDAT.V_PSU_MOT_CLE2%TYPE,
                            NEW_V_PSU_MOT_CLE3           IN PSU_MANDAT.V_PSU_MOT_CLE3%TYPE,
                            NEW_V_PSU_CREE_PAR           IN PSU_MANDAT.V_PSU_CREE_PAR%TYPE,
                            NEW_D_PSU_DATE_CREATION      IN PSU_MANDAT.D_PSU_DATE_CREATION%TYPE,
                            NEW_V_PSU_MODIFIE_PAR        IN PSU_MANDAT.V_PSU_MODIFIE_PAR%TYPE,
                            NEW_D_PSU_DATE_MODIFICATION  IN PSU_MANDAT.D_PSU_DATE_MODIFICATION%TYPE,
                            NEW_V_PSU_APPROUVE_PAR       IN PSU_MANDAT.V_PSU_APPROUVE_PAR%TYPE,
                            NEW_D_PSU_DATE_APPROBATION   IN PSU_MANDAT.D_PSU_DATE_APPROBATION%TYPE);
  PROCEDURE SP_E_CS_CONSIGNATION_ACTION(action                         IN CHAR,
                                        NEW_L_CS_CLE                   IN OUT CS_CONSIGNATION_ACTION.L_CS_CLE%TYPE,
                                        NEW_V_CS_PSU_NUMERO_MANDAT     IN CS_CONSIGNATION_ACTION.V_CS_PSU_NUMERO_MANDAT%TYPE,
                                        NEW_D_CS_DATE_CONSIGNATION     IN CS_CONSIGNATION_ACTION.D_CS_DATE_CONSIGNATION%TYPE,
                                        NEW_V_CS_INTERVENANT           IN CS_CONSIGNATION_ACTION.V_CS_INTERVENANT%TYPE,
                                        NEW_I_TA_CLE                   IN CS_CONSIGNATION_ACTION.I_TA_CLE%TYPE,
                                        NEW_C_CS_GENRE_FICHIER         IN CS_CONSIGNATION_ACTION.C_CS_GENRE_FICHIER%TYPE,
                                        NEW_L_CS_REFERENCE_SOURCE_CLE  IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_SOURCE_CLE%TYPE,
                                        NEW_L_CS_REFERENCE_SOURCE_SITE IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_SOURCE_SITE%TYPE,
                                        NEW_V_CS_REFERENCE_SOURCE      IN CS_CONSIGNATION_ACTION.V_CS_REFERENCE_SOURCE%TYPE,
                                        NEW_D_CS_DATE_COURRIEL         IN CS_CONSIGNATION_ACTION.D_CS_DATE_COURRIEL%TYPE,
                                        NEW_V_CS_REFERENCE_ACTION      IN CS_CONSIGNATION_ACTION.V_CS_REFERENCE_ACTION%TYPE,
                                        NEW_C_CS_GENRE_FICHIER_ACTION  IN CS_CONSIGNATION_ACTION.C_CS_GENRE_FICHIER_ACTION%TYPE,
                                        NEW_L_CS_REFERENCE_ACTION_CLE  IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_ACTION_CLE%TYPE,
                                        NEW_L_CS_REFERENCE_ACTION_SITE IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_ACTION_SITE%TYPE);

  PROCEDURE SP_E_MM_SUJET_SELECTIONNER(NEW_L_LMM_REFERENCE IN LMM_LIEN_MULTIMEDIA.L_LMM_REFERENCE%TYPE,
                                       NEW_L_LMM_REF_SITE  IN LMM_LIEN_MULTIMEDIA.L_LMM_REF_SITE%TYPE,
                                       NEW_L_MM_CLE        IN MM_MULTIMEDIA.L_MM_CLE%TYPE,
                                       NEW_L_SI_CLE        IN MM_MULTIMEDIA.L_SI_CLE%TYPE);

  PROCEDURE SP_E_MM_SUJET_SELECT_DERNIER(NEW_L_LMM_REFERENCE IN LMM_LIEN_MULTIMEDIA.L_LMM_REFERENCE%TYPE,
                                         NEW_L_LMM_REF_SITE  IN LMM_LIEN_MULTIMEDIA.L_LMM_REF_SITE%TYPE);

  PROCEDURE SP_E_APPROUVE_LIEN_DOSSIER_CAT(NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                           NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE,
                                           approuve     IN CHAR);

  procedure SP_E_LPD_PARTAGE_DOSSIER(action              IN CHAR,
                                     NEW_L_LPD_CLE       IN OUT LPD_PARTAGE_DOSSIER.L_LPD_CLE%TYPE,
                                     NEW_L_SI_CLE        IN OUT LPD_PARTAGE_DOSSIER.L_SI_CLE%TYPE,
                                     NEW_L_LPD_REFERENCE IN LPD_PARTAGE_DOSSIER.L_LPD_REFERENCE%TYPE,
                                     NEW_NAME            IN LPD_PARTAGE_DOSSIER.V_LPD_NAME%TYPE,
                                     NEW_L_LPD_REF_SITE  IN LPD_PARTAGE_DOSSIER.L_LPD_REF_SITE%TYPE,
                                     NEW_C_LPD_GENRE     IN LPD_PARTAGE_DOSSIER.C_LPD_GENRE%TYPE);

  procedure SP_E_IS_INSCRIPTION_DATE_SUIVI(NEW_L_IS_CLE IN OUT IS_INSCRIPTION.L_IS_CLE%TYPE,
                                           NEW_L_SI_CLE IN OUT IS_INSCRIPTION.L_SI_CLE%TYPE);

  procedure SP_E_IS_INSCRIPTION_DATE_AIDE(NEW_L_IS_CLE IN OUT IS_INSCRIPTION.L_IS_CLE%TYPE,
                                          NEW_L_SI_CLE IN OUT IS_INSCRIPTION.L_SI_CLE%TYPE);

  procedure SP_E_BI_BILLET_PAIEMENT(NEW_L_BI_REF_CLE       IN BI_BILLET.L_BI_REF_CLE%TYPE,
                                    NEW_L_BI_REF_SITE      IN BI_BILLET.L_BI_REF_SITE%TYPE,
                                    NEW_D_BI_DATE_PAIEMENT IN VARCHAR2);

  procedure SP_E_BI_BILLET(action                     IN CHAR,
                           NEW_L_BI_CLE               IN OUT BI_BILLET.L_BI_CLE%TYPE,
                           NEW_L_SI_CLE               IN OUT BI_BILLET.L_SI_CLE%TYPE,
                           NEW_L_BI_REF_CLE           IN BI_BILLET.L_BI_REF_CLE%TYPE,
                           NEW_L_BI_REF_SITE          IN BI_BILLET.L_BI_REF_SITE%TYPE,
                           NEW_C_BI_REF_GENRE         IN BI_BILLET.C_BI_REF_GENRE%TYPE,
                           NEW_V_BI_NOM               IN BI_BILLET.V_BI_NOM%TYPE,
                           NEW_V_BI_NUMERO_CONTROLE   IN BI_BILLET.V_BI_NUMERO_CONTROLE%TYPE,
                           NEW_L_BI_VALEUR            IN BI_BILLET.L_BI_VALEUR%TYPE,
                           NEW_B_BI_EXTRA             IN BI_BILLET.B_BI_EXTRA%TYPE,
                           NEW_B_BI_PARTICIPATION     IN BI_BILLET.B_BI_PARTICIPATION%TYPE,
                           NEW_B_BI_FORMULE_GROUPE    IN BI_BILLET.B_BI_FORMULE_GROUPE%TYPE,
                           NEW_L_TYPE_MISE            IN BI_BILLET.L_TYPE_MISE%TYPE,
                           NEW_L_BI_MONTANT_LOT       IN BI_BILLET.L_BI_MONTANT_LOT%TYPE,
                           NEW_L_SO_CLE_PROVENANCE    IN BI_BILLET.L_SO_CLE_PROVENANCE%TYPE,
                           NEW_L_SO_SITE_PROVENANCE   IN BI_BILLET.L_SO_SITE_PROVENANCE%TYPE,
                           NEW_D_BI_DATE_ACHAT        IN BI_BILLET.D_BI_DATE_ACHAT%TYPE,
                           NEW_L_SO_CLE_VALIDATION    IN BI_BILLET.L_SO_CLE_VALIDATION%TYPE,
                           NEW_L_SO_SITE_VALIDATION   IN BI_BILLET.L_SO_SITE_VALIDATION%TYPE,
                           NEW_D_BI_DATE_VALIDATION   IN BI_BILLET.D_BI_DATE_VALIDATION%TYPE,
                           NEW_L_JE_CLE               IN BI_BILLET.L_JE_CLE%TYPE,
                           NEW_L_SO_CLE_VERIFICATION  IN BI_BILLET.L_SO_CLE_VERIFICATION%TYPE,
                           NEW_L_SO_SITE_VERIFICATION IN BI_BILLET.L_SO_SITE_VERIFICATION%TYPE,
                           NEW_D_BI_DATE_VERIFICATION IN BI_BILLET.D_BI_DATE_VERIFICATION%TYPE,
                           NEW_L_SO_CLE_FAUTIF        IN BI_BILLET.L_SO_CLE_FAUTIF%TYPE,
                           NEW_L_SO_SITE_FAUTIF       IN BI_BILLET.L_SO_SITE_FAUTIF%TYPE,
                           NEW_D_BI_DATE_PAIEMENT     IN BI_BILLET.D_BI_DATE_PAIEMENT%TYPE,
                           NEW_B_BI_EXTRA_GAGNANT     IN BI_BILLET.B_BI_EXTRA_GAGNANT%TYPE,
                           NEW_L_BI_MONTANT_EXTRA     IN BI_BILLET.L_BI_MONTANT_EXTRA%TYPE);

  procedure SP_E_EV_EVALUATION(action                      IN CHAR,
                               NEW_L_EV_CLE                IN OUT EV_EVALUATION.L_EV_CLE%TYPE,
                               NEW_L_SI_CLE                IN OUT EV_EVALUATION.L_SI_CLE%TYPE,
                               NEW_L_EV_REF_CLE            IN EV_EVALUATION.L_EV_REF_CLE%TYPE,
                               NEW_L_EV_REF_SITE           IN EV_EVALUATION.L_EV_REF_SITE%TYPE,
                               NEW_D_DATE_DEBUT_EVAL       IN EV_EVALUATION.D_DATE_DEBUT_EVAL%TYPE,
                               NEW_D_DATE_FIN_EVAL         IN EV_EVALUATION.D_DATE_FIN_EVAL%TYPE,
                               NEW_L_EV_NO_CLIENT_BIJOU    IN EV_EVALUATION.L_EV_NO_CLIENT_BIJOU%TYPE,
                               NEW_V_EV_FAITS_CONNUS       IN EV_EVALUATION.V_EV_FAITS_CONNUS%TYPE,
                               NEW_V_EV_PROXIMITE          IN EV_EVALUATION.V_EV_PROXIMITE%TYPE,
                               NEW_V_EV_GRADATION          IN EV_EVALUATION.V_EV_GRADATION%TYPE,
                               NEW_V_EV_TRANSACTION        IN EV_EVALUATION.V_EV_TRANSACTION%TYPE,
                               NEW_V_EV_COMMENTAIRE_ETAT   IN EV_EVALUATION.V_EV_COMMENTAIRE_ETAT%TYPE,
                               NEW_V_EV_COMMENTAIRE_PROPOS IN EV_EVALUATION.V_EV_COMMENTAIRE_PROPOS%TYPE,
                               NEW_V_EV_COMMENTAIRE_AUTRE  IN EV_EVALUATION.V_EV_COMMENTAIRE_AUTRE%TYPE,
                               NEW_V_EV_COMMENTAIRE_SIGNE  IN EV_EVALUATION.V_EV_COMMENTAIRE_SIGNE%TYPE,
                               NEW_V_EV_SIGNATAIRE1        IN EV_EVALUATION.V_EV_SIGNATAIRE1%TYPE,
                               NEW_V_EV_SIGNATAIRE2        IN EV_EVALUATION.V_EV_SIGNATAIRE2%TYPE,
                               NEW_V_EV_SIGNATAIRE3        IN EV_EVALUATION.V_EV_SIGNATAIRE3%TYPE,
                               NEW_V_EV_SIGNATAIRE4        IN EV_EVALUATION.V_EV_SIGNATAIRE4%TYPE,
                               NEW_V_EV_SIGNATAIRE5        IN EV_EVALUATION.V_EV_SIGNATAIRE5%TYPE,
                               NEW_D_EV_DATE_EVALUATION    IN EV_EVALUATION.D_EV_DATE_EVALUATION%TYPE);

  procedure SP_E_LVE_VISITE_EVAL(action                  IN CHAR,
                                 NEW_L_LVE_CLE           IN OUT LVE_LIEN_VISITE_EVAL.L_LVE_CLE%TYPE,
                                 NEW_L_SI_CLE            IN OUT LVE_LIEN_VISITE_EVAL.L_SI_CLE%TYPE,
                                 NEW_L_LME_CLE           IN LVE_LIEN_VISITE_EVAL.L_LME_CLE%TYPE,
                                 NEW_L_LME_REF_SITE      IN LVE_LIEN_VISITE_EVAL.L_LME_REF_SITE%TYPE,
                                 NEW_V_LVE_PERIODE       IN LVE_LIEN_VISITE_EVAL.V_LVE_PERIODE%TYPE,
                                 NEW_L_LVE_NOMBRE_VISITE IN LVE_LIEN_VISITE_EVAL.L_LVE_NOMBRE_VISITE%TYPE,
                                 NEW_L_LVE_GAIN_PERTE    IN LVE_LIEN_VISITE_EVAL.L_LVE_GAIN_PERTE%TYPE);

  FUNCTION F_LIRE_FICHIER(NEW_P_FILE IN VARCHAR2) RETURN BLOB;

  PROCEDURE SP_AJOUT_FICHIER(NEW_P_DATA IN BLOB, NEW_P_FILE IN VARCHAR2);

  PROCEDURE SP_DELETE_FICHIER(NEW_P_FILE IN VARCHAR2);

  FUNCTION F_EXISTE(file_in IN VARCHAR2) RETURN PLS_INTEGER;

  FUNCTION CONCATENE_LISTE(p_cursor IN SYS_REFCURSOR) RETURN VARCHAR2;

  PROCEDURE SP_U_MAJ_ADRESSE_RDD;
  PROCEDURE SP_E_UR_URGENCE(action                    IN CHAR,
                            NEW_L_UR_CLE              IN OUT UR_URGENCE.L_UR_CLE%TYPE,
                            NEW_L_SI_CLE              IN OUT SI_SITE.L_SI_CLE%TYPE,
                            NEW_I_CL_CLE              IN CL_CLASSE.I_CL_CLE%TYPE,
                            NEW_L_UR_REF_CLE          IN DO_DOSSIER.L_DO_CLE%TYPE,
                            NEW_L_UR_REF_SITE         IN DO_DOSSIER.L_SI_CLE%TYPE,
                            NEW_L_SO_REF_CLE          IN SO_SOCIETE.L_SO_CLE%TYPE,
                            NEW_L_SO_REF_SITE         IN SO_SOCIETE.L_SI_CLE%TYPE,
                            NEW_V_UR_UNITE            IN UR_URGENCE.V_UR_UNITE%TYPE,
                            NEW_V_UR_DISTRICT         IN UR_URGENCE.V_UR_DISTRICT%TYPE,
                            NEW_V_UR_CONTACT          IN UR_URGENCE.V_UR_CONTACT%TYPE,
                            NEW_V_UR_CONTACT_PRENOM   IN UR_URGENCE.V_UR_CONTACT_PRENOM%TYPE,
                            NEW_V_UR_FONCTION         IN UR_URGENCE.V_UR_FONCTION%TYPE,
                            NEW_V_UR_MATRICULE        IN UR_URGENCE.V_UR_MATRICULE%TYPE,
                            NEW_V_UR_TELEPHONE        IN UR_URGENCE.V_UR_TELEPHONE%TYPE,
                            NEW_L_UR_POSTE            IN UR_URGENCE.L_UR_POSTE%TYPE,
                            NEW_V_UR_TELECOPIEUR      IN UR_URGENCE.V_UR_TELECOPIEUR%TYPE,                            
                            NEW_V_UR_VILLE            IN UR_URGENCE.V_UR_VILLE%TYPE,
                            NEW_V_UR_COURRIEL         IN UR_URGENCE.V_UR_COURRIEL%TYPE,
                            NEW_V_UR_EVENEMENT        IN UR_URGENCE.V_UR_EVENEMENT%TYPE,
                            NEW_V_UR_REPONDANT        IN UR_URGENCE.V_UR_REPONDANT%TYPE,
                            NEW_L_UR_MOTIF            IN UR_URGENCE.L_UR_MOTIF%TYPE,
                            NEW_I_ST_CLE              IN ST_STATUT.I_ST_CLE%TYPE);
                            
  procedure SP_D_LDD_LIEN_DOSSIER(NEW_L_LDD_CLE             IN OUT LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
                                  NEW_L_SI_CLE              IN OUT LDD_LIEN_DOSSIER.L_SI_CLE%TYPE);                            
                            
END CARDEX_LIEN;
/
CREATE OR REPLACE PACKAGE BODY CARDEX_LIEN is
  procedure SP_E_SIS_SITE_INSCRIPTION(action        IN CHAR,
                                      NEW_L_IS_CLE  IN OUT SIS_SITE_INSCRIPTION.L_IS_CLE%TYPE,
                                      NEW_L_SI_CLE  IN OUT SIS_SITE_INSCRIPTION.L_SI_CLE%TYPE,
                                      NEW_L_IS_SITE IN OUT SIS_SITE_INSCRIPTION.L_IS_SITE%TYPE) IS
  begin
    if action = 'I' then
      insert into SIS_SITE_INSCRIPTION
        (L_IS_CLE, L_SI_CLE, L_IS_SITE)
      values
        (NEW_L_IS_CLE, NEW_L_SI_CLE, NEW_L_IS_SITE);
      commit;
    elsif action = 'D' then
      delete from SIS_SITE_INSCRIPTION
       where L_IS_CLE = NEW_L_IS_CLE
         and L_SI_CLE = NEW_L_SI_CLE
         and L_IS_SITE = NEW_L_IS_SITE;
      commit;
    end if;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_SIS_SITE_INSCRIPTION',
                              TRUE);
  END SP_E_SIS_SITE_INSCRIPTION;
  procedure SP_E_IN_INTERVENANT(action                   IN CHAR,
                                NEW_L_IN_CLE             IN OUT IN_INTERVENANT.L_IN_CLE%TYPE,
                                NEW_L_SI_CLE             IN OUT IN_INTERVENANT.L_SI_CLE%TYPE,
                                NEW_NAME                 IN IN_INTERVENANT.NAME%TYPE,
                                NEW_I_LA_CLE             IN IN_INTERVENANT.I_LA_CLE%TYPE,
                                NEW_I_ST_CLE             IN IN_INTERVENANT.I_ST_CLE%TYPE,
                                NEW_I_CC_CLE             IN IN_INTERVENANT.I_CC_CLE%TYPE,
                                NEW_I_NH_CLE             IN IN_INTERVENANT.I_NH_CLE%TYPE,
                                NEW_V_IN_NO_EMPLOYE      IN IN_INTERVENANT.V_IN_NO_EMPLOYE%TYPE,
                                NEW_V_IN_NOM             IN IN_INTERVENANT.V_IN_NOM%TYPE,
                                NEW_V_IN_PRENOM          IN IN_INTERVENANT.V_IN_PRENOM%TYPE,
                                NEW_V_IN_COMMENTAIRE     IN IN_INTERVENANT.V_IN_COMMENTAIRE%TYPE,
                                NEW_C_IN_MOT_PASSE       IN IN_INTERVENANT.C_IN_MOT_PASSE%TYPE,
                                NEW_L_IN_TEMPS_ACTIF     IN IN_INTERVENANT.L_IN_TEMPS_ACTIF%TYPE,
                                NEW_L_IN_TEMPS_CONNEXION IN IN_INTERVENANT.L_IN_TEMPS_CONNEXION%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_IN_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into IN_INTERVENANT
        (L_IN_CLE,
         L_SI_CLE,
         NAME,
         I_LA_CLE,
         I_ST_CLE,
         I_CC_CLE,
         I_NH_CLE,
         V_IN_NO_EMPLOYE,
         V_IN_NOM,
         V_IN_PRENOM,
         V_IN_COMMENTAIRE,
         C_IN_MOT_PASSE,
         L_IN_TEMPS_ACTIF,
         L_IN_TEMPS_CONNEXION,
         V_IN_CREE_PAR,
         D_IN_DATE_CREATION)
      values
        (NEW_L_IN_CLE,
         NEW_L_SI_CLE,
         NEW_NAME,
         NEW_I_LA_CLE,
         NEW_I_ST_CLE,
         NEW_I_CC_CLE,
         NEW_I_NH_CLE,
         NEW_V_IN_NO_EMPLOYE,
         NEW_V_IN_NOM,
         NEW_V_IN_PRENOM,
         NEW_V_IN_COMMENTAIRE,
         NEW_C_IN_MOT_PASSE,
         NEW_L_IN_TEMPS_ACTIF,
         NEW_L_IN_TEMPS_CONNEXION,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update IN_INTERVENANT
         set I_LA_CLE               = NEW_I_LA_CLE,
             I_ST_CLE               = NEW_I_ST_CLE,
             I_CC_CLE               = NEW_I_CC_CLE,
             I_NH_CLE               = NEW_I_NH_CLE,
             V_IN_NO_EMPLOYE        = NEW_V_IN_NO_EMPLOYE,
             V_IN_NOM               = NEW_V_IN_NOM,
             V_IN_PRENOM            = NEW_V_IN_PRENOM,
             V_IN_COMMENTAIRE       = NEW_V_IN_COMMENTAIRE,
             C_IN_MOT_PASSE         = NEW_C_IN_MOT_PASSE,
             L_IN_TEMPS_ACTIF       = NEW_L_IN_TEMPS_ACTIF,
             L_IN_TEMPS_CONNEXION   = NEW_L_IN_TEMPS_CONNEXION,
             V_IN_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_IN_DATE_MODIFICATION = sysdate
       where L_IN_CLE = NEW_L_IN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from IN_INTERVENANT
       where L_IN_CLE = NEW_L_IN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_IN_INTERVENANT',
                              TRUE);
  END SP_E_IN_INTERVENANT;
  procedure SP_E_LMM_LIEN_MULTIMEDIA(action              IN CHAR,
                                     NEW_L_LMM_CLE       IN OUT LMM_LIEN_MULTIMEDIA.L_LMM_CLE%TYPE,
                                     NEW_L_SI_CLE        IN OUT LMM_LIEN_MULTIMEDIA.L_SI_CLE%TYPE,
                                     NEW_L_LMM_REFERENCE IN LMM_LIEN_MULTIMEDIA.L_LMM_REFERENCE%TYPE,
                                     NEW_L_MM_CLE        IN LMM_LIEN_MULTIMEDIA.L_MM_CLE%TYPE,
                                     NEW_L_LMM_REF_SITE  IN LMM_LIEN_MULTIMEDIA.L_LMM_REF_SITE%TYPE,
                                     NEW_C_LMM_REF_GENRE IN LMM_LIEN_MULTIMEDIA.C_LMM_REF_GENRE%TYPE,
                                     NEW_L_MM_REF_SITE   IN LMM_LIEN_MULTIMEDIA.L_MM_REF_SITE%TYPE,
                                     NEW_I_CC_CLE        IN LMM_LIEN_MULTIMEDIA.I_CC_CLE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LMM_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LMM_LIEN_MULTIMEDIA
        (L_LMM_CLE,
         L_SI_CLE,
         L_LMM_REFERENCE,
         L_MM_CLE,
         V_LMM_CREE_PAR,
         D_LMM_DATE_CREATION,
         L_LMM_REF_SITE,
         C_LMM_REF_GENRE,
         L_MM_REF_SITE,
         I_CC_CLE)
      values
        (NEW_L_LMM_CLE,
         NEW_L_SI_CLE,
         NEW_L_LMM_REFERENCE,
         NEW_L_MM_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_LMM_REF_SITE,
         NEW_C_LMM_REF_GENRE,
         NEW_L_MM_REF_SITE,
         NEW_I_CC_CLE);
      commit;
    elsif action = 'U' then
      update LMM_LIEN_MULTIMEDIA
         set L_MM_CLE                = NEW_L_MM_CLE,
             V_LMM_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_LMM_DATE_MODIFICATION = sysdate,
             L_MM_REF_SITE           = NEW_L_MM_REF_SITE,
             I_CC_CLE                = NEW_I_CC_CLE
       where L_LMM_CLE = NEW_L_LMM_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LMM_LIEN_MULTIMEDIA
       where L_LMM_CLE = NEW_L_LMM_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LMM',
                                 NEW_L_LMM_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_LMM_REF_GENRE,
                                 NEW_L_LMM_REFERENCE,
                                 NEW_L_LMM_REF_SITE,
                                 'MM',
                                 NEW_L_MM_CLE,
                                 NEW_L_MM_REF_SITE);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LMM_LIEN_MULTIMEDIA',
                              TRUE);
  END SP_E_LMM_LIEN_MULTIMEDIA;

  procedure SP_E_LJD_LIEN_JEU_DOSSIER(action              IN CHAR,
                                      NEW_L_LJD_CLE       IN OUT LJD_LIEN_JEU_DOSSIER.L_LJD_CLE%TYPE,
                                      NEW_L_SI_CLE        IN OUT LJD_LIEN_JEU_DOSSIER.L_SI_CLE%TYPE,
                                      NEW_L_DO_CLE        IN LJD_LIEN_JEU_DOSSIER.L_DO_CLE%TYPE,
                                      NEW_I_JE_CLE        IN LJD_LIEN_JEU_DOSSIER.I_JE_CLE%TYPE,
                                      NEW_L_LJD_REF_SITE  IN LJD_LIEN_JEU_DOSSIER.L_LJD_REF_SITE%TYPE,
                                      NEW_C_LJD_REF_GENRE IN LJD_LIEN_JEU_DOSSIER.C_LJD_REF_GENRE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LJD_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LJD_LIEN_JEU_DOSSIER
        (L_LJD_CLE,
         L_SI_CLE,
         L_DO_CLE,
         I_JE_CLE,
         V_LJD_CREE_PAR,
         D_LJD_DATE_CREATION,
         L_LJD_REF_SITE,
         C_LJD_REF_GENRE)
      values
        (NEW_L_LJD_CLE,
         NEW_L_SI_CLE,
         NEW_L_DO_CLE,
         NEW_I_JE_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_LJD_REF_SITE,
         NEW_C_LJD_REF_GENRE);
      commit;
    elsif action = 'U' then
      update LJD_LIEN_JEU_DOSSIER
         set I_JE_CLE = NEW_I_JE_CLE
       where L_LJD_CLE = NEW_L_LJD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LJD_LIEN_JEU_DOSSIER
       where L_LJD_CLE = NEW_L_LJD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les caractéristiques, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LJD_LIEN_JEU_DOSSIER
       where L_DO_CLE = NEW_L_DO_CLE
         and L_LJD_REF_SITE = NEW_L_LJD_REF_SITE
         and C_LJD_REF_GENRE = NEW_C_LJD_REF_GENRE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LJD',
                                 NEW_L_LJD_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_LJD_REF_GENRE,
                                 NEW_L_DO_CLE,
                                 NEW_L_LJD_REF_SITE,
                                 'JE',
                                 NEW_I_JE_CLE,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LJD_LIEN_JEU_DOSSIER',
                              TRUE);
  END SP_E_LJD_LIEN_JEU_DOSSIER;

  procedure SP_E_LJV_JEU_EVAL(action             IN CHAR,
                              NEW_L_LJV_CLE      IN OUT LJV_JEU_EVAL.L_LJV_CLE%TYPE,
                              NEW_L_SI_CLE       IN OUT LJV_JEU_EVAL.L_SI_CLE%TYPE,
                              NEW_L_LME_CLE      IN LJV_JEU_EVAL.L_LME_CLE%TYPE,
                              NEW_L_LME_REF_SITE IN LJV_JEU_EVAL.L_LME_REF_SITE%TYPE,
                              NEW_L_JE_CLE       IN LJV_JEU_EVAL.L_JE_CLE%TYPE) is
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LJV_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LJV_JEU_EVAL
        (L_LJV_CLE,
         L_SI_CLE,
         L_LME_CLE,
         L_LME_REF_SITE,
         L_JE_CLE,
         V_LJV_CREE_PAR,
         D_LJV_DATE_CREATION)
      values
        (NEW_L_LJV_CLE,
         NEW_L_SI_CLE,
         NEW_L_LME_CLE,
         NEW_L_LME_REF_SITE,
         NEW_L_JE_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update LJV_JEU_EVAL
         set L_JE_CLE = NEW_L_JE_CLE
       where L_LJV_CLE = NEW_L_LJV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LJV_JEU_EVAL
       where L_LJV_CLE = NEW_L_LJV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les entrées, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LJV_JEU_EVAL
       where L_LME_CLE = NEW_L_LME_CLE
         and L_LME_REF_SITE = NEW_L_LME_REF_SITE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LJV',
                                 NEW_L_LJV_CLE,
                                 NEW_L_SI_CLE,
                                 'EV',
                                 NEW_L_LME_CLE,
                                 NEW_L_LME_REF_SITE,
                                 'LJV',
                                 NEW_L_JE_CLE,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000, 'Database : SP_E_LJV_JEU_EVAL', TRUE);
  END SP_E_LJV_JEU_EVAL;

  procedure SP_E_LEE_ETAT_EVAL(action            IN CHAR,
                               NEW_L_LEE_CLE     IN OUT LEE_LIEN_ETAT_EVAL.L_LEE_CLE%TYPE,
                               NEW_L_SI_CLE      IN OUT LEE_LIEN_ETAT_EVAL.L_SI_CLE%TYPE,
                               NEW_L_EV_CLE      IN LEE_LIEN_ETAT_EVAL.L_EV_CLE%TYPE,
                               NEW_L_EV_REF_SITE IN LEE_LIEN_ETAT_EVAL.L_EV_REF_SITE%TYPE,
                               NEW_L_EE_CLE      IN LEE_LIEN_ETAT_EVAL.L_EE_CLE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LEE_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LEE_LIEN_ETAT_EVAL
        (L_LEE_CLE,
         L_SI_CLE,
         L_EV_CLE,
         L_EV_REF_SITE,
         L_EE_CLE,
         V_LEE_CREE_PAR,
         D_LEE_DATE_CREATION)
      values
        (NEW_L_LEE_CLE,
         NEW_L_SI_CLE,
         NEW_L_EV_CLE,
         NEW_L_EV_REF_SITE,
         NEW_L_EE_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update LEE_LIEN_ETAT_EVAL
         set L_EE_CLE = NEW_L_EE_CLE
       where L_LEE_CLE = NEW_L_LEE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LEE_LIEN_ETAT_EVAL
       where L_LEE_CLE = NEW_L_LEE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les entrées, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LEE_LIEN_ETAT_EVAL
       where L_EV_CLE = NEW_L_EV_CLE
         and L_EV_REF_SITE = NEW_L_EV_REF_SITE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LEE',
                                 NEW_L_LEE_CLE,
                                 NEW_L_SI_CLE,
                                 'EV',
                                 NEW_L_EV_CLE,
                                 NEW_L_EV_REF_SITE,
                                 'LEE',
                                 NEW_L_EE_CLE,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LEE_ETAT_EVAL',
                              TRUE);
  END SP_E_LEE_ETAT_EVAL;

  procedure SP_E_LPE_PROPOS_EVAL(action            IN CHAR,
                                 NEW_L_LPE_CLE     IN OUT LPE_LIEN_PROPOS_EVAL.L_LPE_CLE%TYPE,
                                 NEW_L_SI_CLE      IN OUT LPE_LIEN_PROPOS_EVAL.L_SI_CLE%TYPE,
                                 NEW_L_EV_CLE      IN LPE_LIEN_PROPOS_EVAL.L_EV_CLE%TYPE,
                                 NEW_L_EV_REF_SITE IN LPE_LIEN_PROPOS_EVAL.L_EV_REF_SITE%TYPE,
                                 NEW_L_PE_CLE      IN LPE_LIEN_PROPOS_EVAL.L_PE_CLE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LPE_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LPE_LIEN_PROPOS_EVAL
        (L_LPE_CLE,
         L_SI_CLE,
         L_EV_CLE,
         L_EV_REF_SITE,
         L_PE_CLE,
         V_LPE_CREE_PAR,
         D_LPE_DATE_CREATION)
      values
        (NEW_L_LPE_CLE,
         NEW_L_SI_CLE,
         NEW_L_EV_CLE,
         NEW_L_EV_REF_SITE,
         NEW_L_PE_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update LPE_LIEN_PROPOS_EVAL
         set L_PE_CLE = NEW_L_PE_CLE
       where L_LPE_CLE = NEW_L_LPE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LPE_LIEN_PROPOS_EVAL
       where L_LPE_CLE = NEW_L_LPE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les entrées, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LPE_LIEN_PROPOS_EVAL
       where L_EV_CLE = NEW_L_EV_CLE
         and L_EV_REF_SITE = NEW_L_EV_REF_SITE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LPE',
                                 NEW_L_LPE_CLE,
                                 NEW_L_SI_CLE,
                                 'EV',
                                 NEW_L_EV_CLE,
                                 NEW_L_EV_REF_SITE,
                                 'LPE',
                                 NEW_L_PE_CLE,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LPE_PROPOS_EVAL',
                              TRUE);
  END SP_E_LPE_PROPOS_EVAL;

  procedure SP_E_LVE_VISITE_EVAL(action                  IN CHAR,
                                 NEW_L_LVE_CLE           IN OUT LVE_LIEN_VISITE_EVAL.L_LVE_CLE%TYPE,
                                 NEW_L_SI_CLE            IN OUT LVE_LIEN_VISITE_EVAL.L_SI_CLE%TYPE,
                                 NEW_L_LME_CLE           IN LVE_LIEN_VISITE_EVAL.L_LME_CLE%TYPE,
                                 NEW_L_LME_REF_SITE      IN LVE_LIEN_VISITE_EVAL.L_LME_REF_SITE%TYPE,
                                 NEW_V_LVE_PERIODE       IN LVE_LIEN_VISITE_EVAL.V_LVE_PERIODE%TYPE,
                                 NEW_L_LVE_NOMBRE_VISITE IN LVE_LIEN_VISITE_EVAL.L_LVE_NOMBRE_VISITE%TYPE,
                                 NEW_L_LVE_GAIN_PERTE    IN LVE_LIEN_VISITE_EVAL.L_LVE_GAIN_PERTE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LVE_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LVE_LIEN_VISITE_EVAL
        (L_LVE_CLE,
         L_SI_CLE,
         L_LME_CLE,
         L_LME_REF_SITE,
         V_LVE_PERIODE,
         L_LVE_NOMBRE_VISITE,
         L_LVE_GAIN_PERTE,
         V_LVE_CREE_PAR,
         D_LVE_DATE_CREATION)
      values
        (NEW_L_LVE_CLE,
         NEW_L_SI_CLE,
         NEW_L_LME_CLE,
         NEW_L_LME_REF_SITE,
         NEW_V_LVE_PERIODE,
         NEW_L_LVE_NOMBRE_VISITE,
         NEW_L_LVE_GAIN_PERTE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'U' then
      update LVE_LIEN_VISITE_EVAL
         set V_LVE_PERIODE       = NEW_V_LVE_PERIODE,
             L_LVE_NOMBRE_VISITE = NEW_L_LVE_NOMBRE_VISITE,
             L_LVE_GAIN_PERTE    = NEW_L_LVE_GAIN_PERTE
       where L_LVE_CLE = NEW_L_LVE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LVE_LIEN_VISITE_EVAL
       where L_LVE_CLE = NEW_L_LVE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les entrées, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LVE_LIEN_VISITE_EVAL
       where L_LME_CLE = NEW_L_LME_CLE
         and L_LME_REF_SITE = NEW_L_LME_REF_SITE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LVE',
                                 NEW_L_LVE_CLE,
                                 NEW_L_SI_CLE,
                                 'EV',
                                 NEW_L_LME_CLE,
                                 NEW_L_LME_REF_SITE,
                                 'LVE',
                                 NEW_L_LME_CLE,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LVE_VISITE_EVAL',
                              TRUE);
  END SP_E_LVE_VISITE_EVAL;

  procedure SP_E_LDC_LIEN_DOSSIER_CAT(action       IN CHAR,
                                      NEW_L_DO_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.L_DO_CLE%TYPE,
                                      NEW_L_SI_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.L_SI_CLE%TYPE,
                                      NEW_I_CA_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.I_CA_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into LDC_LIEN_DOSSIER_CATEGORIE
        (L_DO_CLE, L_SI_CLE, I_CA_CLE, V_LDC_CREE_PAR, D_LDC_DATE_CREATION)
      values
        (NEW_L_DO_CLE,
         NEW_L_SI_CLE,
         NEW_I_CA_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate);
      commit;
    elsif action = 'D' then
      delete from LDC_LIEN_DOSSIER_CATEGORIE
       where L_DO_CLE = NEW_L_DO_CLE
         and L_SI_CLE = NEW_L_SI_CLE
         and I_CA_CLE = NEW_I_CA_CLE;
      commit;
    end if;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LDC_LIEN_DOSSIER_CAT',
                              TRUE);
  END SP_E_LDC_LIEN_DOSSIER_CAT;

  procedure SP_E_LSC_CARACTERISTIQUE(action              IN CHAR,
                                     NEW_L_LSC_CLE       IN OUT LSC_CARACTERISTIQUE.L_LSC_CLE%TYPE,
                                     NEW_L_SI_CLE        IN OUT LSC_CARACTERISTIQUE.L_SI_CLE%TYPE,
                                     NEW_L_CR_CLE        IN LSC_CARACTERISTIQUE.L_CR_CLE%TYPE,
                                     NEW_L_LSC_REFERENCE IN LSC_CARACTERISTIQUE.L_LSC_REFERENCE%TYPE,
                                     NEW_L_LSC_REF_SITE  IN LSC_CARACTERISTIQUE.L_LSC_REF_SITE%TYPE,
                                     NEW_C_LSC_REF_GENRE IN LSC_CARACTERISTIQUE.C_LSC_REF_GENRE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LSC_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LSC_CARACTERISTIQUE
        (L_LSC_CLE,
         L_SI_CLE,
         L_CR_CLE,
         L_LSC_REFERENCE,
         V_LSC_CREE_PAR,
         D_LSC_DATE_CREATION,
         L_LSC_REF_SITE,
         C_LSC_REF_GENRE)
      values
        (NEW_L_LSC_CLE,
         NEW_L_SI_CLE,
         NEW_L_CR_CLE,
         NEW_L_LSC_REFERENCE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_LSC_REF_SITE,
         NEW_C_LSC_REF_GENRE);
      commit;
    elsif action = 'U' then
      update LSC_CARACTERISTIQUE
         set L_CR_CLE = NEW_L_CR_CLE
       where L_LSC_CLE = NEW_L_LSC_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_CARACTERISTIQUE(NEW_L_LSC_REFERENCE,
                                              NEW_L_LSC_REF_SITE,
                                              sysdate);
      delete from LSC_CARACTERISTIQUE
       where L_LSC_CLE = NEW_L_LSC_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'A' then
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_CARACTERISTIQUE(NEW_L_LSC_REFERENCE,
                                              NEW_L_LSC_REF_SITE,
                                              sysdate);
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les caractéristiques, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LSC_CARACTERISTIQUE
       where L_LSC_REFERENCE = NEW_L_LSC_REFERENCE
         and L_LSC_REF_SITE = NEW_L_LSC_REF_SITE
         and C_LSC_REF_GENRE = NEW_C_LSC_REF_GENRE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les caractéristiques, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LSC_CARACTERISTIQUE
       where L_LSC_REFERENCE = NEW_L_LSC_REFERENCE
         and L_LSC_REF_SITE = NEW_L_LSC_REF_SITE
         and C_LSC_REF_GENRE = NEW_C_LSC_REF_GENRE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LSC',
                                 NEW_L_LSC_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_LSC_REF_GENRE,
                                 NEW_L_LSC_REFERENCE,
                                 NEW_L_LSC_REF_SITE,
                                 'CR',
                                 NEW_L_CR_CLE,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LSC_CARACTERISTIQUE',
                              TRUE);
  END SP_E_LSC_CARACTERISTIQUE;

  procedure SP_E_AD_ADRESSE(action                  IN CHAR,
                            NEW_L_AD_CLE            IN OUT AD_ADRESSE.L_AD_CLE%TYPE,
                            NEW_L_SI_CLE            IN OUT AD_ADRESSE.L_SI_CLE%TYPE,
                            NEW_L_AD_REFERENCE      IN AD_ADRESSE.L_AD_REFERENCE%TYPE,
                            NEW_V_AD_ADRESSE        IN AD_ADRESSE.V_AD_ADRESSE%TYPE,
                            NEW_V_AD_ADRESSE2       IN AD_ADRESSE.V_AD_ADRESSE2%TYPE,
                            NEW_V_AD_NUM_MUNICIPAL  IN AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE,
                            NEW_L_AD_TYPE_RUE       IN AD_ADRESSE.L_AD_TYPE_RUE%TYPE,
                            NEW_V_AD_NOM_RUE        IN AD_ADRESSE.V_AD_NOM_RUE%TYPE,
                            NEW_L_AD_POINT_CARDINAL IN AD_ADRESSE.L_AD_POINT_CARDINAL%TYPE,
                            NEW_L_AD_TYPE_UNITE     IN AD_ADRESSE.L_AD_TYPE_UNITE%TYPE,
                            NEW_V_AD_NO_UNITE       IN AD_ADRESSE.V_AD_NO_UNITE%TYPE,
                            NEW_V_AD_ADRESSE_POST   IN AD_ADRESSE.V_AD_ADRESSE_POST%TYPE,
                            NEW_V_AD_ELECTRONIQUE_1 IN AD_ADRESSE.V_AD_ELECTRONIQUE_1%TYPE,
                            NEW_V_AD_ELECTRONIQUE_2 IN AD_ADRESSE.V_AD_ELECTRONIQUE_2%TYPE,
                            NEW_I_PA_CLE            IN AD_ADRESSE.I_PA_CLE%TYPE,
                            NEW_L_PR_CLE            IN AD_ADRESSE.L_PR_CLE%TYPE,
                            NEW_L_VI_CLE            IN AD_ADRESSE.L_VI_CLE%TYPE,
                            NEW_V_AD_CODE_POSTAL    IN AD_ADRESSE.V_AD_CODE_POSTAL%TYPE,
                            NEW_I_TE_CLE_1          IN AD_ADRESSE.I_TE_CLE_1%TYPE,
                            NEW_I_AD_TEL_PERIODE_1  IN AD_ADRESSE.I_AD_TEL_PERIODE_1%TYPE,
                            NEW_V_AD_TELEPHONE_1    IN AD_ADRESSE.V_AD_TELEPHONE_1%TYPE,
                            NEW_I_TE_CLE_2          IN AD_ADRESSE.I_TE_CLE_2%TYPE,
                            NEW_I_AD_TEL_PERIODE_2  IN AD_ADRESSE.I_AD_TEL_PERIODE_2%TYPE,
                            NEW_V_AD_TELEPHONE_2    IN AD_ADRESSE.V_AD_TELEPHONE_2%TYPE,
                            NEW_I_TE_CLE_3          IN AD_ADRESSE.I_TE_CLE_3%TYPE,
                            NEW_I_AD_TEL_PERIODE_3  IN AD_ADRESSE.I_AD_TEL_PERIODE_3%TYPE,
                            NEW_V_AD_TELEPHONE_3    IN AD_ADRESSE.V_AD_TELEPHONE_3%TYPE,
                            NEW_I_ST_CLE            IN AD_ADRESSE.I_ST_CLE%TYPE,
                            NEW_V_AD_COMMENTAIRE    IN AD_ADRESSE.V_AD_COMMENTAIRE%TYPE,
                            NEW_L_AD_REF_SITE       IN AD_ADRESSE.L_AD_REF_SITE%TYPE,
                            NEW_C_AD_REF_GENRE      IN AD_ADRESSE.C_AD_REF_GENRE%TYPE,
                            NEW_B_AD_IND_RDD        IN AD_ADRESSE.B_AD_IND_RDD%TYPE) IS
  begin
    if action = 'I' then
      -- Les autres adresses principale deviennent des adresses secondaires
      if NEW_I_ST_CLE = 621 then
        Update AD_ADRESSE
           set I_ST_CLE = 622
         WHERE L_AD_REFERENCE = NEW_L_AD_REFERENCE
           AND L_AD_REF_SITE = NEW_L_AD_REF_SITE
           and I_ST_CLE = 621;
      end if;
      select seq_dossier.nextval into NEW_L_AD_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into AD_ADRESSE
        (L_AD_CLE,
         L_SI_CLE,
         L_AD_REFERENCE,
         V_AD_ADRESSE,
         V_AD_ADRESSE2,
         V_AD_NUM_MUNICIPAL,
         L_AD_TYPE_RUE,
         V_AD_NOM_RUE,
         L_AD_POINT_CARDINAL,
         L_AD_TYPE_UNITE,
         V_AD_NO_UNITE,
         V_AD_ADRESSE_POST,
         V_AD_ELECTRONIQUE_1,
         V_AD_ELECTRONIQUE_2,
         I_PA_CLE,
         L_PR_CLE,
         L_VI_CLE,
         V_AD_CODE_POSTAL,
         I_TE_CLE_1,
         I_AD_TEL_PERIODE_1,
         V_AD_TELEPHONE_1,
         I_TE_CLE_2,
         I_AD_TEL_PERIODE_2,
         V_AD_TELEPHONE_2,
         I_TE_CLE_3,
         I_AD_TEL_PERIODE_3,
         V_AD_TELEPHONE_3,
         I_ST_CLE,
         V_AD_COMMENTAIRE,
         V_AD_CREE_PAR,
         D_AD_DATE_CREATION,
         L_AD_REF_SITE,
         C_AD_REF_GENRE,
         B_AD_IND_RDD)
      values
        (NEW_L_AD_CLE,
         NEW_L_SI_CLE,
         NEW_L_AD_REFERENCE,
         NEW_V_AD_ADRESSE,
         NEW_V_AD_ADRESSE2,
         NEW_V_AD_NUM_MUNICIPAL,
         NEW_L_AD_TYPE_RUE,
         NEW_V_AD_NOM_RUE,
         NEW_L_AD_POINT_CARDINAL,
         NEW_L_AD_TYPE_UNITE,
         NEW_V_AD_NO_UNITE,
         NEW_V_AD_ADRESSE_POST,
         NEW_V_AD_ELECTRONIQUE_1,
         NEW_V_AD_ELECTRONIQUE_2,
         NEW_I_PA_CLE,
         NEW_L_PR_CLE,
         NEW_L_VI_CLE,
         NEW_V_AD_CODE_POSTAL,
         NEW_I_TE_CLE_1,
         NEW_I_AD_TEL_PERIODE_1,
         NEW_V_AD_TELEPHONE_1,
         NEW_I_TE_CLE_2,
         NEW_I_AD_TEL_PERIODE_2,
         NEW_V_AD_TELEPHONE_2,
         NEW_I_TE_CLE_3,
         NEW_I_AD_TEL_PERIODE_1,
         NEW_V_AD_TELEPHONE_3,
         NEW_I_ST_CLE,
         NEW_V_AD_COMMENTAIRE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_AD_REF_SITE,
         NEW_C_AD_REF_GENRE,
         NEW_B_AD_IND_RDD);
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_ADRESSE(NEW_L_AD_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update AD_ADRESSE
         set V_AD_ADRESSE           = NEW_V_AD_ADRESSE,
             V_AD_ADRESSE2          = NEW_V_AD_ADRESSE2,
             V_AD_NUM_MUNICIPAL     = NEW_V_AD_NUM_MUNICIPAL,
             L_AD_TYPE_RUE          = NEW_L_AD_TYPE_RUE,
             V_AD_NOM_RUE           = NEW_V_AD_NOM_RUE,
             L_AD_POINT_CARDINAL    = NEW_L_AD_POINT_CARDINAL,
             L_AD_TYPE_UNITE        = NEW_L_AD_TYPE_UNITE,
             V_AD_NO_UNITE          = NEW_V_AD_NO_UNITE,
             V_AD_ADRESSE_POST      = NEW_V_AD_ADRESSE_POST,
             V_AD_ELECTRONIQUE_1    = NEW_V_AD_ELECTRONIQUE_1,
             V_AD_ELECTRONIQUE_2    = NEW_V_AD_ELECTRONIQUE_2,
             I_PA_CLE               = NEW_I_PA_CLE,
             L_PR_CLE               = NEW_L_PR_CLE,
             L_VI_CLE               = NEW_L_VI_CLE,
             V_AD_CODE_POSTAL       = NEW_V_AD_CODE_POSTAL,
             I_TE_CLE_1             = NEW_I_TE_CLE_1,
             I_AD_TEL_PERIODE_1     = NEW_I_AD_TEL_PERIODE_1,
             V_AD_TELEPHONE_1       = NEW_V_AD_TELEPHONE_1,
             I_TE_CLE_2             = NEW_I_TE_CLE_2,
             I_AD_TEL_PERIODE_2     = NEW_I_AD_TEL_PERIODE_2,
             V_AD_TELEPHONE_2       = NEW_V_AD_TELEPHONE_2,
             I_TE_CLE_3             = NEW_I_TE_CLE_3,
             I_AD_TEL_PERIODE_3     = NEW_I_AD_TEL_PERIODE_3,
             V_AD_TELEPHONE_3       = NEW_V_AD_TELEPHONE_3,
             I_ST_CLE               = NEW_I_ST_CLE,
             V_AD_COMMENTAIRE       = NEW_V_AD_COMMENTAIRE,
             V_AD_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_AD_DATE_MODIFICATION = sysdate,
             B_AD_IND_RDD           = NEW_B_AD_IND_RDD
       where L_AD_CLE = NEW_L_AD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from AD_ADRESSE
       where L_AD_CLE = NEW_L_AD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'AD',
                                 NEW_L_AD_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_AD_REF_GENRE,
                                 NEW_L_AD_REFERENCE,
                                 NEW_L_AD_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000, 'Database : SP_E_AD_ADRESSE', True);
  END SP_E_AD_ADRESSE;

  procedure SP_E_LDD_LIEN_DOSSIER(action                    IN CHAR,
                                  NEW_L_LDD_CLE             IN OUT LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
                                  NEW_L_SI_CLE              IN OUT LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
                                  NEW_L_DO_CLE              IN LDD_LIEN_DOSSIER.L_DO_CLE%TYPE,
                                  NEW_L_LDD_DOSSIER_ASSOCIE IN LDD_LIEN_DOSSIER.L_LDD_DOSSIER_ASSOCIE%TYPE,
                                  NEW_I_RO_CLE              IN LDD_LIEN_DOSSIER.I_RO_CLE%TYPE,
                                  NEW_I_TL_CLE              IN LDD_LIEN_DOSSIER.I_TL_CLE%TYPE,
                                  NEW_L_DO_SITE             IN LDD_LIEN_DOSSIER.L_DO_SITE%TYPE,
                                  NEW_C_DO_GENRE            IN LDD_LIEN_DOSSIER.C_DO_GENRE%TYPE,
                                  NEW_L_LDD_SITE            IN LDD_LIEN_DOSSIER.L_LDD_SITE%TYPE,
                                  NEW_C_LDD_GENRE           IN LDD_LIEN_DOSSIER.C_LDD_GENRE%TYPE) IS
    TROUVE NUMBER := 0;
  begin
    if action = 'I' then
      IF NEW_L_DO_CLE = NEW_L_LDD_DOSSIER_ASSOCIE AND
         NEW_L_DO_SITE = NEW_L_LDD_SITE AND
         NEW_C_DO_GENRE = NEW_C_LDD_GENRE THEN
        RAISE_APPLICATION_ERROR(-20001,
                                'LIEN SUR LUI-MEME NON PERMIS',
                                TRUE);
      END IF;
      SELECT count(*)
        INTO TROUVE
        FROM LDD_LIEN_DOSSIER
       WHERE ((L_DO_CLE = NEW_L_DO_CLE AND L_DO_SITE = NEW_L_DO_SITE AND
             C_DO_GENRE = NEW_C_DO_GENRE AND
             L_LDD_DOSSIER_ASSOCIE = NEW_L_LDD_DOSSIER_ASSOCIE AND
             L_LDD_SITE = NEW_L_LDD_SITE AND
             C_LDD_GENRE = NEW_C_LDD_GENRE) OR
             (L_DO_CLE = NEW_L_LDD_DOSSIER_ASSOCIE AND
             L_DO_SITE = NEW_L_LDD_SITE AND C_DO_GENRE = NEW_C_LDD_GENRE AND
             L_LDD_DOSSIER_ASSOCIE = NEW_L_DO_CLE AND
             L_LDD_SITE = NEW_L_DO_SITE AND C_LDD_GENRE = NEW_C_DO_GENRE))
         and ((NEW_C_DO_GENRE = 'DO' and NEW_C_LDD_GENRE = 'SO' and
             NEW_I_RO_CLE in (25995, 25996, 26176) and
             I_RO_CLE = NEW_I_RO_CLE) OR
             (NEW_C_DO_GENRE != 'DO' or NEW_C_LDD_GENRE != 'SO' or
             NEW_I_RO_CLE not in (25995, 25996, 26176)));
      IF TROUVE != 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'LIEN DEJA EXISTANT', TRUE);
      END IF;
      select seq_dossier.nextval into NEW_L_LDD_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LDD_LIEN_DOSSIER
        (L_LDD_CLE,
         L_SI_CLE,
         L_DO_CLE,
         L_LDD_DOSSIER_ASSOCIE,
         I_RO_CLE,
         I_TL_CLE,
         V_LDD_CREE_PAR,
         D_LDD_DATE_CREATION,
         L_DO_SITE,
         C_DO_GENRE,
         L_LDD_SITE,
         C_LDD_GENRE)
      values
        (NEW_L_LDD_CLE,
         NEW_L_SI_CLE,
         NEW_L_DO_CLE,
         NEW_L_LDD_DOSSIER_ASSOCIE,
         NEW_I_RO_CLE,
         NEW_I_TL_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_DO_SITE,
         NEW_C_DO_GENRE,
         NEW_L_LDD_SITE,
         NEW_C_LDD_GENRE);
      commit;
    elsif action = 'U' then
      update LDD_LIEN_DOSSIER
         set I_RO_CLE = NEW_I_RO_CLE, I_TL_CLE = NEW_I_TL_CLE
       where L_LDD_CLE = NEW_L_LDD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_LIAISON(NEW_L_LDD_CLE, NEW_L_SI_CLE, sysdate);
      delete from LDD_LIEN_DOSSIER
       where L_LDD_CLE = NEW_L_LDD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'M' then
      --Cas particulier pour les responsables de détaillant (personnes physiques dans RDD)
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_LIAISON_DETAILLANT(NEW_L_LDD_DOSSIER_ASSOCIE,
                                                 NEW_L_LDD_SITE,
                                                 NEW_L_DO_CLE);
      delete from LDD_LIEN_DOSSIER L --On supprime les responsables précédents s'il y a lieu
       WHERE L.L_LDD_DOSSIER_ASSOCIE = NEW_L_LDD_DOSSIER_ASSOCIE
         AND L.L_LDD_SITE = NEW_L_LDD_SITE
         AND L.L_DO_CLE <> NEW_L_DO_CLE
         AND L.C_DO_GENRE = 'SU'
         AND L.C_LDD_GENRE = 'SO'
         AND L.I_RO_CLE = 659;
      commit;
    end if;

  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LDD_LIEN_DOSSIER',
                              True);
  END SP_E_LDD_LIEN_DOSSIER;
  procedure SP_E_LPV_LIEN_PARTICULARITE(action              IN CHAR,
                                        NEW_L_LPV_CLE       IN OUT LPV_LIEN_PARTICULARITE.L_LPV_CLE%TYPE,
                                        NEW_L_SI_CLE        IN OUT LPV_LIEN_PARTICULARITE.L_SI_CLE%TYPE,
                                        NEW_L_VE_CLE        IN LPV_LIEN_PARTICULARITE.L_VE_CLE%TYPE,
                                        NEW_I_PT_CLE        IN LPV_LIEN_PARTICULARITE.I_PT_CLE%TYPE,
                                        NEW_L_LPV_REF_SITE  IN LPV_LIEN_PARTICULARITE.L_LPV_REF_SITE%TYPE,
                                        NEW_C_LPV_REF_GENRE IN LPV_LIEN_PARTICULARITE.C_LPV_REF_GENRE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LPV_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into LPV_LIEN_PARTICULARITE
        (L_LPV_CLE,
         L_SI_CLE,
         L_VE_CLE,
         I_PT_CLE,
         V_LPV_CREE_PAR,
         D_LPV_DATE_CREATION,
         L_LPV_REF_SITE,
         C_LPV_REF_GENRE)
      values
        (NEW_L_LPV_CLE,
         NEW_L_SI_CLE,
         NEW_L_VE_CLE,
         NEW_I_PT_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_LPV_REF_SITE,
         NEW_C_LPV_REF_GENRE);
      commit;
    elsif action = 'U' then
      update LPV_LIEN_PARTICULARITE
         set I_PT_CLE = NEW_I_PT_CLE
       where L_LPV_CLE = NEW_L_LPV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_PARTICULARITE(NEW_L_VE_CLE,
                                            NEW_L_LPV_REF_SITE,
                                            sysdate);
      delete from LPV_LIEN_PARTICULARITE
       where L_LPV_CLE = NEW_L_LPV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'A' then
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_PARTICULARITE(NEW_L_VE_CLE,
                                            NEW_L_LPV_REF_SITE,
                                            sysdate);
      delete from LPV_LIEN_PARTICULARITE
       where L_VE_CLE = NEW_L_VE_CLE
         and L_LPV_REF_SITE = NEW_L_LPV_REF_SITE
         and C_LPV_REF_GENRE = NEW_C_LPV_REF_GENRE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les particularités, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LPV_LIEN_PARTICULARITE
       where L_VE_CLE = NEW_L_VE_CLE
         and L_LPV_REF_SITE = NEW_L_LPV_REF_SITE
         and C_LPV_REF_GENRE = NEW_C_LPV_REF_GENRE;
      commit;
    end if;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LPV_LIEN_PARTICULARITE',
                              TRUE);
  END SP_E_LPV_LIEN_PARTICULARITE;
  procedure SP_E_IS_INSCRIPTION(action                        IN CHAR,
                                NEW_L_IS_CLE                  IN OUT IS_INSCRIPTION.L_IS_CLE%TYPE,
                                NEW_L_SI_CLE                  IN OUT IS_INSCRIPTION.L_SI_CLE%TYPE,
                                NEW_L_IS_REFERENCE            IN IS_INSCRIPTION.L_IS_REFERENCE%TYPE,
                                NEW_V_IS_DUREE                IN IS_INSCRIPTION.V_IS_DUREE%TYPE,
                                NEW_D_IS_DATE_DEBUT           IN IS_INSCRIPTION.D_IS_DATE_DEBUT%TYPE,
                                NEW_D_IS_DATE_FIN             IN IS_INSCRIPTION.D_IS_DATE_FIN%TYPE,
                                NEW_I_IS_PERIODE              IN IS_INSCRIPTION.I_IS_PERIODE%TYPE,
                                NEW_I_IS_SITE                 IN IS_INSCRIPTION.I_IS_SITE%TYPE,
                                NEW_I_ST_CLE                  IN IS_INSCRIPTION.I_ST_CLE%TYPE,
                                NEW_C_IS_REF_GENRE            IN IS_INSCRIPTION.C_IS_REF_GENRE%TYPE,
                                NEW_L_IS_REF_SITE             IN IS_INSCRIPTION.L_IS_REF_SITE%TYPE,
                                NEW_B_IS_AIDE_INITIALE        IN IS_INSCRIPTION.B_IS_AIDE_INITIALE%TYPE,
                                NEW_B_IS_AIDE_IMMEDIATE       IN IS_INSCRIPTION.B_IS_AIDE_IMMEDIATE%TYPE,
                                NEW_D_IS_COURRIEL_AIDE        IS_INSCRIPTION.D_IS_COURRIEL_AIDE%TYPE,
                                NEW_D_IS_COURRIEL_SUIVI       IS_INSCRIPTION.D_IS_COURRIEL_SUIVI%TYPE,
                                NEW_V_IS_INTERVENANT_FINAL    IS_INSCRIPTION.V_IS_INTERVENANT_FINAL%TYPE,
                                NEW_V_IS_INTERVENANT_INITIAL  IS_INSCRIPTION.V_IS_INTERVENANT_INITIAL%TYPE,
                                NEW_D_IS_RENCONTRE_FINALE     IS_INSCRIPTION.D_IS_RENCONTRE_FINALE%TYPE,
                                NEW_D_IS_RENCONTRE_INITIALE   IS_INSCRIPTION.D_IS_RENCONTRE_INITIALE%TYPE,
                                NEW_B_IS_TOUS_SITE_APPLICABLE IS_INSCRIPTION.B_IS_TOUS_SITE_APPLICABLE%TYPE) IS
    --Modification le 5-01-1999
    --À la demande des utilisateurs, les contrôles sur l'entrée d'une inscription
    --sont retirés.
    /*FUNCTION F_VERIFIE_INSCRIPTION(NEW_L_IS_REFERENCE IN IS_INSCRIPTION.L_IS_REFERENCE%TYPE,
                          NEW_L_IS_REF_SITE IN IS_INSCRIPTION.L_IS_REF_SITE%TYPE,
                          NEW_D_IS_DATE_DEBUT IN IS_INSCRIPTION.D_IS_DATE_DEBUT%TYPE,
                          NEW_D_IS_DATE_FIN IN IS_INSCRIPTION.D_IS_DATE_FIN%TYPE) RETURN NUMBER IS
    OK NUMBER;
    BEGIN
    SELECT COUNT(*)
        INTO OK
        FROM IS_INSCRIPTION
       WHERE ( L_IS_REFERENCE = NEW_L_IS_REFERENCE ) AND
             ( L_IS_REF_SITE = NEW_L_IS_REF_SITE ) AND
             (( NEW_D_IS_DATE_DEBUT between D_IS_DATE_DEBUT AND NVL(D_IS_DATE_FIN,SYSDATE+75000) ) OR
             ( NVL(NEW_D_IS_DATE_FIN,NEW_D_IS_DATE_DEBUT+36500) between D_IS_DATE_DEBUT AND NVL(D_IS_DATE_FIN,SYSDATE+75000) ) OR
             ( D_IS_DATE_DEBUT between NEW_D_IS_DATE_DEBUT AND NVL(NEW_D_IS_DATE_FIN,NEW_D_IS_DATE_DEBUT+36500) )) AND
             ( I_ST_CLE NOT IN (SELECT I_ST_CLE FROM ST_STATUT WHERE C_ST_TYPE = 'N') )   ;
    RETURN OK;
    END F_VERIFIE_INSCRIPTION;
    */
    Inactif st_statut.i_st_cle%type := 0;
  begin
    if action = 'I' then
      --Ajout de la procédure suivante pour invalider les inscription précédentes du
      --dossier, avant d'en ajouter une nouvelle.
      --On recherche d'abord le code du statut Inactif d'un dossier.
      select count(*)
        into Inactif
        from st_statut
       where c_st_genre_fichier = 'DO'
         and c_st_type = 'I';
      if Inactif = 1 then
        select i_st_cle
          into Inactif
          from st_statut
         where c_st_genre_fichier = 'DO'
           and c_st_type = 'I';
      else
        RAISE_APPLICATION_ERROR(-20005,
                                Inactif || ' Aucun statut de type ''I''. ',
                                TRUE);
        Inactif := 0;
      end if;
      --On invalide ensuite les inscriptions existantes du dossier.
      Update IS_INSCRIPTION
         set I_ST_CLE = Inactif
       WHERE (L_IS_REFERENCE = NEW_L_IS_REFERENCE)
         AND (L_IS_REF_SITE = NEW_L_IS_REF_SITE);
      Commit;
      --IF F_VERIFIE_INSCRIPTION(NEW_L_IS_REFERENCE,NEW_L_IS_REF_SITE,NEW_D_IS_DATE_DEBUT,NEW_D_IS_DATE_FIN) != 0 THEN
      --      RAISE_APPLICATION_ERROR(-20000,'CHEVAUCHEMENT DE DATES');
      --   END IF;
      --Ajout de la nouvelle inscription
      select seq_dossier.nextval into NEW_L_IS_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into IS_INSCRIPTION
        (L_IS_CLE,
         L_SI_CLE,
         L_IS_REFERENCE,
         V_IS_DUREE,
         D_IS_DATE_DEBUT,
         D_IS_DATE_FIN,
         I_IS_PERIODE,
         I_IS_SITE,
         I_ST_CLE,
         V_IS_CREE_PAR,
         D_IS_DATE_CREATION,
         C_IS_REF_GENRE,
         L_IS_REF_SITE,
         B_IS_AIDE_INITIALE,
         B_IS_AIDE_IMMEDIATE,
         B_IS_TOUS_SITE_APPLICABLE)
      values
        (NEW_L_IS_CLE,
         NEW_L_SI_CLE,
         NEW_L_IS_REFERENCE,
         NEW_V_IS_DUREE,
         NEW_D_IS_DATE_DEBUT,
         NEW_D_IS_DATE_FIN,
         NEW_I_IS_PERIODE,
         NEW_I_IS_SITE,
         NEW_I_ST_CLE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_C_IS_REF_GENRE,
         NEW_L_IS_REF_SITE,
         NEW_B_IS_AIDE_INITIALE,
         NEW_B_IS_AIDE_IMMEDIATE,
         NEW_B_IS_TOUS_SITE_APPLICABLE);
      commit; /*SP_E_SIS_SITE_INSCRIPTION('I',NEW_L_IS_CLE,NEW_L_SI_CLE,CARDEX_SPECIAL.G_SITE);*/
    elsif action = 'U' then
      --IF F_VERIFIE_INSCRIPTION(NEW_L_IS_REFERENCE,NEW_L_IS_REF_SITE,NEW_D_IS_DATE_DEBUT,NEW_D_IS_DATE_FIN) != 0 THEN
      --      RAISE_APPLICATION_ERROR(-20000,'CHEVAUCHEMENT DE DATES');
      --   END IF;
      --Ajout le 3 octobre 2008. La mise à jour ne concerne que les champs de rencontre.
      update IS_INSCRIPTION
         set V_IS_INTERVENANT_FINAL   = NEW_V_IS_INTERVENANT_FINAL,
             V_IS_INTERVENANT_INITIAL = NEW_V_IS_INTERVENANT_INITIAL,
             D_IS_RENCONTRE_FINALE    = NEW_D_IS_RENCONTRE_FINALE,
             D_IS_RENCONTRE_INITIALE  = NEW_D_IS_RENCONTRE_INITIALE
       where L_IS_CLE = NEW_L_IS_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from SIS_SITE_INSCRIPTION
       where L_IS_CLE = NEW_L_IS_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      delete from IS_INSCRIPTION
       where L_IS_CLE = NEW_L_IS_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'IS',
                                 NEW_L_IS_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_IS_REF_GENRE,
                                 NEW_L_IS_REFERENCE,
                                 NEW_L_IS_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_IS_INSCRIPTION',
                              true);
  END SP_E_IS_INSCRIPTION;

  PROCEDURE SP_E_LMM_LIEN_MULTIMEDIA(action               IN CHAR,
                                     new_l_mm_si_cle      IN OUT NUMBER,
                                     new_l_mm_cle         IN OUT NUMBER,
                                     new_i_tm_cle         IN NUMBER,
                                     new_v_mm_description IN VARCHAR2,
                                     new_l_lmm_cle        IN OUT NUMBER,
                                     new_l_lmm_si_cle     IN OUT NUMBER,
                                     new_l_lmm_reference  IN NUMBER,
                                     new_l_lmm_site_ref   IN NUMBER,
                                     new_c_lmm_genre_ref  IN CHAR,
                                     new_l_em_cle         IN OUT NUMBER,
                                     new_l_em_si_cle      IN OUT NUMBER,
                                     new_v_mm_extension   IN VARCHAR2,
                                     new_i_cc_cle         IN NUMBER) IS
    occurence NUMBER;
  BEGIN
    if action = 'I' then
      if new_l_mm_si_cle = -1 and new_l_mm_cle = -1 then
        new_l_em_si_cle := cardex_special.g_site;
        SELECT seq_dossier.nextval INTO new_l_em_cle FROM dual;
        new_l_mm_si_cle := cardex_special.g_site;
        SELECT seq_dossier.nextval INTO new_l_mm_cle FROM dual;
        INSERT INTO MM_MULTIMEDIA
          (L_MM_CLE,
           L_SI_CLE,
           L_EM_CLE,
           I_TM_CLE,
           V_MM_DESCRIPTION,
           V_MM_CREE_PAR,
           D_MM_DATE_CREATION,
           L_EM_SI_CLE,
           V_MM_EXTENSION)
        VALUES
          (new_l_mm_cle,
           new_l_mm_si_cle,
           new_l_em_cle,
           new_i_tm_cle,
           new_v_mm_description,
           cardex_special.g_usager,
           sysdate,
           new_l_em_si_cle,
           new_v_mm_extension);
      END IF;
      if new_l_lmm_si_cle = -1 and new_l_lmm_cle = -1 then
        new_l_lmm_si_cle := cardex_special.g_site;
        SELECT seq_dossier.nextval INTO new_l_lmm_cle FROM dual;
        INSERT INTO LMM_LIEN_MULTIMEDIA
          (L_LMM_CLE,
           L_SI_CLE,
           L_LMM_REFERENCE,
           L_MM_CLE,
           V_LMM_CREE_PAR,
           D_LMM_DATE_CREATION,
           V_LMM_MODIFIE_PAR,
           D_LMM_DATE_MODIFICATION,
           L_LMM_REF_SITE,
           C_LMM_REF_GENRE,
           L_MM_REF_SITE,
           I_CC_CLE)
        VALUES
          (new_l_lmm_cle,
           new_l_lmm_si_cle,
           new_l_lmm_reference,
           new_l_mm_cle,
           cardex_special.g_usager,
           sysdate,
           NULL,
           NULL,
           new_l_lmm_site_ref,
           new_c_lmm_genre_ref,
           new_l_mm_si_cle,
           new_i_cc_cle);
      end if;
    elsif action = 'U' then
      UPDATE MM_MULTIMEDIA
         SET V_MM_DESCRIPTION = new_v_mm_description
       WHERE (L_MM_CLE = new_l_mm_cle)
         AND (L_SI_CLE = new_l_mm_si_cle);
      commit;
    elsif action = 'D' then
      DELETE FROM LMM_LIEN_MULTIMEDIA
       WHERE (L_LMM_CLE = new_l_lmm_cle)
         AND (L_SI_CLE = new_l_lmm_si_cle);
      SELECT count(*)
        INTO occurence
        FROM LMM_LIEN_MULTIMEDIA
       WHERE (L_MM_CLE = new_l_mm_cle)
         AND (L_MM_REF_SITE = new_l_mm_si_cle);
      IF occurence = 0 then
        SELECT L_EM_CLE
          INTO new_l_em_cle
          FROM MM_MULTIMEDIA
         WHERE (L_MM_CLE = new_l_mm_cle)
           AND (L_SI_CLE = new_l_mm_si_cle);
        DELETE FROM MM_MULTIMEDIA
         WHERE (L_MM_CLE = new_l_mm_cle)
           AND (L_SI_CLE = new_l_mm_si_cle);
      END IF;
      commit;
    END IF;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LMM_LIEN_MULTIMEDIA',
                              true);
  END SP_E_LMM_LIEN_MULTIMEDIA;
  --Appelée par Sujet, Société et Véhicule pour la mise à jour de la confidentialité
  --dans LMM_LIEN_MULTIMEDIA. Ce champ sert au filtrage des photos dans la réplication.
  PROCEDURE SP_E_LMM_LIEN_MULTIMEDIA(action          IN CHAR,
                                     new_c_genre_ref IN CHAR,
                                     new_l_cle       IN NUMBER,
                                     new_l_si_cle    IN NUMBER,
                                     new_i_cc_cle    IN NUMBER) IS
  BEGIN
    If action = 'U' then
      update lmm_lien_multimedia lmm
         set lmm.i_cc_cle = new_i_cc_cle
       where lmm.l_lmm_reference = new_l_cle
         and lmm.l_lmm_ref_site = new_l_si_cle
         and lmm.c_lmm_ref_genre = new_c_genre_ref;
    END IF;
    COMMIT;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_EM_ELEMENT_MULTIMEDIA',
                              true);
  END SP_E_LMM_LIEN_MULTIMEDIA;
  --Mise à jour de la confidentialité d'un lien Multimédia dans LMM_LIEN_MULTIMEDIA. 
  PROCEDURE SP_U_LMM_LIEN_MULTIMEDIA(new_l_lmm_cle      IN NUMBER,
                                     new_l_si_cle       IN NUMBER,
                                     new_i_cc_cle       IN NUMBER
                                     ) IS
  BEGIN
    update lmm_lien_multimedia lmm
       set lmm.i_cc_cle                = new_i_cc_cle,
           lmm.v_lmm_modifie_par       = cardex_special.g_usager,
           lmm.d_lmm_date_modification = sysdate
     where lmm.l_lmm_cle = new_l_lmm_cle
       and lmm.l_si_cle = new_l_si_cle;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_U_EM_ELEMENT_MULTIMEDIA',
                              true);
  END SP_U_LMM_LIEN_MULTIMEDIA;
  PROCEDURE SP_E_SV_SUIVI(action                  IN CHAR,
                          NEW_L_SV_CLE            IN OUT SV_SUIVI.L_SV_CLE%TYPE,
                          NEW_L_SI_CLE            IN OUT SI_SITE.L_SI_CLE%TYPE,
                          NEW_L_SV_REFERENCE      IN SV_SUIVI.L_SV_REFERENCE%TYPE,
                          NEW_L_SV_REF_SITE       IN SV_SUIVI.L_SV_REF_SITE%TYPE,
                          NEW_C_SV_REF_GENRE      IN SV_SUIVI.C_SV_REF_GENRE%TYPE,
                          NEW_V_SV_SUIVI          IN SV_SUIVI.V_SV_SUIVI%TYPE,
                          NEW_I_TC_CLE            IN TC_TYPE_ACTIVITE.I_TC_CLE%TYPE,
                          NEW_I_ST_CLE            IN ST_STATUT.I_ST_CLE%TYPE,
                          NEW_D_SV_DATE_PREVUE    IN SV_SUIVI.D_SV_DATE_PREVUE%TYPE,
                          NEW_D_SV_DATE_COMPLETEE IN SV_SUIVI.D_SV_DATE_COMPLETEE%TYPE,
                          NEW_L_SV_PO_ORIGINE     IN PO_POSTE.L_PO_CLE%TYPE,
                          NEW_V_SV_DEMANDEUR      IN IN_INTERVENANT.NAME%TYPE,
                          NEW_V_SV_INTERVENANT    IN IN_INTERVENANT.NAME%TYPE,
                          NEW_L_SV_PO_ASSIGNE     IN PO_POSTE.L_PO_CLE%TYPE,
                          NEW_V_SV_CREE_PAR       IN IN_INTERVENANT.NAME%TYPE,
                          NEW_I_CC_SUIVI          IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          NEW_I_NH_SUIVI          IN NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE,
                          NEW_I_CC_CREATEUR       IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          NEW_I_NH_CREATEUR       IN NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE,
                          NEW_D_SV_DATE_CREATION  IN SV_SUIVI.D_SV_DATE_CREATION%TYPE,
                          NEW_V_SV_MODIFIE_PAR    IN IN_INTERVENANT.NAME%TYPE,
                          NEW_D_SV_MODIFICATION   IN SV_SUIVI.D_SV_MODIFICATION%TYPE,
                          NEW_V_SV_APPROBATEUR    IN IN_INTERVENANT.NAME%TYPE,
                          NEW_I_CC_APPROBATEUR    IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          NEW_I_NH_APPROBATEUR    IN NH_NIVEAU_HIERARCHIQUE.I_NH_CLE%TYPE,
                          NEW_D_SV_APPROBATION    IN SV_SUIVI.D_SV_APPROBATION%TYPE,
                          NEW_V_SV_REFERENCE_1    IN SV_SUIVI.V_SV_REFERENCE_1%TYPE,
                          NEW_V_SV_REFERENCE_2    IN SV_SUIVI.V_SV_REFERENCE_2%TYPE,
                          NEW_V_SV_REFERENCE_3    IN SV_SUIVI.V_SV_REFERENCE_3%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_SV_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into SV_SUIVI
        (L_SV_CLE,
         L_SI_CLE,
         L_SV_REFERENCE,
         L_SV_REF_SITE,
         C_SV_REF_GENRE,
         V_SV_SUIVI,
         I_TC_CLE,
         I_ST_CLE,
         D_SV_DATE_PREVUE,
         D_SV_DATE_COMPLETEE,
         L_SV_PO_ORIGINE,
         V_SV_DEMANDEUR,
         V_SV_INTERVENANT,
         L_SV_PO_ASSIGNE,
         V_SV_CREE_PAR,
         I_CC_SUIVI,
         I_NH_SUIVI,
         I_CC_CREATEUR,
         I_NH_CREATEUR,
         D_SV_DATE_CREATION,
         V_SV_REFERENCE_1,
         V_SV_REFERENCE_2,
         V_SV_REFERENCE_3)
      values
        (NEW_L_SV_CLE,
         NEW_L_SI_CLE,
         NEW_L_SV_REFERENCE,
         NEW_L_SV_REF_SITE,
         NEW_C_SV_REF_GENRE,
         NEW_V_SV_SUIVI,
         NEW_I_TC_CLE,
         NEW_I_ST_CLE,
         NEW_D_SV_DATE_PREVUE,
         NEW_D_SV_DATE_COMPLETEE,
         NEW_L_SV_PO_ORIGINE,
         NEW_V_SV_DEMANDEUR,
         NEW_V_SV_INTERVENANT,
         NEW_L_SV_PO_ASSIGNE,
         cardex_special.g_usager,
         NEW_I_CC_SUIVI,
         NEW_I_NH_SUIVI,
         NEW_I_CC_CREATEUR,
         NEW_I_NH_CREATEUR,
         NEW_D_SV_DATE_CREATION,
         NEW_V_SV_REFERENCE_1,
         NEW_V_SV_REFERENCE_2,
         NEW_V_SV_REFERENCE_3);
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_SUIVI(NEW_L_SV_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update SV_SUIVI
         set L_SV_REFERENCE      = NEW_L_SV_REFERENCE,
             L_SV_REF_SITE       = NEW_L_SV_REF_SITE,
             C_SV_REF_GENRE      = NEW_C_SV_REF_GENRE,
             V_SV_SUIVI          = NEW_V_SV_SUIVI,
             I_TC_CLE            = NEW_I_TC_CLE,
             I_ST_CLE            = NEW_I_ST_CLE,
             D_SV_DATE_PREVUE    = NEW_D_SV_DATE_PREVUE,
             D_SV_DATE_COMPLETEE = NEW_D_SV_DATE_COMPLETEE,
             L_SV_PO_ORIGINE     = NEW_L_SV_PO_ORIGINE,
             V_SV_DEMANDEUR      = NEW_V_SV_DEMANDEUR,
             V_SV_INTERVENANT    = NEW_V_SV_INTERVENANT,
             L_SV_PO_ASSIGNE     = NEW_L_SV_PO_ASSIGNE,
             I_CC_SUIVI          = NEW_I_CC_SUIVI,
             I_NH_SUIVI          = NEW_I_NH_SUIVI,
             V_SV_MODIFIE_PAR    = CARDEX_SPECIAL.g_usager,
             D_SV_MODIFICATION   = SYSDATE,
             V_SV_REFERENCE_1    = NEW_V_SV_REFERENCE_1,
             V_SV_REFERENCE_2    = NEW_V_SV_REFERENCE_2,
             V_SV_REFERENCE_3    = NEW_V_SV_REFERENCE_3
       where L_SV_CLE = NEW_L_SV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Cas spécial de mise à jour (approbation du suivi).
    elsif action = 'M' then
      update SV_SUIVI
         set I_NH_SUIVI          = NEW_I_NH_SUIVI,
             V_SV_APPROBATEUR    = NEW_V_SV_APPROBATEUR,
             I_CC_APPROBATEUR    = NEW_I_CC_APPROBATEUR,
             I_NH_APPROBATEUR    = NEW_I_NH_APPROBATEUR,
             D_SV_APPROBATION    = NEW_D_SV_APPROBATION,
             I_ST_CLE            = NEW_I_ST_CLE,
             D_SV_DATE_COMPLETEE = NEW_D_SV_DATE_COMPLETEE
       where L_SV_CLE = NEW_L_SV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from SV_SUIVI
       where L_SV_CLE = NEW_L_SV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'SV',
                                 NEW_L_SV_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_SV_REF_GENRE,
                                 NEW_L_SV_REFERENCE,
                                 NEW_L_SV_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000, 'Database : SP_E_SV_SUIVI', TRUE);
  END SP_E_SV_SUIVI;

  PROCEDURE SP_E_CN_CONSIGNATION(action                     IN CHAR,
                                 NEW_L_CN_CLE               IN OUT CN_CONSIGNATION.L_CN_CLE%TYPE,
                                 NEW_L_SI_CLE               IN OUT SI_SITE.L_SI_CLE%TYPE,
                                 NEW_L_CN_REF_CLE           IN CN_CONSIGNATION.L_CN_REF_CLE%TYPE,
                                 NEW_L_CN_REF_SITE          IN CN_CONSIGNATION.L_CN_REF_SITE%TYPE,
                                 NEW_C_CN_REF_GENRE         IN CN_CONSIGNATION.C_CN_REF_GENRE%TYPE,
                                 NEW_I_DE_CLE               IN DE_DEVISE.I_DE_CLE%TYPE,
                                 NEW_I_TN_CLE               IN TN_TYPE_CONSIGNATION.I_TN_CLE%TYPE,
                                 NEW_L_CN_QUANTITE          IN CN_CONSIGNATION.L_CN_QUANTITE%TYPE,
                                 NEW_R_CN_PRIX              IN CN_CONSIGNATION.R_CN_PRIX%TYPE,
                                 NEW_R_CN_MONTANT           IN CN_CONSIGNATION.R_CN_MONTANT%TYPE,
                                 NEW_V_CN_POIDS             IN CN_CONSIGNATION.V_CN_POIDS%TYPE,
                                 NEW_V_CN_DIMENSION         IN CN_CONSIGNATION.V_CN_DIMENSION%TYPE,
                                 NEW_V_CN_MARQUE            IN CN_CONSIGNATION.V_CN_MARQUE%TYPE,
                                 NEW_V_CN_MODELE            IN CN_CONSIGNATION.V_CN_MODELE%TYPE,
                                 NEW_V_CN_FOURNISSEUR       IN CN_CONSIGNATION.V_CN_FOURNISSEUR%TYPE,
                                 NEW_V_CN_DESCRIPTION       IN CN_CONSIGNATION.V_CN_DESCRIPTION%TYPE,
                                 NEW_V_CN_COMMENTAIRE       IN CN_CONSIGNATION.V_CN_COMMENTAIRE%TYPE,
                                 NEW_V_CN_NUMERO_SERIE      IN CN_CONSIGNATION.V_CN_NUMERO_SERIE%TYPE,
                                 NEW_V_CN_CREE_PAR          IN CN_CONSIGNATION.V_CN_CREE_PAR%TYPE,
                                 NEW_D_CN_DATE_CREATION     IN CN_CONSIGNATION.D_CN_DATE_CREATION%TYPE,
                                 NEW_V_CN_MODIFIE_PAR       IN CN_CONSIGNATION.V_CN_MODIFIE_PAR%TYPE,
                                 NEW_D_CN_DATE_MODIFICATION IN CN_CONSIGNATION.D_CN_DATE_MODIFICATION%TYPE,
                                 NEW_V_CN_APPROUVE_PAR      IN CN_CONSIGNATION.V_CN_APPROUVE_PAR%TYPE,
                                 NEW_D_CN_DATE_APPROBATION  IN CN_CONSIGNATION.D_CN_DATE_APPROBATION%TYPE,
                                 NEW_C_CN_APPROUVABLE       IN CN_CONSIGNATION.C_CN_APPROUVABLE%TYPE,
                                 NEW_C_CN_APPROUVE          IN CN_CONSIGNATION.C_CN_APPROUVE%TYPE,
                                 NEW_V_CN_REFERENCE1        IN CN_CONSIGNATION.V_CN_REFERENCE1%TYPE,
                                 NEW_V_CN_REFERENCE2        IN CN_CONSIGNATION.V_CN_REFERENCE2%TYPE,
                                 NEW_I_DN_CLE               IN DN_DENOMINATION.I_DN_CLE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_CN_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into CN_CONSIGNATION
        (L_CN_CLE,
         L_SI_CLE,
         I_DE_CLE,
         I_TN_CLE,
         L_CN_REF_CLE,
         L_CN_REF_SITE,
         C_CN_REF_GENRE,
         L_CN_QUANTITE,
         R_CN_PRIX,
         R_CN_MONTANT,
         V_CN_POIDS,
         V_CN_DIMENSION,
         V_CN_MARQUE,
         V_CN_MODELE,
         V_CN_FOURNISSEUR,
         V_CN_DESCRIPTION,
         V_CN_COMMENTAIRE,
         V_CN_NUMERO_SERIE,
         V_CN_REFERENCE1,
         V_CN_REFERENCE2,
         C_CN_APPROUVABLE,
         C_CN_APPROUVE,
         V_CN_CREE_PAR,
         D_CN_DATE_CREATION,
         V_CN_MODIFIE_PAR,
         D_CN_DATE_MODIFICATION,
         V_CN_APPROUVE_PAR,
         D_CN_DATE_APPROBATION,
         I_DN_CLE)
      values
        (NEW_L_CN_CLE,
         NEW_L_SI_CLE,
         NEW_I_DE_CLE,
         NEW_I_TN_CLE,
         NEW_L_CN_REF_CLE,
         NEW_L_CN_REF_SITE,
         NEW_C_CN_REF_GENRE,
         NEW_L_CN_QUANTITE,
         NEW_R_CN_PRIX,
         NEW_R_CN_MONTANT,
         NEW_V_CN_POIDS,
         NEW_V_CN_DIMENSION,
         NEW_V_CN_MARQUE,
         NEW_V_CN_MODELE,
         NEW_V_CN_FOURNISSEUR,
         NEW_V_CN_DESCRIPTION,
         NEW_V_CN_COMMENTAIRE,
         NEW_V_CN_NUMERO_SERIE,
         NEW_V_CN_REFERENCE1,
         NEW_V_CN_REFERENCE2,
         NEW_C_CN_APPROUVABLE,
         NEW_C_CN_APPROUVE,
         cardex_special.g_usager,
         sysdate,
         NEW_V_CN_MODIFIE_PAR,
         NEW_D_CN_DATE_MODIFICATION,
         NEW_V_CN_APPROUVE_PAR,
         NEW_D_CN_DATE_APPROBATION,
         NEW_I_DN_CLE);
      commit;
    elsif action = 'U' then
      update CN_CONSIGNATION
         set I_DE_CLE               = NEW_I_DE_CLE,
             I_TN_CLE               = NEW_I_TN_CLE,
             L_CN_REF_CLE           = NEW_L_CN_REF_CLE,
             L_CN_REF_SITE          = NEW_L_CN_REF_SITE,
             C_CN_REF_GENRE         = NEW_C_CN_REF_GENRE,
             L_CN_QUANTITE          = NEW_L_CN_QUANTITE,
             R_CN_PRIX              = NEW_R_CN_PRIX,
             R_CN_MONTANT           = NEW_R_CN_MONTANT,
             V_CN_POIDS             = NEW_V_CN_POIDS,
             V_CN_DIMENSION         = NEW_V_CN_DIMENSION,
             V_CN_MARQUE            = NEW_V_CN_MARQUE,
             V_CN_MODELE            = NEW_V_CN_MODELE,
             V_CN_FOURNISSEUR       = NEW_V_CN_FOURNISSEUR,
             V_CN_DESCRIPTION       = NEW_V_CN_DESCRIPTION,
             V_CN_COMMENTAIRE       = NEW_V_CN_COMMENTAIRE,
             V_CN_NUMERO_SERIE      = NEW_V_CN_NUMERO_SERIE,
             V_CN_REFERENCE1        = NEW_V_CN_REFERENCE1,
             V_CN_REFERENCE2        = NEW_V_CN_REFERENCE2,
             C_CN_APPROUVABLE       = NEW_C_CN_APPROUVABLE,
             C_CN_APPROUVE          = NEW_C_CN_APPROUVE,
             V_CN_CREE_PAR          = NEW_V_CN_CREE_PAR,
             D_CN_DATE_CREATION     = NEW_D_CN_DATE_CREATION,
             V_CN_MODIFIE_PAR       = cardex_special.g_usager,
             D_CN_DATE_MODIFICATION = sysdate,
             V_CN_APPROUVE_PAR      = NEW_V_CN_APPROUVE_PAR,
             D_CN_DATE_APPROBATION  = NEW_D_CN_DATE_APPROBATION,
             I_DN_CLE               = NEW_I_DN_CLE
       where L_CN_CLE = NEW_L_CN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Cas spécial de mise à jour (approbation de la consignation).
    elsif action = 'M' then
      update CN_CONSIGNATION
         set L_CN_CLE              = NEW_L_CN_CLE,
             V_CN_APPROUVE_PAR     = cardex_special.g_usager,
             D_CN_DATE_APPROBATION = sysdate,
             C_CN_APPROUVE         = 'yes'
       where L_CN_CLE = NEW_L_CN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from CN_CONSIGNATION
       where L_CN_CLE = NEW_L_CN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'CN',
                                 NEW_L_CN_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_CN_REF_GENRE,
                                 NEW_L_CN_CLE,
                                 NEW_L_CN_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_CN_CONSIGNATION',
                              TRUE);
  END SP_E_CN_CONSIGNATION;
  PROCEDURE SP_E_PSU_MANDAT(action                       IN CHAR,
                            NEW_L_PSU_CLE                IN OUT PSU_MANDAT.L_PSU_CLE%TYPE,
                            NEW_L_SI_CLE                 IN OUT SI_SITE.L_SI_CLE%TYPE,
                            NEW_V_PSU_NUMERO_MANDAT      IN OUT PSU_MANDAT.V_PSU_NUMERO_MANDAT%TYPE,
                            NEW_I_TA_CLE                 IN TA_TYPE_ACCES.I_TA_CLE%TYPE,
                            NEW_V_PSU_DESCRIPTION        IN PSU_MANDAT.V_PSU_DESCRIPTION%TYPE,
                            NEW_V_PSU_DESTINATAIRE_A     IN PSU_MANDAT.V_PSU_DESTINATAIRE_A%TYPE,
                            NEW_V_PSU_DESTINATAIRE_CC    IN PSU_MANDAT.V_PSU_DESTINATAIRE_CC%TYPE,
                            NEW_V_PSU_DESTINATAIRE_CCI   IN PSU_MANDAT.V_PSU_DESTINATAIRE_CCI%TYPE,
                            NEW_I_ST_CLE                 IN ST_STATUT.I_ST_CLE%TYPE,
                            NEW_V_PSU_MESSAGE            IN PSU_MANDAT.V_PSU_MESSAGE%TYPE,
                            NEW_D_PSU_DATE_DEBUT         IN PSU_MANDAT.D_PSU_DATE_DEBUT%TYPE,
                            NEW_D_PSU_DATE_FIN           IN PSU_MANDAT.D_PSU_DATE_FIN%TYPE,
                            NEW_C_PSU_GENRE_FICHIER      IN PSU_MANDAT.C_PSU_GENRE_FICHIER%TYPE,
                            NEW_L_PSU_REFERENCE_CLE      IN PSU_MANDAT.L_PSU_REFERENCE_CLE%TYPE,
                            NEW_L_PSU_REFERENCE_SITE     IN PSU_MANDAT.L_PSU_REFERENCE_SITE%TYPE,
                            NEW_I_PSU_EN_CLE             IN PSU_MANDAT.I_PSU_EN_CLE%TYPE,
                            NEW_L_PSU_SI_CLE             IN PSU_MANDAT.L_PSU_SI_CLE%TYPE,
                            NEW_I_PSU_GE_CLE             IN PSU_MANDAT.I_PSU_GE_CLE%TYPE,
                            NEW_I_PSU_NA_CLE             IN PSU_MANDAT.I_PSU_NA_CLE%TYPE,
                            NEW_I_PSU_TY_CLE             IN PSU_MANDAT.I_PSU_TY_CLE%TYPE,
                            NEW_I_PSU_CA_CLE             IN PSU_MANDAT.I_PSU_CA_CLE%TYPE,
                            NEW_I_PSU_FO_CLE             IN PSU_MANDAT.I_PSU_FO_CLE%TYPE,
                            NEW_V_PSU_NAME               IN PSU_MANDAT.V_PSU_NAME%TYPE,
                            NEW_V_PSU_DO_ANCIENNE_REF    IN PSU_MANDAT.V_PSU_DO_ANCIENNE_REFERENCE%TYPE,
                            NEW_V_PSU_DO_NUMERO_DOSSIER  IN PSU_MANDAT.V_PSU_DO_NUMERO_DOSSIER%TYPE,
                            NEW_V_PSU_DO_REFERENCE_1     IN PSU_MANDAT.V_PSU_DO_REFERENCE_1%TYPE,
                            NEW_V_PSU_SU_REFERENCE_3     IN PSU_MANDAT.V_PSU_SU_REFERENCE_3%TYPE,
                            NEW_V_PSU_SU_NOM             IN PSU_MANDAT.V_PSU_SU_NOM%TYPE,
                            NEW_V_PSU_SU_PRENOM          IN PSU_MANDAT.V_PSU_SU_PRENOM%TYPE,
                            NEW_V_PSU_SO_REFERENCE_3     IN PSU_MANDAT.V_PSU_SO_REFERENCE_3%TYPE,
                            NEW_V_PSU_SO_NOM             IN PSU_MANDAT.V_PSU_SO_NOM%TYPE,
                            NEW_V_PSU_VE_IMMATRICULATION IN PSU_MANDAT.V_PSU_VE_IMMATRICULATION%TYPE,
                            NEW_V_PSU_VE_PROVINCE        IN PSU_MANDAT.V_PSU_VE_PROVINCE%TYPE,
                            NEW_V_PSU_MOT_CLE1           IN PSU_MANDAT.V_PSU_MOT_CLE1%TYPE,
                            NEW_V_PSU_MOT_CLE2           IN PSU_MANDAT.V_PSU_MOT_CLE2%TYPE,
                            NEW_V_PSU_MOT_CLE3           IN PSU_MANDAT.V_PSU_MOT_CLE3%TYPE,
                            NEW_V_PSU_CREE_PAR           IN PSU_MANDAT.V_PSU_CREE_PAR%TYPE,
                            NEW_D_PSU_DATE_CREATION      IN PSU_MANDAT.D_PSU_DATE_CREATION%TYPE,
                            NEW_V_PSU_MODIFIE_PAR        IN PSU_MANDAT.V_PSU_MODIFIE_PAR%TYPE,
                            NEW_D_PSU_DATE_MODIFICATION  IN PSU_MANDAT.D_PSU_DATE_MODIFICATION%TYPE,
                            NEW_V_PSU_APPROUVE_PAR       IN PSU_MANDAT.V_PSU_APPROUVE_PAR%TYPE,
                            NEW_D_PSU_DATE_APPROBATION   IN PSU_MANDAT.D_PSU_DATE_APPROBATION%TYPE) IS
    --Cette fonction génère automatiquement le numéro du mandat (Sigle + numéro séquentiel)
    function F_NO_MANDAT return varchar2 is
      no_mandat   varchar2(20) := '';
      v_site      varchar2(5);
      no_sequence number(4, 0) := 0;
    begin
      SELECT V_SI_ABREVIATION
        INTO v_site
        FROM SI_SITE
       WHERE L_SI_CLE = cardex_special.g_site;
      SELECT seq_mandat.nextval into no_sequence from dual;
      If no_sequence = 0 then
        --On vérifie si un numéro a été trouvé.
        select '0' into no_mandat from dual;
      Else
        SELECT v_site || '-' || to_char(no_sequence, 'FM0999')
          into no_mandat
          from dual;
      End if;
      return no_mandat;
    end;
  begin
    if action = 'I' then
      select SEQ_DOSSIER.nextval into NEW_L_PSU_CLE from dual;
      NEW_V_PSU_NUMERO_MANDAT := f_no_mandat;
      If NEW_V_PSU_NUMERO_MANDAT = '0' then
        raise_application_error(-20000,
                                'Database : SP_E_PSU_MANDAT NUMÉRO',
                                true);
      End if;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into PSU_MANDAT
        (L_PSU_CLE,
         L_SI_CLE,
         V_PSU_NUMERO_MANDAT,
         I_TA_CLE,
         V_PSU_DESCRIPTION,
         V_PSU_DESTINATAIRE_A,
         V_PSU_DESTINATAIRE_CC,
         V_PSU_DESTINATAIRE_CCI,
         I_ST_CLE,
         V_PSU_MESSAGE,
         D_PSU_DATE_DEBUT,
         D_PSU_DATE_FIN,
         C_PSU_GENRE_FICHIER,
         L_PSU_REFERENCE_CLE,
         L_PSU_REFERENCE_SITE,
         I_PSU_EN_CLE,
         L_PSU_SI_CLE,
         I_PSU_GE_CLE,
         I_PSU_NA_CLE,
         I_PSU_TY_CLE,
         I_PSU_CA_CLE,
         I_PSU_FO_CLE,
         V_PSU_NAME,
         V_PSU_DO_ANCIENNE_REFERENCE,
         V_PSU_DO_NUMERO_DOSSIER,
         V_PSU_DO_REFERENCE_1,
         V_PSU_SU_REFERENCE_3,
         V_PSU_SU_NOM,
         V_PSU_SU_PRENOM,
         V_PSU_SO_REFERENCE_3,
         V_PSU_SO_NOM,
         V_PSU_VE_IMMATRICULATION,
         V_PSU_VE_PROVINCE,
         V_PSU_MOT_CLE1,
         V_PSU_MOT_CLE2,
         V_PSU_MOT_CLE3,
         V_PSU_CREE_PAR,
         D_PSU_DATE_CREATION,
         V_PSU_MODIFIE_PAR,
         D_PSU_DATE_MODIFICATION,
         V_PSU_APPROUVE_PAR,
         D_PSU_DATE_APPROBATION)
      values
        (NEW_L_PSU_CLE,
         NEW_L_SI_CLE,
         NEW_V_PSU_NUMERO_MANDAT,
         NEW_I_TA_CLE,
         NEW_V_PSU_DESCRIPTION,
         NEW_V_PSU_DESTINATAIRE_A,
         NEW_V_PSU_DESTINATAIRE_CC,
         NEW_V_PSU_DESTINATAIRE_CCI,
         NEW_I_ST_CLE,
         NEW_V_PSU_MESSAGE,
         NEW_D_PSU_DATE_DEBUT,
         NEW_D_PSU_DATE_FIN,
         NEW_C_PSU_GENRE_FICHIER,
         NEW_L_PSU_REFERENCE_CLE,
         NEW_L_PSU_REFERENCE_SITE,
         NEW_I_PSU_EN_CLE,
         NEW_L_PSU_SI_CLE,
         NEW_I_PSU_GE_CLE,
         NEW_I_PSU_NA_CLE,
         NEW_I_PSU_TY_CLE,
         NEW_I_PSU_CA_CLE,
         NEW_I_PSU_FO_CLE,
         NEW_V_PSU_NAME,
         NEW_V_PSU_DO_ANCIENNE_REF,
         NEW_V_PSU_DO_NUMERO_DOSSIER,
         NEW_V_PSU_DO_REFERENCE_1,
         NEW_V_PSU_SU_REFERENCE_3,
         NEW_V_PSU_SU_NOM,
         NEW_V_PSU_SU_PRENOM,
         NEW_V_PSU_SO_REFERENCE_3,
         NEW_V_PSU_SO_NOM,
         NEW_V_PSU_VE_IMMATRICULATION,
         NEW_V_PSU_VE_PROVINCE,
         NEW_V_PSU_MOT_CLE1,
         NEW_V_PSU_MOT_CLE2,
         NEW_V_PSU_MOT_CLE3,
         NEW_V_PSU_CREE_PAR,
         NEW_D_PSU_DATE_CREATION,
         NEW_V_PSU_MODIFIE_PAR,
         NEW_D_PSU_DATE_MODIFICATION,
         NEW_V_PSU_APPROUVE_PAR,
         NEW_D_PSU_DATE_APPROBATION);
      commit;
    elsif action = 'U' then
      update PSU_MANDAT
         set V_PSU_NUMERO_MANDAT         = NEW_V_PSU_NUMERO_MANDAT,
             I_TA_CLE                    = NEW_I_TA_CLE,
             V_PSU_DESCRIPTION           = NEW_V_PSU_DESCRIPTION,
             V_PSU_DESTINATAIRE_A        = NEW_V_PSU_DESTINATAIRE_A,
             V_PSU_DESTINATAIRE_CC       = NEW_V_PSU_DESTINATAIRE_CC,
             V_PSU_DESTINATAIRE_CCI      = NEW_V_PSU_DESTINATAIRE_CCI,
             I_ST_CLE                    = NEW_I_ST_CLE,
             V_PSU_MESSAGE               = NEW_V_PSU_MESSAGE,
             D_PSU_DATE_DEBUT            = NEW_D_PSU_DATE_DEBUT,
             D_PSU_DATE_FIN              = NEW_D_PSU_DATE_FIN,
             C_PSU_GENRE_FICHIER         = NEW_C_PSU_GENRE_FICHIER,
             L_PSU_REFERENCE_CLE         = NEW_L_PSU_REFERENCE_CLE,
             L_PSU_REFERENCE_SITE        = NEW_L_PSU_REFERENCE_SITE,
             I_PSU_EN_CLE                = NEW_I_PSU_EN_CLE,
             L_PSU_SI_CLE                = NEW_L_PSU_SI_CLE,
             I_PSU_GE_CLE                = NEW_I_PSU_GE_CLE,
             I_PSU_NA_CLE                = NEW_I_PSU_NA_CLE,
             I_PSU_TY_CLE                = NEW_I_PSU_TY_CLE,
             I_PSU_CA_CLE                = NEW_I_PSU_CA_CLE,
             I_PSU_FO_CLE                = NEW_I_PSU_FO_CLE,
             V_PSU_NAME                  = NEW_V_PSU_NAME,
             V_PSU_DO_ANCIENNE_REFERENCE = NEW_V_PSU_DO_ANCIENNE_REF,
             V_PSU_DO_NUMERO_DOSSIER     = NEW_V_PSU_DO_NUMERO_DOSSIER,
             V_PSU_DO_REFERENCE_1        = NEW_V_PSU_DO_REFERENCE_1,
             V_PSU_SU_REFERENCE_3        = NEW_V_PSU_SU_REFERENCE_3,
             V_PSU_SU_NOM                = NEW_V_PSU_SU_NOM,
             V_PSU_SU_PRENOM             = NEW_V_PSU_SU_PRENOM,
             V_PSU_SO_REFERENCE_3        = NEW_V_PSU_SO_REFERENCE_3,
             V_PSU_SO_NOM                = NEW_V_PSU_SO_NOM,
             V_PSU_VE_IMMATRICULATION    = NEW_V_PSU_VE_IMMATRICULATION,
             V_PSU_VE_PROVINCE           = NEW_V_PSU_VE_PROVINCE,
             V_PSU_MOT_CLE1              = NEW_V_PSU_MOT_CLE1,
             V_PSU_MOT_CLE2              = NEW_V_PSU_MOT_CLE2,
             V_PSU_MOT_CLE3              = NEW_V_PSU_MOT_CLE3,
             V_PSU_CREE_PAR              = NEW_V_PSU_CREE_PAR,
             D_PSU_DATE_CREATION         = NEW_D_PSU_DATE_CREATION,
             V_PSU_MODIFIE_PAR           = cardex_special.g_usager,
             D_PSU_DATE_MODIFICATION     = SYSDATE,
             V_PSU_APPROUVE_PAR          = NEW_V_PSU_APPROUVE_PAR,
             D_PSU_DATE_APPROBATION      = NEW_D_PSU_DATE_APPROBATION
       where L_PSU_CLE = NEW_L_PSU_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Cas spécial de mise à jour (approbation du mandat).
    elsif action = 'M' then
      update PSU_MANDAT
         set V_PSU_APPROUVE_PAR     = NEW_V_PSU_APPROUVE_PAR,
             D_PSU_DATE_APPROBATION = NEW_D_PSU_DATE_APPROBATION,
             I_ST_CLE               = NEW_I_ST_CLE
       where L_PSU_CLE = NEW_L_PSU_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      /*Un mandat peut être supprimé seulement s'il n'a pas été approuvé ou si
        aucune consignation d'action n'a été enregistré.
        Autrement, il est désactivé.
      */
      delete from PSU_MANDAT
       where L_PSU_CLE = NEW_L_PSU_CLE
         and L_SI_CLE = NEW_L_SI_CLE
         and D_PSU_DATE_APPROBATION IS NULL;
      commit;
      delete from PSU_MANDAT
       where L_PSU_CLE = NEW_L_PSU_CLE
         and L_SI_CLE = NEW_L_SI_CLE
         and D_PSU_DATE_APPROBATION IS NOT NULL
         and (V_PSU_NUMERO_MANDAT NOT IN
             (SELECT DISTINCT C.V_CS_PSU_NUMERO_MANDAT
                 FROM CS_CONSIGNATION_ACTION C
                WHERE C.V_CS_PSU_NUMERO_MANDAT = V_PSU_NUMERO_MANDAT));
      commit;
      update PSU_MANDAT
         SET I_ST_CLE = NEW_I_ST_CLE
       where L_PSU_CLE = NEW_L_PSU_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'PSU',
                                 NEW_L_PSU_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_PSU_GENRE_FICHIER,
                                 NEW_L_PSU_REFERENCE_CLE,
                                 NEW_L_PSU_REFERENCE_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000, 'Database : SP_E_PSU_MANDAT', TRUE);
  END SP_E_PSU_MANDAT;
  PROCEDURE SP_E_CS_CONSIGNATION_ACTION(action                         IN CHAR,
                                        NEW_L_CS_CLE                   IN OUT CS_CONSIGNATION_ACTION.L_CS_CLE%TYPE,
                                        NEW_V_CS_PSU_NUMERO_MANDAT     IN CS_CONSIGNATION_ACTION.V_CS_PSU_NUMERO_MANDAT%TYPE,
                                        NEW_D_CS_DATE_CONSIGNATION     IN CS_CONSIGNATION_ACTION.D_CS_DATE_CONSIGNATION%TYPE,
                                        NEW_V_CS_INTERVENANT           IN CS_CONSIGNATION_ACTION.V_CS_INTERVENANT%TYPE,
                                        NEW_I_TA_CLE                   IN CS_CONSIGNATION_ACTION.I_TA_CLE%TYPE,
                                        NEW_C_CS_GENRE_FICHIER         IN CS_CONSIGNATION_ACTION.C_CS_GENRE_FICHIER%TYPE,
                                        NEW_L_CS_REFERENCE_SOURCE_CLE  IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_SOURCE_CLE%TYPE,
                                        NEW_L_CS_REFERENCE_SOURCE_SITE IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_SOURCE_SITE%TYPE,
                                        NEW_V_CS_REFERENCE_SOURCE      IN CS_CONSIGNATION_ACTION.V_CS_REFERENCE_SOURCE%TYPE,
                                        NEW_D_CS_DATE_COURRIEL         IN CS_CONSIGNATION_ACTION.D_CS_DATE_COURRIEL%TYPE,
                                        NEW_V_CS_REFERENCE_ACTION      IN CS_CONSIGNATION_ACTION.V_CS_REFERENCE_ACTION%TYPE,
                                        NEW_C_CS_GENRE_FICHIER_ACTION  IN CS_CONSIGNATION_ACTION.C_CS_GENRE_FICHIER_ACTION%TYPE,
                                        NEW_L_CS_REFERENCE_ACTION_CLE  IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_ACTION_CLE%TYPE,
                                        NEW_L_CS_REFERENCE_ACTION_SITE IN CS_CONSIGNATION_ACTION.L_CS_REFERENCE_ACTION_SITE%TYPE) is
  begin
    if action = 'I' then
      select seq_consignation_action.nextval into NEW_L_CS_CLE from dual;
      insert into CS_CONSIGNATION_ACTION
        (L_CS_CLE,
         V_CS_PSU_NUMERO_MANDAT,
         D_CS_DATE_CONSIGNATION,
         V_CS_INTERVENANT,
         I_TA_CLE,
         C_CS_GENRE_FICHIER,
         L_CS_REFERENCE_SOURCE_CLE,
         L_CS_REFERENCE_SOURCE_SITE,
         V_CS_REFERENCE_SOURCE,
         D_CS_DATE_COURRIEL,
         V_CS_REFERENCE_ACTION,
         C_CS_GENRE_FICHIER_ACTION,
         L_CS_REFERENCE_ACTION_CLE,
         L_CS_REFERENCE_ACTION_SITE)
      values
        (NEW_L_CS_CLE,
         NEW_V_CS_PSU_NUMERO_MANDAT,
         NEW_D_CS_DATE_CONSIGNATION,
         NEW_V_CS_INTERVENANT,
         NEW_I_TA_CLE,
         NEW_C_CS_GENRE_FICHIER,
         NEW_L_CS_REFERENCE_SOURCE_CLE,
         NEW_L_CS_REFERENCE_SOURCE_SITE,
         NEW_V_CS_REFERENCE_SOURCE,
         NEW_D_CS_DATE_COURRIEL,
         NEW_V_CS_REFERENCE_ACTION,
         NEW_C_CS_GENRE_FICHIER_ACTION,
         NEW_L_CS_REFERENCE_ACTION_CLE,
         NEW_L_CS_REFERENCE_ACTION_SITE);
      commit;
    elsif action = 'U' then
      --Ne devrait jamais se produire.
      update CS_CONSIGNATION_ACTION
         set V_CS_PSU_NUMERO_MANDAT     = NEW_V_CS_PSU_NUMERO_MANDAT,
             D_CS_DATE_CONSIGNATION     = NEW_D_CS_DATE_CONSIGNATION,
             V_CS_INTERVENANT           = NEW_V_CS_INTERVENANT,
             I_TA_CLE                   = NEW_I_TA_CLE,
             C_CS_GENRE_FICHIER         = NEW_C_CS_GENRE_FICHIER,
             L_CS_REFERENCE_SOURCE_CLE  = NEW_L_CS_REFERENCE_SOURCE_CLE,
             L_CS_REFERENCE_SOURCE_SITE = NEW_L_CS_REFERENCE_SOURCE_SITE,
             V_CS_REFERENCE_SOURCE      = NEW_V_CS_REFERENCE_SOURCE,
             D_CS_DATE_COURRIEL         = NEW_D_CS_DATE_COURRIEL,
             V_CS_REFERENCE_ACTION      = NEW_V_CS_REFERENCE_ACTION,
             C_CS_GENRE_FICHIER_ACTION  = NEW_C_CS_GENRE_FICHIER_ACTION,
             L_CS_REFERENCE_ACTION_CLE  = NEW_L_CS_REFERENCE_ACTION_CLE,
             L_CS_REFERENCE_ACTION_SITE = NEW_L_CS_REFERENCE_ACTION_SITE
       where L_CS_CLE = NEW_L_CS_CLE;
      commit;
    end if;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_CS_CONSIGNATION_ACTION',
                              TRUE);
  END SP_E_CS_CONSIGNATION_ACTION;

  PROCEDURE SP_E_MM_SUJET_SELECTIONNER(NEW_L_LMM_REFERENCE IN LMM_LIEN_MULTIMEDIA.L_LMM_REFERENCE%TYPE,
                                       NEW_L_LMM_REF_SITE  IN LMM_LIEN_MULTIMEDIA.L_LMM_REF_SITE%TYPE,
                                       NEW_L_MM_CLE        IN MM_MULTIMEDIA.L_MM_CLE%TYPE,
                                       NEW_L_SI_CLE        IN MM_MULTIMEDIA.L_SI_CLE%TYPE) is
  begin
    -- RETIRER LA SELECTION SUR LES AUTRES
    update MM_MULTIMEDIA MM
       set MM.B_MM_SELECTIONNER = 'no'
     where (MM.L_MM_CLE, MM.L_SI_CLE) in
           (select MM1.L_MM_CLE, MM1.L_SI_CLE
              from MM_MULTIMEDIA MM1, LMM_LIEN_MULTIMEDIA LMM1
             WHERE LMM1.L_MM_CLE = MM1.L_MM_CLE
               AND LMM1.L_MM_REF_SITE = MM1.L_SI_CLE
               AND LMM1.C_LMM_REF_GENRE = 'SU'
               AND LMM1.L_LMM_REFERENCE = NEW_L_LMM_REFERENCE
               AND LMM1.L_LMM_REF_SITE = NEW_L_LMM_REF_SITE);

    -- Sélectionner la photo du sujet
    update MM_MULTIMEDIA MM
       set MM.B_MM_SELECTIONNER = 'yes'
     where MM.L_MM_CLE = NEW_L_MM_CLE
       and MM.L_SI_CLE = NEW_L_SI_CLE;

    commit;
  END SP_E_MM_SUJET_SELECTIONNER;

  PROCEDURE SP_E_MM_SUJET_SELECT_DERNIER(NEW_L_LMM_REFERENCE IN LMM_LIEN_MULTIMEDIA.L_LMM_REFERENCE%TYPE,
                                         NEW_L_LMM_REF_SITE  IN LMM_LIEN_MULTIMEDIA.L_LMM_REF_SITE%TYPE) is
  begin
    -- RETIRER LA SELECTION SUR LES AUTRES
    update MM_MULTIMEDIA MM
       set MM.B_MM_SELECTIONNER = 'no'
     where (MM.L_MM_CLE, MM.L_SI_CLE) in
           (select MM1.L_MM_CLE, MM1.L_SI_CLE
              from MM_MULTIMEDIA MM1, LMM_LIEN_MULTIMEDIA LMM1
             WHERE LMM1.L_MM_CLE = MM1.L_MM_CLE
               AND LMM1.L_MM_REF_SITE = MM1.L_SI_CLE
               AND LMM1.C_LMM_REF_GENRE = 'SU'
               AND LMM1.L_LMM_REFERENCE = NEW_L_LMM_REFERENCE
               AND LMM1.L_LMM_REF_SITE = NEW_L_LMM_REF_SITE);

    -- Sélectionner la photo du sujet
    update MM_MULTIMEDIA MM
       set MM.B_MM_SELECTIONNER = 'yes'
     where MM.l_mm_cle in
           (select l.l_mm_cle
              from lmm_lien_multimedia l
             where l.c_lmm_ref_genre = 'SU'
               AND l.L_LMM_REFERENCE = NEW_L_LMM_REFERENCE
               AND l.L_LMM_REF_SITE = NEW_L_LMM_REF_SITE
               and l.l_lmm_cle in
                   (select max(l2.l_lmm_cle)
                      from lmm_lien_multimedia l2
                     where l2.c_lmm_ref_genre = 'SU'
                       AND l2.L_LMM_REFERENCE = NEW_L_LMM_REFERENCE
                       AND l2.L_LMM_REF_SITE = NEW_L_LMM_REF_SITE
                       AND l.l_lmm_reference = l2.l_lmm_reference));

    commit;
  END SP_E_MM_SUJET_SELECT_DERNIER;

  PROCEDURE SP_E_APPROUVE_LIEN_DOSSIER_CAT(NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                           NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE,
                                           approuve     IN CHAR) is
  begin

    If approuve = 'o' then
      UPDATE LDC_LIEN_DOSSIER_CATEGORIE LDC
         SET LDC.D_LDC_DATE_APPROBATION = sysdate,
             LDC.V_LDC_APPROUVE_PAR     = CARDEX_SPECIAL.g_usager
       WHERE LDC.L_DO_CLE = NEW_L_DO_CLE
         AND LDC.L_SI_CLE = NEW_L_SI_CLE;
    Else
      UPDATE LDC_LIEN_DOSSIER_CATEGORIE LDC
         SET LDC.D_LDC_DATE_APPROBATION = null,
             LDC.V_LDC_APPROUVE_PAR     = null
       WHERE LDC.L_DO_CLE = NEW_L_DO_CLE
         AND LDC.L_SI_CLE = NEW_L_SI_CLE;
    End if;

    commit;
  END SP_E_APPROUVE_LIEN_DOSSIER_CAT;

  procedure SP_E_LPD_PARTAGE_DOSSIER(action              IN CHAR,
                                     NEW_L_LPD_CLE       IN OUT LPD_PARTAGE_DOSSIER.L_LPD_CLE%TYPE,
                                     NEW_L_SI_CLE        IN OUT LPD_PARTAGE_DOSSIER.L_SI_CLE%TYPE,
                                     NEW_L_LPD_REFERENCE IN LPD_PARTAGE_DOSSIER.L_LPD_REFERENCE%TYPE,
                                     NEW_NAME            IN LPD_PARTAGE_DOSSIER.V_LPD_NAME%TYPE,
                                     NEW_L_LPD_REF_SITE  IN LPD_PARTAGE_DOSSIER.L_LPD_REF_SITE%TYPE,
                                     NEW_C_LPD_GENRE     IN LPD_PARTAGE_DOSSIER.C_LPD_GENRE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LPD_CLE from dual;
      NEW_L_SI_CLE := cardex_special.g_site;
      insert into LPD_PARTAGE_DOSSIER
        (L_LPD_CLE,
         L_SI_CLE,
         L_LPD_REFERENCE,
         V_LPD_NAME,
         V_LPD_CREE_PAR,
         D_LPD_DATE_CREATION,
         L_LPD_REF_SITE,
         C_LPD_GENRE)
      values
        (NEW_L_LPD_CLE,
         NEW_L_SI_CLE,
         NEW_L_LPD_REFERENCE,
         NEW_NAME,
         cardex_special.g_usager,
         sysdate,
         NEW_L_LPD_REF_SITE,
         NEW_C_LPD_GENRE);
      commit;
    elsif action = 'U' then
      update LPD_PARTAGE_DOSSIER
         set V_LPD_NAME = NEW_NAME
       where L_LPD_CLE = NEW_L_LPD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from LPD_PARTAGE_DOSSIER
       where L_LPD_CLE = NEW_L_LPD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'C' then
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toute la liste, pour permettre
      --l'insertion d'une nouvelle liste.
      delete from LPD_PARTAGE_DOSSIER
       where L_LPD_REFERENCE = NEW_L_LPD_REFERENCE
         and L_LPD_REF_SITE = NEW_L_LPD_REF_SITE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'LPD',
                                 NEW_L_LPD_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_LPD_GENRE,
                                 NEW_L_LPD_REFERENCE,
                                 NEW_L_LPD_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_LPD_PARTAGE_DOSSIER',
                              TRUE);
  END SP_E_LPD_PARTAGE_DOSSIER;

  procedure SP_E_IS_INSCRIPTION_DATE_SUIVI(NEW_L_IS_CLE IN OUT IS_INSCRIPTION.L_IS_CLE%TYPE,
                                           NEW_L_SI_CLE IN OUT IS_INSCRIPTION.L_SI_CLE%TYPE) IS
  begin
    update is_inscription i
       set i.d_is_courriel_suivi = sysdate
     where i.d_is_courriel_suivi is null
       and i.i_st_cle = 359
       and i.l_is_reference = NEW_L_IS_CLE
       and i.l_is_ref_site = NEW_L_SI_CLE;
  END SP_E_IS_INSCRIPTION_DATE_SUIVI;

  procedure SP_E_IS_INSCRIPTION_DATE_AIDE(NEW_L_IS_CLE IN OUT IS_INSCRIPTION.L_IS_CLE%TYPE,
                                          NEW_L_SI_CLE IN OUT IS_INSCRIPTION.L_SI_CLE%TYPE) IS
  begin
    update is_inscription i
       set i.d_is_courriel_aide = sysdate
     where i.B_IS_AIDE_INITIALE = 'yes'
       and i.d_is_courriel_aide is null
       and i.l_is_reference = NEW_L_IS_CLE
       and i.l_is_ref_site = NEW_L_SI_CLE;
  END SP_E_IS_INSCRIPTION_DATE_AIDE;

  procedure SP_E_BI_BILLET_PAIEMENT(NEW_L_BI_REF_CLE       IN BI_BILLET.L_BI_REF_CLE%TYPE,
                                    NEW_L_BI_REF_SITE      IN BI_BILLET.L_BI_REF_SITE%TYPE,
                                    NEW_D_BI_DATE_PAIEMENT IN VARCHAR2) IS
  BEGIN
    UPDATE BI_BILLET B
       SET B.D_BI_DATE_PAIEMENT = TO_DATE(NEW_D_BI_DATE_PAIEMENT,
                                          'YYYY-MM-DD')
     WHERE B.L_BI_REF_CLE = NEW_L_BI_REF_CLE
       AND B.L_BI_REF_SITE = NEW_L_BI_REF_SITE
       AND B.D_BI_DATE_PAIEMENT IS NULL;
    COMMIT;
  END SP_E_BI_BILLET_PAIEMENT;

  procedure SP_E_BI_BILLET(action                     IN CHAR,
                           NEW_L_BI_CLE               IN OUT BI_BILLET.L_BI_CLE%TYPE,
                           NEW_L_SI_CLE               IN OUT BI_BILLET.L_SI_CLE%TYPE,
                           NEW_L_BI_REF_CLE           IN BI_BILLET.L_BI_REF_CLE%TYPE,
                           NEW_L_BI_REF_SITE          IN BI_BILLET.L_BI_REF_SITE%TYPE,
                           NEW_C_BI_REF_GENRE         IN BI_BILLET.C_BI_REF_GENRE%TYPE,
                           NEW_V_BI_NOM               IN BI_BILLET.V_BI_NOM%TYPE,
                           NEW_V_BI_NUMERO_CONTROLE   IN BI_BILLET.V_BI_NUMERO_CONTROLE%TYPE,
                           NEW_L_BI_VALEUR            IN BI_BILLET.L_BI_VALEUR%TYPE,
                           NEW_B_BI_EXTRA             IN BI_BILLET.B_BI_EXTRA%TYPE,
                           NEW_B_BI_PARTICIPATION     IN BI_BILLET.B_BI_PARTICIPATION%TYPE,
                           NEW_B_BI_FORMULE_GROUPE    IN BI_BILLET.B_BI_FORMULE_GROUPE%TYPE,
                           NEW_L_TYPE_MISE            IN BI_BILLET.L_TYPE_MISE%TYPE,
                           NEW_L_BI_MONTANT_LOT       IN BI_BILLET.L_BI_MONTANT_LOT%TYPE,
                           NEW_L_SO_CLE_PROVENANCE    IN BI_BILLET.L_SO_CLE_PROVENANCE%TYPE,
                           NEW_L_SO_SITE_PROVENANCE   IN BI_BILLET.L_SO_SITE_PROVENANCE%TYPE,
                           NEW_D_BI_DATE_ACHAT        IN BI_BILLET.D_BI_DATE_ACHAT%TYPE,
                           NEW_L_SO_CLE_VALIDATION    IN BI_BILLET.L_SO_CLE_VALIDATION%TYPE,
                           NEW_L_SO_SITE_VALIDATION   IN BI_BILLET.L_SO_SITE_VALIDATION%TYPE,
                           NEW_D_BI_DATE_VALIDATION   IN BI_BILLET.D_BI_DATE_VALIDATION%TYPE,
                           NEW_L_JE_CLE               IN BI_BILLET.L_JE_CLE%TYPE,
                           NEW_L_SO_CLE_VERIFICATION  IN BI_BILLET.L_SO_CLE_VERIFICATION%TYPE,
                           NEW_L_SO_SITE_VERIFICATION IN BI_BILLET.L_SO_SITE_VERIFICATION%TYPE,
                           NEW_D_BI_DATE_VERIFICATION IN BI_BILLET.D_BI_DATE_VERIFICATION%TYPE,
                           NEW_L_SO_CLE_FAUTIF        IN BI_BILLET.L_SO_CLE_FAUTIF%TYPE,
                           NEW_L_SO_SITE_FAUTIF       IN BI_BILLET.L_SO_SITE_FAUTIF%TYPE,
                           NEW_D_BI_DATE_PAIEMENT     IN BI_BILLET.D_BI_DATE_PAIEMENT%TYPE,
                           NEW_B_BI_EXTRA_GAGNANT     IN BI_BILLET.B_BI_EXTRA_GAGNANT%TYPE,
                           NEW_L_BI_MONTANT_EXTRA     IN BI_BILLET.L_BI_MONTANT_EXTRA%TYPE) IS
  BEGIN

    if action = 'I' then
      select seq_dossier.nextval into NEW_L_BI_CLE from dual;
      NEW_L_SI_CLE := cardex_special.g_site;
      insert into BI_BILLET
        (L_BI_CLE,
         L_SI_CLE,
         L_BI_REF_CLE,
         L_BI_REF_SITE,
         C_BI_REF_GENRE,
         V_BI_NOM,
         V_BI_NUMERO_CONTROLE,
         L_BI_VALEUR,
         B_BI_EXTRA,
         B_BI_PARTICIPATION,
         B_BI_FORMULE_GROUPE,
         L_TYPE_MISE,
         L_BI_MONTANT_LOT,
         L_SO_CLE_PROVENANCE,
         L_SO_SITE_PROVENANCE,
         D_BI_DATE_ACHAT,
         L_SO_CLE_VALIDATION,
         L_SO_SITE_VALIDATION,
         D_BI_DATE_VALIDATION,
         V_BI_CREE_PAR,
         D_BI_DATE_CREATION,
         L_JE_CLE,
         L_SO_CLE_VERIFICATION,
         L_SO_SITE_VERIFICATION,
         D_BI_DATE_VERIFICATION,
         L_SO_CLE_FAUTIF,
         L_SO_SITE_FAUTIF,
         D_BI_DATE_PAIEMENT,
         B_BI_EXTRA_GAGNANT,
         L_BI_MONTANT_EXTRA)
      values
        (NEW_L_BI_CLE,
         NEW_L_SI_CLE,
         NEW_L_BI_REF_CLE,
         NEW_L_BI_REF_SITE,
         NEW_C_BI_REF_GENRE,
         NEW_V_BI_NOM,
         NEW_V_BI_NUMERO_CONTROLE,
         NEW_L_BI_VALEUR,
         NEW_B_BI_EXTRA,
         NEW_B_BI_PARTICIPATION,
         NEW_B_BI_FORMULE_GROUPE,
         NEW_L_TYPE_MISE,
         NEW_L_BI_MONTANT_LOT,
         NEW_L_SO_CLE_PROVENANCE,
         NEW_L_SO_SITE_PROVENANCE,
         NEW_D_BI_DATE_ACHAT,
         NEW_L_SO_CLE_VALIDATION,
         NEW_L_SO_SITE_VALIDATION,
         NEW_D_BI_DATE_VALIDATION,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_JE_CLE,
         NEW_L_SO_CLE_VERIFICATION,
         NEW_L_SO_SITE_VERIFICATION,
         NEW_D_BI_DATE_VERIFICATION,
         NEW_L_SO_CLE_FAUTIF,
         NEW_L_SO_SITE_FAUTIF,
         NEW_D_BI_DATE_PAIEMENT,
         NEW_B_BI_EXTRA_GAGNANT,
         NEW_L_BI_MONTANT_EXTRA);
    elsif action = 'U' then
      update BI_BILLET
         set V_BI_NOM               = NEW_V_BI_NOM,
             V_BI_NUMERO_CONTROLE   = NEW_V_BI_NUMERO_CONTROLE,
             L_BI_VALEUR            = NEW_L_BI_VALEUR,
             B_BI_EXTRA             = NEW_B_BI_EXTRA,
             B_BI_PARTICIPATION     = NEW_B_BI_PARTICIPATION,
             B_BI_FORMULE_GROUPE    = NEW_B_BI_FORMULE_GROUPE,
             L_TYPE_MISE            = NEW_L_TYPE_MISE,
             L_BI_MONTANT_LOT       = NEW_L_BI_MONTANT_LOT,
             L_SO_CLE_PROVENANCE    = NEW_L_SO_CLE_PROVENANCE,
             L_SO_SITE_PROVENANCE   = NEW_L_SO_SITE_PROVENANCE,
             D_BI_DATE_ACHAT        = NEW_D_BI_DATE_ACHAT,
             L_SO_CLE_VALIDATION    = NEW_L_SO_CLE_VALIDATION,
             L_SO_SITE_VALIDATION   = NEW_L_SO_SITE_VALIDATION,
             D_BI_DATE_VALIDATION   = NEW_D_BI_DATE_VALIDATION,
             L_JE_CLE               = NEW_L_JE_CLE,
             L_SO_CLE_VERIFICATION  = NEW_L_SO_CLE_VERIFICATION,
             L_SO_SITE_VERIFICATION = NEW_L_SO_SITE_VERIFICATION,
             D_BI_DATE_VERIFICATION = NEW_D_BI_DATE_VERIFICATION,
             L_SO_CLE_FAUTIF        = NEW_L_SO_CLE_FAUTIF,
             L_SO_SITE_FAUTIF       = NEW_L_SO_SITE_FAUTIF,
             D_BI_DATE_PAIEMENT     = NEW_D_BI_DATE_PAIEMENT,
             B_BI_EXTRA_GAGNANT     = NEW_B_BI_EXTRA_GAGNANT,
             L_BI_MONTANT_EXTRA     = NEW_L_BI_MONTANT_EXTRA
       where L_BI_CLE = NEW_L_BI_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
    elsif action = 'D' then
      delete from BI_BILLET
       where L_BI_CLE = NEW_L_BI_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
    end if;
  END SP_E_BI_BILLET;

  procedure SP_E_LME_LIEN_MISE_EVAL(action                    IN CHAR,
                                    NEW_L_LME_CLE             IN OUT LME_LIEN_MISE_EVAL.L_LME_CLE%TYPE,
                                    NEW_L_SI_CLE              IN OUT LME_LIEN_MISE_EVAL.L_SI_CLE%TYPE,
                                    NEW_L_EV_CLE              IN LME_LIEN_MISE_EVAL.L_EV_CLE%TYPE,
                                    NEW_L_EV_SI_CLE           IN LME_LIEN_MISE_EVAL.L_EV_SI_CLE%TYPE,
                                    NEW_L_LME_TYPE_JEU        IN LME_LIEN_MISE_EVAL.L_LME_TYPE_JEU%TYPE,
                                    NEW_V_LME_COMMENTAIRE_JEU IN LME_LIEN_MISE_EVAL.V_LME_COMMENTAIRE_JEU%TYPE,
                                    NEW_L_LME_MISE_MINIMUM    IN LME_LIEN_MISE_EVAL.L_LME_MISE_MINIMUM%TYPE,
                                    NEW_L_LME_MISE_MAXIMUM    IN LME_LIEN_MISE_EVAL.L_LME_MISE_MAXIMUM%TYPE,
                                    NEW_L_LME_MISE_MOYENNE    IN LME_LIEN_MISE_EVAL.L_LME_MISE_MOYENNE%TYPE,
                                    NEW_L_LME_GAIN_PERTE      IN LME_LIEN_MISE_EVAL.L_LME_GAIN_PERTE%TYPE,
                                    NEW_L_LME_MISE_TOTAL      IN LME_LIEN_MISE_EVAL.L_LME_MISE_TOTAL%TYPE,
                                    NEW_L_LME_TEMPS_JEU_MIN   IN LME_LIEN_MISE_EVAL.L_LME_TEMPS_JEU_MIN%TYPE,
                                    NEW_L_LME_TEMPS_JEU_MAX   IN LME_LIEN_MISE_EVAL.L_LME_TEMPS_JEU_MAX%TYPE,
                                    NEW_L_LME_TEMPS_JEU_TOTAL IN LME_LIEN_MISE_EVAL.L_LME_TEMPS_JEU_TOTAL%TYPE) IS
  BEGIN

    if action = 'I' then
      select seq_dossier.nextval into NEW_L_LME_CLE from dual;
      NEW_L_SI_CLE := cardex_special.g_site;
      insert into LME_LIEN_MISE_EVAL
        (L_LME_CLE,
         L_SI_CLE,
         L_EV_CLE,
         L_EV_SI_CLE,
         L_LME_TYPE_JEU,
         V_LME_COMMENTAIRE_JEU,
         L_LME_MISE_MINIMUM,
         L_LME_MISE_MAXIMUM,
         L_LME_MISE_MOYENNE,
         L_LME_GAIN_PERTE,
         L_LME_MISE_TOTAL,
         L_LME_TEMPS_JEU_MIN,
         L_LME_TEMPS_JEU_MAX,
         L_LME_TEMPS_JEU_TOTAL,
         V_LME_CREE_PAR,
         D_LME_DATE_CREATION)
      values
        (NEW_L_LME_CLE,
         NEW_L_SI_CLE,
         NEW_L_EV_CLE,
         NEW_L_EV_SI_CLE,
         NEW_L_LME_TYPE_JEU,
         NEW_V_LME_COMMENTAIRE_JEU,
         NEW_L_LME_MISE_MINIMUM,
         NEW_L_LME_MISE_MAXIMUM,
         NEW_L_LME_MISE_MOYENNE,
         NEW_L_LME_GAIN_PERTE,
         NEW_L_LME_MISE_TOTAL,
         NEW_L_LME_TEMPS_JEU_MIN,
         NEW_L_LME_TEMPS_JEU_MAX,
         NEW_L_LME_TEMPS_JEU_TOTAL,
         CARDEX_SPECIAL.g_usager,
         SYSDATE);
    elsif action = 'U' then
      update LME_LIEN_MISE_EVAL
         set L_EV_CLE              = NEW_L_EV_CLE,
             L_EV_SI_CLE           = NEW_L_EV_SI_CLE,
             L_LME_TYPE_JEU        = NEW_L_LME_TYPE_JEU,
             V_LME_COMMENTAIRE_JEU = NEW_V_LME_COMMENTAIRE_JEU,
             L_LME_MISE_MINIMUM    = NEW_L_LME_MISE_MINIMUM,
             L_LME_MISE_MAXIMUM    = NEW_L_LME_MISE_MAXIMUM,
             L_LME_MISE_MOYENNE    = NEW_L_LME_MISE_MOYENNE,
             L_LME_GAIN_PERTE      = NEW_L_LME_GAIN_PERTE,
             L_LME_MISE_TOTAL      = NEW_L_LME_MISE_TOTAL,
             L_LME_TEMPS_JEU_MIN   = NEW_L_LME_TEMPS_JEU_MIN,
             L_LME_TEMPS_JEU_MAX   = NEW_L_LME_TEMPS_JEU_MAX,
             L_LME_TEMPS_JEU_TOTAL = NEW_L_LME_TEMPS_JEU_TOTAL,
             V_LME_CREE_PAR        = CARDEX_SPECIAL.g_usager,
             D_LME_DATE_CREATION   = SYSDATE
       where L_LME_CLE = NEW_L_LME_CLE
         AND L_SI_CLE = NEW_L_SI_CLE;
    elsif action = 'D' then
      delete from LJV_JEU_EVAL
       where L_LME_CLE = NEW_L_LME_CLE
         AND L_SI_CLE = NEW_L_SI_CLE;
      commit;
      delete from LVE_LIEN_VISITE_EVAL
       where L_LME_CLE = NEW_L_LME_CLE
         AND L_SI_CLE = NEW_L_SI_CLE;
      commit;
      delete from LME_LIEN_MISE_EVAL
       where L_LME_CLE = NEW_L_LME_CLE
         AND L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Nouvelle action pour le Cardex-Web permettant de
      --supprimer d'un coup ("Clear") toutes les entrées, pour permettre
      --l'insertion d'une nouvelle liste.
    elsif action = 'C' then
      delete from LJV_JEU_EVAL ljv
       where (ljv.l_lme_cle, ljv.l_lme_ref_site) in
             (select lme.l_lme_cle, lme.l_si_cle
                from LME_LIEN_MISE_EVAL lme
               where lme.l_ev_cle = NEW_L_EV_CLE
                 and lme.l_ev_si_cle = NEW_L_EV_SI_CLE);
      commit;
      delete from LVE_LIEN_VISITE_EVAL lve
       where (lve.l_lme_cle, lve.l_lme_ref_site) in
             (select lme.l_lme_cle, lme.l_si_cle
                from LME_LIEN_MISE_EVAL lme
               where lme.l_ev_cle = NEW_L_EV_CLE
                 and lme.l_ev_si_cle = NEW_L_EV_SI_CLE);
      commit;
      delete from LME_LIEN_MISE_EVAL
       where l_ev_cle = NEW_L_EV_CLE
         AND l_ev_si_cle = NEW_L_EV_SI_CLE;
      commit;
    end if;
    commit;
  END SP_E_LME_LIEN_MISE_EVAL;

  procedure SP_E_EV_EVALUATION(action                      IN CHAR,
                               NEW_L_EV_CLE                IN OUT EV_EVALUATION.L_EV_CLE%TYPE,
                               NEW_L_SI_CLE                IN OUT EV_EVALUATION.L_SI_CLE%TYPE,
                               NEW_L_EV_REF_CLE            IN EV_EVALUATION.L_EV_REF_CLE%TYPE,
                               NEW_L_EV_REF_SITE           IN EV_EVALUATION.L_EV_REF_SITE%TYPE,
                               NEW_D_DATE_DEBUT_EVAL       IN EV_EVALUATION.D_DATE_DEBUT_EVAL%TYPE,
                               NEW_D_DATE_FIN_EVAL         IN EV_EVALUATION.D_DATE_FIN_EVAL%TYPE,
                               NEW_L_EV_NO_CLIENT_BIJOU    IN EV_EVALUATION.L_EV_NO_CLIENT_BIJOU%TYPE,
                               NEW_V_EV_FAITS_CONNUS       IN EV_EVALUATION.V_EV_FAITS_CONNUS%TYPE,
                               NEW_V_EV_PROXIMITE          IN EV_EVALUATION.V_EV_PROXIMITE%TYPE,
                               NEW_V_EV_GRADATION          IN EV_EVALUATION.V_EV_GRADATION%TYPE,
                               NEW_V_EV_TRANSACTION        IN EV_EVALUATION.V_EV_TRANSACTION%TYPE,
                               NEW_V_EV_COMMENTAIRE_ETAT   IN EV_EVALUATION.V_EV_COMMENTAIRE_ETAT%TYPE,
                               NEW_V_EV_COMMENTAIRE_PROPOS IN EV_EVALUATION.V_EV_COMMENTAIRE_PROPOS%TYPE,
                               NEW_V_EV_COMMENTAIRE_AUTRE  IN EV_EVALUATION.V_EV_COMMENTAIRE_AUTRE%TYPE,
                               NEW_V_EV_COMMENTAIRE_SIGNE  IN EV_EVALUATION.V_EV_COMMENTAIRE_SIGNE%TYPE,
                               NEW_V_EV_SIGNATAIRE1        IN EV_EVALUATION.V_EV_SIGNATAIRE1%TYPE,
                               NEW_V_EV_SIGNATAIRE2        IN EV_EVALUATION.V_EV_SIGNATAIRE2%TYPE,
                               NEW_V_EV_SIGNATAIRE3        IN EV_EVALUATION.V_EV_SIGNATAIRE3%TYPE,
                               NEW_V_EV_SIGNATAIRE4        IN EV_EVALUATION.V_EV_SIGNATAIRE4%TYPE,
                               NEW_V_EV_SIGNATAIRE5        IN EV_EVALUATION.V_EV_SIGNATAIRE5%TYPE,
                               NEW_D_EV_DATE_EVALUATION    IN EV_EVALUATION.D_EV_DATE_EVALUATION%TYPE) IS
    CURSOR cLienMiseEval IS
      SELECT *
        FROM LME_LIEN_MISE_EVAL
       where L_EV_CLE = NEW_L_EV_CLE
         AND L_EV_SI_CLE = NEW_L_SI_CLE;
  BEGIN

    if action = 'I' then
      select seq_dossier.nextval into NEW_L_EV_CLE from dual;
      NEW_L_SI_CLE := cardex_special.g_site;
      insert into EV_EVALUATION
        (L_EV_CLE,
         L_SI_CLE,
         L_EV_REF_CLE,
         L_EV_REF_SITE,
         D_DATE_DEBUT_EVAL,
         D_DATE_FIN_EVAL,
         L_EV_NO_CLIENT_BIJOU,
         V_EV_FAITS_CONNUS,
         V_EV_PROXIMITE,
         V_EV_GRADATION,
         V_EV_TRANSACTION,
         V_EV_COMMENTAIRE_ETAT,
         V_EV_COMMENTAIRE_PROPOS,
         V_EV_COMMENTAIRE_AUTRE,
         V_EV_COMMENTAIRE_SIGNE,
         V_EV_SIGNATAIRE1,
         V_EV_SIGNATAIRE2,
         V_EV_SIGNATAIRE3,
         V_EV_SIGNATAIRE4,
         V_EV_SIGNATAIRE5,
         D_EV_DATE_EVALUATION,
         V_EV_CREE_PAR,
         D_EV_DATE_CREATION)
      values
        (NEW_L_EV_CLE,
         NEW_L_SI_CLE,
         NEW_L_EV_REF_CLE,
         NEW_L_EV_REF_SITE,
         NEW_D_DATE_DEBUT_EVAL,
         NEW_D_DATE_FIN_EVAL,
         NEW_L_EV_NO_CLIENT_BIJOU,
         NEW_V_EV_FAITS_CONNUS,
         NEW_V_EV_PROXIMITE,
         NEW_V_EV_GRADATION,
         NEW_V_EV_TRANSACTION,
         NEW_V_EV_COMMENTAIRE_ETAT,
         NEW_V_EV_COMMENTAIRE_PROPOS,
         NEW_V_EV_COMMENTAIRE_AUTRE,
         NEW_V_EV_COMMENTAIRE_SIGNE,
         NEW_V_EV_SIGNATAIRE1,
         NEW_V_EV_SIGNATAIRE2,
         NEW_V_EV_SIGNATAIRE3,
         NEW_V_EV_SIGNATAIRE4,
         NEW_V_EV_SIGNATAIRE5,
         NEW_D_EV_DATE_EVALUATION,
         CARDEX_SPECIAL.g_usager,
         SYSDATE);
    elsif action = 'U' then
      update EV_EVALUATION
         set D_DATE_DEBUT_EVAL       = NEW_D_DATE_DEBUT_EVAL,
             D_DATE_FIN_EVAL         = NEW_D_DATE_FIN_EVAL,
             L_EV_NO_CLIENT_BIJOU    = NEW_L_EV_NO_CLIENT_BIJOU,
             V_EV_FAITS_CONNUS       = NEW_V_EV_FAITS_CONNUS,
             V_EV_PROXIMITE          = NEW_V_EV_PROXIMITE,
             V_EV_GRADATION          = NEW_V_EV_GRADATION,
             V_EV_TRANSACTION        = NEW_V_EV_TRANSACTION,
             V_EV_COMMENTAIRE_ETAT   = NEW_V_EV_COMMENTAIRE_ETAT,
             V_EV_COMMENTAIRE_PROPOS = NEW_V_EV_COMMENTAIRE_PROPOS,
             V_EV_COMMENTAIRE_AUTRE  = NEW_V_EV_COMMENTAIRE_AUTRE,
             V_EV_COMMENTAIRE_SIGNE  = NEW_V_EV_COMMENTAIRE_SIGNE,
             V_EV_SIGNATAIRE1        = NEW_V_EV_SIGNATAIRE1,
             V_EV_SIGNATAIRE2        = NEW_V_EV_SIGNATAIRE2,
             V_EV_SIGNATAIRE3        = NEW_V_EV_SIGNATAIRE3,
             V_EV_SIGNATAIRE4        = NEW_V_EV_SIGNATAIRE4,
             V_EV_SIGNATAIRE5        = NEW_V_EV_SIGNATAIRE5,
             D_EV_DATE_EVALUATION    = NEW_D_EV_DATE_EVALUATION,
             V_EV_MODIFIE_PAR        = CARDEX_SPECIAL.g_usager,
             D_EV_DATE_MODIFICATION  = SYSDATE
       where L_EV_CLE = NEW_L_EV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
    elsif action = 'D' then
      FOR lme in cLienMiseEval LOOP
        SP_E_LME_LIEN_MISE_EVAL(action,
                                lme.L_LME_CLE,
                                lme.L_SI_CLE,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null);
      END LOOP;
      delete from LEE_LIEN_ETAT_EVAL
       where L_EV_CLE = NEW_L_EV_CLE
         and L_EV_REF_SITE = NEW_L_SI_CLE;
      commit;
      delete from LPE_LIEN_PROPOS_EVAL
       where L_EV_CLE = NEW_L_EV_CLE
         and L_EV_REF_SITE = NEW_L_SI_CLE;
      commit;
      delete from EV_EVALUATION
       where L_EV_CLE = NEW_L_EV_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
    end if;
    commit;
  END SP_E_EV_EVALUATION;

  FUNCTION F_LIRE_FICHIER(NEW_P_FILE IN VARCHAR2) RETURN BLOB IS
  BEGIN
    RETURN FTP.GET_LOCAL_BINARY_DATA(P_DIR  => 'PHOTO_DIR',
                                     P_FILE => NEW_P_FILE);
  END F_LIRE_FICHIER;

  PROCEDURE SP_AJOUT_FICHIER(NEW_P_DATA IN BLOB, NEW_P_FILE IN VARCHAR2) IS
  BEGIN
    FTP.PUT_LOCAL_BINARY_DATA(P_DIR  => 'PHOTO_DIR',
                              P_FILE => NEW_P_FILE,
                              P_DATA => NEW_P_DATA);
  END SP_AJOUT_FICHIER;

  PROCEDURE SP_DELETE_FICHIER(NEW_P_FILE IN VARCHAR2) IS
  BEGIN
    UTL_FILE.FREMOVE(location => 'PHOTO_DIR', filename => new_p_file);
  END SP_DELETE_FICHIER;

  FUNCTION F_EXISTE(file_in IN VARCHAR2) RETURN PLS_INTEGER IS
    TYPE fgetattr_t IS RECORD(
      fexists     BOOLEAN,
      file_length PLS_INTEGER,
      block_size  PLS_INTEGER);
    fgetattr_rec fgetattr_t;
  BEGIN
    UTL_FILE.fgetattr(location    => 'PHOTO_DIR',
                      filename    => file_in,
                      fexists     => fgetattr_rec.fexists,
                      file_length => fgetattr_rec.file_length,
                      block_size  => fgetattr_rec.block_size);
    RETURN fgetattr_rec.file_length;
  END F_EXISTE;

  --Fonction pour concaténer les résultats sur une seule ligne. Sert dans les rapports Jasper.
  FUNCTION CONCATENE_LISTE(p_cursor IN SYS_REFCURSOR) RETURN VARCHAR2 IS
    l_return VARCHAR2(32767);
    l_temp   VARCHAR2(32767);
  BEGIN
    LOOP
      FETCH p_cursor
        INTO l_temp;
      EXIT WHEN p_cursor%NOTFOUND;
      l_return := l_return || ', ' || l_temp;
    END LOOP;
    RETURN LTRIM(l_return, ', ');
  END;

  --Mise à jour des adresses de détaillants qui ne sont pas dans Cardex.
  --On ne considère que les changements de téléphone ou de courriel.
  PROCEDURE SP_U_MAJ_ADRESSE_RDD IS
  BEGIN
    MERGE INTO ad_adresse a
    using (SELECT distinct v.TEL_CODE_REGIONAL,
                           v.TEL_NO,
                           v.TEL_NO_POSTE,
                           v.FAX_CODE_REGIONAL,
                           v.FAX_NO,
                           v.TEL_SANS_FRAIS_CODE_REGIONAL,
                           v.TEL_SANS_FRAIS_NO,
                           v.TEL_SANS_FRAIS_NO_POSTE,
                           v.EMAIL,
                           a1.l_ad_cle,
                           a1.l_si_cle
             FROM VEXI_CDX_DDS_DETAILLANT V, SO_SOCIETE S, ad_adresse a1
            WHERE S.V_SO_REFERENCE_3 = V.NO_DETAILLANT
              and S.L_SI_CLE = 8
              and s.i_cc_cle <> 14920
              and a1.l_ad_reference = s.l_so_cle
              and a1.l_ad_ref_site = s.l_si_cle
              and a1.c_ad_ref_genre = 'SO'
              and a1.b_ad_ind_rdd = 'yes'
              and (replace(a1.v_ad_telephone_1, '-', '') <>
                  trim(v.TEL_CODE_REGIONAL || ' ' || v.TEL_NO || ' ' ||
                        v.TEL_NO_POSTE) or
                  replace(a1.v_ad_telephone_2, '-', '') <>
                  trim(v.FAX_CODE_REGIONAL || ' ' || v.FAX_NO) or
                  replace(a1.v_ad_telephone_3, '-', '') <>
                  trim(v.TEL_SANS_FRAIS_CODE_REGIONAL || ' ' ||
                        v.TEL_SANS_FRAIS_NO || ' ' ||
                        v.TEL_SANS_FRAIS_NO_POSTE) or
                  a1.v_ad_electronique_1 <> trim(v.EMAIL))
              and exists (
              select l.l_ldd_dossier_associe
                from ldd_lien_dossier l, do_dossier d
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and d.i_ca_cle = 662
                 and l.l_ldd_dossier_associe = s.l_so_cle
                 and l.l_ldd_site = s.l_si_cle
                 and l.c_ldd_genre = 'SO')) src

    ON (a.l_ad_cle = src.l_ad_cle and a.l_si_cle = src.l_si_cle)

    WHEN MATCHED THEN
      UPDATE
         SET a.v_ad_telephone_1 = trim(src.TEL_CODE_REGIONAL || ' ' ||
                                       src.TEL_NO || ' ' || src.TEL_NO_POSTE),
             a.v_ad_telephone_2 = trim(src.FAX_CODE_REGIONAL || ' ' ||
                                       src.FAX_NO),

             a.v_ad_telephone_3    = trim(src.TEL_SANS_FRAIS_CODE_REGIONAL || ' ' ||
                                          src.TEL_SANS_FRAIS_NO || ' ' ||
                                          src.TEL_SANS_FRAIS_NO_POSTE),
             a.v_ad_electronique_1 = trim(src.EMAIL);
    commit;
  END SP_U_MAJ_ADRESSE_RDD;

  procedure SP_E_AD_ADRESSE_RDD(action                  IN CHAR,
                                NEW_L_AD_CLE            IN OUT AD_ADRESSE.L_AD_CLE%TYPE,
                                NEW_L_SI_CLE            IN OUT AD_ADRESSE.L_SI_CLE%TYPE,
                                NEW_L_AD_REFERENCE      IN AD_ADRESSE.L_AD_REFERENCE%TYPE,
                                NEW_V_AD_ADRESSE        IN AD_ADRESSE.V_AD_ADRESSE%TYPE,
                                NEW_V_AD_ADRESSE2       IN AD_ADRESSE.V_AD_ADRESSE2%TYPE,
                                NEW_V_AD_NUM_MUNICIPAL  IN AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE,
                                NEW_L_AD_TYPE_RUE       IN AD_ADRESSE.L_AD_TYPE_RUE%TYPE,
                                NEW_V_AD_NOM_RUE        IN AD_ADRESSE.V_AD_NOM_RUE%TYPE,
                                NEW_L_AD_POINT_CARDINAL IN AD_ADRESSE.L_AD_POINT_CARDINAL%TYPE,
                                NEW_L_AD_TYPE_UNITE     IN AD_ADRESSE.L_AD_TYPE_UNITE%TYPE,
                                NEW_V_AD_NO_UNITE       IN AD_ADRESSE.V_AD_NO_UNITE%TYPE,
                                NEW_V_AD_ADRESSE_POST   IN AD_ADRESSE.V_AD_ADRESSE_POST%TYPE,
                                NEW_V_AD_ELECTRONIQUE_1 IN AD_ADRESSE.V_AD_ELECTRONIQUE_1%TYPE,
                                NEW_V_AD_ELECTRONIQUE_2 IN AD_ADRESSE.V_AD_ELECTRONIQUE_2%TYPE,
                                NEW_I_PA_CLE            IN AD_ADRESSE.I_PA_CLE%TYPE,
                                NEW_L_PR_CLE            IN AD_ADRESSE.L_PR_CLE%TYPE,
                                NEW_L_VI_CLE            IN AD_ADRESSE.L_VI_CLE%TYPE,
                                NEW_V_AD_CODE_POSTAL    IN AD_ADRESSE.V_AD_CODE_POSTAL%TYPE,
                                NEW_I_TE_CLE_1          IN AD_ADRESSE.I_TE_CLE_1%TYPE,
                                NEW_V_AD_TELEPHONE_1    IN AD_ADRESSE.V_AD_TELEPHONE_1%TYPE,
                                NEW_I_TE_CLE_2          IN AD_ADRESSE.I_TE_CLE_2%TYPE,
                                NEW_V_AD_TELEPHONE_2    IN AD_ADRESSE.V_AD_TELEPHONE_2%TYPE,
                                NEW_I_TE_CLE_3          IN AD_ADRESSE.I_TE_CLE_3%TYPE,
                                NEW_V_AD_TELEPHONE_3    IN AD_ADRESSE.V_AD_TELEPHONE_3%TYPE,
                                NEW_I_ST_CLE            IN AD_ADRESSE.I_ST_CLE%TYPE,
                                NEW_V_AD_COMMENTAIRE    IN AD_ADRESSE.V_AD_COMMENTAIRE%TYPE,
                                NEW_L_AD_REF_SITE       IN AD_ADRESSE.L_AD_REF_SITE%TYPE,
                                NEW_C_AD_REF_GENRE      IN AD_ADRESSE.C_AD_REF_GENRE%TYPE,
                                NEW_B_AD_IND_RDD        IN AD_ADRESSE.B_AD_IND_RDD%TYPE) IS
  begin
    -- Les autres adresses principale deviennent des adresses secondaires
    if NEW_I_ST_CLE = 621 then
      Update AD_ADRESSE
         set I_ST_CLE = 622
       WHERE L_AD_REFERENCE = NEW_L_AD_REFERENCE
         AND L_AD_REF_SITE = NEW_L_AD_REF_SITE
         and I_ST_CLE = 621;
    end if;
    select seq_dossier.nextval into NEW_L_AD_CLE from dual;
    insert into AD_ADRESSE
      (L_AD_CLE,
       L_SI_CLE,
       L_AD_REFERENCE,
       V_AD_ADRESSE,
       V_AD_ADRESSE2,
       V_AD_NUM_MUNICIPAL,
       L_AD_TYPE_RUE,
       V_AD_NOM_RUE,
       L_AD_POINT_CARDINAL,
       L_AD_TYPE_UNITE,
       V_AD_NO_UNITE,
       V_AD_ADRESSE_POST,
       V_AD_ELECTRONIQUE_1,
       V_AD_ELECTRONIQUE_2,
       I_PA_CLE,
       L_PR_CLE,
       L_VI_CLE,
       V_AD_CODE_POSTAL,
       I_TE_CLE_1,
       V_AD_TELEPHONE_1,
       I_TE_CLE_2,
       V_AD_TELEPHONE_2,
       I_TE_CLE_3,
       V_AD_TELEPHONE_3,
       I_ST_CLE,
       V_AD_COMMENTAIRE,
       V_AD_CREE_PAR,
       D_AD_DATE_CREATION,
       L_AD_REF_SITE,
       C_AD_REF_GENRE,
       B_AD_IND_RDD)
    values
      (NEW_L_AD_CLE,
       NEW_L_SI_CLE,
       NEW_L_AD_REFERENCE,
       NEW_V_AD_ADRESSE,
       NEW_V_AD_ADRESSE2,
       NEW_V_AD_NUM_MUNICIPAL,
       NEW_L_AD_TYPE_RUE,
       NEW_V_AD_NOM_RUE,
       NEW_L_AD_POINT_CARDINAL,
       NEW_L_AD_TYPE_UNITE,
       NEW_V_AD_NO_UNITE,
       NEW_V_AD_ADRESSE_POST,
       NEW_V_AD_ELECTRONIQUE_1,
       NEW_V_AD_ELECTRONIQUE_2,
       NEW_I_PA_CLE,
       NEW_L_PR_CLE,
       NEW_L_VI_CLE,
       NEW_V_AD_CODE_POSTAL,
       NEW_I_TE_CLE_1,
       NEW_V_AD_TELEPHONE_1,
       NEW_I_TE_CLE_2,
       NEW_V_AD_TELEPHONE_2,
       NEW_I_TE_CLE_3,
       NEW_V_AD_TELEPHONE_3,
       NEW_I_ST_CLE,
       NEW_V_AD_COMMENTAIRE,
       CARDEX_SPECIAL.g_usager,
       sysdate,
       NEW_L_AD_REF_SITE,
       NEW_C_AD_REF_GENRE,
       NEW_B_AD_IND_RDD);
    commit;

    cardex_special.sp_e_ac_acces(action,
                                 'AD',
                                 NEW_L_AD_CLE,
                                 NEW_L_SI_CLE,
                                 NEW_C_AD_REF_GENRE,
                                 NEW_L_AD_REFERENCE,
                                 NEW_L_AD_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_AD_ADRESSE_RDD',
                              True);
  END SP_E_AD_ADRESSE_RDD;

  PROCEDURE SP_E_UR_URGENCE(action                    IN CHAR,
                            NEW_L_UR_CLE              IN OUT UR_URGENCE.L_UR_CLE%TYPE,
                            NEW_L_SI_CLE              IN OUT SI_SITE.L_SI_CLE%TYPE,
                            NEW_I_CL_CLE              IN CL_CLASSE.I_CL_CLE%TYPE,
                            NEW_L_UR_REF_CLE          IN DO_DOSSIER.L_DO_CLE%TYPE,
                            NEW_L_UR_REF_SITE         IN DO_DOSSIER.L_SI_CLE%TYPE,
                            NEW_L_SO_REF_CLE          IN SO_SOCIETE.L_SO_CLE%TYPE,
                            NEW_L_SO_REF_SITE         IN SO_SOCIETE.L_SI_CLE%TYPE,
                            NEW_V_UR_UNITE            IN UR_URGENCE.V_UR_UNITE%TYPE,
                            NEW_V_UR_DISTRICT         IN UR_URGENCE.V_UR_DISTRICT%TYPE,
                            NEW_V_UR_CONTACT          IN UR_URGENCE.V_UR_CONTACT%TYPE,
                            NEW_V_UR_CONTACT_PRENOM   IN UR_URGENCE.V_UR_CONTACT_PRENOM%TYPE,
                            NEW_V_UR_FONCTION         IN UR_URGENCE.V_UR_FONCTION%TYPE,
                            NEW_V_UR_MATRICULE        IN UR_URGENCE.V_UR_MATRICULE%TYPE,
                            NEW_V_UR_TELEPHONE        IN UR_URGENCE.V_UR_TELEPHONE%TYPE,
                            NEW_L_UR_POSTE            IN UR_URGENCE.L_UR_POSTE%TYPE,
                            NEW_V_UR_TELECOPIEUR      IN UR_URGENCE.V_UR_TELECOPIEUR%TYPE,
                            NEW_V_UR_VILLE            IN UR_URGENCE.V_UR_VILLE%TYPE,
                            NEW_V_UR_COURRIEL         IN UR_URGENCE.V_UR_COURRIEL%TYPE,
                            NEW_V_UR_EVENEMENT        IN UR_URGENCE.V_UR_EVENEMENT%TYPE,
                            NEW_V_UR_REPONDANT        IN UR_URGENCE.V_UR_REPONDANT%TYPE,
                            NEW_L_UR_MOTIF            IN UR_URGENCE.L_UR_MOTIF%TYPE,
                            NEW_I_ST_CLE              IN ST_STATUT.I_ST_CLE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_UR_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into UR_URGENCE
        (L_UR_CLE,
         L_SI_CLE,
         I_CL_CLE,
         L_SO_REF_CLE,
         L_SO_REF_SITE,
         V_UR_UNITE,
         V_UR_DISTRICT,
         V_UR_CONTACT,
         V_UR_CONTACT_PRENOM,
         V_UR_FONCTION,
         V_UR_MATRICULE,
         V_UR_TELEPHONE,
         L_UR_POSTE,
         V_UR_TELECOPIEUR,
         V_UR_VILLE,
         V_UR_COURRIEL,
         V_UR_EVENEMENT,
         V_UR_REPONDANT,
         L_UR_MOTIF,
         I_ST_CLE,
         V_UR_CREE_PAR,
         D_UR_DATE_CREATION,
         L_UR_REF_CLE,
         L_UR_REF_SITE)
      values
        (NEW_L_UR_CLE,
         NEW_L_SI_CLE,
         NEW_I_CL_CLE,
         NEW_L_SO_REF_CLE,
         NEW_L_SO_REF_SITE,
         NEW_V_UR_UNITE,
         NEW_V_UR_DISTRICT,
         NEW_V_UR_CONTACT,
         NEW_V_UR_CONTACT_PRENOM,
         NEW_V_UR_FONCTION,
         NEW_V_UR_MATRICULE,
         NEW_V_UR_TELEPHONE,
         NEW_L_UR_POSTE,
         NEW_V_UR_TELECOPIEUR,
         NEW_V_UR_VILLE,
         NEW_V_UR_COURRIEL,
         NEW_V_UR_EVENEMENT,
         NEW_V_UR_REPONDANT,
         NEW_L_UR_MOTIF,
         NEW_I_ST_CLE,
         cardex_special.g_usager,
         sysdate,
         NEW_L_UR_REF_CLE,
         NEW_L_UR_REF_SITE);
      commit;
    elsif action = 'U' then
      update UR_URGENCE
         set I_CL_CLE            = NEW_I_CL_CLE,
             L_SO_REF_CLE        = NEW_L_SO_REF_CLE,
             L_SO_REF_SITE       = NEW_L_SO_REF_SITE,
             V_UR_UNITE          = NEW_V_UR_UNITE,
             V_UR_DISTRICT       = NEW_V_UR_DISTRICT,
             V_UR_CONTACT        = NEW_V_UR_CONTACT,
             V_UR_CONTACT_PRENOM = NEW_V_UR_CONTACT_PRENOM,
             V_UR_FONCTION       = NEW_V_UR_FONCTION,
             V_UR_MATRICULE      = NEW_V_UR_MATRICULE,
             V_UR_TELEPHONE      = NEW_V_UR_TELEPHONE,
             L_UR_POSTE          = NEW_L_UR_POSTE,
             V_UR_TELECOPIEUR    = NEW_V_UR_TELECOPIEUR,
             V_UR_VILLE          = NEW_V_UR_VILLE,
             V_UR_COURRIEL       = NEW_V_UR_COURRIEL,
             V_UR_EVENEMENT      = NEW_V_UR_EVENEMENT,
             V_UR_REPONDANT      = NEW_V_UR_REPONDANT,
             L_UR_MOTIF          = NEW_L_UR_MOTIF,
             I_ST_CLE            = NEW_I_ST_CLE,
             L_UR_REF_CLE        = NEW_L_UR_REF_CLE,
             L_UR_REF_SITE       = NEW_L_UR_REF_SITE
       where L_UR_CLE = NEW_L_UR_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from UR_URGENCE
       where L_UR_CLE = NEW_L_UR_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'UR',
                                 NEW_L_UR_CLE,
                                 NEW_L_SI_CLE,
                                 'UR',
                                 NEW_L_UR_REF_CLE,
                                 NEW_L_UR_REF_SITE,
                                 NULL,
                                 NULL,
                                 NULL);
  exception
    when others then
      raise_application_error(-20000, 'Database : SP_E_UR_URGENCE', TRUE);
  END SP_E_UR_URGENCE;
  
  procedure SP_D_LDD_LIEN_DOSSIER(NEW_L_LDD_CLE             IN OUT LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
                                  NEW_L_SI_CLE              IN OUT LDD_LIEN_DOSSIER.L_SI_CLE%TYPE) IS
  begin
      --On conserve les anciens liens dans un audit
      CARDEX_AUDIT.SP_E_AUDIT_LIAISON(NEW_L_LDD_CLE, NEW_L_SI_CLE, sysdate);
      delete from LDD_LIEN_DOSSIER
       where L_LDD_CLE = NEW_L_LDD_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_D_LDD_LIEN_DOSSIER',
                              True);
  END SP_D_LDD_LIEN_DOSSIER;
  
END CARDEX_LIEN;
/
