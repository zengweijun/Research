//
//  _206_反转链表.m
//  链表
//
//  Created by 曾维俊 on 2019/5/6.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "_206_反转链表.h"
#import "ListNode.h"

/**
 单链表反转
 https://leetcode-cn.com/problems/reverse-linked-list/
 
 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
 
 递归+迭代实现
 
 迭代：while循环实现
 
 递归：函数嵌套调用
 过程分析:head->1->2->3->NULL
 思路为：
    1.当前函数栈保存head后，让head往后移动，重复此操作，知道head移动到最后一个
    2.由于函数栈是嵌套关系，最后的head先出栈，依次往前，将出栈后的head返回给上一个
    3.每出一次栈，会对外一层栈中的头两个节点执行反转操作
        3.1 newHead->5->4->null
        3.2 head->3->4->5->null
        3.3 head->3->4->3 让头节点的下一个节点指向头结点，导致newHead跟着变化newHead->5->4->3，此时实际上是:
            newHead->5->4->3
            head->3->4->3（3和4连个节点其实是一个环，下一步需要断掉该环）
        3.4 head->3->null（断掉环），同时newHead会变为newHead->5->4->3->null
 
    4. 步骤3会不断执行，直到最外层栈结束，返回newHead即为翻转后的结果
 
    【该思路巧妙运用栈先进后出原则，让head向后移动开栈的之前记住当期head，实现】
 
 
 
   1.开栈帧1
    {
        head->1->2->3->NULL（参数）
        if (不满足) ...往下执行

            2.开栈帧2
            {
                head->2->3->NULL（参数）
                if (不满足) ...往下执行
 
                    3.开栈帧3
                     {
                        head->3->NULL（参数）
                        if (满足 head.next == NULL) 直接return 返回 head->3->NULL
                     } 4.栈帧3销毁（只有一个元素，没做翻转）
 
 
                5.来到这里
                5.1 此时newHead就是栈帧3的返回值：newHead->3->NULL
                5.2 此时的head->2->3->NULL
 
                head.next.next = head; ==> 2->3->2 (此时结构 head->2->3->2,==> newHead->3->2，即有两个指针同时指向3)
                head.next = nil; ==>断掉head中2指向3的指针（断掉循环结构），此时head->2->NULL,newHead->3->2->NULL
                return 函数返回：newHead->3->2->NULL
            } 6.栈帧2销毁（只有2个元素，翻转，元素为链表最后两个元素，即实现最后两个元素的翻转）
 
        6.来到这里
        6.1 此时newHead就是栈帧2的返回值：newHead->3->2->NULL
        6.2 head->1->2->3->NULL
 
        head.next.next = head;  ==> 1->2->1 (此时结构 head->1->2->1,==> newHead->3->2->1，即有两个指针同时指向3)
        head.next = nil; ==>断掉head中1指向2的指针（断掉循环结构），此时head->1->NULL,newHead->3->2->1->NULL
        return 函数返回：newHead->3->2->NULL（只有2个元素，翻转，次元素为链表最后两个元素，即实现最后两个元素的翻转）
    }7.栈帧1销毁（实现前两个元素翻转，即实现整个链表的翻转）
 */

// 递归
ListNode *re_reverseList(ListNode *head) {
    if (head == nil || head.next == nil) return head;
    
    // 使head向后移动，并开辟新的栈帧（新栈帧获取到的head是向后移动后的值）
    ListNode *newHead = re_reverseList(head.next);
    
    // 使用当前栈帧中的head
    head.next.next = head;
    head.next = nil;
    return newHead;
}

// 迭代
ListNode *reverseList(ListNode *head) {
    if (head == nil || head.next == nil) return head;
    
    ListNode *newHead = nil; //空链表
    while (head != nil) {
        // 1.记住下一个head下一个，方便head向后移动
        ListNode *temp = head.next;
        
        // 2.将head节点插入newHead头部
        head.next = newHead;
        newHead = head;
        
        // 3.head向后移动
        head = temp;
    }
    
    return newHead;
}

@implementation _206_____

+ (void)main {
    
    NSMutableArray *numbers = @[].mutableCopy;
    for (NSInteger i = 0; i < 10; i++) {
        [numbers addObject:@(i)];
    }
    ListNode *head = createList(numbers);
    
    printList(head, @"递归翻转前:head");
    // 递归实现
    ListNode *head1 = re_reverseList(head);
    printList(head1, @"递归翻转后:head");
    
    // 迭代实现
    ListNode *head2 = reverseList(head1);
    printList(head2, @"迭代再翻转:head");
}

@end
