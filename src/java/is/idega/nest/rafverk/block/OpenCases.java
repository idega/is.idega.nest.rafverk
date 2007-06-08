/*
 * Stupid 1-1 copy of is.idega.idegaweb.egov.cases.presentation.OpenCases.
 * 
 * Done to change extension of CasesProcessor to CasesList.
 * 
 *   
 * $Id: OpenCases.java,v 1.1 2007/06/08 17:08:20 thomas Exp $ Created on Nov 7, 2005
 * 
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 * 
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 */
package is.idega.nest.rafverk.block;

import is.idega.idegaweb.egov.cases.data.CaseCategory;
import is.idega.idegaweb.egov.cases.data.CaseType;
import is.idega.idegaweb.egov.cases.data.GeneralCase;
import is.idega.idegaweb.egov.cases.util.CaseConstants;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.business.IBORuntimeException;
import com.idega.core.builder.data.ICPage;
import com.idega.core.file.data.ICFile;
import com.idega.event.IWPageEventListener;
import com.idega.presentation.IWContext;
import com.idega.presentation.Layer;
import com.idega.presentation.text.Heading1;
import com.idega.presentation.text.Link;
import com.idega.presentation.text.Text;
import com.idega.presentation.ui.Form;
import com.idega.presentation.ui.Label;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;

public class OpenCases extends CasesList implements IWPageEventListener {

	private ICPage iMyCasesPage;

	protected String getBlockID() {
		return "openCases";
	}

	public boolean actionPerformed(IWContext iwc) {
		if (iwc.isParameterSet(PARAMETER_CASE_PK)) {
			Object casePK = iwc.getParameter(PARAMETER_CASE_PK);

			try {
				getCasesBusiness(iwc).takeCase(casePK, iwc.getCurrentUser(), iwc);
				return true;
			}
			catch (RemoteException re) {
				throw new IBORuntimeException(re);
			}
			catch (FinderException fe) {
				fe.printStackTrace();
			}
		}
		return false;
	}

	protected Collection getCases(User user) throws RemoteException {
		Collection groups = getUserBusiness().getUserGroupsDirectlyRelated(user);
		return getBusiness().getOpenCases(groups);
	}

