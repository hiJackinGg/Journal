package com.mycompany.journal.config;

import com.mycompany.journal.db.model.Manager;

import java.util.Set;
import java.util.TreeSet;

/**
 * Retrives all subordinates of specified manager (including delegates).
 */
public class ManagerParser {

    public static Set<Manager> findSubordinates(Manager manager){
        boolean isUser = true;
        Set<Manager> set = new TreeSet<>();

        findSubordinates(manager, isUser, set);

        return set;
    }

    private static void findSubordinates(Manager manager, boolean isUser, Set<Manager> set) {

        if(!isUser) {
            if (!set.add(manager))
                return;
        }

        for(Manager m : manager.getDelegatedFrom()){
            findSubordinates(m, false, set);
        }

        for(Manager m : manager.getSubordinates()){
            findSubordinates(m, false, set);
        }

    }
}
