package deprecated;



/**
 * <code>AppExceptionFactory</code> is used to create a specific subclass of
 * <code>AppException</code>. Centralising the instigating of an <code>
 * AppException</code> allows the specific <code>AppException</code> subclass
 * to be changed easily, having only to modify a small amount of code.
 * 
 * @author Kevin Doran
 * @version 1.0 10.04.2011
 * @deprecated
 */
@Deprecated
public class AppExceptionFactory
{
	/**
	 * Creates a {@link WrappAppException} and initialises it with the given 
	 * exception.
	 * 
	 * @param previousException the exception to set as the source exception in
	 * 							the new <code>WrapAppException</code>.
	 * @return the created <code>WrapAppException</code>.
	 */
	public AppException createAppException(Exception previousException)
	{
		WrapAppException appException = new WrapAppException(previousException);
		return appException;
	}
	
	/**
	 * Creates the default <code>AppException</code>; a <code>WrapAppException
	 * </code> which does not have a a source exception. 
	 * @return the created <code>WrapAppException</code>.
	 */
	public AppException createAppException()
	{
		WrapAppException appException = new WrapAppException();
		return appException;
	}
}
