package com.example.admin.recyclerviewdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.admin.recyclerviewdemo.R;
import com.example.admin.recyclerviewdemo.adapter.MyAdapter;
import com.example.admin.recyclerviewdemo.adapter.OptimizeAdapter;
import com.example.admin.recyclerviewdemo.adapter.VBaseRecylerAdapter;
import com.example.admin.recyclerviewdemo.utils.DividerGridItemDecoration;
import com.example.admin.recyclerviewdemo.utils.DividerListItemDecoration;
import com.example.admin.recyclerviewdemo.utils.MyItemAnimator;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MyAdapter.onChildListener,View.OnClickListener{

    private RecyclerView myRecyclerVIew;
    private ArrayList<String> list;
    //    private OptimizeAdapter myAdapter;
    private MyAdapter myAdapter;
    private Button btn_list,btn_grid,btn_flow,btn_add,btn_del,btn_change,btn_delanim,btn_addanim,btn_changeanim,btn_refresh,btn_refreshanim,btn_drag_list,btn_drag_gird;
    private MyItemAnimator myItemAnimator;
    private DividerListItemDecoration dividerListItemDecoration;
    private DividerGridItemDecoration dividerGridItemDecoration;
    private Context mContent= MainActivity.this;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
//        myAdapter = new OptimizeAdapter(mContent,R.layout.item,list);
        myAdapter = new MyAdapter(mContent);
        myAdapter.setListener(this);
        myRecyclerVIew.setAdapter(myAdapter);
        myRecyclerVIew.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setmList(list);
//        myAdapter.setOnLongItemClickListener();
        initCtrl();

//        /**
//         * 添加分割线基本写法
//          * 这个方法是控件绘制之前的绘制方法，在这里绘制会比较灵活
//     */
//        myRecyclerVIew.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDraw(c, parent, state);
//                c.drawColor(Color.RED);
//            }
//
//            /**
//             *绘制前景
//             * @param c
//             * @param parent
//             * @param state
//             */
//            @Override
//            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDrawOver(c, parent, state);
////                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image);
////                c.drawBitmap(bitmap,parent.getWidth()/2-bitmap.getWidth()/2,parent.getHeight()/2-bitmap.getHeight(),null);
//            }
//
//            /**
//             * 设置分割线
//             * @param outRect
//             * @param view
//             * @param parent
//             * @param state
//             */
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                //参数（左上右下）
//                int position = parent.getChildAdapterPosition(view);
//                //outRect.set(5,5*position,5,5*position);
//                outRect.set(5,5,5,5);
//            }
//        });
        //工具类使用于List方式
        dividerListItemDecoration = new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST);
        dividerGridItemDecoration = new DividerGridItemDecoration(this);
        myRecyclerVIew.addItemDecoration(dividerListItemDecoration);
