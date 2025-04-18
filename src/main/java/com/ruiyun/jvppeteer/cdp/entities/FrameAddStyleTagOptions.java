package com.ruiyun.jvppeteer.cdp.entities;

import com.ruiyun.jvppeteer.cdp.core.CdpFrame;

/**
 * 在${@link CdpFrame#addScriptTag(FrameAddScriptTagOptions)}用到
 */
public class FrameAddStyleTagOptions {

    private String url;

    private String path;

    private String content = "";

    public FrameAddStyleTagOptions() {
        super();
    }

    public FrameAddStyleTagOptions(String url, String path, String content) {
        this.url = url;
        this.path = path;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
