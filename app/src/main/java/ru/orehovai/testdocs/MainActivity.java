package ru.orehovai.testdocs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
            doc.setTitle("документ");
            doc.setDate("1.1.1.1");
            doc.setSize("много");
            allDocs.add(doc);

            setContentView(R.layout.activity_main);

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

            viewPager = findViewById(R.id.pager);
            viewPager.setAdapter(new DocsFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
            tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            recyclerViewFastAccess = findViewById(R.id.recycler_fast_access_docs);
            recyclerViewFastAccess.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerViewFastAccess.setAdapter(new FastAccessRecyclerViewAdapter(this, allDocs));
        }
    }

    public List<Doc> getAllDocs() {
        return allDocs;
    }
}
