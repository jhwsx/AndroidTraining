package com.wzc.g4_fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * @author wzc
 * @date 2018/7/24
 */
public class StackActivity extends AppCompatActivity {
    int mStackLevel = 1;
    private FragmentManager mManager;
    private static final String TAG = StackActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_activity);
        mManager = getSupportFragmentManager();

        Button push = (Button) findViewById(R.id.push);
        final Button pop = (Button) findViewById(R.id.pop);
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinishing()) {
                    return;
                }
                mStackLevel++;
                mManager.beginTransaction()
                        .add(R.id.framelayout, CountingFragment.newInstance(mStackLevel))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();

            }
        });
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mManager.popBackStackImmediate()) {
                    mStackLevel = 1;
                } else {
                    Toast.makeText(StackActivity.this, "Well, nothing to pop!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState == null) {
            mManager.beginTransaction()
                    .add(R.id.framelayout, CountingFragment.newInstance(mStackLevel))
                    .commit();
        } else {
            mStackLevel = savedInstanceState.getInt("level");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static class CountingFragment extends Fragment {
        private static final String TAG = CountingFragment.class.getSimpleName();
        private TextView mTextView;
        private static final String ARGS_LEVEL = "args_level";
        private int mRgb;

        public static CountingFragment newInstance(int level) {

            Bundle args = new Bundle();
            args.putInt(ARGS_LEVEL, level);
            CountingFragment fragment = new CountingFragment();
            fragment.setArguments(args);
            return fragment;
        }

        private int mLevel;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.d(TAG, "onAttach: ");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: ");
            if (getArguments() != null) {
                mLevel = getArguments().getInt(ARGS_LEVEL);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            return inflater.inflate(R.layout.counting_fragment, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated: ");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "onStart: ");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG, "onResume: ");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, "onPause: ");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop: ");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView: ");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy: ");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d(TAG, "onDetach: ");
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG, "onViewCreated: ");
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
            Log.d(TAG, "onSaveInstanceState: save color = " + mRgb);
        }
    }

}
