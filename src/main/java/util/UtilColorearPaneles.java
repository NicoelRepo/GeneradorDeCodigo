package util;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;
import java.util.function.Predicate;

import javax.swing.Box;
import javax.swing.JPanel;

public class UtilColorearPaneles
{

    /**
     * Colorea todos los paneles que se encuentran contenidos en el contenedor pasado como parametro.
     * @param contenedor
     * @param color
     */
    public static void coloreaPaneles(Container contenedor, Color color)
    {
        if (contenedor instanceof JPanel)
        {
            contenedor.setBackground(color);
        }

        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof Container)
            {
                coloreaPaneles((Container) componente, color);
            }
        }
    }


    /**
     * Esta función es una variación de la que recibe solo el contenedor y el color. Permite pasar como
     * parametro cuales son los tipos de objeto que deben ser coloreados.
     * @param contenedor
     * @param color
     * @param arrayClases
     */
    public static void coloreaComponentes(Container contenedor, Color color, Class<?>[] arrayClases)
    {
        final Container contenedorAcutal = contenedor;

        boolean flag = Arrays.asList(arrayClases).stream().anyMatch(new Predicate<Class<?>>() {
            @Override
            public boolean test(Class<?> clase)
            {
                return clase.isInstance(contenedorAcutal);
            }
        });

        if (flag)
        {
            contenedor.setBackground(color);
        }

        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof Container)
            {
                coloreaComponentes((Container) componente, color, arrayClases);
            }
        }
    }


    /**
     * Permite mostrar por consola el arbol de componentes a partir de un contenedor raiz.
     * @param contenedor
     * @param caracter: Normalmente es "" o "-".
     */
    public static void mostrarArbolComponentes(Container contenedor, String caracter)
    {
        Class<?> clase = contenedor.getClass();
        if (clase.getSimpleName() != null && !clase.getSimpleName().equals(""))
        {
            System.out.println(caracter + clase.getSimpleName());
        }
        else
        {
            System.out.println(caracter + contenedor.getClass());
        }
        caracter = "   " + caracter;
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof Container)
            {
                mostrarArbolComponentes((Container) componente, caracter);
            }
        }
    }

}
