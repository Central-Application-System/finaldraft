package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class payment extends AppCompatActivity {
    private static final int PICK_PDF_REQUEST = 1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onPayButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, PICK_PDF_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the URI of the selected PDF file
            Uri pdfUri = data.getData();

            // Upload the PDF file to Firebase Storage under user's payments
            uploadPdfToFirebase(pdfUri);
        }
    }

    private void uploadPdfToFirebase(Uri pdfUri) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Get the current user's ID
            String userId = currentUser.getUid();

            // Get a reference to the Firebase Storage
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();

            // Create a reference to the location where the PDF file will be stored in Firebase Storage
            StorageReference userPaymentsRef = storageRef.child("users").child(userId).child("payments");
            StorageReference pdfRef = userPaymentsRef.child(userId + ".pdf");

            // Upload the PDF file to Firebase Storage
            pdfRef.putFile(pdfUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // PDF upload successful
                        Toast.makeText(this, "Payment received, application successful", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // PDF upload failed
                        Toast.makeText(this, "Failed to upload PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            // Handle the case when the user is not logged in
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    public void payWithMOMO(View view) {
        Toast.makeText(this, "Service not available yet", Toast.LENGTH_SHORT).show();
    }
}