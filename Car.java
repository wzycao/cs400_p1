public class Car implements CarInterface {
    private String brand;

    private String model;

    private int year;

    private double price;

    private double mileage;

    public Car(String brand, String model, Integer year, Integer price, Float mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
    }


    public String getBrand(){
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getMileage() {
        return this.mileage;
    }

    /**
     * Comparison method for cars. Cars will be organized/compared with respect
     * to their mileage.
     * @param otherCar Other car object to compare this to
     * @return A value less than 0 if this car has a lower mileage than otherCar,
     * value more than 0 if this car has a higher mileage than otherCar, or the value 0 if the mileage of each car
     * is equal.
     */
    @Override
    public int compareTo(Car otherCar) {
        return (int)(this.getMileage() - otherCar.getMileage());
    }
}
