package tu;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            System.out.println(e);
        }
        new MyFrame();
        List<String> data = new ArrayList<>();
        data.add("ab2");
        data.add("ac6");
        data.add("bc7");
        String vertex = new String("abc");
        //System.out.println("从ui界面接受的边关系 : " + MyFrame.strEdgeData);
        if (MyFrame.START) {
            System.out.println("开始了啊啊啊啊啊啊啊啊啊");
            Graph g = new Graph(data, Integer.parseInt(MyFrame.strVerNum), Integer.parseInt(MyFrame.strEdgeNum), vertex);
            g.prim();
        }

    }
}
