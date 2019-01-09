
/**
 * Displays the limit and value of this number
 * 
 * @author Kevin Qin
 * @version 1.5 2015-02-21
 */
public class NumberDisplay
{
    // instance fields
    private int limit;
    private int value;
    
    /*
     * constructor methods
     */
    /**
     * Constructor for NumberDisplay
     * 
     * @param limit the limit of this number. Must be greater than 1.
     * @param value the value of this number. Must not be negative.
     */
    public NumberDisplay(int limit, int value)
    {
        if ((limit > 1) && (value >= 0))
        {
            this.limit = limit;
            this.value = value;
        }
        else
        {
            this.limit = 2;
            this.value = 0;
        }
    }// end of NumberDisplay
    
    /*
     * accessor methods
     */
    /**
     * returns the value this number would display
     * 
     * @reutrn the value this number would display
     */
    public String getDisplayValue()
    {
        if (value < 10)
        {
            return "0" + value;
        }
        else
        {
            return "" + value;
        }
    }// end of getDisplayValue()
    
    /**
     * returns the limit of this number
     * 
     * @return the value of this number's limit
     */
    public int getLimit()
    {
        return limit;
    }// end of getLimit()
    
    /**
     * returns the value of this number
     * 
     * @return the value of this number's value
     */
    public int getValue()
    {
        return value;
    }// end of getValue()
    
    /*
     * mutator methods
     */
    /**
     * sets the limit of this number to a new limit
     * 
     * @param newLimit the new value of this number. Must be positive and must be greater than one
     */
    public void setLimit(int newLimit)
    {
        if (newLimit > 1)
        {
            limit = newLimit;
        }
    }// end of setLimit
    
    /**
     * sets the value of this number to a new value
     * 
     * @param newValue the new value of this number. Must be positive and not be above the limit
     */
    public void setValue(int newValue)
    {
        if ((newValue >= 0) && (newValue < limit))
        {
            value = newValue;
        }
        else
        {
            value = 0;
        }
    }// end of setValue
    
    /**
     * increases the value of this number by 1 everytime
     */
    public void increment()
    {
        value = value + 1;
    }// end of increment()
}