
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Program{
    public static void main(String[] args){
        //utworzenie obiektu lasy Calculator odpowiedzialnej za uruchomienie kalkulatora
        Calculator kalkulator = new Calculator();
    }
}
//klasa Calculator (nazwa wlasna kalkulatora), wraz z klasami wewnetrznymi, jest odpowiedzialna za utworzenie kalkulatora
class Calculator{
    private JFrame window;
    //obiekt klasy GridLayout uporzadkuje przyciski kalkulatora
    private GridLayout buttonLayout = new GridLayout(5,4); 

    private ButtonPanel buttons = new ButtonPanel();
    private String outputString = "";
    private Label output = new Label();
    private double operand1, operand2;
    private char operator;
    //utworzenie nowego obiektu klasy Font - czcionki dla okna z wprowadzanymi wartosciami
    private Font outputFont = new Font("Font", 1, 90); 
    //konstruktor klasy Calculator
    public Calculator(){
        window = new JFrame("Calc");
        window.setSize(400,400);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        output.setFont(outputFont);
        window.add(output, BorderLayout.PAGE_START);
        buttons.setLayout(buttonLayout);
        window.add(buttons);
        window.setVisible(true);
    }
    //klasa ButtonPanel jest odpowiedzialna za utworzenie panelu z przyciskami kalkulatora
    class ButtonPanel extends JPanel{
        //utworzenie przyciskow - obiektow klasy JButton
        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");
        JButton six = new JButton("6");
        JButton seven = new JButton("7");
        JButton eight = new JButton("8");
        JButton nine = new JButton("9");
        JButton zero = new JButton("0");
        JButton dot = new JButton(".");
        JButton add = new JButton("+");
        JButton subtract = new JButton("-");
        JButton multiply = new JButton("*");
        JButton divide = new JButton("/");
        JButton equals = new JButton("=");
        JButton clear = new JButton("C");
        //w konstruktorze klasy ButtonPanel przypisywane sa zachowania dla poszczegolnych przyciskow
        //i dodanie ich do panelu
        public ButtonPanel(){
            one.addActionListener(new Numbers(one.getText()));
            two.addActionListener(new Numbers(two.getText()));
            three.addActionListener(new Numbers(three.getText()));
            four.addActionListener(new Numbers(four.getText()));
            five.addActionListener(new Numbers(five.getText()));
            six.addActionListener(new Numbers(six.getText()));
            seven.addActionListener(new Numbers(seven.getText()));
            eight.addActionListener(new Numbers(eight.getText()));
            nine.addActionListener(new Numbers(nine.getText()));
            zero.addActionListener(new Numbers(zero.getText()));
            add.addActionListener(new Operators(add.getText()));
            subtract.addActionListener(new Operators(subtract.getText()));
            multiply.addActionListener(new Operators(multiply.getText()));
            divide.addActionListener(new Operators(divide.getText()));
            dot.addActionListener(new Numbers(dot.getText()));
            equals.addActionListener(new Equals(equals.getText()));
            clear.addActionListener(new Clear());

            add(seven);
            add(eight);
            add(nine);
            add(add);
            add(four);
            add(five);
            add(six);
            add(subtract);
            add(one);
            add(two);
            add(three);
            add(multiply);
            add(dot);
            add(zero);
            add(equals);
            add(divide);
            add(clear);
        }
        //klasa Numbers definiuje zdarzenia dla przyciskow z cyframi
        class Numbers implements ActionListener{
            String symbol;
            public Numbers(String symbol){
                this.symbol = symbol;
            }

            public void actionPerformed(ActionEvent e){
                outputString+=symbol;
                output.setText(outputString);
            }
        }
        //klasa Operators definiuje zdarzenia dla przyciskow z operatorami ("+", "-", "*", "/")
        class Operators implements ActionListener{
            String symbol;
            public Operators(String symbol){
                this.symbol = symbol;
            }

            public void actionPerformed(ActionEvent e){
                operand1 = Double.parseDouble(outputString);
                outputString = "";
                operator = symbol.charAt(0);
                output.setText(symbol);
                outputString = "";
            }
        }
        //klasa Equals definiuje zdarzenia dla przycisku "="
        class Equals implements ActionListener{
            String symbol;
            double result;
            public Equals(String symbol){
                this.symbol = symbol;
            }

            public void actionPerformed(ActionEvent e){
                operand2 = Double.parseDouble(outputString);
                outputString = "";
                switch(operator){
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        result = operand1 / operand2;
                        break;
                }
                output.setText(Double.toString(result));
                outputString = "";
            }
        }
        //klasa Clear definiuje zdarzenie dla przycisku "C" odpowiedzialnego za wymazywanie danych w kalkulatorze
        class Clear implements ActionListener{
            public void actionPerformed(ActionEvent e){
                outputString = "";
                operand1 = 0;
                operand2 = 0;
                operator = '\0';
                output.setText(outputString);
            }
        }
    }
}
