package com.maple.baiduocr.entity;

public class UploadEntity {

    /**
     * code : 1
     * data : {"src":"/upload/20210326/fccdf653ce8be89a940e28293988b424.jpeg","src_thumb":"/upload/20210326/thumb_fccdf653ce8be89a940e28293988b424.jpeg"}
     * msg : 上传成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * src : /upload/20210326/fccdf653ce8be89a940e28293988b424.jpeg
         * src_thumb : /upload/20210326/thumb_fccdf653ce8be89a940e28293988b424.jpeg
         */

        private String src;
        private String src_thumb;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getSrc_thumb() {
            return src_thumb;
        }

        public void setSrc_thumb(String src_thumb) {
            this.src_thumb = src_thumb;
        }
    }
}
