package de.fhws.app.showcase.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

@Startup
@Singleton
public class BatchJobExample {

    @PostConstruct
    public void init() {
        System.out.println("start batch job");
    }

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void runner() {
        System.out.println("mach was...");
    }
}
