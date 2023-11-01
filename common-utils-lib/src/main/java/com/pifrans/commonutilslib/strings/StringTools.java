package com.pifrans.commonutilslib.strings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringTools {


    public static String format(String template, Object... args) {
        for (Object arg : args) {
            template = template.replaceFirst("\\{}", arg.toString());
        }
        return template;
    }
}
