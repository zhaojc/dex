package com.lotoquebec.cardex.integration.dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import oracle.jdbc.OracleTypes;

import org.apache.commons.collections.CollectionUtils;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetInteretGalerieVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.CompletDossierGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.CompletReperageSujetGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.CompletSujetGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.CountDossierGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.CountReperageSujetGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.CountSujetGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.DossierDirectCompletSujetGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.galerie.SujetDirectCompletSujetGalerieConstruireRechercheSQL;
import com.lotoquebec.cardex.util.NatureDossierPredicate;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.FonctionTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Liste des appels � la base de donn�es pour diff�rents acc�s aux
 * photos.  Impl�mente l'interface PhotoDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.25 $, $Date: 2002/04/30 17:47:12 $
 * @see com.lotoquebec.cardex.integration.PhotoDAO
 */

public class PhotoDAO {

	public static final String DATE_DEBUT_DOSSIER = "dateDebutDossier";
	public static final String DATE_FIN_DOSSIER = "dateFinDossier";
	public static final String GENRE_DOSSIER = "genreDossier";
	public static final String NATURE_DOSSIER = "natureDossier";
	public static final String CONFIDENTIALITE_DOSSIER = "confidentialiteDossier";

   private final Logger      log =
       (Logger)LoggerCardex.getLogger(PhotoDAO.class);
   
   
//Classe pour permettre le tri des r�sultats de la recherche des photos
//par ordre de date de cr�ation d�croissant.
    class DateCreationComparator implements Comparator, Serializable {
      public int compare(Object o1,Object o2) {
        if (o1 instanceof PhotoVO && o2 instanceof PhotoVO ) {
          PhotoVO l1 = (PhotoVO)o1;
          PhotoVO l2 = (PhotoVO)o2;
          if (l1.getDateCreation() == null) {
              return 1;
          }else if (l2.getDateCreation() == null) {
                  return -1;
                }else{
                      //return String.CASE_INSENSITIVE_ORDER.compare(l1.getDateCreation(),l2.getDateCreation());
                      if(l1.getDateCreation().before(l2.getDateCreation())){
                        return 1;
                      }else if(l2.getDateCreation().before(l1.getDateCreation())){
                              return -1;
                            }else{
                              return 0;
                            }
                      }
          }else {
             return -1;
          }
        }
    }
class DateDebutDossierComparator implements Comparator, Serializable {
	public int compare(Object o1,Object o2) {
		if (o1 instanceof PhotoVO && o2 instanceof PhotoVO ) {
	        PhotoVO l1 = (PhotoVO)o1;
	        PhotoVO l2 = (PhotoVO)o2;
	        Dossier do1 = l1.getDossier();
	        Dossier do2 = l2.getDossier();
	        
	        if (do1.getDateDebut() == null) {
	            return 1;
	        }else if (do2.getDateDebut() == null) {
	        	return -1;
	        }else{
	        	if(do1.getDateDebut().before(do2.getDateDebut())){
	        		return 1;
	            }else if(do2.getDateDebut().before(do1.getDateDebut())){
	            	return -1;
	            }else{
	            	return 0;
	            }
	        }
	    }else {
	       return -1;
	    }
	} 
}
    
class DateFinDossierComparator implements Comparator, Serializable {
	public int compare(Object o1,Object o2) {
		if (o1 instanceof PhotoVO && o2 instanceof PhotoVO ) {
	        PhotoVO l1 = (PhotoVO)o1;
	        PhotoVO l2 = (PhotoVO)o2;
	        Dossier do1 = l1.getDossier();
	        Dossier do2 = l2.getDossier();
	        
	        if (do1.getDateFin() == null) {
	            return 1;
	        }else if (do2.getDateFin() == null) {
	        	return -1;
	        }else{
	        	if(do1.getDateFin().before(do2.getDateFin())){
	        		return 1;
	            }else if(do2.getDateFin().before(do1.getDateFin())){
	            	return -1;
	            }else{
	            	return 0;
	            }
	        }
	    }else {
	       return -1;
	    }
	} 
}

abstract class SujetInteretGalerieRCB implements RowCallbackHandler{
	private Map<Sujet, SujetInteretGalerieVO> sujetInteretGalerieMap;
	
	protected abstract void ajouterDossier(SujetInteretGalerieVO sujetInteretGalerieVO, Dossier dossier);
	
	private SujetInteretGalerieRCB(Map<Sujet, SujetInteretGalerieVO> sujetInteretGalerieMap) {
		super();
		this.sujetInteretGalerieMap = sujetInteretGalerieMap;
	}

