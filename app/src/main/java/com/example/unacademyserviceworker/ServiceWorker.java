package com.example.unacademyserviceworker;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceWorker<T> {
    private String workerName;
    private ExecutorService executorService;
    private Handler mainHandler;
    private LinkedBlockingQueue<Task<T>> taskQueue;

    public ServiceWorker(String workerName) {
        this.workerName = workerName;
        executorService = Executors.newSingleThreadExecutor(); //executes task on single worker thread as it uses Executors.newSingleThreadExecutor()
        mainHandler = new Handler(Looper.getMainLooper());     //for dispatching the result to UI thread when background work is done
        taskQueue = new LinkedBlockingQueue<>();               //add the tasks which are submitted to this taskQueue (FIFO order). BlockingQueue implementations such as LinkedBlockingQueue are thread safe
    }

    public void addTask(Task<T> task) {
        taskQueue.add(task);

        executorService.execute(() -> {
            Task<T> taskToBeExecuted = taskQueue.poll();   //remove task from the front of the taskQueue and execute it
            if (taskToBeExecuted != null) {
                T t = taskToBeExecuted.onExecuteTask();
                onBackgroundTaskExecutionFinished(taskToBeExecuted, t);
            }
        });
    }

    private void onBackgroundTaskExecutionFinished(Task<T> taskToBeExecuted, T t) {
        mainHandler.post(() -> taskToBeExecuted.onTaskComplete(t));
    }
}
