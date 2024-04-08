package controller;

import service.MemberService;
import service.MemberServiceImpl;
import service.dto.LoginResponse;
import service.dto.LogoutResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Controller {
    private final MemberService memberService;

    public Controller() {
        this.memberService = new MemberServiceImpl();
    }

    public void run(){

        Optional<byte[]> authenticate = Optional.empty();
        while(true){
            System.out.println("-------멤버 서비스 콘솔---------");
            System.out.println("1: 회원가입 / 2: 로그인 / 3: 회원정보 수정 / 4: 탈퇴 / 0: 종료");
            System.out.print("입력: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                int input = Integer.parseInt(reader.readLine());
                switch(input) {
                    case 1 :
                        signUp(reader);
                        break;
                    case 2 :
                        authenticate = login(reader);
                        break;
                    case 3 :
                        update(authenticate, reader);
                        break;
                    case 4 :
                        boolean isSuccess = delete(authenticate);
                        if(isSuccess)
                            authenticate = Optional.empty();
                        break;
                    case 0 :
                        System.out.println("종료되었습니다.");
                        return;
                    default:
                        System.out.println("0~4까지만 입력 가능합니다.");
                }
            } catch (Exception e) {
                System.out.println("입력이 올바르지 않습니다.");
            }
            System.out.print("\n\n");
        }
    }

    private void signUp(BufferedReader reader) throws IOException {
        System.out.print("아이디 입력: ");
        String id = reader.readLine();
        System.out.print("비밀번호 입력: ");
        String password = reader.readLine();
        System.out.print("닉네임 입력: ");
        String nickname = reader.readLine();
        System.out.print(memberService.signUp(id, password, nickname));
    }

    private Optional<byte[]> login(BufferedReader reader) throws IOException{
        System.out.print("아이디 입력: ");
        String id = reader.readLine();
        System.out.print("비밀번호 입력: ");
        String password = reader.readLine();
        LoginResponse loginResponse = memberService.login(id, password);

        System.out.println(loginResponse.getResultSb());
        return loginResponse.getAuthenticate();
    }

    private void update(Optional<byte[]> authenticate, BufferedReader reader) throws IOException {
        System.out.print("변경할 닉네임 입력: ");
        String nickname = reader.readLine();
        System.out.println(memberService.updateNickname(authenticate, nickname));
    }

    private boolean delete(Optional<byte[]> authenticate) throws IOException {
        LogoutResponse logoutResponse = memberService.delete(authenticate);
        System.out.print(logoutResponse.getResultSb());
        return logoutResponse.isSuccess();
    }

}
