import java.util.Arrays;

public class MyTest {

	public static void main(String[] args) {
		FibonacciHeap fib = new FibonacciHeap();
		System.out.println(fib.isEmpty());
		fib.insert(10);
		fib.insert(5);
		fib.insert(20);
		System.out.println(fib.findMin().getPrev().getPrev().getNext().getKey());
		System.out.println(Arrays.toString(fib.countersRep()));
		System.out.println(fib.size());
		System.out.println(fib.isEmpty());
	}

}
