/**
 * 
 */
package org.zkoss.module.reviewer;

import javax.servlet.ServletContext;

import org.zkoss.web.fn.ServletFns;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Textbox;

/**
 *
 * @author simonpai
 */
public class ReviewerController extends GenericForwardComposer {
	
	private static final long serialVersionUID = -2842118622491493758L;
	
	private SourceCode _rootSrc;
	
	private Button showBtn;
	private Div srcDiv;
	private Textbox srcBox;
	
	// TODO: how to add marker and annotation (information) on cases
	// for example, an overlaying Div with arrows and rectangles
	
	public void onClick$showBtn(Event event) {
		boolean toShow = !srcDiv.isVisible();
		srcDiv.setVisible(toShow);
		showBtn.setLabel(toShow ? "Hide Source" : "Show Source");
	}
	
	public void onClick$downloadBtn(Event event) throws Exception {
		Filedownload.save(_rootSrc.getPath(), "text/zul");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		String path = (String) arg.get(ReviewerRichlet.SRC_KEY);
		ServletContext context = ServletFns.getCurrentServletContext();
		_rootSrc = new SourceCodeImpl(context, path);
		
		// TODO: introduce "code resource" concept
		// TODO: enhance zul scanning to include relative resources (included zul, java (might be a challenge))
		
		String content = _rootSrc.getContent();
		srcBox.setRows(_rootSrc.getLineCount() + 1);
		srcBox.setValue(content == null ? "Source Not Available" : content);
	}
	
}
