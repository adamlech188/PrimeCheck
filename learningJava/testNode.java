public class testNode { 

  public static void main(String[] args) { 
     Node<String> me = new Node<String>("Adam"); 
	 System.out.println(me.data()); 
	 Node<String> meRef = new Node<String> ("John", me); 
	 System.out.println(meRef.nextNode().data()); 
  } 
} 