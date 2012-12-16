import java.util.Random;

public class RandomExtension extends Random
{
	private static final long serialVersionUID = 8914228484090011478L;

	/*
	* Extension method of nextInt and able to generate a random number between two numbers
	*/
	public int nextInt(int min, int max)
	{
		return min + (int)(Math.random()*(max - min));
	}
}
