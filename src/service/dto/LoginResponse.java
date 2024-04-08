package service.dto;

import java.util.Optional;

public class LoginResponse {
    private StringBuilder resultSb;
    private Optional<byte[]> authenticate;

    public LoginResponse(StringBuilder resultSb, Optional<byte[]> authenticate) {
        this.resultSb = new StringBuilder(resultSb);
        this.authenticate = authenticate;
    }

    public Optional<byte[]> getAuthenticate() {
        return authenticate;
    }

    public StringBuilder getResultSb() {
        return resultSb;
    }
}
