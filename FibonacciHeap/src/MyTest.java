import java.util.Arrays;

public class MyTest {

	public static void main(String[] args) {
		FibonacciHeap fib = new FibonacciHeap();
		FibonacciHeap fib2 = new FibonacciHeap();
		fib.insert(10);
		fib.insert(5);
		fib.insert(20);
		fib.insert(6);
		fib.insert(2);
//		fib2.insert(6);
//		fib2.insert(1);
//		fib2.insert(11);
		
		fib.deleteMin();
//		System.out.println(fib.numOfTrees);
		int[] test = FibonacciHeap.kMin(fib, 3);
		System.out.println(Arrays.toString(test));
		

//		fib.meld(fib2);
//		System.out.println(fib.getFirst().key);
//		System.out.println(fib.getFirst().getPrev().getKey()); //6
//		System.out.println(fib.getFirst().getPrev().getNext().getKey()); //20
		
//		fib.link(a, b);
//		System.out.println(fib.getFirst().getPrev().getKey());
//		FibonacciHeap.HeapNode curr = fib.getFirst();
//		System.out.println(curr.getKey());
//		curr = curr.getNext();
//		
//		while(curr != fib.getFirst()) {
//			System.out.println(curr.getKey());
//			curr = curr.getNext();
//			
//		}
		
		
		
//		System.out.println(fib.findMin().getPrev().getPrev().getNext().getKey());
//		System.out.println(Arrays.toString(fib.countersRep()));
//		System.out.println(fib.size());
//		System.out.println(fib.isEmpty());
	}

}