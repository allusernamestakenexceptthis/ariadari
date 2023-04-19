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

package com.gomilkyway.cmdline.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.fasterxml.jackson.core.util.VersionUtil;

public class CliVersionUtil {
    
    private static final String VERSION_FILE = "version.properties";
    private static final String VERSION_PROP = "version";

    public static String getVersion() {

        Properties props = new Properties();
        try (InputStream inputStream = VersionUtil.class.getClassLoader().getResourceAsStream(VERSION_FILE)) {
            if (inputStream != null) {
                props.load(inputStream);
                return props.getProperty(VERSION_PROP);
            }
        } catch (IOException e) {
            // ignore
        }

        return "unknown";
    }

}
