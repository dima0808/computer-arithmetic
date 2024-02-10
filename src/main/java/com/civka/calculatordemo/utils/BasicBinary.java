package com.civka.calculatordemo.utils;

public class BasicBinary {

    private String x;
    private String y;
    private String c;

    private Double decX;
    private Double decY;

    private String operation;

    private String convertFrom;
    private String convertTo;

    public BasicBinary() {}

    public BasicBinary(String x, String y) {
        this.x = x.replaceAll(",$", "");
        this.y = y.replaceAll(",$", "");
        this.decX = binaryToDouble(this.x);
        this.decY = binaryToDouble(this.y);
    }

    public BasicBinary(String c, String convertFrom, String convertTo) {
        this.c = c;
        this.operation = "convert";
        this.convertFrom = convertFrom;
        this.convertTo = convertTo;
    }

    public <T> T addBinary(Class<T> returnType) {
        if (returnType == String.class) return returnType.cast(doubleToBinary(decX + decY));
        else return returnType.cast(decX + decY);
    }

    public <T> T subtractBinary(Class<T> returnType) {
        if (returnType == String.class) return returnType.cast(doubleToBinary(decX - decY));
        else return returnType.cast(decX - decY);
    }

    public <T> T multiplyBinary(Class<T> returnType) {
        if (returnType == String.class) return returnType.cast(doubleToBinary(decX * decY));
        else return returnType.cast(decX * decY);
    }

    public <T> T divideBinary(Class<T> returnType) {
        if (returnType == String.class) return returnType.cast(doubleToBinary(decX / decY));
        else return returnType.cast(decX / decY);
    }

    public String convertNumber() {
        int decimalNumber = Integer.parseInt(c, Integer.parseInt(convertFrom));
        return Integer.toString(decimalNumber, Integer.parseInt(convertTo)).toUpperCase();
    }

    public static String addBinary(String s1, String s2) {
        if (s1 == null || s2 == null) return "";
        int first = s1.length() - 1;
        int second = s2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (first >= 0 || second >= 0) {
            int sum = carry;

            if (first >= 0) {
                // for summary in additional code
                if ((s1.charAt(first) != '0' && s1.charAt(first) != '1') &&
                        (s2.charAt(first) == s2.charAt(second))) {
                    sb.append(s2.charAt(first));
                    first--;
                    second--;
                    continue;
                }
                sum += s1.charAt(first) - '0';
                first--;
            }
            if (second >= 0) {
                sum += s2.charAt(second) - '0';
                second--;
            }
            carry = sum >> 1;
            sum = sum & 1;
            sb.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0) sb.append('1');

        sb.reverse();
        return String.valueOf(sb);
    }

    public static Double binaryToDouble(String s) {
        int p = 0;
        double result = 0D;
        boolean negative = s.charAt(0) == '-';

        String[] arrS = (s.substring(negative ? 1 : 0)).split(",");

        String whole = arrS[0];
        int lenWhole = whole.length();
        for (int i = lenWhole - 1; i >= 0; i--) {
            if (whole.charAt(i) == '1') result += Math.pow(2, p);
            p++;
        }

        if (s.contains(",")) {
            String fractional = arrS[1];
            int lenFractional = fractional.length();
            p = 1;
            for (int i = 0; i < lenFractional; i++) {
                if (fractional.charAt(i) == '1') result += Math.pow(2, -p);
                p++;
            }
        }
        return (s.charAt(0) == '-') ? -result : result;
    }

    public static String doubleToBinary(Double value) {

        boolean negative = value < 0;

        if (negative) value = -value;

        double fractionalPart = value % 1;
        int integralPart = value.intValue();
        StringBuilder s = new StringBuilder();

        while (integralPart > 0)
        {
            s.insert(0, ((integralPart % 2) == 0 ? "0" : "1"));
            integralPart = integralPart / 2;
        }

        s.append(",");

        int bit;
        while (fractionalPart > 0) {
            if (s.length() - s.indexOf(",") > 32)
                break;
            fractionalPart *= 2;
            bit = (int) fractionalPart;
            s.append(bit);
            fractionalPart -= bit;
        }

        if (s.charAt(0) == ',') s.insert(0, "0");
        return (negative ? "-" : "") + String.valueOf(s).replaceAll(",$", "");
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getC() {
        return c;
    }

    public Double getDecX() {
        return decX;
    }

    public Double getDecY() {
        return decY;
    }

    public String getOperation() {
        return operation;
    }

    public String getConvertFrom() {
        return convertFrom;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setX(String x) {
        this.x = x.replaceAll(",$", "");
        this.decX = binaryToDouble(this.x);
    }

    public void setY(String y) {
        this.y = y.replaceAll(",$", "");
        this.decY = binaryToDouble(this.y);
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setConvertFrom(String convertFrom) {
        this.convertFrom = convertFrom;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }
}
