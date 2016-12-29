package com.guavademo.liam;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * Created by guavademo on 2016/1/20.
 */
public class OrderingDemo {

    private static Ordering<Node> nodeOrdering = Ordering.natural().reverse().onResultOf(new Function<Node, Integer>() {
        public Integer apply(Node input) {
            return input.getFirst();
        }
    }).compound(Ordering.natural().onResultOf(new Function<Node, Integer>() {
        public Integer apply(Node input) {
            return input.getSecond();
        }
    }));

    public static void main(String[] args) {
        Node n1 = new Node(13, 12);
        Node n2 = new Node(11, 10);
        List<Node> nodes = Lists.newArrayList(n1, n2);

        List<Node> sortedNodes = nodeOrdering.sortedCopy(nodes);
        for (Node sortedNode : sortedNodes) {
            System.out.println(sortedNode);
        }
        System.out.println("=====================================");

        Node n3 = new Node(11, 12);
        Node n4 = new Node(11, 10);
        List<Node> nodes2 = Lists.newArrayList(n3, n4);

        List<Node> sortedNodes2 = nodeOrdering.sortedCopy(nodes2);
        for (Node sortedNode : sortedNodes2) {
            System.out.println(sortedNode);
        }
    }

    private static class Node {
        private Integer first;
        private Integer second;

        public Node(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public void setFirst(Integer first) {
            this.first = first;
        }

        public Integer getSecond() {
            return second;
        }

        public void setSecond(Integer second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return "Node{" + "first=" + first + ", second=" + second + '}';
        }
    }
}
