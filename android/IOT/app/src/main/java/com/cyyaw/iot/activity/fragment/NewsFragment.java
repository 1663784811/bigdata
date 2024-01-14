package com.cyyaw.iot.activity.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyyaw.iot.R;
import com.cyyaw.iot.activity.adapter.NewsAdapter;

import java.util.HashMap;


public class NewsFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int pageNum = 1;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        recyclerView = mRootView.findViewById(R.id.recyclerView);
//        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
    }

    @Override
    protected void initData() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(newsAdapter);
//        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Serializable obj) {
////                showToast("点击");
//                NewsEntity newsEntity = (NewsEntity) obj;
//                String url = "http://192.168.31.32:8089/newsDetail?title=" + newsEntity.getAuthorName();
//                Bundle bundle = new Bundle();
//                bundle.putString("url", url);
//                navigateToWithBundle(WebActivity.class, bundle);
//            }
//        });
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                pageNum = 1;
//                getNewsList(true);
//            }
//        });
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                pageNum++;
//                getNewsList(false);
//            }
//        });
        getNewsList(true);
    }

    private void getNewsList(final boolean isRefresh) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
//        params.put("limit", ApiConfig.PAGE_SIZE);
//        Api.config(ApiConfig.NEWS_LIST, params).getRequest(getActivity(), new TtitCallback() {
//            @Override
//            public void onSuccess(final String res) {
//                if (isRefresh) {
//                    refreshLayout.finishRefresh(true);
//                } else {
//                    refreshLayout.finishLoadMore(true);
//                }
//                NewsListResponse response = new Gson().fromJson(res, NewsListResponse.class);
//                if (response != null && response.getCode() == 0) {
//                    List<NewsEntity> list = response.getPage().getList();
//                    if (list != null && list.size() > 0) {
//                        if (isRefresh) {
//                            datas = list;
//                        } else {
//                            datas.addAll(list);
//                        }
//                        mHandler.sendEmptyMessage(0);
//                    } else {
//                        if (isRefresh) {
//                            showToastSync("暂时无数据");
//                        } else {
//                            showToastSync("没有更多数据");
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                if (isRefresh) {
//                    refreshLayout.finishRefresh(true);
//                } else {
//                    refreshLayout.finishLoadMore(true);
//                }
//            }
//        });
    }
}