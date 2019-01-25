package hudson.plugins.openid;

import hudson.Extension;
import hudson.Plugin;
import hudson.model.UnprotectedRootAction;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Serve static resources even when the user doesn't have read access, as in prior to the login.
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class StaticResourceServer implements UnprotectedRootAction {
    public String getIconFileName() {
        return null;
    }

    public String getDisplayName() {
        return null;
    }

    public String getUrlName() {
        return "openid-assets";
    }

    // serve static resources
    public void doDynamic(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
        Plugin openIdPlugin = Jenkins.get().getPlugin("openid");
        if (openIdPlugin == null) {
            throw new AssertionError("OpenID plugin (self) not installed");
        }
        openIdPlugin.doDynamic(req,rsp);
    }
}
