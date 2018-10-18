package com.example.indus.businesscard.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.indus.businesscard.Const;
import com.example.indus.businesscard.R;
import com.example.indus.businesscard.data.DataUtils;
import com.example.indus.businesscard.data.NewsItem;

import java.text.SimpleDateFormat;
import java.util.List;

public class NewsDetailsActivity extends AppCompatActivity {

    private List<NewsItem> news;

    private ImageView detailsPhoto;
    private TextView detailsTitle;
    private TextView detailsPublishedDate;
    private TextView detailsOverviewText;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        initView();
        createToolbar();
        setViewData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ////todo сделать возврат
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        detailsPhoto = findViewById(R.id.details_photo);
        detailsTitle = findViewById(R.id.details_title);
        detailsPublishedDate = findViewById(R.id.details_published_date);
        detailsOverviewText = findViewById(R.id.details_overview);
    }

    private void createToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setViewData(){
        news = DataUtils.generateNews();
        Intent intent = getIntent();
        int newsId = intent.getIntExtra(Const.NEWS_ID, -1);

        Glide
                .with(this)
                .load(news.get(newsId).getImageUrl())
                .into(detailsPhoto);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Const.DATE_FORMAT);
        detailsPublishedDate.setText(dateFormat.format(news.get(newsId).getPublishDate()));
        detailsTitle.setText(news.get(newsId).getTitle());
        detailsOverviewText.setText(news.get(newsId).getFullText());

    }
}
