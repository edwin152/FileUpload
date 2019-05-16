package cn.ddzu.shop.enums;

public enum ResultCode {
    SUCCESS,
    ERROR_NO_USER,
    ERROR_WRONG_PASSWORD,
    ERROR_PARAM_EMPTY;

    public int getCode() {
        switch (this) {
            case SUCCESS:
                return 1;
            case ERROR_NO_USER:
                return 1001;
            case ERROR_WRONG_PASSWORD:
                return 1002;
            case ERROR_PARAM_EMPTY:
                return 1003;
            default:
                return 0;
        }
    }

    public String getMsg() {
        switch (this) {
            case SUCCESS:
                return "success";
            case ERROR_NO_USER:
                return "找不到该用户";
            case ERROR_WRONG_PASSWORD:
                return "密码错误";
            case ERROR_PARAM_EMPTY:
                return "参数异常";
            default:
                return "error";
        }
    }
}
