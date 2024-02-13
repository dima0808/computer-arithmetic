package com.civka.calculatordemo.entity;

import java.util.ArrayList;
import java.util.List;

public class LabData {

    private Integer creditNumber;
    private Integer labNumber;

    public LabData() {}

    public LabData(Integer creditNumber) {
        this.creditNumber = creditNumber;
    }

    public Integer getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(Integer creditNumber) {
        this.creditNumber = creditNumber;
    }

    public Integer getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(Integer labNumber) {
        this.labNumber = labNumber;
    }

    public String getCreditNumberBinary() {
        return Integer.toBinaryString(creditNumber);
    }

    public List<Integer> getA(int howMuch) {
        List<Integer> a = new ArrayList<>();
        String creditNumberBinary = getCreditNumberBinary();
        for (int i = 1; i < howMuch + 1; i++) {
            a.add(creditNumberBinary.charAt(creditNumberBinary.length() - i) - '0');
        }
        return a;
    }
}
