package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {
    private Map<String, Member> members;

    public MemberRepositoryImpl() {
        this.members = new HashMap<>();
    }

    @Override
    public void save(Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(members.getOrDefault(id, null));
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        for(Member member : members.values()) {
            if(member.getNickname().equals(nickname)) return Optional.of(member);
        }

        return Optional.empty();
    }


    @Override
    public void delete(String id) {
        Member member = findById(id).orElseThrow(() -> new RuntimeException("해당 id를 가진 멤버가 존재하지 않습니다"));

        members.remove(member.getId());
    }
}
