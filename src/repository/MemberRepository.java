package repository;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByNickname(String nickname);
    void delete(String id);
}
