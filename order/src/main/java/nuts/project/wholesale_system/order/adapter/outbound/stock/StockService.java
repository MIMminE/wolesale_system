package nuts.project.wholesale_system.order.adapter.outbound.stock;

import nuts.project.wholesale_system.order.domain.exception.StockException;
import nuts.project.wholesale_system.order.domain.ports.stock.StockResponse;
import nuts.project.wholesale_system.order.domain.ports.stock.StockServicePort;
import nuts.project.wholesale_system.order.domain.ports.stock.StockRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static nuts.project.wholesale_system.order.domain.exception.StockException.StockExceptionCase.*;

@Component
public class StockService implements StockServicePort {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<StockResponse> checkStock(StockRequest request) throws StockException {


        try {
            //TODO
            System.out.println("stock check!");

            if (request.getRequestItems() == null) {
                throw new StockException(OUT_OF_STOCK);
            }

            return Optional.empty();
        } catch (RuntimeException e) {
            throw new StockException(STOCK_SERVICE_FAIL);
        }
    }

    @Override
    public Optional<StockResponse> reserveStock(StockRequest request) {
        try {
            //TODO
            System.out.println("stock reserve");
            if (request.getRequestItems() == null) {
                throw new StockException(OUT_OF_STOCK);
            }

            return Optional.empty();
        } catch (RuntimeException e) {
            throw new StockException(STOCK_SERVICE_FAIL);
        }
    }

    @Override
    public Optional<StockResponse> deductStock(StockRequest request) {
        try {
            //TODO
            System.out.println("stock deduct");
            return Optional.empty();
        } catch (RuntimeException e) {
            throw new StockException(STOCK_SERVICE_FAIL);
        }
    }

    @Override
    public Optional<StockResponse> returnStock(StockRequest request) {
        try {
            //TODO
            System.out.println("stock return");
            return Optional.empty();
        } catch (RuntimeException e) {
            throw new StockException(STOCK_SERVICE_FAIL);
        }
    }
}
