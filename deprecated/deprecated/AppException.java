package deprecated;


/**
 * <code>AppException</code> wraps a <code>RuntimeException</code> giving it a errorId property. Despite having no concrete methods, <code>AppException </code> must be an abstract class so it can extend <code>RuntimeException </code>. <code>AppException</code> uses the errorId string to identify and distinguish between <code>AppException</code>s. This is an alternative  to using a class hierarchy of exceptions. <code>AppException</code> and its subclasses were designed in line with the exception handling guidelines  from   {@see   http://tutorials.jenkov.com.html}  .
 * @author   Kevin Doran
 * @version   1.0 10.04.2011
 * @deprecated
 */
@Deprecated
public abstract class AppException extends RuntimeException
{
	/**
	 * Returns the source/original exception if there is one. 
	 * 
	 * @return the source exception or <code>null</code> if there is none.
	 */
	public abstract Throwable getSourceException();
	
	/**
	 * Sets the source/original exception to be the given <code>Exception
	 * </code>.
	 * 
	 * @param sourceException 	the <code>Exception</code> to set as the 
	 * 							source/original exception.
	 */
	public abstract void setSourceException(Exception sourceException);
	
	/**
	 * Returns the errorId of the <code>AppException</code>. The errorId string is used to identify and distinguish <code>AppException</code>s.
	 * @return   	the errorId string of the <code>AppException</code>.
	 * @uml.property  name="errorId"
	 */
	public abstract String getErrorId();
	
	/**
	 * Sets the errorId string of the <code>AppException</code>. 
	 * @param errorId   	the string to set as the errorId.
	 * @uml.property  name="errorId"
	 */
	public abstract void setErrorId(String errorId);
}