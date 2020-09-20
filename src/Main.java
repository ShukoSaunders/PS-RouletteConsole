import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
    	
    	String format = "%-40s%-40s%-40s%-40s%n";
    	String format2 = "%-40s%-40s%-40s%n";
        String heading11 = "Player";
        String heading12 = "Bet";
        String heading13 = "Outcome";
        String heading14 = "Winings";
        String heading21 = "Total Win";
        String heading22 = "Total Bet";
    	boolean even = false;
    	boolean odd = false;
    	boolean playerExits = false;
    	Integer round = 0;
    	Integer num = 0;
    	Entry entry;
    	char play = 'Y';
    	
    	ArrayList<String> playerList = new ArrayList<>();
    	ArrayList<String> playerListTotal = new ArrayList<>();
    	ArrayList<Entry>  entries = new ArrayList<>();
    	ArrayList<EntryTotal>  entryTotals = new ArrayList<>();

    	
    	System.out.println("Initializing: Loading PlayerNames Data ");
    	playerList = readFile();
    	entryTotals = readInputFile2();
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
		            printResults(format, format2, heading11, heading12, heading13, heading14,heading21, heading22, playerList, entries, entryTotals);
		            
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
	
	 private static ArrayList<EntryTotal> readInputFile2() {
		 ArrayList<EntryTotal> entryTotalList = new ArrayList<>();
		 try {
	   	      File myObj = new File("InputFile2.txt");
	   	      Scanner myReader = new Scanner(myObj);
	   	      
	   	      while (myReader.hasNextLine()) {
	   	        String lineEntry = myReader.nextLine();
	   	        String [] entrylist = lineEntry.split(",");
	   	        EntryTotal entryTotal = new EntryTotal(entrylist[0],Double.parseDouble(entrylist[1]), Double.parseDouble(entrylist[2]));
	   	        entryTotalList.add(entryTotal);
	   	      }
	   	      
	   	      myReader.close();
   	      
		  } catch (FileNotFoundException e) {
		   	      System.out.println("An error occurred.");
		   	      e.printStackTrace();
		  }
		 
		 return entryTotalList;
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
    
    public static void printResults(String format,String format2, String heading11, String heading12, String heading13, String heading14, String heading21, String heading22, ArrayList<String> playerList, ArrayList<Entry> entries, ArrayList<EntryTotal>  entryTotalsList) {  	
    	System.out.println("---------------------Output1---------------------");
    	System.out.printf(format, heading11, heading12, heading13, heading14);
        System.out.println("---");
        
        int betCount = 0;
        
        
        for(String player: playerList) {
        	EntryTotal entryTotal = new EntryTotal();
        	String playerName = player;
        	betCount = 0;
        	for (Entry entryCheck : entries){
	        	  if (entryCheck.getName().contains(player)){
	        		  betCount++;
	        		  System.out.printf(format, player, entryCheck.getBetValue(), entryCheck.getOutcome(), entryCheck.getWinAmmount());
	        	  }
	        	  
	        	}
        	entryTotal.setName(player);
  		  	entryTotal.setTotalBet(betCount);
  		  	double sum = entries.stream().filter(s -> s.getName().equals(playerName)).filter(e -> e.getOutcome().equals(Outcome.WIN)).mapToDouble(w -> w.getWinAmmount()).sum();
  		  	entryTotal.setTotalWin(sum);
        	entryTotalsList.add(entryTotal);
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println("---------------------Output2---------------------");
        System.out.printf(format2, heading11, heading21, heading22);
        System.out.println("---");
        ArrayList<EntryTotal>  entryTotalsListVariable = new ArrayList<>();
        
        for(EntryTotal entryTotale: entryTotalsList) {
        	EntryTotal entryTotalT = new EntryTotal();
        	String playerNamee = entryTotale.getName();
        	entryTotalT.setName(playerNamee); 
        	double betTotal = entryTotalsList.stream().filter(s -> s.getName().equals(playerNamee)).mapToDouble(w -> w.getTotalBet()).sum();
        	double winTotal = entryTotalsList.stream().filter(s -> s.getName().equals(playerNamee)).mapToDouble(w -> w.getTotalWin()).sum();
        	entryTotalT.setTotalBet(betTotal);
        	entryTotalT.setTotalWin(winTotal);
        	
        	entryTotalsListVariable.add(entryTotalT);
        }
        
        List<EntryTotal>  entryTotalsListPrint = entryTotalsListVariable.stream() 
        		  .filter(distinctByKey(EntryTotal::getName)) 
        		  .collect(Collectors.toList());
        
        for(EntryTotal entryTotale: entryTotalsListPrint) {
        	System.out.printf(format2, entryTotale.getName(), entryTotale.getTotalWin(), entryTotale.getTotalBet());
        }
        System.out.println("");
        System.out.println("");
        //
    }
    
    
    public static <T> Predicate<T> distinctByKey(
    	    Function<? super T, ?> keyExtractor) {
    	  
    	    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
    	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
    	}

   
    
    
    
}
