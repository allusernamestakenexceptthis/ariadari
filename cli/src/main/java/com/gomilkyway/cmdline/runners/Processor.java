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
