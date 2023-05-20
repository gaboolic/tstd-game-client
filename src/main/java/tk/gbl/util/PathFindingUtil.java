package tk.gbl.util;

import java.io.*;
import java.util.*;

/**
 * Date: 2017/4/5
 * Time: 18:32
 *
 * @author Tian.Dong
 */
public class PathFindingUtil {

    //12001到12003，入口
    static Map<String, Integer> mapEnterMapping = new HashMap<>();
    //12001到12003，代价
    static Map<String, Integer> mapCostMapping = new HashMap<>();
    //12001 - 5 -> map2Id
    static Map<String, Integer> mapId_enter_toMapIdMapping = new HashMap<>();
    //12001对应程序1 2 3 4 5
    static Map<Integer, Integer> mapMapIdMapping = new HashMap<>();
    //1 2 3 4 5对应12001
    static Map<Integer, Integer> mapIdMapMapping = new HashMap<>();
    //12001 -> 涿郡城门
    static Map<Integer, String> mapIdMapNameMapping = new HashMap<>();
    static int[][] path;

    static {
        InputStream is = PathFindingUtil.class.getResourceAsStream("/WarpID.ini");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            int mapCount = 0;
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    String[] infos = line.split("\t");
                    String type = infos[0];
                    if (type.equals("1")) {
                        String map1Id = infos[1];
                        String map2Id = infos[3];
                        int enter = Integer.parseInt(infos[2]);
                        int cost = 1;
                        if (infos.length > 4) {
                            cost = Integer.parseInt(infos[4]);
                        }
                        //地图和地图之间的入口映射
                        if (mapEnterMapping.get(map1Id + "-" + map2Id) == null) {
                            mapEnterMapping.put(map1Id + "-" + map2Id, enter);
                        }
                        //地图和地图之间的代价映射
                        if (mapCostMapping.get(map1Id + "-" + map2Id) == null) {
                            mapCostMapping.put(map1Id + "-" + map2Id, cost);
                        }
                        mapId_enter_toMapIdMapping.put(map1Id + "-" + enter, Integer.parseInt(map2Id));
                    }
                    if (type.equals("1")) {
                        int fromMapId = Integer.parseInt(infos[1]);
                        int toMapId = Integer.parseInt(infos[3]);

                        Integer mappingMapId = mapMapIdMapping.get(fromMapId);
                        if (mappingMapId == null) {
                            mapMapIdMapping.put(fromMapId, mapCount);
                            mapIdMapMapping.put(mapCount, fromMapId);
                            mapCount++;
                        }
                        Integer mappingMapId2 = mapMapIdMapping.get(toMapId);
                        if (mappingMapId2 == null) {
                            mapMapIdMapping.put(toMapId, mapCount);
                            mapIdMapMapping.put(mapCount, toMapId);
                            mapCount++;
                        }
                    }
                    if (type.equals("2")) {
                        int mapId = Integer.parseInt(infos[1]);
                        String name = infos[2];
                        mapIdMapNameMapping.put(mapId, name);
                    }
                }
            }
            path = new int[mapCount][mapCount];
            for (Map.Entry<String, Integer> mapEnterMappingEntry : mapEnterMapping.entrySet()) {
                int cost = 1;
                String map1_map2 = mapEnterMappingEntry.getKey();
                if (mapCostMapping.get(map1_map2) != null) {
                    cost = mapCostMapping.get(map1_map2);
                }
                String map1 = map1_map2.split("-")[0];
                String map2 = map1_map2.split("-")[1];
                int map1Id = Integer.parseInt(map1);
                int map2Id = Integer.parseInt(map2);
                Integer map1MappingId = mapMapIdMapping.get(map1Id);
                Integer map2MappingId = mapMapIdMapping.get(map2Id);
                path[map1MappingId][map2MappingId] = cost;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getMapPath_dfs(int map1Id, int map2Id) {
        int start = mapMapIdMapping.get(map1Id);
        int end = mapMapIdMapping.get(map2Id);
        int[] f = new int[path.length];//父亲
        int[] color = new int[path.length];//颜色
        int[] d = new int[path.length];//距离
        for (int i = 0; i < path.length; i++) {
            d[i] = -1;
            f[i] = -1;
            color[i] = 0;//白色
        }
        d[start] = 0;
        f[start] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (queue.size() != 0) {
            int u = queue.poll();
            for (int v = 0; v < path.length; v++) {
                if (path[u][v] > 0) {
                    if (color[v] == 0) {
                        color[v] = 1;
                        d[v] = d[u] + 1;
                        f[v] = u;
                        queue.add(v);
                    }
                }
            }
            color[u] = 2;
        }
        List<Integer> pathList = new ArrayList<>();
        getPath(f, start, end, pathList);


        List<Integer> enterList = new ArrayList<>();
        if (start == end) {
            return enterList;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pathList.size() - 1; i++) {
            int fromMapMappingId = pathList.get(i);
            int toMapMappingId = pathList.get(i + 1);
            int fromMapId = mapIdMapMapping.get(fromMapMappingId);
            int toMapId = mapIdMapMapping.get(toMapMappingId);
            Integer enter = mapEnterMapping.get(fromMapId + "-" + toMapId);
            stringBuilder.append(fromMapId + "-" + enter + "->" + toMapId + ",");
            enterList.add(enter);
        }
//        System.out.println(stringBuilder);
        return enterList;
    }

    /**
     * public void initialize_single_sourse(g,s) {
     * for each vertex v属于V[G]
     * do d[v] = 无穷
     * pi[v] = null
     * d[s] = 0;
     * }
     * public void relax(u,v,w){
     * if(d[v]>d[u]+w(u,v)){
     * d[v] = d[u]+w(u,v);
     * pi[v] = u;
     * }
     * }
     * <p/>
     * public void dj(){
     * initialize_single_sourse(g,s);
     * s = null;
     * q = V[G];
     * while(q!=null) {
     * <p/>
     * }
     * }
     */
    public static List<Integer> getMapPath(int map1Id, int map2Id) {
        int start = mapMapIdMapping.get(map1Id);
        int end = mapMapIdMapping.get(map2Id);
        if (start == end) {
            return new ArrayList<>();
        }
        //前趋
        int[] p = new int[mapMapIdMapping.size()];
        boolean[] finalArray = new boolean[mapMapIdMapping.size()];
        //每个点到起点的距离
        int[] d = new int[mapMapIdMapping.size()];
        for (int v = 0; v < mapMapIdMapping.size(); v++) {
            d[v] = path[start][v];
            if (d[v] == 0) {
                d[v] = 10000000;
            }
        }
        d[start] = 0;
        finalArray[start] = true;

        int k = 0;
        for (int v = 0; v < mapMapIdMapping.size(); v++) {
            int min = 1000000;
            for (int w = 0; w < mapMapIdMapping.size(); w++) {
                if (!finalArray[w] && d[w] < min) {
                    k = w;
                    min = d[w];
                }
            }
            finalArray[k] = true;
            for (int w = 0; w < mapMapIdMapping.size(); w++) {
                int cost = path[k][w];
                if(cost == 0) {
                    cost = 1000000;
                }
                if (!finalArray[w] && (min + cost < d[w])) {
                    //说明找到了更短的路径
                    d[w] = min + cost;
                    p[w] = k;
                }
            }
        }

        /**
         * 此时的P数组为 {0,0,1,4,2,4,3,6,7}：
         * 这个P数组值可能难理解一些。比如P[8]=7，它表示要查看V0到V8的最短路径时，顶点V8的前驱是V7；
         * 再由P[7]=6表示要查看V0到V7的最短路径时，顶点V7的前驱是V6，同理，P[6]=3表示V6的前驱是V3。
         * 这样就可以得到：V0到V8最短路径为V8<-V7<-V6<-V3<-V4<-V2<-V1<-V0
         * 即就是： V0->V1->V2->V4->V3->V6->V7->V8。
         */
        List<Integer> enterListTemp = new ArrayList<>();
        while (true) {
            int prev = p[end];
            if(prev == 0) {
                break;
            }
            int realPrevMapId = mapIdMapMapping.get(prev);
            int realEndMapId = mapIdMapMapping.get(end);
            int enter = mapEnterMapping.get(realPrevMapId + "-" + realEndMapId);
            enterListTemp.add(enter);
            end = prev;
            if(p[end] == start) {
                break;
            }
        }
        int realStartMapId = mapIdMapMapping.get(start);
        int realEndMapId = mapIdMapMapping.get(end);
        int enter = mapEnterMapping.get(realStartMapId + "-" + realEndMapId);
        enterListTemp.add(enter);

        List<Integer> enterList = new ArrayList<>();
        for (int i = enterListTemp.size() - 1; i >= 0; i--) {
            enterList.add(enterListTemp.get(i));
        }


        return enterList;
    }

    public static void getPath(int[] f, int s, int v, List<Integer> pathList) {
        if (v == s) {
            pathList.add(s);
            return;
        }
        if (f[v] == -1) {
            return;
        }
        getPath(f, s, f[v], pathList);
        pathList.add(v);
    }

    public static String showPath(Integer currentMapId, List<Integer> enterList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer enter : enterList) {
            String key = currentMapId + "-" + enter;
            Integer toMapId = mapId_enter_toMapIdMapping.get(key);
            if (toMapId == null) {
                System.out.println("null!!!" + key);
            }
            stringBuilder.append(key);
            stringBuilder.append("->");
            stringBuilder.append(toMapId);
            stringBuilder.append(",");
            currentMapId = toMapId;
        }
        return stringBuilder.toString();
    }

    public static String getMapName(int mapId) {
        String name = mapIdMapNameMapping.get(mapId);
        if (name != null) {
            return name;
        }
        return "无地图名称";
    }


}
