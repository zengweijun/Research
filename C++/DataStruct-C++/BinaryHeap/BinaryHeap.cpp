//
//  BinaryHeap.cpp
//  DataStruct-C++
//
//  Created by ybf on 2019/6/12.
//  Copyright © 2019 nius. All rights reserved.
//

/** 二叉堆：符合完全二叉树性质，元素总是从上到下、从左到右规则排列
 最大堆（大根堆、大顶堆）：任何节点值总是大于子节点的值（左右子节点）
 最小堆：任何节点值总是小于子节点的值（左右子节点）
 
 一般使用数组实现，假设堆顶元素的位置放在素组0所以位置，则有如下关系
 (01) 索引为i的左孩子的索引是 (2*i+1);
 (02) 索引为i的右孩子的索引是 (2*i+2);
 (03) 索引为i的父结点的索引是 floor((i-1)/2);
 
 应用：
    1.top K 问题
    2.优先级队列的底层实现
    3. ...
 */


#include <iostream>

using namespace std;

template <class E>
class BinaryHeap {
    
private:
    /// 默认容量
    static const int DEFAULT_CAPACITY = 10;
    
    int m_size;     // 元素数量
    int m_capacity; // 容量
    E *m_elements;  // 元素数组
    
    /// Helper
    void elementNotNullCheck(E element);
    void ensureCapacity(int needCapacity);
    void siftUp(int index);
    void siftDown(int index);
    
    int compare(E e1, E e2);
    void emptyCheck();
    
public:
    BinaryHeap(int capacity = DEFAULT_CAPACITY);
    ~BinaryHeap();
    
    /// 元素个数
    int size();
    
    /// 是否为空
    bool isEmpty();
    
    /// 清空
    void clear();
    
    /// 添加元素
    void add(E element);
    
    /// 获得堆顶元素
    E get();
    
    /// 删除堆顶元素
    E remove();
    
    /// 删除堆顶元素的同时插入一个新元素
    E replace(E element);
    
    
    /// 删除堆顶元素的同时插入一个新元素
    void print();
};

template <class E>
BinaryHeap<E>::BinaryHeap(int capacity) {
    m_size = 0;
    m_capacity = (capacity>DEFAULT_CAPACITY) ? capacity : DEFAULT_CAPACITY;
    m_elements = new E[m_capacity];
}

template <class E>
BinaryHeap<E>::~BinaryHeap<E>() {
    delete[] m_elements;
    m_size = 0;
    m_capacity = 0;
}

template <class E>
int BinaryHeap<E>::size(){
    return m_size;
}

template <class E>
bool BinaryHeap<E>::isEmpty() {
    return m_size == 0;
}

template <class E>
void BinaryHeap<E>::clear() {
    delete[] m_elements;
    m_size = 0;
    m_capacity = DEFAULT_CAPACITY;
    
    m_elements = new E[DEFAULT_CAPACITY];
}

template <class E>
void BinaryHeap<E>::add(E element) {
    elementNotNullCheck(element);
    ensureCapacity(m_size+1);
    m_elements[m_size] = element;
    siftUp(m_size);
    m_size++;
}

template <class E>
E BinaryHeap<E>::get() {
    
}

template <class E>
E BinaryHeap<E>::remove() {
    emptyCheck();
    
    // 先取到最后一个元素，覆盖第0个元素然后下滤，返回第0个元素
    int lastIndex = --m_size;
    E root = m_elements[0];
    m_elements[0] = m_elements[lastIndex];
    delete m_elements[lastIndex];
    
    siftDown(0);
    return root;
}

template <class E>
E BinaryHeap<E>::replace(E element) {
    
}

template <class E>
void BinaryHeap<E>::print() {
    while (m_size > 0 ) {
        cout << remove() << " ";
    }
    cout << "\n";
}

/// ------------- Helper
template <class E>
void BinaryHeap<E>::elementNotNullCheck(E element) {
    if (element != 0) return; // Do nothing
    throw "element must not be null";
}

template <class E>
void BinaryHeap<E>::ensureCapacity(int needCapacity) {
    int oldCapacity = m_capacity;
    if (oldCapacity >= needCapacity) return; // do nothing
    
    // 新容量为旧容量的1.5倍
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    E *newEelements = new E[newCapacity];
    for (int i = 0; i < m_size; i++) newEelements[i] = m_elements[i];
    m_elements = newEelements;
    
    cout << "发生扩容操作" << oldCapacity << "-->" << newCapacity << endl;
}

template <class E>
void BinaryHeap<E>::siftUp(int index) {
    // 让index位置的元素上滤
    
    E element = m_elements[index];
    while (index > 0) {
        int parentIndex = (index-1) >> 1;
        E parentElement = m_elements[parentIndex];
        if (compare(element, parentElement) <= 0) break;
        // 将父元素存储在index位置
        m_elements[index] = parentElement;
        // 重新赋值index
        index = parentIndex;
    }
    m_elements[index] = element;
}

template <class E>
void BinaryHeap<E>::siftDown(int index) {
    // 让index位置的元素下滤
    
    E element = m_elements[index];
    int half = m_size >> 1;
    
    // 第一个叶子节点的索引 == 非叶子节点的数量
    // index < 第一个叶子节点的索引
    // 必须保证index位置是非叶子节点
    // 因为叶子节点不能再下滤
    
    while (index < half) {
        // index的节点有2种情况
        // 1.只有左子节点
        // 2.同时有左右子节点
        
        // 默认为左子节点跟它进行比较
        int childIndex = (index << 1) + 1;
        E child = m_elements[childIndex];
        
        // 右子节点
        int rightIndex = childIndex + 1;
        // 如果存在右子节点时候进行比较
        // 选出左右子节点最大的那个
        if (rightIndex < m_size && compare(m_elements[rightIndex], child) > 0) {
            child = m_elements[childIndex = rightIndex];
        }
        
        if (compare(element, child) >= 0) break;
        
        // 将子节点存放到index位置
        m_elements[index] = child;
        // 重新设置index
        index = childIndex;
    }
    m_elements[index] = element;
}

template <class E>
int BinaryHeap<E>::compare(E e1, E e2) {
    return e1 - e2;
}

template <class E>
void BinaryHeap<E>::emptyCheck() {
    if (m_size == 0) throw "Heap is empty";
}


