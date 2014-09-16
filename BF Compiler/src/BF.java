//Jacob Chaves
//BrainF*** Compiler
//Compiler Construction

import java.util.LinkedList;

public class BF
{
	//Interface for the different classes of BrainFuck
	public interface Node
	{
		void accept (Visitor v);
	}
	
	public static void main(String args[])
	{
		// Parsing string to tree
		Node hello = parse("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
		// Code generation from tree
		hello.accept(new CompilerVisitorJava());
	}
	
	public static abstract class Sequence implements Node 
	{
		//Declare linked list of child nodes
		LinkedList list = new LinkedList();
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
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Program extends Sequence
	{
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Left
	{
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Right
	{
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Increment
	{
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Decrement
	{
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Input
	{
		public void accept (Visitor v)
		{
			
		}
	}
	public static class Output
	{
		public void accept (Visitor v)
		{
			
		}
	}
	
	/*
	 * Given a StringBuffer, and a Sequence (Program or Loop),
	 * fill up Sequence with appropriate nodes
	 */
	private static Sequence doParse (StringBuffer buf, Sequence seq)
	{
		//Store a single character
		
		//As long as the string buffer isn't empty
		while()
		{
			//consumer one character from the start of the buffer
			
			//Add proper node classes to seq, based on consumed character
		}
		return seq;
	}
	
	/*
	 * We could do this directly from main method
	 * But, instead I separated it
	 */
	public static Program parse (String str)
	{
		//Just pass StringBuffer to method doParse
		//Program() is just a container for the output of doParse
		return (Program) doParse(new StringBuffer(str), new Program());
	}
	
	/*
	 * VISITOR CLASS AND INTERFACE
	 */
	public interface Visitor
	{
		void visit(Left left);
		void visit(Right right);
		void visit(Increment increment);
		void visit(Decrement derement);
		void visit(Input input);
		void visit(Output output);
		void visit(Loop loop);
		void visit(Program program);
	}
	
	public class CompilerVisitorJava implements Visitor
	{
		public void visit(Left left)
		public void visit(Right right)
		public void visit(Increment increment)
		public void visit(Decrement derement)
		public void visit(Input input)
		public void visit(Output output)
		public void visit(Loop loop)
		public void visit(Program program)
	}
}
