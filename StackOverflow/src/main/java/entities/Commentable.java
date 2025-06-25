package entities;

import java.util.ArrayList;
import java.util.List;

public interface Commentable {
    List<Comment> comments = new ArrayList<>();

    default void addComment(Comment comment) {
        comments.add(comment);
    }

    default List<Comment> getComments() {
        return comments;
    }
}
