package ancheta.testGrafo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

import org.apache.xerces.parsers.DOMParser;
import org.junit.jupiter.api.Test;
/*
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
*/

import ancheta.comun.ClasePruebaGrafo;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.grafo.Grafo;
import ancheta.grafo.IVertice;
import ancheta.grafo.Vertice;

public class TestGrafo {

	private Grafo<String, ClasePruebaGrafo> grafo = new Grafo<String, ClasePruebaGrafo>();

	/*
	public void setUpEscenario1() {

		try {
			// Estas primeras lineas cargan el xml
			StringBuffer lectura = new StringBuffer();
			BufferedReader in = new BufferedReader(new FileReader("data/testGrafo.xml"));
			String str;
			while ((str = in.readLine()) != null)
				lectura.append(str);
			String xmlString = lectura.toString();
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new StringReader(xmlString)));
			Document documento = parser.getDocument();
			// ya habiendolo cargado con ayuda de xerces lo recorremos y metemos la
			// informacion que aqui se guarda en el grafo
			Element elementoRaiz = documento.getDocumentElement();
			// Se pide el item 1 pues se quiere cargar el primer escenario
			Node grafoXml = elementoRaiz.getChildNodes().item(1);
			cargarxml(grafoXml);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	private void cargarxml(Node grafoXml) {

		NodeList elementosGrafo = grafoXml.getChildNodes();
		int j = 0;
		for (int i = 0; i < elementosGrafo.getLength(); i++) {
			// el modulo 2 es por que en los pares hay informacion vacia ��?!
			if (i % 2 == 1) {
				if (j == 0) {
					// crear vertices
					// System.out.println("-------------------Vertices--------------------");
					Node vertices = elementosGrafo.item(i);
					NodeList losVertices = vertices.getChildNodes();
					int k = 0;
					while (k < losVertices.getLength()) {
						if (k % 2 == 1) {
							Node vertice = losVertices.item(k);
							NamedNodeMap atributos = vertice.getAttributes();
							String id = null;
							String nombre = null;
							for (int l = 0; l < atributos.getLength(); l++) {
								if (l % 2 == 0) {
									id = atributos.item(l).getNodeValue();
								} else {
									nombre = atributos.item(l).getNodeValue();
								}
							}
							// System.out.println("id: "+id+" - nombre: "+nombre);
							ClasePruebaGrafo nuevoVertice = new ClasePruebaGrafo(id, nombre);
							try {
								grafo.agregar(id, nuevoVertice);
							} catch (ElementoRepetidoException e) {
								fail("en el test no se mandan repetidos");
							}
						}
						k++;
					}
					// System.out.println("----------------------["+k/2+"]----------------------");
				} else if (j == 1) {
					// crear arcos
					// System.out.println();
					// System.out.println("---------------------Arcos---------------------");
					Node arcos = elementosGrafo.item(i);
					NodeList losArcos = arcos.getChildNodes();
					int k = 0;
					while (k < losArcos.getLength()) {
						if (k % 2 == 1) {
							Node arco = losArcos.item(k);
							NamedNodeMap atributos = arco.getAttributes();
							String inicio = null;
							String fin = null;
							int peso = 0;
							for (int l = 0; l < atributos.getLength(); l++) {
								if (l % 3 == 0) {
									fin = atributos.item(l).getNodeValue();
								} else if (l % 3 == 1) {
									inicio = atributos.item(l).getNodeValue();
								} else {
									peso = Integer.parseInt(atributos.item(l).getNodeValue());
								}
							}
							// System.out.println("inicio: "+inicio+" || fin: "+fin+" || peso: "+peso);
							// TODO GRAFO RELACIONAR
						}
						k++;
					}
					// System.out.println("----------------------["+k/2+"]----------------------");
				}
				j++;
			}
		}
	}

	@Test
	public void testAgregar() {
		setUpEscenario1();
		assertEquals(9, grafo.getIdActual());
		assertEquals(9, grafo.getNumeroElementos());
		Iterator<IVertice<ClasePruebaGrafo>> iter = grafo.iterator();
		// se revisa el orden en el iterador para asegurar que los guarda como
		// deseamos(solo con alguno elementos)
		int i = 0;
		while (iter.hasNext()) {
			Vertice<ClasePruebaGrafo> esteVer = (Vertice<ClasePruebaGrafo>) iter.next();
			if (i == 0) {
				assertEquals("Juan Manuel", esteVer.getElemento().getText());
			} else if (i == 1) {
				assertEquals("Ana", esteVer.getElemento().getText());
			} else if (i == 2) {
				assertEquals("Santiago", esteVer.getElemento().getText());
			} else if (i == 8) {
				assertEquals("Diego", esteVer.getElemento().getText());
			} else if (i > 8) {
				fail("no se esperaban mas");
			}
			i++;
		}
		// el elemento no es valido, contiene un id que ya se a usado
		ClasePruebaGrafo nuevoVertice = new ClasePruebaGrafo("100", "Estefania");
		try {
			grafo.agregar("20", nuevoVertice);
			fail("Deveria ser considerado elemento repetido");
		} catch (ElementoRepetidoException e) {
		}
		assertEquals(9, grafo.getIdActual());
		assertEquals(9, grafo.getNumeroElementos());
	}
	*/
}
