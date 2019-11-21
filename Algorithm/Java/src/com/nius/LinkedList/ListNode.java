package com.nius.LinkedList;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static void show(ListNode head) {
        System.out.println();
        ListNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val + "\t");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static ListNode createList(int[] arr) {

        ListNode head = null;
        ListNode cur = null;
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (cur == null) {
                head = cur = new ListNode(val);
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
        }
        return head;
    }
}
