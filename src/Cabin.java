import java.io.Serializable;

public class Cabin implements Serializable {
    private Passenger firstPassengers;
    private Passenger secondPassengers;
    private Passenger thirdPassengers;

    public Cabin() {
    }

    public Passenger getFirstPassengers() {
        return firstPassengers;
    }

    public void setFirstPassengers(Passenger firstPassengers) {
        this.firstPassengers = firstPassengers;
    }

    public Passenger getSecondPassengers() {
        return secondPassengers;
    }

    public void setSecondPassengers(Passenger secondPassengers) {
        this.secondPassengers = secondPassengers;
    }

    public Passenger getThirdPassengers() {
        return thirdPassengers;
    }

    public void setThirdPassengers(Passenger thirdPassengers) {
        this.thirdPassengers = thirdPassengers;
    }

    @Override
    public String toString() {
        return "Cabin{" +
                "firstPassengers=" + firstPassengers +
                ", secondPassengers=" + secondPassengers +
                ", thirdPassengers=" + thirdPassengers +
                '}';
    }
}
