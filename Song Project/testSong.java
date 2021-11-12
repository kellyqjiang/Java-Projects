public class testSong
{
	public static void main(String[] args)
	{
		System.out.print("Enter your song name: ");
		String name = IO.readString();
		Song song1 = new Song(name);

		System.out.print("Enter the year your song was written: ");
		int year = IO.readInt();
		song1.setYear(year);

		System.out.print("Enter the number of writers/composers the song has: ");
		int numOfWriters = IO.readInt();

		for(int i = 0; i < numOfWriters; i++)
		{
			System.out.print("Enter a writer: ");
			String writers = IO.readString();
			song1.addWriter(writers);
		}

		song1.setRating(5);

		System.out.print("Enter your song name: ");
		String name2 = IO.readString();
		Song song2 = new Song(name2);

		System.out.print("Enter the year your song was written: ");
		int year2 = IO.readInt();
		song2.setYear(year2);

		System.out.print("Enter the number of writers/composers the song has: ");
		int numOfWriters2 = IO.readInt();

		for(int i = 0; i < numOfWriters; i++)
		{
			System.out.print("Enter a writer: ");
			String writers = IO.readString();
			song2.addWriter(writers);
		}

		song2.setRating(3);

		System.out.println(song1.toString());
		System.out.println(song2.toString());

		System.out.println(song1.equals(song2));
		System.out.println(song1.compareTo(song2));

	}
}
