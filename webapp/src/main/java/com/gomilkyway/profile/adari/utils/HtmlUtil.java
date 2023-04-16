package com.gomilkyway.profile.adari.utils;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;

public class HtmlUtil {
    
    public static String stripHtml(String html) {
        CleanerProperties props = new CleanerProperties();
        props.setPruneTags("script");
        return new HtmlCleaner(props).clean(html).getText().toString().trim();
    }

    public static String stripAndOneLine(String html) {
        return stripHtml(html).replaceAll("\\s+", " ").replace("\\n", "");
    }

}
