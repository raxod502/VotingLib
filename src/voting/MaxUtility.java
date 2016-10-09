package voting;

import java.util.List;

public class MaxUtility implements VotingSystem {
    
    private final UtilityCombiner utilityCombiner;
    
    public MaxUtility(UtilityCombiner utilityCombiner) {
        this.utilityCombiner = utilityCombiner;
    }
    
    @Override
    public List<Candidate> getWinners(List<Voter> voters, List<Candidate> candidates) {
        return Util.maxElementsDouble(candidates, winner -> {
            double[] utilities = new double[voters.size()];
            for (int i = 0; i < voters.size(); i++) {
                utilities[i] = voters.get(i).utilityIfCandidateElected(winner);
            }
            return utilityCombiner.combineUtilities(utilities);
        });
    }
    
    @Override
    public String toString() {
        return "Max utility";
    }
    
}
