package cn.gxh.toolbardemo;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private ImageView mIvSearch;
    private SearchView.SearchAutoComplete mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        /*// getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 显示导航按钮
        mToolBar.setNavigationIcon(android.R.drawable.ic_delete); // 设置导航按钮

        mToolBar.setLogo(R.mipmap.ic_launcher);          // 设置logo图片
        mToolBar.setTitle("ToolBar Title");              // 设置标题
        //代码设置TiTle不起作用，得在清单文件label
        mToolBar.setTitleTextColor(Color.RED);           // 设置标题颜色
        mToolBar.setSubtitle("This is subtitle");        // 设置子标题*/

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(MainActivity.this,"navigationIcon监听",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        //SearchView
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        initSearchView(searchView);
        return true;
    }

    private void initSearchView(SearchView searchView) {
    //设置该页面一显示就把SearchView显示出来
            //searchView.setIconified(false);
            //设置该页面一显示就把SearchView显示出来并且不被隐藏
            // searchView.setIconifiedByDefault(false);

       /* mIvSearch = (ImageView) searchView.findViewById(R.id.search_go_btn);
        mIvSearch.setImageResource(R.drawable.back);
        mIvSearch.setVisibility(View.VISIBLE);*/
        searchView.setSubmitButtonEnabled(true);

        mEditText = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        mEditText.setHint("请输入要搜索的商品名称");
        mEditText.setTextColor(Color.GRAY);

        //监听焦点改变
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        //关闭监听
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this,"close",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //监听文本变化
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,"submit---"+query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this,newText,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
