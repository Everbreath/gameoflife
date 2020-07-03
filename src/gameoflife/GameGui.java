package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameGui extends JFrame implements ActionListener {
    private static GameGui frame;
    private JPanel backPanel, centerPanel, bottomPanel;
    private JButton  once, gamestart, pause, reset,gameover;// һ�� ��ʼ ֹͣ ���� �˳�
    private JButton[][] btnBlock;  //ϸ����ͼ
    private boolean[][] isSelected;
    private int maxRow, maxCol;
    private Game_of_Life life;
    private boolean isRunning;
    private Thread thread;
    private boolean isDead;

    public int speed=917;//ÿһ���ݻ�ʱ��


    public static void main(String arg[]) {
        frame = new GameGui("��� ��˼��С��");
    }



    public void initGUI() {
      
            maxRow = 50;
   
            maxCol = 75;
     

        life = new Game_of_Life(maxRow, maxCol);

        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(maxRow, maxCol));
        bottomPanel = new JPanel();
  
        btnBlock = new JButton[maxRow][maxCol];
       
        once = new JButton("�仯һ��");
        
        gamestart = new JButton("��ʼ");
        pause = new JButton("��ͣ");
        reset = new JButton("����ϸ����ͼ");
        gameover = new JButton("game over");
        isSelected = new boolean[maxRow][maxCol];
        
        this.setContentPane(backPanel);
       
        backPanel.add(centerPanel, "Center");
        backPanel.add(bottomPanel, "North");

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                btnBlock[i][j] = new JButton("");
                btnBlock[i][j].setBackground(Color.WHITE);
                centerPanel.add(btnBlock[i][j]);
            }
        }

       


        bottomPanel.add(once);
        bottomPanel.add(gamestart);
        bottomPanel.add(pause);
        bottomPanel.add(reset);
        bottomPanel.add(gameover);

        this.setSize(1500, 1000);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });

        once.addActionListener(this);
        gamestart.addActionListener(this);
        pause.addActionListener(this);
        reset.addActionListener(this);
        gameover.addActionListener(this);
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                btnBlock[i][j].addActionListener(this);
            }
        }
        panelInit();
    }

    //�����ʼ��
    public void panelInit(){
        int[][] cellworld = life.getcellworld();
        for(int i = 0;i<maxRow;i++){
            for(int j = 0;j<maxCol;j++){
                if(Math.random()>0.5){
                    btnBlock[i][j].setBackground(Color.black);
                    cellworld[i + 1][j + 1] = 1;
                }
            }
        }
    }

    public GameGui(String name) {
        super(name);
        initGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

         if(e.getSource() == once) {
            makeNextGeneration();
        } else if (e.getSource() == gamestart) {
            isRunning = true;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        makeNextGeneration();
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        isDead = true;
                        for(int row = 1; row <= maxRow; row++) {
                            for (int col = 1; col <= maxCol; col++) {
                                if (life.getcellworld()[row][col] != 0) {
                                    isDead = false;
                                    break;
                                }
                            }
                            if (!isDead) {
                                break;
                            }
                        }
                        if (isDead) {
                            JOptionPane.showMessageDialog(null, "�ݻ�����");
                            isRunning = false;
                            thread = null;
                        }
                    }
                }
            });
            thread.start();
        }else if (e.getSource() == pause) {
            isRunning = false;
            thread = null;
        } else if (e.getSource() == reset) {
            initGUI();
        }else if (e.getSource() == gameover) {
            System.exit(0);
        } else {
            int[][] cellworld = life.getcellworld();
            for (int i = 0; i < maxRow; i++) {
                for (int j = 0; j < maxCol; j++) {
                    if (e.getSource() == btnBlock[i][j]) {
                        isSelected[i][j] = !isSelected[i][j];
                        if (isSelected[i][j]) {
                            btnBlock[i][j].setBackground(Color.black);
                            cellworld[i + 1][j + 1] = 1;
                        } else {
                            btnBlock[i][j].setBackground(Color.white);
                            cellworld[i + 1][j + 1] = 0;
                        }
                        break;
                    }
                }
            }
            life.setcellworld(cellworld);
        }
    }

    public void makeNextGeneration() {
        life.update();
        int[][] cellworld = life.getcellworld();
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (cellworld[i + 1][j + 1] == 1) {
                    btnBlock[i][j].setBackground(Color.BLACK);
                } else {
                    btnBlock[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}