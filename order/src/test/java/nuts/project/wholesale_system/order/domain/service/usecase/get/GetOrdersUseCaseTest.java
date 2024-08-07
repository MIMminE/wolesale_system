package nuts.project.wholesale_system.order.domain.service.usecase.get;

import nuts.project.wholesale_system.order.adapter.outbound.repository.order.OrderEntity;
import nuts.project.wholesale_system.order.domain.service.dto.OrderProcessDto;
import nuts.project.wholesale_system.order.support.UseCaseTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.List;

import static nuts.project.wholesale_system.order.domain.exception.OrderException.OrderExceptionCase.GET_NO_SUCH_ELEMENT;

class GetOrdersUseCaseTest extends UseCaseTestSupport {
//    @DisplayName("회원 번호에 해당하는 주문 정보들을 반환한다.")
//    @Test
//    void getOrdersUseCaseSuccess() {
//        // given
//        OrderEntity orderEntity = orderRepository.save(getOrderedObject(OrderEntity.class).get(0));
//        String orderId = orderEntity.getOrderId();
//        String userId = orderEntity.getUserId();
//
//        BDDMockito.given(paymentService.getPaymentInformation(orderId))
//                .willReturn(new PaymentResponse("testAccount", "testName", 15000));
//
//        // when
//        List<OrderProcessDto> orderProcessDtoList = getOrdersUseCase.execute(userId);
//
//        // then
//        Assertions.assertThat(orderProcessDtoList.get(0))
//                .extracting("paymentInformation.payInfo", "order.orderId")
//                .contains("testAccount", orderId);
//    }

    @DisplayName("잘못된 회원 번호로 요청을 보냈을 경우 예외를 던진다.")
    @Test
    void getOrdersUseCaseUserIdNotFoundOrderId() {
        // given
        OrderEntity orderEntity = getOrderedObject(OrderEntity.class).get(0);
        String userId = orderEntity.getUserId();

        // when then
        Assertions.assertThatThrownBy(() -> getOrdersUseCase.execute(userId))
                .hasMessage(GET_NO_SUCH_ELEMENT.getMessage());
    }
}