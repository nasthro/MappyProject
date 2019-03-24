package mappy.test;

public class Expressway extends Connection {
	

	private String name;
	
	private float safe;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSafe() {
		return safe;
	}

	public void setSafe(float safe) {
		this.safe = safe;
	}

	
	   @Override
	   public String toString() {
	     return "";
	   }
	
}
