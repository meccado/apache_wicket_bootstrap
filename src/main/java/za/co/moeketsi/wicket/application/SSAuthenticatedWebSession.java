package za.co.moeketsi.wicket.application;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class SSAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 2350262171667007890L;

	public SSAuthenticatedWebSession(Request request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean authenticate(String username, String password) {
		boolean success = username.equals("guest") && password.equals("guest");
		return success;
	}

	@Override
	public Roles getRoles() {
        return new Roles(Roles.USER);
	}

}
