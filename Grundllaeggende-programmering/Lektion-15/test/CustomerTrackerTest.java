package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.*;
import src.*;

public class CustomerTrackerTest {
    private MockDB db;
    private CustomerTracker cTracker;

    public CustomerTrackerTest() {

    }

    @Before
    public void setUp() {
        this.db = new MockDB();
        this.cTracker = new CustomerTracker(db, 47);
    }

    @After
    public void tearDown() {
        this.db = null;
        this.cTracker = null;
    }

    @Test
    public void today_returns35() {
        int expectedValue = 35;
        int actualValue = cTracker.today();

        assertEquals(expectedValue, actualValue);
    }

    @Test 
    public void avgThisWeek_returns30() {
        double expectedValue = 30.0;
        double actualValue = cTracker.avgThisWeek();
        
        assertEquals(expectedValue, actualValue, 0);
    }

    //Tester i stedet for en NoDataForWeekException
    @Test
    public void comparedToWeek_given0_throwsAnException() {
        assertThrows(NoDataForWeekException.class, () -> cTracker.comparedToWeek(0));
    }

    //Tester i stedet for en NoDataForWeekException
    @Test
    public void comparedToWeek48_throwsAnException() {
        assertThrows(NoDataForWeekException.class, () -> cTracker.comparedToWeek(48));
    }
}
