//
//  _141_ 环形链表.m
//  链表
//
//  Created by 曾维俊 on 2019/5/6.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "_141_ 环形链表.h"
#import "ListNode.h"

/*
 https://leetcode-cn.com/problems/linked-list-cycle/
 给定一个链表，判断链表中是否有环。
 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 
 解题思想：快慢指针
 */

@implementation _141______

BOOL hasCycle(ListNode *head) {
    if (head == nil || head.next == nil) return NO;
    
    ListNode *slow = head;
    ListNode *fast = head.next;
    
    while (fast != nil && fast.next != nil) {
        if (slow.val == fast.val) return YES;
        slow = slow.next;
        fast = fast.next.next; // 若在java中，必须保证first.next不为空，否则空指针异常
    }
    return NO;
}

+ (void)main {
    NSMutableArray *numbers = @[].mutableCopy;
    for (NSInteger i = 0; i < 10; i++) {
        [numbers addObject:@(i)];
    }
    ListNode *head = createList(numbers);
    BOOL ret = hasCycle(head);
    NSLog(@"ret:%d", ret);
    
    ListNode *chead = createCycleList(numbers, 2);
    BOOL cret = hasCycle(chead);
    NSLog(@"ret:%d", cret);
    
    // 一个元素的环
    NSMutableArray *numbers1 = @[].mutableCopy;
    for (NSInteger i = 0; i < 1; i++) {
        [numbers1 addObject:@(i)];
    }
    ListNode *head1 = createCycleList(numbers1, 0);
    BOOL ret1 = hasCycle(head1);
    NSLog(@"ret:%d", ret1);
 }

@end
