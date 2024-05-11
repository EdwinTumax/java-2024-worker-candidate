package edu.kalumenrollmentmanagement.core.verticles;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import edu.kalumenrollmentmanagement.core.domain.models.OrderCandidate;
import edu.kalumenrollmentmanagement.core.domain.models.ResponseEventBusCandidate;
import edu.kalumenrollmentmanagement.core.domain.models.ResponseEventBusCandidateData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

@Component
public class CandidateProcessVerticle extends AbstractVerticle{
	private EventBus eventBus;
	private static final String EVENT_BUS_ENROLLMENT_PROCESS = "EVENT_BUS_ENROLLMENT_PROCESS";
	@Autowired
	private Gson gson;

	private static final Logger logger = LoggerFactory.getLogger(CandidateProcessVerticle.class);
	
	@Override
	public void start() {
		this.eventBus = vertx.eventBus();
		this.vertx.setTimer(5000, handler -> {
			sendOrder();
		});
	}
	
	public void sendOrder() {	
		logger.info("Iniciando el proceso de envio de ordenes al servicio soap");
		this.eventBus.consumer(EVENT_BUS_ENROLLMENT_PROCESS, message -> {
			OrderCandidate order = gson.fromJson(message.body().toString(),OrderCandidate.class);
			this.logger.info("Order: " + order);			
			ResponseEventBusCandidate response = new ResponseEventBusCandidate();
			response.setOrderId(String.valueOf(new Date().getTime()));
			SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
			response.setOrderDate(dt.format(new Date()));
			response.setStatusCode("201");
			response.setData(new ResponseEventBusCandidateData("EXP-2024001","edwintumax@gmail.com"));
			JsonObject result = JsonObject.mapFrom(response);
			message.replyAndRequest(result, resultHandler -> {
				this.logger.info("Se envio la respuesta al verticle de rabbit de la orden " + order.getOrderId());				
			});
			/*this.eventBus.request(EVENT_BUS_ENROLLMENT_PROCESS, result, resultHandler -> {
				if(resultHandler.succeeded()) {
					
					this.logger.info("Se envio la respuesta al verticle de rabbit de la orden " + order.getOrderId());
				} else {
					
					this.logger.error("Hubo un error al enviar el mensaje al verticle de rabbit");
				}
			});*/
		});	
	}
}
