package CarRental;

public class Main {
	public static void main(String[] args) {
		CarRentalSystem rentalSystem=new CarRentalSystem();
		Car car1=new Car("coo1","mahidra","thar",1500);
		Car car2=new Car("coo2","mahidra","bolero",1000);
		Car car3=new Car("coo3","Toyota","fartuner",3000);
		rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.menu();
	}

}
