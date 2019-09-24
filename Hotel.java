import java.text.DecimalFormat;
import java.util.ArrayList;

public class Hotel
{
	/////////////////////////////////////////////////////////////////////////////////////////
	// Instance Variables
	/////////////////////////////////////////////////////////////////////////////////////////
	//ArrayList<Hotel> hotels = getHotelsFromFile("Hotels.txt");
	//ArrayList<Reservation> reservations = readReservationsFromFile("Reservations.txt")

	//Instance variables encapsulating info found in Hotels.txt
	DecimalFormat df = new DecimalFormat(".00");
	private long UniqueId;
	private String HotelName;
	private String stName;
	private String hotCity;
	private String hotState;
	private double PricePerNight;
	private ArrayList <Reservation> reservations = new ArrayList <Reservation>();
	
	/////////////////////////////////////////////////////////////////////////////////////////
	// Overloaded Constructor
	/////////////////////////////////////////////////////////////////////////////////////////
	public Hotel(long id, String name, String addr, String cityName, String stAbbrev, double price)
	{
		// Overloaded constructor variables
		UniqueId = id;
		HotelName = name; 
		stName = addr;
		hotCity = cityName;
		hotState = stAbbrev;
		PricePerNight = price;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	// Default constructor
	/////////////////////////////////////////////////////////////////////////////////////////
	public Hotel()
	{
		//Default constructor variables
		UniqueId = 1;
		HotelName = "Burrito"; 
		stName = "Bellevue";
		hotCity = "Guac";
		hotState = "D-Grissom";
		PricePerNight = 100.00;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	// Class Methods
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	// This method takes in a new reservation and compares it against 
	// all other reservations in this hotels reservations ArrayList.
	// Returns true if the new reservation can be made; returns false
	// if the new reserveration (newRes) will conflict with an old
	// reservation.
	/////////////////////////////////////////////////////////////////////////////////////////
	public boolean canBook(Reservation newRes)
	{
		// For loops on reservations
		for (Reservation r: reservations){
			
			//Check to see if the reservation days match the new reservation days in the updated array
			if(r.getIdNumber() == newRes.getIdNumber()
				&& (r.getFirstMonth() == newRes.getFirstMonth() || r.getLastMonth() == newRes.getLastMonth())){
				if(r.getFirstDay() == (newRes.getFirstDay()) || r.getLastDay() == newRes.getLastDay()) 
				{
					return false;
				}
				//Check to see if the reservations on the first day match any reservations on the last day
				if (newRes.getFirstDay() > (r.getFirstDay()) && (newRes.getFirstDay() < r.getLastDay())){
					return false;
				}
				//Check to see if the reservations on the last day match any reservations on the first day
				if(newRes.getLastDay() > (r.getFirstDay()) && (newRes.getLastDay() < r.getLastDay())){
					return false;
				}
			}
		}
		
		//Else, return true
		return true;
	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	public void addReservation(Reservation newRes)
	{
		//Add the new reservations to the old reservations
		reservations.add(newRes);
		
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	// SIMPLE method that uses the previous two methods (canBook() and addReservation()). If
	// canBook() returns true, calls addReservation() to add newRes and returns true;
	// otherwise, returns false.
	/////////////////////////////////////////////////////////////////////////////////////////
	public boolean addResIfCanBook(Reservation newRes)
	{
		//If statement allowing the reservations to book
		if (canBook(newRes)){
			addReservation(newRes);
			return true;
		}
		
		else 
		return false;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	public String toDisplayString()
	{
		//Generates display String for printout
		return UniqueId + ") " + HotelName + " (" + stName + ", " + hotCity + ", " + hotState 
				+ ") @ $" + df.format(PricePerNight) + "/night";
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	//Getters and setters for uniqueId
	public long getUniqueId() {
		return UniqueId;
	}

	public void setUniqueId(long UniqueId) {
		this.UniqueId = UniqueId;
	}
	
	//Getters and setters for hotelName
	public String getHotelName() {
		return HotelName;
	}

	public void setHotelName(String hotelName) {
		this.HotelName = hotelName;
	}

	//Getters and setters for stName
	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	//Getters and setters for hotCity
	public String getHotCity() {
		return hotCity;
	}

	public void setHotCity(String hotCity) {
		this.hotCity = hotCity;
	}

	//Getters and setters for hotState
	public String getHotState() {
		return hotState;
	}

	public void setHotState(String hotState) {
		this.hotState = hotState;
	}

	//Getters and setters for pricePerNight
	public double getPricePerNight() {
		return PricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.PricePerNight = pricePerNight;
	}

	//Getters and setters for getReservations
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}	
}	