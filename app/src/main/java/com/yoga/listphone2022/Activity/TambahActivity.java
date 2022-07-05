package com.yoga.listphone2022.Activity;

import androidx.appcompat.app.AppCompatActivity;

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


public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etdeskripsi, etsubdeskripsi, etfoto;
    private Button btnSimpan;
    private String nama, deskripsi, subdeskripsi, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etdeskripsi = findViewById(R.id.et_deskripsi);
        etsubdeskripsi = findViewById(R.id.et_subdeskripsi);
        etfoto = findViewById(R.id.et_foto);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = etNama.getText().toString();
                deskripsi = etdeskripsi.getText().toString();
                subdeskripsi = etsubdeskripsi.getText().toString();
                foto = etfoto.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }
                else if(deskripsi.trim().equals("")){
                    etdeskripsi.setError("Alamat Harus Diisi");
                }
                else if(subdeskripsi.trim().equals("")){
                    etsubdeskripsi.setError("Telepon Harus Diisi");
                }
                else if(foto.trim().equals("")){
                    etfoto.setError("link foto Harus Diisi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(nama, deskripsi, subdeskripsi, foto);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}