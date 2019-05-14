//
//  AVLTree.m
//  Algorithm-OC
//
//  Created by 曾维俊 on 2019/5/11.
//  Copyright © 2019年 Nius. All rights reserved.
//

#import "AVLTree.h"

@interface AVLTNode : BTNode {
@public
    NSInteger _height;
}
- (NSInteger)balanceFactor;
- (BOOL)isBalanced;
- (void)updateHeight;
- (AVLTNode *)tallerChild;
@end

@implementation AVLTNode
- (instancetype)init {
    self = [super init];
    if (self) {
        // 对于任意一个节点，只要创建出来添加到树中，就是叶子节点，因此默认高度为1
        _height = 1;
    }
    return self;
}
- (NSInteger)balanceFactor {
    return [self _leftHeight] - [self _rightHeight];
}

- (BOOL)isBalanced {
    return ABS([self balanceFactor]) <= 1;
}

- (void)updateHeight {
    self->_height = MAX([self _leftHeight], [self _rightHeight]) + 1;
}

- (AVLTNode *)tallerChild {
    if ([self _leftHeight] > [self _rightHeight]) return (AVLTNode *)self->_left;
    if ([self _leftHeight] < [self _rightHeight]) return (AVLTNode *)self->_right;
    return self.isLeftChild ? (AVLTNode *)self->_left : (AVLTNode *)self->_right;
}

- (NSInteger)_leftHeight {
    AVLTNode *left = ((AVLTNode *)self->_left);
    return left ? left->_height : 0;
}
- (NSInteger)_rightHeight {
    AVLTNode *right = ((AVLTNode *)self->_right);
    return right ? right->_height : 0;
}

- (NSString *)debugPrintdescription {
    NSString *str = [super debugPrintdescription];
    return [str stringByAppendingFormat:@"_h(%zd)", self->_height];
}

@end

/**
 总结：
    添加：可能导致所有祖先节点失衡
         但是只要最低失衡节点回复平衡后，整棵树就恢复平衡【仅需O(1)次调整】
    删除：可能导致父节点或祖先节点失衡（只有一个节点失衡）
         但恢复平衡后可能导致更高的祖先节点失衡最多需要【最多需要O(log2(n))次调整】
 
 平均时间复杂度
    搜索：O(log2(n))
    添加：O(log2(n))，仅需O(1)次调整
    删除：O(log2(n))，最多需要O(log2(n))次调整
 */
@implementation AVLTree

- (void)afterAdd:(BTNode *)node {
    // 注意，添加一个节点以后，当前节点一定是叶子节点
    // 所以父节点一定平衡，即parent平衡不会被打破，有可能打破的是从祖父节点往上
    // 只有可能导致从祖父节点开始的parent链失衡，其它节点不会受到影响
    /**失衡传递情况：
     1. 要导致失衡，最低可能失衡的节点是祖父节点，至于再往上，有可能失衡到根节点，也有可能失衡在曾祖父节点终止
     2. 因此只要导致失衡的节点都需要更高高度
     */

    // 默认直接指向父节点
    // 注意，添加完成后需要更新parent链节点高度
    BTNode *current = node;
    BTNode *parent = current->_parent;
    while (parent) {
        // 这里从父节点开始遍历的目的是为了更新父节点高度，若不更新高度实际上应从祖父节点开始
        if ([self isBalanced:parent]) {
            // 如果一路往上倒根都是平衡的，直接更新parent链上节点的高度
            [self updateHeight:parent];
        } else {
            // 循环到这里，说明已经找到高度失衡的节点（高度最低的失衡点)
            // 此时的parent可能是current的祖父节点，也有可能往上
            [self rebalance:parent];
            
            // 重新平衡子树之后，子树的高度会回到当初状态（即当前树高度不变），故往上的parent链高度无需更新
            break;
        }
        parent = parent->_parent;
    }
}

- (void)rebalance:(BTNode *)grandparent {
    // 失衡节点设定为grandparent
    // 要调整平衡，其实就调整高度大的子树
    // 因此找到其 parent 和 parent的下一层最大子树(设定为node)用来判断失衡方向
    AVLTNode *parent = ((AVLTNode *)grandparent).tallerChild;
    AVLTNode *node = parent.tallerChild;

    // 单旋转只需旋转本身即可
    // 双旋转只需旋转需要先选择最大子树bht1，在选择本身
    
    if (parent.isLeftChild) { // L
        if (node.isLeftChild) { // LL
            //【单次右旋转即可解决】
            [self rotateRight:grandparent];
        } else { // LR (bht2 == bht1->_left)
            //【先左旋转抵消R，再右旋转抵消L】
            [self rotateLeft:parent];
            [self rotateRight:grandparent];
        }
    } else { // R
        if (node.isLeftChild) { // RL
            //【先右旋转抵消R，在左旋转抵消L】
            [self rotateRight:parent];
            [self rotateLeft:grandparent];
        } else { // RR
            //【单次左旋转即可解决】
            [self rotateLeft:grandparent];
        }
    }
}

