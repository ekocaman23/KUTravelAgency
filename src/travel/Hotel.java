package travel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Hotel {
	private int hotelID;
	private static int hotelNumber = 1;
	
	private String name;
	private String location;
	private double pricePerNight;
	private String roomType;
	private int availableRooms;
	private double distanceToAirport;
	private static ArrayList<Hotel> hotelList = new ArrayList<>();
	
	/**
	 * @param name
	 * @param location
	 * @param pricePerNight
	 * @param roomType
	 * @param availableRooms
	 * @param distanceToAirport
	 */
	public Hotel(String name, String location, double pricePerNight, String roomType, int availableRooms, double distanceToAirport) {
		this.name = name;
		this.location = location;
		this.pricePerNight = pricePerNight;
		this.roomType = roomType;
		this.availableRooms = availableRooms;
		this.distanceToAirport = distanceToAirport;
		this.hotelID = hotelNumber++;
		
		hotelList.add(this);
	}

	
	
	public int getHotelID() {
		return hotelID;
	}


	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getAvailableRooms() {
		return availableRooms;
	}
	
	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}

	public double getDistanceToAirport() {
		return distanceToAirport;
	}

	public static ArrayList<Hotel> getHotelList() {
		return hotelList;
	}
	
	public static Hotel findHotelFromID(int hotelID) {
		for (Hotel h : getHotelList()) {
			 if (h.getHotelID() == hotelID) {
				 return h;
			 }
		}
		
		return null;
	}

	public static ArrayList<Hotel> searchHotel(String name, String location, double[] priceInterval, String roomType, double[] distanceInterval) {
		ArrayList<Hotel> specificHotels = new ArrayList<>();
		for (Hotel h : getHotelList()) {
			if (h.getAvailableRooms() == 0) {
				continue;
			}
			
			if (name != null) {
				if (h.getName().equals(name) != true) {
					continue;
				}
			}
			
			if (location != null) {
				if (h.getLocation().equals(location) != true) {
					continue;
				}
			}
			
			if (priceInterval != null) {
				if (priceInterval[0] > h.getPricePerNight() || h.getPricePerNight() > priceInterval[1]) {
					continue;
				}
			}
			
			if (roomType != null) {
				if (h.getRoomType().equals(roomType) != true) {
					continue;
				}

			}
			
			if (distanceInterval != null) {
				if (distanceInterval[0] > h.getDistanceToAirport() || h.getDistanceToAirport() > distanceInterval[1]) {
					continue;
				}
			}
			
			/*
			if (amenities != null) {
				boolean noAminity = false;
				for (String searchedAminity : amenities) {
					if (h.getAmenities().contains(searchedAminity) != true) {
						noAminity = true;
					}
				}
				
				if (noAminity == true) {
					continue;
				}
			}
			*/
			
			specificHotels.add(h);
		}
		
		return specificHotels;
	}
	
	public static int highestPrice() {
		List<Double> prices = new ArrayList<>();
		for (Hotel hotel : getHotelList()) {
			prices.add(hotel.getPricePerNight());
		}
		
		return Collections.max(prices).intValue();
	}
	
	public static int lowestPrice() {
		List<Double> prices = new ArrayList<>();
		for (Hotel hotel : getHotelList()) {
			prices.add(hotel.getPricePerNight());
		}
		
		return Collections.min(prices).intValue();
	}
	
	public static int highestDistance() {
		List<Double> distances = new ArrayList<>();
		for (Hotel hotel : getHotelList()) {
			distances.add(hotel.getDistanceToAirport());
		}
		
		return Collections.max(distances).intValue();
	}
	
	
	public static int lowestDistance() {
		List<Double> distances = new ArrayList<>();
		for (Hotel hotel : getHotelList()) {
			distances.add(hotel.getDistanceToAirport());
		}
		
		return Collections.min(distances).intValue();
	}
	
	public void bookHotel() {
		if (getAvailableRooms() > 0) {
			setAvailableRooms(getAvailableRooms() - 1);
		}
	}
}
