package com.cts.app.parser;

import org.htmlparser.tags.CompositeTag;

public class SectionTag extends CompositeTag{
	
	private static final String[] mIds = new String[] {"SECTION"};    
    private static final String[] mEndTagEnders = new String[] {"BODY", "HTML"};
    public SectionTag() {
    }
    public String[] getIds ()
    {
      return (mIds);
    }
    public String[] getEndTagEnders ()
    {
      return (mEndTagEnders);
    }

}
