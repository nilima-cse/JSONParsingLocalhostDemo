package com.android.nilima.jsonparsinglocalhostdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CashBookActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputRecord;
    EditText inputBalance;
    EditText inputShare;
    EditText inputDate;
    EditText inputFixedBlnc;
    EditText inputParticulars;
    EditText inputLedgerfolio;
    String record_no,balance,date,share,fixedBlnc,particulars,ledgerfolio;

    // url to create new product
    private static String url_cash_book = "http://10.0.2.2/society/cashbook.php";


    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        // Edit Text
        inputRecord = (EditText) findViewById(R.id.inputName);
        inputBalance = (EditText) findViewById(R.id.inputPrice);
        inputDate = (EditText) findViewById(R.id.inputDesc);
        inputShare = (EditText) findViewById(R.id.inputCreate);
        inputFixedBlnc = (EditText) findViewById(R.id.inputUpdate);
        inputParticulars = (EditText) findViewById(R.id.inputParticulars);
        inputLedgerfolio = (EditText) findViewById(R.id.inputLedgerfolio);


        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                record_no = inputRecord.getText().toString();
                balance = inputBalance.getText().toString();
                date = inputDate.getText().toString();
                share=inputShare.getText().toString();
                fixedBlnc=inputFixedBlnc.getText().toString();
                particulars=inputParticulars.getText().toString();
                ledgerfolio=inputLedgerfolio.getText().toString();
                System.out.print(record_no+balance+date+share+fixedBlnc+particulars+ledgerfolio);
                Toast.makeText(CashBookActivity.this, record_no + balance + date + share + fixedBlnc+particulars+ledgerfolio, Toast.LENGTH_LONG).show();
                new CreateNewProduct().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CashBookActivity.this);
            pDialog.setMessage("Creating Cashbook..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            jsonParser=new JSONParser();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("recordno", record_no));
            params.add(new BasicNameValuePair("open_bal", balance));
            params.add(new BasicNameValuePair("dater", date));
            params.add(new BasicNameValuePair("sharemoney", share));
            params.add(new BasicNameValuePair("fixed", fixedBlnc));
            params.add(new BasicNameValuePair("particulars_r", particulars));
            params.add(new BasicNameValuePair("ledgerfolio", ledgerfolio));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_cash_book,
                    JSONParser.POST, params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(CashBookActivity.this, MainActivity.class);
                   startActivity(i);

                    // closing this screen
                     finish();
                } else {
                    // failed to create product
                    Toast.makeText(getApplicationContext(),"Failed to Create",Toast.LENGTH_LONG).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}