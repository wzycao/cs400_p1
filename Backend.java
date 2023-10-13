import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is designed to read data from a csv file, store said data in a Red Black Tree, and create new sub-lists
 * given certain inputs or parameters.
 */
public class Backend implements BackendInterface {
    IterableMultiKeySortedCollectionInterface<Car> thisTree;
    public Backend(String filePath,IterableMultiKeySortedCollectionInterface<Car> treeImplementation) {
        thisTree = treeImplementation;
        readFileData(filePath);
    }

    @Override
    public boolean readFileData(String filePath) {
        RedBlackTreePlaceholder<Car> returnTree = new RedBlackTreePlaceholder<>();
    /*
    the testcars.csv file is organized by price, brand, model, year, title_status, mileage, color, vin, lot, state, and
    country. We only care about the price, brand, model, year, and mileage. So, we will collect all data before the
    first comma and put that into price. Then,
     */
        File carsList = new File(filePath);
        try {
            Scanner fileScnr = new Scanner(carsList);
            fileScnr.nextLine();  //Skip first line, which just denotes values of each part.
            while (fileScnr.hasNextLine()) { //Some elements have spaces.
                String indivCarData = fileScnr.nextLine();
                String[] carData = indivCarData.split(",");
                //For now, we will not consider nested commas or quotes, as our data set testcars.csv has no nested commas.
                Integer price = Integer.parseInt(carData[0]);
                String brand = carData[1];
                String model = carData[2];
                Integer year = Integer.parseInt(carData[3]);
                Float mileage = Float.parseFloat(carData[5]);

                Car newInsertCar = new Car(brand, model, year, price, mileage);
                // TODO: fix insertion method.
                thisTree.insertSingleKey(newInsertCar);
            }

        } catch (FileNotFoundException e) {
          return false;
        }

        return true;
    }


    @Override
    public List<Car> getCarsWithMinimumMileage() {
        LinkedList<Car> listOfMinCars = new LinkedList<>();
        Iterator<Car> iterator = thisTree.iterator();
        /*
        Iterator starting point should be the lowest value, as the iterator is in-order traversal.
        So. iterator.next() should give us all the cars with the minimum amount of mileage.
         */
       Car minimumCarKey = iterator.next();
       listOfMinCars.add(minimumCarKey);
       return listOfMinCars;
    }

    @Override
    public List<Car> getCarsWithMileageAboveThreshold(float mileageThreshold) {
        /*
        Find iteration start point, then set it. Then, add every element into the list.
         */
        Car comparisonCar = new Car("na", "na", 0, 0, mileageThreshold);
        thisTree.setIterationStartPoint(comparisonCar);
        Iterator<Car> iterator = thisTree.iterator();
        LinkedList<Car> returnList = new LinkedList<>();



        while (iterator.hasNext()) returnList.add(iterator.next());
        return returnList;
    }


}
