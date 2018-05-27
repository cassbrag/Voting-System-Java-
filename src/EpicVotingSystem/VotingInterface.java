package EpicVotingSystem;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Date : 19/10/2017
 */

public class VotingInterface
{
   private VotingController vc;
   private Staff theStaff;
   private Candidate theCandidate;
   private Admin theAdmin;
   private final String USERNAME = "";
   private final String PASSWORD = "";
   private int numberOfCandidates = 0;
   private int loginAttempts = 0;
   
   private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
   
   DecimalFormat dfp = new DecimalFormat("#.#");
   DecimalFormat dfv = new DecimalFormat("#");
   String dateAsString = new Date().toString();
 
   public static void main(String[] args)
   {
       VotingInterface vi = new VotingInterface();
       vi.start();
   }

   public void start()
   {
       vc = new VotingController();
       commenceVoting();
   }
  //=======================================================================
    //This method adds a new account to the system.
	public void addAccount()
	{
		 System.out.println("Enter \"C\" to add a Candidate account.\nEnter \"S\" to add a Staff account.\nEnter \"A\" to add an Admin account.\nEnter \"Q\" to go back.");
		 String input = getInput();
		 
		 //Details to add a new Candidate account.
		 if (input.equalsIgnoreCase("C"))
	     {
			 try
			 {
	            System.out.print("Enter a new Candidate code: ");
	            int newCandCode = Integer.parseInt(getInput());
			 
	            //Checks if a unique Candidate code is entered.
	            if (vc.checkCandidateCode(newCandCode) == false)
	            {
	        	    System.out.println("The Candidate code that you have entered already exists.\n");
	        	    addAccount();
	            }
	            else
	            {
	        	    System.out.print("Enter a new Candidate name: ");
	        	    String newCandName = getInput();
	        
	        	    //Checks if input is empty.
	        	    if (newCandName.equalsIgnoreCase(""))
	           	    {
	           		   System.out.println("You must enter a name.\n");
	           		   addAccount();
	           	    }
	           	    else
	           	    {
	        	       //Automatically sets the new Candidate vote count to 0.
	        	       int newCandVC = Integer.parseInt("0");
	        	 
	        	       //Saves new account to the database.
	        	       vc.addCandidateData(newCandCode, newCandName, newCandVC);
	        
	        	       System.out.println("The new Candidate account is added to the System.\n");
	        	       manageAdmin();
	           	    }
	            }
			 }
	         catch (NumberFormatException e)
			 {
	        	 //Invalid Candidate code entered.
				 System.out.println("Cannot understand your input.\n");
				 addAccount();
			 }
	     }
		 //Details to add a new Staff account.
	     else if (input.equalsIgnoreCase("S"))
	     {
	    	 try
             {
	    		 System.out.print("Enter a new Staff ID: ");
	 	        int newStaffId = Integer.parseInt(getInput());
	 	            
	 	        //Checks if a unique Staff ID is entered.
	 	        if (vc.checkStaffId(newStaffId) == false)
	 		    {
	 		        System.out.println("The Staff ID that you have entered already exists.\n");
	 		        addAccount();
	 		    }
	 	        else
                {
	 	        	System.out.print("Enter a new Staff name: ");
	 	            String newStaffName = getInput();
	 	            
	 	            //Checks if input is empty.
	 	            if (newStaffName.equalsIgnoreCase(""))
	           	    {
	           		   System.out.println("You must enter a name.\n");
	           		   addAccount();
	           	    }
	           	    else
	           	    {
	 	               //Automatically sets the new Staff vote count to 0.
	 	               int newStaffVC = Integer.parseInt("0");
	 	            
	 	               System.out.print("Enter a new Staff password: ");
	 	               String newStaffPass = getInput();
	 	            
	 	               //Checks if input is empty.
	 	               if (newStaffPass.equalsIgnoreCase(""))
	           	       {
	           		      System.out.println("You must enter a password.\n");
	           		      addAccount();
	           	       }
	           	       else
	           	       {
	 	                  //Puts a String placeholder in the database file.
	 	                  String newStaffDate = "Not Voted";
	 	            
	 	                  //Saves new account to the database.
	 	                  vc.addStaffData(newStaffId, newStaffName, newStaffVC, newStaffPass, newStaffDate);
	 	           
	 	                  System.out.println("The new Staff account is added to the System.\n");
	 	                  manageAdmin();
	           	       }
	           	    }
                }
             }
	    	 catch (NumberFormatException e)
			 {
	    		 //Invalid Staff ID entered.
				 System.out.println("Cannot understand your input.\n");
				 addAccount();
			 }
	     }
		 //Details to add a new Admin account.
	     else if (input.equalsIgnoreCase("A"))
	     {
	    	 try
	    	 {
	     	    System.out.print("Enter a new Admin ID: ");
	            int newAdminId = Integer.parseInt(getInput());
	            
	            //Checks if a unique Admin ID is entered.
	            if (vc.checkAdminId(newAdminId) == false)
		        {
		           System.out.println("The Admin ID that you have entered already exists.\n");
		           addAccount();
		        }
	            else
	            {
	               System.out.print("Enter a new Admin name: ");
	               String newAdminName = getInput();
	               
	               //Checks if input is empty.
	               if (newAdminName.equalsIgnoreCase(""))
	           	   {
	           		  System.out.println("You must enter a name.\n");
	           		  addAccount();
	           	   }
	           	   else
	           	   {
	                  System.out.print("Enter a new Admin username: ");
	                  String newAdminUsername = getInput();
	                  
	                  //Checks if a unique new Admin username is entered.
	                  if (vc.checkAdminUsername(newAdminUsername) == false)
	  		          {
	  		           System.out.println("The Admin username that you have entered already exists.\n");
	  		           addAccount();
	  		          }
	                  else
	                  {
	                	 //Checks if input is empty.
	                     if (newAdminUsername.equalsIgnoreCase(""))
		           	     {
		           		    System.out.println("You must enter a username.\n");
		           		    addAccount();
		           	     }
		           	     else
		           	     {
	                        System.out.print("Enter a new Admin password: ");
	                        String newAdminPass = getInput();
	             
	                        //Checks if input is empty.
	                        if (newAdminPass.equalsIgnoreCase(""))
	  	           	        {
	  	           		       System.out.println("You must enter a password.\n");
	  	           		       addAccount();
	  	           	        }
	  	           	        else
	  	           	        {
	                           //Saves new account to the database.
	                           vc.addAdminData(newAdminId, newAdminName, newAdminUsername, newAdminPass);
	              
	                           System.out.println("The new Admin account is added to the System.\n");
	                           manageAdmin();
	  	           	        }
		           	     }
	                  }
	           	   }
	            }
	    	 }
	    	 catch (NumberFormatException e)
			 {
				 System.out.println("Cannot understand your input.\n");
				 addAccount();
			 }
	     }
	     else if (input.equalsIgnoreCase("Q"))
	     {
	    	 manageAdmin();
	     }
	     else
	     {
	         System.out.println("Cannot understand your input.\n");
	         addAccount();
	     } 
	}
  //=======================================================================
    //This method displays a menu to vote or login as admin.
	public void commenceVoting()
	{
		System.out.println("Welcome to the Epic Voting System!");
		System.out.println("This was created by Epic Construction.\n");
		System.out.println("Please enter one of the following three options:");
		System.out.println("\"Voter\" > Vote for a Candidate\n\"Admin\" > Login to access administrator functions\n\"Help\"  > Instructions for the voting system");
		String theInput = getInput();
		
		if (theInput.equalsIgnoreCase("Voter")) 
		{
			manageVote();
		}
		else if (theInput.equalsIgnoreCase("Admin")) 
		{
			validateAdmin();
		}
		else if (theInput.equalsIgnoreCase("Help")) 
		{
			System.out.println("Once you have logged in, a Candidate list will be displayed.");
			System.out.println("You will be required to enter the Candidate code of the Candidate that you would like to vote for.");
			System.out.println("Do not worry if you enter the wrong code! You will be asked to confirm your vote.");
			System.out.println("Once confirmed, your vote will be saved and you cannot vote again.\n");
			System.out.println("Press enter to go back to the main screen.");
		    new Scanner(System.in).nextLine();
		    commenceVoting();
		}
		else 
		{
			System.out.println("Cannot understand your input.\n");
			commenceVoting();
		}
	}
  //=======================================================================
	// This method welcomes the staff after a successful login and displays candidate names and instructions on how to place a vote.
	public void displayVotingScreen()
	{
	    System.out.print("\"V\" > Vote for a Candidate\n\"Q\" > Log out\n");
	    String theInput = getInput();
	    	 
	    if (theInput.equalsIgnoreCase("V")) 
	    {
	       getStaffVote();
	    }
	    else if (theInput.equalsIgnoreCase("Q")) 
	    {
	     	System.out.println("You have logged out.\n");
	     	commenceVoting();
	    }
	    else 
	    {
	     	 System.out.println("Cannot understand your input.");
	    	 displayVotingScreen();
	    }
	}
  //=======================================================================
	//Screen input reader.
	private String getInput()
	{
		String theInput = "";
		
		try
		{
		    theInput = in.readLine().trim();
		}
		catch(IOException e)
		{
		    System.out.println(e);
		}
		return theInput;
	}
  //=======================================================================
	// This method displays candidate names and instructions on how to place a vote.
	private void getStaffVote()
	{
	   //Displays list of Candidates.
	   listCandidatesVote();
	  
	   try
	   {
	      System.out.print("\n\nEnter the code of the Candidate that you would like to vote for: ");
	      theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
	 
	      System.out.printf("You've chosen %s, type 'yes' to confirm your vote or 'no' to chose another Candidate.\n", theCandidate.getName());
	      String theInput = getInput();
	
	      if (theInput.equalsIgnoreCase("Yes"))  
	      {
		     System.out.printf("\nYou are voting on %s.\n", dateAsString);
		 
		     //Records vote to the appropriate database files.
		     vc.recordVote(dateAsString);
		     System.out.println("Thank you for voting!\n");
		     System.out.println("Press enter to go back to the main screen.");
		     new Scanner(System.in).nextLine();
		     commenceVoting();
          }
		  else if (theInput.equalsIgnoreCase("No")) 
		  {
			 System.out.println("Enter \"V\" to vote for a Candidate or \"Q\" to go back.");
			 String input = getInput();
			 
			 if (input.equalsIgnoreCase("V"))
			 {
				 getStaffVote();
			 }
			 else if (input.equalsIgnoreCase("Q"))
			 {
				 displayVotingScreen();
			 }
			 else
			 {
				 System.out.println("Cannot understand your input.\n");
				 getStaffVote();
			 }
		  }
		  else
		  {
			 System.out.println("Cannot understand your input.\n");
			 getStaffVote();
		  }
	  }
	  catch (Exception e)
	  {
		 System.out.println("Invalid Candidate code.\n");
		 getStaffVote();
	  }
	}
  //=======================================================================
	//Displays the Candidate List for Admins.
		public void listCandidatesAdmin()
		{
			System.out.println("------------------------------");
	  	    System.out.println("\tCandidate List");
	  	    System.out.println("------------------------------");
	  	    //Headers
	  	    System.out.printf("%s%10s%16s", "Code", "Name", "Vote Count");
	  	     
	  	    //Prints list of candidates with Vote Count from the database file.
	  	    int i = vc.candidates.indexOf(0); //Sets i to equal the candidates index number from the array.
	  	    for (i = 0; i < vc.candidates.size(); i++) //Retrieves all of the index numbers.
	  	    {
	  		   System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name + "\t" + vc.candidates.get(i).votes);
	  	    }
	  	    
