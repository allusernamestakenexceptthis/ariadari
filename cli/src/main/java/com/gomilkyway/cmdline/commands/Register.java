package com.gomilkyway.cmdline.commands;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(
    name = "register",
    description = "Register new user",
    mixinStandardHelpOptions = true,
    version = "1.0.0"
)
public class Register implements Callable<Integer> {

    @Option(names = {"-u", "--username"}, description = "Username", required = true)
    @Option(names = {"-p", "--password"}, description = "Password", required = true)

    @Override
    public Integer call() throws Exception {
        

        System.out.println("Implementing option " + this.option);

        return null;
    }

    
}
