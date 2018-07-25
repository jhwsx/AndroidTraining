package com.wzc.g4_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * https://www.oodlestechnologies.com/blogs/Headless-Fragments-To-Handle-Orientation-Change-In-Android
 * @author wzc
 * @date 2018/7/23
 */
public class UsingHeadlessFragmentActivity extends AppCompatActivity {
    private static final String TAG = UsingHeadlessFragmentActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new UiFragment())
                    .commit();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public static class UiFragment extends Fragment {
        private static final String TAG = UiFragment.class.getSimpleName();
        private HeadlessFragment mHeadlessFragment;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.d(TAG, "onAttach: ");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: ");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            return inflater.inflate(R.layout.ui_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG, "onViewCreated: ");
            Button btnRestart = (Button) view.findViewById(R.id.btn_restart);
            btnRestart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHeadlessFragment.restart();
                }
            });
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated: ");
            FragmentManager fragmentManager = getFragmentManager();
            mHeadlessFragment = (HeadlessFragment) fragmentManager.findFragmentByTag("head");
            if (mHeadlessFragment == null) {
                mHeadlessFragment = new HeadlessFragment();
                mHeadlessFragment.setTargetFragment(this, 0);
                getFragmentManager().beginTransaction().add(mHeadlessFragment, "head").commit();
            }
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
    }

    public static class HeadlessFragment extends Fragment {
        private static final String TAG = HeadlessFragment.class.getSimpleName() + 1;
        private boolean mReady = false;
        private ProgressBar mProgressBar;
        private boolean mQuiting = false;
        private int mProgress;
        /**
         * This is the thread that will do our work.  It sits in a loop running
         * the progress up until it has reached the top, then stops and waits.
         */
        final Thread mThread = new Thread() {
            @Override
            public void run() {
                // We'll figure the real value out later.
                int max = 10000;

                // This thread runs almost forever.
                while (true) {

                    // Update our shared state with the UI.
                    synchronized (this) {
                        // Our thread is stopped if the UI is not ready
                        // or it has completed its work.
                        while (!mReady || mProgress >= max) {
                            if (mQuiting) {
                                return;
                            }
                            try {
                                wait();
                            } catch (InterruptedException e) {
                            }
                        }

                        // Now update the progress.  Note it is important that
                        // we touch the progress bar with the lock held, so it
                        // doesn't disappear on us.
                        mProgress++;
                        max = mProgressBar.getMax();
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("currThread", "run: " + mProgress);
                                    mProgressBar.setProgress(mProgress);
                                }
                            });
                        }
                    }

                    // Normally we would be doing some work, but put a kludge
                    // here to pretend like we are.
                    synchronized (this) {
                        try {
                            wait(50);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        };

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.d(TAG, "onAttach: ");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: ");
            // Control whether a fragment instance is retained across Activity
            // re-creation (such as from a configuration change)
            // 如果不添加这行代码, 那么在屏幕翻转时, 进度会重新开始
            setRetainInstance(true);

            mThread.start();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            return null;
        }


        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated: ");
            mProgressBar = (ProgressBar) getTargetFragment().getView().findViewById(R.id.progressBar);

            synchronized (mThread) {
                mReady = true;
                mThread.notify();
            }
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

        /**
         * This is called when the fragment is going away.  It is NOT called
         * when the fragment is being propagated between activity instances.
         */
        @Override
        public void onDestroy() {
            Log.d(TAG, "onDestroy: ");
            // Make the thread go away.
            synchronized (mThread) {
                mReady = false;
                mQuiting = true;
                mThread.notify();
            }

            super.onDestroy();
        }

        /**
         * This is called right before the fragment is detached from its
         * current activity instance.
         */
        @Override
        public void onDetach() {
            Log.d(TAG, "onDetach: ");
            // This fragment is being detached from its activity.  We need
            // to make sure its thread is not going to touch any activity
            // state after returning from this function.
            synchronized (mThread) {
                mProgressBar = null;
                mReady = false;
                mThread.notify();
            }

            super.onDetach();
        }

        /**
         * API for our UI to restart the progress thread.
         */
        public void restart() {
            synchronized (mThread) {
                mProgress = 0;
                mThread.notify();
            }
        }
    }
}
