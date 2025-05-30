package com.ruiyun.example;

import com.ruiyun.jvppeteer.api.core.Browser;
import com.ruiyun.jvppeteer.api.core.Page;
import com.ruiyun.jvppeteer.api.core.Target;
import com.ruiyun.jvppeteer.cdp.core.Puppeteer;
import com.ruiyun.jvppeteer.cdp.entities.GoToOptions;
import com.ruiyun.jvppeteer.common.PuppeteerLifeCycle;
import com.ruiyun.jvppeteer.cdp.entities.TargetType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


import static com.ruiyun.example.A_LaunchTest.LAUNCHOPTIONS;

public class B_PageGotoTest {
    /**
     * 新建一个页面，并打开网址
     */
    @Test
    public void test2() throws Exception {
        //启动浏览器
        try  {
            Browser browser = Puppeteer.launch(LAUNCHOPTIONS);
            //打开一个页面
            Page page = browser.newPage();
            GoToOptions options = new GoToOptions();
            List<PuppeteerLifeCycle> waitUntil = new ArrayList<>();
            //页面加载到 networkidle状态下 goto才算完成
            waitUntil.add(PuppeteerLifeCycle.networkIdle);
            options.setWaitUntil(waitUntil);
            page.goTo("https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Map", options);
            //不添加waitUntil参数，默认是load
            page.goTo("https://www.baidu.com/?tn=68018901_16_pg");
                    Thread.sleep(5000);
            System.out.println("完成了。。。");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用打开浏览器就有的页面 去打开网址
    @Test
    public void test3() throws Exception {
        try (Browser browser = Puppeteer.launch(LAUNCHOPTIONS)) {
            List<Target> targets = browser.targets();
            Target target = targets.stream().filter(t -> t.type().equals(TargetType.PAGE)).findFirst().orElse(null);
            if (target == null) {
                return;
            }
            Page page = target.page();
            page.goTo("https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Map");
            //不添加waitUntil参数，默认是load
            page.goTo("https://www.baidu.com/?tn=68018901_16_pg");
        }
    }

    //测试超时时间，0代表无限等待
    @Test
    public void test4() throws Exception {
        try (Browser browser = Puppeteer.launch(LAUNCHOPTIONS)) {
            List<Target> targets = browser.targets();
            Target target = targets.stream().filter(t -> t.type().equals(TargetType.PAGE)).findFirst().orElse(null);
            if (target == null) {
                return;
            }
            Page page = target.page();
            page.setDefaultNavigationTimeout(0);
            page.goTo("https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Map");
            //不添加waitUntil参数，默认是load
            page.goTo("https://www.baidu.com/?tn=68018901_16_pg");
        }
    }
    //测试超时时间，使用options的超时时间，而不是默认的
    @Test
    public void test5() throws Exception {
        try (Browser browser = Puppeteer.launch(LAUNCHOPTIONS)) {
            List<Target> targets = browser.targets();
            Target target = targets.stream().filter(t -> t.type().equals(TargetType.PAGE)).findFirst().orElse(null);
            if (target == null) {
                return;
            }
            Page page = target.page();
            page.setDefaultNavigationTimeout(0);
            GoToOptions options = new GoToOptions();
            options.setTimeout(1);
            page.goTo("https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Map",options);
            //不添加waitUntil参数，默认是load
            page.goTo("https://www.baidu.com/?tn=68018901_16_pg");
        }
    }

    //测试超时时间，使用page设置的超时时间
    @Test
    public void test6() throws Exception {
        try (Browser browser = Puppeteer.launch(LAUNCHOPTIONS)) {
            List<Target> targets = browser.targets();
            Target target = targets.stream().filter(t -> t.type().equals(TargetType.PAGE)).findFirst().orElse(null);
            if (target == null) {
                return;
            }
            Page page = target.page();
            page.setDefaultNavigationTimeout(2);
            page.goTo("https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Map");
            //不添加waitUntil参数，默认是load
            page.goTo("https://www.baidu.com/?tn=68018901_16_pg");
        }
    }
}
