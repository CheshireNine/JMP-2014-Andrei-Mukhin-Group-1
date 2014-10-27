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
public final class JAXBSerializationHelper {

	/**
	 * 
	 */
	private JAXBSerializationHelper() {
		super();
	}

	public static void marshal(Object list, Class clazz, File file)
			throws IOException, JAXBException {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		FileOutputStream stream = FileManager.getFileOutputStream(file);
		m.marshal(list, stream);
		stream.close();
		
	}

	public static Object unmarshal(Class clazz, File file) throws JAXBException {
		if(file.length() != 0L) {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
			return (Object) jaxbUnmarshaller.unmarshal(file);
		}
		return null;
	}

}
