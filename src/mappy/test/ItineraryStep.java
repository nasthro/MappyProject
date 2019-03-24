package mappy.test;

class ItineraryStep extends Itinerary {
		
	private Connection step;

	private Itinerary next;
	
	public ItineraryStep() {
		this.setStep(null);
		this.setNext(null);
	}
	
	public Itinerary getNext() {
		return next;
	}

	public void setNext(Itinerary next) {
		this.next = next;
	}
	
	public Connection getStep() {
		return step;
	}

	public void setStep(Connection step) {
		this.step = step;
	}

   @Override
   public String toString() {
     return 
    		"Km: " + this.getNext().getKilometer() + " Price: " + this.getNext().getPrice() + 
    		" Time: " + this.getNext().getTime();
   }

}
