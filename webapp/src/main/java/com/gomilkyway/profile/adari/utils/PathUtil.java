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

public class PathUtil {

    private PathUtil() {
        throw new IllegalStateException("Utility class, can't be instantiated");
    }	
    
    public static String sanitizePath(String servletPath) {
        String sanitizedPath = servletPath.replaceAll("[^a-zA-Z0-9-_/\\.]", "");
        sanitizedPath = sanitizedPath.replaceAll("/+", "/");
        sanitizedPath = sanitizedPath.startsWith("/") ? sanitizedPath.substring(1) : sanitizedPath;
        return sanitizedPath;
    }

    public static String getExtensionFromFilename(String filename) {
        if (!filename.contains(".")){
            return "";
        }

        return filename.substring(filename.lastIndexOf(".") + 1);
    }

}
