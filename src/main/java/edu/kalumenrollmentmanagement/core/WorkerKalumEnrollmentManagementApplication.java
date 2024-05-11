package edu.kalumenrollmentmanagement.core;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.kalumenrollmentmanagement.core.verticles.CandidateProcessVerticle;
import edu.kalumenrollmentmanagement.core.verticles.QueueConsumerVerticle;
import io.vertx.core.Vertx;

@SpringBootApplication
public class WorkerKalumEnrollmentManagementApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(WorkerKalumEnrollmentManagementApplication.class);

	private Vertx vertx;
	
	@Autowired
	private QueueConsumerVerticle queueConsumerVerticle;
	
	@Autowired
	private CandidateProcessVerticle candidateProcessVerticle;
	
	public static void main(String[] args) {
		SpringApplication.run(WorkerKalumEnrollmentManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Enrollment process iniciado.");
	}
	
	@PostConstruct
	public void deploymentVerticle() {
		this.vertx = Vertx.vertx();
		this.vertx.deployVerticle(queueConsumerVerticle);
		this.vertx.deployVerticle(candidateProcessVerticle);
	}

}
