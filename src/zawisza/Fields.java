package zawisza;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class Fields {
    protected final JFrame f = new JFrame("Chess");

    protected Boolean MoveW = true;
    protected Boolean ch = false;

    protected Icon WPawnIcon = new ImageIcon("PawnIcon.png");

    //pawns
    protected JButton WPawn1 = new JButton(WPawnIcon);
    protected JButton WPawn2 = new JButton(WPawnIcon);
    protected JButton WPawn3 = new JButton(WPawnIcon);
    protected JButton WPawn4 = new JButton(WPawnIcon);
    protected JButton WPawn5 = new JButton(WPawnIcon);
    protected JButton WPawn6 = new JButton(WPawnIcon);
    protected JButton WPawn7 = new JButton(WPawnIcon);
    protected JButton WPawn8 = new JButton(WPawnIcon);

    protected JButton BPawn1 = new JButton(WPawnIcon);
    protected JButton BPawn2 = new JButton(WPawnIcon);
    protected JButton BPawn3 = new JButton(WPawnIcon);
    protected JButton BPawn4 = new JButton(WPawnIcon);
    protected JButton BPawn5 = new JButton(WPawnIcon);
    protected JButton BPawn6 = new JButton(WPawnIcon);
    protected JButton BPawn7 = new JButton(WPawnIcon);
    protected JButton BPawn8 = new JButton(WPawnIcon);

    //rooks
    protected JButton WRookLeft = new JButton();
    protected JButton WRookRight = new JButton();
    protected JButton BRookLeft = new JButton();
    protected JButton BRookRight = new JButton();

    //runners
    protected JButton WRunnerLeft = new JButton();
    protected JButton WRunnerRight = new JButton();
    protected JButton BRunnerLeft = new JButton();
    protected JButton BRunnerRight = new JButton();

    //knights
    protected JButton WKnightLeft = new JButton();
    protected JButton WKnightRight = new JButton();
    protected JButton BKnightLeft = new JButton();
    protected JButton BKnightRight = new JButton();

    //queens
    protected JButton WQueen = new JButton();
    protected JButton BQueen = new JButton();

    //kings
    protected JButton WKing = new JButton();
    protected JButton BKing = new JButton();

    protected JButton check = new JButton();
    protected JButton check2 = new JButton();
    protected JButton check3 = new JButton();
    protected JButton check4 = new JButton();
    protected JButton check5 = new JButton();
    protected JButton check6 = new JButton();
    protected JButton check7 = new JButton();
    protected JButton check8 = new JButton();
    protected JButton check9 = new JButton();
    protected JButton check10 = new JButton();
    protected JButton check11 = new JButton();
    protected JButton check12 = new JButton();
    protected JButton check13 = new JButton();
    protected JButton check14 = new JButton();
    protected JButton check15 = new JButton();
    protected JButton check16 = new JButton();
    protected JButton check17 = new JButton();
    protected JButton check18 = new JButton();
    protected JButton check19 = new JButton();
    protected JButton check20 = new JButton();
    protected JButton check21 = new JButton();
    protected JButton check22 = new JButton();
    protected JButton check23 = new JButton();
    protected JButton check24 = new JButton();
    protected JButton check25 = new JButton();
    protected JButton check26 = new JButton();
    protected JButton check27 = new JButton();

    protected JButton atack1 = new JButton();
    protected JButton atack2 = new JButton();
    protected JButton atack3 = new JButton();
    protected JButton atack4 = new JButton();
    protected JButton atack5 = new JButton();
    protected JButton atack6 = new JButton();
    protected JButton atack7 = new JButton();
    protected JButton atack8 = new JButton();

    protected JButton[] arrayWPawns;
    protected JButton[] arrayBPawns;
    protected JButton[] arrayWPieces;
    protected JButton[] arrayBPieces;
    protected JButton[] arrayAtackFields;
    protected JButton[] arrayCheckFields;

    protected JPanel MainPanel = new JPanel();

    protected JPanel[] arrayW;
    protected JPanel[] arrayB;

    protected JButton[] PiecesW;
    protected JButton[] PiecesB;

    protected JPanel A = new JPanel();
    protected JPanel B = new JPanel();
    protected JPanel C = new JPanel();
    protected JPanel D = new JPanel();
    protected JPanel E = new JPanel();
    protected JPanel F = new JPanel();
    protected JPanel G = new JPanel();
    protected JPanel H = new JPanel();

    protected JPanel A1 = new JPanel();
    protected JPanel A2 = new JPanel();
    protected JPanel A3 = new JPanel();
    protected JPanel A4 = new JPanel();
    protected JPanel A5 = new JPanel();
    protected JPanel A6 = new JPanel();
    protected JPanel A7 = new JPanel();
    protected JPanel A8 = new JPanel();

    protected JPanel B1 = new JPanel();
    protected JPanel B2 = new JPanel();
    protected JPanel B3 = new JPanel();
    protected JPanel B4 = new JPanel();
    protected JPanel B5 = new JPanel();
    protected JPanel B6 = new JPanel();
    protected JPanel B7 = new JPanel();
    protected JPanel B8 = new JPanel();

    protected JPanel C1 = new JPanel();
    protected JPanel C2 = new JPanel();
    protected JPanel C3 = new JPanel();
    protected JPanel C4 = new JPanel();
    protected JPanel C5 = new JPanel();
    protected JPanel C6 = new JPanel();
    protected JPanel C7 = new JPanel();
    protected JPanel C8 = new JPanel();

    protected JPanel D1 = new JPanel();
    protected JPanel D2 = new JPanel();
    protected JPanel D3 = new JPanel();
    protected JPanel D4 = new JPanel();
    protected JPanel D5 = new JPanel();
    protected JPanel D6 = new JPanel();
    protected JPanel D7 = new JPanel();
    protected JPanel D8 = new JPanel();

    protected JPanel E1 = new JPanel();
    protected JPanel E2 = new JPanel();
    protected JPanel E3 = new JPanel();
    protected JPanel E4 = new JPanel();
    protected JPanel E5 = new JPanel();
    protected JPanel E6 = new JPanel();
    protected JPanel E7 = new JPanel();
    protected JPanel E8 = new JPanel();

    protected JPanel F1 = new JPanel();
    protected JPanel F2 = new JPanel();
    protected JPanel F3 = new JPanel();
    protected JPanel F4 = new JPanel();
    protected JPanel F5 = new JPanel();
    protected JPanel F6 = new JPanel();
    protected JPanel F7 = new JPanel();
    protected JPanel F8 = new JPanel();

    protected JPanel G1 = new JPanel();
    protected JPanel G2 = new JPanel();
    protected JPanel G3 = new JPanel();
    protected JPanel G4 = new JPanel();
    protected JPanel G5 = new JPanel();
    protected JPanel G6 = new JPanel();
    protected JPanel G7 = new JPanel();
    protected JPanel G8 = new JPanel();

    protected JPanel H1 = new JPanel();
    protected JPanel H2 = new JPanel();
    protected JPanel H3 = new JPanel();
    protected JPanel H4 = new JPanel();
    protected JPanel H5 = new JPanel();
    protected JPanel H6 = new JPanel();
    protected JPanel H7 = new JPanel();
    protected JPanel H8 = new JPanel();


    protected HashMap<String, Component> componentMap = new HashMap<>();
    protected Vector<String> checksWVector = new Vector<>();
    protected Vector<String> checksBVector = new Vector<>();
    protected Vector<String> simulation = new Vector<>();
    protected Vector<String> simulation2 = new Vector<>();
    protected Component[][] components;
    protected int[][] howKnight = {{-2,-1},{-2,1},{-1,2},{1,2},{2,-1},{2,1}, {-1,-2},{1,-2}};
}
