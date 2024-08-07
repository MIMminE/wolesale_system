package nuts.project.wholesale_system.order.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class OrderException extends RuntimeException {

    public OrderException(OrderExceptionCase orderExceptionCase) {
        super(orderExceptionCase.getMessage());
    }

    @RequiredArgsConstructor
    @Getter
    public enum OrderExceptionCase {

        CREATE_REDUNDANCY_BUSINESS_NUMBER("This is a business number that is already registered."),
        DELETE_NO_SUCH_ELEMENT("There is no target to delete."),
        GET_NO_SUCH_ELEMENT("No data is available for searching."),
        SEARCH_NO_SUCH_ELEMENT("There is no data found for that condition."),
        UPDATE_NO_SUCH_ELEMENT("An invalid ID was entered."),
        UPDATE_NO_MATCH_STATUS("Invalid status change request."),
        UNKNOWN_EXCEPTION("It's an unknown exception."),

        PAYMENT_SERVICE_FAIL("Payment Service fail"),
        PAYMENT_NOT_FOUND("Payment not found."),
        STOCK_SERVICE_FAIL("Stock Service fail"),
        OUT_OF_STOCK("Out of stock"),;


        private final String message;
    }
}
