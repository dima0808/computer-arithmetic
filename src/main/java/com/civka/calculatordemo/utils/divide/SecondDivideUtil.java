package com.civka.calculatordemo.utils.divide;

import com.civka.calculatordemo.utils.AbstractOperation;
import com.civka.calculatordemo.utils.BasicBinary;
import com.civka.calculatordemo.utils.MachineCodeUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SecondDivideUtil extends AbstractOperation {

    private String nrg1;

    public SecondDivideUtil(int operandsBitRate, String x, String y) {
        super(operandsBitRate, x, y);
        this.valuesDescription = "RG<sub>1</sub> = Y, RG<sub>2</sub> = X, RG<sub>3</sub> = 1..11";

        this.rg1 = "0" + y + "0".repeat(n);
        this.rg2 = "0" + x + "0".repeat(n);

        this.rg3 = "1".repeat(n + 1);
    }

    @Override
    public List<List<String>> makeTable() {
        List<List<String>> table = new ArrayList<>();

        List<String> tempRow;

        String tempRG1;
        String lastRateRG2;
        String invLastRateRG2;
        String smp;
        MachineCodeUtil machineCodeRG1;

        List<String> colors = new ArrayList<>();
        colors.add("#fcc4c0");
        colors.add("#fcf3c0");
        colors.add("#dcfcc0");
        colors.add("#c0e7fc");
        colors.add("#c3c0fc");
        colors.add("#c9c9c9");
        colors.add("#e6c0fc");
        colors.add("#96d0f2");
        colors.add("#d7e4fc");
        colors.add("#c9b861");
        Iterator<String> colorIterator = colors.iterator();
        String color;

        while(rg3.charAt(0) == '1') {
            tempRow = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                tempRow.add("");
            }
            color = colorIterator.next();
            if (rg2.charAt(0) == '0') {
                machineCodeRG1 = new MachineCodeUtil("-" + rg1);
                nrg1 = machineCodeRG1.getAdditionalCode().substring(2);
                rg1 = "0" + rg1.substring(0, rg1.length() - 1);
                tempRow.set(3, String.format("%s<br>+ <u>%s</u><br>" +
                        "<span style=\"background-color: %s\">%s</span><br>%s",
                        machineCodeRG1.getReverseCode().substring(2), "&nbsp;".repeat(n * 2) + "1", color, nrg1, rg1));
                rg2 = BasicBinary.addBinary(rg2, nrg1);
                if (rg2.length() > n * 2 + 1) {
                    rg2 = rg2.substring(1);
                    smp = "1";
                } else smp = "0";
                tempRow.set(2, String.format("+ <u><span style=\"background-color: %s\">%s</span></u><br><b>%c</b>%s", color, nrg1, rg2.charAt(0), rg2.substring(1)));
                tempRow.set(4, "RG<sub>2</sub> = RG<sub>2</sub> + <span style=\"text-decoration: overline;\">RG₁</span> + D;<br>" +
                        "RG<sub>3</sub> = L(RG<sub>3</sub>).SM(p);<br>" +
                        "RG<sub>1</sub> = 0.R(RG<sub>1</sub>)");
            } else {
                tempRG1 = rg1;
                rg1 = "0" + rg1.substring(0, rg1.length() - 1);
                tempRow.set(3, String.format("<span style=\"background-color: %s\">%s</span><br>%s",
                        color, tempRG1, rg1));
                rg2 = BasicBinary.addBinary(rg2, tempRG1);
                if (rg2.length() > n * 2 + 1) {
                    rg2 = rg2.substring(1);
                    smp = "1";
                } else smp = "0";
                tempRow.set(2, String.format("+ <u><span style=\"background-color: %s\">%s</span></u><br><b>%c</b>%s", color, tempRG1, rg2.charAt(0), rg2.substring(1)));
                tempRow.set(4, "RG<sub>2</sub> = RG<sub>2</sub> + RG<sub>1</sub>;<br>" +
                        "RG<sub>3</sub> = L(RG<sub>3</sub>).SM(p);<br>" +
                        "RG<sub>1</sub> = 0.R(RG<sub>1</sub>)");
            }
            tempRow.set(1, smp);
            rg3 = rg3.substring(1) + smp;
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
}
