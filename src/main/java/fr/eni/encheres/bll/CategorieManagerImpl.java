/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de traiter les catégories
 * @author cgoarnisson2021
 * @date 13 janv. 2022 - 20:27:53
 * @version ProjetEncheres - V0.1  
 */
public class CategorieManagerImpl implements CategorieManager {

	private static class CategorieManagerHolder {
		private static CategorieManagerImpl instance = new CategorieManagerImpl();
	}
	
	private CategorieManagerImpl() {}
	
	public static CategorieManagerImpl getInstance() {
		return CategorieManagerHolder.instance;
		
	}
	
	@Override
	public List<Categorie> listeDesCategories() throws BLLException {
		try {
			return DAOFactory.getCategorieDAO().listeDesCategories();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public void ajouterCategorie(Categorie categorie) throws BLLException {
		try {
			DAOFactory.getCategorieDAO().ajouterCategorie(categorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

}
