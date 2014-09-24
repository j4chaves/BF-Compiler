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
	 * 
	 * Most visit methods just output their java-equivalent instruction.
	 * visit(Program program) is the main method of the Visitor class
	 * and runs a loop for the input and visits the corresponding
	 * method for each character that is given.
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
	
	public static class CompilerVisitorJava implements Visitor
	{
		public void visit(Left left)
		{
			System.out.println("pointer--;");
		}
		public void visit(Right right)
		{
			System.out.println("pointer++;");
		}
		public void visit(Increment increment)
		{
			System.out.println("array[pointer]++;");
		}
		public void visit(Decrement decrement)
		{
			System.out.println("array[pointer]--;");
		}
		public void visit(Input input)
		{
			//Looks like this is unnecessary
			//There doesn't seem to be an input
			//character in the gven brainf*** code
		}
		public void visit(Output output)
		{
			System.out.println("System.out.print((char)array[pointer]);");
		}
		public void visit(Loop loop)
		{
			System.out.println("while(array[pointer] != 0) {");
		}
		public void visit(Program program)
		{
			//This is the beginning portion of a Java program that is compiling BF
			System.out.println(
				"public class Output { \n" + 
					"\tpublic static void main (String[] args) throws Exception {\n" +
						"\t\tbyte[] array = new byte[30000];\n" +
						"\t\tint pointer = 0;\n" +
						"\t\tpointer = 0;");
			
			//This loop goes through the linked list and creates a
			//Node class for each character in the list and then
			//accepts the CompilerVisitorJava to accept the visitor
			while(program.list.size()!=0)
			{
				System.out.print("\t\t");
				Node n = program.list.pop();//.accept(this);
				if(n.getClass() == (Loop.class))
				{
					n.accept(this);
					/*
					 * This loop is to indent everything 
					 * that is within a loop in brainf***
					 * so that the code is properly placed in Java
					 */
					Node f = program.list.pop();
					while(f.getClass() != Loop.class)
					{
						System.out.print("\t\t\t");
						f.accept(this);
						f = program.list.pop();
					}
					System.out.println("\t\t}");
				}
				else
					n.accept(this);
			}
			
			//Prints out the final 2 semicolons in their correct java-indented spots
			System.out.println("\t}");
			System.out.println("}");
		}
	}
	
	
	/* THESE ARE THE GIVEN FUNCTIONS FOR THIS PROJECT
	 * 
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
				//seq.add(doParse(buf, seq));
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