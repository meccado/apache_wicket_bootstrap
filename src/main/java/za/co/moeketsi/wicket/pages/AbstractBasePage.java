package za.co.moeketsi.wicket.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.moeketsi.wicket.panels.Footer;
import za.co.moeketsi.wicket.panels.Header;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.IeEdgeMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MobileViewportMetaTag;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;

public abstract class AbstractBasePage extends WebPage {

	private static final long serialVersionUID = -5635232836335270839L;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	public AbstractBasePage() {
		super();
	}
	public AbstractBasePage(final PageParameters parameters) {
		super(parameters);
		
		add(new HtmlTag("html"));
        add(new MobileViewportMetaTag("viewport"));
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket Bootstrap Application")));
        add(new MetaTag("author", Model.of("author"), Model.of("Moeketsi Mokoena <tsw603gp@gmail.com>")));
        
        add(new Header("header"));
        add(new Footer("footer"));
	}
	


	@Override
	public void renderHead(IHeaderResponse response) 
	{
		super.renderHead(response);
		Bootstrap.renderHead(response);
		// font-awesome (optional)
		response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
	}
}
