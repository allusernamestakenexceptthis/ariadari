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

}
