package communication;

import java.io.File;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyProperties implements Serializable
{
	private static MyProperties instance;
	@XmlElement
	public String server_ip;
	@XmlElement
	public int port;
	@XmlElement
	public String default_algo;
	@XmlElement
	public String default_domain;
	
	private MyProperties()
	{
		loadFromFile();
	}
	
	public static MyProperties getInstance()
	{
		if (instance == null)
		{
			instance = new MyProperties();
		}
		return instance;
	}
	
	private void loadFromFile()
	{
		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(MyProperties.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MyProperties p = (MyProperties) jaxbUnmarshaller.unmarshal(new File("resource/properties.xml"));
			server_ip = p.server_ip;
			port = p.port;
			default_algo = p.default_algo;
			default_domain = p.default_domain;
		} catch (JAXBException e)
		{
			
		}
	}
	
	
//	public static void main(String[] args)
//	{
//		MyProperties myProperties = new MyProperties();
//		myProperties.server_ip = "127.0.0.1";
//		myProperties.port = 5001;
//		myProperties.default_domain = "MazeDomain";
//		myProperties.default_algo = "Astar";
//		
//		JAXBContext jaxbContext;
//		try
//		{
//			jaxbContext = JAXBContext.newInstance(MyProperties.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			jaxbMarshaller.marshal(myProperties, new File("properties.xml"));
//		} catch (JAXBException e)
//		{
//			System.out.println(e);
//		}
//		
//	}
}
