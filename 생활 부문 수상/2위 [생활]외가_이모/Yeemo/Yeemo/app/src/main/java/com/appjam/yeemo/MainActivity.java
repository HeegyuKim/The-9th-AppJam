package com.appjam.yeemo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton menu1, menu2, menu3;
    RelativeLayout   topLinear, bottomLinear, middleLinear;
    ImageView alice;
    private static final String IMAGEVIEW_TAG = "드래그 이미지";

    Point p;

    EditText editTitle, editSubtitle;

    DBManager dbMgr;
    SQLiteDatabase db;
    Cursor cursor;

    ArrayList<String> titleArr = new ArrayList<String>();
    ArrayList<String> subtitleArr = new ArrayList<String>();

    public void GetDBInformation() {  //db 안에 있는 데이터들 불러오기
        try{

            dbMgr = new DBManager(this);
            db = dbMgr.getReadableDatabase();
            cursor = db.query("IncreaseYeemo", null, "type is not null", null,
                        null, null, "type asc");

            while(cursor.moveToNext())
            {
                int view = cursor.getInt(cursor.getColumnIndex("type"));
                int time = cursor.getInt(cursor.getColumnIndex("time"));
                int x = cursor.getInt(cursor.getColumnIndex("x"));
                int y = cursor.getInt(cursor.getColumnIndex("y"));
                String dtitle = cursor.getString(cursor.getColumnIndex("title"));
                String dsubtitle = cursor.getString(cursor.getColumnIndex("subTitle"));

                titleArr.add(dtitle);
                subtitleArr.add(dsubtitle);

                title = dtitle;
                subtitle = dsubtitle;
                ImageView img = new ImageView(MainActivity.this);
                img.setImageResource(R.drawable.aaa);
                img.setX(x);
                img.setY(y);

                switch (view)
                {
                    case 1:
                        topLinear.addView(img, x, y);
                        break;
                    case 2:

                        middleLinear.addView(img, x, y);
                        break;
                    case 3:
                        bottomLinear.addView(img, x, y);
                        break;
                }
               /* ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) img.getLayoutParams();
                params.width = 200;
                params.height = 200;*
                img.setLayoutParams(params);*/
                img.setTag(IMAGEVIEW_TAG);
                img.setOnLongClickListener(longListener);
                img.setOnClickListener(listenerHeart);

            }

        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    //앨리스를 누르면, 리스트 뷰
    public void startList(View v)
    {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("titleArr", titleArr);
        intent.putExtra("subtitleArr", subtitleArr);
        startActivity(intent);
    }

    public void GetIntent(Intent intent)
    {
        Toast.makeText(this, "불러옵니다", Toast.LENGTH_SHORT).show();
        int type= intent.getIntExtra("type", 0);
        int time = intent.getIntExtra("time", 0);
        int x = intent.getIntExtra("x", 0);
        int y = intent.getIntExtra("y", 0);
        String dtitle = intent.getStringExtra("title");
        String dsubtitle = intent.getStringExtra("subtitle");

        title = dtitle;
        subtitle = dsubtitle;

        ImageView img = new ImageView(MainActivity.this);
        img.setImageResource(R.drawable.aaa);

        img.setX(x);
        img.setY(y);

        switch(type)
        {
            case 1:
                topLinear.addView(img, x, y);
                break;
            case 2:
                middleLinear.addView(img, x, y);
                break;
            case 3:
                bottomLinear.addView(img, x, y);
                break;
        }


        /*ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) img.getLayoutParams();
        params.width = 200;
        params.height = 200;
        img.setLayoutParams(params);*/
        img.setTag(IMAGEVIEW_TAG);
        img.setOnLongClickListener(longListener);
        img.setOnClickListener(listenerHeart);
        title = dtitle;
        subtitle = dsubtitle;

        dbMgr = new DBManager(getApplicationContext());
        db = dbMgr.getWritableDatabase();

        //디비에 넣을 값들을 저장하는 ContentValues
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("subTitle",subtitle);   //속성 이름, 값
        cv.put("time", time);
        cv.put("x", x);
        cv.put("y", y);
        cv.put("type", type);

        db.insert("IncreaseYeemo", null, cv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //middleLinear = (LinearLayout)findViewById(R.id.middlelinear);
        topLinear = (RelativeLayout)findViewById(R.id.toplinear);
        topLinear.setOnDragListener(new DragListener());
        middleLinear = (RelativeLayout)findViewById(R.id.middlelayout);
        middleLinear.setOnDragListener(new DragListener());
        bottomLinear = (RelativeLayout)findViewById(R.id.bottomlinear);
        bottomLinear.setOnDragListener(new DragListener());
        alice = (ImageView)findViewById(R.id.alice);
        alice.setOnDragListener(new DragListener());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu1 = (FloatingActionButton)findViewById(R.id.menu_item1);
        menu2 = (FloatingActionButton)findViewById(R.id.menu_item2);
        menu3 = (FloatingActionButton)findViewById(R.id.menu_item3);
        menu1.setOnClickListener(listener);
        menu2.setOnClickListener(listener);
        menu3.setOnClickListener(listener);
        //final FloatingActionMenu menuMultipleActions = (FloatingActionMenu) findViewById(R.id.multiple_actions);

        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("title")+"", Toast.LENGTH_SHORT).show();
        if(intent.getStringExtra("title") != null)
            GetIntent(intent);
        GetDBInformation();


    }

    public void startSetting(View v)
    {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
        //v.getHitRect(Rect);
    }

    EditText edit;
    View.OnClickListener listenerHeart = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int[] location = new int[2];
            ImageView img = (ImageView)view;
            img.getLocationOnScreen(location);

            p = new Point();
            p.x = location[0];
            p.y = location[1];
            showPopup(MainActivity.this, p);
            Toast.makeText(MainActivity.this, p.x+"", Toast.LENGTH_SHORT).show();
        }
    };

    String title="", subtitle="";
    int id;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            id = view.getId();

            LayoutInflater li = LayoutInflater.from(MainActivity.this);
            final View someLayout = (LinearLayout)li.inflate(R.layout.custom_dialog, null);


            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setView(someLayout);
            alert.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //메모 추가
                    editTitle = (EditText) someLayout.findViewById(R.id.edit_title);
                    editSubtitle = (EditText) someLayout.findViewById(R.id.edit_subtitle);
                    ImageView img = new ImageView(MainActivity.this);
                    img.setImageResource(R.drawable.aaa);

                    int x = (int) (Math.random() * (bottomLinear.getWidth() - 200));
                    int y = (int) (Math.random() * (bottomLinear.getHeight() - 200));
                    img.setX(x);
                    img.setY(y);
                    int time=0;
                    int view=0;
                    switch (id) {
                        case R.id.menu_item1:
                            time = 1; //1분마다
                            view = 1;
                            topLinear.addView(img, x, y);
                            break;
                        case R.id.menu_item2:
                            time = 5;
                            view = 2;
                            middleLinear.addView(img, x, y);
                            break;
                        case R.id.menu_item3:
                            time = 10;
                            view = 3;
                            bottomLinear.addView(img, x, y);
                            break;
                    }
                    ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) img.getLayoutParams();
                    params.width = 200;
                    params.height = 200;
                    img.setLayoutParams(params);
                    img.setTag(IMAGEVIEW_TAG);
                    img.setOnLongClickListener(longListener);
                    img.setOnClickListener(listenerHeart);
                    title = editTitle.getText().toString();
                    subtitle = editSubtitle.getText().toString();

                    dbMgr = new DBManager(getApplicationContext());
                    db = dbMgr.getWritableDatabase();

                    //디비에 넣을 값들을 저장하는 ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("title", title);
                    cv.put("subTitle",subtitle);   //속성 이름, 값
                    cv.put("time", time);
                    cv.put("x", x);
                    cv.put("y", y);
                    cv.put("type", view);

                    db.insert("IncreaseYeemo", null, cv);
                    Toast.makeText(MainActivity.this, "저장", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                }
            });

            alert.show();

        }
    };

    //팝업
    private void showPopup(Context context, Point p) {
        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout)findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_window, viewGroup);

        TextView tv1 = (TextView)layout.findViewById(R.id.textView1);
        TextView tv2 = (TextView)layout.findViewById(R.id.textView2);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setFocusable(true);
        popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
