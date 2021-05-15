package com.xmlutil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.bean.EMP;


public class XMLShow {
	/** �ļ����� gb18030*/
	public static final String GBK="gb18030";
	/** �ļ����� UTF-8*/
	public static final String UTF_8="UTF-8";
	public ArrayList<EMP> getEmpArrayList(){
		ArrayList<EMP> al = new ArrayList<EMP>();
		EMP e1=new EMP();
		EMP e2=new EMP();
		 e1.setEname("fds");
		 float flg=(float) 123.90;
	     e1.setSal(flg);
		 e1.setHiredate(new java.sql.Date( new Date().getTime()));
		 e1.setLoc("fsdf");
         e1.setDname("fsfds");
         al.add(e1);
         return al;
	}
	public static void main(String[] args) {
		XMLShow xml =new XMLShow();
		File f1=new File("E://emp.xml");
		File f2=new File("E://emp2.xml");
		xml.createEMPXML(xml.getEmpArrayList(),XMLShow.UTF_8,f1.getAbsolutePath());
		xml.readEMPXML(f1.getAbsolutePath());
		System.out.println(xml.getNodeText("database.dbtype", xml.getDocumentBySAX(new File ("src/system-config.xml") )));
		xml.ObjectToXml(f2, XMLShow.UTF_8) ;
		System.out.println(xml.getNodeText("KeyDepts", xml.getDocumentBySAX(f2)));

	}
	private void createEMPXML(ArrayList<EMP> al,String ecncoding,String filepath) {
		try{
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("root");
			Element b1 = root.addElement("persons");			
			for(EMP abc : al){
				Element c1 = b1.addElement("person");
					c1.addAttribute("name", abc.getEname());
					c1.addAttribute("salary", abc.getSal()+"");
					
					c1.addAttribute("offdate", abc.getHiredate().toString());		
					Element d1 = c1.addElement("location");
						d1.addText("zw");
						d1.addAttribute("dd", abc.getLoc());
						d1.addAttribute("mc", abc.getDname());
			}
			Element c1 = root.addElement("personnum");
			c1.addText(al.size()+"");
			OutputFormat format=new OutputFormat();
			format.setIndent(true);
			format.setIndent("  ");
			format.setNewlines(true);
			format.setNewLineAfterDeclaration(false);//ɾ������������֮��Ŀ���
			format.setTrimText(true);
			format.setEncoding(ecncoding);
			//format.setExpandEmptyElements(true);//�Ƿ񽫿ձ�ǩ��Ϊǰ��Գ�<a></a>
			FileOutputStream fi=new FileOutputStream(filepath);
			OutputStreamWriter oo=new OutputStreamWriter(fi, ecncoding);
			XMLWriter xw = new XMLWriter(oo,format);	
			xw.write(doc);
			xw.close();
			System.out.println("ok");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void readEMPXML(String absfile){
		Document doc=null;
		SAXReader reader=new SAXReader();
		try {
			doc=reader.read(new File(absfile));//����XML�ļ�
			Element root=doc.getRootElement();
			//System.out.println("���ڵ�����\t"+root.getName());
			Iterator it=root.elementIterator("personnum");//root��personnum��ǩ
			while(it.hasNext()){
				Element et=(Element)it.next();
				System.out.println(et.getName());//�ڵ�����
				System.out.println(et.getText());//�ڵ�ֵ
			}
			Element persons=root.element("persons");//root��persons��ǩ
			if(persons!=null){
				Iterator ets=persons.elementIterator();
				while(ets.hasNext()){  //����person
					Element etss=(Element)ets.next();
					System.out.println("��ǩ��:"+etss.getName());
					System.out.println("person����"+etss.attributeValue("name"));
					Element loc=etss.element("location");  //ֱ�ӻ�ȡ�ӱ�ǩ
				   System.out.println("�칫�ص�"+loc.attributeValue("dd"));
					
				}
			}			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}						
	}
	/**
	 * ��ȡ note�ڵ�ֵ 
	 * root �½ڵ㿪ʼ�� cache.jcs.switch
	 */
	public String getNodeText(String nodeid,Document doc){
		String val=null;
		try {
			Element root=doc.getRootElement();
		    String []gh=nodeid.split("\\.");
		    Element t=root.element(gh[0]);
		    for(int i=1;i<gh.length;i++){
		    		t=t.element(gh[i]);
		    }
		    val=t.getText();
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}
	/**
	 * 
	 * @param file :xml�ļ�
	 * @return   Document
	 * @throws DocumentException
	 */
	public Document getDocumentBySAX(File file) {
		Document doc=null;
		SAXReader reader=new SAXReader();
		try {
			doc=reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * 
	 * @param xmlstr xml�ַ���
	 * @return   Document
	 * @throws DocumentException
	 */
	public Document getDocumentBySAX(String xmlstr){
		Document doc=null;
		SAXReader reader=new SAXReader();
		try {
			doc=DocumentHelper.parseText(xmlstr) ;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * 
	 * @param file:�ļ���·��
	 * @param encoding �ļ�����
	 * @return
	 */
	public boolean ObjectToXml(File file,String encoding){
		boolean flag=false;
		try{
			if(file.exists())
				   file.delete();
			file.createNewFile();
			OutputStream is=new FileOutputStream (file);
			OutputStreamWriter isr=new OutputStreamWriter(is,encoding);
			BufferedWriter bf=new BufferedWriter(isr);
			StringBuffer sb=new StringBuffer("");
			sb.append( "<?xml version=\"1.0\" encoding=\"");
			sb.append(encoding+"\"?>") ;
			sb.append("\n")  ;
			sb.append("<root>");
			sb.append("\n")  ;
	        sb.append("      <KeyDepts ProVer=\"0\">123</KeyDepts>");
	        sb.append("\n")  ;
	        sb.append("</root>");
			bf.write(sb.toString());
			bf.flush();
			bf.close();
			isr.close();
			is.close();
			flag=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
