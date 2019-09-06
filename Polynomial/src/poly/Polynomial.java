package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		if(poly1 == null && poly2 == null) //in case both are null at the start, return null
		{
			return null;
		}
		
		Node result = new Node(0, 0, null); //"dummy node", just a place to start
		Node ptr = result; //point points at "dummy node"
		
		//in case one is null and other is not
		
		if(poly1 == null && poly2 != null)
		{
			while(poly2 != null)
			{
				Node node = new Node(poly2.term.coeff, poly2.term.degree, null);
				ptr.next = node;
				ptr = ptr.next;
				poly2 = poly2.next;
			}
		}
		else if(poly2 == null && poly1 != null)
		{
			while(poly1 != null)
			{
				Node node = new Node(poly1.term.coeff, poly1.term.degree, null);
				ptr.next = node;
				ptr = ptr.next;
				poly1 = poly1.next;
			}
		}
		
		while(poly1 != null && poly2 !=  null)
		{
			if(poly1.term.degree == poly2.term.degree) //if they are equal
			{
				Node node = new Node(poly1.term.coeff+poly2.term.coeff, poly1.term.degree, null); //creates new node
				if(node.term.coeff == 0) //in case if coeffs add up to 0, we don't need to add it to result
				{
					poly1 = poly1.next; //moves poly1 to next
					poly2 = poly2.next; //moves poly2 to next
				}
				else //if coeffs are not 0, go here
				{
					ptr.next = node; //ptr's next will point at the new node created
					ptr = ptr.next; //ptr now points to ptr.next (aka, our new node in our list)
					poly1 = poly1.next; //moves poly1 to next
					poly2 = poly2.next; //moves poly2 to next
				}
			}
			else if(poly1.term.degree > poly2.term.degree)
			{
				Node node = new Node(poly2.term.coeff, poly2.term.degree,  null); 
				//if poly1 degree is greater, we print out poly2 to keep the order of coefficients
				//otherwise it would print out the greater coefficient later, i.e. x^4  + x^5
				ptr.next = node;
				ptr = ptr.next;
				poly2  = poly2.next;
			}
			else  if(poly2.term.degree > poly1.term.degree)
			{
				Node node = new Node(poly1.term.coeff, poly1.term.degree, null);
				ptr.next = node;
				ptr = ptr.next;
				poly1 = poly1.next;
			}
		}
		
		while(poly1 != null) //in case poly2 has less nodes than poly1
		{
			Node node = new Node(poly1.term.coeff, poly1.term.degree, null);
			ptr.next = node;
			ptr = ptr.next;
			poly1 = poly1.next;
		}
		
		while(poly2 != null) //in case poly1 has less nodes than poly2
		{
			Node node = new Node(poly2.term.coeff, poly2.term.degree, null);
			ptr.next = node;
			ptr = ptr.next;
			poly2 = poly2.next;
		}
		
		ptr = result; //points back to the start, to the "dummy node"
		result = ptr.next; //gets rid of dummy node from result
		
		return result;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		if(poly1 == null && poly2 == null) //in case both are null at the start, return null
		{
			return null;
		}
		
		Node result = null; //creates result node
		
		Node front = poly2; //node to point at poly2's front so we can go through poly2 and come back
		
		while(poly1 != null) //this while loop changes poly1
		{
			Node multiply = new Node(0, 0, null); //"dummy node" for ptr to point at
			Node ptr = multiply;
			
			while(poly2 != null) //this while look changes poly2 and multiplies it by whatever poly1 is pointing at
			{
				Node node = new Node(poly1.term.coeff*poly2.term.coeff, poly1.term.degree+poly2.term.degree, null);
				ptr.next = node;
				ptr = ptr.next;
				poly2 = poly2.next;
			}
			
			ptr = multiply; //points back to the start, to the "dummy node"
			multiply = ptr.next; //gets rid of dummy node from result
			
			result = add(result, multiply); //changes result list and adds multiply to it, does this each iteration
			
			poly2 = front; //uses front we implemented before to point poly2 to front again in case while loop goes through again 
			
			poly1 = poly1.next; //moves poly1 to next node
		}
		
		return result;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		if(poly == null) //in case poly is null
		{
			return 0;
		}
		
		float result = 0; //empty result
		float powerX = 1; //to calculate x to a certain power
		
		while(poly != null)
		{
			powerX = 1; //resets powerX
			if(poly.term.degree != 0) 
			{
				for(int i = poly.term.degree; i > 0; i--) //goes through degree amount of times for the power
				{
					powerX *= x;
				}
				
				result += powerX*poly.term.coeff; //adds this to result
			}
			else if(poly.term.degree == 0) //if poly degree is 0, no need for powerX
			{
				result += poly.term.coeff; //so just add the coeff to the result
			}
			
			poly = poly.next; //moves poly to next node
		}
		
		return result;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
