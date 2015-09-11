package za.co.moeketsi.wicket.panels;

import java.util.Calendar;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class Footer extends Panel {
	private static final long serialVersionUID = -5487715120979234284L;

	public Footer(String id) {
		super(id);
		
		add(new Label("copyright", Calendar.getInstance().get(Calendar.YEAR)));
		add(new Label("wicket-version", getApplication().getFrameworkSettings().getVersion()));
	}
}
