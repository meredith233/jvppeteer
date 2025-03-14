package com.ruiyun.jvppeteer.cdp.core;

import com.ruiyun.jvppeteer.api.core.CDPSession;
import com.ruiyun.jvppeteer.api.events.ConnectionEvents;
import com.ruiyun.jvppeteer.common.AwaitableResult;
import com.ruiyun.jvppeteer.common.Constant;
import com.ruiyun.jvppeteer.common.ParamsFactory;
import com.ruiyun.jvppeteer.cdp.events.TracingCompleteEvent;
import com.ruiyun.jvppeteer.transport.CdpCDPSession;
import com.ruiyun.jvppeteer.util.Helper;
import com.ruiyun.jvppeteer.util.StringUtil;
import com.ruiyun.jvppeteer.util.ValidateUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * You can use [`tracing.start`](#tracingstartoptions) and [`tracing.stop`](#tracingstop) to create a trace file which can be opened in Chrome DevTools or [timeline viewer](https://chromedevtools.github.io/timeline-viewer/)
 */
public class Tracing implements Constant {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracing.class);
    /**
     * 当前要trace的 chrome devtools protocol session
     */
    private volatile CDPSession client;

    /**
     * 判断是否已经在追踪中
     */
    private boolean recording;

    /**
     * 追踪到的信息要保存的文件路径
     */
    private String path;

    public Tracing(CDPSession client) {
        this.client = client;
        this.recording = false;
        this.path = "";
    }

    public void start(String path) {
        this.start(path, false, null);
    }

    /**
     * <p>start tracing</p>
     * 每个浏览器一次只能激活一条跟踪
     *
     * @param path        A path to write the trace file to. 跟踪文件写入的路径
     * @param screenshots captures screenshots in the trace 捕获跟踪中的屏幕截图
     * @param categories  specify custom categories to use instead of default. 指定要使用的自定义类别替换默认值
     */
    public void start(String path, boolean screenshots, Set<String> categories) {
        ValidateUtil.assertArg(!this.recording, "Cannot start recording trace while already recording trace.");
        if (categories == null)
            categories = new HashSet<>(DEFAULTCATEGORIES);
        if (screenshots)
            categories.add("disabled-by-default-devtools.screenshot");
        this.path = path;
        this.recording = true;
        Map<String, Object> params = ParamsFactory.create();
        params.put("transferMode", "ReturnAsStream");
        List<String> excludedCategories = categories.stream().filter(category -> category.startsWith("-")).map(category -> category.substring(1)).collect(Collectors.toList());
        List<String> includedCategories = categories.stream().filter(category -> !category.startsWith("-")).collect(Collectors.toList());
        Map<String, Object> catParams = ParamsFactory.create();
        catParams.put("excludedCategories", excludedCategories);
        catParams.put("includedCategories", includedCategories);
        params.put("traceConfig", catParams);
        this.client.send("Tracing.start", params);
    }

    /**
     * stop tracing
     */
    public void stop() {
        AwaitableResult<Object> waitableResult = AwaitableResult.create();
        this.client.once(ConnectionEvents.Tracing_tracingComplete, (Consumer<TracingCompleteEvent>) event -> {
            try {
                ValidateUtil.assertArg(StringUtil.isNotEmpty(event.getStream()), "Missing \"stream\"");
                Helper.readProtocolStream(Tracing.this.client, event.getStream(), Tracing.this.path);
                waitableResult.complete();
            } catch (IOException e) {
                LOGGER.error("Error reading trace", e);
            }
        });
        this.client.send("Tracing.end");
        this.recording = false;
        waitableResult.waiting();
    }

    void updateClient(CDPSession newSession) {
        this.client = newSession;
    }
}
