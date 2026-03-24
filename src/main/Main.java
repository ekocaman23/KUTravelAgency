package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import login_page.Customer;
import travel.Flight;
import travel.Hotel;
import travel.Taxi;
import travel.Travel;

public class Main {
	static boolean loginSuccess = false;
	static boolean closeOperation = false;
	
	public static void main(String[] args) {
		
		/************** Pledge of Honor ******************************************
		I hereby certify that I have completed this programming project on my own
		without any help from anyone else. The effort in the project thus belongs
		completely to me. I did not search for a solution, or I did not consult any
		program written by others or did not copy any program from other sources. I
		read and followed the guidelines provided in the project description.
		READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
		SIGNATURE: Ertuğrul Recep Kocaman, 0086304
		*************************************************************************/
		
		
		String flightsPath = "assets/FinalKU_Travel_Agency_Dataset_Flights.csv";
		
		try {
            List<String> lines = Files.readAllLines(Paths.get(flightsPath)); 
            for (int i = 0; i < lines.size(); i++) {
            	if (i == 0) {
            		continue;
            	}
            	
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            	
                String[] lineDivided = lines.get(i).split(",");
                if (lineDivided.length == 9) {
                	new Flight(lineDivided[0], lineDivided[1], lineDivided[2], lineDivided[3], LocalTime.parse(lineDivided[4], formatter), LocalTime.parse(lineDivided[5], formatter), 
                    		Double.parseDouble(lineDivided[7]), lineDivided[6], Integer.parseInt(lineDivided[8]));
                }
                
                else {
                	
                	new Flight(lineDivided[0], lineDivided[1], lineDivided[2], Double.parseDouble(lineDivided[7]), lineDivided[6], Integer.parseInt(lineDivided[8]),
                	lineDivided[9], lineDivided[10], LocalTime.parse(lineDivided[11], formatter), LocalTime.parse(lineDivided[12], formatter), LocalTime.parse(lineDivided[13], formatter), LocalTime.parse(lineDivided[14], formatter));
                }
                
            }
        } catch (IOException e) {
            System.err.println("Invalid file.");
        }
		
		
		String hotelsPath = "assets/FinalKU_Travel_Agency_Dataset_Hotels.csv";
		
	        try {
	            List<String> lines = Files.readAllLines(Paths.get(hotelsPath));
	            for (int i = 0; i < lines.size(); i++) {
	            	if (i == 0) {
	            		continue;
	            	}
	                String[] lineDivided = lines.get(i).split(",");
	                new Hotel(lineDivided[0], lineDivided[1], Double.parseDouble(lineDivided[4]), lineDivided[2], Integer.parseInt(lineDivided[3]), Double.parseDouble(lineDivided[5]));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	    String taxisPath = "assets/FinalKU_Travel_Agency_Dataset_Taxis.csv";
	 	   
	    	try {
	            List<String> lines = Files.readAllLines(Paths.get(taxisPath));
	            for (int i = 0; i < lines.size(); i++) {
	            	if (i == 0) {
	            		continue;
	            	}
	                String[] lineDivided = lines.get(i).split(",");
	                new Taxi(lineDivided[0], lineDivided[1], Integer.parseInt(lineDivided[2]), Double.parseDouble(lineDivided[3]), Double.parseDouble(lineDivided[4]));
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	
	    	
	    String travelFileName = "assets/Travel_List.txt";
	    Travel.loadTravels(travelFileName);	
	    	
	    String fileName = "assets/Customer_List.txt";
		Customer.loadCustomers(fileName);	
	        
	      LoginFrame loginFrame = new LoginFrame();
	      loginFrame.setVisible(true);
	      
	   
	
	
	}
}
