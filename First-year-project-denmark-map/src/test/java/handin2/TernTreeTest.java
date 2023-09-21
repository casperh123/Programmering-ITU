package handin2;

import Exceptions.NoSuchAddressException;
import MapObjects.Markers.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class TernTreeTest {
    List<Address> addresses;
    TernTree tst;

    @BeforeEach
    void setUp() {
        addresses = new ArrayList<>();
        Address addr1 = new Address("Vinkelvej", "16", "4673", "Rødvig");
        Address addr2 = new Address("Nørrebrogade", "11", "2500", "DinMor");
        Address addr3 = new Address("Vinkelvej", "18", "4673", "Rødvig");
        Address addr4 = new Address("Vinkelvej", "1", "4673", "Rødvig");
        Address addr5 = new Address("Bøllebob", "56", "6969", "SexCity");
        Address addr6 = new Address("Amagergade", "12", "2300", "København S");
        Address addr7 = new Address("Amagerbrogade", "201", "2300", "København S");
        Address addr8 = new Address("Amageralle", "89", "2300", "København S");
        addresses.add(addr1);
        addresses.add(addr2);
        addresses.add(addr3);
        addresses.add(addr4);
        addresses.add(addr5);
        addresses.add(addr6);
        addresses.add(addr7);
        addresses.add(addr8);

        tst = new TernTree();
        tst.insert(addresses);
    }

    @AfterEach
    void tearDown() {
        addresses = null;
        tst = null;
    }

    @Test
    void insertTest() {
        assert(tst.root.data == 'v');
        assert(tst.root.middle.middle.middle.middle.middle.middle.middle.middle.data == 'j');
    }

    @Test
    void searchTest() {
        char data = tst.search(tst.root, "vi").data;
        assert (data == 'i');
    }

    @Test
    void searchTest2() {
        assert (tst.search(tst.root, "vinkel").data == tst.root.middle.middle.middle.middle.middle.data);
        assert (tst.search(tst.root, "amag").data == tst.root.left.left.left.middle.middle.middle.data);
    }

    @Test
    void searchNotFoundTest() {
        Exception thrown = Assertions.assertThrows(NoSuchElementException.class, () -> tst.search(tst.root, "allé"));
        Assertions.assertEquals("Tree or input is empty", thrown.getMessage());
    }

    @Test
    void findSuffixesTest1() {
        tst.findSuffixes("vin");
        for (Address address : tst.matches) {
            System.out.println(address);
        }
        assert(tst.matches.size() == 3);
    }

    @Test
    void findSuffixesTest2() {
        tst.findSuffixes("ama");
        for (Address address : tst.matches) {
            System.out.println(address);
        }
        assert(tst.matches.size() == 3);
    }

    @Test
    void findSuffixesFrom1CharTest() {
        tst.findSuffixes("ama");
        List<Address> compareList = new ArrayList<>();
        for (Address match : tst.matches) {
            compareList.add(match);
        }
        tst.findSuffixes("a");
        assert tst.matches.size() == 3;
        for (Address address : compareList) {
            assert tst.matches.contains(address);
        }
    }

    @Test
    void findOnly5SuffixesTest() {
        StringBuilder stringBuilder = new StringBuilder("Strandbovej");
        Address address;
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(String.valueOf(i));
            address = new Address(stringBuilder.toString(), "16", "2300", "København");
            tst.insert(address);
        }
        tst.findSuffixes("strand");
        for (Address match : tst.matches) {
            System.out.println(match);
        }
        assert (tst.matches.size() == 5);
    }

    @Test
    void findSuffixesMultipleTimesTest() {
        tst.findSuffixes("a");
        for (Address match : tst.matches) {
            System.out.println(match);
        }
        assert(tst.matches.size() == 3);
        tst.clearMatches();
        System.out.println(tst.matches.size());
        tst.findSuffixes("nør");
        for (Address match : tst.matches) {
            System.out.println(match);
        }
        assert(tst.matches.size() == 1);
    }

    @Test
    void tstMatchesClearTest() {
        tst.findSuffixes("vin");
        tst.matches.clear();
        assert (tst.matches.size() == 0);
    }

    @Test
    void getFinalAddressTest() throws NoSuchAddressException {
       Address address = tst.getFinalAddress("Vinkelvej 1, 4673 Rødvig");
        assert(address.toString().equals("Vinkelvej 1, 4673 Rødvig"));
    }

    @Test
    void getMatchesTest() {
        tst.findSuffixes("ama");
        List<Address> list = tst.getMatches();
        assert(list != null);
    }
}