	/*
	 *   L_SU_CLE                      SU_SUJET.L_SU_CLE%TYPE,
	 *   SU_L_SI_CLE                   SU_SUJET.L_SI_CLE%TYPE,
	 *   L_DO_CLE                      DO_DOSSIER.L_DO_CLE%TYPE, 
	 *   DO_L_SI_CLE                   DO_DOSSIER.L_SI_CLE%TYPE,
	 *   I_NA_CLE                      NA_NATURE.I_NA_CLE%TYPE,
	 *   V_DO_ANCIENNE_REFERENCE       DO_DOSSIER.V_DO_ANCIENNE_REFERENCE%TYPE);
	 */
	public void processRow(ResultSet rs) throws SQLException {
		Sujet sujet = new SujetVO(rs.getLong(1),rs.getLong(2));
		Dossier dossier = new DossierVO(rs.getLong(3),rs.getLong(4));
		dossier.setNature(rs.getLong(5));
		dossier.setNumeroDossier(rs.getString(6));
		SujetInteretGalerieVO sujetInteretGalerieVO = new SujetInteretGalerieVO(sujet);
		
		if (sujetInteretGalerieMap.containsKey(sujet))
			sujetInteretGalerieVO = sujetInteretGalerieMap.get(sujet);
		ajouterDossier(sujetInteretGalerieVO, dossier);
		sujetInteretGalerieMap.put(sujetInteretGalerieVO.getSujet(), sujetInteretGalerieVO);
	}
};

/**
 * �criture d'une photo, appel�e par la m�thode "insert" .
 * Elle remplace en mode insertion l'autre m�thode editPhoto. Elle tient compte
 * de la nouvelle mani�re de g�rer les images (depuis mai 2005) � partir d'un
 * champ BLOB et non plus d'un fichier.
 * Proc�dure appel�e : CARDEX_LIEN.SP_E_LMM_LIEN_MULTIMEDIA
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param photo Photo : photo saisie � l'�cran.
 * @param action  java.lang.String : U ou I
 * @param genreFichier String : code � deux lettres de la table qui lie la photo
 * (Dossier (DO), Sujet (SU), Soci�t� (SO), V�hicule (VE)).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Photo
 */
private Photo editPhoto(CardexAuthenticationSubject subject, 
			Photo photo,
			String action, 
			String genreFichier) throws DAOException {
  Connection connection = DAOConnection.getInstance().getConnection(subject);
  CallableStatement callableStatement = null;
  Statement commandeSql = null;
  ResultSet resultSet = null;
  Photo newPhoto = new PhotoVO();
  try {
	  	connection.setAutoCommit(false);
        callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LMM_LIEN_MULTIMEDIA (?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
        log.fine("CARDEX_LIEN.SP_E_LMM_LIEN_MULTIMEDIA");
        log.fine("Action: " + action);
        log.fine("Photo: " + photo);
        log.fine("genreFichier: " + genreFichier);

        if (photo.getImage() != null && photo.getImage().length == 0)
        	throw new DAOException("Image de taille � 0");
        
        callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
        callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
        callableStatement.registerOutParameter(6, java.sql.Types.DECIMAL);
        callableStatement.registerOutParameter(7, java.sql.Types.DECIMAL);
        callableStatement.registerOutParameter(11, java.sql.Types.DECIMAL);
        callableStatement.registerOutParameter(12, java.sql.Types.DECIMAL);

        callableStatement.setString(1,action);
        OracleDAOUtils.setLong(callableStatement,2, photo.getSite());
        OracleDAOUtils.setLong(callableStatement,3, photo.getCle());
        OracleDAOUtils.setLong(callableStatement,4,photo.getTypeMultimedia());
        callableStatement.setString(5, photo.getDescription());
        OracleDAOUtils.setLong(callableStatement,6, photo.getLienMultimedia());
        OracleDAOUtils.setLong(callableStatement,7, photo.getLienSiteMultimedia());
        OracleDAOUtils.setLong(callableStatement,8, photo.getLien());
        OracleDAOUtils.setLong(callableStatement,9, photo.getLienSite());
        callableStatement.setString(10, genreFichier);
        OracleDAOUtils.setLong(callableStatement,11, photo.getLienElement());
        OracleDAOUtils.setLong(callableStatement,12, photo.getLienSiteElement());
        log.fine("extension : " + photo.getExtension());
        String extension = "";
        if(StringUtils.isNotEmpty(photo.getExtension())){
        	extension = photo.getExtension().toUpperCase();
        }else{
        	extension = "JPG";
        }
        callableStatement.setString(13, extension );
        callableStatement.setLong(14,photo.getConfidentialite());
        callableStatement.execute();
        

        newPhoto.setCle(callableStatement.getLong(3));
        newPhoto.setSite(callableStatement.getLong(2));
        newPhoto.setLienElement(callableStatement.getLong(11));
        newPhoto.setLienSiteElement(callableStatement.getLong(12));
        newPhoto.setExtension(extension);
       
        if ("I".equals(action)){
			ajoutFichier(photo.getImage(), String.valueOf(newPhoto.getLienElement()), extension, connection);
			if (GlobalConstants.Image.EXTENTION_IMAGE_COMPRESSION_ACCEPTE.indexOf(extension) > -1)
				ajoutFichier(construirePetiteImage(photo.getImage()), newPhoto.getLienElement()+"p", "JPG", connection);
        }

        if ("D".equals(action)){
        	supprimerFichier(String.valueOf(newPhoto.getLienElement()), extension, connection);
			
			if (GlobalConstants.Image.EXTENTION_IMAGE_COMPRESSION_ACCEPTE.indexOf(extension) > -1)
				supprimerFichier(newPhoto.getLienElement()+"p", "JPG", connection);
        }
		//connection.commit();
		connection.setAutoCommit(true);

		return newPhoto;
	} catch (Exception se) {
		ExceptionHandler.getInstance().traiterException(subject, se);
		throw new DAOException(se);
	} finally {
		if(callableStatement != null) {
			try {
					callableStatement.close();
		    } catch (java.sql.SQLException e) {
                throw new DAOException(e);
	        }
		}
         if (commandeSql != null){
            try{
              commandeSql.close();
            }catch (SQLException se) {
			System.out.println(se.getMessage());
            }
         }
         if (resultSet != null){
            try{
              resultSet.close();
            }catch (SQLException se) {
			System.out.println(se.getMessage());
            }
         }
		    if (connection != null) {
            try{
		         if(!connection.getAutoCommit())
		         {
		            connection.rollback();
		         }
		           	   connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
		    }
  }
}
            
/**
 * Mise � jour d'un lien multim�dia, appel�e par la m�thode "updateLienMultimedia" .
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param photo Photo : lien photo moidifi� � l'�cran.
* @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 */
    public void updateLienMultimedia(final Photo photo, CardexAuthenticationSubject subject) throws DAOException {
        
        Connection connection = DAOConnection.getInstance().getConnection(subject);
        StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
        PreparerCallableStatement rch = new PreparerCallableStatement() {
            
            public void preparer(CallableStatement callableStatement) throws SQLException  {
                callableStatement.setLong(1, photo.getLienMultimedia());
                callableStatement.setLong(2, photo.getLienSiteMultimedia());
                callableStatement.setLong(3, photo.getConfidentialite());
            }
        };

        try {
            connection.setAutoCommit(false);
            storeProcTemplate.prepareCall("CARDEX_LIEN.SP_U_LMM_LIEN_MULTIMEDIA", 3, rch);
            storeProcTemplate.query(false);
            //connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception se) {
            ExceptionHandler.getInstance().traiterException(subject, se);
            throw new DAOException(se);
        }
        finally {
            if (connection != null) {
                try {
                    if (!connection.getAutoCommit()) {
                        connection.rollback();
                    }
                    connection.close();
                }
                catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
        }
    }
  
	private void ajoutFichier(final byte[] byteFile, final String numeroFichier, final String extension, final Connection connection) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
    			//Blob newBlob = BLOB.createTemporary(connection, false, oracle.sql.BLOB.DURATION_SESSION);
				Blob newBlob = construireBlob(connection);
    			newBlob.setBytes(1,byteFile); // Si cette ligne ne fonctionne pas c'est que le driver BD est pas bon.
    			
    			callableStatement.setBlob(1, newBlob);
    			callableStatement.setString(2, numeroFichier+"."+extension);
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_AJOUT_FICHIER", 2, rch);
		storeProcTemplate.query(false);	
	}
	
	private void supprimerFichier(final String numeroFichier, final String extension, final Connection connection) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1, numeroFichier+"."+extension);
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_DELETE_FICHIER", 1, rch);
		storeProcTemplate.query(false);	
	}	
	
