package com.mrabid.hhis.Modal;

import java.util.ArrayList;

/**
 * Created by Mr Abid on 10/2/2017.
 */

public class ResponseLogin {
    private String status;
    private String message;
    private User data;

    public ResponseLogin() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
