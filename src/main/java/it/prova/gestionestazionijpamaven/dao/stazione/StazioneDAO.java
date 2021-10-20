package it.prova.gestionestazionijpamaven.dao.stazione;

import it.prova.gestionestazionijpamaven.dao.IBaseDAO;
import it.prova.gestionestazionijpamaven.model.Stazione;

public interface StazioneDAO extends IBaseDAO<Stazione> {

	public Stazione findByIdFetchingTreni(Long id) throws Exception;

}
