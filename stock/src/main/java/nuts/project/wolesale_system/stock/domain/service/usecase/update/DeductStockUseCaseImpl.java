package nuts.project.wolesale_system.stock.domain.service.usecase.update;

import nuts.project.wolesale_system.stock.domain.service.dto.UpdateStockResultDto;
import org.springframework.stereotype.Component;

@Component
public class DeductStockUseCaseImpl implements DeductStockUseCase{
    @Override
    public UpdateStockResultDto execute(String stockId, int deductQuantity) {
        return null;
    }
}