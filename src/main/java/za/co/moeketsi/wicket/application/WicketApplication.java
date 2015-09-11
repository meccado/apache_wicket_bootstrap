package za.co.moeketsi.wicket.application;

import org.apache.wicket.Application;
import org.apache.wicket.ResourceBundles;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.moeketsi.wicket.pages.AbstractBasePage;
import za.co.moeketsi.wicket.pages.HomePage;
import za.co.moeketsi.wicket.pages.Login;
import za.co.moeketsi.wicket.pages.Logout;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICoreJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIDraggableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIMouseJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIResizableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIWidgetJavaScriptReference;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see za.co.mycompany.mywicketproject.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication
{
	

	private static final String BASE_PACKAGE_FOR_PAGES =  AbstractBasePage.class.getPackage().getName();
	private static final String DEFAULT_ENCODING = "UTF-8";

	protected final Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		BootstrapSettings settings = new BootstrapSettings();
		ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.United){
			{
				/*available().add(new MetroTheme());
				available().add(new GoogleTheme());
				available().add(new WicketTheme());
				available().add(new BootstrapTheme());*/
			}
		};
		settings.setJsResourceFilterName("footer-container")
		.setThemeProvider(themeProvider);
		Bootstrap.install(this, settings);
		BootstrapLess.install(this);
		
		getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
		getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);

		if (getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT)) {
			getMarkupSettings().setStripWicketTags(true);
			getMarkupSettings().setStripComments(true);
			getMarkupSettings().setCompressWhitespace(true);

			getDebugSettings().setAjaxDebugModeEnabled(true);
			getDebugSettings().setComponentUseCheck(true);
			getDebugSettings().setDevelopmentUtilitiesEnabled(true);

			getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		}

		ResourceBundles bundles = getResourceBundles();

		getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bootstrap-extensions.js",
				JQueryUICoreJavaScriptReference.instance(),
				JQueryUIWidgetJavaScriptReference.instance(),
				JQueryUIMouseJavaScriptReference.instance(),
				JQueryUIDraggableJavaScriptReference.instance(),
				JQueryUIResizableJavaScriptReference.instance(),
				Html5PlayerJavaScriptReference.instance()
				);

		bundles.addCssBundle(WicketApplication.class, "bootstrap-extensions.css",
				Html5PlayerCssReference.instance(),
				OpenWebIconsCssReference.instance()
				);
		//new AnnotatedMountScanner().scanPackage(BASE_PACKAGE_FOR_PAGES).mount(this);
		mountPage("/login", Login.class);
		mountPage("/logout", Logout.class);
	}

	public static WicketApplication get() {
		return (WicketApplication) Application.get();
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SSAuthenticatedWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return Login.class;
	}
}