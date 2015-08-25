/**
 * 
 */
package org.zkoss.module.reviewer;

/**
 *
 * @author simonpai
 */
public interface SourceCode {
	
	public SourceCode getParent();
	public SourceCode[] getMembers();
	
	public String getContent();
	public String getPath();
	public int getLineCount();
	
}
