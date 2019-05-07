package wj.nius.both;

import wj.nius.AbList;

/**
 * 双向列表
 * @author nius
 *
 * @param <E>
 */
public class BothCircleLinkList<E> extends AbList<E> {
	
	// 节点
	private static class Node<E> { 
		E element;
		Node<E> prev;
		Node<E> next;
		Node(E element, Node<E> prev, Node<E> next){
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			if (prev != null) {
				sb.append(prev.element);
			} else {
				sb.append("null");
			}
			
			sb.append("<-").append(element).append("->");

			if (next != null) {
				sb.append(next.element);
			} else {
				sb.append("null");
			}
			
			return sb.toString();
		}

	}
	

	private Node<E> first;	// 头结点
	private Node<E> last;	// 尾结点
	
	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
		
		// 在java中，只要断掉gc root对象的指针，被指向的对象之间就算有循环引用也会被销毁
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		// 先记住之前的元素值
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		validateIndexForAdd(index);
		
		if (index == size) { // 往尾节点位置添加元素
			Node<E> oldLast = last;
			last = new Node<E>(element, oldLast, first);
			if (oldLast == null) { // 空链表（index=0/size=0）
				last.next = last.prev = last;
				first = last;
			} else {
				oldLast.next = last;
				first.prev = last;
			}			
		} else { // 往一般位置添加元素
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<E>(element, prev, next);
			next.prev = node;
			
			if (index == 0) { // 往头节点位置添加元素
				first = node;
				last.next = first;
			} else {
				prev.next = node;
			}
		}
		size++;
	}
	
	@Override
	public E remove(E element) {
		return remove(indexOf(element));
	}

	@Override
	public E remove(int index) {
		validateIndex(index);
		Node<E> node = node(index);
		if (size == 1) { //只有一个元素时只能用这种方法删除
			last = first = null;
		} else {
			Node<E> prev = node.prev;
			Node<E> next = node.next;
			
			prev.next = next;
			next.prev = prev;

			if (node == first) { // index == 0
				first = next;
			}
			if (node == last) { // index == size-1
				last = prev;
			}
		}
		
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i;
				node = node.next;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	
	private Node<E> node(int index) {
		validateIndex(index);
		
		// 先进行判断
		// 如果index < size一半，从first开始查找
		// 如果index >= size一半，从last开始查找
		
		// (first)1->2->3->4->5(last)
		// (first)1<-2<-3<-4<-5(last)
		//  index 0  1  2  3  4
		if (index < (size >> 1)) {
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else { 
			Node<E> node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}			
			return node;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(node);
			
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
}
