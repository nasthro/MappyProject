package mappy.test;

import java.util.Iterator;
import java.util.List;

public class Main {
	

	/**
	 * 
	 * Per il test dell'applicazione vengono creati
	 * quattro itinerari formati da più connessioni
	 * o da una singola connessione, viene utilizzata
	 * la classe Muppy per creare gli itinerari,
	 * successivamente vengono chiamati i metodi 
	 * getItinerary della classe muppy, il primo
	 * contiene un comparer per la comparazione
	 * in base a Km, Prezzo, Tempo e Salti di percorso
	 * e restituisce l'itinerario migliore in base ai
	 * parametri dati, il secondo restituisce una lista 
	 * basata sulla citta di partenza e quella di arrivo.
	 * 
	 * 
	 * 
	 * 
	 * @param args parametro di avvio del metodo main
	 */
	
	
	public static void main(String[] args) {

		try {
	
			// Primo Itinerario : Percorso ROMA -> MILANO
			
			MainRoad con1 = new MainRoad();		
			Motorway con2 = new Motorway();	
			Expressway con3 = new Expressway();	
			
			con1.setFrom("ROMA");
			con1.setTo("PESCARA");
			con1.setKm(200);
			con1.setTime(2);
			con1.setNumber(2);
			con1.setTrafficLight(true);		
		
		
			con2.setFrom("PESCARA");
			con2.setTo("BOLOGNA");
			con2.setKm(400);
			con2.setTime(4);
			con2.setPrice(150);
			
		
			con3.setFrom("BOLOGNA");
			con3.setTo("MILANO");
			con3.setKm(500);
			con3.setTime(4);
			con3.setName("mainway");
			con3.setSafe(50);
	
			
			Mappy mappy = new Mappy();
			
			mappy.setName("Itinerario 1");
			
			mappy.add(con1);
			
			mappy.add(con2);
			
			mappy.add(con3);
			
			mappy.save();
			

			
			// Secondo Itinerario : Percorso ROMA -> MILANO
			
			Expressway con4 = new Expressway();		
			Motorway con5 = new Motorway();	
	
			
			con4.setFrom("ROMA");
			con4.setTo("PESCARA");
			con4.setKm(200);
			con4.setTime(2);
			con4.setName("streetway");
			con4.setSafe(150);
		
		
			con5.setFrom("PESCARA");
			con5.setTo("MILANO");
			con5.setKm(800);
			con5.setTime(7);
			con5.setPrice(350);
			
			mappy.setName("Itinerario 2");
	
			mappy.add(con4);
			
			mappy.add(con5);
			
			mappy.save();
			
			
			// Secondo Itinerario : Percorso ROMA -> MILANO
			
			
			
			Motorway con6 = new Motorway();	
			Expressway con7 = new Expressway();		
			MainRoad con8 = new MainRoad();
			
			con6.setFrom("ROMA");
			con6.setTo("TERAMO");
			con6.setKm(301.50f);
			con6.setTime(3);
			con6.setPrice(250.00f);
		
		
			con7.setFrom("TERAMO");
			con7.setTo("BOLOGNA");
			con7.setKm(600.00f);
			con7.setTime(3);
			con7.setName("mainway");
			con7.setSafe(50.00f);	
			
			con8.setFrom("BOLOGNA");
			con8.setTo("MILANO");
			con8.setKm(404.70f);
			con8.setTime(2);
			con8.setNumber(2);
			con8.setTrafficLight(true);	
			
			mappy.setName("Itinerario 3");
	
			mappy.add(con6);
			
			mappy.add(con7);
			
			mappy.add(con8);
			
			mappy.save();

			
			// Secondo Itinerario : Percorso ROMA -> MILANO
						
			
			Motorway con9 = new Motorway();	
			
			con9.setFrom("ROMA");
			con9.setTo("MILANO");
			con9.setKm(1150.50f);
			con9.setTime(12);
			con9.setPrice(250.00f);
	
			mappy.setName("Itinerario 4");
			
			mappy.add(con9);
			
			mappy.save();
			
			System.out.println("####### REPORT ELENCO ITINERARI: #######\n");			
		
			List<ItineraryStep> itineraries = mappy.getItinerary("ROMA", "MILANO");
			
			Iterator<ItineraryStep> iterator = itineraries.iterator();
			
			int counter = 0;
			
			while(iterator.hasNext()) {
				
				ItineraryStep itin = iterator.next();
				System.out.println("ITINERARIO N. " + ++counter + " => Km." + itin.getKilometer() + " Price: " + itin.getPrice() + " Time: " + itin.getTime() + " Steps: " + itin.getSteps());			
				
			}
			
			System.out.println("\n####### ELENCO COMPARAZIONE ITINERARI #######\n");
			
			KilometerComparer kmcomp = new KilometerComparer();
			
			ItineraryStep itinerary = mappy.getItinerary("ROMA", "MILANO", kmcomp);
			
			System.out.println("ITINERARIO CON KM INFERIORI: NOME: " + itinerary.getName() + " - KM TOT: "  + itinerary.getNext().getKilometer());			

				
			
			PriceComparer pricecomp = new PriceComparer();
			
			itinerary = mappy.getItinerary("ROMA", "MILANO", pricecomp);
			
			System.out.println("ITINERARIO CON PREZZO INFERIORE: NOME: " + itinerary.getName()  + " - PRICE TOT: "  + itinerary.getNext().getPrice());			
			
			TimeComparer timecomp = new TimeComparer();
			
			itinerary = mappy.getItinerary("ROMA", "MILANO", timecomp);
			
			System.out.println("ITINERARIO CON TEMPO INFERIORE: NOME: " + itinerary.getName()  + " - TIME TOT: "  + itinerary.getNext().getTime());	
			
			StepsComparer stepscomp = new StepsComparer();
			
			itinerary = mappy.getItinerary("ROMA", "MILANO", stepscomp);
			
			System.out.println("ITINERARIO CON SALTI INFERIORI: NOME: " + itinerary.getName()  + " - STEPS TOT: "  + itinerary.getNext().getSteps());	

		
		} catch(NoSuchItinerary ex) {
			
			System.out.println("NoSuchItinerary: " + ex.getMessage());
			
		} catch (InvalidItinerary ex) {
			
			System.out.println("InvalidItinerary: " + ex.getMessage());
		} 

	}

}
