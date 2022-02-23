/**
 * 
 */
package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.RetraitDAO;

/**
 * Classe en charge de traiter les données de la table RETRAITS
 */
public class RetraitDAOImpl implements RetraitDAO {

	private final static String INSERT = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville) VALUES(?,?,?,?)";
	@Override
	public void ajouterRetrait(Retrait retrait) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt;
			pStmt = cnx.prepareStatement(INSERT);
			pStmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			pStmt.setString(2, retrait.getLieu());
			pStmt.setString(3, retrait.getCodePostal());
			pStmt.setString(4, retrait.getVille());

			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

}
