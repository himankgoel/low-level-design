package entities;

import java.util.ArrayList;
import java.util.List;

public interface Votable {

    List<Vote> votes = new ArrayList<>();
    default void updateVote(Vote vote) {
        votes.add(vote);
    }

    default List<Vote> getVotes() {
        return votes;
    }
}
