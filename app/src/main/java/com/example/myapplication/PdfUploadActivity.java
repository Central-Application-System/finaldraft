package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.Objects;

public class PdfUploadActivity extends AppCompatActivity {

    private static final int PDF_UPLOAD_REQUEST_CODE = 1;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_upload);

        storageReference = FirebaseStorage.getInstance().getReference();

        Button uploadPdfButton = findViewById(R.id.buttonUploadpdf);
        uploadPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PDF_UPLOAD_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_UPLOAD_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri pdfUri = data.getData();
            uploadPdf(pdfUri);
        }
    }

    private void uploadPdf(Uri pdfUri) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            StorageReference pdfReference = storageReference.child("pdfs/" + userId + "/results.pdf");

            pdfReference.putFile(pdfUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // PDF upload successful
                        Toast.makeText(PdfUploadActivity.this, "PDF uploaded successfully", Toast.LENGTH_SHORT).show();

                        // Navigate to the Questions activity
                        Intent intent = new Intent(PdfUploadActivity.this, questions.class);
                        startActivity(intent);
                        finish(); // Optional: finish the current activity if you don't want to come back to it
                    })
                    .addOnFailureListener(e -> {
                        // Handle PDF upload failure
                        Toast.makeText(PdfUploadActivity.this, "PDF upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}