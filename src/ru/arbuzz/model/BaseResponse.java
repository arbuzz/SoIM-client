package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "authResponse")
public class BaseResponse {

    public static final int OK = 1;
    public static final int AUTH_ERROR = 2;

    @Attribute
    private int resultCode;

    public BaseResponse() {}

    public BaseResponse(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
