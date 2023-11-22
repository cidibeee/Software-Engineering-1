package org.hbrs.se1.ws23.uebung4.prototype;

import java.util.Comparator;

public class UserStoryComparator implements Comparator<UserStory> {
    @Override
    public int compare(UserStory o1, UserStory o2) {
        return Double.compare(o1.getPrio(), o2.getPrio());
    }
}
