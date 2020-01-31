package ivy.haihong.com.vipvideo_android.Home;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ivy.haihong.com.vipvideo_android.Home.Model.VideoModel;
import ivy.haihong.com.vipvideo_android.Home.WebView.WebViewActivity;
import ivy.haihong.com.vipvideo_android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabHomeFragment extends Fragment {
    private View _view;
    ListView mHotListView;
    private HomeLostAdapter mHomeLostAdapter;
    ArrayList<VideoModel> mDataSource;

    public TabHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null != _view) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (null != parent) {
                parent.removeView(_view);
            }
        } else {
            _view = inflater.inflate(R.layout.fragment_home, null);
            loadData();
            initView();
            initData();
        }
        return _view;
     }

    private void loadData()
    {
        ArrayList<String> imageurls = new ArrayList<>();
        imageurls.add("https://img.alicdn.com/tfs/TB1WeJ9Xrj1gK0jSZFuXXcrHpXa-195-195.png");
        imageurls.add("https://www.iqiyipic.com/common/fix/site-v4/sprite-headLogo-nonIndex.png");
        //imageurls.add("https://img.mgtv.com/imgotv-channel/6.1.7/pcweb-header/svg/logo.svg");
        imageurls.add("http://www.wkun.com/zt/hunantv_logo/images/logo07.jpg");
        imageurls.add("http://static.letvcdn.com/lc04_phone/201803/01/15/01/logo_red.svg");
        imageurls.add("https://vm.gtimg.cn/tencentvideo/vstyle/web/v6/style/img/common/sprite_head_logo.svg");
        imageurls.add("https://css.tv.itc.cn/channel/v2/header-images/logo-sohu2.svg");
        imageurls.add("http://sr1.pplive.cn/mcms/nav/images/1508317145889.png");


        ArrayList<String> targetURLS = new ArrayList<>();
        targetURLS.add("https://movie.youku.com/");
        //targetURLS.add("https://www.iqiyi.com/dianying/");
        targetURLS.add("https://m.iqiyi.com/v_19rvwbg5lk.html?list=19rr9727c2");
        targetURLS.add("https://www.iqiyi.com/v_19rr7p0ns4.html?key=497e5c296ca7ff592f821e933523f96a");
        targetURLS.add("https://www.iqiyi.com/iframe/loginreg?ver=1");
        targetURLS.add("https://www.mgtv.com/movie/");
        targetURLS.add("http://movie.le.com/");
        targetURLS.add("https://v.qq.com/channel/movie");
        targetURLS.add("https://tv.sohu.com/movie/");
        targetURLS.add("http://movie.pptv.com/");

        ArrayList<String> targetNames=new ArrayList<>();

        for(int i=0;i<targetURLS.size();i++)
        {
            String s = targetURLS.get(i);
            String sAr[]  =s.split("\\.");
            targetNames.add(sAr[1]);
        }

        ArrayList<VideoModel> mArr = new ArrayList<>();
        for (int i=0; i<imageurls.size(); i++) {
            VideoModel model = new VideoModel(imageurls.get(i),targetURLS.get(i),targetNames.get(i));
            mArr.add(model);
        }
        mDataSource = mArr;
    }

    private void initData() {
        mHomeLostAdapter =  new HomeLostAdapter(mDataSource,_view.getContext());
        mHotListView.setAdapter(mHomeLostAdapter);

        mHotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final VideoModel videoModel = mDataSource.get(position);
                Log.i("lichanghong",videoModel.toString());

                final  String targeturl = videoModel.getTargetURL();

                    Intent intent = new Intent();
                    intent.setClass(_view.getContext(), WebViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", targeturl );
                    intent.putExtras(bundle);
                    startActivity(intent);

                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });

        loadData();
    }


    private void initView() {
        mHotListView  = (ListView) _view.findViewById(R.id.hotListView);
    }


}
