package mappy.test;

import java.util.ArrayList;
import java.util.List;

public abstract class Itinerary {

	private List<Connection> connections;

	private float Kilometer;
	
	private float Price;
	
	private int Time;
	
	private String Name;
	
	public int getSteps() {
		return this.connections.size();
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Itinerary() {

		connections = new ArrayList<Connection>();

	}
	
	/**
	 * 
	 * Restituisce tutte le connessioni dell'itinerario
	 * 
	 * @return  lista delle connessioni attive
	 * 
	 */

	public List<Connection> getConnections() {
		return connections;
	}

	/**
	 * 
	 * Aggiunge una nuova lista di connessioni all'itinerario
	 * 
	 * @param connections lista di connessioni
	 * 
	 */
	
	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	/**
	 * 
	 * Crea un itinerario vuoto
	 * 
	 * @return Ritorna un itinerario vuoto
	 * 
	 */
	
	public static Itinerary makeEmpty() {
		
		EmptyItinerary itinerary = new EmptyItinerary();		
		return itinerary;
		
	}
	
	
	/**
	 * 
	 * Metodo per l'aggiunta della connessione all'itinerario,
	 * viene utilizzato per creare uno step successivo
	 * all'itinerario corrente
	 * 
	 * @param connection Una nuova connessione
	 * 
	 * @return ritorna una nuova istanza dell'itinerario aggiornata
	 * con la connessione aggiunta
	 * 
	 * @throws InvalidItinerary Itinerario non valido
	 * 
	 */
	
	
	public Itinerary add(Connection connection) throws InvalidItinerary{

			 float kilometer = 0;		
			 float price = 0;	
			 int time = 0;
		
			if(
				connection.getTo() == "" || 
				connection.getFrom() == "" || 
				connection.getTo() == connection.getFrom()
			  ) 
			{
				throw new InvalidItinerary();
			}		

			this.getConnections().add(connection); 
			
			// Ricalcolo Km, Time e Price
			
			for( int i = 0; i < this.getConnections().size(); i++) {
				
				kilometer += this.getConnections().get(i).getKm();
				time += this.getConnections().get(i).getTime();

				if( this.getConnections().get(i) instanceof Motorway ) {
					
					price += ((Motorway)this.getConnections().get(i)).getPrice(); 
				
				}
			}
			
			this.setKilometer(kilometer); 
			
			this.setTime(time); 
				
			this.setPrice(price);
			
			return this;
		
	};
	
	public float getKilometer() {
		return Kilometer;
	}

	public float getPrice() {
		return Price;
	}

	public int getTime() {
		return Time;
	}
		
	public void setKilometer(float kilometer) {
		Kilometer = kilometer;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public void setTime(int time) {
		Time = time;
	}

	
   @Override
   public String toString() {
     return "";
   }
	   
}
