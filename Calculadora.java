import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class Calculadora extends JFrame implements KeyListener{

    private JButton cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve;
    private JButton suma, resta, multiplicacion, division;
    private JButton coma;
    private JButton resultado;
    private JButton cambiosigno;
    private JButton raiz;
    private JButton exponente2;
    private JButton fraccion;
    private JButton retroceso;
    private JButton C;
    private JButton LOG;
    private JButton porcentaje;

    private double primerNumero = 0;
    private char operacion = ' ';
    private JTextField textField;

    public Calculadora() {
        setSize(500, 500);
        setTitle("Calculadora");
        setBounds(100, 104, 335, 525);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        iniciarComponentes();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textField.requestFocusInWindow();
    }

    private void iniciarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        Color buttonColor = Color.decode("#f9f9f9");
        Color resultatColor = Color.decode("#005A9E");

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 42));
        textField.setBackground(buttonColor);
        textField.setBorder(new CircularBorder(15));
        textField.setEditable(false);
        textField.setFocusable(false);
        panel.add(textField);

        porcentaje = new JButton("%");
        porcentaje.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        porcentaje.setBackground(buttonColor);
        porcentaje.setBorder(new CircularBorder(22));
        porcentaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                    try {
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                        double currentNumber = Double.parseDouble(textField.getText());
                        double resultado = currentNumber * 0.01;
                        if (Math.abs(resultado) > 1e10 || (Math.abs(resultado) < 1e-10 && resultado != 0)) {
                            textField.setText(String.format("%.6e", resultado));
                        } else {
                            textField.setText(String.valueOf(resultado));
                        }
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                    } catch (NumberFormatException ex) {
                        textField.setText("Error");
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                    }
                }
            }
        });
        panel.add(porcentaje);

        LOG = new JButton("log");
        LOG.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        LOG.setBackground(buttonColor);
        LOG.setBorder(new CircularBorder(22));
        LOG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                if (!textField.getText().isEmpty()) {
                    try {
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                        double currentNumber = Double.parseDouble(textField.getText());
                        if (currentNumber > 0) {
                            double logValue = Math.log10(currentNumber);
                            if (Math.abs(logValue) > 1e10 || (Math.abs(logValue) < 1e-10 && logValue != 0)) {
                                textField.setText(String.format("%.6e", logValue));
                            } else {
                                textField.setText(String.valueOf(logValue));
                            }
                        } else {
                            textField.setText("Error");
                        }
                    } catch (NumberFormatException ex) {
                        textField.setText("Error");
                    }
                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                }
            }
        });
        panel.add(LOG);

        C = new JButton("C");
        C.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        C.setBackground(buttonColor);
        C.setBorder(new CircularBorder(22));
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
        panel.add(C);

        retroceso = new JButton("←");
        retroceso.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        retroceso.setBackground(buttonColor);
        retroceso.setBorder(new CircularBorder(22));
        retroceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                if (!currentText.isEmpty()) {
                    String newText = currentText.substring(0, currentText.length() - 1);
                    textField.setText(newText);
                }
            }
        });
        panel.add(retroceso);

        fraccion = new JButton("1/x");
        fraccion.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        fraccion.setBackground(buttonColor);
        fraccion.setBorder(new CircularBorder(22));
        fraccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                    try {
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                        double currentNumber = Double.parseDouble(textField.getText());
                        if (currentNumber != 0) {
                            double inverse = 1 / currentNumber;
                            if (Math.abs(inverse) > 1e10 || (Math.abs(inverse) < 1e-10 && inverse != 0)) {
                                textField.setText(String.format("%.6e", inverse));
                            } else {
                                textField.setText(String.valueOf(inverse));
                            }
                        } else {
                            textField.setText("Error");
                        }
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                    } catch (NumberFormatException ex) {
                        textField.setText("Error");
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                    }
                }
            }
        });
        panel.add(fraccion);

        exponente2 = new JButton("x^2");
        exponente2.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        exponente2.setBackground(buttonColor);
        exponente2.setBorder(new CircularBorder(22));
        exponente2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                try {
                    if (!textField.getText().isEmpty()) {
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                        double currentNumber = Double.parseDouble(textField.getText());
                        double exponent = Math.pow(currentNumber, 2);
                        if (Math.abs(exponent) > 1e10 || (Math.abs(exponent) < 1e-10 && exponent != 0)) {
                            textField.setText(String.format("%.6e", exponent));
                        } else {
                            textField.setText(String.valueOf(exponent));
                        }
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                    }
                }
                catch (Exception ex) {
                    textField.setText("Error");
                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                }
            }
        });
        panel.add(exponente2);

        raiz = new JButton("√x");
        raiz.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        raiz.setBackground(buttonColor);
        raiz.setBorder(new CircularBorder(22));
        raiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                try {
                    if (!textField.getText().isEmpty()) {
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                        double currentNumber = Double.parseDouble(textField.getText());
                        if (currentNumber >= 0) {
                            double squareRoot = Math.sqrt(currentNumber);
                            if (Math.abs(squareRoot) > 1e10 || (Math.abs(squareRoot) < 1e-10 && squareRoot != 0)) {
                                textField.setText(String.format("%.6e", squareRoot));
                            } else {
                                textField.setText(String.valueOf(squareRoot));
                            }
                            ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                        } else {
                            textField.setText("Error");
                            ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                        }
                    }
                }
                catch (Exception ex) {
                    textField.setText("Error");
                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                }
            }
        });
        panel.add(raiz);

        division = new JButton("/");
        division.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        division.setBackground(buttonColor);
        division.setBorder(new CircularBorder(22));
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    primerNumero = Double.parseDouble(textField.getText());
                    textField.setText("");
                    operacion = '/';
                }
            }
        });
        panel.add(division);

        siete = new JButton("7");
        siete.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        siete.setBackground(buttonColor);
        siete.setBorder(new CircularBorder(22));
        siete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "7");
                textField.requestFocusInWindow();
            }
        });
        panel.add(siete);

        ocho = new JButton("8");
        ocho.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        ocho.setBackground(buttonColor);
        ocho.setBorder(new CircularBorder(22));
        ocho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "8");
            }
        });
        panel.add(ocho);

        nueve = new JButton("9");
        nueve.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        nueve.setBackground(buttonColor);
        nueve.setBorder(new CircularBorder(22));
        nueve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "9");
            }
        });
        panel.add(nueve);

        multiplicacion = new JButton("×");
        multiplicacion.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        multiplicacion.setBackground(buttonColor);
        multiplicacion.setBorder(new CircularBorder(22));
        multiplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    primerNumero = Double.parseDouble(textField.getText());
                    textField.setText("");
                    operacion = '*';
                }
            }
        });
        panel.add(multiplicacion);

        cuatro = new JButton("4");
        cuatro.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        cuatro.setBackground(buttonColor);
        cuatro.setBorder(new CircularBorder(22));
        cuatro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "4");
            }
        });
        panel.add(cuatro);

        cinco = new JButton("5");
        cinco.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        cinco.setBackground(buttonColor);
        cinco.setBorder(new CircularBorder(22));
        cinco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "5");
            }
        });
        panel.add(cinco);

        seis = new JButton("6");
        seis.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        seis.setBackground(buttonColor);
        seis.setBorder(new CircularBorder(22));
        seis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "6");
            }
        });
        panel.add(seis);

        resta = new JButton("-");
        resta.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        resta.setBackground(buttonColor);
        resta.setBorder(new CircularBorder(22));
        resta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    primerNumero = Double.parseDouble(textField.getText());
                    textField.setText("");
                    operacion = '-';
                }
            }
        });
        panel.add(resta);

        uno = new JButton("1");
        uno.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        uno.setBackground(buttonColor);
        uno.setBorder(new CircularBorder(22));
        uno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "1");
            }
        });
        panel.add(uno);

        dos = new JButton("2");
        dos.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        dos.setBackground(buttonColor);
        dos.setBorder(new CircularBorder(22));
        dos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "2");
            }
        });
        panel.add(dos);

        tres = new JButton("3");
        tres.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        tres.setBackground(buttonColor);
        tres.setBorder(new CircularBorder(22));
        tres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "3");
            }
        });
        panel.add(tres);

        suma = new JButton("+");
        suma.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        suma.setBackground(buttonColor);
        suma.setBorder(new CircularBorder(22));
        suma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    primerNumero = Double.parseDouble(textField.getText());
                    textField.setText("");
                    operacion = '+';
                }
            }
        });
        panel.add(suma);

        cambiosigno = new JButton("+/-");
        cambiosigno.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        cambiosigno.setBackground(buttonColor);
        cambiosigno.setBorder(new CircularBorder(22));
        cambiosigno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                    double currentNumber = Double.parseDouble(textField.getText());
                    currentNumber = -currentNumber;
                    textField.setText(String.valueOf(currentNumber));
                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                }
            }
        });
        panel.add(cambiosigno);

        cero = new JButton("0");
        cero.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        cero.setBackground(buttonColor);
        cero.setBorder(new CircularBorder(22));
        cero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "0");
            }
        });
        panel.add(cero);

        coma = new JButton(".");
        coma.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        coma.setBackground(buttonColor);
        coma.setBorder(new CircularBorder(22));
        coma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                if (!currentText.contains(".") && !currentText.isEmpty()) {
                    textField.setText(currentText + ".");
                }
                else if (currentText.isEmpty()) {
                    textField.setText("0.");
                }
            }
        });
        panel.add(coma);

        resultado = new JButton("=");
        resultado.setFont(new Font("Avenir Next LT Pro Light", Font.PLAIN, 14));
        resultado.setBorder(new CircularBorder(22));
        resultado.setForeground(Color.WHITE);
        resultado.setOpaque(true);
        resultado.setBackground(resultatColor);
        resultado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentFilter filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                try {
                    if (!textField.getText().isEmpty()) {
                        filter = ((AbstractDocument) textField.getDocument()).getDocumentFilter();
                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(null);
                        double segundoNumero = Double.parseDouble(textField.getText());
                        double resultadoOperacion = 0;
                        switch (operacion) {
                            case '+':
                                resultadoOperacion = primerNumero + segundoNumero;
                                break;
                            case '-':
                                resultadoOperacion = primerNumero - segundoNumero;
                                break;
                            case '*':
                                resultadoOperacion = primerNumero * segundoNumero;
                                break;
                            case '/':
                                if (segundoNumero != 0) {
                                    resultadoOperacion = primerNumero / segundoNumero;
                                } else {
                                    textField.setText("Error");
                                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                                    return;
                                }
                                break;
                        }
                        if (Math.abs(resultadoOperacion) > 1e10 || (Math.abs(resultadoOperacion) < 1e-10 && resultadoOperacion != 0)) {
                            textField.setText(String.format("%.6e", resultadoOperacion));
                        } else {
                            textField.setText(String.valueOf(resultadoOperacion));
                        }

                        ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                    }
                } catch (Exception ex) {
                    textField.setText("Error");
                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(filter);
                }
            }
        });
        panel.add(resultado);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = panel.getWidth();
                int panelHeight = panel.getHeight();
                
                if (panelWidth > 500 && panelHeight > 500) {

                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter(16));

                    textField.setBounds(10, 65, 1255, 90);

                    porcentaje.setBounds(10, 175, 310, 70);
                    LOG.setBounds(325, 175, 310, 70);
                    C.setBounds(640, 175, 310, 70);
                    retroceso.setBounds(955, 175, 310, 70);

                    fraccion.setBounds(10, 250, 310, 70);
                    exponente2.setBounds(325, 250, 310, 70);
                    raiz.setBounds(640, 250, 310, 70);
                    division.setBounds(955, 250, 310, 70);

                    siete.setBounds(10, 325, 310, 70);
                    ocho.setBounds(325, 325, 310, 70);
                    nueve.setBounds(640, 325, 310, 70);
                    multiplicacion.setBounds(955, 325, 310, 70);

                    cuatro.setBounds(10, 400, 310, 70);
                    cinco.setBounds(325, 400, 310, 70);
                    seis.setBounds(640, 400, 310, 70);
                    resta.setBounds(955, 400, 310, 70);

                    uno.setBounds(10, 475, 310, 70);
                    dos.setBounds(325, 475, 310, 70);
                    tres.setBounds(640, 475, 310, 70);
                    suma.setBounds(955, 475, 310, 70);

                    cambiosigno.setBounds(10, 550, 310, 70);
                    cero.setBounds(325, 550, 310, 70);
                    coma.setBounds(640, 550, 310, 70);
                    resultado.setBounds(955, 550, 310, 70);
                }
                else {

                    ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter(8));

                    textField.setBounds(2, 65, 318, 75);

                    porcentaje.setBounds(7, 175, 75, 45);
                    LOG.setBounds(84, 175, 75, 45);
                    C.setBounds(161, 175, 75, 45);
                    retroceso.setBounds(238, 175, 75, 45);

                    fraccion.setBounds(7, 222, 75, 45);
                    exponente2.setBounds(84, 222, 75, 45);
                    raiz.setBounds(161, 222, 75, 45);
                    division.setBounds(238, 222, 75, 45);

                    siete.setBounds(7, 269, 75, 45);
                    ocho.setBounds(84, 269, 75, 45);
                    nueve.setBounds(161, 269, 75, 45);
                    multiplicacion.setBounds(238, 269, 75, 45);

                    cuatro.setBounds(7, 316, 75, 45);
                    cinco.setBounds(84, 316, 75, 45);
                    seis.setBounds(161, 316, 75, 45);
                    resta.setBounds(238, 316, 75, 45);

                    uno.setBounds(7, 363, 75, 45);
                    dos.setBounds(84, 363, 75, 45);
                    tres.setBounds(161, 363, 75, 45);
                    suma.setBounds(238, 363, 75, 45);

                    cambiosigno.setBounds(7, 410, 75, 45);
                    cero.setBounds(84, 410, 75, 45);
                    coma.setBounds(161, 410, 75, 45);
                    resultado.setBounds(238, 410, 75, 45);
                }
            }
        });
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_0:
                cero.doClick();
                break;
            case KeyEvent.VK_1:
                uno.doClick();
                break;
            case KeyEvent.VK_2:
                dos.doClick();
                break;
            case KeyEvent.VK_3:
                tres.doClick();
                break;
            case KeyEvent.VK_4:
                cuatro.doClick();
                break;
            case KeyEvent.VK_5:
                if (e.isShiftDown()) {
                    porcentaje.doClick();
                }
                else {
                    cinco.doClick();
                }
                break;
            case KeyEvent.VK_6:
                seis.doClick();
                break;
            case KeyEvent.VK_7:
                if (e.isShiftDown()) {
                    division.doClick();
                }
                else {
                    siete.doClick();
                }
                break;
            case KeyEvent.VK_8:
                ocho.doClick();
                break;
            case KeyEvent.VK_9:
                nueve.doClick();
                break;
            case KeyEvent.VK_PLUS:
                if (e.isShiftDown()) {
                    multiplicacion.doClick();
                }
                else {
                    suma.doClick();
                }
                break;
            case KeyEvent.VK_PERIOD:
                coma.doClick();
                break;
            case KeyEvent.VK_ENTER:
                resultado.doClick();
                break;
            case KeyEvent.VK_MINUS:
                resta.doClick();
                break;
            case KeyEvent.VK_MULTIPLY:
                multiplicacion.doClick();
                break;
            case KeyEvent.VK_DIVIDE:
                division.doClick();
                break;
            case KeyEvent.VK_BACK_SPACE:
                retroceso.doClick();
                break;
            case KeyEvent.VK_ESCAPE:
                C.doClick();
                break;
        }
        textField.requestFocus();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}