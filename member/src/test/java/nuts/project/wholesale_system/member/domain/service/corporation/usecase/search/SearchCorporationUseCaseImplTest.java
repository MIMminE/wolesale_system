package nuts.project.wholesale_system.member.domain.service.corporation.usecase.search;

import nuts.project.wholesale_system.member.adapter.outbound.repository.corporation.CorporationEntity;
import nuts.project.wholesale_system.member.domain.exception.CorporationUseCaseException;
import nuts.project.wholesale_system.member.domain.model.Corporation;
import nuts.project.wholesale_system.member.support.CorporationUseCaseTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static nuts.project.wholesale_system.member.domain.exception.CorporationUseCaseException.CorporationExceptionCase.*;

class SearchCorporationUseCaseImplTest extends CorporationUseCaseTestSupport {

    @Test
    @DisplayName("corporationName 만을 사용한 조회 성공 테스트")
    void successSearchCorporationsUseCase() {

        // given
        List<CorporationEntity> corporationEntities = corporationRepository.saveAll(getOrderedObject(CorporationEntity.class));

        // when
        CorporationEntity searchTargetEntity = corporationEntities.get(0);

        // then
        List<Corporation> result = searchCorporationsUseCase.execute(null, searchTargetEntity.getCorporationName(), null, null, null, null);
        String corporationId = result.get(0).getCorporationId();

        Corporation corporation = corporationRepository.findById(corporationId).orElseThrow().toCorporation();
        Assertions.assertThat(corporation).isEqualTo(result.get(0));
    }

    @Test
    @DisplayName("corporationName 값이 같은 두 데이터 조회 성공 테스트")
    void successTwoItemsSearchCorporationsUseCase() {

        // given
        List<CorporationEntity> corporationEntities = getOrderedObject(CorporationEntity.class);
        CorporationEntity firstCorporationEntity = corporationEntities.get(0);
        corporationRepository.save(firstCorporationEntity);


        CorporationEntity secondCorporationEntity = corporationEntities.get(1);
        secondCorporationEntity.setCorporationName(firstCorporationEntity.getCorporationName());
        secondCorporationEntity.setBusinessNumber("testBusinessNumber");
        corporationRepository.save(secondCorporationEntity);

        // when
        CorporationEntity searchTargetEntity = corporationEntities.get(0);

        // then
        List<Corporation> result = searchCorporationsUseCase.execute(null, searchTargetEntity.getCorporationName(), null, null, null, null);

        Corporation firstCorporation = result.get(0);
        Corporation secondCorporation = result.get(1);

        String firstCorporationId = firstCorporation.getCorporationId();
        String secondCorporationId = secondCorporation.getCorporationId();

        Assertions.assertThat(corporationRepository.findById(firstCorporationId).orElseThrow().toCorporation()).isEqualTo(firstCorporation);
        Assertions.assertThat(corporationRepository.findById(secondCorporationId).orElseThrow().toCorporation()).isEqualTo(secondCorporation);
    }

    @Test
    @DisplayName("검색 조건에 맞는 데이터가 없을 때 예외 발생")
    void exceptionSearchCorporationsUseCase() {

        // given
        String corporationId = UUID.randomUUID().toString();

        // when then
        Assertions.assertThatThrownBy(() -> searchCorporationsUseCase.execute(corporationId, null, null, null, null, null))
                .hasMessage(SEARCH_NO_SUCH_ELEMENT.getMessage())
                .isInstanceOf(CorporationUseCaseException.class);

    }
}