package za.co.moeketsi.wicket.pages;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;


@AuthorizeInstantiation(Roles.USER)
public class Logout extends WebPage {
	private static final long serialVersionUID = 1L;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		getSession().invalidate();
		throw new RestartResponseException(Login.class);
	}
}
