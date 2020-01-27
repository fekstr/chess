package com.fekstr.ui;

import com.fekstr.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class SquareUI extends JButton implements ActionListener {

    private Square square;
    private ChessPiece piece;
    private Color backgroundColor;
    private static ArrayList<SquareUI> squareuiList = new ArrayList<SquareUI>();
    private ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
    public boolean highlighted;
    public static boolean activePlay = false;


    SquareUI(Square square, Color color) {
        //super(new ImageIcon(Class.class.getResource("./img/Chess_rlt60.png")));
        this.backgroundColor = color;
        //this.coordinate = coordinate;
        this.square = square;
        this.highlighted = false;

        setBorder(new LineBorder(Color.WHITE, 0));
        setBackground(this.backgroundColor);
        setOpaque(true);

        setBorderPainted(true);
        addActionListener(this);
        //setIcon(new ImageIcon(Class.class.getResource("./img/Chess_rlt60.png")));
        setPieceImage();
        //setHorizontalAlignment(SwingConstants.LEFT);

        squareuiList.add(this);

        if(!square.isEmpty()) {

            // debug
            if (square.getPiece().getColor() == Player.WHITE) {
                setForeground(Color.WHITE);
            } else {
                setForeground(Color.BLACK);
            }
            setText(square.getPiece().getClass().getSimpleName());
        } else {
            setText(null);
        }

        /* DEBUG AREA */
        //setForeground(Color.RED);
        if (square.isThreatened) {
            setBorder(new LineBorder(Color.RED, 2));
        }
    }

    public void actionPerformed(ActionEvent e) {

        String status = "no status";

        // Click on piece or highlighted square
        if(!this.square.isEmpty() || this.isHighlighted()) {

            Coordinate c = square.getCoordinate();


            // No piece has been selected
            if(!activePlay && Board.getCurrentPlayer() == this.square.getPlayer()) {
                activePlay = true;
                ArrayList<Coordinate> validMoves = square.getValidMoves();

                if (validMoves.size() == 0) {
                    activePlay = false;
                    System.out.println("No valid moves");
                } else {
                    BoardUI.highlightValidMoves(validMoves);
                    BoardUI.setCurrentCoordinate(c);
                }

            }

            // A piece has previously been selected
            else if (activePlay && BoardUI.getCurrentCoordinate() != null && this.isHighlighted()) {
                status = Board.makeMove(BoardUI.getCurrentCoordinate(), c);
                BoardUI.setCurrentCoordinate(null);

                BoardUI.renderBoard();

                activePlay = false;
            }


            else {
                activePlay = false;
                BoardUI.resetHighlight();
                status = "INVALID MOVE";
            }


            System.out.println("that worked");
        } else {
            // Reset on click outside of highlight
            status = "INVALID MOVE";
            activePlay = false;
            BoardUI.resetHighlight();

        }

        System.out.println(status);
        BoardUI.setStatus(status);

//        System.out.println(square.getCoordinate());
//        System.out.println(square.getPiece());
//        highlightSquares();

    }

    private Square getSquare() {
        return this.square;
    }

    public void highlightGreen() {
        this.highlighted = !this.highlighted;
        setBackground(Color.GREEN);

    }

    public void resetBackgroundColor() {
        setBackground(this.backgroundColor);
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }


    private void highlight() {
        if(backgroundColor.equals(Color.WHITE)) {
            setBackground(Color.lightGray);
        } else {
            setBackground(Color.darkGray);
        }

    }

    private void highlightSquares() {
        highlight();
    }

    private ImageIcon getScaledIcon(final Image image, final double scale)
    {
        ImageIcon scaledIcon = new ImageIcon(image)
        {
            public int getIconWidth()
            {
                return (int)(image.getWidth(null) * scale);
            }

            public int getIconHeight()
            {
                return (int)(image.getHeight(null) * scale);
            }

            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                g.drawImage(image, x, y, getIconWidth(), getIconHeight(), c);
            }
        };
        return scaledIcon;
    }
    private void setImage(File imgFile) {
        try {
            Image img = ImageIO.read(imgFile);
            setIcon(new ImageIcon(img));
            //setIcon(getScaledIcon(img, 0.8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setPieceImage() {
        if (!square.isEmpty()) {
            ChessPiece chessPiece = square.getPiece();
            File imgFile = new File("./img/Chess_rlt60.png");
            switch (chessPiece.getType()) {
                case BISHOP:
                    if (square.getPiece().getColor() == Player.BLACK) {
                        imgFile = new File("./img/Chess_bdt60.png");
                    } else {
                        imgFile = new File("./img/Chess_blt60.png");
                    }

                    break;
                case KING:
                    if (square.getPiece().getColor() == Player.BLACK) {
                        imgFile = new File("./img/Chess_kdt60.png");
                    } else {
                        imgFile = new File("./img/Chess_klt60.png");
                    }
                    break;
                case KNIGHT:
                    if (square.getPiece().getColor() == Player.BLACK) {
                        imgFile = new File("./img/Chess_ndt60.png");
                    } else {
                        imgFile = new File("./img/Chess_nlt60.png");
                    }
                    break;
                case PAWN:
                    if (square.getPiece().getColor() == Player.BLACK) {
                        imgFile = new File("./img/Chess_pdt60.png");
                    } else {
                        imgFile = new File("./img/Chess_plt60.png");
                    }
                    break;
                case QUEEN:
                    if (square.getPiece().getColor() == Player.BLACK) {
                        imgFile = new File("./img/Chess_qdt60.png");
                    } else {
                        imgFile = new File("./img/Chess_qlt60.png");
                    }
                    break;
                case ROOK:
                    if (square.getPiece().getColor() == Player.BLACK) {
                        imgFile = new File("./img/Chess_rdt60.png");
                    } else {
                        imgFile = new File("./img/Chess_rlt60.png");
                    }
                    break;

            }
            setImage(imgFile);
            //File imageCheck = new File("./img/Chess_rlt60.png");


        }






    }
}
