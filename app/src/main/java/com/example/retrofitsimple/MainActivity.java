package com.example.retrofitsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;

    NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.postInfoTextView);
    }

    public void showPostById(int id){
        networkService = NetworkService.getInstance();
        networkService.getWebsiteAPI()
                .getPostById(id)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post post = response.body();

                        textView.setText("");
                        textView.append(post.getId() + "\n");
                        textView.append(post.getUserId() + "\n");
                        textView.append(post.getTitle() + "\n");
                        textView.append(post.getBody() + "\n");
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }

    public void buttonClicked(View view) {

        int num;
        try{
            num = Integer.parseInt(editText.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            num = 1;
        }

        showPostById(num);
    }


}