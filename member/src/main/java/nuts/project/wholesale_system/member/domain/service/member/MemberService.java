package nuts.project.wholesale_system.member.domain.service.member;

import lombok.RequiredArgsConstructor;
import nuts.project.wholesale_system.member.domain.model.Member;
import nuts.project.wholesale_system.member.domain.service.member.usecase.create.CreateMemberUseCase;
import nuts.project.wholesale_system.member.domain.service.member.usecase.delete.DeleteMemberUseCase;
import nuts.project.wholesale_system.member.domain.service.member.usecase.get.GetMemberUseCase;
import nuts.project.wholesale_system.member.domain.service.member.usecase.update.UpdateMemberUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final GetMemberUseCase getMemberUseCase;
    private final CreateMemberUseCase createMemberUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;
    private final UpdateMemberUseCase updateMemberUseCase;

    public Member getMember(String id) {
        return getMemberUseCase.execute(id);
    }

    public Member createMember(String name, String id, String password, String contactNumber, String corporationId) {
        return createMemberUseCase.execute(name, id, password, contactNumber, corporationId);
    }

    public Member deleteMember(String memberId) {
        return deleteMemberUseCase.execute(memberId);
    }

    public Member updateMember(String memberId, Member member) {
        return updateMemberUseCase.execute(memberId, member);
    }
}
