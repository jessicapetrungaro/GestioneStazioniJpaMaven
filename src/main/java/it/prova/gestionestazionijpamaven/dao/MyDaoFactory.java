package it.prova.gestionestazionijpamaven.dao;

import it.prova.gestionestazionijpamaven.dao.citta.CittaDAO;
import it.prova.gestionestazionijpamaven.dao.citta.CittaDAOImpl;
import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDAO;
import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDAOImpl;
import it.prova.gestionestazionijpamaven.dao.treno.TrenoDAO;
import it.prova.gestionestazionijpamaven.dao.treno.TrenoDAOImpl;

public class MyDaoFactory {

	private static CittaDAO cittaDaoInstance = null;
	private static TrenoDAO trenoDaoInstance = null;
	private static StazioneDAO stazioneDaoInstance = null;

	public static CittaDAO getCittaDAOInstance() {
		if (cittaDaoInstance == null)
			cittaDaoInstance = new CittaDAOImpl();

		return cittaDaoInstance;
	}

	public static TrenoDAO getTrenoDAOInstance() {
		if (trenoDaoInstance == null)
			trenoDaoInstance = new TrenoDAOImpl();

		return trenoDaoInstance;
	}

	public static StazioneDAO getStazioneDAOInstance() {
		if (stazioneDaoInstance == null)
			stazioneDaoInstance = new StazioneDAOImpl();

		return stazioneDaoInstance;
	}
}
