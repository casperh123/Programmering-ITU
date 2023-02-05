import com.example.adressparsing.Address;
import com.example.adressparsing.AdressParserApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdressTest {

        @Test
        void appHasAGreeting() {
            AdressParserApplication classUnderTest = new AdressParserApplication();
            assertNotNull(classUnderTest.getGreeting(), "App should have a greeting");
        }

        @Test void simple() {
            var addr = Address.parse("Finsensvej 50, 2000 Frederiksberg");
            assertEquals("Finsensvej", addr.street);
            assertEquals("50", addr.house);
            assertEquals("2000", addr.postcode);
            assertEquals("Frederiksberg", addr.city);
        }

    }