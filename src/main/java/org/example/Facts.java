package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facts {
    private Map<String, Object> factsList = new HashMap<String, Object>();
    private List<String> message = new ArrayList<String>();
    private boolean eligible;

    public Map<String, Object> getFactsList() {
        return factsList;
    }

    public void setFactsList(Map<String, Object> factsList) {
        this.factsList = factsList;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
}
