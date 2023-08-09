package com.example;

import org.springframework.util.StringUtils;

class WebAdviceStringUtil {
    private static String removeAllEmoji(String source) {
        if (!StringUtils.hasText(source)) {
            return source;
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : source.toCharArray()) {
            if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 去表情符号 -> 去前后空白字符串
     */
    public static String processStringValue(String source) {
        return StringUtils.trimWhitespace(removeAllEmoji(source));
    }
}

