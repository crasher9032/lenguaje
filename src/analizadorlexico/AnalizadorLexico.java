package analizadorlexico;

import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * ´author JaviCo & Tavo
 */
public class AnalizadorLexico {

    public static void main(String[] args) {
        Archivo a = new Archivo();
        String text = a.LeerGrafico();
        String entrada = text.replaceAll("\n", "´");
        int contLinea = 1;
        boolean continuar = true;
        boolean fin = false;
        //String entrada = JOptionPane.showInputDialog("Escribe algo");
        String palabraAux = "";
        String simbolo = "";
        List<String> identificadores = new ArrayList<String>();
        List<String> tokens = new ArrayList<String>();
        List<String> auxList = new ArrayList<String>();
        List<String> lineList = new ArrayList<String>();
        tokens.add("codigo");
        int longitud = entrada.length();
        int contador = 0;
        int contSintax = 1;
        int contLlaves = 0;
        
        String salida = "1. ";
        int contSalida = 1;
        for (int i = 0; i < longitud; i++) {
            if (String.valueOf(entrada.charAt(i)).equals("´")) {
                contSalida++;
                System.out.println(salida);
                salida = String.valueOf(contSalida) + ". ";
            } else {
                salida = salida + String.valueOf(entrada.charAt(i));
            }
        }
        System.out.println("");

        while (!fin) {
            try {
                switch (String.valueOf(entrada.charAt(contador))) {
                    case "´":
                        palabraAux = "/n";
                        tokens.add("salto");
                        System.out.println(palabraAux + " es salto de linea");
                        palabraAux = "";
                        contador++;
                        break;
                    case " ":
                        contador++;
                        break;
                    case "+":
                        palabraAux = palabraAux + "+";
                        contador++;

                        if (contador < longitud) {
                            if (String.valueOf(entrada.charAt(contador)).equals("+")) {
                                palabraAux = palabraAux + "+";
                                tokens.add("incremento");
                                System.out.println(palabraAux + " es incremento");
                                contador++;
                            } else {
                                tokens.add("suma");
                                System.out.println(palabraAux + " es suma");
                            }
                        } else {
                            tokens.add("suma");
                            System.out.println(palabraAux + " es suma");
                        }

                        palabraAux = "";
                        break;
                    case "-":
                        palabraAux = palabraAux + "-";
                        contador++;
                        if (contador < longitud) {
                            if (String.valueOf(entrada.charAt(contador)).equals("-")) {
                                palabraAux = palabraAux + "-";
                                tokens.add("decremento");
                                System.out.println(palabraAux + " es decremento");
                                contador++;
                            } else {
                                tokens.add("resta");
                                System.out.println(palabraAux + " es resta");
                            }
                        } else {
                            tokens.add("resta");
                            System.out.println(palabraAux + " es resta");
                        }
                        palabraAux = "";
                        break;
                    case "*":
                        palabraAux = palabraAux + "*";
                        tokens.add("multiplicacion");
                        System.out.println(palabraAux + " es multiplicacion");
                        palabraAux = "";
                        contador++;
                        break;
                    case "/":
                        palabraAux = palabraAux + "/";
                        tokens.add("division");
                        System.out.println(palabraAux + " es division");
                        palabraAux = "";
                        contador++;
                        break;
                    case "'":
                        if (String.valueOf(tokens.get(tokens.size() - 1)) == "palabra") {
                            tokens.remove(tokens.size() - 1);
                        }
                        contador++;
                        for (int i = 1; i == 1;) {
                            if (String.valueOf(entrada.charAt(contador)).equals("'")) {
                                contador++;
                                tokens.add("palabra");
                                System.out.println(palabraAux + " es una palabra");
                                i = 0;
                            } else {
                                palabraAux = palabraAux + String.valueOf(entrada.charAt(contador));
                                contador++;
                            }
                        }
                        palabraAux = "";
                        break;
                    case "=":
                        palabraAux = palabraAux + "=";
                        contador++;

                        if (contador < longitud) {
                            if (String.valueOf(entrada.charAt(contador)).equals("=")) {
                                palabraAux = palabraAux + "=";
                                tokens.add("comparacion");
                                System.out.println(palabraAux + " es comparacion");
                                contador++;
                            } else {
                                tokens.add("error");
                                System.out.println(palabraAux + " es error lexico");
                            }
                        } else {
                            tokens.add("error");
                            System.out.println(palabraAux + " es error lexico");
                        }

                        palabraAux = "";
                        break;
                    case "(":
                        palabraAux = palabraAux + "(";
                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "if") {
                            System.out.println("Inicio de condicion");
                        }*/
                        tokens.add("parentesis");
                        System.out.println(palabraAux + " abre parentesis");
                        palabraAux = "";
                        contador++;
                        break;
                    case ")":
                        palabraAux = palabraAux + ")";

                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "parentesis") {
                            tokens.remove(tokens.size() - 1);
                        }
                        System.out.println(palabraAux + " cierra parentesis");
                        if (String.valueOf(tokens.get(tokens.size() - 1)) == "if") {
                            System.out.println("Cierre de condicion");
                            tokens.add("condicion");
                        }*/
                        tokens.add("cierraparentesis");
                        System.out.println(palabraAux + " cierra parentesis");
                        palabraAux = "";
                        contador++;
                        break;
                    case "{":
                        palabraAux = palabraAux + "{";
                        tokens.add("llave");
                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "condicion") {
                            System.out.println("cuerpo del if");
                        }*/
                        System.out.println(palabraAux + " abre llaves");
                        contador++;
                        palabraAux = "";
                        break;
                    case "}":
                        palabraAux = palabraAux + "}";
                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "llaves") {
                            tokens.remove(tokens.size() - 1);
                            System.out.println(palabraAux + " cierra llaves");
                        }
                        if (String.valueOf(tokens.get(tokens.size() - 1)) == "condicion") {
                            tokens.remove(tokens.size() - 1);
                            System.out.println("fin de if");
                            tokens.remove(tokens.size() - 1);
                        }*/
                        tokens.add("cierrallave");
                        System.out.println(palabraAux + " cierra llaves");
                        palabraAux = "";
                        contador++;
                        break;
                    case ";":
                        /*System.out.println("Error en la linea "+contLinea);
                        if (tokens.isEmpty()) {
                        } else {
                            tokens.remove(0);
                            for (int i = 0; i < tokens.size(); i++) {
                                System.out.println("-" + String.valueOf(tokens.get(i)));
                            }
                        }
                        contLinea++;*/
                        palabraAux = ";";
                        tokens.add("fin");
                        System.out.println(palabraAux + " es fin de linea");
                        palabraAux = "";
                        contador++;
                        break;
                    case "<":
                        palabraAux = palabraAux + "<";
                        contador++;

