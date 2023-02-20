package com.example.smartrestaurant.Admin.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartrestaurant.Admin.AdminActivity;
import com.example.smartrestaurant.Entry.LoginActivity;
import com.example.smartrestaurant.Entry.RegisterActivity;
import com.example.smartrestaurant.R;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddFoodActivity extends AppCompatActivity {
    private ImageView back,camera;
    private static final int GALLERYPICK = 1;
    private Uri ImageUri;
    private Button addphotobtn;
    private EditText namefood,textfood,foodprice;
    private String name,text,price,saveCurentDate,saveCurentTime,ProductRandomKey;
    private String downloadImageUrl;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        init();
        addphotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ValidateProductData();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               OpenGallery();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFoodActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ValidateProductData() {
        text = textfood.getText().toString();
        name = namefood.getText().toString();
        price = foodprice.getText().toString();
        if(ImageUri == null){
            Toast.makeText(this,"Добавьте изображение",Toast.LENGTH_SHORT).show();}
        else if(TextUtils.isEmpty(text)){
            Toast.makeText(this,"Добавьте описание",Toast.LENGTH_SHORT).show();}
        else if(TextUtils.isEmpty(price)){
            Toast.makeText(this,"Добавьте цену",Toast.LENGTH_SHORT).show();}
        else if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Добавьте название",Toast.LENGTH_SHORT).show();}
        else {
            StoreProductInformation();
        }

    }

    private void StoreProductInformation() {
        loadingBar.setTitle("Загрузка");
        loadingBar.setMessage("Пожалуйста, подождите...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat curentDate = new SimpleDateFormat("dd.MM.yyyy");
        saveCurentDate = curentDate.format(calendar.getTime());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat curentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurentTime = curentTime.format(calendar.getTime());
        ProductRandomKey = saveCurentDate + saveCurentTime;
        StorageReference filePath = ProductImageRef.child(ImageUri.getLastPathSegment() + ProductRandomKey + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri); //для того чтобы понять загрузилось изображение
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               String message = e.toString();
               Toast.makeText(AddFoodActivity.this,"Ошибка: " + message,Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddFoodActivity.this,"Изображение загружено", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AddFoodActivity.this,"Фото сохранено",Toast.LENGTH_SHORT).show();
                       SaveProductInfoToDataBase();
                        }
                    }
                });
            }
        });
    }

    private void SaveProductInfoToDataBase() {
        HashMap<String,Object> productMap = new HashMap<>();
        productMap.put("pid",ProductRandomKey);
        productMap.put("date",saveCurentDate);
        productMap.put("time",saveCurentTime);
        productMap.put("image",downloadImageUrl);
        productMap.put("foodprice",price);
        productMap.put("name_food",name);
        productMap.put("textfood",text);
        ProductsRef.child(ProductRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {  loadingBar.dismiss();
                    Toast.makeText(AddFoodActivity.this,"Товар добавлен", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String message = task.getException().toString();
                    Toast.makeText(AddFoodActivity.this, "Ошибка " + message, Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GALLERYPICK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERYPICK && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            camera.setImageURI(ImageUri);
        }
    }

    private void init()
    {
        back = findViewById(R.id.back);
        camera = findViewById(R.id.addphoto);
        addphotobtn = findViewById(R.id.addphotobtn);
        namefood = findViewById(R.id.name_food);
        textfood = findViewById(R.id.text_food);
        foodprice = findViewById(R.id.food_price);

        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Product_Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        loadingBar = new ProgressDialog(this);
        }
}