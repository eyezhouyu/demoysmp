package com.wonders.common.wechat.decode;

import java.util.ArrayList;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description :
 */
class ByteGroup {

    private ArrayList<Byte> byteContainer = new ArrayList<>();

    /**
     * toBytes
     * @date 2019/5/8
     * 
     * @return byte[]
     **/
    byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    /**
     * addBytes
     * @date 2019/5/8
     * @param bytes bytes
     **/
    void addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
    }

    /**
     * size
     * @date 2019/5/8
     * 
     * @return int
     **/
    int size() {
        return byteContainer.size();
    }
}
