/*
*
* This class implements a library of songs
*
* @author CS111 - Rutgers University
*
*/
public class SongApp {

  private Song[] items;      // keep Songs in an unsorted array
  private int numberOfItems; // number of Songs in the array

  /*
  * Default constructor creates array with capacity for 5 Songs
  */
  public SongApp () {
      items = new Song[5];
			numberOfItems = 0;
  }

  /*
  * One argument constructor creates array with user defines capacity
  *
  * @param capacity defines the capacity of the Song library
  */
  public SongApp (int capacity) {
      items = new Song[capacity];
			numberOfItems = 0;
  }

  /*
  * Add a Song into the library (unsorted array)
  *
  * If the library is full (there is not enough space to add another Song)
  *   - create a new array with double the size of the current array.
  *   - copy all current Songs into new array
  *   - add new Song
  *
  * @param m The Song to be added to the libary
  */
  public void addSong (Song m) {
      if(numberOfItems >= items.length)
			{
				int cap = items.length*2;
				Song[] doubleItems =  new Song[cap];
				//Song[] doubleItems = new Song[cap];
				for(int i = 0; i < items.length; i++)
				{
					doubleItems[i] = items[i];
				}
				doubleItems[numberOfItems] = m;
				numberOfItems++;

				items = doubleItems;
			}
			else
			{
				items[numberOfItems] = m;
				numberOfItems++;
			}
  }

  /*
  * Removes a Song from the library. Returns true if Song is removed, false
  * otherwise.
  * The array should not become sparse after a remove, that is, all Songs
  * should be in consecutive indexes in the array.
  *
  * @param m The Song to be removed
  *
  */
  public boolean removeSong (Song m) {
      for(int i = 1; i < numberOfItems; i++)
			{
				if(items[i-1].equals(m))
				{
					items[i-1] = items[numberOfItems-1];
					items[numberOfItems-1] = null;

					numberOfItems--;

					return true;
				}
			}

			return false;
  }

  /*
  * Returns the library of songs
  *
  * @return The array of Songs
  */
  public Song[] getSongs () {
      return items;
  }

  /*
  * Returns the current number of Songs in the library
  *
  * @return The number of items in the array
  */
  public int getNumberOfItems () {
      return numberOfItems;
  }

  /*
  * Update the rating of Song @m to @rating
  *
  * @param @m The Song to have its ratings updated
  * @param @rating The new rating of @m
  * @return true if update is successful, false otherwise
  *
  */
  public boolean updateRating (Song m, int rating) {
      for(int i = 0; i < numberOfItems; i++)
			{
				if(items[i].equals(m))
				{
					items[i].setRating(rating);
					return true;
				}
			}

			return false;
  }

  /*
  * Prints all Songs
  */
  public void print () {
			for(int i = 0; i < numberOfItems; i++)
			{
				System.out.println(items[i] + ", ");
			}
  }

  /*
  * Return all the Songs by @songwriter. The array size should be the
  * number of Songs by @songwriter.
  *
  * @param songwriter The songwriter's name
  * @param An array of all the Songs by @songwriter
  *
  */
  public Song[] getSongsBySongwriter (String songwriter) {
		Song[] writer = new Song[numberOfItems];
		int x = 0;

      for(int i = 0; i < numberOfItems; i++)
			{
				for(int j = 0; j < items[i].getNumberOfWriters(); j++)
				{
					if(items[i].getWriterAtIndex(j).equals(songwriter))
					{
						writer[x] = items[i];
						x++;
					}
				}
			}

			Song[] writerReturn = new Song[x];
			for(int j = 0; j < writerReturn.length; j++)
			{
				writerReturn[j] = writer[j];
			}

			return writerReturn;
  }

  /*
  * Return all the Songs released in @year. The array size should be the
  * number of Songs made in @year.
  *
  * @param year The year the Songs were made
  * @return An array of all the Songs made in @year
  *
  */
  public Song[] getSongsByYear (int year) {
		Song[] years = new Song[numberOfItems];
		int x = 0;

		for(int i = 0; i < numberOfItems; i++)
		{
			if(items[i].getYear() == year)
			{
				years[x] = items[i];
				x++;
			}
		}

		Song[] yearReturn = new Song[x];
		for(int j = 0; j < yearReturn.length; j++)
		{
			yearReturn[j] = years[j];
		}

		return yearReturn;
  }

  /*
  * Return all the Songs with ratings greater then @rating
  *
  * @param rating
  * @return An array of all Songs with rating greater than @rating
  *
  */
  public Song[] getSongsWithRatingsGreaterThan (int rating) {
		Song[] ratings = new Song[numberOfItems];
		int x = 0;
			for(int i = 0; i < numberOfItems; i++)
			{
				if(items[i].getRating() > rating)
				{
					ratings[x] = items[i];
					x++;
				}
			}

		Song[] ratingReturn = new Song[x];
		for(int j = 0; j < ratingReturn.length; j++)
		{
			ratingReturn[j] = ratings[j];
		}

			return ratingReturn;
  }

  /*
  * Search for Song name @name using binary search algorithm.
  * Assumes items is sorted
  */
  public Song searchSongByName (String name) {
      if(numberOfItems == 0 || name == null)
			{
				return null;
			}
			else
			{
				int left = 0;
				int right = numberOfItems-1;
				int midIndex = (left+(right-left/2));

				while(left <= right)
				{
					if(items[right].getName().compareTo(name) == 0)
					{
						return items[right];
					}
					else if(items[left].getName().compareTo(name) == 0)
					{
						return items[left];
					}
					else if(items[midIndex].getName().compareTo(name) == 0)
					{
						return items[midIndex];
					}
					else if(items[midIndex].getName().compareTo(name) > 0)
					{
						right = midIndex-1;
						midIndex = (left+(right-left/2));
					}
					else if(items[midIndex].getName().compareTo(name) < 0)
					{
						left = midIndex+1;
						midIndex = (left+(right-left/2));
					}
				}
				return null;
			}
  }

 /*
  * Sorts Songs by year using insertion sort
  */
  public void sortByYear ()
	{
		int n = numberOfItems;
    for (int i = 1; i < n; i++)
    {
      Song key = items[i];
			int target = items[i].getYear();
      int j = i-1;

      while (j>=0 && items[j].getYear() > target)
      {
        items[j+1] = items[j];
        j--;
      }

			items[j+1] = key;
    }
  }

 /*
  * Sorts array of Songs by name using selection sort
  */
  public void sortByName () {
      int n = numberOfItems-1;

			while(n > 1)
			{
				String max = items[n].getName();
				int maxIndex = n;

				for(int i = 0; i < n; i++)
				{
					if(items[i].getName().compareTo(max) > 0)
					{
						maxIndex = i;
						max = items[i].getName();
					}
				}

				Song temp = items[n];
				items[n] = items[maxIndex];
				items[maxIndex] = temp;
				n--;
			}
  }

 /*
  * Search for Song name using recursive linear search
  * @param name The name of the song to search
  * @param Songs The array containing all songs
  * @param l The left index
  * @param r The right index
  * @return The song with name @name or null if song is not found
  */
  public static Song searchSongByName (String name, Song[] Songs, int l, int r) {
      if(r < l)
			{
				return null;
			}

			if(Songs[l] != null)
			{
				if(Songs[l].getName().equals(name))
				{
					return Songs[l];
				}
			}
			if(Songs[r] != null)
			{
				if(Songs[r].getName().equals(name))
				{
					return Songs[r];
				}
			}

			return searchSongByName(name, Songs, l+1, r-1);
  }
}
