package wj.nius;

public class ArrayList {
	private int size; // 元素数量
	private int[] elements; // 元素容器
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	/// 构造函数
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = new int[capacity];	
		size = 0;
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public void clear() {
		size = 0;
	}

	public int size() {
		return size;
	}
 
	public boolean isEmpty() {
		 return size == 0;
	}

	public boolean contains(int element) {
		return false;
	}

	public void add(int element) {
		add(size, element);
	}

	public int get(int index) {
		validateIndex(index);
		return elements[index];
	}

	public int set(int index, int element) {
		validateIndex(index);
		return 0;
	}

	// InsertObjectAtIndex
	public void add(int index, int element) {
		
	}
	
	public int remove(int index) {
		return 0;
	}

	public int indexOf(int element) {
		for (int i = 0; i < size; i++) {
			if (elements[i] == element) return i;
		}
		return ELEMENT_NOT_FOUND;
	}
	
	private void validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("size = " + size + ", but index = " + index);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [ ");
		for (int i = 0; i < size; i++) {
			if (i != 0) string.append(",");
			string.append(elements[i]);			
		}
		string.append(" ]");
		return string.toString();
	}
}
