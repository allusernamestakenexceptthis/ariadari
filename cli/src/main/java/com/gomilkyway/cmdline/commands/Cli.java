package com.gomilkyway.cmdline.commands;

import java.util.Arrays;
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
