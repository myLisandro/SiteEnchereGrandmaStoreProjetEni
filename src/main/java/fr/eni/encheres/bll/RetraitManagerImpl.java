/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

public class RetraitManagerImpl implements RetraitManager {

	private static class RetraitManagerHolder {
		private static RetraitManagerImpl instance = new RetraitManagerImpl();
	}
	
	private RetraitManagerImpl() {}
	
	public static RetraitManagerImpl getInstance() {
		return RetraitManagerHolder.instance;
		
	}
	
	@Override
	public void ajouterNouveauRetrait(Retrait retrait) throws BLLException {
		try {
			DAOFactory.getRetraitDAO().ajouterRetrait(retrait);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

}
