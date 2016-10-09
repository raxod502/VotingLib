package voting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InstantRunoff implements VotingSystem {
    
    @Override
    public List<Candidate> getWinners(List<Voter> voters, List<Candidate> candidates) {
        List<Candidate> candidatesRemaining = new ArrayList<>(candidates);
        List<List<Set<Candidate>>> preferenceOrders = voters
                .stream()
                .map(v -> v.getPartialPreferenceOrder(candidates))
                .collect(Collectors.toList());
        while (true) {
            List<Candidate> eliminatedCandidates = Util.minElementsDouble(candidatesRemaining,
                    c -> preferenceOrders
                            .stream()
                            .mapToDouble(order -> order.get(0).contains(c) ? 1.0 / order.get(0).size() : 0)
                            .sum());
            eliminatedCandidates.stream().forEach(candidatesRemaining::remove);
            for (List<Set<Candidate>> order : preferenceOrders) {
                Iterator<Set<Candidate>> iterator = order.iterator();
                while (iterator.hasNext()) {
                    Set<Candidate> equivalenceClass = iterator.next();
                    for (Candidate c : eliminatedCandidates) {
                        equivalenceClass.remove(c);
                        if (equivalenceClass.isEmpty()) {
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
            if (preferenceOrders.get(0).isEmpty()) {
                return eliminatedCandidates;
            }
        }
    }
    
    @Override
    public String toString() {
        return "Instant runoff";
    }
}
