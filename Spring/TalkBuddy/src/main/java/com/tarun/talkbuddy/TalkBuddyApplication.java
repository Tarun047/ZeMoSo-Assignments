package com.tarun.talkbuddy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
java -ea -cp build/classes/java/main/:$RANDOOP_JAR randoop.main.Main gentests --testclass=com.tarun.talkbuddy.TalkBuddyApplication  --junit-output-dir=out/test --output-limit=5

* */

@SpringBootApplication
@CrossOrigin("localhost:3000")
@EnableJpaAuditing
public class TalkBuddyApplication {
	public static void main(String[] args) {
		SpringApplication.run(TalkBuddyApplication.class, args);
	}

}
