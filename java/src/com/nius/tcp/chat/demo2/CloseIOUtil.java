package com.nius.tcp.chat.demo2;

import java.io.Closeable;
import java.io.IOException;

public class CloseIOUtil {
    public static void closeIOStream(Closeable...ios) {
        for (Closeable tmp: ios) {
            if (null != tmp) {
                try {
                    tmp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
