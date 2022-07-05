package com.yoga.listphone2022.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yoga.listphone2022.API.APIRequestData;
import com.yoga.listphone2022.API.RetroServer;
import com.yoga.listphone2022.Model.ResponseModel;
import com.yoga.listphone2022.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private int xId;
    private String xNama, xDeskripsi, xSubdeskripsi, xfoto;
    private EditText etNama, etDeskripsi, etSubdeskripsi,etfoto;
    private Button btnUbah;
    private String yNama, yDeskripsi, ySubdeskripsi, yfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xNama = terima.getStringExtra("xNama");
        xDeskripsi = terima.getStringExtra("xDeskripsi");
        xSubdeskripsi = terima.getStringExtra("xSubdeskripsi");
        xfoto = terima.getStringExtra("xFoto");

        etNama = findViewById(R.id.et_nama);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etSubdeskripsi = findViewById(R.id.et_subdeskripsi);
        etfoto = findViewById(R.id.et_foto);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(xNama);
        etDeskripsi.setText(xDeskripsi);
        etSubdeskripsi.setText(xSubdeskripsi);
        etfoto.setText(xfoto);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yNama = etNama.getText().toString();
                yDeskripsi = etDeskripsi.getText().toString();
                ySubdeskripsi = etSubdeskripsi.getText().toString();
                yfoto = etfoto.getText().toString();
                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yNama, yDeskripsi, ySubdeskripsi, yfoto);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}