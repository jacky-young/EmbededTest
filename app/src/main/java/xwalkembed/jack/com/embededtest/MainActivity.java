package xwalkembed.jack.com.embededtest;

import android.os.Bundle;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.internal.XWalkViewBridge;

import java.lang.reflect.Method;


public class MainActivity extends XWalkActivity {
    private XWalkView mXWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    @Override
    protected void onXWalkReady() {
        try {
            Method __getBridge = XWalkView.class.getDeclaredMethod("getBridge");
            __getBridge.setAccessible(true);
            XWalkViewBridge xWalkViewBridge = null;
            xWalkViewBridge = (XWalkViewBridge) __getBridge.invoke(mXWalkView);
            XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
            xWalkViewBridge.getSettings().setAllowFileAccessFromFileURLs(true);
            xWalkViewBridge.getSettings().setAllowUniversalAccessFromFileURLs(true);
            xWalkViewBridge.getSettings().setDomStorageEnabled(false);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        mXWalkView.load("http://www.baidu.com/", null);
    }
}
