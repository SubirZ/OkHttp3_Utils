package com.example.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.indianic.indianicapp.Indianic;
import com.example.R;
import java.util.ArrayList;

public class InvoiceListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private long mLastClickTime = 0;
    private int lastPosition = -1;
    private ListView lvInvoices;
    private RecyclerView recyclerView;
    private InvoicesAdapter invoicesAdapter;
    private WsInvoiceService invoiceAsyncTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invoices, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getDAta();
    }

    public void initView(View view) {
        invoicesAdapter = new InvoicesAdapter(getActivity());
        lvInvoices = (ListView) view.findViewById(R.id.fragment_invoices_listView);
        lvInvoices.setAdapter(invoicesAdapter);

        //Initializing viewPager
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        lvInvoices.setOnItemClickListener(this);
    }

    private void setRecyclerData(final ResponseModel responseModel) {

        recyclerView.setLayoutManager(new CenterLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        //Creating our pager adapter
        final InvoiceHeaderAdapter adapter = new InvoiceHeaderAdapter(getActivity(), invoiceResponseModel);
        recyclerView.setAdapter(adapter);

        final CenterLayoutManager linearLayoutManager = (CenterLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int lastVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (lastPosition == -1) {
                    lastPosition = lastVisibleItem;
                }
                Log.e("positionPPP", linearLayoutManager.findFirstCompletelyVisibleItemPosition() + "");
//                tvRightSubHeader.setText(getResources().getStringArray(R.array.array_invoices_header)[lastVisibleItem]);
                if (lastVisibleItem >= 0 && lastVisibleItem != lastPosition) {
                    lastPosition = lastVisibleItem;
                    invoicesAdapter.setCurrentPosition(lastVisibleItem);
                    invoicesAdapter.clearModel();
                    if (lastVisibleItem == 1) {
                        invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_CLOSE))));
                    } else if (lastVisibleItem == 2) {
                        invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_OVERDUE))));
                    } else {
                        invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_OVERDUE))));
                        invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_OPEN))));
                        invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_CLOSE))));
                    }
                    UtilMethods.animateItems(getActivity(), lvInvoices);
                    invoicesAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private class InvoiceListSorting implements Predicate<InvoicesModel> {
        String status = "";

        InvoiceListSorting(final String status) {
            this.status = status;
        }

        @Override
        public boolean apply(@Nullable InvoicesModel jobListModel) {
            return jobListModel.getStatus().equalsIgnoreCase(status);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            Log.e("ISClick", "Return");
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        // your code
    }

    /**
     * Method to start task
     */
    public void getDAta() {
        if (invoiceAsyncTask != null && invoiceAsyncTask.getStatus() == AsyncTask.Status.PENDING) {
            invoiceAsyncTask.execute();
        } else if (invoiceAsyncTask == null || invoiceAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            invoiceAsyncTask = new WsInvoiceService();
            invoiceAsyncTask.execute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (invoiceAsyncTask != null && invoiceAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            invoiceAsyncTask.cancel(true);
        }
    }

    private class WsInvoiceService extends AsyncTask<Void, Void, ResponseModel> {
        private WsInvoices wsInvoices;
        private ProgressDialog dialog;
        private String appKey = Constants.APP_KEY;
        private String pairKey = Constants.PAIR_KEY;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage(getString(R.string.loading_msg));
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected InvoiceResponseModel doInBackground(Void... params) {
            wsInvoices = new WsInvoices(getActivity());
            return wsInvoices.executeService(getActivity(), appKey, pairKey);
        }

        @Override
        protected void onPostExecute(ResponseModel responseModel) {
            super.onPostExecute(invoiceResponseModel);

            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
            }
            if (wsInvoices.isSuccess() && invoiceResponseModel != null) {
                invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_OVERDUE))));
                invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_CLOSE))));
                invoicesAdapter.addInvoice(new ArrayList<>(Collections2.filter(responseModel.getData(), new InvoiceListSorting(Constants.STATUS_OPEN))));

                UtilMethods.animateItems(getActivity(), lvInvoices);
                setRecyclerData(invoiceResponseModel);
            } else {
                showSnackbar(getView(), wsInvoices.getMessage());
            }
        }
    }
}
