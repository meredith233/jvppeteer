package com.ruiyun.jvppeteer.cdp.events;

import com.ruiyun.jvppeteer.cdp.entities.CorsErrorStatus;

/**
 * Fired when HTTP request has failed to load.
 */
public class LoadingFailedEvent {

    /**
     * Request identifier.
     */
    private String requestId;
    /**
     * Timestamp.
     */
    private long timestamp;
    /**
     * Resource type.
     */
    private String type;
    /**
     * User friendly error message.
     */
    private String errorText;
    /**
     * True if loading was canceled.
     */
    private boolean canceled;
    /**
     * The reason why loading was blocked, if any.
     * "other"|"csp"|"mixed-content"|"origin"|"inspector"|"subresource-filter"|"content-type"|"collapsed-by-client";
     */
    private String blockedReason;
    /**
     * The reason why loading was blocked by CORS, if any.
     */
    private CorsErrorStatus corsErrorStatus;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getBlockedReason() {
        return blockedReason;
    }

    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }

    public CorsErrorStatus getCorsErrorStatus() {
        return corsErrorStatus;
    }

    public void setCorsErrorStatus(CorsErrorStatus corsErrorStatus) {
        this.corsErrorStatus = corsErrorStatus;
    }
}
