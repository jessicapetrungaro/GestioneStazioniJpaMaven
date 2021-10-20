package it.prova.gestionestazionijpamaven.service;

import it.prova.gestionestazionijpamaven.dao.MyDaoFactory;

public class MyServiceFacrtory {

	private static StazioneService stazioneServiceInstance = null;
	private static CittaService cittaServiceInstance = null;
	private static TrenoService trenoServiceInstance = null;

	public static StazioneService getStazioneServiceInstance() {
		if (stazioneServiceInstance == null)
			stazioneServiceInstance = new StazioneServiceImpl();

		stazioneServiceInstance.setStazioneDAO(MyDaoFactory.getStazioneDAOInstance());

		return stazioneServiceInstance;
	}

	public static CittaService getCittaServiceInstance() {
		if (cittaServiceInstance == null)
			cittaServiceInstance = new CittaServiceImpl();

		cittaServiceInstance.setCittaDAO(MyDaoFactory.getCittaDAOInstance());

		if (stazioneServiceInstance == null)
			stazioneServiceInstance = new StazioneServiceImpl();
		cittaServiceInstance.setStazioneDAO(MyDaoFactory.getStazioneDAOInstance());

		return cittaServiceInstance;
	}

	public static TrenoService getTrenoServiceInstance() {
		if (trenoServiceInstance == null)
			trenoServiceInstance = new TrenoServiceImpl();

		trenoServiceInstance.setTrenoDAO(MyDaoFactory.getTrenoDAOInstance());

		return trenoServiceInstance;
	}
}
