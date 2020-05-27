package com.pedrorocha.covid19info.data.model;

public class CaseInfo {

    private CaseType caseType;
    private int value;

    public CaseInfo(CaseType caseType, int value) {
        this.caseType = caseType;
        this.value = value;
    }

    public CaseType getCaseType() {
        return caseType;
    }

    public int getValue() {
        return value;
    }
}
