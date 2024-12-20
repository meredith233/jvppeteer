package com.ruiyun.jvppeteer.common;

import com.ruiyun.jvppeteer.cdp.core.WaitTask;
import com.ruiyun.jvppeteer.exception.JvppeteerException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);
    private final Set<WaitTask> tasks = new CopyOnWriteArraySet<>();

    public void add(WaitTask task) {
        tasks.add(task);
    }

    public void delete(WaitTask task) {
        tasks.remove(task);
    }

    public void terminateAll(JvppeteerException error) {
        tasks.forEach(task -> {
                    try {
                        task.terminate(error);
                    } catch (Exception e) {
                        LOGGER.error("jvppeteer error", e);
                    }
                }
        );
        tasks.clear();
    }

    public void rerunAll() {
//        CompletionService<Void> completionService = new ExecutorCompletionService<>(WaitTask.waitTaskService);
//        List<Future<Void>> futures = new java.util.ArrayList<>();
//        this.tasks.forEach(task -> {
//            Future<Void> future = completionService.submit(() -> {
//                task.rerun();
//                return null;
//            });
//            futures.add(future);
//
//        });
//        for (Future<Void> future : futures) {
//            try {
//                future.get();
//            } catch (Exception e) {
//                LOGGER.error("jvppeteer error", e);
//            }
//        }
    }
}
