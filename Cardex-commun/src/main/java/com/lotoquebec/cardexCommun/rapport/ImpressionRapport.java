package com.lotoquebec.cardexCommun.rapport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class ImpressionRapport {

	protected abstract ImprimerThread obtenirNewImprimerThread(String nomRapport, JasperPrint print);
	
	private static int NB_MAX_TENTATIVE_EXCEPTION=5;
	private static int NB_MAX_TENTATIVE_ATTENTE=5;
	
	public void impression(String nomRapport, JasperPrint print) throws FileNotFoundException {
		int nbTentativeException=0;
		
		if (StringUtils.isEmpty(nomRapport))
			throw new FileNotFoundException("nomRapport est null");
		
		for (;nbTentativeException<NB_MAX_TENTATIVE_EXCEPTION;nbTentativeException++) {
			int nbTentativeAttente=0;
			System.out.println("nbTentativeException "+nbTentativeException);
				
			ImprimerThread imprimerThread = obtenirNewImprimerThread(nomRapport, print);
			imprimerThread.start();
			
			for (;nbTentativeAttente<NB_MAX_TENTATIVE_ATTENTE;nbTentativeAttente++){
				try {
					Thread.currentThread().sleep(60000); //1 minute
				} catch (Exception e) {}
				
				if (imprimerThread.isTermineCorrectement())
					break;
				else{
					System.out.println("imprimerPDFThread ne ce termine pas correctement ");
					if (imprimerThread.isException()){
						System.out.println("imprimerPDFThread est en exception ");
						break;
					}
				}
			}
			if (imprimerThread.isTermineCorrectement())
				break;
			else
				if (nbTentativeAttente==NB_MAX_TENTATIVE_ATTENTE){
					System.out.println("Stoper imprimerPDFThread");
					//imprimerThread.stop();
					imprimerThread.interrupt();
				}
		}
		if (nbTentativeException==NB_MAX_TENTATIVE_EXCEPTION)
			throw new FileNotFoundException("Probl�me lors de l'impression du rapport '"+nomRapport+"'");

	}
	
	abstract class ImprimerThread extends Thread{
		
		protected String nomRapport;
		protected JasperPrint print;
		private Boolean actif;
		private Boolean exception = false;
		
		public abstract void runIT(FileOutputStream sortie) throws IOException, JRException;
		
		public void run(){
			actif = true;
			
			try {
				// Sauvegarde dans un fichier
				FileOutputStream sortie = new FileOutputStream(nomRapport);
				System.out.println("exportReportToPdfStream "+nomRapport);
				
				if (sortie == null)
					System.out.println("sortie est null ");
				
				runIT(sortie);
				
				System.out.println("flush "+nomRapport);
				sortie.flush();
				System.out.println("close "+nomRapport);
				sortie.close();
				System.out.println("fin "+nomRapport);
			} catch (Exception e) {
				System.out.println("Exception "+nomRapport);
				exception = true;
				ExceptionHandler.getInstance().handle(e);
			}
			actif = false;
		}
		
		public ImprimerThread(String nomRapport, JasperPrint print){
			this.nomRapport = nomRapport;
			this.print = print;
		}
		
		public Boolean isActif() {
			return actif;
		}

		public Boolean isException() {
			return exception;
		}
		
		public boolean isTermineCorrectement(){
			return exception == false && actif == false;
		}
	}
}
