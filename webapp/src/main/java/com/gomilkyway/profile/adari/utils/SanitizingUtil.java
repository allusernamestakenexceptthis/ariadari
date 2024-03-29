/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gomilkyway.profile.adari.utils;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;

public class SanitizingUtil {
    
    public static String stripHtml(String html) {
        CleanerProperties props = new CleanerProperties();
        props.setPruneTags("script");
        return new HtmlCleaner(props).clean(html).getText().toString().trim();
    }

    public static String stripAndOneLine(String html) {
        return stripHtml(html).replaceAll("\\s+", " ").replace("\\n", "");
    }

    public static String sanitizeFileName(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9-_.]", "");
    }
}
