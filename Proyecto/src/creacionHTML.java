import java.io.FileWriter;
import java.io.IOException;

public class creacionHTML {
	
	public static void crearPrintWriter() {
		try (FileWriter writer = new FileWriter("..\\WEB\\index.html")) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void encabezadoHTML() {
		try {
			FileWriter writer = new FileWriter("..\\WEB\\index.html");
			String newLine = System.lineSeparator();
			
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
