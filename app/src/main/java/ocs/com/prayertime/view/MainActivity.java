package ocs.com.prayertime.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ocs.com.prayertime.R;
import ocs.com.prayertime.viewmodel.PrayerAPI;
import ocs.com.prayertime.viewmodel.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import ocs.com.prayertime.model.Timings;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.fajrTime)
    TextView fajrTV;
    @BindView(R.id.dhuhrTime)
    TextView dhuhrTV;
    @BindView(R.id.asrTime)
    TextView asrTV;
    @BindView(R.id.maghrebTime)
    TextView maghrebTV;
    @BindView(R.id.ishaTime)
    TextView ishaTV;

    @BindView(R.id.country_edit_text)
    EditText countryET;
    @BindView(R.id.city_edit_text)
    EditText cityET;

    @BindView(R.id.submit_button)
    Button submitBtn;

    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    List<Timings> prayerList;
    @Inject
    ViewModel viewModel;

    PrayerAPI prayerAPI;

    String curDate;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitComponents();

        //SetButtonListener();

        SetCalendarListener();
    }

    public void InitComponents() {

        ButterKnife.bind(this);

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        curDate = sdf.format(date.getTime());
        ((MyApplication) getApplication()).getMainComponent().inject(MainActivity.this);

        prayerAPI = viewModel.getPrayerAPI();
    }


    @OnClick(R.id.submit_button)
    void submit() {
        if (countryET != null && cityET != null && viewModel.IsCountryAvailable(countryET.getText().toString())) {
            if (!countryET.getText().toString().equals("") && !countryET.getText().toString().equals("")) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);

                viewModel.getTimings(cityET.getText().toString(), countryET.getText().toString()).subscribe(new SingleObserver<Timings>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Timings timings) {

                        prayerList.add(timings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "Subscriber error!", Toast.LENGTH_SHORT).show();
                    }
                });


                if (prayerList != null && curDate != null) {
                    SetETsByDate(Integer.parseInt(curDate));
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

//    @OnClick(R.id.submit_button)
//    void submit() {
//        if (countryET != null && cityET != null && viewModel.IsCountryAvailable(countryET.getText().toString())) {
//            if (!countryET.getText().toString().equals("") && !countryET.getText().toString().equals("")) {
//
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
//
//
//                viewModel.getTimings(cityET.getText().toString(), countryET.getText().toString()).enqueue(new Callback<Timings>() {
//                    @Override
//                    public void onResponse(Call<Timings> call, Response<Timings> response) {
//                        Log.v("response", "Response success");
//
//
//
//                        prayerList = (List) response.body();
//                        if (prayerList != null && curDate != null) {
//                            SetETsByDate(Integer.parseInt(curDate));
//                        } else {
//                            Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Timings> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//
//            }
//        }
//
//    }


    public void SetCalendarListener() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, final int dayOfMonth) {
                if (prayerList != null)
                    SetETsByDate(dayOfMonth);
            }
        });
    }


    public void SetETsByDate(int n) {
        n -= 1;
        fajrTV.setText(prayerList.get(n).getFajr());
        dhuhrTV.setText(prayerList.get(n).getDhuhr());
        asrTV.setText(prayerList.get(n).getAsr());
        maghrebTV.setText(prayerList.get(n).getMaghrib());
        ishaTV.setText(prayerList.get(n).getIsha());
    }

}