                        if (contador < longitud) {
                            switch (String.valueOf(entrada.charAt(contador))) {
                                case "=":
                                    palabraAux = palabraAux + "=";
                                    tokens.add("menor igual");
                                    System.out.println(palabraAux + " es menor igual que");
                                    contador++;
                                    break;
                                case ">":
                                    palabraAux = palabraAux + ">";
                                    tokens.add("diferente");
                                    System.out.println(palabraAux + " es diferente que");
                                    contador++;
                                    break;
                                default:
                                    tokens.add("menor");
                                    System.out.println(palabraAux + " es menor que");
                                    break;
                            }
                        } else {
                            tokens.add("menor");
                            System.out.println(palabraAux + " es menor que");
                        }
                        palabraAux = "";
                        /*contador++;
                        if(String.valueOf(entrada.charAt(contador)).equals(">")){
                            tokens.add("diferente");
                        }else{
                            
                        }*/
                        break;
                    case ">":
                        palabraAux = palabraAux + ">";
                        contador++;

                        if (contador < longitud) {
                            if (String.valueOf(entrada.charAt(contador)).equals("=")) {
                                palabraAux = palabraAux + "=";
                                tokens.add("mayor igual");
                                System.out.println(palabraAux + " es mayor igual que");
                                contador++;
                            } else {
                                tokens.add("mayor");
                                System.out.println(palabraAux + " es mayor que");
                            }
                        } else {
                            tokens.add("mayor");
                            System.out.println(palabraAux + " es mayor que");
                        }
                        palabraAux = "";
                        break;
                    case ":":
                        palabraAux = palabraAux + ":";
                        contador++;