//        myDeviderDecoration = new MyDeviderDecoration(this);
//          myRecyclerVIew.addItemDecoration(myDeviderDecoration);
    }

    private void initView() {
        btn_drag_list = (Button) findViewById(R.id.btn_drag_list);
        btn_drag_gird = (Button) findViewById(R.id.btn_drag_gird);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//      textRefresh = (TextView) findViewById(R.id.textRefresh);
        myRecyclerVIew = ((RecyclerView) findViewById(R.id.myRecyclerView));
        btn_list = ((Button) findViewById(R.id.btn_list));
        btn_grid = ((Button) findViewById(R.id.btn_grid));
        btn_flow = ((Button) findViewById(R.id.btn_flow));
        btn_refresh = (Button) findViewById(R.id.refresh);
//        btn_add = ((Button) findViewById(R.id.btn_add));
//        btn_del = ((Button) findViewById(R.id.btn_del));
//        btn_change = ((Button) findViewById(R.id.btn_change));
        btn_delanim = ((Button) findViewById(R.id.btn_delanim));
        btn_addanim = ((Button) findViewById(R.id.btn_addanim));
        btn_changeanim = ((Button) findViewById(R.id.btn_changeanim));
        btn_refreshanim = ((Button) findViewById(R.id.btn_refreshanim));
//        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        initData();
                        myAdapter.setmList(list);
                        myAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i%3==0) {
                list.add(String.format(Locale.CHINA,"第%03d条数据%s",i,"~~~~~加宽~~~~~~~~~~~~~"));
            }else{
                list.add(String.format(Locale.CHINA,"第%03d条数据",i));
            }
        }
    }

    private void initCtrl(){
        btn_drag_list.setOnClickListener(this);
        btn_drag_gird.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
//        btn_add.setOnClickListener(this);
//        btn_del.setOnClickListener(this);
//        btn_change.setOnClickListener(this);
        btn_delanim.setOnClickListener(this);
        btn_addanim.setOnClickListener(this);
        btn_changeanim.setOnClickListener(this);
        btn_refreshanim.setOnClickListener(this);

//        myAdapter.setOnItemLongClickListener(new VBaseRecylerAdapter.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, int dataIndex) {
//                String s = list.get(dataIndex);
//                Toast.makeText(mContent,s+"被你长按",Toast.LENGTH_LONG).show();
//            }
//        });

      /*  //item的点击事件
        myAdapter.setOnItemClickListener(new OptimizeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String s = list.get(position);
                Toast.makeText(mContent,s+"被你点击",Toast.LENGTH_LONG).show();
            }
        });
        //item的长按事件
        myAdapter.setOnLongItemClickListener(new OptimizeAdapter.OnRecyclerViewLongItemClickListener(){
            @Override
            public void onLongItemClick(View view, int position) {
                String s = list.get(position);
                Toast.makeText(mContent,s+"被你长按",Toast.LENGTH_LONG).show();
            }
        });*/
    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this,"点击了第"+position+"个条目",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(int position) {
        Toast.makeText(this,"点击了第"+position+"个条目的图片",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_list:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);
                myRecyclerVIew.setLayoutManager(linearLayoutManager);
//                myRecyclerVIew.addItemDecoration(myDeviderDecoration);
                myRecyclerVIew.removeItemDecoration(dividerGridItemDecoration);
                myRecyclerVIew.addItemDecoration(dividerListItemDecoration);
                break;
            case R.id.btn_grid:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
                //设置跨行或者跨列的数目
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position==0) {
                            return 1;
                        }else if(position==1){
                            return 1;
                        }
                        return 1;
                    }
                });
                myRecyclerVIew.setLayoutManager(gridLayoutManager);
//                myRecyclerVIew.removeItemDecoration(myDeviderDecoration);
                myRecyclerVIew.removeItemDecoration(dividerListItemDecoration);
                myRecyclerVIew.addItemDecoration(dividerGridItemDecoration);
                break;
            case R.id.btn_flow:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                myRecyclerVIew.setLayoutManager(staggeredGridLayoutManager);
                break;
            case R.id.refresh:    //单独刷新第1条数据
                myAdapter.notifyItemChanged(0);
                Toast.makeText(this,"单独刷新第一条",Toast.LENGTH_LONG).show();
                break;
//            case R.id.btn_add:
//                myAdapter.Add(0,"新添加的数据");
//                myRecyclerVIew.scrollToPosition(0);
//                break;
//            case R.id.btn_del:
//                myAdapter.Remove(0);
//                break;
//            case R.id.btn_change:
//                myAdapter.Change(0,"更改后的数据");
//                break;
            case R.id.btn_drag_list:
                startActivity(new Intent(mContent,ListDragMenuActivity.class));
                break;
            case R.id.btn_drag_gird:
                startActivity(new Intent(mContent,GridDragMenuActivity.class));
                break;
            case R.id.btn_addanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setAddDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                myAdapter.Add(0,"新添加的数据");
                myRecyclerVIew.scrollToPosition(0);
                break;
            case R.id.btn_delanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setRemoveDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                myAdapter.Remove(0);
                break;
            case R.id.btn_changeanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setChangeDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                myAdapter.Change(0,"更改后的数据");
                break;
            case R.id.btn_refreshanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setChangeDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                myAdapter.notifyItemChanged(0);
                Toast.makeText(this,"单独刷新第一条",Toast.LENGTH_LONG).show();
                break;
        }
    }
}

