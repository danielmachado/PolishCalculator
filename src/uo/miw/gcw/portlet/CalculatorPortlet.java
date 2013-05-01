package uo.miw.gcw.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import uo.miw.gcw.Calculator;
import uo.miw.gcw.Constants;

public class CalculatorPortlet extends GenericPortlet {

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		createURLs(request, response);

		String wID = request.getWindowID();

		Calculator c = (Calculator) request.getPortletSession().getAttribute(
				wID, PortletSession.APPLICATION_SCOPE);

		if (c == null) {
			c = new Calculator();
			request.getPortletSession().setAttribute(wID, c,
					PortletSession.APPLICATION_SCOPE);
		}

		request.setAttribute(Constants.RESULT, c.getResult());
		String jspName = getPortletConfig().getInitParameter("jspView");

		dispatchToJsp(jspName, request, response);

	}

	private void createURLs(RenderRequest request, RenderResponse response) {
		
		PortletURL actionUrlEnter = response.createActionURL();
		actionUrlEnter.setParameter(Constants.ENTER, Constants.ENTER);
		request.setAttribute("actionUrlEnter", actionUrlEnter.toString());

		PortletURL actionUrlPlus = response.createActionURL();
		actionUrlPlus.setParameter(Constants.OPERATOR, Constants.PLUS);
		request.setAttribute("actionUrlPlus", actionUrlPlus.toString());

		PortletURL actionUrlMinus = response.createActionURL();
		actionUrlMinus.setParameter(Constants.OPERATOR, Constants.MINUS);
		request.setAttribute("actionUrlMinus", actionUrlMinus.toString());

		PortletURL actionUrlClr = response.createActionURL();
		actionUrlClr.setParameter(Constants.OPERATOR, Constants.CLR);
		request.setAttribute("actionUrlClr", actionUrlClr.toString());

		PortletURL actionUrlMult = response.createActionURL();
		actionUrlMult.setParameter(Constants.OPERATOR, Constants.MULT);
		request.setAttribute("actionUrlMult", actionUrlMult.toString());

		PortletURL actionUrlDiv = response.createActionURL();
		actionUrlDiv.setParameter(Constants.OPERATOR, Constants.DIV);
		request.setAttribute("actionUrlDiv", actionUrlDiv.toString());

		PortletURL actionUrlMi = response.createActionURL();
		actionUrlMi.setParameter(Constants.OPERATOR, Constants.MI);
		request.setAttribute("actionUrlMi", actionUrlMi.toString());

		PortletURL actionUrlMo = response.createActionURL();
		actionUrlMo.setParameter(Constants.OPERATOR, Constants.MO);
		request.setAttribute("actionUrlMo", actionUrlMo.toString());
	}

	private void dispatchToJsp(String jspName, RenderRequest request,
			RenderResponse response) throws PortletException, IOException {

		getPortletContext().getRequestDispatcher(jspName).include(request,
				response);

	}

	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {

		Calculator c = (Calculator) request.getPortletSession().getAttribute(
				request.getWindowID(), PortletSession.APPLICATION_SCOPE);

		if (request.getParameter(Constants.ENTER) == null) {
			c.operate(request.getParameter(Constants.OPERATOR));
		} else {
			c.addValue(request.getParameter(Constants.VALUE));
		}

		QName event = new QName("http://uo.miw.gcw/events", "polish.modified");
		response.setEvent(event, request.getWindowID());
	}

}
