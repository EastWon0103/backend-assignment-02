package service.dto;

public class LogoutResponse {
    private boolean success;
    private StringBuilder resultSb;

    public LogoutResponse(boolean success, StringBuilder resultSb) {
        this.success = success;
        this.resultSb = resultSb;
    }

    public StringBuilder getResultSb() {
        return resultSb;
    }

    public boolean isSuccess() {
        return success;
    }
}
