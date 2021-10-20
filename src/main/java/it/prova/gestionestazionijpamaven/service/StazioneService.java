package it.prova.gestionestazionijpamaven.service;

import java.util.List;


import it.prova.gestionestazionijpamaven.dao.stazione.StazioneDAO;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;



public interface StazioneService {

	public List<Stazione> listAll() throws Exception;

	public Stazione caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Stazione articoloInstance) throws Exception;

	public void inserisciNuovo(Stazione articoloInstance) throws Exception;

	public void rimuovi(Stazione articoloInstance) throws Exception;
	
	public void aggiungiTreno(Stazione articoloInstance, Treno trenoInstance) throws Exception;
	
	public Stazione caricaSingoloElementoEagerTreno(Long id) throws Exception;
	
	public void scollegaCategoria(Stazione articoloInstance, Treno categoriaInstance) throws Exception;

	
	// per injection
		public void setStazioneDAO(StazioneDAO stazioneDAO);
}
