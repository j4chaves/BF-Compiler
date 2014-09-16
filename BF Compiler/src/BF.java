//Jacob Chaves
//BrainF*** Compiler
//Compiler Construction
public class BF 
{
	public static void main(String args[])
	{
		// Parsing string to tree
		Node hello = parse("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
		// Code generation from tree
		hello.accept(new CompilerVisitorJava());
	}
	
	public static abstract class Sequence implements Node {
		//Declare linked list of child nodes
		
		//This allows to add child node to linked list
		public void add(Node instruction)
		{
			
		}
	}
	
	/*
	 * Program and Loop are interior nodes. All others are leaf nodes
	 */
	public static class Loop extends Sequence 
	{
		
	}
	public static class Program extends Sequence
	{
		
	}
	/*
	 * Given a StringBuffer, and a Sequence (Program or Loop),
	 * fill up Sequence with appropriate nodes
	 */
	private static Sequence doParse (StringBuffer buf, Sequence seq)
	{
		//Store a single character
		
		//As long as the string buffer isn't empty
		while(...)
		{
			//consumer one character from the start of the buffer
			
			Add proper node classes to seq, based on consumed character
		}
	}
}
