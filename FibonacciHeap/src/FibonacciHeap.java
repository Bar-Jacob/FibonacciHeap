/**
 * FibonacciHeap
 *
 * An implementation of fibonacci heap over integers.
 */

public class FibonacciHeap
{
	private HeapNode min;
	private HeapNode first;
    private int size = 0;
    private static int numOfCuts = 0; 
    private static int numOfLinks = 0;
    private int numOfMarkedNodes = 0;

   /**
    * public boolean isEmpty()
    *
    * precondition: none
    * 
    * The method returns true if and only if the heap
    * is empty.
    *   
    */
    public boolean isEmpty()
    {
    	return (this.size == 0); 
    	//return (this.first == null);
    }
		
   /**
    * public HeapNode insert(int key)
    *
    * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
    * 
    * Returns the new node created. 
    */
    public HeapNode insert(int key)
    {    
    	//creating new HeapNode
    	HeapNode newNode = new HeapNode(key);
    
    	if(this.isEmpty()) { 
    		this.min = newNode;
    		this.min.next = newNode; this.min.prev = newNode; //making it a circular doubly linked list
    		
    	}else {
    		this.first.setSibling(newNode);		
    	}
    	this.first = newNode;			//updating first
    	
    	//checking if the new node is also the new min
    	if(newNode.key < this.min.key) {
    		this.min = newNode;
    	}
    	
    	this.size++;
    	return newNode; 
    }

   /**
    * public void deleteMin()
    *
    * Delete the node containing the minimum key.
    *
    */
    public void deleteMin()
    {
     	return; // should be replaced by student code
     	
    }

   /**
    * public HeapNode findMin()
    *
    * Return the node of the heap whose key is minimal. 
    *
    */
    public HeapNode findMin()
    {
    	return this.min;
    } 
    
   /**
    * public void meld (FibonacciHeap heap2)
    *
    * Meld the heap with heap2
    *
    * our notes:
    * meld this => x-y-z with heap2 => a-b-c
    * before meld -> this.first.prev = z, heap2.first.prev = c
    * after meld -> this.first.prev = c, no heap2
    */
    public void meld (FibonacciHeap heap2)
    {
    	//connecting c with x
    	heap2.first.prev.next = this.first; 
    	//connecting z with a
    	this.first.prev.next = heap2.first; 
    	//connecting x with c
    	this.first.prev = heap2.first.prev;
    	//connecting a with z
    	heap2.first.prev = this.first.prev;
    	
    	if(heap2.min.getKey() < this.min.getKey()) { //updating new min
    		this.min = heap2.min;
    		heap2.min = null;
    	}		
    }

   /**
    * public int size()
    *
    * Return the number of elements in the heap
    *   
    */
    public int size()
    {
    	return this.size;
    }
    	
    /**
    * public int[] countersRep()
    *
    * Return a counters array, where the value of the i-th entry is the number of trees of order i in the heap. 
    * 
    */
    public int[] countersRep()
    {
    	int[] arr = new int[this.maxRank() + 1];
    	arr[this.first.rank]++; 
    	
    	HeapNode curr = this.first.next;
    	while(curr != this.first) { //same as in maxRank
    		arr[curr.rank]++;
    		curr = curr.next;
    	}
        return arr; 
    }
	
    /**
     * public static int maxRank()
     * iterating over the ranks of each root, and returning the max rank
     */
    public int maxRank() {
    	int maxRank = this.first.rank;
    	HeapNode curr = this.first.next;
    	while(curr != this.first) { //circular linked list, eventually it will go back to the first one.
    		if(curr.rank > maxRank) {
    			maxRank = curr.rank;
    		}
    		curr = curr.next;
    	}
    	return maxRank;
    }
    
    
    
   /**
    * public void delete(HeapNode x)
    *
    * Deletes the node x from the heap. 
    *
    */
    public void delete(HeapNode x) 
    {    
    	return; // should be replaced by student code
    }

   /**
    * public void decreaseKey(HeapNode x, int delta)
    *
    * The function decreases the key of the node x by delta. The structure of the heap should be updated
    * to reflect this chage (for example, the cascading cuts procedure should be applied if needed).
    */
    public void decreaseKey(HeapNode x, int delta)
    {    
    	return; // should be replaced by student code
    }

   /**
    * public int potential() 
    *
    * This function returns the current potential of the heap, which is:
    * Potential = #trees + 2*#marked
    * The potential equals to the number of trees in the heap plus twice the number of marked nodes in the heap. 
    */
    public int potential() 
    {    
    	return 0; // should be replaced by student code
    }

