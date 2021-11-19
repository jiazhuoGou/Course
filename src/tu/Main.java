package tu;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

public class Main {
    public static boolean mainOk = false;

    public static void main(String[] args) throws InterruptedException {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            System.out.println(e);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyFrame().setVisible(true);
            }
        });

        // new MyFrame();
        List<String> data = new ArrayList<>(); // 用来存储关系
        String tmpStrData = null;
        while (true) { // 为了界面能够一直循环
            // 用要用一个等待线程去阻塞主线程，不然主线程直接就完了
            MyThread myThread = new MyThread();
            myThread.run();
            myThread.join();
            System.out.println("哈哈哈哈哈开始了啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
            // 开始转换关系集合
            tmpStrData = MyFrame.strEdgeData;
            String[] dataArr  = tmpStrData.split(" ");
            for (int i = 0; i < dataArr.length; i++) {
                data.add(dataArr[i]);
            }
            // 执行算法
            Graph g = new Graph(data, Integer.parseInt(MyFrame.strVerNum), Integer.parseInt(MyFrame.strEdgeNum), MyFrame.strVertexs);
            System.out.println("当前的算法选择 ： " + MyFrame.algoType);
            switch (MyFrame.algoType) {
                case 1: g.prim();break;
                case 2: g.dijstra(MyFrame.strSrc.toCharArray()[0]);break;
                case 3: g.maximumMatching();break;
                default:break;
            }
            MyFrame.START = false; // 继续阻塞然后一直循环
            data.clear();  // 清楚掉关系缓存
        }
    }

    static class MyThread extends Thread {
        //public boolean tmp;
        public void run() {
            while (!MyFrame.START);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
