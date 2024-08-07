package nuts.project.wholesale_system.order.domain.service.dto;

import lombok.*;

@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Getter
public class PaymentInformation {
    private String userId;
    private String orderId;
    private String accountNumber;
}
