package org.orion.utils;

import com.zhangmen.common.BizException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @ClassName IPUtils
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/16 11:35
 **/

public class IPUtils {

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        //返回站点本地地址
                        if (ip.isSiteLocalAddress() && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new BizException("IP地址获取失败" + e.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(getIpAddress());
    }
}