   /**
    * public static int totalLinks() 
    *
    * This static function returns the total number of link operations made during the run-time of the program.
    * A link operation is the operation which gets as input two trees of the same rank, and generates a tree of 
    * rank bigger by one, by hanging the tree which has larger value in its root on the tree which has smaller value 
    * in its root.
    */
    public static int totalLinks()
    {    
    	return numOfLinks;
    }

   /**
    * public static int totalCuts() 
    *
    * This static function returns the total number of cut operations made during the run-time of the program.
    * A cut operation is the operation which diconnects a subtree from its parent (during decreaseKey/delete methods). 
    */
    public static int totalCuts()
    {    
    	return numOfCuts;
    }

     /**
    * public static int[] kMin(FibonacciHeap H, int k) 
    *
    * This static function returns the k minimal elements in a binomial tree H.
    * The function should run in O(k*deg(H)). 
    * You are not allowed to change H.
    */
    public static int[] kMin(FibonacciHeap H, int k)
    {    
    	int[] arr = new int[k];
    	FibonacciHeap fibHeap = new FibonacciHeap();
        HeapNode currNode = H.findMin(); //this is the first min key
        
        // k iterations for k min keys
        for(int i = 0; i < k; i++) { 
            if(currNode != null) { //the node has a son
            	//inserting the first node 
            	HeapNode iNode = fibHeap.insert(currNode.getKey()); // iNode = inserted Node
                iNode.setInfo(currNode); 							//connecting a node from fibHeap it's origin in H
                currNode = currNode.next;
                while(currNode != null || currNode != H.first) {	//we always start from the first because we have pointer to the left most child
                	iNode = fibHeap.insert(currNode.getKey());
                    iNode.setInfo(currNode); 						
                    currNode = currNode.next;
                }
            } 
            currNode = fibHeap.findMin().getInfo(); //finding the min and it's origin to insert
            arr[i] = currNode.getKey();
            fibHeap.deleteMin(); 
            currNode = currNode.child; //continuing to the next level
        }
        return arr;
    }
    
    
    /**
     * HeapNode link(HeapNode a, HeapNode b)
     * link 2 trees with the rank (k) in the heap to one tree with rank k+1
     * returns the root of the new tree
     */
    public HeapNode link(HeapNode a, HeapNode b) {
        HeapNode parent;
        HeapNode child;
        if (a.getKey() < b.getKey()) { //the smaller will be the root
            parent = a;
            child = b;
        }else {
            parent = b;
            child = a;
        }
        parent.setChild(child);
        
        if(this.first == child) { //update first field
        	this.first = parent;
        }
        numOfLinks++;
        return parent;
    }
    
    
    /**
     * public HeapNode getFirst()
     * return the first HeapNode in the heap
     */
    public HeapNode getFirst() {
    	return this.first;
    }
    
   /**
    * public class HeapNode
    * 
    * If you wish to implement classes other than FibonacciHeap
    * (for example HeapNode), do it in this file, not in 
    * another file 
    *  
    */
    public class HeapNode{
    	public int key;
    	private int rank;
    	private boolean marked; //if marked than true, else false
    	private HeapNode child; //left child
    	private HeapNode next;
    	private HeapNode prev;
    	private HeapNode parent;
    	private HeapNode info; //only used for kMin

  	public HeapNode(int key) {
	    this.key = key;
	    this.rank = 0; //when created, heapNode has no children
	    this.child = null;
	    this.next = null;
	    this.prev = null;
	    this.parent = null;
	    this.info = null;
      }

  	public int getKey() {
	    return this.key;
      }
  	
  	public int getRank() {
  		return this.rank;
  	}
  	
  	public HeapNode getChild() {
  		return this.child;
  	}
  	
  	public HeapNode getNext() {
  		return this.next;
  	}
  	
  	public HeapNode getPrev() {
  		return this.prev;
  	}

  	public HeapNode getParent() {
  		return this.parent;
  	}
  	
  	public HeapNode getInfo() {
  		return this.info;
  	}
  	
  	public void setInfo(HeapNode node) {
  		this.info = node;
  	}
  	
  	public void setChild(HeapNode node) { //will always set it as leftMost child
  		node.parent = this; //updating node's parent
  		if(this.child == null) { //parent has no child
  			this.child = node;
  			node.next = node;
  			node.prev = node;
  			
  		}else {
  			this.child.setSibling(node);
  		}
  		this.child = node;
  		this.rank++;
  	}
  	
  	//add node to the beginning of the sibling's circular linked list
  	public void setSibling(HeapNode node) { 
  		this.prev.next = node; //closing the circle including the new sibling
		node.prev = this.prev; //like wise
		this.prev = node;		
		node.next = this;	
  	}

    }
}