	private Blob construireBlob(Connection connection) throws SQLException{
		CallableStatement callableStatement = null;
		try{
			callableStatement = connection.prepareCall("{ call DBMS_LOB.CREATETEMPORARY(?, TRUE)}");
			callableStatement.registerOutParameter(1, OracleTypes.BLOB);
			callableStatement.execute();
			return (Blob)callableStatement.getObject(1);
		} finally {
			if ( callableStatement != null ) {
				try {callableStatement.close();} catch (Throwable e) {}
			}
		}
	}
	
	/**
	 * 
	 * @param byteImageSource
	 * @return
	 * @throws IOException
	 */
	private byte[] construirePetiteImage(byte[] byteImageSource) throws IOException{
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(byteImageSource));
		ByteArrayOutputStream thumbnailOutStream = new ByteArrayOutputStream();
		boolean isImageModifiee = false;
		
		if (bufferedImage == null){
			throw new RuntimeException("Probl�me de creation d'image");
		}
		
		if (bufferedImage.getHeight() > GlobalConstants.Image.MAX_THUMBNAIL_HEIGHT) {
			Image thumbnail = bufferedImage.getScaledInstance(-1, GlobalConstants.Image.MAX_THUMBNAIL_HEIGHT, Image.SCALE_SMOOTH);
			
			if (thumbnail.getWidth(null) > 0)
				bufferedImage = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null), BufferedImage.TYPE_INT_RGB);
			else
				bufferedImage = new BufferedImage(1, thumbnail.getHeight(null), BufferedImage.TYPE_INT_RGB);
			bufferedImage.getGraphics().drawImage(thumbnail, 0, 0, null);
			isImageModifiee = true;
		}
		if (bufferedImage.getWidth() > GlobalConstants.Image.MAX_THUMBNAIL_WIDTH) {
			Image thumbnail = bufferedImage.getScaledInstance(GlobalConstants.Image.MAX_THUMBNAIL_WIDTH, -1, Image.SCALE_SMOOTH);
			
			if (thumbnail.getHeight(null) > 0)
				bufferedImage = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null), BufferedImage.TYPE_INT_RGB);
			else
				bufferedImage = new BufferedImage(thumbnail.getWidth(null), 1, BufferedImage.TYPE_INT_RGB);
			bufferedImage.getGraphics().drawImage(thumbnail, 0, 0, null);
			isImageModifiee = true;
		}
		if (isImageModifiee == false){
			BufferedImage rgbImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
	        ColorConvertOp op = new ColorConvertOp(null);
	        op.filter(bufferedImage, rgbImage);
	        bufferedImage = rgbImage;
		}
		ImageIO.write(bufferedImage, "jpg", thumbnailOutStream);
		thumbnailOutStream.close();
		
		return thumbnailOutStream.toByteArray();
	}
	
/**
 * Appel de la m�thode editPhoto pour la cr�ation d'une photo
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param photo Photo : photo saisie � l'�cran.
 * @param genreFichier String : code identifiant la table source qui lie une photo
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Photo
 */
    public Photo insert(CardexAuthenticationSubject subject, Photo photo, String genreFichier) throws DAOException {
      return editPhoto(subject,photo,"I", genreFichier);
    }

/**
 * Appel de la m�thode editPhoto pour la mise � jour d'une photo
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param photo Photo : photo saisie � l'�cran
 * @param genreFichier String : code identifiant la table source qui lie une photo
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 */
    public void update(CardexAuthenticationSubject subject, Photo photo, String genreFichier) throws DAOException {
      editPhoto(subject, photo,"U", genreFichier);
    }
    

/**
 * Appel de la m�thode editPhoto pour la suppression d'une photo
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param photo Photo : photo saisie � l'�cran
 * @param genreFichier String : code identifiant la table source qui lie une photo
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Photo
 */
    public Photo delete(CardexAuthenticationSubject subject, Photo photo, String genreFichier) throws DAOException {
      return editPhoto(subject,photo,"D", genreFichier);
    }

