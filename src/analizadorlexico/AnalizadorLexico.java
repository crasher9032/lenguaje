package analizadorlexico;
import javax.swing.JOptionPane;
import java.util.*;
/**
 *
 * @author JaviCo & Tavo
 */
public class AnalizadorLexico {
    public static void main(String[] args) {
        Archivo a = new Archivo();
        String entrada=a.LeerGrafico();
        System.out.println(entrada);
        boolean numero=true;
        boolean fin = false;
        //String entrada = JOptionPane.showInputDialog("Escribe algo");
        String palabraAux = "";
        List<String> identificadores=new ArrayList<String>();
        List<String> tokens=new ArrayList<String>();
        tokens.add("codigo");
        int longitud = entrada.length();
        String[] simbolos = {"+","-","*","/","'","(",")","{","}",";"};
        String[] validos = {"a","b","c","d","e","f","g","h","i","j","k","l","m",
        "n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E",
        "F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W",
        "X","Y","Z","1","2","3","4","5","6","7","8","9","0","_"};
        int contador = 0;

        while (!fin) {
            try {
                switch (String.valueOf(entrada.charAt(contador))) {
                    case "+":
                        palabraAux = palabraAux + "+";
                        contador++;                        

                        if (contador < longitud) {
                            if (String.valueOf(entrada.charAt(contador)).equals("+")) {
                                palabraAux = palabraAux + "+";
                                tokens.add("incremento");
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es incremento");
                                contador++;
                            } else {
                                tokens.add("suma");
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es suma");
                            }
                        } else {
                            tokens.add("suma");
                            JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es suma");
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
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es decremento");
                                contador++;
                            } else {
                                tokens.add("resta");
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es resta");
                            }
                        } else {
                            tokens.add("resta");
                            JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es resta");
                        }
                        palabraAux = "";
                        break;
                    case "*":
                        palabraAux = palabraAux + "*";
                        tokens.add("multiplicacion");
                        JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es multiplicacion");
                        palabraAux = "";
                        contador++;
                        break;
                    case "/":
                        palabraAux = palabraAux + "/";
                        tokens.add("division");
                        JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es division");
                        palabraAux = "";
                        contador++;
                        break;
                    case "'":
                        contador++;
                        for (int i = 1; i == 1;) {
                            if (String.valueOf(entrada.charAt(contador)).equals("'")) {
                                contador++;
                                tokens.add("palabra");
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es una palabra");
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
                        if(String.valueOf(entrada.charAt(contador)).equals("=")){
                            palabraAux = palabraAux + "=";
                            tokens.add("comparacion");                            
                            JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es incremento");
                            //declarar vector y almacenar
                        }else{
                            JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es invalido");
                            //asignacion
                            contador--;
                        }
                    break;
                    case "0":
                        if(palabraAux.length()>1){
                            JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + "es invalida");
                        }else{
                            tokens.add("cero");
                            JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es valor 0");
                            palabraAux = "";
                            contador++;
                        }
                        break;
                    case "(":
                        contador++;
                        for (int i = 1; i == 1;) {
                            if (String.valueOf(entrada.charAt(contador)).equals(")")) {
                                contador++;
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " esta entre parentesis");
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
                        JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " falta cerrar parentesis");
                        palabraAux = "";
                        contador++;            
                        break;
                    case "{":
                        contador++;
                        for (int i = 1; i == 1;) {
                            if(String.valueOf(entrada.charAt(contador)).equals("}")) {
                                contador++;
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " esta entre llaves");
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
                        JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " falta cerrar llaves");
                        palabraAux = "";
                        contador++;            
                        break;
                    case ";":
                        palabraAux = palabraAux + ";";
                        JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es fin de linea");
                        palabraAux = "";
                        contador++;
                        break;
                    case "<":
                        contador++;
                        if(String.valueOf(entrada.charAt(contador)).equals(">")){
                            tokens.add("diferente");
                        }else{
                            
                        }
                        break;
                    case ":":
                        contador++;
                        if(String.valueOf(entrada.charAt(contador)).equals("=")){
                            if(tokens.get(tokens.size()-1)=="identificador"){
                            tokens.add("asignacion");
                            contador++;    
                            }else{
                            JOptionPane.showMessageDialog(null, "Falta identificador");
                            }
                        }else{
                            
                        }
                    break;
                    default:
                        for(int i=contador;i<longitud;){
                            if(String.valueOf(entrada.charAt(contador)).equals("+") ||
                            String.valueOf(entrada.charAt(contador)).equals("-") ||
                            String.valueOf(entrada.charAt(contador)).equals("*") ||
                            String.valueOf(entrada.charAt(contador)).equals("/") ||
                            String.valueOf(entrada.charAt(contador)).equals("'") ||
                            String.valueOf(entrada.charAt(contador)).equals("(") ||
                            String.valueOf(entrada.charAt(contador)).equals(")") ||
                            String.valueOf(entrada.charAt(contador)).equals("{") ||
                            String.valueOf(entrada.charAt(contador)).equals("}") ||
                            String.valueOf(entrada.charAt(contador)).equals(";")){
                                contador = i;
                                i = longitud + 2;
                            }else{
                                palabraAux = palabraAux + String.valueOf(entrada.charAt(contador));
                                i++;
                                contador++;
                            }
                        }
                        switch(palabraAux){
                            case "si":
                                contador++;
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");
                                tokens.add("if");
                                if(String.valueOf(entrada.charAt(contador)).equals("(")){
                                    
                                }else{
                                    
                                }
                                break;
                            case "sino":
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");
                                if(tokens.get(tokens.size()-1)=="if"){
                                    tokens.add("else");
                                    contador++;
                                }else{
                                    System.out.println("Falta si");
                                }
                                break;
                            case "pala":
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");
                                break;
                            case "num":
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");
                                break;
                            case "ciclo":
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");
                                break;
                            case "otto":
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");                                
                                break;
                            case "imprimir":
                                JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es palabra reservada");                                
                                break;
                            default:
                                for(int i=0;i<palabraAux.length()&&numero==true;i++){
                                    if(String.valueOf(palabraAux.charAt(i)).equals("1")||
                                        String.valueOf(palabraAux.charAt(i)).equals("2")||
                                        String.valueOf(palabraAux.charAt(i)).equals("3")||
                                        String.valueOf(palabraAux.charAt(i)).equals("4")||
                                        String.valueOf(palabraAux.charAt(i)).equals("5")||
                                        String.valueOf(palabraAux.charAt(i)).equals("6")||
                                        String.valueOf(palabraAux.charAt(i)).equals("7")||
                                        String.valueOf(palabraAux.charAt(i)).equals("8")||
                                        String.valueOf(palabraAux.charAt(i)).equals("9")||
                                        String.valueOf(palabraAux.charAt(i)).equals("0")){
                                        numero=true;
                                    }else{
                                        numero=false;
                                    }
                                }
                                if(numero==false){
                                    if(String.valueOf(palabraAux.charAt(0)).equals("1")||
                                        String.valueOf(palabraAux.charAt(0)).equals("2")||
                                        String.valueOf(palabraAux.charAt(0)).equals("3")||
                                        String.valueOf(palabraAux.charAt(0)).equals("4")||
                                        String.valueOf(palabraAux.charAt(0)).equals("5")||
                                        String.valueOf(palabraAux.charAt(0)).equals("6")||
                                        String.valueOf(palabraAux.charAt(0)).equals("7")||
                                        String.valueOf(palabraAux.charAt(0)).equals("8")||
                                        String.valueOf(palabraAux.charAt(0)).equals("9")||
                                        String.valueOf(palabraAux.charAt(0)).equals("0")||
                                        String.valueOf(palabraAux.charAt(0)).equals("_"))

                                    {
                                        JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " no es un identificador valido");
                                    }
                                    else{
                                    identificadores.add(palabraAux);
                                    JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es un identificador");
                                    }
                                    break;
                                }
                                if(numero==true){
                                    JOptionPane.showMessageDialog(null, entrada + "\n" + palabraAux + " es un numero");
                                }
                        }
                        palabraAux = "";
                        break;
                }
            } catch (Exception e) {
                fin = false;
                break;
            }
        }
    }
}