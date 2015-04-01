package com.lotoquebec.iris.infrastructure.services.configuration;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <p>Draconian error handler for SAX. This handler re-throws
 * SAXParseException whenever it encounters a warning, an error
 * or a fatal error.</p>
 *
 * @see org.xml.sax.ErrorHandler for error levels explanation.
 * @author Charles Beauregard
 */
public class SaxErrorHandler implements ErrorHandler {

	/**
	 * Constructor for SaxErrorHandler.
	 */
	public SaxErrorHandler() {
		super();
	}

	/**
	 * @see org.xml.sax.ErrorHandler#error(SAXParseException)
	 */
	public void error(SAXParseException spe) throws SAXException {
		throw spe;
	}

	/**
	 * @see org.xml.sax.ErrorHandler#fatalError(SAXParseException)
	 */
	public void fatalError(SAXParseException spe) throws SAXException {
		throw spe;
	}

	/**
	 * @see org.xml.sax.ErrorHandler#warning(SAXParseException)
	 */
	public void warning(SAXParseException spe) throws SAXException {
		throw spe;
	}
}
