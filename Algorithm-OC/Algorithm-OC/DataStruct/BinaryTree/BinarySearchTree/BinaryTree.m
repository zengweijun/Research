//
//  BinaryTree.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/9.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "BinaryTree.h"
#import "Queue.h"
#import "Stack.h"

@interface BTNode(){
    @public
    // 后序遍历中用到
    // 用以记录元素第几次出现在栈顶
    int _flag;
}
@end
@implementation BTNode
+ (instancetype)nodeWithElement:(id)e parent:(BTNode *)p {
    BTNode *n = [self new];
    n->_parent = p;
    n->_element = e;
    return n;
}
- (BTNodeDegree)degree {
    if (_left && _right) return BTNodeDegreeTwo;
    if ((_left && !_right) || (!_left && _right)) return BTNodeDegreeOne;
    return BTNodeDegreeZero;
}
- (BOOL)isLeaf {
    return self.degree == BTNodeDegreeZero;
}
- (BOOL)isDegreeOne {
    return self.degree == BTNodeDegreeOne;
}
- (BOOL)isDegreeTwo {
    return self.degree == BTNodeDegreeTwo;
}
- (BOOL)isLeftChild {
    return (self->_parent) && (self == self->_parent->_left);
}
- (BOOL)isRightChild {
    return (self->_parent) && (self == self->_parent->_right);
}
- (BTNode *)sibling {
    if (self.isLeftChild) return self->_parent->_right;
    if (self.isRightChild) return self->_parent->_left;
    return nil;
}

- (NSString *)description {
    return [self debugPrintdescription];
}
- (NSString *)debugDescription {
    return [self debugPrintdescription];
}
- (NSString *)debugPrintdescription {
    BTNode *tmp = self;
    BTNode *tmpParent = tmp->_parent;
    NSString *tmpStr = tmp->_element;
    NSString *tmpParentStr = @"null";
    if (tmpParent) {
        tmpParentStr = tmpParent->_element;
    };
    return [NSString stringWithFormat:@"%@_p(%@)", tmpStr, tmpParentStr];
}
@end

@implementation BinaryTree

- (NSInteger)size {
    return _size;
}

- (BOOL)isEmpty {
    return _size == 0;
}

- (void)clear {
    _root = nil;
    _size = 0;
}

- (BOOL)isComplete {
    if (!_root) return NO;
    /**
     完全二叉树性质
     
     1.所有也只节点只会出现在最后两层
     2.叶子节点都靠左对齐
     3.倒数第二层往上是一颗满二叉树
     
     思路：层序遍历
        任意一个节点：出队，让其左右子树入队（如果存在）
        1.如果有左右子节点，继续往下遍历
        2.如果只存在右子节点，终止遍历，返回false
        3.如果只存在左子节点 or 本身是叶子节点，则后边所有节点都是叶子节点
     */
    
    BOOL shouldLeaf = NO;
    Queue *queue = [Queue new];
    [queue enQueue:_root];
    
    while (!queue.isEmpty) {
        BTNode *node = queue.deQueue;
        if (shouldLeaf && !node.isLeaf) return NO;
        
#if 0
        // 不论度为多少都入队
        if (node->_left) [queue enQueue:node->_left];
        if (node->_right) [queue enQueue:node->_right];
        
        if (!node->_left && node->_right) { // 如果只有右子节点
            return NO;
        } else if ((node->_left && !node->_right) ||
                   node.isLeaf) {
            // 1.有左子节点，无右子节点(此时，该节点后边的所有节点都应该是叶子节点)
            // 2.无子节点(此时，该节点后边的所有节点都应该是叶子节点)
            // 后面遍历的节点都必须是叶子节点
            shouldLeaf = YES; // 下一轮循环进来的节点必须是叶子节点
        }
        
#else
        if (node->_left) {
            [queue enQueue:node->_left];
        } else if (node->_right) {
            return NO;
        }
        
        if (node->_right) {
            [queue enQueue:node->_right];
        } else {
            // right = nil + (left != nil、left = nil)
            shouldLeaf = YES;
        }
#endif
    }
    return YES;
}

