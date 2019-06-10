package org.orion.curator;


import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.text.MessageFormat;
import java.util.List;

/**
 * @ClassName CuratorManager
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/17 19:07
 **/
@Slf4j
public class CuratorManager {


    //创建连接实例
    static CuratorClient curatorClient = CuratorClient.getInstance();

    private static CuratorFramework client = curatorClient.getCurator();

    /**
     * 创建永久Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return java.lang.String 返回创建成功的节点路径
     * @since 1.0.0
     */
    public static String createPersistentNode(String nodePath, String nodeValue) {
        try {
            return client.create().creatingParentsIfNeeded()
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            log.error(MessageFormat.format("创建永久Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
        }
        return null;
    }

    /**
     * 创建永久有序Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return java.lang.String 返回创建成功的节点路径
     * @since 1.0.0
     */
    public static String createSequentialPersistentNode(String nodePath, String nodeValue) {
        try {
            return client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            log.error(MessageFormat.format("创建永久有序Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
        }
        return null;
    }

    /**
     * 创建临时Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return java.lang.String 返回创建成功的节点路径
     * @since 1.0.0
     */
    public static String createEphemeralNode(String nodePath, String nodeValue) {
        try {
            return client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            log.error(MessageFormat.format("创建临时Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
        }
        return null;
    }

    /**
     * 创建临时有序Zookeeper节点
     *
     * @param nodePath  节点路径（如果父节点不存在则会自动创建父节点），如：/curator
     * @param nodeValue 节点数据
     * @return java.lang.String 返回创建成功的节点路径
     * @since 1.0.0
     */
    public static String createSequentialEphemeralNode(String nodePath, String nodeValue) {
        try {
            return client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(nodePath, nodeValue.getBytes());
        } catch (Exception e) {
            log.error(MessageFormat.format("创建临时有序Zookeeper节点失败,nodePath:{0},nodeValue:{1}", nodePath, nodeValue), e);
        }
        return null;
    }

    /**
     * 检查Zookeeper节点是否存在
     *
     * @param nodePath 节点路径
     * @return boolean 如果存在则返回true
     * @since 1.0.0
     */
    public static boolean checkExists(String nodePath) {
        try {
            Stat stat = client.checkExists().forPath(nodePath);

            return stat != null;
        } catch (Exception e) {
            log.error(MessageFormat.format("检查Zookeeper节点是否存在出现异常,nodePath:{0}", nodePath), e);
        }
        return false;
    }

    /**
     * 获取某个Zookeeper节点的所有子节点
     *
     * @param nodePath 节点路径
     * @return java.util.List<java.lang.String> 返回所有子节点的节点名
     * @since 1.0.0
     */
    public static List<String> getChildren(String nodePath) {
        try {
            return client.getChildren().forPath(nodePath);
        } catch (Exception e) {
            log.error(MessageFormat.format("获取某个Zookeeper节点的所有子节点出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    /**
     * 获取某个Zookeeper节点的数据
     *
     * @param nodePath 节点路径
     * @return java.lang.String
     * @since 1.0.0
     */
    public static String getData(String nodePath) {
        try {
            return new String(client.getData().forPath(nodePath));
        } catch (Exception e) {
            log.error(MessageFormat.format("获取某个Zookeeper节点的数据出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    /**
     * 设置某个Zookeeper节点的数据
     *
     * @param nodePath 节点路径
     * @since 1.0.0
     */
    public static void setData(String nodePath, String newNodeValue) {
        try {
            client.setData().forPath(nodePath, newNodeValue.getBytes());
        } catch (Exception e) {
            log.error(MessageFormat.format("设置某个Zookeeper节点的数据出现异常,nodePath:{0}", nodePath), e);
        }
    }

    /**
     * 删除某个Zookeeper节点
     *
     * @param nodePath 节点路径
     * @since 1.0.0
     */
    public static void delete(String nodePath) {
        try {
            client.delete().guaranteed().forPath(nodePath);
        } catch (Exception e) {
            log.error(MessageFormat.format("删除某个Zookeeper节点出现异常,nodePath:{0}", nodePath), e);
        }
    }

    /**
     * 级联删除某个Zookeeper节点及其子节点
     *
     * @param nodePath 节点路径
     * @since 1.0.0
     */
    public static void deleteChildrenIfNeeded(String nodePath) {
        try {
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(nodePath);
        } catch (Exception e) {
            log.error(MessageFormat.format("级联删除某个Zookeeper节点及其子节点出现异常,nodePath:{0}", nodePath), e);
        }
    }

    /**
     * <p><b>注册节点监听器</b></p>
     * NodeCache: 对一个节点进行监听，监听事件包括指定路径的增删改操作
     *
     * @param nodePath 节点路径
     * @return void
     * @since 1.0.0
     */
    public static NodeCache registerNodeCacheListener(String nodePath) {
        try {
            //1. 创建一个NodeCache
            NodeCache nodeCache = new NodeCache(client, nodePath);

            //2. 添加节点监听器
            nodeCache.getListenable().addListener(() -> {
                ChildData childData = nodeCache.getCurrentData();
                if (childData != null) {
                    System.out.println("Path: " + childData.getPath());
                    System.out.println("Stat:" + childData.getStat());
                    System.out.println("Data: " + new String(childData.getData()));
                }
            });

            //3. 启动监听器
            nodeCache.start();

            //4. 返回NodeCache
            return nodeCache;
        } catch (Exception e) {
            log.error(MessageFormat.format("注册节点监听器出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    /**
     * <p><b>注册子目录监听器</b></p>
     * PathChildrenCache：对指定路径节点的一级子目录监听，不对该节点的操作监听，对其子目录的增删改操作监听
     *
     * @param nodePath 节点路径
     * @param listener 监控事件的回调接口
     * @return org.apache.curator.framework.recipes.cache.PathChildrenCache
     * @since 1.0.0
     */
    public static PathChildrenCache registerPathChildListener(String nodePath, PathChildrenCacheListener listener) {
        try {
            //1. 创建一个PathChildrenCache
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, nodePath, true);

            //2. 添加目录监听器
            pathChildrenCache.getListenable().addListener(listener);

            //3. 启动监听器
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

            //4. 返回PathChildrenCache
            return pathChildrenCache;
        } catch (Exception e) {
            log.error(MessageFormat.format("注册子目录监听器出现异常,nodePath:{0}", nodePath), e);
        }
        return null;
    }

    /**
     * <p><b>注册目录监听器</b></p>
     * TreeCache：综合NodeCache和PathChildrenCahce的特性，可以对整个目录进行监听，同时还可以设置监听深度
     *
     * @param nodePath 节点路径
     * @param maxDepth 自定义监控深度
     * @param listener 监控事件的回调接口
     * @return org.apache.curator.framework.recipes.cache.TreeCache
     * @since 1.0.0
     */
    public static TreeCache registerTreeCacheListener(String nodePath, int maxDepth, TreeCacheListener listener) {
        try {
            //1. 创建一个TreeCache
            TreeCache treeCache = TreeCache.newBuilder(client, nodePath)
                    .setCacheData(true)
                    .setMaxDepth(maxDepth)
                    .build();

            //2. 添加目录监听器
            treeCache.getListenable().addListener(listener);

            //3. 启动监听器
            treeCache.start();

            //4. 返回TreeCache
            return treeCache;
        } catch (Exception e) {
            log.error(MessageFormat.format("注册目录监听器出现异常,nodePath:{0},maxDepth:{1}", nodePath), e);
        }
        return null;
    }


}