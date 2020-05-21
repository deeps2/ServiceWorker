package com.example.unacademyserviceworker;

public interface Task<T> {
    T onExecuteTask();
    void onTaskComplete(T task);
}
