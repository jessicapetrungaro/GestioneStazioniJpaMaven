package it.prova.gestionestazionijpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionijpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionijpamaven.dao.treno.TrenoDAO;
import it.prova.gestionestazionijpamaven.model.Citta;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;

public class TrenoServiceImpl implements TrenoService {

	private TrenoDAO trenoDAO;

	@Override
	public List<Treno> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return trenoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Treno caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return trenoDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			trenoDAO.update(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			trenoDAO.insert(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			trenoDAO.delete(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void setTrenoDAO(TrenoDAO trenoDAO) {
		this.trenoDAO = trenoDAO;

	}

	@Override
	public List<Treno> cercaTreniDiUnaCitta(Citta cittaInstance) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			trenoDAO.setEntityManager(entityManager);

			return trenoDAO.findAllTreniDiUnaCitta(cittaInstance);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	public void scollegaStazione(Stazione stazioneInstance, Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			trenoDAO.setEntityManager(entityManager);

			stazioneInstance = entityManager.merge(stazioneInstance);
			trenoInstance = entityManager.merge(trenoInstance);

			stazioneInstance.removeFromTreno(trenoInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Integer> cercaNumeroAbitantiByTreno(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			trenoDAO.setEntityManager(entityManager);

			return trenoDAO.findNumeroAbitantiByTreno(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	
	}

