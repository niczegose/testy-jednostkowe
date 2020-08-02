package pl.kurs.java.service;

import java.util.Arrays;

public class TextService {
    public String reverse(String src) {

        return new StringBuilder(src).reverse().toString();
    }

    public boolean isPalindrome(String src) {
        if (src == null || src.length() == 0) {
            return false;
        }

        String tmp = src.toLowerCase().replaceAll(" ", "");

        for (int i = 0; i < tmp.length() / 2; i++) {
            if (tmp.charAt(i) != tmp.charAt(tmp.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram(String source, String toCheck) {
        if (source == null) {
            throw new NullPointerException("No source string to compare!");
        }
        if (toCheck == null) {
            return false;
        }
        if (source.equals(" ") || source.length() == 0) {
            return false;
        }
        char[] chars = source.toLowerCase().replaceAll(" ", "").toCharArray();
        Arrays.sort(chars);
        String src = new String(chars);
        char[] chars2 = toCheck.toLowerCase().replaceAll(" ", "").toCharArray();
        Arrays.sort(chars2);
        String srcToCheck = new String(chars2);
        return src.equals(srcToCheck);
    }
}
