package com.ruiyun.jvppeteer.cdp.events;

import com.ruiyun.jvppeteer.cdp.entities.StackTrace;

/**
 * Fired when frame has been attached to its parent.
 */
public class FrameAttachedEvent {

    /**
     * Id of the frame that has been attached.
     */
    private String frameId;
    /**
     * Parent frame identifier.
     */
    private String parentFrameId;
    /**
     * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
     */
    private StackTrace stack;

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getParentFrameId() {
        return parentFrameId;
    }

    public void setParentFrameId(String parentFrameId) {
        this.parentFrameId = parentFrameId;
    }

    public StackTrace getStack() {
        return stack;
    }

    public void setStack(StackTrace stack) {
        this.stack = stack;
    }
}