/**
 * Lecture des photos associ�es � une entit� (Dossier, Sujet, Soci�t�, V�hicule).
 * Proc�dure appel�e : CARDEX_LIRE_LIEN.SP_L2_LMM_LIEN_MULTIMEDIA
 * Date de cr�ation : (2002-01-28)
 * 
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param cle long : cl� de r�f�rence de l'entit�
 * @param site long : site de r�f�rence de l'entit�
 * @param genreFichier String : identification de l'entit�.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Collection : liste des photos associ�es
 */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject, long cle, long site, Timestamp dateLiaison, String genreFichier) throws DAOException {
      log.fine("findLiensPhoto()");
	  //return findLiensPhotoBLOB(subject, cle, site, genreFichier);
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L2_LMM_LIEN_MULTIMEDIA (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         ArrayList results = new ArrayList();
         while (resultSet.next()){
              PhotoVO linkedPhoto = new PhotoVO();
              linkedPhoto.setSite(resultSet.getLong("L_MM_REF_SITE"));
              linkedPhoto.setCle(resultSet.getLong("L_MM_CLE"));
              linkedPhoto.setTypeMultimedia(resultSet.getLong("I_TM_CLE"));
              linkedPhoto.setDescription(resultSet.getString("V_MM_DESCRIPTION"));
              linkedPhoto.setLienMultimedia(resultSet.getLong("L_LMM_CLE"));
              linkedPhoto.setLienSiteMultimedia(resultSet.getLong("L_SI_CLE"));
              linkedPhoto.setLien(resultSet.getLong("L_LMM_REFERENCE"));
              linkedPhoto.setLienSite(resultSet.getLong("L_LMM_REF_SITE"));
              linkedPhoto.setLienElement(resultSet.getLong("L_EM_CLE"));
              linkedPhoto.setLienSiteElement(resultSet.getLong("L_EM_SI_CLE"));
              linkedPhoto.setExtension(resultSet.getString("V_MM_EXTENSION"));
              linkedPhoto.setDateCreation(resultSet.getTimestamp("D_MM_DATE_CREATION"));
              linkedPhoto.setIntervenant(resultSet.getString("V_MM_CREE_PAR"));
              linkedPhoto.setSelectionner(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_MM_SELECTIONNER")));
              //linkedPhoto.setUrl(ServeurFichier.getURLLocalisationServeurDeFichier(user.getSite()) + linkedPhoto.getLienElement() + linkedPhoto.getLienSiteElement()+"."+linkedPhoto.getExtension());
              linkedPhoto.setUrl("");
              log.fine("   [Photo id='"+linkedPhoto.getCle()+"' Site='"+linkedPhoto.getSite()+"']");
              results.add(linkedPhoto);
         }//while
         return results;
      }catch (SQLException se) {
          throw new DAOException(se);
//      }catch (BusinessResourceException bre) {
//          throw new DAOException(bre);
      } finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
      } //finally
   }

    /**
     * Lecture des photos historiques associ�es � une entit� (Dossier, Sujet, Soci�t�, V�hicule).
     * Proc�dure appel�e : CARDEX_LIRE_LIEN.SP_L2_LMM_LIEN_MULTIMEDIA
     * Date de cr�ation : (2002-01-28)
     * 
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
     * @param cle long : cl� de r�f�rence de l'entit�
     * @param site long : site de r�f�rence de l'entit�
     * @param genreFichier String : identification de l'entit�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     * @return Collection : liste des photos associ�es
     */
        public Collection findLiensPhotoAudit(CardexAuthenticationSubject subject, long cle, long site, Timestamp dateLiaison, String genreFichier) throws DAOException {
          log.fine("findLiensPhoto()");
    	  //return findLiensPhotoBLOB(subject, cle, site, genreFichier);
          Connection connection = DAOConnection.getInstance().getConnection(subject);
    	  CallableStatement callableStatement = null;
    	  ResultSet resultSet = null;
          try {
             callableStatement =
                connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L2_LMM_LIEN_MULTIMEDIA (?,?,?,?,?); end;");
             callableStatement.setLong(1,cle);
             callableStatement.setLong(2,site);
             callableStatement.setString(3,genreFichier);
             if(dateLiaison == null){
             	callableStatement.setNull(4, java.sql.Types.TIMESTAMP);
             }else{
             	callableStatement.setTimestamp(4,dateLiaison);
             }
             callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
             callableStatement.execute();
             resultSet = (ResultSet)callableStatement.getObject(5);
             ArrayList results = new ArrayList();
             while (resultSet.next()){
                  PhotoVO linkedPhoto = new PhotoVO();
                  linkedPhoto.setSite(resultSet.getLong("L_MM_REF_SITE"));
                  linkedPhoto.setCle(resultSet.getLong("L_MM_CLE"));
                  linkedPhoto.setTypeMultimedia(resultSet.getLong("I_TM_CLE"));
                  linkedPhoto.setDescription(resultSet.getString("V_MM_DESCRIPTION"));
                  linkedPhoto.setLienMultimedia(resultSet.getLong("L_LMM_CLE"));
                  linkedPhoto.setLienSiteMultimedia(resultSet.getLong("L_SI_CLE"));
                  linkedPhoto.setLien(resultSet.getLong("L_LMM_REFERENCE"));
                  linkedPhoto.setLienSite(resultSet.getLong("L_LMM_REF_SITE"));
                  linkedPhoto.setLienElement(resultSet.getLong("L_EM_CLE"));
                  linkedPhoto.setLienSiteElement(resultSet.getLong("L_EM_SI_CLE"));
                  linkedPhoto.setExtension(resultSet.getString("V_MM_EXTENSION"));
                  linkedPhoto.setDateCreation(resultSet.getTimestamp("D_MM_DATE_CREATION"));
                  linkedPhoto.setSelectionner(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_MM_SELECTIONNER")));
                  //linkedPhoto.setUrl(ServeurFichier.getURLLocalisationServeurDeFichier(user.getSite()) + linkedPhoto.getLienElement() + linkedPhoto.getLienSiteElement()+"."+linkedPhoto.getExtension());
                  linkedPhoto.setUrl("");
                  log.fine("   [Photo id='"+linkedPhoto.getCle()+"' Site='"+linkedPhoto.getSite()+"']");
                  results.add(linkedPhoto);
             }//while
             return results;
          }catch (SQLException se) {
              throw new DAOException(se);
//          }catch (BusinessResourceException bre) {
//              throw new DAOException(bre);
          } finally {
    			if(resultSet != null) {
    				try {
    						resultSet.close();
    			    } catch (java.sql.SQLException e) {
                        throw new DAOException(e);
    		        }
    	        }
    			if(callableStatement != null) {
    				try {
    						callableStatement.close();
    			    } catch (java.sql.SQLException e) {
                        throw new DAOException(e);
    		        }
    			}
     		    if (connection != null) {
                    try{
    			         if(!connection.getAutoCommit())
    			         {
    			            connection.rollback();
    			         }
     		           	   connection.close();
                    } catch (SQLException e) {
                        throw new DAOException(e);
                    }
     		    }
          } //finally
       }
    
/**
 * Routine pour traiter les ResultSet retourn�s par les recherches de photo.
 * Date de cr�ation : (2002-02-20)
 * @author Fran�ois Gu�rin
 * @param resultSet  ResultSet : donn�es retourn�es par une recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ArrayList : liste des r�sultats trait�s.
 */
    public RowCallbackHandler constuireRowCallBackHandler(final List<Photo> photoList){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			   Photo photo = construirePhoto(resultSet);
  			   photoList.add(photo); 					
  		   }


  	   };
     }    

	private Photo construirePhoto(/*CardexAuthenticationSubject subject,*/ ResultSet resultSet) throws SQLException {
        PhotoVO photo = new PhotoVO();
        photo.setSite(resultSet.getLong("L_SI_CLE"));
        photo.setCle(resultSet.getLong("L_LMM_CLE"));
        photo.setTypeMultimedia(resultSet.getLong("I_TM_CLE"));
        photo.setDescription(OracleDAOUtils.getString(resultSet, "V_MM_DESCRIPTION"));
        photo.setLienMultimedia(resultSet.getLong("L_MM_CLE"));
        photo.setLienSiteMultimedia(resultSet.getLong("L_MM_REF_SITE"));
        photo.setLien(resultSet.getLong("L_LMM_REFERENCE"));
        photo.setLienSite(resultSet.getLong("L_LMM_REF_SITE"));
        photo.setLienElement(resultSet.getLong("L_EM_CLE"));
        photo.setLienSiteElement(resultSet.getLong("L_EM_SI_CLE"));
        photo.setGenreFichier(OracleDAOUtils.getString(resultSet, "C_LMM_REF_GENRE"));
        photo.setIntervenant(OracleDAOUtils.getString(resultSet, "V_LMM_CREE_PAR"));
        photo.setDateCreation(resultSet.getTimestamp("D_LMM_DATE_CREATION"));
        photo.setReference(OracleDAOUtils.getString(resultSet, "REFERENCE"));
		//photo.setNumeroDossier(OracleDAOUtils.getString(resultSet, "DOSSIER"));
        photo.setSeverite(resultSet.getLong("SEVERITE"));
		//photo.setCleDossier(resultSet.getLong("L_DO_CLE"));
		//photo.setSiteDossier(resultSet.getLong("SITE_DOSSIER"));
        //photo.setExtension(resultSet.getString("V_MM_EXTENSION"));
        //photo.setIndex(i++);
        //oracle.sql.BLOB image = (oracle.sql.BLOB)resultSet.getBlob("B_EM_ELEMENT");
        //photo.setImage(image.getBytes(1, (int)image.length()));

        String genreFichier = OracleDAOUtils.getString(resultSet, "C_LMM_REF_GENRE").trim();
        if (GlobalConstants.GenreFichier.DOSSIER.equals(genreFichier)){
            DossierVO dossier = new DossierVO();
            dossier.setCle(photo.getLien());
            dossier.setSite(photo.getLienSite());
            dossier.setNumeroCardex(photo.getReference());
            dossier.setDateDebut(resultSet.getTimestamp(DATE_DEBUT_DOSSIER));
            dossier.setDateFin(resultSet.getTimestamp(DATE_FIN_DOSSIER));
/*            dossier.setGenre(resultSet.getLong(PhotoDAO.GENRE_DOSSIER));
            dossier.setNature(resultSet.getLong(PhotoDAO.NATURE_DOSSIER));
            dossier.setConfidentialite(resultSet.getLong(PhotoDAO.CONFIDENTIALITE_DOSSIER));
*/          
            photo.setDossier(dossier);
            photo.setAttachDossier(true);
        }else if (GlobalConstants.GenreFichier.SUJET.equals(genreFichier)) {
            SujetVO sujet = new SujetVO();
            sujet.setCle(photo.getLien());
            sujet.setSite(photo.getLienSite());
            sujet.setNumeroFiche(photo.getReference());
            photo.setSujet(sujet);

            DossierVO dossier = new DossierVO();
			/*dossier.setCle(photo.getCleDossier());
			dossier.setSite(photo.getSiteDossier());
			dossier.setNumeroDossier(photo.getNumeroDossier());*/
            dossier.setDateDebut(resultSet.getTimestamp(DATE_DEBUT_DOSSIER));
            dossier.setDateFin(resultSet.getTimestamp(DATE_FIN_DOSSIER));
            /*dossier.setGenre(resultSet.getLong(PhotoDAO.GENRE_DOSSIER));
            dossier.setNature(resultSet.getLong(PhotoDAO.NATURE_DOSSIER));
            dossier.setConfidentialite(resultSet.getLong(PhotoDAO.CONFIDENTIALITE_DOSSIER));
            */
            photo.setDossier(dossier);
            photo.setAttachSujet(true);
        }
		return photo;
	}
    
  private ArrayList traitementResultats(CardexAuthenticationSubject subject, ResultSet resultSet) throws DAOException {
       ArrayList results = new ArrayList();
       try { //On limite le nombre d'enregistrements retourn�s � 5000.
       	    //int i = 0;
            while (resultSet.next() && results.size() < GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_GALERIE){
            	GererTacheUtilisateur.verifierThreadCourrant();
            	Photo photo = construirePhoto(resultSet);
            	//photo.setUrl(ServeurFichier.getURLLocalisationServeurDeFichier(user.getSite()) + photo.getLienElement() + photo.getLienSiteElement()+"."+photo.getExtension());
            	results.add(photo);
         }
         return results;
      } catch (SQLException se) {
          throw new DAOException(se);
//      } catch (BusinessResourceException be) {
//          throw new DAOException(be);
      }
    }

