package src;

public class NoDataForWeekException extends RuntimeException {
    private int week;

    public NoDataForWeekException(int week) {
        super();
        this.week = week;
    }

    public String getMessage() {
        return "Ingen data for uge: " + week;
    }
}
