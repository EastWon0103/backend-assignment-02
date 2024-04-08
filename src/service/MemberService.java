package service;

import repository.Member;
import service.dto.LoginResponse;
import service.dto.LogoutResponse;

import java.util.Optional;

public interface MemberService {
    StringBuilder signUp(String id, String password, String nickname);
    LoginResponse login(String id, String password);
    StringBuilder updateNickname(Optional<byte[]> authenticate, String nickname);
    LogoutResponse delete(Optional<byte[]> authenticate);
}
