package com.civka.calculatordemo.utils.multiply;

import com.civka.calculatordemo.utils.BasicBinary;

import java.util.ArrayList;
import java.util.List;

public class FourthMultiplyUtil extends AbstractMultiplier {

    public FourthMultiplyUtil(int operandsBitRate, String x, String y) {
        super(operandsBitRate, x, y);
        this.valuesDescription = "RG<sub>1</sub> = 0, RG<sub>2</sub> = X, RG<sub>3</sub> = 0.Y.0...0";

        this.rg1 = "0".repeat(2*n + 1);
        this.rg2 = x;
        this.rg3 = "0" + y + "0".repeat(n);
    }

    @Override
    public List<List<String>> makeTable() {
        List<List<String>> table = new ArrayList<>();

        List<String> tempRow;
        while (!rg2.equals("0".repeat(n))) {
            tempRow = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
                tempRow.add("");
            }

            if (rg2.charAt(0) == '0') {
                rg3 = "0" + rg3.substring(0, rg3.length() - 1);
                rg2 = rg2.substring(1) + "0";

                tempRow.set(0, rg1);
                tempRow.set(3, "RG3 = 0.R(RG3), RG<sub>2</sub> = L(RG<sub>2</sub>).0");
            } else {
                rg1 = BasicBinary.addBinary(rg1, rg3);
                if (rg1.length() > 2*n + 1) {
                    rg1 = rg1.substring(1);
                }

                tempRow.set(0, "+ <u>" + rg3 + "</u><br>" + rg1);

                rg3 = "0" + rg3.substring(0, rg3.length() - 1);
                rg2 = rg2.substring(1) + "0";

                tempRow.set(3, "RG<sub>1</sub> = RG<sub>1</sub> + RG<sub>3</sub>;<br>" +
                        "RG<sub>3</sub> = 0.R(RG<sub>3</sub>), RG<sub>2</sub> = L(RG<sub>2</sub>).0");
            }
            if (!rg2.equals("0".repeat(n)))
                tempRow.set(1, "<b>" + rg2.charAt(0) + "</b>" + rg2.substring(1));
            else
                tempRow.set(1, "<b>" + rg2 + "</b>");
            tempRow.set(2, rg3);

            table.add(tempRow);
        }
        result = rg1.substring(0, rg1.length() - 1);
        return table;
    }
}
