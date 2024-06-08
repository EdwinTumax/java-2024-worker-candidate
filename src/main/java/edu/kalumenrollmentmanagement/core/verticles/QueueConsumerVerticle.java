package edu.kalumenrollmentmanagement.core.verticles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import edu.kalumenrollmentmanagement.core.domain.models.ResponseEventBusCandidate;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.rabbitmq.QueueOptions;
import io.vertx.rabbitmq.RabbitMQClient;


@Component
public class QueueConsumerVerticle extends AbstractVerticle {	
	@Autowired
	private Gson gson;
	private EventBus eventBus;
	private RabbitMQClient rabbitMQClient;
	private static final Logger logger = LoggerFactory.getLogger(QueueConsumerVerticle.class);	
	private static final String EVENT_BUS_ENROLLMENT_PROCESS = "EVENT_BUS_ENROLLMENT_PROCESS";
	
	@Override
	public void start() {
		this.eventBus = vertx.eventBus();
		this.vertx.setTimer(5000, handler -> {
			readMessageEvent();
		});
	}
	
	public void readMessageEvent() {
		this.logger.info("Iniciando el proceso de lectura");
		JsonObject config = new JsonObject();
		config
			.put("user","guest")
			.put("password","guest")
			.put("host","localhost")
			.put("port","5672")
			.put("virtualhost","/")
			.put("queue","kalum.candidate");	
		
		this.rabbitMQClient = RabbitMQClient.create(vertx,config);
		
		this.rabbitMQClient.start(startResult -> {
			if(startResult.succeeded()) {
				this.logger.info("Conexion a rabbit de forma exitosa");
				this.rabbitMQClient.basicQos(1,asyncResult -> {
					this.logger.debug("Se establecio la lectura de mensajes en la cola de 1");
				});
				this.rabbitMQClient.basicConsumer("edu.kalum.queue.candidate",new QueueOptions().setAutoAck(false), consumer -> {
					if(consumer.succeeded()) {
						consumer.result().handler(message -> {							
							DeliveryOptions options = new DeliveryOptions();
							options.addHeader("count","1");							
							this.eventBus.request(EVENT_BUS_ENROLLMENT_PROCESS, message.body().toJsonObject(), options, resultEventBus -> {
								if(resultEventBus.succeeded()) {
									ResponseEventBusCandidate responseEventBus = gson.fromJson(resultEventBus.result().body().toString(), ResponseEventBusCandidate.class);
									if(responseEventBus.getStatusCode().equals("201")) {
										this.rabbitMQClient.basicAck(message.envelope().getDeliveryTag(), false, asyncResponse -> {
											this.logger.info("El mensaje en la cola fue procesada exitosamente");
										});	
									} else if(responseEventBus.getStatusCode().equals("403")) {
										this.rabbitMQClient.basicAck(message.envelope().getDeliveryTag(), false, asynresponse -> {
											this.logger.warn("Hubo un problema al momento de procesar la orden ".concat(responseEventBus.getOrderId()));
										});
									} else {
										this.rabbitMQClient.basicNack(message.envelope().getDeliveryTag(), false, true, asyncResponse ->{
											this.logger.error("Error al momento de procesar la orden ".concat(responseEventBus.getOrderId()));
										});
									}							
								} else {
									this.logger.error("Error al momento de enviar el mensaje al EVENT BUS");
								}
							});							
						});
					} else {
						this.logger.error("Error al momento de suscribirse a la cola");
					}
				});
				
			} else {
				this.logger.error("Error en conexion a rabbit".concat(startResult.cause().getMessage()));
			}
		});
	}
}
