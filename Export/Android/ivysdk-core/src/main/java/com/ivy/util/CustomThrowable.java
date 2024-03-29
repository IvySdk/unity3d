package com.ivy.util;

import android.os.Debug;
import android.util.Log;

public class CustomThrowable extends Throwable {
    private StackTraceElement[] stackTrace;

    public CustomThrowable(String message, StackTraceElement[] stackTrace) {
        super(message);
        this.stackTrace = stackTrace;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public static String parseClassName(String line) {
        return line.substring(0, line.indexOf(':'));
    }

    public static String parseMethodName(String line) {
        return line.substring(line.indexOf(':') + 1);
    }

    public static String parseFileName(String line) {
        return "";
    }

    public static int parseLineNumber(String line) {
        return 0;
    }

    public static StackTraceElement[] convertUnityStackTrace(String unityStackTrace) {
        if(unityStackTrace == null) return null;
        // 分割Unity堆栈跟踪到行
        String[] lines = unityStackTrace.split("\n");

        // 创建一个新的StackTraceElement数组
        StackTraceElement[] stackTrace = new StackTraceElement[lines.length];

        // 遍历每一行
        for (int i = 0; i < lines.length; i++) {
            // 解析行中的类名，方法名，文件名和行号
            String className = parseClassName(lines[i]);
            String methodName = parseMethodName(lines[i]);
            String fileName = parseFileName(lines[i]);
            int lineNumber = parseLineNumber(lines[i]);

            // 创建一个新的StackTraceElement并添加到数组中
            stackTrace[i] = new StackTraceElement(className, methodName, fileName, lineNumber);
        }

        return stackTrace;
    }

    public static CustomThrowable convertUnityStackTraceToAndroid(String title, String unityStackTrace)
    {
        //Log.e("DDD", title + ":" + unityStackTrace);
        StackTraceElement[] st = convertUnityStackTrace(unityStackTrace);
        return new CustomThrowable(title, st);
    }
}
