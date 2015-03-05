package com.lotoquebec.cardexCommun.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * Cette classe calcule la prochaine journée ouvrable à Loto-Québec
 * en tenant compte des congés.
 * @author levassc
 *
 */
public class JourneeOuvrable {

	private static Calendar actuelleExecution = null;
	private static List<Date> dateConge = new ArrayList<Date>();
	
	private JourneeOuvrable() {
		super();
	}
	
	// + 1 jour
	// + 21 jours
	// + 30 jours
	public static synchronized Date ajouterJours(int jour){
		Calendar cal = Calendar.getInstance();

		if (actuelleExecution == null
		|| new DateMemeJourPredicate(cal.getTime()).evaluate(actuelleExecution.getTime()) == false){
			actuelleExecution = Calendar.getInstance();
			actuelleExecution.setTime( cal.getTime() );
			calculer60JoursConge();
		}
		
		while (jour > 0){
			cal.add(Calendar.DATE, 1);
			jour--;
			
			while( CollectionUtils.exists(dateConge, new DateMemeJourPredicate(cal.getTime())) )
				cal.add(Calendar.DATE, 1);
		}
		
		return cal.getTime();
	}
	
	//Calcul des jours ouvrables à partir d'une date donnée. On ajoute le nombre de jours ouvrables
	//passé en paramètre. La date calculée est notamment comparée dans le diffété CDX00_00017
	//sur les demandes incomplètes.
	public static synchronized Date ajouterJours(Date dateDepart, int jour){

		Calendar dateDepartCal = new GregorianCalendar();
		
		dateDepartCal.setTime(dateDepart);
		if (actuelleExecution == null
		|| new DateMemeJourPredicate(dateDepartCal.getTime()).evaluate(actuelleExecution.getTime()) == false){
			actuelleExecution = Calendar.getInstance();
			actuelleExecution.setTime( dateDepartCal.getTime() );
			calculer60JoursConge();
		}
		
		while (jour > 0){
			dateDepartCal.add(Calendar.DATE, 1);
			jour--;
			
			while( CollectionUtils.exists(dateConge, new DateMemeJourPredicate(dateDepartCal.getTime())) )
				dateDepartCal.add(Calendar.DATE, 1);
		}
		
		return dateDepartCal.getTime();
	}

	/**
	 * Nous calculons les congés des 60 prochains jours parce que la limite
	 * est de 30 jours.  S'il faut calculer plus de 30 jours, il faut monter 
	 * cette limite.  
	 * Nous ajoutons tous les congés (non les fins de semaine) pour la prochaine année.
	 * Pour éviter des if et des calculs de date.
	 */
	private static void calculer60JoursConge(){
		int annee = actuelleExecution.get(Calendar.YEAR);
		Calendar finCal = Calendar.getInstance();
		finCal.setTime( actuelleExecution.getTime() );
		dateConge.clear();
		
		// Fin de semaine
		for (int i=0;i<60;i++){
			finCal.add(Calendar.DATE, 1);
			int numeroJour = finCal.get(Calendar.DAY_OF_WEEK);
			
			if (Calendar.SATURDAY == numeroJour || Calendar.SUNDAY == numeroJour)
				dateConge.add( finCal.getTime() );
		}
		dateConge.addAll( joursConge( obtenirDate(annee, 1, 1) ) ); // jour de l'an
		dateConge.addAll( joursPaque( dimanchePaque(annee) ) ); // Pâque
		dateConge.add( fetePatriotes(annee) ); // Journée nationale des patriotes
		dateConge.add( jourConge( obtenirDate(annee, 6, 24) ) ); // St-Jean
		dateConge.add( jourConge( obtenirDate(annee, 7, 1) ) ); // fête du Canada
		dateConge.add( feteTravail(annee) ); // fête du Travail
		dateConge.add( feteActionGraces(annee) ); // Action de grâces
		dateConge.addAll( joursConge( obtenirDate(annee, 12, 25) ) ); // Noël
		dateConge.addAll( joursConge( obtenirDate(annee+1, 1, 1) ) ); // prochain jour de l'an
	}
	
	private static Calendar obtenirDate(int annee, int mois, int jour){
		return new GregorianCalendar(annee, mois-1, jour, 0, 0, 0);
	}
	
	/*
	 * le lundi qui précède le 25 mai (Journée nationale des patriotes)
	 */
	private static Date fetePatriotes(int annee){
		Calendar cal = obtenirDate(annee, 5, 24);
		int numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		
		while (numeroJour != Calendar.MONDAY){
			cal.add(Calendar.DATE, -1);
			numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		}
		
		return cal.getTime();
	}

	/*
	 * le 1er lundi de septembre (fête du Travail)
	 */
	private static Date feteTravail(int annee){
		Calendar cal = obtenirDate(annee, 9, 1);
		int numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		
		while (numeroJour != Calendar.MONDAY){
			cal.add(Calendar.DATE, 1);
			numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		}
		
		return cal.getTime();
	}
	
