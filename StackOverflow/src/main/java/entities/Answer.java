package entities;

import lombok.Getter;

@Getter
public class Answer implements Commentable, Votable {
    private String content;
    private final User author;

    public Answer(String content, User author) {
        this.content = content;
        this.author = author;
    }
}
