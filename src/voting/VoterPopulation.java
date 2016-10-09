package voting;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VoterPopulation {
    
    private final List<Voter> voters;
    
    public VoterPopulation(List<Voter> voters) {
        this.voters = voters;
    }
    
    public VoterPopulation(Voter... voters) {
        this(Stream.of(voters).collect(Collectors.toList()));
    }
    
    public VoterPopulation(VoterDistribution distribution, int size) {
        this(Stream.generate(distribution::getVoter).limit(size).collect(Collectors.toList()));
    }
    
    public List<Voter> getVoters() {
        return voters;
    }
    
    public int getSize() {
        return voters.size();
    }
    
}
