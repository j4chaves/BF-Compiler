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
	
	public static abstract class Sequence implements Node 
	{
		//Declare linked list of child nodes
		LinkedList<Node> list = new LinkedList<Node>();
		//This allows to add child node to linked list
		public void add(Node instruction)
		{
			list.add(instruction);
		}
		
		public void accept (Visitor v)
		{
			/* I don't think this is right at all
			for(Node n: list)
				n.accept(v);
			v.visit(this);
			*/ 
		}
	}
	
	/*
	 * Program and Loop are interior nodes. All others are leaf nodes
	 */
	public static class Loop extends Sequence  implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Program extends Sequence implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Left implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Right implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Increment implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Decrement implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Input implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	public static class Output implements Node
	{
		public void accept (Visitor v)
		{
			v.visit(this);
		}
	}
	
	
	
	/*
	 * VISITOR CLASS AND INTERFACE
	 */
	public interface Visitor
	{
		void visit(Left left);
		void visit(Sequence sequence);
		void visit(Right right);
		void visit(Increment increment);
		void visit(Decrement derement);
		void visit(Input input);
		void visit(Output output);
		void visit(Loop loop);
		void visit(Program program);
	}
	
	public static class CompilerVisitorJava implements Visitor
	{
		public void visit(Left left)
		{
			
		}
		public void visit(Right right)
		{
			
		}
		public void visit(Increment increment)
		{
			System.out.println("array[pointer]++");
		}
		public void visit(Decrement decrement)
		{
			System.out.println("array[pointer]--");
		}
		public void visit(Input input)
		{
			
		}
		public void visit(Output output)
		{
			
		}
		public void visit(Loop loop)
		{
			
		}
		public void visit(Program program)
		{
			System.out.println(
				"public class Output { \n" + 
					"\tpublic static void main (String[] args) throws Exception {\n" +
						"\t\tbyte[] array = new byte[30000];\n" +
						"\t\tint pointer = 0;\n" +
						"\t\tpointer = 0;");
			for(int i=0; i<program.list.size(); i++)
			{
				program.list.get(i).accept(this);;
			}
			
			System.out.println("}");
		}
		public void visit(Sequence sequence) 
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
		char character = buf.charAt(0);
		//As long as the string buffer isn't empty
		while(buf.length() > 0)
		{
			//consume one character from the start of the buffer
			buf.deleteCharAt(0);
			//Add proper node classes to seq, based on consumed character
			if (character == '>')
				seq.add(new Right());
			else if (character == '<')
				seq.add(new Left());
			else if (character == '+')
				seq.add(new Increment());
			else if (character == '-')
				seq.add(new Decrement());
			else if (character == '.')
				seq.add(new Output());
			else if (character == ',')
				seq.add(new Input());
			else if (character == '[')
				seq.add(new Loop());		//I don't think this is right.  I'm not sure how to handle these loop chars
			else if (character == ']')		//Remember to come back and fix them cause they're probably wrong
				seq.add(new Loop());
			
			if (buf.length() != 0)
				character = buf.charAt(0);
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
	
	public static void main(String args[])
	{
		// Parsing string to tree
		Node hello = parse("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
		// Code generation from tree
		hello.accept(new CompilerVisitorJava());
	}
}
