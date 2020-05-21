package com.example.unacademyserviceworker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    Button button1;
    Button button2;

    ServiceWorker serviceWorker1;
    ServiceWorker serviceWorker2;
    OkHttpClient client;

    String IMAGE_1 = "https://res.cloudinary.com/deeps2/image/upload/v1535372162/sample.jpg";
    String IMAGE_2 = "https://res.cloudinary.com/deeps2/image/upload/v1535975381/upload_with_preset1535975372372.jpg";

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


}
