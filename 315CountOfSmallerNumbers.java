package com.company;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private class Node {
        private final int value;
        private int count;

        public Node(int value) {
            this.value = value;
            this.count = 0;
        }

        public int getValue() {
            return value;
        }

        public int getCount() {
            return count;
        }

        public void incCount(int c) {
            count += c;
        }
    }

    private void mergeSort(List<Node> list) {
        if (list.size() <= 1) {
            return;
        }

        List<Node> list1 = new ArrayList<Node>(list.size() / 2);
        List<Node> list2 = new ArrayList<Node>(list.size() - list.size() / 2);
        int i = 0;
        while (i < list.size() / 2) {
            list1.add(list.get(i++));
        }
        while (i < list.size()) {
            list2.add(list.get(i++));
        }
        mergeSort(list1);
        mergeSort(list2);

        int count = 0;
        i = 0;
        int j = 0;
        list.clear();
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).getValue() <= list2.get(j).getValue()) {
                list1.get(i).incCount(count);
                list.add(list1.get(i++));
            } else {
                count++;
                list.add(list2.get(j++));
            }
        }

        while (i < list1.size()) {
            list1.get(i).incCount(count);
            list.add(list1.get(i++));
        }

        while (j < list2.size()) {
            list.add(list2.get(j++));
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < nums.length; ++i) {
            nodes.add(new Node(nums[i]));
        }

        List<Node> nodesCopy = new ArrayList<Node>(nodes);
        mergeSort(nodesCopy);

        List results = new ArrayList<Integer>();
        for (Node n : nodes) {
            results.add(n.getCount());
        }
        return results;
    }
}
