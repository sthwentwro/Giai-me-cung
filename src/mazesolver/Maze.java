package mazesolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    int[][] maze;
    public boolean[][] visited; //mảng 2 chiều chứa danh sách các nút đã duyệt và chưa duyệt
    public static Toado start = new Toado(5,0);//tọa độ bắt đầu
    public static Toado end = new Toado(12,19);//tọa dộ kết thúc
    public List<Toado> duongdi = new ArrayList<Toado>();//danh sách tọa độ đường đi
    // lấy tọa độ bắt đầu
    public Toado getEntry() {
        return start;
    }
    //lấy tọa độ kết thúc
    public Toado getExit() {
        return end;
    }
    //nếu đã đến tọa độ đích thì true
    public boolean isExit(int x, int y) {
        return x == end.getX() && y == end.getY();
    }
    //nếu đứng ở vị trí bắt đầu thì true
    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }
    //nếu là vị trí đã duyệt thì trả về true
    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }
    // ví trí là tường thì trả về true
    public boolean isWall(int row, int col) {
        return maze[row][col] == 1;
    }
    //đánh dấu vị trí đang xét 
    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }
    //kiểm tra vị trí nếu nằm ngoài mê cung thì trả về false
    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }
        return true;
    }
    //reset lại mảng lưu vị trí đã đi qua
    public void reset() {
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], false);
    }
    public Maze(int[][] maze){
        this.maze = maze;
        visited = new boolean[maze.length][maze[0].length];
    }
}
