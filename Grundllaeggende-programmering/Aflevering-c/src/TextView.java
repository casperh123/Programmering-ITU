package src;
public class TextView {
    private ICostumerTracker iCostumerTracker;

    public TextView(ICostumerTracker cTracker) {
        this.iCostumerTracker = cTracker;
    }

    public void printToday() {
        try {
            System.out.println("Today: " + iCostumerTracker.today() + " customers");
        } catch(NoDataForWeekException e) {
            System.out.println(e.getMessage());
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugen findes i systemet, men indeholder ingen data");
        }
    }

    public void printAvgThisWeek() {
        try {
            System.out.println("Average this week: " + iCostumerTracker.avgThisWeek() + " customers");
        } catch(NoDataForWeekException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void printComparedToWeek(int week) {

        try {
            double comparedWeekDifference = iCostumerTracker.comparedToWeek(week);

            if(comparedWeekDifference < 0) {
               System.out.println("Decrease by " + comparedWeekDifference + " customers");
            } else if(comparedWeekDifference > 0) {
                System.out.println("Increase by " + comparedWeekDifference + " customers");
            } else {
                System.out.println("No Difference");
            }
        } catch(NoDataForWeekException e) {
            System.out.println(e.getMessage());
        }
    }
}


