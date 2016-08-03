package com.feicuiedu.gitdroid.github.hotrepo.repolist;

import com.feicuiedu.gitdroid.github.hotrepo.Language;
import com.feicuiedu.gitdroid.github.hotrepo.repolist.modle.Repo;
import com.feicuiedu.gitdroid.github.hotrepo.repolist.modle.ReposResult;
import com.feicuiedu.gitdroid.github.hotrepo.repolist.view.RepoListView;
import com.feicuiedu.gitdroid.github.network.GitHubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepoListPresenter {
    // 视图的接口
    private RepoListView repoListView;
    private int nextPage = 0;
    private Language language;

    private Call<ReposResult> repoCall;

    public RepoListPresenter(RepoListView repoListView, Language language) {
        this.repoListView = repoListView;
        this.language = language;
    }

    // 下拉刷新处理
    public void refresh() {
        // 隐藏loadmore
        repoListView.hideLoadMoreLoading();
        repoListView.showContentView();
        nextPage = 1; // 永远刷新最新数据
        repoCall = GitHubClient.getInstance().searchRepos(
                "language:" + language.getPath()
                , nextPage);
        repoCall.enqueue(repoCallback);
    }

    // 加载更多处理
    public void loadMore() {
        repoListView.showLoadMoreLoading();
        repoCall = GitHubClient.getInstance().searchRepos(
                "language:" + language.getPath()
                , nextPage);
        repoCall.enqueue(loadMoreCallback);
    }

    private final Callback<ReposResult> loadMoreCallback = new Callback<ReposResult>(){

        @Override public void onResponse(Call<ReposResult> call, Response<ReposResult> response) {
            repoListView.hideLoadMoreLoading();
            // 得到响应结果
            ReposResult reposResult = response.body();
            if (reposResult == null) {
                repoListView.showLoadMoreErro("结果为空!");
                return;
            }
            // 取出当前语言下的所有仓库
            List<Repo> repoList = reposResult.getRepoList();
            repoListView.addMoreData(repoList);
            nextPage++;
        }

        @Override public void onFailure(Call<ReposResult> call, Throwable t) {
            // 视图停止刷新
            repoListView.hideLoadMoreLoading();
            repoListView.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };

    private final Callback<ReposResult> repoCallback = new Callback<ReposResult>() {
        @Override public void onResponse(Call<ReposResult> call, Response<ReposResult> response) {
            // 视图停止刷新
            repoListView.stopRefresh();
            // 得到响应结果
            ReposResult reposResult = response.body();
            if (reposResult == null) {
                repoListView.showErrorView("结果为空!");
                return;
            }
            // 当前搜索的语言,没有仓库
            if (reposResult.getTotalCount() <= 0) {
                repoListView.refreshData(null);
                repoListView.showEmptyView();
                return;
            }
            // 取出当前语言下的所有仓库
            List<Repo> repoList = reposResult.getRepoList();
            repoListView.refreshData(repoList);
            // 下拉刷新成功(1), 下一面则更新为2
            nextPage = 2;
        }

        @Override public void onFailure(Call<ReposResult> call, Throwable t) {
            // 视图停止刷新
            repoListView.stopRefresh();
            repoListView.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };
}
