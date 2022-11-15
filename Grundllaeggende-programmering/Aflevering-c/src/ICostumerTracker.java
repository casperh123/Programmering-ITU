package src;
public interface ICostumerTracker {
    public int today();
    public double avgThisWeek();
    public double comparedToWeek(int week);
}