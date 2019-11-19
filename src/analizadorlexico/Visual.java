package analizadorlexico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tavod & javico
 */
public class Visual extends javax.swing.JFrame {
    public Visual() {
        initComponents();
        this.setLocationRelativeTo(null);
        areaResultados.append("Introduce tu codigo en el campo de arriba o usa el boton de archivo para buscar un archivo"
                + "\nde texto en tu pc");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaCodigo = new javax.swing.JTextArea();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaResultados = new javax.swing.JTextArea();
        btnArchivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaCodigo.setColumns(20);
        areaCodigo.setRows(5);
        jScrollPane1.setViewportView(areaCodigo);

        btnAnalizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        areaResultados.setEditable(false);
        areaResultados.setBackground(new java.awt.Color(240, 240, 240));
        areaResultados.setColumns(20);
        areaResultados.setRows(5);
        jScrollPane2.setViewportView(areaResultados);

        btnArchivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnArchivo.setText("Archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalizar)
                    .addComponent(btnArchivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        String text = areaCodigo.getText();
        areaResultados.setText("");
        //areaResultados.append("jaja");
        //areaResultados.append("\n"+"jaja");
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
                areaResultados.append("\n"+salida);
                salida = String.valueOf(contSalida) + ". ";
            } else {
                salida = salida + String.valueOf(entrada.charAt(i));
            }
        }
        areaResultados.append("\n"+"");

        while (!fin) {
            try {
                switch (String.valueOf(entrada.charAt(contador))) {
                    case "´":
                        palabraAux = "/n";
                        tokens.add("salto");
                        areaResultados.append("\n"+palabraAux + " es salto de linea");
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
                                areaResultados.append("\n"+palabraAux + " es incremento");
                                contador++;
                            } else {
                                tokens.add("suma");
                                areaResultados.append("\n"+palabraAux + " es suma");
                            }
                        } else {
                            tokens.add("suma");
                            areaResultados.append("\n"+palabraAux + " es suma");
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
                                areaResultados.append("\n"+palabraAux + " es decremento");
                                contador++;
                            } else {
                                tokens.add("resta");
                                areaResultados.append("\n"+palabraAux + " es resta");
                            }
                        } else {
                            tokens.add("resta");
                            areaResultados.append("\n"+palabraAux + " es resta");
                        }
                        palabraAux = "";
                        break;
                    case "*":
                        palabraAux = palabraAux + "*";
                        tokens.add("multiplicacion");
                        areaResultados.append("\n"+palabraAux + " es multiplicacion");
                        palabraAux = "";
                        contador++;
                        break;
                    case "/":
                        palabraAux = palabraAux + "/";
                        tokens.add("division");
                        areaResultados.append("\n"+palabraAux + " es division");
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
                                areaResultados.append("\n"+palabraAux + " es una palabra");
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
                                areaResultados.append("\n"+palabraAux + " es comparacion");
                                contador++;
                            } else {
                                tokens.add("error");
                                areaResultados.append("\n"+palabraAux + " es error lexico");
                            }
                        } else {
                            tokens.add("error");
                            areaResultados.append("\n"+palabraAux + " es error lexico");
                        }

                        palabraAux = "";
                        break;
                    case "(":
                        palabraAux = palabraAux + "(";
                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "if") {
                            areaResultados.append("\n"+"Inicio de condicion");
                        }*/
                        tokens.add("parentesis");
                        areaResultados.append("\n"+palabraAux + " abre parentesis");
                        palabraAux = "";
                        contador++;
                        break;
                    case ")":
                        palabraAux = palabraAux + ")";

                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "parentesis") {
                            tokens.remove(tokens.size() - 1);
                        }
                        areaResultados.append("\n"+palabraAux + " cierra parentesis");
                        if (String.valueOf(tokens.get(tokens.size() - 1)) == "if") {
                            areaResultados.append("\n"+"Cierre de condicion");
                            tokens.add("condicion");
                        }*/
                        tokens.add("cierraparentesis");
                        areaResultados.append("\n"+palabraAux + " cierra parentesis");
                        palabraAux = "";
                        contador++;
                        break;
                    case "{":
                        palabraAux = palabraAux + "{";
                        tokens.add("llave");
                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "condicion") {
                            areaResultados.append("\n"+"cuerpo del if");
                        }*/
                        areaResultados.append("\n"+palabraAux + " abre llaves");
                        contador++;
                        palabraAux = "";
                        break;
                    case "}":
                        palabraAux = palabraAux + "}";
                        /*if (String.valueOf(tokens.get(tokens.size() - 1)) == "llaves") {
                            tokens.remove(tokens.size() - 1);
                            areaResultados.append("\n"+palabraAux + " cierra llaves");
                        }
                        if (String.valueOf(tokens.get(tokens.size() - 1)) == "condicion") {
                            tokens.remove(tokens.size() - 1);
                            areaResultados.append("\n"+"fin de if");
                            tokens.remove(tokens.size() - 1);
                        }*/
                        tokens.add("cierrallave");
                        areaResultados.append("\n"+palabraAux + " cierra llaves");
                        palabraAux = "";
                        contador++;
                        break;
                    case ";":
                        /*areaResultados.append("\n"+"Error en la linea "+contLinea);
                        if (tokens.isEmpty()) {
                        } else {
                            tokens.remove(0);
                            for (int i = 0; i < tokens.size(); i++) {
                                areaResultados.append("\n"+"-" + String.valueOf(tokens.get(i)));
                            }
                        }
                        contLinea++;*/
                        palabraAux = ";";
                        tokens.add("fin");
                        areaResultados.append("\n"+palabraAux + " es fin de linea");
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
                                    areaResultados.append("\n"+palabraAux + " es menor igual que");
                                    contador++;
                                    break;
                                case ">":
                                    palabraAux = palabraAux + ">";
                                    tokens.add("diferente");
                                    areaResultados.append("\n"+palabraAux + " es diferente que");
                                    contador++;
                                    break;
                                default:
                                    tokens.add("menor");
                                    areaResultados.append("\n"+palabraAux + " es menor que");
                                    break;
                            }
                        } else {
                            tokens.add("menor");
                            areaResultados.append("\n"+palabraAux + " es menor que");
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
                                areaResultados.append("\n"+palabraAux + " es mayor igual que");
                                contador++;
                            } else {
                                tokens.add("mayor");
                                areaResultados.append("\n"+palabraAux + " es mayor que");
                            }
                        } else {
                            tokens.add("mayor");
                            areaResultados.append("\n"+palabraAux + " es mayor que");
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
                                areaResultados.append("\n"+palabraAux + " es igualacion");
                                contador++;
                            } else {
                                tokens.add("error");
                                areaResultados.append("\n"+palabraAux + " falta = (err lexico)");
                            }
                        } else {
                            tokens.add("error");
                            areaResultados.append("\n"+palabraAux + " falta = (err lexico)");
                        }

                        palabraAux = "";

                        /*contador++;
                        if(String.valueOf(entrada.charAt(contador)).equals("=")){
                            if(tokens.get(tokens.size()-1)=="identificador"){
                            tokens.add("asignacion");
                            contador++;    
                            }else{
                            areaResultados.append("\n"+"Falta identificador");
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
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                tokens.add("if");
                                /*if(String.valueOf(entrada.charAt(contador)).equals("(")){
                                    
                                }else{
                                    
                                }*/
                                break;
                            case "sino":
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                tokens.add("else");
                                /*areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                if (tokens.get(tokens.size() - 1) == "if") {
                                    tokens.add("else");
                                    contador++;
                                } else {
                                    areaResultados.append("\n"+"Falta si");
                                }*/
                                break;
                            case "pala":
                                tokens.add("String");
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "num":
                                tokens.add("int");
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "ciclo":
                                tokens.add("for");
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "otto":
                                tokens.add("break");
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            case "imprimir":
                                tokens.add("print");
                                areaResultados.append("\n"+palabraAux + " es palabra reservada");
                                //contador++;
                                break;
                            default:
                                if (simbolo.matches("[A-Z].*") || simbolo.matches("[a-z].*")
                                        || simbolo.matches("[0-9].*") || simbolo.equals("_")) {
                                    if (palabraAux.matches("[0-9]*")) {
                                        if (simbolo.equals("0") && palabraAux.length() != 1) {
                                            tokens.add("error");
                                            areaResultados.append("\n"+palabraAux + " es un numero invalido (err lexico)");
                                        } else {
                                            tokens.add("numero");
                                            areaResultados.append("\n"+palabraAux + " es un numero");
                                        }
                                        continuar = false;
                                    }
                                    if (continuar) {
                                        if (String.valueOf(palabraAux.charAt(0)).matches("[0-9].*")
                                                || String.valueOf(palabraAux.charAt(0)).equals("_")) {
                                            tokens.add("error");
                                            areaResultados.append("\n"+palabraAux + " es identif. INVALIDO (err lexico)");
                                        } else {
                                            identificadores.add(palabraAux);
                                            tokens.add("identif");
                                            areaResultados.append("\n"+palabraAux + " es identificador");
                                        }
                                    }
                                } else {
                                    tokens.add("error");
                                    areaResultados.append("\n"+simbolo + " es un simbolo desconocido");
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

        areaResultados.append("\n"+"");
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                            areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                            areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                            areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                            areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                            areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                            areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax + 1));
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
                                        areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                        areaResultados.append("\n"+" -" + tokens.get(contSintax + 1));
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
                                        areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                        areaResultados.append("\n"+" -" + tokens.get(contSintax + 1));
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
                                    areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                    areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                        areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                        areaResultados.append("\n"+" -" + tokens.get(contSintax));
                                        areaResultados.append("\n"+" -" + tokens.get(contSintax + 1));
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
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax + 1));
                                areaResultados.append("\n"+" -" + tokens.get(contSintax + 2));
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
                            //areaResultados.append("\n"+"cae en el catch");
                            //contSintax++;
                        }
                        continuar = true;
                        break;
                    case "llave":
                        try {
                            areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                            areaResultados.append("\n"+" -" + tokens.get(contSintax));
                            contSintax++;
                        } catch (Exception e) {
                        }
                        break;
                    case "cierrallave":
                        try {
                            contLlaves--;
                            contSintax++;
                            
                            areaResultados.append("\n"+"Llaves: "+contLlaves);
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
                                                    areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                                    areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
                                        areaResultados.append("\n"+"Se desborda lista");
                                        areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                        areaResultados.append("\n"+" -" + tokens.get(contSintax-1));
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
                                areaResultados.append("\n"+"Se desborda lista");
                                areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                                areaResultados.append("\n"+" -" + tokens.get(contSintax-1));
                                contSintax++;
                            }else{
                                areaResultados.append("\n"+"NO se desborda lista");
                                auxList.remove(auxList.size() - 1);
                                contSintax++;
                            }
                        }
                        break;
                    default:
                        areaResultados.append("\n"+"Err. Sint. en linea " + contLinea);
                        areaResultados.append("\n"+" -" + tokens.get(contSintax));
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
            //areaResultados.append("\n"+contLinea);
        }
        try{
            areaResultados.append("\n"+auxList);
            for(int i=0;i<=auxList.size();i++){
                areaResultados.append("\n"+"Err. Sint. en linea " + lineList.get(i));
                areaResultados.append("\n"+" -" + auxList.get(i));
            }
        }catch(Exception e){
            areaResultados.append("\n"+"Entra a otro lado");
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        Archivo a = new Archivo();
        areaCodigo.setText("");
        areaCodigo.setText(a.LeerGrafico());
    }//GEN-LAST:event_btnArchivoActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visual().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaCodigo;
    private javax.swing.JTextArea areaResultados;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
