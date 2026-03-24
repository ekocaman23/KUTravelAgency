package travel;

import java.util.ArrayList;

public class Taxi {
	private int taxiID;
	private static int taxiNumber = 1;
	
	private String city;
	private String type;
	private double baseFare;
	private int availableTaxis;
	private double farePerKilometer;
	private static ArrayList<Taxi> taxiList = new ArrayList<>();
	
	/**
	 * @param city
	 * @param type
	 * @param baseFare
	 * @param availableTaxis
	 * @param farePerKilometer
	 */
	public Taxi(String city, String type,  int availableTaxis, double baseFare, double farePerKilometer) {
		this.city = city;
		this.type = type;
		this.availableTaxis = availableTaxis;
		this.baseFare = baseFare;
		this.farePerKilometer = farePerKilometer;
		this.taxiID = taxiNumber++;
		
		taxiList.add(this);
	}
	
	
	
	public static ArrayList<Taxi> getTaxiList() {
		return taxiList;
	}
	
	
	
	public int getTaxiID() {
		return taxiID;
	}


	public String getCity() {
		return city;
	}

	public String getType() {
		return type;
	}

	public int getAvailableTaxis() {
		return availableTaxis;
	}

	public double getBaseFare() {
		return baseFare;
	}

	public double getFarePerKilometer() {
		return farePerKilometer;
	}


	public double calculateFare(Hotel h) {
		double totalFare = getBaseFare() + h.getDistanceToAirport() * getFarePerKilometer();
		return totalFare;
	}
	
	public static Taxi findTaxiFromID(int taxiID) {
		for (Taxi t : getTaxiList()) {
			 if (t.getTaxiID() == taxiID) {
				 return t;
			 }
		}
		
		return null;
	}
	
	public static ArrayList<Taxi> searchTaxi(String city, String type) {
		ArrayList<Taxi> specificTaxis = new ArrayList<>();
		for (Taxi t : getTaxiList()) {
			if (t.getAvailableTaxis() == 0) {
				continue;
			}
			
			if (city != null) {
				if (!t.getCity().equals(city)) {
					continue;
				}
			}
			
			if (type != null) {
				if (!t.getType().equals(type)) {
					continue;
				}
			}
			
			specificTaxis.add(t);
		}
		
		
		return specificTaxis;
	}
	
	
	
	
}
