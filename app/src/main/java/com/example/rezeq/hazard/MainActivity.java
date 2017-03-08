package com.example.rezeq.hazard;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView nameTextView;
    TextView emailTextView;
    TextView uidTextView;
    BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment newFragment;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (id){
                    case R.id.action_profile:
                        newFragment = new ProfileFragment();
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Log.i("frag","profile");
                        return true;
                    case R.id.action_groups:
                        newFragment = new GroupsFragment();
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Log.i("frag","groups");
                        return true;
                    case R.id.action_map:
                        newFragment = new MapFragment();
                        transaction.replace(R.id.fragment, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        Log.i("frag","map");
                        return true;
                    default:
                        return false;
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Intent i = new Intent(MainActivity.this , LoginActivity.class);
                    startActivity(i);
                }//else{
//                    nameTextView.setText(String.format("Name: %s",user.getDisplayName()));
//                    emailTextView.setText(String.format("Email: %s",user.getEmail()));
//                    uidTextView.setText(String.format("UUID: %s",user.getUid()));
                //}
            }
        };
    }

    public void logout(View view) {
        mAuth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.sign_out:
                mAuth.signOut();
                return true;
            default:
                return false;
        }
    }
}
