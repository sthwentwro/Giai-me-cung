package mazesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Breadth_First_Search {
     private static final int[][] huong = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };//(sang phải),(đi xuống),(sang trái),(đi lên)
    
     public List<Toado> solve(Maze maze) {
        LinkedList<Toado> nextToVisit = new LinkedList<>();
        //lấy tọa độ bắt đầu
        Toado start = maze.getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            //xét nút vừa bỏ vị trí đầu tiên trong danh sách
            Toado cur = nextToVisit.remove();
            //kiểm tra xem nút đó đã xét chưa và có trong khu vực mê cung không
            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }
            //kiểm tra nút có phải là tường không
            if (maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }
            //nếu nút là vị trí đích thì truy vết lại đường đi
            if (maze.isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }
            //thêm vào danh sách tất cả các nút con của nút hiện tại
            for (int[] direction : huong) {
                Toado coordinate = new Toado(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();//trả về một danh sách rỗng
    }
    //phương thức quay lại từ nút đích
    //đến nút gốc của nó
     private List<Toado> backtrackPath(Toado cur) {
        List<Toado> path = new ArrayList<>();
        Toado iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }
        Collections.reverse(path);//đảo ngược lại danh sách để lấy đúng chiều từ đầu tới nút đích
        return path;
    }
}
