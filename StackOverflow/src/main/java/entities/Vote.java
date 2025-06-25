package entities;

import lombok.Getter;

@Getter
public class Vote {
    private final User user;
    private final VoteType voteType;

    public Vote(User user, VoteType voteType) {
        this.user = user;
        this.voteType = voteType;
    }

    @Getter
    public enum VoteType {
        UPVOTE(1),
        DOWNVOTE(-1);
        private final int value;

        VoteType(int value) {
            this.value = value;
        }
    }
}
