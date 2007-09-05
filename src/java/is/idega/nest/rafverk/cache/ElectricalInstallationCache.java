/*
 * $Id: ElectricalInstallationCache.java,v 1.1 2007/09/05 16:33:16 thomas Exp $
 * Created on Aug 24, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.cache;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.idega.repository.data.Instantiator;
import com.idega.repository.data.Singleton;
import com.idega.repository.data.SingletonRepository;
import com.idega.user.data.User;


/**
 * 
 *  Last modified: $Date: 2007/09/05 16:33:16 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ElectricalInstallationCache implements Singleton {
	
private static Instantiator instantiator = new Instantiator() { public Object getInstance() { return new ElectricalInstallationCache();}};
	
	private ElectricalInstallationCache() {
		// empty
	}
	
	public static ElectricalInstallationCache getInstance() {
		return (ElectricalInstallationCache) SingletonRepository.getRepository().getInstance(ElectricalInstallationCache.class, instantiator);
	}	
	
	private Set changesForUser = Collections.synchronizedSet(new HashSet());
	
	public void addChangeForUser(User user) {
		Object pk = user.getPrimaryKey();
		changesForUser.add(pk);
	}
	
	public boolean changesForUser(User user) {
		Object pk = user.getPrimaryKey();
		return changesForUser.remove(pk);
	}
	
	
}
