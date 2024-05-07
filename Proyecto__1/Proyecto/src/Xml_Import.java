import java.net.*;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*; 
//https://api.tutiempo.net/xml.html
public class Xml_Import {

    public static void main(String[] args) { 
        
        // Objeto de la clase URL. Nos permite realizar una conexión a una URL
        URL url=null;
        int contador=0;
        String strUrl = null;
        
        // Crea el String con la URL a conectarse (archivo .xml)
        strUrl = "https://api.tutiempo.net/xml/?lan=es&apid=zwDX4azaz4X4Xqs&ll=40.4178,-3.7022";
        
        // Conexión a la URL
        try {
            url = new URL (strUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return; // Añadido para salir del método en caso de error de URL
        }
        
        try {
            
            // Crea un Document que recibirá el contenido de la URL (archivo .xml)
            // Genera un árbol de objetos que representan cada elemento del XML
            DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse(url.openStream());
            
            // Accede al nodo raíz del documento y lo normaliza eliminando nodos vacíos
            documento.getDocumentElement().normalize();
            
            // Lista de nodos que corresponden a los elementos "hour_hour"
            NodeList listaHoras = documento.getElementsByTagName("hour_hour");
            NodeList listaDias = documento.getElementsByTagName("day"+contador);
            // Crear un objeto PrintWriter para escribir en un archivo HTML
            FileWriter writer = new FileWriter("/home/daw/Escritorio/Proyecto__1/clima_madrid.html");
            
            // Escribir el encabezado del archivo HTML
            writer.write("<!DOCTYPE html>");
            writer.write("<html lang=\"es\">");
            writer.write("<head>");
            writer.write("<meta charset=\"UTF-8\">");
            writer.write("<title>El Tiempo</title>");
            writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/Proyecto_1.css\">");
            writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/menu.css\">");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<div class=\"tit\">");
            writer.write("<div>");
            writer.write("<h1>El tiempo</h1>");
            writer.write("</div>");
            writer.write("<a>");
            writer.write("<img class=\"logo\" src=\"Imagenes/logo.jpg\" alt=\"Logo de la empresa\" title=\"Volver arriba\">");
            writer.write("</a>");
            writer.write("</div>");
            writer.write("<nav>");
            writer.write("<div class=\"menu\">");
            writer.write("<ul>");
            writer.write("<li><a href=\"#Hoy\">Hoy</a></li>");
            writer.write("<li><a href=\"#hora\">Por hora</a></li>");
            writer.write("<li><a href=\"#días\">7 días</a></li>");
            writer.write("</ul>");
            writer.write("</div> ");
            writer.write("</nav>");
            writer.write("<hr>");
            writer.write("<section>");
            
            // Recorrer la lista de nodos y escribir la información en el archivo HTML
            for (int i = 0; i < listaHoras.getLength(); i++) {
                Node hora = listaHoras.item(i);
                if (hora.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoHora = (Element) hora;
                    writer.write("<article id=Hoy class=\"nos\">");
                    writer.write("<div>");
                    writer.write("<h3>Clima hoy</h3>");
                    writer.write("<div>");
                    writer.write("<p>Temperatura: " + elementoHora.getElementsByTagName("temperature").item(0).getTextContent() + "</p>");
                    writer.write("</div>");
                    writer.write("<div>");
                    writer.write("<p> Clima:" + elementoHora.getElementsByTagName("text").item(0).getTextContent() + "</p>");
                    writer.write("</div>");
                    writer.write("<div>");
                    writer.write("<p> Humedad:" + elementoHora.getElementsByTagName("humidity").item(0).getTextContent() + "</p>");
                    writer.write("</div>");
                    writer.write("</div>");
                    writer.write("<a>");
                    writer.write("<img class=\"img_tiempo\" src=\"Imagenes/logo.jpg\" alt=\"Logo de la empresa\" title=\"Volver arriba\">");
                    writer.write("</a>");
                    writer.write("</article>");
                }
            }
            ///////////////////////////////////////////////////////////////
            writer.write("<article id=hora >");
            for (int x=1;x<=24;x++) {
            	
            	listaHoras = documento.getElementsByTagName("hour"+contador);
            for (int i = 0; i < listaHoras.getLength(); i++) {
                Node hora = listaHoras.item(i);
                if (hora.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoHora = (Element) hora;

                    writer.write("<div>");
                    writer.write("<p><h3>Hora: " + elementoHora.getElementsByTagName("hour_data").item(0).getTextContent() + "</h3>"+ "");
                    writer.write("Temperatura: " + elementoHora.getElementsByTagName("temperature").item(0).getTextContent() + "");
                    writer.write("Clima: " + elementoHora.getElementsByTagName("text").item(0).getTextContent() + "");
                    writer.write("Humedad: " + elementoHora.getElementsByTagName("humidity").item(0).getTextContent() + "</p>");
                    writer.write("</div>");

                    
                }
                }
            contador++;
            }
            writer.write("</article>");
            writer.write("<article id=días class=\"nos\">");
            writer.write("<br></br>");
            contador=0;
            for (int x=0;x<=7;x++) {
            	listaDias = documento.getElementsByTagName("day"+contador);
            	
            for (int i = 0; i < listaDias.getLength(); i++) {
                Node dia = listaDias.item(i);
                if (dia.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoDias = (Element) dia;
                    
                    writer.write("<div>");
                    writer.write("<h3>Dia "+contador+"</h3>");
                    writer.write("<div>");
                    writer.write("<p>Temperatura: " + elementoDias.getElementsByTagName("temperature_max").item(0).getTextContent() + "</p>");
                    writer.write("</div>");
                    writer.write("<div>");
                    writer.write("<p> Clima:" + elementoDias.getElementsByTagName("text").item(0).getTextContent() + "</p>");
                    writer.write("</div>");
                    writer.write("<div>");
                    writer.write("<p> Humedad:" + elementoDias.getElementsByTagName("wind").item(0).getTextContent() + "</p>");
                    writer.write("</div>");
                    writer.write("</div>");
                   
                }
                }
            contador++;
            }
            writer.write("</article>");
            // Escribir el cierre del archivo HTML
            writer.write("<p> </p>");
            writer.write("<p> </p>");
            writer.write("</section>");
            writer.write("</body>");
            writer.write("</html>");
            
            // Cerrar el PrintWriter
            writer.close();
            
            System.out.println("se ha generado bien");
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

