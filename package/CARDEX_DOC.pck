CREATE OR REPLACE PACKAGE CARDEX_DOC is
  procedure SP_E_SO_DETAILLANTS(NEW_L_SO_CLE                 IN OUT SO_SOCIETE.L_SO_CLE%TYPE,
                                NEW_L_SI_CLE                 IN OUT SO_SOCIETE.L_SI_CLE%TYPE,
                                NEW_I_ST_CLE                 IN SO_SOCIETE.I_ST_CLE%TYPE,
                                NEW_I_LS_CLE                 IN SO_SOCIETE.I_LS_CLE%TYPE,
                                NEW_V_SO_RAISON_SOCIALE      IN SO_SOCIETE.V_SO_RAISON_SOCIALE%TYPE,
                                NEW_V_SO_NOM                 IN SO_SOCIETE.V_SO_NOM%TYPE,
                                NEW_I_CL_CLE                 IN SO_SOCIETE.I_CL_CLE%TYPE,
                                NEW_D_SO_DATE_FONDATION      IN SO_SOCIETE.D_SO_DATE_FONDATION%TYPE,
                                NEW_I_CC_CLE                 IN SO_SOCIETE.I_CC_CLE%TYPE,
                                NEW_V_SO_MOT_PASSE           IN SO_SOCIETE.V_SO_MOT_PASSE%TYPE,
                                NEW_V_SO_REFERENCE_NOM       IN SO_SOCIETE.V_SO_REFERENCE_NOM%TYPE,
                                NEW_V_SO_REFERENCE_PRENOM    IN SO_SOCIETE.V_SO_REFERENCE_PRENOM%TYPE,
                                NEW_V_SO_REFERENCE_1         IN SO_SOCIETE.V_SO_REFERENCE_1%TYPE,
                                NEW_V_SO_REFERENCE_2         IN SO_SOCIETE.V_SO_REFERENCE_2%TYPE,
                                NEW_V_SO_REFERENCE_3         IN SO_SOCIETE.V_SO_REFERENCE_3%TYPE,
                                NEW_I_SE_CLE                 IN SE_SEVERITE.I_SE_CLE%TYPE,
                                NEW_L_SO_CENTRE_REGIONAL     IN SO_SOCIETE.L_SO_CENTRE_REGIONAL%TYPE,
                                NEW_V_SO_DISTRICT            IN SO_SOCIETE.V_SO_DISTRICT%TYPE,
                                NEW_V_SO_CODE_COMPTE         IN SO_SOCIETE.V_SO_CODE_COMPTE%TYPE,
                                NEW_B_SO_ACTIF               IN SO_SOCIETE.B_SO_ACTIF%TYPE,
                                NEW_D_SO_DATE_INACTIVATION   IN SO_SOCIETE.D_SO_DATE_INACTIVATION%TYPE,
                                NEW_B_SO_IND_RDD             IN SO_SOCIETE.B_SO_IND_RDD%TYPE,
                                NEW_V_SO_COMMENTAIRE         IN SO_SOCIETE.V_SO_COMMENTAIRE%TYPE,
                                NEW_V_SO_RAISON_INACTIVATION IN SO_SOCIETE.V_SO_RAISON_INACTIVATION%TYPE,
                                NEW_I_SE_CLE_CASINO          IN SO_SOCIETE.I_SE_CLE_CASINO%TYPE);
  procedure SP_E_SO_SOCIETE(action                       IN CHAR,
                            NEW_L_SO_CLE                 IN OUT SO_SOCIETE.L_SO_CLE%TYPE,
                            NEW_L_SI_CLE                 IN OUT SO_SOCIETE.L_SI_CLE%TYPE,
                            NEW_I_ST_CLE                 IN SO_SOCIETE.I_ST_CLE%TYPE,
                            NEW_I_LS_CLE                 IN SO_SOCIETE.I_LS_CLE%TYPE,
                            NEW_V_SO_RAISON_SOCIALE      IN SO_SOCIETE.V_SO_RAISON_SOCIALE%TYPE,
                            NEW_V_SO_NOM                 IN SO_SOCIETE.V_SO_NOM%TYPE,
                            NEW_I_CL_CLE                 IN SO_SOCIETE.I_CL_CLE%TYPE,
                            NEW_D_SO_DATE_FONDATION      IN SO_SOCIETE.D_SO_DATE_FONDATION%TYPE,
                            NEW_I_CC_CLE                 IN SO_SOCIETE.I_CC_CLE%TYPE,
                            NEW_V_SO_MOT_PASSE           IN SO_SOCIETE.V_SO_MOT_PASSE%TYPE,
                            NEW_V_SO_REFERENCE_NOM       IN SO_SOCIETE.V_SO_REFERENCE_NOM%TYPE,
                            NEW_V_SO_REFERENCE_PRENOM    IN SO_SOCIETE.V_SO_REFERENCE_PRENOM%TYPE,
                            NEW_V_SO_REFERENCE_1         IN SO_SOCIETE.V_SO_REFERENCE_1%TYPE,
                            NEW_V_SO_REFERENCE_2         IN SO_SOCIETE.V_SO_REFERENCE_2%TYPE,
                            NEW_V_SO_REFERENCE_3         IN SO_SOCIETE.V_SO_REFERENCE_3%TYPE,
                            NEW_I_SE_CLE                 IN SE_SEVERITE.I_SE_CLE%TYPE,
                            NEW_L_SO_CENTRE_REGIONAL     IN SO_SOCIETE.L_SO_CENTRE_REGIONAL%TYPE,
                            NEW_V_SO_DISTRICT            IN SO_SOCIETE.V_SO_DISTRICT%TYPE,
                            NEW_V_SO_CODE_COMPTE         IN SO_SOCIETE.V_SO_CODE_COMPTE%TYPE,
                            NEW_B_SO_ACTIF               IN SO_SOCIETE.B_SO_ACTIF%TYPE,
                            NEW_D_SO_DATE_INACTIVATION   IN SO_SOCIETE.D_SO_DATE_INACTIVATION%TYPE,
                            NEW_B_SO_IND_RDD             IN SO_SOCIETE.B_SO_IND_RDD%TYPE,
                            NEW_V_SO_COMMENTAIRE         IN SO_SOCIETE.V_SO_COMMENTAIRE%TYPE,
                            NEW_V_SO_RAISON_INACTIVATION IN SO_SOCIETE.V_SO_RAISON_INACTIVATION%TYPE,
                            NEW_I_SE_CLE_CASINO          IN SO_SOCIETE.I_SE_CLE_CASINO%TYPE,
                            NEW_I_SO_ECHANTILLONAGE      IN SO_SOCIETE.I_SO_ECHANTILLONAGE%TYPE);
  procedure SP_E_VE_VEHICULE(action                       IN CHAR,
                             NEW_L_VE_CLE                 IN OUT VE_VEHICULE.L_VE_CLE%TYPE,
                             NEW_L_SI_CLE                 IN OUT VE_VEHICULE.L_SI_CLE%TYPE,
                             NEW_I_MD_CLE                 IN VE_VEHICULE.I_MD_CLE%TYPE,
                             NEW_I_CC_CLE                 IN VE_VEHICULE.I_CC_CLE%TYPE,
                             NEW_V_VE_IMMATRICULATION     IN VE_VEHICULE.V_VE_IMMATRICULATION%TYPE,
                             NEW_V_VE_PROVINCE            IN VE_VEHICULE.V_VE_PROVINCE%TYPE,
                             NEW_C_VE_ANNEE               IN VE_VEHICULE.C_VE_ANNEE%TYPE,
                             NEW_V_VE_VIGNETTE            IN VE_VEHICULE.V_VE_VIGNETTE%TYPE,
                             NEW_V_VE_NUMERO_SERIE        IN VE_VEHICULE.V_VE_NUMERO_SERIE%TYPE,
                             NEW_V_VE_ASSUREUR            IN VE_VEHICULE.V_VE_ASSUREUR%TYPE,
                             NEW_V_VE_POLICE              IN VE_VEHICULE.V_VE_POLICE%TYPE,
                             NEW_D_VE_EXPIRATION_VIGNETTE IN VE_VEHICULE.D_VE_EXPIRATION_VIGNETTE%TYPE,
                             NEW_D_VE_EXPIRATION_POLICE   IN VE_VEHICULE.D_VE_EXPIRATION_POLICE%TYPE,
                             NEW_V_VE_MOT_PASSE           IN VE_VEHICULE.V_VE_MOT_PASSE%TYPE,
                             NEW_V_VE_COMMENTAIRE         IN VE_VEHICULE.V_VE_COMMENTAIRE%TYPE,
                             NEW_L_PR_CLE                 IN VE_VEHICULE.L_PR_CLE%TYPE);
  procedure SP_E_DO_DOSSIER(action                      IN CHAR,
                            NEW_L_DO_CLE                IN OUT DO_DOSSIER.L_DO_CLE%TYPE,
                            NEW_L_SI_CLE                IN OUT DO_DOSSIER.L_SI_CLE%TYPE,
                            NEW_V_DO_NUMERO_DOSSIER     IN OUT DO_DOSSIER.V_DO_NUMERO_DOSSIER%TYPE,
                            NEW_I_CA_CLE                IN DO_DOSSIER.I_CA_CLE%TYPE,
                            NEW_I_SE_CLE                IN DO_DOSSIER.I_SE_CLE%TYPE,
                            NEW_I_CC_CLE                IN DO_DOSSIER.I_CC_CLE%TYPE,
                            NEW_I_NH_CLE                IN DO_DOSSIER.I_NH_CLE%TYPE,
                            NEW_I_PE_CLE                IN DO_DOSSIER.I_PE_CLE%TYPE,
                            NEW_I_ST_CLE                IN DO_DOSSIER.I_ST_CLE%TYPE,
                            NEW_V_DO_MOT_PASSE          IN DO_DOSSIER.V_DO_MOT_PASSE%TYPE,
                            NEW_D_DO_DATE_DEBUT         IN DO_DOSSIER.D_DO_DATE_DEBUT%TYPE,
                            NEW_D_DO_DATE_FIN           IN DO_DOSSIER.D_DO_DATE_FIN%TYPE,
                            NEW_V_DO_DUREE              IN DO_DOSSIER.V_DO_DUREE%TYPE,
                            NEW_V_DO_ANCIENNE_REFERENCE IN DO_DOSSIER.V_DO_ANCIENNE_REFERENCE%TYPE,
                            NEW_V_DO_REFERENCE_VIDEO    IN DO_DOSSIER.V_DO_REFERENCE_VIDEO%TYPE,
                            NEW_D_DO_DATE_RAPPORTEE     IN DO_DOSSIER.D_DO_DATE_RAPPORTEE%TYPE,
                            NEW_I_DO_CLASSE             IN DO_DOSSIER.I_DO_CLASSE%TYPE,
                            NEW_I_DO_RACE               IN DO_DOSSIER.I_DO_RACE%TYPE,
                            NEW_V_DO_LIEU               IN DO_DOSSIER.V_DO_LIEU%TYPE,
                            NEW_I_OR_CLE                IN DO_DOSSIER.I_OR_CLE%TYPE,
                            NEW_I_CR_CLE                IN DO_DOSSIER.I_CR_CLE%TYPE,
                            NEW_V_DO_REFERENCE1         IN DO_DOSSIER.V_DO_REFERENCE1%TYPE,
                            NEW_V_DO_REFERENCE2         IN DO_DOSSIER.V_DO_REFERENCE2%TYPE,
                            NEW_V_DO_ASSIGNE_A          IN DO_DOSSIER.V_DO_ASSIGNE_A%TYPE,
                            NEW_D_DO_DATE_ASSIGNATION   IN DO_DOSSIER.D_DO_DATE_ASSIGNATION%TYPE,
                            NEW_D_DO_DATE_EVENEMENT     IN DO_DOSSIER.D_DO_DATE_EVENEMENT%TYPE,
                            NEW_V_DO_REFERENCE3         IN DO_DOSSIER.V_DO_REFERENCE3%TYPE,
                            NEW_V_DO_REFERENCE4         IN DO_DOSSIER.V_DO_REFERENCE4%TYPE,
                            NEW_V_DO_REFERENCE5         IN DO_DOSSIER.V_DO_REFERENCE5%TYPE,
                            NEW_C_DO_FONDE              IN DO_DOSSIER.C_DO_FONDE%TYPE,
                            NEW_V_DO_REFERENCE6         IN DO_DOSSIER.V_DO_REFERENCE6%TYPE,
                            NEW_V_DO_REFERENCE7         IN DO_DOSSIER.V_DO_REFERENCE7%TYPE,
                            NEW_I_DO_FONDE              IN DO_DOSSIER.I_DO_FONDE%TYPE,
                            NEW_I_RF_CLE                IN RF_REFERENCE_VIDEO.I_RF_CLE%TYPE,
                            NEW_B_DO_ENR_CONSERVE       DO_DOSSIER.B_DO_ENREGISTREMENT_CONSERVE%TYPE,
                            NEW_B_DO_ENR_NUMERIQUE      DO_DOSSIER.B_DO_ENREGISTREMENT_NUMERIQUE%TYPE,
                            NEW_L_DO_ORIGINE            DO_DOSSIER.L_DO_ORIGINE%TYPE);
  procedure SP_E_SU_SUJET(action                     IN CHAR,
                          NEW_L_SU_CLE               IN OUT SU_SUJET.L_SU_CLE%TYPE,
                          NEW_L_SI_CLE               IN OUT SU_SUJET.L_SI_CLE%TYPE,
                          NEW_I_SX_CLE               IN SU_SUJET.I_SX_CLE%TYPE,
                          NEW_I_ST_CLE               IN SU_SUJET.I_ST_CLE%TYPE,
                          NEW_I_NT_CLE               IN SU_SUJET.I_NT_CLE%TYPE,
                          NEW_I_RA_CLE               IN SU_SUJET.I_RA_CLE%TYPE,
                          NEW_I_LS_CLE               IN SU_SUJET.I_LS_CLE%TYPE,
                          NEW_V_SU_NOM               IN SU_SUJET.V_SU_NOM%TYPE,
                          NEW_V_SU_PRENOM            IN SU_SUJET.V_SU_PRENOM%TYPE,
                          NEW_V_SU_SURNOM            IN SU_SUJET.V_SU_SURNOM%TYPE,
                          NEW_D_SU_DATE_NAISSANCE    IN SU_SUJET.D_SU_DATE_NAISSANCE%TYPE,
                          NEW_L_SU_TYPE_AGE          IN SU_SUJET.L_SU_TYPE_AGE%TYPE,
                          NEW_C_SU_ASSURANCE_SOCIALE IN SU_SUJET.C_SU_ASSURANCE_SOCIALE%TYPE,
                          NEW_V_SU_PERMIS_CONDUIRE   IN SU_SUJET.V_SU_PERMIS_CONDUIRE%TYPE,
                          NEW_V_SU_GRANDEUR_METRIQUE IN SU_SUJET.V_SU_GRANDEUR_METRIQUE%TYPE,
                          NEW_V_SU_GRANDEUR_IMPERIAL IN SU_SUJET.V_SU_GRANDEUR_IMPERIAL%TYPE,
                          NEW_V_SU_POIDS_METRIQUE    IN SU_SUJET.V_SU_POIDS_METRIQUE%TYPE,
                          NEW_V_SU_POIDS_IMPERIAL    IN SU_SUJET.V_SU_POIDS_IMPERIAL%TYPE,
                          NEW_C_SU_SYSTEME           IN SU_SUJET.C_SU_SYSTEME%TYPE,
                          NEW_I_CC_CLE               IN SU_SUJET.I_CC_CLE%TYPE,
                          NEW_V_SU_MOT_PASSE         IN SU_SUJET.V_SU_MOT_PASSE%TYPE,
                          NEW_V_SU_REFERENCE_1       IN SU_SUJET.V_SU_REFERENCE_1%TYPE,
                          NEW_V_SU_REFERENCE_2       IN SU_SUJET.V_SU_REFERENCE_2%TYPE,
                          NEW_V_SU_REFERENCE_3       IN OUT SU_SUJET.V_SU_REFERENCE_3%TYPE,
                          NEW_I_SE_CLE               IN SE_SEVERITE.I_SE_CLE%TYPE,
                          NEW_V_SU_NO_PASSEPORT      IN SU_SUJET.V_SU_NO_PASSEPORT%TYPE,
                          NEW_V_SU_ASSURANCE_MALADIE IN SU_SUJET.V_SU_ASSURANCE_MALADIE%TYPE,
                          NEW_I_SE_CLE_AUTRES        IN SE_SEVERITE.I_SE_CLE%TYPE,
                          NEW_B_SU_IND_RDD           IN SU_SUJET.B_SU_IND_RDD%TYPE,
                          NEW_I_SE_CLE_CASINO        IN SU_SUJET.I_SE_CLE_CASINO%TYPE,
                          NEW_D_SU_DATE_FIN_EMPLOI   IN SU_SUJET.D_SU_DATE_FIN_EMPLOI%TYPE);
  procedure SP_E_SU_SUJET_DETAILLANTS(NEW_L_SU_CLE            IN OUT SU_SUJET.L_SU_CLE%TYPE,
                                      NEW_L_SI_CLE            IN OUT SU_SUJET.L_SI_CLE%TYPE,
                                      NEW_V_SU_REFERENCE_3    IN OUT SU_SUJET.V_SU_REFERENCE_3%TYPE,
                                      NEW_V_SU_NOM            IN SU_SUJET.V_SU_NOM%TYPE,
                                      NEW_V_SU_PRENOM         IN SU_SUJET.V_SU_PRENOM%TYPE,
                                      NEW_L_SU_TYPE_AGE       IN SU_SUJET.L_SU_TYPE_AGE%TYPE,
                                      NEW_I_CC_CLE            IN SU_SUJET.I_CC_CLE%TYPE,
                                      NEW_I_SE_CLE            IN SE_SEVERITE.I_SE_CLE%TYPE,
                                      NEW_I_SE_CLE_AUTRES     IN SE_SEVERITE.I_SE_CLE%TYPE,
                                      NEW_B_SU_IND_RDD        IN SU_SUJET.B_SU_IND_RDD%TYPE);

  procedure SP_E_MM_MULTIMEDIA(action               IN CHAR,
                               NEW_L_MM_CLE         IN OUT MM_MULTIMEDIA.L_MM_CLE%TYPE,
                               NEW_L_SI_CLE         IN OUT MM_MULTIMEDIA.L_SI_CLE%TYPE,
                               NEW_L_EM_CLE         IN MM_MULTIMEDIA.L_EM_CLE%TYPE,
                               NEW_I_TM_CLE         IN MM_MULTIMEDIA.I_TM_CLE%TYPE,
                               NEW_V_MM_DESCRIPTION IN MM_MULTIMEDIA.V_MM_DESCRIPTION%TYPE,
                               NEW_L_EM_SI_CLE      IN MM_MULTIMEDIA.L_EM_SI_CLE%TYPE,
                               NEW_V_MM_EXTENSION   IN MM_MULTIMEDIA.V_MM_EXTENSION%TYPE);

  procedure SP_E_SI_SITE_SEQUENCE(NEW_L_SI_CLE      IN SI_SITE.L_SI_CLE%TYPE,
                                  NEW_D_SI_SEQUENCE IN SI_SITE.D_SI_SEQUENCE%TYPE,
                                  NEW_N_SI_SEQUENCE IN SI_SITE.N_SI_SEQUENCE%TYPE);

  procedure SP_E_SU_SUJET_ASSIGNER_CC_C(NEW_L_SU_CLE IN SU_SUJET.L_SU_CLE%TYPE,
                                        NEW_L_SI_CLE IN SU_SUJET.L_SI_CLE%TYPE);

  procedure SP_E_DO_DOSSIER_ASSIGNER_CC_C(NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                          NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE);

  procedure SP_I_MOUVEMENTS(NOM                MOUVEMENTS.NOM%TYPE,
                            PRENOM             MOUVEMENTS.PRENOM%TYPE,
                            NAS                MOUVEMENTS.NAS%TYPE,
                            TYPE               MOUVEMENTS.TYPE%TYPE,
                            DATE_EMBAUCHE      MOUVEMENTS.DATE_EMBAUCHE%TYPE,
                            DATE_DEPART        MOUVEMENTS.DATE_DEPART%TYPE,
                            SITE               MOUVEMENTS.SITE%TYPE,
                            NO_EMPLOYE         MOUVEMENTS.NO_EMPLOYE%TYPE,
                            DATE_NAISSANCE     MOUVEMENTS.DATE_NAISSANCE%TYPE,
                            CLE                MOUVEMENTS.CLE%TYPE,
                            DESCRIPTION_CONGE  MOUVEMENTS.DESCRIPTION_CONGE%TYPE,
                            DATE_DEBUT_CONGE   MOUVEMENTS.DATE_DEBUT_CONGE%TYPE,
                            STATUT             MOUVEMENTS.STATUT%TYPE,
                            DATE_FIN_CONGE     MOUVEMENTS.DATE_FIN_CONGE%TYPE,
                            DESCRIPTION_EMPLOI MOUVEMENTS.DESCRIPTION_EMPLOI%TYPE);

  procedure SP_E_SU_SEVERITE_3;
  procedure SP_E_SO_SEVERITE_3;
  procedure SP_E_DO_SEVERITE_3;
  procedure SP_E_SU_SEVERITE_2;
  procedure SP_E_SO_SEVERITE_2;
  procedure SP_E_DO_SEVERITE_2;
  procedure SP_E_SU_SEVERITE_4;
  procedure SP_E_SO_SEVERITE_4;
  procedure SP_E_DO_SEVERITE_4;
  procedure SP_U_DETAILLANTS;
  procedure SP_U_PERSONNE_MORALE;
  procedure SP_E_SU_SEVERITE_CASINO(NEW_L_SU_CLE IN SU_SUJET.L_SU_CLE%TYPE,
                                    NEW_L_SI_CLE IN SU_SUJET.L_SI_CLE%TYPE,
                                    NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE);
  procedure SP_E_SU_SEVERITE_AUTRE(NEW_L_SU_CLE IN SU_SUJET.L_SU_CLE%TYPE,
                                   NEW_L_SI_CLE IN SU_SUJET.L_SI_CLE%TYPE,
                                   NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE);
  procedure SP_E_SO_SEVERITE_CASINO(NEW_L_SO_CLE IN SO_SOCIETE.L_SO_CLE%TYPE,
                                    NEW_L_SI_CLE IN SO_SOCIETE.L_SI_CLE%TYPE,
                                    NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE);
  procedure SP_E_SO_SEVERITE_AUTRE(NEW_L_SO_CLE IN SO_SOCIETE.L_SO_CLE%TYPE,
                                   NEW_L_SI_CLE IN SO_SOCIETE.L_SI_CLE%TYPE,
                                   NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE);
