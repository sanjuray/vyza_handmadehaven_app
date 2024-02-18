package com.example.handicrafts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.os.Bundle;
import android.view.MenuItem;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
>>>>>>> c5a9c4168f40145528d0b8d166cdc55d768efe4e

import com.example.handicrafts.categories.catFragment;
import com.example.handicrafts.fav.favFragment;
import com.example.handicrafts.home.homefragment;
<<<<<<< HEAD
import com.example.handicrafts.profile.accountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
=======
import com.example.handicrafts.login.SignupPage;
import com.example.handicrafts.profile.accountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
>>>>>>> c5a9c4168f40145528d0b8d166cdc55d768efe4e
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    BottomNavigationView navigationView;
<<<<<<< HEAD

=======
    FloatingActionButton floatingActionButton;
>>>>>>> c5a9c4168f40145528d0b8d166cdc55d768efe4e
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
<<<<<<< HEAD

=======
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, SignupPage.class));
            }
        });
>>>>>>> c5a9c4168f40145528d0b8d166cdc55d768efe4e
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, new HomeFragment())
                    .commit();
        }
        navigationView=findViewById(R.id.bottom_nav);

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.item1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame, new homefragment())
                                .commit();
                        return true;
                     case R.id.item2:

                         getSupportFragmentManager().beginTransaction()
                                 .replace(R.id.frame, new favFragment())
                                 .commit();
                             return  true;

                    case R.id.item3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame, new catFragment())
                                .commit();
                        return true;

                    case R.id.item4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame, new accountFragment())
                                .commit();
                        return true;



                    default:
                        return false;
                }





            }
        });


    }

}



