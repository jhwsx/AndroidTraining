package com.wzc.g4_fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static com.wzc.g4_fragment.ArticleActivity.EXTRA_HEADLINE;

/**
 * @author wzc
 * @date 2018/7/23
 */
public class NewsReaderActivity extends AppCompatActivity
        implements HeadlinesFragment.OnHeadlineItemClickListener {

    private HeadlinesFragment mHeadlinesFragment;
    private ArticleFragment mArticleFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mHeadlinesFragment = (HeadlinesFragment) getSupportFragmentManager().findFragmentById(R.id.headlines);
        mArticleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article);

        View articleView = findViewById(R.id.article);
        if (articleView != null) {
            // two panes
            mArticleFragment.displayArticle("Headline0");
        }
    }

    @Override
    public void onHeadlineItemClicked(int position, String headline) {
        boolean hasTwopanes = getResources().getBoolean(R.bool.has_two_panes);
        if (hasTwopanes) {
            mArticleFragment.displayArticle(headline);
        } else {
            Intent starter = new Intent(NewsReaderActivity.this, ArticleActivity.class);
            starter.putExtra(EXTRA_HEADLINE, headline);
            startActivityForResult(starter, 1000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == 1000) {
            String response = data.getStringExtra(ArticleFragment.EXTRA_RESPONSE);
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        }

    }
}