	/*
	 * Calcul la St-Jean et la fête du Canada
	 * Si la journée férie est le samedi => congé vendredi
	 * Si dimanche => congé lundi
	 */
	private static Date jourConge(Calendar cal){
		int numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		
		switch (numeroJour){
		
			case Calendar.SUNDAY: 
				cal.add(Calendar.DATE, 1);
				break;
				
			case Calendar.SATURDAY: 
				cal.add(Calendar.DATE, -1);
				break;

		};
		
		return cal.getTime();
	}

	/*
	 * le 2e lundi d’octobre (Action de grâces)
	 */
	private static Date feteActionGraces(int annee){
		Calendar cal = obtenirDate(annee, 10, 1);
		int numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		
		for (int i=0;i<2;i++){
			while (numeroJour != Calendar.MONDAY){
				cal.add(Calendar.DATE, 1);
				numeroJour = cal.get(Calendar.DAY_OF_WEEK);
			}
			if (i<1){
				cal.add(Calendar.DATE, 1);
				numeroJour = cal.get(Calendar.DAY_OF_WEEK);
			}
		}
		
		return cal.getTime();
	}
	
	/*
	 * Calcul pour Noël et jour de l'an
	 * Si la journée férie est le samedi, dimanche ou lundi
	 * les journées de congé seront le vendredi, lundi et mardi.
	 */
	private static List<Date> joursConge(Calendar cal){
		List<Date> joursConge = new ArrayList<Date>();
		int numeroJour = cal.get(Calendar.DAY_OF_WEEK);
		
		switch (numeroJour){
		
			case Calendar.SUNDAY: 
				cal.add(Calendar.DATE, -2);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 3);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 1);
				joursConge.add(cal.getTime());				
				break;
				
			case Calendar.MONDAY: 
				cal.add(Calendar.DATE, -3);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 3);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 1);
				joursConge.add(cal.getTime());				
				break;			
			
			case Calendar.FRIDAY: 
				cal.add(Calendar.DATE, -1);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 1);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 3);
				joursConge.add(cal.getTime());				
				break;			
				
			case Calendar.SATURDAY: 
				cal.add(Calendar.DATE, -1);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 3);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 1);
				joursConge.add(cal.getTime());				
				break;
				
			default:
				cal.add(Calendar.DATE, -1);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 1);
				joursConge.add(cal.getTime());
				cal.add(Calendar.DATE, 1);
				joursConge.add(cal.getTime());
				break;
		};
		
		return joursConge;
	}
	
	private static List<Date> joursPaque(Calendar dichanchePaque){
		Calendar samediPaque = (Calendar) dichanchePaque.clone();
		samediPaque.add(Calendar.DATE, -1);
		List<Date> joursConge = new ArrayList<Date>();
		joursConge.add( jourConge(samediPaque) );
		joursConge.add( jourConge(dichanchePaque) );
		return joursConge;
	}
	
	  /*
	   * Compute the day of the year that Easter falls on. Step names E1 E2 etc.,
	   * are direct references to Knuth, Vol 1, p 155. @exception
	   * IllegalArgumentexception If the year is before 1582 (since the algorithm
	   * only works on the Gregorian calendar).
	   */
	private static final Calendar dimanchePaque(int year) {
	    if (year <= 1582) {
	      throw new IllegalArgumentException(
	          "Algorithm invalid before April 1583");
	    }
	    int golden, century, x, z, d, epact, n;

	    golden = (year % 19) + 1; /* E1: metonic cycle */
	    century = (year / 100) + 1; /* E2: e.g. 1984 was in 20th C */
	    x = (3 * century / 4) - 12; /* E3: leap year correction */
	    z = ((8 * century + 5) / 25) - 5; /* E3: sync with moon's orbit */
	    d = (5 * year / 4) - x - 10;
	    epact = (11 * golden + 20 + z - x) % 30; /* E5: epact */
	    
	    if ((epact == 25 && golden > 11) || epact == 24)
	      epact++;
	    n = 44 - epact;
	    n += 30 * (n < 21 ? 1 : 0); /* E6: */
	    n += 7 - ((d + n) % 7);
	    
	    if (n > 31) /* E7: */
	      return new GregorianCalendar(year, 4 - 1, n - 31); /* April */
	    else
	      return new GregorianCalendar(year, 3 - 1, n); /* March */
	}
	
	/**
	 * La fonction suivante calcule l'écart en jours entre deux dates  
	 * en tenant compte des jours ouvrables uniquement.
	 * La valeur retournée est le nombre de jours ouvrables écoulé entre 2 dates.
	 */
	public static synchronized int calculerDifference(Date dateDebut, Date dateFin){
		
		int nombreJours = 0;
		Date dateDepart = dateDebut;
		while (dateDepart.before(dateFin)){
			dateDepart = ajouterJours(dateDebut, ++nombreJours);
			if(nombreJours >= 50) break;
		}
		return nombreJours;
		
	}

	/**
	 * La fonction suivante calcule l'écart en jours entre deux dates  
	 * en tenant compte des jours ouvrables uniquement.
	 * La valeur retournée est la date finale sans les jours ouvrables.
	 */
	public static synchronized Date calculerDifferenceDate(Date dateDebut, Date dateFin){
		
		int nombreJours = 0;
		Date dateDepart = dateDebut;
		while (dateDepart.before(dateFin)){
			dateDepart = ajouterJours(dateDebut, ++nombreJours);
			if(nombreJours >= 50) break;
		}
		return dateDepart;
		
	}

	/**
	 * Calcul des jours ouvrables pour une période donnée. On ajuste la date de départ pour en tenir compte. 
	 * La valeur retournée sert au calcul des délais
	 * (différence entre 2 dates pour les jours ouvrables seulement)
	 * PAS TESTÉ (juillet 2013)
	 */