- (void)afterRotate:(BTNode *)node child:(BTNode *)child tmpRoot:(BTNode *)tmpRoot {
    [super afterRotate:node child:child tmpRoot:tmpRoot];
    
    // 子树发生变化的需要更细高度
    [self updateHeight:node];
    [self updateHeight:tmpRoot];
}

- (void)rebalance2:(BTNode *)unbalance {
    AVLTNode *parent = ((AVLTNode *)unbalance).tallerChild;
    AVLTNode *node = parent.tallerChild;
    if (parent.isLeftChild) { // L
        if (node.isLeftChild) { // LL
            //【单次右旋转即可解决】
            [self rotateWithUnbalance:unbalance
                                    a:unbalance->_left->_left->_left
                                    b:unbalance->_left->_left
                                    c:unbalance->_left->_left->_right
                                    d:unbalance->_left
                                    e:unbalance->_left->_right
                                    f:unbalance
                                    g:unbalance->_right];
        } else { // LR (bht2 == bht1->_left)
            //【先左旋转抵消R，再右旋转抵消L】
            [self rotateWithUnbalance:unbalance
                                    a:unbalance->_left->_left
                                    b:unbalance->_left
                                    c:unbalance->_left->_right->_left
                                    d:unbalance->_left->_right
                                    e:unbalance->_left->_right->_right
                                    f:unbalance
                                    g:unbalance->_right];
        }
    } else { // R
        if (node.isRightChild) { // RR
            //【单次左旋转即可解决】
            [self rotateWithUnbalance:unbalance
                                    a:unbalance->_left
                                    b:unbalance
                                    c:unbalance->_right->_left
                                    d:unbalance->_right
                                    e:unbalance->_right->_right->_left
                                    f:unbalance->_right->_right
                                    g:unbalance->_right->_right->_right];
        } else { // RL
            //【先右旋转抵消R，在左旋转抵消L】
            [self rotateWithUnbalance:unbalance
                                    a:unbalance->_left
                                    b:unbalance
                                    c:unbalance->_right->_left->_left
                                    d:unbalance->_right->_left
                                    e:unbalance->_right->_left->_right
                                    f:unbalance->_right
                                    g:unbalance->_right->_right];
        }
    }
}

- (void)rotateWithUnbalance:(BTNode *)unbalance
                          a:(BTNode *)a b:(BTNode *)b c:(BTNode *)c
                          d:(BTNode *)d
                          e:(BTNode *)e f:(BTNode *)f g:(BTNode *)g {
    [super rotateWithUnbalance:unbalance a:a b:b c:c d:d e:e f:f g:g];
    
    // 子树发生变化的需要更细高度
    [self updateHeight:b];
    [self updateHeight:f];
    [self updateHeight:d];
}

- (void)afterRemove:(BTNode *)node replace:(BTNode *)replace {
    // 删除节点，可能导致从父节开始点往上某一个节点失衡（parent链式上的某一个失衡，而且有且只有一个会失衡）
    // 因为删除节点只有一种情况会导致点失衡：删除的是某一个节点中较短的那一颗子树上节点
    // 所以，失衡点在parent链式上(包含父节点)，有且只有一个
    // 注意，虽然只可能会导致一个节点失衡，但是失衡节点重新平衡后，可能导致parent链传递失衡
    
    BTNode *current = node;
    BTNode *parent = current->_parent;
    while (parent) {
        if ([self isBalanced:parent]) {
            [self updateHeight:parent];
        } else {
            [self rebalance:parent];
            
            // 重新平衡后，可能导致parent链传递失衡，因此必须往上检查，不能break
            // break;
        }
        parent = parent->_parent;
    }
}

- (BTNode *)createNodeWithElement:(id)o parent:(BTNode *)parent {
    return [AVLTNode nodeWithElement:o parent:parent];
}

- (void)updateHeight:(BTNode *)node {
    [(AVLTNode *)node updateHeight];
}

- (BOOL)isBalanced:(BTNode *)node {
    return ((AVLTNode *)node).isBalanced;
}

- (NSString *)debugPrintdescription {
    NSString *bstStr = [MJBinaryTrees printString:self];
    NSString *str = [NSString stringWithFormat:@"%@<%p>\n", self.class, self];
    str = [str stringByAppendingString:@"---------------------------------\n"];
    str = [str stringByAppendingString:bstStr?bstStr:@"BinaryTree is null"];
    str = [str stringByAppendingString:@"\n---------------------------------"];
    return str;
}

@end
