package com.acercow.androidlib.net;

/**
 * Auto-generated: 2018-01-19 16:32:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Response<T> {

    private boolean isError;
    private int errorType;
    private String errorMessage;
    private T result;

    public void setIsError(boolean isError) {
        this.isError = isError;
    }
    public boolean getIsError() {
        return isError;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
    public int getErrorType() {
        return errorType;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setResult(T result) {
        this.result = result;
    }
    public T getResult() {
        return result;
    }

}