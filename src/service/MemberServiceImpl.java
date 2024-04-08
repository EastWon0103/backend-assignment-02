package service;

import repository.Member;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import service.dto.LoginResponse;
import service.dto.LogoutResponse;
import util.EncryptionUtil;

import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EncryptionUtil encryptionUtil;

    public MemberServiceImpl(){
        this.memberRepository = new MemberRepositoryImpl();
        this.encryptionUtil = new EncryptionUtil();
    }

    @Override
    public StringBuilder signUp(String id, String password, String nickname) {
        StringBuilder sb = new StringBuilder();

        if(!isValidId(id)) {
            sb.append("해당 아이디는 사용할 수 없습니다.\n");
            return sb;
        }

        if(!isValidNickname(nickname)) {
            sb.append("해당 닉네임은 사용할 수 없습니다.\n");
            return sb;
        }

        memberRepository.save(new Member(id, password, nickname));
        sb.append("회원가입 완료되었습니다\n")
            .append(String.format("아이디: %s\n", id))
            .append(String.format("닉네임: %s\n", nickname));
        return sb;
    }

    @Override
    public LoginResponse login(String id, String password) {
        StringBuilder sb = new StringBuilder();
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()) {
            sb.append("해당 아이디를 가진 유저가 존재하지 않습니다.\n");
            return new LoginResponse(sb, Optional.empty());
        }

        Member member = optionalMember.get();
        if (!member.getPassword().equals(password)) {
            sb.append("비밀번호가 맞지 않습니다.\n");
            return new LoginResponse(sb, Optional.empty());
        }
        sb.append("로그인이 성공했습니다.^^\n")
            .append(String.format("아이디: %s\n", id))
            .append(String.format("닉네임: %s\n", member.getNickname()));


        return new LoginResponse(sb, Optional.of(encryptionUtil.encrypt(id)));
    }

    @Override
    public StringBuilder updateNickname(Optional<byte[]> authenticate, String nickname) {
        StringBuilder sb = new StringBuilder();
        if(authenticate.isEmpty()) {
            sb.append("로그인 상태가 아닙니다.\n");
            return sb;
        }

        String id = encryptionUtil.decrypt(authenticate.get());
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("유자기 존재하지 않음"));
        member.setNickname(nickname);

        memberRepository.save(member);
        sb.append("닉네임이 업데이트가 되었습니다.\n")
            .append(String.format("아이디: %s\n", id))
            .append(String.format("닉네임: %s\n", member.getNickname()));;
        return sb;
    }

    @Override
    public LogoutResponse delete(Optional<byte[]> authenticate) {
        StringBuilder sb = new StringBuilder();
        if(authenticate.isEmpty()) {
            sb.append("로그인 상태가 아닙니다.\n");
            return new LogoutResponse(false, sb);
        }


        String id = encryptionUtil.decrypt(authenticate.get());
        memberRepository.delete(id);
        sb.append("탈퇴 완료했습니다.\n");
        return new LogoutResponse(true, sb);
    }

    public boolean isValidId(String id) {
        return memberRepository.findById(id).isEmpty();
    }

    public boolean isValidNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isEmpty();
    }
}
