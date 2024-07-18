package nuts.project.wholesale_system.order.domain.service.usecase.get;

import nuts.project.wholesale_system.order.adapter.outbound.repository.order.OrderEntity;
import nuts.project.wholesale_system.order.domain.exception.PaymentException;
import nuts.project.wholesale_system.order.domain.model.Order;
import nuts.project.wholesale_system.order.domain.model.OrderItem;
import nuts.project.wholesale_system.order.domain.ports.payment.PaymentResponse;
import nuts.project.wholesale_system.order.domain.service.dto.OrderProcessDto;
import nuts.project.wholesale_system.order.support.UseCaseTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.List;

import static nuts.project.wholesale_system.order.domain.exception.OrderException.OrderExceptionCase.GET_NO_SUCH_ELEMENT;
import static nuts.project.wholesale_system.order.domain.exception.PaymentException.PaymentExceptionCase.PAYMENT_SERVICE_FAIL;
import static org.junit.jupiter.api.Assertions.*;

class GetOrdersUseCaseTest extends UseCaseTestSupport {
    @DisplayName("getOrdersUseCase 성공 테스트")
    @Test
    void getOrdersUseCaseSuccess() {
        // given
        OrderEntity orderEntity = orderRepository.save(getOrderedObject(OrderEntity.class).get(0));
        String orderId = orderEntity.getOrderId();
        String userId = orderEntity.getUserId();

        BDDMockito.given(paymentService.getPaymentInformation(orderId))
                .willReturn(new PaymentResponse("testAccount", "testName", 15000));

        // when
        List<OrderProcessDto> orderProcessDtoList = getOrdersUseCase.execute(userId);

        // then
        Assertions.assertThat(orderProcessDtoList.get(0))
                .extracting("paymentInformation.payInfo", "order.orderId")
                .contains("testAccount", orderId);
    }

    @DisplayName("getOrdersUseCase 예외 발생 테스트 : 조회한 유저에 대한 주문 정보가 없을 때")
    @Test
    void getOrdersUseCaseUserIdNotFoundException() {
        // given
        OrderEntity orderEntity = getOrderedObject(OrderEntity.class).get(0);
        String userId = orderEntity.getUserId();

        // when then
        Assertions.assertThatThrownBy(() -> getOrdersUseCase.execute(userId))
                .hasMessage(GET_NO_SUCH_ELEMENT.getMessage());
    }

    @DisplayName("getOrdersUseCase 예외 발생 테스트 : 결제 시스템과 통신에 실패할 때")
    @Test
    void getOrdersUseCasePaymentException() {
        // given
        OrderEntity orderEntity = orderRepository.save(getOrderedObject(OrderEntity.class).get(0));
        String orderId = orderEntity.getOrderId();
        String userId = orderEntity.getUserId();

        BDDMockito.given(paymentService.getPaymentInformation(orderId))
                .willThrow(new PaymentException(PAYMENT_SERVICE_FAIL));

        // when then
        Assertions.assertThatThrownBy(() -> getOrdersUseCase.execute(userId))
                .hasMessage(PAYMENT_SERVICE_FAIL.getMessage());

    }
}