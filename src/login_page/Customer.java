package login_page;

import java.io.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import exceptions.PasswordLengthException;
import exceptions.UsernameAlreadyTakenException;
import travel.Flight;
import travel.Hotel;
import travel.Taxi;
import travel.Travel;

public class Customer extends User {
	private String username;
	private String password;
	private String name;
	private int customerID;
	private ArrayList<Travel> travelHistory;
	private Map<Integer, Integer> travelReservation;
	
	private static int customerCount = 1;
	private static ArrayList<Customer> customerList = new ArrayList<>();
	
	

	/**
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @throws Exception 
	 */
	public Customer(String username, String password, String name) throws UsernameAlreadyTakenException, PasswordLengthException {
		for (Customer c : getCustomerList()) {
			if (c.getUsername().equals(username)) {
				throw new UsernameAlreadyTakenException();
			}
		}
		
		if (password.length() < 8) {
			throw new PasswordLengthException();
		}
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.customerID = customerCount;
		this.travelHistory = new ArrayList<>();
		this.travelReservation = new HashMap<Integer, Integer>();
		
		customerList.add(this);
		customerCount++;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	
	

	private String getPassword() {
		return password;
	}

	
	public String getName() {
		return name;
	}


	public static ArrayList<Customer> getCustomerList() {
		return customerList;
	}
	
	
	
	public ArrayList<Travel> getTravelHistory() {
		return travelHistory;
	}
	
	public static Customer findCustomerFromUsername(String username) {
		for (Customer customer : customerList) {
			if(customer.getUsername().equals(username)) {
				return customer;
			}
		}
		
		return null;
	}
	
	public double[] travelCount() {
		double[] travelCounter = new double[3];
		
		travelCounter[0] = travelHistory.size();
		travelCounter[1] = 0;
		travelCounter[2] = 0;
		
		for (Travel travel : travelHistory) {
			if (travel.getType() == 2) {
				travelCounter[2] += 1;
			}
		}
		
		travelCounter[1] = travelCounter[0] - travelCounter[2];
		
		return travelCounter;
	}
	
	public double totalPrice() {
		double totalPrice = 0;
		
		for (Travel travel : travelHistory) {
			if (checkTravelReservation(travel) == 1) {
				totalPrice += travel.getTotalFlightPrice() + travel.getTotalHotelPrice() + travel.getTotalTaxiPrice();
			}
		}
		
		return totalPrice;
	}

	public void updateTravelHistory(Travel travel) {
		travelHistory.add(travel);
		Collections.sort(travelHistory, Comparator.comparingInt(Travel::getType));
	}
	
	public void updateAllTravelTypes() {
		for (Travel travel : travelHistory) {
			travel.setType(checkTravelReservation(travel));
		}
	}
	
	public void addTravelReservation(Travel travel) {
		travelReservation.put(travel.getTravelID(), 1);
	}
	
	public void updateTravelReservation(Travel travel, int value) {
		travelReservation.put(travel.getTravelID(), value);
	}
	
	public int checkTravelReservation(Travel travel) {
		
		if (!travelReservation.containsKey(travel.getTravelID())) {
			return 0;
		}
		
		else {
			return travelReservation.get(travel.getTravelID());
		}
	}
	
	public String reservationsToString() {
		String reservations = "";
		
		for (Map.Entry<Integer, Integer> entry : travelReservation.entrySet()) {
			reservations += entry.getKey() + "," + entry.getValue() + "-"; 
		}
		
		return reservations;
	}
	
	@Override
	public String toString() {
		String travels = "";
		for (Travel t : travelHistory) {
			travels += t.getTravelID() + "," + t.flightsToString() + "," + t.hotelsToString() + "," + t.nightsToString() + "," + t.taxisToString() + "," + t.getType() + "," + t.getTotalFlightPrice() + "," + t.getTotalHotelPrice() + "," + t.getTotalTaxiPrice() + "-";
		}
		
		travels += "";
		
		return username + ";" + password + ";" + name + ";" + travels + ";" + reservationsToString();
	}
	
	public static Customer fromString(String line) {
        String[] parts = line.split(";");
        Customer customer = new Customer(parts[0], parts[1], parts[2]);
        
        String[] travels = null;
        String[] reservations = null;
        
        if (parts.length > 3) {
        	travels = parts[3].split("-");
        	reservations = parts[4].split("-");
        }
         
        if (travels != null) {
        	for (String travel : travels) {
            	String[] travelParts = travel.split(",");
            	ArrayList<Flight> flights = new ArrayList<Flight>();
            	
      
            	
                String[] flightIDs = travelParts[1].split("\\|");
                
                for (String flightID : flightIDs) {
                	flights.add(Flight.findFlightFromID(flightID));  
                }
                
                ArrayList<Hotel> hotels = new ArrayList<Hotel>();
                String[] hotelIDs = travelParts[2].split("\\|");
                
                for (String hotelID : hotelIDs) {
                	hotels.add(Hotel.findHotelFromID(Integer.parseInt(hotelID)));  
                }
                
                ArrayList<Integer> howManyNights = new ArrayList<Integer>();
                String[] nights = travelParts[3].split("\\|");
                
                for (String night : nights) {
                	howManyNights.add(Integer.parseInt(night));
                }
                
                ArrayList<Taxi> taxis = new ArrayList<Taxi>();
                String[] taxiIDs = travelParts[4].split("\\|");
                
                for (String taxiID : taxiIDs) {
                	taxis.add(Taxi.findTaxiFromID(Integer.parseInt(taxiID)));  
                }
                
                double totalFlightPrice = Double.parseDouble(travelParts[6]);
                double totalHotelPrice = Double.parseDouble(travelParts[7]);
                double totalTaxiPrice = Double.parseDouble(travelParts[8]);
                
                
                Travel theTravel = new Travel(Integer.parseInt(travelParts[0]), flights, hotels, taxis);
                theTravel.setType(Integer.parseInt(travelParts[5]));
                theTravel.setHowManyNights(howManyNights);
                
                theTravel.setTotalFlightPrice(totalFlightPrice);
                theTravel.setTotalHotelPrice(totalHotelPrice);
                theTravel.setTotalTaxiPrice(totalTaxiPrice);
                
                customer.updateTravelHistory(theTravel);
                customer.updateTravelReservation(theTravel, 1);
            }
        }
        
        if (reservations != null) {
        	for (String reservation : reservations) {
        		String[] entryAndValue = reservation.split(",");
        		customer.updateTravelReservation(Travel.findTravelFromID( Integer.parseInt(entryAndValue[0])), Integer.parseInt(entryAndValue[1]));
        	}
        }
        
        
        return customer;
    }
	
	
	
	public static void saveCustomers(String fileName) {
	       try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
	           for (Customer customer : Customer.customerList) {
	               writer.write(customer.toString());
	               writer.newLine();
	           }
	       } catch (IOException ee) {
	    	   
	       }
	}
	
