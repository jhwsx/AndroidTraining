package com.example.t3_add_animations.screenslide;

import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by wzc on 2017/12/25.
 */

public class ZoomoutPageTransformer implements ViewPager.PageTransformer {


    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    /**
     * Apply a property transformation to the given page.
     *
     * @param view Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     *                 page position to the right, and -1 is one page position to the left.
     */
    @Override
    public void transformPage(View view, float position) {
        Log.d("ZoomoutPageTransformer", "view = " + view + ", position = " + position);
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            // 这里是为了让滑入屏幕的页及早出来,做的偏移
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA)); // [0.5,1]

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
//        从第一页滑到第二页
//        12-25 09:49:14.436 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ......ID 0,0-480,686 #7f0b0061 app:id/content}, position = -0.004166667
//        12-25 09:49:14.436 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ......ID 480,0-960,686 #7f0b0061 app:id/content}, position = 0.99583334
//        12-25 09:49:14.454 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.014583333
//        12-25 09:49:14.454 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.98541665
//        12-25 09:49:14.468 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.025
//        12-25 09:49:14.468 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.975
//        12-25 09:49:14.483 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.033333335
//        12-25 09:49:14.483 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.96666664
//        12-25 09:49:14.499 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.039583333
//        12-25 09:49:14.500 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.9604167
//        12-25 09:49:14.514 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.047916666
//        12-25 09:49:14.515 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.95208335
//        12-25 09:49:14.531 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.05625
//        12-25 09:49:14.531 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.94375
//        12-25 09:49:14.545 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.0625
//        12-25 09:49:14.547 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.9375
//
//        [ 12-25 09:49:14.554   322:23238 I/         ]
//        post waitpid (pid=23237) status=0000
//        12-25 09:49:14.561 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.07083333
//        12-25 09:49:14.562 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.9291667
//        12-25 09:49:14.581 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.077083334
//        12-25 09:49:14.581 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.92291665
//        12-25 09:49:14.593 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.083333336
//        12-25 09:49:14.594 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.9166667
//        12-25 09:49:14.610 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.08958333
//        12-25 09:49:14.611 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.91041666
//        12-25 09:49:14.626 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.09583333
//        12-25 09:49:14.627 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.90416664
//        12-25 09:49:14.643 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.104166664
//        12-25 09:49:14.644 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.8958333
//        12-25 09:49:14.656 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.110416666
//        12-25 09:49:14.658 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.88958335
//
//        [ 12-25 09:49:14.658   322:23240 I/         ]
//        post waitpid (pid=23239) status=0100
//        12-25 09:49:14.687 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.1125
//        12-25 09:49:14.687 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.8875
//        12-25 09:49:14.703 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.12291667
//        12-25 09:49:14.704 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.87708336
//        12-25 09:49:14.726 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.12708333
//        12-25 09:49:14.727 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.87291664
//        12-25 09:49:14.739 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.13333334
//        12-25 09:49:14.740 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.8666667
//        12-25 09:49:14.753 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.14166667
//        12-25 09:49:14.754 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.85833335
//        12-25 09:49:14.773 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.14791666
//        12-25 09:49:14.774 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.8520833
//        12-25 09:49:14.782 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.15416667
//        12-25 09:49:14.785 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.84583336
//        12-25 09:49:14.800 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.16041666
//        12-25 09:49:14.800 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.83958334
//        12-25 09:49:14.828 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.17291667
//        12-25 09:49:14.829 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.82708335
//        12-25 09:49:14.860 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.17708333
//        12-25 09:49:14.860 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.8229167
//        12-25 09:49:14.878 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.18958333
//        12-25 09:49:14.878 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.81041664
//        12-25 09:49:14.891 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.20416667
//        12-25 09:49:14.892 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.79583335
//        12-25 09:49:14.938 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.2375
//        12-25 09:49:14.939 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.7625
//        12-25 09:49:14.955 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.25625
//        12-25 09:49:14.955 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.74375
//        12-25 09:49:14.971 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.27291667
//        12-25 09:49:14.971 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.7270833
//        12-25 09:49:14.986 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.28958333
//        12-25 09:49:14.986 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.7104167
//        12-25 09:49:15.002 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.30208334
//        12-25 09:49:15.002 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.6979167
//        12-25 09:49:15.018 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.31875
//        12-25 09:49:15.019 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.68125
//        12-25 09:49:15.049 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.33541667
//        12-25 09:49:15.049 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.6645833
//        12-25 09:49:15.064 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.35
//        12-25 09:49:15.065 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.65
//        12-25 09:49:15.080 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.36458334
//        12-25 09:49:15.081 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.6354167
//        12-25 09:49:15.101 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.37708333
//        12-25 09:49:15.102 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.62291664
//        12-25 09:49:15.112 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.39166668
//        12-25 09:49:15.112 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.60833335
//        12-25 09:49:15.136 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.40416667
//        12-25 09:49:15.136 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.59583336
//        12-25 09:49:15.149 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.41875
//        12-25 09:49:15.150 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.58125
//        12-25 09:49:15.159 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.43541667
//        12-25 09:49:15.160 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.56458336
//        12-25 09:49:15.175 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.45208332
//        12-25 09:49:15.176 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.54791665
//        12-25 09:49:15.193 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.46875
//        12-25 09:49:15.193 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.53125
//        12-25 09:49:15.208 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.48541668
//        12-25 09:49:15.209 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.51458335
//        12-25 09:49:15.222 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.49583334
//        12-25 09:49:15.222 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.50416666
//        12-25 09:49:15.253 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.60833335
//        12-25 09:49:15.254 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.39166668
//        12-25 09:49:15.269 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.65
//        12-25 09:49:15.270 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.35
//        12-25 09:49:15.301 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{8e65907 VFED.V... ........ 0,0-480,686 #7f0b0061 app:id/content}, position = -0.7
//        12-25 09:49:15.301 22768-22768/com.example.t3_add_animations D/ZoomoutPageTransformer: view = android.widget.ScrollView{e94ae15 VFED.V... ........ 480,0-960,686 #7f0b0061 app:id/content}, position = 0.3