//        popup.showAsDropDown(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
        tv1.setText(title);
        tv2.setText(subtitle);

    }


    private View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {

            // 태그 생성
            ClipData.Item item = new ClipData.Item(
                    (CharSequence) view.getTag());

            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(),
                    mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);
//
            //           view.startDrag(data, // data to be dragged
//                    shadowBuilder, // drag shadow
//                    view, // 드래그 드랍할  Vew
//                    0 // 필요없은 플래그
//            );

            view.startDrag(data, // data to be dragged
                    new CanvasShadow(view, (int)view.getX(), (int)view.getY()), // drag shadow
                    view, // 드래그 드랍할  Vew
                    0 // 필요없은 플래그
            );
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    };

    class CanvasShadow extends View.DragShadowBuilder {
        int mWidth, mHeight;
        int mX, mY;
        public CanvasShadow(View v, int x, int y) {
            super(v);
            mWidth = v.getWidth();
            mHeight = v.getHeight();
            mX = x;
            mY = y;
        }
    }

    float preX, preY;
    int preV;
    int preTime;
    class DragListener implements View.OnDragListener {
        //        Drawable normalShape = getResources().getDrawable(
//                R.drawable.view1);

        public boolean onDrag(View v, DragEvent event) {

            // 이벤트 시작
            switch (event.getAction()) {

                // 이미지를 드래그 시작될때
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d("DragClickListener", "ACTION_DRAG_STARTED");
                    preX = event.getX();
                    preY = event.getY();
                    switch(v.getId())
                    {
                        case R.id.menu_item1:
                            preTime = 1;
                            preV = 1;
                            break;
                        case R.id.menu_item2:
                            preTime = 5;
                            preV = 2;
                            break;
                        case R.id.menu_item3:
                            preTime = 10;
                            preV = 3;
                            break;
                    }
//                    normalShape = v.getBackground();
                    break;

                // 드래그한 이미지를 옮길려는 지역으로 들어왔을때
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENTERED");
                    // 이미지가 들어왔다는 것을 알려주기 위해 배경이미지 변경
//                    v.setBackground(targetShape);
                    break;

                // 드래그한 이미지가 영역을 빠져 나갈때
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("DragClickListener", "ACTION_DRAG_EXITED");
//                    v.setBackground(normalShape);
                    break;

                // 이미지를 드래그해서 드랍시켰을때
                case DragEvent.ACTION_DROP:
                    Log.d("DragClickListener", "ACTION_DROP");
                    /*if (v == findViewById(R.id.alice)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        *//*LinearLayout containView = (LinearLayout) v;
                        containView.addView(view);
                        view.setX(event.getX());
                        view.setY(event.getY());*//*

                        //containView.addView(view);
                        //view.setVisibility(View.VISIBLE);

                    }else */
                    if(v == findViewById(R.id.alice))
                    {
                        //delete
                        //String query = "delete from IncreaseYeemo where title='"+title+"'";
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);
                        Toast.makeText(MainActivity.this, "삭제", Toast.LENGTH_SHORT).show();

                        dbMgr = new DBManager(getApplicationContext());
                        db = dbMgr.getWritableDatabase();
                        db.execSQL("delete from IncreaseYeemo where title='"+title+"';");

                        //디비에 넣을 값들을 저장하는 ContentValues
                        ContentValues cv = new ContentValues();
                        cv.put("title", title);
                        cv.put("subTitle",subtitle);   //속성 이름, 값
                        cv.put("time", preTime);
                        cv.put("x", preX);
                        cv.put("y", preY);
                        cv.put("type", preV);

                        db.insert("DeleteYeemo", null, cv);
                        db.close();
                        dbMgr.close();

                    }
                    else if (v == findViewById(R.id.middlelayout)) {
                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view
                                .getParent();
                        viewgroup.removeView(view);

                        RelativeLayout containView = (RelativeLayout) v;
                        containView.addView(view);
                        view.setX(event.getX());
                        view.setY(event.getY());
                        view.setVisibility(View.VISIBLE);
                    }else if (v == findViewById(R.id.bottomlinear)) {

                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        RelativeLayout containView = (RelativeLayout) v;
                        containView.addView(view);
                        view.setX(event.getX());
                        view.setY(event.getY());
                        view.setVisibility(View.VISIBLE);


                    }else if(v == (findViewById(R.id.toplinear))){

                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        RelativeLayout containView = (RelativeLayout) v;
                        containView.addView(view);
                        view.setX(event.getX());
                        view.setY(event.getY());
                        view.setVisibility(View.VISIBLE);

                        break;
                    }
                   /* View view = (View)event.getLocalState();
                    ViewGroup parent = (ViewGroup)view.getParent();
                    parent.removeView(view);
                    LinearLayout newparent = (LinearLayout)v;
                    newparent.addView(view);
                    view.setX(event.getX());
                    view.setY(event.getY());
                    view.setVisibility(View.VISIBLE);*/
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED");

                default:
                    break;
            }
            return true;
        }
    }


}
