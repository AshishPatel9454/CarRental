package CarRental;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
	private List<Car> cars;
	private List<Customer> customers;
	private List<Rental> rentals;

	public CarRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rentals = new ArrayList<>();

	}

	public void addCar(Car car) {
		cars.add(car);

	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void rentCar(Car car, Customer customer, int rentalDays) {
		if (car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car, customer, rentalDays));

		} else {
			System.out.println("car is not available for rent");
		}

	}

	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove = null;
		for (Rental rental : rentals) {
			if (rental.getCar() == car) {
				rentalToRemove = rental;
				break;
			}
			if (rentalToRemove != null) {
				rentals.remove(rentalToRemove);
				System.out.println("Car return successfully.");
			} else {
				System.out.println("Car was not rented.");
			}
		}

	}

	public void menu() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("===Car Rental System===");
			System.out.println("1. Rent a Car");
			System.out.println("2. Return a Car");
			System.out.println("3 exit");
			System.out.println("Enter your choice ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 1) {
				System.out.println("\n Rent aCar \n");
				System.out.println("Enter your name");
				String customerName = scanner.nextLine();
				System.out.println("\nAvaliable Cars");
				for (Car car : cars) {
					if (car.isAvailable())
						System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
				}
				System.out.println("Enter the car id you want to rent");
				String carId = scanner.nextLine();
				System.out.println("Enter rental Days");
				int rentalDays = scanner.nextInt();
				scanner.nextLine();
				Customer newCustomer = new Customer("cus" + (customers.size() + 1), customerName);
				addCustomer(newCustomer);

				Car selectedCar = null;
				for (Car car : cars) {
					if (car.getCarId().equals(carId) && car.isAvailable()) {
						selectedCar = car;
						break;
					}
				}
				if (selectedCar != null) {
					double totalPrice = selectedCar.calculatePrice(rentalDays);
					System.out.println("---Rental Information---");
					System.out.println("Customer Id" + newCustomer.getCustomerId() + "\n");
					System.out.println("Customer name" + newCustomer.getName() + "\n");
					System.out.println("Car " + selectedCar.getBrand() + " " + selectedCar.getModel() + "\n");
					System.out.println("RentalDays" + rentalDays + "\n");
					System.out.println("Total Price " + totalPrice + "\n");
					System.out.println("--Conferm rental--(Y/N)");
					String conferm = scanner.nextLine();
					if (conferm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar, newCustomer, rentalDays);
						System.out.println("car rented successfully");
					} else {
						System.out.println("rental cancelled");
					}

				} else {
					System.out.println("invalid car selection or car not available for rent");

				}
			}

			else if (choice == 2) {
				System.out.println("==Return a Car==");
				System.out.println("Enter car Id you want to return");
				String carId = scanner.nextLine();
				Car carToReturn = null;

				for (Car car : cars) {
					if (car.getCarId().equals(carId) && !car.isAvailable()) {
						carToReturn = car;
						break;
					}
				}
				if (carToReturn != null) {
					Customer customer = null;
					for (Rental rental : rentals) {
						if (rental.getCar() == carToReturn) {
							customer = rental.getCustomer();
							break;
						}

					}
					if (customer != null) {
						returnCar(carToReturn);
						System.out.println("car return seccessfully by" + customer.getName());

					} else {
						System.out.println("car was not rented or information is missing");
					}
				} else {
					System.out.println("invalid car Id or car is not rented");
				}

			} else if (choice == 3) {
				break;
			}

			else {
				System.out.println("Invalid choice please Enter valid choice");
			}
		}
		System.out.println("Thankyou for using car rental system");
	}

}
