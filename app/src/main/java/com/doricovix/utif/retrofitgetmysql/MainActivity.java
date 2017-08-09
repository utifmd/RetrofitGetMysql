package com.doricovix.utif.retrofitgetmysql;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_scc;
    private RecyclerView.LayoutManager lo_mng;
    private SccAdapter adapter;
    private List<ItemScc> itemScc = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        rv_scc = (RecyclerView) findViewById(R.id.rv_scc);

        lo_mng = new LinearLayoutManager(this);
        adapter = new SccAdapter(this, itemScc);

        rv_scc.setLayoutManager(lo_mng);
        rv_scc.setAdapter(adapter);

        loadDataFromServer();
    }

    private void loadDataFromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReqInterface reqInterface = retrofit.create(ReqInterface.class);

        ItemRequest request = new ItemRequest();
        request.setOperation(Constants.OP_FETCH_SCC);

        Call<ItemResponse> call = reqInterface.operation(request);
        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                ItemResponse resp = response.body();
                String result = resp.getResult();
                String msg = resp.getMessage();

                itemScc = resp.getItemScc();

                adapter = new SccAdapter(MainActivity.this, itemScc);
                rv_scc.setAdapter(adapter);

                if (result.equals(Constants.SUCCESS)){
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    Log.d("TAG", String.valueOf(resp.getItemScc()));
                }

            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.icon_scc);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        search(searchView);

        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
