package EpicVotingSystem;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Date : 19/10/2017
 */

public class VotingController
{
    //Creates an Arraylist to read & store staff & candidate data from the database.
    public ArrayList<Staff> staffs = new ArrayList<Staff>();
    public ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    public ArrayList<Admin> admins = new ArrayList<Admin>();
    
    //Used to get the total number of staff who have voted and for the conversion to percentages.
    public double numberofVoted = 0;
    public double percentVoted;
    public double percentVotes;

   //Access individual staff & candidate from array list.
    public Staff theStaff;
    public Candidate theCandidate;
    public Admin theAdmin;
  //=================================================================
    //VotingController constructor.
    public VotingController()
    {
    	loadCandidateData();
        loadStaffData();
        loadAdminData();
    }
  //=================================================================
    //Adds a new Candidate account to the database.
    public void addCandidateData(int newCandCode, String newCandName, int newCandVC)
    {
    	theCandidate = new Candidate(newCandCode, newCandName, newCandVC);
    	candidates.add(theCandidate);
    	saveCandidateData();
    }
  //===================================================================
    //Adds a new Staff account to the database.
    public void addStaffData(int newStaffId, String newStaffName, int newStaffVC, String newStaffPass, String newStaffDate)
    {
    	theStaff = new Staff(newStaffId, newStaffName, newStaffVC, newStaffPass, newStaffDate);
    	staffs.add(theStaff);
    	saveStaffData();
    }
  //===================================================================
    //Adds a new Admin account to the database.
    public void addAdminData(int newAdminId, String newAdminName, String newAdminUsername, String newAdminPass)
    {
    	theAdmin = new Admin(newAdminId, newAdminName, newAdminUsername, newAdminPass);
    	admins.add(theAdmin);
    	saveAdminData();
    }
  //===================================================================
    public Candidate getCandidate(int candidateCode)
    {
        //Returns the candidate if found in the candidates ArrayList
    	Iterator it = candidates.iterator();
        while(it.hasNext())
        {
            theCandidate = (Candidate) it.next();
            if(theCandidate.getCandidateCode() == candidateCode)
            {
                return theCandidate;
            }
        }
        return null;
    }
  //=================================================================
    //Returns the collection of candidates.
    public ArrayList getCandidates()
    {
        return candidates;
    }
  //=================================================================
    //Checks if the new Candidate code already exists in the system.
    public boolean checkCandidateCode(int newCandCode)
    {
    	Iterator it = candidates.iterator();
        while(it.hasNext())
        {
            theCandidate = (Candidate) it.next();
            if(theCandidate.getCandidateCode() == newCandCode)
            {
                return false;
            }
        }
        return true;
    }
  //=================================================================
    //Checks if the new Staff ID already exists in the system.
    public boolean checkStaffId(int newStaffId)
    {
    	Iterator it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId() == newStaffId)
            {
                return false;
            }
        }
        return true;
    }
  //=================================================================
    //Checks if the new Admin ID already exists in the system.
    public boolean checkAdminId(int newAdminId)
    {
    	Iterator it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if(theAdmin.getId() == newAdminId)
            {
                return false;
            }
        }
        return true;
    }
  //=================================================================
    //Checks if the new Admin username already exists in the system.
    public boolean checkAdminUsername(String newAdminUsername)
    {
    	Iterator it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if((theAdmin.getUsername()).equals(newAdminUsername))
            {
                return false;
            }
        }
        return true;
    }
  //=================================================================
    //Returns a Staff if found in the staffs ArrayList.
    public Staff getStaff(int id)
    {
        Iterator it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId() == id)
            {
                return theStaff;
            }
        }
        return null;
    }
  //=================================================================
    //Returns an Admin if found in the admins ArrayList.
    public Admin getAdmin(String username)
    {
    	Iterator<Admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (Admin) it.next();
            if((theAdmin.getUsername()).equals(username))
            {
                return theAdmin;
            }
        }
        return null;
    }
  //===================================================================
   //Returns the collection of admins.
   public ArrayList getAdmins()
   {
       return admins;
   }
  //=================================================================
   //Returns total number of staffs in the collection.
   public int getTotalCandidates()
   {
       return candidates.size();
   }
  //=================================================================
    //Returns total number of staffs in the collection.
    public double getTotalVoters()
    {
        return staffs.size();
    }
  //=================================================================
    //Returns total pecentage of candidate votes.
    public double getCandidateVotesPercent()
    {
        return percentVotes;
    }
  //=================================================================
    //Returns staff details of those who have voted.
    public Staff getStaffVoted()
    {
    	Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.hasVoted() == 1)
            {
            	System.out.print("\n" + theStaff.getId() + "\t\t" + theStaff.getName() + "\t\t" + theStaff.getDate());
            }
        }
        return null;
    }
  //=================================================================
    //Returns staff details of those who haven't voted.
    public Staff getStaffNotVoted()
    {
    	Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.hasVoted() == 0)
            {
            	System.out.print("\n\t   " + theStaff.getId() + "\t\t    " + theStaff.getName());
            }
        }
        return null;
    }
  //=================================================================
    //Returns total pecentage of staffs who have voted.
    public double getStaffVotedPercent()
    {
    	return percentVoted;
    }
  //=================================================================
    //Loads candidates from database. This method is complete and working ok.
    public void loadCandidateData()
    {
        try
        {
             String fileName = "candidates.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String candidateData;

             while((candidateData = reader.readLine())!= null)
             {
                 String[] candidateDetails = candidateData.split(",");
                 int candidatecode = Integer.parseInt(candidateDetails[0]);
                 int candidatevotes = Integer.parseInt(candidateDetails[2]);
                 theCandidate = new Candidate(candidatecode, candidateDetails[1], candidatevotes);
                 candidates.add(theCandidate);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading candidate data from file.\n");
         }
    }
  //=================================================================
    //Loads staff names from file.
    public void loadStaffData()
    {
    	try
        {
             String fileName = "staff.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String staffData;

             while((staffData = reader.readLine())!= null)
             {
                 String[] staffDetails = staffData.split(",");
                 int id = Integer.parseInt(staffDetails[0]);
                 String name = (staffDetails[1]);
                 int voted = Integer.parseInt(staffDetails[2]);
                 String password = (staffDetails[3]);
                 String date = (staffDetails[4]);
                 theStaff = new Staff(id, name, voted, password, date);
                 staffs.add(theStaff);
                 
                 if (theStaff.hasVoted() == 1)
                 {
                	 numberofVoted++;
                 }
             }
             percentVoted = (numberofVoted/staffs.size()) * 100;
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading staff details from file.\n");
         }
    }
  //=================================================================
    //Loads admin details from file.
    public void loadAdminData()
    {
          try
          {
             String fileName = "admin.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String adminData;

             while((adminData = reader.readLine())!= null)
             {
            	 String[] adminDetails = adminData.split(",");
                 int id = Integer.parseInt(adminDetails[0]);
                 String name = adminDetails[1];
                 String username = adminDetails[2];
                 String password = adminDetails[3];
                 theAdmin = new Admin(id, name, username, password);
                 admins.add(theAdmin);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem with loading admin details from file.\n");
         }
    }
  //=================================================================    
    //Revords staff vote
    public void recordVote(String date)
    {
        theStaff.setVoted();
        theStaff.setDate(date);
        theCandidate.addVote();
        saveStaffData();//save to file
        saveCandidateData();//save to file
    }
  //=================================================================
    //Saves staff data to database.
    public void saveStaffData()
    {
        try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("staff.txt"));
            Iterator it = staffs.iterator();
            String staffDetails;
            while(it.hasNext())
            {
                theStaff = (Staff) it.next();
                staffDetails = theStaff.getId() + "," + theStaff.getName() + "," + theStaff.hasVoted() + "," + theStaff.getPassword() + "," + theStaff.getDate() + "\n";
                writer.write(staffDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
  //=================================================================
    //Saves candidate data to database.
    public void saveCandidateData()
    {
    	try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("candidates.txt"));
            Iterator it = candidates.iterator();
            String candidateDetails;
            while(it.hasNext())
            {
                theCandidate = (Candidate) it.next();
                candidateDetails = theCandidate.getCandidateCode() + "," + theCandidate.getName() + "," + theCandidate.getVotes() + "\n";
                writer.write(candidateDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
  //=================================================================
    //Saves admin data to database.
    public void saveAdminData()
    {
    	try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("admin.txt"));
            Iterator it = admins.iterator();
            String adminDetails;
            while(it.hasNext())
            {
                theAdmin = (Admin) it.next();
                adminDetails = theAdmin.getId() + "," + theAdmin.getName() + "," + theAdmin.getUsername() + "," + theAdmin.getPassword() + "\n";
                writer.write(adminDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
   //=================================================================
     //Removes a Candidate account from the database.
     public void removeCandidateAccount()
     {
    	candidates.remove(theCandidate);
    	saveCandidateData();
    	System.out.println("The account has been removed.\n");
     }
  //===================================================================
     //Removes a Staff account from the database.
     public void removeStaffAccount()
     {
     	staffs.remove(theStaff);
     	saveStaffData();
     	System.out.println("The account has been removed.");
     }
   //===================================================================
    //Removes a Admin account from the database.
     public void removeAdminAccount()
     {
     	admins.remove(theAdmin);
     	saveAdminData();
     	System.out.println("The account has been removed.");
     }
   //===================================================================
}