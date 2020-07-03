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
    private JButton  once, gamestart, pause, reset,gameover;// 一次 开始 停止 重置 退出
    private JButton[][] btnBlock;  //细胞地图
    private boolean[][] isSelected;
    private int maxRow, maxCol;
    private Game_of_Life life;
    private boolean isRunning;
    private Thread thread;
    private boolean isDead;

    public int speed=917;//每一代演化时间


    public static void main(String arg[]) {
        frame = new GameGui("岑涛 刘思成小组");
    }



    public void initGUI() {
      
            maxRow = 50;
   
            maxCol = 75;
     

        life = new Game_of_Life(maxRow, maxCol);

        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(maxRow, maxCol));
        bottomPanel = new JPanel();
  
        btnBlock = new JButton[maxRow][maxCol];
       
        once = new JButton("变化一次");
        
        gamestart = new JButton("开始");
        pause = new JButton("暂停");
        reset = new JButton("重置细胞地图");
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

    //随机初始化
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
                            JOptionPane.showMessageDialog(null, "演化结束");
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