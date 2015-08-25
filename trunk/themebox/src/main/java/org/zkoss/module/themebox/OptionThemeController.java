/* OptionBoxThemeController.java

{{IS_NOTE
 Purpose:
  
 Description:
  
 History:
  Apr 7, 2011 5:01:55 PM , Created by simonpai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.module.themebox;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.theme.Themes;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * 
 * @author simonpai
 */
public class OptionThemeController extends GenericForwardComposer {
	
	private static final long serialVersionUID = -6970652081723260941L;
	
	private ListModelList _model;
	private Listbox themelistbox;
	
	public void onSelect$themelistbox(Event event){
		// get selected theme from listbox
		String key = (String) themelistbox.getSelectedItem().getValue();
		Themes.setTheme(execution, key);
		execution.sendRedirect(null);
	}
	
	public void onAfterRender$themelistbox(Event event) {
		String key = Themes.getCurrentTheme();
		for(Object obj : themelistbox.getChildren()){
			Listitem li = (Listitem) obj;
			if(key.equals(li.getValue())) 
				themelistbox.setSelectedItem(li);
		}
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		_model = new ListModelList(Themes.getThemes());
		
		themelistbox.setItemRenderer(new ListitemRenderer(){
			public void render(Listitem item, Object data) throws Exception {
				String name = (String) data;
				item.setLabel(Themes.getDisplayName(name));
				item.setValue(name);
			}
		});
		
		themelistbox.setModel(_model);
		
	}
	
}
