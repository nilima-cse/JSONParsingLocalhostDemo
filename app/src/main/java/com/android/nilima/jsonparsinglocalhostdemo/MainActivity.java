package com.android.nilima.jsonparsinglocalhostdemo;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
public class MainActivity extends Activity {

    Button cashBtn;

//    private String URL_ITEMS = "http://10.0.2.2/society/cashbook.php";
//    private static final String TAG_FIXTURE = "cashbook";
//    private static final String TAG_MATCHID = "recordno";
//    private static final String TAG_TEAMA = "open_bal";
//    private static final String TAG_TEAMB = "sharemoney";
//    private static final String TAG_TICKET = "p_ticket";
//    private static final String TAG_DATER = "dater";
//    private static final String TAG_PARTICULARS = "particulars_r";
//    private static final String TAG_LEDGER = "ledgerfolio";
//    private static final String TAG_NONRET = "non_ret";
//    private static final String TAG_OPTIONAL = "optional";
//    private static final String TAG_FIXED = "fixed";
//    private static final String TAG_COMMANDENT = "commandent_acc";
//    private static final String TAG_PRINCIPAL = "principal";

//    JSONArray matchFixture = null;
//    ArrayList<HashMap<String, String>> matchFixtureList = new ArrayList<HashMap<String, String>>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cashBtn=(Button)findViewById(R.id.cashBtn);


        cashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CashBookActivity.class);
                startActivity(intent);

            }
        });


        // Call Async task to get the match fixture
       // new GetFixture().execute();
    }
//    private class GetFixture extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//        @Override
//        protected Void doInBackground(Void... arg) {
//            ServiceHandler serviceClient = new ServiceHandler();
//            Log.d("url: ", "> " + URL_ITEMS);
//            String json = serviceClient.makeServiceCall(URL_ITEMS,ServiceHandler.GET);
//            // print the json response in the log
//            Log.d("Get match fixture", "> " + json);
//            if (json != null) {
//                try {
//                    Log.d("try", "in the try");
//                    JSONObject jsonObj = new JSONObject(json);
//                    Log.d("jsonObject", "new json Object");
//                    // Getting JSON Array node
//                    matchFixture = jsonObj.getJSONArray(TAG_FIXTURE);
//                    Log.d("json aray", "user point array");
//                    int len = matchFixture.length();
//                    Log.d("len", "get array length");
//                    for (int i = 0; i < matchFixture.length(); i++) {
//                        JSONObject c = matchFixture.getJSONObject(i);
//                        String matchId = c.getString(TAG_MATCHID);
//                        Log.d("matchId", matchId);
//                        String teamA = c.getString(TAG_TEAMA);
//                        Log.d("teamA", teamA);
//                        String teamB = c.getString(TAG_TEAMB);
//                        Log.d("teamB", teamB);
//                        //  hashmap for single match
//                        HashMap<String, String> matchFixture = new HashMap<String, String>();
//                        // adding each child node to HashMap key => value
//                        matchFixture.put(TAG_MATCHID, matchId);
//                        matchFixture.put(TAG_TEAMA, teamA);
//                        matchFixture.put(TAG_TEAMB, teamB);
//                        matchFixtureList.add(matchFixture);
//                    }
//                }
//                catch (JSONException e) {
//                    Log.d("catch", "in the catch");
//                    e.printStackTrace();
//                }
//            } else {
//                Log.e("JSON Data", "Didn't receive any data from server!");
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, matchFixtureList,
//                    R.layout.list_item, new String[] {
//                    TAG_MATCHID, TAG_TEAMA,TAG_TEAMB
//            }
//                    , new int[] {
//                    R.id.matchId,R.id.teamA,
//                    R.id.teamB
//            }
//            );
//            setListAdapter(adapter);
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
}