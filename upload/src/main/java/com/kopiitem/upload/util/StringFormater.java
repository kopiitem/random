package com.kopiitem.upload.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author donny.fm
 */
public class StringFormater {

    public static String format(String string, Object... objects) {
        int i = 0;
        for (Object object : objects) {
            string = string.replace("{" + String.valueOf(i) + "}", String.valueOf(object));
            i++;
        }
        return string;
    }

    public static String handleException(Exception ex) {
        String strStackTrace = null;
        if (ex != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            ex.printStackTrace(printWriter);
            strStackTrace = stringWriter.toString();
            int endIndex = (strStackTrace.length() > 5000 ? 5000 : strStackTrace.length());
            strStackTrace = strStackTrace.substring(0, endIndex);
        }
        return strStackTrace;
    }
}
