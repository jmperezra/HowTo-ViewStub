package jmperezra.com.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btnPre, btnPost;
    private ViewStub vsLore;
    private ViewGroup wrapper;
    private TextView tvInfo, tv1, tv2, tv3;
    private View viewInflated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViewInAttrs();
        setListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadViewInAttrs(){
        btnPre = (Button) findViewById(R.id.btnPre);
        btnPost = (Button) findViewById(R.id.btnPost);
        vsLore = (ViewStub) findViewById(R.id.vsLore);
        wrapper = (ViewGroup) findViewById(R.id.wrapper);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

    }

    private void setListeners(){
        if (btnPre != null){
            btnPre.setOnClickListener(this);
        }

        if (btnPost != null){
            btnPost.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnPre.getId()){
            onPre();
        }else if (v.getId() == btnPost.getId()){
            onPost();
        }
    }


    private void onPre(){
       showInfo( getAllChildren(wrapper).size() );
    }

    private void onPost(){
        if (viewInflated == null) {
            viewInflated = vsLore.inflate();
            inflateViewAfterViewStub();
            inflateViewAfterViewStub2();
        }
        onPre();
    }

    private void showInfo(int num_views){
        tvInfo.setText(getString(R.string.NUM_OF_VIEWS, num_views));
    }

    private void inflateViewAfterViewStub(){
        if (viewInflated != null){
            tv1 = (TextView) viewInflated.findViewById(R.id.tv1);
            tv2 = (TextView) viewInflated.findViewById(R.id.tv2);
            tv3 = (TextView) viewInflated.findViewById(R.id.tv3);
        }
    }

    private void inflateViewAfterViewStub2(){
        if (viewInflated != null){
            tv1 = (TextView) findViewById(R.id.tv1);
            tv2 = (TextView) findViewById(R.id.tv2);
            tv3 = (TextView) findViewById(R.id.tv3);
        }
    }



    /**
     * Código código de :
     * http://stackoverflow.com/questions/18668897/android-get-all-children-elements-of-a-viewgroup
     * @param v
     * @return
     */
    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }
}
