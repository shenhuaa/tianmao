package com.tianmao.app.util;


import java.beans.ConstructorProperties;

public class Qrcode {
    private String ticket;
    private String url;

    @ConstructorProperties({"ticket", "url"})
    Qrcode(String ticket, String url) {
        this.ticket = ticket;
        this.url = url;
    }

    public static Qrcode.QrcodeBuilder builder() {
        return new Qrcode.QrcodeBuilder();
    }

    public String getTicket() {
        return this.ticket;
    }

    public String getUrl() {
        return this.url;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Qrcode)) {
            return false;
        } else {
            Qrcode other = (Qrcode)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$ticket = this.getTicket();
                Object other$ticket = other.getTicket();
                if (this$ticket == null) {
                    if (other$ticket != null) {
                        return false;
                    }
                } else if (!this$ticket.equals(other$ticket)) {
                    return false;
                }

                Object this$url = this.getUrl();
                Object other$url = other.getUrl();
                if (this$url == null) {
                    if (other$url != null) {
                        return false;
                    }
                } else if (!this$url.equals(other$url)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Qrcode;
    }

    public String toString() {
        return "Qrcode(ticket=" + this.getTicket() + ", url=" + this.getUrl() + ")";
    }

    public static class QrcodeBuilder {
        private String ticket;
        private String url;

        QrcodeBuilder() {
        }

        public Qrcode.QrcodeBuilder ticket(String ticket) {
            this.ticket = ticket;
            return this;
        }

        public Qrcode.QrcodeBuilder url(String url) {
            this.url = url;
            return this;
        }

        public Qrcode build() {
            return new Qrcode(this.ticket, this.url);
        }

        public String toString() {
            return "Qrcode.QrcodeBuilder(ticket=" + this.ticket + ", url=" + this.url + ")";
        }
    }
}