	protected void showProcessor(IWContext iwc, Object casePK) throws RemoteException {
		Form form = new Form();
		form.setStyleClass("adminForm");
		form.setStyleClass("overview");
		form.setEventListener(this.getClass());
		if (this.iMyCasesPage != null) {
			form.setPageToSubmitTo(this.iMyCasesPage);
		}
		form.addParameter(PARAMETER_ACTION, "");
		form.maintainParameter(PARAMETER_CASE_PK);

		GeneralCase theCase = null;
		try {
			theCase = getBusiness().getGeneralCase(casePK);
		}
		catch (FinderException fe) {
			fe.printStackTrace();
			throw new IBORuntimeException(fe);
		}
		CaseCategory category = theCase.getCaseCategory();
		CaseCategory parentCategory = category.getParent();
		CaseType type = theCase.getCaseType();
		ICFile attachment = theCase.getAttachment();
		User owner = theCase.getOwner();
		IWTimestamp created = new IWTimestamp(theCase.getCreated());

		form.add(getPersonInfo(iwc, owner));

		Heading1 heading = new Heading1(getResourceBundle().getLocalizedString(getPrefix() + "case_overview", "Case overview"));
		heading.setStyleClass("subHeader");
		heading.setStyleClass("topSubHeader");
		form.add(heading);

		Layer section = new Layer(Layer.DIV);
		section.setStyleClass("formSection");
		form.add(section);

		if (theCase.isPrivate()) {
			section.add(getAttentionLayer(getResourceBundle().getLocalizedString(getPrefix() + "case.is_private", "The sender wishes that this case be handled as confidential.")));
		}

		Layer caseType = new Layer(Layer.SPAN);
		caseType.add(new Text(type.getName()));

		Layer caseCategory = new Layer(Layer.SPAN);
		caseCategory.add(new Text(category.getLocalizedCategoryName(iwc.getCurrentLocale())));

		Layer message = new Layer(Layer.SPAN);
		message.add(new Text(theCase.getMessage()));

		Layer createdDate = new Layer(Layer.SPAN);
		createdDate.add(new Text(created.getLocaleDateAndTime(iwc.getCurrentLocale(), IWTimestamp.SHORT, IWTimestamp.SHORT)));

		if (getBusiness().useTypes()) {
			Layer element = new Layer(Layer.DIV);
			element.setStyleClass("formItem");
			Label label = new Label();
			label.setLabel(getResourceBundle().getLocalizedString("case_type", "Case type"));
			element.add(label);
			element.add(caseType);
			section.add(element);
		}

		if (parentCategory != null) {
			Layer parentCaseCategory = new Layer(Layer.SPAN);
			parentCaseCategory.add(new Text(parentCategory.getLocalizedCategoryName(iwc.getCurrentLocale())));

			Layer element = new Layer(Layer.DIV);
			element.setStyleClass("formItem");
			Label label = new Label();
			label.setLabel(getResourceBundle().getLocalizedString("case_category", "Case category"));
			element.add(label);
			element.add(parentCaseCategory);
			section.add(element);

			element = new Layer(Layer.DIV);
			element.setStyleClass("formItem");
			label = new Label();
			label.setLabel(getResourceBundle().getLocalizedString("sub_case_category", "Sub case category"));
			element.add(label);
			element.add(caseCategory);
			section.add(element);
		}
		else {
			Layer element = new Layer(Layer.DIV);
			element.setStyleClass("formItem");
			Label label = new Label();
			label.setLabel(getResourceBundle().getLocalizedString("case_category", "Case category"));
			element.add(label);
			element.add(caseCategory);
			section.add(element);
		}

		Layer element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
		Label label = new Label();
		label.setLabel(getResourceBundle().getLocalizedString("created_date", "Created date"));
		element.add(label);
		element.add(createdDate);
		section.add(element);

		if (attachment != null) {
			Link link = new Link(new Text(attachment.getName()));
			link.setFile(attachment);
			link.setTarget(Link.TARGET_BLANK_WINDOW);

			Layer attachmentSpan = new Layer(Layer.SPAN);
			attachmentSpan.add(link);

			element = new Layer(Layer.DIV);
			element.setStyleClass("formItem");
			label = new Label();
			label.setLabel(getResourceBundle().getLocalizedString("attachment", "Attachment"));
			element.add(label);
			element.add(attachmentSpan);
			section.add(element);
		}

		element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
		label = new Label();
		label.setLabel(getResourceBundle().getLocalizedString("message", "Message"));
		element.add(label);
		element.add(message);
		section.add(element);

		Layer clear = new Layer(Layer.DIV);
		clear.setStyleClass("Clear");
		section.add(clear);

		Layer bottom = new Layer(Layer.DIV);
		bottom.setStyleClass("bottom");
		form.add(bottom);

		Link back = getButtonLink(getResourceBundle().getLocalizedString("back", "Back"));
		back.setStyleClass("homeButton");
		back.addParameter(PARAMETER_ACTION, String.valueOf(ACTION_VIEW));
		bottom.add(back);

		if (iwc.getAccessController().hasRole(CaseConstants.ROLE_CASES_SUPER_ADMIN, iwc)) {
			Link next = getButtonLink(getResourceBundle().getLocalizedString(getPrefix() + "allocate_case", "Allocate case"));
			next.addParameter(PARAMETER_ACTION, String.valueOf(ACTION_ALLOCATION_FORM));
			next.maintainParameter(PARAMETER_CASE_PK, iwc);
			bottom.add(next);
		}

		Link next = getButtonLink(theCase.getCaseStatus().equals(getBusiness().getCaseStatusPending()) ? getResourceBundle().getLocalizedString(getPrefix() + "take_over_case", "Take over case") : getResourceBundle().getLocalizedString(getPrefix() + "take_case", "Take case"));
		next.setValueOnClick(PARAMETER_ACTION, String.valueOf(ACTION_PROCESS));
		next.setToFormSubmit(form);
		bottom.add(next);

		add(form);
	}

	protected void save(IWContext iwc) throws RemoteException {
		String casePK = iwc.getParameter(PARAMETER_CASE_PK);
		if (casePK != null) {
			try {
				GeneralCase theCase = getCasesBusiness(iwc).getGeneralCase(new Integer(casePK));
				Object userPK = iwc.getParameter(PARAMETER_USER);
				String message = iwc.getParameter(PARAMETER_MESSAGE);

				User user = getUserBusiness().getUser(new Integer(userPK.toString()));

				getCasesBusiness(iwc).allocateCase(theCase, user, message, iwc.getCurrentUser(), iwc);
			}
			catch (RemoteException e) {
				throw new IBORuntimeException(e);
			}
			catch (FinderException e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean showCheckBox() {
		return true;
	}

	public void setMyCasesPage(ICPage myCasesPage) {
		this.iMyCasesPage = myCasesPage;
	}
}