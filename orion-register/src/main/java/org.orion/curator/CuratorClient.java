package org.orion.curator;


import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.orion.common.ZKconfig;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author :仓颉
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Slf4j
public class CuratorClient {

    private final int CONNECT_TIMEOUT = 15000;
    private final int RETRY_TIME = Integer.MAX_VALUE;
    private final int RETRY_INTERVAL = 1000;
    private CuratorFramework curator;
    private volatile static CuratorClient instance;

    private final Set<ZkStateListener> stateListeners = new CopyOnWriteArraySet<ZkStateListener>();

    public void addStateListener(ZkStateListener listener) {
        stateListeners.add(listener);
    }


    public CuratorFramework getCurator() {
        return curator;
    }

    private CuratorFramework newCurator(String zkServers) {
        return CuratorFrameworkFactory.builder().connectString(zkServers)
                .retryPolicy(new RetryNTimes(RETRY_TIME, RETRY_INTERVAL))
                .connectionTimeoutMs(CONNECT_TIMEOUT).build();
    }

    private CuratorClient(String zkServers) {
        if (curator == null) {
            curator = newCurator(zkServers);
            curator.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                    if (connectionState == ConnectionState.LOST) {
                        //丢失链接
                    }
                    if (connectionState == ConnectionState.CONNECTED) {
                        //链接新建
                    }
                    if (connectionState == ConnectionState.RECONNECTED) {
                        //重新链接
                        for (ZkStateListener s : stateListeners) {
                            s.reconnected();
                        }
                    }
                }
            });
            curator.start();
        }
    }

    public static CuratorClient getInstance() {
        if (instance == null) {
            synchronized (CuratorClient.class) {
                if (instance == null) {
                    instance = new CuratorClient(ZKconfig.ZK_REGISTER_ADDRESS);
                }
            }
        }
        return instance;
    }

    /**
     * 写数据：/docker/jobcenter/ioc/app/app0..../app1...../app2
     *
     * @param path
     * @param content
     * @return 返回真正写到的路径
     * @throws Exception
     */
    public String writeNode(String path, String content, CreateMode mode, boolean flag) throws Exception {
        if (!checkPathIsNull(path) && flag) {
            deleteNode(path);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        String writePath = curator.create().creatingParentsIfNeeded().withMode(mode)
                .forPath(sb.toString(), content.getBytes("utf-8"));
        return writePath;
    }

    /**
     * 删除一个节点，并递归删除其所有子节点
     *
     * @param path
     */
    public void deleteNode(String path) {
        try {
            curator.delete().deletingChildrenIfNeeded().forPath(path);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
//
//    /**
//     * 读取子节点的value
//     *
//     * @return
//     */
//    public String readNodeValue(String parentPath, String subPath) {
//        Map<String, String> cacheMap = zkCacheMap.get(parentPath);
//        if (cacheMap != null && cacheMap.size() > 0) {
//            return cacheMap.get(subPath);
//        }
//        try {
//            if (curator.checkExists().forPath(parentPath + subPath) == null) {
//                return null;
//            } else {
//                byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, subPath)).forPath(parentPath + subPath);
//                String value = new String(b, "utf-8");
//                return value;
//            }
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//
//
//    /**
//     * 随机读取一个path子路径
//     * 先从cache中读取，如果没有，再从zookeeper中查询
//     *
//     * @param path
//     * @return
//     * @throws Exception
//     */
//    public String readRandom(String path) throws Exception {
//        String parentPath = path;
//        Map<String, String> cacheMap = zkCacheMap.get(path);
//        if (cacheMap != null && cacheMap.size() > 0) {
//            return getRandomValue4Map(cacheMap);
//        }
//        if (curator.checkExists().forPath(path) == null) {
//            return null;
//        } else {
//            cacheMap = new HashMap<String, String>();
//            List<String> list = curator.getChildren().usingWatcher(new ZKWatcher(parentPath, path)).forPath(parentPath);
//            if (list == null || list.size() == 0) {
//                return null;
//            }
//            Random random = new Random();
//            String child = list.get(random.nextInt(list.size()));
//            return child;
////            path = path + "/" + child;
////            byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, path)).forPath(path);
////            String value = new String(b, "utf-8");
////            if (StringUtils.isNotEmpty(value)) {
////                cacheMap.put(path, value);
////                zkCacheMap.put(parentPath, cacheMap);
////            }
////            return value;
//        }
//    }
//
//
//    /**
//     * 读取path下所有子路径下的内容
//     * 先从map中读取，如果不存在，再从zookeeper中查询
//     *
//     * @param path
//     * @return
//     * @throws Exception
//     */
//    public List<String> readAll(String path) throws Exception {
//        String parentPath = path;
//        Map<String, String> cacheMap = zkCacheMap.get(parentPath);
//        List<String> list = new ArrayList<>();
//        if (cacheMap != null) {
//            list.addAll(cacheMap.values());
//            return list;
//        }
//        if (curator.checkExists().forPath(path) == null) {
//            return null;
//        } else {
//            cacheMap = new HashMap<>();
//            List<String> children = curator.getChildren().usingWatcher(new ZKWatcher(parentPath, path)).forPath(path);
//            if (children == null || children.size() == 0) {
//                return null;
//            } else {
//                String basePath = path;
//                for (String child : children) {
//                    path = basePath + "/" + child;
//                    byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, path))
//                            .forPath(path);
//                    String value = new String(b, "utf-8");
//                    if (StringUtils.isNotBlank(value)) {
//                        list.add(value);
//                        cacheMap.put(path, value);
//                    }
//                }
//            }
//            zkCacheMap.put(parentPath, cacheMap);
//            return list;
//        }
//    }
//
//    /**
//     * 获取路径下的所有子路径
//     *
//     * @param path
//     * @return
//     */
//    public List<String> getChildren(String path) throws Exception {
//        if (curator.checkExists().forPath(path) == null) {
//            return null;
//        } else {
//            List<String> children = curator.getChildren().forPath(path);
//            return children;
//        }
//    }
//

    /**
     * 判断节点是否存在
     *
     * @param path
     * @return
     */
    public boolean checkPathIsNull(String path) {
        try {
            if (curator.checkExists().forPath(path) != null) {
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return true;
    }

    public void close() {
        if (curator != null) {
            curator.close();
            curator = null;
        }
    }

//    /**
//     * 随机获取Map中的一个值
//     *
//     * @param map
//     * @return
//     */
//    private String getRandomValue4Map(Map<String, String> map) {
//        Object[] values = map.values().toArray();
//        Random rand = new Random();
//        return values[rand.nextInt(values.length)].toString();
//    }
//
//    /**
//     * zookeeper监听节点数据变化
//     */
//    private class ZKWatcher implements CuratorWatcher {
//        private String parentPath;
//        private String path;
//
//        public ZKWatcher(String parentPath, String path) {
//            this.parentPath = parentPath;
//            this.path = path;
//        }
//
//        @Override
//        public void process(WatchedEvent event) throws Exception {
//            Map<String, String> cacheMap = zkCacheMap.get(parentPath);
//            if (cacheMap == null) {
//                cacheMap = new HashMap<String, String>();
//            }
//            if (event.getType() == Watcher.Event.EventType.NodeDataChanged
//                    || event.getType() == Watcher.Event.EventType.NodeCreated) {
//                byte[] data = curator.getData().
//                        usingWatcher(this).forPath(path);
//                cacheMap.put(path, new String(data, "utf-8"));
//            } else if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
//                cacheMap.remove(path);
//            } else if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
//                //子节点发生变化，重新进行缓存
//                cacheMap.clear();
//                List<String> children = curator.getChildren().usingWatcher(new ZKWatcher(parentPath, path)).forPath(path);
//                if (children != null && children.size() > 0) {
//                    for (String child : children) {
//                        String childPath = parentPath + "/" + child;
//                        byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, childPath))
//                                .forPath(childPath);
//                        String value = new String(b, "utf-8");
//                        if (StringUtils.isNotBlank(value)) {
//                            cacheMap.put(childPath, value);
//                        }
//                    }
//                }
//            }
//            zkCacheMap.put(parentPath, cacheMap);
//        }
//    }


}
