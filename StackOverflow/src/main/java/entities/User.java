package entities;

import lombok.Getter;

import javax.naming.NoPermissionException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    private final String username;
    private int reputation;
    private final List<Question> questionsPosted;
    private final List<Comment> commentsPosted;
    private final List<Answer> answersPosted;

    public User(String username) {
        this.username = username;
        questionsPosted = new ArrayList<>();
        commentsPosted = new ArrayList<>();
        answersPosted = new ArrayList<>();
    }

    public Question askQuestion(final Question question) {
        this.questionsPosted.add(question);
        return question;
    }

    public Question askQuestion(final String title, final String content, final List<String> tags) {
        Question question = new Question(title, content, this, tags);
        return askQuestion(question);
    }

    public Comment postComment(Commentable commentable, String commentContent) {
        final Comment comment = new Comment(commentContent, this);
        this.commentsPosted.add(comment);
        commentable.addComment(comment);
        return comment;
    }

    public Answer postAnswer(final Question question, final String content) {
        Answer answer = new Answer(content, this);
        question.addAnswer(answer);
        this.answersPosted.add(answer);
        return answer;
    }


    public void addReputation(int increment) {
        this.reputation += increment;
    }
}
