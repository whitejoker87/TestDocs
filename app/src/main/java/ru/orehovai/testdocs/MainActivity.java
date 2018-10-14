package ru.orehovai.testdocs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ProgressBar progressBar;

    //private MainContract.Presenter presenter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerViewFastAccess;

    private List<Doc> allDocs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allDocs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Doc doc = new Doc();
            doc.setName("документ");
            doc.setDate("1.1.1.1");
            doc.setSize("много");
            allDocs.add(doc);

            setContentView(R.layout.activity_main);

            initialize();
            initProgressBar();

            //presenter = new MainPresenterImpl(this, new GetNoticeIntractorImpl());
            //presenter.requestDataFromServer();


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);


        }
    }

    public List<Doc> getAllDocs() {
        return allDocs;
    }

    private void initialize() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new DocsFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        recyclerViewFastAccess = findViewById(R.id.recycler_fast_access_docs);
        recyclerViewFastAccess.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFastAccess.setAdapter(new FastAccessRecyclerViewAdapter(this, allDocs, recyclerItemClickListener));
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.VISIBLE);

        this.addContentView(relativeLayout, params);
    }


    /**
     * RecyclerItem click event listener
     */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Doc doc) {

            Toast.makeText(MainActivity.this,
                    "List title:  " + doc.getName(),
                    Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(List<Doc> noticeArrayList) {

        FastAccessRecyclerViewAdapter adapter = new FastAccessRecyclerViewAdapter(this, noticeArrayList, recyclerItemClickListener);
        recyclerViewFastAccess.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //presenter.onDestroy();
    }
}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_refresh) {
//            Presenter.onRefreshButtonClick();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


