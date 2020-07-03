package gameoflife;


public class Game_of_Life {
  
	private int maxRow;
    private int maxCol;

    int[][] cellworld;//int二维数组来表示细胞世界，并约定 1--生存；0--死亡

    public Game_of_Life(int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        cellworld = new int[maxRow + 2][maxCol + 2];//初始化设置成+2为了防止数组越界，方便统一计算，简化了对边界的计算
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
                        newcellworld[row][col] = cellworld[row][col]; //不管细胞状态是生还是死，当周围存在2个存活细胞，状态不变
                        break;
                    case 3:
                        newcellworld[row][col] = 1; //不管细胞状态是生还是死，当周围存在3个存活细胞，都变为生存状态
                        break;
                    default:
                        newcellworld[row][col] = 0; //全部置死
                }
        //生成下一幅细胞图
        for (int row = 1; row <= maxRow; row++)
            for (int col = 1; col <= maxCol; col++)
                cellworld[row][col] = newcellworld[row][col];
    }

    //用来获取当前细胞周围8格的生存细胞数量
    public int getNeighborCount(int row, int col) 
    {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++)
            for (int j = col - 1; j <= col + 1; j++)
                count += cellworld[i][j];
        count -= cellworld[row][col];//减去自身
        return count;
    }
}
