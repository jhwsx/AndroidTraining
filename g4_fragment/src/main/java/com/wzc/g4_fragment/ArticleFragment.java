package com.wzc.g4_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * @author wzc
 * @date 2018/7/23
 */
public class ArticleFragment extends Fragment {
    private final static String ARGS_HEADLINE = "args_headline";
    private TextView mTextView;
    public static final String EXTRA_RESPONSE = "response";
    public static final int REQUEST_RATE_ARTICLE_CODE = 2;

    public static ArticleFragment newInstance(String headline) {

        Bundle args = new Bundle();
        args.putString(ARGS_HEADLINE, headline);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String mHeadline;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHeadline = getArguments().getString(ARGS_HEADLINE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.article_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = (TextView) view.findViewById(R.id.textView);
        TextView contextMenu = (TextView) view.findViewById(R.id.textView_context_menu);
        registerForContextMenu(contextMenu);
        mTextView.setText(mHeadline + "====Content" + "\n" + "You can click here to rate this article.");
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                RateArticleDialog rateArticleDialog = new RateArticleDialog();
                rateArticleDialog.setTargetFragment(ArticleFragment.this, REQUEST_RATE_ARTICLE_CODE);
                rateArticleDialog.show(fragmentManager, "dialog");
            }
        });
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESPONSE, "finish reading " + mHeadline);
        getActivity().setResult(RESULT_OK, intent);
    }

    public void displayArticle(String headline) {
        mTextView.setText(headline + "====Content");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == RateArticleDialog.REQUEST_FEEDBACK_RATE_CODE) {
            String rate = data.getStringExtra(RateArticleDialog.EXTRA_REPSONSE_RATE);
            Intent intent = new Intent();
            intent.putExtra(EXTRA_RESPONSE, "finish reading " + mHeadline + ", rate = " + rate);
            getActivity().setResult(RESULT_OK, intent);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.add(Menu.NONE, R.id.context_menu_1, Menu.NONE, "Like");
        menu.add(Menu.NONE, R.id.context_menu_2, Menu.NONE, "Share");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu_1:
                Toast.makeText(getActivity(), "Like", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.context_menu_2:
                Toast.makeText(getActivity(), "Share", Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return super.onContextItemSelected(item);
    }
}
