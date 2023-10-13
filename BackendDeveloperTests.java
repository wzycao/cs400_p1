import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackendDeveloperTests {

    /**
     * Tests if the Car class constructs correctly, and if all of its elements are set and accessed correctly.
     */
    @Test
    public void testGetCarData() {
        Car car = new Car("toyota", "corolla", 2004, 5000, 117291.0F);
        assertEquals("toyota", car.getBrand());
        assertEquals("corolla", car.getModel());
        assertEquals(2004, car.getYear());
        assertEquals((double) 5000, car.getPrice());
        assertEquals((double) 117291, car.getMileage());
    }

    /**
     * Tests if testcars.csv, the data set used for the backend class, is read and stored correctly.
     */
    @Test
    public void testReadFileData() {
        RedBlackTreePlaceholder<Car> testTree = new RedBlackTreePlaceholder<>();

        Backend testBackend = new Backend("testcars.csv",
                testTree);

        //This is the first car element in testcars.csv.
        Car firstCar = new Car("chevrolet", "1500", 2018, 27700, 6654.0F);
        assertEquals(testTree.thisTree.get(0).getBrand(), firstCar.getBrand());
    }

    /**
     * Tests if the correct result is given back if given a nonexistent file.
     */
    @Test
    public void testBadFileData() {
        Backend testBackend = new Backend("testcars.csv", new RedBlackTreePlaceholder<>());
        assertFalse(testBackend.readFileData("fakefile123"));
    }

    /**
     * Tests if the getCarsWithMinimumMileage method works correctly by calling the method on a local list,
     * and comparing it to an expected result list.
     */
    @Test
    public void testMinimumMileageList() {
        Backend testBackend = new Backend("testcars.csv", new RedBlackTreePlaceholder<>());

        /*
        General idea should be to make a test BST/list , make
        a lowest mileage list from that, then compare to result of method call.
         */

        Car minCar1 = new Car("chevrolet", "1500", 2018, 27700, 6654.0F);
        LinkedList<Car> expectedList = new LinkedList<Car>();
        expectedList.add(minCar1);
        List<Car> actualList = testBackend.getCarsWithMinimumMileage();

        assertEquals(expectedList.get(0).getMileage(), actualList.get(0).getMileage());

    }
    /**
     * Tests if the getCarsWithMileageAboveThreshold method works correctly by calling the method
     * on a local list,
     * and comparing it to an expected result list.
     */
    @Test
    public void testMileageThresholdList() {
        Backend testBackend = new Backend("testcars.csv", new RedBlackTreePlaceholder<Car>());

        Car threshCar1 = new Car("ford","door",2014,25000,64146.0F);
        Car threshCar2 = new Car("ford", "se", 2011, 2899, 190552.0F);
        Car threshCar3 = new Car("toyota", "cruiser", 2008, 6300, 274117.0F);

        LinkedList<Car> expectedList = new LinkedList<Car>();
        expectedList.add(threshCar1);
        expectedList.add(threshCar2);
        expectedList.add(threshCar3);

        List<Car> actualList =
                testBackend.getCarsWithMileageAboveThreshold(64146.0F);
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < actualList.size(); i++) {
            assertEquals(expectedList.get(i).getMileage(), actualList.get(i).getMileage());
        }

    }
}