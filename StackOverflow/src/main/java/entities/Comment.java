package entities;

import Util.IdGenerator;
import lombok.Getter;

@Getter
public class Comment {
    private String id;
    private String content;
    private User author;

    public Comment(String content, User author) {
        this.author = author;
        this.content = content;
        this.id = IdGenerator.generateUniqueId();
    }
}
