public class Person 
{
	String firstName, lastName;
	String email;
	
	public Person(String fN, String lN, String email)
	{
		this.firstName = fN;
		this.lastName = lN;
		this.email = email;
	}
	
	public boolean equals(Object o)
	{
		if(o == null || !(o instanceof Person))
		{
			return false;
		}
		Person other = (Person) o;
		return other.email.equals(email);
	}
	
	public String toString()
	{
		return "[" + firstName + ", " + lastName + "] - " + email;
	}
}