package com.booksharing.community.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.booksharing.community.R;
import com.booksharing.community.fragments.AboutFragment;
import com.booksharing.community.fragments.HomeFragment;
import com.booksharing.community.fragments.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * Main Activity - Entry point of the application
 * Manages Bottom Navigation and Fragment transactions
 * 
 * @author RANA MUHAMMAD AWAIS
 * @email rana.16241.ac@iqra.edu.pk
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Bottom Navigation View
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default fragment (Home)
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // Bottom Navigation Item Selected Listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.nav_upload) {
                    selectedFragment = new UploadFragment();
                } else if (itemId == R.id.nav_about) {
                    selectedFragment = new AboutFragment();
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Load fragment into the container
     * 
     * @param fragment Fragment to be loaded
     */
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Navigate to specific fragment programmatically
     * Used by FAB in HomeFragment to navigate to Upload
     * 
     * @param fragment Fragment to navigate to
     */
    public void navigateToFragment(Fragment fragment) {
        loadFragment(fragment);
        
        // Update bottom navigation selection
        if (fragment instanceof UploadFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_upload);
        } else if (fragment instanceof HomeFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else if (fragment instanceof AboutFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_about);
        }
    }
}