- (NSInteger)height {
    return [self heightWithNode:_root];
}

- (NSInteger)heightWithNode:(BTNode *)node {
#if 0
    // 递归
    if (!node) return 0;
    return MAX([self heightWithNode:node->_left], [self heightWithNode:node->_right]) + 1;
#else
    // 非递归
    if (!node) return 0;
    
    NSInteger height = 0;
    NSInteger levelSize = 1; // 每一层节点数量
    
    Queue *queue = [Queue new];
    [queue enQueue:node];
    
    while (!queue.isEmpty) {
        BTNode *node = queue.deQueue;
        levelSize--;
        
        if (node->_left) [queue enQueue:node->_left];
        if (node->_right) [queue enQueue:node->_right];
        
        if (levelSize == 0) {
            // 本层以遍完毕，即将遍历下一次
            // 而且此时下一层中所有节点都已经入队
            height++;
            levelSize = queue.count;
        }
    }
    
    return height;
#endif
}

// 前驱节点（中序遍历的前一个节点）
- (BTNode *)precursor:(BTNode *)n {
    if (!n) return nil;
    
    // 如果存在左子树，即为左子树中最右子节点tmp.left.right.right...
    BTNode *tmp = n->_left;
    if (tmp) {
        while (tmp->_right) {
            tmp = tmp->_right;
        }
        return tmp;
    }
    
    // 不存在左子树(从父节点->祖父节点->左转弯)
    // 1.tmp从父节点往上，直到发现父节点左转弯，即tmp是其父节点的右子树时，tmp.parent即为前驱节点
    // 2.tmp从父节点往上，如果一直没有发现左转弯，知道tmp指向null，说明其是根节点的左子树中的最小(左)子节点，没有前驱
    tmp = n;
    while (tmp->_parent && (tmp == tmp->_parent->_left)) {
        // 到达根节点 or 无左转弯
        tmp = tmp->_parent;
    }
    
    // tmp.parent == null
    // tmp == tmp.parent.right
    return tmp->_parent;
}

// 后继节点（中序遍历的后一个节点）
- (BTNode *)successor:(BTNode *)n {
    if (!n) return nil;
    
    // 右子树中最小节点
    BTNode *tmp = n->_right;
    if (tmp) {
        while (tmp->_left) {
            tmp = tmp->_left;
        }
        return tmp;
    }
    
    // 寻找右转弯节点
    tmp = n;
    while (tmp->_parent && (tmp == tmp->_parent->_right)) {
        tmp = tmp->_parent;
    }
    
    // tmp.parent == null
    // tmp == tmp.parent.left
    return tmp->_parent;
}

#pragma mark - Traversal
- (void)preorderTraversal:(void (^)(id, BOOL *))block {
    if (!block) return;
    [self _preorderTraversal:_root block:block];
    
//    // 递归方式要终止遍历，这个终止条件要放在递归函数外
    BOOL stop;
    [self _recursivePreorderTraversal:_root stop:&stop block:block];
    
    /*
          ┌─────────8───────┐
          │                 │
     ┌────3────┐       ┌────10───┐
     |         |       │         │
     1     ┌───6───┐   9     ┌───14
           │       │         │
           4       7         13
     */
}
- (void)_recursivePreorderTraversal:(BTNode *)node stop:(BOOL *)stop block:(void (^)(id, BOOL *))block {
    if ((!node) || (*stop)) return;
    // 递归
    block(node->_element, stop); if (*stop) return;
    [self _recursivePreorderTraversal:node->_left stop:stop block:block];
    [self _recursivePreorderTraversal:node->_right stop:stop block:block];
}
- (void)_preorderTraversal:(BTNode *)node block:(void (^)(id, BOOL *))block {
    if (!node) return;
    
    // 非递归(此处应该想到，递归的本质就是栈的叠加，应优先想到栈接口可以替换递归)
    BOOL stop = NO;
    Stack *stack = [Stack new];
    BTNode *tmp = node;
    while (tmp || !stack.isEmpty) {
        if (tmp) {
            block(tmp->_element, &stop);
            if (stop) return;
            [stack push:tmp];
            tmp = tmp->_left;
        } else {
            // 能来到这里，左指针已经为空，但是栈中还有元素，所以没有访问完毕
            // 说明tmp的左树已经访问完毕，pop栈顶，让tmp访问右子树，循环改过程
            tmp = stack.pop;
            tmp = tmp->_right;
        }
    }
}

