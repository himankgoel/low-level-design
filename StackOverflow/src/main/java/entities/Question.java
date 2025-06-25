package entities;

import Util.IdGenerator;
import lombok.Getter;
import lombok.NonNull;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;

import static Util.Constants.DEFAULT_ANSWER_POSTED_REPUTATION;

@Getter
public class Question implements Commentable, Votable {
    @NonNull private final String id;
    @NonNull private String title;
    @NonNull private String content;
    @NonNull private final User author;
    private final List<Tag> tags;
    private final List<Answer> answers;
    private Answer acceptedAnswer;
    private int bounty;

    public Question(final String title, final String content, final User author, final List<String> tags) {
        this.id = IdGenerator.generateUniqueId();
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags.stream().map(Tag::new).toList();
        this.answers = new ArrayList<>();
        this.bounty = DEFAULT_ANSWER_POSTED_REPUTATION;
    }

    public void addAnswer(final Answer answer) {
        this.answers.add(answer);
    }

    public void markAsAccepted(final User user, final Answer acceptedAnswer) throws NoPermissionException {
        if (!user.equals(author)) {
            throw new NoPermissionException("User doesn't have permission to update this question.");
        }
        this.acceptedAnswer = acceptedAnswer;
    }
}
