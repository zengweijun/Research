//
//  RBTree.h
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/13.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BBSTree.h"

/**
 红黑树（红黑树等价于B树中的2-3-4树）
 
 性质
 1.节点为黑色Black和红色Red组成
 2.根节点为黑色Black
 3.叶子节点(假象节点为null)是黑色Black
 4.所有路径上不能出现两个连续的红色Red节点
 5.从任意一个节点到叶子节点所有路径上，包含的黑色Black节点数量相同
 */
@interface RBTree<ObjectType> : BBSTree<ObjectType>

@end

