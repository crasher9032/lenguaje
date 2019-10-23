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
        boolean continuar=true;
        boolean fin = false;
        String entrada = JOptionPane.showInputDialog("Escribe algo");
        String palabraAux = "";
        String simbolo = "";
        List<String> identificadores=new ArrayList<String>();
        List<String> tokens=new ArrayList<String>();
        tokens.add("codigo");
        int longitud = entrada.length();
        int contador = 0;
        
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
                        contador++;
                        for (int i = 1; i == 1;) {
                            if (String.valueOf(entrada.charAt(contador)).equals(")")) {
                                tokens.add("parentesis");
                                contador++;
                                System.out.println(palabraAux + " esta entre parentesis");
                                i = 0;
                            } else {
                                palabraAux = palabraAux + String.valueOf(entrada.charAt(contador));
                                contador++;
                            }
                        }
                        palabraAux = "";
                        break;
                    case ")":
                        palabraAux = palabraAux + ")";
                        tokens.add("error");
                        System.out.println(palabraAux + " falta cerrar parentesis (err lexico)");
                        palabraAux = "";
                        contador++;            
                        break;
                    case "{":
                        contador++;
                        for (int i = 1; i == 1;) {
                            if(String.valueOf(entrada.charAt(contador)).equals("}")) {
                                tokens.add("llave");
                                contador++;
                                System.out.println(palabraAux + " esta entre llaves");
                                i = 0;
                            } else {
                                palabraAux = palabraAux + String.valueOf(entrada.charAt(contador));
                                contador++;
                            }
                        }
                        palabraAux = "";
                        break;
                    case "}":
                        palabraAux = palabraAux + ")";
                        tokens.add("error");
                        System.out.println(palabraAux + " falta cerrar llaves (err lexico)");
                        palabraAux = "";
                        contador++;            
                        break;
                    case ";":
                        palabraAux = palabraAux + ";";
                        tokens.add("fin");
                        System.out.println(palabraAux + " es fin de linea");
                        palabraAux = "";
                        contador++;
                        break;
                    case "<":
                        palabraAux = palabraAux + "<";
                        contador++;                        

                        if (contador < longitud) {
                            switch(String.valueOf(entrada.charAt(contador))){
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
                        }else{
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
                        for(int i=contador;i<longitud;i++){
                            if(String.valueOf(entrada.charAt(contador)).matches("[A-Z].*") ||
                               String.valueOf(entrada.charAt(contador)).matches("[a-z].*") ||
                               String.valueOf(entrada.charAt(contador)).matches("[0-9].*") ||
                               String.valueOf(entrada.charAt(contador)).equals("_")){
                                palabraAux = palabraAux + String.valueOf(entrada.charAt(contador));
                                contador++;
                            }else{
                                i = longitud + 1;
                            }
                        }
                        switch(palabraAux){
                            case "si":
                                contador++;
                                System.out.println(palabraAux + " es palabra reservada");
                                tokens.add("if");
                                /*if(String.valueOf(entrada.charAt(contador)).equals("(")){
                                    
                                }else{
                                    
                                }*/
                                break;
                            case "sino":
                                System.out.println(palabraAux + " es palabra reservada");
                                if(tokens.get(tokens.size()-1)=="if"){
                                    tokens.add("else");
                                    contador++;
                                }else{
                                    System.out.println("Falta si");
                                }
                                break;
                            case "pala":
                                tokens.add("String");
                                System.out.println(palabraAux + " es palabra reservada");
                                contador++;
                                break;
                            case "num":
                                tokens.add("int");
                                System.out.println(palabraAux + " es palabra reservada");
                                contador++;
                                break;
                            case "ciclo":
                                tokens.add("for");
                                System.out.println(palabraAux + " es palabra reservada");
                                contador++;
                                break;
                            case "otto":
                                tokens.add("break");
                                System.out.println(palabraAux + " es palabra reservada");
                                contador++;
                                break;
                            case "imprimir":
                                tokens.add("print");
                                System.out.println(palabraAux + " es palabra reservada"); 
                                contador++;
                                break;
                            default:
                                if(simbolo.matches("[A-Z].*") || simbolo.matches("[a-z].*") ||
                                   simbolo.matches("[0-9].*") || simbolo.equals("_")){                                    
                                    if(palabraAux.matches("[0-9]*")){
                                        if(simbolo.equals("0")){
                                            tokens.add("error");
                                            System.out.println(palabraAux + " es un numero invalido (err lexico)");                                             
                                        }else{
                                            tokens.add("numero");
                                            System.out.println(palabraAux + " es un numero");                                          
                                        }
                                        continuar = false;
                                    }
                                    if(continuar){
                                        if(String.valueOf(palabraAux.charAt(0)).matches("[0-9].*") ||
                                           String.valueOf(palabraAux.charAt(0)).equals("_")){
                                            tokens.add("error");
                                            System.out.println(palabraAux + " es ident. INVALIDO (err lexico)");
                                        }else{
                                            tokens.add("identif");
                                            System.out.println(palabraAux + " es identificador");
                                        }     
                                    }  
                                }else{
                                    tokens.add("error");
                                    System.out.println(simbolo + " es un simbolo desconocido");
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
                fin = false;
                break;
            }
        }
    }
}