package nuts.project.wolesale_system.gateway.port.log;

public interface LogPort {

    void send(String routingKey, String exchange, Object message);
}