                        if (contador < longitud) {
                            if (String.valueOf(entrada.charAt(contador)).equals("=")) {
                                palabraAux = palabraAux + "=";
                                tokens.add("igualacion");
                                System.out.println(palabraAux + " es igualacion");
                                contador++;
                            } else {
                                tokens.add("error");
                                System.out.println(palabraAux + " falta = (err lexico)");
                            }
                        } else {
                            tokens.add("error");
                            System.out.println(palabraAux + " falta = (err lexico)");
                        }

                        palabraAux = "";

                        /*contador++;
                        if(String.valueOf(entrada.charAt(contador)).equals("=")){
                            if(tokens.get(tokens.size()-1)=="identificador"){
                            tokens.add("asignacion");
                            contador++;    
                            }else{
                            System.out.println("Falta identificador");
                            }
                        }else{
                            
                        }*/
                        break;
                    default:
                        simbolo = String.valueOf(entrada.charAt(contador));
                        for (int i = contador; i < longitud; i++) {
                            if (String.valueOf(entrada.charAt(contador)).matches("[A-Z].*")
                                    || String.valueOf(entrada.charAt(contador)).matches("[a-z].*")
                                    || String.valueOf(entrada.charAt(contador)).matches("[0-9].*")
                                    || String.valueOf(entrada.charAt(contador)).equals("_")) {
                                palabraAux = palabraAux + String.valueOf(entrada.charAt(contador));
                                contador++;
                            } else {
                                i = longitud + 1;
                            }
                        }
                        switch (palabraAux) {
                            case "si":
                                System.out.println(palabraAux + " es palabra reservada");
                                tokens.add("if");
                                /*if(String.valueOf(entrada.charAt(contador)).equals("(")){
                                    
                                }else{
                                    
                                }*/
                                break;
                            case "sino":
                                System.out.println(palabraAux + " es palabra reservada");
                                tokens.add("else");
                                /*System.out.println(palabraAux + " es palabra reservada");
                                if (tokens.get(tokens.size() - 1) == "if") {
                                    tokens.add("else");
                                    contador++;
                                } else {
                                    System.out.println("Falta si");
                                }*/
                                break;
                            case "pala":
                                tokens.add("String");
                                System.out.println(palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "num":
                                tokens.add("int");
                                System.out.println(palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "ciclo":
                                tokens.add("for");
                                System.out.println(palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "otto":
                                tokens.add("break");
                                System.out.println(palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "imprimir":
                                tokens.add("print");
                                System.out.println(palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            default:
                                if (simbolo.matches("[A-Z].*") || simbolo.matches("[a-z].*")
                                        || simbolo.matches("[0-9].*") || simbolo.equals("_")) {
                                    if (palabraAux.matches("[0-9]*")) {
                                        if (simbolo.equals("0") && palabraAux.length() != 1) {
                                            tokens.add("error");
                                            System.out.println(palabraAux + " es un numero invalido (err lexico)");
                                        } else {
                                            tokens.add("numero");
                                            System.out.println(palabraAux + " es un numero");
                                        }
                                        continuar = false;
                                    }
                                    if (continuar) {
                                        if (String.valueOf(palabraAux.charAt(0)).matches("[0-9].*")
                                                || String.valueOf(palabraAux.charAt(0)).equals("_")) {
                                            tokens.add("error");
                                            System.out.println(palabraAux + " es identif. INVALIDO (err lexico)");
                                        } else {
                                            identificadores.add(palabraAux);
                                            tokens.add("identif");
                                            System.out.println(palabraAux + " es identificador");
                                        }
                                    }
                                } else {
                                    tokens.add("error");
                                    System.out.println(simbolo + " es un simbolo desconocido");
                                    contador++;
                                }
                                break;
                        }
                        //contador++;
                        continuar = true;
                        palabraAux = "";
                        simbolo = "";
                        break;
                }
            } catch (Exception e) {
                //fin = false;
                break;
            }
        }

        System.out.println("");
        //Sintactico
        while (!fin) {
            try {
                switch (tokens.get(contSintax)) {
                    case "salto":
                        contLinea++;
                        contSintax++;
                        break;
                    case "identif":
                        try {
                            contSintax++;
                            if(tokens.get(contSintax).equals("igualacion")){
                                contSintax++;
                                if (tokens.get(contSintax).equals("identif")
                                || tokens.get(contSintax).equals("numero")
                                || tokens.get(contSintax).equals("palabra")) {
                                    for (int i = 1; i == 1;) {
                                        if (tokens.get(contSintax).equals("identif")
                                        || tokens.get(contSintax).equals("numero")
                                        || tokens.get(contSintax).equals("palabra")) {
                                            contSintax++;
                                            switch (tokens.get(contSintax)) {
                                                case "suma":
                                                    contSintax++;
                                                    break;
                                                case "resta":
                                                    contSintax++;
                                                    break;
                                                case "multiplicacion":
                                                    contSintax++;
                                                    break;
                                                case "division":
                                                    contSintax++;
                                                    break;
                                                case "fin":
                                                    if (tokens.get(contSintax + 1).equals("salto")) {
                                                        contLinea++;
                                                        contSintax = contSintax + 2;
                                                    } else {
                                                        contSintax++;
                                                    }
                                                    i = 0;
                                                    break;
                                                default:
                                                    continuar = false;
                                                    i = 0;
                                                    break;
                                            }
                                        } else {
                                            continuar = false;
                                        }
                                    }
                                } else {
                                    continuar = false;
                                }
                            }else{
                                continuar = false;
                            }

                            if (!continuar) {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "fin":
                                                if (tokens.get(j + 1).equals("salto")) {
                                                    contLinea++;
                                                    contSintax = j + 2;
                                                } else {
                                                    contSintax = j + 1;
                                                }
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Err. Sint. en linea " + contLinea);
                            System.out.println(" -" + tokens.get(contSintax));
                            for (int i = 1; i == 1;) {
                                for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                    switch (tokens.get(j)) {
                                        case "fin":
                                            if (tokens.get(j + 1).equals("salto")) {
                                                contLinea++;
                                                contSintax = j + 2;
                                            } else {
                                                contSintax = j + 1;
                                            }
                                            j = tokens.size() + 5;
                                            i = 0;
                                            break;
                                        case "salto":
                                            contLinea++;
                                            contSintax = j + 1;
                                            j = tokens.size() + 5;
                                            i = 0;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            fin = true;
                            break;
                        }
                        continuar = true;
                        break;
                    case "String":
                        try {
                            contSintax++;
                            if (tokens.get(contSintax).equals("identif")) {
                                contSintax++;
                                switch (tokens.get(contSintax)) {
                                    case "igualacion":
                                        contSintax++;
                                        if (tokens.get(contSintax).equals("identif")
                                        || tokens.get(contSintax).equals("palabra")) {
                                            for (int i = 1; i == 1;) {
                                                if (tokens.get(contSintax).equals("identif")
                                                || tokens.get(contSintax).equals("palabra")) {
                                                    contSintax++;
                                                    switch (tokens.get(contSintax)) {
                                                        case "suma":
                                                            contSintax++;
                                                            break;
                                                        case "fin":
                                                            if (tokens.get(contSintax + 1).equals("salto")) {
                                                                contLinea++;
                                                                contSintax = contSintax + 2;
                                                            } else {
                                                                contSintax++;
                                                            }
                                                            i = 0;
                                                            break;
                                                        default:
                                                            continuar = false;
                                                            i = 0;
                                                            break;
                                                    }
                                                } else {
                                                    continuar = false;
                                                }
                                            }
                                        } else {
                                            continuar = false;
                                        }
                                        break;
                                    case "fin":
                                        contSintax++;
                                        break;
                                    default:
                                        continuar = false;
                                        break;
                                }
                            } else {
                                continuar = false;
                            }

                            if (!continuar) {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "fin":
                                                if (tokens.get(j + 1).equals("salto")) {
                                                    contLinea++;
                                                    contSintax = j + 2;
                                                } else {
                                                    contSintax = j + 1;
                                                }
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Err. Sint. en linea " + contLinea);
                            System.out.println(" -" + tokens.get(contSintax));
                            for (int i = 1; i == 1;) {
                                for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                    switch (tokens.get(j)) {
                                        case "fin":
                                            if (tokens.get(j + 1).equals("salto")) {
                                                contLinea++;
                                                contSintax = j + 2;
                                            } else {
                                                contSintax = j + 1;
                                            }
                                            j = tokens.size() + 5;
                                            i = 0;
                                            break;
                                        case "salto":
                                            contLinea++;
                                            contSintax = j + 1;
                                            j = tokens.size() + 5;
                                            i = 0;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            fin = true;
                            break;
                        }
                        continuar = true;
                        break;
                    case "int":
                        try {
                            contSintax++;
                            if (tokens.get(contSintax).equals("identif")) {
                                contSintax++;
                                switch (tokens.get(contSintax)) {
                                    case "igualacion":
                                        contSintax++;
                                        if (tokens.get(contSintax).equals("identif")
                                        || tokens.get(contSintax).equals("numero")) {
                                            for (int i = 1; i == 1;) {
                                                if (tokens.get(contSintax).equals("identif")
                                                || tokens.get(contSintax).equals("numero")) {
                                                    contSintax++;
                                                    switch (tokens.get(contSintax)) {
                                                        case "suma":
                                                            contSintax++;
                                                            break;
                                                        case "resta":
                                                            contSintax++;
                                                            break;
                                                        case "multiplicacion":
                                                            contSintax++;
                                                            break;
                                                        case "division":
                                                            contSintax++;
                                                            break;
                                                        case "fin":
                                                            if (tokens.get(contSintax + 1).equals("salto")) {
                                                                contLinea++;
                                                                contSintax = contSintax + 2;
                                                            } else {
                                                                contSintax++;
                                                            }
                                                            i = 0;
                                                            break;
                                                        default:
                                                            continuar = false;
                                                            i = 0;
                                                            break;
                                                    }
                                                } else {
                                                    continuar = false;
                                                }
                                            }
                                        } else {
                                            continuar = false;
                                        }
                                        break;
                                    case "fin":
                                        contSintax++;
                                        break;
                                    default:
                                        continuar = false;
                                        break;
                                }
                            } else {
                                continuar = false;
                            }

                            if (!continuar) {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "fin":
                                                if (tokens.get(j + 1).equals("salto")) {
                                                    contLinea++;
                                                    contSintax = j + 2;
                                                } else {
                                                    contSintax = j + 1;
                                                }
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Err. Sint. en linea " + contLinea);
                            System.out.println(" -" + tokens.get(contSintax));
                            for (int i = 1; i == 1;) {
                                for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                    switch (tokens.get(j)) {
                                        case "fin":
                                            if (tokens.get(j + 1).equals("salto")) {
                                                contLinea++;
                                                contSintax = j + 2;
                                            } else {
                                                contSintax = j + 1;
                                            }
                                            j = tokens.size() + 5;
                                            i = 0;
                                            break;
                                        case "salto":
                                            contLinea++;
                                            contSintax = j + 1;
                                            j = tokens.size() + 5;
                                            i = 0;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            fin = true;
                            break;
                        }
                        continuar = true;
                        break;
                    case "print":
                        try {
                            contSintax++;
                            if (tokens.get(contSintax).equals("parentesis")) {
                                contSintax++;
                                for (int i = 1; i == 1;) {
                                    if (tokens.get(contSintax).equals("identif")
                                    || tokens.get(contSintax).equals("palabra")) {
                                        contSintax++;
                                        switch (tokens.get(contSintax)) {
                                            case "suma":
                                                contSintax++;
                                                break;
                                            case "cierraparentesis":
                                                contSintax++;
                                                if (tokens.get(contSintax).equals("fin")) {
                                                    contSintax++;
                                                    if (tokens.get(contSintax).equals("salto")) {
                                                        contLinea++;
                                                    }
                                                    contSintax++;
                                                } else {
                                                    continuar = false;
                                                    break;
                                                }
                                                i = 0;
                                                break;
                                            default:
                                                continuar = false;
                                                i = 0;
                                                break;
                                        }
                                    } else {
                                        continuar = false;
                                        i = 0;
                                    }
                                }
                            }else{
                                continuar = false;
                            }
                            
                            if (!continuar) {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "fin":
                                                if (tokens.get(j + 1).equals("salto")) {
                                                    contLinea++;
                                                    contSintax = j + 2;
                                                } else {
                                                    contSintax = j + 1;
                                                }
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!continuar) {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "fin":
                                                if (tokens.get(j + 1).equals("salto")) {
                                                    contLinea++;
                                                    contSintax = j + 2;
                                                } else {
                                                    contSintax = j + 1;
                                                }
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                        continuar = true;
                        break;
                    case "for":
                        try {
                            if (tokens.get(contSintax + 1).equals("parentesis")) {
                                contSintax = contSintax + 2;
                                switch (tokens.get(contSintax)) {
                                    case "int":
                                        if (tokens.get(contSintax + 1).equals("identif")
                                                && tokens.get(contSintax + 2).equals("igualacion")
                                                && tokens.get(contSintax + 3).equals("numero")
                                                || tokens.get(contSintax + 3).equals("identif")
                                                && tokens.get(contSintax + 4).equals("fin")) {
                                            contSintax = contSintax + 5;
                                        } else {
                                            continuar = false;
                                        }
                                        break;
                                    case "identif":
                                        if (tokens.get(contSintax + 1).equals("igualacion")
                                                && tokens.get(contSintax + 2).equals("numero")
                                                || tokens.get(contSintax + 2).equals("identif")
                                                && tokens.get(contSintax + 3).equals("fin")) {
                                            contSintax = contSintax + 4;
                                        } else {
                                            continuar = false;
                                        }
                                        break;
                                    default:
                                        continuar = false;
                                        break;
                                }
                            } else {
                                continuar = false;
                            }

                            if (continuar) {
                                if (tokens.get(contSintax).equals("identif")) {
                                    if (tokens.get(contSintax + 1).equals("comparacion")
                                            || tokens.get(contSintax + 1).equals("diferente")
                                            || tokens.get(contSintax + 1).equals("menor")
                                            || tokens.get(contSintax + 1).equals("menor igual")
                                            || tokens.get(contSintax + 1).equals("mayor")
                                            || tokens.get(contSintax + 1).equals("mayor igual")) {
                                        if (tokens.get(contSintax + 2).equals("numero")
                                                || tokens.get(contSintax + 2).equals("identif")) {
                                            if (tokens.get(contSintax + 3).equals("fin")) {
                                                contSintax = contSintax + 4;
                                            } else {
                                                continuar = false;
                                            }
                                        } else {
                                            continuar = false;
                                        }
                                    } else {
                                        continuar = false;
                                    }
                                } else {
                                    continuar = false;
                                }
                            }
                            if (continuar) {
                                switch (tokens.get(contSintax)) {
                                    case "identif":
                                        if (tokens.get(contSintax + 1).equals("incremento")
                                                || tokens.get(contSintax + 1).equals("decremento")) {
                                            if (tokens.get(contSintax + 2).equals("cierraparentesis")
                                            && tokens.get(contSintax + 3).equals("llave")) {
                                                contLlaves++;
                                                auxList.add("for");
                                                lineList.add(String.valueOf(contLinea));
                                                contSintax = contSintax + 4;
                                            } else {
                                                continuar = false;
                                            }
                                        } else {
                                            continuar = false;
                                        }
                                        break;
                                    case "cierraparentesis":
                                        if (tokens.get(contSintax + 1).equals("llave")) {
                                            auxList.add("for");
                                            lineList.add(String.valueOf(contLinea));
                                            contSintax = contSintax + 2;
                                        } else {
                                            continuar = false;
                                        }
                                        break;
                                }
                            }
                            if (!continuar) {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax + 1));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            //1000
                                            case "llave":
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "cierrallave":
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {

                        }
                        break;
                    case "if":
                        try {
                            if (tokens.get(contSintax + 1).equals("parentesis")
                                    && tokens.get(contSintax + 2).equals("identif")) {
                                contSintax = contSintax + 3;
                                if (tokens.get(contSintax).equals("comparacion")
                                || tokens.get(contSintax).equals("diferente")) {
                                    if (tokens.get(contSintax + 1).equals("numero")
                                    || tokens.get(contSintax + 1).equals("palabra")
                                    || tokens.get(contSintax + 1).equals("identif")) {
                                        contSintax = contSintax + 2;
                                    } else {
                                        continuar = false;
                                        System.out.println("Err. Sint. en linea " + contLinea);
                                        System.out.println(" -" + tokens.get(contSintax + 1));
                                        for (int i = 1; i == 1;) {
                                            for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                                switch (tokens.get(j)) {
                                                    case "salto":
                                                        contLinea++;
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    case "parentesis":
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    case "cierraparentesis":
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                } else if (tokens.get(contSintax).equals("menor")
                                        || tokens.get(contSintax).equals("menor igual")
                                        || tokens.get(contSintax).equals("mayor")
                                        || tokens.get(contSintax).equals("mayor igual")) {
                                    if (tokens.get(contSintax + 1).equals("numero")
                                            || tokens.get(contSintax + 1).equals("identif")) {
                                        contSintax = contSintax + 2;
                                    } else {
                                        continuar = false;
                                        System.out.println("Err. Sint. en linea " + contLinea);
                                        System.out.println(" -" + tokens.get(contSintax + 1));
                                        for (int i = 1; i == 1;) {
                                            for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                                switch (tokens.get(j)) {
                                                    case "salto":
                                                        contLinea++;
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    case "parentesis":
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    case "cierraparentesis":
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    continuar = false;
                                    System.out.println("Err. Sint. en linea " + contLinea);
                                    System.out.println(" -" + tokens.get(contSintax));
                                    for (int i = 1; i == 1;) {
                                        for (int j = contSintax; j <= tokens.size(); j++) {
                                            switch (tokens.get(j)) {
                                                case "salto":
                                                    contLinea++;
                                                    contSintax = j + 1;
                                                    j = tokens.size() + 5;
                                                    i = 0;
                                                    break;
                                                case "llave":
                                                    contSintax = j + 1;
                                                    j = tokens.size() + 5;
                                                    i = 0;
                                                    break;
                                                case "cierrallave":
                                                    contSintax = j + 1;
                                                    j = tokens.size() + 5;
                                                    i = 0;
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                }
                                if (continuar) {
                                    if (tokens.get(contSintax).equals("cierraparentesis")
                                    && tokens.get(contSintax + 1).equals("llave")) {
                                        contLlaves++;
                                        auxList.add("if");
                                        lineList.add(String.valueOf(contLinea));
                                        contSintax = contSintax + 2;
                                    } else {
                                        System.out.println("Err. Sint. en linea " + contLinea);
                                        System.out.println(" -" + tokens.get(contSintax));
                                        System.out.println(" -" + tokens.get(contSintax + 1));
                                        for (int i = 1; i == 1;) {
                                            for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                                switch (tokens.get(j)) {
                                                    case "salto":
                                                        contLinea++;
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    case "llave":
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                                                        i = 0;
                                                        break;
                                                    case "cierrallave":
                                                        contSintax = j + 1;
                                                        j = tokens.size() + 5;
                    
                                                        i = 0;
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax + 1));
                                System.out.println(" -" + tokens.get(contSintax + 2));
                                for (int i = 1; i == 1;) {
                                    for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                        switch (tokens.get(j)) {
                                            case "salto":
                                                contLinea++;
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "llave":
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            case "cierrallave":
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            //System.out.println("cae en el catch");
                            //contSintax++;
                        }
                        continuar = true;
                        break;
                    case "llave":
                        try {
                            System.out.println("Err. Sint. en linea " + contLinea);
                            System.out.println(" -" + tokens.get(contSintax));
                            contSintax++;
                        } catch (Exception e) {
                        }
                        break;
                    case "cierrallave":
                        try {
                            contLlaves--;
                            contSintax++;
                            
                            System.out.println("Llaves: "+contLlaves);
                            switch (auxList.get(auxList.size() - 1)) {
                                case "if":
                                    for (int i = 1; i == 1;) {
                                        switch (tokens.get(contSintax)) {
                                            case "else":
                                                contSintax++;
                                                if (tokens.get(contSintax).equals("llave")) {
                                                    contLlaves++;
                                                    auxList.add("else");
                                                    contSintax++;
                                                } else {
                                                    System.out.println("Err. Sint. en linea " + contLinea);
                                                    System.out.println(" -" + tokens.get(contSintax));
                                                    for (int h = 1; h == 1;) {
                                                        for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                                            switch (tokens.get(j)) {
                                                                case "salto":
                                                                    contLinea++;
                                                                    contSintax = j + 1;
                                                                    j = tokens.size() + 5;
                                                                    break;
                                                                case "llave":
                                                                    contSintax = j + 1;
                                                                    j = tokens.size() + 5;
                                                                    break;
                                                                case "cierrallave":
                                                                    contSintax = j + 1;
                                                                    j = tokens.size() + 5;
                                                                    break;
                                                                default:
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                }
                                                i = 0;
                                                break;
                                            case "salto":
                                                contLinea++;
                                                contSintax++;
                                                break;
                                            default:
                                                auxList.remove(auxList.size() - 1);
                                                i = 0;
                                                break;
                                        }
                                    }
                                    break;
                                case "for":
                                    if(auxList.isEmpty()){
                                        System.out.println("Se desborda lista");
                                        System.out.println("Err. Sint. en linea " + contLinea);
                                        System.out.println(" -" + tokens.get(contSintax-1));
                                    }else{
                                        auxList.remove(auxList.size() - 1);
                                    }
                                    break;
                                case "else":
                                    if (auxList.get(auxList.size() - 2).equals("if")) {
                                        auxList.remove(auxList.size() - 1);
                                        auxList.remove(auxList.size() - 1);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            if(auxList.isEmpty()){
                                System.out.println("Se desborda lista");
                                System.out.println("Err. Sint. en linea " + contLinea);
                                System.out.println(" -" + tokens.get(contSintax-1));
                                contSintax++;
                            }else{
                                System.out.println("NO se desborda lista");
                                auxList.remove(auxList.size() - 1);
                                contSintax++;
                            }
                        }
                        break;
                    default:
                        System.out.println("Err. Sint. en linea " + contLinea);
                        System.out.println(" -" + tokens.get(contSintax));
                        for (int i = 1; i == 1;) {
                            for (int j = contSintax; j <= tokens.size(); j++) {
                                switch (tokens.get(j)) {
                                    case "fin":
                                        contSintax = j + 1;
                                        j = tokens.size() + 5;
                                        i = 0;
                                        break;
                                    case "salto":
                                        contLinea++;
                                        contSintax = j + 1;
                                        j = tokens.size() + 5;
                                        i = 0;
                                        break;
                                    case "llave":
                                        contSintax = j + 1;
                                        j = tokens.size() + 5;
                                        i = 0;
                                        break;
                                    case "cierrallave":
                                        contSintax = j + 1;
                                        j = tokens.size() + 5;
                                        i = 0;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                break;
            }
            //System.out.println(contLinea);
        }
        try{
            System.out.println(auxList);
            for(int i=0;i<=auxList.size();i++){
                System.out.println("Err. Sint. en linea " + lineList.get(i));
                System.out.println(" -" + auxList.get(i));
            }
        }catch(Exception e){
            System.out.println("Entra a otro lado");
        }
        /*if (tokens.isEmpty()) {
        } else {
            tokens.remove(0);
            System.out.println("Errores sintacticos:");
            //System.out.println("Error en la linea "+ contLinea);
            for (int i = 0; i < tokens.size(); i++) {
                System.out.println("-" + String.valueOf(tokens.get(i)));
            }
        }*/
    }
}