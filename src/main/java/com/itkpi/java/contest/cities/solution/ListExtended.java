package com.itkpi.java.contest.cities.solution;

import java.util.ArrayList;

class ListExtended<String> extends ArrayList<String> {

    String getLast() {
        return this.get(this.size() - 1);
    }

    void removeLast() {
        this.remove(this.size() - 1);
    }
}
