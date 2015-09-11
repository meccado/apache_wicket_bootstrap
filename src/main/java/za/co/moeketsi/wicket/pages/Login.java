package za.co.moeketsi.wicket.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;


public class Login extends AbstractBasePage {
	private static final long serialVersionUID = 1L;
	
	public Login(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		// TODO Auto-generated method stub
		super.onInitialize() ;
		
		LoginForm loginForm = new LoginForm("loginForm");
		add(loginForm);
	}
	
	class LoginForm extends BootstrapForm<Void> {

		private static final long serialVersionUID = 9020491262182659478L;
		

		public LoginForm(String componentId) {
			super(componentId);
			
			IndicatingAjaxButton submit = new IndicatingAjaxButton("submit", Model.of("Submit")) {

				private static final long serialVersionUID = 3720720385904525027L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					//super.onSubmit(target, form);
					target.add(LoginForm.this);
				}
				
				@Override
				protected void onError(AjaxRequestTarget target, Form<?> form) {

					//super.onError(target, form);
					target.add(LoginForm.this);
				}
				
			};
			add(submit);
		} 
		
	}
}
