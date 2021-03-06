package org.eclipse.xpand2.output.cpp;

import java.io.File;

import java.net.URL;

import org.eclipse.emf.mwe.core.resources.ResourceLoaderFactory;
import org.eclipse.internal.xpand2.ast.TextStatement;
import org.eclipse.internal.xtend.expression.ast.SyntaxElement;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xpand2.output.OutputImpl;


/*
import java.util.Stack;
*/

public class CppOutput extends OutputImpl {
    
  
	public void write(final String bytes) {
		
    	//boolean has_end=(bytes.trim().length()>0&&(bytes.trim().substring(0,1).equals(NEWLINE)/*||bytes.substring(0,1).equals(" ")*/));
		
		if(emit && bytes.trim().length()>0 && (cur_output!=last_output || cur_line!=last_line || (cur_file!=null && (!cur_file.equals(last_file)))))
	    {
			if(cur_file.equals(last_file) && cur_output==last_output)
			{
				if(had_end)
					super.write(continue_file(cur_line)+bytes);
				else
				{
					super.write(bytes.replaceFirst("\n", "\n"+continue_file(cur_line)));
				}
				last_line=cur_line;
			}
			else
			{
				if(had_end)
	    		{
	    			super.write(change_file(cur_file,cur_line)+bytes);
	    			last_file=cur_file;
	    			last_line=cur_line;
	    			last_output=cur_output;
	    		}
	    		else
	    		{
	    			/*if (bytes.matches("\n"))*/ {
	    				last_file=cur_file;
		    			last_line=cur_line;
		    			last_output=cur_output;
	    			}
	    			super.write(bytes.replaceFirst("\n", "\n"+change_file(cur_file,cur_line)));
	    		}	
			}
			emit=false;
	    	
	    	
	    }
		else
			super.write(bytes);	
		
		had_end=(bytes.trim().length()>0&&(bytes.trim().substring(bytes.trim().length()-1,bytes.trim().length()).equals("\n")/*||bytes.substring(bytes.length()-1,bytes.length()).equals(" ")*/));
		
    }

private String cur_file;
    private String last_file="";
    
    
    private int cur_line;
    private int last_line;
    private boolean had_end=true;

	

/*
 *     TODO: generate file/file_out
    String lineTrace="";
    
	private final Stack<SyntaxElement> s = new Stack<SyntaxElement>();
	
	private class Line
	{
		public String file;
		public int line;
		
		public Line(String file,int line)
		{
			this.file=file;
			this.line=line;
		}
		
		public Line(SyntaxElement s)
		{
			this(s.getFileName(),s.getLine());
		}
		
	}
	
  	private final Stack<Line> lines = new Stack<Line>();
	private Sting enter_file(String file,int line)
	{
		return "# "+line
		+ " "+file
		+ " 1"+NEWLINE;
	}
	
	private String returnto_file(String file,int line)
	{
		return "# "+line
		+ " "+file
		+ " 2"+NEWLINE;
	}
	

*/
	private String change_file(String _file,int line)
	{
		URL url = ResourceLoaderFactory.createResourceLoader().getResource(cur_file); 
		String file=(url.getFile());//java.net.URLDecoder.decode
		file=new File(file).getPath();
		file=file.replace("\\bin\\", "\\src\\").replace("\\", "\\\\\\\\");; //hack
		
		return "# "+line
		+ " \""+file+"\""
		+"\n";
	}
	private String continue_file(int line)
	{
		return "# "+line+"\n";
	}
	
	private boolean emit=false;
	Object last_output,cur_output;
	@Override
	public void pushStatement(SyntaxElement stmt, XpandExecutionContext ctx) {
		if (! (stmt instanceof TextStatement)) {
			emit=true;
		}
		cur_line=stmt.getLine();
		cur_file=stmt.getFileName();
		cur_output=super.current();
		/*
		s.push(stmt);
		*/
		super.pushStatement(stmt,ctx);
	}
	
	/*
	@Override
	public SyntaxElement popStatement() {

		s.pop();
		return super.popStatement();
	}
	*/
	
}