	public static void loadCustomers(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Customer.customerList.clear();
            while ((line = reader.readLine()) != null) {
                Customer.fromString(line); 
            }
        } catch (IOException e) {
        	
        }
    }
	

	public static ArrayList<Object> login(String username, String password) {
		int loginStatus = 0;
		ArrayList<Object> returnCustomer = new ArrayList<>();
		
		if (Admin.checkAdminLogin(username, password)) {
			loginStatus = 4;
			returnCustomer.add(loginStatus);
			returnCustomer.add(null);
			return returnCustomer;
		}
		
		for (Customer c : getCustomerList()) {
			if (c.getUsername().equals(username)) {
				if (c.getPassword().equals(password)) {
					loginStatus = 1;
					returnCustomer.add(loginStatus);
					returnCustomer.add(c);
					return returnCustomer;
				}
				
				else {
					loginStatus = 2;
					returnCustomer.add(loginStatus);
					returnCustomer.add(null);
					return returnCustomer;
				}
			}
		}
		
		loginStatus = 3;
		returnCustomer.add(loginStatus);
		returnCustomer.add(null);
		return returnCustomer;
	}
	
	public boolean travelIDExists(int travelID) {
		for (Travel travel : travelHistory) {
			if (travel.getTravelID() == travelID) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	

}
