/*
 * Contributors: Daniel Weber - Initial implementation
 */
package org.eclipse.xpand2.output.cpp;

import org.eclipse.xpand2.output.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
import org.eclipse.cdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.core.parser.ParserUtil;
import org.eclipse.cdt.core.parser.ScannerInfo;
import org.eclipse.cdt.internal.formatter.CodeFormatterVisitor;
import org.eclipse.cdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.mwe.core.resources.ResourceLoaderFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

/**
 * An XPand post processor for C/C++ code formatting based on cdt's code
 * formatter.
 *
 * Is uses some internal cdt classes to do its job, but this was he only way to
 * get this to work outside an Eclipse runtime. Use it like this (from an oaw
 * workflow):
 *
 * <pre>
 *    &lt;beautifier class=&quot;oaw.tests.workflow.CppBeautifier&quot; configFile=&quot;myCFormatterOptions.xml&quot;/&gt;
 * </pre>
 *
 * In order to create a configuration file, use cdt's preferences to set
 * formatter options and then export that configuration to a file.
 *
 *
 * @author DaWeber@harmanbecker.com
 */
@SuppressWarnings("restriction")
public class CppBeautifier implements PostProcessor
{
   private static final String DEFAULT_CDT_OPTIONS = "org/openarchitectureware/xpand2/output/cdtformat-kandr.xml";
   private String    configFile = DEFAULT_CDT_OPTIONS;

   /** Logger instance. */
   private final Log log = LogFactory.getLog(getClass());

   /*
    * (non-Javadoc)
    *
    * @see org.openarchitectureware.xpand2.output.PostProcessor#afterClose(org.openarchitectureware.xpand2.output.FileHandle)
    */
   public void afterClose(final FileHandle impl)
   {
   }

   /*
    * The implementation could have been much easier:
    *
    * <pre>
    * CodeFormatter formatter = ToolFactory.createDefaultCodeFormatter(getFormatterOptions());
    * // CodeFormatter formatter = ToolFactory.createDefaultCodeFormatter(null);
    * String SOURCE = impl.getBuffer().toString();
    * IDocument document = new Document(SOURCE);
    * try
    * {
    *    formatter
    *          .format(CodeFormatter.K_COMPILATION_UNIT, SOURCE, 0, SOURCE.length(), 0, &quot;\n&quot;)
    *          .apply(document);
    *    impl.setBuffer(new StringBuffer(document.get()));
    * }
    * catch(MalformedTreeException e)
    * {
    *    log.error(e.getMessage(), e);
    * }
    * catch(BadLocationException e)
    * {
    *    log.error(e.getMessage(), e);
    * }
    * </pre>
    *
    * but unfortunately, this requires the cdt plug-in to be up and running in
    * an Eclipse runtime, which does not work when running oaw workflows in a
    * standalone environment.
    *
    * @see org.openarchitectureware.xpand2.output.PostProcessor#beforeWriteAndClose(org.openarchitectureware.xpand2.output.FileHandle)
    */
   @SuppressWarnings("unchecked")
   public void beforeWriteAndClose(final FileHandle fileHandle)
   {
      Map options = createFormatterOptions();
      String source = fileHandle.getBuffer().toString();
      try
      {
         CodeFormatterVisitor codeFormatter = new CodeFormatterVisitor(
               createCodeFormatterSettings(options), 0, source.length());
         IASTTranslationUnit ast = createTranslationUnit(options, source);
         IDocument document = new Document(source);
         codeFormatter.format(source, ast).apply(document);
         fileHandle.setBuffer(new StringBuffer(document.get()));
      }
      catch(Exception e)
      {
         log.error(e.getMessage(), e);
      }
   }

   /**
    * @return the configuration file for the formatter
    */
   public String getConfigFile()
   {
      return configFile;
   }

   /**
    * @param configFile configuration file for the formatter
    */
   public void setConfigFile(final String configFile)
   {
      this.configFile = configFile;
   }

