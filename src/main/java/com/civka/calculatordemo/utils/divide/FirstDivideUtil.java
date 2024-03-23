package com.civka.calculatordemo.utils.divide;

import com.civka.calculatordemo.utils.AbstractOperation;
import com.civka.calculatordemo.utils.BasicBinary;
import com.civka.calculatordemo.utils.MachineCodeUtil;

import java.util.ArrayList;
import java.util.List;

public class FirstDivideUtil extends AbstractOperation {

    private String nrg1;
    private String rg1Description;

    public FirstDivideUtil(int operandsBitRate, String x, String y) {
        super(operandsBitRate, x, y);
        this.valuesDescription = "RG<sub>1</sub> = Y, RG<sub>2</sub> = X, RG<sub>3</sub> = 1..11";

        this.rg1 = "00" + y;
        this.nrg1 = new MachineCodeUtil("-" + rg1).getAdditionalCode().substring(2);
        this.rg1Description = "<span style=\"background-color: #ffecb3\">" + rg1 + "</span><br>" +
                "<span>" + new MachineCodeUtil("-" + rg1).getReverseCode().substring(2) + "</span><br>+ <u>" +
                "&nbsp;".repeat(n + 1) + "1</u><br><span style=\"background-color: #b3d1ff\">" + nrg1 + "</span>";

        this.rg2 = "00" + x;
        this.rg3 = "1".repeat(n + 1);
    }

    @Override
    public List<List<String>> makeTable() {
        List<List<String>> table = new ArrayList<>();

        List<String> tempRow;

        String tempRG2;
        String lastRateRG2;
        String invLastRateRG2;
        while(rg3.charAt(0) == '1') {
            tempRow = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                tempRow.add("");
            }

            if (rg2.charAt(0) == '0') {
                rg2 = BasicBinary.addBinary(rg2, nrg1);
                if (rg2.length() > n + 2) rg2 = rg2.substring(1);
                tempRG2 = rg2;
                lastRateRG2 = String.valueOf(rg2.charAt(0));
                rg2 = rg2.substring(1) + "0";
                tempRow.set(2, String.format("+ <u><span style=\"background-color: #b3d1ff\">%s</span></u><br>" +
                        "%s<br><b>%c</b>%s", nrg1, tempRG2, rg2.charAt(0), rg2.substring(1)));
                tempRow.set(4, "RG<sub>2</sub> = RG<sub>2</sub> + <span style=\"text-decoration: overline;\">RG₁</span> + D;<br>" +
                        "RG<sub>3</sub> = L(RG<sub>3</sub>).<span style=\"text-decoration: overline;\">RG₂[" + (n + 2) + "]</span>;<br>" +
                        "RG<sub>2</sub> = L(RG<sub>2</sub>).0");
            } else {
                rg2 = BasicBinary.addBinary(rg2, rg1);
                if (rg2.length() > n + 2) rg2 = rg2.substring(1);
                tempRG2 = rg2;
                lastRateRG2 = String.valueOf(rg2.charAt(0));
                rg2 = rg2.substring(1) + "0";
                tempRow.set(2, String.format("+ <u><span style=\"background-color: #ffecb3\">%s</span></u><br>" +
                        "%s<br><b>%c</b>%s", nrg1, tempRG2, rg2.charAt(0), rg2.substring(1)));
                tempRow.set(4, "RG<sub>2</sub> = RG<sub>2</sub> + RG<sub>1</sub>;<br>" +
                        "RG<sub>3</sub> = L(RG<sub>3</sub>).<span style=\"text-decoration: overline;\">RG₂[" + (n + 2) + "]</span>;<br>" +
                        "RG<sub>2</sub> = L(RG<sub>2</sub>).0");
            }
            invLastRateRG2 = lastRateRG2.equals("1") ? "0" : "1";
            tempRow.set(1, "<i>" + invLastRateRG2 + "</i>");
            rg3 = rg3.substring(1) + invLastRateRG2;
            tempRow.set(0, (rg3.charAt(0) == '0' ? "<u><b>0</b></u>" : "1") + rg3.substring(1));

            table.add(tempRow);
        }
        result = rg3;
        return table;
    }

    public String getNrg1() {
        return nrg1;
    }

    public void setNrg1(String nrg1) {
        this.nrg1 = nrg1;
    }

    public String getRg1Description() {
        return rg1Description;
    }

    public void setRg1Description(String rg1Description) {
        this.rg1Description = rg1Description;
    }
}
