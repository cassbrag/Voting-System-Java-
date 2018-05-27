package EpicVotingSystem;
import java.util.Date;

/**
 * Date : 19/10/2017
 */

public class Staff
{
    public int id;
    public String name;
    public int voted; //checks if staff has voted
    public String password;
    public String date;

    public Staff(int id, String name, int voted, String password, String date)
    {
            this.id = id;
            this.name = name;
            this.voted = voted;
            this.password = password;
            this.date = date;
    }

    public void setId(int id)
    {
       this.id = id;
    }

    public void setName(String name)
    {
       this.name = name;
    }

    public void setVoted()
    {
       this.voted = 1;
    }

    public int getId()
    {
       return id;
    }

    public String getName()
    {
       return name;
    }

    public int hasVoted()
    {
       return voted;
    }

    public void setPassword(String password)
    {
       this.password = password;
    }
    
	public String getPassword() {
	   return password;
	}
	
	public void setDate(String date)
	{
	   this.date = date;
	}
	
	public String getDate()
	{
		return date;
	}
}