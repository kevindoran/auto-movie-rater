package util;

/**
 * A publisher keeps track of a number of {@link Subscribers} and
 * calls their {@link Subscriber#update(Object)} update method when it has 
 * changed state. A publisher is an Observable in the
 * {@link http://en.wikipedia.org/wiki/Observer_pattern observer pattern}. A 
 * HashSet is a useful collection type to use for storage, at it doesn't allow
 * multiple equal objects.
 * 
 * @author Kevin Doran
 *
 * @param <T>	The underlying type being observed. An object of this type will
 * 				be passed to the subscriber. It should have public
 * 				methods to allow the subscriber to extract relevant
 * 				information. 
 */
public interface Publisher<T>
{
	/**
	 * Adds the given subscriber to the list of subscribers kept by
	 * the publisher. This subscriber will now have 
	 * its {@code update(Object)} method called when the publisher
	 * changes. 
	 * 
	 * @param subscriber	the subscriber to add.
	 * 
	 * @return				{@code true} if the subscriber was added,
	 * 						{@code false} if not. A subscriber will not be added
	 * 						if it is already added. 
	 */
	public boolean addSubscriber(Subscriber<T> subscriber);
	
	
	/**
	 * Removes the given subscriber from the list of subscribers kept by
	 * the publisher. The subscriber's {@code update(Object)} method will not
	 * be called in future. 
	 * 
	 * @param subscriber	the subscriber to remove.
	 * 
	 * @return				{@code true} if the subscriber was removed,
	 * 						{@code false} if not. A subscriber will not be
	 *						removed it is is not currently added. 
	 */
	public boolean removeSubscriber(Subscriber<T> subscriber);
	
	// Better to send the change to the subscriber as a parameter (esp with threading).
//	/**
//	 * Returns the object which is the subject of the publishing (i.e. the
//	 * object that is being subscribed to).
//	 * 
//	 * @return     the object that is being subscribed to.
//	 */
//	public T getChange();
}
	

