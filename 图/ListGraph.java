package com.mj.图;


import com.sun.jdi.Value;

import java.util.*;
import java.util.Map.Entry;

public class ListGraph<V, E> extends Graph<V, E> {
    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private final Set<Edge<V, E>> edges = new HashSet<>();
    private final Comparator<Edge<V, E>> edgeComparator = (e1, e2) -> weightManager.compare(e1.weight, e2.weight);

    public ListGraph() {
        super();
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public void print() {
        vertices.forEach((v, vertex) ->
        {
            System.out.println(v);
        });

        edges.forEach(System.out::println);
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);


    }

    @Override
    public void removeVertex(V v) {
        //TODO 删除点
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) {
            return;
        }
        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }


    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        Vertex<V, E> toVertex = vertices.get(to);
        if (fromVertex == null || toVertex == null) {
            return;
        }
        Edge<V, E> edge = new Edge<V, E>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void bfs(V begin, vertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        HashSet<Vertex<V, E>> set = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        set.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visit(vertex.value)) {
                return;
            }
            ;
            for (Edge<V, E> outEdge : vertex.outEdges) {
                if (!set.contains(outEdge.to)) {
                    queue.offer(outEdge.to);
                    set.add(outEdge.to);
                }
            }
        }

    }

    @Override
    public void dfs(V begin, vertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        Stack<Vertex<V, E>> stack = new Stack<>();
        HashSet<Vertex<V, E>> visited = new HashSet<>();
        stack.push(beginVertex);
        if (visitor.visit(beginVertex.value)) {
            return;
        }
        ;
        visited.add(beginVertex);
        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                stack.push(edge.from);
                stack.push(edge.to);
                if (visitor.visit(edge.to.value)) {
                    return;
                }
                visited.add(edge.to);
                break;
            }
        }
    }

    @Override
    Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return bellmanFord(begin);
    }

    @Override
    Map<V, Map<V, PathInfo<V, E>>> shortestPath() {

        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        //初始化
        for(Edge<V,E> edge:edges){
            Map<V, PathInfo<V, E>> map = paths.get(edge.from.value);
            if(map==null){
                map = new HashMap<>();
                paths.put(edge.from.value, map);
            }
            PathInfo<V, E> pathInfo = new PathInfo<V, E>(edge.weight);
            pathInfo.edgeInfos.add(edge.info());
            map.put(edge.to.value, pathInfo);

        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    if (v1 == v2 || v1 == v3 || v2 == v3) { return; }
                    //v1 -> v2
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) { return; }
                    //v2 ->v3
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) { return; }
                    //v1 -> v3
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);
                    if (path13 == null) { return; }

                    E newWeight = weightManager.add(path12.weight, path23.weight);
                    if(path13!=null&&weightManager.compare(newWeight, path13.weight)>0){
                        return;
                    }
                    if(path13 == null){
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);
                    }
                    path13.weight = newWeight;
                    path13.edgeInfos.clear();
                    path13.edgeInfos.addAll(path12.edgeInfos);
                    path13.edgeInfos.addAll(path23.edgeInfos);
                });
            });
        });
        return paths;
    }

    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        PathInfo<V, E> beginPath = new PathInfo<>();
        beginPath.weight = weightManager.zero();
        selectedPaths.put(begin, beginPath);
        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) {
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath != null) {
                    relaxForBellmanFord(edge, fromPath, selectedPaths);
                }
            }
        }
        for (int i = 0; i < count; i++) {
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath != null) {
                    if (!relaxForBellmanFord(edge, fromPath, selectedPaths)) {
                        System.out.println("有负权环");
                        return null;
                    }
                }
            }
        }
        return selectedPaths;
    }

    private boolean relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {

        //新的
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //旧的
        PathInfo<V, E> oldPath = paths.get(edge.to);
        //更新

        if (oldPath != null && weightManager.compare(oldPath.weight, newWeight) < 0) {
            return false;
        }
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
        return true;
    }

    private HashMap<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }
        HashMap<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        HashMap<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();

        for (Edge<V, E> outEdge : beginVertex.outEdges) {
            PathInfo<V, E> pathInfo = new PathInfo<>();
            pathInfo.weight = outEdge.weight;
            pathInfo.edgeInfos.add(outEdge.info());
            paths.put(outEdge.to, pathInfo);
        }


        while (!paths.isEmpty()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);
            //minVertex outEdges松弛
            for (Edge<V, E> outEdge : minVertex.outEdges) {
                if (selectedPaths.containsKey(outEdge.to.value)) {
                    continue;
                }
                relax(outEdge, minPath, paths);

            }
        }
        selectedPaths.remove(begin);
        return selectedPaths;
    }


    /**
     * @param edge     进行松弛边
     * @param fromPath edge的from的最短路径信息
     * @param paths    存放没有离开桌面的点
     */
    private void relax(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        //新的
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        //旧的
        PathInfo<V, E> oldPath = paths.get(edge.to);
        //更新

        if (oldPath != null && weightManager.compare(oldPath.weight, newWeight) < 0) {
            return;
        }
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());

    }

    /**
     * 找出最小路径
     *
     * @param paths
     * @return
     */
    private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
        Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
        while (it.hasNext()) {
            Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;

//        for (Map.Entry<Vertex<V, E>, E> entry : paths.entrySet()) {
//            E weight = entry.getValue();
//            if (minWeight == null || weightManager.compare(weight, minWeight) < 0) {
//                minVertex = entry.getKey();
//                minWeight = weight;
//            }
//        }
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {

        return kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        HashSet<Vertex<V, E>> addedVertices = new HashSet<>();
        Vertex<V, E> vertex = iterator.next();
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
        int edgeSize = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) {
                continue;
            }
            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);

        }
        return edgeInfos;

    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) {
            return null;
        }
        Set<EdgeInfo<V, E>> set = new HashSet<>();
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((v, vertex) -> uf.makeSet(vertex));
        while (!heap.isEmpty() && set.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (uf.isSame(edge.from, edge.to)) {
                continue;
            }
            set.add(edge.info());
            uf.union(edge.from, edge.to);
        }

        return set;
    }

    @Override
    public List<V> topologicalSort() {
        ArrayList<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        HashMap<Vertex<V, E>, Integer> ins = new HashMap<>();
        vertices.forEach((v, vertex) ->
        {
            int in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            } else {
                ins.put(vertex, in);
            }
        });
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = ins.get(edge.to) - 1;
                if (toIn == 0) {
                    queue.offer(edge.to);
                } else {
                    ins.put(edge.to, toIn);
                }
            }

        }
        return list;
    }

