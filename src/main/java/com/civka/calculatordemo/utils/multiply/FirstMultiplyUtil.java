package com.civka.calculatordemo.utils.multiply;

import java.util.ArrayList;
import java.util.List;

public class FirstMultiplyUtil extends AbstractMultiplier {

    private int ct;

    public FirstMultiplyUtil(int operandsBitRate, String x, String y) {
        super(operandsBitRate, x, y);
        this.valuesDescription = "RG<sub>1</sub> = 0, RG<sub>2</sub> = X, RG<sub>3</sub> = 0.Y;<br>" + "СТ = n = " + n;
        this.ct = n;

        this.rg1 = "0".repeat(n + 1);
        this.rg2 = x;
        this.rg3 = "0" + y;
    }

    public List<List<String>> makeTable() {
        List<List<String>> table = new ArrayList<>();

        List<String> tempRow;
        String tempRG1;
        String firstRateRG1;
        boolean condition;
        while (ct != 0) {
            ct -= 1;
            tempRow = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                tempRow.add("");
            }
            condition = rg2.charAt(n - 1) == '0';
            if (condition) {
                firstRateRG1 = Character.toString(rg1.charAt(n));
                rg1 = "0" + rg1.substring(0, n);
                rg2 = firstRateRG1 + rg2.substring(0, n - 1);

                tempRow.set(0, rg1);
                tempRow.set(4, "RG<sub>1</sub> = 0.R(RG<sub>1</sub>), RG<sub>2</sub> = RG<sub>1</sub>[1].R(RG<sub>2</sub>);<br>CT = CT - 1");
            } else {
                rg1 = addBinary(rg1, rg3);
                if (rg1.length() > n + 1) {
                    rg1 = rg1.substring(1);
                }
                tempRG1 = rg1;

                firstRateRG1 = Character.toString(rg1.charAt(n));
                rg1 = "0" + rg1.substring(0, n);
                rg2 = firstRateRG1 + rg2.substring(0, n - 1);

                tempRow.set(0, "+ <u>" + rg3 + "</u><br>" + tempRG1 + "<br>" + rg1);
                tempRow.set(4, "RG<sub>1</sub> = RG<sub>1</sub> + RG<sub>3</sub>;<br>RG<sub>1</sub> = 0.R(RG<sub>1</sub>), RG<sub>2</sub> = RG<sub>1</sub>[1].R(RG<sub>2</sub>);<br>CT = CT - 1");
            }
            tempRow.set(2, "");
            if (ct != 0) {
                tempRow.set(1, (condition ? "" : "<br><br>")
                        + rg2.substring(0, n - 1) + "<b>" + rg2.charAt(n - 1) + "</b>");
                tempRow.set(3, String.format("%" + q + "s", Integer.toBinaryString(ct)).replace(' ', '0'));
            } else {
                tempRow.set(1, (condition ? "" : "<br><br>")  + rg2);
                tempRow.set(3, "<b>" +
                        String.format("%" + q + "s", Integer.toBinaryString(ct)).replace(' ', '0') +
                        "</b>");
            }

            table.add(tempRow);
        }
        result = rg1 + rg2;
        result = result.substring(1);
        return table;
    }

    public String getCt() {
        return Integer.toBinaryString(ct);
    }
}
