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
        String okPlus = getModifyReverseCode();
        if (okPlus.charAt(0) == '0') return okPlus;
        String oneString = okPlus.replaceAll("1", "0");
        return BasicBinary.addBinary(okPlus, oneString.substring(0, oneString.length() - 1) + "1");
    }

    public String leftShift(String machineCode) {
        String code;
        if (machineCode.equals("ОК")) code = getModifyReverseCode();
        else code = getModifyAdditionalCode();
        StringBuilder leftShift = new StringBuilder();
        String temp;
        for (int i = 0; i < code.length() - 1; i++) {
            if (code.charAt(i) == '.' || code.charAt(i) == ',') continue;

            temp = "";
            for (int j = i + 1; j < code.length(); j++) {
                if (code.charAt(j) == '.' || code.charAt(j) == ',') {
                    temp = String.valueOf(code.charAt(j));
                    continue;
                }

                leftShift.append(code.charAt(j) - '0');
                break;
            }
            leftShift.append(temp);
        }
        leftShift.append((machineCode.equals("ОК")) ? "1" : "0");
        return String.valueOf(leftShift);
    }

    public String rightShift() {
        String code = getModifyAdditionalCode();
        StringBuilder rightShift = new StringBuilder();
        for (int i = code.length() - 1; i > 0; i--) {
            if (code.charAt(i) == '.' || code.charAt(i) == ',') {
                rightShift.append(code.charAt(i));
                continue;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (code.charAt(j) == '.' || code.charAt(j) == ',') continue;
                rightShift.append(code.charAt(j) - '0');
                break;
            }
        }
        rightShift.append(code.charAt(0) == '1' ? '1' : '0');
        return String.valueOf(rightShift.reverse());
    }

    public static List<String> toTableCode(String code) {
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < code.length() - 1; i++) {
            if (code.charAt(i + 1) == '.' || code.charAt(i + 1) == ',') {
                arr.add(String.valueOf(code.charAt(i)) + (code.charAt(i + 1)));
                i++;
            } else {
                arr.add(String.valueOf(code.charAt(i)));
            }
        }
        arr.add(code.substring(code.length() - 1));
        return arr;
    }
}
