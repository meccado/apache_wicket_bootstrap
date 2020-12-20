package za.co.moeketsi.wicket.pages;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends AbstractBasePage {
	private static final long serialVersionUID = 1L;
	

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
    }
}
