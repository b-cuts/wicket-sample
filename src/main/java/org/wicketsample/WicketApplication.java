/*
 * This is free and unencumbered software released into the public domain.
 */
/*
 * This is free and unencumbered software released into the public domain.
 */
package org.wicketsample;

import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/*
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.directory.fortress.web.control.WicketSession;
import org.apache.wicket.Session;
*/

/**
 * Wicket Sample WebApplication impl.
 *
 * @author Shawn McKinney
 * @version $Rev$
 */
public class WicketApplication extends WebApplication
{
	// TODO STEP: uncomment save fortress session to wicket session:
/*
	@Override
	public Session newSession(Request request, Response response)
	{
		return new WicketSession(request);
	}
*/
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// TODO STEP: uncomment to enable injection of fortress spring beans:
		//getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		// Catch runtime exceptions this way:
		getRequestCycleListeners().add( new AbstractRequestCycleListener()
		{
			@Override
			public IRequestHandler onException( RequestCycle cycle, Exception e )
			{
				return new RenderPageRequestHandler( new PageProvider( new ErrorPage( e ) ) );
			}
		} );
		getMarkupSettings().setStripWicketTags(true);
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return LaunchPage.class;
	}
}
