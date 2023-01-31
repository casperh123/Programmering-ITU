package src;
import java.util.HashMap;

public class CustomerTracker implements ICostumerTracker {
    
    private HashMap<Integer, int[]> customerData;
    private int currentWeek;

    public CustomerTracker(MockDB db, int currentWeek) {
        this.customerData = db.getCustomerData();
        this.currentWeek = currentWeek;
    }

    public int today() {

        if(customerData.containsKey(currentWeek)) {
            
            int[] dataCurrentWeek = customerData.get(currentWeek);
            int arrLength = dataCurrentWeek.length;

            if(arrLength > 0) {
                return dataCurrentWeek[arrLength - 1];
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } else {
            throw new NoDataForWeekException(currentWeek);
        }
    }

    private double avgWeek(int week) {

        if(customerData.containsKey(week)) {
            int[] dataCurrentWeek = customerData.get(week);
            int weeklyCustomers = 0;
    
            for(int dailyCustomers : dataCurrentWeek) {
                weeklyCustomers += dailyCustomers;
            }

            return (double) weeklyCustomers / 7;
        } else {
            throw new NoDataForWeekException(week); 
        }
    }

    public double avgThisWeek() {

        if(customerData.containsKey(currentWeek)) {
            int[] dataCurrentWeek = customerData.get(currentWeek);
            int weeklyCustomers = 0;

            for(int dailyCustomers : dataCurrentWeek) {
                weeklyCustomers += dailyCustomers;
            }

            return (double) weeklyCustomers / 7;
        } else {
            throw new NoDataForWeekException(currentWeek);
        }
    }

    public double comparedToWeek(int week) {
        return avgWeek(currentWeek) - avgWeek(week);
    }
}
