/*
 * Modified is.idega.idegaweb.egov.cases.presentation.MyCases - 
 * NOTE: that is a quick hack, need to be reviewed/refactored
 * 
 * $Id: MyCases.java,v 1.3 2007/06/21 15:10:33 thomas Exp $ Created on Nov 7, 2005
 * 
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 * 
 * This software is the proprietary information of Idega hf. Use is subject to license terms.
 */
package is.idega.nest.rafverk.block;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationMessageBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationRendererBusiness;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.util.DataConverter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.business.IBOLookup;
import com.idega.business.IBORuntimeException;
import com.idega.business.IBOService;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.Street;
import com.idega.presentation.IWContext;
import com.idega.presentation.Layer;
import com.idega.presentation.text.Heading1;
import com.idega.presentation.text.Link;
import com.idega.presentation.text.Text;
import com.idega.presentation.ui.DropdownMenu;
import com.idega.presentation.ui.Form;
import com.idega.presentation.ui.Label;
import com.idega.presentation.ui.TextArea;
import com.idega.presentation.ui.util.SelectorUtility;
import com.idega.user.data.User;
import com.idega.util.IWTimestamp;
import com.idega.util.StringHandler;

public class MyCases extends CasesList {

	private static final String PARAMETER_CASE_CATEGORY_PK = "prm_case_category_pk";
	private static final String PARAMETER_SUB_CASE_CATEGORY_PK = "prm_sub_case_category_pk";
	private static final String PARAMETER_CASE_TYPE_PK = "prm_case_type_pk";

	protected String getBlockID() {
		return "myCases";
	}

	protected Collection getCases(User user) throws RemoteException {
		return getBusiness().getMyCases(user);
	}

