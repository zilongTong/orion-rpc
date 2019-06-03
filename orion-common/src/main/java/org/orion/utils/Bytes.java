package org.orion.utils;

/**
 * @ClassName Bytes
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/21 14:31
 **/
public class Bytes {
    public static String substring(String src, int start_idx, int end_idx) {
        byte[] b = src.getBytes();
        String tgt = "";
        for (int i = start_idx; i <= end_idx; i++) {
            tgt += (char) b[i];
        }
        return tgt;
    }
}