END CARDEX_DOC;
/
CREATE OR REPLACE PACKAGE BODY CARDEX_DOC is
  procedure SP_E_SO_DETAILLANTS(NEW_L_SO_CLE                 IN OUT SO_SOCIETE.L_SO_CLE%TYPE,
                                NEW_L_SI_CLE                 IN OUT SO_SOCIETE.L_SI_CLE%TYPE,
                                NEW_I_ST_CLE                 IN SO_SOCIETE.I_ST_CLE%TYPE,
                                NEW_I_LS_CLE                 IN SO_SOCIETE.I_LS_CLE%TYPE,
                                NEW_V_SO_RAISON_SOCIALE      IN SO_SOCIETE.V_SO_RAISON_SOCIALE%TYPE,
                                NEW_V_SO_NOM                 IN SO_SOCIETE.V_SO_NOM%TYPE,
                                NEW_I_CL_CLE                 IN SO_SOCIETE.I_CL_CLE%TYPE,
                                NEW_D_SO_DATE_FONDATION      IN SO_SOCIETE.D_SO_DATE_FONDATION%TYPE,
                                NEW_I_CC_CLE                 IN SO_SOCIETE.I_CC_CLE%TYPE,
                                NEW_V_SO_MOT_PASSE           IN SO_SOCIETE.V_SO_MOT_PASSE%TYPE,
                                NEW_V_SO_REFERENCE_NOM       IN SO_SOCIETE.V_SO_REFERENCE_NOM%TYPE,
                                NEW_V_SO_REFERENCE_PRENOM    IN SO_SOCIETE.V_SO_REFERENCE_PRENOM%TYPE,
                                NEW_V_SO_REFERENCE_1         IN SO_SOCIETE.V_SO_REFERENCE_1%TYPE,
                                NEW_V_SO_REFERENCE_2         IN SO_SOCIETE.V_SO_REFERENCE_2%TYPE,
                                NEW_V_SO_REFERENCE_3         IN SO_SOCIETE.V_SO_REFERENCE_3%TYPE,
                                NEW_I_SE_CLE                 IN SE_SEVERITE.I_SE_CLE%TYPE,
                                NEW_L_SO_CENTRE_REGIONAL     IN SO_SOCIETE.L_SO_CENTRE_REGIONAL%TYPE,
                                NEW_V_SO_DISTRICT            IN SO_SOCIETE.V_SO_DISTRICT%TYPE,
                                NEW_V_SO_CODE_COMPTE         IN SO_SOCIETE.V_SO_CODE_COMPTE%TYPE,
                                NEW_B_SO_ACTIF               IN SO_SOCIETE.B_SO_ACTIF%TYPE,
                                NEW_D_SO_DATE_INACTIVATION   IN SO_SOCIETE.D_SO_DATE_INACTIVATION%TYPE,
                                NEW_B_SO_IND_RDD             IN SO_SOCIETE.B_SO_IND_RDD%TYPE,
                                NEW_V_SO_COMMENTAIRE         IN SO_SOCIETE.V_SO_COMMENTAIRE%TYPE,
                                NEW_V_SO_RAISON_INACTIVATION IN SO_SOCIETE.V_SO_RAISON_INACTIVATION%TYPE,
                                NEW_I_SE_CLE_CASINO          IN SO_SOCIETE.I_SE_CLE_CASINO%TYPE) IS
  begin
    select seq_dossier.nextval into NEW_L_SO_CLE from dual;
    insert into SO_SOCIETE
      (L_SO_CLE,
       L_SI_CLE,
       I_ST_CLE,
       I_LS_CLE,
       V_SO_RAISON_SOCIALE,
       V_SO_NOM,
       I_CL_CLE,
       D_SO_DATE_FONDATION,
       I_CC_CLE,
       V_SO_MOT_PASSE,
       V_SO_REFERENCE_NOM,
       V_SO_REFERENCE_PRENOM,
       V_SO_REFERENCE_1,
       V_SO_REFERENCE_2,
       V_SO_REFERENCE_3,
       V_SO_CREE_PAR,
       D_SO_DATE_CREATION,
       C_SO_SNDX_RAISON_SOCIALE,
       C_SO_SNDX_NOM,
       I_SE_CLE,
       L_SO_CENTRE_REGIONAL,
       V_SO_DISTRICT,
       V_SO_CODE_COMPTE,
       B_SO_ACTIF,
       D_SO_DATE_INACTIVATION,
       B_SO_IND_RDD,
       V_SO_COMMENTAIRE,
       V_SO_RAISON_INACTIVATION,
       I_SE_CLE_CASINO)
    values
      (NEW_L_SO_CLE,
       NEW_L_SI_CLE,
       NEW_I_ST_CLE,
       NEW_I_LS_CLE,
       NEW_V_SO_RAISON_SOCIALE,
       substr(NEW_V_SO_NOM, 1, 49),
       NEW_I_CL_CLE,
       NEW_D_SO_DATE_FONDATION,
       NEW_I_CC_CLE,
       NEW_V_SO_MOT_PASSE,
       NEW_V_SO_REFERENCE_NOM,
       NEW_V_SO_REFERENCE_PRENOM,
       NEW_V_SO_REFERENCE_1,
       NEW_V_SO_REFERENCE_2,
       NEW_V_SO_REFERENCE_3,
       CARDEX_SPECIAL.g_usager,
       sysdate,
       soundex(NEW_V_SO_RAISON_SOCIALE),
       soundex(NEW_V_SO_NOM),
       NEW_I_SE_CLE,
       NEW_L_SO_CENTRE_REGIONAL,
       NEW_V_SO_DISTRICT,
       NEW_V_SO_CODE_COMPTE,
       NEW_B_SO_ACTIF,
       NEW_D_SO_DATE_INACTIVATION,
       NEW_B_SO_IND_RDD,
       NEW_V_SO_COMMENTAIRE,
       NEW_V_SO_RAISON_INACTIVATION,
       NEW_I_SE_CLE_CASINO);
    commit;
  END SP_E_SO_DETAILLANTS;
  procedure SP_E_SO_SOCIETE(action                       IN CHAR,
                            NEW_L_SO_CLE                 IN OUT SO_SOCIETE.L_SO_CLE%TYPE,
                            NEW_L_SI_CLE                 IN OUT SO_SOCIETE.L_SI_CLE%TYPE,
                            NEW_I_ST_CLE                 IN SO_SOCIETE.I_ST_CLE%TYPE,
                            NEW_I_LS_CLE                 IN SO_SOCIETE.I_LS_CLE%TYPE,
                            NEW_V_SO_RAISON_SOCIALE      IN SO_SOCIETE.V_SO_RAISON_SOCIALE%TYPE,
                            NEW_V_SO_NOM                 IN SO_SOCIETE.V_SO_NOM%TYPE,
                            NEW_I_CL_CLE                 IN SO_SOCIETE.I_CL_CLE%TYPE,
                            NEW_D_SO_DATE_FONDATION      IN SO_SOCIETE.D_SO_DATE_FONDATION%TYPE,
                            NEW_I_CC_CLE                 IN SO_SOCIETE.I_CC_CLE%TYPE,
                            NEW_V_SO_MOT_PASSE           IN SO_SOCIETE.V_SO_MOT_PASSE%TYPE,
                            NEW_V_SO_REFERENCE_NOM       IN SO_SOCIETE.V_SO_REFERENCE_NOM%TYPE,
                            NEW_V_SO_REFERENCE_PRENOM    IN SO_SOCIETE.V_SO_REFERENCE_PRENOM%TYPE,
                            NEW_V_SO_REFERENCE_1         IN SO_SOCIETE.V_SO_REFERENCE_1%TYPE,
                            NEW_V_SO_REFERENCE_2         IN SO_SOCIETE.V_SO_REFERENCE_2%TYPE,
                            NEW_V_SO_REFERENCE_3         IN SO_SOCIETE.V_SO_REFERENCE_3%TYPE,
                            NEW_I_SE_CLE                 IN SE_SEVERITE.I_SE_CLE%TYPE,
                            NEW_L_SO_CENTRE_REGIONAL     IN SO_SOCIETE.L_SO_CENTRE_REGIONAL%TYPE,
                            NEW_V_SO_DISTRICT            IN SO_SOCIETE.V_SO_DISTRICT%TYPE,
                            NEW_V_SO_CODE_COMPTE         IN SO_SOCIETE.V_SO_CODE_COMPTE%TYPE,
                            NEW_B_SO_ACTIF               IN SO_SOCIETE.B_SO_ACTIF%TYPE,
                            NEW_D_SO_DATE_INACTIVATION   IN SO_SOCIETE.D_SO_DATE_INACTIVATION%TYPE,
                            NEW_B_SO_IND_RDD             IN SO_SOCIETE.B_SO_IND_RDD%TYPE,
                            NEW_V_SO_COMMENTAIRE         IN SO_SOCIETE.V_SO_COMMENTAIRE%TYPE,
                            NEW_V_SO_RAISON_INACTIVATION IN SO_SOCIETE.V_SO_RAISON_INACTIVATION%TYPE,
                            NEW_I_SE_CLE_CASINO          IN SO_SOCIETE.I_SE_CLE_CASINO%TYPE,
                            NEW_I_SO_ECHANTILLONAGE      IN SO_SOCIETE.I_SO_ECHANTILLONAGE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_SO_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into SO_SOCIETE
        (L_SO_CLE,
         L_SI_CLE,
         I_ST_CLE,
         I_LS_CLE,
         V_SO_RAISON_SOCIALE,
         V_SO_NOM,
         I_CL_CLE,
         D_SO_DATE_FONDATION,
         I_CC_CLE,
         V_SO_MOT_PASSE,
         V_SO_REFERENCE_NOM,
         V_SO_REFERENCE_PRENOM,
         V_SO_REFERENCE_1,
         V_SO_REFERENCE_2,
         V_SO_REFERENCE_3,
         V_SO_CREE_PAR,
         D_SO_DATE_CREATION,
         C_SO_SNDX_RAISON_SOCIALE,
         C_SO_SNDX_NOM,
         I_SE_CLE,
         L_SO_CENTRE_REGIONAL,
         V_SO_DISTRICT,
         V_SO_CODE_COMPTE,
         B_SO_ACTIF,
         D_SO_DATE_INACTIVATION,
         B_SO_IND_RDD,
         V_SO_COMMENTAIRE,
         V_SO_RAISON_INACTIVATION,
         I_SE_CLE_CASINO,
         I_SO_ECHANTILLONAGE)
      values
        (NEW_L_SO_CLE,
         NEW_L_SI_CLE,
         NEW_I_ST_CLE,
         NEW_I_LS_CLE,
         NEW_V_SO_RAISON_SOCIALE,
         NEW_V_SO_NOM,
         NEW_I_CL_CLE,
         NEW_D_SO_DATE_FONDATION,
         NEW_I_CC_CLE,
         NEW_V_SO_MOT_PASSE,
         NEW_V_SO_REFERENCE_NOM,
         NEW_V_SO_REFERENCE_PRENOM,
         NEW_V_SO_REFERENCE_1,
         NEW_V_SO_REFERENCE_2,
         NEW_V_SO_REFERENCE_3,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         soundex(NEW_V_SO_RAISON_SOCIALE),
         soundex(NEW_V_SO_NOM),
         NEW_I_SE_CLE,
         NEW_L_SO_CENTRE_REGIONAL,
         NEW_V_SO_DISTRICT,
         NEW_V_SO_CODE_COMPTE,
         NEW_B_SO_ACTIF,
         NEW_D_SO_DATE_INACTIVATION,
         NEW_B_SO_IND_RDD,
         NEW_V_SO_COMMENTAIRE,
         NEW_V_SO_RAISON_INACTIVATION,
         NEW_I_SE_CLE_CASINO,
         NEW_I_SO_ECHANTILLONAGE);
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_SOCIETE(NEW_L_SO_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update SO_SOCIETE
         set I_ST_CLE                 = NEW_I_ST_CLE,
             I_LS_CLE                 = NEW_I_LS_CLE,
             V_SO_RAISON_SOCIALE      = NEW_V_SO_RAISON_SOCIALE,
             V_SO_NOM                 = NEW_V_SO_NOM,
             I_CL_CLE                 = NEW_I_CL_CLE,
             D_SO_DATE_FONDATION      = NEW_D_SO_DATE_FONDATION,
             I_CC_CLE                 = NEW_I_CC_CLE,
             V_SO_MOT_PASSE           = NEW_V_SO_MOT_PASSE,
             V_SO_REFERENCE_NOM       = NEW_V_SO_REFERENCE_NOM,
             V_SO_REFERENCE_PRENOM    = NEW_V_SO_REFERENCE_PRENOM,
             V_SO_REFERENCE_1         = NEW_V_SO_REFERENCE_1,
             V_SO_REFERENCE_2         = NEW_V_SO_REFERENCE_2,
             V_SO_REFERENCE_3         = NEW_V_SO_REFERENCE_3,
             C_SO_SNDX_RAISON_SOCIALE = soundex(NEW_V_SO_RAISON_SOCIALE),
             C_SO_SNDX_NOM            = soundex(NEW_V_SO_NOM),
             I_SE_CLE                 = NEW_I_SE_CLE,
             L_SO_CENTRE_REGIONAL     = NEW_L_SO_CENTRE_REGIONAL,
             V_SO_DISTRICT            = NEW_V_SO_DISTRICT,
             V_SO_CODE_COMPTE         = NEW_V_SO_CODE_COMPTE,
             B_SO_ACTIF               = NEW_B_SO_ACTIF,
             D_SO_DATE_INACTIVATION   = NEW_D_SO_DATE_INACTIVATION,
             B_SO_IND_RDD             = NEW_B_SO_IND_RDD,
             V_SO_COMMENTAIRE         = NEW_V_SO_COMMENTAIRE,
             V_SO_RAISON_INACTIVATION = NEW_V_SO_RAISON_INACTIVATION,
             I_SE_CLE_CASINO          = NEW_I_SE_CLE_CASINO,
             I_SO_ECHANTILLONAGE      = NEW_I_SO_ECHANTILLONAGE
       where L_SO_CLE = NEW_L_SO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Mise à jour de la confidentialité de la photo en cas de changement
      cardex_lien.SP_E_LMM_LIEN_MULTIMEDIA('U',
                                           'SO',
                                           new_l_so_cle,
                                           new_l_si_cle,
                                           new_i_cc_cle);
    elsif action = 'D' then
      delete from SO_SOCIETE
       where L_SO_CLE = NEW_L_SO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'SO',
                                 NEW_L_SO_CLE,
                                 NEW_L_SI_CLE,
                                 'SO',
                                 NEW_L_SO_CLE,
                                 NEW_L_SI_CLE,
                                 'SO',
                                 NEW_L_SO_CLE,
                                 NEW_L_SI_CLE);
  END SP_E_SO_SOCIETE;
  procedure SP_E_VE_VEHICULE(action                       IN CHAR,
                             NEW_L_VE_CLE                 IN OUT VE_VEHICULE.L_VE_CLE%TYPE,
                             NEW_L_SI_CLE                 IN OUT VE_VEHICULE.L_SI_CLE%TYPE,
                             NEW_I_MD_CLE                 IN VE_VEHICULE.I_MD_CLE%TYPE,
                             NEW_I_CC_CLE                 IN VE_VEHICULE.I_CC_CLE%TYPE,
                             NEW_V_VE_IMMATRICULATION     IN VE_VEHICULE.V_VE_IMMATRICULATION%TYPE,
                             NEW_V_VE_PROVINCE            IN VE_VEHICULE.V_VE_PROVINCE%TYPE,
                             NEW_C_VE_ANNEE               IN VE_VEHICULE.C_VE_ANNEE%TYPE,
                             NEW_V_VE_VIGNETTE            IN VE_VEHICULE.V_VE_VIGNETTE%TYPE,
                             NEW_V_VE_NUMERO_SERIE        IN VE_VEHICULE.V_VE_NUMERO_SERIE%TYPE,
                             NEW_V_VE_ASSUREUR            IN VE_VEHICULE.V_VE_ASSUREUR%TYPE,
                             NEW_V_VE_POLICE              IN VE_VEHICULE.V_VE_POLICE%TYPE,
                             NEW_D_VE_EXPIRATION_VIGNETTE IN VE_VEHICULE.D_VE_EXPIRATION_VIGNETTE%TYPE,
                             NEW_D_VE_EXPIRATION_POLICE   IN VE_VEHICULE.D_VE_EXPIRATION_POLICE%TYPE,
                             NEW_V_VE_MOT_PASSE           IN VE_VEHICULE.V_VE_MOT_PASSE%TYPE,
                             NEW_V_VE_COMMENTAIRE         IN VE_VEHICULE.V_VE_COMMENTAIRE%TYPE,
                             NEW_L_PR_CLE                 IN VE_VEHICULE.L_PR_CLE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_VE_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into VE_VEHICULE
        (L_VE_CLE,
         L_SI_CLE,
         I_MD_CLE,
         I_CC_CLE,
         V_VE_IMMATRICULATION,
         V_VE_PROVINCE,
         C_VE_ANNEE,
         V_VE_VIGNETTE,
         V_VE_NUMERO_SERIE,
         V_VE_ASSUREUR,
         V_VE_POLICE,
         D_VE_EXPIRATION_VIGNETTE,
         D_VE_EXPIRATION_POLICE,
         V_VE_CREE_PAR,
         D_VE_DATE_CREATION,
         V_VE_MOT_PASSE,
         V_VE_COMMENTAIRE,
         L_PR_CLE)
      values
        (NEW_L_VE_CLE,
         NEW_L_SI_CLE,
         NEW_I_MD_CLE,
         NEW_I_CC_CLE,
         NEW_V_VE_IMMATRICULATION,
         NEW_V_VE_PROVINCE,
         NEW_C_VE_ANNEE,
         NEW_V_VE_VIGNETTE,
         NEW_V_VE_NUMERO_SERIE,
         NEW_V_VE_ASSUREUR,
         NEW_V_VE_POLICE,
         NEW_D_VE_EXPIRATION_VIGNETTE,
         NEW_D_VE_EXPIRATION_POLICE,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_V_VE_MOT_PASSE,
         NEW_V_VE_COMMENTAIRE,
         NEW_L_PR_CLE);
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_VEHICULE(NEW_L_VE_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update VE_VEHICULE
         set I_MD_CLE                 = NEW_I_MD_CLE,
             I_CC_CLE                 = NEW_I_CC_CLE,
             V_VE_IMMATRICULATION     = NEW_V_VE_IMMATRICULATION,
             V_VE_PROVINCE            = NEW_V_VE_PROVINCE,
             C_VE_ANNEE               = NEW_C_VE_ANNEE,
             V_VE_VIGNETTE            = NEW_V_VE_VIGNETTE,
             V_VE_NUMERO_SERIE        = NEW_V_VE_NUMERO_SERIE,
             V_VE_ASSUREUR            = NEW_V_VE_ASSUREUR,
             V_VE_POLICE              = NEW_V_VE_POLICE,
             D_VE_EXPIRATION_VIGNETTE = NEW_D_VE_EXPIRATION_VIGNETTE,
             D_VE_EXPIRATION_POLICE   = NEW_D_VE_EXPIRATION_POLICE,
             V_VE_MOT_PASSE           = NEW_V_VE_MOT_PASSE,
             V_VE_COMMENTAIRE         = NEW_V_VE_COMMENTAIRE,
             L_PR_CLE                 = NEW_L_PR_CLE
       where L_VE_CLE = NEW_L_VE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Mise à jour de la confidentialité de la photo en cas de changement
      cardex_lien.SP_E_LMM_LIEN_MULTIMEDIA('U',
                                           'VE',
                                           new_l_ve_cle,
                                           new_l_si_cle,
                                           new_i_cc_cle);
    elsif action = 'D' then
      delete from VE_VEHICULE
       where L_VE_CLE = NEW_L_VE_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'VE',
                                 NEW_L_VE_CLE,
                                 NEW_L_SI_CLE,
                                 'VE',
                                 NEW_L_VE_CLE,
                                 NEW_L_SI_CLE,
                                 'VE',
                                 NEW_L_VE_CLE,
                                 NEW_L_SI_CLE);
  END SP_E_VE_VEHICULE;
  procedure SP_E_DO_DOSSIER(action                      IN CHAR,
                            NEW_L_DO_CLE                IN OUT DO_DOSSIER.L_DO_CLE%TYPE,
                            NEW_L_SI_CLE                IN OUT DO_DOSSIER.L_SI_CLE%TYPE,
                            NEW_V_DO_NUMERO_DOSSIER     IN OUT DO_DOSSIER.V_DO_NUMERO_DOSSIER%TYPE,
                            NEW_I_CA_CLE                IN DO_DOSSIER.I_CA_CLE%TYPE,
                            NEW_I_SE_CLE                IN DO_DOSSIER.I_SE_CLE%TYPE,
                            NEW_I_CC_CLE                IN DO_DOSSIER.I_CC_CLE%TYPE,
                            NEW_I_NH_CLE                IN DO_DOSSIER.I_NH_CLE%TYPE,
                            NEW_I_PE_CLE                IN DO_DOSSIER.I_PE_CLE%TYPE,
                            NEW_I_ST_CLE                IN DO_DOSSIER.I_ST_CLE%TYPE,
                            NEW_V_DO_MOT_PASSE          IN DO_DOSSIER.V_DO_MOT_PASSE%TYPE,
                            NEW_D_DO_DATE_DEBUT         IN DO_DOSSIER.D_DO_DATE_DEBUT%TYPE,
                            NEW_D_DO_DATE_FIN           IN DO_DOSSIER.D_DO_DATE_FIN%TYPE,
                            NEW_V_DO_DUREE              IN DO_DOSSIER.V_DO_DUREE%TYPE,
                            NEW_V_DO_ANCIENNE_REFERENCE IN DO_DOSSIER.V_DO_ANCIENNE_REFERENCE%TYPE,
                            NEW_V_DO_REFERENCE_VIDEO    IN DO_DOSSIER.V_DO_REFERENCE_VIDEO%TYPE,
                            NEW_D_DO_DATE_RAPPORTEE     IN DO_DOSSIER.D_DO_DATE_RAPPORTEE%TYPE,
                            NEW_I_DO_CLASSE             IN DO_DOSSIER.I_DO_CLASSE%TYPE,
                            NEW_I_DO_RACE               IN DO_DOSSIER.I_DO_RACE%TYPE,
                            NEW_V_DO_LIEU               IN DO_DOSSIER.V_DO_LIEU%TYPE,
                            NEW_I_OR_CLE                IN DO_DOSSIER.I_OR_CLE%TYPE,
                            NEW_I_CR_CLE                IN DO_DOSSIER.I_CR_CLE%TYPE,
                            NEW_V_DO_REFERENCE1         IN DO_DOSSIER.V_DO_REFERENCE1%TYPE,
                            NEW_V_DO_REFERENCE2         IN DO_DOSSIER.V_DO_REFERENCE2%TYPE,
                            NEW_V_DO_ASSIGNE_A          IN DO_DOSSIER.V_DO_ASSIGNE_A%TYPE,
                            NEW_D_DO_DATE_ASSIGNATION   IN DO_DOSSIER.D_DO_DATE_ASSIGNATION%TYPE,
                            NEW_D_DO_DATE_EVENEMENT     IN DO_DOSSIER.D_DO_DATE_EVENEMENT%TYPE,
                            NEW_V_DO_REFERENCE3         IN DO_DOSSIER.V_DO_REFERENCE3%TYPE,
                            NEW_V_DO_REFERENCE4         IN DO_DOSSIER.V_DO_REFERENCE4%TYPE,
                            NEW_V_DO_REFERENCE5         IN DO_DOSSIER.V_DO_REFERENCE5%TYPE,
                            NEW_C_DO_FONDE              IN DO_DOSSIER.C_DO_FONDE%TYPE,
                            NEW_V_DO_REFERENCE6         IN DO_DOSSIER.V_DO_REFERENCE6%TYPE,
                            NEW_V_DO_REFERENCE7         IN DO_DOSSIER.V_DO_REFERENCE7%TYPE,
                            NEW_I_DO_FONDE              IN DO_DOSSIER.I_DO_FONDE%TYPE,
                            NEW_I_RF_CLE                IN RF_REFERENCE_VIDEO.I_RF_CLE%TYPE,
                            NEW_B_DO_ENR_CONSERVE       IN DO_DOSSIER.B_DO_ENREGISTREMENT_CONSERVE%TYPE,
                            NEW_B_DO_ENR_NUMERIQUE      IN DO_DOSSIER.B_DO_ENREGISTREMENT_NUMERIQUE%TYPE,
                            NEW_L_DO_ORIGINE            IN DO_DOSSIER.L_DO_ORIGINE%TYPE) IS
    FUNCTION F_TRAITE_INSCRIPTION(NEW_I_CA_CLE IN CA_CATEGORIE.I_CA_CLE%TYPE)
      RETURN NUMBER IS
      OK NUMBER(1, 0) := 0;
    BEGIN
      SELECT NA.B_NA_INSCRIPTION
        INTO OK
        FROM NA_NATURE NA, CA_CATEGORIE CA, TY_TYPE TY
       WHERE (TY.I_TY_CLE = CA.I_TY_CLE)
         and (TY.I_NA_CLE = NA.I_NA_CLE)
         AND (CA.I_CA_CLE = NEW_I_CA_CLE);
      RETURN OK;
    END;
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_DO_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into DO_DOSSIER
        (L_DO_CLE,
         L_SI_CLE,
         V_DO_NUMERO_DOSSIER,
         I_CA_CLE,
         I_SE_CLE,
         I_CC_CLE,
         I_NH_CLE,
         I_PE_CLE,
         I_ST_CLE,
         V_DO_MOT_PASSE,
         D_DO_DATE_DEBUT,
         D_DO_DATE_FIN,
         V_DO_DUREE,
         V_DO_ANCIENNE_REFERENCE,
         V_DO_REFERENCE_VIDEO,
         D_DO_DATE_RAPPORTEE,
         I_DO_CLASSE,
         I_DO_RACE,
         V_DO_LIEU,
         I_OR_CLE,
         I_CR_CLE,
         V_DO_REFERENCE1,
         V_DO_REFERENCE2,
         V_DO_ASSIGNE_A,
         D_DO_DATE_ASSIGNATION,
         D_DO_DATE_EVENEMENT,
         V_DO_CREE_PAR,
         D_DO_DATE_CREATION,
         V_DO_REFERENCE3,
         V_DO_REFERENCE4,
         V_DO_REFERENCE5,
         C_DO_FONDE,
         V_DO_REFERENCE6,
         V_DO_REFERENCE7,
         I_DO_FONDE,
         I_RF_CLE,
         B_DO_ENREGISTREMENT_CONSERVE,
         B_DO_ENREGISTREMENT_NUMERIQUE,
         L_DO_ORIGINE)
      values
        (NEW_L_DO_CLE,
         NEW_L_SI_CLE,
         NEW_V_DO_NUMERO_DOSSIER,
         NEW_I_CA_CLE,
         NEW_I_SE_CLE,
         NEW_I_CC_CLE,
         NEW_I_NH_CLE,
         NEW_I_PE_CLE,
         NEW_I_ST_CLE,
         NEW_V_DO_MOT_PASSE,
         NEW_D_DO_DATE_DEBUT,
         NEW_D_DO_DATE_FIN,
         NEW_V_DO_DUREE,
         NEW_V_DO_ANCIENNE_REFERENCE,
         NEW_V_DO_REFERENCE_VIDEO,
         NEW_D_DO_DATE_RAPPORTEE,
         NEW_I_DO_CLASSE,
         NEW_I_DO_RACE,
         NEW_V_DO_LIEU,
         NEW_I_OR_CLE,
         NEW_I_CR_CLE,
         NEW_V_DO_REFERENCE1,
         NEW_V_DO_REFERENCE2,
         NEW_V_DO_ASSIGNE_A,
         NEW_D_DO_DATE_ASSIGNATION,
         NEW_D_DO_DATE_EVENEMENT,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_V_DO_REFERENCE3,
         NEW_V_DO_REFERENCE4,
         NEW_V_DO_REFERENCE5,
         NEW_C_DO_FONDE,
         NEW_V_DO_REFERENCE6,
         NEW_V_DO_REFERENCE7,
         NEW_I_DO_FONDE,
         NEW_I_RF_CLE,
         NEW_B_DO_ENR_CONSERVE,
         NEW_B_DO_ENR_NUMERIQUE,
         NEW_L_DO_ORIGINE);
      commit;
      /*IF F_TRAITE_INSCRIPTION(NEW_I_CA_CLE) = 1 THEN
           CARDEX_LIEN.SP_E_IS_INSCRIPTION(
           'I' ,
           NEW_L_IS_CLE,
           NEW_L_IS_SITE,
           NEW_L_DO_CLE,
           NEW_V_DO_DUREE,
           NEW_D_DO_DATE_DEBUT,
           NEW_D_DO_DATE_FIN,
           NEW_I_PE_CLE,
           NEW_L_SI_CLE,
           NULL,
           'DO',
           NEW_L_SI_CLE);
      END IF;*/
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_DOSSIER(NEW_L_DO_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update DO_DOSSIER
         set V_DO_NUMERO_DOSSIER           = NEW_V_DO_NUMERO_DOSSIER,
             I_CA_CLE                      = NEW_I_CA_CLE,
             I_SE_CLE                      = NEW_I_SE_CLE,
             I_CC_CLE                      = NEW_I_CC_CLE,
             I_NH_CLE                      = NEW_I_NH_CLE,
             I_PE_CLE                      = NEW_I_PE_CLE,
             I_ST_CLE                      = NEW_I_ST_CLE,
             V_DO_MOT_PASSE                = NEW_V_DO_MOT_PASSE,
             D_DO_DATE_DEBUT               = NEW_D_DO_DATE_DEBUT,
             D_DO_DATE_FIN                 = NEW_D_DO_DATE_FIN,
             V_DO_DUREE                    = NEW_V_DO_DUREE,
             V_DO_ANCIENNE_REFERENCE       = NEW_V_DO_ANCIENNE_REFERENCE,
             V_DO_REFERENCE_VIDEO          = NEW_V_DO_REFERENCE_VIDEO,
             D_DO_DATE_RAPPORTEE           = NEW_D_DO_DATE_RAPPORTEE,
             I_DO_CLASSE                   = NEW_I_DO_CLASSE,
             I_DO_RACE                     = NEW_I_DO_RACE,
             V_DO_LIEU                     = NEW_V_DO_LIEU,
             I_OR_CLE                      = NEW_I_OR_CLE,
             I_CR_CLE                      = NEW_I_CR_CLE,
             V_DO_REFERENCE1               = NEW_V_DO_REFERENCE1,
             V_DO_REFERENCE2               = NEW_V_DO_REFERENCE2,
             V_DO_ASSIGNE_A                = NEW_V_DO_ASSIGNE_A,
             D_DO_DATE_ASSIGNATION         = NEW_D_DO_DATE_ASSIGNATION,
             D_DO_DATE_EVENEMENT           = NEW_D_DO_DATE_EVENEMENT,
             V_DO_REFERENCE3               = NEW_V_DO_REFERENCE3,
             V_DO_REFERENCE4               = NEW_V_DO_REFERENCE4,
             V_DO_REFERENCE5               = NEW_V_DO_REFERENCE5,
             C_DO_FONDE                    = NEW_C_DO_FONDE,
             V_DO_REFERENCE6               = NEW_V_DO_REFERENCE6,
             V_DO_REFERENCE7               = NEW_V_DO_REFERENCE7,
             I_DO_FONDE                    = NEW_I_DO_FONDE,
             I_RF_CLE                      = NEW_I_RF_CLE,
             B_DO_ENREGISTREMENT_CONSERVE  = NEW_B_DO_ENR_CONSERVE,
             B_DO_ENREGISTREMENT_NUMERIQUE = NEW_B_DO_ENR_NUMERIQUE,
             L_DO_ORIGINE                  = NEW_L_DO_ORIGINE
       where L_DO_CLE = NEW_L_DO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;

    elsif action = 'D' then
      delete from DO_DOSSIER
       where L_DO_CLE = NEW_L_DO_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'DO',
                                 NEW_L_DO_CLE,
                                 NEW_L_SI_CLE,
                                 'DO',
                                 NEW_L_DO_CLE,
                                 NEW_L_SI_CLE,
                                 'DO',
                                 NEW_L_DO_CLE,
                                 NEW_L_SI_CLE);
  exception
    when others then
      raise_application_error(-20000, 'Database : SP_E_DO_DOSSIER', true);
  END SP_E_DO_DOSSIER;
  procedure SP_E_SU_SUJET(action                     IN CHAR,
                          NEW_L_SU_CLE               IN OUT SU_SUJET.L_SU_CLE%TYPE,
                          NEW_L_SI_CLE               IN OUT SU_SUJET.L_SI_CLE%TYPE,
                          NEW_I_SX_CLE               IN SU_SUJET.I_SX_CLE%TYPE,
                          NEW_I_ST_CLE               IN SU_SUJET.I_ST_CLE%TYPE,
                          NEW_I_NT_CLE               IN SU_SUJET.I_NT_CLE%TYPE,
                          NEW_I_RA_CLE               IN SU_SUJET.I_RA_CLE%TYPE,
                          NEW_I_LS_CLE               IN SU_SUJET.I_LS_CLE%TYPE,
                          NEW_V_SU_NOM               IN SU_SUJET.V_SU_NOM%TYPE,
                          NEW_V_SU_PRENOM            IN SU_SUJET.V_SU_PRENOM%TYPE,
                          NEW_V_SU_SURNOM            IN SU_SUJET.V_SU_SURNOM%TYPE,
                          NEW_D_SU_DATE_NAISSANCE    IN SU_SUJET.D_SU_DATE_NAISSANCE%TYPE,
                          NEW_L_SU_TYPE_AGE          IN SU_SUJET.L_SU_TYPE_AGE%TYPE,
                          NEW_C_SU_ASSURANCE_SOCIALE IN SU_SUJET.C_SU_ASSURANCE_SOCIALE%TYPE,
                          NEW_V_SU_PERMIS_CONDUIRE   IN SU_SUJET.V_SU_PERMIS_CONDUIRE%TYPE,
                          NEW_V_SU_GRANDEUR_METRIQUE IN SU_SUJET.V_SU_GRANDEUR_METRIQUE%TYPE,
                          NEW_V_SU_GRANDEUR_IMPERIAL IN SU_SUJET.V_SU_GRANDEUR_IMPERIAL%TYPE,
                          NEW_V_SU_POIDS_METRIQUE    IN SU_SUJET.V_SU_POIDS_METRIQUE%TYPE,
                          NEW_V_SU_POIDS_IMPERIAL    IN SU_SUJET.V_SU_POIDS_IMPERIAL%TYPE,
                          NEW_C_SU_SYSTEME           IN SU_SUJET.C_SU_SYSTEME%TYPE,
                          NEW_I_CC_CLE               IN SU_SUJET.I_CC_CLE%TYPE,
                          NEW_V_SU_MOT_PASSE         IN SU_SUJET.V_SU_MOT_PASSE%TYPE,
                          NEW_V_SU_REFERENCE_1       IN SU_SUJET.V_SU_REFERENCE_1%TYPE,
                          NEW_V_SU_REFERENCE_2       IN SU_SUJET.V_SU_REFERENCE_2%TYPE,
                          NEW_V_SU_REFERENCE_3       IN OUT SU_SUJET.V_SU_REFERENCE_3%TYPE,
                          NEW_I_SE_CLE               IN SE_SEVERITE.I_SE_CLE%TYPE,
                          NEW_V_SU_NO_PASSEPORT      IN SU_SUJET.V_SU_NO_PASSEPORT%TYPE,
                          NEW_V_SU_ASSURANCE_MALADIE IN SU_SUJET.V_SU_ASSURANCE_MALADIE%TYPE,
                          NEW_I_SE_CLE_AUTRES        IN SE_SEVERITE.I_SE_CLE%TYPE,
                          NEW_B_SU_IND_RDD           IN SU_SUJET.B_SU_IND_RDD%TYPE,
                          NEW_I_SE_CLE_CASINO        IN SU_SUJET.I_SE_CLE_CASINO%TYPE,
                          NEW_D_SU_DATE_FIN_EMPLOI   IN SU_SUJET.D_SU_DATE_FIN_EMPLOI%TYPE) IS
  begin
    if action = 'I' then
      select seq_sujet.nextval into NEW_L_SU_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      if NEW_V_SU_REFERENCE_3 =
         '                                                 *' then
        NEW_V_SU_REFERENCE_3 := cardex_special.f_no_sujet(NEW_L_SU_CLE,
                                                          NEW_L_SI_CLE,
                                                          SYSDATE);
      end if;
      insert into SU_SUJET
        (L_SU_CLE,
         L_SI_CLE,
         I_SX_CLE,
         I_ST_CLE,
         I_NT_CLE,
         I_RA_CLE,
         I_LS_CLE,
         V_SU_NOM,
         V_SU_PRENOM,
         V_SU_SURNOM,
         D_SU_DATE_NAISSANCE,
         L_SU_TYPE_AGE,
         C_SU_ASSURANCE_SOCIALE,
         V_SU_PERMIS_CONDUIRE,
         V_SU_GRANDEUR_METRIQUE,
         V_SU_GRANDEUR_IMPERIAL,
         V_SU_POIDS_METRIQUE,
         V_SU_POIDS_IMPERIAL,
         C_SU_SYSTEME,
         I_CC_CLE,
         V_SU_MOT_PASSE,
         V_SU_REFERENCE_1,
         V_SU_REFERENCE_2,
         V_SU_REFERENCE_3,
         V_SU_CREE_PAR,
         D_SU_DATE_CREATION,
         C_SU_SNDX_NOM,
         C_SU_SNDX_PRENOM,
         C_SU_SNDX_SURNOM,
         I_SE_CLE,
         V_SU_NO_PASSEPORT,
         V_SU_ASSURANCE_MALADIE,
         I_SE_CLE_AUTRES,
         B_SU_IND_RDD,
         I_SE_CLE_CASINO,
         D_SU_DATE_FIN_EMPLOI)
      values
        (NEW_L_SU_CLE,
         NEW_L_SI_CLE,
         NEW_I_SX_CLE,
         NEW_I_ST_CLE,
         NEW_I_NT_CLE,
         NEW_I_RA_CLE,
         NEW_I_LS_CLE,
         NEW_V_SU_NOM,
         NEW_V_SU_PRENOM,
         NEW_V_SU_SURNOM,
         NEW_D_SU_DATE_NAISSANCE,
         NEW_L_SU_TYPE_AGE,
         NEW_C_SU_ASSURANCE_SOCIALE,
         NEW_V_SU_PERMIS_CONDUIRE,
         NEW_V_SU_GRANDEUR_METRIQUE,
         NEW_V_SU_GRANDEUR_IMPERIAL,
         NEW_V_SU_POIDS_METRIQUE,
         NEW_V_SU_POIDS_IMPERIAL,
         NEW_C_SU_SYSTEME,
         NEW_I_CC_CLE,
         NEW_V_SU_MOT_PASSE,
         NEW_V_SU_REFERENCE_1,
         NEW_V_SU_REFERENCE_2,
         NEW_V_SU_REFERENCE_3,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         soundex(NEW_V_SU_NOM),
         soundex(NEW_V_SU_PRENOM),
         soundex(NEW_V_SU_SURNOM),
         NEW_I_SE_CLE,
         NEW_V_SU_NO_PASSEPORT,
         NEW_V_SU_ASSURANCE_MALADIE,
         NEW_I_SE_CLE_AUTRES,
         NEW_B_SU_IND_RDD,
         NEW_I_SE_CLE_CASINO,
         NEW_D_SU_DATE_FIN_EMPLOI);
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_SUJET(NEW_L_SU_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update SU_SUJET
         set I_SX_CLE               = NEW_I_SX_CLE,
             I_ST_CLE               = NEW_I_ST_CLE,
             I_NT_CLE               = NEW_I_NT_CLE,
             I_RA_CLE               = NEW_I_RA_CLE,
             I_LS_CLE               = NEW_I_LS_CLE,
             V_SU_NOM               = NEW_V_SU_NOM,
             V_SU_PRENOM            = NEW_V_SU_PRENOM,
             V_SU_SURNOM            = NEW_V_SU_SURNOM,
             D_SU_DATE_NAISSANCE    = NEW_D_SU_DATE_NAISSANCE,
             L_SU_TYPE_AGE          = NEW_L_SU_TYPE_AGE,
             C_SU_ASSURANCE_SOCIALE = NEW_C_SU_ASSURANCE_SOCIALE,
             V_SU_PERMIS_CONDUIRE   = NEW_V_SU_PERMIS_CONDUIRE,
             V_SU_GRANDEUR_METRIQUE = NEW_V_SU_GRANDEUR_METRIQUE,
             V_SU_GRANDEUR_IMPERIAL = NEW_V_SU_GRANDEUR_IMPERIAL,
             V_SU_POIDS_METRIQUE    = NEW_V_SU_POIDS_METRIQUE,
             V_SU_POIDS_IMPERIAL    = NEW_V_SU_POIDS_IMPERIAL,
             C_SU_SYSTEME           = NEW_C_SU_SYSTEME,
             I_CC_CLE               = NEW_I_CC_CLE,
             V_SU_MOT_PASSE         = NEW_V_SU_MOT_PASSE,
             V_SU_REFERENCE_1       = NEW_V_SU_REFERENCE_1,
             V_SU_REFERENCE_2       = NEW_V_SU_REFERENCE_2,
             V_SU_REFERENCE_3       = NEW_V_SU_REFERENCE_3,
             C_SU_SNDX_NOM          = soundex(NEW_V_SU_NOM),
             C_SU_SNDX_PRENOM       = soundex(NEW_V_SU_PRENOM),
             C_SU_SNDX_SURNOM       = soundex(NEW_V_SU_SURNOM),
             I_SE_CLE               = NEW_I_SE_CLE,
             V_SU_NO_PASSEPORT      = NEW_V_SU_NO_PASSEPORT,
             V_SU_ASSURANCE_MALADIE = NEW_V_SU_ASSURANCE_MALADIE,
             I_SE_CLE_AUTRES        = NEW_I_SE_CLE_AUTRES,
             B_SU_IND_RDD           = NEW_B_SU_IND_RDD,
             I_SE_CLE_CASINO        = NEW_I_SE_CLE_CASINO,
             D_SU_DATE_FIN_EMPLOI   = NEW_D_SU_DATE_FIN_EMPLOI
       where L_SU_CLE = NEW_L_SU_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
      --Mise à jour de la confidentialité de la photo en cas de changement
      cardex_lien.SP_E_LMM_LIEN_MULTIMEDIA('U',
                                           'SU',
                                           new_l_su_cle,
                                           new_l_si_cle,
                                           new_i_cc_cle);
    elsif action = 'D' then
      delete from SU_SUJET
       where L_SU_CLE = NEW_L_SU_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'SU',
                                 NEW_L_SU_CLE,
                                 NEW_L_SI_CLE,
                                 'SU',
                                 NEW_L_SU_CLE,
                                 NEW_L_SI_CLE,
                                 'SU',
                                 NEW_L_SU_CLE,
                                 NEW_L_SI_CLE);
  END SP_E_SU_SUJET;
  procedure SP_E_MM_MULTIMEDIA(action               IN CHAR,
                               NEW_L_MM_CLE         IN OUT MM_MULTIMEDIA.L_MM_CLE%TYPE,
                               NEW_L_SI_CLE         IN OUT MM_MULTIMEDIA.L_SI_CLE%TYPE,
                               NEW_L_EM_CLE         IN MM_MULTIMEDIA.L_EM_CLE%TYPE,
                               NEW_I_TM_CLE         IN MM_MULTIMEDIA.I_TM_CLE%TYPE,
                               NEW_V_MM_DESCRIPTION IN MM_MULTIMEDIA.V_MM_DESCRIPTION%TYPE,
                               NEW_L_EM_SI_CLE      IN MM_MULTIMEDIA.L_EM_SI_CLE%TYPE,
                               NEW_V_MM_EXTENSION   IN MM_MULTIMEDIA.V_MM_EXTENSION%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_MM_CLE from dual;
      NEW_L_SI_CLE := CARDEX_SPECIAL.g_site;
      insert into MM_MULTIMEDIA
        (L_MM_CLE,
         L_SI_CLE,
         L_EM_CLE,
         I_TM_CLE,
         V_MM_DESCRIPTION,
         V_MM_CREE_PAR,
         D_MM_DATE_CREATION,
         L_EM_SI_CLE,
         V_MM_EXTENSION)
      values
        (NEW_L_MM_CLE,
         NEW_L_SI_CLE,
         NEW_L_EM_CLE,
         NEW_I_TM_CLE,
         NEW_V_MM_DESCRIPTION,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_L_EM_SI_CLE,
         NEW_V_MM_EXTENSION);
      commit;
    elsif action = 'U' then
      update MM_MULTIMEDIA
         set L_EM_CLE         = NEW_L_EM_CLE,
             I_TM_CLE         = NEW_I_TM_CLE,
             V_MM_DESCRIPTION = NEW_V_MM_DESCRIPTION,
             L_EM_SI_CLE      = NEW_L_EM_SI_CLE,
             V_MM_EXTENSION   = NEW_V_MM_EXTENSION
       where L_MM_CLE = NEW_L_MM_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      delete from MM_MULTIMEDIA
       where L_MM_CLE = NEW_L_MM_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
    cardex_special.sp_e_ac_acces(action,
                                 'MM',
                                 NEW_L_MM_CLE,
                                 NEW_L_SI_CLE,
                                 'MM',
                                 NEW_L_MM_CLE,
                                 NEW_L_SI_CLE,
                                 'MM',
                                 NEW_L_MM_CLE,
                                 NEW_L_SI_CLE);
  END SP_E_MM_MULTIMEDIA;

  procedure SP_E_SI_SITE_SEQUENCE(NEW_L_SI_CLE      IN SI_SITE.L_SI_CLE%TYPE,
                                  NEW_D_SI_SEQUENCE IN SI_SITE.D_SI_SEQUENCE%TYPE,
                                  NEW_N_SI_SEQUENCE IN SI_SITE.N_SI_SEQUENCE%TYPE) IS
  begin
    update SI_SITE
       set D_SI_SEQUENCE = NEW_D_SI_SEQUENCE,
           N_SI_SEQUENCE = NEW_N_SI_SEQUENCE
     where L_SI_CLE = NEW_L_SI_CLE;
  END SP_E_SI_SITE_SEQUENCE;

  procedure SP_E_SU_SUJET_ASSIGNER_CC_C(NEW_L_SU_CLE IN SU_SUJET.L_SU_CLE%TYPE,
                                        NEW_L_SI_CLE IN SU_SUJET.L_SI_CLE%TYPE) IS
  begin
    --Audit des changements
    cardex_audit.SP_E_AUDIT_SUJET(NEW_L_SU_CLE, NEW_L_SI_CLE);
    UPDATE SU_SUJET
       SET I_CC_CLE = 307
     where L_SU_CLE = NEW_L_SU_CLE
       AND L_SI_CLE = NEW_L_SI_CLE;
  
    --Mise à jour de la confidentialité de la photo en cas de changement
    cardex_lien.SP_E_LMM_LIEN_MULTIMEDIA('U',
                                         'SU',
                                         NEW_L_SU_CLE,
                                         NEW_L_SI_CLE,
                                         307);
  END SP_E_SU_SUJET_ASSIGNER_CC_C;

  procedure SP_E_DO_DOSSIER_ASSIGNER_CC_C(NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                          NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE) IS
  begin
    --Audit des changements
    cardex_audit.SP_E_AUDIT_DOSSIER(NEW_L_DO_CLE, NEW_L_SI_CLE);
    UPDATE DO_DOSSIER
       SET I_CC_CLE = 307
     where L_DO_CLE = NEW_L_DO_CLE
       AND L_SI_CLE = NEW_L_SI_CLE;
  
    --Mise à jour de la confidentialité de la photo en cas de changement
    cardex_lien.SP_E_LMM_LIEN_MULTIMEDIA('U',
                                         'DO',
                                         NEW_L_DO_CLE,
                                         NEW_L_SI_CLE,
                                         307);
  END SP_E_DO_DOSSIER_ASSIGNER_CC_C;

  procedure SP_I_MOUVEMENTS(NOM                MOUVEMENTS.NOM%TYPE,
                            PRENOM             MOUVEMENTS.PRENOM%TYPE,
                            NAS                MOUVEMENTS.NAS%TYPE,
                            TYPE               MOUVEMENTS.TYPE%TYPE,
                            DATE_EMBAUCHE      MOUVEMENTS.DATE_EMBAUCHE%TYPE,
                            DATE_DEPART        MOUVEMENTS.DATE_DEPART%TYPE,
                            SITE               MOUVEMENTS.SITE%TYPE,
                            NO_EMPLOYE         MOUVEMENTS.NO_EMPLOYE%TYPE,
                            DATE_NAISSANCE     MOUVEMENTS.DATE_NAISSANCE%TYPE,
                            CLE                MOUVEMENTS.CLE%TYPE,
                            DESCRIPTION_CONGE  MOUVEMENTS.DESCRIPTION_CONGE%TYPE,
                            DATE_DEBUT_CONGE   MOUVEMENTS.DATE_DEBUT_CONGE%TYPE,
                            STATUT             MOUVEMENTS.STATUT%TYPE,
                            DATE_FIN_CONGE     MOUVEMENTS.DATE_FIN_CONGE%TYPE,
                            DESCRIPTION_EMPLOI MOUVEMENTS.DESCRIPTION_EMPLOI%TYPE) IS
  BEGIN
    insert into MOUVEMENTS
      (NOM,
       PRENOM,
       NAS,
       TYPE,
       DATE_EMBAUCHE,
       DATE_DEPART,
       SITE,
       NO_EMPLOYE,
       DATE_NAISSANCE,
       CLE,
       DESCRIPTION_CONGE,
       DATE_DEBUT_CONGE,
       STATUT,
       DATE_FIN_CONGE,
       DESCRIPTION_EMPLOI,
       DATE_INSERTION)
    values
      (NOM,
       PRENOM,
       NAS,
       TYPE,
       DATE_EMBAUCHE,
       DATE_DEPART,
       SITE,
       NO_EMPLOYE,
       DATE_NAISSANCE,
       CLE,
       DESCRIPTION_CONGE,
       DATE_DEBUT_CONGE,
       STATUT,
       DATE_FIN_CONGE,
       DESCRIPTION_EMPLOI,
       sysdate);
  END SP_I_MOUVEMENTS;

  --Mise-à-jour de la sévérité des sociétés d'investigation qui ont un dossier actif et dont la sévérité est pas à 2
  --Différé CDX_00008
  procedure SP_E_SO_SEVERITE_3 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SO_SOCIETE
      (L_SO_CLE,
       L_SI_CLE,
       I_ST_CLE,
       I_LS_CLE,
       V_SO_RAISON_SOCIALE,
       V_SO_NOM,
       I_CL_CLE,
       D_SO_DATE_FONDATION,
       I_CC_CLE,
       V_SO_MOT_PASSE,
       V_SO_REFERENCE_NOM,
       V_SO_REFERENCE_PRENOM,
       V_SO_REFERENCE_1,
       V_SO_REFERENCE_2,
       V_SO_REFERENCE_3,
       V_SO_CREE_PAR,
       D_SO_DATE_CREATION,
       C_SO_SNDX_RAISON_SOCIALE,
       C_SO_SNDX_NOM,
       I_SE_CLE,
       V_SO_CHANGE_PAR,
       D_SO_DATE_CHANGEMENT)
      select L_SO_CLE,
             L_SI_CLE,
             I_ST_CLE,
             I_LS_CLE,
             V_SO_RAISON_SOCIALE,
             V_SO_NOM,
             I_CL_CLE,
             D_SO_DATE_FONDATION,
             I_CC_CLE,
             V_SO_MOT_PASSE,
             V_SO_REFERENCE_NOM,
             V_SO_REFERENCE_PRENOM,
             V_SO_REFERENCE_1,
             V_SO_REFERENCE_2,
             V_SO_REFERENCE_3,
             V_SO_CREE_PAR,
             D_SO_DATE_CREATION,
             C_SO_SNDX_RAISON_SOCIALE,
             C_SO_SNDX_NOM,
             I_SE_CLE,
             CARDEX_SPECIAL.g_usager,
             sysdate
        from SO_SOCIETE P
       WHERE exists
       (select s2.v_so_nom
                from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_so_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SO'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin >= sysdate
                 and (s2.i_se_cle = 14954 or s2.i_se_cle = 1328)
                 and d.i_ge_cle = 21876
                 and S2.l_so_cle = p.l_so_cle
                 and s2.l_si_cle = p.l_si_cle
                 and d.i_cc_cle <> 14902
                 and d.i_st_cle = 359)
         and p.i_cc_cle <> 14920;
    --On change ensuite la sévérité
    update SO_SOCIETE P
       set p.i_se_cle = 14955
     WHERE exists
     (select s2.v_so_nom
              from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_so_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SO'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin >= sysdate
               and (s2.i_se_cle = 14954 or s2.i_se_cle = 1328)
               and d.i_ge_cle = 21876
               and S2.l_so_cle = p.l_so_cle
               and s2.l_si_cle = p.l_si_cle
               and d.i_cc_cle <> 14902
               and d.i_st_cle = 359)
       and p.i_cc_cle <> 14920;
    commit;
  end SP_E_SO_SEVERITE_3;

  --Mise-à-jour de la sévérité des sujets d'investigation qui ont un dossier actif et dont la sévérité est à 2
  --Différé CDX_00008
  procedure SP_E_SU_SEVERITE_3 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SU_SUJET
      (L_SU_CLE,
       L_SI_CLE,
       I_SX_CLE,
       I_ST_CLE,
       I_NT_CLE,
       I_RA_CLE,
       I_LS_CLE,
       V_SU_NOM,
       V_SU_PRENOM,
       V_SU_SURNOM,
       D_SU_DATE_NAISSANCE,
       C_SU_ASSURANCE_SOCIALE,
       V_SU_PERMIS_CONDUIRE,
       I_CC_CLE,
       V_SU_MOT_PASSE,
       V_SU_REFERENCE_1,
       V_SU_REFERENCE_2,
       V_SU_REFERENCE_3,
       V_SU_CREE_PAR,
       D_SU_DATE_CREATION,
       C_SU_SNDX_NOM,
       C_SU_SNDX_PRENOM,
       C_SU_SNDX_SURNOM,
       I_SE_CLE,
       V_SU_NO_PASSEPORT,
       L_SU_TYPE_AGE,
       V_SU_ASSURANCE_MALADIE,
       I_SE_CLE_AUTRES,
       V_SU_CHANGE_PAR,
       D_SU_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SU_CLE,
             L_SI_CLE,
             I_SX_CLE,
             I_ST_CLE,
             I_NT_CLE,
             I_RA_CLE,
             I_LS_CLE,
             V_SU_NOM,
             V_SU_PRENOM,
             V_SU_SURNOM,
             D_SU_DATE_NAISSANCE,
             C_SU_ASSURANCE_SOCIALE,
             V_SU_PERMIS_CONDUIRE,
             I_CC_CLE,
             V_SU_MOT_PASSE,
             V_SU_REFERENCE_1,
             V_SU_REFERENCE_2,
             V_SU_REFERENCE_3,
             V_SU_CREE_PAR,
             D_SU_DATE_CREATION,
             C_SU_SNDX_NOM,
             C_SU_SNDX_PRENOM,
             C_SU_SNDX_SURNOM,
             I_SE_CLE,
             V_SU_NO_PASSEPORT,
             L_SU_TYPE_AGE,
             V_SU_ASSURANCE_MALADIE,
             I_SE_CLE_AUTRES,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SU_SUJET P
       WHERE exists
       (select s2.v_su_nom
                from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_su_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SU'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin >= sysdate
                 and (s2.i_se_cle = 14954 or s2.i_se_cle = 1328)
                 and d.i_ge_cle = 21876
                 and S2.l_su_cle = p.l_su_cle
                 and s2.l_si_cle = p.l_si_cle
                 and d.i_cc_cle <> 14920
                 and d.i_st_cle = 359)
         and p.i_cc_cle <> 14920;
    -- On inscrit ensuite la sévérité
    update SU_SUJET P
       set p.i_se_cle = 14955
     WHERE exists
     (select s2.v_su_nom
              from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_su_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SU'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin >= sysdate
               and (s2.i_se_cle = 14954 or s2.i_se_cle = 1328)
               and d.i_ge_cle = 21876
               and S2.l_su_cle = p.l_su_cle
               and s2.l_si_cle = p.l_si_cle
               and d.i_cc_cle <> 14920
               and d.i_st_cle = 359)
       and p.i_cc_cle <> 14920;
    commit;
  end SP_E_SU_SEVERITE_3;

  --Mise-à-jour de la sévérité des sujets d'investigation dont le dossier est expiré et dont la sévérité n'est pas à 2
  --Différé CDX_00008
  procedure SP_E_SU_SEVERITE_2 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SU_SUJET
      (L_SU_CLE,
       L_SI_CLE,
       I_SX_CLE,
       I_ST_CLE,
       I_NT_CLE,
       I_RA_CLE,
       I_LS_CLE,
       V_SU_NOM,
       V_SU_PRENOM,
       V_SU_SURNOM,
       D_SU_DATE_NAISSANCE,
       C_SU_ASSURANCE_SOCIALE,
       V_SU_PERMIS_CONDUIRE,
       I_CC_CLE,
       V_SU_MOT_PASSE,
       V_SU_REFERENCE_1,
       V_SU_REFERENCE_2,
       V_SU_REFERENCE_3,
       V_SU_CREE_PAR,
       D_SU_DATE_CREATION,
       C_SU_SNDX_NOM,
       C_SU_SNDX_PRENOM,
       C_SU_SNDX_SURNOM,
       I_SE_CLE,
       V_SU_NO_PASSEPORT,
       L_SU_TYPE_AGE,
       V_SU_ASSURANCE_MALADIE,
       I_SE_CLE_AUTRES,
       V_SU_CHANGE_PAR,
       D_SU_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SU_CLE,
             L_SI_CLE,
             I_SX_CLE,
             I_ST_CLE,
             I_NT_CLE,
             I_RA_CLE,
             I_LS_CLE,
             V_SU_NOM,
             V_SU_PRENOM,
             V_SU_SURNOM,
             D_SU_DATE_NAISSANCE,
             C_SU_ASSURANCE_SOCIALE,
             V_SU_PERMIS_CONDUIRE,
             I_CC_CLE,
             V_SU_MOT_PASSE,
             V_SU_REFERENCE_1,
             V_SU_REFERENCE_2,
             V_SU_REFERENCE_3,
             V_SU_CREE_PAR,
             D_SU_DATE_CREATION,
             C_SU_SNDX_NOM,
             C_SU_SNDX_PRENOM,
             C_SU_SNDX_SURNOM,
             I_SE_CLE,
             V_SU_NO_PASSEPORT,
             L_SU_TYPE_AGE,
             V_SU_ASSURANCE_MALADIE,
             I_SE_CLE_AUTRES,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SU_SUJET P
       WHERE exists
       (select s2.v_su_reference_3
                from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_su_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SU'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin <= sysdate
                 and (s2.i_se_cle = 14955 or s2.i_se_cle is null or
                     s2.i_se_cle = 1328)
                 and d.i_ge_cle = 21876
                 and s2.l_su_cle = p.l_su_cle
                 and s2.l_si_cle = p.l_si_cle)
         and not exists
       (select s2.v_su_reference_3
                from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_su_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SU'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin > sysdate
                 and (s2.i_se_cle <> 561)
                 and (d.i_se_cle <> 14954 or
                     (d.i_st_cle = 359 and d.i_se_cle is null))
                 and d.i_ge_cle = 21876
                 and s2.l_su_cle = p.l_su_cle
                 and s2.l_si_cle = p.l_si_cle)
         and p.v_su_nom is not null
         and p.i_cc_cle <> 14920;
    --On change ensuite la sévérité
    update SU_SUJET P
       set p.i_se_cle = 14954
     WHERE exists
     (select s2.v_su_reference_3
              from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_su_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SU'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin <= sysdate
               and (s2.i_se_cle = 14955 or s2.i_se_cle is null or
                   s2.i_se_cle = 1328)
               and d.i_ge_cle = 21876
               and s2.l_su_cle = p.l_su_cle
               and s2.l_si_cle = p.l_si_cle)
       and not exists
     (select s2.v_su_reference_3
              from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_su_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SU'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin > sysdate
               and (s2.i_se_cle <> 561)
               and (d.i_se_cle <> 14954 or
                   (d.i_st_cle = 359 and d.i_se_cle is null))
               and d.i_ge_cle = 21876
               and s2.l_su_cle = p.l_su_cle
               and s2.l_si_cle = p.l_si_cle)
       and p.v_su_nom is not null
       and p.i_cc_cle <> 14920;
    commit;
  end SP_E_SU_SEVERITE_2;

  --Mise-à-jour de la sévérité des sociétés d'investigation dont le dossier est expiré et dont la sévérité n'est pas à 2
  --Différé CDX_00008
  procedure SP_E_SO_SEVERITE_2 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SO_SOCIETE
      (L_SO_CLE,
       L_SI_CLE,
       I_ST_CLE,
       I_LS_CLE,
       V_SO_RAISON_SOCIALE,
       V_SO_NOM,
       I_CL_CLE,
       D_SO_DATE_FONDATION,
       I_CC_CLE,
       V_SO_MOT_PASSE,
       V_SO_REFERENCE_NOM,
       V_SO_REFERENCE_PRENOM,
       V_SO_REFERENCE_1,
       V_SO_REFERENCE_2,
       V_SO_REFERENCE_3,
       V_SO_CREE_PAR,
       D_SO_DATE_CREATION,
       C_SO_SNDX_RAISON_SOCIALE,
       C_SO_SNDX_NOM,
       I_SE_CLE,
       V_SO_CHANGE_PAR,
       D_SO_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SO_CLE,
             L_SI_CLE,
             I_ST_CLE,
             I_LS_CLE,
             V_SO_RAISON_SOCIALE,
             V_SO_NOM,
             I_CL_CLE,
             D_SO_DATE_FONDATION,
             I_CC_CLE,
             V_SO_MOT_PASSE,
             V_SO_REFERENCE_NOM,
             V_SO_REFERENCE_PRENOM,
             V_SO_REFERENCE_1,
             V_SO_REFERENCE_2,
             V_SO_REFERENCE_3,
             V_SO_CREE_PAR,
             D_SO_DATE_CREATION,
             C_SO_SNDX_RAISON_SOCIALE,
             C_SO_SNDX_NOM,
             I_SE_CLE,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SO_SOCIETE P
       WHERE exists
       (select s2.v_so_nom
                from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_so_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SO'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin <= sysdate
                 and (s2.i_se_cle = 14955 or s2.i_se_cle is null or
                     s2.i_se_cle = 1328)
                 and d.i_ge_cle = 21876
                 and s2.l_so_cle = p.l_so_cle
                 and s2.l_si_cle = p.l_si_cle)
         and not exists
       (select s2.v_so_nom
                from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_so_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SO'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin > sysdate
                 and (s2.i_se_cle <> 561)
                 and (d.i_se_cle <> 14954)
                 and d.i_ge_cle = 21876
                 and s2.l_so_cle = p.l_so_cle
                 and s2.l_si_cle = p.l_si_cle)
         and p.v_so_nom is not null
         and p.i_cc_cle <> 14920;
    -- On change ensuite la sévérité        
    update SO_SOCIETE P
       set p.i_se_cle = 14954
     WHERE exists
     (select s2.v_so_nom
              from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_so_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SO'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin <= sysdate
               and (s2.i_se_cle = 14955 or s2.i_se_cle is null or
                   s2.i_se_cle = 1328)
               and d.i_ge_cle = 21876
               and s2.l_so_cle = p.l_so_cle
               and s2.l_si_cle = p.l_si_cle)
       and not exists
     (select s2.v_so_nom
              from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_so_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SO'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin > sysdate
               and (s2.i_se_cle <> 561)
               and (d.i_se_cle <> 14954)
               and d.i_ge_cle = 21876
               and s2.l_so_cle = p.l_so_cle
               and s2.l_si_cle = p.l_si_cle)
       and p.v_so_nom is not null
       and p.i_cc_cle <> 14920;
    commit;
  end SP_E_SO_SEVERITE_2;

  --Mise-à-jour à 4 de la sévérité des sujets d'investigation dont le dossier va venir à échéance dans 90 jours
  --Différé CDX_00008
  procedure SP_E_SU_SEVERITE_4 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SU_SUJET
      (L_SU_CLE,
       L_SI_CLE,
       I_SX_CLE,
       I_ST_CLE,
       I_NT_CLE,
       I_RA_CLE,
       I_LS_CLE,
       V_SU_NOM,
       V_SU_PRENOM,
       V_SU_SURNOM,
       D_SU_DATE_NAISSANCE,
       C_SU_ASSURANCE_SOCIALE,
       V_SU_PERMIS_CONDUIRE,
       I_CC_CLE,
       V_SU_MOT_PASSE,
       V_SU_REFERENCE_1,
       V_SU_REFERENCE_2,
       V_SU_REFERENCE_3,
       V_SU_CREE_PAR,
       D_SU_DATE_CREATION,
       C_SU_SNDX_NOM,
       C_SU_SNDX_PRENOM,
       C_SU_SNDX_SURNOM,
       I_SE_CLE,
       V_SU_NO_PASSEPORT,
       L_SU_TYPE_AGE,
       V_SU_ASSURANCE_MALADIE,
       I_SE_CLE_AUTRES,
       V_SU_CHANGE_PAR,
       D_SU_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SU_CLE,
             L_SI_CLE,
             I_SX_CLE,
             I_ST_CLE,
             I_NT_CLE,
             I_RA_CLE,
             I_LS_CLE,
             V_SU_NOM,
             V_SU_PRENOM,
             V_SU_SURNOM,
             D_SU_DATE_NAISSANCE,
             C_SU_ASSURANCE_SOCIALE,
             V_SU_PERMIS_CONDUIRE,
             I_CC_CLE,
             V_SU_MOT_PASSE,
             V_SU_REFERENCE_1,
             V_SU_REFERENCE_2,
             V_SU_REFERENCE_3,
             V_SU_CREE_PAR,
             D_SU_DATE_CREATION,
             C_SU_SNDX_NOM,
             C_SU_SNDX_PRENOM,
             C_SU_SNDX_SURNOM,
             I_SE_CLE,
             V_SU_NO_PASSEPORT,
             L_SU_TYPE_AGE,
             V_SU_ASSURANCE_MALADIE,
             I_SE_CLE_AUTRES,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SU_SUJET P
       WHERE exists
       (select s2.v_su_reference_3
                from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_su_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SU'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin between sysdate and sysdate + 90
                 and (s2.i_se_cle = 14955)
                 and d.i_ge_cle = 21876
                 and d.i_cc_cle <> 14920
                 and s2.l_su_cle = p.l_su_cle
                 and s2.l_si_cle = p.l_si_cle)
         and not exists
       (select s2.v_su_reference_3
                from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_su_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SU'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin > sysdate + 90
                 and (s2.i_se_cle = 561 or s2.i_se_cle is null or
                     s2.i_se_cle = 1328)
                 and d.i_ge_cle = 21876
                 and s2.l_su_cle = p.l_su_cle
                 and s2.l_si_cle = p.l_si_cle)
         and p.i_cc_cle <> 14920
         and p.v_su_nom is not null;
    -- On change ensuite la sévérité
    update SU_SUJET P
       set p.i_se_cle = 1328
     WHERE exists
     (select s2.v_su_reference_3
              from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_su_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SU'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin between sysdate and sysdate + 90
               and (s2.i_se_cle = 14955)
               and d.i_ge_cle = 21876
               and d.i_cc_cle <> 14920
               and s2.l_su_cle = p.l_su_cle
               and s2.l_si_cle = p.l_si_cle)
       and not exists
     (select s2.v_su_reference_3
              from su_sujet s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_su_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SU'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin > sysdate + 90
               and (s2.i_se_cle = 561 or s2.i_se_cle is null or
                   s2.i_se_cle = 1328)
               and d.i_ge_cle = 21876
               and s2.l_su_cle = p.l_su_cle
               and s2.l_si_cle = p.l_si_cle)
       and p.i_cc_cle <> 14920
       and p.v_su_nom is not null;
    commit;
  end SP_E_SU_SEVERITE_4;

  --Mise-à-jour à 4 de la sévérité des sociétés d'investigation dont le dossier va venir à échéance dans 90 jours
  --Différé CDX_00008  
  procedure SP_E_SO_SEVERITE_4 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SO_SOCIETE
      (L_SO_CLE,
       L_SI_CLE,
       I_ST_CLE,
       I_LS_CLE,
       V_SO_RAISON_SOCIALE,
       V_SO_NOM,
       I_CL_CLE,
       D_SO_DATE_FONDATION,
       I_CC_CLE,
       V_SO_MOT_PASSE,
       V_SO_REFERENCE_NOM,
       V_SO_REFERENCE_PRENOM,
       V_SO_REFERENCE_1,
       V_SO_REFERENCE_2,
       V_SO_REFERENCE_3,
       V_SO_CREE_PAR,
       D_SO_DATE_CREATION,
       C_SO_SNDX_RAISON_SOCIALE,
       C_SO_SNDX_NOM,
       I_SE_CLE,
       V_SO_CHANGE_PAR,
       D_SO_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SO_CLE,
             L_SI_CLE,
             I_ST_CLE,
             I_LS_CLE,
             V_SO_RAISON_SOCIALE,
             V_SO_NOM,
             I_CL_CLE,
             D_SO_DATE_FONDATION,
             I_CC_CLE,
             V_SO_MOT_PASSE,
             V_SO_REFERENCE_NOM,
             V_SO_REFERENCE_PRENOM,
             V_SO_REFERENCE_1,
             V_SO_REFERENCE_2,
             V_SO_REFERENCE_3,
             V_SO_CREE_PAR,
             D_SO_DATE_CREATION,
             C_SO_SNDX_RAISON_SOCIALE,
             C_SO_SNDX_NOM,
             I_SE_CLE,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SO_SOCIETE P
       WHERE exists
       (select s2.v_so_nom
                from SO_SOCIETE s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_so_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SO'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin between sysdate and sysdate + 90
                 and (s2.i_se_cle = 14955)
                 and d.i_ge_cle = 21876
                 and d.i_cc_cle <> 14920
                 and s2.l_so_cle = p.l_so_cle
                 and s2.l_si_cle = p.l_si_cle)
         and not exists
       (select s2.v_so_nom
                from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
               where d.l_do_cle = l.l_do_cle
                 and d.l_si_cle = l.l_do_site
                 and l.c_do_genre = 'DO'
                 and l.l_ldd_dossier_associe = s2.l_so_cle
                 and l.l_ldd_site = s2.l_si_cle
                 and l.c_ldd_genre = 'SO'
                 and (d.l_si_cle = 30 or d.l_si_cle = 11)
                 and d.d_do_date_fin > sysdate + 90
                 and (s2.i_se_cle = 561 or s2.i_se_cle is null or
                     s2.i_se_cle = 1328)
                 and d.i_ge_cle = 21876
                 and s2.l_so_cle = p.l_so_cle
                 and s2.l_si_cle = p.l_si_cle)
         and p.i_cc_cle <> 14920
         and p.v_so_nom is not null;
    --On change ensuite la sévérité
    update SO_SOCIETE P
       set p.i_se_cle = 1328
     WHERE exists
     (select s2.v_so_nom
              from SO_SOCIETE s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_so_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SO'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin between sysdate and sysdate + 90
               and (s2.i_se_cle = 14955)
               and d.i_ge_cle = 21876
               and d.i_cc_cle <> 14920
               and s2.l_so_cle = p.l_so_cle
               and s2.l_si_cle = p.l_si_cle)
       and not exists
     (select s2.v_so_nom
              from so_societe s2, v_do_dossier_ca_ty d, ldd_lien_dossier l
             where d.l_do_cle = l.l_do_cle
               and d.l_si_cle = l.l_do_site
               and l.c_do_genre = 'DO'
               and l.l_ldd_dossier_associe = s2.l_so_cle
               and l.l_ldd_site = s2.l_si_cle
               and l.c_ldd_genre = 'SO'
               and (d.l_si_cle = 30 or d.l_si_cle = 11)
               and d.d_do_date_fin > sysdate + 90
               and (s2.i_se_cle = 561 or s2.i_se_cle is null or
                   s2.i_se_cle = 1328)
               and d.i_ge_cle = 21876
               and s2.l_so_cle = p.l_so_cle
               and s2.l_si_cle = p.l_si_cle)
       and p.i_cc_cle <> 14920
       and p.v_so_nom is not null;
    commit;
  end SP_E_SO_SEVERITE_4;

  --Mise-à-jour à 4 de la sévérité des dossiers d'investigation qui vont venir à échéance dans 90 jours
  --Différé CDX_00008  
  procedure SP_E_DO_SEVERITE_4 IS
  begin
    insert into AUD_DO_DOSSIER
      (L_DO_CLE,
       L_SI_CLE,
       V_DO_NUMERO_DOSSIER,
       I_CA_CLE,
       I_SE_CLE,
       I_CC_CLE,
       I_NH_CLE,
       I_PE_CLE,
       I_ST_CLE,
       V_DO_MOT_PASSE,
       D_DO_DATE_DEBUT,
       D_DO_DATE_FIN,
       V_DO_ANCIENNE_REFERENCE,
       V_DO_REFERENCE_VIDEO,
       V_DO_LIEU,
       I_OR_CLE,
       I_CR_CLE,
       V_DO_REFERENCE1,
       V_DO_REFERENCE2,
       V_DO_ASSIGNE_A,
       D_DO_DATE_ASSIGNATION,
       V_DO_CREE_PAR,
       D_DO_DATE_CREATION,
       V_DO_REFERENCE3,
       V_DO_REFERENCE4,
       V_DO_REFERENCE5,
       V_DO_REFERENCE6,
       V_DO_REFERENCE7,
       I_DO_FONDE,
       I_RF_CLE,
       B_DO_ENREGISTREMENT_CONSERVE,
       B_DO_ENREGISTREMENT_NUMERIQUE,
       L_DO_ORIGINE,
       V_DO_CHANGE_PAR,
       D_DO_DATE_CHANGEMENT)
      select L_DO_CLE,
             L_SI_CLE,
             V_DO_NUMERO_DOSSIER,
             I_CA_CLE,
             I_SE_CLE,
             I_CC_CLE,
             I_NH_CLE,
             I_PE_CLE,
             I_ST_CLE,
             V_DO_MOT_PASSE,
             D_DO_DATE_DEBUT,
             D_DO_DATE_FIN,
             V_DO_ANCIENNE_REFERENCE,
             V_DO_REFERENCE_VIDEO,
             V_DO_LIEU,
             I_OR_CLE,
             I_CR_CLE,
             V_DO_REFERENCE1,
             V_DO_REFERENCE2,
             V_DO_ASSIGNE_A,
             D_DO_DATE_ASSIGNATION,
             V_DO_CREE_PAR,
             D_DO_DATE_CREATION,
             V_DO_REFERENCE3,
             V_DO_REFERENCE4,
             V_DO_REFERENCE5,
             V_DO_REFERENCE6,
             V_DO_REFERENCE7,
             I_DO_FONDE,
             I_RF_CLE,
             B_DO_ENREGISTREMENT_CONSERVE,
             B_DO_ENREGISTREMENT_NUMERIQUE,
             L_DO_ORIGINE,
             CARDEX_SPECIAL.g_usager,
             sysdate
        from DO_DOSSIER P
       WHERE (p.l_si_cle = 30 or p.l_si_cle = 11)
         and p.d_do_date_fin between sysdate and sysdate + 90
         and (p.i_se_cle <> 1328 or p.i_se_cle <> 561)
         and p.i_cc_cle <> 14920
         and exists (select d.l_do_cle
                from v_do_dossier_ca_ty d
               where d.i_ge_cle = 21876
                 and d.l_do_cle = p.l_do_cle);
    --On change ensuite la sévérité
    update DO_DOSSIER P
       set p.i_se_cle = 1328
     WHERE (p.l_si_cle = 30 or p.l_si_cle = 11)
       and p.d_do_date_fin between sysdate and sysdate + 90
       and (p.i_se_cle <> 1328 or p.i_se_cle <> 561)
       and p.i_cc_cle <> 14920
       and exists (select d.l_do_cle
              from v_do_dossier_ca_ty d
             where d.i_ge_cle = 21876
               and d.l_do_cle = p.l_do_cle);
    commit;
  end SP_E_DO_SEVERITE_4;

  --Mise-à-jour de la sévérité des dossiers d'investigation expirés et dont la sévérité n'est pas à 2
  --Différé CDX_00008
  procedure SP_E_DO_SEVERITE_2 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_DO_DOSSIER
      (L_DO_CLE,
       L_SI_CLE,
       V_DO_NUMERO_DOSSIER,
       I_CA_CLE,
       I_SE_CLE,
       I_CC_CLE,
       I_NH_CLE,
       I_PE_CLE,
       I_ST_CLE,
       V_DO_MOT_PASSE,
       D_DO_DATE_DEBUT,
       D_DO_DATE_FIN,
       V_DO_ANCIENNE_REFERENCE,
       V_DO_REFERENCE_VIDEO,
       V_DO_LIEU,
       I_OR_CLE,
       I_CR_CLE,
       V_DO_REFERENCE1,
       V_DO_REFERENCE2,
       V_DO_ASSIGNE_A,
       D_DO_DATE_ASSIGNATION,
       V_DO_CREE_PAR,
       D_DO_DATE_CREATION,
       V_DO_REFERENCE3,
       V_DO_REFERENCE4,
       V_DO_REFERENCE5,
       V_DO_REFERENCE6,
       V_DO_REFERENCE7,
       I_DO_FONDE,
       I_RF_CLE,
       B_DO_ENREGISTREMENT_CONSERVE,
       B_DO_ENREGISTREMENT_NUMERIQUE,
       L_DO_ORIGINE,
       V_DO_CHANGE_PAR,
       D_DO_DATE_CHANGEMENT)
      select L_DO_CLE,
             L_SI_CLE,
             V_DO_NUMERO_DOSSIER,
             I_CA_CLE,
             I_SE_CLE,
             I_CC_CLE,
             I_NH_CLE,
             I_PE_CLE,
             I_ST_CLE,
             V_DO_MOT_PASSE,
             D_DO_DATE_DEBUT,
             D_DO_DATE_FIN,
             V_DO_ANCIENNE_REFERENCE,
             V_DO_REFERENCE_VIDEO,
             V_DO_LIEU,
             I_OR_CLE,
             I_CR_CLE,
             V_DO_REFERENCE1,
             V_DO_REFERENCE2,
             V_DO_ASSIGNE_A,
             D_DO_DATE_ASSIGNATION,
             V_DO_CREE_PAR,
             D_DO_DATE_CREATION,
             V_DO_REFERENCE3,
             V_DO_REFERENCE4,
             V_DO_REFERENCE5,
             V_DO_REFERENCE6,
             V_DO_REFERENCE7,
             I_DO_FONDE,
             I_RF_CLE,
             B_DO_ENREGISTREMENT_CONSERVE,
             B_DO_ENREGISTREMENT_NUMERIQUE,
             L_DO_ORIGINE,
             CARDEX_SPECIAL.g_usager,
             sysdate
        from DO_DOSSIER P
       WHERE (p.l_si_cle = 30 or p.l_si_cle = 11)
         and p.d_do_date_fin <= sysdate
         and p.i_cc_cle <> 14920
         and (p.i_se_cle = 14955 or p.i_se_cle is null or p.i_se_cle = 1328)
         and exists (select d.l_do_cle
                from v_do_dossier_ca_ty d
               where d.i_ge_cle = 21876
                 and d.l_do_cle = p.l_do_cle);
    --On change ensuite la sévérité
    update DO_DOSSIER P
       set p.i_se_cle = 14954
     WHERE (p.l_si_cle = 30 or p.l_si_cle = 11)
       and p.d_do_date_fin <= sysdate
       and p.i_cc_cle <> 14920
       and (p.i_se_cle = 14955 or p.i_se_cle is null or p.i_se_cle = 1328)
       and exists (select d.l_do_cle
              from v_do_dossier_ca_ty d
             where d.i_ge_cle = 21876
               and d.l_do_cle = p.l_do_cle);
    commit;
  end SP_E_DO_SEVERITE_2;

  --Mise-à-jour à 3 de la sévérité des dossiers d'investigation actif dont la sévérité est à 2
  --Différé CDX_00008  
  procedure SP_E_DO_SEVERITE_3 IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_DO_DOSSIER
      (L_DO_CLE,
       L_SI_CLE,
       V_DO_NUMERO_DOSSIER,
       I_CA_CLE,
       I_SE_CLE,
       I_CC_CLE,
       I_NH_CLE,
       I_PE_CLE,
       I_ST_CLE,
       V_DO_MOT_PASSE,
       D_DO_DATE_DEBUT,
       D_DO_DATE_FIN,
       V_DO_ANCIENNE_REFERENCE,
       V_DO_REFERENCE_VIDEO,
       V_DO_LIEU,
       I_OR_CLE,
       I_CR_CLE,
       V_DO_REFERENCE1,
       V_DO_REFERENCE2,
       V_DO_ASSIGNE_A,
       D_DO_DATE_ASSIGNATION,
       V_DO_CREE_PAR,
       D_DO_DATE_CREATION,
       V_DO_REFERENCE3,
       V_DO_REFERENCE4,
       V_DO_REFERENCE5,
       V_DO_REFERENCE6,
       V_DO_REFERENCE7,
       I_DO_FONDE,
       I_RF_CLE,
       B_DO_ENREGISTREMENT_CONSERVE,
       B_DO_ENREGISTREMENT_NUMERIQUE,
       L_DO_ORIGINE,
       V_DO_CHANGE_PAR,
       D_DO_DATE_CHANGEMENT)
      select L_DO_CLE,
             L_SI_CLE,
             V_DO_NUMERO_DOSSIER,
             I_CA_CLE,
             I_SE_CLE,
             I_CC_CLE,
             I_NH_CLE,
             I_PE_CLE,
             I_ST_CLE,
             V_DO_MOT_PASSE,
             D_DO_DATE_DEBUT,
             D_DO_DATE_FIN,
             V_DO_ANCIENNE_REFERENCE,
             V_DO_REFERENCE_VIDEO,
             V_DO_LIEU,
             I_OR_CLE,
             I_CR_CLE,
             V_DO_REFERENCE1,
             V_DO_REFERENCE2,
             V_DO_ASSIGNE_A,
             D_DO_DATE_ASSIGNATION,
             V_DO_CREE_PAR,
             D_DO_DATE_CREATION,
             V_DO_REFERENCE3,
             V_DO_REFERENCE4,
             V_DO_REFERENCE5,
             V_DO_REFERENCE6,
             V_DO_REFERENCE7,
             I_DO_FONDE,
             I_RF_CLE,
             B_DO_ENREGISTREMENT_CONSERVE,
             B_DO_ENREGISTREMENT_NUMERIQUE,
             L_DO_ORIGINE,
             CARDEX_SPECIAL.g_usager,
             sysdate
        from DO_DOSSIER P
       WHERE (p.l_si_cle = 30 or p.l_si_cle = 11)
         and p.d_do_date_fin > sysdate
         and p.i_cc_cle <> 14920
         and (p.i_se_cle = 14954)
         and exists (select d.l_do_cle
                from v_do_dossier_ca_ty d
               where d.i_ge_cle = 21876
                 and d.l_do_cle = p.l_do_cle);
    --On change ensuite la sévérité        
    update DO_DOSSIER P
       set p.i_se_cle = 14955
     WHERE (p.l_si_cle = 30 or p.l_si_cle = 11)
       and p.d_do_date_fin > sysdate
       and p.i_cc_cle <> 14920
       and (p.i_se_cle = 14954)
       and exists (select d.l_do_cle
              from v_do_dossier_ca_ty d
             where d.i_ge_cle = 21876
               and d.l_do_cle = p.l_do_cle);
    commit;
  end SP_E_DO_SEVERITE_3;

  --Procédure pour mettre à jour les détaillants Cardex de l'entité Loto-Québec
  --à partir des données de RDD (réseau des détaillants)
  procedure SP_U_DETAILLANTS IS
  begin
    MERGE INTO so_societe s
    using (select v1.NO_DETAILLANT,
                  v1.NOM_DETAILLANT || ' ' || v1.NO_SUCC_DV as "NOM_DETAILLANT",
                  v1.NO_CENTREREG,
                  v1.NO_DISTRICT,
                  v1.CODE_CPTE_COC,
                  v1.NOM_CC,
                  v1.REGION_ADM_SECT_DIV_DESC,
                  v1.LANG_ECRIT,
                  v1.STATUT_ANNULATION,
                  v1.DATE_ANNUL_NO_DET,
                  v1.RAISON_ANNULATION
             from vexi_cdx_dds_detaillant v1, so_societe s1
            where s1.v_so_reference_3 = v1.NO_DETAILLANT
              and s1.i_cc_cle <> 14920
              and s1.l_si_cle = 8
              and (convert(upper(s1.v_so_nom), 'US7ASCII') <>
                  convert(upper(v1.NOM_DETAILLANT), 'US7ASCII') || ' ' ||
                  v1.NO_SUCC_DV or
                  nvl(to_char(s1.l_so_centre_regional), ' ') <>
                  v1.NO_CENTREREG or
                  nvl(to_char(s1.v_so_district), ' ') <> v1.NO_DISTRICT or
                  nvl(to_char(s1.v_so_code_compte), ' ') <>
                  v1.CODE_CPTE_COC or
                  nvl(s1.v_so_raison_sociale, ' ') <> v1.NOM_CC or
                  nvl(s1.v_so_reference_1, ' ') <>
                  upper(decode(v1.REGION_ADM_SECT_DIV_DESC,
                                '02 Saguenay-Lac-St-Jean',
                                '02 Saguenay',
                                '12 Chaudière-Appalaches',
                                '12 Appalaches',
                                substr(v1.REGION_ADM_SECT_DIV_DESC, 1, 20))) or
                  s1.i_ls_cle <>
                  decode(convert(upper(v1.LANG_ECRIT), 'US7ASCII'),
                          '',
                          null,
                          'FRANCAIS',
                          410,
                          'ANGLAIS',
                          411,
                          412) or nvl(s1.i_cl_cle, 0) <> 18971 or
                  nvl(s1.b_so_actif, ' ') <>
                  decode(v1.STATUT_ANNULATION, '', 'yes', 'no ') or
                  s1.d_so_date_inactivation <> v1.DATE_ANNUL_NO_DET or
                  nvl(s1.v_so_raison_inactivation, ' ') <>
                  v1.RAISON_ANNULATION)
           union
           select v2.NO_DETAILLANT,
                  v2.NOM_DETAILLANT || ' ' || v2.NO_SUCC_DV as "NOM_DETAILLANT",
                  v2.NO_CENTREREG,
                  v2.NO_DISTRICT,
                  v2.CODE_CPTE_COC,
                  v2.NOM_CC,
                  v2.REGION_ADM_SECT_DIV_DESC,
                  v2.LANG_ECRIT,
                  v2.STATUT_ANNULATION,
                  v2.DATE_ANNUL_NO_DET,
                  v2.RAISON_ANNULATION
             from vexi_cdx_dds_detaillant v2, so_societe s2
            where s2.v_so_reference_3 = v2.NO_DETAILLANT
              and s2.i_cc_cle <> 14920
              and s2.l_si_cle = 8
              and convert(upper(s2.v_so_nom), 'US7ASCII') =
                  convert(upper(v2.NOM_DETAILLANT), 'US7ASCII') || ' ' ||
                  v2.NO_SUCC_DV
              and nvl(to_char(s2.l_so_centre_regional), ' ') =
                  v2.NO_CENTREREG
              and nvl(to_char(s2.v_so_district), ' ') = v2.NO_DISTRICT
              and nvl(to_char(s2.v_so_code_compte), ' ') = v2.CODE_CPTE_COC
              and nvl(s2.v_so_raison_sociale, ' ') = v2.NOM_CC
              and nvl(s2.v_so_reference_1, ' ') =
                  upper(decode(v2.REGION_ADM_SECT_DIV_DESC,
                               '02 Saguenay-Lac-St-Jean',
                               '02 Saguenay',
                               '12 Chaudière-Appalaches',
                               '12 Appalaches',
                               substr(v2.REGION_ADM_SECT_DIV_DESC, 1, 20)))
              and s2.i_ls_cle =
                  decode(convert(upper(v2.LANG_ECRIT), 'US7ASCII'),
                         '',
                         null,
                         'FRANCAIS',
                         410,
                         'ANGLAIS',
                         411,
                         412)
              and nvl(s2.i_cl_cle, 0) = 18971
              and s2.b_so_ind_rdd <> 'yes') src
    
    ON (s.v_so_reference_3 = src.NO_DETAILLANT)
    WHEN MATCHED THEN
      UPDATE
         SET s.v_so_nom                 = substr(src.NOM_DETAILLANT, 1, 49),
             s.l_so_centre_regional     = src.NO_CENTREREG,
             s.v_so_district            = src.NO_DISTRICT,
             s.v_so_code_compte         = src.CODE_CPTE_COC,
             s.v_so_raison_sociale      = src.NOM_CC,
             s.v_so_reference_1         = upper(decode(src.REGION_ADM_SECT_DIV_DESC,
                                                       '02 Saguenay-Lac-St-Jean',
                                                       '02 Saguenay',
                                                       '12 Chaudière-Appalaches',
                                                       '12 Appalaches',
                                                       substr(src.REGION_ADM_SECT_DIV_DESC,
                                                              1,
                                                              20))),
             s.i_ls_cle                 = decode(convert(upper(src.LANG_ECRIT),
                                                         'US7ASCII'),
                                                 '',
                                                 410,
                                                 'FRANCAIS',
                                                 410,
                                                 'ANGLAIS',
                                                 411,
                                                 412),
             s.b_so_actif               = decode(src.STATUT_ANNULATION,
                                                 '',
                                                 'yes',
                                                 'no '),
             s.d_so_date_inactivation   = src.DATE_ANNUL_NO_DET,
             s.v_so_raison_inactivation = src.RAISON_ANNULATION,
             S.B_SO_IND_RDD             = 'yes',
             S.I_CL_CLE                 = 18971; --Détaillant Loto-Québec 
    commit;
  END SP_U_DETAILLANTS;

  --Procédure pour mettre à jour les champs référence nom et prénom des détaillants Cardex de l'entité Loto-Québec
  --à partir des données de RDD (personnes morales du réseau des détaillants)
  procedure SP_U_PERSONNE_MORALE IS
  begin
    MERGE INTO so_societe s
    using (select distinct v.NOM_REPRESENTANT_PM,
                           v.PRENOM_REPRESENTANT_PM,
                           v.NO_DETAILLANT
             from vexi_cdx_dds_detaillant v, so_societe s1
            where s1.v_so_reference_3 = v.NO_DETAILLANT
              and (nvl(s1.v_so_reference_nom, ' ') <> v.NOM_REPRESENTANT_PM or
                  nvl(s1.v_so_reference_prenom, ' ') <>
                  v.PRENOM_REPRESENTANT_PM)
              AND v.NOM_REPRESENTANT_PM IS NOT NULL
              AND v.PRENOM_REPRESENTANT_PM IS NOT NULL
              AND v.NOM_REPRESENTANT_PM IS NOT NULL
              AND v.PRENOM_REPRESENTANT_PM IS NOT NULL
              and length(v.NOM_REPRESENTANT_PM) <= 20
              and length(v.PRENOM_REPRESENTANT_PM) <= 20
              and s1.l_si_cle = 8
              and s1.i_cc_cle <> 14920
              and s1.b_so_ind_rdd = 'yes'
              and v.MOR_DATE_DEBUT_ACTV_RP || v.NOM_REPRESENTANT_PM =
                  (select max(v2.MOR_DATE_DEBUT_ACTV_RP ||
                              v2.NOM_REPRESENTANT_PM)
                     from vexi_cdx_dds_detaillant v2
                    where v.NO_DETAILLANT = v2.NO_DETAILLANT)) src
    
    ON (s.v_so_reference_3 = src.NO_DETAILLANT)
    WHEN MATCHED THEN
      UPDATE
         SET s.v_so_reference_nom    = src.NOM_REPRESENTANT_PM,
             s.v_so_reference_prenom = src.PRENOM_REPRESENTANT_PM;
    commit;
  END SP_U_PERSONNE_MORALE;

  --Procédure pour créer des sujets provenant de RDD (personne physique)
  procedure SP_E_SU_SUJET_DETAILLANTS(NEW_L_SU_CLE            IN OUT SU_SUJET.L_SU_CLE%TYPE,
                                      NEW_L_SI_CLE            IN OUT SU_SUJET.L_SI_CLE%TYPE,
                                      NEW_V_SU_REFERENCE_3    IN OUT SU_SUJET.V_SU_REFERENCE_3%TYPE,
                                      NEW_V_SU_NOM            IN SU_SUJET.V_SU_NOM%TYPE,
                                      NEW_V_SU_PRENOM         IN SU_SUJET.V_SU_PRENOM%TYPE,
                                      NEW_L_SU_TYPE_AGE       IN SU_SUJET.L_SU_TYPE_AGE%TYPE,
                                      NEW_I_CC_CLE            IN SU_SUJET.I_CC_CLE%TYPE,
                                      NEW_I_SE_CLE            IN SE_SEVERITE.I_SE_CLE%TYPE,
                                      NEW_I_SE_CLE_AUTRES     IN SE_SEVERITE.I_SE_CLE%TYPE,
                                      NEW_B_SU_IND_RDD        IN SU_SUJET.B_SU_IND_RDD%TYPE) IS
  begin
    select seq_sujet.nextval into NEW_L_SU_CLE from dual;
    if NEW_V_SU_REFERENCE_3 =
       '                                                 *' then
      NEW_V_SU_REFERENCE_3 := cardex_special.f_no_sujet(NEW_L_SU_CLE,
                                                        NEW_L_SI_CLE,
                                                        SYSDATE);
    end if;
  
    insert into SU_SUJET
      (L_SU_CLE,
       L_SI_CLE,
       V_SU_NOM,
       V_SU_PRENOM,
       L_SU_TYPE_AGE,
       V_SU_REFERENCE_3,
       I_CC_CLE,
       V_SU_CREE_PAR,
       D_SU_DATE_CREATION,
       C_SU_SNDX_NOM,
       C_SU_SNDX_PRENOM,
       I_SE_CLE,
       I_SE_CLE_AUTRES,
       B_SU_IND_RDD)
    values
      (NEW_L_SU_CLE,
       NEW_L_SI_CLE,
       NEW_V_SU_NOM,
       NEW_V_SU_PRENOM,
       NEW_L_SU_TYPE_AGE,
       NEW_V_SU_REFERENCE_3,
       NEW_I_CC_CLE,
       CARDEX_SPECIAL.g_usager,
       sysdate,
       soundex(NEW_V_SU_NOM),
       soundex(NEW_V_SU_PRENOM),
       NEW_I_SE_CLE,
       NEW_I_SE_CLE_AUTRES,
       NEW_B_SU_IND_RDD);
    commit;
  END SP_E_SU_SUJET_DETAILLANTS;

  --Mise à jour de la sévérité Casino des sujets
  procedure SP_E_SU_SEVERITE_CASINO(NEW_L_SU_CLE IN SU_SUJET.L_SU_CLE%TYPE,
                                    NEW_L_SI_CLE IN SU_SUJET.L_SI_CLE%TYPE,
                                    NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE) IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SU_SUJET
      (L_SU_CLE,
       L_SI_CLE,
       I_SX_CLE,
       I_ST_CLE,
       I_NT_CLE,
       I_RA_CLE,
       I_LS_CLE,
       V_SU_NOM,
       V_SU_PRENOM,
       V_SU_SURNOM,
       D_SU_DATE_NAISSANCE,
       C_SU_ASSURANCE_SOCIALE,
       V_SU_PERMIS_CONDUIRE,
       I_CC_CLE,
       V_SU_MOT_PASSE,
       V_SU_REFERENCE_1,
       V_SU_REFERENCE_2,
       V_SU_REFERENCE_3,
       V_SU_CREE_PAR,
       D_SU_DATE_CREATION,
       C_SU_SNDX_NOM,
       C_SU_SNDX_PRENOM,
       C_SU_SNDX_SURNOM,
       I_SE_CLE,
       V_SU_NO_PASSEPORT,
       L_SU_TYPE_AGE,
       V_SU_ASSURANCE_MALADIE,
       I_SE_CLE_AUTRES,
       V_SU_CHANGE_PAR,
       D_SU_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SU_CLE,
             L_SI_CLE,
             I_SX_CLE,
             I_ST_CLE,
             I_NT_CLE,
             I_RA_CLE,
             I_LS_CLE,
             V_SU_NOM,
             V_SU_PRENOM,
             V_SU_SURNOM,
             D_SU_DATE_NAISSANCE,
             C_SU_ASSURANCE_SOCIALE,
             V_SU_PERMIS_CONDUIRE,
             I_CC_CLE,
             V_SU_MOT_PASSE,
             V_SU_REFERENCE_1,
             V_SU_REFERENCE_2,
             V_SU_REFERENCE_3,
             V_SU_CREE_PAR,
             D_SU_DATE_CREATION,
             C_SU_SNDX_NOM,
             C_SU_SNDX_PRENOM,
             C_SU_SNDX_SURNOM,
             I_SE_CLE,
             V_SU_NO_PASSEPORT,
             L_SU_TYPE_AGE,
             V_SU_ASSURANCE_MALADIE,
             I_SE_CLE_AUTRES,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SU_SUJET S
       where s.l_su_cle = NEW_L_SU_CLE
         AND S.L_SI_CLE = NEW_L_SI_CLE;
    -- On inscrit ensuite la sévérité
    update su_sujet s
       set s.i_se_cle_casino = NEW_L_SE_CLE
     where s.l_su_cle = NEW_L_SU_CLE
       AND S.L_SI_CLE = NEW_L_SI_CLE;
    commit;
  END SP_E_SU_SEVERITE_CASINO;

  --Mise à jour de la sévérité Investigation Autre des sujets
  procedure SP_E_SU_SEVERITE_AUTRE(NEW_L_SU_CLE IN SU_SUJET.L_SU_CLE%TYPE,
                                   NEW_L_SI_CLE IN SU_SUJET.L_SI_CLE%TYPE,
                                   NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE) IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SU_SUJET
      (L_SU_CLE,
       L_SI_CLE,
       I_SX_CLE,
       I_ST_CLE,
       I_NT_CLE,
       I_RA_CLE,
       I_LS_CLE,
       V_SU_NOM,
       V_SU_PRENOM,
       V_SU_SURNOM,
       D_SU_DATE_NAISSANCE,
       C_SU_ASSURANCE_SOCIALE,
       V_SU_PERMIS_CONDUIRE,
       I_CC_CLE,
       V_SU_MOT_PASSE,
       V_SU_REFERENCE_1,
       V_SU_REFERENCE_2,
       V_SU_REFERENCE_3,
       V_SU_CREE_PAR,
       D_SU_DATE_CREATION,
       C_SU_SNDX_NOM,
       C_SU_SNDX_PRENOM,
       C_SU_SNDX_SURNOM,
       I_SE_CLE,
       V_SU_NO_PASSEPORT,
       L_SU_TYPE_AGE,
       V_SU_ASSURANCE_MALADIE,
       I_SE_CLE_AUTRES,
       V_SU_CHANGE_PAR,
       D_SU_DATE_CHANGEMENT,
       I_SE_CLE_CASINO)
      select L_SU_CLE,
             L_SI_CLE,
             I_SX_CLE,
             I_ST_CLE,
             I_NT_CLE,
             I_RA_CLE,
             I_LS_CLE,
             V_SU_NOM,
             V_SU_PRENOM,
             V_SU_SURNOM,
             D_SU_DATE_NAISSANCE,
             C_SU_ASSURANCE_SOCIALE,
             V_SU_PERMIS_CONDUIRE,
             I_CC_CLE,
             V_SU_MOT_PASSE,
             V_SU_REFERENCE_1,
             V_SU_REFERENCE_2,
             V_SU_REFERENCE_3,
             V_SU_CREE_PAR,
             D_SU_DATE_CREATION,
             C_SU_SNDX_NOM,
             C_SU_SNDX_PRENOM,
             C_SU_SNDX_SURNOM,
             I_SE_CLE,
             V_SU_NO_PASSEPORT,
             L_SU_TYPE_AGE,
             V_SU_ASSURANCE_MALADIE,
             I_SE_CLE_AUTRES,
             CARDEX_SPECIAL.g_usager,
             sysdate,
             I_SE_CLE_CASINO
        from SU_SUJET S
       where s.l_su_cle = NEW_L_SU_CLE
         AND S.L_SI_CLE = NEW_L_SI_CLE;
    -- On inscrit ensuite la sévérité
    update su_sujet s
       set s.i_se_cle = NEW_L_SE_CLE
     where s.l_su_cle = NEW_L_SU_CLE
       AND S.L_SI_CLE = NEW_L_SI_CLE;
    commit;
  END SP_E_SU_SEVERITE_AUTRE;

  --Mise-à-jour de la sévérité Casino des sociétés d'investigation
  --Différé CDX_00008
  procedure SP_E_SO_SEVERITE_CASINO(NEW_L_SO_CLE IN SO_SOCIETE.L_SO_CLE%TYPE,
                                    NEW_L_SI_CLE IN SO_SOCIETE.L_SI_CLE%TYPE,
                                    NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE) IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SO_SOCIETE
      (L_SO_CLE,
       L_SI_CLE,
       I_ST_CLE,
       I_LS_CLE,
       V_SO_RAISON_SOCIALE,
       V_SO_NOM,
       I_CL_CLE,
       D_SO_DATE_FONDATION,
       I_CC_CLE,
       V_SO_MOT_PASSE,
       V_SO_REFERENCE_NOM,
       V_SO_REFERENCE_PRENOM,
       V_SO_REFERENCE_1,
       V_SO_REFERENCE_2,
       V_SO_REFERENCE_3,
       V_SO_CREE_PAR,
       D_SO_DATE_CREATION,
       C_SO_SNDX_RAISON_SOCIALE,
       C_SO_SNDX_NOM,
       I_SE_CLE,
       V_SO_CHANGE_PAR,
       D_SO_DATE_CHANGEMENT)
      select L_SO_CLE,
             L_SI_CLE,
             I_ST_CLE,
             I_LS_CLE,
             V_SO_RAISON_SOCIALE,
             V_SO_NOM,
             I_CL_CLE,
             D_SO_DATE_FONDATION,
             I_CC_CLE,
             V_SO_MOT_PASSE,
             V_SO_REFERENCE_NOM,
             V_SO_REFERENCE_PRENOM,
             V_SO_REFERENCE_1,
             V_SO_REFERENCE_2,
             V_SO_REFERENCE_3,
             V_SO_CREE_PAR,
             D_SO_DATE_CREATION,
             C_SO_SNDX_RAISON_SOCIALE,
             C_SO_SNDX_NOM,
             I_SE_CLE,
             CARDEX_SPECIAL.g_usager,
             sysdate
        from SO_SOCIETE S
       where s.l_so_cle = NEW_L_SO_CLE
         AND S.L_SI_CLE = NEW_L_SI_CLE;
  
    --On inscrit ensuite la sévérité
    update so_societe s
       set s.i_se_cle_casino = NEW_L_SE_CLE
     where s.l_so_cle = NEW_L_SO_CLE
       AND S.L_SI_CLE = NEW_L_SI_CLE;
    commit;
  END SP_E_SO_SEVERITE_CASINO;

  --Mise-à-jour de la sévérité Investigation autre des sociétés
  --Différé CDX_00008
  procedure SP_E_SO_SEVERITE_AUTRE(NEW_L_SO_CLE IN SO_SOCIETE.L_SO_CLE%TYPE,
                                   NEW_L_SI_CLE IN SO_SOCIETE.L_SI_CLE%TYPE,
                                   NEW_L_SE_CLE IN SE_SEVERITE.I_SE_CLE%TYPE) IS
  begin
    --On inscrit d'abord dans l'audit
    insert into AUD_SO_SOCIETE
      (L_SO_CLE,
       L_SI_CLE,
       I_ST_CLE,
       I_LS_CLE,
       V_SO_RAISON_SOCIALE,
       V_SO_NOM,
       I_CL_CLE,
       D_SO_DATE_FONDATION,
       I_CC_CLE,
       V_SO_MOT_PASSE,
       V_SO_REFERENCE_NOM,
       V_SO_REFERENCE_PRENOM,
       V_SO_REFERENCE_1,
       V_SO_REFERENCE_2,
       V_SO_REFERENCE_3,
       V_SO_CREE_PAR,
       D_SO_DATE_CREATION,
       C_SO_SNDX_RAISON_SOCIALE,
       C_SO_SNDX_NOM,
       I_SE_CLE,
       V_SO_CHANGE_PAR,
       D_SO_DATE_CHANGEMENT)
      select L_SO_CLE,
             L_SI_CLE,
             I_ST_CLE,
             I_LS_CLE,
             V_SO_RAISON_SOCIALE,
             V_SO_NOM,
             I_CL_CLE,
             D_SO_DATE_FONDATION,
             I_CC_CLE,
             V_SO_MOT_PASSE,
             V_SO_REFERENCE_NOM,
             V_SO_REFERENCE_PRENOM,
             V_SO_REFERENCE_1,
             V_SO_REFERENCE_2,
             V_SO_REFERENCE_3,
             V_SO_CREE_PAR,
             D_SO_DATE_CREATION,
             C_SO_SNDX_RAISON_SOCIALE,
             C_SO_SNDX_NOM,
             I_SE_CLE,
             CARDEX_SPECIAL.g_usager,
             sysdate
        from SO_SOCIETE S
       where s.l_so_cle = NEW_L_SO_CLE
         AND S.L_SI_CLE = NEW_L_SI_CLE;
  
    update so_societe s
       set s.i_se_cle = NEW_L_SE_CLE
     where s.l_so_cle = NEW_L_SO_CLE
       AND S.L_SI_CLE = NEW_L_SI_CLE;
    commit;
  END SP_E_SO_SEVERITE_AUTRE;

END CARDEX_DOC;
/
