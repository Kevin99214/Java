
/**
 * Displays this clock's hour and minutes
 * 
 * @author Kevin Qin
 * @version 1.2 02-23-2015
 */
public class ClockDisplay
{
    //class fields
    private final long DELAY = 1000L;
    
    // instance variables 
    private NumberDisplay hoursDisplay;
    private NumberDisplay minutesDisplay;
    private Clock secondCounter;
    private String display;
    
    /*
     * constructor methods
     */
    /**
     * Constructor for objects of class ClockDisplay
     * 
     * @param minuteLimit the limit of this clock's number of minutes. Must be a positive integer
     * @param hourLimit the limit of this clock's number of hours. Must be a positive integer
     * @param minuteValue this clock's current minute value. Must be a positive number and be less than the limit 
     * @param minuteValue this clock's current hour value. Must be a positive number and be less than the limit
     */
    public ClockDisplay(int minuteLimit, int hourLimit, int minuteValue, int hourValue)
    {
        minutesDisplay = new NumberDisplay(minuteLimit, minuteValue); 
        hoursDisplay = new NumberDisplay(hourLimit, hourValue);
        updateClock();
    } // end of ClockDisplay constructor

    /*
     * accessor methods
     */
    /**
     * returns the display on this clock's time
     * 
     * @return the display on this clock's time
     */
    public String getClockDisplay()
    {
        return display;
    } // end of getClockDisplay()
    
    /**
     * returns the value of this clock's minutes
     */
    public void getMinutesValue()
    {
        minutesDisplay.getValue();
    } // end of getMinutesValue()
    
    /**
     * returns the value of this clock's hours
     */
    public void getHoursValue()
    {
        hoursDisplay.getValue();
    } // end of getHoursValue()
    
    /** 
     * returns the limit of this clock's minutes
     */
    public void getMinutesLimit()
    {
        minutesDisplay.getLimit();
    } // end of getMinutesLimit()
    
    /**
     * returns the limit of this clock's hours
     */
    public void getHoursLimit()
    {
        hoursDisplay.getLimit();
    } // end of getHoursLimit()
    
    /*
     * mutator methods
     */
    /**
     * increments the time forward by one
     */
    public void increment()
    {       
        minutesDisplay.increment(); // increases the minute because seconds reached 60
        
        if (minutesDisplay.getValue() == minutesDisplay.getLimit())
        {
            hoursDisplay.increment();
            minutesDisplay.setValue(0); //  resets minutes to 0
        } // end of if (hoursDisplay.getValue() == hoursDisplay.getLimit())
               
        if (hoursDisplay.getValue() == hoursDisplay.getLimit())
        {
            hoursDisplay.setValue(0); // resets hour to 0
        } // end of if (hoursDisplay.getValue() == hoursDisplay.getLimit())
        updateClock(); 
    } // end of increment()
    
    /**
     * sets the value of this clock's minutes
     * 
     * @param newMinutes the new value of this clock's minutes. Must be a positive number and be less than the limit
     */
    public void setMinutesValue(int newMinutes)
    {
        minutesDisplay.setValue(newMinutes);
        updateClock();
    } // end of setMinutesValue
    
    /**
     * sets the value of this clock's hours
     * 
     * @param newMinutes the new limit of this clock's hours. Must be a positive number and be less than the limit
     */
    public void setHoursValue(int newHours)
    {
        hoursDisplay.setValue(newHours);
        updateClock();
    } // end of setHoursValue
    
    /**
     * sets the limit of this clock's minutes
     * 
     * @param newMinutes the new limit of this clock's minutes. Must be a positive integer that is greater than one
     */
    public void setMinutesLimit(int newMinuteLimit)
    {
        hoursDisplay.setLimit(newMinuteLimit);
        updateClock();
    } // end of setMinutesLimit
    
    /**
     * sets the limit of this clock's hours
     * 
     * @param newMinutes the new value of this clock's minutes. Must be a positive integer that is greater than one
     */
    public void setHoursLimit(int newHourLimit)
    {
        hoursDisplay.setLimit(newHourLimit);
        updateClock();
    } // end of setHoursLimit
    
    private void updateClock()
    {
        display = "" + hoursDisplay.getDisplayValue() + ":" + minutesDisplay.getDisplayValue() + "";
    } // end of updateClock()
} // end of clockDisplay class 
