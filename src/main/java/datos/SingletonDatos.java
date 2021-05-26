package datos;

import entity.CodeFile;
import entity.Plantilla;
import estrategy.EstrategyGenerateText;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import readersAndWriters.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingletonDatos
{
    private static SingletonDatos instance;
    private final List<Plantilla> listaPlantillas = new ArrayList<>();

    private SingletonDatos()
    {
        Document document = null;
        try
        {
            document = XMLReader.readXML(new File("database.xml"));
        }
        catch (ParserConfigurationException | IOException | SAXException e)
        {
            e.printStackTrace();
        }

        NodeList listaDePlantillas = document.getElementsByTagName("plantilla");
        for (int i = 0; i < listaDePlantillas.getLength(); i++)
        {
            NodeList nodosPlantilla = listaDePlantillas.item(i).getChildNodes();
            List<Node> listaNodos = IntStream.range(0, nodosPlantilla.getLength()).
                    mapToObj(index -> nodosPlantilla.item(index)).
                    filter(node -> node.getNodeType() == Node.ELEMENT_NODE).
                    collect(Collectors.toList());

            // Obtener la raiz
            String raizPlantilla = null;
            Optional<Node> optionalNode = listaNodos.stream()
                    .filter(node -> node.getNodeName().equals("root"))
                    .findAny();
            if (optionalNode.isPresent())
            {
                raizPlantilla = optionalNode.get().getTextContent();
                System.out.println(raizPlantilla);
            }
            else
            {
                System.out.println("No hay raiz definida");
            }

            // Obtener los archivos
            List<CodeFile> listaArchivos = listaNodos.stream()
                    .filter(node -> node.getNodeName().equals("file"))
                    .map(SingletonDatos::crearArchivoUsandoNodo)
                    .collect(Collectors.toList());

            this.listaPlantillas.add(new Plantilla(raizPlantilla, listaArchivos));
        }
    }

    private static CodeFile crearArchivoUsandoNodo(Node nodoArchivo)
    {
        CodeFile codeFile = new CodeFile();
        NodeList listaNodosDeArchivo = nodoArchivo.getChildNodes();
        IntStream.range(0, listaNodosDeArchivo.getLength())
                .mapToObj(index -> listaNodosDeArchivo.item(index))
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .forEach(node -> {
                    String nodeName = node.getNodeName();
                    switch (nodeName)
                    {
                        case "name":
                            codeFile.setName(node.getTextContent());
                            break;

                        case "parameters":
                            NodeList listaNodosParametros = node.getChildNodes();
                            IntStream.range(0, listaNodosParametros.getLength())
                                    .mapToObj(index -> listaNodosParametros.item(index))
                                    .filter(node1 -> node1.getNodeType() == Node.ELEMENT_NODE && node1.getNodeName().equals("param"))
                                    .forEach(node1 -> {
                                        codeFile.getMapParameters().put(node1.getTextContent(), null);
                                    });
                            break;

                        case "path":
                            codeFile.setPath(node.getTextContent());
                            break;

                        case "estrategys":
                            NodeList listaNodosEstrategias = node.getChildNodes();
                            IntStream.range(0, listaNodosEstrategias.getLength())
                                    .mapToObj(index -> listaNodosEstrategias.item(index))
                                    .filter(node1 -> node1.getNodeType() == Node.ELEMENT_NODE && node1.getNodeName().equals("estrategy"))
                                    .forEach(node1 -> {
                                        try
                                        {
                                            codeFile.getEstrategysSecuence().add((EstrategyGenerateText) Class.forName(node1.getTextContent()).newInstance());
                                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    });
                            break;

                        case "extension":
                            codeFile.setExtention(node.getTextContent());
                            break;

                        default:
                            break;
                    }
                });

        return codeFile;
    }

    public static synchronized SingletonDatos getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonDatos();
        }
        return instance;
    }
}
