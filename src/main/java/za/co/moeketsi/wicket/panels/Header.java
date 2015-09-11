package za.co.moeketsi.wicket.panels;

import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import za.co.moeketsi.wicket.pages.HomePage;
import za.co.moeketsi.wicket.pages.Logout;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarText;

public class Header extends Panel {
	private static final long serialVersionUID = -5487715120979234284L;

	public Header(String id) {
		super(id);
		add(newNavbar("navbar"));
	}
	
	
    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);

        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);

        //navbar.setBrandImage(imageResourceReference, imageAltAttrModel);
        // show brand name
        navbar.setBrandName(Model.of("Apache Wicket Bootstrap Application"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                        new NavbarButton<Void>(HomePage.class, Model.of("Home")).setIconType(GlyphIconType.home),

                        new NavbarExternalLink(Model.of("https://github.com/l0rdn1kk0n/wicket-bootstrap"))
                                .setLabel(Model.of("Github"))
                                .setTarget(NavbarExternalLink.Target.blank)
                                .setIconType(GlyphIconType.export))
        );
        
        NavbarButton<Logout> navbarButton = new NavbarButton<Logout>(Logout.class, Model.of("Logout"))
				.setIconType(GlyphIconType.off);
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT, navbarButton));
		MetaDataRoleAuthorizationStrategy.authorize(navbarButton, Component.RENDER, Roles.USER);
		
        navbar.addComponents(new NavbarText(navbar.newExtraItemId(), "Plain text").position(Navbar.ComponentPosition.RIGHT));

        return navbar;
    }
}
