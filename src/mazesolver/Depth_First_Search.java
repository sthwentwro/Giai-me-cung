
package mazesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Depth_First_Search {
    private static final int[][] huong = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };//(sang phải),(đi xuống),(sang trái),(đi lên)
    
    public List<Toado> solve(Maze maze) {
        List<Toado> path = new ArrayList<>();
        //nếu tìm thấy đường đi thì trả về danh sách đường đi
        //còn không thì trả về danh sách rỗng
        if (explore(maze, maze.getEntry().getX(),
                    maze.getEntry().getY(),
                    path)) {
            return path;
        }
        return Collections.emptyList();
    }
    
    private boolean explore(Maze maze, int row, int col, List<Toado> path) {
        if (!maze.isValidLocation(row, col)|| maze.isWall(row, col) || maze.isExplored(row, col)) {
            return false;
        }
        //thêm nút vào danh sách đường đi
        path.add(new Toado(row, col));
        //set vị trí hiện tại vào danh sách các node đã duyệt
        maze.setVisited(row, col, true);
        //nếu đã ở vị trí đích thì trả về true
        if (maze.isExit(row, col)) {
            return true;
        }

        for (int[] direction : huong) {
            Toado coordinate = layToado_ketiep(row, col, direction[0], direction[1]);
            if (explore(maze, coordinate.getX(), coordinate.getY(), path)) {
                return true;
            }
        }
        //loại bỏ nút lá khỏi danh sách đường đi khi đã duyệt hết mà không phải nút kết thúc  
        path.remove(path.size() - 1);
        return false;
    }
    
    private Toado layToado_ketiep(int row, int col, int i, int j) {
            return new Toado(row + i, col + j);
    }
}
