package cn.ddzu.shop.enums;

public enum ResultCode {
    SUCCESS,
    ERROR_NO_USER,
    ERROR_WRONG_PASSWORD,
    ERROR_PARAM_EMPTY,
    ERROE_UPLOAD_FAILED;

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
            case ERROE_UPLOAD_FAILED:
                return 1004;
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
            case ERROE_UPLOAD_FAILED:
                return "上传失败";
            default:
                return "error";
        }
    }
}
