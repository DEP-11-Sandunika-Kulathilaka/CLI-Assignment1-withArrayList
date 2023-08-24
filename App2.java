import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//import java.util.Arrays;

public class App2 {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREATE_ACCOUNT = "Create New Account";
        final String DEPOSITS = "Deposits";
        final String WITHDRAWLS = "Withdrawls"; 
        final String TRANSFER = "Transfer";
        final String CHECK_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete Accounts";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String screen = DASHBOARD;
        //ArrayList<String> holders = new ArrayList();
        String[][] customers = new String[0][];
        //String[] accountNumber = {};

        mainLoop:
        do {
            final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen) {
                case DASHBOARD:
                System.out.println("\n[1] Create New Account");
                System.out.println("[2] Deposits");
                System.out.println("[3] Withdrawls");
                System.out.println("[4] Transfer");
                System.out.println("[5] Check Account Balance");
                System.out.println("[6] Delete Accounts");
                System.out.println("[7] Exit\n");
                System.out.print("Enter the option: ");
                int option = scanner.nextInt();
                scanner.nextLine();


                switch (option){
                    case 1:screen = CREATE_ACCOUNT; break;
                    case 2:screen = DEPOSITS; break;
                    case 3:screen = WITHDRAWLS; break;
                    case 4:screen = TRANSFER; break;
                    case 5:screen = CHECK_BALANCE; break;
                    case 6:screen = DELETE_ACCOUNT ; break;
                    case 7:System.out.println(CLEAR); System.exit(0);
                    default: continue;
                }
                break;

                case CREATE_ACCOUNT:
                    // Automatically ID Printing
                    
                    String id = String.format("ID: SDB-%05d \n", customers.length +1);
                    System.out.println(id);

                    boolean valid = true;
                    String name;
                    double deposit;

                    // Name Validation
                    nameValidation:
                    do{
                        valid = true;
                        System.out.print("Name: ");
                        name = scanner.nextLine().strip().toUpperCase();

                        if (name.isEmpty()) {
                            valid = false;
                            System.out.printf(ERROR_MSG, "Can't be Empty");
                            continue;    
                        } 
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) ||
                            Character.isSpaceChar(name.charAt(i)))) {
                                valid = false;
                                System.out.printf(ERROR_MSG, "Invalid Name");
                                continue nameValidation;
                            }   
                        }
                        
                    }while(!valid);

                    // Check Deposit Amount
                    amountValidation:
                    do{
                        valid = true;
                        System.out.print("Intial Deposit: ");
                        deposit = scanner.nextDouble();
                        scanner.nextLine();

                        if (deposit < 5000) {
                            valid = false;
                            System.out.printf(ERROR_MSG, "Insufficient Balance");
                            continue amountValidation;
                        }

                    }while(!valid);

                    

                    String[][] newCustomer = new String [customers.length+1][3];
                    for (int i = 0; i < customers.length; i++) {
                        newCustomer[i] = customers[i];
                    }
    
                    newCustomer[newCustomer.length-1][0] = id;
                    newCustomer[newCustomer.length-1][1] = name;
                    newCustomer[newCustomer.length-1][2] = String.valueOf(deposit);

                    customers = newCustomer;

                    //System.out.println(Arrays.toString(customers));

                    System.out.printf(SUCCESS_MSG, String.format("%s-%s added succesfully", id, name));

                    System.out.print("Do you want to add another ? (Y/n) : \n");
                    if (scanner.nextLine().toUpperCase().strip().equals("Y"))
                    continue;

                    screen = DASHBOARD; 
                    break;

                case DEPOSITS:

                String accountNumber;
                // Enter the A/C No:
                accountNumberValidation:
                do{
                    valid = true;
                    System.out.print("Enter the Account Number: ");
                    accountNumber = scanner.nextLine().strip();

                    if (accountNumber.isEmpty()) {
                        valid = false;
                        System.out.printf(ERROR_MSG, "Can't be Empty");
                        System.out.print("Do you want to try again? (Y/n) : \n");
                        if (scanner.nextLine().toUpperCase().strip().equals("Y"))
                        continue accountNumberValidation; 
                        screen = DASHBOARD;
                        break;

                    } 
                    if (!accountNumber.startsWith("SDB-")) {
                        valid = false;
                        System.out.printf(ERROR_MSG, "Invalid Format");
                        System.out.print("Do you want to try again ? (Y/n) : \n");
                        if (scanner.nextLine().toUpperCase().strip().equals("y"))
                        continue accountNumberValidation;
                        screen = DASHBOARD;
                        break;

                    }
                    String idDigits = accountNumber.substring(4);

                    for (int j = 0; j < idDigits.length(); j++) {

                        if (!Character.isDigit(idDigits.charAt(j)) || Character.isSpaceChar(idDigits.charAt(j))) {
                            valid =false;
                            System.out.printf(ERROR_MSG, "Invalid Format");
                            System.out.print("Do you want to try again ? (Y/n) : \n");
                            if (scanner.nextLine().toUpperCase().strip().equals("y"))continue;  
                            screen = DASHBOARD; 
                            break;
                        }
                    }

                }while(!valid);   

                for (int j = 0; j < customers.length; j++) {
                            if (accountNumber.equals(customers[j])) {

                                //int accNumber = customers[j][0];
                                valid = false;
                                System.out.printf(ERROR_MSG, "Not Found");
                                System.out.print("Do you want to try again ? (Y/n) : \n");
                                if (!scanner.nextLine().toUpperCase().strip().equals("Y"))
                                screen = DASHBOARD;
                                break;
                            }

                        }

                //Current Balance  
                    
            }
    }while(true);
}
}