/**
 * Lecture des pi�ces jointes associ�es � une entit� Dossier.
 * Proc�dure appel�e : SP_L3_LMM_LIEN_MULTIMEDIA
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param cle long : cl� de r�f�rence de l'entit�
 * @param site long : site de r�f�rence de l'entit�
 * @param genreFichier String : identification de l'entit�.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Collection : liste des photos associ�es
 */
    public Collection findLiensPieceJointe(CardexAuthenticationSubject subject, long cle, long site, String genreFichier) throws DAOException {
      log.fine("findLiensPieceJointe()");
	  //return findLiensPieceJointeBLOB(subject, cle, site, genreFichier);
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin SP_L3_LMM_LIEN_MULTIMEDIA (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         ArrayList results = new ArrayList();
         while (resultSet.next()){
              PhotoVO linkedPhoto = new PhotoVO();
              linkedPhoto.setSite(resultSet.getLong("L_MM_REF_SITE"));
              linkedPhoto.setCle(resultSet.getLong("L_MM_CLE"));
              linkedPhoto.setTypeMultimedia(resultSet.getLong("I_TM_CLE"));
              linkedPhoto.setDescription(resultSet.getString("V_MM_DESCRIPTION"));
              linkedPhoto.setLienMultimedia(resultSet.getLong("L_LMM_CLE"));
              linkedPhoto.setLienSiteMultimedia(resultSet.getLong("L_SI_CLE"));
              linkedPhoto.setLien(resultSet.getLong("L_LMM_REFERENCE"));
              linkedPhoto.setLienSite(resultSet.getLong("L_LMM_REF_SITE"));
              linkedPhoto.setLienElement(resultSet.getLong("L_EM_CLE"));
              linkedPhoto.setLienSiteElement(resultSet.getLong("L_EM_SI_CLE"));
              linkedPhoto.setExtension(resultSet.getString("V_MM_EXTENSION").toUpperCase());
              linkedPhoto.setDateCreation(resultSet.getTimestamp("D_MM_DATE_CREATION"));
              linkedPhoto.setIntervenant(resultSet.getString("V_MM_CREE_PAR"));
//              linkedPhoto.setUrl(ServeurFichier.getURLLocalisationServeurDeFichier(user.getSite()) + linkedPhoto.getLienElement() + linkedPhoto.getLienSiteElement()+"."+linkedPhoto.getExtension());
              linkedPhoto.setUrl("");
              linkedPhoto.setConfidentialite(resultSet.getLong("I_CC_CLE"));
              log.fine("   [Photo id='"+linkedPhoto.getCle()+"' Site='"+linkedPhoto.getSite()+"']");
              results.add(linkedPhoto);
         }//while
         return results;
      }catch (SQLException se) {
          throw new DAOException(se);
//      }catch (BusinessResourceException bre) {
//          throw new DAOException(bre);
      } finally {
 			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
      } //finally
    }

