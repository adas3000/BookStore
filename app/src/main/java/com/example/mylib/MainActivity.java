package com.example.mylib;

import android.annotation.TargetApi;


import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.mylib.Data.Shelv_Type;
import com.example.mylib.sql.SqlManager;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;


import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO add user nickname functionality

        SqlManager.init(getApplicationContext());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllBooksFragment()).commit();
            navigationView.setCheckedItem(R.id.mylib_allbooks);
        }
    }

    @TargetApi(21)
    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f != null) {

                if (f instanceof AddBookFragment) {
                    ((AddBookFragment) f).onBackPressed();
                    return;
                } else if (f instanceof AllBooksFragment) {
                    SqlManager.getInstance().onClose();
                    finishAndRemoveTask();
                } else if (f instanceof SingleBookFragment) {
                    ((SingleBookFragment) f).onBackPressed();
                    return;
                }

            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mylib_allbooks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllBooksFragment()).addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.mylib_allbooks);
                break;
            case R.id.mylib_addbook:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddBookFragment()).addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.mylib_addbook);
                break;
            case R.id.AllUserBooks:

                navigationView.setCheckedItem(R.id.AllUserBooks);
                break;
            case R.id.FinishedBooks:
                navigationView.setCheckedItem(R.id.FinishedBooks);
                break;
            case R.id.NowReadingBooks:
                navigationView.setCheckedItem(R.id.NowReadingBooks);
                break;
            case R.id.WantToReadBooks:
                navigationView.setCheckedItem(R.id.WantToReadBooks);
                break;
            case R.id.HaveBooks:
                navigationView.setCheckedItem(R.id.HaveBooks);
                break;
            case R.id.FavorBooks:
                navigationView.setCheckedItem(R.id.FavorBooks);
                break;
        }
        //TODO navigation view not checking different items than mylib_allbooks and mylib_addbook ;fix it

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @TargetApi(21)
    @Override
    protected void onDestroy() {
        SqlManager.getInstance().onClose();
        finishAndRemoveTask();
        super.onDestroy();
    }

}

