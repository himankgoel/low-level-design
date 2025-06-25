package entities;

import lombok.Getter;

@Getter
public class Tag {

    private String value;

    public Tag(String value) {
        this.value = value;
    }
}
