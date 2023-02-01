package bfst22.factorial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    
    private Factorial math;

    @BeforeEach
    void setUp() {
        math = new Factorial();
    }
    
    @Test 
    void factorialOf0() {
        assertEquals(math.factorial(0), 1, "factorial(0) should return 1");
    }

    @Test
    void testThrowsBadUserExperience() {
        assertThrows(BadUserException.class, () -> math.factorial(-200));
    }

    @Test
    void factorialReturnsFactorialTest() {
        assertEquals(math.factorial(4), 24);
    }
}