/*	private static Calendar calculerJoursOuvrables(Calendar dateDebut, Calendar dateFin){
		int annee = dateDebut.get(Calendar.YEAR);
		List<Date> nombreJoursOuvrables = new ArrayList<Date>();
		int ecart = (int)( (dateDebut.getTimeInMillis() - dateFin.getTimeInMillis()) / (1000 * 60 * 60 * 24));
		//On recherche d'abord les dates en fonction de l'année de la date de début.
		Calendar dateFinSemaine = new GregorianCalendar();
		for (int i=0;i<ecart;i++){
			//Ajout des fins de semaine
			
			dateFinSemaine.add(Calendar.DATE, 1);
			int numeroJour = dateFinSemaine.get(Calendar.DAY_OF_WEEK);
			
			if (Calendar.SATURDAY == numeroJour || Calendar.SUNDAY == numeroJour)
				nombreJoursOuvrables.add( dateFinSemaine.getTime() );
		}
		//Ajout des jours fériés
		nombreJoursOuvrables.addAll( joursConge( obtenirDate(annee, 1, 1) ) ); // jour de l'an
		nombreJoursOuvrables.addAll( joursPaque( dimanchePaque(annee) ) ); // Pâque
		nombreJoursOuvrables.add( fetePatriotes(annee) ); // Journée nationale des patriotes
		nombreJoursOuvrables.add( jourConge( obtenirDate(annee, 6, 24) ) ); // St-Jean
		nombreJoursOuvrables.add( jourConge( obtenirDate(annee, 7, 1) ) ); // fête du Canada
		nombreJoursOuvrables.add( feteTravail(annee) ); // fête du Travail
		nombreJoursOuvrables.add( feteActionGraces(annee) ); // Action de grâces
		nombreJoursOuvrables.addAll( joursConge( obtenirDate(annee, 12, 25) ) ); // Noël
		nombreJoursOuvrables.addAll( joursConge( obtenirDate(annee+1, 1, 1) ) ); // prochain jour de l'an
		//Si l'écart avec la date de fin chevauche plus d'une année, il faut également ajouter les jours 
		//fériés pour ces années.
		int anneeFin = dateFin.get(Calendar.YEAR);
		while (annee < anneeFin){
			annee++;
			nombreJoursOuvrables.addAll( joursConge( obtenirDate(annee, 1, 1) ) ); // jour de l'an
			nombreJoursOuvrables.addAll( joursPaque( dimanchePaque(annee) ) ); // Pâque
			nombreJoursOuvrables.add( fetePatriotes(annee) ); // Journée nationale des patriotes
			nombreJoursOuvrables.add( jourConge( obtenirDate(annee, 6, 24) ) ); // St-Jean
			nombreJoursOuvrables.add( jourConge( obtenirDate(annee, 7, 1) ) ); // fête du Canada
			nombreJoursOuvrables.add( feteTravail(annee) ); // fête du Travail
			nombreJoursOuvrables.add( feteActionGraces(annee) ); // Action de grâces
			nombreJoursOuvrables.addAll( joursConge( obtenirDate(annee, 12, 25) ) ); // Noël
			nombreJoursOuvrables.addAll( joursConge( obtenirDate(annee+1, 1, 1) ) ); // prochain jour de l'an
		}
		
		//Il reste maintenant à ajuster la date de départ en ajoutant tous les jours ouvrables trouvés
		while (ecart > 0){
			dateDebut.add(Calendar.DATE, 1);
			ecart--;
			
			while( CollectionUtils.exists(nombreJoursOuvrables, new DateMemeJourPredicate(dateDebut.getTime())) )
				dateDebut.add(Calendar.DATE, 1);
		}
		
		return dateDebut;
	}
*/	

}
