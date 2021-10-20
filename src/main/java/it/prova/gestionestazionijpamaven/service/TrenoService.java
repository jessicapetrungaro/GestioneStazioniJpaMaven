package it.prova.gestionestazionijpamaven.service;

import java.util.List;

import it.prova.gestionestazionijpamaven.dao.treno.TrenoDAO;
import it.prova.gestionestazionijpamaven.model.Citta;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;

public interface TrenoService {
	
	public List<Treno> listAll() throws Exception;

	public Treno caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Treno trenoInstance) throws Exception;

	public void inserisciNuovo(Treno trenoInstance) throws Exception;

	public void rimuovi(Treno trenoInstance) throws Exception;
	
	public List<Treno> cercaTreniDiUnaCitta(Citta cittaInstance) throws Exception;
	
	public void scollegaStazione(Stazione stazioneInstance, Treno trenoInstance) throws Exception;
	
	public List<Integer> cercaNumeroAbitantiByTreno(Long id) throws Exception;	
	
	// per injection
		public void setTrenoDAO(TrenoDAO trenoDAO);
}

