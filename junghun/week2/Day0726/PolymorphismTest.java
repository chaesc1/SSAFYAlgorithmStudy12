package Day0726;

class Vehicle {
    static void displayType() {
        System.out.println("This is a vehicle");
    }

    void displayInfo() {
        System.out.println("Vehicle information");
    }
}

class Car extends Vehicle {
    static void displayType() {
        System.out.println("This is a car");
    }

    @Override
    void displayInfo() {
        System.out.println("Car information");
    }
}

class Bike extends Vehicle {
    static void displayType() {
        System.out.println("This is a bike");
    }

    @Override
    void displayInfo() {
        System.out.println("Bike information");
    }
}

public class PolymorphismTest {
    public static void showVehicleInfo(Vehicle v) {
        // 정적 바인딩
        v.displayType();
        // 동적 바인딩
        v.displayInfo();
    }

    public static void main(String[] args) {
        Vehicle myCar = new Car();
        Vehicle myBike = new Bike();

        showVehicleInfo(myCar);
        showVehicleInfo(myBike);
    }
}
