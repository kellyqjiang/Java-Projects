import java.util.HashMap;

public class HashUse
{
	public static void main(String[] args)
	{
		//declaring and allocating a hash table
		HashMap<String, Person> contacts = new HashMap<String, Person>(100, 2);
		
		Person p1 = new Person("Ana Paula", "Centeno", "anapaula@cs.rutgers.edu");
		Person p2 = new Person("Sesh", "Venugopal", "venugopa@cs.rutgers.edu");
		
		//insert into hash table
		contacts.put("anapaula@cs.rutgers.edu", p1);
		contacts.put("venugopa@cs.rutgers.edu", p2);
		
		//retrieve from the hash table
		System.out.println(contacts.get("venugopa@cs.rutgers.edu"));
		System.out.println(contacts.get("anapaula@cs.rutgers.edu"));
		
		System.out.println("Iterating over keys:");
		for(String k: contacts.keySet())
		{
			System.out.println(contacts.get(k));
		}
		
		System.out.println("Interating over values:");
		for(Person p: contacts.values())
		{
			System.out.println(p);
		}
	}
}