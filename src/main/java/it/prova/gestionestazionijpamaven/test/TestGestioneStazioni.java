package it.prova.gestionestazionijpamaven.test;

import java.util.Date;

import it.prova.gestionestazionijpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionijpamaven.model.Citta;
import it.prova.gestionestazionijpamaven.model.Stazione;
import it.prova.gestionestazionijpamaven.model.Treno;
import it.prova.gestionestazionijpamaven.service.CittaService;
import it.prova.gestionestazionijpamaven.service.MyServiceFacrtory;
import it.prova.gestionestazionijpamaven.service.StazioneService;
import it.prova.gestionestazionijpamaven.service.TrenoService;

public class TestGestioneStazioni {

	public static void main(String[] args) {
		StazioneService articoloServiceInstance = MyServiceFacrtory.getStazioneServiceInstance();
		CittaService cittaServiceInstance = MyServiceFacrtory.getCittaServiceInstance();
		TrenoService trenoServiceInstance = MyServiceFacrtory.getTrenoServiceInstance();

		try {

			System.out.println(".....INIZIO DEI TEST........");

			testInserisciCitta(cittaServiceInstance);

			testInserimentoStazione(cittaServiceInstance, articoloServiceInstance);

			testInserisciTreno(trenoServiceInstance);

			testAssociareStazioniACitta(cittaServiceInstance, articoloServiceInstance);

			testCollegamentoTreniAStazione(cittaServiceInstance, articoloServiceInstance, trenoServiceInstance);

			testRimozioneCitta(cittaServiceInstance, articoloServiceInstance, trenoServiceInstance);
			
			testNumeroAbitantiByTreno(cittaServiceInstance, articoloServiceInstance, trenoServiceInstance);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserisciCitta(CittaService cittaServiceInstance) throws Exception {
		System.out.println(".......testInserisciCitta inizio.............");

		long nowTimeMilliseconds = new Date().getTime();

		Citta cittaInstance = new Citta("Roma" + nowTimeMilliseconds, 50);
		cittaServiceInstance.inserisciNuovo(cittaInstance);
		if (cittaInstance.getId() == null)
			throw new RuntimeException("testInserisciCitta fallito ");

		System.out.println(".......testInserisciCitta fine: PASSED.............");
	}

	private static void testInserimentoStazione(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance) throws Exception {
		System.out.println(".......testInserimentoStazione inizio.............");

		long nowTimeMilliseconds = new Date().getTime();

		Citta cittaInstance = new Citta("Milano" + nowTimeMilliseconds, 80);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance = new Stazione("Centrale" + nowTimeMilliseconds, "Via centrale", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);

		if (stazioneInstance.getId() == null)
			throw new RuntimeException("testInserimentoStazione fallito ");

		System.out.println(".......testInserimentoStazione fine: PASSED.............");
	}

	private static void testInserisciTreno(TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testInserisciTreno inizio.............");

		long nowTimeMilliseconds = new Date().getTime();

		Treno trenoInstance = new Treno("FrecciaRossa" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoInstance);
		if (trenoInstance.getId() == null)
			throw new RuntimeException("testInserisciTreno fallito ");

		System.out.println(".......testInserisciTreno fine: PASSED.............");
	}

	private static void testAssociareStazioniACitta(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance) throws Exception {
		System.out.println(".......testAssociareStazioniACitta inizio.............");

		long nowTimeMilliseconds = new Date().getTime();

		Citta cittaInstance = new Citta("Milano" + nowTimeMilliseconds, 80);
		cittaServiceInstance.inserisciNuovo(cittaInstance);
		Long cittaTest = cittaInstance.getId();

		Stazione stazioneInstance = new Stazione("Centrale" + nowTimeMilliseconds, "Via centrale", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);

		Stazione stazioneInstance2 = new Stazione("Away" + nowTimeMilliseconds, "Via Away", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance2);

		if (cittaServiceInstance.caricaSingoloElementoEagerCategoria(cittaTest).getStazioni().size() != 2)
			throw new RuntimeException("testAssociareStazioniACitta fallito ");

		System.out.println(".......testAssociareStazioniACitta fine: PASSED.............");
	}

	private static void testCollegamentoTreniAStazione(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testCollegamentoTreniAStazione inizio.............");

		long nowTimeMilliseconds = new Date().getTime();

		Citta cittaInstance = new Citta("Napoli" + nowTimeMilliseconds, 30);
		cittaServiceInstance.inserisciNuovo(cittaInstance);
		Long cittaTest = cittaInstance.getId();

		Stazione stazioneInstance = new Stazione("Pompei" + nowTimeMilliseconds, "Via centrale", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		Long stazioneIdTest = stazioneInstance.getId();

		Treno trenoInstance1 = new Treno("FrecciaRossa" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoInstance1);
		Treno trenoInstance2 = new Treno("Italo" + nowTimeMilliseconds, 1003);
		trenoServiceInstance.inserisciNuovo(trenoInstance2);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance1);
		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance2);

		if (stazioneServiceInstance.caricaSingoloElementoEagerTreno(stazioneIdTest).getTreni().size() != 2) {
			throw new RuntimeException("testCollegamentoTreniAStazione fallito ");
		}

		System.out.println("..........testCollegamentoTreniAStazione PASSED........");

	}

	private static void testRimozioneCitta(CittaService cittaServiceInstance, StazioneService stazioneServiceInstance,
			TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testRimozioneCitta inizio.............");

		long nowTimeMilliseconds = new Date().getTime();

		Citta cittaInstance = new Citta("Napoli" + nowTimeMilliseconds, 30);
		cittaServiceInstance.inserisciNuovo(cittaInstance);
		Long cittaTest = cittaInstance.getId();

		Stazione stazioneInstance = new Stazione("Pompei" + nowTimeMilliseconds, "Via centrale", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		Long stazioneIdTest = stazioneInstance.getId();

		Treno trenoInstance1 = new Treno("FrecciaRossa" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoInstance1);
		Treno trenoInstance2 = new Treno("Italo" + nowTimeMilliseconds, 1003);
		trenoServiceInstance.inserisciNuovo(trenoInstance2);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance1);
		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance2);

		if (stazioneServiceInstance.caricaSingoloElementoEagerTreno(stazioneIdTest).getTreni().size() != 2) {
			throw new RuntimeException("test ASSOCIAZIONE TRENI A STAZIONE fallito ");
		}

		cittaServiceInstance.deleteEager(cittaTest);

		if (cittaServiceInstance.caricaSingoloElemento(cittaTest) != null) {
			throw new RuntimeException("testRimuoviOrdine fallito: cancellazione ordine fallita");
		}

		System.out.println("..........testRimozioneCitta PASSED........");

	}

