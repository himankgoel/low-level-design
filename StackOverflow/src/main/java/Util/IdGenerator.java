package Util;

import java.util.UUID;

public class IdGenerator {
    IdGenerator(){
    }

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