	  	    System.out.println("\n\nPress enter to go back.");
		    new Scanner(System.in).nextLine();
		    viewLists();
		}
  //=======================================================================
	//Displays the Candidate List for Voters.
	public void listCandidatesVote()
	{
		System.out.println("------------------------------");
  	    System.out.println("\tCandidate List");
  	    System.out.println("------------------------------");
  	    //Headers
  	    System.out.printf("%s%10s", "Code", "Name");
  	     
  	    //Prints list of candidates without Vote Count from the database.
  	    int i = vc.candidates.indexOf(0); //Sets i to equal the candidates index number from the array.
  	    for (i = 0; i < vc.candidates.size(); i++) //Retrieves all of the index numbers.
  	    {
  		   System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name);
  	    }
	}
  //=======================================================================
	//Displays the Staff List.
	public void listStaffs()
	{
		System.out.println("------------------------------");
       	System.out.println("\tStaff List");
       	System.out.println("------------------------------");
       	//Headers
       	System.out.printf("%s%12s%25s%15s%33s", "ID", "Name", "Voting Status", "Password", "Time & Date Voted");
       	   
        //Prints list of staffs from the database.
       	int i = vc.staffs.indexOf(0); //Sets i to equal the candidates index number from the array.
       	for (i = 0; i < vc.staffs.size(); i++) //Retrieves all of the index numbers.
       	{
       	   System.out.print
       	   ("\n" + vc.staffs.get(i).id + "\t" + vc.staffs.get(i).name + "\t\t" + vc.staffs.get(i).voted + "\t\t" + vc.staffs.get(i).password + "\t\t" + vc.staffs.get(i).date);
       	}
       	
       	System.out.println("\n\nPress enter to go back.");
	    new Scanner(System.in).nextLine();
	    viewLists();
	}
  //=======================================================================
	//Displays the Staff List.
	public void listAdmins()
	{
		System.out.println("----------------------------");
  	    System.out.println("\tAdmin List");
  	    System.out.println("----------------------------");
  	    //Headers
  	    System.out.printf("%s%12s%19s%18s", "ID", "Name", "Username", "Password");
  	     
  	    //Prints list of candidates from the database.
  	    int i = vc.admins.indexOf(0); //Sets i to equal the candidates index number from the array.
  	    for (i = 0; i < vc.admins.size(); i++) //Retrieves all of the index numbers.
  	    {
  	  	   System.out.print("\n" + vc.admins.get(i).id + "\t" + vc.admins.get(i).name + "\t" + vc.admins.get(i).username + "\t" + vc.admins.get(i).password);
  	    }
  	    
  	    System.out.println("\n\nPress enter to go back.");
	    new Scanner(System.in).nextLine();
	    viewLists();
	}
  //=======================================================================
	//This method helps to manage admin session after a successful login.
	 private boolean manageAdmin()
     {
        boolean adminQuit = false;
        boolean systemQuit = false;

        while (!adminQuit)
        {
            System.out.println("\"V\"  > View the current voting statistics\n\"S\"  > End voting\n\"D\"  > Display account lists\n\"U\"  > Update an account\n\"A\"  > Add an account\n\"R\"  > Remove an account\n\"Q\"  > Log out");
            String input = getInput();
            if (input.equalsIgnoreCase("V"))
            {
                adminQuit = true;
                printVoteResults();
            }
            	else if(input.equalsIgnoreCase("S"))
            	{
            		//stop system
            		adminQuit = true;
            		systemQuit = true;
            		System.out.println("Voting System Closed.");
            	}
            	else if(input.equalsIgnoreCase("D"))
                {
            	    adminQuit = true;
            	    viewLists();
                }
            	else if(input.equalsIgnoreCase("U"))
            	{
                    adminQuit = true;
                    updateAccount();
                }
            	else if(input.equalsIgnoreCase("A"))
            	{
                    adminQuit = true;
                    addAccount();
                }
            	else if(input.equalsIgnoreCase("R"))
            	{
                    adminQuit = true;
                    removeAccount();
                }
            	else if (input.equalsIgnoreCase("Q"))
            	{
            		adminQuit = true;
            		System.out.println("You have logged out.\n");
            		commenceVoting();
            	}
           else
           {
              System.out.println("Cannot understand your input.\n");
           }
        }
        return systemQuit;
     }
  //=======================================================================
	// This method enables staff login.
	 public void manageVote()
	{
		try 
        {
			   System.out.print("Please enter your Staff ID to log in: ");
			   int id = Integer.parseInt(getInput());
			   if (vc.getStaff(id) != null)
			   {
				   theStaff = vc.getStaff(id);
				   System.out.print("Please enter your Staff password to log in (CASE SENSITIVE): ");
				   String password = getInput();
				   
				   //Checks if the Staff password matches the input.
				   if (theStaff.getPassword().equals(password))
				   {
					   //Checks if the Staff member has already voted.
					   if (theStaff.hasVoted() == 0) 
					   {
						  System.out.printf("\nHello %s!\n", theStaff.getName());
			    		  displayVotingScreen();
			    	   }
			    	   else if (theStaff.hasVoted() == 1) 
			    	   {
			    		  System.out.printf("\nHello %s!\n", theStaff.getName());
			    		  System.out.print("You have voted already. Goodbye!\n");
			    		  commenceVoting();
			    	   }
				   }
			       else
			       {
					  System.out.println("Invalid password entered.\n");
					  loginAttempts++;
					  manageVote();
				   }
			   }
			   else 
			   {
				   System.out.println("\nNo match found on your ID.");
				   loginAttempts++;
				   
				   if (loginAttempts == 3)
				   {
					   System.out.println("Too many login attempts.\n");
					   commenceVoting();
				   }
				   else
				   {
				       System.out.println("Enter \"R\" to re-enter your ID or enter \"Q\" to quit.");
				       String input = getInput();
				   
				       if (input.equalsIgnoreCase("R")) 
				       {
					      manageVote();
				       }
				       else if (input.equalsIgnoreCase("Q"))
				       {
					      commenceVoting();
				       }
					   else
					   {
						   System.out.println("Cannot understand your input.");
						   manageVote();
					   }
				   }
			   }
        }
        catch (NumberFormatException ex)
		{
           System.out.println("Cannot understand your input.\n");
	       manageVote();
        }
	}
  //=======================================================================
	//This method prints out the voting statistics after a successful admin login.
	public void printVoteResults()
	{
		System.out.println("Enter \"C\" to view Candidate statistics.\nEnter \"S\" to view Staff statistics.\nEnter \"Q\" to go back.");
		String input = getInput();
		
		if (input.equalsIgnoreCase("C"))
		{
			System.out.println("==================================");
	       	System.out.println("   Candidate Voting Statistics");
	        System.out.println("==================================");
	       	System.out.println("Total Number of Candidates: " + vc.getTotalCandidates() + "\n");
	       	
	       	System.out.println("\t Voting Results");
	       	System.out.println("----------------------------------");
	       	//Headers
	       	System.out.printf("%s%12s%19s", "ID", "Name", "Vote Count");
	        
	       	//Prints list of candidates from the database.
	       	int i = vc.candidates.indexOf(0); //Sets i to equal the candidates index number from the array.
	  	    for (i = 0; i < vc.candidates.size(); i++) //Retrieves all of the index numbers.
	  	    {
	  		     System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).getName() + "\t" + vc.candidates.get(i).votes + " (" + dfp.format((vc.candidates.get(i).votes/vc.numberofVoted) * 100) + "%)");
	  	    }
	  	    
	  	    System.out.println("\n\nPress enter to go back.");
		    new Scanner(System.in).nextLine();
		    manageAdmin();
		}
		else if (input.equalsIgnoreCase("S"))
		{
			System.out.println("=====================================================================");
	       	System.out.println("\t\t  Staff Voting Statistics");
	        System.out.println("=====================================================================");
	       	System.out.println("Total Number of Staff: " + dfv.format(vc.getTotalVoters()));
			System.out.println("Number of Staffs Voted: " + dfv.format(vc.numberofVoted) + " (" + dfp.format(vc.getStaffVotedPercent()) + "%)\n");
			
	       	System.out.println("=====================================================================");
	       	System.out.println("\t\t\t      Has Voted");
	        System.out.println("=====================================================================");
	       	//Headers
	       	System.out.printf("%s%20s%38s", "ID", "Name", "Date of Vote");
	        
	        //Prints list of staffs who have voted from the database.
	       	vc.getStaffVoted();
	       	
	       	System.out.println("\n=====================================================================");
	       	System.out.println("\t\t\t   Has Not Voted");
	        System.out.println("=====================================================================");
	       	//Headers
	       	System.out.printf("%15s%30s", "ID", "Name");
	        
	        //Prints list of staffs who haven't voted from the database.
	       	vc.getStaffNotVoted();
	       	
	       	System.out.println("\n\nPress enter to go back.");
		    new Scanner(System.in).nextLine();
		    manageAdmin();
		}
		else if (input.equalsIgnoreCase("Q"))
		{
			manageAdmin();
		}
		else
        {
            System.out.println("Cannot understand your input.\n");
            printVoteResults();
        }
	}
  //=======================================================================
	//This method removes an account that the Admin selects.
	public void removeAccount()
	{
		System.out.println("Enter \"C\" to remove a Candidate account.\nEnter \"S\" to remove a Staff account.\nEnter \"A\" to remove an Admin account.\nEnter \"Q\" to go back.");
		String input = getInput();
		
		if (input.equalsIgnoreCase("C"))
        {
			try 
			{
			   System.out.print("Enter the Candidate code of the account that you would like to remove: ");
	    	   theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
	    	
	    	   System.out.printf("You've chosen %s, type 'yes' to confirm or 'no' to choose another Candidate.\n", theCandidate.getName());
               String theInput = getInput();
        	 
        	   if (theInput.equalsIgnoreCase("yes"))  
        	   {
        	      vc.removeCandidateAccount();
        	      manageAdmin();
          	   }
          	      else if (theInput.equalsIgnoreCase("no")) 
          	      {
          		     removeAccount();
          	      }
          	      else
          	      {
          	    	System.out.println("Cannot understand your input.\n");
          	    	removeAccount();
          	      }
            }
			catch (Exception e)
    		{
    			System.out.println("Invalid code entered.\n");
    			removeAccount();
    		}
        }
        else if (input.equalsIgnoreCase("S"))
        {
        	try
        	{
                System.out.print("Enter the Staff ID of the account that you would like to remove: ");
    	        theStaff = vc.getStaff(Integer.parseInt(getInput()));
    	    	
    	        System.out.printf("You've chosen %s, type 'yes' to confirm or 'no' to choose another Staff.\n", theStaff.getName());
                String theInput = getInput();
            	 
                if (theInput.equalsIgnoreCase("yes"))  
                {
                   vc.removeStaffAccount();
                   manageAdmin();
                }
                else if (theInput.equalsIgnoreCase("no")) 
                {
              	   removeAccount();
                }  
                else
        	    {
        	       System.out.println("Cannot understand your input.\n");
        	       removeAccount();
        	    }
        	}
        	catch (Exception e)
    		{
    			System.out.println("Invalid ID entered.\n");
    			removeAccount();
    		}
        }
        else if (input.equalsIgnoreCase("A"))
        {
        	try
        	{
         	    System.out.print("Enter the Admin username of the account that you would like to remove: ");
    	        theAdmin = vc.getAdmin(getInput());
    	    	
    	        System.out.printf("You've chosen %s, type 'yes' to confirm or 'no' to choose another Staff.\n", theAdmin.getName());
                String theInput = getInput();
            	 
                if (theInput.equalsIgnoreCase("yes"))  
                {
            	   vc.removeAdminAccount();
            	   manageAdmin();
                }
                else if (theInput.equalsIgnoreCase("no")) 
                {
                   removeAccount();
                }
                else
        	    {
        	       System.out.println("Cannot understand your input.\n");
        	       removeAccount();
        	    }
        	}
        	catch (Exception e)
    		{
    			System.out.println("Invalid username entered.\n");
    			removeAccount();
    		}
        }
        else if (input.equalsIgnoreCase("Q"))
        {
        	manageAdmin();
        }
        else
        {
            System.out.println("Cannot understand your input.\n");
            removeAccount();
        }
	}
  //=======================================================================
	//This method updates an account that the Admin selects.
    public void updateAccount()
    {
    	System.out.println("Enter \"C\" to update a Candidate account.\nEnter \"S\" to update a Staff account.\nEnter \"A\" to update an Admin account.\nEnter \"Q\" to go back.");
    	String input = getInput();
    	 
    	if (input.equalsIgnoreCase("C"))
        {
    		try
            {
               System.out.print("Enter the code of the Candidate account that you would like to update: ");
               theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
            
               System.out.printf("You've chosen %s, type 'yes' to confirm or 'no' to choose another Candidate.\n", theCandidate.getName());
               String theInput = getInput();
        	 
        	   if (theInput.equalsIgnoreCase("yes"))  
        	   {
          	      System.out.printf("Enter a new name for Candidate %d: ", theCandidate.getCandidateCode());
          	      String name = getInput();
          	   
          	      //Checks if the input is empty.
          	      if (name.equalsIgnoreCase(""))
          	      {
          		     System.out.println("You must enter a name.\n");
          		     updateAccount();
          	      }
          	      else
          	      {
          		     theCandidate.setName(name);
            	     vc.saveCandidateData();
            	     System.out.printf("You have updated Candidate %d's name to %s.", theCandidate.getCandidateCode(), theCandidate.getName());
          	      }
          	   }
          	   else if (theInput.equalsIgnoreCase("no")) 
          	   {
          		  updateAccount();
          	   }
          	   else
          	   {
          		  System.out.println("Cannot understand your input.\n");
          		  updateAccount();
          	   }
            }
    		catch (Exception e)
    		{
    			System.out.println("Invalid code entered.\n");
    			updateAccount();
    		}
         }
         	else if (input.equalsIgnoreCase("S"))
         	{
         	   try
         	   {
         		  System.out.print("Enter the ID of the Staff account that you would like to update: ");
                  theStaff = vc.getStaff(Integer.parseInt(getInput()));
                   
                  System.out.printf("You've chosen %s, type 'yes' to confirm or 'no' to choose another Staff.\n", theStaff.getName());
              	  String theInput = getInput();
              	 
              	  if (theInput.equalsIgnoreCase("yes"))  
              	  {
              		 System.out.printf("Please enter a new name for Staff %d: ", theStaff.getId());
              		 String name = getInput();
              		  
              		 //Checks if the input is empty.
              		 if (name.equalsIgnoreCase(""))
              	     {
              		    System.out.println("You must enter a name.\n");
              		    updateAccount();
              	     }
              		 else
              		 {
                        theStaff.setName(name);
                	    vc.saveCandidateData();
                	    System.out.printf("You have updated Staff %d's name to %s.", theStaff.getId(), theStaff.getName());
              		 }
                  }
              	 	 else if (theInput.equalsIgnoreCase("no"))
              	 	 {
              	 		updateAccount();
              	 	 }
                  else
                  {
                     System.out.println("Cannot understand your input.\n");
                     updateAccount();
                  }
         	   }
         	  catch (NumberFormatException e)
       		  {
       			 System.out.println("Invalid ID entered.\n");
       			 updateAccount();
       		  }
         	}
         	else if (input.equalsIgnoreCase("A"))
            {
         		try
         		{
         		   System.out.print("Enter the username of the Admin account that you would like to update: ");
           		   theAdmin = vc.getAdmin(getInput());
                    
                   System.out.printf("You've chosen %s, type 'yes' to confirm or 'no' to choose another Admin.\n", theAdmin.getName());
               	   String theInput = getInput();
               	 
               	   if (theInput.equalsIgnoreCase("yes"))  
               	   {
                 	  System.out.printf("Please enter a password for Admin %d: ", theAdmin.getId());
                 	  String password = getInput();
                 	    
                 	  //Checks if the input is empty.
                 	  if (password.equalsIgnoreCase(""))
                	  {
                		 System.out.println("You must enter a password.\n");
                		 updateAccount();
                	  }
                      else
                	  {
                 		 theAdmin.setPassword(password);
                 		 vc.saveAdminData();
                 		 System.out.printf("You have updated Admin %d's password to %s.", theAdmin.getId(), theAdmin.getPassword());
                	  }
                   }
               	   else if (theInput.equalsIgnoreCase("no"))
               	   {
               	 	  updateAccount();
               	   }
               	   else
                   {
                      System.out.println("Cannot understand your input.\n");
                      updateAccount();
                   }
                }
         		catch (NullPointerException ex)
         		{
         		   System.out.println("Invalid ID entered.\n");
         		   updateAccount();
         		}
            }
         	else if (input.equalsIgnoreCase("Q"))
         	{
         		manageAdmin();
         	}
            else
            {
                System.out.println("Cannot understand your input.\n");
                updateAccount();
            }
     }
  //=======================================================================
    //This method validates administrator username & password.
    public void validateAdmin()
    {
    	try
    	{
    	   System.out.print("Please enter your Admin username to log in (CASE SENSISTIVE): ");
           String username = getInput();
           theAdmin = vc.getAdmin(username);
    	
           //Checks if the Admin username matches the input.
           if (new String(theAdmin.getUsername()).equals(username))
           {
    	       System.out.print("Please enter your Admin password to log in (CASE SENSITIVE): ");
    	       String password = getInput();
    			 
    	       //Checks if the Admin password matches the input.
    	       if (new String(theAdmin.getPassword()).equals(password))
    		   {
    	    	  System.out.printf("\nHello %s!\n", theAdmin.getName());
    		      manageAdmin();
    	       } 
    		   else
    		   {
    			  System.out.printf("Invalid password entered.\n");
        		  validateAdmin();
    		   }
           }
    	}
    	catch (NullPointerException ex)
		{
           System.out.println("Invalid username.");
	       validateAdmin();
        }
    }
  //=======================================================================
    //This method allows the admin to wiew account lists.
    public void viewLists()
    {
    	System.out.println("\"C\" > Display Candidate list\n\"S\" > Display Staff List\n\"A\" > Display Admin list\n\"Q\" > Go back");
        String input = getInput();
        if(input.equalsIgnoreCase("C"))
        {
        	listCandidatesAdmin();
        }
            else if(input.equalsIgnoreCase("S"))
            {
          	    listStaffs();
            }
            else if(input.equalsIgnoreCase("A"))
            {
            	listAdmins();
            }
            else if (input.equalsIgnoreCase("Q"))
            {
            	manageAdmin();
            }
        else
        {
            System.out.println("Cannot understand your input.\n");
            viewLists();
        }
    }
  //=======================================================================
}