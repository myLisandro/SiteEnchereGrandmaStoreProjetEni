package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.ArticleVenduDAO;
import fr.eni.encheres.dao.DALException;

/**
 * Classe en charge d'effectuer des traitements de base de donn�es
 *
 */

public class ArticleVenduDAOImpl implements ArticleVenduDAO {

	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?)";
	
	private final static String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
	
	private final static String UPDATE_ETAT_VENTE = "UPDATE ARTICLES_VENDUS SET etat_article=? WHERE no_article=?";
	
	private final static String SELECT_ALL = "SELECT no_article, nom_article, date_debut_encheres, date_fin_encheres, prix_vente, no_utilisateur,etat_article FROM ARTICLES_VENDUS";
	
	private final static String SELECT_ARTICLE_BY_USER = "SELECT av.no_article,nom_article,description, libelle AS categorie,ISNULL(MAX(montant_enchere), prix_initial) AS prix, date_debut_encheres, date_fin_encheres,r.rue,r.code_postal,r.ville, pseudo\r\n"
			+ "FROM ARTICLES_VENDUS av\r\n"
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur\r\n"
			+ "LEFT JOIN ENCHERES e ON av.no_article = e.no_article\r\n"
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie\r\n"
			+ "INNER JOIN RETRAITS r ON av.no_article = r.no_article\r\n"
			+ "GROUP BY av.no_article, nom_article, description, libelle, prix_initial, date_debut_encheres, date_fin_encheres,r.rue,r.code_postal,r.ville,pseudo";
	
	private final static String SELECT_BY_ID_BEST_ENCHERE = "SELECT TOP 1 av.no_article, nom_article,description,etat_article,libelle AS categorie, prix_initial, date_fin_encheres,r.rue, r.code_postal,r.ville, pseudo AS vendeur,av.no_utilisateur AS no_vendeur,ISNULL(MAX(montant_enchere), 0) AS \"meilleure offre\",e.no_utilisateur AS encheriste\r\n"
			+ "FROM ARTICLES_VENDUS av\r\n"
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur\r\n"
			+ "LEFT JOIN ENCHERES e ON av.no_article = e.no_article\r\n"
			+ "INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie\r\n"
			+ "INNER JOIN RETRAITS r ON av.no_article = r.no_article\r\n"
			+ "WHERE av.no_article = ?\r\n"
			+ "GROUP BY av.no_article, nom_article, description,etat_article, libelle, prix_initial, date_fin_encheres,r.rue,r.code_postal,r.ville,pseudo, av.no_utilisateur, e.no_utilisateur\r\n"
			+ "ORDER BY no_article, \"meilleure offre\" DESC";
	
	
	/**
	 * Méthode en charge d'ajouter un nouvel article dans la BDD
	 */
	@Override
	public ArticleVendu ajouterArticleAVendre (ArticleVendu articleVendu) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt;
			pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, articleVendu.getNomArticle());
			pStmt.setString(2, articleVendu.getDescription());
			pStmt.setDate(3,Date.valueOf(articleVendu.getDateDebutEncheres()));
			pStmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pStmt.setInt(5, articleVendu.getMiseAPrix());
			pStmt.setInt(6, articleVendu.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(7, articleVendu.getCategorieArticle().getNoCategorie());

			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				Integer idGenere = rs.getInt(1);
				articleVendu.setNoArticle(idGenere);
			}
					
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return articleVendu;
	}

	
	@Override
	public void updatePrixVente(Integer noArticleEnchere, Integer proposition) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_PRIX_VENTE);
			pStmt.setInt(1, proposition);
			pStmt.setInt(2, noArticleEnchere);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}		
	}
	
	@Override
	public void updateEtatVente(ArticleVendu article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ETAT_VENTE);
			pStmt.setString(1, article.getEtatVente());
			pStmt.setInt(2, article.getNoArticle());
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}		
	}
	
	@Override
	public List<ArticleVendu> getAllArticleVendu() throws DALException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		Utilisateur utilisateurVendeur;
		Categorie categorie;
		Retrait retrait;
		try(Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
		
				Integer noArticle = rs.getInt("no_article");
				String nom = rs.getString("nom_article");
				LocalDate dateDebutEncheres = (rs.getDate("date_debut_encheres")).toLocalDate();
				LocalDate dateFinEncheres = (rs.getDate("date_fin_encheres")).toLocalDate();
				Integer prixVente = rs.getInt("prix_vente");
				utilisateurVendeur = new Utilisateur(rs.getInt("no_utilisateur"));
				String etatVente = rs.getString("etat_article");
					
				ArticleVendu articleVendu = new ArticleVendu(noArticle, nom, dateDebutEncheres, dateFinEncheres, prixVente, etatVente, utilisateurVendeur);
				
				articlesVendus.add(articleVendu);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		
		return articlesVendus;
	}
	
	@Override
	//TODO modifier le nom
	//methode qui permet de recuperer une map avec comme clef un objet articles et en valeur un objet Utilisateur;
	public List<ArticleVendu> selectJointArticleUtilisateur() throws DALException  {
		List<ArticleVendu> lstArticleVendus = new ArrayList<ArticleVendu>();
		Utilisateur utilisateur ;
		Categorie categorie ;
		Retrait retrait;
		
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ARTICLE_BY_USER);
			while(rs.next()) {
			
				utilisateur = new Utilisateur(rs.getString("pseudo"));
			
				categorie = new Categorie(rs.getString("categorie"));
				
				retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				
				
				ArticleVendu articleAvecUtilisateuretCategorie = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),  rs.getString("description")
						,(rs.getDate("date_debut_encheres")).toLocalDate(),(rs.getDate("date_fin_encheres")).toLocalDate(), rs.getInt("prix"), utilisateur, categorie, retrait);
				
		
				lstArticleVendus.add(articleAvecUtilisateuretCategorie);				
				
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		
		return lstArticleVendus;
	}

	
	@Override
	public ArticleVendu selectArticleByIdBestEnchere(Integer idArticle) throws DALException {		
		ArticleVendu article = null;

		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID_BEST_ENCHERE);
			pStmt.setInt(1, idArticle);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				article = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		
		return article;
	}
	
	
	
	
	private ArticleVendu map(ResultSet rs) throws SQLException {
		Utilisateur utilisateurVendeur;
		Utilisateur utilisateurEncheriste;
		Categorie categorie;
		Retrait retrait;
		Enchere enchere;
		ArticleVendu article;
		
		Integer noArticle = rs.getInt("no_article");
		String nom = rs.getString("nom_article");
		String description = rs.getString("description");
		Integer prixInitial = rs.getInt("prix_initial");
		LocalDate dateFinEncheres = (rs.getDate("date_fin_encheres")).toLocalDate();
		String etatVente = rs.getString("etat_article");
		
		
		utilisateurVendeur = new Utilisateur(rs.getInt("no_vendeur"), rs.getString("vendeur"));
		utilisateurEncheriste = new Utilisateur(rs.getInt("encheriste"));
		
		categorie = new Categorie(rs.getString("categorie"));
		retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
		article = new ArticleVendu(noArticle, nom, description, dateFinEncheres, prixInitial, etatVente, 
				utilisateurVendeur, categorie, retrait);
		
		enchere = new Enchere(rs.getInt("meilleure offre"), article, utilisateurEncheriste);
				
		article.setEnchereMaximum(enchere);
		
		return article;
	}
	
}
