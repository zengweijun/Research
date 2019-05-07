package wj.nius;

@SuppressWarnings("unchecked")
public class ArrayList<E> {
	private int size; // 元素数量
	private E[] elements; // 元素容器
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	/// 构造函数
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];	
		size = 0;
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	

	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	public int size() {
		return size;
	}
 
	public boolean isEmpty() {
		 return size == 0;
	}

	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	public void add(E element) {
		add(size, element);
	}

	public E get(int index) {
		validateIndex(index);
		return elements[index];
	}

	public E set(int index, E element) {
		validateIndex(index);
		E old = elements[index];
		elements[index] = element;
		return old;
	}

	// InsertObjectAtIndex
	public void add(int index, E element) {
		validateIndexForAdd(index);
		
		ensureCapacity(size + 1); 
		
		// 后边元素往后挪
		for (int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}
		
		elements[index] = element;
		size++;
	}
	
	public E remove(int index) {
		validateIndex(index);
		E old = elements[index];
		
		for (int i = index+1; i < size; i++) {
			elements[i-1] = elements[i];
		}
		
		elements[--size] = null;
		
		trim();
		return old;
	}

	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) return i;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	
	// 扩容
	private void ensureCapacity(int needCapacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= needCapacity) return;
		
		// 新容量为旧容量1.5倍
		int newCapacity = oldCapacity + (oldCapacity>>1);
		E[] newElements = (E[]) new Object[newCapacity]; 
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	// 缩容
	private void trim() {
		// 缩容标准：元素数量 <= 总容量一半
		// 注意，此处缩容和扩容 容量变化量 不能一样,否则可能导致复杂度震荡
		// 1.扩容为增加之前容量的一半：10->15，变换量为5
		// 2.假如缩容规则导致的结果为：15->10，变化量也是5
		// 3.可能导致的结果是（若此时数组中有10个元素）
		// 	  3.1 末尾增加一个元素 ==> 导致扩容（此时有11个元素：10->15）
		//    3.2 末尾此时又删除一个元素导致缩容（此时10个元素：15->10）
		// 4.本来末尾删减元素的复杂度是O(1)，如果发生3的情况就是(n)
		// 5.最终情况就是复杂度在O(1)/O(n)两个阶跳动，复杂度震荡，因此应避免变化量相同

		// 此处缩容操作规则定位：容量减为原来一半，变化量不会和扩容相同，避免了复杂度震荡
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity>>1;
		if (size >= newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;
		
		E[] newElements = (E[]) new Object[newCapacity]; 
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;

		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}
	
	private void validateIndexForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
	
	private void validateIndex(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("size = " + size + ", but index = " + index);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [\n");
		for (int i = 0; i < size; i++) {
			if (i != 0) string.append(",\n");
			string.append(elements[i]);			
		}
		string.append("\n]");
		return string.toString();
	}
}
