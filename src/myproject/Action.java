package myproject;

/**
 *
 * @author Ateto
 *
 * Description: Action class
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Pair {
    private String key;
    private String value;
    
    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
}

public class Action extends Home implements ActionListener{
    
    JLabel question;
    File file;
    Scanner sc;
    JButton A, B, C, D;
    JButton backButton = new JButton("Back");
    ArrayList<Pair> list;
    ArrayList<String> output, taken, mistake;
    Random rand;
    JTextField field = new JTextField();
    
    int score;
    int idx;
    String q;
    String mis;
    char ans;
    char input;
    boolean done;
    final int T = 10;
    final int N = 4;
    
    Action() throws FileNotFoundException, InterruptedException{
        
        file = new File("./src/myproject/file");
        
        A = setButton(0);
        B = setButton(1);
        C = setButton(2);
        D = setButton(3);
        frame.add(A);
        frame.add(B);
        frame.add(C);
        frame.add(D);
        
        list = new ArrayList();
        output = new ArrayList();
        taken = new ArrayList();
        mistake = new ArrayList();
        sc = new Scanner(file);
        rand = new Random();
        
        question = new JLabel();
        question.setBackground(Color.WHITE);
        question.setFont(new Font("Dialog", Font.PLAIN, 20));
        question.setBounds(50, 80, 315, 100);
        question.setBorder(BorderFactory.createBevelBorder(1));
        question.setOpaque(true);
        question.setHorizontalAlignment(JTextField.CENTER);
        question.setVisible(false);
        
        backButton.setBounds(130, 250, 150, 50);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        backButton.setVisible(false);
        backButton.setBackground(Color.WHITE);
        
        field.setBounds(130, 200, 150, 50);
        field.setVisible(false);
        
        frame.add(question);
        frame.add(backButton);
        frame.add(field);
        while(sc.hasNext()){
            list.add(new Pair(sc.next(), sc.next()));
        }
        while(true){
            while(page==0){
                Thread.sleep(100);
            }
            if(page == 1){
                CtoE();
            }else if(page == 2){
                EtoC();
            }else if(page == 3){
                Spell();
            }
            while(page!=0){
                Thread.sleep(100);
            }
        }
    }
    
    public JButton setButton(int idx) {
        
        JButton button = new JButton();
        button.setBounds(55 + idx%2*155, 200 + idx/2*60, 150, 60);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setVisible(false);
        button.setBackground(Color.WHITE);
        
        return button;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == CButton){
            page = 1;
            removeAllButton();
        }else if(e.getSource() == EButton){
            page = 2;
            removeAllButton();
        }else if(e.getSource() == SButton){
            page = 3;
            removeAllButton();
        }
        
        if(e.getSource() == A){
            done = true;
            input = 'a';
        }else if(e.getSource() == B){
            done = true;
            input = 'b';
        }else if(e.getSource() == C){
            done = true;
            input = 'c';
        }else if(e.getSource() == D){
            done = true;
            input = 'd';
        }
        
        if(e.getSource() == backButton){
            page = 0;
            question.setVisible(false);
            backButton.setVisible(false);
            field.setVisible(false);
            returnHome();
        }
        
    }
    
    boolean enable(String str, ArrayList<String> list) {
        for(int i=0; i<list.size(); i++){
            if(str == list.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    private void CtoE() throws InterruptedException {
        
        A.setVisible(true);
        B.setVisible(true);
        C.setVisible(true);
        D.setVisible(true);
        question.setVisible(true);
        score = 0;
        
        for(int i=1; i<=T; i++){
            output.clear();
            taken.clear();
            mistake.clear();
            ans = 'a';
            idx = rand.nextInt(list.size());
            q = list.get(idx).getValue();
            mis = list.get(idx).getKey() + " " + q;
            output.add(list.get(idx).getKey());
            System.out.println(i + ". " + q);
            question.setText(Integer.toString(i) + ". " + q);
            
            while(output.size() != 4) {
                
                idx = rand.nextInt(list.size());
                if(enable(list.get(idx).getKey(), output)){
                    output.add(list.get(idx).getKey());
                }
                
            }
            
            for(int j=0; j<N; j++){
                do{
                    idx = rand.nextInt(N);
                }while(!enable(output.get(idx), taken));
                
                taken.add(output.get(idx));
                if(idx == 0){
                    ans += j;
                }
                String str = output.get(idx);
                if(j == 0){
                    A.setText(str);
                }else if(j == 1){
                    B.setText(str);
                }else if(j == 2){
                    C.setText(str);
                }else{
                    D.setText(str);
                }
            }
            done = false;
            while(!done){
                Thread.sleep(100);
            }
            done = false;
            if(input == ans){
                question.setText("CORRECT!!");
                System.out.println("CORRECT!!");
                score += 1;
            }else{
                question.setText("WRONG...");
                System.out.println("WRONG...");
                mistake.add(mis);
            }
            Thread.sleep(300);
        }

        
        question.setText("Accuracy: " + score*100/T + "%");
        A.setVisible(false);
        B.setVisible(false);
        C.setVisible(false);
        D.setVisible(false);
        
        backButton.setVisible(true);
    }
    
    private void EtoC() throws InterruptedException {
        A.setVisible(true);
        B.setVisible(true);
        C.setVisible(true);
        D.setVisible(true);
        question.setVisible(true);
        score = 0;
        
        for(int i=1; i<=T; i++){
            output.clear();
            taken.clear();
            mistake.clear();
            ans = 'a';
            idx = rand.nextInt(list.size());
            q = list.get(idx).getKey();
            mis = list.get(idx).getValue() + " " + q;
            output.add(list.get(idx).getValue());
            System.out.println(i + ". " + q);
            question.setText(Integer.toString(i) + ". " + q);
            
            while(output.size() != 4) {
                
                idx = rand.nextInt(list.size());
                if(enable(list.get(idx).getValue(), output)){
                    output.add(list.get(idx).getValue());
                }
                
            }
            
            for(int j=0; j<N; j++){
                do{
                    idx = rand.nextInt(N);
                }while(!enable(output.get(idx), taken));
                
                taken.add(output.get(idx));
                if(idx == 0){
                    ans += j;
                }
                String str = output.get(idx);
                if(j == 0){
                    A.setText(str);
                }else if(j == 1){
                    B.setText(str);
                }else if(j == 2){
                    C.setText(str);
                }else{
                    D.setText(str);
                }
            }
            done = false;
            while(!done){
                Thread.sleep(100);
            }
            done = false;
            if(input == ans){
                question.setText("CORRECT!!");
                System.out.println("CORRECT!!");
                score += 1;
            }else{
                question.setText("WRONG...");
                System.out.println("WRONG...");
                mistake.add(mis);
            }
            Thread.sleep(300);
        }
        
        question.setText("Accuracy: " + score*100/T + "%");
        A.setVisible(false);
        B.setVisible(false);
        C.setVisible(false);
        D.setVisible(false);
        
        backButton.setVisible(true);
    }
    
    private void Spell() throws InterruptedException {
        
        question.setVisible(true);
        field.setVisible(true);
        
        for(int i = 1; i <= T; i++) {
            done = false;
            while(!done) {
                try {
                    idx = rand.nextInt() % list.size();
                    q = list.get(idx).getValue();
                    done = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("again.");
                }
            }
            question.setText(i + ". " + q + " (" + list.get(idx).getKey().length() + ")");
            done = false;
            while(!done){
                Thread.sleep(100);
                if(field.getText().equals(list.get(idx).getKey())){
                    done = true;
                }
            }
            field.setText("");
            question.setText("CORRECT!!");
            Thread.sleep(300);
        }
        backButton.setVisible(true);
        field.setVisible(false);
        question.setText("Finish!!");
    }
    
}
