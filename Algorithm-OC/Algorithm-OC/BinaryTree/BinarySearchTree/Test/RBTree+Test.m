//
//  RBTree+Test.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/13.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "RBTree+Test.h"

@implementation RBTree (Test)
+ (void)test {
    int data1[] = { 92, 74, 43, 26, 34, 33, 28, 23, 53, 84, 90, 11, 21, 40, 26, 44, 9, 51, 32, 56, 46, 17 };
    int len1 = sizeof(data1) / sizeof(int);
    RBTree *rbTree = [RBTree new];
    for (int i = 0; i < len1; i++) {
        NSLog(@"添加了%d", data1[i]);
        [rbTree add:@(data1[i])];
        NSLog(@"%@", rbTree);
    }
}
@end
