package tu;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    public int sizeVertex; // 顶点数
    public int sizeEdge; // 边数
    public char[] vertex; // 节点用字符表示
    public int[] visited;  //
    public int[][] edge; // 邻接矩阵
    public  static int MAX = 99;
    public char[][] parent;  // 最小生成树的
    public char[][] parentDij; // 最短路径的

    public Graph() {}

    /*
     * data: 边集合
     * size: 顶点个数
     * size2: 边数
     * vertex : 顶点集合
     * */
    public Graph(List<String> data, int size, int size2, String vertex) {
        this.vertex = vertex.toCharArray();
        System.out.println("顶点集合 " + Arrays.toString(this.vertex));
        this.sizeVertex = size;
        this.sizeEdge = size2;
        // 初始化邻接矩阵
        edge = new int[size][size];
        parent = new char[2][sizeVertex];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    edge[i][j] = 0;
                } else {
                    edge[i][j] = MAX;
                }
            }
        } // size2是边数
        String str;
        for (int i = 0; i < size2; i++) {
            str = data.get(i);
            char[] c = str.toCharArray();
            int m = vertex.indexOf(c[0]);
            int n = vertex.indexOf(c[1]);
            edge[m][n] = c[2] - '0';
            edge[n][m] = edge[m][n];
            // vertex.indexOf(str[0]);
        }
        // 初始化 是否被访问数组
        visited = new int[sizeVertex];
        for (int i = 0; i < sizeVertex; i++) visited[i] = 1; // 代表未被访问
        System.out.println("访问数组 " + Arrays.toString(visited));
        show(edge, size);
    }

    public void show(int[][] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public void show(char[][] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    // currVex顶点数 currArc边数
    public void prim() {
        int[][] Min = new int[2][sizeVertex]; // Min第一行寸的是该点父亲的下标，节点与下标映射
        int MST = 0; // 树的最小权值和
        for (int i = 0; i < sizeVertex; i++) {
            Min[0][i] = MAX;
            Min[1][i] = MAX;
        }
        System.out.println("洗白点的顺序");
        for (int i = 0; i < sizeVertex; i++) { // 遍历顶点
            int w = 0;
            for (int j = 0; j < sizeVertex; j++) { // 邻接矩阵
                if ((Min[0][w] > Min[0][j]) && visited[j] == 1) {
                    /** 首选保证未选中
                     * 找出当前还没有被选中的 并且 离选中的最近的点*/
                    w = j;
                }
            }
            visited[w] = 0; // 距离最小的被找到，他成为选中的节点集合
            System.out.print(vertex[w] + " "); // 打印按顺序找出的点
            for (int a = 0; a < sizeVertex; a++) { // 找出选中点的父亲
                if (edge[w][a] == Min[0][w]  && visited[a] == 0 ) {
                    Min[1][w] = a;
                }
            }
            if (w != 0) { // 权值累加时期排除刚开始的节点权值，因为一开始的节点权值为无穷大
                MST += Min[0][w]; // 权值累加
            }
            for (int k = 0; k < sizeVertex; k++) {
                if (visited[k] != 0 && edge[w][k] < Min[0][k]) {
                    Min[0][k] = edge[w][k]; // 更新每个蓝点到白点集团的最小值
                }
            }
        }
        System.out.println();
        System.out.println("最小生成树权值总和 : " + MST);

        // 打印先后顺序
        for (int i = 0; i < sizeVertex; i++) {
            parent[0][i] = vertex[i];
        }
        for (int i = 1; i < sizeVertex; i++) {
            parent[1][i] = vertex[Min[1][i]];
        }
        parent[1][0] = vertex[0];
        System.out.println("最小生成树的关系：");
        show(parent, 2);
    }

    // 输入起点，它会打印对图中所有点的路劲
    public void dijstra(char src) {
        int[] shortest = new int[sizeVertex]; // 记录到某点的最短路径长度
        int[] visited = new int[sizeVertex]; // 记录该点是否被访问
        String[] path = new String[sizeVertex]; // 存储输出路径
        int[][] newEdge = new int[sizeVertex][sizeVertex];// 为了源邻接矩阵不被破坏
        String vertextStr = new String(vertex); // 把节点字符数组转String
        int srcIndex = vertextStr.indexOf(src); // 源点下标
        parentDij = new char[2][sizeVertex];

        for (int i = 0; i < shortest.length; i++) shortest[i] = 0;
        for (int i = 0; i < visited.length; i++) visited[i] = 0;
        for (int i = 0; i < sizeVertex; i++) {
            for (int j = 0; j < sizeVertex; j++) {
                newEdge[i][j] = edge[i][j];
            }
        }
        for (int i = 0; i < sizeVertex; i++) {
            parentDij[0][i] = vertex[i];
        }
        parentDij[1][srcIndex] = vertex[srcIndex]; // 源点的父亲就是他自己

        // 初始化输出路径
        for (int i = 0; i < sizeVertex; i++) {
            path[i] = new String(src + " -> " + vertex[i]);
        }

        shortest[vertextStr.indexOf(src)] = 0; // 它到自己的距离是0
        visited[vertextStr.indexOf(src)] = 1; // 一来就访问


        for (int i = 1; i < sizeVertex; i++) {
            int min = MAX;
            int w = -1; // w记录队列中距离最小的点的下标

            for (int j = 0; j < sizeVertex; j++) {
                if (visited[j] == 0 && newEdge[srcIndex][j] < min) {
                    // 首先判断该点是否被访问，在看它是不是最短的
                    min = newEdge[srcIndex][j];
                    w = j; // 记录最短的点的下标给j
                }
            }

            // 解决直连最短的点在前驱数组中存储不到的问题
            // 如果选中的点原来就是直连最短的，那么手动添加，因为后面标记为访问之后会漏掉
            if (edge[srcIndex][w] < MAX && newEdge[srcIndex][w] >= edge[srcIndex][w] ) {
                parentDij[1][w] = src;
            }

            // 更新最短路径
            shortest[w] = min;
            visited[w] = 1; // 标记为访问
            // 再更新源点到其他点的最短路径
            for (int j = 0; j < sizeVertex; j++) {
                if (visited[j] == 0 && newEdge[srcIndex][w] + newEdge[w][j] < newEdge[srcIndex][j]) {
                    // 如果经过新的点发现小于直连，那么才更新
                    // 如果之前新的比现在新的还小，那么其实不用管
                    // 永远是到已被访问的点集的距离，而不是到新访问的点的距离
                    newEdge[srcIndex][j] = newEdge[srcIndex][w] + newEdge[w][j];
                    path[j] = path[w] + " -> " + vertex[j];
                    parentDij[1][j] = vertex[w];
                }
            }
        } // 大循环，因为源点自己不用找，所以次数少一次

        // 打印最短路径
        System.out.println("前驱关系如下： ");
        show(parentDij, 2); // 先打印前驱关系
        System.out.println("各点到源点最短路径长度如下： ");
        System.out.println(Arrays.toString(shortest));
        System.out.println("最短路径具体展示如下: ");
        for (int i = 0; i < sizeVertex; i++) {
            if (vertex[i] != src) { // 如果该点不是源点
                if (shortest[i] == MAX) {
                    System.out.println(src + "到" + vertex[i] + "不可达");
                } else {
                    System.out.println(src + "到" + vertex[i] + "最短路径: " + path[i]);
                }
            }
        }
        System.out.println();

    }

}