/**
 * Recherche de photos � l'aide de crit�res de recherche dans la Galerie.
 * Cette fonction est appel�e par select() si l'option de recherche
 * des dossiers a �t� s�lectionn�e dans les crit�res.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L_galerie PACKAGE CARDEX_SPECIAL).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-25)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRecherchePhoto : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des photos retourn�es par la recherche.
 */
    public List<Photo> selectDossier(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Photo> photoList = new ArrayList<Photo>();
    	PreparerSQL preparerSQL = (new CompletDossierGalerieConstruireRechercheSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(photoList));
 	   
    	return photoList;	
    }
    
    public Integer nombreDeGalerieDossierRecherche(CardexAuthenticationSubject subject,CriteresRecherchePhoto criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new CountDossierGalerieConstruireRechercheSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }        
    

/**
 * Recherche de photos � l'aide de crit�res de recherche dans la Galerie.
 * Cette fonction est appel�e par select() si l'option de recherche
 * des sujets a �t� s�lectionn�e dans les crit�res.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L_galerie PACKAGE CARDEX_SPECIAL).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-25)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRecherchePhoto : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des photos retourn�es par la recherche.
 */
    public List<Photo> selectSujet(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria) throws DAOException {
    	
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Photo> photoList = new ArrayList<Photo>();
    	PreparerSQL preparerSQL = (new CompletSujetGalerieConstruireRechercheSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(photoList));
    	
    	assignerSujetInteretDosser(subject, criteria, photoList);
 	   
    	return photoList;	
    }
    
    private void assignerSujetInteretDosser(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria, List<Photo> photoList) throws DAOException{
    	SujetInteretGalerieCache sujetInteretGalerie = SujetInteretGalerieCache.getInstanceOf(subject);
    	boolean isCritereActif = criteria.getStatut() == 0 || GlobalConstants.Statut.DOSSIER_ACTIF == criteria.getStatut();
    	NatureDossierPredicate natureDossierPredicate = new NatureDossierPredicate(criteria.getNature());
    	
    	for (Photo photo:photoList){
    		Set<Dossier> sujetInteretDossiers = null;
    		
    		if (isCritereActif)
    			sujetInteretDossiers = sujetInteretGalerie.getActifSujetInteretDossier(photo.getSujet());
    		else
    			sujetInteretDossiers = sujetInteretGalerie.getInactifSujetInteretDossier(photo.getSujet());
    		photo.setSujetInteretDossiers( sujetInteretDossiers );
    		CollectionUtils.filter(photo.getSujetInteretDossiers(), natureDossierPredicate);
    	}
    }
    
    public Integer nombreDeGalerieSujetRecherche(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new CountSujetGalerieConstruireRechercheSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }     
    

