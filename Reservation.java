import java.io.Serializable;
import java.text.DecimalFormat;

public class Reservation implements Serializable
{
	/////////////////////////////////////////////////////////////////////////////////////////
	// Instance Variables
	/////////////////////////////////////////////////////////////////////////////////////////
	//Create instance variables encapsulating the reservations
	DecimalFormat df = new DecimalFormat(".00");
	private long idNumber;
	private int firstMonth;
	private int lastMonth;
	private int firstDay; 
	private int lastDay;
	
	/////////////////////////////////////////////////////////////////////////////////////////
	// Overloaded Constructor
	/////////////////////////////////////////////////////////////////////////////////////////
	public Reservation(long hotId, int inMonth, int outMonth, int inDay, int outDay)
	{
		//Overloaded constructor variables
		idNumber = hotId;
		firstMonth = inMonth;
		lastMonth = outMonth;
		firstDay = inDay; 
		lastDay = outDay;
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	// Default constructor
	/////////////////////////////////////////////////////////////////////////////////////////
	public Reservation()
	{
		//Default constructor variable
		idNumber = 0;
		firstMonth = 0;
		lastMonth = 0;
		firstDay = 0;
		lastDay = 0;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////
	// Class Methods
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	public String getFormattedDisplayString()
	{
		//Display Strings for printout statements
		String ret = "";
		
		ret += 
				this.firstMonth + "/" + this.firstDay + " - " +
				this.lastMonth + "/" + this.lastDay;
		
		return ret;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
	public String getFormattedDisplayString(double currentPrice)
	{
		//Display String for printout for first month, last month, and cost per night
		String ret = "";
		ret += 
				this.firstMonth + "/" + this.firstDay + " - " +
				this.lastMonth + "/" + this.lastDay + 
				" @ $" + df.format(currentPrice) + " per night ";
		
		return ret;
		
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////
	// Getters/Setters
	/////////////////////////////////////////////////////////////////////////////////////////
	//Getters and setter for id number
	public long getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}
	
	//Getters and setter for first month
	public int getFirstMonth() {
		return firstMonth;
	}
	public void setFirstMonth(int firstMonth) {
		this.firstMonth = firstMonth;
	}
	//Getters and setter for last month
	public int getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(int lastMonth) {
		this.lastMonth = lastMonth;
	}
	//Getters and setter for first days
	public int getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(int firstDay) {
		this.firstDay = firstDay;
	}
	//Getters and setter for last days
	public int getLastDay() {
		return lastDay;
	}
	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
	}
	
}
