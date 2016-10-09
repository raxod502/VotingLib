package voting;

import java.util.List;
import java.util.Random;

public final class Election {
    
    private static final Random random = new Random();
    
    private Election() {}
    
    public static double getUtility(VoterPopulation voterPopulation,
                                    CandidatePopulation candidatePopulation,
                                    VotingSystem votingSystem,
                                    UtilityCombiner utilityCombiner) {
        List<Voter> voters = voterPopulation.getVoters();
        List<Candidate> candidates = candidatePopulation.getCandidates();
        List<Candidate> winners = votingSystem.getWinners(voters, candidates);
        Candidate winner = winners.get(random.nextInt(winners.size()));
        double[] utilities = new double[voters.size()];
        for (int i=0; i<voters.size(); i++) {
            utilities[i] = voters.get(i).utilityIfCandidateElected(winner);
        }
        return utilityCombiner.combineUtilities(utilities);
    }
    
}
