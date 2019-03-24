package mappy.test;

public class TimeComparer implements Compare {

	public final boolean isBetter(Itinerary i1, Itinerary i2) {
		
		return i1.getTime() < i2.getTime();
		
	}
	
	   @Override
	   public String toString() {
	     return "";
	   }

}
