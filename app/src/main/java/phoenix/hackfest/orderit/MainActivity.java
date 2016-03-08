package phoenix.hackfest.orderit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import phoenix.hackfest.orderit.Fragments.AboutUs;
import phoenix.hackfest.orderit.Fragments.MyKart;
import phoenix.hackfest.orderit.HelperClasses.Constants;
import phoenix.hackfest.orderit.Services.RegistrationIntentService;
import phoenix.ism.hackfest.orderit.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private boolean isReceiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, NavigationDrawerActivity.class));
        finish();

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPreferences.getString(Constants.QuickstartPreferences.REGISTRATION_ID,"");
        Boolean regToServer = sharedPreferences.getBoolean(Constants.QuickstartPreferences.SENT_TOKEN_TO_SERVER,false);

        if(token.isEmpty() || !regToServer)
        {
            Intent i=new Intent(this, RegistrationIntentService.class);
            startService(i);
        }


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OrderDetails.class));

            }
        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager profile = getFragmentManager();
        FragmentTransaction ft = profile.beginTransaction();
        if (id == R.id.name) {
            ft.replace(R.id.main, new Name());
            ft.commit();


        } else if (id == R.id.mkart) {

            ft.replace(R.id.main, new MyKart());
            ft.commit();


        } else if (id == R.id.abtus) {
            ft.replace(R.id.main, new AboutUs());
            ft.commit();


        } else if (id == R.id.notf) {
            ft.replace(R.id.main, new Notification());
            ft.commit();
        }
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.closeDrawer(GravityCompat.START);
            return true;
    }



    @Override
    protected void onResume() {
        super.onResume();
//        registerReceiver();
    }

//    @Override
//    protected void onPause() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
//        isReceiverRegistered = false;
//        super.onPause();
//    }
//
//
//    private void registerReceiver(){
//        if(!isReceiverRegistered) {
//            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                    new IntentFilter(Constants.QuickstartPreferences.REGISTRATION_COMPLETE));
//            isReceiverRegistered = true;
//        }
//    }


    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
