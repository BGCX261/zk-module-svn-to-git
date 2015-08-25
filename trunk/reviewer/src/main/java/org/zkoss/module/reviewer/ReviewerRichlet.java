/**
 * 
 */
package org.zkoss.module.reviewer;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.GenericRichlet;
import org.zkoss.zk.ui.Page;

/**
 *
 * @author simonpai
 */
public class ReviewerRichlet extends GenericRichlet {
	
	public static final String URL_PREFIX = "/review";
	public static final String SRC_KEY = "src";
	
	@Override
	public void service(Page page) throws Exception {
		String path = page.getRequestPath();
		if(path == null)
			throw new RuntimeException("Null request path");
		if(path.startsWith(URL_PREFIX + "/"))
			path = path.substring(URL_PREFIX.length());
		
		String view = isIgnored(path) ? 
				"~./mod/reviewer/view/ignored.zul" : 
				"~./mod/reviewer/view/reviewer.zul";
		
		page.setTitle("Review: " + path);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put(SRC_KEY, path);
		Executions.createComponents(view, null, m);
	}
	
	private boolean isIgnored(String path){
		return false; // TODO: intercept non-zul/zhtml/html resource
	}
	
}