//    @Override
//    public void bfs(V begin) {
//        Vertex<V, E> beginVertex = vertices.get(begin);
//        if (beginVertex == null) {
//            return;
//        }
//        HashSet<Vertex<V, E>> set = new HashSet<>();
//        Queue<Vertex<V, E>> queue = new LinkedList<>();
//        queue.offer(beginVertex);
//        set.add(beginVertex);
//
//        while (!queue.isEmpty()) {
//            Vertex<V, E> vertex = queue.poll();
//            System.out.println(vertex.value);
//            for (Edge<V, E> outEdge : vertex.outEdges) {
//                if (!set.contains(outEdge.to)) {
//                    queue.offer(outEdge.to);
//                    set.add(outEdge.to);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void dfs(V begin) {
//        Vertex<V, E> vertex = vertices.get(begin);
//        if (vertex == null) {
//            return;
//        }
//        dfs(vertex, new HashSet<>());
//    }

//    private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
//        System.out.println(vertex.value);
//        visitedVertices.add(vertex);
//        for (Edge<V, E> edge : vertex.outEdges) {
//            if (visitedVertices.contains(edge.to)) {
//                continue;
//            }
//            dfs(edge.to, visitedVertices);
//        }
//    }

    //非递归
//    private void dfs(Vertex<V, E> beginVertex, Set<Vertex<V, E>> visited) {
//
//        Stack<Vertex<V, E>> stack = new Stack<>();
//
//        stack.push(beginVertex);
//        System.out.println(beginVertex.value);
//        visited.add(beginVertex);
//        while (!stack.isEmpty()) {
//            Vertex<V, E> vertex = stack.pop();
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (visited.contains(edge.to)) {
//                    continue;
//                }
//                stack.push(edge.from);
//                stack.push(edge.to);
//                System.out.println(edge.to.value);
//                visited.add(edge.to);
//                break;
//            }
//        }
//
//    }


    public static class Vertex<V, E> {
        V value;

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }

        //以点为终点的边集合
        Set<Edge<V, E>> inEdges = new HashSet<>();

        @Override
        public boolean equals(Object o) {
            Vertex<V, E> vertex = (Vertex<V, E>) o;
            return Objects.equals(vertex.value, value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        //以点为起点的边集合
        Set<Edge<V, E>> outEdges = new HashSet<>();
    }

    private static class Edge<V, E> {
        //起点
        Vertex<V, E> from;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<V, E>(from.value, to.value, weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        //终点
        Vertex<V, E> to;
        //权重
        E weight;

        @Override
        public boolean equals(Object o) {
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(edge.from, from) && Objects.equals(edge.to, to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

    }
}
