package edu.teco.squery.resource;

import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.openarchitectureware.workflow.util.ResourceLoaderImpl;
import org.openarchitectureware.xtext.parser.IXtextParser;
import org.openarchitectureware.xtext.resource.AbstractXtextResource;

import edu.teco.squery.parser.XtextParser;

public class sQueryResource extends AbstractXtextResource {
	public sQueryResource(URI uri) {
		super(uri);
		setFormattingExtension("edu::teco::squery::Formatting");
		setResourceLoader(new ResourceLoaderImpl(XtextParser.class.getClassLoader()));
	}

	@Override
	protected IXtextParser createParser(InputStream inputStream) {
		return new XtextParser(inputStream);
	}

}

