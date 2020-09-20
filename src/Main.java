import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
    	
    	boolean even = false;
    	boolean odd = false;
    	boolean playerExits = false;
    	Integer num = 0;
    	Entry entry;
    	char play = 'Y';
    	
    	ArrayList<String> playerList = new ArrayList<>();
    	ArrayList<Entry>  entries = new ArrayList<>();
 	
    	
    	System.out.println("Initializing: Loading PlayerNames Data ");
    	playerList = readFile();
    	System.out.println("Initializing complete: Loading Data completed successfully");
    	
    	
    	
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);
        
//        System.out.println("Do you want to play?: Y/N");
//        play = input.nextLine().charAt(0);
//        
//        if(play=='Y' || play =='N') {
//        	
//        } else {
//        	System.out.println("Please enter either 'Y' for yes or 'N' for no.");
//        }
//        
        while (play=='Y') {
	        System.out.print("Enter your name: ");
	        String name = input.next();
	        
	        // Search for Players name
	        for (String nameValue : playerList){
	        	  if (nameValue.contains(name)){
	        		  playerExits = true;
	        		  System.out.println( name + " name Found");
	        	  }
	        	}
	        
	        
	        if (playerExits) {
	        	
	        	System.out.print("Enter your bet. Either number, EVEN or ODD: ");
	            String bet = input.next();
	            if(bet.equalsIgnoreCase("even")) {
	            	even = true;
	            } else if(bet.equalsIgnoreCase("odd")) {
	            	odd = true;
	            } else {
	               num = Integer.valueOf(bet);
	            }
	            
	            System.out.print("Enter your bet ammount: ");
	            double ammount = input.nextDouble();
	            
	            if(bet.equalsIgnoreCase("even")) {
	            	entry = new Entry (name, even, ammount);
	            } else if(bet.equalsIgnoreCase("odd")) {
	            	entry = new Entry (name, odd, ammount, bet);
	            } else {
	            	entry = new Entry (name, num, ammount);
	            }
	            entries.add(entry);
	            
	            Entry resultEntry = playBet(entry);
	
	            System.out.println("Results, win is: " +  resultEntry.isWin() + " Win amount is " + resultEntry.getWinAmmount());
	        	
	        } else {
	        	System.out.println( " Sorry player with name: " + name + " does not exist.");
	        }
	        
	        System.out.print("Do you want to continue? Y/N");
	        
	        String answer = input.next();
	        
	        if(answer.length()>0) {
	        	char playAgain = answer.charAt(0);
	        	if (playAgain=='N') {
		        	System.out.print("Goodbye!");
		            System.exit(1);
		        } else if (playAgain=='Y'){
		           continue;
		        }
	        } else {
//	        	System.out.println("TESTING ------- ERROR  anser length is: " + answer.length());
	        }
	        
	        
	        
        
        }
        
        

        
        
        // Closes the scanner
        input.close();
    	
    	
    }
    
    
    public static ArrayList<String> readFile() {
    	ArrayList<String> nameList = new ArrayList<>();
    	try {
    	      File myObj = new File("PlayerNames.txt");
    	      Scanner myReader = new Scanner(myObj);
    	      while (myReader.hasNextLine()) {
    	        String name = myReader.nextLine();
    	        System.out.println(name);
    	        nameList.add(name);
    	      }
    	      myReader.close();
    	} catch (FileNotFoundException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	}
		return nameList;
    }
    
    
    public static Entry playBet(Entry entry) {
    	int result = genRandomNumber();
    	
    	if(entry.isEven()) {
    		if ( result % 2 == 0 ) {
    			entry.setWin(true);
        		entry.setWinAmmount(entry.getBetAmmount() * 2);
    		}
    	} else if (entry.isOdd()) {
    		if (!( result % 2 == 0)) {
    			entry.setWin(true);
        		entry.setWinAmmount(entry.getBetAmmount() * 2);
    		}
    	} else {
    		if (entry.getNumber() == result) {
    			entry.setWin(true);
        		entry.setWinAmmount(entry.getBetAmmount() * 36);
    		}
    	}
    	
		return entry;
    }
    
    public static int genRandomNumber() {
    	Random ran = new Random();
    	int x = ran.nextInt(36) + 1;
    	 System.out.println("Generating Random Number : " + x);
    	return x;
    	
    }
    
    
    
}
