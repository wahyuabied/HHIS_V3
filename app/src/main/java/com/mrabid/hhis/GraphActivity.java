package com.mrabid.hhis;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.Modal.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphActivity extends AppCompatActivity {

    BarChart grafik;
    ProgressDialog progressDialog;
    ArrayList<IBarDataSet>dataset;
    Toolbar toolbar;
    Gson gson;
    RequestQueue requestQueue;
    SharedPref sharedPref;
    Graph isiGrafik ;
    Spinner rangeBulan;
    TextView judul;
    Long rangeBulanS;
    String url;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_person_login_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Disease's Graph");
        toolbar.setTitleTextColor(Color.WHITE);

        judul = (TextView)findViewById(R.id.tv_title_graph);
        rangeBulan = (Spinner)findViewById(R.id.spn_bulan_graph);
        button = (Button)findViewById(R.id.btn_bulan_graph);

        grafik = (BarChart)findViewById(R.id.graph_graph);
        progressDialog=new ProgressDialog(GraphActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.setCanceledOnTouchOutside(false);

        rangeBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rangeBulanS = parent.getItemIdAtPosition(position);
                if(rangeBulanS==0){
                    url = AppConfig.GRAPH1MONTH;
                }else if(rangeBulanS==1){
                    url = AppConfig.GRAPH3MONTHS;
                }else{
                    url = AppConfig.GRAPHALL;
                }
                Toast.makeText(GraphActivity.this, url, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul.setVisibility(View.VISIBLE);
                getData();
            }
        });

    }

    public void getData(){
        progressDialog = ProgressDialog.show(GraphActivity.this,"Please wait...","loading...",false,false);
        requestQueue = Volley.newRequestQueue(GraphActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String id;
        id = sharedPref.loadData("id");

        StringRequest postRequest = new StringRequest(Request.Method.GET, url+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        ResponseGraph posts = new ResponseGraph();
                        try {
                            posts = gson.fromJson(response, ResponseGraph.class);
                        } catch (Exception e) {
                            posts.setStatus("error");
                        }
                        if (posts.getStatus().equalsIgnoreCase("sukses")) {
                            if(posts.getData().getId_pasien()+""== ""){
                                Toast.makeText(GraphActivity.this, "Anda tidak memiliki riwayat penyakit", Toast.LENGTH_SHORT).show();
                            }else {
                                isiGrafik = new Graph(posts.getData().getId_pasien(),posts.getData().getJumlah(),posts.getData().getNama());
                                Log.d("Response",isiGrafik.getJumlah().get(0)+"");
                            }

                            ArrayList <BarEntry> barEntries = new ArrayList<>();
                            final ArrayList<String>getXAxisValues = new ArrayList<>();

                            for(int i=0;i<isiGrafik.getJumlah().size();i++){
                                barEntries.add(new BarEntry(i,isiGrafik.getJumlah().get(i).floatValue()));
                                getXAxisValues.add(isiGrafik.getNama().get(i).toString());
                            }
                            BarDataSet barDataSet = new BarDataSet(barEntries, "Health Graph");
                            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                            dataset = new ArrayList<>();
                            dataset.add(barDataSet);
                            BarData data = new BarData(dataset);

                            XAxis xAxis = grafik.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.TOP);
                            xAxis.setValueFormatter(new IAxisValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, AxisBase axis) {
                                    return getXAxisValues.get((int)value);
                                }
                            });
                            grafik.setData(data);
                            grafik.invalidate();

                        } else {
                            Toast.makeText(GraphActivity.this, "Maaf server sedang dalam masalah", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.toString());
                        Toast.makeText(GraphActivity.this, "Cek paket data anda", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        requestQueue.add(postRequest);
    }


    public class ResponseGraph{
        private String status;
        private Graph data;

        public ResponseGraph(String status, Graph data) {
            this.status = status;
            this.data = data;
        }

        public ResponseGraph() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Graph getData() {
            return data;
        }

        public void setData(Graph data) {
            this.data = data;
        }
    }
}
