//
//  BSTree+Test.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/9.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BSTree+Test.h"

@interface Person : NSObject
@property (nonatomic, assign) int age;
@property (nonatomic, copy) NSString *name;
@end
@implementation Person
- (NSString *)description {
    return [NSString stringWithFormat:@"P(%d,%@)", self.age, self.name];
}
@end
@interface SubPerson : Person
@end
@implementation SubPerson
@end

@implementation BSTree (Test)
+ (void)test {
    // 构造二叉树，按照层序遍历顺序加入即可
    //    [self testPrint];
//      [self testRemove];
//    [self testTraversal];
    [self testIsComplete];
//    [self testObjctType];
}

+ (void)testObjctType {
    BSTree<Person *> *bst = [BSTree new];
    [bst preorderTraversal:^(Person *o, BOOL *stop) {
        
    }];
    
    Person *p;
    [bst add:p];
    SubPerson *sp;
    [bst add:sp];
}

+ (void)testIsComplete {
    /*
          ┌──────8──────────┐
          │                 │
     ┌────3────┐       ┌────10───┐
     |         |       │         │
     1     ┌───6───┐   9     ┌───14
           │       │         │
           4       7         13
     */
    int data1[] = { 8, 3, 1, 6, 4, 7, 10, 9, 14, 13 };
    int len1 = sizeof(data1) / sizeof(int);
    BSTree *bst1 = [BSTree new];
    for (int i = 0; i < len1; i++) {
        [bst1 add:@(data1[i])];
    }
    NSLog(@"%@", bst1);
    NSLog(@"IsComplete:%d", bst1.isComplete);
    NSLog(@"Height:%zd", bst1.height);
    
    /*
            ┌──8───┐
            │      │
       ┌────3      10
       |
       1
     */
    int data2[] = { 8, 3, 10, 1 };
    int len2 = sizeof(data2) / sizeof(int);
    BSTree *bst2 = [BSTree new];
    for (int i = 0; i < len2; i++) {
        [bst2 add:@(data2[i])];
    }
    NSLog(@"%@", bst2);
    NSLog(@"IsComplete:%d", bst2.isComplete);
    NSLog(@"Height:%zd", bst2.height);
    
    /*
          ┌──────8──────────┐
          │                 │
     ┌────3────┐       ┌────10
     |         |       │
     1         6       9
     */
    int data3[] = { 8, 3, 10, 1, 6, 9};
    int len3 = sizeof(data3) / sizeof(int);
    BSTree *bst3 = [BSTree new];
    for (int i = 0; i < len3; i++) {
        [bst3 add:@(data3[i])];
    }
    NSLog(@"%@", bst3);
    NSLog(@"IsComplete:%d", bst3.isComplete);
    NSLog(@"Height:%zd", bst3.height);
    
    int data4[] = { 8, 5, 12, 3, 7, 11, 15, 2, 4, 6, 8, 10, 12, 14, 17, 1, 16 };
    int len4 = sizeof(data4) / sizeof(int);
    BSTree *bst4 = [BSTree new];
    for (int i = 0; i < len4; i++) {
        [bst4 add:@(data4[i])];
    }
    NSLog(@"%@", bst4);
    NSLog(@"IsComplete:%d", bst4.isComplete);
    NSLog(@"Height:%zd", bst4.height);
    
}

+ (void)testTraversal {
    /*
          ┌──────8──────────┐
          │                 │
     ┌────3────┐       ┌────10───┐
     |         |       │         │
     1     ┌───6───┐   9     ┌───14
           │       │         │
           4       7         13
     */
    int data1[] = { 8, 3, 1, 6, 4, 7, 10, 9, 14, 13 };
    int len1 = sizeof(data1) / sizeof(int);
    BSTree *bst1 = [BSTree new];
    for (int i = 0; i < len1; i++) {
        [bst1 add:@(data1[i])];
    }
    
    NSLog(@"------------前序遍历------------");
    NSLog(@"%@", bst1);
    // (8) 3 1 6 4 7 10 9 14 13
    [bst1 preorderTraversal:^(id o, BOOL *stop) {
        NSLog(@"%@", o);
    }];

    NSLog(@"------------中序遍历------------");
    NSLog(@"%@", bst1);
    // 1 3 4 6 7 (8) 9 10 13 14
    // 中序遍历规律（排好序的）
    [bst1 inorderTraversal:^(id o, BOOL *stop) {
        NSLog(@"%@", o);
    }];
    
    NSLog(@"------------后序遍历------------");
    NSLog(@"%@", bst1);
    // 1 4 7 6 3 9 13 14 10 (8)
    [bst1 postorderTraversal:^(id o, BOOL *stop) {
        NSLog(@"%@", o);
    }];
    
    NSLog(@"------------层序遍历------------");
    NSLog(@"%@", bst1);
    // (8) 3 10 1 6 9 14 4 7 13
    [bst1 levelOrderTraversal:^(id o, BOOL *stop) {
        NSLog(@"%@", o);
        if ([o integerValue] == 6) *stop = YES;
    }];
}

+ (void)testRemove {
    /*
          ┌──────8──────────┐
          │                 │
     ┌────3────┐       ┌────10───┐
     |         |       │         │
     1     ┌───6───┐   9     ┌───14
           │       │         │
           4       7         13
     */
    int data1[] = { 8, 3, 1, 6, 4, 7, 10, 9, 14, 13 };
    int len1 = sizeof(data1) / sizeof(int);
    BSTree *bst1 = [BSTree new];
    for (int i = 0; i < len1; i++) {
        [bst1 add:@(data1[i])];
    }
    NSLog(@"%@", bst1);
    [bst1 remove:@(1)];
    NSLog(@"%@", bst1);
    [bst1 remove:@(3)];
    NSLog(@"%@", bst1);
    [bst1 remove:@(8)];
    NSLog(@"%@", bst1);
}

+ (void)testPrint {
    // 根据加入的顺序不一致，或的二叉树就不一样
    int data1[] = { 38, 18, 4, 69, 85, 71, 34, 36, 29, 100 };
    int len1 = sizeof(data1) / sizeof(int);
    BSTree *bst1 = [BSTree new];
    for (int i = 0; i < len1; i++) {
        [bst1 add:@(data1[i])];
    }
    NSLog(@"%@", bst1);
    
    int data2[] = {  38, 71, 34, 36, 29, 100, 18, 4, 69 };
    int len2 = sizeof(data2) / sizeof(int);
    BSTree *bst2 = [BSTree new];
    for (int i = 0; i < len2; i++) {
        [bst2 add:@(data2[i])];
    }
    
    NSLog(@"%@", bst2);
    
    
    int data3[] = {  38, 71, 34, 36, 29, 100, 18, 4, 69 };
    int len3 = sizeof(data3) / sizeof(int);
    BSTree *bst3 = [BSTree treeWithComparatorBlock:^NSComparisonResult(Person *p1, Person *p2) {
        if (p1.age > p2.age) return NSOrderedDescending;
        if (p1.age < p2.age) return NSOrderedAscending;
        return NSOrderedSame;
    }];
    for (int i = 0; i < len3; i++) {
        Person *p = [Person new];
        p.age = data3[i];
        p.name = [NSString stringWithFormat:@"name(%d)", p.age];
        [bst3 add:p];
    }
    
    NSLog(@"%@", bst3);
}
@end
