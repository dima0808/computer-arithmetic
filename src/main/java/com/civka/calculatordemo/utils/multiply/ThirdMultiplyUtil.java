package com.civka.calculatordemo.utils.multiply;

import com.civka.calculatordemo.utils.AbstractOperation;
import com.civka.calculatordemo.utils.BasicBinary;

import java.util.ArrayList;
import java.util.List;

public class ThirdMultiplyUtil extends AbstractOperation {

    private int ct;

    public ThirdMultiplyUtil(int operandsBitRate, String x, String y) {
        super(operandsBitRate, x, y);
        this.valuesDescription = "RG<sub>1</sub> = 0, RG<sub>2</sub> = X.0, RG<sub>3</sub> = Y;<br>" + "СТ = n = " + n;
        this.ct = n;

        this.rg1 = "0".repeat(n);
        this.rg2 = x + "0";
        this.rg3 = y;
    }

    public List<List<String>> makeTable() {
        List<List<String>> table = new ArrayList<>();

        List<String> tempRow;
        String tempRG1;
        String tempRG2;
        String lastRateRG1;
        String ci;
        while (ct != 0) {
            ct -= 1;
            tempRow = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                tempRow.add("");
            }

            if (rg2.charAt(0) == '0') {
                lastRateRG1 = Character.toString(rg1.charAt(0));
                rg1 = rg1.substring(1) + "0";
                rg2 = rg2.substring(1) + lastRateRG1;

                tempRow.set(0, "<b>" + rg2.charAt(0) + "</b>" + rg2.substring(1));
                tempRow.set(1, rg1);
                tempRow.set(4, String.format("RG<sub>1</sub> = L(RG<sub>1</sub>).0, RG<sub>2</sub> = L(RG<sub>2</sub>).RG<sub>1</sub>[%d];<br>CT = CT - 1", n));
            } else {
                ci = "0";
                rg1 = BasicBinary.addBinary(rg1, rg3);
                if (rg1.length() > n) {
                    rg1 = rg1.substring(1);
                    ci = "1";
                }
                tempRG1 = rg1;

                rg2 = BasicBinary.addBinary(rg2, "0".repeat(n) + ci);
                if (rg2.length() > n + 1) {
                    rg2 = rg2.substring(1);
                }
                tempRG2 = rg2;

                lastRateRG1 = Character.toString(rg1.charAt(0));
                rg1 = rg1.substring(1) + "0";
                rg2 = rg2.substring(1) + lastRateRG1;

                if (ct != 0) {
                    tempRow.set(0, "+ <u>" + "0".repeat(n) + ci + "</u><br>" + tempRG2 + "<br>"
                            + "<b>" + rg2.charAt(0) + "</b>" + rg2.substring(1));
                } else {
                    tempRow.set(0, "+ <u>" + "0".repeat(n) + ci + "</u><br>" + tempRG2 + "<br>" + rg2);
                }
                tempRow.set(1, "+ <u>" + rg3 + "</u><br>" + tempRG1 + "<br>" + rg1);
                tempRow.set(4, String.format("RG<sub>1</sub> = RG<sub>1</sub> + RG<sub>3</sub> , RG<sub>2</sub> = RG<sub>2</sub> + 0 + CI;<br>" +
                        "RG<sub>1</sub> = L(RG<sub>1</sub>).0, RG<sub>2</sub> = L(RG<sub>2</sub>).RG<sub>1</sub>[%d];<br>CT = CT - 1", n));
            }
            tempRow.set(2, "");
            if (ct != 0) {
                tempRow.set(3, String.format("%" + q + "s", Integer.toBinaryString(ct)).replace(' ', '0'));
            } else {
                tempRow.set(3, "<b>" +
                        String.format("%" + q + "s", Integer.toBinaryString(ct)).replace(' ', '0') +
                        "</b>");
            }

            table.add(tempRow);
        }
        result = rg2 + rg1;
        result = result.substring(0, result.length() - 1);
        return table;
    }

    public String plusGOperation(String g) {
        String xygOperation = BasicBinary.addBinary(result, g);
        return (xygOperation.length() == n * 2 + 1) ? "1," + xygOperation.substring(1) : "," + xygOperation;
    }

    public String getCt() {
        return Integer.toBinaryString(ct);
    }
}

