package com.mj.图;

import java.util.*;

public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;

    abstract int edgeSize();

    abstract int verticesSize();

    abstract void addVertex(V v);

    abstract void addEdge(V from, V to);

    abstract void addEdge(V from, V to, E weight);

    abstract void removeVertex(V v);

    abstract void removeEdge(V from, V to);

    abstract void bfs(V begin, vertexVisitor<V> visitor);

    abstract void dfs(V begin, vertexVisitor<V> visitor);

    abstract Map<V, PathInfo<V, E>> shortestPath(V begin);

    abstract Map<V, Map<V, PathInfo<V, E>>> shortestPath();

    abstract Set<EdgeInfo<V, E>> mst();

    abstract List<V> topologicalSort();

    interface vertexVisitor<V> {
        boolean visit(V v);
    }

    public Graph() {
    }

    public static class PathInfo<V, E> {
        protected E weight;


        public PathInfo(E weight) {
            this.weight = weight;
        }

        public PathInfo(E weight, List<EdgeInfo<V, E>> edgeInfos) {
            this.weight = weight;
            this.edgeInfos = edgeInfos;
        }

        public PathInfo() {
        }

        @Override
        public String toString() {
            return
                    "权重=" + weight +
                    ", 最短路径=" + edgeInfos;
        }

        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();
    }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    interface WeightManager<E> {
        int compare(E w1, E w2);

        E add(E w1, E w2);

        E zero();
    }

    static class EdgeInfo<V, E> {
        @Override
        public String toString() {
            return
                    from +
                    "->" + to +
                    ":" + weightE;
        }

        V from;

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeightE() {
            return weightE;
        }

        public void setWeightE(E weightE) {
            this.weightE = weightE;
        }

        V to;
        E weightE;

        public EdgeInfo(V from, V to, E weightE) {
            this.from = from;
            this.to = to;
            this.weightE = weightE;
        }
    }

}
