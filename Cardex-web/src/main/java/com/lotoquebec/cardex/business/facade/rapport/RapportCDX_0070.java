package com.lotoquebec.cardex.business.facade.rapport;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.text.DateFormat;

public abstract class RapportCDX_0070 extends RapportCardex{

	private final static Logger log = (Logger)LoggerCardex.getLogger(RapportCDX_0070.class);
	private Date debutDate;
	private Date finDate;
	private CardexAuthenticationSubject subject;
	
	protected abstract List<RapportVOCDX_00070> construireDonneeRapport(CardexAuthenticationSubject subject, Date dateDebut, Date dateFin) throws DAOException;
	protected abstract String obtenirTitre();
	
	public RapportCDX_0070(CardexAuthenticationSubject subject, Date debutDate, Date finDate) {
		super(subject);
		this.subject = subject;
		this.debutDate = debutDate;
		this.finDate = finDate;
	}

	@Override
	protected Collection construireDonneeRapport(CardexAuthenticationSubject subject) throws DAOException {
		return construireDonneeRapport(subject, debutDate, finDate);
	}
	
	@Override
	protected InputStream obtenirJasperReport() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_ACTIVITES);
	}
	
	@Override
	protected Map construireParametres() {
		Map parameters = new HashMap();
		parameters.put("DATE_DEBUT", DateFormat.format(debutDate, DateFormat.DATE_FORMAT_AVEC_HEURE));
		parameters.put("DATE_FIN", DateFormat.format(finDate, DateFormat.DATE_FORMAT_AVEC_HEURE));
		parameters.put("UTILISATEUR", "différé Cardex");
		parameters.put("TITRE", obtenirTitre());
		
		return parameters;
	}

	
}