/**
 * Recherche de photos � l'aide de crit�res de recherche dans la Galerie.
 * Cette fonction est appel�e par select() si l'option de recherche
 * des rep�rages a �t� s�lectionn�e dans les crit�res.
 * Un rep�rage est un incident d'expulsion d'un client auto-exclus.  Un dossier
 * de rep�rage est alors cr�� et reli� au dossier d'autoexclusion.
 * Il est cependant utile pour les agents de s�curit� d'identifier dans la Galerie les
 * clients rep�r�s r�cemment.  Pour ce faire, la proc�dure d'affichage des photos doit
 * remonter du dossier de rep�rage � celui de l'autoexclusion et, de l�, � la fiche du
 * sujet o� la photo est associ�e.
 * Cette fonction sp�cifique remonte simplement d'un niveau de plus pour retrouver
 * la photo du sujet � afficher.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L_galerie PACKAGE CARDEX_SPECIAL).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-03-18)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRecherchePhoto : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des photos retourn�es par la recherche.
 */
    public List<Photo> selectReperage(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Photo> photoList = new ArrayList<Photo>();
    	PreparerSQL preparerSQL = (new CompletReperageSujetGalerieConstruireRechercheSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(photoList));
    	
    	return photoList;	
    }
    
    public Integer nombreDeGalerieReperageRecherche(CardexAuthenticationSubject subject, CriteresRecherchePhoto criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new CountReperageSujetGalerieConstruireRechercheSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }     
    

/**
 * Recherche de photos � l'aide de crit�res de recherche dans la Galerie.
 * Cette fonction est appel�e par select() si l'option de recherche directe
 * des dossiers a �t� s�lectionn�e dans les crit�res.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L_galerie PACKAGE CARDEX_SPECIAL).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-25)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRecherchePhoto : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des photos retourn�es par la recherche.
 */
    public List<Photo> selectDossierDirect(final CardexAuthenticationSubject subject,CriteresRecherchePhoto criteresRecherchePhoto) throws DAOException{
    	final List<Photo> photoList = new ArrayList<Photo>();
		JDBCTemplate template = new JDBCTemplate(subject);
		PreparerSQL preparerSQL = (new DossierDirectCompletSujetGalerieConstruireRechercheSQL()).construireSQL(subject, criteresRecherchePhoto);
		
		RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				photoList.add( construirePhoto(rs) );
			}
		};
		template.query(preparerSQL, callbackHandler);
		return photoList;
    }//selectDossierDirect

