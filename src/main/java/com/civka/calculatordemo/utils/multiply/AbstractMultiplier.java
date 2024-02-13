package com.civka.calculatordemo.utils.multiply;

import java.util.List;

public abstract class AbstractMultiplier {

    protected final String x;
    protected final String y;
    protected final int n;
    protected final int q;

    protected String valuesDescription;

    protected String rg1;
    protected String rg2;
    protected String rg3;

    protected String result;

    public AbstractMultiplier(int operandsBitRate, String x, String y) {
        this.x = x;
        this.y = y;
        this.n = operandsBitRate;
        this.q = (int) (Math.log(n)/Math.log(2)) + 1;
    }

    public abstract List<List<String>> makeTable();

    public String getX() {
        return x;
    }

    public int getDecimalX() {
        return Integer.parseInt(x, 2);
    }

    public String getY() {
        return y;
    }

    public int getDecimalY() {
        return Integer.parseInt(y, 2);
    }

    public int getN() {
        return n;
    }

    public String getResult() {
        return result;
    }

    public int getDecimalResult() {
        return Integer.parseInt(result, 2);
    }

    public String getValuesDescription() {
        return valuesDescription;
    }

    public String getRg1() {
        return rg1;
    }

    public String getRg2() {
        return rg2;
    }

    public String getRg3() {
        return rg3;
    }
}
