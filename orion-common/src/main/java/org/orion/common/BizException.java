//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.orion.common;
/**
 * @author leo
 * @date 2019/2/12
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 5402012883466443408L;
    private String code;
    private String message;

    public BizException() {
    }

    public BizException(String code) {
        this(code, (String)null);
    }

    public BizException(String code, String message) {
        this(code, message, (Throwable)null);
    }

    public BizException(String code, Throwable cause) {
        this(code, (String)null, cause);
    }

    public BizException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
