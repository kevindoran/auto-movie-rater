package deprecated;




/**
 * <code>WrapAppException</code> is an <code>AppException</code> that wraps around each previous exception, in other words, a new <code>WrapAppException </code> is created for each throw, rather than passing the original exception all the way up the stack. Although easier to implement, the  <code>WrapAppException</code> does not have the best stack trace, as the  stack trace is harder to read due to the wrapping of exceptions.  
 * @author   Kevin Doran
 * @version   1.0 10.04.2011
 * @deprecated
 */
@Deprecated
public class WrapAppException extends AppException
{

	private Throwable sourceException;
	private String errorId = "";
	
	/**
	 * @uml.property  name="previousAppException"
	 * @uml.associationEnd  
	 */
	private AppException previousAppException;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	
	/**
	 * Creates the default <code>WrapAppException</code>.
	 */
	public WrapAppException()
	{}
	
	/**
	 * Creates a <code>WrapAppException</code> setting the given <code>
	 * Exception</code> to be the source exception of the new <code>
	 * WrapAppException</code>.
	 * 
	 * @param sourceException 	the exception to set as the source exception of 
	 * 							the <code>WrapAppException</code>.
	 */
	public WrapAppException(Exception sourceException)
	{
		this.sourceException = sourceException;
	}
	
	/**
	 * Creates a <code>WrapAppException</code> setting the given <code>
	 * AppException</code> to be the previous <code>AppException</code>
	 * of the new <code>WrapAppException</code>.
	 * 
	 * @param sourceException 	the exception to set as the source exception of 
	 * 							the <code>WrapAppException</code>.
	 */
	public WrapAppException(AppException previousAppException)
	{
		this.previousAppException = previousAppException;
	}
	
	/**
	 * @return
	 * @uml.property  name="sourceException"
	 */
	@Override		
	public Throwable getSourceException()
	{
		// Only the first ApplicationException can contain a sourceException.
		if(previousAppException == null)
			return sourceException;
		else
			return previousAppException.getSourceException();
	}


	@Override		
	public void setSourceException(Exception sourceException)
	{
		this.sourceException = sourceException;
	}


	/**
	 * @return
	 * @uml.property  name="errorId"
	 */
	@Override		
	public String getErrorId()
	{
		// If an errorId was not set in this WrapAppExcption, get the one from
		// the previous AppException, if there is one. 
		if(errorId != null)
			return errorId;
		else if(previousAppException != null)
			return previousAppException.getErrorId();
		else
			return "";
	}


	/**
	 * @param  errorId
	 * @uml.property  name="errorId"
	 */
	@Override		
	public void setErrorId(String errorId)
	{
		this.errorId = errorId;
	}
}