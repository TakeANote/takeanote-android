package takeanote.takeanote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.orm.SugarContext;

import butterknife.ButterKnife;
import takeanote.takeanote.R;
import takeanote.takeanote.activity.interaction.INoteInteraction;
import takeanote.takeanote.fragment.FriendListFragment;
import takeanote.takeanote.fragment.NoteListFragment;
import takeanote.takeanote.fragment.SharedNoteListFragment;
import takeanote.takeanote.model.Document;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, INoteInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SugarContext.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NoteListFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
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
        Fragment fragment = null;

        if (id == R.id.nav_notes) {
            fragment = new NoteListFragment();
        } else if (id == R.id.nav_shared_notes) {
            fragment = new SharedNoteListFragment();
        } else if (id == R.id.nav_record_notes) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_friends) {
            fragment = new FriendListFragment();
        } else if (id == R.id.nav_logout) {

        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onNoteSelected(Document document) {
        Intent intent = new Intent(this, DocActivity.class);
        Bundle b = new Bundle();
        b.putLong(DocActivity.DOCUMENT_TAG, document.getId());
        intent.putExtras(b);
        startActivity(intent);
    }
}
