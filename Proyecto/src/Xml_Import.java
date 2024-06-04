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
            FileWriter writer = new FileWriter("..\\WEB\\index.html");
            String newLine = System.lineSeparator();
            
            // Escribir el encabezado del archivo HTML
            writer.write("<!DOCTYPE html>" + newLine +
					"<html lang=\\\"es\\\">" + newLine + 
					"<head>" + newLine +
					"<meta charset=\"UTF-8\">" + newLine +
					"<title>El Tiempo</title>" + newLine + 
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/Proyecto_1.css\">" + newLine +
					"<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/menu.css\">" + newLine +
					"</head>" + newLine +
					"<body>" + newLine + 
					"<div class=\"tit\">" + newLine +
					"<div>" + newLine + 
					"<h1>El tiempo</h1>" + newLine + 
					"</div>" + newLine +
					"<a>" + newLine +
					"<img class=\"logo\" src=\"Imagenes/logo.jpg\" alt=\"Logo de la empresa\" title=\"Volver arriba\">" + newLine +
					"</a>" + newLine +
					"</div>" + newLine +
					"<nav>" + newLine + 
					"<div class=\"menu\">" + newLine +
					"<ul>" + newLine +
					"<li><a href=\"#Hoy\">Hoy</a></li>" + newLine +
					"<li><a href=\"#hora\">Por hora</a></li>" + newLine +
					"<li><a href=\"#días\">7 días</a></li>" + newLine +
					"</ul>" + newLine +
					"</div> " + newLine +
					"</nav>" + newLine +
					"<hr>" + newLine +
					"<section>");
            
            // Recorrer la lista de nodos y escribir la información en el archivo HTML
            for (int i = 0; i < listaHoras.getLength(); i++) {
                Node hora = listaHoras.item(i);
                if (hora.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoHora = (Element) hora;
                    writer.write("<article id=Hoy class=\"nos\">" + newLine +
                    				"<div>" + newLine +
                    				"<h3>Clima hoy</h3>" + newLine +
                    				"<div>" + newLine +
                    				"<p>Temperatura: " + elementoHora.getElementsByTagName("temperature").item(0).getTextContent() + "</p>" + newLine +
                    				"</div>" + newLine +
                    				"<div>" + newLine +
                    				"<p> Clima:" + elementoHora.getElementsByTagName("text").item(0).getTextContent() + "</p>" + newLine +
                    				"</div>" + newLine +
                    				"<div>" + newLine +
                    				"<p> Humedad:" + elementoHora.getElementsByTagName("humidity").item(0).getTextContent() + "</p>" + newLine +
                    				"</div>" + newLine +
                    				"</div>" + newLine +
                    				"<a>" + newLine +
                    				"<img class=\"img_tiempo\" src=\"Imagenes/logo.jpg\" alt=\"Logo de la empresa\" title=\"Volver arriba\">" + newLine +
                    				"</a>" + newLine +
                    				"</article>");
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

                    writer.write("<div>" + newLine +
                    		"<p><h3>Hora: " + elementoHora.getElementsByTagName("hour_data").item(0).getTextContent() + "</h3>"+ "" + newLine +
                    		"Temperatura: " + elementoHora.getElementsByTagName("temperature").item(0).getTextContent() + "" + newLine +
                    		"Clima: " + elementoHora.getElementsByTagName("text").item(0).getTextContent() + "" + newLine +
                    		"Humedad: " + elementoHora.getElementsByTagName("humidity").item(0).getTextContent() + "</p>" + newLine +
                    		"</div>");
                }
                }
            contador++;
            }
            writer.write("</article>" + newLine +
            		"<article id=días class=\"nos\">" + newLine +
            		"<br></br>");
            contador=0;
            for (int x=0;x<=7;x++) {
            	listaDias = documento.getElementsByTagName("day"+contador);
            	
            for (int i = 0; i < listaDias.getLength(); i++) {
                Node dia = listaDias.item(i);
                if (dia.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoDias = (Element) dia;
                    
                    writer.write("<div>" + newLine +
                    		"<h3>Dia "+contador+"</h3>" + newLine +
                    		"<div>" + newLine +
                    		"<p>Temperatura: " + elementoDias.getElementsByTagName("temperature_max").item(0).getTextContent() + "</p>" + newLine +
                    		"</div>" + newLine +
                    		"<div>" + newLine +
                    		"<p> Clima:" + elementoDias.getElementsByTagName("text").item(0).getTextContent() + "</p>" + newLine +
                    		"</div>" + newLine +
                    		"<div>" + newLine +
                    		"<p> Humedad:" + elementoDias.getElementsByTagName("wind").item(0).getTextContent() + "</p>" + newLine +
                    		"</div>" + newLine +
                    		"</div>");                   
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
            
            System.out.println("Se ha generado correctamente");
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

