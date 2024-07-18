package nuts.project.wolesale_system.stock.domain.service;

import lombok.RequiredArgsConstructor;
import nuts.project.wolesale_system.stock.domain.model.StockCategory;
import nuts.project.wolesale_system.stock.domain.service.dto.CreateStockResultDto;
import nuts.project.wolesale_system.stock.domain.service.dto.GetStockResultDto;
import nuts.project.wolesale_system.stock.domain.service.dto.UpdateStockResultDto;
import nuts.project.wolesale_system.stock.domain.service.usecase.create.CreateStockUseCase;
import nuts.project.wolesale_system.stock.domain.service.usecase.delete.DeleteStockUseCase;
import nuts.project.wolesale_system.stock.domain.service.usecase.get.GetStockUseCase;
import nuts.project.wolesale_system.stock.domain.service.usecase.update.AddStockUseCase;
import nuts.project.wolesale_system.stock.domain.service.usecase.update.DeductStockUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final CreateStockUseCase createStockUseCase;
    private final DeleteStockUseCase deleteStockUseCase;
    private final GetStockUseCase getStockUseCase;
    private final DeductStockUseCase deductStockUseCase;
    private final AddStockUseCase addStockUseCase;

    public CreateStockResultDto createStock(String productName, String category) {

        StockCategory stockCategory = StockCategory.valueOf(category);
        return createStockUseCase.execute(productName, stockCategory);
    }

    public void deleteStock(String stockId) {
        deleteStockUseCase.execute(stockId);
    }

    public GetStockResultDto getStock(String stockId) {
        return getStockUseCase.execute(stockId);
    }

    public UpdateStockResultDto deductStock(String stockId, int count) {
        return deductStockUseCase.execute(stockId, count);
    }

    public UpdateStockResultDto addStock(String stockId, int count) {
        return addStockUseCase.execute(stockId, count);
    }
}