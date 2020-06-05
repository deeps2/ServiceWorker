package com.example.unacademyserviceworker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    Button button1;
    Button button2;

    ServiceWorker serviceWorker1;
    ServiceWorker serviceWorker2;
    OkHttpClient client;

    String IMAGE_1 = "https://res.cloudinary.com/deeps2/image/upload/v1535372162/sample.jpg";
    String IMAGE_2 = "https://res.cloudinary.com/deeps2/image/upload/v1535975381/upload_with_preset1535975372372.jpg";

    /*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.first_image);
        imageView2 = findViewById(R.id.second_image);
        button1 = findViewById(R.id.button_one);
        button2 = findViewById(R.id.button_two);

        serviceWorker1 = new ServiceWorker("service_worker_1");
        serviceWorker2 = new ServiceWorker("service_worker_2");
        client = new OkHttpClient();

        button1.setOnClickListener(v -> fetchImage1AndSet());

        button2.setOnClickListener(v -> fetchImage2AndSet());
    }

    private void fetchImage1AndSet() {
        imageView1.setImageDrawable(null);

        serviceWorker1.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecuteTask() {
                Request request = new Request.Builder().url(IMAGE_1).build();
                Bitmap bitmap = null;
                try {
                    Response response = client.newCall(request).execute();
                    bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            public void onTaskComplete(Bitmap result) {
                if (result != null)
                    imageView1.setImageBitmap(result);
            }
        });
    }

    private void fetchImage2AndSet() {
        imageView2.setImageDrawable(null);

        serviceWorker2.addTask(new Task<Bitmap>() {
            @Override
            public Bitmap onExecuteTask() {
                Request request = new Request.Builder().url(IMAGE_2).build();
                Bitmap bitmap = null;
                try {
                    Response response = client.newCall(request).execute();
                    bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            public void onTaskComplete(Bitmap result) {
                if (result != null)
                    imageView2.setImageBitmap(result);
            }
        });
    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAsyncTask at = new MyAsyncTask();
        at.execute("thread_1");
        at.execute("thread_2");
        //new MyAsyncTask().execute("thread_1");
        //new MyAsyncTask().execute("thread_2");


        ServiceWorker serviceWorker = new ServiceWorker("hello");
        serviceWorker.addTask(new Task<String>() {
            @Override
            public String onExecuteTask() {
                //Log.d("TAG_1", "onExecuteTask() entered");

                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 5000) {

                }

                Log.d("TAG_1", "onExecuteTask() done");

                return "TAG_1";
            }

            @Override
            public void onTaskComplete(String output) {
                //Log.i("TAG_1", "onTaskComplete() 11 --> entered");

                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 7000) {

                }

                Log.i("TAG_1", "onTaskComplete() 11 --> " + output);

            }
        });


        serviceWorker.addTask(new Task<String>() {
            @Override
            public String onExecuteTask() {
                //Log.d("TAG_2", "onExecuteTask() entered");

                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 1000) {

                }

                Log.d("TAG_2", "onExecuteTask() done");

                return "TAG_2";
            }

            @Override
            public void onTaskComplete(String output) {
                //Log.i("TAG_1", "onTaskComplete() 22 --> entered");

                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 3000) {

                }

                Log.i("TAG_2", "onTaskComplete() 22 --> " + output);
            }
        });


    }


    public class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            int a = 1, b = 1, c = 1;
            a = 10;
            c = a;
            a = b;
            b = c;
            Log.d("TAGS", "onPreExecute");

        }

        @Override
        protected String doInBackground(String... strings) {
            int a = 1, b = 1, c = 1;
            a = 10;
            c = a;
            a = b;
            b = c;

            if (strings[0].equals("thread_1")) {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 5000) {

                }
                Log.i("TAGS", "doInBackground() " + strings[0]);
            } else {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 1000) {

                }
                Log.i("TAGS", "doInBackground() " + strings[0]);
            }


            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            int a = 1, b = 1, c = 1;
            a = 10;
            c = a;
            a = b;
            b = c;


            if (s.equals("thread_1")) {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 7000) {

                }
                Log.d("TAGS", "onPostExecute() " + s);

            } else {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 1000) {

                }
                Log.d("TAGS", "onPostExecute() " + s);
            }
        }
    }

}
