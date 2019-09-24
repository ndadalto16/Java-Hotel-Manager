import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CS225_Project5_Client
{
	public static void main(String[] args)
	{
		// Your program should always output your name and the lab/problem
		System.out.println("Nathan DaDalto");
		System.out.println("Project 5");
		System.out.println("");

		// Create scanner
		Scanner scan = new Scanner(System.in);

		// Perform initial reading of data
		ArrayList<Hotel> hotels = getHotelsFromFile("src/Hotels.txt");
		ArrayList<Reservation> reservations = readReservationsFromFile("src/Reservations.txt");

		// Associate each reservation with the appropriate hotel
		assignReservationsToCorrectHotel(reservations, hotels);

		// Print out welcome 
		System.out.println("Welcome to the Parental Paradise Hotel Chain.");
		Hotel selectedHotel = null;

		// Prompt with hotel list and repeat until get valid hotel selection
		do
		{		
			System.out.println("Please select from one of the following hotels (select the number and press enter) to make a reservation: ");
			for (Hotel h : hotels)
				System.out.println("\t" + h.toDisplayString());
			System.out.print("\nYour selection: ");

			// Read user input & get hotel
			int choice = scan.nextInt();

			for (Hotel h : hotels)
				if (h.getUniqueId() == choice)
					selectedHotel = h;

			if (selectedHotel == null)
				System.out.println("Invalid hotel choice. Please try again...");

		} while (selectedHotel == null);

		// Create variables for reservation
		int month;
		int checkinDay;
		int checkoutDay;
		boolean invalidDateRange = true;

		// Prompt for check-in month and check-in/check-out day until get valid selection
		do
		{
			System.out.println("Please enter details about your reservation request: ");
			System.out.println("\tMonth (1-12): ");
			month = scan.nextInt();
			System.out.println("\tCheck-in day: ");
			checkinDay = scan.nextInt();
			System.out.println("\tCheck-out day: ");
			checkoutDay = scan.nextInt();

			if (month <= 0 || month > 12)
				System.out.println("Invalid month choice. Please try again...");
			else if (checkinDay <= 0 || checkoutDay <= 0)
				System.out.println("Invalid check-in or check-out choice; must be greater than 0. Please try again...");
			else if (checkinDay > 31 || checkoutDay > 31)
				System.out.println("Invalid check-in or check-out choice; must be greater than 0. Please try again...");
			else if (checkoutDay == checkinDay)
				System.out.println("Invalid check-in or check-out range; must stay at least one night. Please try again...");
			else if (checkoutDay <= checkinDay)
				System.out.println("Invalid check-in or check-out range; cannot checkout before checking in. Please try again...");
			else
			{
				invalidDateRange = false;
			}

		} while (invalidDateRange);

		// Create reservation from user input
		Reservation newRes = new Reservation(selectedHotel.getUniqueId(), month, month, checkinDay, checkoutDay);
		
		// Try to add reservation
		if (selectedHotel.addResIfCanBook(newRes))
		{
			// Then add new reservation to global reservations list and write out to file
			System.out.println("Reservation successfully added: " + selectedHotel.getHotelName() + ": " + newRes.getFormattedDisplayString(selectedHotel.getPricePerNight()));
			reservations.add(newRes);
			writeReservationFile("src/Reservations.txt", reservations);
		}
		else
		{
			System.out.println("Could not add the reservation (" + newRes.getFormattedDisplayString() + ") to " + selectedHotel.getHotelName() + " b/c of a conflict.");
			System.out.println("Please re-run the program to try a new date.");
		}
		
		// Exit program
		System.out.println("\nThank you for using the Parental Paradise Hotel Manager.");

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// This method takes in a list of hotels and reservations. At this point, the hotel
	// objects should have an empty ArrayList of Reservations (as an instance variable).
	// This method cycles through the reservations and assigns them to the hotel with
	// matching uniqueId as the resrevation's hotelId.
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void assignReservationsToCorrectHotel(ArrayList<Reservation> reservations, ArrayList<Hotel> hotels)
	{
		
		//for loop checking the list of hotels and reservations 
		for(Hotel h: hotels){
			for(Reservation r: reservations){
				
				//If correct, the id number adds to Reservations 
				if(r.getIdNumber() == h.getUniqueId()){
					h.addReservation(r);
				}
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	private static ArrayList<Hotel> getHotelsFromFile(String fileName) 
	{
		
		// Create connection to fileName for reading
		ArrayList <Hotel> hotels = new ArrayList <Hotel>();
		try{
		FileInputStream fis = new FileInputStream(fileName);
		Scanner scan = new Scanner (fis);
		
		//Reads Hotels from fileName and returns as a new ArrayList of hotels.
		while(true){
		String line = scan.nextLine();
		String [] parts = line.split(", ");
		parts[0] = parts[0].replace("Hotel(", "");
		
		parts[5] = parts[5].replace(")", "");
		Hotel readHotel = new Hotel((Long.parseLong(parts[0])), 
		parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
		hotels.add(readHotel);
			
			}
		}
		//Exceptions
		catch(FileNotFoundException e){
			e.getMessage();
			
		}catch(NoSuchElementException e){
			e.getMessage();
		}
		return hotels;
	}
				
				
				

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// Reads Reservations from a given file and stores them into the reservations
	// list. Returns a new ArrayList of reservations read in from file. If no reservations
	// in file found at fileName, should return an empty ArrayList. 
	//
	// Uses serialize for input.
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	private static ArrayList<Reservation> readReservationsFromFile(String fileName)
	{
		// Create connection to fileName for reading
				FileInputStream fis = null;
				ObjectInputStream ois = null;

				ArrayList<Reservation> reservations = new ArrayList<Reservation>();

				try
				{
					//uses serialize for input
					fis = new FileInputStream(fileName);
					ois = new ObjectInputStream(fis);

					// Read in reservations from file
					while(true)
					{
						Reservation b = (Reservation)ois.readObject();
						reservations.add(b);
					}
				}
				catch(EOFException e)
				{
					System.out.println(fileName + " file successfully read.");
				}
				catch(Exception e)
				{
					System.out.println("ERROR: " + e.getMessage());
				}
				
				
				return reservations;
			}
			


	/////////////////////////////////////////////////1/////////////////////////////////////////////////////
	// Overwrites the file at fileName with the reservations found in globalReservations. 
	// globalReservations should contain the reservations found in the file when the program
	// begin, as well as any new reservation the user made. Returns true upon success; false upon failure
	// Uses serialize for input.
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void writeReservationFile(String fileName, ArrayList<Reservation> globalReservations)
	{
		
		// Create connection to fileName for printing
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try
		{
			//uses serialize for input
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			
		// Write objects to file
		for (Reservation b : globalReservations )
			oos.writeObject(b);

		}
		//exceptions
		catch (IOException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		finally
		{
			try
			{
				// Close files
				oos.close();
				fos.close();
			}
			//exceptions
			catch (IOException e)
			{
				System.out.println("ERROR: " + e.getMessage());
			}
		}
		}
	}
	
		


