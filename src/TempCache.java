import android.support.v4.util.LruCache;

/*
 * This class permits to have a temporary cache in your app
 * The cache is just set to an instance of app, so if it's killed, the cache is erased
 * If you want a persistant cache, use the Cache class
 It required the Android v4 support library
 */
public class TempCache extends LruCache<String, String>
{
	public TempCache(int maxSize)
	{
		super(maxSize);
	}
}
