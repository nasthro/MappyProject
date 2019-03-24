package mappy.test;

public class KilometerComparer implements Compare {

	public final boolean isBetter(Itinerary i1, Itinerary i2) {

		return i1.getKilometer() < i2.getKilometer() ;
		
	}
	
	   @Override
	   public String toString() {
	     return "";
	   }

}
