//
//  ListNode.h
//  链表
//
//  Created by 曾维俊 on 2019/5/6.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ListNode : NSObject
@property (nonatomic, strong) ListNode *next;
@property (nonatomic, assign) NSInteger val;
@end

static inline ListNode *createNode(NSInteger val) {
    ListNode *node = [ListNode new];
    node.val = val;
    return node;
}

static inline ListNode *createList(NSArray<NSNumber *> *elements) {
    ListNode *head = nil;
    ListNode *prevNode = nil;
    for (NSInteger i = 0; i < elements.count; i++) {
        ListNode *node = createNode([elements[i] integerValue]);
        if (i == 0) head = node;
        prevNode.next = node;
        prevNode = node;
    }
    return head;
}

static inline ListNode *createCycleList(NSArray<NSNumber *> *elements, NSInteger pos) {
    ListNode *head = nil;
    ListNode *prevNode = nil;
    ListNode *temp = nil;
    for (NSInteger i = 0; i < elements.count; i++) {
        ListNode *node = createNode([elements[i] integerValue]);
        if (i == 0) head = node;
        
        prevNode.next = node;
        prevNode = node;
        
        // 在pos位置增加环
        if (i == pos) temp = node;
        if (i == elements.count-1) node.next = temp;
    }
    return head;
}


static inline void printList(ListNode *head, NSString *headName) {
    ListNode *tempHead = head;
    NSString *string = [NSString stringWithFormat:@"%@->", headName];
    while (tempHead != nil) {
        string = [string stringByAppendingFormat:@"%ld", (long)tempHead.val];
        string =  [string stringByAppendingString:@"->"];
        tempHead = tempHead.next;
    }
    string = [string stringByAppendingString:@"null"];
    NSLog(@"%@", string);
}
