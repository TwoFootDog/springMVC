package com.commons.service;

import org.springframework.util.StringUtils;

public class CommonFunction {
    public static boolean isSpaceOrNull(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isStringEquals(String str1, String str2) {
        return !StringUtils.isEmpty(str1) && str1.equals(str2);
    }

    // str1과 str2의 String중 적어도 한개 이상이 일치하는 경우(OR)
    public static boolean isOneStringEquals(String str1, String... str2) {
        if (!StringUtils.isEmpty(str1)) {
            for (String str : str2) {
                if (isStringEquals(str1, str))
                    return true;
            }
        }
            return false;
    }

    // str1과 str2 전체가 일치하는 경우(AND)
    public static boolean isAllStringEquals(String str1, String... str2) {
        if (!StringUtils.isEmpty(str1)) {
            for (String str : str2) {
                if (!str1.equals(str))
                    return false;
            }
            return true;
        } else
            return false;
    }

    // str1과 str2 전체가 일치하지 않는 경우
    public static boolean isAllStringNotEquals(String str1, String... str2) {
        if (!StringUtils.isEmpty(str1)) {
            for (String str : str2) {
                if (str1.equals(str)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isStringLengthEquals(String str1, int length) {
        return !StringUtils.isEmpty(str1) && str1.length()==length;
    }

}
