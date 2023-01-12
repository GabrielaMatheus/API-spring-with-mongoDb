package projeto.locadora.locadora.amqp;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class aluguelAmqp {
    @Bean
    public Queue criaFila(){
        //return new Queue("aluguel.concluido", false); --outra forma
        return QueueBuilder.nonDurable("aluguel.concluido").build();
    }
    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn){
        return new RabbitAdmin(conn);
    }

    //cria a fila no rabbit e detecta a inicialização
    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
