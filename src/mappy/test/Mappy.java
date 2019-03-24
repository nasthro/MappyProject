package mappy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mappy {
	

	private ItineraryStep itinerary;
	private List<ItineraryStep> itineraries;
	private Stream<ItineraryStep> streamItineraries;
	
	public Mappy() {
		
	    this.itinerary = new ItineraryStep();
		this.itinerary.setNext(Itinerary.makeEmpty()); 
		itineraries = new ArrayList<ItineraryStep>();
	}
	
	
	public List<ItineraryStep> getItineraries() {
		return itineraries;
	}

	public void setItineraries(List<ItineraryStep> itineraries) {
		this.itineraries = itineraries;
	}

	public ItineraryStep getItinerary() {
		return itinerary;
	}

	public void setItinerary(ItineraryStep itinerary) {
		this.itinerary = itinerary;
	}

	public void setName(String ItineraryName) {

		    this.itinerary.setName(ItineraryName);

	}
	
	/**
	 * 
	 * Aggiunge la connessione all'interno
	 * dell'itinerario corrente e richiama il 
	 * metodo add della classe itinerario
	 * 
	 * @param c Nuova connessione 
	 * 
	 */
	
	public void add(Connection c) {
		
		try {

			this.itinerary.setStep(c);
			
			this.itinerary.setNext(this.itinerary.add(c));
			
		} catch (InvalidItinerary e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * 
	 * Salva l'itinerario corrente all'interno
	 * dell'elenco itinerari e poi inizializza
	 * un nuovo itinerario per una successiva creazione
	 * svuotando il campo next
	 * 
	 */
	
	public void save() {
		this.itineraries.add(this.itinerary);
	    this.itinerary = new ItineraryStep();
		this.itinerary.setNext(Itinerary.makeEmpty()); 
	}
	
	/**
	 * 
	 * Metodo per la comparazione degli itinerari 
	 * 
	 * @param  From Citta di origine
	 * @param  To   Citta destinazione
	 * @param  best Interfaccia per la comparazione:
	 * Può essere di tipo:
	 * 				 - PriceComparer (restituisce quella con prezzo inferiore)
	 * 				 - StepsComparer (restituisce quella con numero salti inferiori)
	 * 				 - KilometerComparer (restituisce quella con chilometri inferiori)
	 * 				 - TimeComparer (restituisce quella con tempo inferiore)
	 * @return      Restituisce un oggetto ItinerarySte in base 
	 * 				ai criteri di filtraggio dei parametri From e To
	 * 
	 * @throws InvalidItinerary se l'itinerario contiene parametri sbagliati
	 * @throws NoSuchItinerary se non viene individuato alcun itinerario
	 * 
	 */
	
	public ItineraryStep getItinerary(String From, String To, Compare best) throws InvalidItinerary, NoSuchItinerary {	
		
		if (To.equals("") || From.equals(""))
			throw new InvalidItinerary();
		
		streamItineraries = itineraries.stream();
		
		ItineraryStep itineraryReturn = new ItineraryStep();
			
		List<ItineraryStep> itineraryLst = new ArrayList<ItineraryStep>();
		
		itineraryLst = streamItineraries.filter(c -> c.getConnections().get(0).getFrom().equalsIgnoreCase(From) 
				&& c.getConnections().get(c.getConnections().size()-1).getTo().equalsIgnoreCase(To)).collect(Collectors.toList());
		
		streamItineraries.close();
		
		if(itineraryLst.size() == 0) 				
			throw new NoSuchItinerary();
		
		if( itineraryLst.size() > 1 ) {
			
			for( int x = 0; x < itineraryLst.size(); x++) {
				
				for( int y = x + 1; y < itineraryLst.size(); y++) {
					
					if(best.isBetter(itineraries.get(x), itineraryLst.get(y)) == false){
						
						itineraryReturn = itineraryLst.get(x);
						itineraryLst.set(x, itineraryLst.get(y));
						itineraryLst.set(y, itineraryReturn);

					}
				}
			}
		}

		itineraryReturn = itineraryLst.get(0);
		
		return itineraryReturn;
		
	}
	
	/** 
	 * 
	 * Metodo per la creazione di un elenco degli itinerari 
	 * 
	 * @param  From Citta di origine
	 * @param  To   Citta destinazione
	 * @return      Restituisce un oggetto List ItineraryStep
	 * 				contenente tutti gli itinerari inseriti corrispondenti
	 * 				ai criteri di filtraggio dei parametri From e To
	 * 
	 * @throws InvalidItinerary se l'itinerario contiene parametri sbagliati
	 * @throws NoSuchItinerary se non viene individuato alcun itinerario
	 * 
	 * 
	 */
	
	public List<ItineraryStep> getItinerary(String From, String To) throws InvalidItinerary, NoSuchItinerary {
			
		if (To.equals("") || From.equals(""))
			throw new InvalidItinerary();
		
		streamItineraries = itineraries.stream();
	
		List<ItineraryStep> itineraryLst = new ArrayList<ItineraryStep>();
		
		itineraryLst = streamItineraries.filter(c -> c.getConnections().get(0).getFrom().equalsIgnoreCase(From) 
				&& c.getConnections().get(c.getConnections().size()-1).getTo().equalsIgnoreCase(To)).collect(Collectors.toList());

		streamItineraries.close();
		
		if(itineraryLst.size() == 0) 				
			throw new NoSuchItinerary();
		
		return itineraryLst;

	}
	
	   @Override
	   public String toString() {
	     return "";
	   }

}
