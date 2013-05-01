package uo.miw.gcw.portlet;

import java.io.IOException;

import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import uo.miw.gcw.Calculator;
import uo.miw.gcw.Constants;

public class CalculatorViewerPortlet extends GenericPortlet {
	
	private Calculator c;

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		String jspName = getInitParameter("viewer");

		response.setContentType("text/html");
		
		if(c==null){
			c = new Calculator();
		}
		
		request.setAttribute(Constants.LINES, c.getLines());

		dispatchToJsp(jspName, request, response);

	}

	@ProcessEvent(qname = "{http://uo.miw.gcw/events}polish.modified")
	public void polishModified(EventRequest request, EventResponse response)
			throws PortletException, IOException {

		String wID = (String) request.getEvent().getValue();

		PortletSession session = request.getPortletSession();
		c = (Calculator) session.getAttribute(wID,
				PortletSession.APPLICATION_SCOPE);

		if (c == null) {
			c = new Calculator();
			session.setAttribute(wID, c, PortletSession.APPLICATION_SCOPE);
		}

	}

	private void dispatchToJsp(String jspName, RenderRequest request,
			RenderResponse response) throws PortletException, IOException {

		getPortletContext().getRequestDispatcher(jspName).include(request,
				response);

	}

}
