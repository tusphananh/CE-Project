package Main.Models;

import Main.Main;

import java.util.Collection;

// This finder class will help us find the Objects which implement the Identifier (Helper in OOAD)
public class Finder {

    // This func will travels the ArrayList(Collection) to find the Object exist or not through ID(getID in Identifier)
    public static <E extends Identifier> E search(Collection<E> collection, String key) {
        for (E e : collection) {
            if (e.getID().equals(key)) {
                return e;
            }
        }
        return null;
    }

}
