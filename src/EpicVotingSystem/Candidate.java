package EpicVotingSystem;

/**
 * Date : 19/10/2017
 */

public class Candidate
{
    int candidateCode;
    String name = null;
    int votes = 0; //total votes

    //constructor
    public Candidate(int candidateCode, String name, int votes)
    {
        this.candidateCode = candidateCode;
        this.name = name;
        this.votes = votes;
    }

    public int getCandidateCode()
    {
        return candidateCode;
    }

    public void setName(String name)
    {
       this.name = name;
    }
    
    public String getName()
    {
        return name;
    }

    public int getVotes()
    {
        return  votes;
    }

    public void addVote()
    {
        votes++;
    }
}