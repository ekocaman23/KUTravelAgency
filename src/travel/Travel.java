package travel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

import login_page.Customer;

public class Travel {
	private int travelID;
	private int type;
	private String name;
	
	private ArrayList<Flight> flights = new ArrayList<>();
	private double totalFlightPrice;
	
	private ArrayList<Hotel> hotels = new ArrayList<>();
	private double totalHotelPrice;
	private  ArrayList<Integer> howManyNights = new ArrayList<>();
	
	private ArrayList<Taxi> taxis = new ArrayList<>();
	private double totalTaxiPrice;
	private static ArrayList<Travel> travelList = new ArrayList<Travel>();
	
	private static ArrayList<Travel> allTravelList = new ArrayList<Travel>();

	private static int travelCounter = 1;

	/**
	 * @param name
	 * @param flights
	 * @param hotels
	 * @param taxis
	 */
	public Travel(String name, ArrayList<Flight> flights, ArrayList<Hotel> hotels, ArrayList<Taxi> taxis) {
		SecureRandom secureRandom = new SecureRandom();
        
		int randomID = 100000 + secureRandom.nextInt(900000);
        
        while (IDExists(randomID)) {
        	randomID = 100000 + secureRandom.nextInt(900000);
        }
		
		this.travelID = randomID;
		this.type = 1;

		this.name = name;
		this.flights = flights;
		this.hotels = hotels;
		this.taxis = taxis;
		
		allTravelList.add(this);
	}
	
	public Travel(int travelID, ArrayList<Flight> flights, ArrayList<Hotel> hotels, ArrayList<Taxi> taxis) {
		this.travelID = travelID;
		this.type = 1;

		this.name = name;
		this.flights = flights;
		this.hotels = hotels;
		this.taxis = taxis;
		
		allTravelList.add(this);
	}
	
	public boolean IDExists(int ID) {
		for (Travel travel : allTravelList) {
			if (travel.getTravelID() == ID) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	public double getTotalFlightPrice() {
		return totalFlightPrice;
	}



	public void setTotalFlightPrice(double totalFlightPrice) {
		this.totalFlightPrice = totalFlightPrice;
	}



	public double getTotalHotelPrice() {
		return totalHotelPrice;
	}



	public void setTotalHotelPrice(double totalHotelPrice) {
		this.totalHotelPrice = totalHotelPrice;
	}



	public double getTotalTaxiPrice() {
		return totalTaxiPrice;
	}



	public void setTotalTaxiPrice(double totalTaxiPrice) {
		this.totalTaxiPrice = totalTaxiPrice;
	}



	public String getName() {
		return name;
	}

	public int getTravelID() {
		return travelID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}


	public ArrayList<Taxi> getTaxis() {
		return taxis;
	}


	public static ArrayList<Travel> getTravelList() {
		return travelList;
	}

	public ArrayList<Integer> getHowManyNights() {
		return howManyNights;
	}

	public void setHowManyNights(ArrayList<Integer> howManyNights) {
		this.howManyNights = howManyNights;
	}

	public static Travel findTravelFromID(int travelID) {
		for (Travel travel : allTravelList) {
			 if (travel.getTravelID() == travelID) {
				 return travel;
			 }
		}
		
		return null;
	}
	
	public String getFinalDestination() {
		return flights.getFirst().getDepartureCity();
	}

	public String getFinalArrival() {
		return (flights.getLast().getArrivalCity() != null) ? flights.getLast().getArrivalCity()
				: flights.getLast().getFinalArrivalCity();
	}

	public String flightsToString() {
		String theFlights = "";
		for (Flight f : flights) {
			theFlights += f.getFlightID() + "|";
		}
		
		
		return theFlights;
	}

	public String hotelsToString() {
		String theHotels = "";
		
		for (Hotel h : hotels) {
			theHotels += h.getHotelID() + "|";
		}
		
		
		return theHotels;
	}
	
	public String nightsToString() {
		String theNights = "";
		
		for (int i : howManyNights) {
			theNights += i + "|";
		}
		
		
		return theNights;
	}

	public String taxisToString() {
		String theTaxis = "";
		for (Taxi t : taxis) {
			theTaxis += t.getTaxiID() + "|";
		}
		
		
		return theTaxis;
	}
	
	@Override
	public String toString() {
		String travel = "";
		travel += getTravelID() + "," + flightsToString() + "," + hotelsToString() + "," + nightsToString() + "," + taxisToString() + "," + getType() + "," + getTotalFlightPrice() + "," + getTotalHotelPrice() + "," + getTotalTaxiPrice();
		
		travel += "";
		
		return travel;
	}
	
	public static Travel fromString(String line) {
        String[] parts = line.split(",");
         
        ArrayList<Flight> flights = new ArrayList<Flight>();
        String[] flightIDs = parts[1].split("\\|");
        
        for (String flightID : flightIDs) {
        	flights.add(Flight.findFlightFromID(flightID));  
        }
        
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        String[] hotelIDs = parts[2].split("\\|");
        
        for (String hotelID : hotelIDs) {
        	hotels.add(Hotel.findHotelFromID(Integer.parseInt(hotelID)));  
        }
        
        ArrayList<Integer> howManyNights = new ArrayList<Integer>();
        String[] nights = parts[3].split("\\|");
        
        for (String night : nights) {
        	howManyNights.add(Integer.parseInt(night));
        }
        
        ArrayList<Taxi> taxis = new ArrayList<Taxi>();
        String[] taxiIDs = parts[4].split("\\|");
        
        for (String taxiID : taxiIDs) {
        	taxis.add(Taxi.findTaxiFromID(Integer.parseInt(taxiID)));  
        }
        
        double totalFlightPrice = Double.parseDouble(parts[6]);
        double totalHotelPrice = Double.parseDouble(parts[7]);
        double totalTaxiPrice = Double.parseDouble(parts[8]);
        
        Travel theTravel = new Travel( Integer.parseInt(parts[0]), flights, hotels, taxis);
        theTravel.setType(Integer.parseInt(parts[5]));
        theTravel.setHowManyNights(howManyNights);
        travelList.add(theTravel);
        
        theTravel.setTotalFlightPrice(totalFlightPrice);
        theTravel.setTotalHotelPrice(totalHotelPrice);
        theTravel.setTotalTaxiPrice(totalTaxiPrice);
        
        return theTravel;
    }
	
	
	public static void saveTravels(String fileName) {
	       try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
	           for (Travel travel : Travel.travelList) {
	               writer.write(travel.toString());
	               writer.newLine();
	           }
	       } catch (IOException ee) {
	    	   
	       }
	}
	
	public static void loadTravels(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Travel.travelList.clear();
            while ((line = reader.readLine()) != null) {
                Travel.fromString(line); 
            }
        } catch (IOException e) {
        	
        }
    }

}
