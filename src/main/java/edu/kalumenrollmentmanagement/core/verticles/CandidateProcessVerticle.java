package edu.kalumenrollmentmanagement.core.verticles;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import edu.kalumenrollmentmanagement.core.clients.soap.services.CandidateGenerateNumberRequest;
import edu.kalumenrollmentmanagement.core.clients.soap.services.CandidateGenerateNumberResponse;
import edu.kalumenrollmentmanagement.core.clients.soap.services.GenerateNumberFileServiceImplService;
import edu.kalumenrollmentmanagement.core.clients.soap.services.IGenerateNumberFileService;
import edu.kalumenrollmentmanagement.core.domain.models.OrderCandidate;
import edu.kalumenrollmentmanagement.core.domain.models.ResponseEventBusCandidate;
import edu.kalumenrollmentmanagement.core.domain.models.ResponseEventBusCandidateData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;

@Component
public class CandidateProcessVerticle extends AbstractVerticle{
	private EventBus eventBus;
	private static final String EVENT_BUS_ENROLLMENT_PROCESS = "EVENT_BUS_ENROLLMENT_PROCESS";
	private static final String CIRCUIT_BREAKER_BUS_ENROLLMENT_PROCESS = "CIRCUIT_BREAKER_BUS_ENROLLMENT_PROCESS";
	@Autowired
	private Gson gson;

	private static final Logger logger = LoggerFactory.getLogger(CandidateProcessVerticle.class);
	
	@Override
	public void start() {
		this.eventBus = vertx.eventBus();
		
		CircuitBreaker circuitBreaker = CircuitBreaker.create(CIRCUIT_BREAKER_BUS_ENROLLMENT_PROCESS, vertx, new CircuitBreakerOptions()
				.setMaxFailures(5)
				.setTimeout(20000)
				.setFallbackOnFailure(true)				
				.setResetTimeout(10000))
				.openHandler(cb -> {logger.info("Circuit Breaker opened");})
				.closeHandler(cb -> {logger.info("Circuit Breaker closed");})
				.halfOpenHandler(cb -> {logger.info("Circuit Breaker half open");});
		
		this.vertx.setTimer(5000, handler -> {
			sendOrder(circuitBreaker);
		});
	}
	
	public void sendOrder(CircuitBreaker circuitBreaker) {
		logger.info("Iniciando el proceso de envio de ordenes al servicio soap");
		this.eventBus.consumer(EVENT_BUS_ENROLLMENT_PROCESS, message -> {
			OrderCandidate order = gson.fromJson(message.body().toString(),OrderCandidate.class);
			this.logger.info("Order: " + order);
			circuitBreaker.<JsonObject>execute( future -> {
				JsonObject result = null;
				ResponseEventBusCandidate responseEventBus = new ResponseEventBusCandidate();
				responseEventBus.setOrderId(String.valueOf(new Date().getTime()));
				SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
				responseEventBus.setOrderDate(dt.format(new Date()));
				try {
					IGenerateNumberFileService generateNumberFileService = new GenerateNumberFileServiceImplService()
							.getGenerateNumberFileServiceImplPort();
					CandidateGenerateNumberRequest request = new CandidateGenerateNumberRequest();
					request.setApellidos(order.getData().getApellidos());
					request.setNombres(order.getData().getNombres());
					request.setDireccion(order.getData().getDireccion());
					request.setTelefono(order.getData().getTelefono());
					request.setEmail(order.getData().getEmail());
					request.setCarreraId(order.getData().getCarreraId());
					request.setJornadaId(order.getData().getJornadaId());
					request.setExamenId(order.getData().getExamenId());				
					CandidateGenerateNumberResponse response = generateNumberFileService.executeCandidateNumber(request);
					responseEventBus.setData(new ResponseEventBusCandidateData(response.getCandidateNumber(),order.getData().getEmail()));
					if(response.getStatus() == 201) {	
						responseEventBus.setStatusCode("201");	
						result = JsonObject.mapFrom(responseEventBus);
						this.logger.debug("Web Services success: " + this.gson.toJson(response));
					} else {
						responseEventBus.setStatusCode("403");
						result = JsonObject.mapFrom(responseEventBus);
						this.logger.debug("Web Services error: " + this.gson.toJson(response));
					}
					future.complete(result);
				}catch(Exception ex) {					
					logger.error(ex.getCause().getMessage());
					responseEventBus.setData(new ResponseEventBusCandidateData("0",order.getData().getEmail()));
					responseEventBus.setStatusCode("503");
					result = JsonObject.mapFrom(responseEventBus);
					future.fail(gson.toJson(result));
				}				
			}).onComplete(response -> {
				if(response.succeeded()) {
					this.logger.info("Web Services Success");
					message.reply(response.result());	
				} else {
					ResponseEventBusCandidate responseEventBus = new ResponseEventBusCandidate();
					responseEventBus.setData(new ResponseEventBusCandidateData("0",order.getData().getEmail()));
					responseEventBus.setStatusCode("503");
					responseEventBus.setOrderId(String.valueOf(new Date().getTime()));
					SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
					responseEventBus.setOrderDate(dt.format(new Date()));
					this.logger.error("Web Services Failed");
					message.reply(gson.toJson(responseEventBus));	
				}
			});
			
		});	
	}
}
