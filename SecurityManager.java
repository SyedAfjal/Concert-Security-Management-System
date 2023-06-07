import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SecurityManager {
    public static void main(String[] args) {
        SecurityCheck securityCheck = new SecurityCheck();
        Line line = new Line();
        Scanner input = new Scanner(System.in);
        String option = "";
        while(!option.equals("Q")){
            mainMenu();
            option = input.nextLine();
            switch (option.toUpperCase()){
                case"A":
                    try{
                        System.out.println("Please enter a name:");
                        String name = input.nextLine();
                        System.out.println("Please enter a seat number:");
                        int seatNumber = input.nextInt();
                        //Person p = new Person(name,seatNumber);
                        securityCheck.addPerson(name,seatNumber);
                        System.out.println("Loading....");
                        System.out.println(name+ " successfully added to the line " + line.getLineIndex());
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "N":
                    try {
                        securityCheck.removeNextAttendee();
                        System.out.println(securityCheck.removeNextAttendee().getName()+" from seat "+ securityCheck.removeNextAttendee().getSeatNumber()+ " removed from line");
                    }catch (Exception ex){
                        ex.getMessage();
                    }

                    break;
                case"R":
                    try{
                        ArrayList<Integer> lines = new ArrayList<Integer>();
                        System.out.println("Lines to remove:");
                        String[] removeInput = input.nextLine().split(",");
                        int[] linesToRemove = new int[removeInput.length];
                        // Convert input to integer array
                        for (int i = 0; i < removeInput.length; i++) {
                            linesToRemove[i] = Integer.parseInt(removeInput[i].trim());
                        }
                        securityCheck.removeLines(linesToRemove);
                        // Display loading message
                        System.out.println("Loading...");
                        System.out.print("Lines ");
                        for (int i = 0;i<linesToRemove.length;i++){
                            System.out.print(linesToRemove[i]+",");
                        }
                        System.out.println(" have been decommissioned!");
                        securityCheck.printLines();
                    }catch (Exception ex){
                        ex.getMessage();
                    }

                    break;
                case"L":
                    try{
                        System.out.println("Add how many more lines?:");
                        int addingLines = input.nextInt();
                        securityCheck.addNewLines(addingLines);
                        System.out.println("Lines " + line.getLineIndex() + " and" + line.getLineIndex() + " introduced!");

                    } catch (Exception ex){
                        ex.getMessage();
                    }
                    break;
                case"P":
                    securityCheck.printAllLines();
                    break;

            }
        }
    }
    public static void mainMenu(){
        System.out.println("Menu:\n" + "o (A) – Add Person\n" +
                "o (N) – Next Person\n" +
                "o (R) – Remove Lines\n" +
                "o (L) – Add New Lines\n" +
                "o (P) – Print All Lines\n" +
                "o (Q) – Quit");
    }
}

