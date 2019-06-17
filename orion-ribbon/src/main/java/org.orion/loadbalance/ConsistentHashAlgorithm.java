package org.orion.loadbalance;


import lombok.NonNull;
import org.springframework.util.Assert;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性Hash算法
 *
 * @author leo
 */
public class ConsistentHashAlgorithm {

    /**
     * 创建新的虚拟节点列表
     *
     * @param servers
     * @return
     */
    public static SortedMap<Integer, String> newVirtualNodes(@NonNull List<String> servers, int vNodeNum) {
        Assert.isTrue(vNodeNum > 0, "number of virtual nodes should be positive");
        SortedMap<Integer, String> vNodes = new TreeMap<>();
        servers.forEach(str -> {
            for (int i = 0; i < vNodeNum; i++) {
                String virtualNodeName = str + "&&VN" + i;
                int hash = getHash(virtualNodeName);
//                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                vNodes.put(hash, virtualNodeName);
            }
        });
        return vNodes;
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    /**
     * 得到应当路由到的结点
     */
    public static String getServer(String node, SortedMap<Integer, String> virtualNodes) {
        // 得到带路由的结点的Hash值
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap =
                virtualNodes.tailMap(hash);
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        // 返回对应的虚拟节点名称，这里字符串稍微截取一下
        String virtualNode = subMap.get(i);
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

//    public static void main(String[] args) {
//        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
//        for (int i = 0; i < nodes.length; i++)
//            System.out.println("[" + nodes[i] + "]的hash值为" +
//                    getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
//    }
}