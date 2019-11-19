package com.wzc.g3_intent;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_ALBUM = 1;
    String[] arr = {
            "share image from drawable file",
            "share image from album",
            "share text",
            "action test",
            "category test",
            "data test 1",
            "data test 2",
            "data test 3",
            "alarm clock",
            "set timer",
            "show all alarms",
            "Perform a web search"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                        String uriString = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null);
                        Uri uri = Uri.parse(uriString);
                        intent.putExtra(Intent.EXTRA_STREAM, uri);
                        Intent intentChooser = Intent.createChooser(intent, "Share image from drawable");
                        if (getPackageManager().resolveActivity(intentChooser, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                            startActivity(intentChooser);
                        }
                        break;
                    }
                    case 1: {
                        Intent albumIntent = new Intent(Intent.ACTION_PICK);
                        albumIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        if (albumIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(albumIntent, REQUEST_ALBUM);
                        }
                        break;
                    }
                    case 2: {
                        Intent textIntent = new Intent(Intent.ACTION_SEND);
                        textIntent.setType("text/plain");
                        textIntent.putExtra(Intent.EXTRA_TEXT, "Play around fully with Android.");
//        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
//            startActivity(intent);
//        }

                        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(textIntent, PackageManager.MATCH_ALL);
                        if (resolveInfos != null && !resolveInfos.isEmpty()) {
                            for (int i = 0; i < resolveInfos.size(); i++) {
                                ResolveInfo resolveInfo = resolveInfos.get(i);
                                if (TextUtils.equals(resolveInfo.activityInfo.applicationInfo.packageName, getPackageName())) {
                                    Intent intent1 = new Intent(textIntent);
                                    intent1.setComponent(new ComponentName(getPackageName(), "com.wzc.g3_intent.ReceiveTextActivity"));
                                    startActivity(intent1);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 3: {
                        Intent actionTestIntent = new Intent();
                        actionTestIntent.setAction("com.wzc.g3_intent.ACTION_TEST");
                        startActivity(actionTestIntent);
                        break;
                    }
                    case 4: {
                        Intent categoryTestIntent = new Intent();
                        categoryTestIntent.setAction("com.wzc.g3_intent.ACTION_TEST_4");
                        categoryTestIntent.addCategory("com.wzc.g3_intent.CATEGORY_TEST");

                        // android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.wzc.g3_intent.ACTION_TEST_4 cat=[com.wzc.g3_intent.CATEGORY_XXX] }
//                        categoryTestIntent.addCategory("com.wzc.g3_intent.CATEGORY_XXX");
                        startActivity(categoryTestIntent);
                        break;
                    }
                    case 5: {
                        // no URI, only mime type test
                        Intent dataTest1Intent = new Intent();
                        dataTest1Intent.setAction("com.wzc.g3_intent.ACTION_TEST_5");
                        dataTest1Intent.setType("text/plain");
                        startActivity(dataTest1Intent);
                        break;
                    }

                    case 6: {
                        // no MIME type , only URI test
                        Intent dataTest2Intent = new Intent();
                        dataTest2Intent.setAction("com.wzc.g3_intent.ACTION_TEST_6");
                        String uriString2 = "wzc.datatest2://com.wzc.datatest2.host/test2";
                        Uri parse = Uri.parse(uriString2);
                        dataTest2Intent.setData(parse);
                        startActivity(dataTest2Intent);
                        break;
                    }

                    case 7: {
                        // both URI and MIME type
                        Intent dataTest3Intent = new Intent();
                        dataTest3Intent.setAction("com.wzc.g3_intent.ACTION_TEST_7");
                        String uriString3 = "wzc.datatest3://com.wzc.datatest3.host:8888/test3";
                        Uri parse = Uri.parse(uriString3);
                        dataTest3Intent.setDataAndType(parse, "text/plain");
                        startActivity(dataTest3Intent);
                        break;
                    }
                    case 8: {
                        /*NEED <uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>*/
                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                                .putExtra(AlarmClock.EXTRA_MESSAGE, "wake up, lazy worm")
                                .putExtra(AlarmClock.EXTRA_HOUR, 8)
                                .putExtra(AlarmClock.EXTRA_MINUTES, 00);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        break;
                    }
                    case 9: {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "fire shenzhou rocket");
                            intent.putExtra(AlarmClock.EXTRA_LENGTH, 10);
                            intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "only support above api 19", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }

                    case 10:{
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "only support above api 19", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case 11:{
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, "dying to survive");
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                        break;
                    }
                    default:
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_ALBUM) {
            Uri uri = data.getData();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(intent);
        }
    }

}
