package mappy.test;

public class PriceComparer implements Compare {

	public final boolean isBetter(Itinerary i1, Itinerary i2) {
		
		return i1.getPrice() < i2.getPrice();
		
	}
	
	   @Override
	   public String toString() {
	     return "";
	   }

}
