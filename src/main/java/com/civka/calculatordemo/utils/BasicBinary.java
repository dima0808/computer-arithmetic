package com.civka.calculatordemo.utils;

public abstract class BasicBinary {

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
        if (carry > 0)
            sb.append('1');

        sb.reverse();
        return String.valueOf(sb);
    }
}
