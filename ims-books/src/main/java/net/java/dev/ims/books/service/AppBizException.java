package net.java.dev.ims.books.service;


/**
 *
 * @author Jeff.Yu
 *
 */
public class AppBizException extends Exception {
    public static final long serialVersionUID = 20040000;
    private String code;
    private Object[] args;

    public AppBizException(String code, String msg) {
        super(code + ": " + msg);
        this.code = code;
    }

    public AppBizException(String code, String msg, Throwable cause) {
        super(code + ": " + msg, cause);
        this.code = code;
    }

    public AppBizException(String code, Object[] args) {
        super(code);
        this.code = code;
        this.args = args;
    }

    public AppBizException(String code, Object[] args, String msg) {
        super(code + ": " + msg);
        this.code = code;
        this.args = args;
    }

    public AppBizException(String code, Object[] args, String msg,
        Throwable cause) {
        super(code + ": " + msg, cause);
        this.code = code;
        this.args = args;
    }

    public AppBizException(Throwable cause) {
        super(cause);
    }

    /**
     * @return Returns the args.
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * @param args The args to set.
     */
    public void setArgs(Object[] args) {
        this.args = args;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }
}
