//
//  AVLTree+Test.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/11.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "AVLTree+Test.h"

@implementation AVLTree (Test)
+ (void)test {
    [self testInsert];
}

+ (void)testInsert {
//    int data1[] = { 92, 74, 43, 26, 34, 33, 28, 23, 53, 84, 90, 11, 21, 40, 26, 44, 9, 51, 32, 56, 46, 17 };
//    int data1[] = { 92, 74, 43, 26, 34, 33, 28 };
    int data1[] = { 85, 19, 69, 3, 7, 99, 95 };
    int len1 = sizeof(data1) / sizeof(int);
    AVLTree *avlTree = [AVLTree new];
    for (int i = 0; i < len1; i++) {
        NSLog(@"添加了%d", data1[i]);
        [avlTree add:@(data1[i])];
        NSLog(@"%@", avlTree);
    }
    
    NSLog(@"%@", avlTree);
    [avlTree remove:@99];
    NSLog(@"%@", avlTree);
    [avlTree remove:@85];
    NSLog(@"%@", avlTree);
    [avlTree remove:@95];
    NSLog(@"%@", avlTree);
//    [avlTree remove:@34];
//    NSLog(@"%@", avlTree);
}

@end
