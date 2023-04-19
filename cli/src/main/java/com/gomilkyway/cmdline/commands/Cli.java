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

package com.gomilkyway.cmdline.commands;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(
    name = "ari-adari",
    description = "Command line tool for ariadari website",
    mixinStandardHelpOptions = true,
    version = "1.0.0"
)
public class Cli implements Callable<Integer> {
    
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message")
    private boolean helpRequested;

    @Override
    public Integer call() throws Exception {

        System.out.printf("Subcommand required");
        return -1;
    }

    
}
