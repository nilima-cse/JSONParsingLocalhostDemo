package com.android.nilima.jsonparsinglocalhostdemo;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowCashBookActivity extends ListActivity {

    private String URL_ITEMS = "http://10.0.2.2/localwebsite/society/cashbook.php";
    private static final String TAG_CASHBOOK = "cashbook";
    private static final String TAG_RECORDNO = "recordno";
    private static final String TAG_DATE = "dater";
    private static final String TAG_OPEN_BAL = "open_bal";
    private static final String TAG_SHARE = "sharemoney";
    JSONArray cashBookArr = null;
    ArrayList<HashMap<String, String>> cashbookList = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cash_book);

        new CashBook().execute();
    }
    private class CashBook extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... arg) {
            JSONParser jsonParser = new JSONParser();
            Log.d("url: ", "> " + URL_ITEMS);
            //String json = serviceClient.makeHttpRequest(URL_ITEMS, JSONParser.GET);
            JSONObject json = jsonParser.makeHttpRequest(URL_ITEMS,JSONParser.GET);

            String jo=json.toString();

            // print the json response in the log
            //Log.d("Get match fixture response", "> " + json);
            if (jo != null) {
                try {
                    Log.d("try", "in the try");
                    JSONObject jsonObj = new JSONObject(jo);
                    Log.d("jsonObject", "new json Object");
                    // Getting JSON Array node
                    cashBookArr = jsonObj.getJSONArray(TAG_CASHBOOK);
                    Log.d("json aray", "user point array");
                    int len = cashBookArr.length();
                    Log.d("len", "get array length");
                    for (int i = 0; i < cashBookArr.length(); i++) {
                        JSONObject c = cashBookArr.getJSONObject(i);
                        String recordNo = c.getString(TAG_RECORDNO);
                        Log.d("recordno", recordNo);
                        String open_bal = c.getString(TAG_OPEN_BAL);
                        Log.d("open_bal", open_bal);
                        String date = c.getString(TAG_DATE);
                        Log.d("dater", date);
                        String sharemoney = c.getString(TAG_SHARE);
                        Log.d("sharemoney", date);
                        //  hashmap for single match
                        HashMap<String, String> cashMap = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        cashMap.put(TAG_RECORDNO, recordNo);
                        cashMap.put(TAG_OPEN_BAL, open_bal);
                        cashMap.put(TAG_DATE, date);
                        cashMap.put(TAG_SHARE, sharemoney);
                        cashbookList.add(cashMap);
                    }
                }
                catch (JSONException e) {
                    Log.d("catch", "in the catch");
                    e.printStackTrace();
                }
            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(
                    ShowCashBookActivity.this, cashbookList,
                    R.layout.list_item, new String[] {
                    TAG_RECORDNO, TAG_OPEN_BAL,TAG_DATE,TAG_SHARE
            }
                    , new int[] {
                    R.id.recordno,R.id.balance,
                    R.id.date,R.id.share
            }
            );
            setListAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_cash_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
