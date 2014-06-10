package util;

/**
 * A subscriber is informed by a {@link Publisher} to which it is subscriber to
 * of any notable changes in the publisher. 
 * <p>
 * A subscriber subscribes to a publisher by passing itself to the publisher's
 * {@link Publisher#addSubscriber(Subscriber)} method. The publisher will then
 * call the subscriber's {@code update} method whenever it wishes to inform 
 * subscribers of a change in its state. 
 * 
 * @author Kevin Doran
 *
 * @param <T>	The underlying type being observed. An object of this type will
 * 				be passed to the subscriber. It should have public
 * 				methods to allow the subscriber to extract relevant
 * 				information. 
 */
public interface Subscriber<T>
{
	/**
	 * This method is called by a publisher to which the subscriber is
	 * subscribed to. A subscriber may be subscribed to multiple publishers. A
	 * reference to the calling publisher is passed to the update method 
	 * the subscriber to distinguish which publisher has been updated. 
	 * 
	 * @param publisher	the publisher that called the subscriber's update
	 * 					method. 
	 */
	public void update(T change);
}
