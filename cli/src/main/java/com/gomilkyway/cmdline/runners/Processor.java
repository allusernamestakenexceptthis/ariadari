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

package com.gomilkyway.cmdline.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IFactory;

public class Processor implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;
    private final Command command;

    private int returnCode = 0;

    public Processor(IFactory factory, Command command) {
        this.factory = factory;
        this.command = command;
    }

    @Override
    public void run(String... args) throws Exception {
        returnCode = new CommandLine(command, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return returnCode;
    }
    
}
