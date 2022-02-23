/**
 * 
 */
package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.EnchereDAO;

public class EnchereDAOImpl implements EnchereDAO {

	private static final String INSERT ="INSERT INTO ENCHERES(date_enchere, montant_enchere,no_article,no_utilisateur) VALUES(?,?,?,?)";
	private static final String SELECT_BY_USER ="SELECT date_enchere, montant_enchere, no_utilisateur FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String SELECT_ALL_BY_ARTICLES ="SELECT date_enchere, montant_enchere, no_article , no_utilisateur FROM ENCHERES WHERE no_article = ?";
	
	@Override
	public void ajouterNouvelleEnchere(Integer noArticle, Integer noEncheriste, Integer montantNouvelleEnchere) throws DALException {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT);

			pStmt.setDate(1, Date.valueOf(LocalDate.now()));
			pStmt.setInt(2, montantNouvelleEnchere);
			pStmt.setInt(3, noArticle);
			pStmt.setInt(4, noEncheriste);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	
	}
	@Override
	public List<Enchere> selectAllEncheresByUser(Utilisateur utilisateur) throws DALException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_USER);
			pStmt.setInt(1, utilisateur.getNoUtilisateur());
		
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), utilisateur);
						
				lstEnchere.add(enchere);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return lstEnchere;
	
	}
	
	public List<Enchere> selectAllEncheresByArticle(ArticleVendu article) throws DALException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_BY_ARTICLES);
			pStmt.setInt(1, article.getNoArticle());
		
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				Enchere enchere = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), article , rs.getInt("no_utilisateur"));
						
				lstEnchere.add(enchere);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return lstEnchere;
	
	}

}
