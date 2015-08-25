/**
 * 
 */
package org.zkoss.module.reviewer;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zk.ui.util.WebAppInit;

/**
 *
 * @author simonpai
 */
public class ReviewerWebAppInit implements WebAppInit {
	
	@Override
	public void init(WebApp wapp) throws Exception {
		Configuration config = wapp.getConfiguration();
		config.addRichlet("reviewer", ReviewerRichlet.class, null);
		config.addRichletMapping("reviewer", ReviewerRichlet.URL_PREFIX + "/*");
	}
	
}
