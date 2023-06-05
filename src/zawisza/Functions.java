package zawisza;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Functions extends Fields{

    //add something to something
    protected void addPanelsToMain(JPanel Main, JPanel A,JPanel B,JPanel C,JPanel D,JPanel E,JPanel F,JPanel G, JPanel H){
        for (JPanel jPanel : Arrays.asList(A, B, C, D, E, F, G, H)) {
            Main.add(jPanel);
            Main.setBorder(new EmptyBorder(0,0,0,0));
        }
    }
    protected void addPanels(JPanel Main, JPanel A,JPanel B,JPanel C,JPanel D,JPanel E,JPanel F,JPanel G, JPanel H){
        for (JPanel jPanel : Arrays.asList(A, B, C, D, E, F, G, H)) {
            Main.add(jPanel);
            Main.setBorder(new EmptyBorder(0,0,0,0));
            jPanel.setLayout(new GridLayout(1,1,0,0));
        }
    }
    protected void addButtons(JPanel[] Panel, JButton[] Pawns, JButton[] Pieces, char Char){
        int i = 0;
        if(Char == 'b'){
            for(int j = 0; i<Pieces.length;i++, j++){
                if(Pieces[j] != null) {
                    Panel[i].add(Pieces[j]);
                    Pieces[j].setName('A' + "" + (j + 1));
                    Panel[i].setName('A' + "" + (j + 1));
                }
            }
            for(int j = 0; i<(Pieces.length + Pawns.length);i++, j++){
                Panel[i].add(Pawns[j]);
                Pawns[j].setName('B'+""+(j+1));
                Panel[i].setName('B'+""+(j+1));
            }
        }else{
            for(int j = 0; i<Pawns.length;i++, j++){
                Panel[i].add(Pawns[j]);
                Pawns[j].setName('G'+""+(j+1));
                Panel[i].setName('G'+""+(j+1));
            }
            for(int j = 0; i<(Pieces.length + Pawns.length);i++, j++){
                if(Pieces[j] != null) {
                    Panel[i].add(Pieces[j]);
                    Pieces[j].setName('H' + "" + (j + 1));
                    Panel[i].setName('H' + "" + (j + 1));
                }
            }
        }

    }

    protected void giveSize(JPanel A,JPanel B,JPanel C,JPanel D,JPanel E,JPanel F,JPanel G, JPanel H){
        for (JPanel jPanel : Arrays.asList(A, B, C, D, E, F, G, H)) {
            jPanel.setPreferredSize(new Dimension(600,75));
            jPanel.setMinimumSize(new Dimension(400,50));
            jPanel.setMaximumSize(new Dimension(600,75));
            jPanel.setBackground(Color.BLACK);

        }
    }

    protected void drawBoard(Component[][] components){
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if((i+j)%2!=0){
                    components[i][j].setBackground(Color.GRAY);
                }else{
                    components[i][j].setBackground(Color.WHITE);
                }
                components[i][j].setPreferredSize(new Dimension(75,75));
                components[i][j].setMinimumSize(new Dimension(50,50));
                components[i][j].setMaximumSize(new Dimension(75,75));
            }
        }
    }

    protected void removeButton(JButton Button){
        if(Button.getParent()!=null){
            Container buttonParent;
            buttonParent = Button.getParent();
            buttonParent.remove(Button);
            buttonParent.repaint();
            buttonParent.revalidate();
        }
    }
    protected void optionsButton(JButton[] Button, Color color){
        for (JButton jButton : Button) {
            if (jButton != null) {
                jButton.setPreferredSize(new Dimension(75, 75));
                jButton.setBackground(color);
            }
            //Button[i].setBackground((Button[i].getParent()).getBackground());
        }

    }


    protected void setBackEnabled(JButton[] Button){
        for (JButton jButton : Button) {
            if (!(jButton.isEnabled())) {
                jButton.setEnabled(true);
            }
        }
    }
    protected void setNotEnabled(JButton[] Button){
        for (JButton jButton : Button) {
            if ((jButton.isEnabled())) {
                jButton.setEnabled(false);
            }
        }
    }
    protected JButton getEnabledButton(JButton[] Button){
        for (JButton jButton : Button) {
            if (!(jButton.isEnabled())) {
                return jButton;
            }
        }
        return null;
    }
    protected boolean checkSameColor(JButton Button, JButton Button2){
        String name1;
        String name2;
        name1 = Button.getName();
        name2 = Button2.getName();
        return name1.toCharArray()[0] == name2.toCharArray()[0] || (name1.toCharArray()[0] + 1) == name2.toCharArray()[0] || name1.toCharArray()[0] == (name2.toCharArray()[0] + 1);

    }

    //After click secondary move
    protected void atackPiece(JButton Button){
        Container Pole;
        JButton addedButton;
        if(MoveW){
            addedButton = getEnabledButton(PiecesW);
        }else{
            addedButton = getEnabledButton(PiecesB);
        }


        for (JButton jButton : arrayCheckFields) {
            if (jButton.getParent() != null) {
                removeButton(jButton);
            }
        }

        Pole = Button.getParent();
        removeCheck(addedButton);
        removeButton(addedButton);

        for (JButton jButton : arrayAtackFields) {
            if (jButton.getParent() != null) {
                removeButton(jButton);
            }
        }

        if(MoveW){
            setBackEnabled(PiecesB);
            ((Pole).getComponent(0)).setEnabled(false);
            removeCheck(getEnabledButton(PiecesB), false);
            removeButton(getEnabledButton(PiecesB));
            getEnabledButton(PiecesB).setVisible(false);
            getEnabledButton(PiecesW).setEnabled(true);
            setNotEnabled(PiecesW);
        }else{
            setBackEnabled(PiecesW);
            ((Pole).getComponent(0)).setEnabled(false);
            removeCheck(getEnabledButton(PiecesW),true);
            removeButton(getEnabledButton(PiecesW));
            getEnabledButton(PiecesW).setVisible(false);
            getEnabledButton(PiecesB).setEnabled(true);
            setNotEnabled(PiecesB);
        }
        (Pole).add(addedButton);
        addCheck();

        if(MoveW) {
            if(findCheck(BKing.getParent().getName(), true)){
                BKing.setBackground(Color.MAGENTA);
                ch = true;
            }else{
                BKing.setBackground(Color.CYAN);
                ch=false;
            }
        }else{
            if(findCheck(WKing.getParent().getName(), false)){
                WKing.setBackground(Color.MAGENTA);
                ch = true;
            }else{
                WKing.setBackground(Color.CYAN);
                ch=false;
            }
        }

        if(ch){
            System.out.println("Szach");
            findEnd();
        }

        MoveW = !MoveW;
    }
    protected void movePiece(JButton Button){
        if(MoveW){
            removeCheck(getEnabledButton(PiecesW));
            removeButton(getEnabledButton(PiecesW));
        }else{
            removeCheck(getEnabledButton(PiecesB));
            removeButton(getEnabledButton(PiecesB));
        }

        for (JButton jButton : arrayCheckFields) {
            if (jButton.getParent() != null && jButton != Button) {
                removeButton(jButton);
            }
        }

        for (JButton jButton : arrayAtackFields) {
            if (jButton.getParent() != null) {
                removeButton(jButton);
            }
        }

        if(MoveW){
            (Button.getParent()).add(getEnabledButton(PiecesW));
            addCheck();
            setNotEnabled(PiecesW);
            setBackEnabled(PiecesB);
            if(ifSomethingisinSomething(Button, arrayWPawns) && Button.getParent().getName().toCharArray()[0] == 64){
                Button.setBackground(Color.lightGray);
            }
        }else{
            (Button.getParent()).add(getEnabledButton(PiecesB));
            addCheck();
            setNotEnabled(PiecesB);
            setBackEnabled(PiecesW);
            if(ifSomethingisinSomething(Button, arrayBPawns) && Button.getParent().getName().toCharArray()[0] == 72){
                Button.setBackground(Color.lightGray);
            }
        }
        removeButton(Button);



        if(MoveW) {
            if(findCheck(BKing.getParent().getName(), true)){
                BKing.setBackground(Color.MAGENTA);
                ch = true;
            }else{
                BKing.setBackground(Color.CYAN);
                ch=false;
            }
        }else{
            if(findCheck(WKing.getParent().getName(), false)){
                WKing.setBackground(Color.MAGENTA);
                ch = true;
            }else{
                WKing.setBackground(Color.CYAN);
                ch=false;
            }
        }

        if(ch){
            System.out.println("Szach");
            findEnd();
        }

        MoveW = !MoveW;
    }


    //Show Fields
    protected void ShowPawnField(int j, JButton Button) {
        Button.setEnabled(false);
        int a;
        String newName;
        Container Field;
        char[] Charsname1 = ((Button.getParent()).getName()).toCharArray();
        char[] Charsname = ((Button.getParent()).getName()).toCharArray();
        if(j > 0){
            a = 1;
        }else{
            a = -1;
        }

        Charsname[0] = (char) (Charsname[0] + a);
        if((checkSameColor(Button, WPawn1) && Charsname[0] < 73) || (checkSameColor(Button, BPawn1) && Charsname[0] > 64) && !ch) {
            System.out.println(ch);
            System.out.println("test");
            newName = String.valueOf(Charsname);
            Field = (Container) componentMap.get(newName);
            if (Field.getComponentCount() == 0 && checkSimulation(Button, Field)) {
                Field.add(check2);
                Field.repaint();
                Field.revalidate();
            }

            Charsname1[0] = Charsname[0];
            Charsname1[1] = (char) (Charsname[1] + 1);

            if (j == 2 || j == -2 && (Field.getComponentCount() == 0 || Field.getComponent(0) == check2)) {
                Charsname[0] = (char) (Charsname[0] + (j - a));
                newName = String.valueOf(Charsname);
                Field = (Container) componentMap.get(newName);
                if (Field.getComponentCount() == 0 && checkSimulation(Button, Field)) {
                    Field.add(check);
                    Field.repaint();
                    Field.revalidate();
                }
            }

            Charsname[0] = Charsname1[0];
            Charsname[1] = (char) (Charsname1[1] - 2);

            if(Charsname[1] != '0'){
                Field = (Container) (componentMap.get(String.valueOf(Charsname)));
                if (Field.getComponentCount() != 0 && !checkSameColor(Button, (JButton) Field.getComponent(0)) && checkSimulation(Button, Field)) {
                    Field.add(atack1);
                    Field.repaint();
                    Field.revalidate();
                }
            }
            if(Charsname1[1] != '9') {
                Field = (Container) (componentMap.get(String.valueOf(Charsname1)));
                if (Field.getComponentCount() != 0 && !checkSameColor(Button, (JButton) Field.getComponent(0)) && checkSimulation(Button, Field)) {
                    Field.add(atack2);
                    Field.repaint();
                    Field.revalidate();
                }
            }
        }
        //Button.setBackground((Button.getParent()).getBackground());
    }
    protected void showRookField(JButton Button){
        Button.setEnabled(false);
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];
        int a = 0;
        int b = 0;

        //left
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            if((int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //right
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            if((int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //up
        for(int i = 1; i < 8 ; i++){
            temp[0] --;
            if((int)temp[0] > 64){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0] = ParentButtonName[0];

        //down
        for(int i = 1; i < 8 ; i++){
            temp[0] ++;
            if((int)temp[0] < 73){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
    }
    protected void showRunnerField(JButton Button){
        Button.setEnabled(false);
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];
        int a = 0;
        int b = 0;

        //left-up
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-down
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-down
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-up
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

    }
    protected void showKnightField(JButton Button){
        Button.setEnabled(false);
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];
        int a = 0;
        int b = 0;

        for(int i = 0; i < 8; i++){
            temp[0] = (char) (temp[0] + howKnight[i][0]);
            temp[1] = (char) (temp[1] + howKnight[i][1]);
            if((int)temp[0] > 64 && (int)temp[0] < 73 && (int)temp[1] > 48 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                        }
                        break;
                    }
                }
            }
            temp[0] = ParentButtonName[0];
            temp[1] = ParentButtonName[1];
        }
    }
    protected void showQueenField(JButton Button){
        Button.setEnabled(false);
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];
        int a = 0;
        int b = 0;

        //left
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            if((int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //right
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            if((int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //up
        for(int i = 1; i < 8 ; i++){
            temp[0] --;
            if((int)temp[0] > 64){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0] = ParentButtonName[0];

        //down
        for(int i = 1; i < 8 ; i++){
            temp[0] ++;
            if((int)temp[0] < 73){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0] = ParentButtonName[0];

        //left-up
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }

        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-up
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-down
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-down
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
    }
    protected void showKingField(JButton Button){
        Button.setEnabled(false);
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];
        int a = 0;
        int b = 0;

        //left
        temp[1] --;
        if((int)temp[1] > 48){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }
        temp[1] = ParentButtonName[1];

        //right
        temp[1] ++;
        if((int)temp[1] < 57){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }
        temp[1] = ParentButtonName[1];

        //up
        temp[0] --;
        if((int)temp[0] > 64) {
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }
        temp[0] = ParentButtonName[0];

        //down
        temp[0] ++;
        if((int)temp[0] < 73){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }
        temp[0] = ParentButtonName[0];

        //left-up
        temp[1] --;
        temp[0] --;
        if((int)temp[0] > 64 && (int)temp[1] > 48){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }

        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-up
        temp[1] ++;
        temp[0] --;
        if((int)temp[0] > 64 && (int)temp[1] < 57){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-down
        temp[1] ++;
        temp[0] ++;
        if((int)temp[0] < 73 && (int)temp[1] < 57){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if(findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                        a++;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                            b++;
                        }
                    }
                }
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-down
        temp[1] --;
        temp[0] ++;
        if((int)temp[0] < 73 && (int)temp[1] > 48){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (findCheck(String.valueOf(temp))){
                if(checkSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        Field.add(arrayCheckFields[a]);
                        Field.repaint();
                        Field.revalidate();
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            Field.add(arrayAtackFields[b]);
                            Field.repaint();
                            Field.revalidate();
                        }
                    }
                }
            }
        }
    }

    //Click on the piece
    protected void move(){
        for (JButton jButton : arrayCheckFields) {
            if (jButton.getParent() != null) {
                removeButton(jButton);
            }
        }

        for (JButton jButton : arrayAtackFields) {
            if (jButton.getParent() != null) {
                removeButton(jButton);
            }
        }

        if(MoveW){
            for (JButton jButton : PiecesW) {
                if (jButton.getParent() != null) {
                    setBackEnabled(PiecesW);
                }
            }
        }else{
            for (JButton jButton : PiecesB) {
                if (jButton.getParent() != null) {
                    setBackEnabled(PiecesB);
                }
            }
        }
    }
    protected void movePawn(JButton Button){
        move();

        char[] ButtonName = (Button.getName()).toCharArray();
        char[] ButtonParentName = ((Button.getParent()).getName()).toCharArray();

        Button.setEnabled(false);

        if(ButtonParentName[0] == 'B' && ButtonName[0] == 'B'){
            ShowPawnField(2, Button);
        }else{
            if(ButtonParentName[0] != 'B' && ButtonName[0] == 'B'){
                ShowPawnField(1, Button);
            }else{
                if(ButtonParentName[0] == 'G' && ButtonName[0] == 'G') {
                    ShowPawnField(-2, Button);
                }else{
                    ShowPawnField(-1, Button);
                }
            }
        }
    }
    protected void moveTower(JButton Button){
        move();
        showRookField(Button);
    }
    protected void moveRunner(JButton Button){
        move();
        showRunnerField(Button);
    }
    protected void moveKnight(JButton Button){
        move();
        showKnightField(Button);
    }
    protected void moveQueen(JButton Button){
        move();
        showQueenField(Button);
    }
    protected void moveKing(JButton Button){
        move();
        showKingField(Button);
    }

    protected boolean findCheck(String FieldName){
        if(MoveW){
            return !checksBVector.contains(FieldName);
        }else{
            return !checksWVector.contains(FieldName);
        }
    }
    protected boolean findCheck(String FieldName, Boolean temp){
        if(temp){
            return checksWVector.contains(FieldName);
        }else{
            return checksBVector.contains(FieldName);
        }
    }
    protected boolean findCheckSimulation(String FieldName, Vector<String> vector){
        return !vector.contains(FieldName);
    }
    protected boolean checkSimulation(JButton Button, Container Field){
        JButton[] Pieces = new JButton[16];
        if(!MoveW){
            System.arraycopy(PiecesW, 0, Pieces, 0, PiecesW.length);
        }else{
            System.arraycopy(PiecesB, 0, Pieces, 0, PiecesB.length);
        }
        addToVector(Pieces, simulation, Button, Field);
        if(MoveW){
            return (findCheckSimulation(WKing.getParent().getName(), simulation));
        }else{
            return (findCheckSimulation(BKing.getParent().getName(), simulation));
        }

    }
    protected boolean checkFinalSimulation(JButton Button, Container Field){
        JButton[] FinalPieces = new JButton[16];
        if(MoveW){
            System.arraycopy(PiecesW, 0, FinalPieces, 0, PiecesW.length);
        }else{
            System.arraycopy(PiecesB, 0, FinalPieces, 0, PiecesB.length);
        }
        addToVector(FinalPieces, simulation, Button, Field);
        if(!MoveW){
            return (findCheckSimulation(WKing.getParent().getName(), simulation));
        }else{
            return (findCheckSimulation(BKing.getParent().getName(), simulation));
        }

    }


    protected void addCheck(){
        if(MoveW){
            addToVector(PiecesW, checksWVector);
        }else{
            addToVector(PiecesB, checksBVector);
        }
    }
    protected void addToVector(JButton[] Button, Vector<String> vector){
        vector.removeAllElements();
        for (JButton jButton : Button) {
            if(jButton != null && jButton.getParent() != null) {
                char[] ParentButtonName = ((jButton.getParent().getName()).toCharArray());
                char[] temp = new char[2];
                temp[0] = ParentButtonName[0];
                temp[1] = ParentButtonName[1];
                int index;
                index = returnIndex(jButton);
                Container Field;

                //pawns
                if (index > -1 && index < 8) {
                    if (jButton.getName().contains("B")) {
                        temp[0]++;
                    } else {
                        temp[0]--;
                    }
                    temp[1]--;
                    if (temp[1] != '0') {
                        vector.add(String.valueOf(temp));
                    }
                    temp[1] = (char) (temp[1] + 2);
                    if (temp[1] != '9') {
                        vector.add(String.valueOf(temp));
                    }
                }

                //rooks
                if (index == 8 || index == 9) {
                    //left
                    for (int i = 1; i < 8; i++) {
                        temp[1]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[1] > 48){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[1] = ParentButtonName[1];

                    //right
                    for (int i = 1; i < 8; i++) {
                        temp[1]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[1] < 57){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[1] = ParentButtonName[1];

                    //up
                    for (int i = 1; i < 8; i++) {
                        temp[0]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] > 64){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];

                    //down
                    for (int i = 1; i < 8; i++) {
                        temp[0]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] < 73){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                }

                //runners
                if (index == 10 || index == 11) {
                    //left-up
                    for (int i = 1; i < 8; i++) {
                        temp[1]--;
                        temp[0]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] > 64 && (int) temp[1] > 48){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-up
                    for (int i = 1; i < 8; i++) {
                        temp[1]++;
                        temp[0]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] > 64 && (int) temp[1] < 57){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-down
                    for (int i = 1; i < 8; i++) {
                        temp[1]++;
                        temp[0]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] < 73 && (int) temp[1] < 57){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //left-down
                    for (int i = 1; i < 8; i++) {
                        temp[1]--;
                        temp[0]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] < 73 && (int) temp[1] > 48){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];
                }

                //knights
                if (index == 12 || index == 13) {
                    for (int i = 0; i < 8; i++) {
                        temp[0] = (char) (temp[0] + howKnight[i][0]);
                        temp[1] = (char) (temp[1] + howKnight[i][1]);
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] > 64 && (int) temp[0] < 73 && (int) temp[1] > 48 && (int) temp[1] < 57){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];
                    }
                }

                //queen
                if (index == 14) {
                    //left
                    for (int i = 1; i < 8; i++) {
                        temp[1]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if (((int) temp[1] > 48)){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[1] = ParentButtonName[1];

                    //right
                    for (int i = 1; i < 8; i++) {
                        temp[1]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if (((int) temp[1] < 57)){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[1] = ParentButtonName[1];

                    //up
                    for (int i = 1; i < 8; i++) {
                        temp[0]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if (((int) temp[0] > 64)){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];

                    //down
                    for (int i = 1; i < 8; i++) {
                        temp[0]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if (((int) temp[0] < 73)){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];

                    //left-up
                    for (int i = 1; i < 8; i++) {
                        temp[1]--;
                        temp[0]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] > 64 && (int) temp[1] > 48){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-up
                    for (int i = 1; i < 8; i++) {
                        temp[1]++;
                        temp[0]--;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] > 64 && (int) temp[1] < 57){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-down
                    for (int i = 1; i < 8; i++) {
                        temp[1]++;
                        temp[0]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] < 73 && (int) temp[1] < 57){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //left-down
                    for (int i = 1; i < 8; i++) {
                        temp[1]--;
                        temp[0]++;
                        Field = (Container) componentMap.get(String.valueOf(temp));
                        if ((int) temp[0] < 73 && (int) temp[1] > 48){
                            if(Field.getComponentCount() == 0){
                                vector.add(String.valueOf(temp));
                            }else{
                                if(Field.getComponent(0) == WKing || Field.getComponent(0) == BKing){
                                    vector.add(String.valueOf(temp));
                                }
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                }

                //king
                if (index == 15) {
                    //left
                    temp[1]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if (((int) temp[1] > 48) && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[1] = ParentButtonName[1];

                    //right
                    temp[1]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[1] < 57 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[1] = ParentButtonName[1];

                    //up
                    temp[0]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] > 64 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];

                    //down
                    temp[0]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] < 73 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];

                    //left-up
                    temp[1]--;
                    temp[0]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] > 64 && (int) temp[1] > 48 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-up
                    temp[1]++;
                    temp[0]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] > 64 && (int) temp[1] < 57 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-down
                    temp[1]++;
                    temp[0]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] < 73 && (int) temp[1] < 57 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //left-down
                    temp[1]--;
                    temp[0]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] < 73 && (int) temp[1] > 48 && Field.getComponentCount() == 0) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                }
            }
        }
    }
    protected void addToVector(JButton[] Button, Vector<String> vector, JButton simulatedButton, Container simulatedField){
        vector.removeAllElements();
        String simulatedFieldName = simulatedField.getName();
        char[] simulatedFieldNameArray = simulatedFieldName.toCharArray();
        for (JButton jButton : Button) {
            if(jButton != null && jButton.getParent() != null) {
                char[] ParentButtonName = ((jButton.getParent().getName()).toCharArray());
                char[] temp = new char[2];
                temp[0] = ParentButtonName[0];
                temp[1] = ParentButtonName[1];
                int index;
                index = returnIndex(jButton);
                Container Field;

                //pawns
                if (index > -1 && index < 8){
                    if(!jButton.getParent().getName().equals(simulatedField.getName())) {
                        if (jButton.getName().contains("B")) {
                            temp[0]++;
                        } else {
                            temp[0]--;
                        }
                        temp[1]--;
                        if (temp[1] != '0') {
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if (Field.getComponentCount() == 0) {
                                vector.add(String.valueOf(temp));
                            } else {
                                if (Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                }
                            }
                        }
                        temp[1] = (char) (temp[1] + 2);
                        if (temp[1] != '9') {
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if (Field.getComponentCount() == 0) {
                                vector.add(String.valueOf(temp));
                            } else {
                                if (Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                }
                            }
                        }
                    }
                }

                //rooks
                if (index == 8 || index == 9) {
                    if(!jButton.getParent().getName().equals(simulatedField.getName())) {

                        //left
                        for (int i = 1; i < 8; i++) {
                            temp[1]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if (((int) temp[1] > 48)) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        temp[1] = ParentButtonName[1];

                        //right
                        for (int i = 1; i < 8; i++) {
                            temp[1]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[1] < 57) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        temp[1] = ParentButtonName[1];

                        //up
                        for (int i = 1; i < 8; i++) {
                            temp[0]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        temp[0] = ParentButtonName[0];

                        //down
                        for (int i = 1; i < 8; i++) {
                            temp[0]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] < 73) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }

                //runners
                if (index == 10 || index == 11) {
                    if(!jButton.getParent().getName().equals(simulatedField.getName())) {
                        //left-up
                        for (int i = 1; i < 8; i++) {
                            temp[1]--;
                            temp[0]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64 && (int) temp[1] > 48) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];

                        //right-up
                        for (int i = 1; i < 8; i++) {
                            temp[1]++;
                            temp[0]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64 && (int) temp[1] < 57) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];

                        //right-down
                        for (int i = 1; i < 8; i++) {
                            temp[1]++;
                            temp[0]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] < 73 && (int) temp[1] < 57) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];

                        //left-down
                        for (int i = 1; i < 8; i++) {
                            temp[1]--;
                            temp[0]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] < 73 && (int) temp[1] > 48) {
                                if (Arrays.equals(temp, simulatedFieldNameArray)) {
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)) {
                                    vector.add(String.valueOf(temp));
                                } else {
                                    if (Field.getComponent(0) == WKing || Field.getComponent(0) == BKing) {
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];
                    }
                }

                //knights
                if (index == 12 || index == 13) {
                    if(!jButton.getParent().getName().equals(simulatedField.getName())){
                        for (int i = 0; i < 8; i++) {
                            temp[0] = (char) (temp[0] + howKnight[i][0]);
                            temp[1] = (char) (temp[1] + howKnight[i][1]);
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64 && (int) temp[0] < 73 && (int) temp[1] > 48 && (int) temp[1] < 57) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                }
                            }
                            temp[0] = ParentButtonName[0];
                            temp[1] = ParentButtonName[1];
                        }
                    }
                }

                //queen
                if (index == 14) {
                    if(!jButton.getParent().getName().equals(simulatedField.getName())){

                        //left
                        for (int i = 1; i < 8; i++) {
                            temp[1]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[1] > 48) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[1] = ParentButtonName[1];

                        //right
                        for (int i = 1; i < 8; i++) {
                            temp[1]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[1] < 57) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[1] = ParentButtonName[1];

                        //up
                        for (int i = 1; i < 8; i++) {
                            temp[0]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[0] = ParentButtonName[0];

                        //down
                        for (int i = 1; i < 8; i++) {
                            temp[0]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] < 73) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[0] = ParentButtonName[0];

                        //left-up
                        for (int i = 1; i < 8; i++) {
                            temp[1]--;
                            temp[0]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64 && (int) temp[1] > 48) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];

                        //right-up
                        for (int i = 1; i < 8; i++) {
                            temp[1]++;
                            temp[0]--;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] > 64 && (int) temp[1] < 57) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];

                        //right-down
                        for (int i = 1; i < 8; i++) {
                            temp[1]++;
                            temp[0]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] < 73 && (int) temp[1] < 57){
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                        temp[0] = ParentButtonName[0];
                        temp[1] = ParentButtonName[1];

                        //left-down
                        for (int i = 1; i < 8; i++) {
                            temp[1]--;
                            temp[0]++;
                            Field = (Container) componentMap.get(String.valueOf(temp));
                            if ((int) temp[0] < 73 && (int) temp[1] > 48) {
                                if(Arrays.equals(temp, simulatedFieldNameArray)){
                                    vector.add(String.valueOf(temp));
                                    break;
                                }
                                if(Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton || ifSomethingisinSomething((JButton) Field.getComponent(0), arrayCheckFields)){
                                    vector.add(String.valueOf(temp));
                                }else{
                                    if(Field.getComponent(0) == WKing  || Field.getComponent(0) == BKing ){
                                        vector.add(String.valueOf(temp));
                                    }
                                    break;
                                }
                            }else{
                                break;
                            }
                        }
                    }
                }

                //king
                if (index == 15) {
                    //left
                    temp[1]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if (((int) temp[1] > 48) && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[1] = ParentButtonName[1];

                    //right
                    temp[1]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[1] < 57 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[1] = ParentButtonName[1];

                    //up
                    temp[0]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] > 64 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];

                    //down
                    temp[0]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] < 73 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];

                    //left-up
                    temp[1]--;
                    temp[0]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] > 64 && (int) temp[1] > 48 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-up
                    temp[1]++;
                    temp[0]--;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] > 64 && (int) temp[1] < 57 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //right-down
                    temp[1]++;
                    temp[0]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] < 73 && (int) temp[1] < 57 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                    temp[0] = ParentButtonName[0];
                    temp[1] = ParentButtonName[1];

                    //left-down
                    temp[1]--;
                    temp[0]++;
                    Field = (Container) componentMap.get(String.valueOf(temp));
                    if ((int) temp[0] < 73 && (int) temp[1] > 48 && (Field.getComponentCount() == 0 || Field.getComponent(0) == simulatedButton)) {
                        vector.add(String.valueOf(temp));
                    } else {
                        break;
                    }
                }
            }
        }
    }


    protected void removeCheck(JButton Button){
        if(MoveW){
            removeFromVector(Button, checksWVector);
        }else{
            removeFromVector(Button, checksBVector);
        }
    }
    protected void removeCheck(JButton Button, Boolean temp){
        if(temp){
            removeFromVector(Button, checksWVector);
        }else{
            removeFromVector(Button, checksBVector);
        }
    }
    protected void removeFromVector(JButton Button, Vector<String> vector){
        char[] ParentButtonName = ((Button.getParent().getName()).toCharArray());
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];
        int index = returnIndex(Button);

        //pawns
        if(index > -1 && index < 8){
            if(Button.getName().contains("B")){
                temp[0]++;
            }else{
                temp[0]--;
            }
            temp[1]--;
            if(temp[1] != '0'){
                vector.remove(String.valueOf(temp));
            }
            temp[1]= (char) (temp[1] +2);
            if(temp[1] != '9') {
                vector.remove(String.valueOf(temp));
            }
        }

        //rooks
        if(index == 8 || index == 9){
            //left
            for(int i = 1; i < 8 ; i++){
                temp[1] --;
                if((int)temp[1] > 48){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[1] = ParentButtonName[1];

            //right
            for(int i = 1; i < 8 ; i++){
                temp[1] ++;
                if((int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[1] = ParentButtonName[1];

            //up
            for(int i = 1; i < 8 ; i++){
                temp[0] --;
                if((int)temp[0] > 64){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0] = ParentButtonName[0];

            //down
            for(int i = 1; i < 8 ; i++){
                temp[0] ++;
                if((int)temp[0] < 73){
                    vector.remove(String.valueOf(temp));
                }
            }
        }

        //runners
        if(index == 10 || index == 11){
            //left-up
            for(int i = 1; i < 8 ; i++){
                temp[1] --;
                temp[0] --;
                if((int)temp[0] > 64 && (int)temp[1] > 48){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //right-up
            for(int i = 1; i < 8 ; i++){
                temp[1] ++;
                temp[0] --;
                if((int)temp[0] > 64 && (int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //right-down
            for(int i = 1; i < 8 ; i++){
                temp[1] ++;
                temp[0] ++;
                if((int)temp[0] < 73 && (int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //left-down
            for(int i = 1; i < 8 ; i++){
                temp[1] --;
                temp[0] ++;
                if((int)temp[0] < 73 && (int)temp[1] > 48){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];
        }

        //knights
        if(index == 12 || index == 13){
            for(int i = 0; i < 8; i++){
                temp[0] = (char) (temp[0] + howKnight[i][0]);
                temp[1] = (char) (temp[1] + howKnight[i][1]);
                if((int)temp[0] > 64 && (int)temp[0] < 73 && (int)temp[1] > 48 && (int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
                temp[0] = ParentButtonName[0];
                temp[1] = ParentButtonName[1];
            }
        }

        //queen
        if(index == 14){
            //left
            for(int i = 1; i < 8 ; i++){
                temp[1] --;
                if((int)temp[1] > 48){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[1] = ParentButtonName[1];

            //right
            for(int i = 1; i < 8 ; i++){
                temp[1] ++;
                if((int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[1] = ParentButtonName[1];

            //up
            for(int i = 1; i < 8 ; i++){
                temp[0] --;
                if((int)temp[0] > 64){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0] = ParentButtonName[0];

            //down
            for(int i = 1; i < 8 ; i++){
                temp[0] ++;
                if((int)temp[0] < 73){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0] = ParentButtonName[0];

            //left-up
            for(int i = 1; i < 8 ; i++){
                temp[1] --;
                temp[0] --;
                if((int)temp[0] > 64 && (int)temp[1] > 48){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //right-up
            for(int i = 1; i < 8 ; i++){
                temp[1] ++;
                temp[0] --;
                if((int)temp[0] > 64 && (int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //right-down
            for(int i = 1; i < 8 ; i++){
                temp[1] ++;
                temp[0] ++;
                if((int)temp[0] < 73 && (int)temp[1] < 57){
                    vector.remove(String.valueOf(temp));
                }
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //left-down
            for(int i = 1; i < 8 ; i++){
                temp[1] --;
                temp[0] ++;
                if((int)temp[0] < 73 && (int)temp[1] > 48){
                    vector.remove(String.valueOf(temp));
                }
            }
        }

        //king
        if(index == 15){
            //left
            temp[1] --;
            if((int)temp[1] > 48){
                vector.remove(String.valueOf(temp));
            }
            temp[1] = ParentButtonName[1];

            //right
            temp[1] ++;
            if((int)temp[1] < 57){
                vector.remove(String.valueOf(temp));
            }
            temp[1] = ParentButtonName[1];

            //up
            temp[0] --;
            if((int)temp[0] > 64){
                vector.remove(String.valueOf(temp));
            }
            temp[0] = ParentButtonName[0];

            //down
            temp[0] ++;
            if((int)temp[0] < 73){
                vector.remove(String.valueOf(temp));
            }
            temp[0] = ParentButtonName[0];

            //left-up
            temp[1] --;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] > 48){
                vector.remove(String.valueOf(temp));
            }

            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //right-up
            temp[1] ++;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] < 57){
                vector.remove(String.valueOf(temp));
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //right-down
            temp[1] ++;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] < 57){
                vector.remove(String.valueOf(temp));
            }
            temp[0]= ParentButtonName[0];
            temp[1]= ParentButtonName[1];

            //left-down
            temp[1] --;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] > 48){
                vector.remove(String.valueOf(temp));
            }
        }

        Collections.sort(vector);
    }

    protected int returnIndex(JButton Button){
        JButton B2;
        for(int i = 0; i < PiecesW.length; i++){
            B2 = PiecesW[i];
            if(Button == B2){
                return i;
            }
        }

        for(int i = 0; i < PiecesB.length; i++){
            B2 = PiecesB[i];
            if(Button == B2){
                return i;
            }
        }
        return -1;
    }
    protected boolean ifSomethingisinSomething(JButton Button, JButton[] button){
        JButton B2;
        for(JButton jButton : button) {
            B2 = jButton;
            if (Button == B2) {
                return true;
            }
        }
        return false;

    }

    protected void findEnd(){
        JButton[] Pieces = new JButton[16];
        if(MoveW){
            //white end move
            System.arraycopy(PiecesB, 0, Pieces, 0, PiecesB.length);
        }else{
            //black end move
            System.arraycopy(PiecesW, 0, Pieces, 0, PiecesW.length);
        }
        if(finalSimulation(Pieces)){
            System.out.println("Szach mat");
        }
    }

    protected boolean finalSimulation(JButton[] Buttons){
        for (JButton jButton : Buttons) {
            if(jButton != null && jButton.getParent() != null) {
                char[] ParentButtonName = ((jButton.getParent().getName()).toCharArray());
                char[] temp = new char[2];
                temp[0] = ParentButtonName[0];
                temp[1] = ParentButtonName[1];
                int index;
                index = returnIndex(jButton);

                //pawns
                if (index > -1 && index < 8) {
                    if(temp[0] == 'B' && !MoveW){
                        if(ShowFinalPawnField(2,jButton)){
                            System.out.println(" 1 ");
                            return false;
                        }
                    }else{
                        if(temp[0] != 'B' && !MoveW){
                            if(ShowFinalPawnField(1,jButton)){
                                System.out.println(" 2 ");
                                return false;
                            }
                        }else{
                            if(temp[0] == 'G') {
                                if(ShowFinalPawnField(-2,jButton)){
                                    System.out.println(" 3 ");
                                    return false;
                                }
                            }else{
                                if(ShowFinalPawnField(-1,jButton)){
                                    System.out.println(" 4 ");
                                    return false;
                                }
                            }
                        }
                    }
                }

                //rooks
                if (index == 8 || index == 9) {
                    if(ShowFinalRookField(jButton)){
                        System.out.println("5");
                        return false;
                    }
                }

                //runners
                if (index == 10 || index == 11) {
                    if(ShowFinalRunnerField(jButton)){
                        System.out.println("6");
                        return false;
                    }
                }

                //knights
                if (index == 12 || index == 13) {
                    if(ShowFinalKnightField(jButton)){
                        System.out.println("7");
                        return false;
                    }
                }

                //queen
                if (index == 14) {
                    if(ShowFinalQueenField(jButton)) {
                        System.out.println("8");
                        return false;
                    }
                }

                //king
                if (index == 15) {
                    if(ShowFinalKingField(jButton)) {
                        System.out.println("9");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Show Fields
    protected boolean ShowFinalPawnField(int j, JButton Button) {
        int a;
        String newName;
        Container Field;
        char[] Charsname1 = ((Button.getParent()).getName()).toCharArray();
        char[] Charsname = ((Button.getParent()).getName()).toCharArray();
        if(j > 0){
            a = 1;
        }else{
            a = -1;
        }

        Charsname[0] = (char) (Charsname[0] + a);
        if((checkSameColor(Button, WPawn1) && Charsname[0] < 73) || (checkSameColor(Button, BPawn1) && Charsname[0] > 64)) {
            newName = String.valueOf(Charsname);
            Field = (Container) componentMap.get(newName);
            if (Field.getComponentCount() == 0 && checkFinalSimulation(Button, Field)) {
                System.out.println("11");
                return true;
            }

            Charsname1[0] = Charsname[0];
            Charsname1[1] = (char) (Charsname[1] + 1);

            if (j == 2 || j == -2 && (Field.getComponentCount() == 0 || Field.getComponent(0) == check2)) {
                Charsname[0] = (char) (Charsname[0] + (j - a));
                newName = String.valueOf(Charsname);
                Field = (Container) componentMap.get(newName);
                if (Field.getComponentCount() == 0 && checkFinalSimulation(Button, Field)) {
                    System.out.println("12");
                    return true;
                }
            }

            Charsname[0] = Charsname1[0];
            Charsname[1] = (char) (Charsname1[1] - 2);

            if(Charsname[1] != '0'){
                Field = (Container) (componentMap.get(String.valueOf(Charsname)));
                if (Field.getComponentCount() != 0 && !checkSameColor(Button, (JButton) Field.getComponent(0)) && checkFinalSimulation(Button, Field)) {
                    System.out.println("13");
                    return true;
                }
            }
            if(Charsname1[1] != '9') {
                Field = (Container) (componentMap.get(String.valueOf(Charsname1)));
                return (Field.getComponentCount() != 0 && !checkSameColor(Button, (JButton) Field.getComponent(0)) && checkFinalSimulation(Button, Field));
            }
        }else{
            newName = String.valueOf(Charsname);
            Field = (Container) componentMap.get(newName);
            if (Field.getComponentCount() == 0 && checkFinalSimulation(Button, Field)){
                System.out.println("014");
                return true;
            }

            Charsname1[0] = Charsname[0];
            Charsname1[1] = (char) (Charsname[1] + 1);
            if (j == 2 || j == -2 && Field.getComponent(0).getName() == null) {
                Charsname[0] = (char) (Charsname[0] + (j - a));
                newName = String.valueOf(Charsname);
                Field = (Container) componentMap.get(newName);
                if (Field.getComponentCount() == 0 && findCheck(newName) && findCheck(Button.getParent().getName())) {
                    System.out.println("15");
                    return true;
                }
            }

            Charsname[0] = Charsname1[0];
            Charsname[1] = (char) (Charsname1[1] - 2);

            if(Charsname[1] != '0'){
                Field = (Container) (componentMap.get(String.valueOf(Charsname)));
                if (Field.getComponentCount() != 0 && !checkSameColor(Button, (JButton) Field.getComponent(0)) && findCheck(newName) && findCheck(Button.getParent().getName())) {
                    System.out.println("16");
                    return true;
                }
            }
            if(Charsname1[1] != '9') {
                Field = (Container) (componentMap.get(String.valueOf(Charsname1)));
                return (Field.getComponentCount() != 0 && !checkSameColor(Button, (JButton) Field.getComponent(0)) && findCheck(newName) && findCheck(Button.getParent().getName()));
            }
        }
        return false;
        //Button.setBackground((Button.getParent()).getBackground());
    }
    protected boolean ShowFinalRookField(JButton Button){
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];



        //left
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            if((int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //right
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            if((int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //up
        for(int i = 1; i < 8 ; i++){
            temp[0] --;
            if((int)temp[0] > 64){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0] = ParentButtonName[0];


        //down
        for(int i = 1; i < 8 ; i++){
            temp[0] ++;
            if((int)temp[0] < 73){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }


        return false;
    }
    protected boolean ShowFinalRunnerField(JButton Button){
        Button.setEnabled(false);
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-up
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-down
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-down
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-up
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        return false;
    }
    protected boolean ShowFinalKnightField(JButton Button){
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        for(int i = 0; i < 8; i++){
            temp[0] = (char) (temp[0] + howKnight[i][0]);
            temp[1] = (char) (temp[1] + howKnight[i][1]);
            if((int)temp[0] > 64 && (int)temp[0] < 73 && (int)temp[1] > 48 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }
            temp[0] = ParentButtonName[0];
            temp[1] = ParentButtonName[1];
        }
        return false;
    }
    protected boolean ShowFinalQueenField(JButton Button){
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            if((int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //right
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            if((int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[1] = ParentButtonName[1];

        //up
        for(int i = 1; i < 8 ; i++){
            temp[0] --;
            if((int)temp[0] > 64){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0] = ParentButtonName[0];

        //down
        for(int i = 1; i < 8 ; i++){
            temp[0] ++;
            if((int)temp[0] < 73){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0] = ParentButtonName[0];


        //left-up
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-up
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] --;
            if((int)temp[0] > 64 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-down
        for(int i = 1; i < 8 ; i++){
            temp[1] ++;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] < 57){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-down
        for(int i = 1; i < 8 ; i++){
            temp[1] --;
            temp[0] ++;
            if((int)temp[0] < 73 && (int)temp[1] > 48){
                Container Field = (Container) (componentMap.get(String.valueOf(temp)));
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            return true;
                        }
                        break;
                    }
                }
            }else{
                break;
            }
        }

        return false;
    }
    protected boolean ShowFinalKingField(JButton Button){
        char[] ParentButtonName = ((Button.getParent()).getName()).toCharArray();
        char[] temp = new char[2];
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left
        temp[1] --;
        if((int)temp[1] > 48){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("401");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("402");
                            return true;
                        }
                    }
                }
            }
        }
        temp[1] = ParentButtonName[1];

        //right
        temp[1] ++;
        if((int)temp[1] < 57){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("403");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("404");
                            return true;
                        }
                    }
                }
            }
        }
        temp[1] = ParentButtonName[1];

        //up
        temp[0] --;
        if((int)temp[0] > 64) {
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("405");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("406");
                            return true;
                        }
                    }
                }
            }
        }
        temp[0] = ParentButtonName[0];

        //down
        temp[0] ++;
        if((int)temp[0] < 73){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("407");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("408");
                            return true;
                        }
                    }
                }
            }
        }
        temp[0] = ParentButtonName[0];

        //left-up
        temp[1] --;
        temp[0] --;
        if((int)temp[0] > 64 && (int)temp[1] > 48){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("409");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("410");
                            return true;
                        }
                    }
                }
            }
        }

        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-up
        temp[1] ++;
        temp[0] --;
        if((int)temp[0] > 64 && (int)temp[1] < 57){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("411");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("412");
                            return true;
                        }
                    }
                }
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //right-down
        temp[1] ++;
        temp[0] ++;
        if((int)temp[0] < 73 && (int)temp[1] < 57){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if(!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("413");
                        return true;
                    }else{
                        if (!checkSameColor(Button, (JButton) Field.getComponent(0))) {
                            System.out.println("414");
                            return true;
                        }
                    }
                }
            }
        }
        temp[0]= ParentButtonName[0];
        temp[1]= ParentButtonName[1];

        //left-down
        temp[1] --;
        temp[0] ++;
        if((int)temp[0] < 73 && (int)temp[1] > 48){
            Container Field = (Container) (componentMap.get(String.valueOf(temp)));
            if (!findCheck(String.valueOf(temp))){
                if(checkFinalSimulation(Button, Field)){
                    if(Field.getComponentCount()== 0){
                        System.out.println("415");
                        return true;
                    }else{
                        return !checkSameColor(Button, (JButton) Field.getComponent(0));
                    }
                }
            }
        }
        return false;
    }
}
