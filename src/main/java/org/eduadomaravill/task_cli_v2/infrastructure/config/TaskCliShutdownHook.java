package org.eduadomaravill.task_cli_v2.infrastructure.config;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class TaskCliShutdownHook implements ApplicationRunner {

    private final Terminal terminal;

    @Autowired
    public TaskCliShutdownHook(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void run(ApplicationArguments args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            terminal.writer().println("\n----------------------------👋 ¡Thanks for using Task CLI! See you later. 🚀----------------------------");
            terminal.flush();
        }));
    }
}
