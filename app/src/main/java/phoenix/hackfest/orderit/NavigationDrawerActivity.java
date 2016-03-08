package phoenix.hackfest.orderit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import phoenix.hackfest.orderit.Fragments.MyKart;
import phoenix.hackfest.orderit.Fragments.UserDetailsFragment;
import phoenix.hackfest.orderit.Models.User;
import phoenix.ism.hackfest.orderit.R;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = "NavigationDrawerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation_drawer);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationDrawerActivity.this, OrderDetails.class));
                finish();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        User user=User.getUser(this);
        View headerLayout = navigationView.getHeaderView(0);
        ((TextView)headerLayout.findViewById(R.id.userName)).setText(user.getName());
        ((TextView)headerLayout.findViewById(R.id.userEmail)).setText(user.getEmail());

        displayFragment(new MyKart(), false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.profile){
            displayFragment(new UserDetailsFragment(),true);
        }else if(id==R.id.mkart){
            displayFragment(new MyKart(), true);
        }else if(id==R.id.splitIt){
            displayFragment(new MyKart(), true);
        }
//        else if(id != R.id.notf){
//
//        }
        else{
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayFragment(Fragment fragment, boolean add) {
        Log.e(TAG, "display fragment");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (add) {
            transaction.addToBackStack(null);
        }
//        transaction.setCustomAnimations(enterAnim,exitAnim);
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

}
