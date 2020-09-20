import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
    	
    	String format = "%-40s%-40s%-40s%-40s%n";
        String row1column1 = "Player";
        String row1column2 = "Bet";
        String row1column3 = "Outcome";
        String row1column4 = "Winings";
    	boolean even = false;
    	boolean odd = false;
    	boolean playerExits = false;
    	Integer round = 0;
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
        

        while (play=='Y') {
        	round = round++;
        	
        	while (true) {
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
		            
		            
		            // Place the bet for this entry
		            entries = placeBet(entries, entry);
		            
		            
		        } else {
		        	System.out.println( " Sorry player with name: " + name + " does not exist.");
		        }
		        
		        System.out.print("Do you want to continue? Y/N");
		        
		        String answer = input.next();
		        
		        if(answer.length()>0) {
		        	char playAgain = answer.charAt(0);
		        	if (playAgain=='N') {
			        	System.out.print("Thank You!");
			        	break;
			        } else if (playAgain=='Y'){
			           continue;
			        }
		        } else {
		        }

		        
	        }
        	
        	System.out.println("Do you want to end this round? Y/N");
	        
	        String roundAnswer = input.next();
	        if(roundAnswer.length()>0) {
	        	char endRound = roundAnswer.charAt(0);
	        	if (endRound=='Y') {
	        		// Play all bets for this round
	        		entries = playBet(entries);
	        		
	        		//Print Results for this round
		            printResults(format, row1column1, row1column2, row1column3, row1column4, playerList, entries);
		            
		            //Clear Bets and Entries
		            entries.clear();
		        	System.out.print("Goodbye!");
		        	System.exit(1);
		        } else if (endRound=='N'){
		        	System.out.println("continue playing....");
		           continue;
		        }
	        } else {
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
    
    
    public static ArrayList<Entry> placeBet(ArrayList<Entry> entries,Entry entry) {
    	entries.add(entry);
    	
    	
		return entries;
    }
    
    public static ArrayList<Entry> playBet(ArrayList<Entry> entries) {
    	int result = genRandomNumber();
    	
    	for(Entry entry: entries) {
    		
    		if(entry.isEven()) {
        		if ( result % 2 == 0 ) {
        			entry.setOutcome(Outcome.WIN);
            		entry.setWinAmmount(entry.getBetAmmount() * 2);
        		} else {
        			entry.setOutcome(Outcome.LOSE);
        		}
        	} else if (entry.isOdd()) {
        		if (!( result % 2 == 0)) {
        			entry.setOutcome(Outcome.WIN);
            		entry.setWinAmmount(entry.getBetAmmount() * 2);
        		} else {
        			entry.setOutcome(Outcome.LOSE);
        		}
        	} else {
        		if (entry.getNumber() == result) {
        			entry.setOutcome(Outcome.WIN);
            		entry.setWinAmmount(entry.getBetAmmount() * 36);
        		} else {
        			entry.setOutcome(Outcome.LOSE);
        		}
        	}
    		
    	}
    	
    	return entries;
    	
    }
    
    public static int genRandomNumber() {
    	Random ran = new Random();
    	int x = ran.nextInt(36) + 1;
    	System.out.println("");
        System.out.println("");
    	 System.out.println("Number: " + x);
    	return x;
    	
    }
    
    public static void printResults(String format, String row1column1, String row1column2, String row1column3, String row1column4, ArrayList<String> playerList, ArrayList<Entry> entries) {  	
        System.out.printf(format, row1column1, row1column2, row1column3, row1column4);
        System.out.println("---");
        
        for(String player: playerList) {
        	for (Entry entryCheck : entries){
	        	  if (entryCheck.getName().contains(player)){
	        		  System.out.printf(format, player, entryCheck.getBetValue(), entryCheck.getOutcome(), entryCheck.getWinAmmount());
	        	  } 
	        	}
        }
        System.out.println("");
        System.out.println("");
    }
    
    
    
}
