package com.mycompany.journal.db.model;


public enum SubdivisionLevel {

    DEPARTMENT(10),

    SERVICE(9),

    SECTOR(8);

    private int level;

    SubdivisionLevel(int lvl) {
        this.level = lvl;
    }

    public int toInteger() {
        return level;
    }
}
