import java.io.Serializable;

public class Passenger implements Serializable {
    private String firstName;
    private String sureName;
    private int expenses;

    public Passenger(String firstName, String sureName) {
        this.firstName = firstName;
        this.sureName = sureName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", sureName='" + sureName + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
