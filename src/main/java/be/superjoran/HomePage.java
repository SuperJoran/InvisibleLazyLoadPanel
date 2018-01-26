package be.superjoran;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import java.util.concurrent.TimeUnit;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 5403835843096891606L;

    @Override
    protected void onInitialize() {
        super.onInitialize();


        WebMarkupContainer initiallyInvisibleMarkupContainer = new WebMarkupContainer("initiallyInvisibleMarkupContainer");
        initiallyInvisibleMarkupContainer.setOutputMarkupId(true);
        initiallyInvisibleMarkupContainer.setOutputMarkupPlaceholderTag(true);

        initiallyInvisibleMarkupContainer.setVisible(false);

        initiallyInvisibleMarkupContainer.add(new AjaxLazyLoadPanel("lazyLoadPanel") {
            private static final long serialVersionUID = -6643306285683137331L;

            @Override
            public Component getLazyLoadComponent(String s) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return new Label(s, Model.of("Hello, world!"));
            }
        });

        this.add(initiallyInvisibleMarkupContainer);


        this.add(new AjaxLink<Object>("makeVisibleLink") {
            private static final long serialVersionUID = 6608685827963651798L;

            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                initiallyInvisibleMarkupContainer.setVisible(true);
                ajaxRequestTarget.add(initiallyInvisibleMarkupContainer);
            }
        });
    }
}
