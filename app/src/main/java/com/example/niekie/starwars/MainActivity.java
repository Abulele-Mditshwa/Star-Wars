package com.example.niekie.starwars;

/*
This class is basically the splash screen.
So it waits until all the data is  pulled from the API then it
opens the new Layout activity.
 */

//These are used for the background process
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;


// these classes are used to fetch the data from JSON server
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//These are the JSON classes
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import static com.example.niekie.starwars.ListViewActivity.*;


public class MainActivity extends AppCompatActivity {


    public  static  String JSONdata; // the data that will be passed.
    public static String NewHope,Phantom_Menice,Revenge_of_Sith; // the title of the movies
    public static String dateNewHope, datePhantom_Menice,dateRevenge_of_Sith; // these are our dates that will be received

    public static ProgressBar bar;
    public static String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar)findViewById(R.id.progressBar);
        bar.setVisibility(View.VISIBLE);

                    doThis bgp = new doThis();
                    bgp.runMethod(); // runs everything
    }//end method onCreate



    //----------------------------------------------------------------------------------------------------------------------------------------------------------------p--------------------------


    public class doThis extends AsyncTask<Void,Integer,Void> {

         public String data = "";




        // this method deals with what must be done before the task is executed
        @Override
        protected void onPreExecute() {

            Toast.makeText(getApplicationContext(), "Downloading !", Toast.LENGTH_LONG).show();
            super.onPreExecute();
            bar.setProgress(0); // the initial value//
            bar.setVisibility(View.VISIBLE);
            bar.setMax(100); // this the maximum value we can load our data

        }//end PreExecute()


        // informs us of the progress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate();
            bar.getProgress();
        }//end


        // heavy stuff must be done here
        @Override
        protected Void doInBackground(Void... voids) {


            // this takes the data from the API
                try {
                    URL url = new URL("https://api.myjson.com/bins/65xvl"); //this gets data from the Swapi api
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // establish a connection
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";

                        while(line !=null)
                        {
                        line = bufferedReader.readLine();

                            try{
                                JSONArray JA = new JSONArray(data); // JSON array

                                for( int i = 0 ; i < JA.length() ; ++i)
                                {
                                    JSONObject JO = (JSONObject) JA.get(i); //gets each object in the JSON Object

                                    
                                    JSONObject retrieveJSONData = new JSONObject(JSONdata); // retrieves the JSON data

                                    JSONObject result = retrieveJSONData.getJSONObject("result"); // takes the result object from the api

                                    NewHope = result.getString("title"); // title of movie
                                    Intent passData = new Intent(MainActivity.this,ListViewActivity.class);
                                    passData.putExtra(Intent.EXTRA_TEXT,NewHope);
                                }//end for loop


                            }//end try block for the JSONARRAY
                            catch(JSONException e){

                            }//end catch

                        }//end while

                }//end try for the Http
        catch(MalformedURLException e){
                    e.printStackTrace();
        }//enc catch


            catch(IOException e){
                e.printStackTrace();
            }//end catch



        /*
                    try{
                        JSONArray JA = new JSONArray(data); // JSON array

                        for( int i = 0 ; i < JA.length() ; ++i)
                        {
                            JSONObject JO = (JSONObject) JA.get(i); //gets each object in the JSON Object

                            singleParsed = "results" + JO.get("results") + "\n"; // gets the name.
                            ParsedData = singleParsed + ParsedData;
                        JSONObject retrieveJSONData = new JSONObject(JSONdata); // retrieves the JSON data

                            JSONObject result = retrieveJSONData.getJSONObject("result"); // takes the result object from the api

                            NewHope = result.getString("title"); // title of movie
                            Intent passData = new Intent(MainActivity.this,ListViewActivity.class);
                            passData.putExtra(Intent.EXTRA_TEXT,NewHope);
                        }//end for loop


                    }//end try block for the JSONARRAY
                    catch (JSONException e) {
                        e.printStackTrace();
                    }



                }//end try
                catch(MalformedURLException e)
                {
                    e.printStackTrace();
                } catch(IOException e){
                    e.printStackTrace();
                }//end catch
                catch(NullPointerException e){
                    e.printStackTrace();
                }


                */

            return null;
        }//end Background method

        //what must happen when the background process is finish
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext()," Download Finished", Toast.LENGTH_LONG).show();
            bar.setProgress(View.GONE);// removes the progress bar

        /*
            try{
                JSONArray JA = new JSONArray(data); // JSON array

                for( int i = 0 ; i < JA.length() ; ++i)
                {
                    JSONObject JO = (JSONObject) JA.get(i); //gets each object in the JSON Object
                    singleParsed = "results" + JO.get("results") + "\n"; // gets the name.
                 //   ParsedData = singleParsed + ParsedData ;
                }//end for loop


            }//end try block for the JSONARRAY
            catch (JSONException e) {
                e.printStackTrace();
            }
            */



            // this passdata  across activities

             //startActivity(passData); // the data is sent to the activity



            finish(); // closes this current activity and opens a new one





            // here is going to open the new Activity.
            Intent newActivity = new Intent(MainActivity.this,ListViewActivity.class);
            startActivity(newActivity); // the data is sent to the activity


        }//end postExectute


        public void runMethod(){

            doThis instance = new doThis();

            instance.execute();//runs
        }

    }//end doThis class










}//end class MainActivity
