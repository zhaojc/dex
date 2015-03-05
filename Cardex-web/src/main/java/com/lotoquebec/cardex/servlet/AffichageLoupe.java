package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class AffichageLoupe extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
   /**
	* Ce servlet permet d'afficher les photos ou les pi�ces jointes dans l'�cran w_consultation_piece_jointe.jsp
	* ou dans les onglets photos et pi�ces jointes
	* ou dans une fen�tre s�par�e dans l'application concern�e.
	* Ce servlet est appel� pour l'affichages des images dans les diff�rents �crans, 
	* ou � partir de la loupe plac�e � c�t� d'une photo
	* ou � partir du num�ro � c�t� d'une image affich�e dans un onglet.
	* Le servlet r�cup�re les coordonn�es de l'image et interroge le r�pertoire
	* pour ramener le BLOB qui contient l'image r�f�r�e. Le contenu est retourn�
	* � la page via un OutputStream qui sert � transmettre des donn�es en format Byte.
	* Voici un exemple d'appel au servlet dans la page JSP :
	* <A HREF="javascript: viewPhoto('<%= request.getContextPath() %>/AffichageLoupe?CLE=<bean:write name='element' property='lienElement' />&SITE=<bean:write name='element' property='lienSiteElement' />&EXTENSION=<bean:write name='element' property='extension' />');" >
	* 
	*/
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();

		try {
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
			GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());

            PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
			
			//R�cup�ration des param�tres
			//String extension = (String)request.getParameter("EXTENSION");
			long cle = Long.parseLong(request.getParameter("CLE"));
			long site = Long.parseLong(request.getParameter("SITE"));
			
			String strGrandeImage = request.getParameter("GrandeImage");
			boolean grandeImage = false;
			
			if (StringUtils.isNotEmpty(strGrandeImage))
				grandeImage = Boolean.valueOf(strGrandeImage);
			
			FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, cle, site, grandeImage);
			
			response.setContentType(obtenirContentType(fichierMultimediaVO.getExtention()));
			
			out.write(fichierMultimediaVO.getImageByte());			

		} catch (BusinessResourceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		out.flush();
	    out.close();
	    destroy();
	}
	
	//http://www.coderanch.com/t/366362/Servlets/java/res-setContentType
	//http://www.iana.org/assignments/media-types/index.html
	private String obtenirContentType(String extension){
		
		if ("DOC".equalsIgnoreCase(extension)
        || "RTF".equalsIgnoreCase(extension)
		|| "DOT".equalsIgnoreCase(extension))
			return "application/msword";
		
        else if ("DOCX".equalsIgnoreCase(extension))
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

        else if ("XLS".equalsIgnoreCase(extension))
			return "application/vnd.ms-excel";

        else if ("XLSX".equalsIgnoreCase(extension))
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

        else if ("PPT".equalsIgnoreCase(extension))
			return "application/vnd.sealed.ppt";

        else if ("PPTX".equalsIgnoreCase(extension))
            return "application/vnd.openxmlformats-officedocument.presentationml.presentation";

		else if ("TXT".equalsIgnoreCase(extension))
			return "text/plain";
		
        if ("HTM".equalsIgnoreCase(extension)
         || "HTML".equalsIgnoreCase(extension))
            return "text/html";

        else if ("MSG".equalsIgnoreCase(extension))
            return "application/vnd.ms-outlook";

        else if ("PDF".equalsIgnoreCase(extension))
			return "application/pdf";		
		else{
			return (new MimetypesFileTypeMap()).getContentType("a." + extension);
		}
	}
	
}