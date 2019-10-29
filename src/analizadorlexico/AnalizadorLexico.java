package analizadorlexico;

import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author JaviCo & Tavo
 */
public class AnalizadorLexico {

    public static void main(String[] args) {
        //Archivo a = new Archivo();
        //String entrada=a.LeerGrafico();
        //System.out.println(entrada);
        boolean continuar = true;
        boolean fin = false;
        String entrada = JOptionPane.showInputDialog("Escribe algo");
        String palabraAux = "";
        String simbolo = "";
        List<String> identificadores = new ArrayList<String>();
        List<String> tokens = new ArrayList<String>();
        tokens.add("codigo");
        int longitud = entrada.length();
        int contador = 0;
        int contSintax = 1;

        System.out.println(entrada);
        System.out.println("");

        while (!fin) {
            try {
                switch (String.valueOf(entrada.charAt(contador))) {
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
                        if(String.valueOf(tokens.get(tokens.size()-1))=="palabra"){
                            tokens.remove(tokens.size()-1);
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
                        if(String.valueOf(tokens.get(tokens.size()-1))=="if"){
                            System.out.println("Inicio de condicion");
                        }
                        tokens.add("parentesis");
                        System.out.println(palabraAux + " abre parentesis");
                        palabraAux = "";
                        contador++;
                        break;
                    case ")":
                        palabraAux = palabraAux + ")";
                        if(String.valueOf(tokens.get(tokens.size()-1))=="parentesis"){
                            tokens.remove(tokens.size()-1);
                        }
                        System.out.println(palabraAux + " cierra parentesis");
                        if(String.valueOf(tokens.get(tokens.size()-1))=="if"){
                            System.out.println("Cierre de condicion");
                            tokens.add("condicion");
                        }
                        palabraAux = "";
                        contador++;
                        break;
                    case "{":
                        palabraAux = palabraAux + "{";
                        tokens.add("llaves");
                        if(String.valueOf(tokens.get(tokens.size()-1))=="condicion"){
                            System.out.println("cuerpo del if");
                        }
                        System.out.println(palabraAux + " abre llaves");
                        contador++;
                        palabraAux = "";
                        break;
                    case "}":
                        palabraAux = palabraAux + "}";
                        if(String.valueOf(tokens.get(tokens.size()-1))=="llaves"){
                            tokens.remove(tokens.size()-1);
                            System.out.println(palabraAux + " cierra llaves");
                        }
                        if(String.valueOf(tokens.get(tokens.size()-1))=="condicion"){
                            tokens.remove(tokens.size()-1);
                            System.out.println("fin de if");
                            tokens.remove(tokens.size()-1);
                        }
                        palabraAux = "";
                        contador++;
                        break;
                    case ";":
                        palabraAux = palabraAux + ";";
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
                                if (tokens.get(tokens.size() - 1) == "if") {
                                    tokens.add("else");
                                    contador++;
                                } else {
                                    System.out.println("Falta si");
                                }
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
                                            System.out.println(palabraAux + " es ident. INVALIDO (err lexico)");
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
        while (!fin) {
            try {
                switch (tokens.get(contSintax)) {
                    case "String":
                        try {
                            if (tokens.get(contSintax + 1).equals("identif")
                                    && tokens.get(contSintax + 2).equals("fin")) {
                                System.out.println(tokens.get(contSintax) + " " + tokens.get(contSintax + 1)
                                        + " " + tokens.get(contSintax + 2) + " ES CORRECTO");
                                contSintax = contSintax + 3;
                                continuar = false;
                            }
                            if (continuar) {
                                palabraAux = tokens.get(contSintax) + " " + tokens.get(contSintax + 1)
                                        + " " + tokens.get(contSintax + 2);
                                if (tokens.get(contSintax + 1).equals("identif")
                                        && tokens.get(contSintax + 2).equals("igualacion")) {
                                    contSintax = contSintax + 3;
                                    for (int i = 1; i == 1;) {
                                        if (tokens.get(contSintax).equals("identif")
                                                || tokens.get(contSintax).equals("palabra")) {
                                            palabraAux = palabraAux + " " + tokens.get(contSintax);
                                            contSintax++;
                                            switch (tokens.get(contSintax)) {
                                                case "suma":
                                                    palabraAux = palabraAux + " " + tokens.get(contSintax);
                                                    contSintax++;
                                                    break;
                                                case "fin":
                                                    palabraAux = palabraAux + " " + tokens.get(contSintax);
                                                    System.out.println(palabraAux + " ES CORRECTO");
                                                    contSintax++;
                                                    i = 0;
                                                    break;
                                                default:
                                                    for (int h = 1; h == 1;) {
                                                        for (int j = contSintax; j <= tokens.size(); j++) {
                                                            if (tokens.get(j).equals("fin")) {
                                                                palabraAux = palabraAux + " " + tokens.get(j);
                                                                contSintax = j + 1;
                                                                j = tokens.size() + 5;
                                                                h = 0;
                                                            } else {
                                                                palabraAux = palabraAux + " " + tokens.get(j);
                                                            }
                                                        }
                                                    }
                                                    System.out.println(palabraAux + " es ERROR SINTACTICO");
                                                    break;
                                            }
                                        } else {
                                            palabraAux = palabraAux + " " + tokens.get(contSintax);
                                            System.out.println(palabraAux + " es ERROR SINTACTICO");
                                            i = 0;
                                            contSintax++;
                                        }
                                    }
                                } else {
                                    for (int i = 1; i == 1;) {
                                        for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                            if (tokens.get(j).equals("fin")) {
                                                palabraAux = palabraAux + " " + tokens.get(j);
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                            } else {
                                                palabraAux = palabraAux + " " + tokens.get(j);
                                            }
                                        }
                                    }
                                    System.out.println(palabraAux + " es ERROR SINTACTICO");
                                }
                            }
                            palabraAux = "";
                            continuar = true;
                        } catch (Exception e) {
                            System.out.println(palabraAux + " es ERROR SINTACTICO");
                            break;
                        }
                        break;
                    case "int":
                        try {
                            if (tokens.get(contSintax + 1).equals("identif")
                                    && tokens.get(contSintax + 2).equals("fin")) {
                                System.out.println(tokens.get(contSintax) + " " + tokens.get(contSintax + 1)
                                        + " " + tokens.get(contSintax + 2) + " ES CORRECTO");
                                contSintax = contSintax + 3;
                                continuar = false;
                            }
                            if (continuar) {
                                palabraAux = tokens.get(contSintax) + " " + tokens.get(contSintax + 1)
                                        + " " + tokens.get(contSintax + 2);
                                if (tokens.get(contSintax + 1).equals("identif")
                                        && tokens.get(contSintax + 2).equals("igualacion")) {
                                    contSintax = contSintax + 3;
                                    for (int i = 1; i == 1;) {
                                        if (tokens.get(contSintax).equals("identif")
                                                || tokens.get(contSintax).equals("numero")) {
                                            palabraAux = palabraAux + " " + tokens.get(contSintax);
                                            contSintax++;
                                            switch (tokens.get(contSintax)) {
                                                case "suma":
                                                    palabraAux = palabraAux + " " + tokens.get(contSintax);
                                                    contSintax++;
                                                    break;
                                                case "resta":
                                                    palabraAux = palabraAux + " " + tokens.get(contSintax);
                                                    contSintax++;                                                    
                                                    break;
                                                case "fin":
                                                    palabraAux = palabraAux + " " + tokens.get(contSintax);
                                                    System.out.println(palabraAux + " ES CORRECTO");
                                                    contSintax++;
                                                    i = 0;
                                                    break;
                                                default:
                                                    for (int h = 1; h == 1;) {
                                                        for (int j = contSintax; j <= tokens.size(); j++) {
                                                            if (tokens.get(j).equals("fin")) {
                                                                palabraAux = palabraAux + " " + tokens.get(j);
                                                                contSintax = j + 1;
                                                                j = tokens.size() + 5;
                                                                h = 0;
                                                            } else {
                                                                palabraAux = palabraAux + " " + tokens.get(j);
                                                            }
                                                        }
                                                    }
                                                    System.out.println(palabraAux + " es ERROR SINTACTICO");
                                                    break;
                                            }
                                        } else {
                                            palabraAux = palabraAux + " " + tokens.get(contSintax);
                                            System.out.println(palabraAux + " es ERROR SINTACTICO");
                                            i = 0;
                                            contSintax++;
                                        }
                                    }
                                } else {
                                    for (int i = 1; i == 1;) {
                                        for (int j = contSintax + 1; j <= tokens.size(); j++) {
                                            if (tokens.get(j).equals("fin")) {
                                                palabraAux = palabraAux + " " + tokens.get(j);
                                                contSintax = j + 1;
                                                j = tokens.size() + 5;
                                                i = 0;
                                            } else {
                                                palabraAux = palabraAux + " " + tokens.get(j);
                                            }
                                        }
                                    }
                                    System.out.println(palabraAux + " es ERROR SINTACTICO");
                                }
                            }
                            palabraAux = "";
                            continuar = true;
                        } catch (Exception e) {
                            System.out.println(palabraAux + " es ERROR SINTACTICO");
                            break;
                        }
                        break;
                    default:
                        System.out.println("No " + tokens.get(contSintax));
                        contSintax = contSintax + 1;
                        break;
                }
            } catch (Exception e) {
                if (contSintax == tokens.size()) {

                } else {
                    System.out.println(palabraAux + " es ERROR SINTACTICO");
                }
                break;
            }
        }
        if(tokens.isEmpty()){
            
        }else{
            System.out.println("Problemas");
            for (int i=0;i<tokens.size();i++) {
                System.out.println("-"+String.valueOf(tokens.get(i)));
            }
        }
        //System.out.println("token "+tokens.get(1));
    }
}
