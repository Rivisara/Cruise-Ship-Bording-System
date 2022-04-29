import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public int NumberOfCabins = 12;
    public static List<Cabin> CabinList = new ArrayList<>();
    public static String[] viewCabins = new String[12];
    public static List<String> passengerList = new ArrayList<>();
    public static List<Passenger> passengerListObj = new ArrayList<>();
    public static List<Passenger> passengerWaitingList = new ArrayList<>();
    public static List<Cabin> cabinWaitingList = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        initializeCabins();
        while (true) {
            //The menu is being printed
            System.out.println("");
            System.out.println("=================================================================");
            System.out.println("MENU");
            System.out.println("A- Add a customer to a cabin");
            System.out.println("V- View all cabins");
            System.out.println("E- Display Empty cabins ");
            System.out.println("D- Delete customer from a cabin");
            System.out.println("F- Find cabin from customer name");
            System.out.println("S- Store programme data into a file");
            System.out.println("L- Load programme data from a file");
            System.out.println("O- View passengers Ordered alphabetically by name");
            System.out.println("T- View passengers Expenses");
            System.out.println();
            System.out.println("=================================================================");
            System.out.println("Enter the letter of your option or enter 'Stop' to terminate: ");
            String answer = scan.nextLine().toLowerCase();
            switch (answer) {
                case "a":
                    addCustomerToCabin();
                    break;
                case "v":
                    viewAllCabins();
                    break;
                case "e":
                    displayEmptyCabins();
                    break;
                case "d":
                    deletePassenger();
                    break;
                case "f":
                    findPassenger();
                    break;
                case "s":
                    store();
                    break;
                case "l":
                    load();
                    break;
                case "o":
                    sortPassengers();
                    break;
                case "t":
                    passengerExpenses();
                    break;
                default:
                    System.out.println("Try Again !!");
            }
        }

    }

    private static void passengerExpenses() {
        while (true) {
            System.out.println("A - View solo passengers Expenses");
            System.out.println("B- View total passengers Expenses");
            String answer = scan.nextLine().toLowerCase();
            switch (answer) {
                case "a":
                    System.out.println("Enter the FirstName of the Passenger?");
                    String findPassengerA = scan.nextLine();
                    for (Cabin cabin : CabinList) {
                        if (Objects.equals(cabin.getFirstPassengers().getFirstName(), findPassengerA)) {
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getFirstPassengers().getExpenses()+"/=");
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getSecondPassengers().getExpenses()+"/=");
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getThirdPassengers().getExpenses()+"/=");
                        } else if (cabin.getSecondPassengers() != null && Objects.equals(cabin.getSecondPassengers().getFirstName(), findPassengerA)) {
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getFirstPassengers().getExpenses()+"/=");
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getSecondPassengers().getExpenses()+"/=");
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getThirdPassengers().getExpenses()+"/=");
                        } else if (cabin.getThirdPassengers() != null && Objects.equals(cabin.getThirdPassengers().getFirstName(), findPassengerA)) {
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getFirstPassengers().getExpenses()+"/=");
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getSecondPassengers().getExpenses()+"/=");
                            System.out.println(findPassengerA + "'s Total Expense is -  Rs " + cabin.getThirdPassengers().getExpenses()+"/=");
                        }
                    }
                    break;
                case "b":
                    System.out.println("Enter the FirstName of the Passenger?");
                    String findPassengerB = scan.nextLine();
                    int total = 0;
                    for (Cabin cabin : CabinList) {
                        if (Objects.equals(cabin.getFirstPassengers().getFirstName(), findPassengerB)) {
                            total= total+cabin.getFirstPassengers().getExpenses();
                        } else if (cabin.getSecondPassengers() != null && Objects.equals(cabin.getSecondPassengers().getFirstName(), findPassengerB)) {
                            total= total+cabin.getSecondPassengers().getExpenses();
                        } else if (cabin.getThirdPassengers() != null && Objects.equals(cabin.getThirdPassengers().getFirstName(), findPassengerB)) {
                            total= total+cabin.getThirdPassengers().getExpenses();
                        }
                    }
                    System.out.println(findPassengerB+"'s Total Expenses for the Cabin is -  Rs "+total+"/=");
                    break;
                default:
                    System.out.println("Try Again !!");
            }
        }
    }

    private static void initializeCabins() {
        Arrays.fill(viewCabins, "e");
    }

    private static void sortPassengers() {
        String[] passengerStringArray = new String[passengerList.size()];
        for (int i = 0; i < passengerList.size(); i++) {
            passengerStringArray[i] = passengerList.get(i);
        }
        javaAlphabeticalSort(passengerStringArray);
    }

    private static void javaAlphabeticalSort(String[] unOrderedArray) {
        Arrays.sort(unOrderedArray);
        for (int i = 0; i < unOrderedArray.length; i++) {
            System.out.println(unOrderedArray[i]);
        }
    }

    private static void load() throws IOException, ClassNotFoundException {
        File loadFilePassenger = new File("saveTextFilePassengers.txt");
        File loadFileCabin = new File("saveTextFileCabins.txt");
        try {
            if (loadFilePassenger.length() != 0) {
                FileInputStream fileInputStream = new FileInputStream("saveTextFilePassengers.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                for (; ; ) {
                    try {
                        passengerListObj.add((Passenger) objectInputStream.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
            for (Passenger psngr : passengerListObj) {
                passengerList.add(psngr.getFirstName());
            }

            if (loadFileCabin.length() != 0) {
                FileInputStream fileInputStreamCabin = new FileInputStream("saveTextFileCabins.txt");
                ObjectInputStream objectInputStreamCabin = new ObjectInputStream(fileInputStreamCabin);
                for (; ; ) {
                    try {
                        CabinList.add((Cabin) objectInputStreamCabin.readObject());
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
            System.out.println(CabinList);
            System.out.println(passengerListObj);
            for (Cabin cabin : CabinList) {
                if (cabin.getFirstPassengers() != null) {
                    viewCabins[CabinList.indexOf(cabin)] = "taken";
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void store() {
        System.out.println("Saving....");
        String textFilePassenger = "saveTextFilePassengers.txt";
        String textFileCabin = "saveTextFileCabins.txt";
        try {
            ObjectOutputStream objectSavingPassenger = new ObjectOutputStream(new FileOutputStream(textFilePassenger));
            for (Passenger passenger : passengerListObj) {
                objectSavingPassenger.writeObject(passenger);
            }
            objectSavingPassenger.close();
            ObjectOutputStream objectSavingCabin = new ObjectOutputStream(new FileOutputStream(textFileCabin));
            for (Cabin cabin : CabinList) {
                objectSavingCabin.writeObject(cabin);
            }
            objectSavingCabin.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findPassenger() {
        System.out.println("Enter the FirstName of the Passenger?");
        String findPassenger = scan.nextLine();

        for (Cabin cabin : CabinList) {
            if (Objects.equals(cabin.getFirstPassengers().getFirstName(), findPassenger)) {
                System.out.println(findPassenger + "'s Cabin Number is " + (CabinList.indexOf(cabin) + 1));
            } else if (cabin.getSecondPassengers() != null && Objects.equals(cabin.getSecondPassengers().getFirstName(), findPassenger)) {
                System.out.println(findPassenger + "'s Cabin Number is " + (CabinList.indexOf(cabin) + 1));
            } else if (cabin.getThirdPassengers() != null && Objects.equals(cabin.getThirdPassengers().getFirstName(), findPassenger)) {
                System.out.println(findPassenger + "'s Cabin Number is " + (CabinList.indexOf(cabin) + 1));
            }
        }
    }

    private static void deletePassenger() {
        System.out.println("Enter the first name of the passenger?");
        String deletePassenger = scan.nextLine();

        for (Passenger passenger : passengerListObj) {
            if (passenger.getFirstName().equals(deletePassenger)) {
                passengerListObj.remove(passenger);
                for (Cabin cabin : CabinList) {
                    if (cabin.getFirstPassengers() != null && cabin.getFirstPassengers().equals(passenger) || cabin.getSecondPassengers() != null && cabin.getSecondPassengers().equals(passenger) || cabin.getThirdPassengers() != null && cabin.getThirdPassengers().equals(passenger)) {
                        CabinList.remove(cabin);
                        if(passengerWaitingList.isEmpty()){
                            viewCabins[CabinList.indexOf(cabin)] = "e";
                            cabin.setFirstPassengers(null);
                            cabin.setSecondPassengers(null);
                            cabin.setThirdPassengers(null);
                            System.out.println("Customer deleted successfully !!");
                        }else{
                            cabin.setFirstPassengers(cabinWaitingList.get(0).getFirstPassengers());
                            cabin.setSecondPassengers(cabinWaitingList.get(0).getSecondPassengers());
                            cabin.setThirdPassengers(cabinWaitingList.get(0).getThirdPassengers());
                            passengerList.add(passengerWaitingList.get(0).getFirstName());
                            passengerListObj.add(passengerWaitingList.get(0));
                            CabinList.add(cabinWaitingList.get(0));
                            cabinWaitingList.remove(0);
                            System.out.println("Customer deleted successfully !!");
                            System.out.println("Next Passenger in Waiting List is added to the cabin..");
                        }
                    }
                }
            } else {
                System.out.println("No Passenger has found under that name..");
            }
        }
    }

    private static void displayEmptyCabins() {
        String[] viewEmptyCabins = new String[12];
        for (int i = 0; i < viewEmptyCabins.length; i++) {
            if (viewCabins[i] == "e") {
                viewEmptyCabins[i] = "empty";
            } else {
                viewEmptyCabins[i] = "taken";
            }
        }
        for (int i = 0; i < viewCabins.length; i++) {
            System.out.print(viewEmptyCabins[i] + " | ");
        }
    }

    private static void viewAllCabins() {
        for (int i = 0; i < viewCabins.length; i++) {
            System.out.print(viewCabins[i] + " | ");
        }
    }

    private static void addCustomerToCabin() {
        if (CabinList.size() < 12) {
            List<Passenger> passengerCountList = new ArrayList<>();
            System.out.println("Enter the number of Passengers ?");
            int passengerCount = scan.nextInt();
            if (passengerCount > 0 && passengerCount < 4) {
                for (int i = 1; i < passengerCount + 1; i++) {
                    System.out.println("Enter the first name of the passenger");
                    String passengerFirstName = scan.next();
                    System.out.println("Enter the sure name of the passenger");
                    String passengerSureName = scan.next();
                    System.out.println(i + " num of Passengers added...");
                    System.out.println("Press any key to continue to next passenger... :");
                    String uselessSkip = scan.next();
                    Passenger passenger = new Passenger(passengerFirstName, passengerSureName);
                    passengerCountList.add(passenger);
                    passengerList.add(passenger.getFirstName());
                    passengerListObj.add(passenger);
                }
                Cabin cabin = new Cabin();
                cabin.setFirstPassengers(passengerCountList.get(0));
                if (passengerCountList.size() == 3) {
                    cabin.setSecondPassengers(passengerCountList.get(1));
                    cabin.setThirdPassengers(passengerCountList.get(2));
                } else if (passengerCountList.size() == 2) {
                    cabin.setSecondPassengers(passengerCountList.get(1));
                } else {
                    System.out.println("Invalid Error");
                }
                CabinList.add(cabin);
                viewCabins[CabinList.size() - 1] = "taken";

                System.out.println("Passengers added successfully !!");
            } else {
                System.out.println("Maximum num of passengers cabin holds is three");
            }
        } else {
            System.out.println("All cabins are full..");
            System.out.println("...");
            System.out.println("Would you like to join the waiting List...? (y / n)");
            String answer = scan.nextLine().toLowerCase();
            if(answer.equals("yes")){
                List<Passenger> passengerCountList = new ArrayList<>();
                System.out.println("Enter the number of Passengers ?");
                int passengerCount = scan.nextInt();
                if (passengerCount > 0 && passengerCount < 4) {
                    for (int i = 1; i < passengerCount + 1; i++) {
                        System.out.println("Enter the first name of the passenger");
                        String passengerFirstName = scan.next();
                        System.out.println("Enter the sure name of the passenger");
                        String passengerSureName = scan.next();
                        System.out.println(i + " num of Passengers added...");
                        Passenger passenger = new Passenger(passengerFirstName, passengerSureName);
                        passengerCountList.add(passenger);
                        passengerWaitingList.add(passenger);
                    }
                    Cabin cabin = new Cabin();
                    cabin.setFirstPassengers(passengerCountList.get(0));
                    if (passengerCountList.size() == 3) {
                        cabin.setSecondPassengers(passengerCountList.get(1));
                        cabin.setThirdPassengers(passengerCountList.get(2));
                    } else if (passengerCountList.size() == 2) {
                        cabin.setSecondPassengers(passengerCountList.get(1));
                    } else {
                        System.out.println("Invalid Error");
                    }
                    cabinWaitingList.add(cabin);
                    System.out.println("Passengers added to the waiting List !!");
                } else {
                    System.out.println("Maximum num of passengers cabin holds is three");
                }
            }else if(answer.equals("no")){
                System.out.println("thanks for coming!");
            }else{
                System.out.println("invalid input");
            }
        }
    }
}
