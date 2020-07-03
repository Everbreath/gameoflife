package gameoflife;


public class Game_of_Life {
  
	private int maxRow;
    private int maxCol;

    int[][] cellworld;//int��ά��������ʾϸ�����磬��Լ�� 1--���棻0--����

    public Game_of_Life(int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        cellworld = new int[maxRow + 2][maxCol + 2];//��ʼ�����ó�+2Ϊ�˷�ֹ����Խ�磬����ͳһ���㣬���˶Ա߽�ļ���
        for (int row = 0; row <= maxRow + 1; row++)
            for (int col = 0; col <= maxCol + 1; col++)
                cellworld[row][col] = 0;
    }

    public int[][] getcellworld() {
        return cellworld;
    }

    public void setcellworld(int[][] cellworld) {
        this.cellworld = cellworld;
    }

    public void update() {
        int[][] newcellworld = new int[maxRow + 2][maxCol + 2];
        for (int row = 1; row <= maxRow; row++)
            for (int col = 1; col <= maxCol; col++)
                switch (getNeighborCount(row, col)) {
                    case 2:
                        newcellworld[row][col] = cellworld[row][col]; //����ϸ��״̬����������������Χ����2�����ϸ����״̬����
                        break;
                    case 3:
                        newcellworld[row][col] = 1; //����ϸ��״̬����������������Χ����3�����ϸ��������Ϊ����״̬
                        break;
                    default:
                        newcellworld[row][col] = 0; //ȫ������
                }
        //������һ��ϸ��ͼ
        for (int row = 1; row <= maxRow; row++)
            for (int col = 1; col <= maxCol; col++)
                cellworld[row][col] = newcellworld[row][col];
    }

    //������ȡ��ǰϸ����Χ8�������ϸ������
    public int getNeighborCount(int row, int col) 
    {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++)
            for (int j = col - 1; j <= col + 1; j++)
                count += cellworld[i][j];
        count -= cellworld[row][col];//��ȥ����
        return count;
    }
}