	protected void showProcessor(IWContext iwc, Object casePK) throws RemoteException {
		Form form = new Form();
		form.setStyleClass("adminForm");
		form.setStyleClass("overview");
		form.maintainParameter(PARAMETER_CASE_PK);
		form.addParameter(PARAMETER_ACTION, "");

		boolean useSubCategories = getCasesBusiness(iwc).useSubCategories();

		ElectricalInstallation theCase = getElectricalInstallation(casePK, iwc);
		if (theCase == null) {
			add(new Text("Erro: Case not found"));
			return;
		}
		//theCase = getBusiness().getGeneralCase(casePK);

		//CaseCategory category = theCase.getCaseCategory();
		//CaseCategory parentCategory = category.getParent();
		//CaseType type = theCase.getCaseType();
		//ICFile attachment = theCase.getAttachment();
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

//		if (theCase.isPrivate()) {
//			section.add(getAttentionLayer(getResourceBundle().getLocalizedString(getPrefix() + "case.is_private", "The sender wishes that this case be handled as confidential.")));
//		}

		Layer clear = new Layer(Layer.DIV);
		clear.setStyleClass("Clear");

		SelectorUtility util = new SelectorUtility();
//		DropdownMenu categories = (DropdownMenu) util.getSelectorFromIDOEntities(new DropdownMenu(PARAMETER_CASE_CATEGORY_PK), getBusiness().getCaseCategories(), "getName");
//		categories.keepStatusOnAction(true);
//		categories.setSelectedElement(parentCategory != null ? parentCategory.getPrimaryKey().toString() : category.getPrimaryKey().toString());
//		categories.setStyleClass("caseCategoryDropdown");
//
//		DropdownMenu subCategories = new DropdownMenu(PARAMETER_SUB_CASE_CATEGORY_PK);
//		subCategories.keepStatusOnAction(true);
//		subCategories.setSelectedElement(category.getPrimaryKey().toString());
//		subCategories.setStyleClass("subCaseCategoryDropdown");
//
//		if (parentCategory != null) {
//			Collection collection = getCasesBusiness(iwc).getSubCategories(parentCategory);
//			if (collection.isEmpty()) {
//				subCategories.addMenuElement(category.getPrimaryKey().toString(), getResourceBundle().getLocalizedString("case_creator.no_sub_category", "no sub category"));
//			}
//			else {
//				Iterator iter = collection.iterator();
//				while (iter.hasNext()) {
//					CaseCategory subCategory = (CaseCategory) iter.next();
//					subCategories.addMenuElement(subCategory.getPrimaryKey().toString(), subCategory.getLocalizedCategoryName(iwc.getCurrentLocale()));
//				}
//			}
//		}

//		DropdownMenu types = (DropdownMenu) util.getSelectorFromIDOEntities(new DropdownMenu(PARAMETER_CASE_TYPE_PK), getBusiness().getCaseTypes(), "getName");
//		types.keepStatusOnAction(true);
//		types.setSelectedElement(type.getPrimaryKey().toString());
//		types.setStyleClass("caseTypeDropdown");
//
//		HiddenInput hiddenType = new HiddenInput(PARAMETER_CASE_TYPE_PK, type.getPrimaryKey().toString());

		DropdownMenu statuses = new DropdownMenu(PARAMETER_STATUS);
		statuses.addMenuElement(getBusiness().getCaseStatusPending().getStatus(), getBusiness().getLocalizedCaseStatusDescription(theCase, getBusiness().getCaseStatusPending(), iwc.getCurrentLocale()));
		statuses.addMenuElement(getBusiness().getCaseStatusWaiting().getStatus(), getBusiness().getLocalizedCaseStatusDescription(theCase, getBusiness().getCaseStatusWaiting(), iwc.getCurrentLocale()));
		statuses.addMenuElement(getBusiness().getCaseStatusReady().getStatus(), getBusiness().getLocalizedCaseStatusDescription(theCase, getBusiness().getCaseStatusReady(), iwc.getCurrentLocale()));
		statuses.setSelectedElement(theCase.getStatus());
		//statuses.setStyleClass("caseStatusDropdown");

//		Layer message = new Layer(Layer.SPAN);
//		message.add(new Text(TextSoap.formatText(theCase.getMessage())));

		Layer createdDate = new Layer(Layer.SPAN);
		createdDate.add(new Text(created.getLocaleDateAndTime(iwc.getCurrentLocale(), IWTimestamp.SHORT, IWTimestamp.SHORT)));

		TextArea reply = new TextArea(PARAMETER_REPLY);
		reply.setStyleClass("textarea");
		reply.keepStatusOnAction(true);

//		if (getBusiness().useTypes()) {
//			Layer element = new Layer(Layer.DIV);
//			element.setStyleClass("formItem");
//			Label label = new Label(getResourceBundle().getLocalizedString("case_type", "Case type"), types);
//			element.add(label);
//			element.add(types);
//			section.add(element);
//		}
//		else {
//			form.add(hiddenType);
//		}

		Layer element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
//		Label label = new Label(getResourceBundle().getLocalizedString("case_category", "Case category"), categories);
//		element.add(label);
//		element.add(categories);
//		section.add(element);

//		if (useSubCategories) {
//			try {
//				RemoteScriptHandler rsh = new RemoteScriptHandler(categories, subCategories);
//				rsh.setRemoteScriptCollectionClass(CaseCategoryCollectionHandler.class);
//				element.add(rsh);
//			}
//			catch (IllegalAccessException iae) {
//				iae.printStackTrace();
//			}
//			catch (InstantiationException ie) {
//				ie.printStackTrace();
//			}
//
//			element = new Layer(Layer.DIV);
//			element.setStyleClass("formItem");
//			label = new Label(getResourceBundle().getLocalizedString("sub_case_category", "Sub case category"), subCategories);
//			element.add(label);
//			element.add(subCategories);
//			section.add(element);
//		}

		// electrician Name
		String electricianName = theCase.getElectrician().getDisplayName();
		addLabelWithValue("Rafverktaki", electricianName, section);
		
		// energy consumer name
		String consumerName = theCase.getEnergyConsumerName();
		addLabelWithValue("Nafn orkukaupandi", consumerName, section);
		
		// address
		
		String name = null, use = null , comment = null, streetName = null, streetNumber = null, postalCodeNumber = null, postalCodeName = null;
		RealEstate realEstate = theCase.getRealEstate();
		if (realEstate != null) {
			name = realEstate.getName();
			use = realEstate.getUse();
			comment = realEstate.getComment();
			Street street = realEstate.getStreet();
			streetNumber = realEstate.getStreetNumber();
			if (street != null) {
				streetName = street.getName();
				PostalCode postalCode = street.getPostalCode();
				if (postalCode != null) {
					postalCodeNumber = postalCode.getPostalCode();
					postalCodeName = postalCode.getName();
				}
			}
		}	
		addLabelWithValue("Veitstaður", name, section);
		addLabelWithValue("Notkun",  use, section);
		addLabelWithValue("Skýringar", comment, section);
		addLabelWithValue("Gata", streetName, section);
		addLabelWithValue("Gatanúmer", streetNumber, section);
		addLabelWithValue("Póstnúmer", postalCodeNumber, section);
		addLabelWithValue("Sveitarfélag", postalCodeName, section);
		
		
		// type
		String type = theCase.getType();
		String localizedType = DataConverter.lookup(InitialData.NOTKUNARFLOKKUR, type);
		addLabelWithValue("Notkunarflokkur", localizedType, section);
		
		// current line modification
		String currentLineModification = theCase.getCurrentLineModification();
		String localizedCurrentLineModification = DataConverter.lookup(InitialData.HEIMTAUG, currentLineModification);
		addLabelWithValue("Heimtaug", localizedCurrentLineModification, section);

		// current line connection modification
		String currentLineConnectionModification = theCase.getCurrentLineConnectionModification();
		String localizedCurrentLineConnectionModification =  DataConverter.lookup(InitialData.HEIMTAUG_TENGIST, currentLineConnectionModification);
		addLabelWithValue("Heimtaug tengist í", localizedCurrentLineConnectionModification, section);
		
		// link to pdf 
		Rafverktaka rafverktaka = Rafverktaka.getInstanceFromElectricalInstallation(theCase, getElectricalInstallationBusiness(iwc));
		String url = "";
		try {
			url = getElectricalInstallationRendererBusiness(iwc).getPDFApplication(rafverktaka.getElectricalInstallation());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Layer pdfURL = new Layer(Layer.SPAN);
		Link link = new Link("Þjónustubeiðni",url);
		link.setTarget(Link.TARGET_BLANK_WINDOW);
		pdfURL.add(link);
		
		element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
		Label label = new Label();
		label.setLabel(getResourceBundle().getLocalizedString("pdf", "PDF"));
		element.add(label);
		element.add(pdfURL);
		section.add(element);
		

		element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
		label = new Label();
		label.setLabel(getResourceBundle().getLocalizedString("created_date", "Created date"));
		element.add(label);
		element.add(createdDate);
		section.add(element);

//		if (attachment != null) {
//			Link link = new Link(new Text(attachment.getName()));
//			link.setFile(attachment);
//			link.setTarget(Link.TARGET_BLANK_WINDOW);
//
//			Layer attachmentSpan = new Layer(Layer.SPAN);
//			attachmentSpan.add(link);
//
//			element = new Layer(Layer.DIV);
//			element.setStyleClass("formItem");
//			label = new Label();
//			label.setLabel(getResourceBundle().getLocalizedString("attachment", "Attachment"));
//			element.add(label);
//			element.add(attachmentSpan);
//			section.add(element);
//		}

//		element = new Layer(Layer.DIV);
//		element.setStyleClass("formItem");
//		element.setStyleClass("informationItem");
//		label = new Label();
//		label.setLabel(getResourceBundle().getLocalizedString(getPrefix() + "message", "Message"));
//		element.add(label);
//		element.add(message);
//		section.add(element);

		section.add(clear);

		

		heading = new Heading1(getResourceBundle().getLocalizedString("handler_overview", "Handler overview"));
		heading.setStyleClass("subHeader");
		form.add(heading);

		section = new Layer(Layer.DIV);
		section.setStyleClass("formSection");
		form.add(section);

		element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
		label = new Label(getResourceBundle().getLocalizedString("status", "status"), statuses);
		element.add(label);
		element.add(statuses);
		section.add(element);

		element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");
		label = new Label(getResourceBundle().getLocalizedString("reply", "Reply"), reply);
		element.add(label);
		element.add(reply);
		section.add(element);

		section.add(clear);
		
		
//		heading = new Heading1(getResourceBundle().getLocalizedString("handler_overview", "Handler overview"));
//		heading.setStyleClass("subHeader");
//		form.add(heading);
//
//		section = new Layer(Layer.DIV);
//		section.setStyleClass("formSection");
//		form.add(section);
//		section.add(clear);
//
////		element = new Layer(Layer.DIV);
////		element.setStyleClass("formItem");
////		
////		label = new Label(getResourceBundle().getLocalizedString("status", "status"), statuses);
////		element.add(label);
////		element.add(statuses);
////		section.add(element);
//	
//		element = new Layer(Layer.DIV);
//		element.setStyleClass("formItem");
//		
//		label = new Label();
//		label.setLabel(getResourceBundle().getLocalizedString("status", "status"));
//		element.add(label);
//		Layer span = new Layer(Layer.SPAN);
//		span.add(statuses);
//		element.add(span);
//		section.add(element);
//		
//
//		element = new Layer(Layer.DIV);
//		element.setStyleClass("formItem");
//		label = new Label(getResourceBundle().getLocalizedString("reply", "Reply"), reply);
//		element.add(label);
//		element.add(reply);
//		section.add(element);



//		Collection logs = getCasesBusiness(iwc).getCaseLogs(theCase);
//		if (!logs.isEmpty()) {
//			Iterator iter = logs.iterator();
//			while (iter.hasNext()) {
//				CaseLog log = (CaseLog) iter.next();
//				form.add(getHandlerLayer(iwc, this.getResourceBundle(), theCase, log));
//			}
//		}

		Layer bottom = new Layer(Layer.DIV);
		bottom.setStyleClass("bottom");
		form.add(bottom);

		Link back = getButtonLink(getResourceBundle().getLocalizedString("back", "Back"));
		back.setStyleClass("homeButton");
		back.setValueOnClick(PARAMETER_ACTION, String.valueOf(ACTION_VIEW));
		back.setToFormSubmit(form);
		bottom.add(back);

		Link next = getButtonLink(getResourceBundle().getLocalizedString("process", "Process"));
		next.setValueOnClick(PARAMETER_ACTION, String.valueOf(ACTION_SAVE));
		next.setToFormSubmit(form);
		bottom.add(next);

		add(form);
	}

	protected String save(IWContext iwc) throws RemoteException {
		Object casePK = iwc.getParameter(PARAMETER_CASE_PK);
//		Object caseCategoryPK = iwc.getParameter(PARAMETER_CASE_CATEGORY_PK);
//		Object subCaseCategoryPK = iwc.getParameter(PARAMETER_SUB_CASE_CATEGORY_PK);
//		Object caseTypePK = iwc.getParameter(PARAMETER_CASE_TYPE_PK);
		String status = iwc.getParameter(PARAMETER_STATUS);
		String reply = iwc.getParameter(PARAMETER_REPLY);

		ElectricalInstallation electricalInstallation = getElectricalInstallation(casePK, iwc);
		electricalInstallation.setStatus(status);
		electricalInstallation.store();
		
		// send user message
		
		ElectricalInstallationMessageBusiness messageBusiness = getElectricalInstallationMessageBusiness(iwc);
		User sender = iwc.getCurrentUser();
		return messageBusiness.createStatusChangedUserMessage(electricalInstallation, sender, reply);
	}

	protected boolean showCheckBox() {
		return false;
	}
	
	//+++++++++++++++++++ added by thomas
	
	private void addLabelWithValue(String label, String value, Layer section) {
		Layer valueLayer = new Layer(Layer.SPAN);
		if (value == null) {
			value = StringHandler.EMPTY_STRING;
		}
		valueLayer.add(new Text(value));
		
		Label tempLabel = new Label();
		tempLabel.setLabel(label);
		
		Layer element = new Layer(Layer.DIV);
		element.setStyleClass("formItem");

		element.add(tempLabel);
		element.add(valueLayer);
		section.add(element);
	}
	
	private ElectricalInstallation getElectricalInstallation(Object casePK, IWContext iwc) {
		try {
			return getElectricalInstallationBusiness(iwc).getElectricalInstallationByPrimaryKey(casePK);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
		catch (FinderException e) {
			e.printStackTrace();
			return null;
		}
	}
 	
	private ElectricalInstallationBusiness getElectricalInstallationBusiness(IWContext iwc) {
		return (ElectricalInstallationBusiness) getServiceBean(ElectricalInstallationBusiness.class, iwc);
	}
	
	private ElectricalInstallationRendererBusiness getElectricalInstallationRendererBusiness(IWContext iwc) {
		return (ElectricalInstallationRendererBusiness) getServiceBean(ElectricalInstallationRendererBusiness.class,iwc);
	}
	
	private ElectricalInstallationMessageBusiness getElectricalInstallationMessageBusiness(IWContext iwc) {
		return (ElectricalInstallationMessageBusiness) getServiceBean(ElectricalInstallationMessageBusiness.class, iwc);
	}
	
	private IBOService getServiceBean(Class serviceClass, IWContext iwc ) {
		IBOService service = null;
		try {
			service = IBOLookup.getServiceInstance(iwc.getApplicationContext(), serviceClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return service;
	}	
	
}