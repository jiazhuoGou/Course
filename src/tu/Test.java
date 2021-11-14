package tu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入顶点个数和边数
        System.out.println("输入顶点个数和边数");
        int size = sc.nextInt();
        int size2 = sc.nextInt();
        String vertex;
        List<String> data = new ArrayList<>();
        // 输入顶点值
        System.out.println("输入顶点");
        vertex = sc.next();
        System.out.println("输入边: ");
        for (int i = 0; i < size2; i++) {
            data.add(sc.next());
        }
        System.out.println("边集合 : " + data);

        Graph g = new Graph(data, size, size2, vertex);

        // 最小生成树
        g.prim();

        // 最短路径
        Scanner sc2 = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.print("请输入任意键开始求最短路径算法:(quit退出)");
            if (sc2.next().equals("quit")) {
                flag = false;
            } else {
                System.out.println();
                System.out.println("-------最短路径算法：-----------");
                String tmp = sc.next();
                char src = tmp.toCharArray()[0];
                System.out.println("源点是 " + src);
                g.dijstra(src);
            }
        }



    }
}
