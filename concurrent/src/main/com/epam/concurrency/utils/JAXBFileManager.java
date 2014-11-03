  /**
 * 
 */
package com.epam.concurrency.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author Petr_Tsiatnev
 *
 */
public class JAXBFileManager {

	private File sourceFile;
	/**
	 * 
	 */
	public JAXBFileManager() {
		super();
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void marshal(Object list, Class clazz)
			throws IOException, JAXBException {
		if(!sourceFile.exists()) {
			try {
				sourceFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		FileOutputStream stream = FileManager.getFileOutputStream(sourceFile);
		m.marshal(list, stream);
		stream.close();

	}

	public Object unmarshal(Class clazz) throws JAXBException {
		if(sourceFile.exists() && (sourceFile.length() > 0)) {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			return (Object) jaxbUnmarshaller.unmarshal(sourceFile);
		}
		return null;
	}

}
