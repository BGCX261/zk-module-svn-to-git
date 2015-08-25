/**
 * 
 */
package org.zkoss.module.reviewer;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.zkoss.io.Files;

/**
 *
 * @author simonpai
 */
public class SourceCodeImpl implements SourceCode {
	
	private SourceCode _parent;
	private SourceCode[] _members;
	
	private final String _path;
	private final String _content;
	private final int _lineCount;
	
	public SourceCodeImpl(ServletContext context, String path, SourceCode parent) 
			throws IOException {
		this(context, path);
		_parent = parent;
	}
	
	public SourceCodeImpl(ServletContext context, String path) throws IOException {
		_path = path;
		InputStream in = context.getResourceAsStream(path);
		_content = in == null ? null : new String(Files.readAll(in));
		_lineCount = getLineCount(_content);
		_members = buildMembers(context, _content);
	}
	
	@Override
	public SourceCode getParent() {
		return _parent;
	}
	
	@Override
	public SourceCode[] getMembers() {
		return _members;
	}
	
	@Override
	public String getContent() {
		return _content;
	}
	
	@Override
	public int getLineCount() {
		return _lineCount;
	}
	
	@Override
	public String getPath() {
		return _path;
	}
	
	// helper //
	private static int getLineCount(String content) {
		if(content == null) 
			return 0;
		Matcher m = Pattern.compile("\\n").matcher(content);
		int i = 0;
		while(m.find()) i++;
		return i;
	}
	
	private static SourceCode[] buildMembers(ServletContext context, String content) {
		// scan for apply="", use="" for java
		// scan for src="... .zul" ?
		return null; // TODO
	}
	
}
