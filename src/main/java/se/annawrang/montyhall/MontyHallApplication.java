package se.annawrang.montyhall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.annawrang.montyhall.simulator.MontyHallSimulator;

@SpringBootApplication
public class MontyHallApplication implements CommandLineRunner {

	@Autowired
	private MontyHallSimulator montyHallSimulator;

	@Value("${rounds}")
	private int rounds;

	public static void main(String[] args) {
		SpringApplication.run(MontyHallApplication.class, args);
	}

	@Override
	public void run(String... args) {
		montyHallSimulator.simulateRounds(rounds);
	}
}