- (void)inorderTraversal:(void (^)(id, BOOL *))block {
    if (!block) return;
    [self _inorderTraversal:_root block:block];
    
//    // 递归
//    BOOL stop;
//    [self _recursiveInorderTraversal:_root stop:&stop block:block];
}
- (void)_recursiveInorderTraversal:(BTNode *)node stop:(BOOL *)stop block:(void (^)(id, BOOL *))block {
    if ((!node) || (*stop)) return;
    // 递归
    [self _recursiveInorderTraversal:node->_left stop:stop block:block];
    block(node->_element, stop); if (*stop) return;
    [self _recursiveInorderTraversal:node->_right stop:stop block:block];
}
- (void)_inorderTraversal:(BTNode *)node block:(void (^)(id, BOOL *))block {
    if (!node) return;
    
    // 非递归
    BOOL stop = NO;
    Stack *stack = [Stack new];
    BTNode *tmp = node;
    while (tmp || !stack.isEmpty) {
        if (tmp) {
            [stack push:tmp];
            tmp = tmp->_left;
        } else {
            // 能来到这里，左指针已经为空，但是栈中还有元素，所以没有访问完毕
            // 说明tmp的左树已经访问完毕，pop栈顶，让tmp指向右子树，循环改过程
            tmp = stack.pop;
            block(tmp->_element, &stop); if (stop) return;
            tmp = tmp->_right;
        }
    }
}

