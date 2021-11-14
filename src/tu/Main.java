package tu;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Main {
    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            System.out.println(e);
        }
        new MyFrame();
    }
}
