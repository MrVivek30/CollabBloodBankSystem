package org.springproject.bloodbank.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;


public class ChainableUrlBasedViewResolver extends UrlBasedViewResolver {
    private static final Logger logger = LogManager.getLogger("vehicles");
    private static int obCount = 0;

    public ChainableUrlBasedViewResolver() {
        logger.log(Level.INFO, ++obCount + " Objects of ChainableURLBasedViewResolver has been created ");
        setViewClass(InternalResourceView.class);
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        // I am making a path on the file system to the jsp
        String url = getPrefix() + viewName + getSuffix();
        // Taking the input from the filesystem for rendering
        InputStream stream = getServletContext().getResourceAsStream(url);

        if (stream == null) { // if not found i send an empty View
            return new NonExistentView();
        }
        return super.buildView(viewName);  // Or just render the jsp
    }

    private static class NonExistentView extends AbstractUrlBasedView { // empty View
        @Override
        protected boolean isUrlRequired() {
            return false;
        }

        @Override
        public boolean checkResource(Locale locale) throws Exception {
            return false;
        }

        @Override
        protected void renderMergedOutputModel(Map<String, Object> model,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
            // Purposely empty, it should never get called
        }
    }
}

