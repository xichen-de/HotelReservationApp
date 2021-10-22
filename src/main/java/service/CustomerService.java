package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService instance;
    private static final Map<String, Customer> customerMap = new HashMap<>();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        customerMap.put(email, newCustomer);
    }

    public Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }
}
