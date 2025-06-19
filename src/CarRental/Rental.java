package CarRental;

public class Rental {
	private Car car;
	private Customer customer;
	private int rentalDays;
	
	public Rental(Car car, Customer customer, int rentalDays) {
		super();
		this.car = car;
		this.customer = customer;
		this.rentalDays = rentalDays;
	}
	public Car getCar() {
		return car;
	}
	public Customer getCustomer() {
		return customer;
	}
	public int getRentalDays() {
		return rentalDays;
	}
	

}
