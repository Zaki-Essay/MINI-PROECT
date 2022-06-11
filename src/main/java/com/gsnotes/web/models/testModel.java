package com.gsnotes.web.models;

import java.util.List;

public class testModel {

    private List<String> names;

    public testModel(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
