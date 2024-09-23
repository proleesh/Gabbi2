package org.proleesh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Gabbi2Application {

    private static final Logger log = LoggerFactory.getLogger(Gabbi2Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Gabbi2Application.class, args);
        Environment env = run.getBean(Environment.class);
        log.info("Configured server servlet session timeout value: "
                + env.getProperty("server.servlet.session.timeout"));

    }

}
