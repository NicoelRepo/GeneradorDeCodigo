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
import java.util.stream.Stream;

public class SingletonDatos
{
    private static SingletonDatos instance;
    public final List<Plantilla> listaPlantillas = new ArrayList<>();

    public static synchronized SingletonDatos getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonDatos();
        }
        return instance;
    }

    private SingletonDatos()
    {
        listaPlantillas.clear();
        cargarDatos();
    }

    private void cargarDatos()
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
            Node nodoPlantilla = listaDePlantillas.item(i);

            String namePlantilla = null;
            Optional<Node> optionalNode = hijosConCiertoNombre(nodoPlantilla, "name")
                    .findAny();
            if (optionalNode.isPresent())
            {
                namePlantilla = optionalNode.get().getTextContent();
            }
            else
            {
                System.out.println("No hay nombre definido");
            }

            // Obtener la raiz
            String raizPlantilla = null;
            optionalNode = hijosConCiertoNombre(nodoPlantilla, "root")
                    .findAny();
            if (optionalNode.isPresent())
            {
                raizPlantilla = optionalNode.get().getTextContent();
            }
            else
            {
                System.out.println("No hay raiz definida");
            }

            // Obtener los archivos
            List<CodeFile> listaArchivos = hijosConCiertoNombre(nodoPlantilla, "file")
                    .map(SingletonDatos::crearArchivoUsandoNodo)
                    .collect(Collectors.toList());

            this.listaPlantillas.add(new Plantilla(raizPlantilla, listaArchivos, namePlantilla));
        }
    }

    private static CodeFile crearArchivoUsandoNodo(Node nodoArchivo)
    {
        CodeFile codeFile = new CodeFile();
        hijosConCiertoNombre(nodoArchivo, null)
                .forEach(node -> {
                    String nodeName = node.getNodeName();
                    switch (nodeName)
                    {
                        case "name":
                            codeFile.setName(node.getTextContent());
                            break;

                        case "path":
                            codeFile.setPath(node.getTextContent());
                            break;

                        case "estrategys":
                            hijosConCiertoNombre(node, "estrategy")
                                    .forEach(nodeEstrategia -> {
                                        EstrategyGenerateText estrategia = null;
                                        try
                                        {
                                            estrategia = (EstrategyGenerateText) Class.forName(
                                                    nodeEstrategia.getAttributes().getNamedItem("estrategia").getTextContent()
                                            ).newInstance();
                                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
                                        {
                                            e.printStackTrace();
                                        }
                                        final EstrategyGenerateText estrategiaFinal = estrategia;
                                        codeFile.getEstrategysSecuence().add(estrategia);
                                        hijosConCiertoNombre(nodeEstrategia, "param")
                                                .forEach(nodeParam ->
                                                {
                                                    estrategiaFinal.getMapParameters().put(nodeEstrategia.getTextContent(), null);
                                                });
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

    private static Stream<Node> hijosConCiertoNombre(Node nodo, String nombre)
    {
        NodeList listaNodos = nodo.getChildNodes();
        return IntStream.range(0, listaNodos.getLength())
                .mapToObj(index -> listaNodos.item(index))
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE && (node.getNodeName().equals(nombre)) || nombre == null);
    }
}