	private static void testNumeroAbitantiByTreno(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testNumeroAbitantiByTreno inizio.............");

		long nowTimeMilliseconds = new Date().getTime();
		//CITTA 1
		Citta cittaNapoli = new Citta("Napoli" + nowTimeMilliseconds, 30);
		cittaServiceInstance.inserisciNuovo(cittaNapoli);
		Long cittaTestNapoli = cittaNapoli.getId();

		Stazione stazioneNapoli = new Stazione("Pompei" + nowTimeMilliseconds, "Via centrale", cittaNapoli);
		stazioneServiceInstance.inserisciNuovo(stazioneNapoli);
		Long stazioneIdTestNapoli = stazioneNapoli.getId();

		Treno trenoNapoli = new Treno("FrecciaRossa" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoNapoli);
		
		stazioneServiceInstance.aggiungiTreno(stazioneNapoli, trenoNapoli);
	
	
		//CITTA 2
		Citta cittaRoma = new Citta("Roma" + nowTimeMilliseconds, 40);
		cittaServiceInstance.inserisciNuovo(cittaRoma);
		Long cittaTestRoma = cittaRoma.getId();

		Stazione stazioneRoma = new Stazione("Termini" + nowTimeMilliseconds, "Via Termini", cittaRoma);
		stazioneServiceInstance.inserisciNuovo(stazioneRoma);
		Long stazioneIdTestRoma = stazioneRoma.getId();
		
		Treno trenoRoma = new Treno("FrecciaBianca" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoRoma);
		
		stazioneServiceInstance.aggiungiTreno(stazioneRoma, trenoRoma);
		

		//CITTA 3
		Citta cittaMilano = new Citta("Milano" + nowTimeMilliseconds, 50);
		cittaServiceInstance.inserisciNuovo(cittaMilano);
		Long cittaTestMilano = cittaMilano.getId();

		Stazione stazioneMilano = new Stazione("Centrale" + nowTimeMilliseconds, "Via centrale", cittaMilano);
		stazioneServiceInstance.inserisciNuovo(stazioneMilano);
		Long stazioneIdTestMilano = stazioneMilano.getId();
		
		Treno trenoMilano = new Treno("Italo" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoMilano);
		
		stazioneServiceInstance.aggiungiTreno(stazioneMilano, trenoMilano);

		//CITTA 4
		Citta cittaTorino = new Citta("Torino" + nowTimeMilliseconds, 60);
		cittaServiceInstance.inserisciNuovo(cittaTorino);
		Long cittaTestTorino = cittaTorino.getId();

		Stazione stazioneTorino = new Stazione("Centro" + nowTimeMilliseconds, "Via Termini", cittaTorino);
		stazioneServiceInstance.inserisciNuovo(stazioneTorino);
		Long stazioneIdTestTorino = stazioneTorino.getId();
		
		Treno trenoTorino = new Treno("Freccia" + nowTimeMilliseconds, 1540);
		trenoServiceInstance.inserisciNuovo(trenoTorino);
		Long trenoTorinoId = trenoTorino.getId();
		
		stazioneServiceInstance.aggiungiTreno(stazioneTorino, trenoTorino);
		stazioneServiceInstance.aggiungiTreno(stazioneMilano, trenoTorino);
		stazioneServiceInstance.aggiungiTreno(stazioneRoma, trenoTorino);
		stazioneServiceInstance.aggiungiTreno(stazioneNapoli, trenoTorino);
		
		
		if(trenoServiceInstance.cercaNumeroAbitantiByTreno(trenoTorinoId).size() == 0) {
			throw new RuntimeException("TEST NUMERO ABITANTI FALLITO ");
		}
		System.out.println("TEST ABITANTI OK" + trenoServiceInstance.cercaNumeroAbitantiByTreno(cittaTestTorino));
		

		
	}

}
