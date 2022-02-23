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

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.UtilisateurDAO;

/**
 * Classe en charge d'effectuer des traitements en base de données
 */
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private final static String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
	
	private final static String SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur=?";
	
	private final static String INSERT = "INSERT INTO UTILISATEURS(pseudo,nom,prenom, email, telephone,rue,code_postal, ville, mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	private final static String SELECT_BY_PSEUDO ="SELECT no_utilisateur, pseudo, nom,prenom, email, telephone,rue, code_postal, ville FROM UTILISATEURS WHERE pseudo = ?";
	
	private final static String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=? WHERE no_utilisateur=?";
	
	private final static String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = (SELECT credit FROM UTILISATEURS WHERE no_utilisateur=?) + ? WHERE no_utilisateur=?";
	
	private final static String DELETE ="DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	
	/**
	 * Méthode en charge de retourner la liste de tous les utilisateurs du site
	 * @return liste des utilisateurs
	 * @throws DALException
	 */
	@Override
	public List<Utilisateur> getAllUtilisateurs() throws DALException {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				Utilisateur utilisateur = map(rs);
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		
		return utilisateurs;
	}
	
	/**
	 * Méthode en charge de sélectionner un utilisateur par son numéro
	 * @return un utilisateur
	 */
	@Override
	public Utilisateur selectUtilisateurById(Integer no_utilisateur) throws DALException {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
			pStmt.setInt(1, no_utilisateur);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				utilisateur = map(rs);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return utilisateur;
	}


	/**
	 * Méthode en charge d'ajouter un nouvel utilisateur à la base de données
	 */
	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt;
				pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				mappStmt(utilisateur, pStmt);
				pStmt.setInt(10, utilisateur.getCredit());
				pStmt.setBoolean(11, utilisateur.getAdministrateur());
				pStmt.executeUpdate();

				ResultSet rs = pStmt.getGeneratedKeys();
				if (rs.next()) {
					Integer idGenere = rs.getInt(1);
					utilisateur.setNoUtilisateur(idGenere);
				}
			} catch (SQLException e) {
				throw new DALException(e.getMessage());
			}
		}
	

	/*
	 * Selectionne un utilisateur par son pseudo
	 */
	@Override
	public Utilisateur getByPseudo(String pseudoUtilisateur) throws DALException {
		Utilisateur utilisateur = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			pStmt.setString(1, pseudoUtilisateur);
			ResultSet rs= pStmt.executeQuery();
			while(rs.next()) {
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));				
				utilisateur.setVille(rs.getString("ville"));
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		return utilisateur;
	}

	/*
	 * Modifier son compte utilisateur
	 */
	@Override
	public void modifierUtilisateur(Utilisateur utilisateurModif) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
			mappStmt(utilisateurModif, pStmt);
			pStmt.setInt(10, utilisateurModif.getNoUtilisateur());
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
	
	/*
	 * Modifier le crédit de l'utilisateur
	 */
	@Override
	public void modifierCreditUtilisateur(Integer noUtilisateur, Integer montantCredit) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_CREDIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, montantCredit);
			pStmt.setInt(3, noUtilisateur);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}


	/*
	 * Supprimer son compte utilisateur
	 */
	
	@Override
	public void supprimerUtilisateur(Integer idUtilisateur) throws DALException {
		try(Connection cnx= ConnectionProvider.getConnection()){
			PreparedStatement pStmt = cnx.prepareStatement(DELETE);
			pStmt.setInt(1, idUtilisateur);
			pStmt.executeUpdate();			
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		
	}
	
	/**
	 * Méthode en charge de préparer une requête SQL
	 * @param un objet Utilisateur
	 * @param pStmt
	 * @throws SQLException
	 */
	public void mappStmt(Utilisateur utilisateurModif, PreparedStatement pStmt) throws SQLException {
		pStmt.setString(1, utilisateurModif.getPseudo());
		pStmt.setString(2, utilisateurModif.getNom());
		pStmt.setString(3, utilisateurModif.getPrenom());
		pStmt.setString(4, utilisateurModif.getEmail());
		pStmt.setString(5, utilisateurModif.getTelephone());
		pStmt.setString(6, utilisateurModif.getRue());
		pStmt.setString(7, utilisateurModif.getCodePostal());
		pStmt.setString(8, utilisateurModif.getVille());
		pStmt.setString(9, utilisateurModif.getMotDePasse());
	}

	/**
	 * Méthode en charge de traiter le résultat d'une requête SQL
	 * @param rs
	 * @return un objet Utilisateur
	 * @throws SQLException 
	 */
	private Utilisateur map(ResultSet rs) throws SQLException {
		Integer noUtilisateur = rs.getInt("no_utilisateur");
		String pseudo = rs.getString("pseudo");
		String nom = rs.getString("nom");
		String prenom  = rs.getString("prenom");
		String email  = rs.getString("email");
		String telephone  = rs.getString("telephone");
		String rue  = rs.getString("rue");
		String codePostal  = rs.getString("code_postal");
		String ville  = rs.getString("ville");
		String motDePasse  = rs.getString("mot_de_passe");
		Integer credit  = rs.getInt("credit");
		Boolean administrateur  = rs.getBoolean("administrateur");
		
		Utilisateur utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		
		return utilisateur;
	}

	
}
