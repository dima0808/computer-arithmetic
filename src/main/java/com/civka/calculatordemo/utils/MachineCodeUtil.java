package com.civka.calculatordemo.utils;

import java.util.ArrayList;
import java.util.List;

public class MachineCodeUtil {

    private final String binaryNumber;
    private String machineCode = "ПК";

    public MachineCodeUtil(String binaryNumber) {
        this.binaryNumber = binaryNumber;
    }

    public MachineCodeUtil(String binaryNumber, String machineCode) {
        this.binaryNumber = binaryNumber;
        this.machineCode = machineCode;
    }

    public String getBinaryNumber() {
        if (machineCode.equals("ПК")) return binaryNumber;
        else return "error";
    }

    public String getStraightCode() {
        if (!machineCode.equals("ПК")) return "error";
        if (binaryNumber.charAt(0) == '-') return "1." + binaryNumber.substring(1);
        else return "0." + binaryNumber;
    }

    public String getReverseCode() {
        if (machineCode.equals("ОК+")) return binaryNumber.substring(1);
        if (binaryNumber.charAt(0) != '-') return getStraightCode();
        else return "1." + binaryNumber.substring(1)
                    .replaceAll("0", "A")
                    .replaceAll("1", "0").replaceAll("A", "1");
    }

    public String getAdditionalCode() {
        String oneString = getReverseCode().replaceAll("1", "0");
        return BasicBinary.addBinary(getReverseCode(), oneString.substring(0, oneString.length() - 1) + "1");
    }

    public String getModifyReverseCode() {
        if (machineCode.equals("ОК+")) return binaryNumber;
        return ((binaryNumber.charAt(0) == '-') ? "1" : "0") + getReverseCode();
    }

    public String getModifyAdditionalCode() {
        String oneString = getModifyReverseCode().replaceAll("1", "0");
        return BasicBinary.addBinary(getModifyReverseCode(), oneString.substring(0, oneString.length() - 1) + "1");
    }

    public String leftShift(String machineCode) {
        String ok = getModifyAdditionalCode();
        StringBuilder leftShift = new StringBuilder();
        String temp;
        for (int i = 0; i < ok.length() - 1; i++) {
            if (ok.charAt(i) == '.' || ok.charAt(i) == ',') continue;

            temp = "";
            for (int j = i + 1; j < ok.length(); j++) {
                if (ok.charAt(j) == '.' || ok.charAt(j) == ',') {
                    temp = String.valueOf(ok.charAt(j));
                    continue;
                }

                leftShift.append(ok.charAt(j) - '0');
                break;
            }
            leftShift.append(temp);
        }
        leftShift.append((machineCode.equals("ОК")) ? "1" : "0");
        return String.valueOf(leftShift);
    }

    public String rightShift() {
        String ok = getModifyAdditionalCode();
        StringBuilder rightShift = new StringBuilder();
        for (int i = ok.length() - 1; i > 0; i--) {
            if (ok.charAt(i) == '.' || ok.charAt(i) == ',') {
                rightShift.append(ok.charAt(i));
                continue;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (ok.charAt(j) == '.' || ok.charAt(j) == ',') continue;
                rightShift.append(ok.charAt(j) - '0');
                break;
            }
        }
        rightShift.append(ok.charAt(0) == '1' ? '1' : '0');
        return String.valueOf(rightShift.reverse());
    }

    public static List<String> toTableCode(String code) {
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < code.length() - 1; i++) {
            if (code.charAt(i + 1) == '.' || code.charAt(i + 1) == ',') {
                arr.add(String.valueOf(code.charAt(i)) + String.valueOf(code.charAt(i + 1)));
                i++;
            } else {
                arr.add(String.valueOf(code.charAt(i)));
            }
        }
        arr.add(code.substring(code.length() - 1));
        return arr;
    }
}
