package com.nius;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode-cn.com/problems/course-schedule-ii/
public class _210_课程表II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        List<Integer> results = new ArrayList<>();   // 结果
        int[] inDegrees = new int[numCourses];       // 每个课程入度表
        List<List<Integer>> edges = new ArrayList<>(); // 每个课程所能到达的顶点
        Queue<Integer> queue = new LinkedList<>();

        /// 初始化
        // 1.初始化edges
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        // 2.初始化入度表inDegrees、edges
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            inDegrees[prerequisite[0]]++;
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 首先找到入度为0的课程入队
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // 存入结果
        while (!queue.isEmpty()) {
            int course = queue.poll();
            results.add(course);

            List<Integer> couses = edges.get(course);
            for (int c : couses) {
                inDegrees[c]--;
                if (inDegrees[c] == 0) {
                    queue.offer(c);
                }
            }
        }

        if (results.size() != numCourses) {
            // 判断是否有拓扑排序
            System.out.println("没有topo排序");
            return new int[0];
        }

        int[] res = new int[numCourses];
        for (int i = 0; i < results.size(); i++) {
            res[i] = results.get(i);
        }
        return res;
    }

    public static void main(String[] args) {


        int[][] courses = {{0,1},{1,0}}; // 2
//        int[][] courses = {{1,0}}; // 2
//        int[][] courses = {{1,0},{2,0},{3,2},{3,1}}; // 4
        int[] results = new _210_课程表II().findOrder(2, courses);
        for (int i = 0; i < results.length; i++) {
            System.out.println(i);
        }
    }
}
