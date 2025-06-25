import entities.*;
import events.EventManager;
import events.eventListener.AnswerMarkedAsAcceptedListener;
import events.eventListener.AnswerPostedEventListener;
import events.eventListener.QuestionPostedEventListener;
import events.eventListener.VotableVotedEventListener;
import events.eventTypes.*;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Optional;

public class Stackoverflow {

    private final EventManager eventManager;
    public Stackoverflow() {
        eventManager = new EventManager();
        eventManager.subscribe(EventType.QUESTION_POSTED, new QuestionPostedEventListener());
        eventManager.subscribe(EventType.ANSWER_POSTED, new AnswerPostedEventListener());
        eventManager.subscribe(EventType.ANSWER_MARKED_AS_ACCEPTED, new AnswerMarkedAsAcceptedListener());
        eventManager.subscribe(EventType.VOTABLE_VOTED, new VotableVotedEventListener());
    }

    public Question askQuestion(final User user, final String title, final String content, final List<String> tags) {
        final Question question = user.askQuestion(title, content, tags);
        eventManager.notify(new QuestionPostedEvent(question, user));
        return question;
    }

    public Answer postAnswer(final User author, final Question question, final String content) {
        final Answer answer = author.postAnswer(question, content);
        eventManager.notify(new AnswerPostedEvent(question, answer, author));
        return answer;
    }

    public void markAsAccepted(final User user, final Question question, final Answer acceptedAnswer) {
        try {
            question.markAsAccepted(user, acceptedAnswer);
            eventManager.notify(new AnswerMarkedAsAcceptedEvent(acceptedAnswer));
        } catch (NoPermissionException ex) {
            System.out.println("Operation failed.");
        }
    }

    public void upvote(final User user, final Votable votable) {
        Optional<Vote> existingVote = getExistingVote(user, votable);
        if (existingVote.isPresent()) {
            if (existingVote.get().getVoteType().equals(Vote.VoteType.UPVOTE)) {
                System.out.println("No op");
                return;
            }
            // Should undo reputation after removing existing vote.
            removeExistingVote(user, votable);
            eventManager.notify(new VotableVotedEvent(votable));
        }
        Vote vote = new Vote(user, Vote.VoteType.UPVOTE);
        votable.updateVote(vote);
        eventManager.notify(new VotableVotedEvent(votable));
    }

    public void downvote(final User user, final Votable votable) {
        Optional<Vote> existingVote = getExistingVote(user, votable);
        if (existingVote.isPresent()) {
            if (existingVote.get().getVoteType().equals(Vote.VoteType.DOWNVOTE)) {
                System.out.println("No op");
                return;
            }
            removeExistingVote(user, votable);
        }
        Vote vote = new Vote(user, Vote.VoteType.DOWNVOTE);
        votable.updateVote(vote);
    }

    private void removeExistingVote(final User user, final Votable votable) {
        Optional<Vote> existingVoteOptional = getExistingVote(user, votable);
        existingVoteOptional.ifPresent(existingVote -> votable.getVotes().remove(existingVote));
    }

    private Optional<Vote> getExistingVote(final User user, final Votable votable) {
        return votable.getVotes().stream().filter(vote -> vote.getUser().equals(user)).findAny();
    }
}
