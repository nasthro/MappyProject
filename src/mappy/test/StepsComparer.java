package mappy.test;

public class StepsComparer implements Compare {

	public final boolean isBetter(Itinerary i1, Itinerary i2) {
		
		return i1.getSteps() < i2.getSteps();
	}
	
   public String toString() {
     return "";
   }

}
