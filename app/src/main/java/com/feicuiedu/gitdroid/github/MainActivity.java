package com.feicuiedu.gitdroid.github;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.commons.ActivityUtils;
import com.feicuiedu.gitdroid.github.hotrepo.HotRepoFragment;
import com.feicuiedu.gitdroid.github.hotuser.HotUserFragment;
import com.feicuiedu.gitdroid.github.login.LoginActivity;
import com.feicuiedu.gitdroid.github.login.UserRepo;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ��ǰӦ����ҳ��
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout; // ����(��������+�໬�˵�)
    @BindView(R.id.navigationView) NavigationView navigationView; // �໬�˵���ͼ

    // ���Ųֿ�Fragment
    private HotRepoFragment hotRepoFragment;
    private HotUserFragment hotUserFragment;

    private Button btnLogin;
    private ImageView ivIcon;

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        // ���õ�ǰ��ͼ(Ҳ����˵�������˵�ǰ��ͼ����,������onContentChanged��������)
        setContentView(R.layout.activity_main);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        // ActionBar����
        setSupportActionBar(toolbar);
        // ����navigationView�ļ�����
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(// ��������ļ���
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();// ����drawerlayoutͬ���䵱ǰ״̬
        // ���ó������
        drawerLayout.setDrawerListener(toggle);
        btnLogin = ButterKnife.findById(navigationView.getHeaderView(0), R.id.btnLogin);
        ivIcon = ButterKnife.findById(navigationView.getHeaderView(0), R.id.ivIcon);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                activityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });
        // Ĭ����ʾ�������Ųֿ�fragment
        hotRepoFragment = new HotRepoFragment();
        replaceFragment(hotRepoFragment);
    }

    @Override protected void onStart() {
        super.onStart();
        // û����Ȩ�Ļ�
        if (UserRepo.isEmpty()) {
            btnLogin.setText(R.string.login_github);
            return;
        }
        btnLogin.setText(R.string.switch_account);
        // ����Title
        getSupportActionBar().setTitle(UserRepo.getUser().getName());
        // �����û�ͷ��
        String photoUrl = UserRepo.getUser().getAvatar();
        ImageLoader.getInstance().displayImage(photoUrl, ivIcon);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    // �໬�˵�������
    @Override public boolean onNavigationItemSelected(MenuItem item) {
        // ��Ĭ��ѡ����ֶ�������Ϊfalse
        if (item.isChecked()) {
            item.setChecked(false);
        }
        // ����ѡ�����л�
        switch (item.getItemId()) {
            // ���Ųֿ�
            case R.id.github_hot_repo:
                if (!hotRepoFragment.isAdded()) {
                    replaceFragment(hotRepoFragment);
                }
                break;
            case R.id.github_hot_coder:
                if (hotUserFragment == null) hotUserFragment = new HotUserFragment();
                if (!hotUserFragment.isAdded()) {
                    replaceFragment(hotUserFragment);
                }
                break;
        }
        // �ر�drawerLayout
        drawerLayout.post(new Runnable() {
            @Override public void run() {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        // ����true�������ò˵����Ϊchecked״̬
        return true;
    }

}