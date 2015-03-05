/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.securite;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.Business;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;


public class GestionnaireSecurite {
    private static final Logger log = (Logger)LoggerCardex.getLogger(GestionnaireSecurite.class);
    private static final List<String> actionsSecuriteList = new ArrayList<String>();
    
    static{
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.AJOUT);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.AUTRES);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.CONSULTER_DOSSIER);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.CONSULTER_GALERIE);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.CONSULTER_NARRATION);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.MODIFICATION);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.RECHERCHE);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.RECHERCHE_GALERIE);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.RECHERCHE_PILOTAGE);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.SELECTION);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.SUPPRESSION);
    	actionsSecuriteList.add(GlobalConstants.ActionSecurite.TOUTES_ACTIONS);
    }
    
    private static class MethodeValeur{
    	public Method method;
    	public Object valeur;
    }
    
    /**
     * La methode obtenirURLUIComponentState est responsable de gérer
     * les accès aux composants visuels (authorisation)
     * des interfaces utilisateur du cardex.  Dépendemment des
     * rôles contenu dans les tables de pilotage du cardex,
     * un compossant peut être présenté à un utilisateur sous
     * trois état :
     * <lu>
     * <li>Actif (Enabled) </li>
     * <li>Desactif (Disabled) </li>
     * <li>Invisible(Hidden) </li>
     * </lu>
     */
    public static UIComponentState obtenirURLUIComponentState(CardexAuthenticationSubject subject, String url) {
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	
    	if (".do".equals(StringUtils.right(url, 3)))
    		url = StringUtils.replace(url, ".do", "");
    	log.fine("Valider accès du url '" + url + "'");
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();

    	RoleCache roleCache = rolesCacheSecuriteCache.obtenirRoleDeURL(url);
    	
    	if (roleCache == null){
    		log.warning("Rôle de l'url : \""+url+"\" n'a pas été créé.");
    		//rolesCacheSecuriteCache.statut();
    		return UIComponentState.ENABLED;
    	}
    	
    	if (roleCache.isAdministrer()){
	    	String role = roleCache.getRole();
	        
	    	// Aucun rôle signifie que cet URL n'est pas administré
	        if (role == null || role.trim().length() == 0 )
	        	return UIComponentState.ENABLED;
	
	        if (userPrivilege.hasRole(role))
	        	return UIComponentState.ENABLED;
	        else
	    		return UIComponentState.HIDDEN;
    	}
    	return UIComponentState.ENABLED;
    }

    /*
	 * Le but de cette methode est d'avoir en session de l'utilisateur un object 
	 * raccourcitGestionSecurit pour s'assurer qu'il a accès a consulter une
	 * entité Cardex (ex: dossier, sujet, ..) sans avoir à le reconstruire. 
     */
    public static UIComponentState obtenirURLUIComponentState(CardexAuthenticationSubject subject, HttpSession session, Object vo, List<SecuritePredicate> securitePredicates, String... actionsSecurite) {
    	Map<Class, Map<String, RaccourcitGestionAccesSecurite>> raccourcitGestionAccesSecuriteClassMap = (Map<Class, Map<String, RaccourcitGestionAccesSecurite>>) session.getAttribute(GlobalConstants.Securite.SESSION_RACCOURCIT_GESTION_ACCES_SECURITE);
    	
    	if (raccourcitGestionAccesSecuriteClassMap == null){
    		raccourcitGestionAccesSecuriteClassMap = new HashMap<Class, Map<String, RaccourcitGestionAccesSecurite>>();
    		session.setAttribute(GlobalConstants.Securite.SESSION_RACCOURCIT_GESTION_ACCES_SECURITE, raccourcitGestionAccesSecuriteClassMap);
    	}
    	Map<String, RaccourcitGestionAccesSecurite> raccourcitGestionAccesSecuriteMap = (Map<String, RaccourcitGestionAccesSecurite>) raccourcitGestionAccesSecuriteClassMap.get(vo.getClass());
    	
    	if (raccourcitGestionAccesSecuriteMap == null){
    		raccourcitGestionAccesSecuriteMap =  construireRaccourcitGestionAccesSecurite(subject, new HashMap<String, RaccourcitGestionAccesSecurite>(), vo);
    		raccourcitGestionAccesSecuriteClassMap.put(vo.getClass(), raccourcitGestionAccesSecuriteMap);
    	}
    	
    	if (validerEtFiltrerSecuriteRechercher(subject, raccourcitGestionAccesSecuriteMap, vo, securitePredicates, actionsSecurite))
        	return UIComponentState.ENABLED;
        else
    		return UIComponentState.HIDDEN;
    }

    public static void validerSecuriteURL(CardexAuthenticationSubject subject, String url) {
    	
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	log.fine("Valider accès du url '" + url + "'");
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();

    	RoleCache roleCache = rolesCacheSecuriteCache.obtenirRoleDeURL(url);
    	
    	if (roleCache == null){
    		log.warning("Rôle de l'url : \""+url+"\" n'a pas été créé.");
    		System.err.println("Rôle de l'url : \""+url+"\" n'a pas été créé.");
    		//rolesCacheSecuriteCache.statut();
    		return;
    	}
    	
    	if (roleCache.isAdministrer()){
	    	String role = roleCache.getRole();
	        
	    	// Aucun rôle signifie que cet URL n'est pas administré
	        if (StringUtils.isNotEmpty(role)
	        && userPrivilege.hasRole(role) == false){
	        	String message = "URL "+url;
				genererErreurSecurite(subject, role, message);        	
	       	}
    	}
    }

	public static void genererErreurSecurite(CardexAuthenticationSubject subject, String role, String finMessage) {
    	CardexUser cardexUser = (CardexUser)subject.getUser();
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	
    	if (userPrivilege.isRolesEmpty())
    		throw new SecurityException("L'usager "+cardexUser.getCode()+" n'a aucun rôle "); 
    	
    	String message = "L'usager "+cardexUser.getCode()+" n'a pas le rôle '"+role+"' qui lui permet d'accéder à "+finMessage;
    	
		try {
			CourrielAction.envoyerCourrielDestinataire(subject, GlobalConstants.Securite.ERREUR_COURRIEL, message, GlobalConstants.TypesIntervention.ERREUR_SECURITE, "");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		throw new SecurityException(message);
	}

    /**
     * Valide si un rôle Adhoc a été créer.
     * Si, le rôle n'a pas été créer, on laisse passer, mais on fait un message.
     * @param subject
     * @param role
     * @return
     */
    public static UIComponentState obtenirAdhocUIComponentState(CardexAuthenticationSubject subject, String role) {
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
        log.fine("Valider accès au bouton. role : '" + role + "'");
    	
    	// Aucun rôle signifie que ce bouton n'est pas administré
        if (role == null || role.trim().length() == 0 )
        	return UIComponentState.ENABLED;

        RoleCache roleCache = rolesCacheSecuriteCache.get(role);
        
        if (roleCache != null){
        	
        	if (roleCache.isAdministrer())
        		return userPrivilege.hasUIRole(role);
        }else{
        	log.warning("Rôle : \""+role+"\" n'a pas été créé.");
        }
        return UIComponentState.ENABLED;
    }

    /**
     * Valider que le rôle adhoc est bien géré
     * @param subject
     * @param role
     */
    public static void validerSecuriteAdhoc(CardexAuthenticationSubject subject, String role) {
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
        log.fine("Valider accès au bouton. role : '" + role + "'");
    	
        // Aucun rôle adhoc ne peut être vide
        if (role == null || role.trim().length() == 0 )
        	throw new RuntimeException("Rôle adhoc vide dans la sécurité");
        
        RoleCache roleCache = rolesCacheSecuriteCache.get(role);
        
        if (roleCache != null){
        	
        	if (roleCache.isAdministrer()
        	&& userPrivilege.hasRole(role) == false){
	        	String message = "rôle adhoc "+role;
				genererErreurSecurite(subject, role, message);
        	}
        }else{
        	log.warning("Rôle : \""+role+"\" n'a pas été créé.");
        }
    }
    
    /*
     * Retourne true si le rôle est contenu dans la liste des rôles de l'utilisateur
     */
    public static boolean isRoleAdhoc(CardexAuthenticationSubject subject, String role) {
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
        log.fine("Valider accès au bouton. role : '" + role + "'");
    	
    	// Aucun rôle signifie que ce bouton n'est pas administré
        if (role == null || role.trim().length() == 0 )
        	return true;

        RoleCache roleCache = rolesCacheSecuriteCache.get(role);
        
        if (roleCache != null){
        	
        	if (roleCache.isAdministrer())
        			return userPrivilege.hasRole(role);
        }else{
        	log.warning("Rôle : \""+role+"\" n'a pas été créé.");
        }
        return true;
    }
    
    public static UIComponentState obtenirFormulaireUIComponentState(CardexAuthenticationSubject subject, Class classFormulaire, String methode) {
    	return obtenirFormulaireUIComponentState(subject, StringUtils.right(classFormulaire.toString(), classFormulaire.toString().length()-6), methode);
    }
    
    public static UIComponentState obtenirFormulaireUIComponentState(CardexAuthenticationSubject subject, String classFormulaire, String methode) {
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	
    	// Aucun formulaire
    	if (StringUtils.isEmpty(classFormulaire) || methode == null)
    		return UIComponentState.ENABLED;
    	String classeMethode = construireClasseMethode(classFormulaire, methode);
        log.fine("Valider accès du formulaire classe et methode '" + classeMethode + "'");
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();

    	RoleCache roleCache = rolesCacheSecuriteCache.obtenirRoleDeFormulaireMethode(classeMethode);
    	
    	if (roleCache == null){
    		// Il est possible d'avoir des champs seulement présent en présentation.
    		// Dans ce cas, pas de sécurité.  C'est normal de ne pas avoir ce sécurité
    		// pour ce cas en présentation, car il n'y a pas de sécurité côté affaire.
    		//log.warning("Rôle de la classe : \""+classeMethode+"\" n'a pas été créé.");
    		return UIComponentState.ENABLED;
    	}
    	
    	if (roleCache.isAdministrer()){
	    	String role = roleCache.getRole();    	
	    	
	    	// Aucun rôle signifie que ce formulaire n'est pas administré
	        if (role == null || role.trim().length() == 0 )
	        	return UIComponentState.ENABLED;
	
	        return userPrivilege.hasUIRole(role);
    	}
    	return UIComponentState.ENABLED;
    }
    
    public static void validerSecuriteEntreeUtilisateur(CardexAuthenticationSubject subject, Object vo, String... actionSecurite) {
        log.fine("Valider accès du VO '" + vo.getClass().getName() + "'");
		Field[] fields = vo.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				String valeur = getValeur(vo, field);
				
				if (validerAccesSecurite(subject, vo, field.getName(), valeur, actionSecurite) == false){
					String classeMethode = construireClasseMethode(vo, field.getName());
					String role = obtenirRole(subject, vo, field.getName(), valeur, actionSecurite);
					String message = "'"+classeMethode+"' avec la valeur "+valeur;
					genererErreurSecurite(subject, role, message);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
    }
    
    /*
     * Les boolean sont un cas particulier, à l'écran si l'utilisateur ne met rien
     * il va quand même avoir false dans la valeur.  Pour cela, il faut retirer les
     * valeurs des booleans que l'utilisateur n'avait pas le droit de modfiier.
     * Cependant, il ne faut pas appliquer cette méthode à la racine de la sécurité.
     * Sinon, les cases à coché ne serait plus sécurisé.
     */
    public static void assignerBooleanNull(CardexAuthenticationSubject subject, Object vo, String... actionSecurite) {
        log.fine("Valider accès du VO '" + vo.getClass().getName() + "'");
		Field[] fields = vo.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				String valeur = getValeur(vo, field);
				
				if (field.getType().isAssignableFrom(Boolean.class) 
				&& validerAccesSecurite(subject, vo, field.getName(), valeur, actionSecurite) == false){
					Method methodSet =  vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), Boolean.class);
					Object[] setValeur = {null};
					methodSet.invoke(vo, setValeur);				
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
    }    
    
    private static String obtenirRole(CardexAuthenticationSubject subject, Object vo, String methode, String valeur, String... actionsSecurite){
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	RoleCache roleCache = obtenirRoleCacheDeVO(vo, methode);
    	
        if (userPrivilege.hasRoleActifouInactif(roleCache.getRole())){
        
        	// ici c'est sur qu'il y a un code de liste
        	if (StringUtils.isNotEmpty(roleCache.getCodeListe())){
        		ListeCache listeCache = ListeCache.getInstance();
        		StringBuilder role = new StringBuilder();
        		try {
					for (String actionSecurite:actionsSecurite){
						
						if (role.length() > 0)
							role.append(", ");
						
						role.append( listeCache.obtenirRole(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), actionSecurite), valeur) );
					}
					
				} catch (BusinessResourceException e) {
					e.printStackTrace();
				} 
				return role.toString();
			}else
				throw new RuntimeException("Il devrait y avoir un code de liste pour le rôle "+roleCache.getRole());
        }else
        	return roleCache.getRole();    	
    }

    public static synchronized void validerValeurAccessible(CardexAuthenticationSubject subject, TableValeurCleSQLListeCache cleListe, String valeur) {
    	ListeCache listeCache = ListeCache.getInstance();

    	try {
			if (listeCache.isValeurAccessible(subject, cleListe, valeur) == false){
				String message = "la valeur "+valeur;
				String role = listeCache.obtenirRole(subject, cleListe, valeur);
				genererErreurSecurite(subject, role, message);				
			}
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Return true, s'il y a eu modification à l'object cible
     * @param source
     * @param cible
     * @return
     */
    public static boolean isModificiation(Object source, Object cible){
		Field[] fieldsSource = source.getClass().getDeclaredFields();
		Field[] fieldsCible = cible.getClass().getDeclaredFields();
		
		for (int i = 0; i < fieldsSource.length; i++) {
			Field fieldSource = fieldsSource[i];
			Field fieldCible = fieldsCible[i];
			
			// Nous n'allons pas voir le contenu des collections
			if (fieldSource.getType().isAssignableFrom(Collection.class))
				continue;
			
			try {
				String valeurSource = getValeur(source, fieldSource);
				String valeurCible = getValeur(cible, fieldCible);
				
				if (valeurSource.equals(valeurCible) == false){
					return true;
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return false;    	
    }
    
    /*
     * La modification est un cas particulier, car il faut :
     * 1.	Valider que l’utilisateur peut consulter l’enregistrement qu’il modifie
     * 2.	Valider la source des données 
     * 3.	Valider les données de la cible de la modification.  
     * 
     * Par exemple, un utilisateur tente de modifier un dossier, mais ne possède 
     * pas la confidentialité nécessaire.  Il connait l’URL envoie la modification 
     * directement.  L’application valide les données fournies et constate qu’il ne 
     * possède pas le rôle de confidentialité nécessaire.  L’utilisateur ressaye 
     * de modifier le dossier en passant la bonne clé et site, mais pas les bons 
     * renseignements dans les données.  Lors de la validation des données envoyées 
     * par l’utilisateur, la validation constate que les données sources sont en règle.  
     * Le système doit retrouver les données du dossier cible pour constater que 
     * l’utilisateur n’a pas le droit de consulter ce dossier.  Car il ne possède pas 
     * le rôle de confidentialité nécessaire.
     */
    public static void validerSecuriteModificationSansSecuritePredicate(CardexAuthenticationSubject subject, Object source, Object cible) {
    	validerSecuriteModification(subject, source, cible, null);
    }        
    
    protected static void validerSecuriteModification(CardexAuthenticationSubject subject, Object source, Object cible, List<SecuritePredicate> securitePredicates) {
    	validerSecuriteModification(subject, source, cible, securitePredicates, GlobalConstants.ActionSecurite.SELECTION);
    }
    
    protected static void validerSecuriteModification(CardexAuthenticationSubject subject, Object source, Object cible, List<SecuritePredicate> securitePredicates, String...actionsSecuriteConsultation) {
        log.fine("Valider accès du VO '" + source.getClass().getName() + "'");
        ListeCache listeCache = ListeCache.getInstance();
        
        if (securitePredicates != null)
	        for (SecuritePredicate securitePredicate:securitePredicates)
	        	if (securitePredicate.validerSecurite(subject, source))
	        		return;
        
		Field[] fieldsSource = source.getClass().getDeclaredFields();
		Field[] fieldsCible = cible.getClass().getDeclaredFields();
		
		for (int i = 0; i < fieldsSource.length; i++) {
			Field fieldSource = fieldsSource[i];
			Field fieldCible = fieldsCible[i];
			RoleCache roleCache = obtenirRoleCacheDeVO(cible, fieldCible.getName());
			
			if (roleCache == null)
				continue;
			
			try {
				String valeurSource = getValeur(source, fieldSource);
				String valeurCible = getValeur(cible, fieldCible);

				// On ne peut pas modifier un object qu'on ne peut pas consulter.
				// et que le champs est obligatoire
				if (StringUtils.isNotEmpty(roleCache.getCodeListe())
				&& listeCache.isObligatoire(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), "", GlobalConstants.ActionSecurite.MODIFICATION))){
					boolean accesConsultationValide = false;
					
	        		// Erreur de consultation
					for (String actionSecurite:actionsSecuriteConsultation){
						
						if (validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, actionSecurite)){
							accesConsultationValide = true;
							break;
						}
					}
					
	        		if (accesConsultationValide == false){
	        			StringBuilder roles = new StringBuilder();
						for (String actionSecurite:actionsSecuriteConsultation){
							
							if (validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, actionSecurite) == false){
								roles.append( listeCache.obtenirRole(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), "", actionSecurite), valeurCible) );
							}
						}
	        			
	        			String classeMethode = construireClasseMethode(cible, fieldCible.getName());
						String message = "'"+classeMethode+"' avec la valeur "+valeurCible;
						genererErreurSecurite(subject, roles.toString(), message);	
	        		}
					
	        		// Erreur de modification
	        		/*if(validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, GlobalConstants.ActionSecurite.MODIFICATION) == false){
						String classeMethode = construireClasseMethode(cible, fieldCible.getName());
						String message = "'"+classeMethode+"' avec la valeur "+valeurCible;
						String role = listeCache.obtenirRole(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), "", GlobalConstants.ActionSecurite.MODIFICATION), valeurCible);
						genererErreurSecurite(subject, role, message);
					}*/
	        	}
				
				if (valeurSource.equals(valeurCible) == false){
					/*
					 * Si la valeur de la source n'est pas vide, mais que la 
					 * valeur de la cible l'est.  Il est possible que la valeur 
					 * de la cible soit vide car elle a été retiré pour des raisons
					 * de sécurité.  Il faut, dans ce cas remettre la valeur.
					 */
					if (StringUtils.isNotEmpty(valeurSource) && StringUtils.isEmpty(valeurCible) 
					&& validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, GlobalConstants.ActionSecurite.MODIFICATION) == false){
						Method methodSet =  cible.getClass().getDeclaredMethod("set"+StringUtils.capitalise(fieldCible.getName()), fieldCible.getType());
						Object[] setValeur = {valeurSource};
						methodSet.invoke(cible, setValeur);
						continue;
					}
					
					if (roleCache != null && StringUtils.isNotEmpty(roleCache.getCodeListe()) 
					&& "0".equals(valeurSource) == false && "0".equals(valeurCible)
					&& validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, GlobalConstants.ActionSecurite.MODIFICATION) == false){
						Method methodSet =  cible.getClass().getDeclaredMethod("set"+StringUtils.capitalise(fieldCible.getName()), fieldCible.getType());
						Object[] setValeur = {Long.valueOf(valeurSource)};
						methodSet.invoke(cible, setValeur);
						continue;
					}
					
					if (validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, GlobalConstants.ActionSecurite.MODIFICATION) == false
					|| validerAccesSecurite(subject, cible, fieldCible.getName(), valeurCible, GlobalConstants.ActionSecurite.MODIFICATION) == false){
						String classeMethode = construireClasseMethode(source, fieldSource.getName());
						String message = classeMethode+" avec la valeur "+valeurCible;
						String role = "";

						if (validerAccesSecurite(subject, source, fieldSource.getName(), valeurSource, GlobalConstants.ActionSecurite.MODIFICATION) == false)
							role = obtenirRole(subject, source, fieldSource.getName(), valeurSource, GlobalConstants.ActionSecurite.MODIFICATION);
						else
							role = obtenirRole(subject, cible, fieldCible.getName(), valeurCible, GlobalConstants.ActionSecurite.MODIFICATION);
						genererErreurSecurite(subject, role, message);					
					}
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (BusinessResourceException e) {
				e.printStackTrace();
			}
		}
    }

    /**
     * Si le champs ne répond pas à la sécurité, il faut vider le champ 
     * @param subject
     * @param vo
     */
    public static void validerEtFiltrerSecuriteConsulter(CardexAuthenticationSubject subject, Object vo) {
    	validerEtFiltrerSecuriteConsulter(subject, vo, null);
    }
    
    /**
     * Si le champs ne répond pas à la sécurité, il faut vider le champ 
     * @param subject
     * @param vo
     */
    protected static void validerEtFiltrerSecuriteConsulter(CardexAuthenticationSubject subject, Object vo, List<SecuritePredicate> securitePredicates) {
    	validerEtFiltrerSecurite(subject, vo, securitePredicates, GlobalConstants.ActionSecurite.SELECTION);
    }
    /**
     * Il y a pluiseurs actions de consultation, il faut passer la bonne en paramètre
     * @param subject
     * @param vo
     * @param securitePredicates
     * @param actionsSecurite
     */
    protected static void validerEtFiltrerSecurite(CardexAuthenticationSubject subject, Object vo, List<SecuritePredicate> securitePredicates,  String...actionsSecurite) {
        log.fine("Valider accès du VO '" + vo.getClass().getName() + "'");
        ListeCache listeCache = ListeCache.getInstance();
        
        if (securitePredicates != null)
	        for (SecuritePredicate securitePredicate:securitePredicates)
	        	if (securitePredicate.validerSecurite(subject, vo))
	        		return;
        
		Field[] fields = vo.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				String valeur = getValeur(vo, field);
				RoleCache roleCache = obtenirRoleCacheDeVO(vo, field.getName());
				
				if (roleCache == null)
					continue;
				
				// Si le champs n'est pas vide et que l'utilisateur n'a pas le 
				// droit de le voir, il faut vider le champ.
				if (validerAccesSecurite(subject, vo, field.getName(), valeur, actionsSecurite) == false){
					CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
					
					if (userPrivilege.hasRoleActifouInactif(roleCache.getRole())
		        	&& StringUtils.isNotEmpty(roleCache.getCodeListe())){
		        		ListeCache cache = ListeCache.getInstance();
		        		
						for (String actionSecurite:actionsSecurite){
							
							if ( cache.isObligatoire(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), actionSecurite)) ){
								String message = "'"+construireClasseMethode(vo,field.getName())+"' avec la valeur "+valeur;
								String role = listeCache.obtenirRole(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), "", actionSecurite), valeur);
								genererErreurSecurite(subject, role, message);								
							}
						}
		        	}
					
					MethodeValeur methodeValeur = obtenirMethodeValeur(field, vo);
					Object[] setValeur = {methodeValeur.valeur};
					methodeValeur.method.invoke(vo, setValeur);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (BusinessResourceException e) {
				e.printStackTrace();
			}
		}
    }
    
    private static MethodeValeur obtenirMethodeValeur(Field field, Object vo) throws SecurityException, NoSuchMethodException{
    	MethodeValeur methodeValeur = new GestionnaireSecurite.MethodeValeur();
    	
        if (field.getType().isAssignableFrom(Integer.TYPE)
		|| field.getType().isAssignableFrom(Long.TYPE)
		|| field.getType().isAssignableFrom(Double.TYPE)){
			Class parametre = null;

			if ( field.getType().isAssignableFrom(Integer.TYPE) )
				parametre = Integer.TYPE;

			else if ( field.getType().isAssignableFrom(Long.TYPE) )
				parametre = Long.TYPE;

			else if ( field.getType().isAssignableFrom(Double.TYPE) )
				parametre = Double.TYPE;					

			methodeValeur.method = vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), parametre);
			methodeValeur.valeur = 0;

		}else if (field.getType().isAssignableFrom(Boolean.class)){
			methodeValeur.method = vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), Boolean.class);
			methodeValeur.valeur = null;

		}else if (field.getType().isAssignableFrom(String.class)){
			methodeValeur.method = vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), String.class);
			methodeValeur.valeur = "" ;

		}else{
			methodeValeur.method = vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), Object.class);
			methodeValeur.valeur = null;
		}
    	return methodeValeur;
    }
    
    private static boolean validerEtFiltrerSecuriteRechercher(CardexAuthenticationSubject subject, Map<String, RaccourcitGestionAccesSecurite> raccourcitGestionAccesSecuriteMap, Object vo, List<SecuritePredicate> securitePredicates, String... actionsSecurite) {
        log.fine("Valider accès du VO '" + vo.getClass().getName() + "'");
		
        if (securitePredicates != null)
	        for (SecuritePredicate securitePredicate:securitePredicates)
	        	if (securitePredicate.validerSecurite(subject, vo))
	        		return true;
        
        Field[] fields = vo.getClass().getDeclaredFields();
        
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			boolean retirerValeur = false;
			RaccourcitGestionAccesSecurite raccourcitGestionAccesSecurite = raccourcitGestionAccesSecuriteMap.get(construireClasseMethode(vo, field.getName()));
						
			try {
				// On récupère le sous object pour assigner ça sécurité à l'object de raccourcit
				if (Business.class.isAssignableFrom( field.getType() )){
					Business business = (Business) vo.getClass().getDeclaredMethod("get"+StringUtils.capitalise(field.getName())).invoke(vo);
					boolean retourSousObjet = validerEtFiltrerSecuriteRechercher(subject, raccourcitGestionAccesSecuriteMap, business, securitePredicates, actionsSecurite);
					
					if (retourSousObjet == false)
						return false;
				}

				if (raccourcitGestionAccesSecurite.isAdministrer() == false)
					continue;
				
				String valeur = getValeur(vo, field);
				
				if (StringUtils.isEmpty(valeur))
					continue;
				
		        if (StringUtils.isNotEmpty(raccourcitGestionAccesSecurite.getRoleCache().getCodeListe()) && "0".equals(valeur))
		        	continue;
		        
		        if (raccourcitGestionAccesSecurite.isRoleActifouInactif()){
		        	
		        	if (raccourcitGestionAccesSecurite.getAccessibleEtObligatoireMap().size() > 0){
		        		boolean isValeurAccessible = false;
		        		boolean isValeurObligatoire = false;
		        		
			        	for (String actionSecurite:actionsSecurite){
			        		AccessibleEtObligatoire accessibleEtObligatoire = raccourcitGestionAccesSecurite.getAccessibleEtObligatoireMap().get(actionSecurite);
			        		Set<String> cleAccessibleActifEtInactifSet = accessibleEtObligatoire.getCleAccessibleActifEtInactifSet();

			        		// Si une valeur est obligatoire, il faut l'indiquer pour retirer l'enregistrement
		        			if (accessibleEtObligatoire.isObligatoire())
		        				isValeurObligatoire = true;
			        		
		        			// Si on peut accéder à la valeur, la notion d'obligatoire n'est pas importante
			        		if (cleAccessibleActifEtInactifSet.contains(valeur)){
			        			isValeurAccessible = true;
			        			break;
			        		}
			        	}
			        	
			        	if (isValeurAccessible == false){
			        		retirerValeur = true;
			        		
			        		if (isValeurObligatoire)
			        			return false;
			        	}
		        	}
		        	
		        }else
		        	retirerValeur = true;
		        
		        if (retirerValeur){
					Object[] setValeur = {raccourcitGestionAccesSecurite.getValeurCible()};
					raccourcitGestionAccesSecurite.getMethodSet(vo).invoke(vo, setValeur);		        	
		        }
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return true;
    }        
    /*
    public static boolean validerEtFiltrerSecuriteRechercher(CardexAuthenticationSubject subject, Object vo, String... actionsSecurite) {
    	ListeCache listeCache = ListeCache.getInstance();
        log.fine("Valider accès du VO '" + vo.getClass().getName() + "'");
		
        if (intervenantCreateur.validerSecurite(subject, vo))
        	return true;
        
        Field[] fields = vo.getClass().getDeclaredFields();
        
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				Method methodSet = null;
				String valeur = getValeur(vo, field);
				Object valeurCible = null;
				RoleCache roleCache = obtenirRoleCacheDeVO(vo, field.getName());
				
				if (roleCache == null)
					continue;
				
				// Si le champs n'est pas vide et que l'utilisateur n'a pas le 
				// droit de le voir, il faut vider le champ.
				if (validerAccesSecurite(subject, vo, field.getName(), valeur, actionsSecurite) == false){
					
					if (listeCache.isObligatoire(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), "", GlobalConstants.ActionSecurite.RECHERCHE))){
						return false;
					}
					
					if (field.getType().isAssignableFrom(Long.TYPE)){
						methodSet =  vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), Long.TYPE);
						valeurCible = Long.valueOf(0);
					
					}else if (field.getType().isAssignableFrom(String.class)){
						methodSet =  vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), String.class);
						valeurCible = "";
					
					}else{
						methodSet =  vo.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), Object.class);
						valeurCible = null;
					}
					Object[] setValeur = {valeurCible};
					methodSet.invoke(vo, setValeur);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (BusinessResourceException e) {
				e.printStackTrace();
			}
		}
		return true;
    }    */

	private static String getValeur(Object vo, Field field) throws SecurityException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		Class[] classGet = new Class[0];
		String prefix = "get";
		
		if (field.getType().isAssignableFrom(Boolean.TYPE))
			prefix = "is";
		Method methodGet =  vo.getClass().getDeclaredMethod(prefix+StringUtils.capitalise(field.getName()), classGet);
		return getValeur(vo, methodGet);
	}
	
	private static String getValeur(Object vo, Method methodGet)
			throws IllegalAccessException, InvocationTargetException {
		
		if (methodGet.invoke(vo) != null){
			Object o = methodGet.invoke(vo);
			
			/*if (o instanceof String || o instanceof Integer || o instanceof Long 
			|| o instanceof Timestamp || o instanceof Date || o instanceof Boolean 
			|| o instanceof Double)
				return o.toString();*/
			
			if (o instanceof Collection){
				StringBuffer valeur = new StringBuffer();
				
				for (Object element:(Collection)o){
					if (valeur.length() > 0)
						valeur.append("-");
					valeur.append(element.toString());
				}
				return valeur.toString();
			}
			return o.toString();		
			/*else
				System.out.println("Type non validé"+o.getClass());*/
		}
		return "";
	}
	
    protected static Collection validerEtFiltrerSecurite(CardexAuthenticationSubject subject, Collection collection, List<SecuritePredicate> securitePredicates, String... actionsSecurite) {
    	Iterator iter = collection.iterator();
    	
    	if (iter.hasNext()){
    		Map<String, RaccourcitGestionAccesSecurite> raccourcitGestionAccesSecuriteMap = construireRaccourcitGestionAccesSecurite(subject, new HashMap<String, RaccourcitGestionAccesSecurite>(), iter.next());
    		iter = collection.iterator();
    		
        	while (iter.hasNext()){
        		Object vo = iter.next();
        		GererTacheUtilisateur.verifierThreadCourrant();
        		
        		//if (validerEtFiltrerSecuriteRechercher(subject, vo, actionsSecurite) == false)
        		if (validerEtFiltrerSecuriteRechercher(subject, raccourcitGestionAccesSecuriteMap, vo, securitePredicates, actionsSecurite) == false)
        			iter.remove();
        	}
    	}
    	
    	return collection;
    }
    
    /**
     * Constuire les objets de raccourcit
     * @param subject
     * @param vo
     * @param actionsSecurite
     * @return
     */						   
    private static Map<String, RaccourcitGestionAccesSecurite> construireRaccourcitGestionAccesSecurite(CardexAuthenticationSubject subject, Map<String, RaccourcitGestionAccesSecurite> raccourcitGestionAccesSecuriteMap, Object vo) {
    	ListeCache listeCache = ListeCache.getInstance();
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	Field[] fields = vo.getClass().getDeclaredFields();
    	
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			RaccourcitGestionAccesSecurite raccourcitGestionAccesSecurite = new RaccourcitGestionAccesSecurite();
			raccourcitGestionAccesSecuriteMap.put(construireClasseMethode(vo, field.getName()), raccourcitGestionAccesSecurite);
			RoleCache roleCache = obtenirRoleCacheDeVO(vo, field.getName());
			raccourcitGestionAccesSecurite.setNomChamp(field.getName());
			raccourcitGestionAccesSecurite.setRoleCache(roleCache);
			
			try {
				// On récupère le sous object pour assigner ça sécurité à l'object de raccourcit
				if (Business.class.isAssignableFrom( field.getType() )){
					Business business = (Business) vo.getClass().getDeclaredMethod("get"+StringUtils.capitalise(field.getName())).invoke(vo);
					construireRaccourcitGestionAccesSecurite(subject, raccourcitGestionAccesSecuriteMap, business);
				}
				
				// Aucun rôle signifie que ce VO n'est pas administré
		        if (roleCache == null || roleCache.getRole().trim().length() == 0 ){
		        	raccourcitGestionAccesSecurite.setAdministrer(false);
		        	continue;
		        }else
		        	raccourcitGestionAccesSecurite.setAdministrer(true);
		        
		        raccourcitGestionAccesSecurite.setRoleActifouInactif( userPrivilege.hasRoleActifouInactif(roleCache.getRole()) );
		        
	        	if (StringUtils.isNotEmpty(roleCache.getCodeListe())){
	        		
	        		// On construit la sécurité pour toutes les actionsSecurite
	        		// Il est possible d'en avoir plusieurs.  Dans ce cas c'est la plus avantageuse qui remporte.
	        		for (String actionSecurite:actionsSecuriteList){
	        			Map<String, LabelValueBean> labelValueActifEtInactifMap = listeCache.obtenirMapLabelValueActifEtInactif(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), actionSecurite));
	        			boolean obligatoire = listeCache.isObligatoire(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), "", actionSecurite));

	        			// Problème de Serial en Java... Contournement:
	        			Set<String> valeurAccessible = new HashSet<String>(); 
	        			for (String key:labelValueActifEtInactifMap.keySet())
	        				valeurAccessible.add(key);
	        			
	        			AccessibleEtObligatoire accessibleEtObligatoire = new AccessibleEtObligatoire(valeurAccessible, obligatoire);
	        			
	        			raccourcitGestionAccesSecurite.getAccessibleEtObligatoireMap().put(actionSecurite, accessibleEtObligatoire);
	        		}
	        	}
	        	
	        	MethodeValeur methodeValeur = obtenirMethodeValeur(field, vo);
	        	raccourcitGestionAccesSecurite.assignerParametreType(field);
	        	raccourcitGestionAccesSecurite.setValeurCible( methodeValeur.valeur );
        	
			} catch (BusinessResourceException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
				
    	return raccourcitGestionAccesSecuriteMap;
	}

	protected static ValueListIterator validerEtFiltrerListIteratorSecurite(CardexAuthenticationSubject subject, ValueListIterator valueListIterator, List<SecuritePredicate> securitePredicates, String... actionSecuriteRecherche) {
    	try {
			List list = valueListIterator.getNextElements(valueListIterator.getSize());
			validerEtFiltrerSecurite(subject, list, securitePredicates, actionSecuriteRecherche);
			return new ValueListHandler(list);
		} catch (IteratorException e) {
			e.printStackTrace();
			throw new RuntimeException("Erreur lors de la validation de la sécurité avec un ValueListIterator");
		}
    }
    
    /*
     * Valide si l'utilisateur possède le rôle et le droit pour accéder a cette valeur
     */
    public static boolean validerAccesSecurite(CardexAuthenticationSubject subject, Object vo, String methode, String valeur, String... actionsSecurite) {
    	
    	// Aucune valeur, aucun rôle de sécurité
    	if (StringUtils.isEmpty(valeur))
    		return true;
    	
    	CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
    	RoleCache roleCache = obtenirRoleCacheDeVO(vo, methode);
    	
    	// Aucun rôle signifie que ce VO n'est pas administré
        if (roleCache == null || roleCache.getRole().trim().length() == 0 )
        	return true;
        
        if (StringUtils.isNotEmpty(roleCache.getCodeListe()) && "0".equals(valeur))
        	return true;
        
        if (userPrivilege.hasRoleActifouInactif(roleCache.getRole())){
        
        	if (StringUtils.isNotEmpty(roleCache.getCodeListe())){
        		ListeCache cache = ListeCache.getInstance();
        		try {
					for (String actionSecurite:actionsSecurite){
						if ( cache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, roleCache.getCodeListe(), actionSecurite), valeur) )
							return true;
					}
					return false;
				} catch (BusinessResourceException e) {
					e.printStackTrace();
					return false;
				}
        	}else
        		return true;
        }else
        	return false;
    }

	private static RoleCache obtenirRoleCacheDeVO(Object vo, String methode) {
		String classeMethode = construireClasseMethode(vo, methode);
        log.fine("Valider accès du VO classe et methode '" + classeMethode + "'");
    	RolesCacheSecuriteCache rolesCacheSecuriteCache = RolesCacheSecuriteCache.getInstance();

    	RoleCache roleCache = rolesCacheSecuriteCache.obtenirRoleCacheDeVOMethode(classeMethode);
    	
		if (roleCache == null){
			// Il y a trop de cas où c'est normal de ne pas avoir de rôle pour une variable
			//log.warning("Rôle : \""+classeMethode+"\" n'a pas été créé.");
			return null;
		}
    	
		if (roleCache.isAdministrer())
			return roleCache;
		else
			return null;
	}
    
    private static String construireClasseMethode(Object o, String methode){
    	return o.getClass().getName()+"."+methode;
    }

    private static String construireClasseMethode(String strClass, String methode){
    	return strClass+"."+methode;
    }

}