   /**
    * @param options
    * @return
    */
   @SuppressWarnings("unchecked")
   private DefaultCodeFormatterOptions createCodeFormatterSettings(Map options)
   {
      DefaultCodeFormatterOptions defaultSettings = DefaultCodeFormatterOptions
            .getDefaultSettings();
      if(options!=null)
    	  defaultSettings.set(options);
      defaultSettings.line_separator = "\n";
      return defaultSettings;
   }

   /**
    * @return A map of formatter options. Either loaded from a given
    *         configuration file, or using built-in default values.
    */
   @SuppressWarnings("unchecked")
   private Map createFormatterOptions()
   {
      return readConfig(getConfigFile());
   }

   /**
    * @param options
    * @param source
    * @return
    * @throws CoreException
    */
   @SuppressWarnings("unchecked")
   private IASTTranslationUnit createTranslationUnit(Map options, String source)
         throws CoreException
   {
      return getLanguage(options).getASTTranslationUnit(
            new CodeReader(source.toCharArray()), new ScannerInfo(), null, null,
            ParserUtil.getParserLogService());
   }

   /**
    * Determines the language to be used based on the given options.
    *
    * @param options cdt formatter options
    * @return a suitable ILanguage. GPPLanguage as default.
    */
   @SuppressWarnings("unchecked")
   private ILanguage getLanguage(Map options)
   {
      ILanguage language = null;
      
      if(options!=null) language=  	  (ILanguage)options
            .get(DefaultCodeFormatterConstants.FORMATTER_LANGUAGE);
      if(language == null)
      {
         language = GPPLanguage.getDefault();
      }
      return language;
   }
   
	 /**
	  * Searches for the given filename as a ressource and returns a stream on it. Throws an IOException, if the file
	  * cannot be found.
	  * 
	  * @param filename
	  *				   The name of the file to be searched in the ressources.
	  * @return InputStream for subsequent reading
	  * @throws IOException
	  */
	 protected InputStream openStream(String filename) throws IOException {
		 InputStream is = ResourceLoaderFactory.createResourceLoader().getResourceAsStream(filename);
		 if (is == null) {
			 throw new IOException("Config file [" + filename + "] does not exist.");
		 }
		 return is;
	 }

	 
   
   /**
    * Return a Java Properties file representing the options that are in the
    * specified configuration file. In order to use this, simply export a
    * formatter configuration to a file.
    *
    * This code is mainly copied from {@link JavaBeautifier}, it might make
    * sense to factor this out to somewhere else so that more formatters can
    * make use of this.
    *
    * @todo replace this copy'n'pasted code
    *
    * @see JavaBeautifier
    */
   private Properties readConfig(final String filename)
   {
      InputStream stream=null;
      BufferedReader reader=null;


	try
      {
         stream=openStream(filename);
         
         final Properties formatterOptions = new Properties();
         
        
         
         if(filename.endsWith(".xml"))
         {
        	
            Pattern pattern = Pattern
                  .compile("<setting id=\"([^\"]*)\" value=\"([^\"]*)\"\\/>");
            
            reader = new BufferedReader(new InputStreamReader(stream));
            for(String line = reader.readLine(); line != null; line = reader.readLine())
            {
               Matcher matcher = pattern.matcher(line);
               if(matcher.matches())
               {
                  formatterOptions.put(matcher.group(1), matcher.group(2));
               }
            }
         }
         else
         {
            formatterOptions.load(stream);
         }
         return formatterOptions;
      }
      catch(IOException e)
      {
         log.warn("Problem reading code formatter config file (" + e.getMessage() + ").");
      }
      finally
      {
         if(stream != null)
         {
            try
            {
               stream.close();
            }
            catch(IOException e)
            {
               /* ignore */
            }
         }
         if(reader != null)
         {
            try
            {
               reader.close();
            }
            catch(IOException e)
            {
               /* ignore */
            }
         }
      }
      return null;
   }

}
