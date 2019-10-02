package com.project.translate.utils;

public class UrlUtils {

    final private static String[] URL = {
            "https://dictionary.yandex.net/api/v1/dicservice.json/lookup",
            "https://translate.yandex.net/api/v1.5/tr.json/translate"
    };
    final private static String YANDEX_KEY[] = {
            "dict.1.1.20191001T081800Z.dab3b0b97fd15bf5.a6483b95e2b1632f1bd4873db1c5f75f58e39af2",
            "trnsl.1.1.20190924T095719Z.40a07e407a4112ab.7c944c1da04edcc768b5f7e278f678fe7de27498"
    };
    final private static String LANG = "en-ru";

    /**
     * @param text
     * @param flag 0 - Dictionary
     *             1 - Translate
     * @return
     */
    public static String getTranslateURL(String text, int flag) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(URL[flag]).append("?")
                .append("key=").append(YANDEX_KEY[flag]).append("&")
                .append("lang=").append(LANG).append("&")
                .append("text=").append(text);
        return stringBuilder.toString();
    }
}
