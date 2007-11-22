package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class UserMessagesBusinessHomeImpl extends IBOHomeImpl implements UserMessagesBusinessHome {

	public Class getBeanInterfaceClass() {
		return UserMessagesBusiness.class;
	}

	public UserMessagesBusiness create() throws CreateException {
		return (UserMessagesBusiness) super.createIBO();
	}
}