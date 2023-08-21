import java.util.ArrayList;
import java.util.Scanner;

//import java.util.Arrays;

public class App2 {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * @param args
     */
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
        ArrayList<String> holders = new ArrayList();
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
                    System.out.printf("ID: SDB-%05d \n", (holders[index][0]+ 1));

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
                        
                    }while(true);

                    // Check Deposi Amount
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
                        } else {
                            valid = true;
                            break;
                        }
                    }while(true);
                    // Add account holder information to the holders list
                    //holders.add(new String[]{name, String.valueOf(deposit)});
                    // Move back to the main dashboard
                    screen = DASHBOARD;
                    break;
            }
        }while(true);
    }
}