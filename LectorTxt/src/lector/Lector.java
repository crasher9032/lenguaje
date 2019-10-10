/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lector;

import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class Lector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String texto =a.LeerGrafico();
                
        String txt = texto.replaceAll("\n"," ");
        //System.out.println(txt);
        
        int digitosLong;
        int expPosicion;              
        
        boolean letra;
        boolean mensaje;
        boolean exponente;
        
        String digitos;
        
        int longitud = txt.length()-1;
        
        //System.out.println(txt.charAt(longitud-1));        
        
        String []numeros = {"0","1","2","3","4","5","6","7","8","9"};
        String []letras = {"a","b","c","d","e","f","g","h","i","j","k","l","m",
                          "n","ñ","o","p","q","r","s","t","u","v","w","x","y","z"};
        
        for(int i=0;i<longitud;i++){
            letra = true;
            mensaje = true;
            digitosLong = 0;
            digitos = "";
            expPosicion = 0;
            exponente = false;
            String adelante = "";
            String detras = "";
            String palabra = "";            
            
            if(letra){
                for(int k=0;k<27;k++){
                    if(String.valueOf(txt.charAt(i)).equals(letras[k])){
                        k=30;
                        letra = true;
                    }else{
                        letra = false;
                    }                    
                }
            }
            
            
            if(!letra){
                for(int k=0;k<10;k++){
                    if(String.valueOf(txt.charAt(i)).equals(numeros[k])){
                        k=15;
                        letra = false;
                        mensaje = true;
                    }else{
                        mensaje = false;
                    }                    
                }
            }
            
            if(String.valueOf(txt.charAt(longitud-1)).equals(" ")){
                mensaje = false;
            }           
            
            if(letra && mensaje){                                            
                for(int l=i;l<longitud;l++){
                    for(int m=0;m<27;m++){
                        if(String.valueOf(txt.charAt(l)).equals(letras[m])){
                            m=30;
                            letra = true;
                        }else{
                            letra = false;
                        }                 
                    }
                    if(!letra){
                        if(String.valueOf(txt.charAt(l)).equals(" ")){
                            mensaje = true;
                            letra = true;
                        }else{
                            mensaje = false;                  
                        }
                        l=longitud+5;
                    }
                }
            }
            
            if(!letra && mensaje){                
                for(int o=i;o<longitud;o++){
                    if(String.valueOf(txt.charAt(o)).equals(" ")){
                        o = longitud+5;
                    }else{
                        digitosLong = digitosLong+1;                        
                        digitos = digitos + String.valueOf(txt.charAt(o));
                    }
                }
                
                for(int p=0;p<digitosLong;p++){
                    if(String.valueOf(digitos.charAt(p)).equals("E")){
                        expPosicion = p;
                        p = digitosLong+5;
                        exponente = true;
                    }else{
                        exponente = false;
                    }
                }
                
                if(!exponente){
                    try{
                        if(String.valueOf(digitos.charAt(0)).equals(".") ||
                        String.valueOf(digitos.charAt(digitosLong-1)).equals(".")){
                            mensaje = false;
                        }else{
                            float num = Float.parseFloat(digitos);  
                            mensaje = true;
                        }
                    }
                    catch(Exception num){
                        if(!letra){
                            mensaje = false;
                        }                       
                    }
                }else{
                    if(String.valueOf(digitos.charAt(0)).equals("E") || 
                    String.valueOf(digitos.charAt(digitosLong-1)).equals("E")){
                        mensaje = false;
                    }else{
                        for(int q=0;q<expPosicion;q++){
                            adelante = adelante + String.valueOf(digitos.charAt(q));
                        }
                        for(int r=expPosicion+1;r<digitosLong;r++){
                            detras = detras + String.valueOf(digitos.charAt(r));
                        }
                        
                        int adelanteLong = adelante.length()-1;
                        
                        try{                            
                            if(String.valueOf(adelante.charAt(0)).equals(".") ||
                            String.valueOf(adelante.charAt(adelanteLong)).equals(".")){
                                mensaje = false;
                            }else{
                                float num1 = Float.parseFloat(adelante);  
                                mensaje = true;
                            }                           
                        }
                        catch(Exception num1){                            
                            if(!letra){
                                mensaje = true;
                            } 
                        }
                        
                        if(mensaje){
                            try{
                                int num2 = Integer.parseInt(detras);
                                mensaje = true;
                            }
                            catch(Exception num2){
                                if(!letra){
                                    mensaje = false;
                                } 
                            }
                        }
                    }
                }
            }            
            
            for(int d=i;d<longitud;d++){
                if(String.valueOf(txt.charAt(d)).equals(" ")){
                    d = longitud+5;
                }else{
                    palabra = palabra + String.valueOf(txt.charAt(d));
                    i=i+1;
                }
            }

            if(mensaje){
                JOptionPane.showMessageDialog(null, "Mensaje: "+txt+"\n"+"<<"
                        +palabra+">>"+" es CORRECTO");
            }else{
                JOptionPane.showMessageDialog(null, "Mensaje: "+txt+"\n"+"<<"
                        +palabra+">>"+" es una OPERACION INVALIDA");
            }                 
        }                 
    }    
}