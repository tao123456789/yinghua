package com.yinghua.job.domain.wx;

public class WXArticlesPO {
    /*
    标题，必填
     */
    String title;
    /*
    作者
     */
    String author;
    /*
    图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前54个字。
     */
    String digest;
    /*
    必填
    图文消息的具体内容，支持 HTML 标签，必须少于2万字符，小于1M，且此处会去除 JS ,涉及图片 url 必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片 url 将被过滤。
     */
    String content;
    String content_source_url;
    String thumb_media_id;
    String need_open_comment;
    String only_fans_can_comment;

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public String getDigest () {
        return digest;
    }

    public void setDigest (String digest) {
        this.digest = digest;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public String getContent_source_url () {
        return content_source_url;
    }

    public void setContent_source_url (String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getThumb_media_id () {
        return thumb_media_id;
    }

    public void setThumb_media_id (String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getNeed_open_comment () {
        return need_open_comment;
    }

    public void setNeed_open_comment (String need_open_comment) {
        this.need_open_comment = need_open_comment;
    }

    public String getOnly_fans_can_comment () {
        return only_fans_can_comment;
    }

    public void setOnly_fans_can_comment (String only_fans_can_comment) {
        this.only_fans_can_comment = only_fans_can_comment;
    }
}
