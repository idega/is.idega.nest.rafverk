/*
 * $Id: IWBundleStarter.java,v 1.3 2007/12/04 04:40:49 tryggvil Exp $
 * Created on Sep 24, 2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk;

import is.idega.nest.rafverk.bean.constants.CaseConstants;
import is.idega.nest.rafverk.business.ElectricalInstallationCaseBusiness;
import is.idega.nest.rafverk.importer.ElectricalInstallationsImportFileHandler;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import com.idega.block.importer.data.ImportFileClass;
import com.idega.block.importer.data.ImportFileClassHome;
import com.idega.block.importer.data.ImportHandler;
import com.idega.block.importer.data.ImportHandlerHome;
import com.idega.block.importer.data.TabSeparatedImportFile;
import com.idega.block.process.business.CaseCodeManager;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWBundleStartable;


/**
 * Last modified: $Date: 2007/12/04 04:40:49 $ by $Author: tryggvil $
 * 
 * @author <a href="mailto:laddi@idega.com">laddi</a>
 * @version $Revision: 1.3 $
 */
public class IWBundleStarter implements IWBundleStartable {

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#start(com.idega.idegaweb.IWBundle)
	 */
	public void start(IWBundle starterBundle) {
		CaseCodeManager caseCodeManager = CaseCodeManager.getInstance();
		caseCodeManager.addCaseBusinessForCode(CaseConstants.CASE_CODE_KEY_ELINST, ElectricalInstallationCaseBusiness.class);
		caseCodeManager.addCaseBusinessForCode(CaseConstants.CASE_CODE_KEY_ELCHN, ElectricalInstallationCaseBusiness.class);
		registerImportHandler();
		registerTabFileType();
	}

	private void registerImportHandler() {
		
		ImportHandlerHome imHandlerHome;
		try {
			imHandlerHome = (ImportHandlerHome) IDOLookup.getHome(ImportHandler.class);
			
			ImportHandler myHandler=null;
			try {
				myHandler = imHandlerHome.findByClassName(ElectricalInstallationsImportFileHandler.class.getName());
			} catch (FinderException e) {
				try {
					myHandler = imHandlerHome.create();
					myHandler.setName("ElectricalInstallationsImportFileHandler");
					myHandler.setClassName(ElectricalInstallationsImportFileHandler.class.getName());
					myHandler.setDescription("ElectricalInstallationsImportFileHandler");
					myHandler.store();
				} catch (CreateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} catch (IDOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void registerTabFileType() {
		
		ImportFileClassHome imHandlerHome;
		try {
			imHandlerHome = (ImportFileClassHome) IDOLookup.getHome(ImportFileClass.class);
			
			ImportFileClass myHandler=null;
			try {
				myHandler = imHandlerHome.findByClassName(TabSeparatedImportFile.class.getName());
			} catch (FinderException e) {
				try {
					myHandler = imHandlerHome.create();
					myHandler.setName("TabSeparatedImportFile");
					myHandler.setClassName(TabSeparatedImportFile.class.getName());
					myHandler.setDescription("TabSeparatedImportFile");
					myHandler.store();
				} catch (CreateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} catch (IDOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#stop(com.idega.idegaweb.IWBundle)
	 */
	public void stop(IWBundle starterBundle) {
	}
}
