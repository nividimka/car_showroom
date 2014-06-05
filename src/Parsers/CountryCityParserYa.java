package Parsers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by yuraf_000 on 05.06.2014.
 */
public class CountryCityParserYa {
    private ListMultimap<String, String> CountryCityMap = ArrayListMultimap.create();
    private HashMap<String,String> CountryIdMap = new HashMap<>();
    private int counter = 0;
    public CountryCityParserYa(String url) {
        ParseXMLCities(url);

        System.out.println("Количество городов: " + counter);
    }

    public ListMultimap<String, String> GetCountryCityMap() {
        return CountryCityMap;
    }

    private void ParseXMLCities(String url) {
        try {
            String resCityId = null;
            URL UrlToParse = new URL(url);
            DOMParser p = new DOMParser();
            p.parse(new InputSource(UrlToParse.openStream()));
            Document doc = p.getDocument();
            NodeList nodeLst = doc.getElementsByTagName("country");
            for (int i = 0; i < nodeLst.getLength(); i++) {
                Node nNode = nodeLst.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList CitiesLst = eElement.getElementsByTagName("city");
                    for (int x = 0; x < CitiesLst.getLength(); x++) {
                        Element CElement = (Element) CitiesLst.item(x);

                        switch (eElement.getAttribute("name").toString()) {
                            case "США":
                                if (CitiesLst.item(x).getTextContent().indexOf(",") == -1) {
                                    CountryCityMap.put(eElement.getAttribute("name"), CitiesLst.item(x).getTextContent() + "_" + CElement.getAttribute("id"));
                                    counter++;
                                }
                                else {
                                    CountryCityMap.put(eElement.getAttribute("name"), CitiesLst.item(x).getTextContent().substring(0, CitiesLst.item(x).getTextContent().indexOf(",")) + "_" + CElement.getAttribute("id"));
                                    counter++;
                                }
                                break;

                            default:
                                CountryCityMap.put(eElement.getAttribute("name"), CitiesLst.item(x).getTextContent() + "_" + CElement.getAttribute("id"));
                                counter++;

                        }

                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}