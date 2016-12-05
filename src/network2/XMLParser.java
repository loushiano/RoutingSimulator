package network2;
/*
 * XMLParser is a class to parse an xml file using the SAXParser
 * @author Ali al-saaidi
 * 
 */

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import view.Circle;

public class XMLParser {
	public static ArrayList<String> stuff=new ArrayList<String>();
	public static String routers="";
	public static String circles="";
	public static String neighbours="";
	public static boolean correct=true;
	/*
	 * a methode that reads a xml file and parses it
	 * @param f the xml file that we want to parse
	 * @return arrayList of String representing the components of the topology
	 */
	public static ArrayList<String> readSAX(File f) throws Exception{
		SAXParserFactory spf =SAXParserFactory.newInstance();
			SAXParser s = spf.newSAXParser();
			
			
			DefaultHandler dh = new DefaultHandler(){
				boolean flag,flag1,flag2;
				int i=0;
				int beginning=0;
				public void startElement(String u, String ln,String qName,Attributes a){
					if(beginning==0 && !qName.equals("Topology")){
						correct=false;
					}
					beginning++;
					if(qName.equals("name")){
						flag=true;
					}else if(qName.equals("x")||qName.equals("y")){
						flag1=true;
					}else if(qName.equals("neighbor")){
						flag2=true;
					}
				}
				public void endElement(String uri,String localName,String qName){
					
					
				}
				
				public void characters(char[] ch,int start,int length){
					//System.out.println("CHARS: "+new String(ch, start, length));
					if(flag==true){
						 routers += new String(ch, start, length) +" ";
						 i++;
						flag=false;
					}
					if(flag1==true){
						circles+=new String(ch,start,length)+" ";
						flag1=false;
					}
					if(flag2==true){
						if(i==2){
							neighbours+=i;
							i=1;
						}
						neighbours+=new String(ch,start,length)+" ";
						
						flag2=false;
					}
				}
				
				
			};
			routers="";
			neighbours="";
			circles="";
			stuff.clear();
			s.parse(f, dh);
			ArrayList<String> returner =new ArrayList<String>();
			String a1=new String(routers);
			String a2 =new String (neighbours);
			String a3= new String (circles);
			returner.add(a1);returner.add(a2);returner.add(a3);
			return returner;
	}
	
	
	
	
}
