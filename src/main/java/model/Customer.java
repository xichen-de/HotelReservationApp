package model;

import java.util.Objects;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.firstName = firstName;
        }
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.lastName = lastName;
        }
        if (!validateEmail(email)) {
            throw new IllegalArgumentException();
        } else {
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private boolean validateEmail(String email) {
        String emailPattern = ".+@.+.com$";
        return email.matches(emailPattern);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
