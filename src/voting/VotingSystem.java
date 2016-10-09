package voting;

import java.util.List;

public interface VotingSystem {
    
    List<Candidate> getWinners(List<Voter> voters, List<Candidate> candidates);
    
}
