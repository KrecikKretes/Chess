package zawisza;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Board extends Functions{
    public Board() {
        arrayW= new JPanel[]{A1, A2, A3, A4, A5, A6, A7, A8, B1, B2, B3, B4, B5, B6, B7, B8};
        arrayB= new JPanel[]{G1, G2, G3, G4, G5, G6, G7, G8, H1, H2, H3, H4, H5, H6, H7, H8};
        PiecesW = new JButton[]{WPawn1, WPawn2, WPawn3, WPawn4, WPawn5, WPawn6, WPawn7, WPawn8, WRookLeft, WRookRight, WRunnerLeft, WRunnerRight, WKnightLeft, WKnightRight, WQueen, WKing};
        PiecesB = new JButton[]{BPawn1, BPawn2, BPawn3, BPawn4, BPawn5, BPawn6, BPawn7, BPawn8, BRookLeft, BRookRight, BRunnerLeft, BRunnerRight, BKnightLeft, BKnightRight, BQueen, BKing};


        arrayWPawns = new JButton[]{WPawn1, WPawn2, WPawn3, WPawn4, WPawn5, WPawn6, WPawn7, WPawn8};
        arrayBPawns = new JButton[]{BPawn1, BPawn2, BPawn3, BPawn4, BPawn5, BPawn6, BPawn7, BPawn8};
        arrayWPieces = new JButton[]{WRookLeft,WKnightLeft, WRunnerLeft, WQueen, WKing, WRunnerRight, WKnightRight, WRookRight};
        arrayBPieces = new JButton[]{BRookLeft,BKnightLeft, BRunnerLeft, BQueen, BKing, BRunnerRight, BKnightRight, BRookRight};

        arrayAtackFields = new JButton[]{atack1, atack2, atack3, atack4, atack5, atack6, atack7, atack8};
        arrayCheckFields = new JButton[]{check,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11,check12,check13,check14,check15,check16,check17,check18,check19,check20,check21,check22,check23,check24,check25,check26,check27};


        addButtons(arrayW,arrayWPawns, arrayWPieces, 'b');
        addButtons(arrayB,arrayBPawns, arrayBPieces, 'c');


        optionsButton(arrayWPieces, Color.yellow);
        optionsButton(arrayBPieces, Color.yellow);
        optionsButton(arrayBPawns, Color.GREEN);
        optionsButton(arrayWPawns,Color.PINK);
        optionsButton(arrayAtackFields,Color.red);
        optionsButton(arrayCheckFields,Color.BLUE);

        WRunnerLeft.setBackground(Color.orange);
        WRunnerRight.setBackground(Color.orange);
        BRunnerLeft.setBackground(Color.orange);
        BRunnerRight.setBackground(Color.orange);


        setNotEnabled(PiecesB);

        MainPanel.setLayout(new GridLayout(8,1,0,0));
        MainPanel.setPreferredSize(new Dimension(620,620));
        MainPanel.setBorder(new EmptyBorder(10,10,10,10));
        f.setPreferredSize(new Dimension(700,700));
        f.add(MainPanel);

        giveSize(A,B,C,D,E,F,G,H);

        addPanelsToMain(MainPanel,A,B,C,D,E,F,G,H);
        addPanels(A,A1,A2,A3,A4,A5,A6,A7,A8);
        addPanels(B,B1,B2,B3,B4,B5,B6,B7,B8);
        addPanels(C,C1,C2,C3,C4,C5,C6,C7,C8);
        addPanels(D,D1,D2,D3,D4,D5,D6,D7,D8);
        addPanels(E,E1,E2,E3,E4,E5,E6,E7,E8);
        addPanels(F,F1,F2,F3,F4,F5,F6,F7,F8);
        addPanels(G,G1,G2,G3,G4,G5,G6,G7,G8);
        addPanels(H,H1,H2,H3,H4,H5,H6,H7,H8);


        components = new Component[][]{A.getComponents(),B.getComponents(),C.getComponents(),D.getComponents(), E.getComponents(), F.getComponents(), G.getComponents(), H.getComponents() };

        drawBoard(components);

        int k = 0;
        for(char i = 'A'; i < 73; i++){
            for(int j = 0; j < 8; j++){
                components[k][j].setName((i+""+(j+1)));
                componentMap.put((i+""+(j+1)),components[k][j]);
            }
            k++;
        }
        for(int i = 1; i < 9; i++){
            checksWVector.add("C" + i);
            checksBVector.add("F" + i);
            checksWVector.add("C" + i);
            checksBVector.add("F" + i);

            if(i == 3 || i == 6){
                checksWVector.add("C" + i);
                checksBVector.add("F" + i);
            }
        }

        WPawn1.addActionListener(actionEvent -> movePawn(WPawn1));
        WPawn2.addActionListener(actionEvent -> movePawn(WPawn2));
        WPawn3.addActionListener(actionEvent -> movePawn(WPawn3));
        WPawn4.addActionListener(actionEvent -> movePawn(WPawn4));
        WPawn5.addActionListener(actionEvent -> movePawn(WPawn5));
        WPawn6.addActionListener(actionEvent -> movePawn(WPawn6));
        WPawn7.addActionListener(actionEvent -> movePawn(WPawn7));
        WPawn8.addActionListener(actionEvent -> movePawn(WPawn8));

        BPawn1.addActionListener(actionEvent -> movePawn(BPawn1));
        BPawn2.addActionListener(actionEvent -> movePawn(BPawn2));
        BPawn3.addActionListener(actionEvent -> movePawn(BPawn3));
        BPawn4.addActionListener(actionEvent -> movePawn(BPawn4));
        BPawn5.addActionListener(actionEvent -> movePawn(BPawn5));
        BPawn6.addActionListener(actionEvent -> movePawn(BPawn6));
        BPawn7.addActionListener(actionEvent -> movePawn(BPawn7));
        BPawn8.addActionListener(actionEvent -> movePawn(BPawn8));

        WRookLeft.addActionListener(actionEvent -> moveTower(WRookLeft));
        WRookRight.addActionListener(actionEvent -> moveTower(WRookRight));
        BRookLeft.addActionListener(actionEvent -> moveTower(BRookLeft));
        BRookRight.addActionListener(actionEvent -> moveTower(BRookRight));

        WRunnerLeft.addActionListener(actionEvent -> moveRunner(WRunnerLeft));
        WRunnerRight.addActionListener(actionEvent -> moveRunner(WRunnerRight));
        BRunnerLeft.addActionListener(actionEvent -> moveRunner(BRunnerLeft));
        BRunnerRight.addActionListener(actionEvent -> moveRunner(BRunnerRight));

        WKnightLeft.addActionListener(actionEvent -> moveKnight(WKnightLeft));
        WKnightRight.addActionListener(actionEvent -> moveKnight(WKnightRight));
        BKnightLeft.addActionListener(actionEvent -> moveKnight(BKnightLeft));
        BKnightRight.addActionListener(actionEvent -> moveKnight(BKnightRight));

        WQueen.addActionListener(actionEvent -> moveQueen(WQueen));
        BQueen.addActionListener(actionEvent -> moveQueen(BQueen));

        WKing.addActionListener(actionEvent -> moveKing(WKing));
        BKing.addActionListener(actionEvent -> moveKing(BKing));

        check.addActionListener(actionEvent -> movePiece(check));
        check2.addActionListener(actionEvent -> movePiece(check2));
        check3.addActionListener(actionEvent -> movePiece(check3));
        check4.addActionListener(actionEvent -> movePiece(check4));
        check5.addActionListener(actionEvent -> movePiece(check5));
        check6.addActionListener(actionEvent -> movePiece(check6));
        check7.addActionListener(actionEvent -> movePiece(check7));
        check8.addActionListener(actionEvent -> movePiece(check8));
        check9.addActionListener(actionEvent -> movePiece(check9));
        check10.addActionListener(actionEvent -> movePiece(check10));
        check11.addActionListener(actionEvent -> movePiece(check11));
        check12.addActionListener(actionEvent -> movePiece(check12));
        check13.addActionListener(actionEvent -> movePiece(check13));
        check14.addActionListener(actionEvent -> movePiece(check14));
        check15.addActionListener(actionEvent -> movePiece(check15));
        check16.addActionListener(actionEvent -> movePiece(check16));
        check17.addActionListener(actionEvent -> movePiece(check17));
        check18.addActionListener(actionEvent -> movePiece(check18));
        check19.addActionListener(actionEvent -> movePiece(check19));
        check20.addActionListener(actionEvent -> movePiece(check20));
        check21.addActionListener(actionEvent -> movePiece(check21));
        check22.addActionListener(actionEvent -> movePiece(check22));
        check23.addActionListener(actionEvent -> movePiece(check23));
        check24.addActionListener(actionEvent -> movePiece(check24));
        check25.addActionListener(actionEvent -> movePiece(check25));
        check26.addActionListener(actionEvent -> movePiece(check26));
        check27.addActionListener(actionEvent -> movePiece(check27));

        atack1.addActionListener(actionEvent -> atackPiece(atack1));
        atack2.addActionListener(actionEvent -> atackPiece(atack2));
        atack3.addActionListener(actionEvent -> atackPiece(atack3));
        atack4.addActionListener(actionEvent -> atackPiece(atack4));
        atack5.addActionListener(actionEvent -> atackPiece(atack5));
        atack6.addActionListener(actionEvent -> atackPiece(atack6));
        atack7.addActionListener(actionEvent -> atackPiece(atack7));
        atack8.addActionListener(actionEvent -> atackPiece(atack8));

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}