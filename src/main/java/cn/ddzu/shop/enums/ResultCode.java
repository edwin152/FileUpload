package cn.ddzu.shop.enums;

public enum ResultCode {
    SUCCESS,
    ERROR_NO_USER,
    ERROR_WRONG_PASSWORD,
    ERROR_PARAM_EMPTY,
    ERROR_UPLOAD_FAILED,
    ERROR_NO_LOGIN;

    public int getCode() {
        switch (this) {
            case SUCCESS:
                return 0;
            case ERROR_NO_USER:
                return 1001;
            case ERROR_WRONG_PASSWORD:
                return 1002;
            case ERROR_PARAM_EMPTY:
                return 1003;
            case ERROR_UPLOAD_FAILED:
                return 1004;
            case ERROR_NO_LOGIN:
                return 1005;
            default:
                return 1;
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
            case ERROR_UPLOAD_FAILED:
                return "上传失败";
            case ERROR_NO_LOGIN:
                return "未登录";
            default:
                return "error";
        }
    }
}
