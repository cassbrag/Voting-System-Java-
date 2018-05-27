package EpicVotingSystem;

/**
 * Date : 19/10/2017
 */

public class Admin {
	
	public int id;
	public String name;
	public String username;
	public String password;
	
	public Admin(int id, String name, String username, String password)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
	
	public void setId(int id)
    {
       this.id = id;
    }
	
	public int getId()
    {
        return id;
    }
	
    public void setName(String name)
    {
            this.name = name;
    }

    public String getName()
    {
        return name;
    }
    
    public void setUsername(String username)
    {
    	this.username = username;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setPassword(String password)
    {
    	this.password = password;
    }
    
    public String getPassword()
    {
        return password;
    }
}
