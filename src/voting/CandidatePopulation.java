package voting;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CandidatePopulation {
    
    private final List<Candidate> candidates;
    
    public CandidatePopulation(List<Candidate> candidates) {
        this.candidates = candidates;
    }
    
    public CandidatePopulation(Candidate... candidates) {
        this(Stream.of(candidates).collect(Collectors.toList()));
    }
    
    public CandidatePopulation(CandidateDistribution distribution, int size) {
        this(Stream.generate(distribution::getCandidate).limit(size).collect(Collectors.toList()));
    }
    
    public List<Candidate> getCandidates() {
        return candidates;
    }
    
    public int getSize() {
        return candidates.size();
    }
    
}