/**
 * Recherche de photos � l'aide de crit�res de recherche dans la Galerie.
 * Cette fonction est appel�e par select() si l'option de recherche directe
 * des sujets a �t� s�lectionn�e dans les crit�res.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L_galerie PACKAGE CARDEX_SPECIAL).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-03-18)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRecherchePhoto : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des photos retourn�es par la recherche.
 */
    public List<Photo> selectSujetDirect(CardexAuthenticationSubject subject,CriteresRecherchePhoto criteresRecherchePhoto) throws DAOException{
    	final List<Photo> photoList = new ArrayList<Photo>();
		JDBCTemplate template = new JDBCTemplate(subject);
		PreparerSQL preparerSQL = (new SujetDirectCompletSujetGalerieConstruireRechercheSQL()).construireSQL(subject, criteresRecherchePhoto);
		
		RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				photoList.add( construirePhoto(rs) );
			}
		};
		template.query(preparerSQL, callbackHandler);
		return photoList;    	
    }//selectSujetDirect

	public FichierMultimediaVO obtenirFichier(CardexAuthenticationSubject subject, final long cle, final long site, boolean grandeImage) throws DAOException {
		Connection connection = null;
		
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
			PreparerCallableStatement rch = new PreparerCallableStatement() {

				public void preparer(CallableStatement callableStatement)
						throws SQLException {
					callableStatement.setLong(1, cle);
					callableStatement.setLong(2, site);
					callableStatement.registerOutParameter(3,
							OracleTypes.CURSOR);
				}
			};
			storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_LIRE_FICHIER", 3, 3, rch);
			UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					String extension = rs.getString(1);
					long typeMultimedia = rs.getLong(2);
					long cle = rs.getLong(3);
					long site = rs.getLong(4);
					String genre = rs.getString(5).trim();
					object = new FichierMultimediaVO(extension, typeMultimedia,
							cle, site, genre);
				}
			};
			storeProcTemplate.query(unEnregistrementPresent, false);
			//On interroge la base de donn�es pour ramener le contenu de l'image plac� dans un un champ byte.
			FichierMultimediaVO fichierMultimediaVO = (FichierMultimediaVO) unEnregistrementPresent.getObject();
			if (GlobalConstants.Image.EXTENTION_IMAGE_COMPRESSION_ACCEPTE.indexOf(fichierMultimediaVO.getExtention()) > -1){
				if (grandeImage == false){
					//On prend la petite image pour l'affichage.
					fichierMultimediaVO.setImageByte(lireFichier(connection, cle + "p.JPG"));
				}else{
					//On prend l'image originale lors de la visualisation
					fichierMultimediaVO.setImageByte(lireFichier(connection, cle + "." + fichierMultimediaVO.getExtention()));						
				}
			}
			if (fichierMultimediaVO.getImageByte() == null){
					fichierMultimediaVO.setImageByte(lireFichier(connection, cle + "." + fichierMultimediaVO.getExtention()));
			}else{
				if (fichierMultimediaVO.getImageByte().length == 0){
					fichierMultimediaVO.setImageByte(lireFichier(connection, cle + "." + fichierMultimediaVO.getExtention()));
				}
			}
			if (GlobalConstants.GenreFichier.DOSSIER.equals(fichierMultimediaVO.getGenre())) {
				
				Dossier dossier = FabriqueCardexDAO.getInstance().getDossierDAO().find( subject, new DossierVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite()));
				fichierMultimediaVO.setConfidentialite(dossier .getConfidentialite());
				fichierMultimediaVO.setEntite(dossier.getEntite());

			} else if (GlobalConstants.GenreFichier.SUJET.equals(fichierMultimediaVO.getGenre())) {
				Sujet sujet = FabriqueCardexDAO.getInstance().getSujetDAO().find( subject, new SujetVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite()));
				fichierMultimediaVO.setConfidentialite(sujet.getConfidentialite());
				fichierMultimediaVO.setEntite(sujet.getEntite());

			} else if (GlobalConstants.GenreFichier.SOCIETE.equals(fichierMultimediaVO.getGenre())) {
				Societe societe = FabriqueCardexDAO.getInstance().getSocieteDAO().find( subject,new SocieteVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite()));
				fichierMultimediaVO.setConfidentialite(societe.getConfidentialite());
				fichierMultimediaVO.setEntite(societe.getEntite());

			} else if (GlobalConstants.GenreFichier.VEHICULE.equals(fichierMultimediaVO.getGenre())) {
				Vehicule vehicule = VehiculeDAO.find(subject, new VehiculeVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite()));
				fichierMultimediaVO.setConfidentialite(vehicule.getConfidentialite());
				fichierMultimediaVO.setEntite(vehicule.getEntite());
			}
			return fichierMultimediaVO;
		} finally {
			try {
				
				if (connection != null){
					//connection.commit();
					connection.setAutoCommit(true);
			        connection.close();
				}
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		}
	}

	//Cette proc�dure est appel�e par le diff�r� CDX_00016 pour v�rifier si les �l�ments multim�dias ajout�s sont bien pr�sents
	//sur le serveur.
	public FichierMultimediaVO fichierPresent(CardexAuthenticationSubject subject, Photo photoVO, boolean petiteImage) throws DAOException, IOException {
		Connection connection = null;
		
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			//On ramen�ne le contenu de l'image pour le placer dans un champ byte.
			FichierMultimediaVO fichierMultimediaVO = new FichierMultimediaVO(photoVO.getExtension(), photoVO.getTypeMultimedia(),
					photoVO.getLienMultimedia(), photoVO.getLienSiteMultimedia(), photoVO.getGenreFichier());
				String extension = fichierMultimediaVO.getExtention();
				if (petiteImage){
						//On cherche la petite image.
						fichierMultimediaVO.setImageByte(lireFichier(connection, photoVO.getLienMultimedia() + "p.JPG"));
						fichierMultimediaVO.setExtention("p.JPG");
				}else{
					fichierMultimediaVO.setImageByte(lireFichier(connection, photoVO.getLienMultimedia() + "." + extension));
					fichierMultimediaVO.setExtention("."+extension);
				}
			return fichierMultimediaVO;
		} finally {
			try {
				
				if (connection != null){
					//connection.commit();
					connection.setAutoCommit(true);
			        connection.close();
				}
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		}
	}

	private byte[] lireFichier(Connection connection, final String nomFichier) throws DAOException {
		FonctionTemplate fonctionTemplate = new FonctionTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, java.sql.Types.BLOB);
    			callableStatement.setString(2, nomFichier);
			}
    	};		
		
    	fonctionTemplate.prepareCall("CARDEX_LIEN.F_LIRE_FICHIER", 1, rch);

    	try{
	    	Blob blob = (Blob) fonctionTemplate.query(false);	
	    	//On covertir le blob en bytes pour l'affichage
	    	int blobTaille = (int) blob.length();  
	    	byte[] blobBytes = blob.getBytes(1, blobTaille);
	    	return blobBytes;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	//2013-01-23
	//Cette fonction remplace lireFichier dans un BLOB, en raison de la migration
	//des BD Oracle sur Linux. Dans cet environnement, la lecture du r�pertoire de photos ne peut plus
	//se faire � partir de la base de donn�es. Cette t�che est maintenant effectu�e dans l'application.
	//2013-03-14
	//En raison de contraintes techniques, cette proc�dure est temporairement annul�e. L'ancienne proc�dure lireFicher a �t�
	//modifi�e pour traiter des bytes au lieu d'un blob.
/*	private byte[] lireFichierByte(final String nomFichier) throws IOException  {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
            File image = new File(nomFichier);
            FileInputStream fis = new FileInputStream(image);
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); 
            }
            fis.close();            
		}catch(FileNotFoundException ex) {
            System.out.println("Le fichier suivant n'a pas �t� trouv� : " + nomFichier );
            ex.printStackTrace();
        }
        catch(IOException ex) {
            System.out.println("Le fichier suivant n'a pas �t� lu correctement : " + nomFichier);					
            ex.printStackTrace();
        }
        return bos.toByteArray();
	}
*/
	
	void chargerSujetInteretGalerie(CardexAuthenticationSubject subject, final Map<Sujet, SujetInteretGalerieVO> sujetInteretGalerieMap) throws DAOException{
		SujetInteretGalerieRCB actifSujetInteretGalerieRCB = new SujetInteretGalerieRCB(sujetInteretGalerieMap){
			@Override
			protected void ajouterDossier(SujetInteretGalerieVO sujetInteretGalerieVO, Dossier dossier) {
				sujetInteretGalerieVO.getActifDossierSujetInteret().add(dossier);
			}
		};
		SujetInteretGalerieRCB inactifSujetInteretGalerieRCB = new SujetInteretGalerieRCB(sujetInteretGalerieMap){
			@Override
			protected void ajouterDossier(SujetInteretGalerieVO sujetInteretGalerieVO, Dossier dossier) {
				sujetInteretGalerieVO.getInactifDossierSujetInteret().add(dossier);
			}
		};
		
		chargerSujetInteretGalerie(subject, "Cardex_Lire_Doc.SP_L_SU_SI_GALERIE_ACTIF", actifSujetInteretGalerieRCB);
		chargerSujetInteretGalerie(subject, "Cardex_Lire_Doc.SP_L_SU_SI_GALERIE_INACTIF", inactifSujetInteretGalerieRCB);
	}
	
	private void chargerSujetInteretGalerie(CardexAuthenticationSubject subject, String storeProc, RowCallbackHandler rch) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement pcs = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall(storeProc, 1, 1, pcs);
		storeProcTemplate.query(rch);
	}
	
}
