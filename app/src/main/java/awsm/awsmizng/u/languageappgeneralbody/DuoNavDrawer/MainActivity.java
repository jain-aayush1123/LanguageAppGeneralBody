package awsm.awsmizng.u.languageappgeneralbody.DuoNavDrawer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;

import awsm.awsmizng.u.languageappgeneralbody.Fragments.AppCreditsFragment;
import awsm.awsmizng.u.languageappgeneralbody.Fragments.BlankFragment;
import awsm.awsmizng.u.languageappgeneralbody.Fragments.HomeFragment;
import awsm.awsmizng.u.languageappgeneralbody.Fragments.ProfileFragment;
import awsm.awsmizng.u.languageappgeneralbody.R;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    private ArrayList<String> mTitles = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));
        // Initialize the views
        mViewHolder = new ViewHolder();
        // Handle toolbar actions
        handleToolbar();
        // Handle menu actions
        handleMenu();
        // Handle drawer actions
        handleDrawer();
        // Show main fragment in container
        goToFragment(new HomeFragment(), false);
        mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));
        //TODO can play with footer view/ header view here
        View headerView = mViewHolder.mDuoMenuView.getHeaderView();
        View footerView = mViewHolder.mDuoMenuView.getFooterView();
        TextView textView = (TextView) headerView.findViewById(R.id.duo_view_header_text_title);
        textView.setText("title");
        TextView textView2 = (TextView) headerView.findViewById(R.id.duo_view_header_text_sub_title);
        textView2.setText("subtitle");
    }
    
    private void handleToolbar() {
        setSupportActionBar(mViewHolder.mToolbar);
    }
    
    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();
    }
    
    private void handleMenu() {
        mMenuAdapter = new MenuAdapter(mTitles);
        mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }
    
    @Override
    public void onFooterClicked() {
        Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onHeaderClicked() {
        goToFragment(new ProfileFragment(), false);
    }
    
    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment).commit();
    }
    
    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));
        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);
        // Navigate to the right fragment
        switch (position) {
            case 0:
                goToFragment(new HomeFragment(), false);
                break;
            case 1:
                goToFragment(new ProfileFragment(), false);
                break;
            case 2:
                goToFragment(new AppCreditsFragment(), false);
                break;
            default:
                goToFragment(new BlankFragment(), false);
                break;
        }
        // Close the drawer
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }
    
    private class ViewHolder {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;
        private Toolbar mToolbar;
        
        ViewHolder() {
            mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        }
    }
}
