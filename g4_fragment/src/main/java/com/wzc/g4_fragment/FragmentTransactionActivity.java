package com.wzc.g4_fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

/**
 * @author wzc
 * @date 2018/7/25
 */
public class FragmentTransactionActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;
    private CountingFragment mAddFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transaction_activity);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.cb);
        Button btnBeginTransaction = (Button) findViewById(R.id.button_begin_transaction);
        Button btnAdd = (Button) findViewById(R.id.button_add);
        Button btnRemove = (Button) findViewById(R.id.button_remove);
        Button btnReplace = (Button) findViewById(R.id.button_replace);
        Button btnHide = (Button) findViewById(R.id.button_hide);
        Button btnShow = (Button) findViewById(R.id.button_show);
        Button btnCommit = (Button) findViewById(R.id.button_commit);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.framelayout, CountingFragment.newInstance("FragmentTransaction Demo"))
                .commit();
        btnBeginTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddFragment = CountingFragment.newInstance("add");
                mFragmentTransaction
                        .add(R.id.framelayout, mAddFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction.remove(mAddFragment);
            }
        });

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction.replace(R.id.framelayout, CountingFragment.newInstance("replace"));
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction.hide(mAddFragment);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction.show(mAddFragment);
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    mFragmentTransaction.addToBackStack(null);
                }
                mFragmentTransaction.commit();
            }
        });
    }

    public static class CountingFragment extends Fragment {
        private static final String TAG = CountingFragment.class.getSimpleName();
        private TextView mTextView;
        private static final String ARGS_LEVEL = "args_level";
        private int mRgb;

        public static CountingFragment newInstance(String level) {

            Bundle args = new Bundle();
            args.putString(ARGS_LEVEL, level);
            CountingFragment fragment = new CountingFragment();
            fragment.setArguments(args);
            return fragment;
        }

        private String mLevel;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (getArguments() != null) {
                mLevel = getArguments().getString(ARGS_LEVEL);
            }
            Log.d(TAG, mLevel +" "+  "onAttach: ");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, mLevel +" "+ "onCreate: ");

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, mLevel +" "+  "onCreateView: ");
            return inflater.inflate(R.layout.counting_fragment, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, mLevel +" "+  "onActivityCreated: ");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, mLevel +" "+  "onStart: ");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG, mLevel +" "+  "onResume: ");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, mLevel +" "+  "onPause: ");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, mLevel +" "+ "onStop: ");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, mLevel +" "+ "onDestroyView: ");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, mLevel +" "+  "onDestroy: ");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d(TAG, mLevel +" "+ "onDetach: ");
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG, mLevel +" "+ "onViewCreated: ");
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.cl);
            // rock the color
            mRgb = Color.rgb(new Random().nextInt(256),
                    new Random().nextInt(256),
                    new Random().nextInt(256));

            if (savedInstanceState != null) {
                mRgb = savedInstanceState.getInt("color");
                Log.d(TAG, "onViewCreated: restore color = " + mRgb);
            }
            constraintLayout.setBackgroundColor(mRgb);
            mTextView = (TextView) view.findViewById(R.id.textView3);
            mTextView.setText("Fragment #" + mLevel);
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("color", mRgb);
            Log.d(TAG, mLevel +" "+ "onSaveInstanceState: save color = " + mRgb);
        }
    }
}
