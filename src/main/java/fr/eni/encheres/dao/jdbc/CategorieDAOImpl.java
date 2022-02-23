/**
 * 
 */
package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.CategorieDAO;
import fr.eni.encheres.dao.DALException;

/**
 * Classe en charge de traiter les données catégories de la BDD 
 */
public class CategorieDAOImpl implements CategorieDAO {

	private static final String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES";
	private static final String INSERT = "INSERT INTO CATEGORIES(libelle) VALUES(?)";
	
	@Override
	public List<Categorie> listeDesCategories() throws DALException {
		List<Categorie> lstCategories = new ArrayList<Categorie>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Integer no_categorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				Categorie categorie = new Categorie(no_categorie, libelle);
				lstCategories.add(categorie);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		
		return lstCategories;
	}

	@Override
	public void ajouterCategorie(Categorie categorie) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, categorie.getLibelle());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				categorie.setNoCategorie(id);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

}