- (void)postorderTraversal:(void (^)(id, BOOL *))block {
    if (!block) return;
    [self _postorderTraversal:_root block:block];
   
//    // 递归
//    BOOL stop;
//    [self _recursivePostorderTraversal:_root stop:&stop block:block];
}
- (void)_recursivePostorderTraversal:(BTNode *)node stop:(BOOL *)stop block:(void (^)(id, BOOL *))block {
    if ((!node) || (*stop)) return;
    // 递归
    [self _recursivePostorderTraversal:node->_left stop:stop block:block];
    [self _recursivePostorderTraversal:node->_right stop:stop block:block];
    block(node->_element, stop);
}
- (void)_postorderTraversal:(BTNode *)node block:(void (^)(id, BOOL *))block {
    if (!node) return;
    
    /// 非递归
#if 1
    // 思路1：给节点添加标记,标记作用
    // 若节点为叶子节点，则标记自增(出栈->重新入栈)完成后(由于没有子节点)自动出栈
    // 若节点为非叶子节点，则标记自增(出栈->重新入栈)完成后，让其右节点入栈
    // 1.左子树入栈过程
    // 1.1 左子节点入栈-->判断(其左子节点存在)-->左子节点入栈-->判断(其左子节点存在)-->左子节点入栈-->...
    // 1.2 -->判断(其左子节点不存在)-->出栈，flag++(此时先不访问)，接着入栈-->右子节点入栈-->...，重复1.1过程
    // 1.3 当左右子节点都访问完毕后，根节点的flag == 2，此时可以出栈根节点
    
    BOOL stop = NO;
    Stack *stack = [Stack new];
    BTNode *tmp = node;
    int notReadFlag = 1; // flag为1的是，先不读取节点值
    while (tmp || !stack.isEmpty) {
        // 每次遍历到一个节点，就遍历其左子树，并使其入栈，标记为flag为1，直到左子节点为空停止
        while (tmp) {
            tmp->_flag = notReadFlag;
            [stack push:tmp];
            tmp = tmp->_left;
        }
        
        if (!stack.isEmpty) {
            tmp = stack.pop;
            if (tmp->_flag <= notReadFlag) {
                tmp->_flag++;
                [stack push:tmp];
                tmp = tmp->_right;
                // 将节点重新入栈，访问其右节点入栈，以便下一轮循环入栈
                // 此时若其右节点为空，由于其flag已经++过，所以下次循环回来会直接访问出栈
            } else { // flag == 2,此时应该访问该节点
                block(tmp->_element, &stop); if (stop) return;
                
                // 此处节点已经被访问过，说明其已经没有了左右子树，不应该再进入寻找左子树的while循环
                // 故此处设置为nil
                tmp = nil;
            }
        }
    }
    
    // https://www.cnblogs.com/llguanli/p/7363657.html
    // https://blog.csdn.net/gatieme/article/details/51163010
#else
    
    // 思路2，用一个临时变量来记录子节点访问情况
    /**
     1.对于任意一个节点p，当left和right都为空时，说明是叶子节点，可以直接访问
     2.当存在left或者right，如果left和right都已经被访问过，可以直接访问该节点
     3.排除1、2，则现将right入栈、再讲left入栈（保证访问的时候是先left后right）
     */
    BOOL stop = NO;
    Stack *stack = [Stack new];
    BTNode *tmp = node;
    BTNode *read = nil;
    [stack push:tmp];
    while (!stack.isEmpty) {
        tmp = stack.peek;
        if ([tmp degree] == BTNodeDegreeZero ||
            (read && (read == tmp->_left || read== tmp->_right))) {
            // 叶子节点 || 存在左或右子节点但已被访问
            // 虽然(read == tmp->_left || read == tmp->_right)存在三种情况
            // 但是按照入栈顺序，父节点一定在左和右的栈中位置以下，能来到这里，说明子节点一定被访问完毕
            // 比如：只有左节点，left -> parent (read == left)
            // 比如：只有右节点，right -> parent (read == right)
            // 比如：只有左右节点，left -> right -> parent (read == right)
            
            // 直接访问
            tmp = stack.pop;
            read = tmp;
            
            block(tmp->_element, &stop); if (stop) return;
        } else {
            if (tmp->_right) [stack push:tmp->_right];
            if (tmp->_left) [stack push:tmp->_left];
        }
    }
#endif
}

- (void)levelOrderTraversal:(void (^)(id, BOOL *))block {
    if (!block) return;
    [self _levelOrderTraversal:_root block:block];
}
- (void)_levelOrderTraversal:(BTNode *)node block:(void (^)(id, BOOL *))block {
    if (!node) return;
    
    Queue *queue = [Queue new];
    [queue enQueue:node];
    
    while (!queue.isEmpty) {
        BTNode *node = queue.deQueue;
        BOOL stop = NO;
        block(node->_element, &stop);
        if (stop) return;
        node->_left?[queue enQueue:node->_left]:nil;
        node->_right?[queue enQueue:node->_right]:nil;
    }
}

- (NSString *)debugPrintDescription {
    NSString *str = [NSString stringWithFormat:@"%@<%p>\n", self.class, self];
    str = [str stringByAppendingString:@"---------------------------------\n"];
    if (_root) {
        NSString *bstStr = [MJBinaryTrees printString:self];
        str = [str stringByAppendingString:bstStr?bstStr:@"BinaryTree is null"];
    } else {
        str = [str stringByAppendingString:@"null"];
    }
    str = [str stringByAppendingString:@"\n---------------------------------"];
    return str;
}
@end

@implementation BinaryTree(Printer)
- (id)root {
    return _root;
}
- (id)left:(id)node {
    return ((BTNode*)node)->_left;
}
- (id)right:(id)node {
    return ((BTNode*)node)->_right;
}
- (id)string:(id)node {
    return [(BTNode*)node debugPrintdescription];
}
- (NSString *)description {
    return [self debugPrintDescription];
}
- (NSString *)debugDescription {
    return [self debugPrintDescription];
}
@end
