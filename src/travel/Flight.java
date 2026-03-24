package travel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flight {
	private String flightID;
	private String airline;
	private String departureCity;
	private String arrivalCity;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private double ticketPrice;
	private String ticketClass;
	private int availableSeats;
	private String stopoverCity;
	private String finalArrivalCity;
	private LocalTime leg1DepartureTime;
	private LocalTime leg1ArrivalTime;
	private LocalTime leg2DepartureTime;
	private LocalTime leg2ArrivalTime;
	private boolean isDirect;
	private static ArrayList<Flight> flightList = new ArrayList<>();
	
	/**
	 * @param flightID
	 * @param airline
	 * @param departureCity
	 * @param arrivalCity
	 * @param departureTime
	 * @param arrivalTime
	 * @param ticketPrice
	 * @param ticketClass
	 * @param availableSeats
	 * @param stopoverCity
	 * @param finalArrivalCity
	 * @param leg1DepartureTime
	 * @param leg1ArrivalTime
	 * @param leg2DepartureTime
	 * @param leg2ArrivalTime
	 */
	public Flight(String flightID, String airline, String departureCity, String arrivalCity, LocalTime departureTime, 
			LocalTime arrivalTime, double ticketPrice, String ticketClass, int availableSeats) {
		this.flightID = flightID;
		this.airline = airline;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.ticketPrice = ticketPrice;
		this.ticketClass = ticketClass;
		this.availableSeats = availableSeats;
		this.stopoverCity = null;
		this.finalArrivalCity = null;
		this.leg1DepartureTime = null;
		this.leg1ArrivalTime = null;
		this.leg2DepartureTime = null;
		this.leg2ArrivalTime = null;
		this.isDirect = true;
		
		flightList.add(this);
	}
	
	public Flight(String flightID, String airline, String departureCity, double ticketPrice, String ticketClass, int availableSeats, String stopoverCity,
			String finalArrivalCity, LocalTime leg1DepartureTime, LocalTime leg1ArrivalTime,
			LocalTime leg2DepartureTime, LocalTime leg2ArrivalTime) {
		this.flightID = flightID;
		this.airline = airline;
		this.departureCity = departureCity;
		this.arrivalCity = null;
		this.departureTime = null;
		this.arrivalTime = null;
		this.ticketPrice = ticketPrice;
		this.ticketClass = ticketClass;
		this.availableSeats = availableSeats;
		this.stopoverCity = stopoverCity;
		this.finalArrivalCity = finalArrivalCity;
		this.leg1DepartureTime = leg1DepartureTime;
		this.leg1ArrivalTime = leg1ArrivalTime;
		this.leg2DepartureTime = leg2DepartureTime;
		this.leg2ArrivalTime = leg2ArrivalTime;
		this.isDirect = false;
		
		flightList.add(this);
	}
	
	
	
	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public String getAirline() {
		return airline;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public String getTicketClass() {
		return ticketClass;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public String getStopoverCity() {
		return stopoverCity;
	}
	
	public String getFinalArrivalCity() {
		return finalArrivalCity;
	}

	public LocalTime getLeg1DepartureTime() {
		return leg1DepartureTime;
	}
	
	public LocalTime getLeg2ArrivalTime() {
		return leg2ArrivalTime;
	}

	
	public boolean isDirect() {
		return isDirect;
	}

	public static ArrayList<Flight> getFlightList() {
		return flightList;
	}
	
	public static int highestTicketPrice() {
		List<Double> ticketPrices = new ArrayList<>();
		for (Flight flight : getFlightList()) {
			ticketPrices.add(flight.getTicketPrice());
		}
		
		return Collections.max(ticketPrices).intValue();
	}
	
	public static int lowestTicketPrice() {
		List<Double> ticketPrices = new ArrayList<>();
		for (Flight flight : getFlightList()) {
			ticketPrices.add(flight.getTicketPrice());
		}
		
		return Collections.min(ticketPrices).intValue();
	}
	
	public static Flight findFlightFromID(String FlightID) {
		for (Flight f : getFlightList()) {
			 if (f.getFlightID().equals(FlightID)) {
				 return f;
			 }
		}
		
		return null;
	}

	public static ArrayList<Flight> searchFlight(String name, String departureCity, String arrivalCity, 
			LocalTime[] departureTimeInterval, double[] ticketPriceInterval, 
			String stopoverCity) {
		
		ArrayList<Flight> specificFlights = new ArrayList<>();
		for (Flight f : getFlightList()) {
			if (f.getAvailableSeats() == 0) {
				continue;
			}
			
			if (name != null) {
				if (f.getAirline().equals(name) != true) {
					continue;
				}
			}
			
			if (departureCity != null) {
				if (f.getDepartureCity().equals(departureCity) != true) {
					continue;
				}
			}
			
			
			/*
			 * 
			 * 
			 * Due to the difference between direct and connecting flights
			 * 
			 * 
			 */
			
			if (f.getArrivalCity() != null) {
				if (arrivalCity != null) {
					if (f.getArrivalCity().equals(arrivalCity) != true) {
						continue;
					}
				}
			}
			
			else {
				if (arrivalCity != null) {
					if (f.getFinalArrivalCity().equals(arrivalCity) != true) {
						continue;
					}
				}
			}
			
			
			if (f.getDepartureTime() != null) {
				if (departureTimeInterval != null) {
					if (departureTimeInterval[0].isAfter(f.getDepartureTime()) || f.getDepartureTime().isAfter(departureTimeInterval[1])) {
						continue;
					}
				}
			}
			
			else {
				if (departureTimeInterval != null) {
					if (departureTimeInterval[0].isAfter(f.getLeg1DepartureTime()) || f.getLeg1DepartureTime().isAfter(departureTimeInterval[1])) {
						continue;
					}
				}
			}
			
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			
			
			if (ticketPriceInterval != null) {
				if (ticketPriceInterval[0] > f.getTicketPrice() || f.getTicketPrice() > ticketPriceInterval[1]) {
					continue;
				}

			}
			
			if (f.getStopoverCity() != null) {
				if (stopoverCity != null) {
					if (f.getStopoverCity().equals(stopoverCity) == false) {
						continue;
					}
				}
			}
			
			else {
				if (stopoverCity != null) {
					continue;
				}
			}
			
			
			specificFlights.add(f);
		}
		
		return specificFlights;
	}
	
	
	
	
	
}
