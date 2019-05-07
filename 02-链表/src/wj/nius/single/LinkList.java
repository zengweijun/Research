package wj.nius.single;

import wj.nius.AbList;

/**
 * 单向列表
 * @author nius
 *
 * @param <E>
 */
public class LinkList<E> extends AbList<E> {
	
	// 节点
	private static class Node<E> { 
		E element;
		Node<E> next;
		Node(E element, Node<E> next){
			this.element = element;
			this.next = next;
		}
	}
	
	// 头结点
	private Node<E> first;
	
	@Override
	public void clear() {
		first = null;
		size = 0;
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
		// 往链表中的某个位置插入元素思想
		// 1.找到要插入位置的前一个元素 prev
		// 2.让当前元素的next指向要插入元素的位置 node = prev.next
		// 3.让前一个元素的next指向当前元素即完成 prev.next = node
		// 4. 0 位置的元素特殊处理
		
		validateIndexForAdd(index);
		if (index == 0) {
			first = new Node<E>(element, first);
		} else {
			Node<E> prev = node(index - 1);
			prev.next = new Node<E>(element, prev.next);
		}
		size++;
	}
	
	@Override
	public E remove(E element) {
		return remove(indexOf(element));
	}

	@Override
	public E remove(int index) {
		// 思想
		// 1.直接让上一个元素的next略过当前元素指向下一个即可
		// 2.prev.next = prev.next.next
		// 3.对0位置元素单独处理
		
		validateIndex(index);
		Node<E> node = first;
		if (index == 0) {
			first = first.next;
		} else {
			Node<E> prev = node(index - 1);
			node = prev.next;
			prev.next = node.next; // prev.next = prev.next.next;  
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
		// first --> node1 --> node2 --> node3 --> node4 --null 
		// index == 3，需要寻找到node4，循环需要走3次，即走index次
		validateIndex(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}	
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [\n");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) string.append(",\n");
			string.append(node.element);
			node = node.next;
		}
		string.append("\n]");
		return string.toString();
	}
}
