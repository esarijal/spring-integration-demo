package com.erm.middleware.payload;

public class ConsumedMessage {
    private String url;

    public ConsumedMessage() {
    }

    public ConsumedMessage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ConsumedMessage{" +
                "url='" + url + '\'' +
                '}';
    }
}
