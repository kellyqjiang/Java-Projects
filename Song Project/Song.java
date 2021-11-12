public class Song
{
	private String name; // String name of song
	private int year; //year song was released
	private int numberOfWriters; //store number of song's composers
	private String[] writers = new String[100]; //store name of song composers
	private int rating; //value ranging from 1 (horrible) to 5 (the best song ever)

	public Song(String name) //that takes as an argument the song's name
	{
		this.name = name;
	}

	public void setName(String name) //updates song's name attribute
	{
		this.name = name;
	}

	public String getName() //returns song's name attribute
	{
		return name;
	}

	public void setYear(int year) //updates song's year attribute
	{
		this.year = year;
	}

	public int getYear() //returns song's year attribute
	{
		return year;
	}

	public void setRating(int rating) //update song's rating attribute
	{
		if(rating < 1 || rating > 5)
		{
			IO.reportBadInput();
		}
		else
		{
			this.rating = rating;
		}
	}

	public int getRating() //returns song's rating attribute
	{
		return rating;
	}

	public void addWriter(String writerName) //receives a String with the writer’s name as a parameter and inserts the String into the writer
	// array at the first empty position; update numberOfWriters to reflect the new number of writers
	{
		writers[numberOfWriters] = writerName;
		numberOfWriters++;
	}

	public String[] getWriters() //returns the writers array attribute
	{
		return writers;
	}

	public int getNumberOfWriters() //returns the value of numberOfWriters attribute
	{
		return numberOfWriters;
	}

	public String getWriterAtIndex(int index) //returns the writer’s name at that index. Use 0 based indexing where an index of 0 is the first name.
	//It should return null if there is no writer at that index
	{
		return writers[index];
	}

	public String toString() //returns the string representation of this song with the song's name, year, and rating.
	// Output in the format \"name, year, rating\"
	{
		return name + ", " + year + ", " + rating;
	}

	public boolean equals(Object other) //returns true if this object is the same as other object. Two objects are equal if they have the same name,
	//the same writers/composers, and were released at the same year. Assume the ordering of writers might not be the same and no duplicate writer
	//name exists in either song.
	{
		if(other instanceof Song)
		{
			Song song = (Song) other;

			boolean sameName = false;
			boolean sameWriters = true;
			boolean sameYear = false;

			if(this.name.equals(song.getName()))
			{
				sameName = true;
			}

			if(this.year == song.getYear())
			{
				sameYear = true;
			}

			if(this.numberOfWriters != song.getNumberOfWriters())
			{
				return false;
			}
			else
			{
				for(int i = 0; i < this.numberOfWriters; i++)
				{
					for(int j = 0; j < song.getNumberOfWriters(); j++)
					{
						if(this.writers[i].equals(song.getWriterAtIndex(j)))
						{
							sameWriters = true;
							break;
						}
						else
						{
							sameWriters = false;
						}
					}
					if(sameWriters == false)
					{
						break;
					}
				}
			}

			if(sameName == true && sameWriters == true && sameYear == true)
			{
				return true;
			}

			return false;

		}

		return false;
	}

	public int compareTo (Song other) //return 0 if this song's name is lexicographically equal to other song's name; Return a negative number,
	//say -1, if this song's name is less than the other song's name lexicographically; Return a postive number, say 1,
	//if this song's name is greater than the other song's name lexicographically.
	{
		if(this.name.compareToIgnoreCase(other.getName()) > 0)
			return 1;
		else if(this.name.compareToIgnoreCase(other.getName()) < 0)
			return -1;

		return 0;
	}
}
